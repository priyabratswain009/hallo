package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SalesOrderInsuranceDetailsRepositoryExtended extends SalesOrderInsuranceDetailsRepository{
    Flux<SalesOrderInsuranceDetails> findBySalesOrderId(Long SOID);

    Flux<SalesOrderInsuranceDetails> getSOInsuranceDetailsByInsuranceUUID(UUID sOInsuranceDetailsUUID);

    @Query(value="select so.get_t_sales_order_insurance_details_id_by_uuid(:sOInsuranceDetailsUUID)")
    Mono<Long> getIDByUUID(@Param("sOInsuranceDetailsUUID") UUID sOInsuranceDetailsUUID);

    Mono<SalesOrderInsuranceDetails> findBySOId(Long SOID);

    @Query(value = "select *  from so.t_sales_order_insurance_details \n" +
        "where sales_order_insurance_details_id = :soInsId")
    Mono<SalesOrderInsuranceDetails> getSoInsuranceDetailsBySoInsId(@Param("soInsId") Long soInsId);
}
