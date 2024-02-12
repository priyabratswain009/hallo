package com.sunknowledge.dme.rcm.service.dto.soentryandsearch;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetselectedItemsForSOID {

	Long salesorderid;
	String itemnumber;
	String itemname;
	String hcpcs;
	LocalDate originaldos;
	LocalDate originalto;
	LocalDate cmnexpirationdate;
	String wetherserialized;
	Long pickupitemserialnumber;
	Long replaceitemserialnumber;
	
}
