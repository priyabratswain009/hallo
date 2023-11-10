package com.sunknowledge.dme.rcm.service.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientClinicalInformationOutputExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientClinicalInformationPatientMasterExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientClinicalSearchByPatientId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientClinicalSearchQueryHandler {
    Flux<PatientClinicalInformationDTO> getPatientClinicalBySearchParametersQueryHandler(PatientClinicalSearchByPatientId obj);

    Long getIDByUUID(UUID patientClinicalInformationUUID);


    Flux<PatientClinicalInformationPatientMasterExtendedDTO> getPatientClinicalByPatientId(Long patientId);

    Mono<Object> getPatientInsuranceByPatInsuranceId(Long patClinicalInfoId);

    Mono<ServiceOutcome<PatientClinicalInformationOutputExtendedDTO>> getCurrentPatientClinicalByMaxId(Long patientId);
}
