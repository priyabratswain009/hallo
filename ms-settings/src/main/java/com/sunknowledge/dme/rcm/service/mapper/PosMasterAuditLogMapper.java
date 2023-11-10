package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PosMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.PosMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PosMasterAuditLog} and its DTO {@link PosMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface PosMasterAuditLogMapper extends EntityMapper<PosMasterAuditLogDTO, PosMasterAuditLog> {}
