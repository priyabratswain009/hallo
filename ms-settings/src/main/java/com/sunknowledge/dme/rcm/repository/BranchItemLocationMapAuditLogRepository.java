package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.BranchItemLocationMapAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BranchItemLocationMapAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchItemLocationMapAuditLogRepository extends JpaRepository<BranchItemLocationMapAuditLog, Long> {}
