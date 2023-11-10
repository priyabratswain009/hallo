package com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Status {
	public String statusCode;
	public String statusMessage;
	public String statusTimeStamp;
	public ArrayList<Document> documents;
}