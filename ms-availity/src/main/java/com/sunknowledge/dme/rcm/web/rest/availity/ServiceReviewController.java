package com.sunknowledge.dme.rcm.web.rest.availity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.domain.ServiceReview.Patient;
import com.sunknowledge.dme.rcm.domain.ServiceReview.Payer;
import com.sunknowledge.dme.rcm.domain.ServiceReview.RequestingProvider;
import com.sunknowledge.dme.rcm.domain.ServiceReview.ServiceReviewInput;
import com.sunknowledge.dme.rcm.domain.ServiceReview.Subscriber;
import com.sunknowledge.dme.rcm.service.availity.ServiceReviewService;

@RestController
@RequestMapping("/api")
public class ServiceReviewController {

	@Autowired
	private ServiceReviewService ServiceReviewService;
	
	@PostMapping("/getServiceReview")
	public String patientBenefitCoverage() {
		
		ServiceReviewInput objserviceReviewInput = new ServiceReviewInput();
		Payer objpayer = new Payer();
		RequestingProvider objRequestingProvider = new RequestingProvider();
		Subscriber objSubscriber = new Subscriber();
		Patient objPatient = new Patient();
		
		objpayer.setId("AETNA");
		objserviceReviewInput.setPayer(objpayer);
		//Requesting Provider
		objRequestingProvider.setSpecialtyCode("SpecialityCode");
		objRequestingProvider.setLastName("doeone");
		objRequestingProvider.setFirstName("JohnOne");
		objRequestingProvider.setNpi("123456789");
		objRequestingProvider.setPayerAssignedProviderId("123456789");
		objRequestingProvider.setSubmitterId("12345");
		objRequestingProvider.setAddressLine1("addressLine1");
		objRequestingProvider.setAddressLine2("addressLine2");
		objRequestingProvider.setCity("city1");
		objRequestingProvider.setStateCode("wa");
		objRequestingProvider.setZipCode("987456");
		objRequestingProvider.setContactName("AJ");
		objRequestingProvider.setPhone("9587465231");
		objRequestingProvider.setFax("fax");
		objserviceReviewInput.setRequestingProvider(objRequestingProvider);
		//Subscriber
		objSubscriber.setMemberId("123456789");
		objSubscriber.setFirstName("Arijit");
		objSubscriber.setLastName("Sharma");
		objserviceReviewInput.setSubscriber(objSubscriber);
		//Patient
		objPatient.setLastName("PatientLName");
		objPatient.setFirstName("FirstName");
		objPatient.setBirthDate("2010-10-27");
		objPatient.setSubscriberRelationshipCode("18");
		objserviceReviewInput.setPatient(objPatient);
		objserviceReviewInput.setRequestTypeCode("DM");
		objserviceReviewInput.setFromDate("2022-10-27");
		objserviceReviewInput.setToDate("2022-11-27");
		objserviceReviewInput.setCertificationIssueDate("2022-10-27");
		objserviceReviewInput.setCertificationNumber("123456789");
		objserviceReviewInput.setReferenceNumber("987654321");
		objserviceReviewInput.setStatusCode("200");
		objserviceReviewInput.setSessionId("6256981698452");
		
		return ServiceReviewService.getServiceReview(objserviceReviewInput);
	}
	
}
