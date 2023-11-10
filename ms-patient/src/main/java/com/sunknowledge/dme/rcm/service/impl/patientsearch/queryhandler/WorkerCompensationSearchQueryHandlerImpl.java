package com.sunknowledge.dme.rcm.service.impl.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.repository.PatientDiagnosisSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.WorkersCompensationSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientDiagnosisDTO;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.PatientDiagnosisSearchByPatIdPatDiaId;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.query.WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer;
import com.sunknowledge.dme.rcm.service.impl.patientsearch.FunctionalAbilitySearchServiceExtendedImpl;
import com.sunknowledge.dme.rcm.service.mapper.PatientDiagnosisMapper;
import com.sunknowledge.dme.rcm.service.mapper.WorkersCompensationMapper;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientDiagnosisSearchQueryHandler;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.WorkerCompensationSearchQueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class WorkerCompensationSearchQueryHandlerImpl implements WorkerCompensationSearchQueryHandler {

    private final Logger log = LoggerFactory.getLogger(WorkerCompensationSearchQueryHandlerImpl.class);
    @Autowired
    WorkersCompensationSearchRepositoryExtended workersCompensationSearchRepositoryExtended;

    private final WorkersCompensationMapper workersCompensationMapper;

    public WorkerCompensationSearchQueryHandlerImpl(WorkersCompensationMapper workersCompensationMapper) {
        this.workersCompensationMapper = workersCompensationMapper;
    }

    @Override
    public Flux<WorkersCompensationDTO> getWorkerCompensationBySearchParameters(WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer obj) {
        try {
            return workersCompensationSearchRepositoryExtended.findAll()
                .map(workersCompensationMapper::toDto)
                .filter(x ->
                    (
                        (x.getPatientId() == obj.getPatientId() && obj.getWorkersCompensationId() == 0) ||
                            (x.getWorkersCompensationId() == obj.getWorkersCompensationId() && obj.getPatientId() == 0)
                            || (obj.getWorkersCompensationId() == 0 && obj.getPatientId() == 0
                            && obj.getInsuredEmployer() != null
                            && !obj.getInsuredEmployer().trim().equals("")
                            && x.getInsuredEmployer().contains(obj.getInsuredEmployer().trim())
                        )
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getIDByUUID(UUID workersCompensationUuid) {
        try {
            return workersCompensationSearchRepositoryExtended.getIDByUUID(workersCompensationUuid).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flux<WorkersCompensationDTO> getWorkerCompensationByPatientId(Long patientId){
        try {
            return workersCompensationSearchRepositoryExtended.findAll()
                .map(workersCompensationMapper::toDto)
                .filter(x ->
                    (
                        x.getPatientId() == patientId
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<Object> getWorkerCompensationByWorkerCompensationId(Long workersCompensationId) {
        try {
            return workersCompensationSearchRepositoryExtended.findById(workersCompensationId)
                .map(workersCompensationMapper::toDto);
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }
    //Mono<Object> getCurrentWorkerCompensationByMaxId(Long patientId)
    @Override
    public Mono<Object> getCurrentWorkerCompensationByMaxId(Long patientId) {
        try {
            return workersCompensationSearchRepositoryExtended.findByMaxIdAndMaxCreatedDate(patientId)
                .map(workersCompensationMapper::toDto);
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }
}
