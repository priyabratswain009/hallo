package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PatientClinicalInformationAuditLog;
import com.sunknowledge.dme.rcm.service.dto.PatientClinicalInformationAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientClinicalInformationAuditLog} and its DTO {@link PatientClinicalInformationAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientClinicalInformationAuditLogMapper
    extends EntityMapper<PatientClinicalInformationAuditLogDTO, PatientClinicalInformationAuditLog> {}
