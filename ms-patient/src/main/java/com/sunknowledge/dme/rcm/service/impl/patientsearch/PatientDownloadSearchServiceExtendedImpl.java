package com.sunknowledge.dme.rcm.service.impl.patientsearch;

import com.sunknowledge.dme.rcm.repository.PatientDocumentRepository;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentDTO;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDocumentSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientDocumentSearchQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service("PatientDocumentSearchServiceExtended")
public class PatientDownloadSearchServiceExtendedImpl implements PatientDocumentSearchServiceExtended {
    @Autowired
    PatientDocumentSearchQueryHandler patientDocumentSearchQueryHandler;

    @Autowired
    PatientDocumentRepository patientDocumentRepository;
//    @Override
//    public String downloadPatientDocument(Long patientDocumentId) {
//        PatientDocumentFilePathByDocumentId obj =
//            new PatientDocumentFilePathByDocumentId();
//        obj.setPatientDocumentId(patientDocumentId);
//        return patientDocumentSearchQueryHandler.getDocumentFilePathByDocumentIdQueryHandler(obj);
//    }
//
//    public Flux<PatientDocumentDTO> getPatientDocumentDetailsBySearchParameters(PatientDocumentDetailsByPatientInfoOrDocumentInfo obj){
//        return patientDocumentSearchQueryHandler.getPatientDocumentDetailsBySearchParameters(obj);
//    }

    @Override
    public Long getIDByUUID(UUID patientDocumentUuid) {
        return patientDocumentSearchQueryHandler.getIDByUUID(patientDocumentUuid);
    }

    @Override
    public Flux<PatientDocumentDTO> getPatientDocumentByPatientId(Long patientId){
        return patientDocumentSearchQueryHandler.getPatientDocumentByPatientId(patientId);
    }

    @Override
    public Mono<Object> getPatientDocumentByPatientDocumentId(Long patDocumentId){
        return patientDocumentSearchQueryHandler.getPatientDocumentByPatientDocumentId(patDocumentId);
    }

    @Override
    public Mono<Object> getCurrentPatientDocumentByMaxId(Long patientId){
        return patientDocumentSearchQueryHandler.getCurrentPatientDocumentByMaxId(patientId);
    }
}
