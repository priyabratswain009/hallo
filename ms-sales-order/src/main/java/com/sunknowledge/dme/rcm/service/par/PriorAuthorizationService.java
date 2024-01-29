package com.sunknowledge.dme.rcm.service.par;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.Cmn;
import com.sunknowledge.dme.rcm.domain.CmnDocumentMaster;
import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.domain.ParRequestDetails;
import com.sunknowledge.dme.rcm.domain.ParSoMap;
import com.sunknowledge.dme.rcm.dto.cmn.EquipmentDetailsDTO;
import com.sunknowledge.dme.rcm.dto.cmn.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.dto.cmn.SWODataDTO;
import com.sunknowledge.dme.rcm.dto.par.DelinkItemsinPAR;
import com.sunknowledge.dme.rcm.dto.par.ParInputParameters;
import com.sunknowledge.dme.rcm.dto.par.ParSearchForCreate;
import com.sunknowledge.dme.rcm.service.dto.ParMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.ParRequestDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.par.EfaxResponseDTOExtended;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.PARCustomOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SoClinicalInsuranceOutputDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

public interface PriorAuthorizationService {
    Mono<ServiceOutcome<ParMasterDTO>> generatePriorAuthorizationOnSalesOrderItem(Long salesOrderId, String hcpcsCode)
        throws Exception;

    Mono<ServiceOutcome<ParMasterDTO>> createManualPriorAuthorizationOnSalesOrderNItem(
        ParInputParameters parInputParameters);

    Mono<ServiceOutcome<ParRequestDetails>> updatePriorAuthorization(UUID parUUID, String parNo, String dateOfContact,
                                                                     String description, String initialDate, String expirationDate, String authorizedBy, String docName,
                                                                     String docQrCode, String addlInformation) throws Exception;

    ServiceOutcome<ParMaster> loggingPriorAuthorization(Long parId, String logInDate);

    String getRenwalOrExpiringPARList() throws FileNotFoundException, DocumentException, IOException, WriterException;

    Mono<ServiceOutcome> validateinitOrRenewalReport(long parId, Long soId, byte[] qrCodeBytes)
        throws InterruptedException, ExecutionException, Exception;

    Mono<ServiceOutcome> getPriorAuthorizationReport(long parId, Long soId, InsuranceMasterDTO objInsuranceMasterDTO,
                                                     String reqType, byte[] qrCodeBytes) throws WriterException, IOException, DocumentException, Exception;

    SWODataDTO getSWODataOnSalesOrder(Long salesOrderId) throws Exception;

    Cmn generateCmnOnSalesOrder(SWODataDTO swoDataDTO) throws Exception;

    CmnDocumentMaster saveCmnDocument(Cmn cmn, String fileName) throws Exception;

    List<EquipmentDetailsDTO> getEquipmentDetailsOnSalesOrder(Long salesOrderId) throws Exception;

    ParRequestDetails getParRequestDetailsForInit(Long parId, String reqType) throws Exception;

    ParRequestDetails getParRequestDetailsForRenewal(Long parId, String reqType) throws Exception;

    ParRequestDetails getParRequestDetailsWithDocQrCode(Long parId, String docQrCode) throws Exception;

    ParRequestDetails getLatestParRequestDetailIdForRenew() throws Exception;

    String getParNo() throws InterruptedException, ExecutionException;

    ServiceOutcome<ParSearchForCreate> validateParSearchProc(String patientidno, String hcpcsno, String itemno,
                                                             Long soId, String dos) throws Exception;

    void userChoosetoUseExistingPAR(ParSearchForCreate parSearchForCreate) throws Exception;

    ServiceOutcome userChooseNottoUseExistingPAR(ParSearchForCreate parSearchForCreate) throws Exception;

    ServiceOutcome createOrAttachPar(String YesOrNo, ParSearchForCreate parSearchForCreate) throws Exception;

    ServiceOutcome<ParMaster> createNewPar(String patientidno, String hcpcsno) throws Exception;

    ServiceOutcome<ParMaster> parextension(UUID parUuid, String expirationDate, String authorizedBy,
                                           String extensionApprovalDate, String comments) throws Exception;

    ServiceOutcome<ParMaster> parrenewal(UUID parUuid, String renewedPARNo, String effectiveStartDate,
                                         String expirationDate, String renewalAuthorizedBy, String comments) throws Exception;

    Mono<String> isPARRequired(String hcpcsNo, String itemNo, LocalDate dos, Long priceTableId);

    ServiceOutcome<ParMaster> delinkItem(Long parId, Long soId, String hcpcsCode, Long itemId)
        throws InterruptedException, ExecutionException;

    ServiceOutcome<ParMaster> delinkAllItems(DelinkItemsinPAR delinkItemsinPAR)
        throws ExecutionException, InterruptedException;

    Mono<String> isPARRequiredReactive(String hcpcsNo, String itemNo, LocalDate dos, Long priceTableId);

    Mono<ServiceOutcome> dLinkParForItems(Long parId, Long salesOrderId, String salesOrderDetailsItemId)
        throws ExecutionException, InterruptedException;

    Mono<ServiceOutcome> linkForParItems(Long parId, Long salesOrderId, String salesOrderDetailsItemId)
        throws ExecutionException, InterruptedException;

    Mono<ServiceOutcome> getDMEAuthorizationRequestForm1HighMarkHealthOptionsDocument(
        SoClinicalInsuranceOutputDTO soClinicalInsuranceOutputData, ParMasterDTO parMasterDTO,
        List<PARCustomOutputDTO> parCustomOutputList, Long itemId, String soDetailsSaleType, String parNo,
        String parRequestDetailsId, String documentNameList, String genericDocsNotes, byte[] qrCodeBytes)
        throws IOException, DocumentException, com.google.zxing.WriterException;

    Mono<ServiceOutcome> getDMEAuthorizationMedicareRequestDocument(
        SoClinicalInsuranceOutputDTO soClinicalInsuranceOutputData, ParMasterDTO parMasterDTO,
        List<PARCustomOutputDTO> parCustomOutputList, List<SalesOrderItemDetailsDTO> soItemDetailsList, Long itemId,
        String soDetailsSaleType, String parNo, String primaryHcpcsCode, int countNoOfPages,
        String parRequestDetailsId, byte[] qrCodeBytes) throws IOException, DocumentException;

    Mono<ServiceOutcome> generateFaxCoverDocument(SoClinicalInsuranceOutputDTO soClinicalInsuranceOutputData,
                                                  int countNoOfPages, byte[] qrCodeBytes, String parrequestDetailsId)
        throws IOException, DocumentException, com.google.zxing.WriterException;

    Mono<ServiceOutcome> generateCombinedParDocument(ServiceOutcome faxCoverDocsOutCome,
                                                     ServiceOutcome priorAuthGenericDocsOutCome, ServiceOutcome priorAuthMediCareDocsOutCome,
                                                     ServiceOutcome initialOrRenewalDocsOutCome, ServiceOutcome swoDocsOutCome, String combinedFileName,
                                                     String documentNameList, ParRequestDetailsDTO parRequestDetailsDto, String qrCodeName)
        throws DocumentException, IOException;

    Flux<PARCustomOutputDTO> getPARDetailsData(Long soId);

    Mono<ParMasterDTO> getParMasterDataByParId(Long parId);

    Mono<ParRequestDetailsDTO> getParRequestDetailsForInitReactive(Long parId, String reqType) throws Exception;

    Mono<ParRequestDetailsDTO> getLatestParRequestDetailIdForRenewReactive() throws Exception;

    Mono<Integer> getCountOfNoOfPagesInDocs(String documentNameList, String bucketName, String filePath)
        throws IOException;

    Mono<ServiceOutcome<ParMasterDTO>> getParMasterbyParNo(String parNo)
        throws InterruptedException, ExecutionException;

    Mono<ParMaster> saveParMaster(ParMaster parMaster);

    Mono<ParSoMap> saveParSoMap(ParSoMap parSoMap);

    Mono<ServiceOutcome<ParMasterDTO>> getParMasterbyParIdNo(String parIdNo)
        throws InterruptedException, ExecutionException;

    Mono<ServiceOutcome> fetchEfaxAutomatic() throws MessagingException;

    Mono<ServiceOutcome> getAllUnIdentifiedFaxes();

    Mono<ServiceOutcome> updateEfaxResponse(EfaxResponseDTOExtended efaxResponseDTOExtended);

    Mono<ServiceOutcome> getFileAsByteCode(String name) throws IOException;

    Mono<ServiceOutcome> viewPARDetailsByPatientId(UUID patientUUID);

    Mono<ServiceOutcome> viewCMNDetailsByPatientId(UUID patientUUID);

    Mono<ServiceOutcome> viewPatientDetailsByPatientId(UUID patientUUID);

    Mono<ServiceOutcome> tagCMNEfax(UUID eFaxResponseUUID, UUID cmnUUID, UUID cmnDocumentUUID);

    Mono<ServiceOutcome> tagPAREfax(UUID eFaxResponseUUID, UUID parUUID, UUID parRequestUUID);

    Mono<ServiceOutcome> tagPatientEfax(UUID eFaxResponseUUID,UUID soUUID, String documentType, UUID patientUUID) throws ExecutionException, InterruptedException;
}
