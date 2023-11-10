package com.sunknowledge.dme.rcm.service.patiententry.aggregate;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientInsuranceCommand;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.UpdatePatientInsuranceStatusCommand;
import reactor.core.publisher.Mono;

public interface PatientInsuranceEntryAggregate {
    Mono<ResponseDTO> handleSavePatientInsuranceCommand(SavePatientInsuranceCommand savePatientInsuranceCommand);

    Mono<ResponseDTO> handleUpdatePatientInsuranceStatusCommand(UpdatePatientInsuranceStatusCommand updatePatientInsuranceStatusCommand);
}
