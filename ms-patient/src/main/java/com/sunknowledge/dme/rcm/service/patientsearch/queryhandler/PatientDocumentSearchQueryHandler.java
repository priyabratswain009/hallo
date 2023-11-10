package com.sunknowledge.dme.rcm.service.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.service.dto.PatientDocumentDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientDocumentSearchQueryHandler {
    //String getDocumentFilePathByDocumentIdQueryHandler(PatientDocumentFilePathByDocumentId obj);

    //Flux<PatientDocumentDTO> getPatientDocumentDetailsBySearchParameters(PatientDocumentDetailsByPatientInfoOrDocumentInfo queryObj);

    Long getIDByUUID(UUID patientDocumentUuid);

    Flux<PatientDocumentDTO> getPatientDocumentByPatientId(Long patientId);

    Mono<Object> getPatientDocumentByPatientDocumentId(Long patDocumentId);
    Mono<Object> getCurrentPatientDocumentByMaxId(Long patientId);
}
