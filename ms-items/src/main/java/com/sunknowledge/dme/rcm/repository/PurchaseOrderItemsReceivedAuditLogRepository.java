package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceivedAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PurchaseOrderItemsReceivedAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseOrderItemsReceivedAuditLogRepository extends JpaRepository<PurchaseOrderItemsReceivedAuditLog, Long> {}
