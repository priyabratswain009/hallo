package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.FunctionalityMaster;
import com.sunknowledge.dme.rcm.service.dto.FunctionalityMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FunctionalityMaster} and its DTO {@link FunctionalityMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface FunctionalityMasterMapper extends EntityMapper<FunctionalityMasterDTO, FunctionalityMaster> {}
