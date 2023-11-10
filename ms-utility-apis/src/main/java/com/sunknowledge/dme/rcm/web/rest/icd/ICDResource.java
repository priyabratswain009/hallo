package com.sunknowledge.dme.rcm.web.rest.icd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.dto.icd.ICDResultOutcome;
import com.sunknowledge.dme.rcm.dto.icd.RequestICDInput;
import com.sunknowledge.dme.rcm.service.dto.IcdMasterDTO;
import com.sunknowledge.dme.rcm.service.icd.ICDService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ICDResource {
    @Autowired
    ICDService icdService;

    @ApiOperation(value = "Get ICD10 Information")
    @GetMapping(path = "/getICD10Information", produces = "application/json")
    @ResponseBody
    public String getICD10Information(RequestICDInput requestICDInput) {
        log.info("=======================POST for ICD 10 Information==========================");
        String resultOutcomeJson = "";
        try {
            //https://icd.who.int/browse10/2019/en#/B15-B19
            String token = icdService.getToken();
            //String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE2NTc3MTA3NDIsImV4cCI6MTY1NzcxNDM0MiwiaXNzIjoiaHR0cHM6Ly9pY2RhY2Nlc3NtYW5hZ2VtZW50Lndoby5pbnQiLCJhdWQiOlsiaHR0cHM6Ly9pY2RhY2Nlc3NtYW5hZ2VtZW50Lndoby5pbnQvcmVzb3VyY2VzIiwiaWNkYXBpIl0sImNsaWVudF9pZCI6ImJkMzhlZTk3LWQxNGUtNGM0NC1iM2QwLTk3MTcwOWMzMGNmMl9hZTI4MTRiNy0xMjQ1LTRkMzMtOTE2Yy1kYjdmYmNiNzNmMTIiLCJzY29wZSI6WyJpY2RhcGlfYWNjZXNzIl19.VZULeJbQY4ZY-F8fBBiSqYiLLcjDAWdhnr7Q7ygK2taFzoigVkmsVW7tnQP4muwmmbkuvejfLJMPln-JVAguCwf1akXGt1_Fww9rLtrcKgpAEK1esiyvws_rBBUe5pBB8mHSV3up89LftBWgwFF_0r8l7TeZKmGB1xZDNVKBC4nq1MG262ZV_pVsaaHqKXLXc-Y9vkHUnvYJskcAv5PKD0aeO0e3DeSK9DRSitCqEHgTS7jFVsmHdFzNS_8nm9lXw6DVLIvlFWWoMjxZcp1zvKrQb0w3yQF23bubss-8ta-HurGLcohyurG5nZrg6IzH6HnctVF3w7J2wX5Kg83asg";
            System.out.println("CONTROLLER TOKEN:" + token);
            ServiceOutcome<ICDResultOutcome> serviceOutcome = icdService.getICD10Information(requestICDInput, token);
            System.out.println("JSON RESULT:" + serviceOutcome);
            if (serviceOutcome != null) {
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                resultOutcomeJson = ow.writeValueAsString(serviceOutcome);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultOutcomeJson;
    }

//{
//    "releaseId": "2010",
//    "code": "A00.1"
//    }


    @ApiOperation(value = "Get ICD Information")
    @GetMapping(path = "/getICDInformation", produces = "application/json")
    @ResponseBody
    public ServiceOutcome<IcdMasterDTO> getICDInformation(String requestICDInput, String icdType) {
        String resultOutcomeJson = "";
        ServiceOutcome<IcdMasterDTO> serviceOutcome = new ServiceOutcome<>();
        try {
            IcdMasterDTO icdMasterDTO = icdService.getICDDetailsOnCode(requestICDInput);
            if (icdMasterDTO != null) {
                serviceOutcome.setData(icdMasterDTO);
                serviceOutcome.setOutcome(true);
                serviceOutcome.setMessage("ICD data fetched from Internal!");
            } else {
                serviceOutcome = icdService.getICDInformation(requestICDInput, icdType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceOutcome;
    }

    @ApiOperation(value = "Get ICD Information By Icd Code Or Icd Description")
    @GetMapping(path = "/getIcdMasterInfoByIcdCodeOrIcdDescription", produces = "application/json")
    @ResponseBody
    public ServiceOutcome<List<IcdMasterDTO>> getIcdMasterInfoByIcdCodeOrIcdDescription(@RequestParam(required = false) String icdCode, @RequestParam(required = false) String icdDescription) {
        ServiceOutcome<List<IcdMasterDTO>> serviceOutcome = new ServiceOutcome<>();

        if ((icdCode == null || icdCode.equalsIgnoreCase("")) && (icdDescription == null || icdDescription.equalsIgnoreCase(""))) {
            return (new ServiceOutcome(new ArrayList<>(), false, "Icd Code or Icd Description anyone must be provided."));
        }

        try {
            serviceOutcome = icdService.getIcdMasterInfoByIcdCodeOrIcdDescription(icdCode, icdDescription, "active");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceOutcome;
    }

    /**
     * This API will return All ID as id and Icd_Code_Desc as title for the particular Icd_Code_Type and Active Status.
     *
     * @param icdType(Icd_Code_Type) and Active Status
     * @return ServiceOutCome
     */
    @ApiOperation(value = "Get ICD Information By Icd Type")
    @GetMapping(path = "/getIcdMasterInfoByIcdTypeAndIcdCodeOrIcdDescription", produces = "application/json")
    @ResponseBody
    public ServiceOutcome getIcdMasterInfoByIcdTypeAndIcdCodeOrIcdDescription(@RequestParam String icdType,
                                                                              @RequestParam(required = false) String icdCodeOrDescription) {

        if(icdType==null && icdType.equals("")){
            return (new ServiceOutcome(null, false, "icdType must be provided."));
        }

        if(icdCodeOrDescription==null || icdCodeOrDescription.equals("")){
            return (new ServiceOutcome(null, false, "icdCode or icdDescription must be provided."));
        }

        return icdService.getIcdMasterInfoByIcdTypeAndIcdCodeOrIcdDescription(icdType.trim(), icdCodeOrDescription.trim(), "active");
    }

    /**
     * This API will return all Icd_Code_Type value as id and title for all Active status.
     *
     * @return ServiceOutCome
     */
    @ApiOperation(value = "Get All ICD Code Types")
    @GetMapping(path = "/getAllIcdCodeType", produces = "application/json")
    @ResponseBody
    public ServiceOutcome getAllIcdCodeType() {
        return icdService.getAllIcdCodeType("active");
    }

    @ApiOperation(value = "Get ICD Information")
    @GetMapping(path = "/getICDInformationByICDs", produces = "application/json")
    @ResponseBody
    public ServiceOutcome<List<IcdMasterDTO>> getICDInformationByICDs(String requestICDInputs, String icdType) {
        String resultOutcomeJson = "";
        ServiceOutcome<List<IcdMasterDTO>> serviceOutcome = new ServiceOutcome<>();
        List<IcdMasterDTO> icdMasterDTOS = new ArrayList<>();
        if (requestICDInputs != null && requestICDInputs.trim() != "") {
            try {
                String[] _ICDInputs = requestICDInputs.split(",");
                for (String icd : _ICDInputs) {
                    IcdMasterDTO icdMasterDTO = icdService.getICDDetailsOnCode(icd);
                    if (icdMasterDTO != null) {
                        icdMasterDTOS.add(icdMasterDTO);
                    } else {
                        ServiceOutcome outcome = icdService.getICDInformation(icd, icdType);
                        if (outcome.getOutcome()) {
                            icdMasterDTOS.add(((IcdMasterDTO) outcome.getData()));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        serviceOutcome.setData(icdMasterDTOS);
        if (icdMasterDTOS.size() > 0) {
            serviceOutcome.setOutcome(true);
            serviceOutcome.setMessage("ICD data fetched successfully!");
        } else {
            serviceOutcome.setOutcome(false);
            serviceOutcome.setMessage("Invalid ICD Code");
        }
        return serviceOutcome;
    }

    @ApiOperation(value = "Get ICD Information By Icd Master Id")
    @GetMapping(path = "/getICDMasterById", produces = "application/json")
    @ResponseBody
    public ServiceOutcome getICDMasterById(@RequestParam Long icdMasterId) {
        return icdService.getICDMasterById(icdMasterId);
    }

    @ApiOperation(value = "Get ICD Information By Icd Code")
    @GetMapping(path = "/getICDMasterByIcdCode", produces = "application/json")
    @ResponseBody
    public ServiceOutcome getICDMasterByIcdCode(@RequestParam String icdCode) {
        return icdService.getICDMasterByIcdCode(icdCode);
    }
}
