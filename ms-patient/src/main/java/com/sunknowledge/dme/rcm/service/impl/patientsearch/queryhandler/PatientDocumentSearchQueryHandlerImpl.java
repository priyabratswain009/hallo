package com.sunknowledge.dme.rcm.service.impl.patientsearch.queryhandler;

import com.sunknowledge.dme.rcm.repository.PatientDocumentSearchRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDocumentMapper;
import com.sunknowledge.dme.rcm.service.patientsearch.queryhandler.PatientDocumentSearchQueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class PatientDocumentSearchQueryHandlerImpl implements PatientDocumentSearchQueryHandler {

    private final Logger log = LoggerFactory.getLogger(PatientDocumentSearchQueryHandlerImpl.class);
    @Autowired
    PatientDocumentSearchRepositoryExtended patientDocumentSearchRepositoryExtended;

    private final PatientDocumentMapper patientDocumentMapper;

    public PatientDocumentSearchQueryHandlerImpl(PatientDocumentMapper patientDocumentMapper) {
        this.patientDocumentMapper = patientDocumentMapper;
    }

//    @Override
//    public String getDocumentFilePathByDocumentIdQueryHandler(PatientDocumentFilePathByDocumentId obj) {
//        try {
//            return patientDocumentSearchRepositoryExtended.findById(obj.getPatientDocumentId())
//                .map(patientDocumentMapper::toDto).toFuture().get().getFileUploadPath();
//        } catch (InterruptedException e) {
//            log.error("==========> Exception=" + e);
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            log.error("==========> Exception=" + e);
//            throw new RuntimeException(e);
//        }
//    }

//    @Override
//    public Flux<PatientDocumentDTO> getPatientDocumentDetailsBySearchParameters(PatientDocumentDetailsByPatientInfoOrDocumentInfo queryObj) {
//        try {
//            if (queryObj.getPatientDocumentId() != null && queryObj.getPatientDocumentId() > 0) {
//                return Flux.from(patientDocumentSearchRepositoryExtended.findById(queryObj.getPatientDocumentId())
//                    .map(patientDocumentMapper::toDto).filter(x ->
//                        x.getStatus().trim().equalsIgnoreCase("active")
//                    ));
//            } else if (queryObj.getPatientId() != null && queryObj.getPatientId() > 0) {
//                return patientDocumentSearchRepositoryExtended.findAll()
//                    .map(patientDocumentMapper::toDto).filter(
//                        x -> x.getPatientId() == queryObj.getPatientId() &&
//                            x.getStatus().trim().equalsIgnoreCase("active")
//                    );
//            } else if (queryObj.getPatientName() != null && !queryObj.getPatientName().trim().equals("")
//                && queryObj.getPatientDob() != null && queryObj.getPatientDob().isBefore(LocalDate.now())) {
//                return patientDocumentSearchRepositoryExtended.findAll()
//                    .map(patientDocumentMapper::toDto).filter(x ->
//                        CommonUtilities.mergeName(x.getPatientFirstName(), x.getPatientMiddleName(), x.getPatientLastName())
//                            .contains(queryObj.getPatientName().trim())
//                            && x.getPatientDob().compareTo(queryObj.getPatientDob()) == 0 &&
//                            x.getStatus().trim().equalsIgnoreCase("active")
//                    );
//            } else if (queryObj.getPatientSsn() != null && !queryObj.getPatientSsn().trim().equals("")) {
//                return patientDocumentSearchRepositoryExtended.findAll()
//                    .map(patientDocumentMapper::toDto).filter(x ->
//                        x.getPatientSsn().equals(queryObj.getPatientSsn().trim()) &&
//                            x.getStatus().trim().equalsIgnoreCase("active")
//                    );
//            } else if (queryObj.getEmail() != null && !queryObj.getEmail().trim().equals("")) {
//                return patientDocumentSearchRepositoryExtended.findAll()
//                    .map(patientDocumentMapper::toDto).filter(x ->
//                        x.getEmail().equals(queryObj.getEmail().trim()) &&
//                            x.getStatus().trim().equalsIgnoreCase("active")
//                    );
//            } else {
//                return patientDocumentSearchRepositoryExtended.findAll()
//                    .map(patientDocumentMapper::toDto).filter(x ->
//                        ((
//                            queryObj.getPatientName() != null &&
//                                CommonUtilities.mergeName(x.getPatientFirstName(), x.getPatientMiddleName(), x.getPatientLastName())
//                                    .contains(queryObj.getPatientName().trim()) &&
//                                !queryObj.getPatientName().trim().equals("")
//                        )
//                            ||
//                            (
//                                queryObj.getDocumentName() != null &&
//                                    x.getDocumentName().contains(queryObj.getDocumentName().trim()) &&
//                                    !queryObj.getDocumentName().trim().equals("")
//                            )) &&
//                            x.getStatus().trim().equalsIgnoreCase("active")
//                    );
//            }
//        } catch (Exception e) {
//            log.error("==========> Exception=" + e);
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public Long getIDByUUID(UUID patientDocumentUuid) {
        try {
            return patientDocumentSearchRepositoryExtended.getIDByUUID(patientDocumentUuid).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flux<PatientDocumentDTO> getPatientDocumentByPatientId(Long patientId){
        try {
            return patientDocumentSearchRepositoryExtended.findAll()
                .map(patientDocumentMapper::toDto)
                .filter(x ->
                    (
                        x.getPatientId() == patientId
                    )
                        && x.getStatus().trim().equalsIgnoreCase("active")
                );
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<Object> getPatientDocumentByPatientDocumentId(Long patDocumentId) {
        try {
            return patientDocumentSearchRepositoryExtended.findById(patDocumentId)
                .map(patientDocumentMapper::toDto);
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }
    //Mono<Object> getCurrentPatientDocumentByMaxId(Long patientId)
    @Override
    public Mono<Object> getCurrentPatientDocumentByMaxId(Long patientId) {
        try {
            return patientDocumentSearchRepositoryExtended.findByMaxIdAndMaxCreatedDate(patientId)
                .map(patientDocumentMapper::toDto);
        } catch (Exception e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }
}
