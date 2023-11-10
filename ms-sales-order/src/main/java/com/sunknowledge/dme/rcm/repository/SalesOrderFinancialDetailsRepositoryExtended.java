package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SalesOrderFinancialDetailsRepositoryExtended extends SalesOrderFinancialDetailsRepository{
    Flux<SalesOrderFinancialDetails> findBySalesOrderId(Long SOID);

    Flux<SalesOrderFinancialDetails> getSOFinancialDetailsBySOFinancialDetailsUUID(UUID sOFinancialDetailsUUID);

    @Query(value="select so.get_t_sales_order_financial_details_id_by_uuid(:sOFinancialDetailsUUID)")
    Mono<Long> getIDByUUID(@Param("sOFinancialDetailsUUID") UUID sOFinancialDetailsUUID);
}
