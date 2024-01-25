package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleUserMapParameterDTO;
import com.sunknowledge.dme.rcm.service.others.RoleUserMapServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RoleUserMapResourceExtended {

    @Autowired
    RoleUserMapServiceExtended roleUserMapServiceExtended;

    @PatchMapping(value = "/saveRoleUserMap",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveRoleUserMap(@Valid @RequestBody RoleUserMapParameterDTO
                                          roleUserMapParameterDTO){

        return roleUserMapServiceExtended.saveRoleUserMap(roleUserMapParameterDTO);
    }

    @PatchMapping(value = "/updateStatusRoleUserMap",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO updateStatusRoleUserMap(@Valid @RequestBody RoleUserMapParameterDTO
                                          roleUserMapParameterDTO, @RequestParam("status") String status){

        return roleUserMapServiceExtended.updateStatusRoleUserMap(roleUserMapParameterDTO,status);
    }

//        operationType should be
//        -userUUID
//        -roleUUID
//        -userName
//        -roleName
//        -userNo
//        -roleNo
    @GetMapping("/getRoleUserMapByNameOrNoOrUUID")
    public ResponseDTO getRoleUserMapByNameOrNoOrUUID(
        @NotBlank(message = "Data should not be blank")
        @RequestParam("data") String data,
        @NotBlank(message = "Operation_Type should not be blank")
        @RequestParam("operationType") String operationType){
        return roleUserMapServiceExtended.getRoleUserDetailsByRoleUUID(data,operationType);
    }

    @PutMapping(value = "/setRoleUserMapStatusByUuid")
    public ResponseDTO setRoleUserMapStatusByUuid(@RequestParam("uuid") UUID uuid,
                                            @RequestParam("status") String status){
        return roleUserMapServiceExtended.setRoleUserMapStatusByUuid(uuid,status);
    }
}
