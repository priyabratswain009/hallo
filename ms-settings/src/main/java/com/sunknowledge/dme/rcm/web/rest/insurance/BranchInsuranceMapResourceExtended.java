package com.sunknowledge.dme.rcm.web.rest.insurance;

import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.insurance.BranchInsuranceMapExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.insurance.BranchInsuranceMapExtendedForUpdateDTO;
import com.sunknowledge.dme.rcm.service.insurance.BranchInsuranceMapServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class BranchInsuranceMapResourceExtended {
    private final Logger log = LoggerFactory.getLogger(InsuranceMasterResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";

    @Autowired
    BranchInsuranceMapServiceExtended branchInsuranceMapServiceExtended;

    @PatchMapping(value = "saveBranchInsuranceMap", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveBranchInsuranceMap(@RequestBody BranchInsuranceMapExtendedDTO branchInsuranceMapExtendedDTO) {
        if (branchInsuranceMapExtendedDTO.getBranchIdList().contains(0L) || branchInsuranceMapExtendedDTO.getBranchIdList().contains(null) ||
            branchInsuranceMapExtendedDTO.getInsuranceId() == 0L || branchInsuranceMapExtendedDTO.getInsuranceId() == null) {
            return new ResponseDTO(Boolean.FALSE, "Ids Not Valid.", new ArrayList<>());
        }
        return branchInsuranceMapServiceExtended.saveBranchInsuranceMap(branchInsuranceMapExtendedDTO);
    }

    @PostMapping(value = "updateBranchInsuranceMap")
    public ResponseDTO updateBranchInsuranceMap(@RequestBody BranchInsuranceMapExtendedForUpdateDTO branchInsuranceMapExtendedForUpdateDTO) {
        if (branchInsuranceMapExtendedForUpdateDTO.getBranchId() == null || branchInsuranceMapExtendedForUpdateDTO.getBranchId() == 0L
            || branchInsuranceMapExtendedForUpdateDTO.getInsuranceId() == null || branchInsuranceMapExtendedForUpdateDTO.getInsuranceId() == 0L) {
            return new ResponseDTO(Boolean.FALSE, "Ids Not Valid.", new ArrayList<>());
        }
        return branchInsuranceMapServiceExtended.updateBranchInsuranceMap(branchInsuranceMapExtendedForUpdateDTO);
    }

    @GetMapping("/getBranchInsuranceMapByBranchInsuranceMapId")
    public ResponseDTO getBranchInsuranceMapByBranchInsuranceMapId(
        @Min(value = 1, message = "Insurance_Id must be greater than or equal to 1")
        @RequestParam("branchInsuranceMapId") Long branchInsuranceMapId) {
        BranchInsuranceMapDTO obj = branchInsuranceMapServiceExtended.getBranchInsuranceMapByBranchInsuranceMapId(branchInsuranceMapId);
        return (obj != null ?
            (new ResponseDTO(true, "Successfully Data Fetched.", List.of(obj))) :
            (new ResponseDTO(false, "Data Not Found.", new ArrayList<>()))
        );
    }

    @GetMapping("/getBranchInsuranceMapByInsuranceId")
    public ResponseDTO getBranchInsuranceMapByInsuranceId(
        @Min(value = 1, message = "Insurance_Id must be greater than or equal to 1")
        @RequestParam("insuranceId") Long insuranceId) {
        List<BranchInsuranceMapDTO> obj = branchInsuranceMapServiceExtended.getBranchInsuranceMapByInsuranceId(insuranceId);
        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "Successfully Data Fetched." : "Data Not Found.", obj));
    }

    @GetMapping("/getBranchInsuranceMapByBranchId")
    public ResponseDTO getBranchInsuranceMapByBranchId(
        @Min(value = 1, message = "Branch_Id must be greater than or equal to 1")
        @RequestParam("branchId") Long branchId) {
        List<BranchInsuranceMapDTO> obj = branchInsuranceMapServiceExtended.getBranchInsuranceMapByBranchId(branchId);
        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "Successfully Data Fetched." : "Data Not Found.", obj));
    }

    @GetMapping("/getBranchInsuranceMapByBranchIdAndInsuranceId")
    public ResponseDTO getBranchInsuranceMapByBranchIdAndInsuranceId(
        @NotNull(message = "Branch_Id must be provided")
        @Min(value = 1, message = "Branch_Id must be greater than or equal to 1")
        @RequestParam("branchId") Long branchId,
        @NotNull(message = "Insurance_Id must be provided")
        @Min(value = 1, message = "Insurance_Id must be greater than or equal to 1")
        @RequestParam("insuranceId") Long insuranceId) {
        List<BranchInsuranceMapDTO> obj = branchInsuranceMapServiceExtended.getBranchInsuranceMapByBranchIdAndInsuranceId(branchId, insuranceId);
        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "Successfully Data Fetched." : "Data Not Found.", obj));
    }

    @GetMapping("/getBranchInsuranceMapByInsuranceIdNo")
    public ResponseDTO getBranchInsuranceMapByInsuranceIdNo(
        @NotBlank(message = "Insurance_Id_No must be provided")
        @RequestParam("insuranceIdNo") String insuranceIdNo) {
        List<BranchInsuranceMapDTO> obj = branchInsuranceMapServiceExtended.getBranchInsuranceMapByInsuranceIdNo(insuranceIdNo);
        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "Successfully Data Fetched." : "Data Not Found.", obj));
    }

    @GetMapping("/getAllBranchInsuranceMapData")
    public ResponseDTO getAllBranchInsuranceMapData() {
        List<BranchInsuranceMapDTO> obj = branchInsuranceMapServiceExtended.getAllBranchInsuranceMapData();
        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "Successfully Data Fetched." : "Data Not Found.", obj));
    }

    @GetMapping("/getBranchInsuranceMapByStatus")
    public ResponseDTO getBranchInsuranceMapByStatus(
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status) {

        List<BranchInsuranceMapDTO> obj = branchInsuranceMapServiceExtended.getBranchInsuranceMapByStatus(status);
        return (new ResponseDTO(obj.size() > 0 ? true : false, obj.size() > 0 ? "Successfully Data Fetched." : "Data Not Found.", obj));
    }

    @PutMapping("/setBranchInsurancemapStatusById/{branchInsuranceMapId}/{status}")
    public ResponseDTO setBranchInsurancemapStatusById(
        @PathVariable("branchInsuranceMapId") Long branchInsuranceMapId,
        @NotBlank(message = "Status must be provided")
        @PathVariable("status") String status) {

        return branchInsuranceMapServiceExtended.setBranchInsurancemapStatusById(branchInsuranceMapId, status);
    }
}
