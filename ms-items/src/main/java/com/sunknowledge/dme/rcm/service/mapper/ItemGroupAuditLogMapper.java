package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemGroupAuditLog;
import com.sunknowledge.dme.rcm.service.dto.ItemGroupAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemGroupAuditLog} and its DTO {@link ItemGroupAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemGroupAuditLogMapper extends EntityMapper<ItemGroupAuditLogDTO, ItemGroupAuditLog> {}
