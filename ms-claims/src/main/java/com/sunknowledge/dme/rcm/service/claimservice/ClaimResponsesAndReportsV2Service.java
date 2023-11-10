package com.sunknowledge.dme.rcm.service.claimservice;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.pojo.claimreports.ActualClaimResponseReport;
import com.sunknowledge.dme.rcm.pojo.claimreports.ClaimReports;
import com.sunknowledge.dme.rcm.pojo.claimreports.ClaimsTransactionReport;
import com.sunknowledge.dme.rcm.pojo.claimreports.X12EDIReportContents;
import com.sunknowledge.dme.rcm.pojo.claimreports.transaction.ClaimTransactionOutcome;
import com.sunknowledge.dme.rcm.pojo.claimreports.transaction.ClaimsCOB835Data;
import com.sunknowledge.dme.rcm.pojo.claimreports.transaction.ClaimsStatus277Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ClaimResponsesAndReportsV2Service {
	ServiceOutcome<ArrayList<ActualClaimResponseReport>> processActualClaimResponsesReport();
	ServiceOutcome<ClaimReports> completeAvailableCustomerDocumentsList(String token);
	ServiceOutcome<X12EDIReportContents> getX12EDIReportContent(String fileName, String accessToken);
	ServiceOutcome<ClaimsTransactionReport> getActualReadableReportContent(String fileName, String token);
	ClaimTransactionOutcome saveClaimTransaction(List<ActualClaimResponseReport> claimTransactionInput);
	void claimSubmissionStatusOperation(ClaimTransactionOutcome transactionOutcome) throws ExecutionException, InterruptedException;
	List<ClaimsCOB835Data> getAllClaimsCOB835MasterData();
	List<ClaimsStatus277Data> getAllClaimsStatus277MasterData();
    void preparePrimaryClaimSubmissionHealthInsuranceForm(String claimControlNumber) throws Exception;
    void prepareSecondaryClaimSubmissionHealthInsuranceForm(String claimControlNumber) throws Exception;
    void preparePrimaryClaimReSubmissionHealthInsuranceForm(String claimControlNumber) throws Exception;
    void process835CrossoverInsurance();
}
