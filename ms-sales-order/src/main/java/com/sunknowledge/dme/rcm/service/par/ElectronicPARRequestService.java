package com.sunknowledge.dme.rcm.service.par;

import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.EparRequest;
import com.sunknowledge.dme.rcm.domain.ServiceReview.ServiceReviewInput;
import com.sunknowledge.dme.rcm.service.dto.EparRequestDTO;

import reactor.core.publisher.Mono;

public interface ElectronicPARRequestService {

	public Mono<EparRequest> createServiceReview(ServiceReviewInput serviceReviewInput, Long salesOrderId, String soNo,
			String message, String data) throws JsonProcessingException;

	public Mono<ServiceOutcome<EparRequestDTO>> getEpaRequestonSoId(Long soId)
			throws InterruptedException, ExecutionException;

}
