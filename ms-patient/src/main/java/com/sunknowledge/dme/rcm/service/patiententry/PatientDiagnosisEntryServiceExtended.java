package com.sunknowledge.dme.rcm.service.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDiagnosisParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDiagnosisDetailsCommand;
import reactor.core.publisher.Mono;

public interface PatientDiagnosisEntryServiceExtended {
    Mono<ResponseDTO> savePatientDiagnosis(SavePatientDiagnosisDetailsCommand obj);
}
