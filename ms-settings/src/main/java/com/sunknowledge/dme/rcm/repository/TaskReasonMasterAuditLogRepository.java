package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.TaskReasonMasterAuditLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TaskReasonMasterAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskReasonMasterAuditLogRepository extends JpaRepository<TaskReasonMasterAuditLog, Long> {}
