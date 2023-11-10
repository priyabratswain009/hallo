package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.BranchOfficeAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BranchOfficeAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchOfficeAuditLogRepository extends JpaRepository<BranchOfficeAuditLog, Long> {}
