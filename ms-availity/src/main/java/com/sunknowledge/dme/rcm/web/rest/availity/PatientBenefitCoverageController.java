package com.sunknowledge.dme.rcm.web.rest.availity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import com.sunknowledge.dme.rcm.domain.Coverage.CoverageInput;
import com.sunknowledge.dme.rcm.service.availity.PatientBenefitCoverageService;
import com.sunknowledge.dme.rcm.service.availity.PatientCoverageDetailsService;

@RestController
@RequestMapping("/api")
public class PatientBenefitCoverageController {

	@Autowired
	private PatientBenefitCoverageService patientBenefitCoverageService;
	@Autowired
	private PatientCoverageDetailsService patientCoverageDetailsService;

	@PostMapping("/getPatientBenefitCoverage")
	public String patientBenefitCoverage(@RequestParam("payerId") String payerId,
			@RequestParam("providerOrganizationName") String providerOrganizationName,
			@RequestParam("providerNpi") String providerNpi, @RequestParam("providerCity") String providerCity,
			@RequestParam("providerState") String providerState,
			@RequestParam("providerZipcode") String providerZipcode, @RequestParam("memberId") String memberId,
			@RequestParam("patientLastName") String patientLastName,
			@RequestParam("patientFirstName") String patientFirstName, @RequestParam("patientDob") String patientDob,
			@RequestParam("patientGender") String patientGender, @RequestParam("patientState") String patientState,
			@RequestParam("subscriberRelationship") String subscriberRelationship) {

		String resultOutcomeJson = "";
		CoverageInput objCoverageInput = new CoverageInput();
		objCoverageInput.setPayerId(payerId);
		objCoverageInput.setProviderLastName(providerOrganizationName);
		objCoverageInput.setProviderType("Billing");
		objCoverageInput.setProviderNpi(providerNpi);
		objCoverageInput.setProviderCity(providerCity);
		objCoverageInput.setProviderState(providerState);
		objCoverageInput.setProviderZipCode(providerZipcode);
		objCoverageInput.setAsOfDate(String.valueOf(LocalDate.now()));
		objCoverageInput.setServiceType("DM");
		objCoverageInput.setMemberId(memberId);
		objCoverageInput.setPatientLastName(patientLastName);
		objCoverageInput.setPatientFirstName(patientFirstName);
		objCoverageInput.setPatientBirthDate(patientDob);
		objCoverageInput.setPatientGender(patientGender);
		objCoverageInput.setPatientState(patientState);
		objCoverageInput.setSubscriberRelationship(subscriberRelationship);
		try {
			resultOutcomeJson = patientBenefitCoverageService.getPatientBenefitCoverage(objCoverageInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultOutcomeJson;
	}
	
	@GetMapping("/getPatientBenefitCoverage")
	public List<BenefitCoverageResponse> getCoverageDetails(@RequestParam("memberId") String memberId){
		
		return patientCoverageDetailsService.getpatientCoverageDetails(memberId);
	}
}
