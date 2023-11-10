package com.sunknowledge.dme.rcm.web.rest.common;


import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.common.FacilityMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.common.FacilityMasterParameterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@RestController
@RequestMapping("/api")
@Slf4j
public class FacilityMasterResourceExtended {

    @Autowired
    FacilityMasterServiceExtended facilityMasterServiceExtended;

    @PatchMapping(value = "/saveFacilityMaster",consumes = {"application/json", "application/merge-patch+json"})
    public ServiceOutcome saveFacilityMaster(@Valid @RequestBody FacilityMasterParameterDTO
                                                 facilityMasterParameterDTO){
        return facilityMasterServiceExtended.saveFacilityMaster(facilityMasterParameterDTO);
    }

    //    operationType should be
    //    -facilityName
    //    -facilityNo
    //    -facilityUUID
    @GetMapping("/getFacilityMasterByNameOrNoOrUUID")
    public ServiceOutcome getFacilityMasterByNameOrNoOrUUID(
        @NotBlank(message = "Data should not be blank")
        @RequestParam("data") String data,
        @NotBlank(message = "Operation_Type should not be blank")
        @RequestParam("operationType") String operationType){
        return facilityMasterServiceExtended.getFacilityMasterByNameOrNoOrUUID(data,operationType);
    }
}
