package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.NoteTypeMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the NoteTypeMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoteTypeMasterAuditLogRepository extends JpaRepository<NoteTypeMasterAuditLog, Long> {}
