package com.sunknowledge.dme.rcm.service.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientInsuranceVerificationParameterDTO;

public interface CheckPatientElligibility {

	PatientInsuranceVerificationParameterDTO checkElligibility(String patientId);
	boolean checkElligibilityHealth(String token);
}
