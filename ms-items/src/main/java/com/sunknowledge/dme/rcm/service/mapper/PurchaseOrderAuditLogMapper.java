package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderAuditLog;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PurchaseOrderAuditLog} and its DTO {@link PurchaseOrderAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface PurchaseOrderAuditLogMapper extends EntityMapper<PurchaseOrderAuditLogDTO, PurchaseOrderAuditLog> {}
