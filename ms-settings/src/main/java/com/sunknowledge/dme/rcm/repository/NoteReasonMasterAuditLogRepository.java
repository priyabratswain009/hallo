package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.NoteReasonMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the NoteReasonMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NoteReasonMasterAuditLogRepository extends JpaRepository<NoteReasonMasterAuditLog, Long> {}
