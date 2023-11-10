package com.sunknowledge.dme.rcm.service.par;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.Cmn;
import com.sunknowledge.dme.rcm.domain.CmnDocumentMaster;
import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.domain.ParRequestDetails;
import com.sunknowledge.dme.rcm.dto.cmn.EquipmentDetailsDTO;
import com.sunknowledge.dme.rcm.dto.cmn.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.dto.cmn.SWODataDTO;
import com.sunknowledge.dme.rcm.dto.par.DelinkItemsinPAR;
import com.sunknowledge.dme.rcm.dto.par.ParInputParameters;
import com.sunknowledge.dme.rcm.dto.par.ParSearchForCreate;
import com.sunknowledge.dme.rcm.service.dto.ParMasterDTO;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface PriorAuthorizationService {
    Mono<ServiceOutcome<ParMasterDTO>> generatePriorAuthorizationOnSalesOrderItem(Long salesOrderId, String hcpcsCode) throws Exception;

    Mono<ServiceOutcome<ParMasterDTO>> createManualPriorAuthorizationOnSalesOrderNItem(ParInputParameters parInputParameters);
    Mono<ServiceOutcome<ParRequestDetails>> updatePriorAuthorization(UUID parUUID, String parNo, String dateOfContact, String description,
			String initialDate,  String expirationDate, String authorizedBy, String docName, String docQrCode,String addlInformation) throws Exception;

	ServiceOutcome<ParMaster> loggingPriorAuthorization(Long parId, String logInDate);

	String getRenwalOrExpiringPARList() throws FileNotFoundException, DocumentException, IOException, WriterException;

	void validateinitOrRenewalReport(long parId, Long soId) throws InterruptedException, ExecutionException, Exception;

	void getPriorAuthorizationReport(long parId, Long soId, InsuranceMasterDTO objInsuranceMasterDTO, String reqType) throws WriterException, IOException, DocumentException, Exception;

	SWODataDTO getSWODataOnSalesOrder(Long salesOrderId) throws Exception;

	Cmn generateCmnOnSalesOrder(SWODataDTO swoDataDTO) throws Exception;

	CmnDocumentMaster saveCmnDocument(Cmn cmn, String fileName) throws Exception;

	List<EquipmentDetailsDTO> getEquipmentDetailsOnSalesOrder(Long salesOrderId) throws Exception;

	ParRequestDetails getParRequestDetailsForInit(Long parId, String reqType) throws Exception;

	ParRequestDetails getParRequestDetailsForRenewal(Long parId, String reqType) throws Exception;

	ParRequestDetails getParRequestDetailsWithDocQrCode(Long parId, String docQrCode) throws Exception;

	ParRequestDetails getLatestParRequestDetailIdForRenew() throws Exception;

	String getParNo() throws InterruptedException, ExecutionException;

	ServiceOutcome<ParSearchForCreate> validateParSearchProc(String patientidno,String hcpcsno,String itemno,Long soId,String dos) throws Exception;

	void userChoosetoUseExistingPAR(ParSearchForCreate parSearchForCreate) throws Exception;

    ServiceOutcome userChooseNottoUseExistingPAR(ParSearchForCreate parSearchForCreate) throws Exception;

    ServiceOutcome createOrAttachPar(String YesOrNo, ParSearchForCreate parSearchForCreate) throws Exception;

	ServiceOutcome<ParMaster> createNewPar(String patientidno, String hcpcsno) throws Exception;

	ServiceOutcome<ParMaster> parextension(UUID parUuid, String expirationDate, String authorizedBy, String extensionApprovalDate, String comments) throws Exception;

	ServiceOutcome<ParMaster> parrenewal(UUID parUuid, String renewedPARNo, String effectiveStartDate, String expirationDate, String renewalAuthorizedBy, String comments) throws Exception;

    Mono<String> isPARRequired(String hcpcsNo, String itemNo, LocalDate dos, Long priceTableId);

    ServiceOutcome<ParMaster> delinkItem(Long parId, Long soId, String hcpcsCode, Long itemId) throws InterruptedException, ExecutionException;

    ServiceOutcome<ParMaster> delinkAllItems(DelinkItemsinPAR delinkItemsinPAR) throws ExecutionException, InterruptedException;

    Mono<String> isPARRequiredReactive(String hcpcsNo, String itemNo, LocalDate dos,Long priceTableId);

    Mono<ServiceOutcome> dLinkParForItems(Long parId, Long salesOrderId, String salesOrderDetailsItemId) throws ExecutionException, InterruptedException;

    Mono<ServiceOutcome> linkForParItems(Long parId, Long salesOrderId, String salesOrderDetailsItemId) throws ExecutionException, InterruptedException;
}
