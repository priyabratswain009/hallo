package com.sunknowledge.dme.rcm.web.rest.insurance;

import com.sunknowledge.dme.rcm.exception.BranchNotFoundException;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.insurance.InsuranceMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.insurance.InsuranceMasterServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api")
public class InsuranceMasterResourceExtended {
    public static String TYPE = "text/csv";
    private final Logger log = LoggerFactory.getLogger(InsuranceMasterResourceExtended.class);
    @Autowired
    InsuranceMasterServiceExtended insuranceMasterServiceExtended;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @PostMapping(value = "/bulkUploadForInsuranceMaster", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO bulkUploadForInsuranceMaster(@RequestPart MultipartFile documentFile) throws IOException {
        try {
            if (!TYPE.equals(documentFile.getContentType())) {
                throw new BranchNotFoundException("File type should be CSV!");
            } else if (documentFile == null || documentFile.getOriginalFilename().equals("")) {
                throw new BranchNotFoundException("You must select the document file for uploading");
            } else {
                return insuranceMasterServiceExtended.bulkUploadForInsuranceMaster(documentFile);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(value = "saveInsuranceMaster", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveInsuranceMaster(@RequestBody InsuranceMasterParameterDTO insuranceMasterParameterDTO) {
        return insuranceMasterServiceExtended.saveInsuranceMaster(insuranceMasterParameterDTO);
    }


    @GetMapping("/getInsuranceMasterByInsuranceIdList")
    public ResponseDTO getInsuranceMasterByInsuranceIdList(
        @NotNull(message = "Insurance_Id(s) must be provided")
        @NotBlank(message = "Insurance_Id(s) must be provided")
        @RequestParam("insuranceIds") String insuranceIds) {
        List<InsuranceMasterDTO> obj = insuranceMasterServiceExtended.getInsuranceMasterByInsuranceIdList(insuranceIds);
        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "" : "Data Not Found.", obj,200));
    }

    @GetMapping("/getInsuranceMasterById")
    public ResponseDTO getInsuranceMasterById(
        @Min(value = 1, message = "Insurance_Id must be greater than or equal to 1")
        @RequestParam("insuranceId") Long insuranceId) {
        InsuranceMasterDTO obj = insuranceMasterServiceExtended.getInsuranceMasterById(insuranceId);
        return (new ResponseDTO(obj!=null? true : false, obj!=null ? "" : "Data Not Found.", obj,200));
    }

    @GetMapping("/getInsuranceMasterByInsuranceName")
    public ResponseDTO getInsuranceMasterByInsuranceName(
        @NotBlank(message = "Insurance_Name must be provided")
        @RequestParam("insuranceName") String insuranceName) {
        List<InsuranceMasterDTO> obj = insuranceName.trim() != "" ?
            insuranceMasterServiceExtended.getInsuranceMasterByInsuranceName(insuranceName.trim()) : new ArrayList<>();
        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "" : "Data Not Found.", obj,200));
    }

    @GetMapping("/getInsuranceMasterByInsuranceIdNo")
    public ResponseDTO getInsuranceMasterByInsuranceIdNo(
        @NotBlank(message = "Insurance_Id_No must be provided")
        @RequestParam("insuranceIdNo") String insuranceIdNo) {
        List<InsuranceMasterDTO> obj = insuranceMasterServiceExtended.getInsuranceMasterByInsuranceIdNo(insuranceIdNo);
        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "" : "Data Not Found.", obj,200));
    }

    @GetMapping("/fetchInsuranceByCmsCrossoverInsuranceIdNo")
    public ServiceOutcome<InsuranceMasterDTO> fetchInsuranceByCmsCrossoverInsuranceIdNo(
        @NotBlank(message = "Cms_Crossover_Insurance_Id_No must be provided")
        @RequestParam("cmsCrossoverInsuranceIdNo") String cmsCrossoverInsuranceIdNo) {
        List<InsuranceMasterDTO> obj = insuranceMasterServiceExtended.fetchInsuranceByCmsCrossoverInsuranceIdNo(cmsCrossoverInsuranceIdNo);
//        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "Successfully Data Fetched." : "Data Not Found.", obj.size() > 0 ? obj.get(0) : null));
        return (new ServiceOutcome<InsuranceMasterDTO>(obj.size() > 0 ? obj.get(0) : null, obj.size() > 0 ? true : false, obj.size() > 0 ? "" : "Data Not Found.",200));
    }

    @GetMapping("/getAllInsuranceMasterData")
    public ResponseDTO getAllInsuranceMasterData() {
        List<InsuranceMasterDTO> obj = insuranceMasterServiceExtended.getAllInsuranceMasterData();
        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "" : "Data Not Found.", obj,200));
    }

    @GetMapping("/getInsuranceMasterByStatus")
    public ResponseDTO getInsuranceMasterByStatus(
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status) {

        List<InsuranceMasterDTO> obj = insuranceMasterServiceExtended.getInsuranceMasterByStatus(status);
        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "" : "Data Not Found.", obj,200));
    }

    @PutMapping("/setInsuranceMasterStatusByUuid")
    public ResponseDTO setInsuranceMasterStatusByUuid(
        @RequestParam("uuid") UUID uuid,
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status) {

        return insuranceMasterServiceExtended.setInsuranceMasterStatus(uuid, status);
    }

    /**
     * This API will return all Insurance_Id as id and Insurance_Name as title for all Active Status.
     *
     * @return ServiceOutcome
     */
    @GetMapping("/getInsuranceByInsuranceNameForDropdown")
    public ServiceOutcome getInsuranceByInsuranceNameForDropdown() {
        return insuranceMasterServiceExtended.getInsuranceByInsuranceNameForDropdown();
    }
}
