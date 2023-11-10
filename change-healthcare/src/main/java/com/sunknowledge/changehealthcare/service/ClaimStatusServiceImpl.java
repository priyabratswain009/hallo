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
import com.sunknowledge.changehealthcare.pojo.claimStatus.ClaimStatusRequestInput;
import com.sunknowledge.changehealthcare.pojo.claimStatus.ClaimStatusRequestOutput;
import com.sunknowledge.changehealthcare.pojo.claimStatus.ResultClaimStatusOutcome;
import com.sunknowledge.changehealthcare.pojo.elligibility.HealthCheckOutcome;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.AccessTokenError;
import com.sunknowledge.changehealthcare.validation.ValidationUtilities;

@Service
public class ClaimStatusServiceImpl implements ClaimStatusService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private TokenGenerationServiceImpl tokenGenerationServiceImpl;

	// Claim Status Check main
	public ServiceOutcome<ResultClaimStatusOutcome> claimstatus(ClaimStatusRequestInput claimStatusRequestInput) {
		ServiceOutcome<ResultClaimStatusOutcome> routcome = new ServiceOutcome<ResultClaimStatusOutcome>();
		ResultClaimStatusOutcome claimOutcome = new ResultClaimStatusOutcome();
		TokenOutCome objTokenOutCome = new TokenOutCome();

		try {
			ServiceOutcome<ClaimStatusRequestInput> validatedresult = ValidationUtilities
					.validateUserInputs(claimStatusRequestInput);
			if (validatedresult.getOutcome()) {
				objTokenOutCome = tokenGenerationServiceImpl.getToken();
				String token = objTokenOutCome.getTokenResponseOutput().getAccessToken();
				if (claimstatusHealthcheck(token).getOutcome()) {
					routcome = claimstatusCheck(token, claimStatusRequestInput);

				} else {
					claimOutcome.setAccessTokenError(null);
					claimOutcome.setErrorhandler(null);
					claimOutcome.setClaimStatusRequestOutput(null);
					routcome.setData(claimOutcome);
					routcome.setMessage("Unauthorized token access.");
					routcome.setOutcome(false);
				}

			} else {
				claimOutcome.setAccessTokenError(null);
				claimOutcome.setErrorhandler(null);
				claimOutcome.setClaimStatusRequestOutput(null);
				routcome.setData(claimOutcome);
				routcome.setMessage(validatedresult.getMessage());
				routcome.setOutcome(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return routcome;

	}

	// Claim Status Health Check
	public ServiceOutcome<ResultClaimStatusOutcome> claimstatusHealthcheck(String accessToken) {
		boolean response = false;
		ServiceOutcome<ResultClaimStatusOutcome> routcome = new ServiceOutcome<ResultClaimStatusOutcome>();
		ResultClaimStatusOutcome claimOutcome = new ResultClaimStatusOutcome();
		try {
			String url = ApplicationConstants.CLAIMSTATUS_HEALTHCHECK_URL;
			HttpHeaders headers = new HttpHeaders();
			
			headers.add("Content-Type", "application/json");
			headers.set("Authorization", "Bearer " + accessToken);
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				HealthCheckOutcome healthCheckResponse = mapper.readValue(responseEntity.getBody(),
						HealthCheckOutcome.class);
				if (healthCheckResponse.getVersion().equalsIgnoreCase("v2")
						&& healthCheckResponse.getStatus().equalsIgnoreCase("ok")) {
					response = true;
					claimOutcome.setHealthCheckOutcome(healthCheckResponse);
					routcome.setData(claimOutcome);
					routcome.setMessage("ClaimStatus health check! OK.");
					routcome.setOutcome(response);
				} else {
					response = false;
					claimOutcome.setHealthCheckOutcome(healthCheckResponse);
					routcome.setData(claimOutcome);
					routcome.setMessage("ClaimStatus health check! NOT OK.");
					routcome.setOutcome(response);
				}
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

	// Claim Status Check
	public ServiceOutcome<ResultClaimStatusOutcome> claimstatusCheck(String accessToken,
			ClaimStatusRequestInput claimStatusRequestInput) {

		String inputEntity = "";
		ClaimStatusRequestOutput claimStatusRequestOutput = null;
		String url = ApplicationConstants.CLAIMSTATUS_URL;
		ServiceOutcome<ResultClaimStatusOutcome> routcome = new ServiceOutcome<ResultClaimStatusOutcome>();
		ResultClaimStatusOutcome claimOutcome = new ResultClaimStatusOutcome();

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "*/*");
			headers.set("Authorization", "Bearer " + accessToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			inputEntity = ow.writeValueAsString(claimStatusRequestInput);

			HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(inputEntity, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, inputCriteriaEntity,
					String.class);
			System.out.println("2.response=>" + response);
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				claimStatusRequestOutput = mapper.readValue(response.getBody(), ClaimStatusRequestOutput.class);

				ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				claimOutcome.setClaimStatusRequestOutput(claimStatusRequestOutput);
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
