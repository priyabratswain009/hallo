package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.service.dto.PatientInsuranceDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientInsuranceSearchRepositoryExtended extends PatientInsuranceRepository{
    @Query(value = "select patient.get_t_patient_insurance_id_by_uuid(:patientUuid)")
    Mono<Long> getIDByUUID(UUID patientUuid);

    @Query(value = "select distinct(payer_level) FROM patient.t_patient_insurance WHERE patient_id = :patientId and lower(status) = lower('Active')")
    Flux<String> getExixtingPayerLevelsByPatientUUID(@Param("patientId") Long patientId);

    @Query( value = "select * from patient.t_patient_insurance where patient_insurance_id  =:patientInsId")
    Mono<PatientInsuranceDTO> getPatientInsuranceInfoByPatientInsId(@Param("patientInsId") Long patientInsId);

    @Query( value ="select patient_id from patient.t_patient_insurance where patient_insurance_uuid =:patientInsuranceUUID")
    Mono<Long> getPatientIdByPatientInsuranceUUID(@Param("patientInsuranceUUID") UUID patientInsuranceUUID);
}
