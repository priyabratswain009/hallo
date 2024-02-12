package com.sunknowledge.dme.rcm.service.purchaseorder;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderDTO;
import com.sunknowledge.dme.rcm.service.dto.salesorder.DeliveryAddress;

import java.util.concurrent.CompletableFuture;

public interface ItemPurchaseOrderService {
    CompletableFuture<ServiceOutcome<DeliveryAddress>> generatePurchaseOrderOnPoNumber(String poNumber);
    CompletableFuture<ServiceOutcome<PurchaseOrderDTO>> getPurchaseOrderDetailsOnPONumber(String poNumber);
}
