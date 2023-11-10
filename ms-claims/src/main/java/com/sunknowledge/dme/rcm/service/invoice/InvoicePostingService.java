package com.sunknowledge.dme.rcm.service.invoice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.pojo.invoice.*;
import com.sunknowledge.dme.rcm.pojo.salesorder.SalesOrderCombinedSearchParameterDTO;
import com.sunknowledge.dme.rcm.pojo.salesorder.SalesOrderCommonSearchOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.DepositMasterDetailsDTO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface InvoicePostingService {
    void processInvoicePosting();
    ServiceOutcome<List<SalesOrderCommonSearchOutputDTO>> searchSalesOrderListOnSearchCriteria(SalesOrderCombinedSearchParameterDTO salesOrderCombinedSearchParameterDTO) throws ExecutionException, InterruptedException, JsonProcessingException;
    ServiceOutcome<List<InvoiceDetailsProjection>> getInvoiceListOnSalesOrder(String salesOrderNo);
    ServiceOutcome<List<ClaimedInvoiceDetailsProjection>> getInvoiceDetailsOnInvoice(Long invoiceId);
    ServiceOutcome<InvoiceItemsDetailPostingMain> getInvoicePostingDetailsOnInvoice(String invoiceNo);
    ServiceOutcome<ManualInvoiceEntryOutputParameters> manualInvoiceEntry(ManualInvoiceEntryInputParameters manualInvoiceEntryInputParameters);
    ServiceOutcome<DepositMasterDetailsDTO> manualInvoiceEntryPosting(Long depositId);
    List<UnprocessedCOBClaimFiles> prepareUnprocessedCOBClaimsList();
    ServiceOutcome<String> cob835DepositAmountAdjustment(DepositAmountAdjustmentInputParameters depositAmountAdjustmentInputParameters);
}
