package com.sunknowledge.dme.rcm.repository.claims;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.dto.claims.ClaimSubmissionStatusCustomDTO;
import com.sunknowledge.dme.rcm.repository.ClaimSubmissionStatusRepository;

import reactor.core.publisher.Mono;

public interface ClaimSubmissionStatusRepo extends ClaimSubmissionStatusRepository {

	@Query(value = "select * from so.secondary_claim_regeneration (:salesOrderId, :claimControlNumber)")
	Mono<ClaimSubmissionStatusCustomDTO> getSecondarySubmissionData(@Param("salesOrderId") Long salesOrderId,
			@Param("claimControlNumber") String claimControlNumber);
	
	@Query(value="CALL so.so_secondary_submission(:salesOrderId)")
	Mono<ClaimSubmissionStatus> getSecondarySubmissionData(@Param("salesOrderId") Long salesOrderId);

}
