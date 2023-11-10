package com.sunknowledge.dme.rcm.service.patiententry;

import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientInsuranceParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patiententry.command.SavePatientInsuranceCommand;
import reactor.core.publisher.Mono;

public interface PatientInsuranceEntryServiceExtended {
    Mono<ResponseDTO> savePatientInsurance(SavePatientInsuranceCommand obj);

    Mono<ResponseDTO> updatePatientInsuranceStatus(Long patientInsuranceId, String status);
}
