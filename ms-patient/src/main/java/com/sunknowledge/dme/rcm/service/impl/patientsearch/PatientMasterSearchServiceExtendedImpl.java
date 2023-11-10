package com.sunknowledge.dme.rcm.service.impl.patientsearch;

import com.dropbox.core.InvalidAccessTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.dto.PatientCombinedSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientDtoDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientBucketParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientExistCheckingParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.CheckingPatientExistanceByNameAndZipAndBranchAndDob;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByBasicInfoOrAddressOrBranch;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByCombinedInfo;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByPatientId;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientSearchByPatientIdNo;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDTOSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientMasterSearchQueryHandler;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import java.util.Properties;
import java.util.UUID;

@Service("patientMasterSearchServiceExtendedImpl")
public class PatientMasterSearchServiceExtendedImpl implements PatientMasterSearchServiceExtended {
    @Autowired
    PatientMasterSearchQueryHandler patientMasterSearchQueryHandler;

    @Autowired
    @Qualifier("patientDTOSearchServiceExtendedImpl")
    PatientDTOSearchServiceExtended patientDTOSearchServiceExtended;

    @Override
    public Flux<PatientMasterDTO> getPatientBySearchParameters(PatientSearchByBasicInfoOrAddressOrBranch obj) {
//        PatientSearchByBasicInfoOrAddressOrBranch obj =
//            new PatientSearchByBasicInfoOrAddressOrBranch();
//        obj.setPatientID(patientSearchBasicParameterDTO.getPatientID());
//        obj.setName(patientSearchBasicParameterDTO.getName());
//        obj.setDob(patientSearchBasicParameterDTO.getDob());
//        obj.setSsnNo(patientSearchBasicParameterDTO.getSsnNo());
//        obj.setAddress(patientSearchBasicParameterDTO.getAddress());
//        obj.setPhone(patientSearchBasicParameterDTO.getPhone());
//        obj.setBranchId(patientSearchBasicParameterDTO.getBranchId());
//        obj.setBranchName(patientSearchBasicParameterDTO.getBranchName());
        return patientMasterSearchQueryHandler.getPatientBySearchParametersQueryHandler(obj);
    }

    @Override
    public Flux<PatientMasterDTO> checkPatientIsAlreadyExistOrNot(PatientExistCheckingParameterDTO patientExistCheckingParameterDTO) {
        CheckingPatientExistanceByNameAndZipAndBranchAndDob obj =
            new CheckingPatientExistanceByNameAndZipAndBranchAndDob();
        obj.setName(patientExistCheckingParameterDTO.getName());
        obj.setDob(patientExistCheckingParameterDTO.getDob());
        obj.setZip(patientExistCheckingParameterDTO.getZip());
        obj.setPhone(patientExistCheckingParameterDTO.getPhone());
        obj.setBranchId(patientExistCheckingParameterDTO.getBranchId());
        return patientMasterSearchQueryHandler.checkPatientIsAlreadyExistOrNotQueryHandler(obj);
    }

    @Override
    public Flux<PatientMasterDTO> getPatientByPatientIdNo(String patientIdNo) {
        PatientSearchByPatientIdNo obj = new PatientSearchByPatientIdNo();
        obj.setPatientIdNo(patientIdNo);
        return patientMasterSearchQueryHandler.getPatientByPatientIdNoQueryHandler(obj);
    }

    @Override
    public String generatePatientIDNumber() {
        return patientMasterSearchQueryHandler.generatePatientIDNumber();
    }

    @Override
    public Flux<PatientDtoDTO> getPatientBucketData(PatientBucketParameterDTO patientBucketParameterDTO) {
        return patientDTOSearchServiceExtended.getPatientBucketDataBySearchParam(patientBucketParameterDTO);
    }

    @Override
    public Mono<Long> getIDByUUID(UUID patientUuid) {
        return patientMasterSearchQueryHandler.getIDByUUID(patientUuid);
    }

    @Override
    public Flux<PatientCombinedSearchOutputDTO>  getPatientByCombinedSearchParameters(PatientSearchByCombinedInfo obj) {
        return patientMasterSearchQueryHandler.getPatientByCombinedSearchParameters(obj);
    }

    @Override
    public Mono<PatientMasterDTO> getPatientDetailsByPatientId(Long id) {
        PatientSearchByPatientId obj = new PatientSearchByPatientId();
        obj.setPatientId(id);
        return patientMasterSearchQueryHandler.getPatientByPatientIdQueryHandler(obj);
    }

    @Override
    public String getPosNameById(Long posId) {
        return patientMasterSearchQueryHandler.getPosNameById(posId);
    }

    @Override
    public Mono<PatientMasterDTO> getPatientByPatientId(Long patientId) {
        return patientMasterSearchQueryHandler.getPatientByPatientId(patientId);
    }


    // Mono<ServiceOutcome> checkPatientBillingAddressVerifiableOrNot(billingAddressLine1, billingAddressLine2, billingAddressCity, billingAddressState, billingAddressZip)
    @Override
    public Mono<ServiceOutcome> checkPatientBillingAddressVerifiableOrNot(PatientMasterDTO patientData) {
        if(patientData.getBillingAddressLine1() == null || patientData.getBillingAddressLine1().trim().equals("")){
            return Mono.just(new ServiceOutcome(null, false, "Billing Address Line1 must be provided."));
        }

        if(patientData.getBillingAddressZip() == null || patientData.getBillingAddressZip().trim().equals("")){
            return Mono.just(new ServiceOutcome(null, false, "Billing Zip must be provided."));
        }

        if(patientData.getBillingAddressCity() == null || patientData.getBillingAddressCity().trim().equals("")){
            return Mono.just(new ServiceOutcome(null, false, "Billing Address City must be provided."));
        }

        if(patientData.getBillingAddressState() == null || patientData.getBillingAddressState().trim().equals("")){
            return Mono.just(new ServiceOutcome(null, false, "Billing Address State must be provided."));
        }

        String address1 = patientData.getBillingAddressLine1()!= null ? patientData.getBillingAddressLine1().trim() : "";
        String address2 = patientData.getBillingAddressLine2()!= null ? patientData.getBillingAddressLine2().trim() : "";
        String city = patientData.getBillingAddressCity()!= null ? patientData.getBillingAddressCity().trim() : "";
        String state = patientData.getBillingAddressState()!= null ? patientData.getBillingAddressState().trim() : "";
        String zip5 = patientData.getBillingAddressZip()!= null ? patientData.getBillingAddressZip().trim() : "";
        //zip4 = data.getDeliveryZipCode()!= null ? data.getDeliveryZipCode() : "";

        ObjectMapper mapper = new ObjectMapper();
        ServiceOutcome responseBody = new ServiceOutcome();

        try {
            String accessToken = InternalAccessTokenUtilities.getAccessToken();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();
            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                CommonUtilities commonUtilitiesObj = new CommonUtilities();
                Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");
                //address1=&address2=1104%20DIANTHUS%20LN&city=EL%20DORADO%20HILLS&state=CA&zip5=95762&zip4=
                String url = propData.getProperty("usps_url");
                String paramValue = "?address1=" + address1 + "&address2=" + address2 +"&city="+ city +"&state="+ state + "&zip5=" + zip5;
                String completeUrl = url + paramValue;

                System.out.println("completeUrl " + completeUrl);

                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                HttpEntity entityData = new HttpEntity<>(headersData);
                ResponseEntity responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.GET,
                    entityData,
                    Object.class
                );

                if (responseData.getStatusCode() == HttpStatus.OK) {
                    responseBody = mapper.convertValue(responseData.getBody(), ServiceOutcome.class);
                    responseBody.setOutcome(responseBody.getOutcome());  // true means deliverable, false means not deliverable
                    if(responseBody.getOutcome()){
                        responseBody.setMessage("Valid Address.");
                    }else{
                        responseBody.setMessage("Not Valid Address.");
                    }
                    responseBody.setData(responseBody.getData());

                } else {
                    responseBody.setOutcome(false);
                    responseBody.setMessage("API Error: No Response Data Available");
                    responseBody.setData(null);
                }
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

        return Mono.just(responseBody);
    }

    @Override
    public Mono<Boolean> checkSameAsDeliveryAddress(PatientMasterDTO patientData) {
        boolean sameAsDeliveryAddress = false;
        if(patientData.getAddressLine1().trim().equals(patientData.getBillingAddressLine1().trim()) && patientData.getCity().trim().equals(patientData.getBillingAddressCity().trim())
            && patientData.getState().trim().equals(patientData.getBillingAddressState().trim()) && patientData.getZip().trim().equals(patientData.getBillingAddressZip().trim())){
            if ((patientData.getAddressLine2()!=null && patientData.getBillingAddressLine2()!=null) && (patientData.getAddressLine2().trim().equals(patientData.getBillingAddressLine2().trim()))){
                sameAsDeliveryAddress = true;
            }else if(patientData.getAddressLine2()==null && patientData.getBillingAddressLine2()==null){
                sameAsDeliveryAddress = true;
            }
        }
        return Mono.just(sameAsDeliveryAddress);
    }

    @Override
    public Mono<String> getBranchNameByBranchId(Long branchId) {
        return patientMasterSearchQueryHandler.getBranchNameByBranchId(branchId);
    }
}
