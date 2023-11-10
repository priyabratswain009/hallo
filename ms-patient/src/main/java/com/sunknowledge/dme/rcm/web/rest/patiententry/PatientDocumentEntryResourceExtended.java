package com.sunknowledge.dme.rcm.web.rest.patiententry;

import com.sunknowledge.dme.rcm.amazon.AmazonS3Service;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.mapper.PatientDocumentMapper;
import com.sunknowledge.dme.rcm.service.patiententry.PatientDocumentEntryServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDocumentSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
/**
 * REST controller for SalesOrderInsuranceDetailsSearchResource.
 */
@Validated
@RestController
@RequestMapping("/api")
public class PatientDocumentEntryResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientDocumentEntryResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("patientDocumentEntryServiceExtendedImpl")
    PatientDocumentEntryServiceExtended patientDocumentEntryServiceExtended;
    @Autowired
    PatientDocumentSearchServiceExtended patientDocumentSearchServiceExtended;
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Autowired
    AmazonS3Service amazonS3Service;
    @Autowired
    PatientDocumentMapper patientDocumentMapper;


    /**
     * Save Patient Document Information
     */
//    @PatchMapping(value = "savePatientDocumentDetails", consumes = {"application/json", "application/merge-patch+json"})
//    public Mono<ResponseDTO> savePatientDocument(
//        @RequestBody @Valid PatientDocumentParameterDTO patientDocumentParameterDTO
//    ) {
//        //----- Implementing UUID_To_ID Bridge Method ----------
//        Long id = 0L;
//        if (patientDocumentParameterDTO.getPatientDocumentUUID() != null) {
//            id = patientDocumentSearchServiceExtended.getIDByUUID(patientDocumentParameterDTO.getPatientDocumentUUID());
//            id = id == null ? 0L : id;
//        }
//        Long pMasid = 0L;
//        if (patientDocumentParameterDTO.getPatientUUID() != null) {
//            pMasid = patientMasterSearchServiceExtended.getIDByUUID(patientDocumentParameterDTO.getPatientUUID());
//            pMasid = pMasid == null ? 0L : pMasid;
//        }
//        SavePatientDocumentDetailsCommand obj = new SavePatientDocumentDetailsCommand();
//        BeanUtils.copyProperties(patientDocumentParameterDTO,obj);
//        obj.setPatientId(pMasid);
//        obj.setPatientDocumentId(id);
//        return patientDocumentEntryServiceExtended.savePatientDocument(obj);
//    }

    /**
     *
     * @param patientFilePartFlux
     * @param patientUUID
     * @param patientDocumentStatus
     * @param description
     * @param documentType
     * @param noOfFiles
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */

    @PostMapping(value = "/uploadPatientDocument/{patientUUID}/{patientDocumentStatus}/{description}/{documentType}/{noOfFiles}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseDTO> uploadPatientDocument(
        @NotNull(message = "Patient_Document_File must be required.")
        @RequestPart(name = "patientFile", required = true) Flux<FilePart> patientFilePartFlux,
        @NotNull(message = "Patient_UUID must be provided.")
        @PathVariable(name = "patientUUID") UUID patientUUID,
        @NotNull(message = "Patient_Document_Status must be provided.")
        @NotBlank(message = "Patient_Document_Status must be provided.")
        @PathVariable(name = "patientDocumentStatus") String patientDocumentStatus,
        @NotNull(message = "Description must be provided.")
        @NotBlank(message = "Description must be provided.")
        @PathVariable(name = "description") String description,
        @NotNull(message = "Document_Type must be provided.")
        @NotBlank(message = "Document_Type must be provided.")
        @PathVariable(name = "documentType") String documentType,
        @PathVariable(name = "noOfFiles") int noOfFiles

    ) throws IOException, ExecutionException, InterruptedException {
        //----- Implementing UUID_To_ID Bridge Method ----------
        Long id = 0L;
        if (patientUUID != null) {
            id = patientMasterSearchServiceExtended.getIDByUUID(patientUUID).toFuture().get();
            id = id == null ? 0L : id;
            System.out.println("id "+id);
        }
        log.info("======== uploadPatientDocument called ===============");
        Mono<PatientMasterDTO> patientMasterDTO = patientMasterSearchServiceExtended.getPatientDetailsByPatientId(id);
        patientMasterDTO.subscribe(System.out::println);
        PatientMasterDTO obj = patientMasterDTO.toFuture().get();
        System.out.println("obj "+ obj);
        //Long noOfDocs = 3l;
        /*int noOfDocs = patientFilePartFlux.collectList().toFuture().get().size();

        System.out.println("noOfDocs "+noOfDocs);*/
        List<String> documentNoList = new ArrayList<>();
        for(long i=0; i<noOfFiles; i++) {
            //System.out.println("noOfDocs2 " + noOfDocs);
            documentNoList.add(patientDocumentEntryServiceExtended.generateDocumentNo(documentType).toFuture().get());
        }
        System.out.println("documentNoList "+documentNoList);
        //amazonS3Service.saveUploadedFileToS3BucketFile();
        //Flux<FilePart> patientFilePartFlux = Mono.just(patientFilePartList).flatMapMany(Flux::fromIterable);
        return patientDocumentEntryServiceExtended.uploadPatientDocument(
            patientFilePartFlux, patientDocumentStatus,description,documentType,obj,documentNoList).collectList().map(x->{
                return new ResponseDTO(true,"",x);
        });

        /*return Flux.just(new ResponseDTO<>(patientDocumentMono != null?true:false,
            patientDocumentMono != null?"Data Saved Sucessfully":"Data Not Saved",
            List.of(patientDocumentMono.toFuture().get())));*/

    }

    @PostMapping(value = "/uploadPatientDocumentByPath/{documentNames}/{patientUUID}/{patientDocumentStatus}/{description}/{documentType}/{isCloudStorage}/{operationType}")
    public Mono<ResponseDTO> uploadPatientDocumentByPath(
        @RequestParam(name = "documentNames") String documentNameList,
        @NotNull(message = "Patient_UUID must be provided.")
        @RequestParam(name = "patientUUID") UUID patientUUID,
        @NotNull(message = "Patient_Document_Status must be provided.")
        @NotBlank(message = "Patient_Document_Status must be provided.")
        @RequestParam(name = "patientDocumentStatus") String patientDocumentStatus,
        @NotNull(message = "Description must be provided.")
        @NotBlank(message = "Description must be provided.")
        @RequestParam(name = "description") String description,
        @NotNull(message = "Document_Type must be provided.")
        @NotBlank(message = "Document_Type must be provided.")
        @RequestParam(name = "documentType") String documentType,
        @RequestParam(name = "isCloudStorage") boolean isCloudStorage,
        @RequestParam(name="operationType") String operationType
    ) throws IOException, ExecutionException, InterruptedException {
        //----- Implementing UUID_To_ID Bridge Method ----------
        System.out.println("=======documentNameList======"+documentNameList);
        Long id = 0L;
        if (patientUUID != null) {
            id = patientMasterSearchServiceExtended.getIDByUUID(patientUUID).toFuture().get();
            id = id == null ? 0L : id;
            System.out.println("id "+id);
        }
        log.info("============== uploadPatientDocumentByPath called ============");
        Mono<PatientMasterDTO> patientMasterDTO = patientMasterSearchServiceExtended.getPatientDetailsByPatientId(id);
        PatientMasterDTO obj = patientMasterDTO.toFuture().get();
        // Remove square brackets and spaces
        documentNameList = documentNameList.replaceAll("\\[|\\]|\\s", "");

        // Split by commas and trim each UUID
        String[] uuids = documentNameList.split(",");
        System.out.println("documentNameList "+uuids);
        List<String> documentNoList = new ArrayList<>();
        for(int i=0; i<uuids.length; i++) {
            documentNoList.add(patientDocumentEntryServiceExtended.generateDocumentNo(documentType).toFuture().get());
        }

        return patientDocumentEntryServiceExtended.uploadPatientDocumentByPath(uuids,
             patientDocumentStatus,description,documentType,obj,documentNoList,isCloudStorage, operationType).collectList().map(x->{
            return new ResponseDTO(true,"",patientDocumentMapper.toDto(x));
        });


        /*return Mono.just(new ResponseDTO<>(null != null?true:false,
            null != null?"Data Saved Sucessfully":"Data Not Saved",
            List.of(null)));*/
    }

//    @PostMapping(value = "/uploadPatientDocument/{patientDocumentUUID}/{fileNameWithExtension}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public Mono<ResponseDTO> uploadPatientDocument(
//        @NotNull(message = "Patient_Document_File must be required.")
//        @RequestPart(name = "patientFile", required = true) Mono<FilePart> patientFilePartMono,
//        @NotNull(message = "Patient_Document_UUID must be provided.")
//        @PathVariable(value = "patientDocumentUUID", required = true) final UUID patientDocumentUUID,
//        @NotNull(message = "File_Name_With_Extension must be provided.")
//        @NotBlank(message = "File_Name_With_Extension must be provided.")
//        @PathVariable(value = "fileNameWithExtension", required = true) final String fileNameWithExtension
//    ) throws IOException {
//        Long patientDocumentId = 0L;
//        if (patientDocumentUUID != null) {
//            patientDocumentId = patientDocumentSearchServiceExtended.getIDByUUID(patientDocumentUUID);
//            patientDocumentId = patientDocumentId == null ? 0L : patientDocumentId;
//        }
//        return patientDocumentEntryServiceExtended.uploadPatientDocument(patientFilePartMono, patientDocumentId, fileNameWithExtension);
//    }
}
