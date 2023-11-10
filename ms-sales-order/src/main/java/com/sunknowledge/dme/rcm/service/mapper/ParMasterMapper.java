package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.service.dto.ParMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ParMaster} and its DTO {@link ParMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface ParMasterMapper extends EntityMapper<ParMasterDTO, ParMaster> {}
