package com.sunknowledge.dme.rcm.service.po;

import com.sunknowledge.dme.rcm.service.PurchaseOrderService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderDTO;
import com.sunknowledge.dme.rcm.service.dto.po.*;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public interface PurchaseOrderServiceExtended<T> extends PurchaseOrderService {
    List<PurchaseOrderDTO> getPOByPONumber(String poNumber);

    List<Map> getVendorWiseItems(String itemNo, String itemName, Long vendorId);

    ResponseDTO savePurchaseOrder(PurchaseOrderParameterDTO purchaseOrderParameterDTO);

    ResponseDTO cancelFullPurchaseOrder(Long poId);

    ResponseDTO cancelPartialPurchaseOrder(CancelPartialPurchaseOrderParameterDTO cancelPartialPurchaseOrderParameterDTO);

    ResponseDTO receivePurchaseOrder(ReceivePurchaseOrderParameterDTO receivePurchaseOrderParameterDTO);

    ResponseDTO getPurchaseOrderData(String param, String opType);

    ResponseDTO saveDropshipPurchaseOrder(@Valid DropshipPurchaseOrderParameterDTO purchaseOrderParameterDTO);

    ResponseDTO cancelDropshipPurchaseOrder(String poNumber);

    ResponseDTO saveReceiptDropshipPurchaseOrder(ReceiptDropshipPurchaseOrderParameterDTO receiptDropshipPurchaseOrderParameterDTO);
}
