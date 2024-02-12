package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ClaimProgramMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ClaimProgramMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimProgramMasterAuditLogRepository extends JpaRepository<ClaimProgramMasterAuditLog, Long> {}
