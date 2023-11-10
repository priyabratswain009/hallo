package com.sunknowledge.dme.rcm.service.availity;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.config.appconfiguration.ApplicationConstants;
import com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest;
import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import com.sunknowledge.dme.rcm.domain.Coverage.CoverageInput;
import com.sunknowledge.dme.rcm.domain.Coverage.CoverageOutput;
import com.sunknowledge.dme.rcm.repository.Availity.BenefitCoverageRequestRepo;
import com.sunknowledge.dme.rcm.repository.Availity.BenefitCoverageResponseRepo;

@Primary
@Service
public class PatientBenefitCoverageServiceImpl implements PatientBenefitCoverageService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private TokenGenerationService tokenGenerationService;
	@Autowired
	BenefitCoverageRequestRepo benefitCoverageRequestRepository;
	@Autowired
	BenefitCoverageResponseRepo benefitCoverageResponseRepository;

	@Override
	public String getPatientBenefitCoverage(CoverageInput coverageInput) {

		CoverageOutput coverageOutput = null;

		try {
			String token = tokenGenerationService.getToken();

			String url = ApplicationConstants.VERIFY_BENEFITCOVERAGE_URL;
			String inputData = setData(coverageInput);
			url = url + "?" + inputData;

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + token);

			HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(null, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, inputCriteriaEntity,
					String.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				LocalDate reqdate = LocalDate.now();
				coverageOutput = mapper.readValue(response.getBody(), CoverageOutput.class);
				String secondresponse = getPatientBenefitCoverageById(coverageOutput.getCoverages().get(0).getId());
				LocalDate respdate = LocalDate.now();
				BenefitCoverageRequest benefitCoverageRequest = setDataInRequest(coverageInput);
				BenefitCoverageResponse benefitCoverageResponse = setDataInResponse(coverageOutput, response.getBody(),
						secondresponse, reqdate, respdate);
				benefitCoverageRequestRepository.save(benefitCoverageRequest);
				benefitCoverageResponseRepository.save(benefitCoverageResponse);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return coverageOutput.getCoverages().get(0).getLinks().getSelf().getHref();
	}

	public String getPatientBenefitCoverageById(String id) {

		String resp = "";

		try {
			String token = tokenGenerationService.getToken();

			String url = ApplicationConstants.VERIFY_BENEFITCOVERAGE_URL + "/" + id;

			System.out.println(url);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + token);

			HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(null, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, inputCriteriaEntity,
					String.class);
			resp = response.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	String setData(CoverageInput coverageInput) {

		String data = "payerId=" + coverageInput.getPayerId() + "&providerLastName="
				+ coverageInput.getProviderLastName() + "&providerType=" + coverageInput.getProviderType()
				+ "&providerNpi=" + coverageInput.getProviderNpi() + "&providerCity=" + coverageInput.getProviderCity()
				+ "&providerState=" + coverageInput.getPatientState() + "&providerZipCode="
				+ coverageInput.getProviderZipCode() + "&asOfDate=" + coverageInput.getAsOfDate() + "&serviceType="
				+ coverageInput.getServiceType() + "&memberId=" + coverageInput.getMemberId() + "&patientLastName="
				+ coverageInput.getPatientLastName() + "&patientFirstName=" + coverageInput.getPatientFirstName()
				+ "&patientBirthDate=" + coverageInput.getPatientBirthDate() + "&patientGender="
				+ coverageInput.getPatientGender() + "&patientState=" + coverageInput.getPatientState()
				+ "&subscriberRelationship=" + coverageInput.getSubscriberRelationship();

		return data;
	}

	BenefitCoverageRequest setDataInRequest(CoverageInput coverageInput) {
		BenefitCoverageRequest benefitCoverageRequest = new BenefitCoverageRequest();

		benefitCoverageRequest.setPayerId(coverageInput.getPayerId());
		benefitCoverageRequest.setProviderLastName(coverageInput.getProviderLastName());
		benefitCoverageRequest.setProviderFirstName(coverageInput.getProviderFirstName());
		benefitCoverageRequest.setProviderType(coverageInput.getProviderType());
		benefitCoverageRequest.setProviderNpi(coverageInput.getProviderNpi());
		benefitCoverageRequest.setProviderCity(coverageInput.getProviderCity());
		benefitCoverageRequest.setPatientState(coverageInput.getPatientState());
		benefitCoverageRequest.setProviderZipcode(coverageInput.getProviderZipCode());
		System.out.println(coverageInput.getAsOfDate());
		benefitCoverageRequest
				.setAsOfDate(CommonUtilities.stringwithhyphentodateConverter(coverageInput.getAsOfDate()));
		benefitCoverageRequest.setServiceType(coverageInput.getServiceType());
		benefitCoverageRequest.setMemberId(coverageInput.getMemberId());
		benefitCoverageRequest.setPatientLastName(coverageInput.getPatientLastName());
		benefitCoverageRequest.setPatientFirstName(coverageInput.getPatientFirstName());
		benefitCoverageRequest
				.setPatientDob(CommonUtilities.stringtodateConverter(coverageInput.getPatientBirthDate()));
		benefitCoverageRequest.setPatientGender(coverageInput.getPatientGender());
		benefitCoverageRequest.setPatientState(coverageInput.getPatientState());
		benefitCoverageRequest.setSubscriberRelationship(coverageInput.getSubscriberRelationship());
		benefitCoverageRequest.setCreatedById(Long.valueOf("5501"));
		benefitCoverageRequest.setCreatedByName("Arijit");
		benefitCoverageRequest.setUpdatedById(Long.valueOf("5501"));
		benefitCoverageRequest.setUpdatedByName("Arijit");
		LocalDate zone = LocalDate.now();
		benefitCoverageRequest.setUpdatedDate(zone);
		benefitCoverageRequest.setCreatedDate(zone);

		return benefitCoverageRequest;
	}

	BenefitCoverageResponse setDataInResponse(CoverageOutput coverageOutput, String presp, String sresp,
			LocalDate reqdate, LocalDate respdate) {

		BenefitCoverageResponse objBenefitCoverageResponse = new BenefitCoverageResponse();

		for (int i = 0; i < coverageOutput.getCoverages().size(); i++) {

			objBenefitCoverageResponse.setBenefitCoverageRequestId(benefitCoverageRequestRepository.getlastentry());
			objBenefitCoverageResponse.setCreatedDate(
					CommonUtilities.stringzonedtodateConverter(coverageOutput.getCoverages().get(i).getCreatedDate()));
			objBenefitCoverageResponse.setUpdatedDate(
					CommonUtilities.stringzonedtodateConverter(coverageOutput.getCoverages().get(i).getUpdatedDate()));
			objBenefitCoverageResponse.setExpirationDate(CommonUtilities
					.stringzonedtodateConverter(coverageOutput.getCoverages().get(i).getExpirationDate()));
			objBenefitCoverageResponse.setRequestedDate(reqdate);
			objBenefitCoverageResponse.setResponseDate(respdate);
			String serviceType = "";
			if (coverageOutput.getCoverages().get(i).getRequestedServiceType().size() > 1) {
				for (int j = 0; j < coverageOutput.getCoverages().get(i).getRequestedServiceType().size(); j++) {

					serviceType = serviceType + "-"
							+ coverageOutput.getCoverages().get(i).getRequestedServiceType().get(j).getCode();
				}
			} else {
				serviceType = coverageOutput.getCoverages().get(i).getRequestedServiceType().get(0).getCode();
			}
			objBenefitCoverageResponse.setServiceType(serviceType);
			objBenefitCoverageResponse
					.setSubscriberMemberId(coverageOutput.getCoverages().get(i).getSubscriber().getMemberId());
			objBenefitCoverageResponse.setPatientRelationshipCode(
					coverageOutput.getCoverages().get(i).getPatient().getSubscriberRelationshipCode());
			objBenefitCoverageResponse.setPayerId(coverageOutput.getCoverages().get(i).getPayer().getPayerId());
			objBenefitCoverageResponse
					.setProviderNpi(coverageOutput.getCoverages().get(i).getRequestingProvider().getNpi());
			String plancode = "", planstatus = "";
			if (coverageOutput.getCoverages().get(i).getPlans().size() > 1) {
				for (int j = 0; j < coverageOutput.getCoverages().get(i).getPlans().size(); j++) {
					plancode = plancode + "-" + coverageOutput.getCoverages().get(i).getPlans().get(0).getStatusCode();
					planstatus = planstatus + "-" + coverageOutput.getCoverages().get(i).getPlans().get(0).getStatus();
				}
			} else {
				plancode = coverageOutput.getCoverages().get(i).getPlans().get(0).getStatusCode();
				planstatus = coverageOutput.getCoverages().get(i).getPlans().get(0).getStatus();
			}
			objBenefitCoverageResponse.setPlansStatusCode(plancode);
			objBenefitCoverageResponse.setPlansStatus(planstatus);
			objBenefitCoverageResponse.setPrimaryResponse(presp);
			objBenefitCoverageResponse.setSecondaryResponse(sresp);
			objBenefitCoverageResponse.setStatus(coverageOutput.getCoverages().get(i).getStatus());
			objBenefitCoverageResponse.setCreatedById(Long.valueOf("5501"));
			objBenefitCoverageResponse.setCreatedByName("Arijit");
			objBenefitCoverageResponse.setUpdatedById(Long.valueOf("5501"));
			objBenefitCoverageResponse.setUpdatedByName("Arijit");
		}

		return objBenefitCoverageResponse;
	}

}
