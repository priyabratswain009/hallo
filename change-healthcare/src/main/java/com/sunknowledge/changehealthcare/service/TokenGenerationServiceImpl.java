package com.sunknowledge.changehealthcare.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.changehealthcare.application.utils.ApplicationConstants;
import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.core.TokenOutCome;
import com.sunknowledge.changehealthcare.pojo.TokenResponseOutput;
import com.sunknowledge.changehealthcare.pojo.claimStatus.ResultClaimStatusOutcome;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ResultProfessionalClaimOutcome;

@Service
public class TokenGenerationServiceImpl {
	@Autowired
	private RestTemplate restTemplate;

	public TokenOutCome getToken() {
		boolean response = false;
		TokenOutCome routcome = new TokenOutCome();
		try {
			HttpHeaders headers = new HttpHeaders();
			JSONObject jo = new JSONObject();

			String url = ApplicationConstants.TOKEN_GENERATION_URL;
			headers.add("Content-Type", "application/json");

			headers.add("Accept", "*/*");
			jo.put("client_id", ApplicationConstants.CLIENT_ID);
			jo.put("client_secret", ApplicationConstants.CLIENT_SECRET);
			jo.put("grant_type", "client_credentials");
			HttpEntity<String> request = new HttpEntity<String>(jo.toString(), headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				TokenResponseOutput tokenResponseOutput = mapper.readValue(responseEntity.getBody(),
						TokenResponseOutput.class);
				if (tokenResponseOutput.getAccessToken() != null) {
					response = true;
					routcome.setTokenResponseOutput(tokenResponseOutput);
					routcome.setMessage("Token Generation check! OK.");
					routcome.setOutcome(response);
				} else {
					System.out.println("Improper Response");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}
}
