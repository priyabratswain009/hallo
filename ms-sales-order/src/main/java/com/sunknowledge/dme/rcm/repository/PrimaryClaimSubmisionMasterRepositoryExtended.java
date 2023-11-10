package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionMaster;

public interface PrimaryClaimSubmisionMasterRepositoryExtended extends PrimaryClaimSubmisionMasterRepository {

	@Query(value="From ClaimsSubmissionMaster where salesOrderId=:salesOrderNumber")
	PrimaryClaimSubmisionMaster getPrimarySubmissionDetails(@Param("salesOrderNumber") Long salesOrderNumber);
}
