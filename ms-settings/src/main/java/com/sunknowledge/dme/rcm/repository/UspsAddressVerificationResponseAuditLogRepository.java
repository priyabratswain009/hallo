package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponseAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the UspsAddressVerificationResponseAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UspsAddressVerificationResponseAuditLogRepository extends JpaRepository<UspsAddressVerificationResponseAuditLog, Long> {}
