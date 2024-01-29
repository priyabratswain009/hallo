package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMaster;

import liquibase.pro.packaged.T;
import reactor.core.publisher.Flux;

public interface SecondaryClaimsSubmissionMasterDataRepoExtended extends SecondaryClaimSubmisionMasterRepository{

	@Query(value="CALL so.so_secondary_submission(:salesOrderId)")
	Flux<T> getSecondarySubmissionData(@Param("salesOrderId") Long salesOrderId);

	@Query(value="select * from so.t_secondary_claim_submision_master  where sales_order_id = :salesOrderId order by inserted_date asc")
	Flux<SecondaryClaimSubmisionMaster> getSecondarySubmissionDataForValidation(@Param("salesOrderId") Long salesOrderId);

}
