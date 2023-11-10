package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemInventoryStatusAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemInventoryStatusAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemInventoryStatusAuditLogRepository extends JpaRepository<ItemInventoryStatusAuditLog, Long> {}
