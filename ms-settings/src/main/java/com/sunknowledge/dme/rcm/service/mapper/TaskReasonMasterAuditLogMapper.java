package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.TaskReasonMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.TaskReasonMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaskReasonMasterAuditLog} and its DTO {@link TaskReasonMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaskReasonMasterAuditLogMapper extends EntityMapper<TaskReasonMasterAuditLogDTO, TaskReasonMasterAuditLog> {}
