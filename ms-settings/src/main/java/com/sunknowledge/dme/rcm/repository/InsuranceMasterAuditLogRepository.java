package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InsuranceMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the InsuranceMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceMasterAuditLogRepository extends JpaRepository<InsuranceMasterAuditLog, Long> {}
