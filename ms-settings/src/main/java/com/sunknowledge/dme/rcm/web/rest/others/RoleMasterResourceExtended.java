package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.RoleMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.others.RoleMasterServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RoleMasterResourceExtended {

    @Autowired
    RoleMasterServiceExtended roleMasterServiceExtended;

    @PatchMapping(value = "/saveRoleMaster",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO
    saveRoleMaster(@Valid @RequestBody RoleMasterParameterDTO
                                                 roleMasterParameterDTO){

        return roleMasterServiceExtended.saveRoleMaster(roleMasterParameterDTO);
    }

    //    operationType should be
    //    -roleName
    //    -roleNo
    //    -roleUUID
    @GetMapping("/getRoleMasterByNameOrNoOrUUID")
    public ResponseDTO getRoleMasterByNameOrNoOrUUID(
        @NotBlank(message = "Data should not be blank")
        @RequestParam("data") String data,
        @NotBlank(message = "Operation_Type should not be blank")
        @RequestParam("operationType") String operationType){
        return roleMasterServiceExtended.getRoleMasterByNameOrNoOrUUID(data,operationType);
    }

    @PutMapping(value = "/setRoleMasterStatusByUuid")
    public ResponseDTO setRoleMasterStatusByUuid(@RequestParam("uuid") UUID uuid,
                                                    @RequestParam("status") String status){
        return roleMasterServiceExtended.setRoleMasterStatusByUuid(uuid,status);
    }

    @GetMapping("/getAllRoleMasterDataForDropdown")
    public ResponseDTO getAllRoleMasterDataForDropdown(){
        List<Map<String, Object>> roleMasterExtendedDTOList= roleMasterServiceExtended.getAllRoleMasterDataForDropdown();
        return new ResponseDTO(roleMasterExtendedDTOList.size()>0?true:false,
            roleMasterExtendedDTOList.size()>0?"":"Data Not Found",
            roleMasterExtendedDTOList.size()>0?roleMasterExtendedDTOList:null,200);
    }
    @GetMapping("/getAllRoleMasterData")
    public ResponseDTO getAllRoleMasterData(){
        List<RoleMasterDTO> roleMasterDTOS = roleMasterServiceExtended.getAllRoleMasterData();
        return new ResponseDTO(roleMasterDTOS.size()>0?true:false,
            roleMasterDTOS.size()>0?"":"Data Not Found",
            roleMasterDTOS.size()>0?roleMasterDTOS:null,200);
    }
    @PutMapping(value = "/setInactiveRoleRelatedInfo")
    public ResponseDTO setInactiveRoleRelatedInfo(@RequestParam("id") long id,
                                               @RequestParam("status") String status){
        return roleMasterServiceExtended.setInactiveRoleRelatedInfo(id,status);
    }
}
