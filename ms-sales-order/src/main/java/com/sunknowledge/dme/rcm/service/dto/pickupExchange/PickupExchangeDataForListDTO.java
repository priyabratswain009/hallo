package com.sunknowledge.dme.rcm.service.dto.pickupExchange;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickupExchangeDataForListDTO {

	private String pickupexchangeno;
	private String pickupexchangetype;
	private String sono;
	private String pickupexchangestatus;
	private LocalDate pickupexchangescheduledatetime;
	private LocalDate pickupexchangeactualdatetime;
	private String pickupexchangereason;
	
}
