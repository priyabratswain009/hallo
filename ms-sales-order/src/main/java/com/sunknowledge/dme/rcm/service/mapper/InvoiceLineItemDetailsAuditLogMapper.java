package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.InvoiceLineItemDetailsAuditLog;
import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvoiceLineItemDetailsAuditLog} and its DTO {@link InvoiceLineItemDetailsAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface InvoiceLineItemDetailsAuditLogMapper
    extends EntityMapper<InvoiceLineItemDetailsAuditLogDTO, InvoiceLineItemDetailsAuditLog> {}
