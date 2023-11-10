package com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus;

import lombok.Data;

@Data
public class TransactionDetails {
	public String submitterId;
	public String memberId;
	public String patientFirstName;
	public String patientLastName;
	public String payerId;
	public String providerId;
	public Object providerFirstName;
	public String providerLastName;
	public String claimStartDate;
	public String claimEndDate;
	public String payerName;
	public String transactionSubmittedDate;
	public String rejectionInformation;
}