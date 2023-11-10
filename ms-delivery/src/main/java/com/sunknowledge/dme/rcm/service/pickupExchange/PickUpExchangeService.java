package com.sunknowledge.dme.rcm.service.pickupExchange;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.pickupExchange.PickupExchangeReqandResp;
import com.sunknowledge.dme.rcm.service.dto.PickupExchangeDTO;

import net.minidev.json.parser.ParseException;

public interface PickUpExchangeService {

	ServiceOutcome<PickupExchangeDTO> updatePickupExchangedeliveryagentdata(UUID pickupexchangeuuid, String pickupexchangescheduledatetime, String pickupexchangeagentidno,
			String pickupexchangeagentname, String noteForDeliveryAgent);

	ServiceOutcome<PickupExchangeDTO> getPickupExchangeByUUID(UUID pickupexchangeuuid);

	ServiceOutcome<PickupExchangeReqandResp> getPickupExchangeDataForReport(UUID pickupexchangeuuid) throws JsonMappingException, JsonProcessingException, ParseException, org.json.simple.parser.ParseException, Exception;

	ServiceOutcome<PickupExchangeReqandResp> generateandUpdatePickupExchangeDocument(PickupExchangeReqandResp pickupExchangeReqandResp) throws IOException, DocumentException, ParseException, org.json.simple.parser.ParseException, WriterException, com.google.zxing.WriterException, Exception;

	ServiceOutcome<PickupExchangeDTO> getPickupExchangeDataperAgent(String agentIdNo);

	ServiceOutcome<List<PickupExchangeDTO>> getAllPickupExchangeData();

    ServiceOutcome<PickupExchangeReqandResp> preparePickUpExchangeTicketReport(PickupExchangeReqandResp pickupExchangeReqandResp, String poeDocFilePath, String QRCodeOfPOEDocFilePath, String getPOESignaturesFilePath) throws Exception;
}
