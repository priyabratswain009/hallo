package com.sunknowledge.dme.rcm.service.patiententry;

import com.sunknowledge.dme.rcm.domain.PatientDocument;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PatientDocumentEntryServiceExtended {
    Flux<PatientDocument> uploadPatientDocument(Flux<FilePart> patientFilePartFlux, String patientDocumentStatus, String description,
                                       String documentType, PatientMasterDTO patientMasterDTO, List<String> documentNoList);

    Flux<PatientDocument> uploadPatientDocumentByPath(String[] documentNameList, String patientDocumentStatus, String description,
                                                      String documentType, PatientMasterDTO patientMasterDTO, List<String> documentNoList,
                                                      Boolean isCloudStorage, String operationType);

    Mono<String> generateDocumentNo(String documentType);

    //Mono<ResponseDTO> savePatientDocument(SavePatientDocumentDetailsCommand obj);
    //Mono<ResponseDTO> uploadPatientDocument(Mono<FilePart> patientFilePartMono, Long patientDocumentId, String fileNameWithExtension);
}
