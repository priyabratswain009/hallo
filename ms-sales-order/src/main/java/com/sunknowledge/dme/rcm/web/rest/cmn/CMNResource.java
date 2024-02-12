package com.sunknowledge.dme.rcm.web.rest.cmn;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.properties.FileDownloadUtilityService;
import com.sunknowledge.dme.rcm.domain.Cmn;
import com.sunknowledge.dme.rcm.domain.CmnDocumentMaster;
import com.sunknowledge.dme.rcm.dto.cmn.*;
import com.sunknowledge.dme.rcm.repository.cmn.CmnDocumentMasterRepo;
import com.sunknowledge.dme.rcm.repository.cmn.CmnRepo;
import com.sunknowledge.dme.rcm.service.cmn.CMNService;
import com.sunknowledge.dme.rcm.service.dto.CmnDTO;
import com.sunknowledge.dme.rcm.service.dto.CmnDocumentMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.CmnDocumentMasterMapper;
import com.sunknowledge.dme.rcm.service.mapper.CmnMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * @author Bimal K Sahoo
 * @since 03/02/2023
 */
@RestController
@RequestMapping("/api/cmn")
@Slf4j
@CrossOrigin("http://localhost:8080")
public class CMNResource {
    @Autowired
    private CMNService cmnService;
    @Autowired
    private FileDownloadUtilityService fileDownloadUtilityService;
    @Autowired
    private CmnRepo cmnRepository;
    @Autowired
    private CmnMapper cmnMapper;
    @Autowired
    private CmnDocumentMasterMapper cmnDocumentMasterMapper;
    @Autowired
    private CmnDocumentMasterRepo cmnDocumentMasterRepository;

    @ApiOperation(value = "Search of Active CMN/ SWO For Sales Order")
    @PostMapping(value = "/searchActiveCMNForSalesOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ServiceOutcome<CmnSearchResponse>> searchActiveCMNForSalesOrder(@RequestBody SearchCMNInputParameters searchCMNInputParameters) throws ExecutionException, InterruptedException {
        return cmnService.searchActiveCMNForSalesOrder(searchCMNInputParameters);
    }

    @ApiOperation(value = "Search of Initiated CMN/ SWO For Sales Order")
    @PostMapping(value = "/searchInitiatedCMNForSalesOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ServiceOutcome<CmnSearchResponse>> searchInitiatedCMNForSalesOrder(@RequestBody SearchCMNInputParameters searchCMNInputParameters) throws ExecutionException, InterruptedException {
        return cmnService.searchInitiatedCMNForSalesOrder(searchCMNInputParameters);
    }

    @ApiOperation(value = "Prepare and Print CMN Report")
    @PostMapping(value="/prepareAndPrintCMNReport", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ServiceOutcome<CmnResponseDetails>> prepareAndPrintCMNReport(@RequestParam("cmnId") Long cmnId) {
        Mono<ServiceOutcome<CmnResponseDetails>> outcome = null;
        try {
            outcome = cmnService.prepareAndPrintCMNReportOnCmn(cmnId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return outcome;
    }

    @ApiOperation(value = "Upload CMN Document Details")
    @PostMapping(value = "/uploadCMNDocument", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ServiceOutcome<CmnResponseDetails>> uploadPatientDocument(@RequestPart Mono<FilePart> document, @RequestParam("cmnId") Long cmnId,
                                                                          @RequestParam("lengthOfNeed") String lengthOfNeed,
                                                                          @RequestParam("refillAuthorized") String refillAuthorized,
                                                                          @RequestParam("frequencyCount") String frequencyCount,
                                                                          @RequestParam("frequencyInterval") String frequencyInterval,
                                                                          @RequestParam("cmnDocumentType") String cmnDocumentType) throws Exception {
        return document
            .filter(part -> part instanceof FilePart)
            .ofType(FilePart.class)
            .flatMap(r -> {
                try {
                    return cmnService.saveUploadedCmnDocument(r, cmnService.convertParameters(cmnId, lengthOfNeed, refillAuthorized, frequencyCount, frequencyInterval, cmnDocumentType));
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            })
            .flatMap(uploadOutcome -> {
                try {
                    return cmnService.documentFileOperation(uploadOutcome, cmnService.convertParameters(cmnId, lengthOfNeed, refillAuthorized, frequencyCount, frequencyInterval, cmnDocumentType));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
    }

    @ApiOperation(value = "Updating CMN Details")
    @PostMapping(value = "/updateCMNDetails")
    public Mono<ServiceOutcome<CmnDTO>> updateCMNDetails(@RequestBody CmnRequestInput cmnRequestInput){
//      ServiceOutcome<CmnDTO> outcome = cmnService.updateCMNDetails(cmnRequestInput);
        return Mono.justOrEmpty(null);
    }

    @ApiOperation(value = "Fetch To Verify CMN Data")
    @GetMapping(value = "/fetchToVerifyCMNData")
    public Mono<ServiceOutcome<CmnDataDocumentDetails>> fetchToVerifyCMNData(@RequestParam("cmnId") Long cmnId) throws ExecutionException, InterruptedException {
        ServiceOutcome<CmnDataDocumentDetails> outcome = cmnService.fetchToVerifyCMNData(cmnId);
        return Mono.justOrEmpty(outcome);
    }

    @ApiOperation(value = "Logging to CMN")
    @PostMapping(value = "/loggingCMN")
    public Mono<ServiceOutcome<CmnDataDocumentDetails>> loggingCMN(@RequestParam("cmnId") Long cmnId) throws ExecutionException, InterruptedException {
        ServiceOutcome<CmnDataDocumentDetails> outcome = cmnService.loggingCMN(cmnId);
        return Mono.justOrEmpty(outcome);
    }

    @ApiOperation(value = "Send Or Fax CMN Report")
    @PostMapping(value = "/sendOrFaxCMNReport")
    public Mono<ServiceOutcome<CmnFaxDetails>> sendOrFaxCMNReport(@RequestBody CmnFaxDetails cmnFaxDetails) throws ExecutionException, InterruptedException {
        ServiceOutcome<CmnFaxDetails> outcome = cmnService.sendOrFaxCMNReport(cmnFaxDetails);
        return Mono.justOrEmpty(outcome);
    }

    @ApiOperation(value = "Receive Fax Cmn Response Report")
    @PostMapping(value = "/receiveFaxCmnResponseReport")
    public Mono<ServiceOutcome<CmnFaxDetails>> receiveFaxCmnResponseReport(@RequestBody CmnFaxDetails cmnFaxDetails) throws ExecutionException, InterruptedException {
//        ServiceOutcome<CmnFaxDetails> outcome = cmnService.receiveFaxCmnResponseReport(cmnFaxDetails);
        return cmnService.receiveFaxCmnResponseReport(cmnFaxDetails);
//        return Mono.justOrEmpty(null);
    }

    @ApiOperation(value = "Receive Fax Cmn Response Report")
    @PostMapping(value = "/receiveFaxCmnResponseReportTest")
    public Mono<Cmn> receiveFaxCmnResponseReportTest(@RequestBody CmnFaxDetails cmnFaxDetails) throws ExecutionException, InterruptedException {
        log.info("==============================>>>>>>CmnNumber====>"+cmnFaxDetails.getCmnId());
        return cmnRepository.findById(cmnFaxDetails.getCmnId()).map(Optional::of).defaultIfEmpty(Optional.empty())
            .flatMap(cmn -> {
                log.info("===============>>>>"+cmn.get().getCmnNumber());
                return Mono.justOrEmpty(cmn);
            });
    }

    @ApiOperation(value = "Download CMN Document File")
    @GetMapping("/downloadCmnDocumentFiles/{filename:.+}")
    public ResponseEntity<Flux<DataBuffer>> downloadCmnDocumentFiles(@PathVariable String filename, String filetype) {
        String moduleName = "CMN";
        Flux<DataBuffer> file = fileDownloadUtilityService.loadFileAsReactiveDocumentResource(moduleName, filename, filetype);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
            .contentType(MediaType.APPLICATION_OCTET_STREAM).body(file);
    }

    @ApiOperation(value = "Test")
    @GetMapping(path = "/test", produces = "application/json")
    @ResponseBody
    public String test() {
        log.info("=======================TEST==========================");
        String resultOutcomeJson = "My CMN Resource";
        try {
            System.out.println("=======================My Test Service=======================");
            System.out.println("JSON RESULT:"+resultOutcomeJson);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return resultOutcomeJson;
    }

    @ApiOperation(value = "Prepare and Get CMN Data")
    @PostMapping(value="/prepareCMNDataOut", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ServiceOutcome<CMNWrittenOrderOutputDTO>> prepareCMNDataOut(@RequestParam("cmnId") Long cmnId) {
        Mono<ServiceOutcome<CMNWrittenOrderOutputDTO>> outcome = null;
        try {
            System.out.println("=======================generateCMNReport========================="+cmnId);
            outcome = cmnService.prepareCMNDataOut(cmnId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return outcome;
    }

    @ApiOperation(value = "Insert CMN Data")
    @PostMapping(value="/prepareCMNDataIn", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ServiceOutcome<CMNWrittenOrderOutputDTO>> prepareCMNDataIn(@RequestBody CMNWrittenOrderOutputDTO cmnWrittenOrderOutputDTO) {
        Mono<ServiceOutcome<CMNWrittenOrderOutputDTO>> outcome = null;
        try {
            System.out.println("=======================Input JSON Data ========================="+cmnWrittenOrderOutputDTO);
            outcome = cmnService.prepareCMNDataIn(cmnWrittenOrderOutputDTO);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return outcome;
    }

    @ApiOperation(value = "Prepare and Print CMN Report S3")
    @PostMapping(value="/prepareAndPrintCMNReportOnCmnForAwsS3", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ServiceOutcome<CmnResponseDetails>> prepareAndPrintCMNReportOnCmnForAwsS3(@RequestParam("cmnId") Long cmnId) {
        Mono<ServiceOutcome<CmnResponseDetails>> outcome = null;
        try {
            CmnDTO cmnDTO = cmnService.getCMNMasterDataByCmnId(cmnId).toFuture().get();
            SWODataDTO swoDataDTO = cmnService.getSWODataOnSalesOrderReactive(cmnDTO.getSalesOrderId()).toFuture().get();
            List<EquipmentDetailsDTO> EquipmentDetailsList = cmnService.getEquipmentDetailsOnSalesOrderReactive(cmnDTO.getSalesOrderId()).collectList().toFuture().get();
            CmnDocumentMaster cmnDocumentMaster = cmnService.getCmnDocumentByCmnId(cmnDTO.getCmnId()).toFuture().get();
            log.info("====cmnDTO===="+cmnDTO+"====swoDataDTO===="+swoDataDTO+"====EquipmentDetailsList===="+EquipmentDetailsList+"====cmnDocumentMaster===="+cmnDocumentMaster);
            CmnDocumentMasterDTO cmnDocumentMasterDTO = cmnService.saveCmnDocumentInReactive(cmnDTO,  cmnDTO.getCmnNumber() + ".pdf", "First", cmnDocumentMaster).toFuture().get();
            //cmnService.updateCMNDetailsInReactive(cmnDTO, "PRINT", cmnDTO.getCmnNumber() + ".pdf", cmnDocumentMaster, CmnDocumentMasterDTO);
            outcome = cmnService.prepareAndPrintCMNReportOnCmnForAwsS3(cmnDTO, swoDataDTO, EquipmentDetailsList, cmnDocumentMaster, cmnDocumentMasterDTO, "PRINT");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return outcome;
    }
}

