package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientInsuranceVerifSearchRepositoryExtended extends PatientInsVerifStatRepository{

    @Query(value="select patient.get_t_patient_ins_verif_stat_id_by_uuid(:patientInsVerifStatUuid)")
    Mono<Long> getIDByUUID(UUID patientInsVerifStatUuid);

    @Query(value = "select * from patient.t_patient_ins_verif_stat where patient_insurance_id =:patientInsId and insurance_verif_id = (select max(insurance_verif_id) from patient.t_patient_ins_verif_stat where patient_insurance_id =:patientInsId)")
    Mono<PatientInsVerifStatDTO> findByPatientInsIdForPatientInsuranceVerificationId(@Param("patientInsId") Long patientInsId);

    @Query(value="select * from patient.t_patient_ins_verif_stat where patient_insurance_id =:patientInsuranceId")
    Flux<PatientInsVerifStatDTO> findByPatientInsId(@Param("patientInsuranceId") Long patientInsuranceId);

    @Query(value = "select * from patient.t_patient_ins_verif_stat where insurance_verif_id = \n" +
        "(select max(insurance_verif_id)  from patient.t_patient_ins_verif_stat where patient_insurance_id = :patientInsId)\n" +
        "and patient_insurance_id = :patientInsId")
    Mono<PatientInsVerifStatDTO> findLatestByPatientInsId(@Param("patientInsId") Long patientInsId);
}
