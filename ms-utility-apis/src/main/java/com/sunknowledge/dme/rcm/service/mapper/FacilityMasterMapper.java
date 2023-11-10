package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.FacilityMaster;
import com.sunknowledge.dme.rcm.service.dto.FacilityMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FacilityMaster} and its DTO {@link FacilityMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface FacilityMasterMapper extends EntityMapper<FacilityMasterDTO, FacilityMaster> {}
