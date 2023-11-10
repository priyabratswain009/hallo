package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import liquibase.pro.packaged.T;
import reactor.core.publisher.Flux;

public interface ClaimStatusRepoExtended extends ClaimSubmissionStatusRepository{

	@Query(value="CALL so.claims_status_primary(:salesOrderId)")
	Flux<T> getPrimarySubmissionData(@Param("salesOrderId") Long salesOrderId);

	@Query(value="CALL so.claims_status_primary_resubmission(:salesOrderId)")
	Flux<T> getPrimaryReSubmissionData(@Param("salesOrderId") Long salesOrderId);

	@Query(value="CALL so.claims_status_secondary(:salesOrderId)")
	Flux<T> getSecondarySubmissionData(@Param("salesOrderId") Long salesOrderId);

}
