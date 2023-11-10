package com.sunknowledge.dme.rcm.web.rest.soentryandsearch;

import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderInsuranceDetailsSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderInsuranceSearchDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderMasterSearchDetailsDTO;
import com.sunknowledge.dme.rcm.web.rest.SalesOrderMasterResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * REST controller for SalesOrderInsuranceDetailsSearchResource.
 */
@Validated
@RestController
@RequestMapping("/api")
public class SalesOrderInsuranceDetailsSearchResourceExtended {
    private final Logger log = LoggerFactory.getLogger(SalesOrderMasterResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    SalesOrderInsuranceDetailsSearchServiceExtended salesOrderInsuranceDetailsSearchService;

    /**
     * Get SO Insurance Details By Insurance Name
     */
//    @GetMapping("/getInsuranceByInsuranceName")
//    public Flux<SalesOrderInsuranceSearchDetailsDTO> getSOInsuranceDetailsByInsuranceName(
//        @NotBlank(message = "Insurance_Name must be provided")
//        @RequestParam("insuranceName") String insuranceName
//    ) {
//
//        return salesOrderInsuranceDetailsSearchService.getSOInsuranceDetailsByInsuranceName(insuranceName);
//    }
//
//    /**
//     * Get SO Details By Insurance Id
//     */
//    @GetMapping("/getSOByInsuranceId")
//    public Flux<SalesOrderMasterSearchDetailsDTO> getSODetailsByInsuranceId(
//        @Min(value=1, message="Insurance_Id must be greater than or equal to 1")
//        @RequestParam("insuranceId") Long insuranceId
//    ) {
//
//        return salesOrderInsuranceDetailsSearchService.getSODetailsByInsuranceId(insuranceId);
//    }

}
