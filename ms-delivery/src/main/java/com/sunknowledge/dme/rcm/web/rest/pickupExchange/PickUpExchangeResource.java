package com.sunknowledge.dme.rcm.web.rest.pickupExchange;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.pickupExchange.PickupExchangeReqandResp;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeDTO;
import com.sunknowledge.dme.rcm.service.pickupExchange.PickUpExchangeService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.ParseException;

@RestController
@RequestMapping("/api/pickupExchange")
@Slf4j
public class PickUpExchangeResource {

	@Autowired
	private PickUpExchangeService pickUpExchangeService;

	@ApiOperation(value = "Update Pickup Exchange Delivery Agent Data")
	@PostMapping(path = "/updatePickupExchangedeliveryagentdata")
	public ServiceOutcome<PickupExchangeDTO> updatePickupExchangedeliveryagentdata(
			@RequestParam("pickupexchangeuuid") UUID pickupexchangeuuid,
			@RequestParam("pickupexchangescheduledatetime") String pickupexchangescheduledatetime,
			@RequestParam("pickupexchangeagentidno") String pickupexchangeagentidno,
			@RequestParam("pickupexchangeagentname") String pickupexchangeagentname,
			@RequestParam("noteForDeliveryAgent") String noteForDeliveryAgent) {

		return pickUpExchangeService.updatePickupExchangedeliveryagentdata(pickupexchangeuuid,
				pickupexchangescheduledatetime, pickupexchangeagentidno, pickupexchangeagentname, noteForDeliveryAgent);
	}

	@ApiOperation(value = "Get Pickup Exchange Data")
	@GetMapping(path = "/getPickupExchangeByUUID")
	public ServiceOutcome<PickupExchangeDTO> getPickupExchangeByUUID(
			@RequestParam("pickupexchangeuuid") UUID pickupexchangeuuid) {

		return pickUpExchangeService.getPickupExchangeByUUID(pickupexchangeuuid);
	}

	@ApiOperation(value = "Get Pickup Exchange Data for Report")
	@GetMapping(path = "/getPickupExchangeDataForReport")
	public ServiceOutcome<PickupExchangeReqandResp> getPickupExchangeDataForReport(
			@RequestParam("pickupexchangeuuid") UUID pickupexchangeuuid) throws Exception {

		return pickUpExchangeService.getPickupExchangeDataForReport(pickupexchangeuuid);
	}

	@ApiOperation(value = "Generate and Update Pickup Exchange Document")
	@PostMapping(path = "/generateandUpdatePickupExchangeDocument")
	public ServiceOutcome<PickupExchangeReqandResp> generateandUpdatePickupExchangeDocument(
			@RequestBody PickupExchangeReqandResp pickupExchangeReqandResp) throws Exception {

		return pickUpExchangeService.generateandUpdatePickupExchangeDocument(pickupExchangeReqandResp);
	}

	@ApiOperation(value = "Get Pickup Exchange Data Per Agent")
	@GetMapping(path = "/getPickupExchangeDataperAgent")
	public ServiceOutcome<PickupExchangeDTO> getPickupExchangeDataperAgent(
			@RequestParam("agentIdNo") String agentIdNo) throws JsonMappingException,
			JsonProcessingException, ParseException, org.json.simple.parser.ParseException {

		return pickUpExchangeService.getPickupExchangeDataperAgent(agentIdNo);
	}

	@ApiOperation(value = "Get All Pickup Exchange Data")
	@GetMapping(path = "/getAllPickupExchangeData")
	public ServiceOutcome<List<PickupExchangeDTO>> getAllPickupExchangeData(){

		return pickUpExchangeService.getAllPickupExchangeData();
	}

    @ApiOperation(value = "Prepare Pick up/Exchange Ticket Report")
    @PostMapping(path = "/preparePickUpExchangeTicketReport")
    public ServiceOutcome<PickupExchangeReqandResp> preparePickUpExchangeTicketReport(@RequestBody PickupExchangeReqandResp pickupExchangeReqandResp) {
        log.info("=======================Code Starts Here=========================="+pickupExchangeReqandResp);
        String resultOutcomeJson = "Response";
        ServiceOutcome<PickupExchangeReqandResp> serviceOutcome = null;
        try {
            //Optional<DeliveryTicket> deliveryTicket = deliveryService.getDeliveryTicketOnDeliveryTicket(deliveryTicketId);
            serviceOutcome = pickUpExchangeService.preparePickUpExchangeTicketReport(pickupExchangeReqandResp, "default", "default", "default");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceOutcome;
    }
}
