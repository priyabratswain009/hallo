package com.sunknowledge.dme.rcm.web.rest.soentryandsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderInsuranceDetailsMapper;
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
import reactor.core.publisher.Mono;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    @Autowired
    SalesOrderInsuranceDetailsMapper salesOrderInsuranceDetailsMapper;

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

    @GetMapping("/getSalesOrderInsuranceDetailsBySOId")
    public Mono<ServiceOutcome<SalesOrderInsuranceDetailsDTO>> getSalesOrderInsuranceDetailsBySOId(
        @NotNull(message = "Sales_Order_ID must be provided")
        @RequestParam("salesOrderID") Long salesOrderID) {
        return salesOrderInsuranceDetailsSearchService.findBySalesOrderId(salesOrderID)
            .map(data->{
                return new ServiceOutcome<SalesOrderInsuranceDetailsDTO>(salesOrderInsuranceDetailsMapper.toDto(data), true, "Successfully Fetched.");
            }).switchIfEmpty(Mono.just(new ServiceOutcome<SalesOrderInsuranceDetailsDTO>(null, false, "Data Not Found.")));

    }

}
