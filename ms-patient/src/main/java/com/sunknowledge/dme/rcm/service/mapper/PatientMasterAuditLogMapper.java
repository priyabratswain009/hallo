package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientMasterAuditLog;
import com.sunknowledge.dme.rcm.service.dto.PatientMasterAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientMasterAuditLog} and its DTO {@link PatientMasterAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientMasterAuditLogMapper extends EntityMapper<PatientMasterAuditLogDTO, PatientMasterAuditLog> {}
