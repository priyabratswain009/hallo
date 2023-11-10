package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceMasterDTO {

	private Long sales_order_id;
	private String primary_insurer_name;
	private String primary_insurer_address_line_1;
	private String primary_insurer_address_line_2;
	private String primary_insurer_city;
	private String primary_insurer_state;
	private String primary_insurer_zip;
	private String primary_insurer_contact_1;
	private String primary_insurer_fax;
	
}
