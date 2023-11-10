package com.sunknowledge.dme.rcm.web.rest.common;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.common.MarketingReferalTypeMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.common.MarketingReferalTypeMasterExtendedDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
public class MarketingReferalTypeMasterResourceExtended {

    @Autowired
    MarketingReferalTypeMasterServiceExtended marketingReferalTypeMasterServiceExtended;

    @PatchMapping(value = "/saveMarketingReferalTypeMaster",consumes = {"application/json", "application/merge-patch+json"})
    public ServiceOutcome saveMarketingReferalTypeMaster(@Valid @RequestBody MarketingReferalTypeMasterExtendedDTO
                                                                 marketingReferalTypeMasterExtendedDTO) throws InvalidAttributeValueException {
        return marketingReferalTypeMasterServiceExtended.saveMarketingReferalTypeMaster(marketingReferalTypeMasterExtendedDTO);
    }

    @GetMapping("/getAllMarketingReferalTypeMasterInfo")
    public ServiceOutcome getAllMarketingReferalTypeMasterInfo(){
        return marketingReferalTypeMasterServiceExtended.getAllMarketingReferalTypeMasterInfo();
    }

    @GetMapping("/getMarketingReferalTypeMasterInfoByUUID")
    public ServiceOutcome getMarketingReferalTypeMasterInfoByUUID(
        @NotBlank(message = "Marketing Referal Type Master UUID must be provided")
        @RequestParam("uuid") UUID uuid){

        return marketingReferalTypeMasterServiceExtended.getMarketingReferalTypeMasterInfoByUUID(uuid);
    }
}
