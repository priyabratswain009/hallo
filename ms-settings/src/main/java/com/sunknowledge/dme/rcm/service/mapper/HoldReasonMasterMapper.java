package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.HoldReasonMaster;
import com.sunknowledge.dme.rcm.service.dto.HoldReasonMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HoldReasonMaster} and its DTO {@link HoldReasonMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface HoldReasonMasterMapper extends EntityMapper<HoldReasonMasterDTO, HoldReasonMaster> {}
