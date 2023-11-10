package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PurchaseOrderAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseOrderAuditLogRepository extends JpaRepository<PurchaseOrderAuditLog, Long> {}
