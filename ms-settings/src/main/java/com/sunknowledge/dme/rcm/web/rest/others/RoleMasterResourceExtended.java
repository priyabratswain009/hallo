package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.others.RoleMasterServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RoleMasterResourceExtended {

    @Autowired
    RoleMasterServiceExtended roleMasterServiceExtended;

    @PatchMapping(value = "/saveRoleMaster",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveRoleMaster(@Valid @RequestBody RoleMasterParameterDTO
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
}
