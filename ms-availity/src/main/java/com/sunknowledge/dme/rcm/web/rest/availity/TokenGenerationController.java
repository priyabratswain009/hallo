package com.sunknowledge.dme.rcm.web.rest.availity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.service.availity.TokenGenerationService;

@RestController
@RequestMapping("/api")
public class TokenGenerationController {

	@Autowired
	TokenGenerationService tokenGenerationService;
	
	@PostMapping("/getToken")
	public String primaryClaimSubmission() {
		
		String resultOutcomeJson = "";
		try {
			resultOutcomeJson=tokenGenerationService.getToken();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultOutcomeJson;
	}
}
