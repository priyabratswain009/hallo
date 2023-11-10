package com.sunknowledge.changehealthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.changehealthcare.application.utils.ApplicationConstants;
import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.core.TokenOutCome;
import com.sunknowledge.changehealthcare.pojo.elligibility.ElligiblityRequestInput;
import com.sunknowledge.changehealthcare.pojo.elligibility.ElligiblityResponseOutput;
import com.sunknowledge.changehealthcare.pojo.elligibility.HealthCheckOutcome;
import com.sunknowledge.changehealthcare.pojo.elligibility.ResultElligibilityOutcome;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.AccessTokenError;
import com.sunknowledge.changehealthcare.validation.ElligibilityComponentValidation;

@Service
public class ElligibilityServiceImpl implements ElligibilityService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private TokenGenerationServiceImpl tokenGenerationServiceImpl;

	// Elligibility Check main
	public ServiceOutcome<ResultElligibilityOutcome> medicalEligibiltycheck(
			ElligiblityRequestInput elligiblityRequestInput) {
		ServiceOutcome<ResultElligibilityOutcome> routcome = new ServiceOutcome<ResultElligibilityOutcome>();
		TokenOutCome objTokenOutCome = new TokenOutCome();
		ResultElligibilityOutcome claimOutcome = new ResultElligibilityOutcome();
		try {
			ServiceOutcome<ElligiblityRequestInput> validatedresult = ElligibilityComponentValidation
					.validateUserInputs(elligiblityRequestInput);
			if (validatedresult.getOutcome()) {
				objTokenOutCome = tokenGenerationServiceImpl.getToken();
				String token = objTokenOutCome.getTokenResponseOutput().getAccessToken();
				if (eligibiltyHealthcheck(token).getOutcome()) {
					routcome = elligiblityCheck(token, elligiblityRequestInput);
				} else {
					claimOutcome.setAccessTokenError(null);
					claimOutcome.setHealthCheckOutcome(null);
					claimOutcome.setErrorhandler(null);
					claimOutcome.setElligiblityResponseOutput(null);
					routcome.setData(claimOutcome);
					routcome.setMessage("Unauthorized token access.");
					routcome.setOutcome(false);
				}
			} else {
				claimOutcome.setAccessTokenError(null);
				claimOutcome.setHealthCheckOutcome(null);
				claimOutcome.setErrorhandler(null);
				claimOutcome.setElligiblityResponseOutput(null);
				routcome.setData(claimOutcome);
				routcome.setMessage(validatedresult.getMessage());
				routcome.setOutcome(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}

	// Elligibility Health Check
	public ServiceOutcome<ResultElligibilityOutcome> eligibiltyHealthcheck(String token) {
		boolean response = false;
		ServiceOutcome<ResultElligibilityOutcome> routcome = new ServiceOutcome<ResultElligibilityOutcome>();
		ResultElligibilityOutcome claimOutcome = new ResultElligibilityOutcome();
		try {
			String URL = ApplicationConstants.ELLIGIBILITY_HEALTHCHECK_URL;
			HttpHeaders header = new HttpHeaders();
			token = "Bearer " + token;

			header.add("Content-Type", "application/json");
			header.add("Accept", "application/json");
			header.add("Authorization", token);

			HttpEntity<String> request = new HttpEntity<String>(null, header);
			ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, request, String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				HealthCheckOutcome healthCheckOutcome = mapper.readValue(responseEntity.getBody(),
						HealthCheckOutcome.class);
				if (healthCheckOutcome.getVersion().equalsIgnoreCase("v3")
						&& healthCheckOutcome.getStatus().equalsIgnoreCase("ok")) {
					response = true;
					claimOutcome.setHealthCheckOutcome(healthCheckOutcome);
					routcome.setData(claimOutcome);
					routcome.setMessage("Professional health check! OK.");
					routcome.setOutcome(response);
				} else {
					System.out.println("Improper Response");
				}
			} else {
				System.out.println("Failed");
			}
		} catch (HttpStatusCodeException hsce) {
			ResponseEntity<String> responseException = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(hsce.getResponseBodyAsString());
			try {
				ObjectMapper mapper = new ObjectMapper();
				String errorResponse = responseException.getBody();
				AccessTokenError accessTokenError = mapper.readValue(errorResponse, AccessTokenError.class);
				claimOutcome.setAccessTokenError(accessTokenError);
				routcome.setData(claimOutcome);
				routcome.setMessage("Unauthorized token access.");
				routcome.setOutcome(response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return routcome;
	}

	// Elligibility Check
	public ServiceOutcome<ResultElligibilityOutcome> elligiblityCheck(String accessToken,
			ElligiblityRequestInput elligiblityRequestInput) {

		String inputEntity = "";
		ElligiblityResponseOutput elligiblityResponseOutput = null;
		String url = ApplicationConstants.ELLIGIBILITY_URL;
		ServiceOutcome<ResultElligibilityOutcome> routcome = new ServiceOutcome<ResultElligibilityOutcome>();
		ResultElligibilityOutcome claimOutcome = new ResultElligibilityOutcome();

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + accessToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			inputEntity = ow.writeValueAsString(elligiblityRequestInput);

			HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(inputEntity, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, inputCriteriaEntity,
					String.class);
			System.out.println("2.response=>" + response);
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				elligiblityResponseOutput = mapper.readValue(response.getBody(), ElligiblityResponseOutput.class);

				ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				claimOutcome.setElligiblityResponseOutput(elligiblityResponseOutput);
				routcome.setData(claimOutcome);
				routcome.setMessage("Successfully get the data");
				routcome.setOutcome(true);

			} else {
				System.out.println("Failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return routcome;
	}

}
