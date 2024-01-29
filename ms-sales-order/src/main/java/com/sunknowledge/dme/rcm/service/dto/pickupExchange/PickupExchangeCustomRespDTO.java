package com.sunknowledge.dme.rcm.service.dto.pickupExchange;

import java.time.LocalDate;
import java.util.List;

import com.sunknowledge.dme.rcm.domain.PickupExchangeItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PickupExchangeCustomRespDTO {

	private String pickupExchangeNo;
	private String pickupExchangeType;
	private String pickupExchangeStatus;
	private String pickupExchangeReason;
	private String patientNotsignedReason;
	private String status;
	private LocalDate pickupExchangeScheduleDateTime;
	private LocalDate pickupExchangeActualDateTime;
	private LocalDate lastBillingDate;
	private LocalDate dateOfDeath;
	private String isPatientSigned;
	private LocalDate patientSignedDateTime;
	private String soNo;
	private String pickupExchangeAgentName;
	private String isAgentSigned;
	private String pickupExchangeRequest;
	private String pickupExchangeComments;
	private String pickupExchangeNote;
	private String pickupExchangeDocumentNo;
	private String pickupExchangeDocumentName;
	private String pickupExchangeSupportingDocument1;
	private String pickupExchangeSupportingDocument2;
	private List<PickupExchangeItems> pickupExchangeItems;
	
}
