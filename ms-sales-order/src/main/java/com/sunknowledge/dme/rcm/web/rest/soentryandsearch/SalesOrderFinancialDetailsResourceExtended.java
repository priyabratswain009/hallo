package com.sunknowledge.dme.rcm.web.rest.soentryandsearch;

import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderFinancialDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterServiceExtented;
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

import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Validated
@RestController
@RequestMapping("/api")
public class SalesOrderFinancialDetailsResourceExtended {
    private final Logger log = LoggerFactory.getLogger(SalesOrderFinancialDetailsResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    SalesOrderFinancialDetailsServiceExtended salesOrderFinancialDetailsServiceExtended;

    @Autowired
    SalesOrderMasterServiceExtented salesOrderMasterServiceExtented;

    @GetMapping("/getSOFinancialDetailsBySOFinancialDetailsUUID")
    public Mono<SalesOrderFinancialDetails> getSOFinancialDetailsBySOFinancialDetailsUUID(
        @NotNull(message = "SalesOrder_FinancialDetails_UUID must be provided")
        @RequestParam("sOFinancialDetailsUUID") UUID sOFinancialDetailsUUID){
        //----- Implementing UUID_To_ID Bridge Method ----------
        Long id = 0L;
        if (sOFinancialDetailsUUID != null) {
            id = salesOrderFinancialDetailsServiceExtended.getIDByUUID(sOFinancialDetailsUUID);
            id = id != null ? id : 0L;
        }
        return salesOrderFinancialDetailsServiceExtended.findById(id);
    }

    @GetMapping("/getSOFinancialDetailsBySOUUID")
    public Flux<SalesOrderFinancialDetails> getSOFinancialDetailsBySOUUID(
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
        return salesOrderFinancialDetailsServiceExtended.findBySalesOrderId(id);
    }
}
