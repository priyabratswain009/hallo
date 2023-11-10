package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.StopReasonMaster;
import com.sunknowledge.dme.rcm.service.dto.StopReasonMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link StopReasonMaster} and its DTO {@link StopReasonMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface StopReasonMasterMapper extends EntityMapper<StopReasonMasterDTO, StopReasonMaster> {}
