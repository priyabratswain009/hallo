package com.sunknowledge.dme.rcm.service.patientsearch;

import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDiagnosisSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.WorkerCompensationSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface WorkerCompensationSearchServiceExtended {
    Flux<WorkersCompensationDTO> getWorkerCompensationBySearchParameters(WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer obj);

    Long getIDByUUID(UUID workersCompensationUuid);

    Flux<WorkersCompensationDTO> getWorkerCompensationByPatientId(Long patientId);

    Mono<Object> getWorkerCompensationByWorkerCompensationId(Long workersCompensationId);

    Mono<Object> getCurrentWorkerCompensationByMaxId(Long patientId);
}
