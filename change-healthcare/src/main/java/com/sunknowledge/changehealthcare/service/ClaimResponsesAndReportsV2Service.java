package com.sunknowledge.changehealthcare.service;

import java.util.ArrayList;

import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.pojo.claimreports.ActualClaimResponseReport;
import com.sunknowledge.changehealthcare.pojo.claimreports.ClaimReports;
import com.sunknowledge.changehealthcare.pojo.claimreports.ClaimsTransactionReport;
import com.sunknowledge.changehealthcare.pojo.claimreports.X12EDIReportContents;

public interface ClaimResponsesAndReportsV2Service {
	ServiceOutcome<ArrayList<ActualClaimResponseReport>> getActualClaimResponsesReports();
	ServiceOutcome<ClaimReports> completeAvailableCustomerDocumentsList(String token);
	ServiceOutcome<X12EDIReportContents> getX12EDIReportContent(String fileName, String accessToken);
	ServiceOutcome<ClaimsTransactionReport> getActualReadableReportContent(String fileName, String token);
}
