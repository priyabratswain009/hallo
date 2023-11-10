package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemGroupAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemGroupAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemGroupAuditLogRepository extends JpaRepository<ItemGroupAuditLog, Long> {}
