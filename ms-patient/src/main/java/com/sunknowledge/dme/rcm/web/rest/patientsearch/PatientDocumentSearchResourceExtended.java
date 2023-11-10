package com.sunknowledge.dme.rcm.web.rest.patientsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.PatientDocument;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.patientsearch.PatientDocumentSearchInputParameterDTO;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDocumentSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientDocumentsSoDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.patientsearch.PatientMasterSearchServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * REST controller for PatientDocumentSearchResourceExtended.
 */
@Validated
@RestController
@RequestMapping("/api")
public class PatientDocumentSearchResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientDocumentSearchResourceExtended.class);
    @Autowired
    @Qualifier("PatientDocumentSearchServiceExtended")
    PatientDocumentSearchServiceExtended patientDocumentSearchServiceExtended;
    @Autowired
    PatientMasterSearchServiceExtended patientMasterSearchServiceExtended;
    @Autowired
    PatientDocumentsSoDetailsServiceExtended patientDocumentsSoDetailsServiceExtended;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    /**
     * Download Patient Document
     */
//    @GetMapping(value = "/downloadPatientDocument/{patientDocumentId}", produces = APPLICATION_OCTET_STREAM_VALUE)
//    public Mono<ResponseEntity<Resource>> downloadCsv(
//        @NotNull(message = "Patient_Document_Id must be provided.")
//        @Min(value = 1, message = "Patient_Document_Id must be greater than or equal to 1")
//        @PathVariable("patientDocumentId") Long patientDocumentId
//    ) {
//        String location = patientDocumentSearchServiceExtended.downloadPatientDocument(patientDocumentId);
//        return Mono.<Resource>fromCallable(() -> {
//                String path = Paths.get(location).toAbsolutePath().normalize().toString();
//                return new FileSystemResource(path);
//            })
//            .subscribeOn(Schedulers.boundedElastic())
//            .<ResponseEntity<Resource>>flatMap(resource -> {
//                HttpHeaders headers = new HttpHeaders();
//                String fileName = (location.replace("\\", "##").split("##")[2] == null) ?
//                    "" : location.replace("\\", "##").split("##")[2];
//                headers.setContentDispositionFormData(fileName, fileName);
//                return Mono.just(ResponseEntity
//                    .ok().cacheControl(CacheControl.noCache())
//                    .headers(headers)
//                    .body(resource));
//            });
//    }

    /**
     * Get Patient Document Details
     */
//    @GetMapping(value = "/getPatientDocumentDetailsBySearchParameters")
//    public Mono<ResponseDTO> getPatientDocumentDetailsBySearchParameters(
//        @ModelAttribute PatientDocumentSearchParameterDTO patientDocumentSearchParameterDTO
//    ) {
//        try {
//            //----- Implementing UUID_To_ID Bridge Method ----------
//            // ----If UUID not required, then pass UUID as null ----------
//            Long id = 0L;
//            if (patientDocumentSearchParameterDTO.getPatientDocumentUuid() != null) {
//                id = patientDocumentSearchServiceExtended.getIDByUUID(patientDocumentSearchParameterDTO.getPatientDocumentUuid());
//            }
//            Long pMasid = 0L;
//            if (patientDocumentSearchParameterDTO.getPatientMasterUuid() != null) {
//                pMasid = patientMasterSearchServiceExtended.getIDByUUID(patientDocumentSearchParameterDTO.getPatientMasterUuid());
//            }
//            PatientDocumentDetailsByPatientInfoOrDocumentInfo obj = new PatientDocumentDetailsByPatientInfoOrDocumentInfo();
//            obj.setPatientDocumentId(id);
//            obj.setPatientId(pMasid);
//            obj.setDocumentName(patientDocumentSearchParameterDTO.getDocumentName());
//            obj.setPatientName(patientDocumentSearchParameterDTO.getPatientName());
//            obj.setPatientDob(patientDocumentSearchParameterDTO.getPatientDob());
//            obj.setPatientSsn(patientDocumentSearchParameterDTO.getPatientSsn());
//            obj.setEmail(patientDocumentSearchParameterDTO.getEmail());
//            List<PatientDocumentDTO> list = patientDocumentSearchServiceExtended.getPatientDocumentDetailsBySearchParameters(obj).collectList().toFuture().get();
//            if (list == null || list.size() == 0) {
//                return Mono.just(new ResponseDTO<PatientDocumentDTO>(false, "Data Not Found.", list));
//            } else {
//                //======== Convert List To Json Removing Specific Value =============
//                String keyToRemove = "patientDocumentId";
//                JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
//                //======== Convert List To Json Removing Specific Value =============
//                return Mono.just(new ResponseDTO<PatientDocumentDTO>(true, "Successfully Fetched.", jarr));
//            }
//        }catch (InterruptedException e) {
//            log.error("==========> Exception=" + e);
//            throw new RuntimeException(e);
//        }catch (ExecutionException e) {
//            log.error("==========> Exception=" + e);
//            throw new RuntimeException(e);
//        }
//    }

    /******Inputs:: parameterValue should be patient id no or patient uuid, based on operationType.
     * operationType can be patientIdNo or patientUuid
     * *******/
    @GetMapping(value = "/getPatientDocumentDetailsBySearchParameters")
    public Mono<ResponseDTO> getPatientDocumentDetailsBySearchParameters(
        @ModelAttribute PatientDocumentSearchInputParameterDTO patientDocumentSearchInputParameterDTO
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long patientId = null;
            Flux<PatientDocument> patientDocumentsSoMapDetails = null;
            if (patientDocumentSearchInputParameterDTO.getParameterValue() != null && patientDocumentSearchInputParameterDTO.getOperationType().equals("patientUuid")) {
                String patientUuid = patientDocumentSearchInputParameterDTO.getParameterValue();
                patientId = patientMasterSearchServiceExtended.getIDByUUID(UUID.fromString(patientUuid)).toFuture().get();
                patientDocumentsSoMapDetails = patientDocumentsSoDetailsServiceExtended.getPatientDocumentDetailsByPatientId(patientId+"");
                System.out.println("====patientUuid======patientId===="+patientId);
                System.out.println("=====patientDocumentsSoMapDetails===="+patientDocumentsSoMapDetails.collectList().toFuture().get());
                //patientDocumentsSoMapDetails.subscribe(System.out::println);

            } else if (patientDocumentSearchInputParameterDTO.getParameterValue() != null && patientDocumentSearchInputParameterDTO.getOperationType().equals("patientIdNo")) {
                patientDocumentsSoMapDetails = patientDocumentsSoDetailsServiceExtended.getPatientDocumentSoDetailsByPatientIdNo("'" + patientDocumentSearchInputParameterDTO.getParameterValue() + "'");
                patientDocumentsSoMapDetails.subscribe(System.out::println);

                System.out.println("=====patientDocumentsSoMapDetails===="+patientDocumentsSoMapDetails.collectList().toFuture().get());
            } else if (patientDocumentSearchInputParameterDTO.getParameterValue() != null && patientDocumentSearchInputParameterDTO.getOperationType().equals("patientDocumentNo")) {
                patientDocumentsSoMapDetails = patientDocumentsSoDetailsServiceExtended.getPatientDocumentSoDetailsByPatientDocumentNo("'" + patientDocumentSearchInputParameterDTO.getParameterValue() + "'");
                patientDocumentsSoMapDetails.subscribe(System.out::println);

                System.out.println("=====patientDocumentsSoMapDetails===="+patientDocumentsSoMapDetails.collectList().toFuture().get());
            }

            if (patientDocumentsSoMapDetails != null && patientDocumentsSoMapDetails.collectList().toFuture().get().size() >0) {
                return Mono.just(new ResponseDTO<>(true, "Successfully Fetched.", patientDocumentsSoMapDetails.collectList().toFuture().get()));

            } else {
                return Mono.just(new ResponseDTO<PatientDocumentDTO>(false, "Data Not Found.", null));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("getPatientDocumentByPatientUuid")
    public Mono<ResponseDTO> getPatientDocumentByPatientUuid(
        @RequestParam("patientUuid") UUID patientUuid
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long patientId = 0L;
            if (patientUuid != null) {
                patientId = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
            }

            List<PatientDocumentDTO> list = patientDocumentSearchServiceExtended.getPatientDocumentByPatientId(patientId).collectList().toFuture().get();
            if (list == null || list.size() == 0) {
                return Mono.just(new ResponseDTO<PatientDocumentDTO>(false, "Data Not Available.", null));
            } else {
                //======== Convert List To Json Removing Specific Value =============
//                String keyToRemove = "patientClinicalInformationId";
//                JSONArray jarr = CommonUtilities.convertListToJsonRemovingSpecificValue(list, keyToRemove);
                //======== Convert List To Json Removing Specific Value =============
                return Mono.just(new ResponseDTO<Object>(true, "Successfully Fetched.", list.size() > 0 ? list : null));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("getPatientDocumentByPatientDocumentUuid")
    public Mono<ServiceOutcome<PatientDocumentDTO>> getPatientDocumentByPatientDocumentUuid(
        @RequestParam("patientDocumentUuid") UUID patientDocumentUuid
    ) {
        try {
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long patDocumentId = 0L;
            if (patientDocumentUuid != null) {
                patDocumentId = patientDocumentSearchServiceExtended.getIDByUUID(patientDocumentUuid);
            }

            PatientDocumentDTO patientDocumentDTO = (PatientDocumentDTO) patientDocumentSearchServiceExtended.getPatientDocumentByPatientDocumentId(patDocumentId).toFuture().get();
            if (patientDocumentDTO == null || patientDocumentDTO.getPatientDocumentId() == null) {
                return Mono.just(new ServiceOutcome<PatientDocumentDTO>(null, false, "No Data Available."));
            } else {
                return Mono.just(new ServiceOutcome<PatientDocumentDTO>(patientDocumentDTO, true, "Successfully Fetched."));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("getCurrentPatientDocumentByMaxId")
    public Mono<ServiceOutcome<PatientDocumentDTO>> getCurrentPatientDocumentByMaxId(@RequestParam("patientUuid") UUID patientUuid) {
        try {
            Long patientId = 0L;
            if (patientUuid != null) {
                patientId = patientMasterSearchServiceExtended.getIDByUUID(patientUuid).toFuture().get();
            }

            PatientDocumentDTO patientDocumentDTO = (PatientDocumentDTO) patientDocumentSearchServiceExtended.getCurrentPatientDocumentByMaxId(patientId).toFuture().get();
            if (patientDocumentDTO == null || patientDocumentDTO.getPatientDocumentId() == null) {
                return Mono.just(new ServiceOutcome<PatientDocumentDTO>(null, false, "No Data Available."));
            } else {
                return Mono.just(new ServiceOutcome<PatientDocumentDTO>(patientDocumentDTO, true, "Successfully Fetched."));
            }
        } catch (InterruptedException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("==========> Exception=" + e);
            throw new RuntimeException(e);
        }
    }
}
