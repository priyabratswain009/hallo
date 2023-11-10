package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PatientDocument;
import reactor.core.publisher.Flux;

public interface PatientDocumentsSoRepositoryExtended extends PatientDocumentRepository{
    Flux<PatientDocument> findByPatientId(String patientId);

    Flux<PatientDocument> findByPatientIdNo(String patientIdNo);

    Flux<PatientDocument> findByPatientDocumentNo(String patientDocumentNo);
}
