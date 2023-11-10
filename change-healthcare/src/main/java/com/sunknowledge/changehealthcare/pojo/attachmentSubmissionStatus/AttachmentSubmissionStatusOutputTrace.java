package com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus;

import java.util.ArrayList;

import lombok.Data;

@Data
public class AttachmentSubmissionStatusOutputTrace {

	public TransactionDetails transactionDetails;
    public ArrayList<Status> status;
    
}
