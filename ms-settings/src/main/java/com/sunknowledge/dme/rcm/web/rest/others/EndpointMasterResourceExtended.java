package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.others.EndpointMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.FunctionalityMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.others.EndpointMasterServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class EndpointMasterResourceExtended {

    @Autowired
    EndpointMasterServiceExtended endpointMasterServiceExtended;

    @PatchMapping(value = "/saveUpdateEndpointMaster",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveUpdateEndpointMaster(@Valid @RequestBody EndpointMasterParameterDTO
                                                   endpointMasterParameterDTO){

        return endpointMasterServiceExtended.saveUpdateEndpointMaster(endpointMasterParameterDTO);
    }

    //    operationType should be
    //    -endpointName
    //    -endpointGroup
    //    -endpointUUID
    @GetMapping("/getEndpointMasterByNameOrGroupOrUUID")
    public ResponseDTO getEndpointMasterByNameOrGroupOrUUID(
        @NotBlank(message = "Data should not be blank")
        @RequestParam("data") String data,
        @NotBlank(message = "Operation_Type should not be blank")
        @RequestParam("operationType") String operationType){
        return endpointMasterServiceExtended.getEndpointMasterByNameOrGroupOrUUID(data,operationType);
    }

    @PutMapping(value = "/setEndpointMasterStatusByUuid")
    public ResponseDTO setEndpointMasterStatusByUuid(@RequestParam("uuid") UUID uuid,
                                            @RequestParam("status") String status){
        return endpointMasterServiceExtended.setEndpointMasterStatusByUuid(uuid,status);
    }

    @GetMapping(value = "/getAllEndpointMasterData")
    public ResponseDTO getAllEndpointMasterData(){
        return endpointMasterServiceExtended.getAllEndpointMasterData();
    }

    @GetMapping(value = "/getEndpointMasterForDropdown")
    public ServiceOutcome getEndpointMasterForDropdown(){
        List<Map<String, Object>> list =  endpointMasterServiceExtended.getEndpointMasterForDropdown();
        return (new ServiceOutcome(list, list.size() > 0 ? true : false, list.size() > 0 ? "Successfully Data Fetched." : "Data Not Found.",200));
    }
}
