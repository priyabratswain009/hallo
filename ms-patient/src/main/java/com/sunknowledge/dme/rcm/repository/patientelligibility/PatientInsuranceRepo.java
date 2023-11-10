package com.sunknowledge.dme.rcm.repository.patientelligibility;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.PatientInsurance;
import com.sunknowledge.dme.rcm.repository.PatientInsuranceRepository;

import reactor.core.publisher.Mono;

public interface PatientInsuranceRepo extends PatientInsuranceRepository{


	@Query(value = "select * from t_patient_insurance where t_patient_insurance.patient_id=:patientId")
	Mono<PatientInsurance> getPatientInsurance(@Param("patientId") Long patientId);
	
}
