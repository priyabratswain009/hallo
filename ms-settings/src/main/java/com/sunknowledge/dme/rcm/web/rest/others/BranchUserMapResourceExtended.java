package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchItemLocationMapExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchItemLocationMapExtendedUpdateDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchUserMapParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.BranchUserMapUpdateDTO;
import com.sunknowledge.dme.rcm.service.others.BranchUserMapServiceExtended;
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
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api")
public class BranchUserMapResourceExtended {
    private final Logger log = LoggerFactory.getLogger(BranchUserMapResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    BranchUserMapServiceExtended branchUserMapServiceExtended;

    @PatchMapping(value = "saveBranchUserMap", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveBranchUserMap(@RequestBody BranchUserMapParameterDTO branchUserMapParameterDTO){
        if(branchUserMapParameterDTO.getUserIdList().contains(null) || branchUserMapParameterDTO.getUserIdList().contains(0L)
            || branchUserMapParameterDTO.getBranchId() == null || branchUserMapParameterDTO.getBranchId() == 0L){
            return new ResponseDTO(Boolean.FALSE,"Ids Not Valid.",new ArrayList<>(),200);
        }
        return branchUserMapServiceExtended.saveBranchUserMap(branchUserMapParameterDTO);
    }

    @PostMapping(value = "updateBranchUserMap")
    public ResponseDTO updateBranchUserMap(@RequestBody BranchUserMapUpdateDTO branchUserMapUpdateDTO){
        if(branchUserMapUpdateDTO.getUserId() == null || branchUserMapUpdateDTO.getUserId() == 0L
            || branchUserMapUpdateDTO.getBranchId() == null || branchUserMapUpdateDTO.getBranchId() == 0L){
            return new ResponseDTO(Boolean.FALSE,"Ids Not Valid.",new ArrayList<>(),200);
        }
        return branchUserMapServiceExtended.updateBranchUserMap(branchUserMapUpdateDTO);
    }

    @GetMapping(value = "/getBranchByUser")
    public ResponseDTO getBranchByUser(){
        Long userId = 1L;  //--------- [Taken from User Login Service] ----------
        return branchUserMapServiceExtended.getBranchByUser(userId);
    }

    @GetMapping(value = "/getUserByBranch")
    public ResponseDTO getUserByBranch(@RequestParam("branchId") Long branchId){
        return branchUserMapServiceExtended.getUserByBranch(branchId);
    }

    @PutMapping(value = "/setBranchUserMapStatusByUuid")
    public ResponseDTO setBranchUserMapStatusByUuid(@RequestParam("uuid") UUID uuid,
                                                  @RequestParam("status") String status){

        return branchUserMapServiceExtended.setBranchUserMapStatusByUuid(uuid,status);
    }

    @GetMapping(value = "/getAllBranchUserMapData")
    public ResponseDTO getAllBranchUserMapData(){
        return branchUserMapServiceExtended.getAllBranchUserMapData();
    }

    @GetMapping(value = "/getBranchUserMapById")
    public ResponseDTO getBranchUserMapById(@RequestParam("id") Long id){
        return branchUserMapServiceExtended.getBranchUserMapById(id);
    }
}
