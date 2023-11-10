package com.sunknowledge.dme.rcm.service.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientClinicalParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientClinicalDetailsCommand;
import reactor.core.publisher.Mono;

public interface PatientClinicalEntryServiceExtended {
    Mono<ResponseDTO> savePatientClinicalDetails(SavePatientClinicalDetailsCommand obj);
}
