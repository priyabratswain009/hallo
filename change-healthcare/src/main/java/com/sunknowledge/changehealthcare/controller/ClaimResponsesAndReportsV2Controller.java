package com.sunknowledge.changehealthcare.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.pojo.claimreports.ActualClaimResponseReport;
import com.sunknowledge.changehealthcare.service.ClaimResponsesAndReportsV2Service;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Bimal K Sahoo
 * @since 26/05/2022
 *
 */

@RestController
@RequestMapping("/claimresponsereportv2")
@Slf4j
public class ClaimResponsesAndReportsV2Controller {
	@Autowired
	private ClaimResponsesAndReportsV2Service claimResponsesAndReportsV2Service;
	
	@ApiOperation(value = "Get Medical Network Claims Responses and Reports V2 API(Change Healthcare)")
	@GetMapping(path = "/getClaimsResponsesReports", produces = "application/json")
	@ResponseBody
	public String getActualClaimResponsesReports() {
		log.info("=======================POST(CONTROLLER) Get Medical Network Claims Responses and Reports V2 API(Change Healthcare)==========================");
		String resultOutcomeJson = "";
		try {
			ServiceOutcome<ArrayList<ActualClaimResponseReport>> routcome = claimResponsesAndReportsV2Service.getActualClaimResponsesReports();
			if(routcome != null) {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				resultOutcomeJson = ow.writeValueAsString(routcome);
			}
			System.out.println("RESULT OUTCOME=>"+resultOutcomeJson);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return resultOutcomeJson;
	}
}
