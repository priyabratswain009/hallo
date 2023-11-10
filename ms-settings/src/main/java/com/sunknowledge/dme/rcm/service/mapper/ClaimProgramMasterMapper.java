package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimProgramMaster;
import com.sunknowledge.dme.rcm.service.dto.ClaimProgramMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimProgramMaster} and its DTO {@link ClaimProgramMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClaimProgramMasterMapper extends EntityMapper<ClaimProgramMasterDTO, ClaimProgramMaster> {}
