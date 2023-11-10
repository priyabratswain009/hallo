package com.sunknowledge.dme.rcm.service.patiententry.aggregate;

import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientInsuranceVerificationCommand;
import reactor.core.publisher.Mono;

public interface PatientInsuranceVerificationAggregate {
    Mono<ResponseDTO> handleSavePatientInsuranceVerificationCommand(SavePatientInsuranceVerificationCommand savePatientInsuranceVerificationCommand);
    Mono<ResponseDTO> handleSavePatientElligibility(SavePatientInsuranceVerificationCommand savePatientInsuranceVerificationCommand);
    Mono<ResponseDTO> checkAndHandleSavePatientElligibility(Long patientId, PatientMasterDTO objpatient, PatientInsuranceDTO patientInsuranceDTO, String accessToken);
}
