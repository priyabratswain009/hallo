package com.sunknowledge.dme.rcm.service.impl.par;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.utils.ApplicationConstants;
import com.sunknowledge.dme.rcm.domain.ServiceReview.ServiceReviewInput;
import com.sunknowledge.dme.rcm.domain.ServiceReview.ServiceReviewResponseDTO;
import com.sunknowledge.dme.rcm.service.claimssubmissiondata.TokenGenerationService;
import com.sunknowledge.dme.rcm.service.par.ServiceReviewService;

import reactor.core.publisher.Mono;

@Service
public class ServiceReviewServiceImpl implements ServiceReviewService {

	@Autowired
	private TokenGenerationService tokenGenerationService;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Mono<ServiceOutcome<String>> createServiceReview(ServiceReviewInput serviceReviewInput) {
		// TODO Auto-generated method stub
		ServiceOutcome<String> outCome = new ServiceOutcome<String>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

		try {
			String token = tokenGenerationService.getCoverageToken();
			// String token = tokenOutCome.getTokenResponseOutput().getAccessToken();
			String url = ApplicationConstants.SERVICE_REVIEW_NEW_URL;
			String inputEntity = ow.writeValueAsString(serviceReviewInput);

			System.out.println("inputEntity " + inputEntity);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.add("X-Api-Mock-Scenario-ID", "SR-CreateRequestAccepted-i");
			headers.set("Authorization", "Bearer " + token);
			System.out.println("url " + url);
			HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(inputEntity, headers);
			System.out.println("inputCriteriaEntity " + inputCriteriaEntity);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, inputCriteriaEntity,
					String.class);
			System.out.println("response.getStatusCode() " + response.getStatusCode());

			if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				System.out.println("resultOutcomeJson " + response.getHeaders().getLocation());
				outCome.setData(response.getHeaders().getLocation().toString());
				outCome.setMessage(response.getHeaders().getFirst("X-Status-Message"));
				outCome.setOutcome(true);
				return Mono.just(outCome);
			} else {
				return Mono.just(new ServiceOutcome<>(null, false, ""));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// return coverageOutput.getCoverages().get(0).getLinks().getSelf().getHref();
		return Mono.just(new ServiceOutcome<>(null, false, ""));
	}

	@Override
	public Mono<ServiceOutcome<ServiceReviewResponseDTO>> getServiceReviewById(String memberId) {
		// TODO Auto-generated method stub
		ServiceReviewResponseDTO serviceReviewResponseDTO = new ServiceReviewResponseDTO();
		ServiceOutcome<ServiceReviewResponseDTO> outCome = new ServiceOutcome<ServiceReviewResponseDTO>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			String token = tokenGenerationService.getCoverageToken();
			String url = ApplicationConstants.SERVICE_REVIEW_NEW_URL;

			url = url + "/" + memberId;

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.add("X-Api-Mock-Scenario-ID", "SR-GetComplete-i");
			headers.set("Authorization", "Bearer " + token);
			System.out.println("url " + url);
			HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(null, headers);
			System.out.println("inputCriteriaEntity " + inputCriteriaEntity);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, inputCriteriaEntity,
					String.class);
			System.out.println("response.getStatusCode() " + response.getStatusCode());

			if (response.getStatusCode() == HttpStatus.OK) {
				serviceReviewResponseDTO = mapper.readValue(response.getBody(), ServiceReviewResponseDTO.class);
				System.out.println("Response Body - "+response.getBody());
				outCome.setData(serviceReviewResponseDTO);
				outCome.setMessage("Service Review Successfull");
				outCome.setOutcome(true);
				return Mono.just(outCome);
			} else if (response.getStatusCode() == HttpStatus.ACCEPTED) {
				outCome.setData(null);
				outCome.setMessage(response.getHeaders().getFirst("X-Status-Message"));
				outCome.setOutcome(true);
				return Mono.just(outCome);
			} else {
				return Mono.just(new ServiceOutcome<>(null, false, ""));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// return coverageOutput.getCoverages().get(0).getLinks().getSelf().getHref();
		return Mono.just(new ServiceOutcome<>(null, false, ""));
	}

}
