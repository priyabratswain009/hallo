package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemLocationAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemLocationAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemLocationAuditLogRepository extends JpaRepository<ItemLocationAuditLog, Long> {}
