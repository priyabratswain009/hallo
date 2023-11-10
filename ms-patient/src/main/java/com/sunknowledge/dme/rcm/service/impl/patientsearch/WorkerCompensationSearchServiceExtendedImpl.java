package com.sunknowledge.dme.rcm.service.impl.patientsearch;

import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDiagnosisSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.WorkerCompensationSearchParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDiagnosisSearchByPatIdPatDiaId;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDiagnosisSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.WorkerCompensationSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientDiagnosisSearchQueryHandler;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.WorkerCompensationSearchQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service("workerCompensationSearchServiceExtended")
public class WorkerCompensationSearchServiceExtendedImpl implements WorkerCompensationSearchServiceExtended {
    @Autowired
    WorkerCompensationSearchQueryHandler workerCompensationSearchQueryHandler;

    @Override
    public Flux<WorkersCompensationDTO> getWorkerCompensationBySearchParameters(WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer obj) {

        return workerCompensationSearchQueryHandler.getWorkerCompensationBySearchParameters(obj);
    }

    @Override
    public Long getIDByUUID(UUID workersCompensationUuid) {
        return workerCompensationSearchQueryHandler.getIDByUUID(workersCompensationUuid);
    }

    @Override
    public Flux<WorkersCompensationDTO> getWorkerCompensationByPatientId(Long patientId){
        return workerCompensationSearchQueryHandler.getWorkerCompensationByPatientId(patientId);
    }

    @Override
    public Mono<Object> getWorkerCompensationByWorkerCompensationId(Long workersCompensationId){
        return workerCompensationSearchQueryHandler.getWorkerCompensationByWorkerCompensationId(workersCompensationId);
    }

    @Override
    public Mono<Object> getCurrentWorkerCompensationByMaxId(Long patientId){
        return workerCompensationSearchQueryHandler.getCurrentWorkerCompensationByMaxId(patientId);
    }
}
