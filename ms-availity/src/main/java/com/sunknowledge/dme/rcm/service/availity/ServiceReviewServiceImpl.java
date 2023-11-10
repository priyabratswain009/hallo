package com.sunknowledge.dme.rcm.service.availity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sunknowledge.dme.rcm.config.appconfiguration.ApplicationConstants;
import com.sunknowledge.dme.rcm.domain.ServiceReview.ServiceReviewInput;

@Service
public class ServiceReviewServiceImpl implements ServiceReviewService{

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private TokenGenerationService tokenGenerationService;
	
	@Override
	public String getServiceReview(ServiceReviewInput serviceReviewInput) {

		try {
			String token = tokenGenerationService.getToken();

			String url = ApplicationConstants.SERVICE_REVIEW_URL;
			String inputData = setData(serviceReviewInput);
			url = url + "?" + inputData;

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + token);

			HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(null, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, inputCriteriaEntity,
					String.class);

			System.out.println("OUTPUT=======> "+response);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	String setData(ServiceReviewInput serviceReviewInput) {

		String data = "payer.id="
				+ serviceReviewInput.getPayer().getId() + "&requestingProvider.specialtyCode="
				+ serviceReviewInput.getRequestingProvider().getSpecialtyCode() + "&requestingProvider.lastName=" 
				+ serviceReviewInput.getRequestingProvider().getLastName() + "&requestingProvider.firstName="
				+ serviceReviewInput.getRequestingProvider().getFirstName() + "&requestingProvider.npi="
				+ serviceReviewInput.getRequestingProvider().getNpi() + "&requestingProvider.payerAssignedProviderId=" + serviceReviewInput.getRequestingProvider().getPayerAssignedProviderId() + "&requestingProvider.submitterId="
				+ serviceReviewInput.getRequestingProvider().getSubmitterId() + "&requestingProvider.addressLine1=" + serviceReviewInput.getRequestingProvider().getAddressLine1() + "&requestingProvider.addressLine2="
				+ serviceReviewInput.getRequestingProvider().getAddressLine2() + "&requestingProvider.city=" +  serviceReviewInput.getRequestingProvider().getCity() + "&requestingProvider.stateCode="
				+ serviceReviewInput.getRequestingProvider().getStateCode() + "&requestingProvider.zipCode="
				+ serviceReviewInput.getRequestingProvider().getZipCode() + "&requestingProvider.contactName="
				+ serviceReviewInput.getRequestingProvider().getContactName() + "&requestingProvider.phone="
				+ serviceReviewInput.getRequestingProvider().getPhone() + "&requestingProvider.fax="
				+ serviceReviewInput.getRequestingProvider().getFax() + "&subscriber.memberId="
				+ serviceReviewInput.getSubscriber().getMemberId() + "&subscriber.lastName="
				+ serviceReviewInput.getSubscriber().getLastName() + "&subscriber.firstName="
				+ serviceReviewInput.getSubscriber().getFirstName() + "&patient.lastName="
				+ serviceReviewInput.getPatient().getLastName() + "&patient.firstName="
				+ serviceReviewInput.getPatient().getFirstName() + "&patient.birthDate="
				+ serviceReviewInput.getPatient().getBirthDate() + "&patient.subscriberRelationshipCode="
				+ serviceReviewInput.getPatient().getSubscriberRelationshipCode() + "&requestTypeCode="
				+ serviceReviewInput.getRequestTypeCode() + "&fromDate="
				+ serviceReviewInput.getFromDate() + "&toDate="
				+ serviceReviewInput.getToDate() + "&certificationIssueDate="
				+ serviceReviewInput.getCertificationIssueDate() + "&certificationNumber="
				+ serviceReviewInput.getCertificationNumber() + "&referenceNumber="
				+ serviceReviewInput.getReferenceNumber() + "&statusCode="
				+ serviceReviewInput.getStatusCode() + "&sessionId="
				+ serviceReviewInput.getSessionId();

		return data;
	}

}
