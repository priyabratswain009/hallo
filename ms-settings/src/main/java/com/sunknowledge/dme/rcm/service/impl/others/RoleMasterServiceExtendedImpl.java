package com.sunknowledge.dme.rcm.service.impl.others;

import com.dropbox.core.InvalidAccessTokenException;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.RoleMaster;
import com.sunknowledge.dme.rcm.repository.others.RoleMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RolesOutputDTO;
import com.sunknowledge.dme.rcm.service.mapper.RoleMasterMapper;
import com.sunknowledge.dme.rcm.service.others.RoleMasterServiceExtended;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Service
@Slf4j
public class RoleMasterServiceExtendedImpl implements RoleMasterServiceExtended {

    @Autowired
    RoleMasterRepositoryExtended roleMasterRepositoryExtended;
    @Autowired
    RoleMasterMapper roleMasterMapper;


    @Override
    public RoleMasterDTO save(RoleMasterDTO roleMasterDTO) {
        return null;
    }

    @Override
    public RoleMasterDTO update(RoleMasterDTO roleMasterDTO) {
        return null;
    }

    @Override
    public Optional<RoleMasterDTO> partialUpdate(RoleMasterDTO roleMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<RoleMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<RoleMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveRoleMaster(RoleMasterParameterDTO roleMasterParameterDTO) {
        ResponseDTO outcome = new ResponseDTO();

        if(roleMasterParameterDTO.getRoleCode()== null || roleMasterParameterDTO.getRoleCode().trim().equals("")){
            return new ResponseDTO(false, "Role Code Should Not be Null/Blank.", null, 200);
        }

        if(roleMasterParameterDTO.getRoleName()== null || roleMasterParameterDTO.getRoleName().trim().equals("")){
            return new ResponseDTO(false, "Role Name Should Not be Null/Blank.", null, 200);
        }

        if(roleMasterParameterDTO.getRoleDescription()== null || roleMasterParameterDTO.getRoleDescription().trim().equals("")){
            return new ResponseDTO(false, "Role Description Should Not be Null/Blank.", null, 200);
        }
        Long roleId = 0L;
        if(roleMasterParameterDTO.getRoleName() != null && roleMasterParameterDTO.getRoleName() != "") {
            CommonUtilities.dtoTrimmer(roleMasterParameterDTO);

            if(roleMasterParameterDTO.getRoleMasterUuid()!=null){
                roleId = getIDByUUID(roleMasterParameterDTO.getRoleMasterUuid());
            }

            if(roleId!=null && roleId!=0){
                return new ResponseDTO(false, "Role Updation Is Not Allowed. Delete and Create Another Role", null, 200);
            }

            List<RoleMaster> roleMasterList=roleMasterRepositoryExtended.findByStatusIgnoreCase("active");  // Get All Existing Roles
            log.info("======roleMasterList====="+roleMasterList);
            boolean rolenameExists = roleMasterList.stream()
                .anyMatch(role -> Objects.equals(roleMasterParameterDTO.getRoleName(), role.getRoleName()));

            if(rolenameExists){
                return new ResponseDTO(false, "Role Name Already Exists.", null, 200);
            }

            boolean roleCodeExists = roleMasterList.stream()
                .anyMatch(role -> Objects.equals(roleMasterParameterDTO.getRoleCode(), role.getRoleCode()));

            if(roleCodeExists){
                return new ResponseDTO(false, "Role Code Already Exists.", null, 200);
            }

            Optional<RoleMaster> roleMaster=roleMasterRepositoryExtended.findByRoleIdAndStatusIgnoreCase(getIDByUUID(roleMasterParameterDTO.getRoleMasterUuid()),"active");
            RoleMasterDTO roleMasterDTOData = (roleMasterParameterDTO.getRoleMasterUuid() == null) ? new RoleMasterDTO() :
                ( roleMaster.isEmpty() ?
                    new RoleMasterDTO() : roleMasterMapper.toDto(roleMaster.get()));

            if((roleMasterDTOData.getRoleCode()!=null && !roleMasterDTOData.getRoleCode().equals("")) && (roleMasterDTOData.getRoleName()!=null && !roleMasterDTOData.getRoleName().equals(""))){
                if((roleMasterDTOData.getRoleCode().equals(roleMasterParameterDTO.getRoleCode().trim())) && (roleMasterDTOData.getRoleName().equals(roleMasterParameterDTO.getRoleName().trim())))
                    return new ResponseDTO(false, "Role Updation Is Not Allowed. Delete and Create Another Role", null, 200);
            }

            BeanUtils.copyProperties(roleMasterParameterDTO, roleMasterDTOData);

            /**Keycloak Integration to create user in Keycloak as well**/
            ResponseDTO responseBody = new ResponseDTO();
            if (roleMasterParameterDTO.getRoleName() != null ) {
                responseBody = createRoleInKeycloak(roleMasterParameterDTO);
                System.out.println("=======Response After Creating User In Keycloak========" + responseBody);
            }


            if (roleMasterDTOData.getRoleMasterUuid() == null) {
                roleMasterDTOData.setRoleId(null);
                roleMasterDTOData.setRoleMasterUuid(UUID.randomUUID());
                roleMasterDTOData.setCreatedDate(LocalDate.now());
                roleMasterDTOData.setCreatedById(1L);
                roleMasterDTOData.setCreatedByName("Abhijit");
                roleMasterDTOData.setRoleNo(roleMasterRepositoryExtended.getRolNumber());
            } else {
                roleMasterDTOData.setUpdatedDate(LocalDate.now());
                roleMasterDTOData.setUpdatedById(1L);
                roleMasterDTOData.setUpdatedByName("Abhijit");
            }
            RoleMaster roleMasterSaved = roleMasterRepositoryExtended.save(roleMasterMapper.toEntity(roleMasterDTOData));

            outcome.setData((roleMasterSaved));
            outcome.setOutcome(true);
            outcome.setMessage("Data Successfully Saved.");
            return outcome;
        }else{
            outcome.setOutcome(false);
            outcome.setMessage("Data Not Saved.");
            return outcome;
        }
    }

    @Override
    public ResponseDTO getRoleMasterByNameOrNoOrUUID(String data, String operationType) {
        switch (operationType){
            case "roleNo" : {
                RoleMasterDTO roleMaster = roleMasterMapper.toDto(roleMasterRepositoryExtended.findByRoleNoAndStatusIgnoreCase(data,"active"));
                return new ResponseDTO(roleMaster==null?false:true,roleMaster==null?"Data Not Found.":"",roleMaster==null?null:(roleMaster),200);
            }
            case "roleName" : {
                List<RoleMasterDTO> roleMasterList = data.trim()!=""?
                    roleMasterMapper.toDto(roleMasterRepositoryExtended.findByRoleNameLikeIgnoreCaseAndStatusIgnoreCase("%"+data.trim()+"%","active"))
                    :new ArrayList<>();
                return new ResponseDTO(!roleMasterList.isEmpty(),roleMasterList.isEmpty()?"Data Not Found.":"",roleMasterList,200);
            }
            case "roleUUID" : {
                Long id = 0L;
                UUID d = UUID.fromString(data);
                if (d != null) {
                    id = getIDByUUID(d);
                    id = id != null ? id : 0L;
                }
                Optional<RoleMaster> roleMaster = roleMasterRepositoryExtended.findByRoleIdAndStatusIgnoreCase(id,"active");
                return new ResponseDTO(!roleMaster.isEmpty(),roleMaster.isEmpty()?"Data Not Found.":"",
                    roleMaster.isEmpty()?null:(roleMasterMapper.toDto(roleMaster.get())),200
                );
            }
            default:{
                return new ResponseDTO(false, "Please give correct operationType.",null,200);
            }
        }
    }

    @Override
    public Long getIDByUUID(UUID d) {
        return roleMasterRepositoryExtended.getIDByUUID(d);
    }

    @Override
    public RoleMasterDTO getActiveDataById(Long id) {
        Optional<RoleMaster> roleMaster = roleMasterRepositoryExtended.findByRoleIdAndStatusIgnoreCase(id,"active");
        if(roleMaster.isPresent()){
            return roleMasterMapper.toDto(roleMaster.get());
        }
        return null;
    }

    @Override
    public List<Long> getActiveIDByUUID(List<UUID> roleUUID) {
        return roleMasterRepositoryExtended.findByRoleMasterUuidInAndStatusIgnoreCase(roleUUID, "active").stream().map(x->x.getRoleId()).collect(Collectors.toList());
    }

    @Override
    public ResponseDTO setRoleMasterStatusByUuid(UUID uuid, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            /**Keycloak Integration to create user in Keycloak as well**/
            ResponseDTO responseBody = new ResponseDTO();
            responseBody = getRolesInfoFromKeycloak();
            if(responseBody.getOutcome()){
                List<RolesOutputDTO> rolesList = (List<RolesOutputDTO>) responseBody.getData();
                try{
                    Optional<RoleMaster> obj = roleMasterRepositoryExtended.findByRoleMasterUuid(uuid);
                    String roleIdInKeycloak = null;
                    if(obj.isPresent()){
                        if(obj.get().getStatus().equalsIgnoreCase("inactive")){
                            return (new ResponseDTO(Boolean.FALSE, "Role is Already Deactivated/Deleted.",null,200));
                        }
                        String roleName = obj.get().getRoleName().trim();
                        log.info("=============roleName==============="+roleName);
                        for (RolesOutputDTO role : rolesList) {
                            System.out.println("Role Name: " + role.getName());
                            if(role.getName().trim().equals(roleName)){
                                roleIdInKeycloak = role.getId();
                                break;
                            }
                        }
                        log.info("======2. Role Id in Keycloak======"+roleIdInKeycloak);
                        if(roleIdInKeycloak!=null){
                            responseBody = deleRoleInKeycloakByRoleId(roleIdInKeycloak);
                            log.info("=========After Deletion Of Role==========="+responseBody);
                            if(responseBody.getOutcome()){
                                obj.get().setStatus(status);
                                obj.get().setUpdatedById(1l);
                                obj.get().setUpdatedByName("Updated Test");
                                obj.get().setUpdatedDate(LocalDate.now());
                                roleMasterRepositoryExtended.save(obj.get());
                                return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", obj.get(),200));
                            }else{
                                return responseBody;
                            }
                        }else{
                            return (new ResponseDTO(Boolean.FALSE, "Role is Already Deactivated/Deleted.",null,200));
                        }
                    }else{
                        return (new ResponseDTO(Boolean.FALSE, "Data Not Found",null,200));
                    }
                }catch (Exception e){
                    log.error("=====>> Error : "+e);
                    return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",null,200));
                }
            }else{
                return responseBody;
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", null,200));
        }
    }

    @Override
    public List<Map<String, Object>> getAllRoleMasterDataForDropdown() {
        ResponseDTO responseDTO = new ResponseDTO();
        RolesOutputDTO rolesOutputDTO =  new RolesOutputDTO();
        List<RolesOutputDTO> rolesList = new ArrayList<>();
        HashMap<String, String> rolesMap = new HashMap<>();
        List<RoleMaster> posMasterList = roleMasterRepositoryExtended.findByStatusIgnoreCase("active");
        responseDTO = getRolesInfoFromKeycloak();
        if(responseDTO.getOutcome()){
            rolesList = (List<RolesOutputDTO>) responseDTO.getData();
            for (RolesOutputDTO eachRoles : rolesList)
            {
                rolesMap.put("title", eachRoles.getName());
            }
        }
        if (posMasterList.size() > 0) {
            return posMasterList.stream().map(p -> {
                Map<String, Object> map = new HashMap<>();
                if(rolesMap.get("title").equals(p.getRoleName())){
                    map.put("id", p.getRoleId());
                    map.put("title", p.getRoleName());
                }
                return map;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<RoleMasterDTO> getAllRoleMasterData() {
        List<RoleMaster> roleMasters = roleMasterRepositoryExtended.findByStatusIgnoreCase("active");
        if(roleMasters.size()>0){
            return roleMasterMapper.toDto(roleMasters);
        }
        return new ArrayList<>();
    }

    @Override
    public ResponseDTO setInactiveRoleRelatedInfo(long id, String status) {
        Long isUpdtaed = roleMasterRepositoryExtended.setInactiveRoleRelatedInfo(id);

        System.out.println("isUpdtaed "+ isUpdtaed);
        return new ResponseDTO(isUpdtaed>0?true:false,isUpdtaed>0?"Updtaed Successfully":"Data not found.",null,200);
    }

    private ResponseDTO createRoleInKeycloak(RoleMasterParameterDTO roleMasterParameterDTO) { //UserMasterDTO savedUserMasterDTO
        ResponseDTO responseBody = new ResponseDTO();
        Map userMasterDataKeyCloakDTO = new HashMap();
        log.info("=====roleMasterParameterDTO=====" + roleMasterParameterDTO);

        Map<String, Object> user = new HashMap<>();
        user.put("name", roleMasterParameterDTO.getRoleName());
        user.put("description", roleMasterParameterDTO.getRoleDescription());
        user.put("composite", false);

        try {
            //==================== Get the access token ====================
            //String accessToken = InternalAccessTokenUtilities.getAccessTokenByParam("http://localhost:9080/auth/realms/jhipster/protocol/openid-connect/token","client_credentials", "jhipster-registry", "jhipster-registry", "openid profile email", "jhipster");
            //CommonUtilities commonUtilitiesObj = new CommonUtilities();
            //Properties keyCloakPropData = commonUtilitiesObj.readPropertiesFile("/security-property-files/InternalAccessTokenParameters.properties");

            String accessToken = InternalAccessTokenUtilities.getAccessTokenForKeycloak();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            log.info("========token=========" + token);
            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                //===================== Call the MS API with Token =============
                CommonUtilities commonUtilitiesObject = new CommonUtilities();
                Properties propData = commonUtilitiesObject.readPropertiesFile("/project-properties-files/url_config.properties");

                String param = propData.getProperty("create_role_url");
                String client_id = propData.getProperty("client_id");
                String completeUrl=param+"/"+client_id+"/roles/";
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                System.out.println("===========completeUrl==========="+completeUrl);
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
                log.info("=========responseData==========" + responseData);
                String statusCode = extractStatusCode(responseData.toString());
                log.info("Status Code: " + statusCode);

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

    private static String extractStatusCode(String responseHeader) {
        int startIndex = responseHeader.indexOf('<') + 1;
        int endIndex = responseHeader.indexOf(',');

        if (startIndex != -1 && endIndex != -1) {
            String statusCodeStr = responseHeader.substring(startIndex, endIndex).trim();
            return statusCodeStr;
        }

        return null; // Return -1 if status code is not found or cannot be parsed
    }

    private ResponseDTO getRolesInfoFromKeycloak() {
        ResponseDTO responseBody = new ResponseDTO();

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

                String param = propData.getProperty("get_roles_url");
                String client_id = propData.getProperty("client_id");
                String completeUrl=param+"/"+client_id+"/roles/";
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                log.info("===========completeUrl==========="+completeUrl);
                headersData.add("Authorization", "Bearer " + token);
                //headersData.add("Content-Type", "application/json"); //;charset=utf-8
                //=====Parsing set of parameters(input DTO) for Entry MicroService
                HttpEntity<RolesOutputDTO[]> requestEntity = new HttpEntity<>(headersData);
                ResponseEntity<RolesOutputDTO[]> responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.GET,
                    new HttpEntity<>(headersData),
                    RolesOutputDTO[].class
                );
                log.info("=========responseData==========" + responseData);
                List<RolesOutputDTO> rolesList = Arrays.asList(responseData.getBody());

                log.info("Roles List: " + rolesList);

                String statusCode = extractStatusCode(responseData.toString());
                log.info("Status Code: " + statusCode);

                if (statusCode.startsWith("2")) {
                    responseBody.setOutcome(true);
                    responseBody.setMessage("Roles Fetched From Keycloak Successfully.");
                    responseBody.setData(rolesList);
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

    private ResponseDTO deleRoleInKeycloakByRoleId(String roleIdInKeycloak) {
        ResponseDTO responseBody = new ResponseDTO();

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

                String param = propData.getProperty("delete_role_by_id");
                String completeUrl=param+"/"+roleIdInKeycloak;
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                log.info("===========completeUrl==========="+completeUrl);
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
                    responseBody.setMessage("Roles Deleted From Keycloak Successfully.");
                    responseBody.setData(null);
                    responseBody.setStatusCode(200);
                } else {
                    responseBody.setOutcome(false);
                    responseBody.setMessage("Failed to delete role. Status code: "+responseData.getStatusCode());
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
}
