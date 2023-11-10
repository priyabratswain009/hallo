package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PriceMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PriceMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriceMasterAuditLogRepository extends JpaRepository<PriceMasterAuditLog, Long> {}
