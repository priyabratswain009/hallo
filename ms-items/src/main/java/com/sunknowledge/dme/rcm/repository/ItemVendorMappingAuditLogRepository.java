package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ItemVendorMappingAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ItemVendorMappingAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemVendorMappingAuditLogRepository extends JpaRepository<ItemVendorMappingAuditLog, Long> {}
