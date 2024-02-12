package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InsuranceDocumentAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InsuranceDocumentAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceDocumentAuditLogRepository extends JpaRepository<InsuranceDocumentAuditLog, Long> {}
