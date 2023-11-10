package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.HoldReasonMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HoldReasonMasterAuditLog} and its DTO {@link HoldReasonMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface HoldReasonMasterAuditLogMapper extends EntityMapper<HoldReasonMasterAuditLogDTO, HoldReasonMasterAuditLog> {}
