package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemSerialNumberAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemSerialNumberAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemSerialNumberAuditLogRepository extends JpaRepository<ItemSerialNumberAuditLog, Long> {}
