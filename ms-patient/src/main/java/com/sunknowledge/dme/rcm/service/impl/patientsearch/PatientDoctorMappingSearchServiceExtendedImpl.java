package com.sunknowledge.dme.rcm.service.impl.patientsearch;

import com.dropbox.core.InvalidAccessTokenException;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.repository.PatientDoctorMappingSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingParameterV2DTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDoctorMappingCommand;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDoctorMapPatientMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDoctorMappingSearchByPatIdOrMapIdOrDocId;
import com.sunknowledge.dme.rcm.service.patiententry.PatientDoctorMappingServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDoctorMappingSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientDoctorMappingSearchQueryHandler;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service("patientDoctorMappingSearchServiceExtended")
public class PatientDoctorMappingSearchServiceExtendedImpl implements PatientDoctorMappingSearchServiceExtended {
    @Autowired
    PatientDoctorMappingSearchQueryHandler patientDoctorMappingSearchQueryHandler;
    @Autowired
    PatientDoctorMappingServiceExtended patientDoctorMappingServiceExtended;


    @Override
    public Mono<PatientDoctorMappingOutputDTO> getPatientDoctorMappingBySearchParameters(PatientDoctorMappingSearchByPatIdOrMapIdOrDocId obj) {
        return patientDoctorMappingSearchQueryHandler.getPatientDoctorMappingBySearchParameters(obj);
    }

    @Override
    public Mono<ResponseDTO> getDoctorDetails(String npiId,String type) {
        try {
            String message = "Doctor Data";

            //----- Get details for check doctor is exist or not -----
            String accessToken = InternalAccessTokenUtilities.getAccessToken();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            String responseBody = "[]";
            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
//                String npiNumber = "1306951827";
                String npiNumber = npiId;
                CommonUtilities commonUtilitiesObj = new CommonUtilities();
                Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

                String url = propData.getProperty("utility_url");
                String param="?npiNumber={npiNumber}&operationType="+type;
                String completeUrl = url + param;
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                HttpEntity entityData = new HttpEntity<>(headersData);
                ResponseEntity responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.GET,
                    entityData,
                    String.class,
                    npiNumber);
                if (responseData.getStatusCode() == HttpStatus.OK) {
                    responseBody = (String) responseData.getBody();
                } else {
                    message = "API Error: No Response Data Available";
                    return Mono.just(new ResponseDTO(false, message, new ArrayList()));
                }
            } else {
                message = "Missing Access Token";
                return Mono.just(new ResponseDTO(false, message, new ArrayList()));
            }
            JSONObject docJson = (JSONObject) parser.parse(responseBody == null ? "{}" : responseBody);

            message = "Successfully Fetched";
            //List list = new ArrayList();
            if(((Boolean) docJson.get("outcome"))) {
                //list.add(docJson.get("data"));
                return returnDoctorPatientMap(docJson.get("data"));
            }else{
                return Mono.just(new ResponseDTO(false, "Data Not Found", null));
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
    }

    private Mono<ResponseDTO> returnDoctorPatientMap(Object docJsonObj1){
        System.out.println("docJsonObj1 " + docJsonObj1);
        JSONArray docJsonArray = (JSONArray) docJsonObj1;
        JSONObject docJsonObj = (JSONObject) docJsonArray.get(0);
        System.out.println("docJsonObj " + docJsonObj);
        PatientDoctorMappingOutputDTO patientDoctorMapDTO = new PatientDoctorMappingOutputDTO();
        patientDoctorMapDTO.setDoctorFirstName(docJsonObj.get("firstName") == null ?
            "" : docJsonObj.get("firstName").toString());
        patientDoctorMapDTO.setDoctorMiddleName(docJsonObj.get("middleName") == null ?
            "" : docJsonObj.get("middleName").toString());
        patientDoctorMapDTO.setDoctorLastName(docJsonObj.get("lastName") == null ?
            "" :  docJsonObj.get("lastName").toString());
        patientDoctorMapDTO.setDoctorNameSuffix(docJsonObj.get("suffix") == null ?
            "" :  docJsonObj.get("suffix").toString());
        patientDoctorMapDTO.setDoctorAddressLineI(docJsonObj.get("addressLine1") == null ?
            "" :  docJsonObj.get("addressLine1").toString());
        patientDoctorMapDTO.setDoctorAddressLineIi(docJsonObj.get("addressLine2") == null ?
            "" :  docJsonObj.get("addressLine2").toString());
        patientDoctorMapDTO.setDoctorAddressCity(docJsonObj.get("city") == null ?
            "" :  docJsonObj.get("city").toString());
        patientDoctorMapDTO.setDoctorAddressState(docJsonObj.get("state") == null ?
            "" :  docJsonObj.get("state").toString());
        patientDoctorMapDTO.setDoctorAddressZip(docJsonObj.get("zip") == null ?
            "" :  docJsonObj.get("zip").toString());
        patientDoctorMapDTO.setDoctorContactI(docJsonObj.get("contactNo1") == null ?
            "" :  docJsonObj.get("contactNo1").toString());
        patientDoctorMapDTO.setDoctorContactIi(docJsonObj.get("contactNo2") == null ?
            "" :  docJsonObj.get("contactNo2").toString());
        patientDoctorMapDTO.setDoctorFax(docJsonObj.get("faxNumber") == null ?
            "" :  docJsonObj.get("faxNumber").toString());
        patientDoctorMapDTO.setDoctorGender(docJsonObj.get("gender") == null ?
            "" :  docJsonObj.get("gender").toString());
        patientDoctorMapDTO.setDoctorLicenseNumber(docJsonObj.get("licenceNo") == null ?
            "" :  docJsonObj.get("licenceNo").toString());
        patientDoctorMapDTO.setDoctorTaxonomyCode(docJsonObj.get("taxonomyCode") == null ?
            "" :  docJsonObj.get("taxonomyCode").toString());
        patientDoctorMapDTO.setDoctorTaxonomyDescription(docJsonObj.get("taxonomyDesc") == null ?
            "" :  docJsonObj.get("taxonomyDesc").toString());
        patientDoctorMapDTO.setDoctorPracticeState(docJsonObj.get("practiceState") == null ?
            "" :  docJsonObj.get("practiceState").toString());
        patientDoctorMapDTO.setDoctorNpiNumber(docJsonObj.get("npiNumber") == null ?
            "" :  docJsonObj.get("npiNumber").toString());
        patientDoctorMapDTO.setDoctorId(docJsonObj.get("doctorId") == null ?
            0 :  Long.parseLong(docJsonObj.get("doctorId").toString()));
        patientDoctorMapDTO.setDoctorEfax(docJsonObj.get("efax") == null ?
            "" :  docJsonObj.get("efax").toString());
        patientDoctorMapDTO.setDoctorAddressCountry(docJsonObj.get("countryName") == null ?
            "" :  docJsonObj.get("countryName").toString());
        patientDoctorMapDTO.setDoctorEmail(docJsonObj.get("email") == null ?
            "" :  docJsonObj.get("email").toString());
        if(patientDoctorMapDTO.getDoctorNpiNumber() != null) {
            return Mono.just(new ResponseDTO(true, "Data Fetched Succesfully.", patientDoctorMapDTO));
        }else{
            return Mono.just(new ResponseDTO(false, "Data Not Found.", null));
        }
    }
    @Override
    public Mono<Long> getIDByUUID(UUID patientDoctorMapUuid) {
        return patientDoctorMappingSearchQueryHandler.getIDByUUID(patientDoctorMapUuid);
    }

    @Override
    public Mono<ResponseDTO> getPatientDoctor(ResponseDTO doctorDeatils, PatientDoctorMappingOutputDTO patientDoctorMappingOutputDTO,Long countPats,Long patientId, String npiId) throws ExecutionException, InterruptedException {
        System.out.println("patientDoctorMapDTOMono ==============="+patientDoctorMappingOutputDTO);
        if (patientDoctorMappingOutputDTO==null || patientDoctorMappingOutputDTO.getPatientDoctorMapId()==null) {
            System.out.println("===============Save PatientDoctorMap ===============");
            SavePatientDoctorMappingCommand savePatientDoctorMappingCommand = new SavePatientDoctorMappingCommand();
            savePatientDoctorMappingCommand.setPatientId(patientId);
            savePatientDoctorMappingCommand.setPatientDoctorMapId(0L);
            savePatientDoctorMappingCommand.setDoctorNpiNumber(npiId);
            System.out.println("===============countPats ===============" + countPats);
            //Long countPats,JSONObject docJson,SavePatientDoctorMappingCommand docObj,String message
            return patientDoctorMappingServiceExtended.savePatientDoctorMappingV2(countPats,doctorDeatils,savePatientDoctorMappingCommand,"")
                .map(x->doctorDeatils);
        }else{
            return Mono.just(new ResponseDTO(true,"PatientDoctorMap Data Fetched.",patientDoctorMappingOutputDTO));
        }
    }

    @Override
    public Flux<PatientDoctorMapPatientMasterExtendedDTO> getPatientDoctorsByPatientId(Long patientId){
        return patientDoctorMappingSearchQueryHandler.getPatientDoctorsByPatientId(patientId);
    }

    @Override
    public Mono<Object> getPatientDoctorsByPatientDoctorId(Long patientDoctorMapId){
        return patientDoctorMappingSearchQueryHandler.getPatientDoctorsByPatientDoctorId(patientDoctorMapId);
    }

    @Override
    public Mono<Object> getCurrentPatientDoctorsByMaxId(Long patientId){
        return patientDoctorMappingSearchQueryHandler.getCurrentPatientDoctorsByMaxId(patientId);
    }

    @Override
    public Mono<ResponseDTO> savePatientDoctorMappingV2(PatientDoctorMappingParameterV2DTO patientDoctorMappingParameterV2DTO) {
        return null;
    }
}
