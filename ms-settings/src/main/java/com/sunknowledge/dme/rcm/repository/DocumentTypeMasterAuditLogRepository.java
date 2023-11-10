package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DocumentTypeMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DocumentTypeMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentTypeMasterAuditLogRepository extends JpaRepository<DocumentTypeMasterAuditLog, Long> {}
