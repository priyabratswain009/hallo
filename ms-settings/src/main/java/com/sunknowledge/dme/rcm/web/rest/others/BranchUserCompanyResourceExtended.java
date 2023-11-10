package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.branch.BranchOfficeServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api")
public class BranchUserCompanyResourceExtended {
    private final Logger log = LoggerFactory.getLogger(BranchUserCompanyResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    BranchOfficeServiceExtended branchOfficeServiceExtended;

    //===== String opType = "userUUID";
    //===== String opType = "userName";
    @GetMapping("/getBranchDetailsByUserUUIDorUserName")
    public ResponseDTO getBranchDetailsByUserUUIDorUserName(
        @NotNull(message = "Parameter_Value must be provided.")
        @NotBlank(message = "Parameter_Value must be provided.")
        @RequestParam("parameterValue") String parameterValue,
        @NotNull(message = "Operation_Type must be provided.")
        @NotBlank(message = "Operation_Type must be provided.")
        @RequestParam("opType") String opType){
        List<Map> obj = branchOfficeServiceExtended.getBranchDetailsByUserUUIDorUserName(parameterValue.trim(),opType);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));

    }

    //===== String opType = "branchUUID";
    //===== String opType = "branchName";
    @GetMapping("/getUserDetailsByBranchUUIDorBranchName")
    public ResponseDTO getUserDetailsByBranchUUIDorBranchName(
        @NotNull(message = "Parameter_Value must be provided.")
        @NotBlank(message = "Parameter_Value must be provided.")
        @RequestParam("parameterValue") String parameterValue,
        @NotNull(message = "Operation_Type must be provided.")
        @NotBlank(message = "Operation_Type must be provided.")
        @RequestParam("opType") String opType){
        List<Map> obj = branchOfficeServiceExtended.getUserDetailsByBranchUUIDorBranchName(parameterValue.trim(),opType);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));

    }

    //===== String opType = "branchUUID";
    //===== String opType = "branchName";
    @GetMapping("/getLocationDetailsByBranchUUIDorBranchName")
    public ResponseDTO getLocationDetailsByBranchUUIDorBranchName(
        @NotNull(message = "Parameter_Value must be provided.")
        @NotBlank(message = "Parameter_Value must be provided.")
        @RequestParam("parameterValue") String parameterValue,
        @NotNull(message = "Operation_Type must be provided.")
        @NotBlank(message = "Operation_Type must be provided.")
        @RequestParam("opType") String opType){
        List<Map> obj = branchOfficeServiceExtended.getLocationDetailsByBranchUUIDorBranchName(parameterValue.trim(),opType);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));

    }

    //===== String opType = "branchUUID";
    //===== String opType = "branchName";
    @GetMapping("/getInsuranceDetailsByBranchUUIDorBranchName")
    public ResponseDTO getInsuranceDetailsByBranchUUIDorBranchName(
        @NotNull(message = "Parameter_Value must be provided.")
        @NotBlank(message = "Parameter_Value must be provided.")
        @RequestParam("parameterValue") String parameterValue,
        @NotNull(message = "Operation_Type must be provided.")
        @NotBlank(message = "Operation_Type must be provided.")
        @RequestParam("opType") String opType){
        List<Map> obj = branchOfficeServiceExtended.getInsuranceDetailsByBranchUUIDorBranchName(parameterValue.trim(),opType);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));

    }

    //===== String opType = "branchUUID";
    //===== String opType = "branchName";
    @GetMapping("/getCompanyDetailsByBranchUUIDorBranchName")
    public ResponseDTO getCompanyDetailsByBranchUUIDorBranchName(
        @NotNull(message = "Parameter_Value must be provided.")
        @NotBlank(message = "Parameter_Value must be provided.")
        @RequestParam("parameterValue") String parameterValue,
        @NotNull(message = "Operation_Type must be provided.")
        @NotBlank(message = "Operation_Type must be provided.")
        @RequestParam("opType") String opType){
        List<Map> obj = branchOfficeServiceExtended.getCompanyDetailsByBranchUUIDorBranchName(parameterValue.trim(),opType);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "Successfully Data Fetched.": "Data Not Found.", obj));

    }
}
