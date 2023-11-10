package com.sunknowledge.dme.rcm.web.rest.par;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.exceptions.ResourceNotFoundException;
import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.domain.ParRequestDetails;
import com.sunknowledge.dme.rcm.dto.par.DelinkItemsinPAR;
import com.sunknowledge.dme.rcm.dto.par.ParInputParameters;
import com.sunknowledge.dme.rcm.dto.par.ParSearchForCreate;
import com.sunknowledge.dme.rcm.service.dto.ParMasterDTO;
import com.sunknowledge.dme.rcm.service.par.PriorAuthorizationService;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterServiceExtented;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Objects;
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

    @ApiOperation(value = "Prior Authorization Report")
    @GetMapping("/PriorAuthorizationReport")
    public void getPriorAuthorizationReport(@RequestParam("parId") Long parId, @RequestParam("soId") Long soId)
        throws Exception {

        priorAuthorizationService.validateinitOrRenewalReport(parId, soId);

    }

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
}
