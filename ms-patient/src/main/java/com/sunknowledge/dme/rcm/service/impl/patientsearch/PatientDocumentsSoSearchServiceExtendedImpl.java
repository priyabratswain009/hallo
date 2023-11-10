package com.sunknowledge.dme.rcm.service.impl.patientsearch;

import com.sunknowledge.dme.rcm.domain.PatientDocument;
import com.sunknowledge.dme.rcm.repository.PatientDocumentsSoRepositoryExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDocumentsSoDetailsServiceExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PatientDocumentsSoSearchServiceExtendedImpl implements PatientDocumentsSoDetailsServiceExtended {
    @Autowired
    PatientDocumentsSoRepositoryExtended patientDocumentRepository;

    @Override
    public Flux<PatientDocument> getPatientDocumentDetailsByPatientId(String patientId) {
        return patientDocumentRepository.findByPatientId(patientId);
    }

    @Override
    public Flux<PatientDocument> getPatientDocumentSoDetailsByPatientIdNo(String patientIdNo) {
        return patientDocumentRepository.findByPatientIdNo(patientIdNo);
    }

    @Override
    public Flux<PatientDocument> getPatientDocumentSoDetailsByPatientDocumentNo(String patientDocumentNo){
        return patientDocumentRepository.findByPatientDocumentNo(patientDocumentNo);
    }
}
