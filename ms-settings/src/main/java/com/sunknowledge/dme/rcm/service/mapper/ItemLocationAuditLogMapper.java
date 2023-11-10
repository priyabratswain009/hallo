package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemLocationAuditLog;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemLocationAuditLog} and its DTO {@link ItemLocationAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemLocationAuditLogMapper extends EntityMapper<ItemLocationAuditLogDTO, ItemLocationAuditLog> {}
