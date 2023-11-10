package com.sunknowledge.dme.rcm.repository.po;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItems;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsRepository;

import java.util.List;

public interface PurchaseOrderItemsRepositoryExtended extends PurchaseOrderItemsRepository {
    List<PurchaseOrderItems> findByItemIdAndStatusIgnoreCase(Long itemId, String active);

    List<PurchaseOrderItems> findByPoIdAndItemIdIn(Long poId, List itemList);

    List<PurchaseOrderItems> getItemsByPoIdAndStatusIgnoreCase(Long poId, String active);

    List<PurchaseOrderItems> getItemsByPoNumberAndStatusIgnoreCase(String poNumber, String status);

    List<PurchaseOrderItems> getItemsByPoItemsIdAndStatusIgnoreCase(Long poItemsID, String status);
}
