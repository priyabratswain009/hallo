package com.sunknowledge.dme.rcm.service.impl.icd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.IcdMaster;
import com.sunknowledge.dme.rcm.dto.icd.ICDResultOutcome;
import com.sunknowledge.dme.rcm.dto.icd.RequestICDInput;
import com.sunknowledge.dme.rcm.dto.icd.ResponseOutcome;
import com.sunknowledge.dme.rcm.repository.icd.IcdMasterRepo;
import com.sunknowledge.dme.rcm.service.dto.IcdMasterDTO;
import com.sunknowledge.dme.rcm.service.icd.ICDService;
import com.sunknowledge.dme.rcm.utils.ApplicationConstants;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.management.InvalidAttributeValueException;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ICDServiceImpl implements ICDService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IcdMasterRepo icdMasterRepository;

    public String getToken() throws Exception {
        log.info("Getting token...");
        URL url = new URL(ApplicationConstants.ICD_TOKENENDPOINT);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        // set parameters to post
        String urlParameters =
            "client_id=" + URLEncoder.encode(ApplicationConstants.ICD_CLIENTID, StandardCharsets.UTF_8) +
                "&client_secret=" + URLEncoder.encode(ApplicationConstants.ICD_CLIENTSECRET, StandardCharsets.UTF_8) +
                "&scope=" + URLEncoder.encode(ApplicationConstants.ICD_SCOPE, StandardCharsets.UTF_8) +
                "&grant_type=" + URLEncoder.encode(ApplicationConstants.ICD_GRANT_TYPE, StandardCharsets.UTF_8);
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        // response
        int responseCode = con.getResponseCode();
        log.info("Token Response Code : " + responseCode + "\n");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // parse JSON response
        JSONObject jsonObj = new JSONObject(response.toString());
        return jsonObj.getString("access_token");
    }

    public ServiceOutcome<ICDResultOutcome> getICD10Information(RequestICDInput requestICDInput, String accessToken) {
        ServiceOutcome<ICDResultOutcome> serviceOutcome = new ServiceOutcome<ICDResultOutcome>();
        try {
            String uri = ApplicationConstants.ICD_URI + "/" + requestICDInput.getReleaseId() + "/" + requestICDInput.getCode();
            HttpHeaders headers = new HttpHeaders();
            headers.add("API-Version", ApplicationConstants.ICD_API_VERSION);
            headers.add("Accept-Language", ApplicationConstants.ICD_ACCEPT_LANGUAGE);
            headers.set("Authorization", "Bearer " + accessToken);
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
            log.info("RESPONSE:" + responseEntity);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                ObjectMapper mapper = new ObjectMapper();
                ResponseOutcome response;
                response = mapper.readValue(responseEntity.getBody(), ResponseOutcome.class);
                ICDResultOutcome resultOutcome = new ICDResultOutcome();
                resultOutcome.setBrowserUrl(response.getBrowserUrl());

                if (response.getChild() != null && response.getChild().size() > 0) {
                    List<String> diseasesList = new ArrayList<String>();
                    List<String> childList = new ArrayList<String>();
                    for (int i = 0; i < response.getChild().size(); i++) {
                        String s1 = response.getChild().get(i).substring(response.getChild().get(i).lastIndexOf("/") + 1);
                        diseasesList.add(i, s1);
                        childList.add(i, response.getChild().get(i));
                    }
                    resultOutcome.setChild(childList);
                    resultOutcome.setDiseasesList(diseasesList);
                }

                resultOutcome.setClassKind(response.getClassKind());
                resultOutcome.setCode(response.getCode());

                if (response.getCodingHint() != null && response.getCodingHint().size() > 0) {
                    List<String> codingHintValues = new ArrayList<String>();
                    for (int i = 0; i < response.getCodingHint().size(); i++) {
                        codingHintValues.add(i, response.getCodingHint().get(i).getLabel().getValue());
                    }
                    resultOutcome.setCodingHints(codingHintValues);
                }

                resultOutcome.setContext(response.getContext());

                if (response.getExclusion() != null && response.getExclusion().size() > 0) {
                    List<String> exclusionValues = new ArrayList<String>();
                    for (int i = 0; i < response.getExclusion().size(); i++) {
                        exclusionValues.add(i, response.getExclusion().get(i).getLabel().getValue());
                    }
                    resultOutcome.setExclusionValues(exclusionValues);
                }

                resultOutcome.setId(response.getId());

                if (response.getInclusion() != null && response.getInclusion().size() > 0) {
                    List<String> inclusionValues = new ArrayList<String>();
                    for (int i = 0; i < response.getInclusion().size(); i++) {
                        inclusionValues.add(i, response.getInclusion().get(i).getLabel().getValue());
                    }
                    resultOutcome.setInclusionValues(inclusionValues);
                }

                if (response.getParent() != null && response.getParent().size() > 0) {
                    List<String> parentList = new ArrayList<String>();
                    for (int i = 0; i < response.getParent().size(); i++) {
                        parentList.add(i, response.getParent().get(i));
                    }
                    resultOutcome.setParent(parentList);
                }
                resultOutcome.setTitleValue(response.getTitle().getValue());


                serviceOutcome.setData(resultOutcome);
                serviceOutcome.setMessage("Successfully accessed ICD 10 information.");
                serviceOutcome.setOutcome(true);
            } else {
                serviceOutcome.setData(null);
                serviceOutcome.setMessage("Failed to accesse ICD 10 information.");
                serviceOutcome.setOutcome(false);
            }
        } catch (HttpClientErrorException re) {
            if (HttpStatus.NOT_FOUND.equals(re.getStatusCode())) {
                log.info("404:NOT FOUND");
                serviceOutcome.setData(null);
                serviceOutcome.setMessage("Requested resource could not be found");
                serviceOutcome.setOutcome(false);
            } else if (HttpStatus.UNAUTHORIZED.equals(re.getStatusCode())) {
                log.info("401:UNAUTHORIZED");
                serviceOutcome.setData(null);
                serviceOutcome.setMessage("Unauthorized, Token gets expired");
                serviceOutcome.setOutcome(false);
            } else {
                log.info("OK");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            serviceOutcome.setData(null);
            serviceOutcome.setMessage("Requested resource could not be found");
            serviceOutcome.setOutcome(false);
        }
        return serviceOutcome;
    }

    public ServiceOutcome<IcdMasterDTO> getICDInformation(String requestICDInput, String icdType) {
        ServiceOutcome<IcdMasterDTO> serviceOutcome = new ServiceOutcome<IcdMasterDTO>();
        try {
            String uri = null;
//            String result = null;
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = null;
            if (icdType.equalsIgnoreCase("ICD-10-CM")) {
//                result = requestICDInput.substring(0, 2) + "." + requestICDInput.substring(2);
                uri = ApplicationConstants.ICD_CLINICAL_ICD10CM_URI + "?terms=" + requestICDInput;
                responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
            } else if (icdType.equalsIgnoreCase("ICD-9-CM diagnoses")) {
//                result = requestICDInput.substring(0, 2) + "." + requestICDInput.substring(2);
                uri = ApplicationConstants.ICD_CLINICAL_ICD9CM_DX_URI + "?terms=" + requestICDInput;
                responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
            } else if (icdType.equalsIgnoreCase("ICD-9-CM procedures")) {
//                result = requestICDInput.substring(0, 2) + "." + requestICDInput.substring(2);
                uri = ApplicationConstants.ICD_CLINICAL_ICD9CM_SG_URI + "?terms=" + requestICDInput;
                responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
            }
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                String[] strArray = responseEntity.getBody().split(",null,");
                String description = strArray[1].replaceAll("[^ .,a-zA-Z0-9]", "").split(requestICDInput + ",")[1];
                System.out.println("" + description);
                IcdMasterDTO icdMasterDTO = new IcdMasterDTO();
                icdMasterDTO.setIcdCode(requestICDInput);
                icdMasterDTO.setIcdCodeDesc(description.trim());
                icdMasterDTO.setIcdCodeType(icdType);
                serviceOutcome.setData(icdMasterDTO);
                serviceOutcome.setMessage("Successfully accessed ICD information.");
                serviceOutcome.setOutcome(true);
                saveICDInformation(icdMasterDTO);
            } else {
                serviceOutcome.setData(null);
                serviceOutcome.setMessage("Failed to accessed ICD information.");
                serviceOutcome.setOutcome(false);
            }
        } catch (HttpClientErrorException re) {
            if (HttpStatus.NOT_FOUND.equals(re.getStatusCode())) {
                log.info("404:NOT FOUND");
                serviceOutcome.setData(null);
                serviceOutcome.setMessage("Requested resource could not be found");
                serviceOutcome.setOutcome(false);
            } else {
                log.info("OK");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            serviceOutcome.setData(null);
            serviceOutcome.setMessage("Requested resource could not be found");
            serviceOutcome.setOutcome(false);
        }
        return serviceOutcome;
    }

    @Override
    public IcdMasterDTO getICDDetailsOnCode(String icdCode) {
        IcdMasterDTO icdMasterDTO = null;
        IcdMaster icdMaster = icdMasterRepository.getICDDetailsOnCode(icdCode);
        if (icdMaster != null) {
            icdMasterDTO = new IcdMasterDTO();
            BeanUtils.copyProperties(icdMaster, icdMasterDTO);
            long daysElapsed;
            if (icdMaster.getUpdatedDate() != null) {
                daysElapsed = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), icdMaster.getUpdatedDate());
            } else {
                daysElapsed = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), icdMaster.getCreatedDate());
            }
            if (daysElapsed > 15) {
                icdMasterDTO = updateICDInformation(icdMasterDTO);
            }
        }
        return icdMasterDTO;
    }

    @Override
    public IcdMasterDTO saveICDInformation(IcdMasterDTO icdMasterDTO) {
        IcdMaster icdMaster = new IcdMaster();
        icdMaster.setIcdCode(icdMasterDTO.getIcdCode());
        icdMaster.setIcdCodeDesc(icdMasterDTO.getIcdCodeDesc());
        icdMaster.setIcdCodeType(icdMasterDTO.getIcdCodeType());
        icdMaster.setCreatedByName("Admin");
        icdMaster.setCreatedById(0L);
        icdMaster.setCreatedDate(LocalDate.now());
        icdMaster.setStatus("Active");
        icdMaster = icdMasterRepository.save(icdMaster);
        BeanUtils.copyProperties(icdMaster, icdMasterDTO);
        return icdMasterDTO;
    }

    public IcdMasterDTO updateICDInformation(IcdMasterDTO icdMasterDTO) {
        Optional<IcdMaster> icdMaster = icdMasterRepository.findById(icdMasterDTO.getIcdMasterId());
        icdMaster.get().setIcdCode(icdMasterDTO.getIcdCode());
        icdMaster.get().setIcdCodeDesc(icdMasterDTO.getIcdCodeDesc());
        icdMaster.get().setIcdCodeType(icdMasterDTO.getIcdCodeType());
        icdMaster.get().setUpdatedByName("Admin");
        icdMaster.get().setUpdatedById(0L);
        icdMaster.get().setUpdatedDate(LocalDate.now());
        icdMaster.get().setStatus("Active");
        IcdMaster updateIcdMaster = icdMasterRepository.save(icdMaster.get());
        BeanUtils.copyProperties(updateIcdMaster, icdMasterDTO);
        return icdMasterDTO;
    }

    @Override
    public ServiceOutcome<List<IcdMasterDTO>> getIcdMasterInfoByIcdCodeOrIcdDescription(String icdCode, String icdDescription, String status) throws InvalidAttributeValueException {
        ServiceOutcome<List<IcdMasterDTO>> serviceOutcome = new ServiceOutcome<>();
        List<IcdMasterDTO> icdMasterDTOList = new ArrayList<>();
        if (icdCode != null && !icdCode.equalsIgnoreCase("")) {
            icdCode = icdCode.trim();
        }

        if (icdDescription != null && !icdDescription.equalsIgnoreCase("")) {
            icdDescription = icdDescription.trim();
        }

        List<IcdMaster> icdMasterList = icdMasterRepository.findByStatusIgnoreCaseAndIcdCodeLikeIgnoreCaseOrIcdCodeDescLikeIgnoreCase(status, "%" + icdCode + "%", "%" + icdDescription + "%");

        if (icdMasterList != null && icdMasterList.size() > 0) {
            for (IcdMaster icdMaster : icdMasterList) {
                IcdMasterDTO icdMasterDTO = new IcdMasterDTO();
                try {
                    // Copy properties from IcdMaster to IcdMasterDTO
                    BeanUtils.copyProperties(icdMaster, icdMasterDTO);
                } catch (Exception e) {
                    // Handle any exceptions that may occur during copying (e.g., IllegalAccessException)
                    e.printStackTrace();
                }
                icdMasterDTOList.add(icdMasterDTO);
            }
        }

        if (icdMasterList != null && icdMasterList.size() > 0) {
            serviceOutcome.setData(icdMasterDTOList);
            serviceOutcome.setOutcome(true);
            serviceOutcome.setMessage("Successfully Data Fetched.");
        } else {
            serviceOutcome.setData(new ArrayList<>());
            serviceOutcome.setOutcome(false);
            serviceOutcome.setMessage("Data Not Found.");
        }
        return serviceOutcome;
    }

    @Override
    public ServiceOutcome getIcdMasterInfoByIcdTypeAndIcdCodeOrIcdDescription(String icdType, String icdCodeOrDescription, String status) {
        List<IcdMaster> icdMasterList = icdMasterRepository.findByIcdSearchParameters(icdType, "%"+icdCodeOrDescription+"%", status);
        List<Map<String, Object>> list;
        if (icdMasterList != null && !icdMasterList.isEmpty()) {
            list = icdMasterList.stream().map(p -> {
                Map<String, Object> map = new HashMap<>();
                map.put("icdMasterId", p.getIcdMasterId());
                map.put("icdCode", p.getIcdCode());
                map.put("icdCodeDescription", p.getIcdCodeDesc());
                map.put("icdCodeType", p.getIcdCodeType());
                return map;
            }).collect(Collectors.toList());
        } else list = new ArrayList<>();
        return (new ServiceOutcome(list, list.size() > 0 ? true : false, list.size() > 0 ? "Successfully Data Fetched." : "Data Not Found."));
    }

    @Override
    public ServiceOutcome getAllIcdCodeType(String status) {
        List<String> icdCodeTypeListOfObj = icdMasterRepository.findByStatusIgnoreCase(status);
        List<Map<String, String>> list;
        if (icdCodeTypeListOfObj != null && !icdCodeTypeListOfObj.isEmpty()) {
            list = icdCodeTypeListOfObj.stream().map(p -> {
                Map<String, String> map = new HashMap<>();
                map.put("id", p);
                map.put("title", p);
                return map;
            }).collect(Collectors.toList());
        } else list = new ArrayList<>();

        return (new ServiceOutcome(list, list.size() > 0 ? true : false, list.size() > 0 ? "Successfully Data Fetched." : "Data Not Found."));
    }

    @Override
    public ServiceOutcome getICDMasterById(Long icdMasterId){
        if(icdMasterId != null && !icdMasterId.equals("")){
            IcdMaster icdMaster = icdMasterRepository.findByIcdMasterId(icdMasterId);
            return (new ServiceOutcome(icdMaster, icdMaster.getIcdMasterId()!= null ? true : false, icdMaster.getIcdMasterId()!= null ? "Successfully Data Fetched." : "Data Not Found."));
        }else{
            return (new ServiceOutcome(null, false, "Icd Master Id must be provided."));
        }
    }

    @Override
    public ServiceOutcome getICDMasterByIcdCode(String icdCode) {
        if(icdCode != null && !icdCode.equals("")){
            IcdMaster icdMaster = icdMasterRepository.findByIcdCode(icdCode);
            return (new ServiceOutcome(icdMaster, icdMaster.getIcdMasterId()!= null ? true : false, icdMaster.getIcdMasterId()!= null ? "Successfully Data Fetched." : "Data Not Found."));
        }else{
            return (new ServiceOutcome(null, false, "Icd Code must be provided."));
        }
    }
}
