package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.StopReasonMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.StopReasonMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link StopReasonMasterAuditLog} and its DTO {@link StopReasonMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface StopReasonMasterAuditLogMapper extends EntityMapper<StopReasonMasterAuditLogDTO, StopReasonMasterAuditLog> {}
