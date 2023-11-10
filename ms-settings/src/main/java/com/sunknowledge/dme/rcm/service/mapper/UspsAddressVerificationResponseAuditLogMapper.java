package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponseAuditLog;
import com.sunknowledge.dme.rcm.service.dto.UspsAddressVerificationResponseAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UspsAddressVerificationResponseAuditLog} and its DTO {@link UspsAddressVerificationResponseAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface UspsAddressVerificationResponseAuditLogMapper
    extends EntityMapper<UspsAddressVerificationResponseAuditLogDTO, UspsAddressVerificationResponseAuditLog> {}
