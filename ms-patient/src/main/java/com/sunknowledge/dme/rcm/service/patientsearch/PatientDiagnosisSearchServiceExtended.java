package com.sunknowledge.dme.rcm.service.patientsearch;

import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDiagnosisSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDiagnosisSearchByPatIdPatDiaId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientDiagnosisSearchServiceExtended {

    Flux<PatientDiagnosisDTO> getPatientDiagnosisBySearchParameters(PatientDiagnosisSearchByPatIdPatDiaId patientDiagnosisSearchParameterDTO);

    Long getIDByUUID(UUID patientDiagnosisUuid);

    Flux<PatientDiagnosisDTO> getPatientDiagnosisByPatientId(Long patientId);
    Mono<Object> getPatientDiagnosisByPatientDiagnosisId(Long patDiagnosisId);
    Mono<Object> getCurrentPatientDiagnosisByMaxId(Long patientId);
}
