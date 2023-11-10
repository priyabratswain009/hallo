package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.WipStatusMaster;
import com.sunknowledge.dme.rcm.service.dto.WipStatusMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WipStatusMaster} and its DTO {@link WipStatusMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface WipStatusMasterMapper extends EntityMapper<WipStatusMasterDTO, WipStatusMaster> {}
