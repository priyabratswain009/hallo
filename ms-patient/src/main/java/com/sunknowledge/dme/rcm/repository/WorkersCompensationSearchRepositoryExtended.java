package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.WorkersCompensation;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface WorkersCompensationSearchRepositoryExtended extends WorkersCompensationRepository{

    @Query(value = "select patient.get_t_workers_compensation_id_by_uuid(:workersCompensationUuid)")
    Mono<Long> getIDByUUID(UUID workersCompensationUuid);

    @Query(value = "SELECT * FROM patient.t_workers_compensation\n" +
        "WHERE (workers_compensation_id , created_date) =\n" +
        "(SELECT MAX(workers_compensation_id), MAX(created_date)\n" +
        "FROM patient.t_workers_compensation where\n" +
        "patient_id=:patientId) and patient_id=:patientId")
    Mono<WorkersCompensation> findByMaxIdAndMaxCreatedDate(Long patientId);
}
