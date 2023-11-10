package com.sunknowledge.changehealthcare.pojo.claimreports;

import com.sunknowledge.changehealthcare.pojo.claimreports.claimsremittance835.ClaimsRemittanceTransaction;
import com.sunknowledge.changehealthcare.pojo.claimreports.claimstatusresponse277.ClaimStatusResponseTransaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimsTransactionReport {
	private ClaimStatusResponseTransaction claimStatusResponseTransaction;
	private ClaimsRemittanceTransaction claimsRemittanceTransaction;
}
