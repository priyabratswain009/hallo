package com.sunknowledge.dme.rcm.service.patiententry.aggregate;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDiagnosisDetailsCommand;
import reactor.core.publisher.Mono;

public interface PatientDiagnosisEntryAggregate {

    Mono<ResponseDTO> handleSavePatientDiagnosisCommand(SavePatientDiagnosisDetailsCommand savePatientDiagnosisDetailsCommand);
}
