package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.InsuranceMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.InsuranceMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceMasterAuditLog} and its DTO {@link InsuranceMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface InsuranceMasterAuditLogMapper extends EntityMapper<InsuranceMasterAuditLogDTO, InsuranceMasterAuditLog> {}
