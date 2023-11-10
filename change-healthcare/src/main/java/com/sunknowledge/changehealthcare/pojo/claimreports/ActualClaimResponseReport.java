package com.sunknowledge.changehealthcare.pojo.claimreports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualClaimResponseReport {
	private ClaimsTransactionReport claimsTransactionReport;
	private X12EDIReportContents x12ediReportContents;
	private String fileName;
}
