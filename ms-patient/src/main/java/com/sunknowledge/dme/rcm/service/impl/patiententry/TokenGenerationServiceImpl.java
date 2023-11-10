package com.sunknowledge.dme.rcm.service.impl.patiententry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.service.dto.patientelligibility.TokenOutCome;
import com.sunknowledge.dme.rcm.service.dto.patientelligibility.TokenResponseOutput;
import com.sunknowledge.dme.rcm.utils.application.ApplicationConstant;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@Transactional
@Service
public class TokenGenerationServiceImpl {
	@Autowired
	private RestTemplate restTemplate;

	public String getToken() {
		boolean response = false;
		TokenOutCome routcome = new TokenOutCome();
		try {
			HttpHeaders headers = new HttpHeaders();
			JSONObject jo = new JSONObject();

			String url = ApplicationConstant.TOKEN_GENERATION_URL;
			headers.add("Content-Type", "application/json");

			headers.add("Accept", "*/*");
			jo.put("client_id", ApplicationConstant.CLIENT_ID);
			jo.put("client_secret", ApplicationConstant.CLIENT_SECRET);
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
		return routcome.getTokenResponseOutput().getAccessToken();
	}

    public Mono<TokenOutCome> getTokenMono() {
        boolean response = false;
        TokenOutCome routcome = new TokenOutCome();
        try {
            HttpHeaders headers = new HttpHeaders();
            JSONObject jo = new JSONObject();

            String url = ApplicationConstant.TOKEN_GENERATION_URL;
            headers.add("Content-Type", "application/json");

            headers.add("Accept", "*/*");
            jo.put("client_id", ApplicationConstant.CLIENT_ID);
            jo.put("client_secret", ApplicationConstant.CLIENT_SECRET);
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
        return Mono.just(routcome);
    }
}
