package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMapAuditLog;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemProcedureCodeMapAuditLog} and its DTO {@link ItemProcedureCodeMapAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemProcedureCodeMapAuditLogMapper extends EntityMapper<ItemProcedureCodeMapAuditLogDTO, ItemProcedureCodeMapAuditLog> {}
