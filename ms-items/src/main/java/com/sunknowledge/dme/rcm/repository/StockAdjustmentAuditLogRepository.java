package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.StockAdjustmentAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the StockAdjustmentAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockAdjustmentAuditLogRepository extends JpaRepository<StockAdjustmentAuditLog, Long> {}
