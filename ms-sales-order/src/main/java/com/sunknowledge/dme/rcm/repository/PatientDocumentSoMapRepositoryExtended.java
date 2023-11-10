package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDocumentSoMap;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.DocumentsBySoIdOutputDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface PatientDocumentSoMapRepositoryExtended extends PatientDocumentSoMapRepository{
    Flux<PatientDocumentSoMap> findBySoNo(@Param("soNo") String soNo);
    Flux<PatientDocumentSoMap> findPatientDocumentsSoDataByPatientIdNo(String patientIdNo);
    Flux<PatientDocumentSoMap> findPatientDocumentsSoDataByPatientDocumentId(String patientDocumentId);

    @Query(value = "select patient_document_repository_name from patient.t_patient_document where patient_document_uuid IN (:patientDocumentUuid)")
    Flux<String> getFileWithPathByPatientDocumentUuid(List<UUID> patientDocumentUuid);

    @Query(value = "select * from so.documents_for_so(:soId) as\n" +
        "(\n" +
        "\t\tso_id bigint,\n" +
        "\t\tdocument_type character varying,\n" +
        "\t\tdocument_no character varying,\n" +
        "\t\tdocument_name character varying\n" +
        ")")
    Flux<DocumentsBySoIdOutputDTO> findBySoId(@Param("soId") Long soId);

    @Query(value = "select patient_id from so.t_sales_order_master where sales_order_no = :soNo and lower(status)  = lower('active')")
    Mono<Long> getPatientIdBySoNo(@Param("soNo") String soNo);
    @Query(value = "select patient_id_number from patient.t_patient_master where patient_id = :patientId  and lower(status)  = lower('active')")
    Mono<String> getPatientIdNoByPatientId(@Param("patientId") Long patientId);
}
