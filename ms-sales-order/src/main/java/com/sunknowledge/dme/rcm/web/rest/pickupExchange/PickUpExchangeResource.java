package com.sunknowledge.dme.rcm.web.rest.pickupExchange;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.service.dto.pickupExchange.PickupExchangeCustomReqDTO;
import com.sunknowledge.dme.rcm.service.pickupExchange.PickUpExchangeService;

import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class PickUpExchangeResource {

	@Autowired
	private PickUpExchangeService pickUpExchangeService;

	@ApiOperation(value = "Create pickupexchange Data")
	@PostMapping("/pickupexchangeCreation")
	public Mono<PickupExchange> pickupexchangeCreation(@RequestBody PickupExchangeCustomReqDTO pickupExchangeCustomDTO)
			throws Exception {
		return pickUpExchangeService.pickupExchangeCreation(pickupExchangeCustomDTO);
	}

	@ApiOperation(value = "Get pickupexchange Data By Status")
	@GetMapping("getPickupExchangeByStatus")
	public Flux<ServiceOutcome<List<PickupExchange>>> getPickupExchangeByStatus(@RequestParam("status") String status)
			throws InterruptedException, ExecutionException {
		return pickUpExchangeService.getPickupExchangeByStatus(status);
	}

	@ApiOperation(value = "Get pickupexchange Data By PatientIdNo")
	@GetMapping("getPickupExchangeByPatientIdNo")
	public Flux<ServiceOutcome<List<PickupExchange>>> getPickupExchangeByPatientIdNo(
			@RequestParam("PatientIdNo") String PatientIdNo) throws InterruptedException, ExecutionException {
		return pickUpExchangeService.getPickupExchangeByStatus(PatientIdNo);
	}

	@ApiOperation(value = "Get pickupexchange Data By PickupExchangeNo")
	@GetMapping("getPickupExchangeByPickupExchangeNo")
	public Flux<ServiceOutcome> getPickupExchangeByPickupExchangeNo(
			@RequestParam("pickupExchangeNo") String pickupExchangeNo) throws InterruptedException, ExecutionException {
		return pickUpExchangeService.getPickupExchangeByPickupExchangeNo(pickupExchangeNo);
	}

	@ApiOperation(value = "Get pickupexchange Documents Data By PickupExchangeNo")
	@GetMapping("getPickupExchangeDocumentsByPickupExchangeNo")
	public Flux<ServiceOutcome> getPickupExchangeDocumentsByPickupExchangeNo(
			@RequestParam("pickupExchangeNo") String pickupExchangeNo) throws InterruptedException, ExecutionException {
		return pickUpExchangeService.getPickupExchangeDocumentsByPickupExchangeNo(pickupExchangeNo);
	}

	@ApiOperation(value = "Get pickupexchange Items Data By PickupExchangeNo")
	@GetMapping("getPickupExchangeItemsByPickupExchangeNo")
	public Flux<ServiceOutcome> getPickupExchangeItemsByPickupExchangeNo(
			@RequestParam("pickupExchangeNo") String pickupExchangeNo) throws InterruptedException, ExecutionException {
		return pickUpExchangeService.getPickupExchangeItemsByPickupExchangeNo(pickupExchangeNo);
	}

	@ApiOperation(value = "Get pickupexchange Data For List")
	@GetMapping("getPickupExchangeDataForList")
	public Flux<ServiceOutcome> getPickupExchangeDataForList(@RequestParam("pickupExchangeNo") String pickupExchangeNo,
			@RequestParam("pickupexchangetype") String pickupexchangetype, @RequestParam("sono") String sono,
			@RequestParam("patientidno") String patientidno, @RequestParam("patientfirstname") String patientfirstname,
			@RequestParam("patientlastname") String patientlastname,
			@RequestParam("pickupexchangeagentidno") String pickupexchangeagentidno,
			@RequestParam("pickupexchangeagentname") String pickupexchangeagentname,
			@RequestParam("pickupexchangestatus") String pickupexchangestatus)
			throws InterruptedException, ExecutionException {
		return pickUpExchangeService.getPickupExchangeDataForList(pickupExchangeNo, pickupexchangetype, sono,
				patientidno, patientfirstname, patientlastname, pickupexchangeagentidno, pickupexchangeagentname,
				pickupexchangestatus);
	}

}
