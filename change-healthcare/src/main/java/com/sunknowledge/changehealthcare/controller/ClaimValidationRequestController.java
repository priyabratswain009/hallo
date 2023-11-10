package com.sunknowledge.changehealthcare.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.changehealthcare.application.utils.ApplicationConstants;
import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ProfessionalClaimSubmission;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ProfessionalClaimValidation;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ResultProfessionalClaimOutcome;
import com.sunknowledge.changehealthcare.service.ChangeStatusRequestService;
import com.sunknowledge.changehealthcare.validation.ValidationUtilities;

import io.swagger.annotations.ApiOperation;

/**
 * @author Bimal K Sahoo
 * @since 10/05/2022
 *
 */
@RestController
@RequestMapping("/changehealthcare")
public class ClaimValidationRequestController {
	private static final Logger log = LoggerFactory.getLogger(ClaimValidationRequestController.class);
	
	@Autowired
	private ChangeStatusRequestService changeStatusRequestService;
	
	@ApiOperation(value = "Access the Professional Claims(Submission) API")
	@PostMapping(path = "/accessProfessionalClaimsSubmission", produces = "application/json")
	@ResponseBody
	public String accessProfessionalClaimSubmission(@RequestBody ProfessionalClaimSubmission professionalClaimSubmission, HttpServletRequest request) {
		log.info("==========================POST for Access the Professional Claims(Submission) APIs==========================");
		String resultOutcomeJson = "";
		ServiceOutcome<ResultProfessionalClaimOutcome> routcome = new ServiceOutcome<ResultProfessionalClaimOutcome>();
		try {
			//String professionJsonString = ApplicationConstants.PROFESSIONAL_SUBMISSION_JSON_STRING;
			ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String professionJsonString = writer.writeValueAsString(professionalClaimSubmission);
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
			ProfessionalClaimSubmission professionalClaims = mapper.readValue(professionJsonString, ProfessionalClaimSubmission.class);
			System.out.println("professionalClaims:"+professionalClaims);
			ServiceOutcome<ProfessionalClaimSubmission> validationOutput = ValidationUtilities.claimsSubmission(professionalClaims);
			if(validationOutput.getOutcome()) {
				routcome = changeStatusRequestService.accessProfessionalClaimsSubmission(professionalClaims);
				if(routcome != null) {
					ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					resultOutcomeJson = ow.writeValueAsString(routcome);
				}
			}
			else {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				resultOutcomeJson = ow.writeValueAsString(validationOutput);
			}
				
				
//			routcome = changeStatusRequestService.accessProfessionalClaimsSubmission(professionalClaims);
//			if(routcome != null) {
//				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//				resultOutcomeJson = ow.writeValueAsString(routcome);
//			}
			System.out.println("RESULT OUTCOME=>"+resultOutcomeJson);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return resultOutcomeJson;
	}
	
	@ApiOperation(value = "Access the Professional Claims(Validation) API")
	@PostMapping(path = "/accessProfessionalClaimsValidation", produces = "application/json")
	@ResponseBody
	public String accessProfessionalClaims() {
		log.info("=======================POST for Access the Professional Claims(Validation) APIs==========================");
		String resultOutcomeJson = "";
		ServiceOutcome<ResultProfessionalClaimOutcome> routcome = new ServiceOutcome<ResultProfessionalClaimOutcome>();
		try {
			String professionJsonString = ApplicationConstants.PROFESSIONAL_VALIDATION_JSON_STRING;
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
			ProfessionalClaimValidation professionalClaims = mapper.readValue(professionJsonString, ProfessionalClaimValidation.class);
			System.out.println("professionalClaims:"+professionalClaims);
			ServiceOutcome<ProfessionalClaimValidation> validationOutput = ValidationUtilities.claimsValidation(professionalClaims);
			if(validationOutput.getOutcome()) {
				routcome = changeStatusRequestService.accessProfessionalClaimsValidation(professionalClaims);
				if(routcome != null) {
					ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					resultOutcomeJson = ow.writeValueAsString(routcome);
				}
			}
			else {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				resultOutcomeJson = ow.writeValueAsString(validationOutput);
			}
			System.out.println("RESULT OUTCOME=>"+resultOutcomeJson);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return resultOutcomeJson;
	}
}
