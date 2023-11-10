package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDoctorMap;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

public interface PatientDoctorMappingRepositoryExtended extends PatientDoctorMapRepository{

    @Query("select * from patient.t_patient_doctor_map tpdm " +
        "where tpdm.patient_id = :patientId and tpdm.doctor_npi_number = :doctorNpiNumber and lower(tpdm.status) = 'active'")
    Mono<PatientDoctorMap> findByPatientIdAndNpi(@Param("patientId") Long patientId, @Param("doctorNpiNumber") String doctorNpiNumber);
}
