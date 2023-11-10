package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemMasterAuditLogRepository extends JpaRepository<ItemMasterAuditLog, Long> {}
