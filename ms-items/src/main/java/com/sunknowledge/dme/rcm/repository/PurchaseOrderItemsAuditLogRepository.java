package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PurchaseOrderItemsAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseOrderItemsAuditLogRepository extends JpaRepository<PurchaseOrderItemsAuditLog, Long> {}
