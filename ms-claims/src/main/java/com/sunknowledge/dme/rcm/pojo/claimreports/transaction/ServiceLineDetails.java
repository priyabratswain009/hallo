package com.sunknowledge.dme.rcm.pojo.claimreports.transaction;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceLineDetails {
	private String serviceDate;
	private String adjudicatedProcedureCode;
	private String adjudicatedProcedureModifierCodes;
	private Double chargeAmount;
	private Double allowedAmount;
	List<ServiceAdjustments> serviceAdjustmentList;
	private Double providerPaymentAmount;
}
