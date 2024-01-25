package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderClassificationAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SalesOrderClassificationAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderClassificationAuditLogRepository extends JpaRepository<SalesOrderClassificationAuditLog, Long> {}
