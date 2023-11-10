package com.sunknowledge.dme.rcm.service.availity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.config.appconfiguration.ApplicationConstants;
import com.sunknowledge.dme.rcm.domain.token.TokenResponseOutput;

@Service
public class TokenGenerationServiceImpl implements TokenGenerationService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String getToken() {
		String token = "";
		try {

			HttpHeaders headers = new HttpHeaders();

			String url = ApplicationConstants.TOKEN_GENERATION_URL;
			String client_id = ApplicationConstants.CLIENT_ID;
			String client_secret = ApplicationConstants.CLIENT_SECRET;
			String grantType = ApplicationConstants.GRANT_TYPE;
			String scope = ApplicationConstants.SCOPE;

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

}
