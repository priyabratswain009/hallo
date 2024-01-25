package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.StopReasonMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the StopReasonMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StopReasonMasterAuditLogRepository extends JpaRepository<StopReasonMasterAuditLog, Long> {}
