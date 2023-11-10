package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PriceMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.PriceMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PriceMasterAuditLog} and its DTO {@link PriceMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface PriceMasterAuditLogMapper extends EntityMapper<PriceMasterAuditLogDTO, PriceMasterAuditLog> {}
