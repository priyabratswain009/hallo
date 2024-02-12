package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.WipStatusMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the WipStatusMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WipStatusMasterAuditLogRepository extends JpaRepository<WipStatusMasterAuditLog, Long> {}
