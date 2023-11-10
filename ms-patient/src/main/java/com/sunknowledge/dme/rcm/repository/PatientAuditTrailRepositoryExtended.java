package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.service.dto.audittrail.UserActivityReportDTO;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface PatientAuditTrailRepositoryExtended extends PatientMasterAuditLogRepository {

    @Query(value = "SELECT  (case when dml_type = 'CREATE' then\n" +
        "         new_row_data->>'created_by_id'\n" +
        "         else\n" +
        "         new_row_data->>'updated_by_id' end) as user_id,\n" +
        "         (case when dml_type = 'CREATE' then\n" +
        "         new_row_data->>'created_by_name'\n" +
        "         else\n" +
        "         new_row_data->>'updated_by_name' end) as user_name,  \n" +
        "        dml_timestamp::date as date, count(*) as count_of_so_updates, \n" +
        "        ARRAY_TO_STRING(ARRAY_AGG (distinct patient_id_number), ',') as patient_id_number,\n" +
        "        ARRAY_TO_STRING(ARRAY_AGG (distinct new_row_data->>'patient_id'||' => '\n" +
        "        ||coalesce(patient_id_number, '')), ',') as so_ids FROM\n" +
        "(\n" +
        "SELECT * FROM\n" +
        "(\n" +
        "SELECT patint_id as ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id, \n" +
        "    'Patient Master' AS section\n" +
        "\tFROM patient.t_patient_master_audit_log \n" +
        "    WHERE dml_timestamp::date between :fromDate and :toDate\n" +
        "    UNION ALL\n" +
        "SELECT paent_insrnce_id as ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "    'Patient Insurance' AS section\n" +
        "\tFROM patient.t_patient_insurance_audit_log\n" +
        "    WHERE dml_timestamp::date between :fromDate and :toDate\n" +
        "    UNION ALL\n" +
        "SELECT patnt_cliical_infoation_id as ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "\t'Patient Clinical' AS section\n" +
        "    FROM patient.t_patient_clinical_information_audit_log\n" +
        "    WHERE dml_timestamp::date between :fromDate and :toDate\n" +
        "    UNION ALL\n" +
        "SELECT patint_diagois_id as ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "\t'Patient Diagnosis' AS section\n" +
        "    FROM patient.t_patient_diagnosis_audit_log\n" +
        "    WHERE dml_timestamp::date between :fromDate and :toDate\n" +
        "    UNION ALL\n" +
        "SELECT paent_dctor_map_id as ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "\t'Patient Doctor Map' AS section\n" +
        "    FROM patient.t_patient_doctor_map_audit_log\n" +
        "    WHERE dml_timestamp::date between :fromDate and :toDate\n" +
        "    UNION ALL\n" +
        "SELECT ptient_docmt_id as ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "\t'Patient Document' AS section\n" +
        "    FROM patient.t_patient_document_audit_log\n" +
        "    WHERE dml_timestamp::date between :fromDate and :toDate\n" +
        "    UNION ALL\n" +
        "SELECT insrnce_vrif_id as ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "\t'Patient Insurance Verification' AS section\n" +
        "    FROM patient.t_patient_ins_verif_stat_audit_log\n" +
        "    WHERE dml_timestamp::date between :fromDate and :toDate\n" +
        "    UNION ALL\n" +
        "SELECT worers_comenation_id as ref_id, old_row_data, new_row_data, dml_type, dml_timestamp, dml_created_by, id,\n" +
        "\t'Workers Compensation' AS section\n" +
        "    FROM patient.t_workers_compensation_audit_log\n" +
        "    WHERE dml_timestamp::date between :fromDate and :toDate\n" +
        ") AS a \n" +
        "LEFT JOIN\n" +
        "(SELECT patient_id, patient_id_number FROM patient.t_patient_master) AS b\n" +
        "on a.new_row_data->>'patient_id'=b.patient_id::text\n" +
        ") as g WHERE dml_type <> 'DELETE' \n" +
        "group by user_id, user_name, dml_timestamp::date, dml_type")
    Flux<UserActivityReportDTO> findUserActivityReportByDate(LocalDate fromDate, LocalDate toDate);

}
