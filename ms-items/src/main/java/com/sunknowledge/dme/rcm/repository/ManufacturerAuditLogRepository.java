package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ManufacturerAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ManufacturerAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManufacturerAuditLogRepository extends JpaRepository<ManufacturerAuditLog, Long> {}
