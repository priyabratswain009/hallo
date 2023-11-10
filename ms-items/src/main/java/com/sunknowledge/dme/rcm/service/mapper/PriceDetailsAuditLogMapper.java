package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PriceDetailsAuditLog;
import com.sunknowledge.dme.rcm.service.dto.PriceDetailsAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PriceDetailsAuditLog} and its DTO {@link PriceDetailsAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface PriceDetailsAuditLogMapper extends EntityMapper<PriceDetailsAuditLogDTO, PriceDetailsAuditLog> {}
