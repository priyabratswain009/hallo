package com.sunknowledge.dme.rcm.web.rest.soentryandsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderClinicalEntryParameterDTO;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderClinicalDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterServiceExtented;
import liquibase.pro.packaged.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Validated
@RestController
@RequestMapping("/api")
public class SalesOrderClinicalDetailsResourceExtended {
    private final Logger log = LoggerFactory.getLogger(SalesOrderClinicalDetailsResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    SalesOrderClinicalDetailsServiceExtended salesOrderClinicalDetailsServiceExtended;
    @Autowired
    SalesOrderMasterServiceExtented salesOrderMasterServiceExtented;

    @GetMapping("/getSOClinicalBySOClinicalUUID")
    public Mono<ServiceOutcome<SalesOrderClinicalDetails>> getSOClinicalBySOClinicalUUID(
        @NotNull(message = "SalesOrder_Clinical_UUID must be provided")
        @RequestParam("sOClinicalUUID") UUID sOClinicalUUID){
        //----- Implementing UUID_To_ID Bridge Method ----------
        Long id = 0L;
        if (sOClinicalUUID != null) {
            id = salesOrderClinicalDetailsServiceExtended.getIDByUUID(sOClinicalUUID);
            id = id != null ? id : 0L;
        }
        return salesOrderClinicalDetailsServiceExtended.findByClinicalId(id)
            .map(data -> {
                return new ServiceOutcome<SalesOrderClinicalDetails>(data,true,"","200");
            }).switchIfEmpty(Mono.just(new ServiceOutcome<SalesOrderClinicalDetails>(new SalesOrderClinicalDetails(),
                false,"Data Not found.","200")));
    }

    @GetMapping("/getSOClinicalBySOUUID")
    public Mono<ServiceOutcome<SalesOrderClinicalDetails>> getSOClinicalBySOUUID(
        @NotNull(message = "SalesOrder_UUID must be provided")
        @RequestParam("salesOrderUUID") UUID salesOrderUUID){
        //----- Implementing UUID_To_ID Bridge Method ----------
        Long id = 0L;
        if (salesOrderUUID != null) {
            try {
                id = salesOrderMasterServiceExtented.getIDByUUID(salesOrderUUID).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            id = id != null ? id : 0L;
        }
        return salesOrderClinicalDetailsServiceExtended.findBySalesOrderId(id)
            .collectList()
            .map(data -> {
                return new ServiceOutcome<SalesOrderClinicalDetails>(data.get(0),true,"","200");
            }).switchIfEmpty(Mono.just(new ServiceOutcome<SalesOrderClinicalDetails>(new SalesOrderClinicalDetails(),
                false,"Data Not found.","200")));
    }

    @PostMapping("/saveSOClinicalDetails")
    public Mono<ServiceOutcome> saveSOClinicalDetails(@RequestBody @Valid SalesOrderClinicalEntryParameterDTO salesOrderClinicalEntryParameterDTO){

        Mono<ServiceOutcome> serviceOutcomeMono = null;
        Map<String, Object> stringTMap = salesOrderClinicalDetailsServiceExtended.validateSOClinicalParameterDTO(salesOrderClinicalEntryParameterDTO);
        if((Boolean) stringTMap.get("status")) {
            Long soId = 0L;
            SalesOrderMasterDTO salesOrderMasterDTO = new SalesOrderMasterDTO();
            if (salesOrderClinicalEntryParameterDTO.getSalesOrderUUID() != null) {
                try {
                    salesOrderMasterDTO = salesOrderMasterServiceExtented.getSOBySoUUID(salesOrderClinicalEntryParameterDTO.getSalesOrderUUID()).toFuture().get();
                    soId = salesOrderMasterDTO!=null? salesOrderMasterDTO.getSalesOrderId():null;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
                soId = soId != null ? soId : 0L;
            }
            serviceOutcomeMono = salesOrderClinicalDetailsServiceExtended.saveSOClinicalDetails(salesOrderClinicalEntryParameterDTO,soId,salesOrderMasterDTO);
        }else{
            ServiceOutcome serviceOutcome= new ServiceOutcome<>();
            String message = (String) stringTMap.get("message");
            serviceOutcome.setData(new SalesOrderClinicalDetailsDTO());
            serviceOutcome.setOutcome(false);
            serviceOutcome.setMessage(message);
            serviceOutcomeMono = Mono.just(serviceOutcome);
        }
//        ResponseDTO salesOrderClinicalDetailsDTO = salesOrderClinicalDetailsServiceExtended.
//            getICDCodeMasterDataFromUtility("J44.0,J44.1,J44.9,G47.01,G47.02,G47.20","ICD-10-CM");

//        ResponseDTO salesOrderClinicalDetailsDTO = salesOrderClinicalDetailsServiceExtended.
//            getDoctorMasterDataFromPatient(1l,"1306951827");


        return serviceOutcomeMono;
    }

    @GetMapping("/getSOClinicalBySOID")
    public Mono<ServiceOutcome> getSOClinicalBySOID(
        @NotNull(message = "SalesOrder_ID must be provided")
        @RequestParam("salesOrderID") Long salesOrderID){
        if (!salesOrderID.equals(0) && Objects.nonNull(salesOrderID))
            return salesOrderClinicalDetailsServiceExtended.getSOClinicalBySOID(salesOrderID);


        else
            return Mono.just(new ServiceOutcome(null, false, "Data Not Found" ));

    }
}
