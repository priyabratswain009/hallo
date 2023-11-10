package com.sunknowledge.dme.rcm.dto.RenwalOrExpiringPAR;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RenwalOrExpiringPARDTO {

	private Long parid;
	private String parno;
	private Long soid;
	private String sono;
	private Long itemid;
	private ZonedDateTime expdate;
	private ZonedDateTime dateofservice;
	private Long patientid;
	private Long insuranceid;
	
}
