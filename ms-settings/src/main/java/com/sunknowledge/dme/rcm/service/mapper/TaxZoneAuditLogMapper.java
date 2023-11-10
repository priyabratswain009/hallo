package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.TaxZoneAuditLog;
import com.sunknowledge.dme.rcm.service.dto.TaxZoneAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaxZoneAuditLog} and its DTO {@link TaxZoneAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaxZoneAuditLogMapper extends EntityMapper<TaxZoneAuditLogDTO, TaxZoneAuditLog> {}
