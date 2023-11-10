package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientClinicalInformationOutputExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientClinicalInformationPatientMasterExtendedDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientClinicalSearchRepositoryExtended extends PatientClinicalInformationRepository{

    @Query(value="select patient.get_t_patient_clinical_information_id_by_uuid(:patientClinicalInformationUUID)")
    Mono<Long> getIDByUUID(UUID patientClinicalInformationUUID);

    @Query(value="SELECT a.*, b.patient_first_name, b.patient_middle_name, b.patient_last_name, b.dob, b.patient_dod, b.gender,\n" +
        "CASE \n" +
        "    WHEN b.patient_middle_name <> '' \n" +
        "    THEN CONCAT_WS(' ', b.patient_first_name, b.patient_middle_name, b.patient_last_name)\n" +
        "    ELSE CONCAT_WS(' ', b.patient_first_name, b.patient_last_name)\n" +
        "END AS full_name\n" +
        "FROM patient.t_patient_clinical_information a \n" +
        "INNER JOIN  patient.t_patient_master b ON a.patient_id = b.patient_id \n" +
        "WHERE (a.patient_clinical_information_id, a.created_date) =\n" +
        "(SELECT MAX(patient_clinical_information_id), MAX(created_date) FROM patient.t_patient_clinical_information where\n" +
        "patient_id=:patientId) and a.patient_id=:patientId")
    Mono<PatientClinicalInformationOutputExtendedDTO> findByMaxIdAndMaxCreatedDate(Long patientId);

    @Query(value = "SELECT a.*,\n" +
        "b.patient_first_name, b.patient_middle_name, b.patient_last_name, b.dob, b.patient_dod, b.gender,\n" +
        "CASE \n" +
        "    WHEN b.patient_middle_name <> '' \n" +
        "    THEN CONCAT_WS(' ', b.patient_first_name, b.patient_middle_name, b.patient_last_name)\n" +
        "    ELSE CONCAT_WS(' ', b.patient_first_name, b.patient_last_name)\n" +
        "END AS full_name\n" +
        "from  patient.t_patient_clinical_information a \n" +
        "INNER JOIN  patient.t_patient_master b ON a.patient_id = b.patient_id \n" +
        "where a.patient_id = :patientId and lower(a.status) = lower('active') and lower(b.status) =lower('active')")
    Flux<PatientClinicalInformationPatientMasterExtendedDTO> getPatientClinicalByPatientId(@Param("patientId") Long patientId);
}
