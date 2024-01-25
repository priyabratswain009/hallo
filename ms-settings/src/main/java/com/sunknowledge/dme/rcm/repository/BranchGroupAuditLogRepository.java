package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.BranchGroupAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the BranchGroupAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchGroupAuditLogRepository extends JpaRepository<BranchGroupAuditLog, Long> {}
