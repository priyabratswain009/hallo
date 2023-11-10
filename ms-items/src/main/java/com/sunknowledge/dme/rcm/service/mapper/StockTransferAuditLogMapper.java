package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.StockTransferAuditLog;
import com.sunknowledge.dme.rcm.service.dto.StockTransferAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link StockTransferAuditLog} and its DTO {@link StockTransferAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface StockTransferAuditLogMapper extends EntityMapper<StockTransferAuditLogDTO, StockTransferAuditLog> {}
