package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemVendorMappingAuditLog;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingAuditLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemVendorMappingAuditLog} and its DTO {@link ItemVendorMappingAuditLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemVendorMappingAuditLogMapper extends EntityMapper<ItemVendorMappingAuditLogDTO, ItemVendorMappingAuditLog> {}
