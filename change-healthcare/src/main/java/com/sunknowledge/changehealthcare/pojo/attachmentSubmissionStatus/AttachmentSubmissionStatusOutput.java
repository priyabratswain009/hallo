package com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus;

import java.util.ArrayList;

import lombok.Data;

@Data
public class AttachmentSubmissionStatusOutput {
	public String traceId;
	public TransactionDetails transactionDetails;
	public ArrayList<Status> status;
}