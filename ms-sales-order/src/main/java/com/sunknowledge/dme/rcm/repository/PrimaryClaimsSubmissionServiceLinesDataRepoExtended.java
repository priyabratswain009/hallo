package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import liquibase.pro.packaged.T;
import reactor.core.publisher.Flux;

public interface PrimaryClaimsSubmissionServiceLinesDataRepoExtended extends PrimaryClaimSubmisionServicelinesRepository{

	@Query(value="CALL so.so_primary_servicelines_submission(:salesOrderId)")
	Flux<T> getPrimarySubmissionServiceLinesData(@Param("salesOrderId") Long salesOrderId);

}
