package com.sunknowledge.dme.rcm.service.patiententry.aggregate;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientCommand;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDTOCommand;
import reactor.core.publisher.Mono;

public interface PatientDTOEntryAggregate {
    Mono<ResponseDTO> handleGeneratePatientBucket(SavePatientDTOCommand savePatientDTOCommand);
}
