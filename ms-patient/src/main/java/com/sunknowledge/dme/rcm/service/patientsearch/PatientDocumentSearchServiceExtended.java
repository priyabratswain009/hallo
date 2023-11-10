package com.sunknowledge.dme.rcm.service.patientsearch;

import com.sunknowledge.dme.rcm.service.dto.PatientDocumentDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientDocumentSearchServiceExtended {

//    String downloadPatientDocument(Long patientDocumentId);
//
//    Flux<PatientDocumentDTO> getPatientDocumentDetailsBySearchParameters(PatientDocumentDetailsByPatientInfoOrDocumentInfo obj);

    Long getIDByUUID(UUID patientDocumentUuid);

    Flux<PatientDocumentDTO> getPatientDocumentByPatientId(Long patientId);

    Mono<Object> getPatientDocumentByPatientDocumentId(Long patDocumentId);

    Mono<Object> getCurrentPatientDocumentByMaxId(Long patientId);
}
