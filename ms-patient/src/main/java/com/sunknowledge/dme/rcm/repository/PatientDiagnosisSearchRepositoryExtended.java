package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDiagnosis;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientDiagnosisSearchRepositoryExtended extends PatientDiagnosisRepository{

    @Query(value="select patient.get_t_patient_diagnosis_id_by_uuid(:patientDiagnosisUuid)")
    Mono<Long> getIDByUUID(UUID patientDiagnosisUuid);

    @Query(value="SELECT * FROM patient.t_patient_diagnosis\n" +
        "WHERE (patient_diagnosis_id, created_date) =\n" +
        "(SELECT MAX(patient_diagnosis_id), MAX(created_date)\n" +
        "FROM patient.t_patient_diagnosis where\n" +
        "patient_id=:patientId) and patient_id=:patientId")
    Mono<PatientDiagnosis> findByMaxIdAndMaxCreatedDate(Long patientId);
}
