package com.sunknowledge.dme.rcm.service.patiententry.aggregate;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientCommand;
import reactor.core.publisher.Mono;

public interface PatientMasterEntryAggregate {
    Mono<ResponseDTO> handleSavePatientCommand(SavePatientCommand savePatientCommand);
}
