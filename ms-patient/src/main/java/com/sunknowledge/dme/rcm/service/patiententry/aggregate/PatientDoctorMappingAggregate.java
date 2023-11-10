package com.sunknowledge.dme.rcm.service.patiententry.aggregate;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientDoctorMappingCommand;
import reactor.core.publisher.Mono;

public interface PatientDoctorMappingAggregate {

    Mono<ResponseDTO> handleSavePatientDoctorMapping(SavePatientDoctorMappingCommand savePatientDoctorMappingCommand);
}
