package com.sunknowledge.dme.rcm.service.patiententry;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientMasterParameterDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientDTOEntryServiceExtended {
    Mono<ResponseDTO> generatePatientBucket(UUID patientUUID);
}
