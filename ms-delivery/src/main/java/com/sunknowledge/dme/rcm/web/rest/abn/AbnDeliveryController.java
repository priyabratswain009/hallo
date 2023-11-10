package com.sunknowledge.dme.rcm.web.rest.abn;

import java.io.IOException;
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
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.abn.AbnRequestandResponseData;
import com.sunknowledge.dme.rcm.service.abn.AbnDeliveryServices;
import com.sunknowledge.dme.rcm.service.dto.AbnDeliveryDTO;

import io.swagger.annotations.ApiOperation;
import net.minidev.json.parser.ParseException;

@RestController
@RequestMapping("/api/abn")
public class AbnDeliveryController {

	@Autowired
	private AbnDeliveryServices abnDeliveryService;

	@ApiOperation(value = "Update Delivery Agent Data")
	@PostMapping(path = "/updatedeliveryagentdata")
	public ServiceOutcome<AbnDeliveryDTO> updatedeliveryagentdata(
			@RequestParam("abnDeliveryUuid") UUID abnDeliveryUuid,
			@RequestParam("scheduleDeliveryDateTime") String scheduleDeliveryDateTime,
			@RequestParam("deliveryAgentId") Long deliveryAgentId,
			@RequestParam("deliveryAgentName") String deliveryAgentName) {

		return abnDeliveryService.updatedeliveryagentdata(abnDeliveryUuid, scheduleDeliveryDateTime, deliveryAgentId,
				deliveryAgentName);
	}

	@ApiOperation(value = "Get ABN Data")
	@GetMapping(path = "/getABNdataByUUID")
	public ServiceOutcome<AbnRequestandResponseData> getABNdataByUUID(
			@RequestParam("deliveryAbnDataUuid") UUID deliveryAbnDataUuid) throws JsonMappingException, JsonProcessingException, ParseException, org.json.simple.parser.ParseException {

		return abnDeliveryService.getABNdataByUUID(deliveryAbnDataUuid);
	}

	@ApiOperation(value = "Update ABN delivery Data")
	@PostMapping(path = "/updateABNDeliveryData")
	public ServiceOutcome<AbnRequestandResponseData> updateABNDeliveryData(@RequestBody AbnRequestandResponseData abnRequestandResponseData) throws JsonProcessingException, ParseException, org.json.simple.parser.ParseException {

		return abnDeliveryService.updateABNDeliveryData(abnRequestandResponseData);
	}

	@ApiOperation(value = "Generate ABN Document")
	@PostMapping(path = "/generateABNDocument")
	public void generateABNDocument(@RequestBody AbnRequestandResponseData abnRequestandResponseData) throws IOException, DocumentException, ParseException, org.json.simple.parser.ParseException, WriterException, com.google.zxing.WriterException {

		abnDeliveryService.generateABNDocument(abnRequestandResponseData);
	}

}
