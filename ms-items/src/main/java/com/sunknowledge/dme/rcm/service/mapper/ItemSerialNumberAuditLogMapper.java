package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemSerialNumberAuditLog;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemSerialNumberAuditLog} and its DTO {@link ItemSerialNumberAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemSerialNumberAuditLogMapper extends EntityMapper<ItemSerialNumberAuditLogDTO, ItemSerialNumberAuditLog> {}
