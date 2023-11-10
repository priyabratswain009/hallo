package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceived;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PurchaseOrderItemsReceived entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseOrderItemsReceivedRepository extends JpaRepository<PurchaseOrderItemsReceived, Long> {}
