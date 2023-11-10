package com.sunknowledge.dme.rcm.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientDtoEntryRepositoryExtended extends PatientDtoRepository {

    @Query("CALL patient.so_create_patient_dto(:patientUUID)")
    Mono<Void> generatePatientBucket(@Param("patientUUID") UUID patientUUID);

}
