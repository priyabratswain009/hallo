package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PosMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PosMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PosMasterAuditLogRepository extends JpaRepository<PosMasterAuditLog, Long> {}
