package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimResubmisionMaster;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimResubmisionMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrimaryClaimResubmisionMaster} and its DTO {@link PrimaryClaimResubmisionMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface PrimaryClaimResubmisionMasterMapper
    extends EntityMapper<PrimaryClaimResubmisionMasterDTO, PrimaryClaimResubmisionMaster> {}
