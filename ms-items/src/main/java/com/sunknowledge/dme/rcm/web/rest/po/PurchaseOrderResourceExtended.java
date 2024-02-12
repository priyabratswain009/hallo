package com.sunknowledge.dme.rcm.web.rest.po;

import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.po.CancelPartialPurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.DropshipPurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.PurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.ReceiptDropshipPurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.po.ReceivePurchaseOrderParameterDTO;
import com.sunknowledge.dme.rcm.service.po.PurchaseOrderServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class PurchaseOrderResourceExtended<T> {
    private final Logger log = LoggerFactory.getLogger(PurchaseOrderResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public static String TYPE = "text/csv";

    @Autowired
    PurchaseOrderServiceExtended purchaseOrderServiceExtended;

    @PatchMapping(value = "/savePurchaseOrder", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO savePurchaseOrder(@Valid @RequestBody PurchaseOrderParameterDTO purchaseOrderParameterDTO) {
        return purchaseOrderServiceExtended.savePurchaseOrder(purchaseOrderParameterDTO);
    }

    @PostMapping(value = "/saveDropshipPurchaseOrder",consumes = "application/json", produces = "application/json")
    public ResponseDTO saveDropshipPurchaseOrder(@Valid @RequestBody DropshipPurchaseOrderParameterDTO dropshipPurchaseOrderParameterDTO) {
        //consumes = {"application/json", "application/merge-patch+json"}
        return purchaseOrderServiceExtended.saveDropshipPurchaseOrder(dropshipPurchaseOrderParameterDTO);
    }

    @PatchMapping(value = "/saveReceiptDropshipPurchaseOrder", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseDTO saveReceiptDropshipPurchaseOrder(@Valid @RequestBody ReceiptDropshipPurchaseOrderParameterDTO receiptDropshipPurchaseOrderParameterDTO) {
        return purchaseOrderServiceExtended.saveReceiptDropshipPurchaseOrder(receiptDropshipPurchaseOrderParameterDTO);
    }

    @GetMapping("/getVendorWiseItemsForPO")
    public ResponseDTO getVendorWiseItems(
        @NotNull(message = "Item_Number must not be Null.")
        @RequestParam("itemNo") String itemNo,
        @NotNull(message = "Item_Name must not be Null.")
        @RequestParam("itemName") String itemName,
        @NotNull(message = "Vendor_Id must be provided.")
        @Min(value = 1, message = "Vendor_Id must be greater than equals to 1.")
        @RequestParam("vendorId") Long vendorId
    ) {
        List<Map> list = purchaseOrderServiceExtended.getVendorWiseItems(itemNo.trim(), itemName.trim(), vendorId);
        return new ResponseDTO((list.size() > 0) ? true : false,
            (list.size() > 0) ? "Data Successfully Fetched" : "No Data Available",
            list, 200);
    }

    @PutMapping(value = "/cancelFullPurchaseOrder")
    public ResponseDTO cancelFullPurchaseOrder(
        @NotNull(message = "PO_Id must be provided.")
        @Min(value = 1, message = "PO_Id must be greater than equals to 1.")
        @RequestParam("poId") Long poId
    ) {
        return purchaseOrderServiceExtended.cancelFullPurchaseOrder(poId);
    }

    @PutMapping(value = "/cancelDropshipPurchaseOrder")
    public ResponseDTO cancelDropshipPurchaseOrder(
        @NotNull(message = "PO_Number must be provided.")
        @NotBlank(message = "PO_Number must be provided.")
        @RequestParam("poNumber") String poNumber
    ) {
        return purchaseOrderServiceExtended.cancelDropshipPurchaseOrder(poNumber);
    }

    @PutMapping(value = "/cancelPartialPurchaseOrder")
    public ResponseDTO cancelPartialPurchaseOrder(@Valid @RequestBody CancelPartialPurchaseOrderParameterDTO cancelPartialPurchaseOrderParameterDTO) {
        return purchaseOrderServiceExtended.cancelPartialPurchaseOrder(cancelPartialPurchaseOrderParameterDTO);
    }

    @PutMapping(value = "/receivePurchaseOrder")
    public ResponseDTO receivePurchaseOrder(@Valid @RequestBody ReceivePurchaseOrderParameterDTO receivePurchaseOrderParameterDTO) {
        return purchaseOrderServiceExtended.receivePurchaseOrder(receivePurchaseOrderParameterDTO);
    }

    //===== String opType = "POByPoId";
    //===== String opType = "POByPoNumber";
    //===== String opType = "POByFromDateAndToDateOrPOStatus"; String parameterValue="From_Date,To_Date,PO_Status" Ex- "2023-05-01,2023-05-05,Raised"
    //===== String opType = "POByVendorId";
    //===== String opType = "POItemsByPOId";
    //===== String opType = "POItemsByPONumber";
    //===== String opType = "POItemsByPOItemID";
    //===== String opType = "ReceivedPOItemsByPOId";
    //===== String opType = "ReceivedPOItemsByPONumber";
    //===== String opType = "ReceivedPOItemsByItemReceivedID";
    @GetMapping("/getPurchaseOrderData")
    public ResponseDTO getPurchaseOrderData(
        @NotNull(message = "Parameter_Value must be provided.")
        @NotBlank(message = "Parameter_Value must be provided.")
        @RequestParam("parameterValue") String parameterValue,
        @NotNull(message = "Operation_Type must be provided.")
        @NotBlank(message = "Operation_Type must be provided.")
        @RequestParam("opType") String opType
    ) {
        return purchaseOrderServiceExtended.getPurchaseOrderData(parameterValue, opType);
    }
}
