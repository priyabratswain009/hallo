package com.sunknowledge.dme.rcm.service.patiententry.aggregate;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientClinicalDetailsCommand;
import reactor.core.publisher.Mono;

public interface PatientClinicalEntryAggregate {
    Mono<ResponseDTO> handleSavePatientClinicalInfoCommand(SavePatientClinicalDetailsCommand savePatientClinicalDetailsCommand);
}
