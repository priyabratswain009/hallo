package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ManufacturerAuditLog;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ManufacturerAuditLog} and its DTO {@link ManufacturerAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface ManufacturerAuditLogMapper extends EntityMapper<ManufacturerAuditLogDTO, ManufacturerAuditLog> {}
