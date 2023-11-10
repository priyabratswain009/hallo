package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemMasterAuditLog} and its DTO {@link ItemMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemMasterAuditLogMapper extends EntityMapper<ItemMasterAuditLogDTO, ItemMasterAuditLog> {}
