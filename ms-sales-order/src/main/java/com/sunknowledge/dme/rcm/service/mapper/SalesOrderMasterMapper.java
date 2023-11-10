package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesOrderMaster} and its DTO {@link SalesOrderMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesOrderMasterMapper extends EntityMapper<SalesOrderMasterDTO, SalesOrderMaster> {}
