package com.sunknowledge.dme.rcm.service.impl.claimsubmissiondata;

import com.sunknowledge.dme.rcm.service.claimssubmissiondata.TokenGenerationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.application.utils.ApplicationConstants;
import com.sunknowledge.dme.rcm.domain.elligibility.TokenOutCome;
import com.sunknowledge.dme.rcm.domain.elligibility.TokenResponseOutput;
import reactor.core.publisher.Mono;

@Service
public class TokenGenerationServiceImpl implements TokenGenerationService {
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

    public String getCoverageToken(){
        String token = "";
        try {

            HttpHeaders headers = new HttpHeaders();

            String url = ApplicationConstants.AVAILITY_TOKEN_GENERATION_URL;
            String client_id = ApplicationConstants.AVAILITY_CLIENT_ID;
            String client_secret = ApplicationConstants.AVAILITY_CLIENT_SECRET;
            String grantType = ApplicationConstants.AVAILITY_GRANT_TYPE;
            String scope = ApplicationConstants.AVAILITY_SCOPE;

            String requestBody = "grant_type=" + grantType + "&client_id=" + client_id + "&client_secret="
                + client_secret + "&scope=" + scope;

            headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            ObjectMapper mapper = new ObjectMapper();
            TokenResponseOutput tokenResponseOutput = mapper.readValue(responseEntity.getBody(),
                TokenResponseOutput.class);
            token = tokenResponseOutput.getAccessToken();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
    public Mono<TokenOutCome> getTokenMono() {
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
        return Mono.just(routcome);
    }
}
