package com.sunknowledge.changehealthcare.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus.AttachmentSubmissionStatusInput;
import com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus.AttachmentstatTraceInput;
import com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus.ResultAttachmentSubmissionStatusOutcome;
import com.sunknowledge.changehealthcare.service.AttachmentSubmissionStatusService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/changehealthcare")
public class AttachmentSubmissionStatusController {
	@Autowired
	private AttachmentSubmissionStatusService attmstatserv;

	@ApiOperation(value = "Access the Attchment Submission Status Check API")
	@PostMapping(path = "/accessattachmentSubmissionStatusMetatdata", produces = "application/json")
	@ResponseBody
	public String attachmentstatus(@RequestBody AttachmentSubmissionStatusInput attachmentSubmissionStatusInput,
			HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String resultOutcomeJson = "";
		ServiceOutcome<ResultAttachmentSubmissionStatusOutcome> routcome = new ServiceOutcome<ResultAttachmentSubmissionStatusOutcome>();
		try {
			ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String attachmentSubmissionStatusInputJsonString = writer
					.writeValueAsString(attachmentSubmissionStatusInput);

			ObjectMapper mapper = new ObjectMapper();
			AttachmentSubmissionStatusInput attachmentSubmissionstatInputRequestInput = mapper
					.readValue(attachmentSubmissionStatusInputJsonString, AttachmentSubmissionStatusInput.class);
			System.out.println("elligiblityRequestInput:" + attachmentSubmissionstatInputRequestInput);
			routcome = attmstatserv.accessattachmentSubmission(attachmentSubmissionstatInputRequestInput);
			if (routcome != null) {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				resultOutcomeJson = ow.writeValueAsString(routcome);
			}
			System.out.println("RESULT OUTCOME=>" + resultOutcomeJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultOutcomeJson;
	}

	@ApiOperation(value = "Access the Attchment Submission Status Check with Trace Id API")
	@PostMapping(path = "/accessattachmentSubmissionStatusTraceId", produces = "application/json")
	@ResponseBody
	public String attachmentstatuswithTrace(@RequestBody AttachmentstatTraceInput attachmentstatTraceInput,
			HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String resultOutcomeJson = "";
		ServiceOutcome<ResultAttachmentSubmissionStatusOutcome> routcome = new ServiceOutcome<ResultAttachmentSubmissionStatusOutcome>();
		try {
			ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String attachmentSubmissionStatusInputJsonString = writer
					.writeValueAsString(attachmentstatTraceInput);

			ObjectMapper mapper = new ObjectMapper();
			AttachmentstatTraceInput attachmentSubmissionstatInputRequestInput = mapper
					.readValue(attachmentSubmissionStatusInputJsonString, AttachmentstatTraceInput.class);
			System.out.println("attachment status with traceId:" + attachmentSubmissionstatInputRequestInput);
			routcome = attmstatserv.accessattachmentSubmissionwithTrace(attachmentSubmissionstatInputRequestInput);
			if (routcome != null) {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				resultOutcomeJson = ow.writeValueAsString(routcome);
			}
			System.out.println("RESULT OUTCOME=>" + resultOutcomeJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultOutcomeJson;
	}
}
