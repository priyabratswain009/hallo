package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.WipStatusMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.WipStatusMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WipStatusMasterAuditLog} and its DTO {@link WipStatusMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface WipStatusMasterAuditLogMapper extends EntityMapper<WipStatusMasterAuditLogDTO, WipStatusMasterAuditLog> {}
