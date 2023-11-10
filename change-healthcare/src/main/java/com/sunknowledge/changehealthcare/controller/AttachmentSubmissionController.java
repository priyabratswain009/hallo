package com.sunknowledge.changehealthcare.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.pojo.attachmentsubmission.AttachmentSubmissionInput;
import com.sunknowledge.changehealthcare.pojo.attachmentsubmission.ResultAttachmentSubmissionOutcome;
import com.sunknowledge.changehealthcare.pojo.attachmentSubmission.AttachmentSubmissionInput;
import com.sunknowledge.changehealthcare.pojo.attachmentSubmission.ResultAttachmentSubmissionOutcome;
import com.sunknowledge.changehealthcare.service.AttachmentSubmissionService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Bimal K Sahoo
 * @since 16/08/2022
 *
 */

@RestController
@RequestMapping("/changehealthcare")
public class AttachmentSubmissionController {
	@Autowired

	private AttachmentSubmissionService attachmentSubmissionService;

	@ApiOperation(value = "Medical Network Attchment Submission V1 API")
	@PostMapping(path = "/medicalNetworkAttachmentSubmission", produces = "application/json")
	@ResponseBody
	public String medicalNetworkAttachmentSubmission(@RequestParam("jsonInputRequest") String attachmentJsonString, @RequestParam("file") MultipartFile documentFile, @RequestParam("file1") MultipartFile documentFile1, HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String resultOutcomeJson = "";
		ServiceOutcome<ResultAttachmentSubmissionOutcome> routcome = new ServiceOutcome<ResultAttachmentSubmissionOutcome>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
			
			AttachmentSubmissionInput attachmentSubmissionInputRequestInput = mapper.readValue(attachmentJsonString, AttachmentSubmissionInput.class);
			System.out.println("elligiblityRequestInput:" + attachmentSubmissionInputRequestInput);
			
			List<MultipartFile> multipartFileList = new ArrayList<MultipartFile>();
			multipartFileList.add(documentFile1);
			multipartFileList.add(documentFile);
			
			routcome = attachmentSubmissionService.validateFileAndAttachmentSubmission(routcome, multipartFileList, attachmentSubmissionInputRequestInput);
			if(routcome != null) {
	private AttachmentSubmissionService attmserv;

	@ApiOperation(value = "Access the Attchment Submission Check API")
	@PostMapping(path = "/accessattachmentSubmission", produces = "application/json")
	@ResponseBody
	public String medicalelligibility(@RequestBody AttachmentSubmissionInput AttachmentSubmissionInput, HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String resultOutcomeJson = "";
		ServiceOutcome<ResultAttachmentSubmissionOutcome> routcome = new ServiceOutcome<ResultAttachmentSubmissionOutcome>();
		try {
			//String elligibilityJsonString = ApplicationConstants.ELLIGIBILITY_JSON_STRING;
			ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String attachmentSubmissionInputJsonString = writer.writeValueAsString(AttachmentSubmissionInput);

			ObjectMapper mapper = new ObjectMapper();
			AttachmentSubmissionInput attachmentSubmissionInputRequestInput = mapper.readValue(attachmentSubmissionInputJsonString,
					AttachmentSubmissionInput.class);
			System.out.println("elligiblityRequestInput:" + attachmentSubmissionInputRequestInput);
			routcome = attmserv.accessattachmentSubmission(attachmentSubmissionInputRequestInput);
			if (routcome != null) {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				resultOutcomeJson = ow.writeValueAsString(routcome);
			}
			System.out.println("RESULT OUTCOME=>" + resultOutcomeJson);
		} 
		catch (Exception e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultOutcomeJson;
	}
}
