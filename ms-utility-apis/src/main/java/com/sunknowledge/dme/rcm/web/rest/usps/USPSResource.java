package com.sunknowledge.dme.rcm.web.rest.usps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.dto.usps.AddressInput;
import com.sunknowledge.dme.rcm.dto.usps.AddressValidateResponse;
import com.sunknowledge.dme.rcm.service.usps.USPSService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usps")
@Slf4j
public class USPSResource {
    @Autowired
    private USPSService uspsService;

    @ApiOperation(value = "Address Validate Request Service")
    @GetMapping("addressValidateRequestService")
    @ResponseBody
    public ServiceOutcome<AddressValidateResponse> addressValidateRequest(AddressInput address) {
        String resultOutcomeJson = "";
        ServiceOutcome<AddressValidateResponse> addressValidateResponse = null;
        try {
            address = uspsService.createUSPSAddress(address);
            addressValidateResponse = uspsService.addressValidateRequest(address);

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            resultOutcomeJson = ow.writeValueAsString(addressValidateResponse);
            System.out.println("RESULT OUTPUT JSON=>" + resultOutcomeJson);
        } catch (Exception e) {
            log.info("-------------ERROR on USPS address validation---------------");
            e.printStackTrace();
        }
        return addressValidateResponse;
    }
}
