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
import com.sunknowledge.changehealthcare.pojo.elligibility.ElligiblityRequestInput;
import com.sunknowledge.changehealthcare.pojo.elligibility.ResultElligibilityOutcome;
import com.sunknowledge.changehealthcare.service.ElligibilityServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/changehealthcare")
public class ElligibilityController {
	@Autowired
	private ElligibilityServiceImpl empserv;

	@ApiOperation(value = "Access the Elligibility Check API")
	@PostMapping(path = "/accessElligibilityCheck", produces = "application/json")
	@ResponseBody
	public String medicalelligibility(@RequestBody ElligiblityRequestInput ElligiblityRequestInput, HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String resultOutcomeJson = "";
		ServiceOutcome<ResultElligibilityOutcome> routcome = new ServiceOutcome<ResultElligibilityOutcome>();
		try {
			//String elligibilityJsonString = ApplicationConstants.ELLIGIBILITY_JSON_STRING;
			ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String elligibilityJsonString = writer.writeValueAsString(ElligiblityRequestInput);

			ObjectMapper mapper = new ObjectMapper();
			ElligiblityRequestInput elligiblityRequestInput = mapper.readValue(elligibilityJsonString,
					ElligiblityRequestInput.class);
			System.out.println("elligiblityRequestInput:" + elligiblityRequestInput);
			routcome = empserv.medicalEligibiltycheck(elligiblityRequestInput);
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
