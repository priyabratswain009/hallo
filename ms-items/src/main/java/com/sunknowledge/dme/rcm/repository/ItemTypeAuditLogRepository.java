package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemTypeAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemTypeAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemTypeAuditLogRepository extends JpaRepository<ItemTypeAuditLog, Long> {}
