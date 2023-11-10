package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.service.dto.PatientCombinedSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientMasterSearchRepositoryExtended extends PatientMasterRepository {

    @Query(value = "select patient.get_patient_id_no()")
    Mono<String> generatePatientIDNumber();

    @Query(value = "select patient.get_t_patient_master_id_by_uuid(:patientUuid)")
    Mono<Long> getIDByUUID(UUID patientUuid);

    @Query(value = "select pos_name from masterdata.mv_t_pos_master where pos_id = :posId")
    Mono<String> getPosNameById(@Param("posId") Long posId);

    //@Query(value = "select patient.patientsearch(:patientidno, :patientfirstname, :patientmiddlename, :patientlastname, :pdob, :ssnno, :addressline1, :addressline2, :pcity, :patientstate, :pzip, :phone)")
    @Query(value = "select * from patient.patientsearch(:patientidno, :patientfirstname, :patientmiddlename, :patientlastname, :pdob, :ssnno, :addressline1, :addressline2, :pcity, :patientstate, :pzip, :phone, :branchId)AS\n" +
        "(\t\n" +
        "    patient_first_name  character varying COLLATE pg_catalog.\"default\",\n" +
        "    patient_middle_name character varying COLLATE pg_catalog.\"default\",\n" +
        "\tpatient_last_name character varying COLLATE pg_catalog.\"default\",\n" +
        "\tdob character varying COLLATE pg_catalog.\"default\",\n" +
        "\tgender character varying COLLATE pg_catalog.\"default\",\n" +
        "\tssn character varying COLLATE pg_catalog.\"default\",\n" +
        "\tpatient_id_number character varying COLLATE pg_catalog.\"default\",\n" +
        "\tbranch_name character varying COLLATE pg_catalog.\"default\",\n" +
        "\tbilling_address_line1 character varying COLLATE pg_catalog.\"default\",\n" +
        "\tbilling_address_line2 character varying COLLATE pg_catalog.\"default\",\n" +
        "\tcity character varying COLLATE pg_catalog.\"default\",\n" +
        "\tstate character varying COLLATE pg_catalog.\"default\",\n" +
        "\tzip character varying COLLATE pg_catalog.\"default\",\n" +
        "\tcontact_no1 character varying COLLATE pg_catalog.\"default\",\n" +
        "\tcontact_no2 character varying COLLATE pg_catalog.\"default\",\n" +
        "\tfax character varying COLLATE pg_catalog.\"default\",\n" +
        "\tefax character varying COLLATE pg_catalog.\"default\",\n" +
        "\temail character varying COLLATE pg_catalog.\"default\",\n" +
        "\tpatient_master_uuid uuid,\n" +
        "\tinsurance_name  character varying COLLATE pg_catalog.\"default\",\n" +
        "\tpolicy_num character varying COLLATE pg_catalog.\"default\",\n" +
        "\tmember_id  character varying COLLATE pg_catalog.\"default\"\n" +
        ")")
    Flux<PatientCombinedSearchOutputDTO> getPatientByCombinedSearchParameters(
        @Param("patientidno") String patientidno,
        @Param("patientfirstname") String patientfirstname,
        @Param("patientmiddlename") String patientmiddlename,
        @Param("patientlastname") String patientlastname,
        @Param("pdob") String pdob,
        @Param("ssnno") String ssnno,
        @Param("addressline1") String addressline1,
        @Param("addressline2") String addressline2,
        @Param("pcity") String pcity,
        @Param("patientstate") String patientstate,
        @Param("pzip") String pzip,
        @Param("phone") String phone,
        @Param("branchId") Long branchId
    );

    @Query(value = "select * from patient.t_patient_master where patient_id = :patientId and lower(status) = 'active'")
    Mono<PatientMasterDTO> findByPatientId(@Param("patientId") Long patientId);

    @Query(value = "select branch_name from masterdata.mv_t_branch_office where branch_id=:branchId")
    Mono<String> getBranchNameByBranchId(@Param("branchId") Long branchId);
}
