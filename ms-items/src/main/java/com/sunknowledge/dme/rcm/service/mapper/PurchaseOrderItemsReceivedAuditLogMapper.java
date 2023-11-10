package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceivedAuditLog;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderItemsReceivedAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PurchaseOrderItemsReceivedAuditLog} and its DTO {@link PurchaseOrderItemsReceivedAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface PurchaseOrderItemsReceivedAuditLogMapper
    extends EntityMapper<PurchaseOrderItemsReceivedAuditLogDTO, PurchaseOrderItemsReceivedAuditLog> {}
