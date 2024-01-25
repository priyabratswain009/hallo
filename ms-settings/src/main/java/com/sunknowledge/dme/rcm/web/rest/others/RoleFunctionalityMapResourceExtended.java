package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleFunctionalityMapParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleFunctionalityMapUpdateParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleUserMapParameterDTO;
import com.sunknowledge.dme.rcm.service.others.RoleFunctionalityMapServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RoleFunctionalityMapResourceExtended {

    @Autowired
    RoleFunctionalityMapServiceExtended roleFunctionalityMapServiceExtended;

    @PatchMapping(value = "/saveRoleFunctionalityMap",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveRoleFunctionalityMap(@Valid @RequestBody RoleFunctionalityMapParameterDTO
                                           roleFunctionalityMapParameterDTO){

        return roleFunctionalityMapServiceExtended.saveRoleFunctionalityMap(roleFunctionalityMapParameterDTO);
    }

    @PatchMapping(value = "/updateStatusRoleFunctionalityMap",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO updateStatusRoleFunctionalityMap(@Valid @RequestBody RoleFunctionalityMapUpdateParameterDTO
                                                   roleUserMapParameterDTO){

        return roleFunctionalityMapServiceExtended.updateStatusRoleFunctionalityMap(roleUserMapParameterDTO);
    }

    //        operationType should be
    //        -functionalityUUID
    //        -roleUUID
    //        -functionalityName
    //        -roleName
    //        -functionalityNo
    //        -roleNo
    @GetMapping("/getRoleFunctionalityMapByNameOrNoOrUUID")
    public ResponseDTO getRoleFunctionalityMapByNameOrNoOrUUID(
        @NotBlank(message = "Data should not be blank")
        @RequestParam("data") String data,
        @NotBlank(message = "Operation_Type should not be blank")
        @RequestParam("operationType") String operationType){
        return roleFunctionalityMapServiceExtended.getRoleFunctionalityMapByNameOrNoOrUUID(data,operationType);
    }

    @PutMapping(value = "/setRoleFunctionalityMapStatusByUuid")
    public ResponseDTO setRoleFunctionalityMapStatusByUuid(@RequestParam("uuid") UUID uuid,
                                            @RequestParam("status") String status){
        return roleFunctionalityMapServiceExtended.setRoleFunctionalityMapStatusByUuid(uuid,status);
    }
}
