package com.sunknowledge.dme.rcm.pojo.claimreports;

import com.sunknowledge.dme.rcm.pojo.claimreports.claimsremittance835.ClaimsRemittanceTransaction;
import com.sunknowledge.dme.rcm.pojo.claimreports.claimstatusresponse277.ClaimStatusResponseTransaction;
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
