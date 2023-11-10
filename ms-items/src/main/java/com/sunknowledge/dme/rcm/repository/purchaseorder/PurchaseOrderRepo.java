package com.sunknowledge.dme.rcm.repository.purchaseorder;

import com.sunknowledge.dme.rcm.domain.PurchaseOrder;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchaseOrderRepo extends PurchaseOrderRepository {
    @Query("FROM PurchaseOrder WHERE poNumber= :poNumber")
    PurchaseOrder getPurchaseOrderDetailsOnPoNumber(@Param("poNumber") String poNumber);
}
