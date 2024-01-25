package com.sunknowledge.dme.rcm.service.impl.others;

import com.dropbox.core.InvalidAccessTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.UserMaster;
import com.sunknowledge.dme.rcm.repository.others.RoleMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.others.UserMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.UserInformationResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.UserMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.AccessDTO;
import com.sunknowledge.dme.rcm.service.dto.others.CreateUserMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.KeycloakUserInfoDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RSAKeyOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.others.UpdateUserMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.UpdateUserPasswordDTO;
import com.sunknowledge.dme.rcm.service.dto.others.UserMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.mapper.RoleMasterMapper;
import com.sunknowledge.dme.rcm.service.mapper.UserMasterMapper;
import com.sunknowledge.dme.rcm.service.others.UserMasterServiceExtended;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Primary
@Service
public class UserMasterServiceExtendedImpl implements UserMasterServiceExtended {

    private final Logger log = LoggerFactory.getLogger(UserMasterServiceExtendedImpl.class);
    @Autowired
    UserMasterRepositoryExtended userMasterRepositoryExtended;
    @Autowired
    UserMasterMapper userMasterMapper;

    @Autowired
    RoleMasterRepositoryExtended roleMasterRepositoryExtended;
    @Autowired
    RoleMasterMapper roleMasterMapper;

    private static void saveKeyToFile(String filePath, String keyName, byte[] keyBytes) throws Exception {
        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);

        Properties properties = new Properties();
        properties.setProperty(keyName, encodedKey);

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            properties.store(fileOutputStream, null);
        }
    }

    private static PublicKey readPublicKey() throws Exception {
        CommonUtilities commonUtilitiesObject = new CommonUtilities();
        Properties propData = commonUtilitiesObject.readPropertiesFile("/project-properties-files/user-info.properties");

        String encodedPublicKey = propData.getProperty("publicKey");
        byte[] publicKeyBytes = Base64.getDecoder().decode(encodedPublicKey);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
    }

    private static PrivateKey readPrivateKey() throws Exception {
        CommonUtilities commonUtilitiesObject = new CommonUtilities();
        Properties propData = commonUtilitiesObject.readPropertiesFile("/project-properties-files/user-info.properties");
        String encodedPrivateKey = propData.getProperty("privateKey");

        //String encodedPrivateKey = properties.getProperty("privateKey");
        byte[] privateKeyBytes = Base64.getDecoder().decode(encodedPrivateKey);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
    }

    private static String extractStatusCode(String responseHeader) {
        int startIndex = responseHeader.indexOf('<') + 1;
        int endIndex = responseHeader.indexOf(',');

        if (startIndex != -1 && endIndex != -1) {
            String statusCodeStr = responseHeader.substring(startIndex, endIndex).trim();
            return statusCodeStr;
        }

        return null; // Return -1 if status code is not found or cannot be parsed
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public UserMasterDTO save(UserMasterDTO userMasterDTO) {
        return null;
    }

    @Override
    public UserMasterDTO update(UserMasterDTO userMasterDTO) {
        return null;
    }

    @Override
    public Optional<UserMasterDTO> partialUpdate(UserMasterDTO userMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<UserMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<UserMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveUserMaster(CreateUserMasterExtendedDTO userMasterParameterDTO) throws JsonProcessingException, ParseException {
        //Set uniqueItemLocationNameSet = userMasterRepositoryExtended.findAll().stream().map(x -> x.get()).collect(Collectors.toSet());
        if (userMasterParameterDTO.getFirstName() == null) {
            return new ResponseDTO(false, "Invalid Attribute (First_Name)", null, 200);
        } else if (userMasterParameterDTO.getFirstName().trim() == "") {
            return new ResponseDTO(false, "Item_Location_Name must be provided", null, 200);
        }

        if (userMasterParameterDTO.getFirstName() == null || userMasterParameterDTO.getFirstName().trim().equals("")) {
            return new ResponseDTO(false, "First Name Should Not be Null/Blank.", null, 200);
        }
        if (userMasterParameterDTO.getLastName() == null || userMasterParameterDTO.getLastName().trim().equals("")) {
            return new ResponseDTO(false, "Last Name Should Not be Null/Blank.", null, 200);
        }
        if (userMasterParameterDTO.getEmail() == null || userMasterParameterDTO.getEmail().trim().equals("")) {
            return new ResponseDTO(false, "Email Should Not be Null/Blank.", null, 200);
        }
        if (userMasterParameterDTO.getUsername() == null || userMasterParameterDTO.getUsername().trim().equals("")) {
            return new ResponseDTO(false, "Username Should Not be Null/Blank.", null, 200);
        }
        if (userMasterParameterDTO.getPassword() == null || userMasterParameterDTO.getPassword().trim().equals("")) {
            return new ResponseDTO(false, "Password Should Not be Null/Blank.", null, 200);
        }
        if (userMasterParameterDTO.getRoleUUID() == null) {
            return new ResponseDTO(false, "Roll UUID Should Not be Null", null, 200);
        }

        UserMasterDTO userMasterDTO = new UserMasterDTO();

        List<UserMaster> userMasterList = userMasterRepositoryExtended.findByStatusIgnoreCase("active");
        System.out.println("======userMasterList=====" + userMasterList);
        boolean usernameExists = userMasterList.stream()
            .anyMatch(user -> Objects.equals(userMasterParameterDTO.getUsername(), user.getUsername()));

        if (usernameExists) {
            return new ResponseDTO(false, "Username Already Exists.", null, 200);
        }

        boolean userEmailExists = userMasterList.stream()
            .anyMatch(user -> Objects.equals(userMasterParameterDTO.getEmail(), user.getEmail()));

        if (userEmailExists) {
            return new ResponseDTO(false, "Email-Id Already Exists.", null, 200);
        }

        if (!isValidPassword(userMasterParameterDTO.getPassword())) {
            return new ResponseDTO(false, "Password Should Contain Minumum 8 Alphanumeric character(Maximum 20) which consists at least One Capital letter, One Special Character and One digit decimal number in any position.", null, 200);
        }

        RoleMasterDTO roleMasterDTO = roleMasterMapper.toDto(roleMasterRepositoryExtended.findByRoleMasterUuidAndStatusIgnoreCase(userMasterParameterDTO.getRoleUUID(), "active"));

        /**Keycloak Integration to create user in Keycloak as well**/
        ResponseDTO responseBody = new ResponseDTO();
        if (userMasterParameterDTO.getUsername() != null) {
            responseBody = createUserInKeycloak(userMasterParameterDTO);
            log.info("=======Response After Creating User In Keycloak========" + responseBody);
        }

        if (responseBody.getOutcome()) {
            String password = encryptPassword(userMasterParameterDTO.getPassword()); //Encrypt Password Using RSA Algorith

            BeanUtils.copyProperties(userMasterParameterDTO, userMasterDTO);
            userMasterDTO.setPassword(password);
            userMasterDTO.setUserId(null);
            userMasterDTO.setCreatedDate(LocalDate.now());
            userMasterDTO.setCreatedById(1L);
            userMasterDTO.setCreatedByName("Abhijit");
            userMasterDTO.setUserMasterUuid(UUID.randomUUID());

            UserMasterDTO savedUserMasterDTO = userMasterMapper.toDto(
                userMasterRepositoryExtended.save(userMasterMapper.toEntity(userMasterDTO))
            );

            if (roleMasterDTO.getRoleId() != null) {
                //Update in Role User Map in Keycloak then Update in t_role_user_map
            }

            JSONParser parser = new JSONParser();
            UserInformationResponseDTO userInformationResponseDTO = new UserInformationResponseDTO();
            BeanUtils.copyProperties(savedUserMasterDTO, userInformationResponseDTO);
            ObjectMapper mapper = new ObjectMapper();
            String jsonWithPassword = mapper.writerWithView(Scenarios.WithOutPassword.class).writeValueAsString(userInformationResponseDTO);
            JSONObject jsonObject = (JSONObject) parser.parse(jsonWithPassword);
            System.out.println("jsonWithPassword" + jsonWithPassword);
            System.out.println("==========jsonObject==========" + jsonObject);

            if (responseBody.getOutcome()) {
                return new ResponseDTO(true, "Successfully Saved.", (jsonObject), 200);
            } else {
                return new ResponseDTO(false, "User Creation in Keycloak failed.", responseBody.getData(), 200);
            }
        } else {
            return responseBody;
        }
    }

    @Override
    public ResponseDTO updateUserMaster(UpdateUserMasterExtendedDTO userMasterParameterDTO) {
        //Set uniqueItemLocationNameSet = userMasterRepositoryExtended.findAll().stream().map(x -> x.get()).collect(Collectors.toSet());
        if (userMasterParameterDTO.getFirstName() == null) {
            return new ResponseDTO(false, "Invalid Attribute (First_Name)", null, 200);
        } else if (userMasterParameterDTO.getFirstName().trim() == "") {
            return new ResponseDTO(false, "Item_Location_Name must be provided", null, 200);
        }

        if (userMasterParameterDTO.getFirstName() == null || userMasterParameterDTO.getFirstName().trim().equals("")) {
            return new ResponseDTO(false, "First Name Should Not be Null/Blank.", null, 200);
        }

        if (userMasterParameterDTO.getLastName() == null || userMasterParameterDTO.getLastName().trim().equals("")) {
            return new ResponseDTO(false, "Last Name Should Not be Null/Blank.", null, 200);
        }

        if (userMasterParameterDTO.getEmail() == null || userMasterParameterDTO.getEmail().trim().equals("")) {
            return new ResponseDTO(false, "Email Should Not be Null/Blank.", null, 200);
        }

        List<UserMaster> userMasterList = userMasterRepositoryExtended.findByStatusIgnoreCase("active");

        UserMasterDTO userMasterDTO = (userMasterParameterDTO.getUserId() == null ||
            userMasterParameterDTO.getUserId() == 0) ? new UserMasterDTO() :
            (userMasterRepositoryExtended.findById(userMasterParameterDTO.getUserId()).isPresent() ?
                userMasterMapper.toDto(userMasterRepositoryExtended.findById(userMasterParameterDTO.getUserId()).get()) :
                new UserMasterDTO());

        if (!userMasterDTO.getEmail().equals(userMasterParameterDTO.getEmail().trim())) {
            if (userMasterList.size() > 0) {
                for (UserMaster user : userMasterList) {
                    if (user.getEmail().equals(userMasterParameterDTO.getEmail().trim())) {
                        return new ResponseDTO(false, "Email-Id Already Exists.", null, 200);
                    }
                }
            }
        }
        /**Keycloak Integration to update user in Keycloak as well**/
        ResponseDTO responseBody = new ResponseDTO();
        if (userMasterDTO.getUserId() != null) {
            responseBody = getUserInfoFromKeycloak(userMasterDTO.getUsername());
            if (responseBody.getOutcome() && responseBody.getStatusCode() == 200) {
                log.info("=======Output Before Update======" + (KeycloakUserInfoDTO) responseBody.getData());
                responseBody = updateUserInKeycloak(userMasterDTO, (KeycloakUserInfoDTO) responseBody.getData());
            }
            log.info("=======Response After Creating User In Keycloak========" + responseBody);
        }

        BeanUtils.copyProperties(userMasterParameterDTO, userMasterDTO);
        userMasterDTO.setUpdatedDate(LocalDate.now());
        userMasterDTO.setUpdatedById(1L);
        userMasterDTO.setUpdatedByName("Abhijit");

        UserMasterDTO savedUserMasterDTO = userMasterMapper.toDto(
            userMasterRepositoryExtended.save(userMasterMapper.toEntity(userMasterDTO))
        );

        if (responseBody.getOutcome()) {
            return new ResponseDTO(true, "Successfully Updated.", (savedUserMasterDTO), 200);
        } else {
            return new ResponseDTO(false, "User Information Update in Keycloak failed.", responseBody.getData(), responseBody.getStatusCode());
        }

    }

    @Override
    public List<UserMasterDTO> findByUserIdIn(List<Long> userIdList) {
        List<UserMaster> data = userMasterRepositoryExtended.findByUserIdInAndStatusIgnoreCase(userIdList, "active");
        return userMasterMapper.toDto(data);
    }

    @Override
    public UserMasterDTO getDataByActiveId(Long userId) {
        return userMasterMapper.toDto(userMasterRepositoryExtended.findByUserIdAndStatusIgnoreCase(userId, "active"));
    }

    @Override
    public Long getIDByUUID(UUID roleUUID) {
        return userMasterRepositoryExtended.getIDByUUID(roleUUID);
    }

    @Override
    public ResponseDTO getAllUserMasterData() {
        List<UserMaster> userMasters = userMasterRepositoryExtended.findByStatusIgnoreCase("active");
        List<UserMasterExtendedDTO> userMasterExtendedDTOS = new ArrayList<>();

        for (UserMaster userMaster : userMasters) {
            UserMasterExtendedDTO userMasterExtendedDTO = new UserMasterExtendedDTO();
            BeanUtils.copyProperties(userMaster, userMasterExtendedDTO);
            userMasterExtendedDTO.setId(userMaster.getUserId());
            userMasterExtendedDTO.setTitle(CommonUtilities.mergeName(userMaster.getFirstName(), userMaster.getMiddleName(), userMaster.getLastName()));
            userMasterExtendedDTOS.add(userMasterExtendedDTO);
        }
        return new ResponseDTO(userMasterExtendedDTOS.size() > 0 ? true : false,
            userMasterExtendedDTOS.size() > 0 ? "" : "Data Not Found.",
            userMasterExtendedDTOS, 200);
    }

    @Override
    public ResponseDTO createPrivatePublicKey() {
        RSAKeyOutputDTO rsaKeyOutputDTO = new RSAKeyOutputDTO();
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            KeyPair pair = generator.generateKeyPair();

            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();

            String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            //saveKeyToFile("src/main/resources/project-properties-files/rsa_private_key.properties", "privateKey", privateKey.getEncoded());
            //saveKeyToFile("src/main/resources/project-properties-files/rsa_public_key.properties", "publicKey", publicKey.getEncoded());
            rsaKeyOutputDTO.setPrivateKey(encodedPrivateKey);
            rsaKeyOutputDTO.setPublicKey(encodedPublicKey);
            System.out.println("publicKey==>" + publicKey);
            System.out.println("privateKey==>" + privateKey);

            if (rsaKeyOutputDTO.getPrivateKey() != null && !rsaKeyOutputDTO.getPrivateKey().equals("")) {
                return new ResponseDTO(true, "Successfully Created.", (rsaKeyOutputDTO), 200);
            } else {
                return new ResponseDTO(true, "RSA Key Generation Failed.", (rsaKeyOutputDTO), 200);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String encryptPassword(String plainString) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            //PublicKey publicKey = (PublicKey) mapKeyForRSA.get("publicKey");

            PublicKey publicKey = readPublicKey();
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encrypt = cipher.doFinal(plainString.getBytes());
            return new String(Base64.getEncoder().encodeToString(encrypt));
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String decryptPassword(String encryptedString) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            //PrivateKey privateKey = (PrivateKey) mapKeyForRSA.get("privateKey");
            PrivateKey privateKey = readPrivateKey();
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
            return new String(decrypt);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ResponseDTO createUserInKeycloak(CreateUserMasterExtendedDTO userMasterParameterDTO) { //UserMasterDTO savedUserMasterDTO
        ResponseDTO responseBody = new ResponseDTO();
        Map userMasterDataKeyCloakDTO = new HashMap();
        System.out.println("=====userMasterParameterDTO=====" + userMasterParameterDTO);

        Map<String, Object> user = new HashMap<>();
        user.put("username", userMasterParameterDTO.getUsername());
        user.put("firstName", userMasterParameterDTO.getFirstName());
        user.put("lastName", userMasterParameterDTO.getLastName());
        user.put("email", userMasterParameterDTO.getEmail());
        user.put("enabled", true);

        List<Map<String, Object>> credentialsList = new ArrayList<>();
        Map<String, Object> credentials = new HashMap<>();
        credentials.put("type", "password");
        credentials.put("value", userMasterParameterDTO.getPassword());
        credentials.put("temporary", false);
        credentialsList.add(credentials);
        user.put("credentials", credentialsList);

        try {
            //==================== Get the access token ====================
            //String accessToken = InternalAccessTokenUtilities.getAccessTokenByParam("http://localhost:9080/auth/realms/jhipster/protocol/openid-connect/token","client_credentials", "jhipster-registry", "jhipster-registry", "openid profile email", "jhipster");
            String accessToken = InternalAccessTokenUtilities.getAccessTokenForKeycloak();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            System.out.println("========token=========" + token);
            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                //===================== Call the MS API with Token =============
                CommonUtilities commonUtilitiesObj = new CommonUtilities();
                Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

                String completeUrl = propData.getProperty("create_user_keycloak_url");
                //String param="";
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();

                headersData.add("Authorization", "Bearer " + token);
                headersData.add("Content-Type", "application/json"); //;charset=utf-8
                //=====Parsing set of parameters(input DTO) for Entry MicroService
                HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(user, headersData);
                ResponseEntity<String> responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
                );
                System.out.println("=========responseData==========" + responseData);
                String statusCode = extractStatusCode(responseData.toString());
                System.out.println("Status Code: " + statusCode);

                if (statusCode.startsWith("2")) {
                    responseBody.setOutcome(true);
                    responseBody.setMessage("User Successfully Created In Keycloak.");
                    responseBody.setData(responseData.toString());
                    responseBody.setStatusCode(200);
                } else {
                    responseBody.setOutcome(false);
                    responseBody.setMessage("API Error: No Response Data Available");
                    responseBody.setData(new JSONArray());
                    responseBody.setStatusCode(404);
                }
            } else {
                responseBody.setOutcome(false);
                responseBody.setMessage("Missing Access Token");
                responseBody.setData(new JSONArray());
                responseBody.setStatusCode(401);
            }
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                try {
                    throw new InvalidAccessTokenException("REQUEST_CODE_498", "Invalid Access Token - " + e.getMessage());
                } catch (InvalidAccessTokenException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request: Request Datatype Mismatch/Error in Request Body");
            } else {
                System.out.println("=======Error========" + e);
                throw new RuntimeException(e);
            }
        } catch (HttpServerErrorException e) {
            // Server-side errors (5xx)
            if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
                throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
            } else {
                throw new RuntimeException(e);
            }

        } catch (ResourceAccessException e) {
            // Timeouts, connection refused, etc.
            throw new RuntimeException(e);
        } catch (Exception e) {
            // Any other exception
            throw new RuntimeException(e);
        }

        return responseBody;
    }

    public ResponseDTO getUserInfoFromKeycloak(String username) {
        ResponseDTO responseBody = new ResponseDTO();

        try {
            //==================== Get the access token ====================
            //String accessToken = InternalAccessTokenUtilities.getAccessTokenByParam("http://localhost:9080/auth/realms/jhipster/protocol/openid-connect/token","client_credentials", "jhipster-registry", "jhipster-registry", "openid profile email", "jhipster");
            String accessToken = InternalAccessTokenUtilities.getAccessTokenForKeycloak();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            log.info("========token=========" + token);
            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                //===================== Call the MS API with Token =============
                CommonUtilities commonUtilitiesObj = new CommonUtilities();
                Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

                String param = propData.getProperty("get_user_info_keycloak_url");
                String completeUrl = param + username;
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();

                headersData.add("Authorization", "Bearer " + token);
                //headersData.add("Content-Type", "application/json"); //;charset=utf-8
                //=====Parsing set of parameters(input DTO) for Entry MicroService
                //HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(user, headersData);
                HttpEntity<String> requestEntity = new HttpEntity<>(headersData);
                ResponseEntity<List> responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.GET,
                    requestEntity,
                    List.class
                );
                log.info("=========responseData==========" + responseData);
                String statusCode = extractStatusCode(responseData.toString());
                log.info("Status Code: " + statusCode);

                if (statusCode.startsWith("2")) {
                    if (responseData.getStatusCode().is2xxSuccessful() && responseData.getBody() != null && !responseData.getBody().isEmpty()) {
                        //return (Map<String, Object>) responseData.getBody().get(0);
                        Map<String, Object> userMap = (Map<String, Object>) responseData.getBody().get(0);
                        KeycloakUserInfoDTO keycloakUserInfoDTO = new KeycloakUserInfoDTO();
                        log.info("=======userMap========" + userMap);
                        keycloakUserInfoDTO.setId((String) userMap.get("id"));
                        keycloakUserInfoDTO.setCreatedTimestamp((Long) userMap.get("createdTimestamp"));
                        keycloakUserInfoDTO.setUsername((String) userMap.get("username"));
                        keycloakUserInfoDTO.setEnabled((Boolean) userMap.get("enabled"));
                        keycloakUserInfoDTO.setTotp((Boolean) userMap.get("totp"));
                        keycloakUserInfoDTO.setEmailVerified((Boolean) userMap.get("emailVerified"));
                        keycloakUserInfoDTO.setFirstName((String) userMap.get("firstName"));
                        keycloakUserInfoDTO.setLastName((String) userMap.get("lastName"));
                        keycloakUserInfoDTO.setEmail((String) userMap.get("email"));
                        keycloakUserInfoDTO.setDisableableCredentialTypes((List) userMap.get("disableableCredentialTypes"));
                        keycloakUserInfoDTO.setRequiredActions((List) userMap.get("requiredActions"));
                        AccessDTO access = new AccessDTO();
                        // Handle the "access" part
                        Map<String, Boolean> accessMap = (Map<String, Boolean>) userMap.get("access");
                        if (accessMap != null) {
                            access.setManageGroupMembership((Boolean) accessMap.get("manageGroupMembership"));
                            access.setView((Boolean) accessMap.get("view"));
                            access.setMapRoles((Boolean) accessMap.get("mapRoles"));
                            access.setImpersonate((Boolean) accessMap.get("impersonate"));
                            access.setManage((Boolean) accessMap.get("manage"));
                            keycloakUserInfoDTO.setAccess(access);
                        }
                        log.info("=======Fetched User Data========" + keycloakUserInfoDTO);
                        responseBody.setOutcome(true);
                        responseBody.setMessage("User Successfully Fetched From Keycloak.");
                        responseBody.setData(keycloakUserInfoDTO);
                        responseBody.setStatusCode(Integer.parseInt(statusCode));
                    } else {
                        log.info("Failed to get user info. Status code: " + responseData.getStatusCode());
                        responseBody.setOutcome(true);
                        responseBody.setMessage("User Successfully Fetched From Keycloak.");
                        responseBody.setData(responseData.toString());
                        responseBody.setStatusCode(Integer.parseInt(statusCode));
                    }
                } else {
                    responseBody.setOutcome(false);
                    responseBody.setMessage("API Error: No Response Data Available");
                    responseBody.setData(new JSONArray());
                    responseBody.setStatusCode(404);
                }
            } else {
                responseBody.setOutcome(false);
                responseBody.setMessage("Missing Access Token");
                responseBody.setData(new JSONArray());
                responseBody.setStatusCode(401);
            }
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                try {
                    throw new InvalidAccessTokenException("REQUEST_CODE_498", "Invalid Access Token - " + e.getMessage());
                } catch (InvalidAccessTokenException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request: Request Datatype Mismatch/Error in Request Body");
            } else {
                System.out.println("=======Error========" + e);
                throw new RuntimeException(e);
            }
        } catch (HttpServerErrorException e) {
            // Server-side errors (5xx)
            if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
                throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
            } else {
                throw new RuntimeException(e);
            }

        } catch (ResourceAccessException e) {
            // Timeouts, connection refused, etc.
            throw new RuntimeException(e);
        } catch (Exception e) {
            // Any other exception
            throw new RuntimeException(e);
        }

        return responseBody;
    }

    private ResponseDTO updateUserInKeycloak(UserMasterDTO userMasterDTO, KeycloakUserInfoDTO keycloakUserInfoDTO) { //UserMasterDTO savedUserMasterDTO
        ResponseDTO responseBody = new ResponseDTO();
        Map userMasterDataKeyCloakDTO = new HashMap();
        log.info("=====savedUserMasterDTO=====" + userMasterDTO);

        Map<String, Object> user = new HashMap<>();
        //user.put("username", savedUserMasterDTO.getUsername());
        user.put("firstName", userMasterDTO.getFirstName());
        user.put("lastName", userMasterDTO.getLastName());
        user.put("email", userMasterDTO.getEmail());
        //user.put("enabled", true);

        /*List<Map<String, Object>> credentialsList = new ArrayList<>();
        Map<String, Object> credentials = new HashMap<>();
        credentials.put("type", "password");
        credentials.put("value", savedUserMasterDTO.getPassword());
        credentials.put("temporary", false);
        credentialsList.add(credentials);
        user.put("credentials", credentialsList);*/

        try {
            //==================== Get the access token ====================
            //String accessToken = InternalAccessTokenUtilities.getAccessTokenByParam("http://localhost:9080/auth/realms/jhipster/protocol/openid-connect/token","client_credentials", "jhipster-registry", "jhipster-registry", "openid profile email", "jhipster");
            String accessToken = InternalAccessTokenUtilities.getAccessTokenForKeycloak();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            System.out.println("========token=========" + token);
            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                //===================== Call the MS API with Token =============
                CommonUtilities commonUtilitiesObj = new CommonUtilities();
                Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

                String param = propData.getProperty("update_user_keycloak_url");
                String completeUrl = param + keycloakUserInfoDTO.getId();
                System.out.println("=======completeUrl=======" + completeUrl);
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();

                headersData.add("Authorization", "Bearer " + token);
                headersData.add("Content-Type", "application/json"); //;charset=utf-8
                //=====Parsing set of parameters(input DTO) for Entry MicroService
                HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(user, headersData);
                ResponseEntity<String> responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.PUT,
                    requestEntity,
                    String.class
                );
                log.info("=========responseData==========" + responseData);
                String statusCode = extractStatusCode(responseData.toString());
                log.info("Status Code: " + statusCode);

                if (statusCode.startsWith("2")) {
                    responseBody.setOutcome(true);
                    responseBody.setMessage("User Info Successfully Updated In Keycloak.");
                    responseBody.setData(responseData.toString());
                    responseBody.setStatusCode(Integer.parseInt(statusCode));
                } else {
                    responseBody.setOutcome(false);
                    responseBody.setMessage("API Error: No Response Data Available");
                    responseBody.setData(new JSONArray());
                    responseBody.setStatusCode(Integer.parseInt(statusCode));
                }
            } else {
                responseBody.setOutcome(false);
                responseBody.setMessage("Missing Access Token");
                responseBody.setData(new JSONArray());
                responseBody.setStatusCode(401);
            }
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                try {
                    throw new InvalidAccessTokenException("REQUEST_CODE_498", "Invalid Access Token - " + e.getMessage());
                } catch (InvalidAccessTokenException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request: Request Datatype Mismatch/Error in Request Body");
            } else {
                System.out.println("=======Error========" + e);
                throw new RuntimeException(e);
            }
        } catch (HttpServerErrorException e) {
            // Server-side errors (5xx)
            if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
                throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
            } else {
                throw new RuntimeException(e);
            }

        } catch (ResourceAccessException e) {
            // Timeouts, connection refused, etc.
            throw new RuntimeException(e);
        } catch (Exception e) {
            // Any other exception
            throw new RuntimeException(e);
        }

        return responseBody;
    }

    @Override
    public ResponseDTO updateUserPassword(UpdateUserPasswordDTO updateUserPasswordDTO) throws IOException {
        if (updateUserPasswordDTO.getEmailId() == null || updateUserPasswordDTO.getEmailId().trim().equals("")) {
            return new ResponseDTO(false, "Email-Id Should Not be Null/Blank.", null, 200);
        }
        if (updateUserPasswordDTO.getUserName() == null || updateUserPasswordDTO.getUserName().trim().equals("")) {
            return new ResponseDTO(false, "User Name Should Not be Null/Blank.", null, 200);
        }
        if (updateUserPasswordDTO.getOldPassword() == null || updateUserPasswordDTO.getOldPassword().trim().equals("")) {
            return new ResponseDTO(false, "Password Should Not be Null/Blank.", null, 200);
        }
        if (updateUserPasswordDTO.getNewPassword() == null || updateUserPasswordDTO.getNewPassword().trim().equals("")) {
            return new ResponseDTO(false, "Password Should Not be Null/Blank.", null, 200);
        }
        if (updateUserPasswordDTO.getConfirmNewPassword() == null || updateUserPasswordDTO.getConfirmNewPassword().trim().equals("")) {
            return new ResponseDTO(false, "Confirm Password Should Not be Null/Blank.", null, 200);
        }

        if (!updateUserPasswordDTO.getNewPassword().equals(updateUserPasswordDTO.getConfirmNewPassword())) {
            return new ResponseDTO(false, "Confirm Password Should Be Same As New Password.", null, 200);
        }

        if (!isValidPassword(updateUserPasswordDTO.getNewPassword())) {
            return new ResponseDTO(false, "Password Should Contain Minumum 8 Alphanumeric character(Maximum 20) which consists at least One Capital letter, One Special Character and One digit decimal number in any position.", null, 200);
        }

        ResponseDTO responseBody;
        KeycloakUserInfoDTO keycloakUserInfoDTO = new KeycloakUserInfoDTO();
        UserMasterDTO userMasterDTO = userMasterMapper.toDto(userMasterRepositoryExtended.findByUsernameAndEmailAndStatusIgnoreCase(updateUserPasswordDTO.getUserName(), updateUserPasswordDTO.getEmailId(), "active"));
        System.out.println("=============userMasterDTO=============" + userMasterDTO);
        String userNewPasssword = null;
        if (userMasterDTO.getUserId() != null && userMasterDTO.getPassword() != null) {
            System.out.println("==========userMasterDTO.getUserId()==========" + userMasterDTO.getUserId());
            RoleMasterDTO roleMasterDTO = roleMasterMapper.toDto(roleMasterRepositoryExtended.findRoleNameByUserIdAndStatus(userMasterDTO.getUserId(), "active"));
            CommonUtilities commonUtilitiesObj = new CommonUtilities();
            Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

            String adminUserType = propData.getProperty("admin_user_type_keycloak");
            if (roleMasterDTO.getRoleId() != null && !roleMasterDTO.getRoleName().equals(adminUserType)) {
                System.out.println("==========roleMasterDTO===========" + roleMasterDTO);
                String oldPassword = decryptPassword(userMasterDTO.getPassword());
                System.out.println("========oldPassword======" + oldPassword);
                System.out.println("========updateUserPasswordDTO.getOldPassword()==========" + updateUserPasswordDTO.getOldPassword());
                if (oldPassword.equals(updateUserPasswordDTO.getOldPassword())) {
                    return updateUserPasswordInDBAndKeycloakCommonCode(updateUserPasswordDTO, userMasterDTO, keycloakUserInfoDTO, propData);
                } else {
                    return new ResponseDTO(false, "Invalid Attempt.", null, 200);
                }
            } else if (roleMasterDTO.getRoleId() != null && roleMasterDTO.getRoleName().equals("admin")) {
                //Do not need to check Old Password
                return updateUserPasswordInDBAndKeycloakCommonCode(updateUserPasswordDTO, userMasterDTO, keycloakUserInfoDTO, propData);
            } else {
                return new ResponseDTO(false, "Illegal Attempt.", null, 200);
            }

        } else {
            return new ResponseDTO(false, "Invalid Username Or Email.", null, 200);
        }
    }

    private ResponseDTO updateUserPasswordInDBAndKeycloakCommonCode(UpdateUserPasswordDTO updateUserPasswordDTO, UserMasterDTO userMasterDTO, KeycloakUserInfoDTO keycloakUserInfoDTO,
                                                                    Properties propData) {
        ResponseDTO responseBody;
        //Check And Compare User Name in Keycloak
        responseBody = getUserInfoFromKeycloak(userMasterDTO.getUsername());
        if (responseBody.getOutcome() && responseBody.getStatusCode() == 200) {
            keycloakUserInfoDTO = (KeycloakUserInfoDTO) responseBody.getData();
            if (keycloakUserInfoDTO.getUsername().equals(updateUserPasswordDTO.getUserName().trim())) {
                //Old Password Matched in DB and User Name Matched in Keycloak Then We can change password
                //Update password in keycloak as well as in DB
                responseBody = updateUserPasswordInKeycloak(userMasterDTO, (KeycloakUserInfoDTO) responseBody.getData(), updateUserPasswordDTO.getNewPassword(), propData);
                System.out.println("===========After Updating Keycloak Password============" + responseBody);
                if (responseBody.getOutcome()) {
                    //BeanUtils.copyProperties(userMasterParameterDTO, userMasterDTO);
                    //Do Password encryption before Updation in DB
                    String encryptedUser = encryptPassword(updateUserPasswordDTO.getNewPassword());
                    userMasterDTO.setPassword(encryptedUser);
                    userMasterDTO.setUpdatedDate(LocalDate.now());
                    userMasterDTO.setUpdatedById(31L);
                    userMasterDTO.setUpdatedByName("Falguni");

                    UserMasterDTO savedUserMasterDTO = userMasterMapper.toDto(
                        userMasterRepositoryExtended.save(userMasterMapper.toEntity(userMasterDTO))
                    );

                    if (responseBody.getOutcome()) {
                        return new ResponseDTO(true, "Successfully Updated.", (savedUserMasterDTO), 200);
                    } else {
                        return new ResponseDTO(false, "User Password Update failed.", responseBody.getData(), responseBody.getStatusCode());
                    }
                } else {
                    return responseBody;
                }
            } else {
                return new ResponseDTO(false, "Invalid Credential.", null, 200);
            }
        } else {
            return responseBody;
        }
    }

    private ResponseDTO updateUserPasswordInKeycloak(UserMasterDTO userMasterDTO, KeycloakUserInfoDTO keycloakUserInfoDTO, String newPassword, Properties propData) {
        ResponseDTO responseBody = new ResponseDTO();
        log.info("=====savedUserMasterDTO=====" + userMasterDTO);
        // Build the request body with the new password
        String requestBody = "{ \"credentials\": [ { \"value\": \"" + newPassword + "\", \"type\": \"password\" } ] }";
        try {
            //==================== Get the access token ====================
            //String accessToken = InternalAccessTokenUtilities.getAccessTokenByParam("http://localhost:9080/auth/realms/jhipster/protocol/openid-connect/token","client_credentials", "jhipster-registry", "jhipster-registry", "openid profile email", "jhipster");
            String accessToken = InternalAccessTokenUtilities.getAccessTokenForKeycloak();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            log.info("========token=========" + token);
            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                //===================== Call the MS API with Token =============
                //CommonUtilities commonUtilitiesObj = new CommonUtilities();
                //Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

                String param = propData.getProperty("generic_keycloak_url");
                String completeUrl = param + keycloakUserInfoDTO.getId();
                System.out.println("=======completeUrl=======" + completeUrl);
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();

                headersData.add("Authorization", "Bearer " + token);
                headersData.add("Content-Type", "application/json"); //;charset=utf-8
                //=====Parsing set of parameters(input DTO) for Entry MicroService
                HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headersData);
                ResponseEntity<String> responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.PUT,
                    requestEntity,
                    String.class
                );
                log.info("=========responseData==========" + responseData);
                String statusCode = extractStatusCode(responseData.toString());
                log.info("Status Code: " + statusCode);

                if (statusCode.startsWith("2")) {
                    responseBody.setOutcome(true);
                    responseBody.setMessage("User Info Successfully Updated In Keycloak.");
                    responseBody.setData(responseData.toString()); //responseData.toString()
                    responseBody.setStatusCode(Integer.parseInt(statusCode));
                } else {
                    responseBody.setOutcome(false);
                    responseBody.setMessage("API Error: No Response Data Available");
                    responseBody.setData(new JSONArray());
                    responseBody.setStatusCode(Integer.parseInt(statusCode));
                }
            } else {
                responseBody.setOutcome(false);
                responseBody.setMessage("Missing Access Token");
                responseBody.setData(new JSONArray());
                responseBody.setStatusCode(401);
            }
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                try {
                    throw new InvalidAccessTokenException("REQUEST_CODE_498", "Invalid Access Token - " + e.getMessage());
                } catch (InvalidAccessTokenException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request: Request Datatype Mismatch/Error in Request Body");
            } else {
                System.out.println("=======Error========" + e);
                throw new RuntimeException(e);
            }
        } catch (HttpServerErrorException e) {
            // Server-side errors (5xx)
            if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
                throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
            } else {
                throw new RuntimeException(e);
            }

        } catch (ResourceAccessException e) {
            // Timeouts, connection refused, etc.
            throw new RuntimeException(e);
        } catch (Exception e) {
            // Any other exception
            throw new RuntimeException(e);
        }

        return responseBody;
    }

    @Override
    public ResponseDTO activeOrDeactiveUserByUuid(UUID uuid, Boolean isDeactive) {
        ResponseDTO responseBody = new ResponseDTO();
        KeycloakUserInfoDTO keycloakUserInfoDTO;
        if (uuid == null || uuid.equals("")) {
            return (new ResponseDTO(Boolean.FALSE, "uuid Should Not Be Null/Blank.", null, 200));
        }

        UserMasterDTO userMasterDTO = userMasterMapper.toDto(userMasterRepositoryExtended.findByUserMasterUuidAndStatusIgnoreCase(uuid, "active"));
        log.info("==========userMasterDTO==========" + userMasterDTO);
        if (isDeactive && userMasterDTO.getIsDeactivate()) {
            return new ResponseDTO(false, "User Is Already Deactivated.", null, 200);
        }
        if (!isDeactive && !userMasterDTO.getIsDeactivate()) {
            return new ResponseDTO(false, "User Is Already Activated.", null, 200);
        }

        if (userMasterDTO.getUserId() != null) {
            return activateOrDeactivateUserCommonCode(userMasterDTO, isDeactive);
        } else {
            return (new ResponseDTO(Boolean.FALSE, "User UUID Is Not Valid. ", null, 200));
        }
    }

    private ResponseDTO activateOrDeactivateUserCommonCode(UserMasterDTO userMasterDTO, Boolean isDeactive) {
        ResponseDTO responseBody;
        if (userMasterDTO.getUserId() != null) {
            responseBody = getUserInfoFromKeycloak(userMasterDTO.getUsername());
            log.info("==============getUserInfoFromKeycloak=============" + responseBody);
            KeycloakUserInfoDTO keycloakUserInfoObj = (KeycloakUserInfoDTO) responseBody.getData();
            log.info("============keycloakUserInfoObj============" + keycloakUserInfoObj);
            if (responseBody.getOutcome() && (keycloakUserInfoObj.getId() != null)) {
                if (keycloakUserInfoObj.getEnabled() && !userMasterDTO.getIsDeactivate()) {
                    //Perform User Dactivation in KeyCloak First and Then In DB
                    System.out.println("Ready To Deactivate");
                    return updateActivateOrDeactivateUserInfo(userMasterDTO, isDeactive, keycloakUserInfoObj, "Deactive");
                } else if (!keycloakUserInfoObj.getEnabled() && userMasterDTO.getIsDeactivate()) {
                    //Perform User Reactivation in KeyCloak First and Then In DB
                    System.out.println("Ready To Reactivate");
                    return updateActivateOrDeactivateUserInfo(userMasterDTO, isDeactive, keycloakUserInfoObj, "Active");
                }
            } else {
                return responseBody;
            }
        } else {
            return (new ResponseDTO(Boolean.FALSE, "User Does Not Exist.", null, 200));
        }
        return responseBody;
    }

    private ResponseDTO updateActivateOrDeactivateUserInfo(UserMasterDTO userMasterDTO, Boolean isDeactive, KeycloakUserInfoDTO keycloakUserInfoObj, String operationType) {
        ResponseDTO responseBody = new ResponseDTO();
        try {
            String msg = "";
            if (operationType.equals("Deactive")) {
                responseBody = deactivateOrReactiveUserInKeycloakByUserId(keycloakUserInfoObj.getId(), Boolean.FALSE);
                msg = "Deactivated.";
                userMasterDTO.setIsDeactivate(Boolean.TRUE);
            } else if (operationType.equals("Active")) {
                responseBody = deactivateOrReactiveUserInKeycloakByUserId(keycloakUserInfoObj.getId(), Boolean.TRUE);
                msg = "Activated.";
                userMasterDTO.setIsDeactivate(Boolean.FALSE);
            } else {
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error.", null, 200));
            }

            if (responseBody.getOutcome()) {
                userMasterDTO.setUpdatedDate(LocalDate.now());
                userMasterDTO.setUpdatedById(31L);
                userMasterDTO.setUpdatedByName("Falguni");

                UserMasterDTO savedUserMasterDTO = userMasterMapper.toDto(
                    userMasterRepositoryExtended.save(userMasterMapper.toEntity(userMasterDTO))
                );
                log.info("=======savedUserMasterDTO========" + savedUserMasterDTO);
                if (responseBody.getOutcome()) {
                    return new ResponseDTO(true, "Successfully User " + msg, (savedUserMasterDTO), 200);
                } else {
                    return new ResponseDTO(false, "User Deactivativation failed.", responseBody.getData(), responseBody.getStatusCode());
                }
            } else {
                return responseBody;
            }
        } catch (Exception e) {
            log.error("=====>> Error : " + e);
            return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error.", null, 200));
        }
    }

    private ResponseDTO deactivateOrReactiveUserInKeycloakByUserId(String userIdForKeycloak, Boolean deactive) { //UserMasterDTO savedUserMasterDTO
        ResponseDTO responseBody = new ResponseDTO();
        log.info("=====userIdForKeycloak=====" + userIdForKeycloak + "=====deactive=====" + deactive);
        // Create the user deactivation JSON payload
        String deactivationUserJson = "{ \"enabled\": " + deactive + " }";

        try {
            //==================== Get the access token ====================
            //String accessToken = InternalAccessTokenUtilities.getAccessTokenByParam("http://localhost:9080/auth/realms/jhipster/protocol/openid-connect/token","client_credentials", "jhipster-registry", "jhipster-registry", "openid profile email", "jhipster");
            String accessToken = InternalAccessTokenUtilities.getAccessTokenForKeycloak();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            log.info("========token=========" + token);
            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                //===================== Call the MS API with Token =============
                CommonUtilities commonUtilitiesObj = new CommonUtilities();
                Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

                String param = propData.getProperty("update_user_keycloak_url");
                String completeUrl = param + userIdForKeycloak;
                log.info("=======completeUrl=======" + completeUrl);
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();

                headersData.add("Authorization", "Bearer " + token);
                headersData.add("Content-Type", "application/json"); //;charset=utf-8
                //=====Parsing set of parameters(input DTO) for Entry MicroService
                HttpEntity<String> requestEntity = new HttpEntity<>(deactivationUserJson, headersData);
                ResponseEntity<String> responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.PUT,
                    requestEntity,
                    String.class
                );
                log.info("=========responseData==========" + responseData);
                String statusCode = extractStatusCode(responseData.toString());
                log.info("Status Code: " + statusCode);

                if (statusCode.startsWith("2")) {
                    responseBody.setOutcome(true);
                    responseBody.setMessage("User Deactivation Successfully Updated In Keycloak.");
                    responseBody.setData(responseData.toString());
                    responseBody.setStatusCode(Integer.parseInt(statusCode));
                } else {
                    responseBody.setOutcome(false);
                    responseBody.setMessage("API Error: No Response Data Available");
                    responseBody.setData(new JSONArray());
                    responseBody.setStatusCode(Integer.parseInt(statusCode));
                }
            } else {
                responseBody.setOutcome(false);
                responseBody.setMessage("Missing Access Token");
                responseBody.setData(new JSONArray());
                responseBody.setStatusCode(401);
            }
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                try {
                    throw new InvalidAccessTokenException("REQUEST_CODE_498", "Invalid Access Token - " + e.getMessage());
                } catch (InvalidAccessTokenException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request: Request Datatype Mismatch/Error in Request Body");
            } else {
                log.info("=======Error========" + e);
                throw new RuntimeException(e);
            }
        } catch (HttpServerErrorException e) {
            // Server-side errors (5xx)
            if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
                throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
            } else {
                throw new RuntimeException(e);
            }

        } catch (ResourceAccessException e) {
            // Timeouts, connection refused, etc.
            throw new RuntimeException(e);
        } catch (Exception e) {
            // Any other exception
            throw new RuntimeException(e);
        }

        return responseBody;
    }

    @Override
    public ResponseDTO deleteUserMasterStatusByUuid(UUID uuid, String status) {
        ResponseDTO responseBody = new ResponseDTO();
        KeycloakUserInfoDTO keycloakUserInfoDTO;
        if (status.toLowerCase().equals("inactive")) {
            UserMasterDTO userMasterDTO = userMasterMapper.toDto(userMasterRepositoryExtended.findByUserMasterUuidAndStatusIgnoreCase(uuid, "active"));
            //UserMasterDTO userMasterDTO = userMasterMapper.toDto(userMasterRepositoryExtended.findByUserIdAndStatusIgnoreCase(userId, "active"));
            log.info("===========userMasterDTO===========" + userMasterDTO);
            if (userMasterDTO.getStatus().equalsIgnoreCase("inactive")) {
                return (new ResponseDTO(Boolean.FALSE, "User is Already Deleted.", null, 200));
            }

            if (userMasterDTO.getUserId() != null) {
                responseBody = getUserInfoFromKeycloak(userMasterDTO.getUsername());
                log.info("==============getUserInfoFromKeycloak=============" + responseBody);
                if (responseBody.getOutcome()) {
                    KeycloakUserInfoDTO keycloakUserInfoObj = (KeycloakUserInfoDTO) responseBody.getData();
                    if (keycloakUserInfoObj.getId() != null) {
                        responseBody = deleUserInKeycloakByUserId(keycloakUserInfoObj.getId());
                        if (responseBody.getOutcome()) {
                            //To Delete from DB make status "inactive"
                            userMasterDTO.setStatus(status);
                            userMasterDTO.setUpdatedDate(LocalDate.now());
                            userMasterDTO.setUpdatedById(31L);
                            userMasterDTO.setUpdatedByName("Falguni");

                            UserMasterDTO savedUserMasterDTO = userMasterMapper.toDto(
                                userMasterRepositoryExtended.save(userMasterMapper.toEntity(userMasterDTO))
                            );
                            log.info("=======savedUserMasterDTO========" + savedUserMasterDTO);
                            if (responseBody.getOutcome()) {
                                return new ResponseDTO(true, "Successfully User Deleted.", (savedUserMasterDTO), 200);
                            } else {
                                return new ResponseDTO(false, "User Deletion failed.", responseBody.getData(), responseBody.getStatusCode());
                            }
                        }
                    }
                    return responseBody;
                } else {
                    return responseBody;
                }
            } else {
                return (new ResponseDTO(Boolean.FALSE, "User Does Not Exist.", null, 200));
            }
            //System.out.println("==========responseBody==========="+responseBody);

        } else {
            return (new ResponseDTO(Boolean.FALSE, "Status must be Inactive ", null, 200));
        }
    }

    private ResponseDTO deleUserInKeycloakByUserId(String userIdInKeycloak) {
        ResponseDTO responseBody = new ResponseDTO();
        System.out.println("=========userIdInKeycloak=========" + userIdInKeycloak);
        try {
            String accessToken = InternalAccessTokenUtilities.getAccessTokenForKeycloak();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            log.info("========token=========" + token);
            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                //===================== Call the MS API with Token =============
                CommonUtilities commonUtilitiesObject = new CommonUtilities();
                Properties propData = commonUtilitiesObject.readPropertiesFile("/project-properties-files/url_config.properties");

                String param = propData.getProperty("generic_keycloak_url");
                String completeUrl = param + "/" + userIdInKeycloak;
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                System.out.println("===========completeUrl===========" + completeUrl);
                headersData.add("Authorization", "Bearer " + token);
                //headersData.add("Content-Type", "application/json"); //;charset=utf-8
                //=====Parsing set of parameters(input DTO) for Entry MicroService
                ResponseEntity<String> responseData = new RestTemplate().exchange(
                    completeUrl,
                    HttpMethod.DELETE,
                    new HttpEntity<>(headersData),
                    String.class
                );

                if (responseData.getStatusCode().is2xxSuccessful()) {
                    responseBody.setOutcome(true);
                    responseBody.setMessage("User Is Deleted From Keycloak Successfully.");
                    responseBody.setData(null);
                    responseBody.setStatusCode(200);
                } else {
                    responseBody.setOutcome(false);
                    responseBody.setMessage("Failed to delete role. Status code: " + responseData.getStatusCode());
                    responseBody.setData(new JSONArray());
                    responseBody.setStatusCode(200);
                }
            } else {
                responseBody.setOutcome(false);
                responseBody.setMessage("Missing Access Token");
                responseBody.setData(new JSONArray());
                responseBody.setStatusCode(401);
            }
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                try {
                    throw new InvalidAccessTokenException("REQUEST_CODE_498", "Invalid Access Token - " + e.getMessage());
                } catch (InvalidAccessTokenException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request: Request Datatype Mismatch/Error in Request Body");
            } else {
                System.out.println("=======Error========" + e);
                throw new RuntimeException(e);
            }
        } catch (HttpServerErrorException e) {
            // Server-side errors (5xx)
            if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
                throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
            } else {
                throw new RuntimeException(e);
            }

        } catch (ResourceAccessException e) {
            // Timeouts, connection refused, etc.
            throw new RuntimeException(e);
        } catch (Exception e) {
            // Any other exception
            throw new RuntimeException(e);
        }

        return responseBody;
    }

    @Override
    public ResponseDTO validateLogin(String userName, String password) throws IOException, ParseException {
        ResponseDTO responseDTO = getUserInfoFromKeycloak(userName);
        CommonUtilities commonUtilities = new CommonUtilities();
        Properties properties = commonUtilities.readPropertiesFile("/project-properties-files/url_config.properties");
        String status = properties.getProperty("db_active_status");
        if (responseDTO.getOutcome()) {

            UserMasterDTO userMasterDTO = userMasterMapper.toDto(userMasterRepositoryExtended.findByUsernameAndStatusIgnoreCase(userName, status));
            if (Objects.nonNull(userMasterDTO) && Objects.nonNull(userMasterDTO.getUserId())) {
                String dbPassword = decryptPassword(userMasterDTO.getPassword());
                if (userName.equals(userMasterDTO.getUsername()) && password.equals(dbPassword)) {
                    UserInformationResponseDTO userInformationResponseDTO = new UserInformationResponseDTO();
                    BeanUtils.copyProperties(userMasterDTO, userInformationResponseDTO);

                    String accessToken = InternalAccessTokenUtilities.getAccessTokenForKeycloak();
                    JSONParser parser = new JSONParser();
                    JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
                    userInformationResponseDTO.setKeyCloakToken(accessTokenJson.get("access_token").toString());

                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                    mapper.registerModule(new JavaTimeModule());
                    String jsonWithOutPassword = mapper.writerWithView(Scenarios.WithToken.class).writeValueAsString(userInformationResponseDTO);
                    JSONObject jsonObject = (JSONObject) parser.parse(jsonWithOutPassword);
                    return new ResponseDTO(true, "Valid User", jsonObject, Integer.parseInt(properties.getProperty("status_code_200")));
                } else
                    return new ResponseDTO(false, "Invalid UserName Or Password", null, Integer.parseInt(properties.getProperty("status_code_200")));
            } else
                return new ResponseDTO(false, "Invalid User / User is not Present into the Database", null, Integer.parseInt(properties.getProperty("status_code_200")));
        }
        return new ResponseDTO(false, responseDTO.getMessage(), responseDTO.getData(), responseDTO.getStatusCode());
    }
}
