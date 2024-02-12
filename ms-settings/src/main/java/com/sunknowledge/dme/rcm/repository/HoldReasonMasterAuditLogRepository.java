package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.HoldReasonMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the HoldReasonMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HoldReasonMasterAuditLogRepository extends JpaRepository<HoldReasonMasterAuditLog, Long> {}
