package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemInventoryStatusAuditLog;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemInventoryStatusAuditLog} and its DTO {@link ItemInventoryStatusAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemInventoryStatusAuditLogMapper extends EntityMapper<ItemInventoryStatusAuditLogDTO, ItemInventoryStatusAuditLog> {}
