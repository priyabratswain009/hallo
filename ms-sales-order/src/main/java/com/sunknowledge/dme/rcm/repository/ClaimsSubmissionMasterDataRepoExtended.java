package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import liquibase.pro.packaged.T;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ClaimsSubmissionMasterDataRepoExtended extends PrimaryClaimSubmisionMasterRepository{

	@Query(value = "CALL so.so_primary_submission(:salesOrderId)")
    Flux<T> getClaimsSubmissionData(@Param("salesOrderId") Long salesOrderId);

}
