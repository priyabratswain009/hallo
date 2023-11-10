package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.FunctionalAbilityAuditLog;
import com.sunknowledge.dme.rcm.service.dto.FunctionalAbilityAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FunctionalAbilityAuditLog} and its DTO {@link FunctionalAbilityAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface FunctionalAbilityAuditLogMapper extends EntityMapper<FunctionalAbilityAuditLogDTO, FunctionalAbilityAuditLog> {}
