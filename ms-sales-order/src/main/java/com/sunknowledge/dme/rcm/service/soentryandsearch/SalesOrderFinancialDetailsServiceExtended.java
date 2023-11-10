package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails;
import com.sunknowledge.dme.rcm.service.SalesOrderFinancialDetailsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SalesOrderFinancialDetailsServiceExtended extends SalesOrderFinancialDetailsService {
    Flux<SalesOrderFinancialDetails> findBySalesOrderId(Long SOID);

    Flux<SalesOrderFinancialDetails> getSOFinancialDetailsBySOFinancialDetailsUUID(UUID sOFinancialDetailsUUID);

    Long getIDByUUID(UUID sOFinancialDetailsUUID);

    Mono<SalesOrderFinancialDetails> findById(Long id);
}
