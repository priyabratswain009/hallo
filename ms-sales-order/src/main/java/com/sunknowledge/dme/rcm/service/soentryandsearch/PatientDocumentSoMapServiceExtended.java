package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.domain.PatientDocumentSoMap;
import com.sunknowledge.dme.rcm.service.PatientDocumentSoMapService;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentSoMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.DocumentsBySoIdOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.PatientDocumentDetailsInputExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.PatientSoIdAndSoNoOutputExtendedDTO;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface PatientDocumentSoMapServiceExtended extends PatientDocumentSoMapService {
    Flux<PatientDocumentSoMap> getPatientIdNoBySoNo(String soNo);

    Flux<ResponseDTO> attachPatientDocumentsSoData(String patientIdNo, List<LinkedHashMap<String, Object>> patientDocumentsList, List<String> duplicate, Long soId, String soNo) throws ExecutionException, InterruptedException, IOException, ParseException;

    Flux<ResponseDTO> attachPatientDocumentsSoDataByPatientUuid(String parameterValue, String soNo, List<PatientDocumentSoMap> patientDocumentSoMapList,
                                                                List<PatientDocumentSoMap> patientDocumentSoMapAllDataList,
                                                                PatientSoIdAndSoNoOutputExtendedDTO patientSoIdAndSoNoOutputExtendedDTO ) throws ExecutionException, InterruptedException, IOException, ParseException;

    Flux<ResponseDTO> attachPatientDocumentsSoDataByPatientIdNo(String parameterValue, String soNo, List<PatientDocumentSoMap> patientDocumentSoMapAllDataList,
                                                                PatientSoIdAndSoNoOutputExtendedDTO patientSoIdAndSoNoOutputExtendedDTO) throws ExecutionException, InterruptedException, IOException, ParseException;

    Flux<ResponseDTO> attachPatientDocumentsSoDataBySoNo(String parameterValue, String soNo, String patientIdNo, List<PatientDocumentSoMap> patientDocumentSoMapAllDataList,
                                                         PatientSoIdAndSoNoOutputExtendedDTO patientSoIdAndSoNoOutputExtendedDTO) throws ExecutionException, InterruptedException, IOException, ParseException;

    Flux<ResponseDTO> attachPatientDocumentsSoDataByPatientDocumentNo(String parameterValue, String soNo, List<PatientDocumentSoMap> patientDocumentSoMapAllDataList,
                                                                      PatientSoIdAndSoNoOutputExtendedDTO patientSoIdAndSoNoOutputExtendedDTO) throws ExecutionException, InterruptedException, IOException, ParseException;
    PatientDocumentSoMapDTO savePatientDocumentSoMap(PatientDocumentSoMapDTO patientDocumentSoMapDTO);

    ResponseDTO getPatientDocumentsDataByPatientIdNo(String patientIdNo, String operationType) throws IOException, ParseException;

    Flux<PatientDocumentSoMap> uploadPatientDocument(Flux<FilePart> patientFilePartFlux, Long soId, String soNo, UUID patientUUID,
                                                     String patientDocumentStatus, String description, String documentType);
    Flux<String> getFileWithPathByPatientDocumentUuid(List<UUID> patientDocumentUuid);
    Mono<ResponseEntity<ByteArrayResource>> downloadPatientDocument(List<Path> filePaths) throws IOException;

    Mono<ResponseDTO> getAllPatientFiles() throws IOException;

    Mono<ResponseDTO> movePatientDocumentsPddAndPidAndPmrToSpecializedDirectory(PatientDocumentDetailsInputExtendedDTO patientDocumentDetailsInputExtendedDTO, PatientSoIdAndSoNoOutputExtendedDTO patientSoIdAndSoNoOutputExtendedDTO) throws IOException, ExecutionException, InterruptedException;

    Mono<PatientSoIdAndSoNoOutputExtendedDTO> getSoIdAndSoNoBySoUUID(String salesOrderMasterUuid);
    Flux<DocumentsBySoIdOutputDTO> getAllDocumentsBySoID(Long soId);
    Mono<Long> getPatientIdBySoNo(String soNo);
    Mono<String> getPatientIdNoByPatientId(Long patientId);
    Flux<PatientDocumentSoMap> findPatientDocumentsSoDataByPatientIdNo(String patientIdNo);

    Mono<PatientDocumentSoMapDTO> savePatientSoDocumentMap(PatientDocumentSoMapDTO patientDocumentSoMapDTO);

    Flux<PatientDocumentSoMap> uploadPatientDocumentFromSOAndSaveInMap(String documentNameList, UUID patientUUID, String patientDocumentStatus,
                                                                       String description, String documentType, boolean isCloudStorage, String upload,
                                                                       Long soId, String soNo, String folderName);
}
