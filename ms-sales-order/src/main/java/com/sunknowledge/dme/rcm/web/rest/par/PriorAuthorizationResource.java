package com.sunknowledge.dme.rcm.web.rest.par;

import com.sunknowledge.dme.rcm.amazon.BucketName;
import com.sunknowledge.dme.rcm.amazon.FileStore;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.exceptions.ResourceNotFoundException;
import com.sunknowledge.dme.rcm.commonutil.CommonPDFStubs;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.CmnDocumentMaster;
import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.domain.ParRequestDetails;
import com.sunknowledge.dme.rcm.dto.cmn.EquipmentDetailsDTO;
import com.sunknowledge.dme.rcm.dto.cmn.SWODataDTO;
import com.sunknowledge.dme.rcm.dto.par.DelinkItemsinPAR;
import com.sunknowledge.dme.rcm.dto.par.ParInputParameters;
import com.sunknowledge.dme.rcm.dto.par.ParSearchForCreate;
import com.sunknowledge.dme.rcm.service.cmn.CMNService;
import com.sunknowledge.dme.rcm.service.dto.*;
import com.sunknowledge.dme.rcm.service.dto.par.EfaxResponseDTOExtended;
import com.sunknowledge.dme.rcm.service.dto.CmnDTO;
import com.sunknowledge.dme.rcm.service.dto.CmnDocumentMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.ParMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.ParRequestDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.par.FaxDocsSoItemDetailsCustomDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.PARCustomOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SoClinicalInsuranceOutputDTO;
import com.sunknowledge.dme.rcm.service.par.PriorAuthorizationService;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderItemDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterServiceExtented;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @author Bimal K Sahoo
 * @since 24/03/2023
 */
@RestController
@RequestMapping("/api/par")
@Slf4j
public class PriorAuthorizationResource {
    @Autowired
    SalesOrderMasterServiceExtented salesOrderMasterServiceExtented;
    @Autowired
    private PriorAuthorizationService priorAuthorizationService;

    @Autowired
    SalesOrderItemDetailsServiceExtended soItemDetailsServiceExtended;

    @Autowired
    private CMNService cmnService;

    @Autowired
    FileStore fileStore;

    @ApiOperation(value = "Generate Prior Authorization on Sales Order, Patient and Item")
    @PostMapping("/generatePriorAuthorization")
    public Mono<ServiceOutcome<ParMasterDTO>> generatePriorAuthorization(@RequestParam("salesOrderId") Long salesOrderId, @RequestParam("hcpcsCode") String hcpcsCode) throws Exception {
        log.info("===================generatePriorAuthorization======================" + salesOrderId);
        Mono<ServiceOutcome<ParMasterDTO>> outcome = null;
        priorAuthorizationService.generatePriorAuthorizationOnSalesOrderItem(salesOrderId, hcpcsCode);
        return outcome;
    }

    @ApiOperation(value = "Create Manual Prior Authorization on Sales Order, Item")
    @PostMapping("/createManualPriorAuthorization")
    public ResponseEntity<Mono<ServiceOutcome<ParMasterDTO>>> createManualPriorAuthorizationTest(@Valid @RequestBody ParInputParameters parInputParameters) throws ResourceNotFoundException {
        log.info("===================createManualPriorAuthorization======================" + parInputParameters.getSalesorderId());
        Mono<ServiceOutcome<ParMasterDTO>> outcome = priorAuthorizationService.createManualPriorAuthorizationOnSalesOrderNItem(parInputParameters);
        return new ResponseEntity<>(outcome, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Prior Authorization")
    @PostMapping(value = "/updatePriorAuthorization", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<ServiceOutcome<ParRequestDetails>> updatePriorAuthorization(@RequestParam("parUUID") UUID parUUID,
                                                                            @RequestParam("dateOfContact") String dateOfContact, @RequestParam("parNo") String parNo,
                                                                            @RequestParam("description") String description, @RequestParam("initialDate") String initialDate,
                                                                            @RequestParam("expirationDate") String expirationDate, @RequestParam("authorizedBy") String authorizedBy,
                                                                            @RequestPart Mono<FilePart> documentFile, @RequestParam("docQrCode") String docQrCode,
                                                                            @RequestParam("addlInformation") String addlInformation)
        throws Exception {

        return documentFile
            .filter(part -> part instanceof FilePart)
            .ofType(FilePart.class)
            .flatMap(r -> {
                try {
                    return priorAuthorizationService.updatePriorAuthorization(parUUID, parNo, dateOfContact, description,
                        initialDate, expirationDate, authorizedBy, r.filename(), docQrCode, addlInformation);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            });
    }

    @ApiOperation(value = "Prior Authorization Logging")
    @PostMapping("/loggingPriorAuthorization")
    public ServiceOutcome<ParMaster> loggingPriorAuthorization(@RequestParam("parId") Long parId, @RequestParam("logInDate") String logInDate)
        throws Exception {
        ServiceOutcome<ParMaster> response = priorAuthorizationService.loggingPriorAuthorization(parId, logInDate);
        return response;
    }

    @ApiOperation(value = "Prior Authorization Renwal/Expiring List")
    @GetMapping("/PriorAuthorizationRenwalOrExpiringList")
    public String getRenwalOrExpiringPARList()
        throws Exception {
        String parList = priorAuthorizationService.getRenwalOrExpiringPARList();
        return parList;
    }

    /*@ApiOperation(value = "Prior Authorization Report")
    @GetMapping("/PriorAuthorizationReport")
    public void getPriorAuthorizationReport(@RequestParam("parId") Long parId, @RequestParam("soId") Long soId)
        throws Exception {

        priorAuthorizationService.validateinitOrRenewalReport(parId, soId);

    }*/

    @ApiOperation(value = "Validate Par Search Proc")
    @GetMapping("/ValidateParSearchProc")
    public ServiceOutcome<ParSearchForCreate> validateParSearchProc(@RequestParam("patientidno") String patientidno,
                                                                    @RequestParam("hcpcsno") String hcpcsno, @RequestParam("itemno") String itemno, @RequestParam("soId") Long soId,
                                                                    @RequestParam("dos") String dos)
        throws Exception {

        return priorAuthorizationService.validateParSearchProc(patientidno, hcpcsno, itemno, soId, dos);

    }

    @ApiOperation(value = "Create or Attach Par")
    @PostMapping("/CreateorAttachPar")
    public ServiceOutcome createOrAttachPar(
        @RequestBody ParSearchForCreate parSearchForCreate)
        throws Exception {
        String YesOrNo = "No";
        return priorAuthorizationService.createOrAttachPar(YesOrNo, parSearchForCreate);

    }

    @ApiOperation(value = "Create New Par")
    @PostMapping("/CreateNewPar")
    public ServiceOutcome<ParMaster> createNewPar(@RequestParam("patientidno") String patientidno, @RequestParam("hcpcsno") String hcpcsno)
        throws Exception {
        return priorAuthorizationService.createNewPar(patientidno, hcpcsno);

    }

    @ApiOperation(value = "PAR Extension")
    @PostMapping("/PARextension")
    public ServiceOutcome<ParMaster> parextension(@RequestParam("parUuid") UUID parUuid, @RequestParam("expirationDate") String expirationDate,
                                                  @RequestParam("authorizedBy") String authorizedBy, @RequestParam("extensionApprovalDate") String extensionApprovalDate,
                                                  @RequestParam("comments") String comments)
        throws Exception {
        return priorAuthorizationService.parextension(parUuid, expirationDate, authorizedBy, extensionApprovalDate, comments);

    }

    @ApiOperation(value = "PAR Renewal")
    @PostMapping("/PARRenewal")
    public ServiceOutcome<ParMaster> parrenewal(@RequestParam("parUuid") UUID parUuid, @RequestParam("renewedPARNo") String renewedPARNo,
                                                @RequestParam("effectiveStartDate") String effectiveStartDate, @RequestParam("expirationDate") String expirationDate,
                                                @RequestParam("renewalAuthorizedBy") String renewalAuthorizedBy, @RequestParam("comments") String comments)
        throws Exception {
        return priorAuthorizationService.parrenewal(parUuid, renewedPARNo, effectiveStartDate, expirationDate, renewalAuthorizedBy, comments);

    }

    @ApiOperation(value = "Delink Item from PAR")
    @PostMapping("/DelinkItem")
    public ServiceOutcome<ParMaster> delinkItem(@RequestParam("parId") Long parId,
                                                @RequestParam("parId") Long soId,
                                                @RequestParam("hcpcsCode") String hcpcsCode,
                                                @RequestParam("itemId") Long itemId)
        throws Exception {
        return priorAuthorizationService.delinkItem(parId, soId, hcpcsCode, itemId);

    }

    @ApiOperation(value = "Delink All Items from PAR")
    @PostMapping("/DelinkAllItems")
    public ServiceOutcome<ParMaster> delinkAllItems(@RequestBody DelinkItemsinPAR delinkItemsinPAR)
        throws Exception {
        return priorAuthorizationService.delinkAllItems(delinkItemsinPAR);

    }

    /**
     * This API will help to D-Link Single/Multiple Sales Order Item for PAR.
     *
     * @param parId
     * @param salesOrderUuid
     * @param salesOrderDetailsItemId
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/dLinkParForItems")
    public Mono<ServiceOutcome> dLinkParForItems(Long parId, UUID salesOrderUuid, String salesOrderDetailsItemId) throws ExecutionException, InterruptedException {

        Long salesOrderId = 0L;
        if (Objects.nonNull(salesOrderUuid)) {
            try {
                salesOrderId = salesOrderMasterServiceExtented.getIDByUUID(salesOrderUuid).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            salesOrderId = salesOrderId != null ? salesOrderId : 0L;
        }
        if (!salesOrderId.equals(0) && Objects.nonNull(parId))
            return priorAuthorizationService.dLinkParForItems(parId, salesOrderId, salesOrderDetailsItemId);
        else
            return Mono.just(new ServiceOutcome(null, false, " Please Check Par_Id OR Sales_Order_UUID : " + parId + " or " + salesOrderUuid));
    }

    /**
     * This API will help to Link Single/Multiple Sales Order Item for PAR.
     *
     * @param parId
     * @param salesOrderUuid
     * @param salesOrderDetailsItemId
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/linkParForItems")
    public Mono<ServiceOutcome> linkForParItems(Long parId, UUID salesOrderUuid, String salesOrderDetailsItemId) throws ExecutionException, InterruptedException {

        Long salesOrderId = 0L;
        if (Objects.nonNull(salesOrderUuid)) {
            try {
                salesOrderId = salesOrderMasterServiceExtented.getIDByUUID(salesOrderUuid).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            salesOrderId = salesOrderId != null ? salesOrderId : 0L;
        }
        if (!salesOrderId.equals(0) && Objects.nonNull(parId))
            return priorAuthorizationService.linkForParItems(parId, salesOrderId, salesOrderDetailsItemId);
        else
            return Mono.just(new ServiceOutcome(null, false, " Please Check Par_Id OR Sales_Order_UUID : " + parId + " or " + salesOrderUuid));
    }

    /*@ApiOperation(value = "Prepare PAP Setup Form Document")
    @PostMapping(path = "/preparePAPSetupDocumentReport")
    public ServiceOutcome<DeliveryDocumentResponse> preparePAPSetupDocumentReport(@RequestParam("deliveryTicketId") Long deliveryTicketId) {
        log.info("=======================Code Starts Here=========================="+deliveryTicketId);
        ServiceOutcome<DeliveryDocumentResponse> serviceOutcome = null;
        CommonDeliveryInputData commonDeliveryInputData = null;
        try {
            Optional<DeliveryTicket> deliveryTicket = deliveryService.getDeliveryTicketOnDeliveryTicket(deliveryTicketId);
            List<DeliveryItems> deliveryItems = deliveryService.getDeliveryItemsOnDeliveryTicket(deliveryTicketId);
            System.out.println("===================deliveryTicket==================="+deliveryTicket);
            serviceOutcome = deliveryService.preparePAPSetupDocumentReport(deliveryTicket.get(), deliveryItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceOutcome;
    }*/

    @ApiOperation(value = "Generate Combined FAX Cover Documents Api")
    @PatchMapping("/generateCombinedFaxCoverDocument")
    public Mono<ServiceOutcome> generateCombinedFaxCoverDocument(@RequestParam("soId") Long soId,
                                                                 @RequestParam(name = "documentNames") String documentNameList,
                                                                 @RequestParam(name = "genericDocsNotes") String genericDocsNotes) throws Exception {

        ServiceOutcome priorAuthMediCareDocsOutCome = new ServiceOutcome();
        ServiceOutcome priorAuthGenericDocsOutCome = new ServiceOutcome();
        ServiceOutcome swoDocsOutCome = new ServiceOutcome();
        ParMasterDTO parMasterDTO;

        //Get Sales Order Master, clinical and Insurance Information
        SoClinicalInsuranceOutputDTO soClinicalInsuranceOutputData = salesOrderMasterServiceExtented.getSalesOrderMasterClinicalInsuranceData(soId).toFuture().get();
        //Get Sales Order Item Details data
        List<SalesOrderItemDetailsDTO> soItemDetailsList = soItemDetailsServiceExtended.getSalesOrderItemDetailsData(soId).collectList().toFuture().get();
        FaxDocsSoItemDetailsCustomDTO faxDocsSoItemDetailsCustomDTO =soItemDetailsServiceExtended.getFaxDocsSoItemDetailsPrimaryItemInfo(soItemDetailsList).toFuture().get(); //Primary Item

        log.info("itemId:: "+faxDocsSoItemDetailsCustomDTO.getItemId()+" ,parNo:: "+faxDocsSoItemDetailsCustomDTO.getParNo()+" ,soDetailsSaleType:: "+
            faxDocsSoItemDetailsCustomDTO.getSoDetailsSaleType()+" ,primaryHcpcsCode:: "+faxDocsSoItemDetailsCustomDTO.getPrimaryHcpcsCode());

        if(faxDocsSoItemDetailsCustomDTO.getParId()!= null && faxDocsSoItemDetailsCustomDTO.getParId()!=0L){
            parMasterDTO = priorAuthorizationService.getParMasterDataByParId(faxDocsSoItemDetailsCustomDTO.getParId()).toFuture().get();
        }else{
            return Mono.just(new ServiceOutcome<>(null, false, "Insufficient Data."));
        }
        log.info("===========parMasterDTO==========="+parMasterDTO);

        String reqType = "";
        if ((!CommonUtilities.isStringNullOrBlank(parMasterDTO.getParNo())) && parMasterDTO.getExpirationDate() == null
            && parMasterDTO.getParStatus().equalsIgnoreCase("Initiated")) {
            reqType = "Initial";
        } else {
            reqType = "Renewal";
        }

        ParRequestDetailsDTO parRequestDetailsDto;
        int newreqdtlId = 0;
        if (reqType.equalsIgnoreCase("Initial")) {
            parRequestDetailsDto = priorAuthorizationService.getParRequestDetailsForInitReactive(faxDocsSoItemDetailsCustomDTO.getParId(), "Initial").toFuture().get();
            newreqdtlId = Integer.parseInt(String.valueOf(parRequestDetailsDto.getParRequestDetailsId()));
        } else {
            parRequestDetailsDto = priorAuthorizationService.getLatestParRequestDetailIdForRenewReactive().toFuture().get();
            newreqdtlId = Integer.parseInt(parRequestDetailsDto.getParRequestDetailsId().toString()) + 1;
        }
        List<PARCustomOutputDTO> parCustomOutputList = priorAuthorizationService.getPARDetailsData(soId).collectList().toFuture().get();
        log.info("===========PARDetailsData:: parCustomOutputList==========="+parCustomOutputList);

        CmnDTO cmnDTO = cmnService.getCMNMasterData(soId).toFuture().get();
        SWODataDTO swoDataDTO = cmnService.getSWODataOnSalesOrderReactive(soId).toFuture().get();
        List<EquipmentDetailsDTO> EquipmentDetailsList = cmnService.getEquipmentDetailsOnSalesOrderReactive(soId).collectList().toFuture().get();
        CmnDocumentMaster cmnDocumentMaster = cmnService.getCmnDocumentByCmnId(cmnDTO.getCmnId()).toFuture().get();
        /*******Total No of Pages in Merged Docs Starts*********/
        int countNoOfPages = 0;
        int countPageOfFaxCover = 1;
        int countPageOfGeneric = 2;
        if(parCustomOutputList.size()> 4)
            countPageOfGeneric++;

        int countPageOfMedicare = 1;
        int countPageOfIR = 2;

        if(cmnDTO.getCmnId()!=null){
            CmnDocumentMasterDTO cmnDocumentMasterDTO = cmnService.saveCmnDocumentInReactive(cmnDTO,  cmnDTO.getCmnNumber() + ".pdf", "First", cmnDocumentMaster).toFuture().get();
            swoDocsOutCome = cmnService.prepareAndPrintCMNReportOnCmnForAwsS3(cmnDTO, swoDataDTO, EquipmentDetailsList, cmnDocumentMaster, cmnDocumentMasterDTO, "PRINT").toFuture().get();
        }
        log.info("=========swoDocsOutCome========="+swoDocsOutCome);

        int countPageOfSWO = priorAuthorizationService.getCountOfNoOfPagesInDocs(cmnDTO.getCmnNumber()+".pdf", BucketName.BUCKET_NAME.getSoServiceBucket(),"cmnDocuments").toFuture().get();
        int countPageOfPMR = priorAuthorizationService.getCountOfNoOfPagesInDocs(documentNameList,BucketName.BUCKET_NAME.getPatientServiceBucket(),"patientDocuments").toFuture().get();
        if(parMasterDTO.getInsuranceName()!=null && parMasterDTO.getInsuranceName().equalsIgnoreCase("Medicare")) {
            countNoOfPages = countPageOfFaxCover + countPageOfMedicare + countPageOfIR + countPageOfSWO + countPageOfPMR;
        }else{
            countNoOfPages = countPageOfFaxCover + countPageOfGeneric + countPageOfIR + countPageOfSWO + countPageOfPMR;
        }
        /*******Total No of Pages in Merged Docs Starts*********/

        String combinedFileName = "PAR_" + parRequestDetailsDto.getParRequestDetailsId() + "_Fax_Request" + ".pdf";
        CommonPDFStubs commonPDFStubs = new CommonPDFStubs();
        String qrCodeName = "PAR_" + faxDocsSoItemDetailsCustomDTO.getParId() + "_" + String.valueOf(newreqdtlId);
        byte[] qrCodeBytes = commonPDFStubs.generateQRCodeInAmazon(qrCodeName);

        /******* Documents That will be merged Starts *********/
        ServiceOutcome faxCoverDocsOutCome = priorAuthorizationService.generateFaxCoverDocument(soClinicalInsuranceOutputData, countNoOfPages, qrCodeBytes, parRequestDetailsDto.getParRequestDetailsId()+"").toFuture().get();
        if(parMasterDTO.getInsuranceName()!=null && parMasterDTO.getInsuranceName().equalsIgnoreCase("Medicare")){
            priorAuthMediCareDocsOutCome =  priorAuthorizationService.getDMEAuthorizationMedicareRequestDocument(soClinicalInsuranceOutputData, parMasterDTO,
                parCustomOutputList, soItemDetailsList, faxDocsSoItemDetailsCustomDTO.getItemId(), faxDocsSoItemDetailsCustomDTO.getSoDetailsSaleType(),
                faxDocsSoItemDetailsCustomDTO.getParNo(), faxDocsSoItemDetailsCustomDTO.getPrimaryHcpcsCode(), countNoOfPages, parRequestDetailsDto.getParRequestDetailsId()+"", qrCodeBytes).toFuture().get();
        }else{
            priorAuthGenericDocsOutCome = priorAuthorizationService.getDMEAuthorizationRequestForm1HighMarkHealthOptionsDocument(soClinicalInsuranceOutputData, parMasterDTO,
                parCustomOutputList, faxDocsSoItemDetailsCustomDTO.getItemId(), faxDocsSoItemDetailsCustomDTO.getSoDetailsSaleType(), faxDocsSoItemDetailsCustomDTO.getParNo(),
                parRequestDetailsDto.getParRequestDetailsId()+"", documentNameList, genericDocsNotes, qrCodeBytes).toFuture().get();
        }
        ServiceOutcome initialOrRenewalDocsOutCome = priorAuthorizationService.validateinitOrRenewalReport(faxDocsSoItemDetailsCustomDTO.getParId(), soId, qrCodeBytes).toFuture().get();
        return priorAuthorizationService.generateCombinedParDocument(faxCoverDocsOutCome, priorAuthGenericDocsOutCome, priorAuthMediCareDocsOutCome, initialOrRenewalDocsOutCome, swoDocsOutCome, combinedFileName, documentNameList, parRequestDetailsDto, qrCodeName);
        /******* Documents That will be merged Ends *********/
    }

    @PostMapping("/fetchEfaxAutomatic")
    public Mono<ServiceOutcome> fetchEfaxAutomatic() throws MessagingException {
        return priorAuthorizationService.fetchEfaxAutomatic();
    }

    @GetMapping("/getAllUnIdentifiedFaxes")
    public Mono<ServiceOutcome> getAllUnIdentifiedFaxes() {
        return priorAuthorizationService.getAllUnIdentifiedFaxes();
    }
    @GetMapping("/getFileAsByteCode")
    public Mono<ServiceOutcome> getFileAsByteCode(@RequestParam("name") String name) throws IOException {
        return priorAuthorizationService.getFileAsByteCode(name);
    }

    @PatchMapping("/updateEfaxResponse")
    public Mono<ServiceOutcome> updateEfaxResponse(@RequestBody EfaxResponseDTOExtended efaxResponseDTOExtended){
        if(efaxResponseDTOExtended.getEfaxResponseId()==null || efaxResponseDTOExtended.getEfaxResponseId()==0){
            return Mono.just(new ServiceOutcome(null,false,"Id should not be null or 0."));
        }
        return priorAuthorizationService.updateEfaxResponse(efaxResponseDTOExtended);
    }

    @GetMapping("/viewPAROrCMNOrPatientDetailsByPatientUUid")
    public Mono<ServiceOutcome> viewPAROrCMNOrPatientDetailsByPatientUUid(@RequestParam("patientUUID") UUID patientUUID,
                                                          @RequestParam("operationType") String operationType){
        switch (operationType.toLowerCase()){
            case "par":{
                return priorAuthorizationService.viewPARDetailsByPatientId(patientUUID);
            }
            case "cmn":{
                return priorAuthorizationService.viewCMNDetailsByPatientId(patientUUID);
            }
            case "pid":{
                return priorAuthorizationService.viewPatientDetailsByPatientId(patientUUID);
            }
            case "pdd":{
                return priorAuthorizationService.viewPatientDetailsByPatientId(patientUUID);
            }
            case "pmr":{
                return priorAuthorizationService.viewPatientDetailsByPatientId(patientUUID);
            }
            default:{
                return Mono.just(new ServiceOutcome(null,false,"Wrong Operation Type given.","200"));
            }
        }
    }

    /*@GetMapping("/viewCMNDetailsByPatientId")
    public Mono<ServiceOutcome> viewCMNDetailsByPatientId(@RequestParam("patientId") long patientId){
        return priorAuthorizationService.viewCMNDetailsByPatientId(patientId);
    }

    @GetMapping("/viewPatientDetailsByPatientId")
    public Mono<ServiceOutcome> viewPatientDetailsByPatientId(@RequestParam("patientId") long patientId){
        return priorAuthorizationService.viewPatientDetailsByPatientId(patientId);
    }*/

    @PostMapping("/tagCMNEfax")
    public Mono<ServiceOutcome> tagCMNEfax(@RequestParam("eFaxResponseUUID") UUID eFaxResponseUUID,
                                           @RequestParam("cmnUUID") UUID cmnUUID,
                                           @RequestParam("cmnDocumentUUID") UUID cmnDocumentUUID){
        return priorAuthorizationService.tagCMNEfax(eFaxResponseUUID,cmnUUID,cmnDocumentUUID);
    }
    @PostMapping("/tagPAREfax")
    public Mono<ServiceOutcome> tagPAREfax(@RequestParam("eFaxResponseUUID") UUID eFaxResponseUUID,
                                           @RequestParam("parUUID") UUID parUUID,
                                           @RequestParam("parRequestUUID") UUID parRequestUUID){
        return priorAuthorizationService.tagPAREfax(eFaxResponseUUID,parUUID,parRequestUUID);
    }
    @PostMapping("/tagPatientEfax")
    public Mono<ServiceOutcome> tagPatientEfax(@RequestParam("eFaxResponseUUID") UUID eFaxResponseUUID,
                                               @RequestParam("soUUID") UUID soUUID,
                                               @RequestParam("documentType") String documentType,
                                               @RequestParam("patientUUID") UUID patientUUID){
        try {
            return priorAuthorizationService.tagPatientEfax(eFaxResponseUUID, soUUID, documentType, patientUUID);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
