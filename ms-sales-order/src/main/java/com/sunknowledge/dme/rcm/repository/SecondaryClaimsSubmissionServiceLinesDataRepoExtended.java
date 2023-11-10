package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import liquibase.pro.packaged.T;
import reactor.core.publisher.Flux;

public interface SecondaryClaimsSubmissionServiceLinesDataRepoExtended extends SecondaryClaimSubmisionServicelinesRepository{

	@Query(value="CALL so.so_secondary_servicelines_submission(:salesOrderId)")
	Flux<T> getSecondarySubmissionServiceLinesData(@Param("salesOrderId") Long salesOrderId);

}
