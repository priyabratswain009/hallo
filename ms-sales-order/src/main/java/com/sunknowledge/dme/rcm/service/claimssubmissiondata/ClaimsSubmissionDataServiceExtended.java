package com.sunknowledge.dme.rcm.service.claimssubmissiondata;

import com.sunknowledge.dme.rcm.dto.claims.AddSecondaryForPrimaryDTO;

import reactor.core.publisher.Mono;

public interface ClaimsSubmissionDataServiceExtended {

	void getClaimsSubmissionData(String soId);

	Mono<AddSecondaryForPrimaryDTO> addSecondaryForPrimary(Long salesOrderId, String internalClaimControlNumber);
}
