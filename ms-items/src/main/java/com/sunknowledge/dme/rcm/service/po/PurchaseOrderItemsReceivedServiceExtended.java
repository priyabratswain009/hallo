package com.sunknowledge.dme.rcm.service.po;

import com.sunknowledge.dme.rcm.service.PurchaseOrderItemsReceivedService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.po.ReceiptDropshipPurchaseOrderParameterDTO;

import java.util.List;

public interface PurchaseOrderItemsReceivedServiceExtended extends PurchaseOrderItemsReceivedService {
    List<PurchaseOrderItemsReceivedDTO> getPurchaseOrderItemsReceivedByItemId(Long itemId);

    List<PurchaseOrderItemsReceivedDTO> getReceivedPOItemsByPOIdAndStatus(Long poId, String status);

    List<PurchaseOrderItemsReceivedDTO> getReceivedPOItemsByPONumberAndStatus(String poNumber, String status);

    List<PurchaseOrderItemsReceivedDTO> getReceivedPOItemsByItemReceivedIDAndStatus(Long itemReceivedID, String status);

    ResponseDTO saveReceiptDropshipPurchaseOrder(ReceiptDropshipPurchaseOrderParameterDTO receiptDropshipPurchaseOrderParameterDTO);
}
