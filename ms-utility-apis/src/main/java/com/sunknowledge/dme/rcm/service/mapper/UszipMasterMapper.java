package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.UszipMaster;
import com.sunknowledge.dme.rcm.service.dto.UszipMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UszipMaster} and its DTO {@link UszipMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface UszipMasterMapper extends EntityMapper<UszipMasterDTO, UszipMaster> {}
