package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetailsAuditLog;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderClinicalDetailsAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderClinicalDetailsAuditLog} and its DTO {@link SalesOrderClinicalDetailsAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderClinicalDetailsAuditLogMapper
    extends EntityMapper<SalesOrderClinicalDetailsAuditLogDTO, SalesOrderClinicalDetailsAuditLog> {}
