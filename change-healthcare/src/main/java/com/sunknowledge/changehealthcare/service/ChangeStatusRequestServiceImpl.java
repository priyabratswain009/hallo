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
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.changehealthcare.application.utils.ApplicationConstants;
import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.core.TokenOutCome;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.AccessTokenError;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ClaimSubmissionOutput;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ClaimValidationOutput;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.HealthCheckResponse;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ProfessionalClaimSubmission;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ProfessionalClaimValidation;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ProfessionalClaimsErrorHandler;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ResultProfessionalClaimOutcome;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author Bimal K Sahoo
 * @since 10/05/2022
 *
 */
@Service
@Slf4j
public class ChangeStatusRequestServiceImpl implements ChangeStatusRequestService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient webClient;

	@Autowired
	private TokenGenerationServiceImpl tokenGenerationServiceImpl;

	@Override
	public ServiceOutcome<ResultProfessionalClaimOutcome> accessProfessionalClaimsSubmission(
			ProfessionalClaimSubmission professionalClaims) {
		ServiceOutcome<ResultProfessionalClaimOutcome> routcome = new ServiceOutcome<ResultProfessionalClaimOutcome>();
		ResultProfessionalClaimOutcome claimOutcome = new ResultProfessionalClaimOutcome();
		TokenOutCome objTokenOutCome = new TokenOutCome();
		try {
			objTokenOutCome = tokenGenerationServiceImpl.getToken();
			String token = objTokenOutCome.getTokenResponseOutput().getAccessToken();
			System.out.println("TOKEN=>" + token);
			if (professionalClaimHealthCheck(token).getOutcome()) {
				routcome = professionalClaimSubmission(token, professionalClaims);
			} else {
				claimOutcome.setAccessTokenError(null);
				claimOutcome.setClaimSubmissionOutput(null);
				claimOutcome.setHealthCheckResponse(null);
				claimOutcome.setProfessionalClaimsErrorHandler(null);
				claimOutcome.setClaimValidationOutput(null);
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
	public ServiceOutcome<ResultProfessionalClaimOutcome> professionalClaimSubmission(String accessToken,
			ProfessionalClaimSubmission professionalClaimSubmission) {
		String inputEntity = "";
		// String resultOutcomeJson = "";
		ClaimSubmissionOutput professionalClaimSubmissionOutcome = null;
		String url = ApplicationConstants.PROFESSIONALCLAIMS_SUBMISSION_URL;
		ServiceOutcome<ResultProfessionalClaimOutcome> routcome = new ServiceOutcome<ResultProfessionalClaimOutcome>();
		ResultProfessionalClaimOutcome claimOutcome = new ResultProfessionalClaimOutcome();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + accessToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			inputEntity = ow.writeValueAsString(professionalClaimSubmission);

			HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(inputEntity, headers);
			// professionalClaimSubmissionOutput = restTemplate.postForObject(url,
			// inputCriteriaEntity, String.class);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, inputCriteriaEntity,
					String.class);
			System.out.println("2.response=>" + response);
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				professionalClaimSubmissionOutcome = mapper.readValue(response.getBody(), ClaimSubmissionOutput.class);
				if (professionalClaimSubmissionOutcome.getStatus().equals("SUCCESS")) {
					System.out.println("==>" + professionalClaimSubmissionOutcome.getControlNumber());
					ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					// resultOutcomeJson =
					// ow.writeValueAsString(professionalClaimSubmissionOutcome);
					claimOutcome.setClaimSubmissionOutput(professionalClaimSubmissionOutcome);
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

					claimOutcome.setProfessionalClaimsErrorHandler(errorHandler);
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

//	@Override
//	public ServiceOutcome<ResultProfessionalClaimOutcome> professionalClaimSubmission(String accessToken, ProfessionalClaimSubmission professionalClaimSubmission){
//		String url = ApplicationConstants.PROFESSIONALCLAIMS_SUBMISSION_URL;
//		ServiceOutcome<ResultProfessionalClaimOutcome> routcome = new ServiceOutcome<ResultProfessionalClaimOutcome>();
//		ResultProfessionalClaimOutcome claimOutcome = new ResultProfessionalClaimOutcome();
//		ClaimSubmissionOutput professionalClaimSubmissionOutcome = null;
//		Mono<String> responseResult = null;
//		try {
//			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//			String inputEntity = ow.writeValueAsString(professionalClaimSubmission);
//			responseResult = webClient.post()
//					.uri(url)
//					.header("Content-Type","application/json")
//					.header("Accept","application/json")
//					.headers(h -> h.setBearerAuth(accessToken))
//					.body(Mono.just(inputEntity), String.class)
//					.retrieve()
//					.onStatus(
//							(HttpStatus::isError), (it -> handleError(it.statusCode().getReasonPhrase()))
//						)
//					.bodyToMono(String.class);
//			
//			System.out.println("2.response=>"+responseResult.block());
//			String webResponse = responseResult.block().toString();
//			System.out.println("RSULT:"+webResponse);
//			if(webResponse != null) {
//				ObjectMapper mapper = new ObjectMapper();
//				professionalClaimSubmissionOutcome = mapper.readValue(webResponse, ClaimSubmissionOutput.class);
//				if(professionalClaimSubmissionOutcome.getStatus().equals("SUCCESS")) {
//					System.out.println("==>"+professionalClaimSubmissionOutcome.getControlNumber());
//					ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//					claimOutcome.setClaimSubmissionOutput(professionalClaimSubmissionOutcome);
//					routcome.setData(claimOutcome);
//					routcome.setMessage("Successfully get the data");
//					routcome.setOutcome(true);
//				}
//				else {
//					System.out.println("==================FAILURE RESPONSE========================");
//				}
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		return routcome;
//	}

//	@Override
//	public ServiceOutcome<ResultProfessionalClaimOutcome> professionalClaimSubmission(String accessToken, ProfessionalClaimSubmission professionalClaimSubmission){
//		String url = ApplicationConstants.PROFESSIONALCLAIMS_SUBMISSION_URL;
//		ServiceOutcome<ResultProfessionalClaimOutcome> routcome = new ServiceOutcome<ResultProfessionalClaimOutcome>();
//		ResultProfessionalClaimOutcome claimOutcome = new ResultProfessionalClaimOutcome();
//		ClaimSubmissionOutput professionalClaimSubmissionOutcome = null;
//		String responseResult = null;
//		try {
//			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//			String inputEntity = ow.writeValueAsString(professionalClaimSubmission);
//			responseResult = webClient.post()
//					.uri(url)
//					.header("Content-Type","application/json")
//					.header("Accept","application/json")
//					.headers(h -> h.setBearerAuth(accessToken))
//					.body(Mono.just(inputEntity), String.class)
//					.retrieve()
////					.onStatus(
////							HttpStatus :: is4xxClientError, 
////							error -> Mono.error(new RuntimeException("================>API Not Found<=============="))
////							)
////					.onStatus(HttpStatus :: is4xxClientError, 
////							(
////								response -> response.bodyToMono(String.class).map(Exception :: new))
////							)
//					
////					.onStatus(HttpStatus::isError,
////	                        clientResponse -> clientResponse.bodyToMono(ServiceException.class)
////                            .flatMap(serviceException -> Mono.error(serviceException)))
//					
////					.onStatus(HttpStatus :: isError,
////							clientResponse -> clientResponse.bodyToMono(Exception.class)
////							.flatMap(serviceException -> Mono.error(serviceException))
////							)
//					//ProfessionalClaimsErrorHandler errorHandler = mapper.readValue(errorResponse, ProfessionalClaimsErrorHandler.class)
//					
//					.onStatus(HttpStatus::is4xxClientError, clientResponse -> {
//						Mono<String> errorMsg = clientResponse.bodyToMono(String.class);
//						return errorMsg.flatMap(msg -> {
//							System.out.println("Error Message=>"+errorMsg);
//							throw new RuntimeException(msg);
//						});
//					})
//					.bodyToMono(String.class)
//					.block();
//			
//			System.out.println("2.response=>"+responseResult);
//			if(responseResult != null) {
//				ObjectMapper mapper = new ObjectMapper();
//				professionalClaimSubmissionOutcome = mapper.readValue(responseResult, ClaimSubmissionOutput.class);
//				if(professionalClaimSubmissionOutcome.getStatus().equals("SUCCESS")) {
//					System.out.println("==>"+professionalClaimSubmissionOutcome.getControlNumber());
//					ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//					claimOutcome.setClaimSubmissionOutput(professionalClaimSubmissionOutcome);
//					routcome.setData(claimOutcome);
//					routcome.setMessage("Successfully get the data");
//					routcome.setOutcome(true);
//				}
//				else {
//					System.out.println("==================FAILURE RESPONSE========================");
//				}
//			}
//		}
//		catch(Exception e) {
//			HttpStatus s = new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCode();
//			System.out.println("===================>STATUS CODE:"+s.value());
//			if(s.value() == 400) {
//				System.out.println("==================>>>>>"+s.getReasonPhrase());
//			}
//			e.printStackTrace();
//		}
//		return routcome;
//	}

	private Mono<? extends Throwable> handleError(String message) {
		log.error("=============EXCEPTION OCCURED=>" + message);
		return Mono.error(Exception::new);
	}

	@Override
	public ServiceOutcome<ResultProfessionalClaimOutcome> accessProfessionalClaimsValidation(
			ProfessionalClaimValidation professionalClaims) {
		ServiceOutcome<ResultProfessionalClaimOutcome> routcome = new ServiceOutcome<ResultProfessionalClaimOutcome>();
		ResultProfessionalClaimOutcome claimOutcome = new ResultProfessionalClaimOutcome();
		TokenOutCome objTokenOutCome = new TokenOutCome();
		try {
			objTokenOutCome = tokenGenerationServiceImpl.getToken();
			String token = objTokenOutCome.getTokenResponseOutput().getAccessToken();
			System.out.println("TOKEN=>" + token);
			if (professionalClaimHealthCheck(token).getOutcome()) {
				routcome = professionalClaimValidation(token, professionalClaims);
			} else {
				claimOutcome.setAccessTokenError(null);
				claimOutcome.setClaimSubmissionOutput(null);
				claimOutcome.setHealthCheckResponse(null);
				claimOutcome.setProfessionalClaimsErrorHandler(null);
				claimOutcome.setClaimValidationOutput(null);
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
	public ServiceOutcome<ResultProfessionalClaimOutcome> professionalClaimValidation(String accessToken,
			ProfessionalClaimValidation professionalClaimValidation) {
		String inputEntity = "";
		ClaimValidationOutput professionalClaimValidationOutcome = null;
		String url = ApplicationConstants.PROFESSIONALCLAIMS_VALIDATION_URL;
		ResponseEntity<String> response = null;
		ServiceOutcome<ResultProfessionalClaimOutcome> routcome = new ServiceOutcome<ResultProfessionalClaimOutcome>();
		ResultProfessionalClaimOutcome claimOutcome = new ResultProfessionalClaimOutcome();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + accessToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			inputEntity = ow.writeValueAsString(professionalClaimValidation);

			HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(inputEntity, headers);
			response = restTemplate.exchange(url, HttpMethod.POST, inputCriteriaEntity, String.class);
			System.out.println("Response=>" + response);
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				professionalClaimValidationOutcome = mapper.readValue(response.getBody(), ClaimValidationOutput.class);
				if (professionalClaimValidationOutcome.getStatus().equals("SUCCESS")) {
					ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					claimOutcome.setClaimValidationOutput(professionalClaimValidationOutcome);
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
		} catch (HttpStatusCodeException hsce) {
			ResponseEntity<String> responseException = ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(hsce.getResponseBodyAsString());
			if (responseException.getStatusCodeValue() == 400) {
				try {
					ObjectMapper mapper = new ObjectMapper();
					String errorResponse = responseException.getBody();
					System.out.println("ERROR RESPONSE-1:" + errorResponse);
					ProfessionalClaimsErrorHandler errorHandler = mapper.readValue(errorResponse,
							ProfessionalClaimsErrorHandler.class);
					claimOutcome.setProfessionalClaimsErrorHandler(errorHandler);
					routcome.setData(claimOutcome);
					routcome.setMessage("Bad request");
					routcome.setOutcome(false);
				} catch (Exception e) {
					e.printStackTrace();
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
	public ServiceOutcome<ResultProfessionalClaimOutcome> professionalClaimHealthCheck(String accessToken) {
		boolean response = false;
		ServiceOutcome<ResultProfessionalClaimOutcome> routcome = new ServiceOutcome<ResultProfessionalClaimOutcome>();
		ResultProfessionalClaimOutcome claimOutcome = new ResultProfessionalClaimOutcome();
		try {
			String url = ApplicationConstants.PROFESSIONALCLAIMS_HEALTHCHECK_URL;
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + accessToken);
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				HealthCheckResponse healthCheckResponse = mapper.readValue(responseEntity.getBody(),
						HealthCheckResponse.class);
				if (healthCheckResponse.getVersion().equalsIgnoreCase("v3")
						&& healthCheckResponse.getStatus().equalsIgnoreCase("ok")) {
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
}
