package com.sunknowledge.changehealthcare.service;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.changehealthcare.application.utils.ApplicationConstants;
import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.core.TokenOutCome;
import com.sunknowledge.changehealthcare.pojo.attachmentsubmission.AttachmentSubmissionErrorHandler;
import com.sunknowledge.changehealthcare.pojo.attachmentsubmission.AttachmentSubmissionInput;
import com.sunknowledge.changehealthcare.pojo.attachmentsubmission.AttachmentSubmissionOutput;
import com.sunknowledge.changehealthcare.pojo.attachmentsubmission.ResultAttachmentSubmissionOutcome;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.AccessTokenError;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.HealthCheckResponse;

@Service
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.changehealthcare.application.utils.ApplicationConstants;
import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.core.TokenOutCome;
import com.sunknowledge.changehealthcare.pojo.attachmentSubmission.AttachmentSubmissionInput;
import com.sunknowledge.changehealthcare.pojo.attachmentSubmission.AttachmentSubmissionOutput;
import com.sunknowledge.changehealthcare.pojo.attachmentSubmission.ResultAttachmentSubmissionOutcome;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.AccessTokenError;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.HealthCheckResponse;
import com.sunknowledge.changehealthcare.pojo.professionalclaims.ProfessionalClaimsErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AttachmentSubmissionServiceImpl implements AttachmentSubmissionService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private TokenGenerationServiceImpl tokenGenerationServiceImpl;

	@Override
	public ServiceOutcome<ResultAttachmentSubmissionOutcome> validateFileAndAttachmentSubmission(ServiceOutcome<ResultAttachmentSubmissionOutcome> routcome, List<MultipartFile> multipartFileList, AttachmentSubmissionInput attachmentSubmissionInputRequestInput) {
		try {
			boolean fileStatus = false;
			for (MultipartFile file : multipartFileList) {
				File f = new File(file.getOriginalFilename());
				boolean fileType = checkFileType(f);
				if(fileType) {
					fileStatus = true;
				}
				else {
					fileStatus = false;
					break;
				}
			}
			if(fileStatus) {
				routcome = medicalNetworkAttachmentSubmission(attachmentSubmissionInputRequestInput, multipartFileList);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}
	
	@Override
	public ServiceOutcome<ResultAttachmentSubmissionOutcome> medicalNetworkAttachmentSubmission(AttachmentSubmissionInput attachmentSubmissionInput, List<MultipartFile> multipartFileList) {
	public ServiceOutcome<ResultAttachmentSubmissionOutcome> accessattachmentSubmission(
			AttachmentSubmissionInput attachmentSubmissionInput) {
		ServiceOutcome<ResultAttachmentSubmissionOutcome> routcome = new ServiceOutcome<ResultAttachmentSubmissionOutcome>();
		ResultAttachmentSubmissionOutcome claimOutcome = new ResultAttachmentSubmissionOutcome();
		TokenOutCome objTokenOutCome = new TokenOutCome();
		try {
			objTokenOutCome = tokenGenerationServiceImpl.getToken();
			String token = objTokenOutCome.getTokenResponseOutput().getAccessToken();
			//String token = "eyJraWQiOiIyIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJhY2Nlc3NfdG9rZW4iOiJuSXFHaUtLR3VSa0k5RGJQV01DMTBqeXZrZlFEIiwiYXVkIjoiYXBpUGxhdGZvcm0iLCJhcGlfcHJvZHVjdF9saXN0IjpbIk1OX1Byb2R1Y3RfUmVwb3J0c192MSIsIk1OX1Byb2R1Y3RfQXR0YWNobWVudHNfdjEiLCJNTl9Qcm9kdWN0X0NsYWltU3RhdHVzX3YyIiwiTU5fUHJvZHVjdF9FbGlnaWJpbGl0eV92MyIsIk1OX1Byb2R1Y3RfUHJvZmVzc2lvbmFsQ2xhaW1zX3YzIiwiTU5fUHJvZHVjdF9JbnN0aXR1dGlvbmFsQ2xhaW1zX3YxIl0sImFwcGxpY2F0aW9uX25hbWUiOiJNTl9TdW4gS25vd2xlZGdlX0FQUCIsIm5iZiI6MTY2MDcxMzA1OCwiZGV2ZWxvcGVyX2VtYWlsIjoicmFuamFuLmtvbGV5QHN1bmtub3dsZWRnZS5jb20iLCJpc3MiOiJodHRwczpcL1wvc2FuZGJveC5hcGlndy5jaGFuZ2VoZWFsdGhjYXJlLmNvbSIsInNjb3BlcyI6IiIsImV4cCI6MTY2MDcxNjY1OCwiaWF0IjoxNjYwNzEzMDU4LCJjbGllbnRfaWQiOiI5TW90cjgxZTZsU212N2JDdDIxZm5oU2lYN1FlNjVaTCIsImp0aSI6IjJmNjdkZDgzLTk4M2EtNGQ0OS04MjlhLTgwNTQ1NGQ0M2U2MSJ9.EtHbmxZhk7hIV9pEW4d_MeYBkyBSPl5M_rapldhDjj_ylU80xRJ7xvVIQbXhq2sI0M_GsQuzWKqvCfBpRyKZcTr5MoNXuCSO6Z3TYp-Q0X_kHBnJqUE6HUNTW8Mw2HdhK9B_Cp8t2QOr3bbBZmoYZ10P4itdpdaRNogtOfFHcSN8L_10b-8-MSARpyPElzAW9kp44mzz1xOV8x8oQ4KwM3aByTg_QKxnWP1d2LEN8S8SpEud4H2SeL-t8l8TlI0kzGCZ3y4wxLGgmTfj5uWVQ4TYQlH7W8bYWZBWBIhAU7_19CSKfQVoCfiOrSxG73aIIa4Z7_MKxWEv-Mb3BHcJgQ";
			System.out.println("TOKEN=>" + token);
			if (attachmentSubmissionHealthCheck(token).getOutcome()) {
				routcome = attachmentSubmission(token, attachmentSubmissionInput, multipartFileList);
			} 
			else {
			System.out.println("TOKEN=>" + token);
			if (attachmentSubmissionHealthCheck(token).getOutcome()) {
				routcome = attachmentSubmission(token, attachmentSubmissionInput);
			} else {
				claimOutcome.setAccessTokenError(null);
				claimOutcome.setAttachmentSubmissionOutput(null);
				claimOutcome.setHealthCheckResponse(null);
				routcome.setData(claimOutcome);
				routcome.setMessage("Unauthorized token access.");
				routcome.setOutcome(false);
			}
		} 
		catch (Exception e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}

	@Override
	public ServiceOutcome<ResultAttachmentSubmissionOutcome> attachmentSubmission(String accessToken, AttachmentSubmissionInput attachmentSubmissionInput, List<MultipartFile> multipartFileList) {
		ObjectMapper mapper = new ObjectMapper();
		AttachmentSubmissionOutput attachmentSubmissionOutput = null;

		String url = ApplicationConstants.ATTACHMENT_SUBMISSION_URL;
		ServiceOutcome<ResultAttachmentSubmissionOutcome> routcome = new ServiceOutcome<ResultAttachmentSubmissionOutcome>();
		ResultAttachmentSubmissionOutcome claimOutcome = new ResultAttachmentSubmissionOutcome();
		try {
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
			requestHeaders.add("Authorization", "Bearer " + accessToken);
			HttpHeaders requestHeadersAttachment  = new HttpHeaders();
			for(MultipartFile file : multipartFileList) {
				File f = new File(file.getOriginalFilename());
				String fileExtension = checkFileExtension(f);
				if(fileExtension.equalsIgnoreCase("PDF"))
					requestHeadersAttachment.setContentType(MediaType.APPLICATION_PDF);
				if(fileExtension.equalsIgnoreCase("JPG") || fileExtension.equalsIgnoreCase("JPEG"))
					requestHeadersAttachment.setContentType(MediaType.IMAGE_JPEG);
				if(fileExtension.equalsIgnoreCase("PNG"))
					requestHeadersAttachment.setContentType(MediaType.IMAGE_PNG);
				if(fileExtension.equalsIgnoreCase("TIF"))
					requestHeadersAttachment.setContentType(MediaType.valueOf("image/tif"));
				if(fileExtension.equalsIgnoreCase("TIFF"))
					requestHeadersAttachment.setContentType(MediaType.valueOf("image/tiff"));
				
				ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()){
					@Override
					public String getFilename(){
						return file.getOriginalFilename();
					}
				};
				HttpEntity<ByteArrayResource> attachmentPart = new HttpEntity<>(fileAsResource, requestHeadersAttachment);
				body.add("files", attachmentPart);
			}
			
			HttpHeaders requestHeadersJSON = new HttpHeaders();
			requestHeadersJSON.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<AttachmentSubmissionInput> requestEntityJSON = new HttpEntity<>(attachmentSubmissionInput, requestHeadersJSON);
			body.set("request", requestEntityJSON);

			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, requestHeaders);

			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
			System.out.println("2.response=>" + response);
			if (response.getStatusCodeValue() == 207) {
				attachmentSubmissionOutput = mapper.readValue(response.getBody(), AttachmentSubmissionOutput.class);
				if (attachmentSubmissionOutput.getStatus().equals("success")) {
					System.out.println("==>" + attachmentSubmissionOutput.getAttachmentId());
	public ServiceOutcome<ResultAttachmentSubmissionOutcome> attachmentSubmission(String accessToken,
			AttachmentSubmissionInput attachmentSubmissionInput) {
		String inputEntity = "";
		// String resultOutcomeJson = "";
		AttachmentSubmissionOutput attachmentSubmissionOutput = null;
		String url = ApplicationConstants.ATTACHMENT_SUBMISSION_URL;
		url = url +"uploads?request="+ attachmentSubmissionInput;
		ServiceOutcome<ResultAttachmentSubmissionOutcome> routcome = new ServiceOutcome<ResultAttachmentSubmissionOutcome>();
		ResultAttachmentSubmissionOutcome claimOutcome = new ResultAttachmentSubmissionOutcome();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + accessToken);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			inputEntity = ow.writeValueAsString(attachmentSubmissionInput);

			HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(inputEntity, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, inputCriteriaEntity,
					String.class);
			System.out.println("2.response=>" + response);
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				attachmentSubmissionOutput = mapper.readValue(response.getBody(), AttachmentSubmissionOutput.class);
				if (attachmentSubmissionOutput.getStatus().equals("SUCCESS")) {
					System.out.println("==>" + attachmentSubmissionOutput.getAttachmentId());
					ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					// resultOutcomeJson =
					// ow.writeValueAsString(professionalClaimSubmissionOutcome);
					claimOutcome.setAttachmentSubmissionOutput(attachmentSubmissionOutput);
					routcome.setData(claimOutcome);
					routcome.setMessage("Successfully get the data");
					routcome.setOutcome(true);
				} 
				else {
					System.out.println("==================FAILURE RESPONSE========================");
				}
			} 
			else {
				} else {
					System.out.println("==================FAILURE RESPONSE========================");
				}
			} else {
				System.out.println("====================FAILURE====================");
				routcome.setData(null);
				routcome.setMessage("Host Unreachable");
				routcome.setOutcome(false);
			}
		} 
		catch (HttpStatusCodeException e) {
			ResponseEntity<String> responseException = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getResponseBodyAsString());
			if (responseException.getStatusCodeValue() == 400) {
				try {
					String errorResponse = responseException.getBody();
					System.out.println("ERROR RESPONSE-1:" + errorResponse);
					AttachmentSubmissionErrorHandler errorHandler = mapper.readValue(errorResponse, AttachmentSubmissionErrorHandler.class);
					claimOutcome.setAttachmentSubmissionErrorHandler(errorHandler);
					routcome.setData(claimOutcome);
					routcome.setMessage("Bad request");
					routcome.setOutcome(false);
				} 
				catch (Exception a) {
					a.printStackTrace();
				}
			} 
			else {
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
		} 
		catch (Exception e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}
	

	@Override
	public ServiceOutcome<ResultAttachmentSubmissionOutcome> attachmentSubmissionHealthCheck(String token) {
		boolean response = false;
		ServiceOutcome<ResultAttachmentSubmissionOutcome> routcome = new ServiceOutcome<ResultAttachmentSubmissionOutcome>();
		ResultAttachmentSubmissionOutcome claimOutcome = new ResultAttachmentSubmissionOutcome();
		try {
			String url = ApplicationConstants.ATTACHMENT_HEALTHCHECK_URL;
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + token);
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
			System.out.println("HealthCheck Response==> "+responseEntity);
			if (responseEntity.getStatusCode().name().equalsIgnoreCase("OK")) {
				ObjectMapper mapper = new ObjectMapper();
				HealthCheckResponse healthCheckResponse = mapper.readValue(responseEntity.getBody(), HealthCheckResponse.class)
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					String.class);
			System.out.println("HealthCheck Response==> "+responseEntity);
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
				} 
				else {
				} else {
					response = false;
					claimOutcome.setHealthCheckResponse(healthCheckResponse);
					routcome.setData(claimOutcome);
					routcome.setMessage("Professional health check! NOT OK.");
					routcome.setOutcome(response);
				}
			}
		} 
		catch (HttpStatusCodeException hsce) {
			ResponseEntity<String> responseException = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(hsce.getResponseBodyAsString());
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
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		} 
		catch (Exception e) {
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}

	@Override
	public boolean checkFileType(File file) {
		String fileName = file.getName().toUpperCase();
		if(fileName.endsWith(".JPG") || fileName.endsWith(".JPEG") || fileName.endsWith(".PDF") || fileName.endsWith(".TIF") || fileName.endsWith(".TIFF") || fileName.endsWith(".PNG")) {
			return true;
		}
		return false;
	}
	
	public String checkFileExtension(File file) {
		String fileName = file.getName().toUpperCase();
		return FilenameUtils.getExtension(fileName);
	}
}
