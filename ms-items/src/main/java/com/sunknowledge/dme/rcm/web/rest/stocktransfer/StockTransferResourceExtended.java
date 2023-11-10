package com.sunknowledge.dme.rcm.web.rest.stocktransfer;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.po.CancelPartialPurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.PurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.ReceivePurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.StockTransferCompleteParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.StockTransferInitiateParameterDTO;
import com.sunknowledge.dme.rcm.service.po.PurchaseOrderServiceExtended;
import com.sunknowledge.dme.rcm.service.stocktransfer.StockTransferServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/api")
public class StockTransferResourceExtended<T> {
    private final Logger log = LoggerFactory.getLogger(StockTransferResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";

    @Autowired
    StockTransferServiceExtended stockTransferServiceExtended;

    @PatchMapping(value = "/initiateStockTransfer", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO initiateStockTransfer(@Valid @RequestBody StockTransferInitiateParameterDTO stockTransferInitiateParameterDTO) {
        return stockTransferServiceExtended.initiateStockTransfer(stockTransferInitiateParameterDTO);
    }

    @PatchMapping(value = "/completeStockTransfer", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO completeStockTransfer(@Valid @RequestBody StockTransferCompleteParameterDTO stockTransferCompleteParameterDTO) {
        return stockTransferServiceExtended.completeStockTransfer(stockTransferCompleteParameterDTO);
    }

    @PutMapping(value = "/cancelStockTransfer")
    public ResponseDTO cancelStockTransfer(
        @NotNull(message = "Transfer_Id must be provided.")
        @Min(value = 1, message = "Transfer_Id must be greater than equals to 1.")
        @RequestParam("transferId") Long transferId
    ) {
        return stockTransferServiceExtended.cancelStockTransfer(transferId);
    }

    //===== String opType = "StockTransferByTransferId";
    //===== String opType = "StockTransferByFromDateAndToDateOrStockTransferStatus"; String parameterValue="From_Date,To_Date,Stock_Transfer_Status" Ex- "2023-05-01,2023-05-05,Raised"
    //===== String opType = "StockTransferByLocationId";
    @GetMapping("/getStockTransferData")
    public ResponseDTO getStockTransferData(
        @NotNull(message = "Parameter_Value must be provided.")
        @NotBlank(message = "Parameter_Value must be provided.")
        @RequestParam("parameterValue") String parameterValue,
        @NotNull(message = "Operation_Type must be provided.")
        @NotBlank(message = "Operation_Type must be provided.")
        @RequestParam("opType") String opType
    ) {
        return stockTransferServiceExtended.getStockTransferData(parameterValue, opType);
    }
}
