package com.sunknowledge.dme.rcm.service.po;

import com.sunknowledge.dme.rcm.domain.PurchaseOrder;
import com.sunknowledge.dme.rcm.service.PurchaseOrderItemsService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsDTO;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedDTO;

import java.util.List;

public interface PurchaseOrderItemsServiceExtended extends PurchaseOrderItemsService {
    List<PurchaseOrderItemsDTO> getPurchaseOrderItemsByItemId(Long itemId);

    List<PurchaseOrderItemsDTO> findByPoIdAndItemIdIn(Long poId, List itemList);

    List<PurchaseOrderItemsDTO> getItemsByPOIdAndStatus(Long poId, String status);

    List<PurchaseOrderItemsDTO> getItemsByPoNumberAndStatus(String poNumber, String status);

    List<PurchaseOrderItemsDTO> getItemsByPOItemsIDAndStatus(Long poItemsID, String status);

}
