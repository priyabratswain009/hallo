package com.sunknowledge.changehealthcare.service;

import java.util.Objects;

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
import com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus.AttachmentSubmissionStatusInput;
import com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus.AttachmentSubmissionStatusOutput;
import com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus.AttachmentSubmissionStatusOutputTrace;
import com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus.AttachmentstatTraceInput;
import com.sunknowledge.changehealthcare.pojo.attachmentSubmissionStatus.ResultAttachmentSubmissionStatusOutcome;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.AccessTokenError;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.HealthCheckResponse;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ProfessionalClaimsErrorHandler;

@Service
public class AttachmentSubmissionStatusServiceImpl implements AttachmentSubmissionStatusService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private TokenGenerationServiceImpl tokenGenerationServiceImpl;

	@Override
	public ServiceOutcome<ResultAttachmentSubmissionStatusOutcome> accessattachmentSubmission(
			AttachmentSubmissionStatusInput attachmentSubmissionStatusInput) {
		ServiceOutcome<ResultAttachmentSubmissionStatusOutcome> routcome = new ServiceOutcome<ResultAttachmentSubmissionStatusOutcome>();
		ResultAttachmentSubmissionStatusOutcome claimOutcome = new ResultAttachmentSubmissionStatusOutcome();
		TokenOutCome objTokenOutCome = new TokenOutCome();
		try {
			objTokenOutCome = tokenGenerationServiceImpl.getToken();
			String token = objTokenOutCome.getTokenResponseOutput().getAccessToken();
			System.out.println("TOKEN=>" + token);
			if (attachmentSubmissionStatusHealthCheck(token).getOutcome()) {
				routcome = attachmentSubmissionmetadata(token, attachmentSubmissionStatusInput);
			} else {
				claimOutcome.setAccessTokenError(null);
				claimOutcome.setAttachmentSubmissionOutput(null);
				claimOutcome.setHealthCheckResponse(null);
				routcome.setData(claimOutcome);
				routcome.setMessage("Unauthorized token access.");
				routcome.setOutcome(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}

	@Override
	public ServiceOutcome<ResultAttachmentSubmissionStatusOutcome> attachmentSubmissionmetadata(String token,
			AttachmentSubmissionStatusInput attachmentSubmissionStatusInput) {
		String inputEntity = "";
		AttachmentSubmissionStatusOutput[] attachmentSubmissionStatusOutput = null;
		String url = ApplicationConstants.ATTACHMENTSTAT_METADATA_URL;
		ServiceOutcome<ResultAttachmentSubmissionStatusOutcome> routcome = new ServiceOutcome<ResultAttachmentSubmissionStatusOutcome>();
		ResultAttachmentSubmissionStatusOutcome claimOutcome = new ResultAttachmentSubmissionStatusOutcome();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + token);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			inputEntity = ow.writeValueAsString(attachmentSubmissionStatusInput);

			HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(inputEntity, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, inputCriteriaEntity,
					String.class);
			System.out.println("2.response=>" + response.getBody());
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				attachmentSubmissionStatusOutput = mapper.readValue(response.getBody(),
						AttachmentSubmissionStatusOutput[].class);
				if (attachmentSubmissionStatusOutput.length > 0) {
					claimOutcome.setAttachmentSubmissionOutput(attachmentSubmissionStatusOutput);
					routcome.setData(claimOutcome);
					routcome.setMessage("Successfully get the data");
					routcome.setOutcome(true);
				} else {
					System.out.println("==================FAILURE RESPONSE========================");
				}
			} else {
				System.out.println("====================FAILURE====================");
				routcome.setData(null);
				routcome.setMessage("Host Unreachable");
				routcome.setOutcome(false);
			}
		} catch (HttpStatusCodeException e) {
			ResponseEntity<String> responseException = ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getResponseBodyAsString());
			if (responseException.getStatusCodeValue() == 400) {
				try {
					ObjectMapper mapper = new ObjectMapper();
					String errorResponse = responseException.getBody();
					System.out.println("ERROR RESPONSE-1:" + errorResponse);
					ProfessionalClaimsErrorHandler errorHandler = mapper.readValue(errorResponse,
							ProfessionalClaimsErrorHandler.class);

					routcome.setData(claimOutcome);
					routcome.setMessage("Bad request");
					routcome.setOutcome(false);
				} catch (Exception a) {
					a.printStackTrace();
				}
			} else {
				routcome.setData(null);
				routcome.setMessage("Host Unreachable");
				routcome.setOutcome(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}

	@Override
	public ServiceOutcome<ResultAttachmentSubmissionStatusOutcome> attachmentSubmissionStatusHealthCheck(String token) {
		boolean response = false;
		ServiceOutcome<ResultAttachmentSubmissionStatusOutcome> routcome = new ServiceOutcome<ResultAttachmentSubmissionStatusOutcome>();
		ResultAttachmentSubmissionStatusOutcome claimOutcome = new ResultAttachmentSubmissionStatusOutcome();
		try {
			String url = ApplicationConstants.ATTACHMENTSTAT_HEALTHCHECK_URL;
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + token);
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					String.class);
			System.out.println("HealthCheck Response==> " + responseEntity);
			if (responseEntity.getStatusCode().name().equalsIgnoreCase("OK")) {
				ObjectMapper mapper = new ObjectMapper();
				HealthCheckResponse healthCheckResponse = mapper.readValue(responseEntity.getBody(),
						HealthCheckResponse.class);
				if (healthCheckResponse.getStatus().equalsIgnoreCase("Healthy")) {
					response = true;
					claimOutcome.setHealthCheckResponse(healthCheckResponse);
					routcome.setData(claimOutcome);
					routcome.setMessage("Professional health check! OK.");
					routcome.setOutcome(response);
				} else {
					response = false;
					claimOutcome.setHealthCheckResponse(healthCheckResponse);
					routcome.setData(claimOutcome);
					routcome.setMessage("Professional health check! NOT OK.");
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

	@Override
	public ServiceOutcome<ResultAttachmentSubmissionStatusOutcome> accessattachmentSubmissionwithTrace(
			AttachmentstatTraceInput attachmentstatTraceInput) {
		ServiceOutcome<ResultAttachmentSubmissionStatusOutcome> routcome = new ServiceOutcome<ResultAttachmentSubmissionStatusOutcome>();
		ResultAttachmentSubmissionStatusOutcome claimOutcome = new ResultAttachmentSubmissionStatusOutcome();
		TokenOutCome objTokenOutCome = new TokenOutCome();
		try {
			objTokenOutCome = tokenGenerationServiceImpl.getToken();
			String token = objTokenOutCome.getTokenResponseOutput().getAccessToken();
			System.out.println("TOKEN=>" + token);
			if (attachmentSubmissionStatusHealthCheck(token).getOutcome()) {
				routcome = attachmentSubmissionwithTrace(token, attachmentstatTraceInput);
			} else {
				claimOutcome.setAccessTokenError(null);
				claimOutcome.setAttachmentSubmissionOutput(null);
				claimOutcome.setHealthCheckResponse(null);
				routcome.setData(claimOutcome);
				routcome.setMessage("Unauthorized token access.");
				routcome.setOutcome(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}

	@Override
	public ServiceOutcome<ResultAttachmentSubmissionStatusOutcome> attachmentSubmissionwithTrace(String token,
			AttachmentstatTraceInput attachmentstatTraceInput) {
		AttachmentSubmissionStatusOutputTrace attachmentSubmissionStatusOutputTrace = null;
		String url = ApplicationConstants.ATTACHMENTSTAT_TRACE_URL;
		url = url + attachmentstatTraceInput.getTraceid() + "?fieldset=" + attachmentstatTraceInput.getFieldset();
		ServiceOutcome<ResultAttachmentSubmissionStatusOutcome> routcome = new ServiceOutcome<ResultAttachmentSubmissionStatusOutcome>();
		ResultAttachmentSubmissionStatusOutcome claimOutcome = new ResultAttachmentSubmissionStatusOutcome();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + token);
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
			System.out.println("2.response=>" + response.getBody());
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				attachmentSubmissionStatusOutputTrace = mapper.readValue(response.getBody(),
						AttachmentSubmissionStatusOutputTrace.class);
				if (!Objects.isNull(attachmentSubmissionStatusOutputTrace.getStatus())) {
					claimOutcome.setAttachmentSubmissionStatusOutputTrace(attachmentSubmissionStatusOutputTrace);
					routcome.setData(claimOutcome);
					routcome.setMessage("Successfully get the data");
					routcome.setOutcome(true);
				} else {
					System.out.println("==================FAILURE RESPONSE========================");
				}
			} else {
				System.out.println("====================FAILURE====================");
				routcome.setData(null);
				routcome.setMessage("Host Unreachable");
				routcome.setOutcome(false);
			}
		} catch (HttpStatusCodeException e) {
			ResponseEntity<String> responseException = ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getResponseBodyAsString());
			if (responseException.getStatusCodeValue() == 400) {
				try {
					ObjectMapper mapper = new ObjectMapper();
					String errorResponse = responseException.getBody();
					System.out.println("ERROR RESPONSE-1:" + errorResponse);
					ProfessionalClaimsErrorHandler errorHandler = mapper.readValue(errorResponse,
							ProfessionalClaimsErrorHandler.class);

					routcome.setData(claimOutcome);
					routcome.setMessage("Bad request");
					routcome.setOutcome(false);
				} catch (Exception a) {
					a.printStackTrace();
				}
			} else {
				routcome.setData(null);
				routcome.setMessage("Host Unreachable");
				routcome.setOutcome(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}
}
