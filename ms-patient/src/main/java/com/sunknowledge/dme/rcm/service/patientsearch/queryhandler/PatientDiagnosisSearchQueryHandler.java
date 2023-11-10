package com.sunknowledge.dme.rcm.service.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientClinicalSearchByPatientId;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDiagnosisSearchByPatIdPatDiaId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientDiagnosisSearchQueryHandler {
    Flux<PatientDiagnosisDTO> getPatientDiagnosisBySearchParametersQueryHandler(PatientDiagnosisSearchByPatIdPatDiaId obj);

    Long getIDByUUID(UUID patientDiagnosisUuid);

    Flux<PatientDiagnosisDTO> getPatientDiagnosisByPatientId(Long patientId);

    Mono<Object> getPatientDiagnosisByPatientDiagnosisId(Long patDiagnosisId);

    Mono<Object> getCurrentPatientDiagnosisByMaxId(Long patientId);

}
