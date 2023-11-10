package com.sunknowledge.dme.rcm.service.impl.patiententry;

import com.dropbox.core.InvalidAccessTokenException;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.dto.PatientDoctorMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingParameterV2DTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDoctorMappingCommand;
import com.sunknowledge.dme.rcm.service.patiententry.PatientDoctorMappingServiceExtended;
import com.sunknowledge.dme.rcm.service.patiententry.aggregate.PatientDoctorMappingAggregate;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Transactional
@Service("patientDoctorMappingServiceExtended")
public class PatientDoctorMappingServiceExtendedImpl implements PatientDoctorMappingServiceExtended {
    @Autowired
    PatientDoctorMappingAggregate patientDoctorMappingAggregate;

    @Autowired
    @Qualifier("patientMasterSearchServiceExtendedImpl")
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;

    @Override
    public Mono<ResponseDTO> savePatientDoctorMapping(SavePatientDoctorMappingCommand docObj,Long countPats) {
        PatientDoctorMapDTO patientDoctorMapDTO = null;
        String message = "";
        Boolean status = false;
        List<Object> responseList = new ArrayList<>();

        try {
            /*//----- Get Details to check patient is exist or not -----
            PatientSearchByBasicInfoOrAddressOrBranch obj =
                new PatientSearchByBasicInfoOrAddressOrBranch();
            obj.setPatientID(docObj.getPatientId());
            Flux<PatientMasterDTO> list = patientMasterSearchServiceExtended.getPatientBySearchParameters(obj);
            //----- Get Details to check patient is exist or not -----*/

            //----- Get details for check doctor is exist or not -----
            String accessToken = InternalAccessTokenUtilities.getAccessToken();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            String responseBody = "[]";
            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                CommonUtilities commonUtilitiesObj = new CommonUtilities();
                Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");
                String url = propData.getProperty("utility_url");
//                String npiNumber = "1306951827";
                String npiNumber = docObj.getDoctorNpiNumber() == null ? "0" : docObj.getDoctorNpiNumber();
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                HttpEntity entityData = new HttpEntity<>(headersData);
                ResponseEntity responseData = restTemplateData.exchange(
                    url +
                        "?npiNumber={npiNumber}&operationType=Persist",
                    HttpMethod.GET,
                    entityData,
                    String.class,
                    npiNumber);

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

            JSONObject docJson = (JSONObject) parser.parse(responseBody == null ? "{}" : responseBody);
            System.out.println("docJson "+ docJson);

            System.out.println("======outcome======"+docJson.get("outcome"));
            boolean npiOutcome = (boolean) docJson.get("outcome");
            if(!npiOutcome){
                return Mono.just(new ResponseDTO(false, "NPI Registry Server is down/Data not exists, Please try again later!", null));
            }
            //----- Get details for check doctor is exist or not -----

            System.out.println("countPats "+ countPats);
            List list = new ArrayList();
            //list.add(docJson.get("data"));
            ResponseDTO docJsonData = new ResponseDTO(true, message, docJson.get("data"));
            return savePatientDoctorMappingV2(countPats,docJsonData,docObj,message);

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

    @Override
    public Mono<ResponseDTO> savePatientDoctorMappingV2(Long countPats,ResponseDTO docJson,SavePatientDoctorMappingCommand docObj,String message){
        if (countPats > 0) {
            System.out.println("docJson "+ docJson);
            if (docJson !=null && docJson.getStatus()) {
                PatientDoctorMappingOutputDTO patientDoctorMapDTO = (PatientDoctorMappingOutputDTO) docJson.getData();
                System.out.println("docJsonList " + patientDoctorMapDTO);
                if(patientDoctorMapDTO!=null && !patientDoctorMapDTO.getDoctorNpiNumber().equals("")) {
                    /*ArrayList docJsonList2 = (ArrayList) docJsonList.get(0);
                    System.out.println("Inside docJson If");
                    System.out.println("docJsonObj " + docJsonList2);
                    JSONObject docJsonObj = (JSONObject) docJsonList2.get(0);
                    System.out.println("docJsonList3 " + docJsonObj);
                    String firstName = docJsonObj.get("firstName") == null ?
                        "" : docJsonObj.get("firstName").toString();
                    String middleName = docJsonObj.get("middleName") == null ?
                        "" : docJsonObj.get("middleName").toString();
                    String lastName = docJsonObj.get("lastName") == null ?
                        "" :  docJsonObj.get("lastName").toString();
                    String suffix = docJsonObj.get("suffix") == null ?
                        "" :  docJsonObj.get("suffix").toString();
                    String addressLine1 = docJsonObj.get("addressLine1") == null ?
                        "" :  docJsonObj.get("addressLine1").toString();
                    String addressLine2 = docJsonObj.get("addressLine2") == null ?
                        "" :  docJsonObj.get("addressLine2").toString();
                    String city = docJsonObj.get("city") == null ?
                        "" :  docJsonObj.get("city").toString();
                    String state = docJsonObj.get("state") == null ?
                        "" :  docJsonObj.get("state").toString();
                    String zip = docJsonObj.get("zip") == null ?
                        "" :  docJsonObj.get("zip").toString();
                    String contactNo1 = docJsonObj.get("contactNo1") == null ?
                        "" :  docJsonObj.get("contactNo1").toString();
                    String contactNo2 = docJsonObj.get("contactNo2") == null ?
                        "" :  docJsonObj.get("contactNo2").toString();
                    String faxNumber = docJsonObj.get("faxNumber") == null ?
                        "" :  docJsonObj.get("faxNumber").toString();
                    String gender = docJsonObj.get("gender") == null ?
                        "" :  docJsonObj.get("gender").toString();
                    String licenceNo = docJsonObj.get("licenceNo") == null ?
                        "" :  docJsonObj.get("licenceNo").toString();
                    String taxonomyCode = docJsonObj.get("taxonomyCode") == null ?
                        "" :  docJsonObj.get("taxonomyCode").toString();
                    String taxonomyDesc = docJsonObj.get("taxonomyDesc") == null ?
                        "" :  docJsonObj.get("taxonomyDesc").toString();
                    String practiceState = docJsonObj.get("practiceState") == null ?
                        "" :  docJsonObj.get("practiceState").toString();
                    String npiNumber = docJsonObj.get("npiNumber") == null ?
                        "" :  docJsonObj.get("npiNumber").toString();
                    docObj.setDoctorFirstName(firstName);
                    docObj.setDoctorMiddleName(middleName);
                    docObj.setDoctorLastName(lastName);
                    docObj.setDoctorNameSuffix(suffix);
                    docObj.setDoctorAddressLine1(addressLine1);
                    docObj.setDoctorAddressLine2(addressLine2);
                    docObj.setDoctorAddressCity(city);
                    docObj.setDoctorAddressState(state);
                    docObj.setDoctorAddressZip(zip);
                    docObj.setDoctorContact1(contactNo1);
                    docObj.setDoctorContact2(contactNo2);
                    docObj.setDoctorFax(faxNumber);
                    docObj.setDoctorGender(gender);
                    docObj.setDoctorLicenseNumber(licenceNo);
                    docObj.setDoctorPracticeState(practiceState);  //
                    docObj.setDoctorTaxonomyCode(taxonomyCode);   //
                    docObj.setDoctorTaxonomyDescription(taxonomyDesc);
                    docObj.setDoctorNpiNumber(npiNumber);*/
                    Long patientId = docObj.getPatientId();
                    Long patientDoctorMapId = docObj.getPatientDoctorMapId();
                    String npiNumber = docObj.getDoctorNpiNumber();

                    BeanUtils.copyProperties(patientDoctorMapDTO,docObj);
                    docObj.setPatientId(patientId);
                    docObj.setPatientDoctorMapId(patientDoctorMapId);
                    docObj.setDoctorNpiNumber(npiNumber);
                    return patientDoctorMappingAggregate.handleSavePatientDoctorMapping(docObj);
                }else{
                    message = "Mapping Failed! Doctor does not exist / NPI service is down.";
                    return Mono.just(new ResponseDTO(false, message, new ArrayList<>()));
                }
            } else {
                message = "Mapping Failed! Doctor does not exist / NPI service is down.";
                return Mono.just(new ResponseDTO(false, message, new ArrayList<>()));
            }
        }
        else {
            message = "Mapping Failed! Patient does not exist.";
            return Mono.just(new ResponseDTO(false, message, new ArrayList<>()));
        }
    }

    @Override
    public Mono<ResponseDTO> savePatientDoctorMapping_V2(SavePatientDoctorMappingCommand savePatientDoctorMappingCommand) {
        System.out.println("savePatientDoctorMappingCommand "+savePatientDoctorMappingCommand);
        return patientDoctorMappingAggregate.handleSavePatientDoctorMapping(savePatientDoctorMappingCommand);
    }
}
