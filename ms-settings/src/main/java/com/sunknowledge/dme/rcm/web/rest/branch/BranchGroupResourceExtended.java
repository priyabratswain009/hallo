package com.sunknowledge.dme.rcm.web.rest.branch;

import com.sunknowledge.dme.rcm.exception.BranchNotFoundException;
import com.sunknowledge.dme.rcm.service.branch.BranchGroupServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchGroupExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.branch.BranchGroupParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Validated
@RestController
@RequestMapping("/api")
public class BranchGroupResourceExtended {
    private final Logger log = LoggerFactory.getLogger(BranchGroupResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";

    @Autowired
    BranchGroupServiceExtended branchGroupServiceExtended;

    @PostMapping(value = "/bulkUploadForBranchGroup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO bulkUploadForBranchGroup(@RequestPart MultipartFile documentFile) throws IOException {
        try {
            if (!TYPE.equals(documentFile.getContentType())) {
                throw new BranchNotFoundException("File type should be CSV!");
            }
            else if(documentFile == null || documentFile.getOriginalFilename().equals("")){
                throw new BranchNotFoundException("You must select the document file for uploading");
            }
            else{
                return branchGroupServiceExtended.bulkUploadForBranchGroup(documentFile);
            }
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(value = "saveBranchGroup", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveBranchGroup(@RequestBody BranchGroupParameterDTO branchGroupParameterDTO){
        return branchGroupServiceExtended.saveBranchGroup(branchGroupParameterDTO);
    }

    @GetMapping("/getBranchGroupById")
    public ResponseDTO getBranchGroupById(
        @Min(value=1, message="Branch_Group_Id must be greater than or equal to 1")
        @RequestParam("branchGroupId") Long branchGroupId){
        BranchGroupDTO obj = branchGroupServiceExtended.getBranchGroupById(branchGroupId);
        return (new ResponseDTO(obj!=null?true:false, obj!=null? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getActiveBranchGroupById")
    public ResponseDTO getActiveBranchGroupById(
        @Min(value=1, message="Branch_Group_Id must be greater than or equal to 1")
        @RequestParam("branchGroupId") Long branchGroupId){
        List<BranchGroupDTO> obj = branchGroupServiceExtended.getActiveBranchGroupById(branchGroupId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getBranchGroupByBranchGroupName")
    public ResponseDTO getBranchGroupByBranchGroupName(
        @NotBlank(message = "Branch_Name must be provided")
        @RequestParam("branchGroupName") String branchGroupName){
        List<BranchGroupDTO> obj = branchGroupName.trim()!=""?
            branchGroupServiceExtended.getBranchGroupByBranchGroupName(branchGroupName):new ArrayList<>();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getBranchGroupByCompanyId")
    public ResponseDTO getBranchGroupByCompanyId(
        @NotBlank(message = "Company_Id must be provided")
        @RequestParam("companyId") Long companyId){
        List<BranchGroupDTO> obj = branchGroupServiceExtended.getBranchGroupByCompanyId(companyId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getAllBranchGroupData")
    public ResponseDTO getAllBranchGroupData(){
        List<BranchGroupExtendedDTO> obj = branchGroupServiceExtended.getAllBranchGroupData();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getBranchGroupByStatus")
    public ResponseDTO getBranchGroupByStatus(
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status){

        List<BranchGroupExtendedDTO> obj = branchGroupServiceExtended.getBranchGroupByStatus(status);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @PutMapping("/setBranchGroupStatusByUUID")
    public ResponseDTO setBranchGroupStatusByUUID(
        @RequestParam("uuid") UUID uuid,
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status){

        return branchGroupServiceExtended.setBranchGroupStatusByUUID(uuid,status);
    }

    @GetMapping("/getBranchGroupForDropdown")
    public ServiceOutcome getBranchGroupForDropdown(){
        List<Map<String, Object>> list =  branchGroupServiceExtended.getBranchGroupForDropdown();
        return (new ServiceOutcome(list, list.size() > 0 ? true : false, list.size() > 0 ? "" : "Data Not Found.",200));
    }
}
