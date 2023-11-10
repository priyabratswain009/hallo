package com.sunknowledge.dme.rcm.web.rest.common;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.common.SalesRepServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.common.SalesRepParameterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
public class SalesRepResourceExtended {
    @Autowired
    SalesRepServiceExtended salesRepServiceExtended;

    @PatchMapping(value = "/saveSalesRep",consumes = {"application/json", "application/merge-patch+json"})
    public ServiceOutcome saveSalesRep(@Valid @RequestBody SalesRepParameterDTO
                                                 salesRepParameterDTO){
        return salesRepServiceExtended.saveSalesRep(salesRepParameterDTO);
    }

    //    operationType should be
    //    -salesRepName
    //    -salesRepNo
    //    -salesRepUUID
    @GetMapping("/getSalesRepByNameOrNoOrUUID")
    public ServiceOutcome getSalesRepByNameOrNoOrUUID(
        @NotBlank(message = "Data should not be blank")
        @RequestParam("data") String data,
        @NotBlank(message = "Operation_Type should not be blank")
        @RequestParam("operationType") String operationType){
        return salesRepServiceExtended.getSalesRepByNameOrNoOrUUID(data,operationType);
    }

    @GetMapping("/getSalesRepInfoByName")
    public ServiceOutcome getSalesRepInfoByName(
        @NotBlank(message = "Name must be provided")
        @RequestParam("name") String name){
        return salesRepServiceExtended.getSalesRepInfoByName(name);
    }

    @GetMapping("/getSalesRepInfoByUUID")
    public ServiceOutcome getSalesRepInfoByUUID(
        @NotBlank(message = "Claim Reason UUID must be provided")
        @RequestParam("uuid") UUID uuid){

        return salesRepServiceExtended.getSalesRepInfoByUUID(uuid);
    }
}
