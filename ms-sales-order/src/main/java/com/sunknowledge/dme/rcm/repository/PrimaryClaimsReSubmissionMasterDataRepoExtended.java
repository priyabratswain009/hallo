package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import liquibase.pro.packaged.T;
import reactor.core.publisher.Flux;

public interface PrimaryClaimsReSubmissionMasterDataRepoExtended extends PrimaryClaimResubmisionMasterRepository{

	@Query(value = "CALL so.so_primary_resubmission(:salesOrderId)")
	Flux<T> getPrimaryReSubmissionData(@Param("salesOrderId") Long salesOrderId);

}
