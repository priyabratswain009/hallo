package com.sunknowledge.dme.rcm.web.rest.others;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.FunctionalityEndpointMapParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.FunctionalityEndpointMapUpdateParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.RoleFunctionalityMapParameterDTO;
import com.sunknowledge.dme.rcm.service.others.FunctionalityEndpointMappingServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api")
public class FunctionalityEndpointMappingResourceExtended {

    @Autowired
    FunctionalityEndpointMappingServiceExtended functionalityEndpointMappingServiceExtended;

    @PatchMapping(value = "/saveFunctionalityEndpointMap",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveFunctionalityEndpointMap(@Valid @RequestBody FunctionalityEndpointMapParameterDTO
                                                    functionalityEndpointMapParameterDTO){

        return functionalityEndpointMappingServiceExtended.saveFunctionalityEndpointMap(functionalityEndpointMapParameterDTO);
    }

    @PatchMapping(value = "/updateFunctionalityEndpointMap",consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO updateFunctionalityEndpointMap(@Valid @RequestBody FunctionalityEndpointMapUpdateParameterDTO
                                                        functionalityEndpointMapUpdateParameterDTO){

        return functionalityEndpointMappingServiceExtended.updateFunctionalityEndpointMap(functionalityEndpointMapUpdateParameterDTO);
    }

    //        operationType should be
    //        -functionalityUUID
    //        -functionalityName
    //        -functionalityNo
    //        -endpointName
    //        -endpointUUID
    @GetMapping("/getFunctionalityEndpointDetailsByNameOrNoOrUUID")
    public ResponseDTO getFunctionalityEndpointDetailsByNameOrNoOrUUID(
        @NotBlank(message = "Data should not be blank")
        @RequestParam("data") String data,
        @NotBlank(message = "Operation_Type should not be blank")
        @RequestParam("operationType") String operationType){
        return functionalityEndpointMappingServiceExtended.getFunctionalityEndpointDetailsByNameOrNoOrUUID(data,operationType);
    }
}
