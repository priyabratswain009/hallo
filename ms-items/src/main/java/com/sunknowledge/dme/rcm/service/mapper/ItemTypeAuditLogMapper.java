package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemTypeAuditLog;
import com.sunknowledge.dme.rcm.service.dto.ItemTypeAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemTypeAuditLog} and its DTO {@link ItemTypeAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemTypeAuditLogMapper extends EntityMapper<ItemTypeAuditLogDTO, ItemTypeAuditLog> {}
