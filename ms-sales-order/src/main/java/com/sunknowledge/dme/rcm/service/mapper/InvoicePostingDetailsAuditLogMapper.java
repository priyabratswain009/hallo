package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.InvoicePostingDetailsAuditLog;
import com.sunknowledge.dme.rcm.service.dto.InvoicePostingDetailsAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvoicePostingDetailsAuditLog} and its DTO {@link InvoicePostingDetailsAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface InvoicePostingDetailsAuditLogMapper
    extends EntityMapper<InvoicePostingDetailsAuditLogDTO, InvoicePostingDetailsAuditLog> {}
