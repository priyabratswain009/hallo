package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.BranchInsuranceMapAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the BranchInsuranceMapAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchInsuranceMapAuditLogRepository extends JpaRepository<BranchInsuranceMapAuditLog, Long> {}
