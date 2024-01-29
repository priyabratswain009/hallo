package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.dropbox.core.InvalidAccessTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.utils.ApplicationConstants;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.domain.ServiceReview.ServiceReviewInput;
import com.sunknowledge.dme.rcm.domain.elligibility.TokenOutCome;
import com.sunknowledge.dme.rcm.domain.soelligibility.SOElligibilityInput;
import com.sunknowledge.dme.rcm.domain.soelligibility.SOElligibilityOutput;
import com.sunknowledge.dme.rcm.domain.soelligibility.Subscriber;
import com.sunknowledge.dme.rcm.repository.SalesOrderInsuranceDetailsRepositoryExtended;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.claimssubmissiondata.TokenGenerationService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soelligibility.ElligibilityHealthCheck;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderInsuranceEntryParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderInsuranceDetailsMapper;
import org.springframework.beans.BeanUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Primary
@Service
@Transactional
public class SalesOrderInsuranceDetailsServiceExtendedImpl implements SalesOrderInsuranceDetailsServiceExtended {

    @Autowired
    SalesOrderInsuranceDetailsRepositoryExtended salesOrderInsuranceDetailsRepositoryExtended;

    @Autowired
    SalesOrderInsuranceDetailsMapper salesOrderInsuranceDetailsMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TokenGenerationService tokenGenerationService;

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> save(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> update(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> partialUpdate(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderInsuranceDetailsDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderInsuranceDetails> findBySalesOrderId(Long SOID) {
        return salesOrderInsuranceDetailsRepositoryExtended.findSOInsuranceBySalesOrderId(SOID);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SalesOrderInsuranceDetails> getSOInsuranceDetailsByInsuranceUUID(UUID sOInsuranceDetailsUUID) {
        return salesOrderInsuranceDetailsRepositoryExtended.getSOInsuranceDetailsOnInsuranceUUID(sOInsuranceDetailsUUID);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getIDByUUID(UUID sOInsuranceDetailsUUID) {
        try {
            return salesOrderInsuranceDetailsRepositoryExtended.getIDByUUID(sOInsuranceDetailsUUID).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<Long> getIDByUUIDReactive(UUID sOInsuranceDetailsUUID) {
        return salesOrderInsuranceDetailsRepositoryExtended.getIDByUUID(sOInsuranceDetailsUUID);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SalesOrderInsuranceDetails> findById(Long id) {
        return salesOrderInsuranceDetailsRepositoryExtended.findById(id);
    }

    @Override
    public Mono<ResponseDTO> saveSOInsuranceDetails(SalesOrderInsuranceDetailsDTO obj, SalesOrderInsuranceEntryParameterDTO salesOrderInsuranceEntryParameterDTO, Long branchId) {
        String message = "";
        Boolean status = false;
        List<Object> responseList = new ArrayList<>();
        System.out.println("BranchId => "+ branchId);
        System.out.println("insuranceId => "+ obj.getPrimaryInsurerId());
        try {

            if (salesOrderInsuranceEntryParameterDTO.getPrimaryInsurerId() != null &&
                !salesOrderInsuranceEntryParameterDTO.getPrimaryInsurerId().equals(0L)) {
                //----- Get details for check doctor is exist or not -----
                String accessToken = InternalAccessTokenUtilities.getAccessToken();
                JSONParser parser = new JSONParser();
                JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
                String token = accessTokenJson.get("access_token").toString();

                String responseBody = "[]";
                if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                    CommonUtilities commonUtilitiesObj = new CommonUtilities();
                    Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");
                    String url = propData.getProperty("branchInsurance_Map_url");
                    branchId = branchId == null ? 0L : branchId;
                    Long insuranceId = obj.getPrimaryInsurerId() == null ? 0L : obj.getPrimaryInsurerId();
                    RestTemplate restTemplateData = new RestTemplate();
                    HttpHeaders headersData = new HttpHeaders();
                    headersData.add("Authorization", "Bearer " + token);
                    HttpEntity entityData = new HttpEntity<>(headersData);
                    ResponseEntity responseData = restTemplateData.exchange(
                        url +
                            "?branchId={branchId}&insuranceId={insuranceId}",
                        HttpMethod.GET,
                        entityData,
                        String.class,
                        branchId, insuranceId);
                    if (responseData.getStatusCode() == HttpStatus.OK) {
                        responseBody = (String) responseData.getBody();
                    } else {
                        message = "API Error: No Response Data Available";
                        return Mono.just(new ResponseDTO(status, message, responseList));
                    }
                } else {
                    message = "Missing Access Token";
                    return Mono.just(new ResponseDTO(status, message, responseList));
                }
                JSONObject branchInsMapJson = (JSONObject) parser.parse(responseBody == null ? "{}" : responseBody);
//            //System.out.println("branchInsMapJson=" + branchInsMapJson);

                //----------- Get branch details ------------
                if (!branchInsMapJson.isEmpty() && branchInsMapJson.get("data") != null
                    && ((JSONArray) branchInsMapJson.get("data")).size() > 0) {
                    JSONObject branchInfoRow = (JSONObject) ((JSONArray) branchInsMapJson.get("data")).get(0);

//                //System.out.println("branchInfoRow=>" + branchInfoRow);

                    Long insuranceId = branchInfoRow.get("insuranceId") == null ?
                        0 : (Long) branchInfoRow.get("insuranceId");
                    obj.setPrimaryInsurerId(insuranceId);

                    //----------- Get Insurance Master (Claim Program, Insurance Group)-------------
                    String responseInsuranceBody = "[]";
                    Long primaryInsID = obj.getPrimaryInsurerId() == null ? 0L : obj.getPrimaryInsurerId();
                    Long secondaryInsID = obj.getSecondaryInsurerId() == null ? 0L : obj.getSecondaryInsurerId();
                    Long tertiaryInsID = obj.getTertiaryInsurerId() == null ? 0L : obj.getTertiaryInsurerId();
                    if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                        String insuranceIds = primaryInsID + "," + secondaryInsID + "," + tertiaryInsID;
                        CommonUtilities commonUtilitiesObj = new CommonUtilities();
                        Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");
                        String url = propData.getProperty("insuranceMaster_url");
                        RestTemplate restTemplateData = new RestTemplate();
                        HttpHeaders headersData = new HttpHeaders();
                        headersData.add("Authorization", "Bearer " + token);
                        HttpEntity entityData = new HttpEntity<>(headersData);
                        ResponseEntity responseData = restTemplateData.exchange(
                            url +
                                "?insuranceIds={insuranceIds}",
                            HttpMethod.GET,
                            entityData,
                            String.class,
                            insuranceIds);
                        if (responseData.getStatusCode() == HttpStatus.OK) {
                            responseInsuranceBody = (String) responseData.getBody();
                        } else {
                            message = "API Error: No Response Data Available";
                            return Mono.just(new ResponseDTO(status, message, responseList));
                        }
                    } else {
                        message = "Missing Access Token";
                        return Mono.just(new ResponseDTO(status, message, responseList));
                    }
                    JSONObject insMaster = (JSONObject) parser.parse(responseInsuranceBody == null ? "{}" : responseInsuranceBody);
                    //System.out.println("insMaster=" + insMaster);

                    //----------- Get branch details ------------
                    if (!insMaster.isEmpty() && insMaster.get("data") != null
                        && ((JSONArray) insMaster.get("data")).size() > 0) {
                        //System.out.println("--------------------- In insMaster IF");
                        for (int i = 0; i < ((JSONArray) insMaster.get("data")).size(); i++) {
                            //System.out.println("--------------------- In insMaster Loop");
                            JSONObject insMstRow = (JSONObject) ((JSONArray) insMaster.get("data")).get(i);
                            //System.out.println("--------- In insMaster Loop ---- Fetch Row:: " + insMstRow);
                            if (!insMstRow.isEmpty() && ((Long) insMstRow.get("insuranceId")).equals(primaryInsID)) {    //!insMstRow.isEmpty() &&
                                //System.out.println("--------------------- In get(\"insurance_id\").equals(primaryInsID) condition ----");
                                obj.setPrimaryInsuranceGroupId((Long) insMstRow.get("insuranceGroupId"));
                                obj.setPrimaryInsuranceGroupName((String) insMstRow.get("insuranceGroupName"));
                                obj.setPrimaryClaimProgram((String) insMstRow.get("claimProgramName"));
                            }
                            if (!insMstRow.isEmpty() && ((Long) insMstRow.get("insuranceId")).equals(secondaryInsID)) {
                                //System.out.println("--------------------- In get(\"insurance_id\").equals(secondaryInsID) condition ----");
                                obj.setSecondaryInsuranceGroupId((Long) insMstRow.get("insuranceGroupId"));
                                obj.setSecondaryInsuranceGroupName((String) insMstRow.get("insuranceGroupName"));
                                obj.setSecondaryClaimProgram((String) insMstRow.get("claimProgramName"));
                            }
                            if (!insMstRow.isEmpty() && ((Long) insMstRow.get("insuranceId")).equals(tertiaryInsID)) {
                                //System.out.println("--------------------- In get(\"insurance_id\").equals(tertiaryInsID) condition ----");
                                obj.setTertiaryInsuranceGroupId((Long) insMstRow.get("insuranceGroupId"));
                                obj.setTertiaryInsuranceGroupName((String) insMstRow.get("insuranceGroupName"));
                                obj.setTertiaryClaimProgram((String) insMstRow.get("claimProgramName"));
                            }

//                        //System.out.println("--------- insMstRow.get(\"insuranceId\") =" + insMstRow.get("insuranceId"));

                            //System.out.println("----- Fetch Row Last :: insMstRow=" + insMstRow);
                        }

                    } else {
                        message = "Given insurances are not available.";
                        return Mono.just(new ResponseDTO(status, message, responseList));
                    }
                    //----------- Get Insurance Master (Claim Program, Insurance Group) -------------


                } else {
                    message = "Branch is not map with given Insurance.";
                    return Mono.just(new ResponseDTO(status, message, responseList));
                }
            }

            //System.out.println("SOInsuranceObj=" + obj);
            //----------- Get branch details ------------

            //------------Saving Data-----------------------------------------------------
            if (obj.getSalesOrderInsuranceDetailsId() == null || obj.getSalesOrderInsuranceDetailsId() == 0) {
                obj.setCreatedDate(LocalDate.now());
                obj.setCreatedById(1L);   //----- Data taken from login user service
                obj.setCreatedByName("Abhijit Chowdhury"); //----- Data taken from login user service
                obj.setSalesOrderInsuranceDetailsId(null);
                obj.setSalesOrderInsuranceDetailsUuid(UUID.randomUUID());
                obj.setStatus("Active");
                return salesOrderInsuranceDetailsRepositoryExtended
                    .save(salesOrderInsuranceDetailsMapper.toEntity(obj))
                    .map(salesOrderInsuranceDetailsMapper::toDto).map(
                        i -> new ResponseDTO(true, "Successfully Saved", (i)));
            } else {
                List<SalesOrderInsuranceDetails> salesOrderInsuranceDetailsForUpdate = salesOrderInsuranceDetailsRepositoryExtended.
                    getSOInsuranceDetailsOnInsuranceUUID(obj.getSalesOrderInsuranceDetailsUuid()).collectList().toFuture().get();
                if (salesOrderInsuranceDetailsForUpdate.size() > 0) {
                    SalesOrderInsuranceDetails updateObj = salesOrderInsuranceDetailsForUpdate.get(0);
                    BeanUtils.copyProperties(salesOrderInsuranceEntryParameterDTO, updateObj);
                    updateObj.setUpdatedDate(LocalDate.now());
                    updateObj.setUpdatedById(1L);      //----- Data taken from login user service
                    updateObj.setUpdatedByName("Abhijit Chowdhury");   //----- Data taken from login user service
                    return salesOrderInsuranceDetailsRepositoryExtended
                        .save(salesOrderInsuranceDetailsMapper.toEntity(obj))
                        .map(salesOrderInsuranceDetailsMapper::toDto).map(
                            i -> new ResponseDTO(true, "Successfully Saved", (i)));
                } else {
                    message = "Update failed! Sales_Order_Insurance_Details does not exist.";
                    return Mono.just(new ResponseDTO(status, message, responseList));
                }
            }

            //-----------------------------Saving Data----------------------------------------------

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
//        return Mono.just(new ResponseDTO(status, "Test Operation", responseList));
    }

    @Override
    public Mono<ServiceOutcome<SalesOrderInsuranceDetailsDTO>> updateSOSecondaryInsuranceInfo(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO) {
        if (salesOrderInsuranceDetailsDTO.getSalesOrderId() != null && salesOrderInsuranceDetailsDTO.getSalesOrderId() != 0) {
            return salesOrderInsuranceDetailsRepositoryExtended.save(salesOrderInsuranceDetailsMapper.toEntity(salesOrderInsuranceDetailsDTO))
                .map(salesOrderInsuranceDetailsMapper::toDto)
                .map(updatedObj -> new ServiceOutcome<>(updatedObj, true, "Successfully Saved."));
        } else {
            return Mono.just(new ServiceOutcome(null, false, "Sales Order Id should not be null."));
        }
    }

    @Override
    public Mono<ServiceOutcome> updateSOInsuranceStatus(Long soInsId) {
        AtomicInteger count = new AtomicInteger(0);

        return salesOrderInsuranceDetailsRepositoryExtended.findById(soInsId)
            .map(existingSoInsuranceObj ->{
                System.out.println("====existingSoInsuranceObj.existingSoInsuranceObj===="+existingSoInsuranceObj.getSalesOrderInsuranceDetailsId());
                existingSoInsuranceObj.setInsuranceVerificationStatus("Y");
                existingSoInsuranceObj.setUpdatedDate(LocalDate.now());
                existingSoInsuranceObj.setUpdatedById(31L);
                existingSoInsuranceObj.setUpdatedByName("Falguni"+ count.getAndIncrement());
                return existingSoInsuranceObj;
            }).flatMap(salesOrderInsuranceDetailsRepositoryExtended::save)
            .map(updatedObj -> {
                System.out.println("======Final Updated soInsuranceDetails data::===="+updatedObj);
                return new ServiceOutcome(updatedObj, true ,"Sales Order Insurance Details Updated.");
            }).switchIfEmpty(Mono.just(new ServiceOutcome(null, false ,"Unable to Update.")));
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> verifySOInsuranceManually(Long soInsuranceId, String insuranceVerificationStatus, String payerLevel) {
        return salesOrderInsuranceDetailsRepositoryExtended.findById(soInsuranceId)
            .map(data -> {
                if(payerLevel.equalsIgnoreCase("primary")){
                    data.setPrimaryInsurerVerificationStatus(insuranceVerificationStatus);
                    data.setPrimaryInsurerVerificationDate(LocalDate.now());
                } else if(payerLevel.equalsIgnoreCase("secondary")) {
                    data.setSecondaryInsurerVerificationStatus(insuranceVerificationStatus);
                    data.setSecondaryInsurerVerificationDate(LocalDate.now());
                } else if(payerLevel.equalsIgnoreCase("tertiary")) {
                    data.setTertiaryInsurerVerificationStatus(insuranceVerificationStatus);
                    data.setTertiaryInsurerVerificationDate(LocalDate.now());
                }
                return data;
            }).flatMap(salesOrderInsuranceDetailsRepositoryExtended::save)
            .map(salesOrderInsuranceDetailsMapper::toDto);
    }

    @Override
    public Mono<SalesOrderInsuranceDetails> findBySOId(Long soId) {
        return salesOrderInsuranceDetailsRepositoryExtended.findSOInsuranceOnSOId(soId);
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> verifySOInsuranceAutomatic(SalesOrderMasterDTO salesOrderMaster, SalesOrderInsuranceDetails salesOrderInsuranceDetails, String payerLevel, String accessToken) {


        if(payerLevel.equalsIgnoreCase("primary")){
            return checkAndHandleUpdateSOElligibility(salesOrderMaster.getPatientFirstName(),
                salesOrderMaster.getPatientMiddleName(),
                salesOrderMaster.getPatientLastName(),
                salesOrderMaster.getPatientGender(),
                salesOrderMaster.getPatientDob(), salesOrderInsuranceDetails.getPrimaryInsurerPolicyNo(),
                salesOrderInsuranceDetails.getPrimaryInsurancePayerId(), payerLevel, accessToken)
                .map(data -> {
                    salesOrderInsuranceDetails.setPrimaryInsurerVerificationStatus(data);
                    salesOrderInsuranceDetails.setPrimaryInsurerVerificationDate(LocalDate.now());
                    return salesOrderInsuranceDetails;
                }).flatMap(salesOrderInsuranceDetailsRepositoryExtended::save)
                .map(salesOrderInsuranceDetailsMapper::toDto)
                .map(data ->{
                    System.out.println("==========Updated SalesOrderInsuranceDetails data ====== "+ data);
                    return data;
                });
        } else if(payerLevel.equalsIgnoreCase("secondary")) {
            //salesOrderInsuranceDetails.setSecondaryInsurerVerificationStatus(insuranceVerificationStatus);
            salesOrderInsuranceDetails.setSecondaryInsurerVerificationDate(LocalDate.now());
            return null;
        } else if(payerLevel.equalsIgnoreCase("tertiary")) {
            //salesOrderInsuranceDetails.setTertiaryInsurerVerificationStatus(insuranceVerificationStatus);
            salesOrderInsuranceDetails.setTertiaryInsurerVerificationDate(LocalDate.now());
            return null;
        }else{
            return null;
        }
    }

    private Mono<String> checkAndHandleUpdateSOElligibility(String patientFirstName, String patientMiddleName,
                                                                          String patientLastName, String patientGender, LocalDate patientDob,
                                                                          String primaryInsurerPolicyNo, String primaryInsurancePayerId,
                                                                          String payerLevel, String accessToken) {
        String inputEntity = "";

        SOElligibilityOutput soElligibilityOutput = null;
        String url = ApplicationConstants.ELLIGIBILITY_URL;
        try {

            SOElligibilityInput objPatientElligibilityInput;


            if (checkElligibilityHealth(accessToken)) {
                objPatientElligibilityInput = getInputData(patientFirstName, patientMiddleName,
                    patientLastName, patientGender, patientDob,
                    primaryInsurerPolicyNo, primaryInsurancePayerId);

                System.out.println("===========::objPatientElligibilityInput::==========" + objPatientElligibilityInput);

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/json");
                headers.add("Accept", "application/json");
                headers.set("Authorization", "Bearer " + accessToken);
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                inputEntity = ow.writeValueAsString(objPatientElligibilityInput);

                System.out.println("===inputEntity==="+inputEntity);


                HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(inputEntity, headers);
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, inputCriteriaEntity,
                    String.class);

                System.out.println("========response========" + response);
                System.out.println("========response.getBody========" + response.getBody());

                if (response.getStatusCode() == HttpStatus.OK) {
                    ObjectMapper mapper = new ObjectMapper();
                    soElligibilityOutput = mapper.readValue(response.getBody(), SOElligibilityOutput.class);

                    System.out.println("============::soElligibilityOutput::===========" + soElligibilityOutput);

                    if (soElligibilityOutput.getErrors() == null) {

                        System.out.println("==============================::Successful::====================================");
                        return Mono.just("Y");
                    } else {
                        System.out.println("===================Errors======================"+response.getBody());
                        return Mono.just("N");
                    }
                } else {
                    System.out.println("===================Failed======================" + response.getBody());
                    return Mono.just("N");
                }
            } else {
                System.out.println("URL you're Trying to access is currently  Unavailable");
                return Mono.just("N");
            }
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean checkElligibilityHealth(String token) {
        // TODO Auto-generated method stub
        ElligibilityHealthCheck healthCheckOutcome = new ElligibilityHealthCheck();
        boolean status = false;

        try {
            String URL = ApplicationConstants.ELLIGIBILITY_HEALTHCHECK_URL;
            HttpHeaders header = new HttpHeaders();
            token = "Bearer " + token;

            header.add("Content-Type", "application/json");
            header.add("Accept", "application/json");
            header.add("Authorization", token);

            HttpEntity<String> request = new HttpEntity<String>(null, header);
            ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, request, String.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                ObjectMapper mapper = new ObjectMapper();
                healthCheckOutcome = mapper.readValue(responseEntity.getBody(), ElligibilityHealthCheck.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (healthCheckOutcome.getVersion().equalsIgnoreCase("v3")
            && healthCheckOutcome.getStatus().equalsIgnoreCase("ok")) {
            status = true;
        } else {
            status = false;
        }

        return status;
    }
    public static SOElligibilityInput getInputData(String patientFirstName, String patientMiddleName,
                                                   String patientLastName, String patientGender, LocalDate patientDob,
                                                   String primaryInsurerPolicyNo, String primaryInsurancePayerId) {

        SOElligibilityInput objPatientElligibilityInput = new SOElligibilityInput();
        Subscriber objSubscriber = new Subscriber();
        String fname = "", lname = "";

        /*if (CommonUtilities.isStringNullOrBlank(primaryInsurerPolicyNo)) {
            objSubscriber.setMemberId(primaryInsurerPolicyNo);
        }
        if (CommonUtilities.isStringNullOrBlank(
            CommonUtilities.mergeName(patientFirstName, patientMiddleName, patientLastName))) {
            String[] arr = CommonUtilities.mergeName(patientFirstName, patientMiddleName, patientLastName).split(" ");
            fname = arr[0];
            lname = arr[arr.length - 1];
        }
        if (CommonUtilities.isStringNullOrBlank(fname)) {
            objSubscriber.setFirstName(fname);
        }
        if (CommonUtilities.isStringNullOrBlank(lname)) {
            objSubscriber.setLastName(lname);
        }
        if (CommonUtilities.isStringNullOrBlank(patientGender)) {
            objSubscriber.setGender(patientGender);
        }
        if (CommonUtilities.isStringNullOrBlank(String.valueOf(patientDob))) {
            objSubscriber.setDateOfBirth(CommonUtilities.datetoStringConverter(String.valueOf(patientDob)));
        }
        if (CommonUtilities.isStringNullOrBlank(String.valueOf(primaryInsurerPolicyNo))) {
            // No control number is available in patient
            objPatientElligibilityInput.setControlNumber("123456789");
        }
        if (CommonUtilities.isStringNullOrBlank(String.valueOf(primaryInsurancePayerId))) {
            // for sandbox using hard coded value
            objPatientElligibilityInput.setTradingPartnerServiceId(primaryInsurancePayerId);
        }*/

        /****Test Data Set Start******/
        objSubscriber.setMemberId("123456789");
        objSubscriber.setFirstName("johntwo");
        objSubscriber.setLastName("doeone");
        objSubscriber.setGender("M");
        objSubscriber.setDateOfBirth("18800102");
        objPatientElligibilityInput.setControlNumber("123456789");
        objPatientElligibilityInput.setTradingPartnerServiceId("SMNY5");
        /*{
            "subscriber" : {
            "memberId" : "123456789",
                "firstName" : "johntwo",
                "lastName" : "doeone",
                "gender" : "M",
                "dateOfBirth" : "18800102"
        },
            "controlNumber" : "123456789",
            "tradingPartnerServiceId" : "SMNY5"
        }*/
        /****Test Data Set End******/
        objPatientElligibilityInput.setSubscriber(objSubscriber);

        System.out.println("========Input Data========" + objPatientElligibilityInput);
        System.out.println("========Input Data:: Subscriber:: MemberId========" + objPatientElligibilityInput.getSubscriber().getMemberId());
        System.out.println("========Input Data:: Subscriber:: FirstName========" + objPatientElligibilityInput.getSubscriber().getFirstName());
        System.out.println("========Input Data:: Subscriber:: LastName========" + objPatientElligibilityInput.getSubscriber().getLastName());
        System.out.println("========Input Data:: Subscriber:: Gender========" + objPatientElligibilityInput.getSubscriber().getGender());
        System.out.println("========Input Data:: DateOfBirth========" + objPatientElligibilityInput.getSubscriber().getDateOfBirth());
        System.out.println("========Input Data:: ControlNumber========" + objPatientElligibilityInput.getControlNumber());
        System.out.println("========Input Data:: Trading Partner Service Id========" + objPatientElligibilityInput.getTradingPartnerServiceId());


        return objPatientElligibilityInput;
    }

    @Override
    public Mono<ServiceOutcome<String>> getServiceReviewById(long id) {
        // TODO Auto-generated method stub

        ServiceOutcome<String> outCome = new ServiceOutcome<String>();

        try {
            String token = tokenGenerationService.getCoverageToken();

            String url = ApplicationConstants.SERVICE_REVIEW_NEW_URL;
            url = url + "/" + id;

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Accept", "application/json");
            headers.add("X-Api-Mock-Scenario-ID", "SR-GetComplete-i");
            headers.set("Authorization", "Bearer " + token);

            HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(null, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, inputCriteriaEntity,
                String.class);

            System.out.println("OUTPUT=======> "+response);

            JSONParser jsonParser = new JSONParser();
            System.out.println("JSON Data " + jsonParser.parse(response.getBody()));
            outCome.setData(response.getBody());
            outCome.setMessage("Data Recieved Successfully");
            outCome.setOutcome(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Mono.just(outCome);
    }

    @Override
    public Mono<SalesOrderInsuranceDetailsDTO> updateCoverageVerificationStatus(Long soInsuranceId, String coverageVerificationStatus) {
        return salesOrderInsuranceDetailsRepositoryExtended.findById(soInsuranceId)
            .map(data -> {
                data.setCoverageVerificationStatus(coverageVerificationStatus);
                data.setPrimaryInsurerVerificationDate(LocalDate.now());
                data.setUpdatedById(1l);
                data.setUpdatedByName("Abhay Api Test");
                data.setUpdatedDate(LocalDate.now());
                return data;
            }).flatMap(salesOrderInsuranceDetailsRepositoryExtended::save)
            .map(salesOrderInsuranceDetailsMapper::toDto);
    }
}
