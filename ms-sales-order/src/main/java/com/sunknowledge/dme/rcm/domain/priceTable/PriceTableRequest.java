package com.sunknowledge.dme.rcm.domain.priceTable;

import lombok.Data;

@Data
public class PriceTableRequest {

	private Long priceTableId;
	private String priceTableName;
	private String itemId;
	private String saleType;
	private String dos;
	private int period;

}
