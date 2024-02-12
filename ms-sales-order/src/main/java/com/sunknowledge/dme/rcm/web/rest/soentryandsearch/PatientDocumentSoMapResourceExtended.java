package com.sunknowledge.dme.rcm.web.rest.soentryandsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.properties.FileDownloadUtilityService;
import com.sunknowledge.dme.rcm.application.properties.FileUploadConfigProperties;
import com.sunknowledge.dme.rcm.domain.PatientDocumentSoMap;
import com.sunknowledge.dme.rcm.service.dto.PatientDocumentSoMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.DocumentsBySoIdOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.PatientDocumentDetailsInputExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.PatientDocumentSoMapInputParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.PatientSoIdAndSoNoOutputExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderPatientDocumentSearchInputDTO;
import com.sunknowledge.dme.rcm.service.soentryandsearch.PatientDocumentSoMapServiceExtended;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Validated
@RestController
@RequestMapping("/api")
public class PatientDocumentSoMapResourceExtended {
    private final Logger log = LoggerFactory.getLogger(PatientDocumentSoMapResourceExtended.class);

    @Autowired
    PatientDocumentSoMapServiceExtended patientDocumentSoMapService;

    @Autowired
    private FileDownloadUtilityService fileDownloadUtilityService;

    @Autowired
    private FileUploadConfigProperties fileUploadConfigProperties;

    /******Inputs:: parameterValue should be patient id no or patient uuid or so no, based on operationType.
     * operationType can be patientIdNo or patientUuid or soNo
     * *******/
    @GetMapping("/getPatientDocumentDetailsBySearchParameters")
    public Flux<ResponseDTO> getSODocumentsBySearchParameters(@Valid @ModelAttribute SalesOrderPatientDocumentSearchInputDTO salesOrderPatientDocumentSearchInputDTO) throws ExecutionException, InterruptedException, IOException, ParseException {
        //----- Implementing UUID_To_ID Bridge Method ----------
        ResponseDTO responseBody = new ResponseDTO();
        String patientIdNo = null;
        Flux<PatientDocumentSoMap> patientDocumentSoMapObj = null;
        if (salesOrderPatientDocumentSearchInputDTO.getParameterValue() != null && salesOrderPatientDocumentSearchInputDTO.getOperationType().equals("soNo")) {
            patientDocumentSoMapObj = patientDocumentSoMapService.getPatientIdNoBySoNo("'"+salesOrderPatientDocumentSearchInputDTO.getParameterValue()+"'");
            patientDocumentSoMapObj.subscribe(System.out::println);
            List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapObj.collectList().toFuture().get();
            if(patientDocumentSoMapList.size() == 0){
                return Flux.just(new ResponseDTO(false, "Data Not Found.", new ArrayList()));
            }
            System.out.println("======patientDocumentSoMapList====="+patientDocumentSoMapList);
            patientIdNo = patientDocumentSoMapList.get(0).getPatientIdNo();
            System.out.println("======patientIdNo====="+patientIdNo);
            //getPatientDocumentsDataByPatientIdNo function can take patientIdNo, patientUuid, patientIdNo, patientDocumentId as parameter
            return Flux.just(patientDocumentSoMapService.getPatientDocumentsDataByPatientIdNo(patientIdNo, "patientIdNo"));
        } else if (salesOrderPatientDocumentSearchInputDTO.getParameterValue() != null && salesOrderPatientDocumentSearchInputDTO.getOperationType().equals("patientUuid")) {
            return Flux.just(patientDocumentSoMapService.getPatientDocumentsDataByPatientIdNo(salesOrderPatientDocumentSearchInputDTO.getParameterValue(), "patientUuid"));
        } else if (salesOrderPatientDocumentSearchInputDTO.getParameterValue() != null && salesOrderPatientDocumentSearchInputDTO.getOperationType().equals("patientIdNo")) {
            return Flux.just(patientDocumentSoMapService.getPatientDocumentsDataByPatientIdNo(salesOrderPatientDocumentSearchInputDTO.getParameterValue(), "patientIdNo"));
        }else if (salesOrderPatientDocumentSearchInputDTO.getParameterValue() != null && salesOrderPatientDocumentSearchInputDTO.getOperationType().equals("patientDocumentNo")) {
            return Flux.just(patientDocumentSoMapService.getPatientDocumentsDataByPatientIdNo(salesOrderPatientDocumentSearchInputDTO.getParameterValue(), "patientDocumentNo"));
        } else{
            responseBody.setOutcome(false);
            responseBody.setMessage("Data Not Found.");
            responseBody.setData(responseBody.getData());

        }
        return Flux.just(responseBody);
    }

    @PostMapping(value = "/uploadPatientDocument/{soId}/{soNo}/{patientDocumentStatus}/{description}/{documentType}/{patientUUID}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseDTO> uploadPatientDocument(
        @NotNull(message = "Patient_Document_File must be required.")
        @RequestPart(name = "patientFile", required = true) Flux<FilePart> patientFilePartFlux,
        @NotNull(message = "SO_Id must be required.")
        @PathVariable("soId") Long soId,
        @NotNull(message = "SO_No must be required.")
        @NotBlank(message = "SO_No must be required.")
        @PathVariable("soNo") String soNo,
        @NotNull(message = "Patient_Document_Status must be required.")
        @NotBlank(message = "Patient_Document_Status must be required.")
        @PathVariable("patientDocumentStatus") String patientDocumentStatus,
        @NotNull(message = "Description must be required.")
        @NotBlank(message = "Description must be required.")
        @PathVariable("description") String description,
        @NotNull(message = "Document_Type must be required.")
        @NotBlank(message = "Document_Type must be required.")
        @PathVariable("documentType") String documentType,
        @NotNull(message = "Patient_UUID must be required.")
        @PathVariable("patientUUID") UUID patientUUID

    ){
        System.out.println("patientDocumentStatus "+patientDocumentStatus);
        return patientDocumentSoMapService.uploadPatientDocument(
            patientFilePartFlux,soId,soNo,patientUUID,patientDocumentStatus,description,documentType)
            .collectList()
            .map(x->new ResponseDTO<List<PatientDocumentSoMap>>(true,"",x));

    }

    /********** Send Patient Document File Path As parameter to download the files as Zip **********/
    @GetMapping(value = "/downloadPatientDocument",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Mono<ResponseEntity<ByteArrayResource>> downloadPatientDocument(@RequestParam(value="commaSeperatedpatientDocumentUuid") String commaSeperatedpatientDocumentUuid) throws ExecutionException, InterruptedException {
        //@RequestParam(value="fileWithPath[]") String[] fileWithPath
        String[] patientDocumentUuid = commaSeperatedpatientDocumentUuid.split(",");
        List<UUID> patientDocUuid = new ArrayList<>();
        for(String eachPatientDocUuid : patientDocumentUuid){
            UUID uuid = UUID.fromString(eachPatientDocUuid);
            patientDocUuid.add(uuid);
        }

        List<String> fileWithPath = patientDocumentSoMapService.getFileWithPathByPatientDocumentUuid(patientDocUuid).collectList().toFuture().get();
        List<Path> filePaths = new ArrayList<>();
        for(String filePathString : fileWithPath){
            filePaths.add(Path.of(filePathString));
        }
        System.out.println("=========filePaths=========="+filePaths);
        try {
            return patientDocumentSoMapService.downloadPatientDocument(filePaths);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/attachPatientWithSoBySearchParameters")
    public Mono<ResponseDTO> attachPatientWithSoBySearchParameters(@Valid @ModelAttribute PatientDocumentSoMapInputParameterDTO patientDocumentSoMapInputParameterDTO) throws ExecutionException, InterruptedException, IOException, ParseException {
        //----- Implementing UUID_To_ID Bridge Method ----------
        ResponseDTO responseBody = new ResponseDTO();
        List<ResponseDTO> outcome = new ArrayList<>();

        if (patientDocumentSoMapInputParameterDTO.getSoNo() != null && patientDocumentSoMapInputParameterDTO.getSoUUID() != null && !patientDocumentSoMapInputParameterDTO.getSoNo().equals("") && !patientDocumentSoMapInputParameterDTO.getSoUUID().equals("")) {

            String patientIdNo = null;
            Long patientId = patientDocumentSoMapService.getPatientIdBySoNo(patientDocumentSoMapInputParameterDTO.getSoNo()).toFuture().get();
            log.info("======patientId====="+patientId);
            //Check soId and patientIdNo for soNo
            if(patientId!=null && patientId > 0L){
                patientIdNo = patientDocumentSoMapService.getPatientIdNoByPatientId(patientId).toFuture().get();
                log.info("======patientIdNo====="+patientIdNo);
            }else{
                return Mono.just(new ResponseDTO<List<PatientDocumentSoMap>>(false,"Patient Id Does Not exist for soNo: "+patientDocumentSoMapInputParameterDTO.getSoNo(),null));
            }

            PatientSoIdAndSoNoOutputExtendedDTO patientSoIdAndSoNoOutputExtendedDTO = new PatientSoIdAndSoNoOutputExtendedDTO();
            List<PatientDocumentSoMap> patientDocumentSoMapAllDataList = null;

            if(patientDocumentSoMapInputParameterDTO.getParameterType().equals("patientUuid") && patientDocumentSoMapInputParameterDTO.getParameterValue()!=null &&  !patientDocumentSoMapInputParameterDTO.getParameterValue().equals("")){
                List<PatientDocumentSoMap> patientDocumentSoMapList = patientDocumentSoMapService.getPatientIdNoBySoNo(patientDocumentSoMapInputParameterDTO.getSoNo()).collectList().toFuture().get();
                log.info("==========patientDocumentSoMapList========"+patientDocumentSoMapList);
                patientIdNo = patientDocumentSoMapList.size() > 0 ? patientDocumentSoMapList.get(0).getPatientIdNo() : patientIdNo;
                if(patientIdNo==null){
                    return Mono.just(new ResponseDTO(false,"patientIdNo Does not Exist.",null));
                }
                patientDocumentSoMapAllDataList = patientDocumentSoMapService.findPatientDocumentsSoDataByPatientIdNo(patientIdNo)
                    .collectList().toFuture().get();
                log.info("==============patientDocumentSoMapAllDataList============="+patientDocumentSoMapAllDataList);
                patientSoIdAndSoNoOutputExtendedDTO = patientDocumentSoMapService.getSoIdAndSoNoBySoUUID(patientDocumentSoMapInputParameterDTO.getSoUUID()).toFuture().get();
                log.info("============patientSoIdAndSoNoOutputExtendedDTO============"+patientSoIdAndSoNoOutputExtendedDTO);
                //check from patient documents table
                outcome = patientDocumentSoMapService.attachPatientDocumentsSoDataByPatientUuid(patientDocumentSoMapInputParameterDTO.getParameterValue() ,
                    patientDocumentSoMapInputParameterDTO.getSoNo(), patientDocumentSoMapList, patientDocumentSoMapAllDataList,
                    patientSoIdAndSoNoOutputExtendedDTO).collectList().toFuture().get();
            }
            else if(patientDocumentSoMapInputParameterDTO.getParameterType().equals("patientIdNo") && patientDocumentSoMapInputParameterDTO.getParameterValue()!=null &&  !patientDocumentSoMapInputParameterDTO.getParameterValue().equals("")){
                patientDocumentSoMapAllDataList = patientDocumentSoMapService.findPatientDocumentsSoDataByPatientIdNo(patientIdNo).collectList().toFuture().get();
                patientSoIdAndSoNoOutputExtendedDTO = patientDocumentSoMapService.getSoIdAndSoNoBySoUUID(patientDocumentSoMapInputParameterDTO.getSoUUID()).toFuture().get();
                // will check from patient documents table
                outcome = patientDocumentSoMapService.attachPatientDocumentsSoDataByPatientIdNo(patientDocumentSoMapInputParameterDTO.getParameterValue()
                        , patientDocumentSoMapInputParameterDTO.getSoNo(), patientDocumentSoMapAllDataList, patientSoIdAndSoNoOutputExtendedDTO)
                    .collectList().toFuture().get();
            }
            else if(patientDocumentSoMapInputParameterDTO.getParameterType().equals("soNo") && patientDocumentSoMapInputParameterDTO.getParameterValue()!=null &&  !patientDocumentSoMapInputParameterDTO.getParameterValue().equals("")){
                patientDocumentSoMapAllDataList = patientDocumentSoMapService.findPatientDocumentsSoDataByPatientIdNo(patientIdNo).collectList().toFuture().get();
                patientSoIdAndSoNoOutputExtendedDTO = patientDocumentSoMapService.getSoIdAndSoNoBySoUUID(patientDocumentSoMapInputParameterDTO.getSoUUID()).toFuture().get();
                //Will Check from patient document so map table
                outcome =  patientDocumentSoMapService.attachPatientDocumentsSoDataBySoNo(patientDocumentSoMapInputParameterDTO.getParameterValue(),
                        patientDocumentSoMapInputParameterDTO.getSoNo(), patientIdNo, patientDocumentSoMapAllDataList, patientSoIdAndSoNoOutputExtendedDTO)
                    .collectList().toFuture().get();
            }
            else if(patientDocumentSoMapInputParameterDTO.getParameterType().equals("patientDocumentNo") && patientDocumentSoMapInputParameterDTO.getParameterValue()!=null &&  !patientDocumentSoMapInputParameterDTO.getParameterValue().equals("")){
                patientDocumentSoMapAllDataList = patientDocumentSoMapService.findPatientDocumentsSoDataByPatientIdNo(patientIdNo).collectList().toFuture().get();
                patientSoIdAndSoNoOutputExtendedDTO = patientDocumentSoMapService.getSoIdAndSoNoBySoUUID(patientDocumentSoMapInputParameterDTO.getSoUUID()).toFuture().get();
                //Will Check from patient document so map table
                outcome = patientDocumentSoMapService.attachPatientDocumentsSoDataByPatientDocumentNo(patientDocumentSoMapInputParameterDTO.getParameterValue(),
                    patientDocumentSoMapInputParameterDTO.getSoNo(), patientDocumentSoMapAllDataList, patientSoIdAndSoNoOutputExtendedDTO).collectList().toFuture().get();
            }
            return Mono.just(outcome.get(0));
        }
        else{
            responseBody.setOutcome(false);
            responseBody.setMessage("Failed to Attach/Tag.");
            responseBody.setData(responseBody.getData());

        }
        return Mono.just(responseBody);
    }

    @GetMapping("/getAllPatientDocuments")
    public Mono<ResponseDTO> getAllPatientDocuments() throws IOException {
        return patientDocumentSoMapService.getAllPatientFiles();
    }

    @GetMapping(value = "/movePatientDocumentsPddAndPidAndPmrToSpecializedDirectory")
    public Mono<ResponseDTO> movePatientDocumentsPddAndPidAndPmrToSpecializedDirectory(@Valid @ModelAttribute PatientDocumentDetailsInputExtendedDTO patientDocumentDetailsInputExtendedDTO) throws IOException, ExecutionException, InterruptedException {
        PatientSoIdAndSoNoOutputExtendedDTO patientSoIdAndSoNoOutputExtendedDTO = new PatientSoIdAndSoNoOutputExtendedDTO();
        if(patientDocumentDetailsInputExtendedDTO.getSoUUID()!=null && !patientDocumentDetailsInputExtendedDTO.getSoUUID().equals("")){
            patientSoIdAndSoNoOutputExtendedDTO = patientDocumentSoMapService.getSoIdAndSoNoBySoUUID(patientDocumentDetailsInputExtendedDTO.getSoUUID()).toFuture().get();  //Blocking
        }else{
            return Mono.just(new ResponseDTO<List<PatientDocumentSoMap>>(false,"Sales Order UUID Does Not Exist.",null));
        }
        return patientDocumentSoMapService.movePatientDocumentsPddAndPidAndPmrToSpecializedDirectory(patientDocumentDetailsInputExtendedDTO, patientSoIdAndSoNoOutputExtendedDTO);
    }

    @GetMapping(value = "/getDocumentsBySoID")
    public Mono<ServiceOutcome> getDocumentsBySoID(@RequestParam("soId") Long soId) throws IOException, ExecutionException, InterruptedException {
        List<DocumentsBySoIdOutputDTO> documentsList = patientDocumentSoMapService.getAllDocumentsBySoID(soId).collectList().toFuture().get();
        List<DocumentsBySoIdOutputDTO> finalDocsList = new ArrayList<>();
        if(documentsList.size() > 0){
            for(DocumentsBySoIdOutputDTO eachDocument : documentsList){
                if(eachDocument.getDocumentName()!= null && !eachDocument.getDocumentName().trim().equals("")){
                    finalDocsList.add(eachDocument);
                }
            }
            if(finalDocsList.size() > 0)
                return Mono.just(new ServiceOutcome(finalDocsList, true, "Successfully Fetched."));
            else
                return Mono.just(new ServiceOutcome(finalDocsList, false, "Data Not Found."));

        }else{
            return Mono.just(new ServiceOutcome(new ArrayList(), false, "Data Not Found."));
        }
    }

    @PostMapping("/savePatientSoDocumentMap")
    public Mono<ServiceOutcome> savePatientSoDocumentMap(@RequestBody PatientDocumentSoMapDTO patientDocumentSoMapDTO){
        //Todo - need to test
        return patientDocumentSoMapService.savePatientSoDocumentMap(patientDocumentSoMapDTO)
            .map(data -> {
                System.out.println("PatientSoDocumentMap "+ data);
                return new ServiceOutcome(data,true,"","200");
            });

    }
}
