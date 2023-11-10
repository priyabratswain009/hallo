package com.sunknowledge.dme.rcm.service.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientMasterParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientMasterEntryServiceExtended {
    Mono<ResponseDTO> savePatient(SavePatientCommand obj);

    Mono<ResponseDTO> generatePatientBucket(UUID patientUUID);
}
