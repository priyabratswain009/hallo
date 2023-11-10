package com.sunknowledge.dme.rcm.service.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDiagnosisSearchByPatIdPatDiaId;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface WorkerCompensationSearchQueryHandler {
    Flux<WorkersCompensationDTO> getWorkerCompensationBySearchParameters(WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer obj);

    Long getIDByUUID(UUID workersCompensationUuid);

    Flux<WorkersCompensationDTO> getWorkerCompensationByPatientId(Long patientId);

    Mono<Object> getWorkerCompensationByWorkerCompensationId(Long workersCompensationId);

    Mono<Object> getCurrentWorkerCompensationByMaxId(Long patientId);
}
