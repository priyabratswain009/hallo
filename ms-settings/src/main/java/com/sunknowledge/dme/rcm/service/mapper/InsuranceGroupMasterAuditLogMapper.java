package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.InsuranceGroupMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.InsuranceGroupMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceGroupMasterAuditLog} and its DTO {@link InsuranceGroupMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface InsuranceGroupMasterAuditLogMapper extends EntityMapper<InsuranceGroupMasterAuditLogDTO, InsuranceGroupMasterAuditLog> {}
