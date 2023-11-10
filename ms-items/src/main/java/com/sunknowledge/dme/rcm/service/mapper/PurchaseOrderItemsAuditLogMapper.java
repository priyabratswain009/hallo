package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsAuditLog;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PurchaseOrderItemsAuditLog} and its DTO {@link PurchaseOrderItemsAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface PurchaseOrderItemsAuditLogMapper extends EntityMapper<PurchaseOrderItemsAuditLogDTO, PurchaseOrderItemsAuditLog> {}
