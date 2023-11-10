package com.sunknowledge.dme.rcm.service.patientsearch;

import com.sunknowledge.dme.rcm.domain.PatientDocument;
import reactor.core.publisher.Flux;

public interface PatientDocumentsSoDetailsServiceExtended{
    Flux<PatientDocument> getPatientDocumentDetailsByPatientId(String patientId);

    Flux<PatientDocument> getPatientDocumentSoDetailsByPatientIdNo(String patientIdNo);

    Flux<PatientDocument> getPatientDocumentSoDetailsByPatientDocumentNo(String patientDocumentNo);
}
