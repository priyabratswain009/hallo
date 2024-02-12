package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.others.FunctionalityMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.others.FunctionalityMasterServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FunctionalityMasterResourceExtended {

    @Autowired
    FunctionalityMasterServiceExtended functionalityMasterServiceExtended;

    @PatchMapping(value = "/saveFunctionalityMaster",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveFunctionalityMaster(@Valid @RequestBody FunctionalityMasterParameterDTO
                                          functionalityMasterParameterDTO){

        return functionalityMasterServiceExtended.saveFunctionalityMaster(functionalityMasterParameterDTO);
    }

    //    operationType should be
    //    -functionalityName
    //    -functionalityNo
    //    -functionalityUUID
    @GetMapping("/getFunctionalityMasterByNameOrNoOrUUID")
    public ResponseDTO getFunctionalityMasterByNameOrNoOrUUID(
        @NotBlank(message = "Data should not be blank")
        @RequestParam("data") String data,
        @NotBlank(message = "Operation_Type should not be blank")
        @RequestParam("operationType") String operationType){
        return functionalityMasterServiceExtended.getFunctionalityMasterByNameOrNoOrUUID(data,operationType);
    }

    @PutMapping(value = "/setFunctionalityMasterStatusByUuid")
    public ResponseDTO setFunctionalityMasterStatusByUuid(@RequestParam("uuid") UUID uuid,
                                                          @RequestParam("status") String status){
        return functionalityMasterServiceExtended.setFunctionalityMasterStatusByUuid(uuid,status);
    }

    @GetMapping(value = "/getAllFunctionalityMasterData")
    public ResponseDTO getAllFunctionalityMasterData(){
        return functionalityMasterServiceExtended.getAllFunctionalityMasterData();
    }

    @GetMapping(value = "/getFunctionalityMasterForDropdown")
    public ServiceOutcome getFunctionalityMasterForDropdown(){
        List<Map<String, Object>> list =  functionalityMasterServiceExtended.getFunctionalityMasterForDropdown();
        return (new ServiceOutcome(list, list.size() > 0 ? true : false, list.size() > 0 ? "Successfully Data Fetched." : "Data Not Found.",200));
    }


}
