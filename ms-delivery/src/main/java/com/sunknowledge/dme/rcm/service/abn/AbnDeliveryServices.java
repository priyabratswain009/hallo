package com.sunknowledge.dme.rcm.service.abn;

import java.io.IOException;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.abn.AbnRequestandResponseData;
import com.sunknowledge.dme.rcm.service.dto.AbnDeliveryDTO;

import net.minidev.json.parser.ParseException;

public interface AbnDeliveryServices {

	public ServiceOutcome<AbnDeliveryDTO> updatedeliveryagentdata(UUID abnDeliveryUuid, String scheduleDeliveryDateTime,
			Long deliveryAgentId, String deliveryAgentName);

	public ServiceOutcome<AbnRequestandResponseData> getABNdataByUUID(UUID abnDeliveryUuid) throws JsonMappingException, JsonProcessingException, ParseException, org.json.simple.parser.ParseException;

	public ServiceOutcome<AbnRequestandResponseData> updateABNDeliveryData(AbnRequestandResponseData abnRequestandResponseData) throws JsonProcessingException, ParseException, org.json.simple.parser.ParseException;
	
	public void generateABNDocument(AbnRequestandResponseData abnRequestandResponseData) throws IOException, DocumentException, ParseException, org.json.simple.parser.ParseException, WriterException, com.google.zxing.WriterException;

}
