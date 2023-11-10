package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SecondaryClaimSubmisionMasterAuditLog} and its DTO {@link SecondaryClaimSubmisionMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface SecondaryClaimSubmisionMasterAuditLogMapper
    extends EntityMapper<SecondaryClaimSubmisionMasterAuditLogDTO, SecondaryClaimSubmisionMasterAuditLog> {}
