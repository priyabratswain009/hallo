package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDocument;
import org.springframework.data.r2dbc.repository.Query;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientDocumentSearchRepositoryExtended extends PatientDocumentRepository{
    @Query(value = "select patient.get_t_patient_document_id_by_uuid(:patientDocumentUuid)")
    Mono<Long> getIDByUUID(UUID patientDocumentUuid);

    @Query(value = "SELECT * FROM patient.t_patient_document tpd \n" +
        "WHERE (patient_document_id , uploaded_date) =\n" +
        "(SELECT MAX(patient_document_id), MAX(uploaded_date)\n" +
        "FROM patient.t_patient_document where\n" +
        "patient_id=:patientId) and patient_id=:patientId")
    Mono<PatientDocument> findByMaxIdAndMaxCreatedDate(Long patientId);
}
