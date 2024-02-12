package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InsuranceGroupMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InsuranceGroupMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceGroupMasterAuditLogRepository extends JpaRepository<InsuranceGroupMasterAuditLog, Long> {}
