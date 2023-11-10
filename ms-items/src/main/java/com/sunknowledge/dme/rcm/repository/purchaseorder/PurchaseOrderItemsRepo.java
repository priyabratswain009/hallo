package com.sunknowledge.dme.rcm.repository.purchaseorder;

import com.sunknowledge.dme.rcm.domain.PurchaseOrder;
import com.sunknowledge.dme.rcm.domain.PurchaseOrderItems;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PurchaseOrderItemsRepo extends PurchaseOrderItemsRepository {
    @Query("FROM PurchaseOrderItems WHERE poId= :poId")
    List<PurchaseOrderItems> getPurchaseOrderDetailsOnPo(@Param("poId") Long poId);
}
