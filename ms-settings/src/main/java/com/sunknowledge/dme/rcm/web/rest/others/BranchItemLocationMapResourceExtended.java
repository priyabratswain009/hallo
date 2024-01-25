package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchItemLocationMapExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchItemLocationMapExtendedUpdateDTO;
import com.sunknowledge.dme.rcm.service.others.BranchItemLocationMapServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api")
public class BranchItemLocationMapResourceExtended {
    private final Logger log = LoggerFactory.getLogger(BranchItemLocationMapResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";

    @Autowired
    BranchItemLocationMapServiceExtended branchItemLocationMapServiceExtended;

    @PatchMapping(value = "saveBranchItemLocationMap", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveBranchItemLocationMap(@RequestBody BranchItemLocationMapExtendedDTO branchItemLocationMapExtendedDTO){
        if(branchItemLocationMapExtendedDTO.getBranchIdList().contains(null) || branchItemLocationMapExtendedDTO.getBranchIdList().contains(0L)
            || branchItemLocationMapExtendedDTO.getItemLocationId() == null || branchItemLocationMapExtendedDTO.getItemLocationId() == 0L){
            return new ResponseDTO(Boolean.FALSE,"Ids Not Valid.",new ArrayList<>(),200);
        }
        return branchItemLocationMapServiceExtended.saveBranchItemLocationMap(branchItemLocationMapExtendedDTO);
    }

    @PostMapping(value = "updateBranchItemLocationMap")
    public ResponseDTO updateBranchItemLocationMap(@RequestBody BranchItemLocationMapExtendedUpdateDTO branchItemLocationMapExtendedUpdateDTO){
        if(branchItemLocationMapExtendedUpdateDTO.getBranchId() == null || branchItemLocationMapExtendedUpdateDTO.getBranchId() == 0L
            || branchItemLocationMapExtendedUpdateDTO.getItemLocationId() == null || branchItemLocationMapExtendedUpdateDTO.getItemLocationId() == 0L){
            return new ResponseDTO(Boolean.FALSE,"Ids Not Valid.",new ArrayList<>(),200);
        }
        return branchItemLocationMapServiceExtended.updateBranchItemLocationMap(branchItemLocationMapExtendedUpdateDTO);
    }

    @GetMapping("/getBranchItemLocationMapByItemLocationId")
    public ResponseDTO getBranchItemLocationMapByItemLocationId(
        @Min(value=1, message="ItemLocation_Id must be greater than or equal to 1")
        @RequestParam("itemLocationId") Long itemLocationId){
        List<BranchItemLocationMapDTO> obj = branchItemLocationMapServiceExtended.getBranchItemLocationMapByItemLocationId(itemLocationId);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getBranchItemLocationMapByBranchIdORItemLocationIdORBoth/{branchId}/{itemLocationId}")
    public ResponseDTO getBranchItemLocationMapByBranchIdORItemLocationIdORBoth(
        @PathVariable("branchId") Long branchId,
        @PathVariable("itemLocationId") Long itemLocationId){
        List<BranchItemLocationMapDTO> obj = new ArrayList<>();
        BranchItemLocationMapDTO branchItemLocationMapDTO = new BranchItemLocationMapDTO();
        if(branchId!=null && branchId>0 && itemLocationId!=null && itemLocationId>0){
            branchItemLocationMapDTO = branchItemLocationMapServiceExtended.getBranchItemLocationMapByItemLocationIdAndBranchId(itemLocationId,branchId);
            return (new ResponseDTO(branchItemLocationMapDTO!=null?true:false,
                branchItemLocationMapDTO!=null? "": "Data Not Found.", branchItemLocationMapDTO,200));
        }
        else if(branchId!=null && branchId>0){
            obj = branchItemLocationMapServiceExtended.getBranchItemLocationMapByBranchId(branchId);
        }else if(itemLocationId!=null && itemLocationId>0){
            obj = branchItemLocationMapServiceExtended.getBranchItemLocationMapByItemLocationId(itemLocationId);
        }else{
            obj = branchItemLocationMapServiceExtended.getAllBranchItemLocationMapData();
        }
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getAllBranchItemLocationMapData")
    public ResponseDTO getAllBranchItemLocationMapData(){
        List<BranchItemLocationMapDTO> obj = branchItemLocationMapServiceExtended.getAllBranchItemLocationMapData();
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @GetMapping("/getBranchItemLocationMapByStatus")
    public ResponseDTO getBranchItemLocationMapByStatus(
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status){

        List<BranchItemLocationMapDTO> obj = branchItemLocationMapServiceExtended.getBranchItemLocationMapByStatus(status);
        return (new ResponseDTO(obj.size()>0?true:false, obj.size()>0? "": "Data Not Found.", obj,200));
    }

    @PostMapping("/deactiveBranchItemLocationMapByItemLocationIdAndBranchId")
    public ResponseDTO deactiveBranchItemLocationMapByItemLocationIdAndBranchId(Long itemLocationId, Long branchId){
        return branchItemLocationMapServiceExtended.deactiveBranchItemLocationMapByItemLocationIdAndBranchId(itemLocationId, branchId);
    }

    @PutMapping("/setBranchItemLocationMapByUuid")
    public ResponseDTO setBranchItemLocationMapByUuid(
        @RequestParam("uuid") UUID uuid,
        @NotBlank(message = "Status must be provided")
        @RequestParam("status") String status){

        return branchItemLocationMapServiceExtended.setBranchItemLocationMapByUuid(uuid,status);
    }
}
