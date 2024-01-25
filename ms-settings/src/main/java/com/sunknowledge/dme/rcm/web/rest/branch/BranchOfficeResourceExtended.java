package com.sunknowledge.dme.rcm.web.rest.branch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.sunknowledge.dme.rcm.service.dto.branch.BranchOfficeExtendedDTO;
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

import com.sunknowledge.dme.rcm.exception.BranchNotFoundException;
import com.sunknowledge.dme.rcm.service.branch.BranchOfficeServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchOfficeDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchOfficeParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;


@Validated
@RestController
@RequestMapping("/api")
public class BranchOfficeResourceExtended {
    private final Logger log = LoggerFactory.getLogger(BranchOfficeResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";

    @Autowired
    BranchOfficeServiceExtended branchOfficeServiceExtended;

    @PostMapping(value = "/bulkUploadForBranchOffice", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO bulkUploadForBranchOffice(@RequestPart MultipartFile documentFile) throws IOException {
        try {
            if (!TYPE.equals(documentFile.getContentType())) {
                throw new BranchNotFoundException("File type should be CSV!");
            }
            else if(documentFile == null || documentFile.getOriginalFilename().equals("")){
                throw new BranchNotFoundException("You must select the document file for uploading");
            }
            else{
                return branchOfficeServiceExtended.bulkUploadForBranchOffice(documentFile);
            }
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(value = "saveBranchOffice", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveBranchOffice(@RequestBody BranchOfficeParameterDTO branchOfficeParameterDTO){
        return branchOfficeServiceExtended.saveBranchOffice(branchOfficeParameterDTO);
    }

    @GetMapping("/getBranchOfficeById")
    public ResponseDTO getBranchOfficeById(
        @Min(value=1, message="Branch_Id must be greater than or equal to 1")
        @RequestParam("branchId") Long branchId){
        BranchOfficeDTO obj = branchOfficeServiceExtended.getBranchOfficeById(branchId);
        return (new ResponseDTO(obj!=null?true:false, obj!=null? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getActiveBranchOfficeById")
    public ResponseDTO getActiveBranchOfficeById(
        @Min(value=1, message="Branch_Id must be greater than or equal to 1")
        @RequestParam("branchId") Long branchId){
        BranchOfficeDTO obj = branchOfficeServiceExtended.getActiveBranchOfficeById(branchId);
        return (new ResponseDTO(obj!=null?true:false, obj!=null? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getBranchOfficeByBranchName")
    public ResponseDTO getBranchOfficeByBranchName(
        @NotBlank(message = "Branch_Name must be provided")
        @RequestParam("branchName") String branchName){
        List<BranchOfficeDTO> obj = branchName.trim()!=""?
            branchOfficeServiceExtended.getBranchOfficeByBranchName(branchName.trim()):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getBranchOfficeByBranchNo")
    public ResponseDTO getBranchOfficeByBranchNo(
        @NotBlank(message = "Branch_No must be provided")
        @RequestParam("branchNo") String branchNo){

        List<BranchOfficeDTO> obj = branchOfficeServiceExtended.getBranchOfficeByBranchNo(branchNo);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getBranchOfficeByNpi")
    public ResponseDTO getBranchOfficeByNpi(
        @NotBlank(message = "NPI must be provided")
        @RequestParam("npi") String npi){

        List<BranchOfficeDTO> obj = branchOfficeServiceExtended.getBranchOfficeByNpi(npi);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getAllBranchOfficeData")
    public ResponseDTO getAllBranchOfficeData(){
        List<BranchOfficeExtendedDTO> obj = branchOfficeServiceExtended.getAllBranchOfficeData();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getBranchOfficeByStatus")
    public ResponseDTO getBranchOfficeByStatus(
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status){
        List<BranchOfficeDTO> obj = branchOfficeServiceExtended.getBranchOfficeByStatus(status);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @PutMapping("/setBranchOfficeStatusByUuid")
    public ResponseDTO setBranchOfficeStatusByUuid(
        @RequestParam("uuid") UUID uuid,
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status){

        return branchOfficeServiceExtended.setBranchOfficeStatusByUuid(uuid,status);
    }

    @GetMapping("/getBranchOfficeForABNById")
    public BranchOfficeDTO getBranchOfficeForABNById(
        @Min(value=1, message="Branch_Id must be greater than or equal to 1")
        @RequestParam("branchId") Long branchId){
        return branchOfficeServiceExtended.getBranchOfficeForABNById(branchId).getData();
    }

}
