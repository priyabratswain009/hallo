package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientInsVerifStatAuditLog;
import com.sunknowledge.dme.rcm.service.dto.PatientInsVerifStatAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientInsVerifStatAuditLog} and its DTO {@link PatientInsVerifStatAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientInsVerifStatAuditLogMapper extends EntityMapper<PatientInsVerifStatAuditLogDTO, PatientInsVerifStatAuditLog> {}
