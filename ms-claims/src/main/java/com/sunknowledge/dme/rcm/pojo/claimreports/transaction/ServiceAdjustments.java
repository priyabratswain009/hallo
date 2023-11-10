package com.sunknowledge.dme.rcm.pojo.claimreports.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceAdjustments {
	private Double adjustmentCodePr1Amount;
	private Double adjustmentCodePr2Amount;
	private Double adjustmentCodePr3Amount;
	private String adjustmentCoCode1;
	private Double adjustmentCoCode1Amount;
	private String adjustmentCoCode2;
	private Double adjustmentCoCode2Amount;
	private String adjustmentCoCode3;
	private Double adjustmentCoCode3Amount;
	private String adjustmentCoCode4;
	private Double adjustmentCoCode4Amount;
	private String adjustmentCoCode5;
	private Double adjustmentCoCode5Amount;
}
