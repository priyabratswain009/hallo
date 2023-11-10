package com.sunknowledge.changehealthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.changehealthcare.application.utils.ApplicationConstants;
import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.pojo.claimStatus.ClaimStatusRequestInput;
import com.sunknowledge.changehealthcare.pojo.claimStatus.ResultClaimStatusOutcome;
import com.sunknowledge.changehealthcare.service.ClaimStatusService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/changehealthcare")
public class ClaimStatusController {
	@Autowired
	private ClaimStatusService clmserv;

	@ApiOperation(value = "Access the Claim Status API")
	@PostMapping(path = "/accessclaimStatus", produces = "application/json")
	@ResponseBody
	public String medicalClaimStatus() throws JsonMappingException, JsonProcessingException {

		String resultOutcomeJson = "";
		ServiceOutcome<ResultClaimStatusOutcome> routcome = new ServiceOutcome<ResultClaimStatusOutcome>();
		try {
			String claimstatusJsonString = ApplicationConstants.CLAIMSTATUS_JSON_STRING;
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
			ClaimStatusRequestInput claimStatusRequestInput = mapper.readValue(claimstatusJsonString,
					ClaimStatusRequestInput.class);
			System.out.println("elligiblityRequestInput:" + claimStatusRequestInput);
			routcome = clmserv.claimstatus(claimStatusRequestInput);
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
