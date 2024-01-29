package com.sunknowledge.dme.rcm.service.pickupExchange;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.RequestParam;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.service.dto.pickupExchange.PickupExchangeCustomReqDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PickUpExchangeService {

	public Mono<PickupExchange> pickupExchangeCreation(PickupExchangeCustomReqDTO pickupExchangeCustomDTO);

	public Flux<ServiceOutcome<List<PickupExchange>>> getPickupExchangeByStatus(String status)
			throws InterruptedException, ExecutionException;

	public Flux<ServiceOutcome> getPickupExchangeByPickupExchangeNo(String pickupExchangeNo)
			throws InterruptedException, ExecutionException;

	public Flux<ServiceOutcome> getPickupExchangeDocumentsByPickupExchangeNo(String pickupExchangeNo)
			throws InterruptedException, ExecutionException;

	public Flux<ServiceOutcome> getPickupExchangeItemsByPickupExchangeNo(String pickupExchangeNo)
			throws InterruptedException, ExecutionException;

	public Flux<ServiceOutcome> getPickupExchangeDataForList(String pickupExchangeNo, String pickupexchangetype,
			String sono, String patientidno, String patientfirstname, String patientlastname,
			String pickupexchangeagentidno, String pickupexchangeagentname, String pickupexchangestatus)
			throws InterruptedException, ExecutionException;

}
