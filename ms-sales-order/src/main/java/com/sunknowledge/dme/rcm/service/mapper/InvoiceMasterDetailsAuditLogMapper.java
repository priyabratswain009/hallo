package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.InvoiceMasterDetailsAuditLog;
import com.sunknowledge.dme.rcm.service.dto.InvoiceMasterDetailsAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvoiceMasterDetailsAuditLog} and its DTO {@link InvoiceMasterDetailsAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface InvoiceMasterDetailsAuditLogMapper extends EntityMapper<InvoiceMasterDetailsAuditLogDTO, InvoiceMasterDetailsAuditLog> {}
