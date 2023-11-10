package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.StockTransferAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the StockTransferAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockTransferAuditLogRepository extends JpaRepository<StockTransferAuditLog, Long> {}
