package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimFormMaster;
import com.sunknowledge.dme.rcm.service.dto.ClaimFormMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimFormMaster} and its DTO {@link ClaimFormMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClaimFormMasterMapper extends EntityMapper<ClaimFormMasterDTO, ClaimFormMaster> {}
