package com.sunknowledge.dme.rcm.service.par;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.ServiceReview.ServiceReviewInput;
import com.sunknowledge.dme.rcm.domain.ServiceReview.ServiceReviewResponseDTO;

import reactor.core.publisher.Mono;

public interface ServiceReviewService {

	public Mono<ServiceOutcome<String>> createServiceReview(ServiceReviewInput serviceReviewInput);
	public Mono<ServiceOutcome<ServiceReviewResponseDTO>> getServiceReviewById(String memberId);
	
}
