package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import liquibase.pro.packaged.T;
import reactor.core.publisher.Flux;

public interface SecondaryClaimsSubmissionMasterDataRepoExtended extends SecondaryClaimSubmisionMasterRepository{

	@Query(value="CALL so.so_secondary_submission(:salesOrderId)")
	Flux<T> getSecondarySubmissionData(@Param("salesOrderId") Long salesOrderId);

}
