package com.sunknowledge.dme.rcm.service.cmn;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.Cmn;
import com.sunknowledge.dme.rcm.dto.cmn.*;
import com.sunknowledge.dme.rcm.service.dto.CmnDTO;
import com.sunknowledge.dme.rcm.service.dto.CmnDocumentMasterDTO;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CMNService {
    SWODataDTO getSWODataOnSalesOrder(Long salesOrderId) throws Exception;
    List<EquipmentDetailsDTO> getEquipmentDetailsOnSalesOrder(Long salesOrderId) throws Exception;
    CmnDTO generateCmnOnSalesOrder(SWODataDTO swoDataDTO) throws Exception;
    CmnDocumentMasterDTO saveCmnDocument(CmnDTO cmnDTO, String fileName, String updateType) throws Exception;
    Mono<ServiceOutcome<CmnResponseDetails>> prepareAndPrintCMNReportOnCmn(Long cmnId);
    ServiceOutcome<CmnResponseDetails> uploadAndSaveCMNDocumentFile(Long salesOrderId, String fileName) throws Exception;
    Mono<Void> saveUploadCmnDocument1(Mono<FilePart> filePartMono, Long salesOrderId);
    Mono<ServiceOutcome<String>> saveUploadedCmnDocument(FilePart filePart, Mono<CmnResponseDocumentDetails> cmnResponseDocumentDetails) throws ExecutionException, InterruptedException;
    Mono<ServiceOutcome<CmnResponseDetails>> documentFileOperation(ServiceOutcome<String> uploadOutcome, Mono<CmnResponseDocumentDetails> cmnResponseDocumentDetails) throws Exception;
    Mono<String> deleteTmpCreatedFiles(Long salesOrderId);
    Mono<CmnResponseDocumentDetails> convertParameters(Long cmnId, String lengthOfNeed, String refillAuthorized, String frequencyCount, String frequencyInterval, String cmnDocumentType);
    Mono<ServiceOutcome<CmnSearchResponse>> searchActiveCMNForSalesOrder(SearchCMNInputParameters searchCMNInputParameters) throws ExecutionException, InterruptedException;
    Mono<ServiceOutcome<CmnSearchResponse>> searchInitiatedCMNForSalesOrder(SearchCMNInputParameters searchCMNInputParameters) throws ExecutionException, InterruptedException;
    ServiceOutcome<CmnDataDocumentDetails> fetchToVerifyCMNData(Long cmnId) throws ExecutionException, InterruptedException;
    ServiceOutcome<CmnDataDocumentDetails> loggingCMN(Long cmnId) throws ExecutionException, InterruptedException;
    ServiceOutcome<CmnFaxDetails> sendOrFaxCMNReport(CmnFaxDetails cmnFaxDetails) throws ExecutionException, InterruptedException;
    Mono<ServiceOutcome<CmnFaxDetails>> receiveFaxCmnResponseReport(CmnFaxDetails cmnFaxDetails) throws ExecutionException, InterruptedException;
    Flux<DataBuffer> loadFileAsCmnDocumentResource(String filename, String filetype);
    Mono<ServiceOutcome<CMNWrittenOrderOutputDTO>> prepareCMNDataOut(Long cmnId);
    Mono<ServiceOutcome<CMNWrittenOrderOutputDTO>> prepareCMNDataIn(CMNWrittenOrderOutputDTO cmnWrittenOrderOutputDTO);

    //============================= CMN Sub-routines For Sales Order Item Details Integration ==========
    // ====== By @ Abhijit Chowdhury
    Mono<Cmn> searchCMNForSalesOrder(Long patientId, Long salesOrderId, String dos, String itemNo, String orderingProviderNpi);

    Mono<Cmn> createCMNForSalesOrder(Cmn cmn);
    //============================= CMN Sub-routines For Sales Order Item Details Integration ==========
}
