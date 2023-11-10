package com.sunknowledge.dme.rcm.service.impl.common;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.UszipMaster;
import com.sunknowledge.dme.rcm.repository.common.UszipMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.common.UszipMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.UszipMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.DropdownDTO;
import com.sunknowledge.dme.rcm.service.mapper.UszipMasterMapper;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Service
public class UszipMasterServiceExtendedImpl implements UszipMasterServiceExtended {

    @Autowired
    UszipMasterRepositoryExtended uszipMasterRepositoryExtended;
    @Autowired
    UszipMasterMapper uszipMasterMapper;

    @Override
    public UszipMasterDTO save(UszipMasterDTO uszipMasterDTO) {
        return null;
    }

    @Override
    public UszipMasterDTO update(UszipMasterDTO uszipMasterDTO) {
        return null;
    }

    @Override
    public Optional<UszipMasterDTO> partialUpdate(UszipMasterDTO uszipMasterDTO) {
        return Optional.empty();
    }

    @Override
    public Page<UszipMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<UszipMasterDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ServiceOutcome getUsZipDetails(Long zipCode) {

        UszipMaster uszipMaster = new UszipMaster();
        uszipMaster = uszipMasterRepositoryExtended.findByZipCodeAndStatusIgnoreCase(zipCode,"active");
        if(uszipMaster!=null){
            return new ServiceOutcome<>(uszipMasterMapper.toDto(uszipMaster),true,"Data Fetched Successfully");
        }else{
            ServiceOutcome outcome = getUsZipDetailsFromExternalUrl(zipCode);
            if(outcome.getOutcome()){
                uszipMaster = new UszipMaster();
                JSONObject zipDetails = (JSONObject) outcome.getData();
                System.out.println("zipDetails "+zipDetails);

                uszipMaster.setZipCode(zipCode);
                uszipMaster.setStatus("Active");
                uszipMaster.setCityName((String) zipDetails.get("default_city"));
                uszipMaster.setStateCode((String) zipDetails.get("state_abbreviation"));
                uszipMaster.setStateName((String) zipDetails.get("state"));
                uszipMaster.setUszipMasterUuid(UUID.randomUUID());
                System.out.println("uszipMaster "+uszipMaster);
                UszipMaster savedUszipMaster = uszipMasterRepositoryExtended.save(uszipMaster);
                return new ServiceOutcome<>(uszipMasterMapper.toDto(savedUszipMaster),true,"Data Fetched from External Url & Saved Successfully");
            }else {
                return new ServiceOutcome<>(null,false,"Data Not Found");
            }
        }
    }

    private ServiceOutcome getUsZipDetailsFromExternalUrl(Long zipCode) {
        try {
            List responseList = new ArrayList<>();

            //=========================Call Zip Service============================================
            String responseBody = "[]";
            JSONParser parser = new JSONParser();

            CommonUtilities commonUtilitiesObj = new CommonUtilities();
            Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

            String url = propData.getProperty("US_ZIP_URL");
            String ZIP_AUTH_ID = propData.getProperty("ZIP_AUTH_ID");
            String ZIP_AUTH_TOKEN = propData.getProperty("ZIP_AUTH_TOKEN");

            String param = "?auth-id={ZIP_AUTH_ID}&auth-token={ZIP_AUTH_TOKEN}&zipcode={zipCode}";

            String completeUrl = url + param;
            System.out.println("completeUrl "+completeUrl);
            RestTemplate restTemplateData = new RestTemplate();
            HttpHeaders headersData = new HttpHeaders();
            headersData.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entityData = new HttpEntity<>(headersData);
            ResponseEntity responseData = restTemplateData.exchange(
                completeUrl,
                HttpMethod.GET,
                entityData,
                String.class,
                ZIP_AUTH_ID,
                ZIP_AUTH_TOKEN,
                zipCode
            );

            if (responseData.getStatusCode() == HttpStatus.OK) {
                responseBody = (String) responseData.getBody();
            } else {
                String message = "API Error: API Not Available";
                return new ServiceOutcome(responseList, false,message );
            }
            JSONArray zipJson = (JSONArray) parser.parse(responseBody == null ? "[]" : responseBody);
            //System.out.println("zipJson "+zipJson);
            if (zipJson!=null && zipJson.size() > 0) {
                JSONObject zipJsonObj = (JSONObject) zipJson.get(0);
                //System.out.println("zipJsonObj "+zipJsonObj);
                if(zipJsonObj != null) {
                    JSONArray zipcodesList = (JSONArray) zipJsonObj.get("zipcodes");
                    //System.out.println("zipcodesList " + zipcodesList);
                    if(zipcodesList!=null && zipcodesList.size()>0) {
                        JSONObject zipcodesObj = (JSONObject) zipcodesList.get(0);
                        //System.out.println("zipcodesObj " + zipcodesObj);
                        return new ServiceOutcome(zipcodesObj,true,"Successfully Data Fetched from external URL");
                    }else{
                        String message = "API Error: No Response Data Available";
                        return new ServiceOutcome(responseList,false ,message);
                    }
                }else{
                    String message = "API Error: No Response Data Available";
                    return new ServiceOutcome(responseList,false ,message);
                }
            } else {
                String message = "API Error: No Response Data Available";
                return new ServiceOutcome(responseList,false ,message);
            }
            //=======================================================================================
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
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
    public ServiceOutcome getUSZipDetailsByZipCode(Long zipCode){

        if (zipCode <= 0) {
            return new ServiceOutcome<>(null,false,"Zip_Code must be greater than or equal to 1");
        }
        if(zipCode!=null){
            String zipCodeStrFormatted = zipCode+"%";
            List<UszipMaster> uszipMasterList = uszipMasterRepositoryExtended.findByZipCodeAndStatusActive(zipCodeStrFormatted, "active");
            List<Map<String, Object>> list = new ArrayList<>();
            if(uszipMasterList.size() > 0){
                list = uszipMasterList.stream()
                    .map(usZipMasterObj -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("uszipMasterId", usZipMasterObj.getUszipMasterId());
                        map.put("zipCode", usZipMasterObj.getZipCode());
                        map.put("cityName", usZipMasterObj.getCityName());
                        map.put("stateName", usZipMasterObj.getStateName());
                        map.put("status", usZipMasterObj.getStatus());
                        map.put("uszipMasterUuid", usZipMasterObj.getUszipMasterUuid());
                        map.put("country", "USA");
                        return map;
                    })
                    .collect(Collectors.toList());
            }

            return new ServiceOutcome<>(list.size() > 0 ? list : new ArrayList<>(),list.size() > 0 ?true: false,list.size() > 0 ? "Data Fetched Successfully" : "Data Not Found");
        }else{
            return new ServiceOutcome<>(null,false,"Zip_Code should not be null");
        }
    }

    @Override
    public ServiceOutcome getUSZipDetailByIndividualZipCode(Long zipCode){
        if (zipCode <= 0) {
            return new ServiceOutcome<>(null,false,"Zip_Code must be greater than or equal to 1");
        }

        if(zipCode!=null){
            UszipMaster uszipMasterObj = uszipMasterRepositoryExtended.findByZipCodeAndStatusIgnoreCase(zipCode, "active");
            Map<String, Object> uszipMasterObjMap = new HashMap<>();
            if(uszipMasterObj.getUszipMasterId() != null){
                uszipMasterObjMap.put("uszipMasterId", uszipMasterObj.getUszipMasterId());
                uszipMasterObjMap.put("zipCode", uszipMasterObj.getZipCode());
                uszipMasterObjMap.put("cityName", uszipMasterObj.getCityName());
                uszipMasterObjMap.put("stateName", uszipMasterObj.getStateName());
                uszipMasterObjMap.put("status", uszipMasterObj.getStatus());
                uszipMasterObjMap.put("uszipMasterUuid", uszipMasterObj.getUszipMasterUuid());
                uszipMasterObjMap.put("country", "USA");
            }

            return new ServiceOutcome<>(uszipMasterObjMap.isEmpty() ? null : uszipMasterObjMap, uszipMasterObjMap.isEmpty() ?false: true, uszipMasterObjMap.isEmpty() ? "Data Not Found" : "Data Fetched Successfully");
        }else{
            return new ServiceOutcome<>(null,false,"Data Not Found");
        }
    }

    @Override
    public List<Map> getStateDropdown() {
        List<Map> maps = uszipMasterRepositoryExtended.getDistinctStateCode();
        return maps;
    }
}
