package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDoctorMap;
import com.sunknowledge.dme.rcm.service.dto.patiententry.PatientDoctorMappingOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDoctorMapPatientMasterExtendedDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientDoctorMappingSearchRepositoryExtended extends PatientDoctorMapRepository{
    @Query(value = "select patient.get_t_patient_doctor_map_id_by_uuid(:patientDiagnosisUuid)")
    Mono<Long> getIDByUUID(UUID patientDiagnosisUuid);

    @Query(value = "SELECT * FROM patient.t_patient_doctor_map\n" +
        "WHERE (patient_doctor_map_id , created_date) =\n" +
        "(SELECT MAX(patient_doctor_map_id), MAX(created_date)\n" +
        "FROM patient.t_patient_doctor_map where\n" +
        "patient_id=:patientId) and patient_id=:patientId")
    Mono<PatientDoctorMap> findByMaxIdAndMaxCreatedDate(Long patientId);

    @Query(value = "SELECT a.patient_doctor_map_id, a.patient_id, a.created_by_id, a.created_date, a.status, a.created_by_name, a.updated_by_name, a.updated_by_id,\n" +
        "a.patient_doctor_map_uuid, a.updated_date, a.doctor_first_name, a.doctor_middle_name, a.doctor_last_name, a.doctor_name_suffix, \n" +
        "a.doctor_address_line_1 as doctor_address_line1, a.doctor_address_line_2 as doctor_address_line2, a.doctor_address_city, a.doctor_address_state,\n" +
        "a.doctor_address_zip, a.doctor_contact_1 as doctor_contact1, a.doctor_contact_2 as doctor_contact2, a.doctor_fax, a.doctor_npi_number,\n" +
        "a.doctor_gender, a.doctor_taxonomy_code, a.doctor_taxonomy_description, a.doctor_practice_state, a.doctor_license_number,\n" +
        "b.patient_first_name, b.patient_middle_name, b.patient_last_name, b.dob, b.patient_dod, b.gender,\n" +
        "CASE\n" +
        "    WHEN b.patient_middle_name <> ''\n" +
        "    THEN CONCAT_WS(' ', b.patient_first_name, b.patient_middle_name, b.patient_last_name)\n" +
        "    ELSE CONCAT_WS(' ', b.patient_first_name, b.patient_last_name)\n" +
        "END AS full_name\n" +
        "from  patient.t_patient_doctor_map a\n" +
        "INNER JOIN  patient.t_patient_master b ON a.patient_id = b.patient_id\n" +
        "where a.patient_id = :patientId and lower(a.status) = lower('active') and lower(b.status) =lower('active')")
    public Flux<PatientDoctorMapPatientMasterExtendedDTO>  getPatientDoctorsByPatientId(@Param("patientId") Long patientId);

    @Query(value = "select tpdm.*,tpdm.doctor_address_line_1 as \"doctor_address_line_i\" ,\n" +
        "tpdm.doctor_address_line_2 as \"doctor_address_line_ii\",tpdm.doctor_contact_1 as \"doctor_contact_i\",\n" +
        "tpdm.doctor_contact_2 as \"doctor_contact_ii\",mtdm.doctor_id,mtdm.email,mtdm.country_name,mtdm.efax from patient.t_patient_doctor_map tpdm \n" +
        "join masterdata.mv_t_doctor_master mtdm on tpdm.doctor_npi_number = mtdm.npi_number\n" +
        "where tpdm.patient_id = :patientId and tpdm.doctor_npi_number = :doctorNpiNumber and lower(tpdm.status) = 'active'\n" +
        "and lower(mtdm.status) = 'active' limit 1\n" +
        "\n")
    Mono<PatientDoctorMappingOutputDTO> getPatientDoctorMappingBySearchParameters(@Param("doctorNpiNumber") String doctorNpiNumber,
                                                                                  @Param("patientId") Long patientId);
}
