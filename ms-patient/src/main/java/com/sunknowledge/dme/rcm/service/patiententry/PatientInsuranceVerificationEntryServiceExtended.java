package com.sunknowledge.dme.rcm.service.patiententry;

import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientInsuranceVerificationCommand;
import reactor.core.publisher.Mono;

public interface PatientInsuranceVerificationEntryServiceExtended {
    Mono<ResponseDTO> savePatientInsuranceVerification(SavePatientInsuranceVerificationCommand obj);
    Mono<ResponseDTO> checkPatientElligibility(String patientId);
    Mono<ResponseDTO> checkPatientElligibilityReactive(Long patientId, PatientMasterDTO objpatient, PatientInsuranceDTO patientInsuranceDTO, String accessToken);
}
