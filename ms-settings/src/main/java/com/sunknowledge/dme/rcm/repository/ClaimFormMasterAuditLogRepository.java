package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ClaimFormMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ClaimFormMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimFormMasterAuditLogRepository extends JpaRepository<ClaimFormMasterAuditLog, Long> {}
