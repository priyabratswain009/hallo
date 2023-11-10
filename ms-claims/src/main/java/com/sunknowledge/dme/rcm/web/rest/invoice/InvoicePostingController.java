package com.sunknowledge.dme.rcm.web.rest.invoice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.pojo.invoice.*;
import com.sunknowledge.dme.rcm.pojo.salesorder.SalesOrderCombinedSearchParameterDTO;
import com.sunknowledge.dme.rcm.pojo.salesorder.SalesOrderCommonSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.DepositMasterDetailsDTO;
import com.sunknowledge.dme.rcm.service.invoice.InvoicePostingService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Bimal K Sahoo
 * @since 29/12/2022
 *
 */

@RestController
@RequestMapping("/api/invoiceposting")
@Slf4j
public class InvoicePostingController {
    @Autowired
    private InvoicePostingService invoicePostingService;

    @ApiOperation(value = "Invoice Posting")
    @PostMapping(path = "/processInvoicePosting", produces = "application/json")
    @ResponseBody
    public String processInvoicePosting() {
        log.info("=======================POST(CONTROLLER) Process for Invoice Posting==========================");
        String resultOutcomeJson = "aaaaaaaaaaa";
        try {
            invoicePostingService.processInvoicePosting();
            System.out.println("RESULT OUTCOME=>"+resultOutcomeJson);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return resultOutcomeJson;
    }

    @ApiOperation(value = "Search List of Sales-order on search criteria")
    @GetMapping(path = "searchSalesOrderListOnSearchCriteria")
    public ServiceOutcome<List<SalesOrderCommonSearchOutputDTO>> searchSalesOrderListOnSearchCriteria(SalesOrderCombinedSearchParameterDTO salesOrderCombinedSearchParameterDTO) throws ExecutionException, InterruptedException, JsonProcessingException {
        return invoicePostingService.searchSalesOrderListOnSearchCriteria(salesOrderCombinedSearchParameterDTO);
    }

    @ApiOperation("Get List of Invoices on Sales Order")
    @GetMapping(value = "getInvoiceListOnSalesOrder")
    public ServiceOutcome<List<InvoiceDetailsProjection>> getInvoiceListOnSalesOrder(@RequestParam("salesOrderNo") String salesOrderNo){
        return invoicePostingService.getInvoiceListOnSalesOrder(salesOrderNo);
    }

    @ApiOperation("Get Invoice Details on Invoice")
    @GetMapping(value = "getInvoiceDetailsOnInvoice")
    public ServiceOutcome<List<ClaimedInvoiceDetailsProjection>> getInvoiceDetailsOnInvoice(@RequestParam("invoiceId") Long invoiceId){
        return invoicePostingService.getInvoiceDetailsOnInvoice(invoiceId);
    }


    @ApiOperation("Get List of Invoice Posting Details on Invoice")
    @GetMapping(value = "getInvoicePostingDetailsOnInvoice")
    public ServiceOutcome<InvoiceItemsDetailPostingMain> getInvoicePostingDetailsOnInvoice(@RequestParam("invoiceNo") String invoiceNo){
        return invoicePostingService.getInvoicePostingDetailsOnInvoice(invoiceNo);
    }

    @ApiOperation(value = "Manual Invoice Entry")
    @PostMapping(value = "manualInvoiceEntry")
    public ServiceOutcome<ManualInvoiceEntryOutputParameters> manualInvoiceEntry(ManualInvoiceEntryInputParameters manualInvoiceEntryInputParameters){
        return invoicePostingService.manualInvoiceEntry(manualInvoiceEntryInputParameters);
    }

    @ApiOperation(value = "Manual Invoice Entry Posting")
    @PostMapping(value = "manualInvoiceEntryPosting")
    public ServiceOutcome<DepositMasterDetailsDTO> manualInvoiceEntryPosting(@RequestParam("depositId") Long depositId){
        return invoicePostingService.manualInvoiceEntryPosting(depositId);
    }

    @ApiOperation(value = "Prepare Unprocessed COB Claims List")
    @GetMapping(value = "prepareUnprocessedCOBClaimsList")
    public List<UnprocessedCOBClaimFiles> prepareUnprocessedCOBClaimsList(){
        return invoicePostingService.prepareUnprocessedCOBClaimsList();
    }

    @ApiOperation(value = "COB - 835 Deposit Amount Adjustment")
    @PostMapping(value = "cob835DepositAmountAdjustment")
    public ServiceOutcome<String> cob835DepositAmountAdjustment(DepositAmountAdjustmentInputParameters depositAmountAdjustmentInputParameters){
        return invoicePostingService.cob835DepositAmountAdjustment(depositAmountAdjustmentInputParameters);
    }
}
