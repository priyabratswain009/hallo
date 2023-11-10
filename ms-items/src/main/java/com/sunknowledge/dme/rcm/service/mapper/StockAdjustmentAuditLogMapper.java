package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.StockAdjustmentAuditLog;
import com.sunknowledge.dme.rcm.service.dto.StockAdjustmentAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link StockAdjustmentAuditLog} and its DTO {@link StockAdjustmentAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface StockAdjustmentAuditLogMapper extends EntityMapper<StockAdjustmentAuditLogDTO, StockAdjustmentAuditLog> {}
