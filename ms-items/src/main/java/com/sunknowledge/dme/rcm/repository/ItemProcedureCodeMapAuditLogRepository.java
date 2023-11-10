package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMapAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemProcedureCodeMapAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemProcedureCodeMapAuditLogRepository extends JpaRepository<ItemProcedureCodeMapAuditLog, Long> {}
