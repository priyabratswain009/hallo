package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PriceDetailsAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PriceDetailsAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriceDetailsAuditLogRepository extends JpaRepository<PriceDetailsAuditLog, Long> {}
