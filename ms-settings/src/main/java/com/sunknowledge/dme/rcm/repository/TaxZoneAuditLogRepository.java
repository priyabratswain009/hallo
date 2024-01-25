package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.TaxZoneAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TaxZoneAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaxZoneAuditLogRepository extends JpaRepository<TaxZoneAuditLog, Long> {}
