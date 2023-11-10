package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionMaster;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimSubmisionMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrimaryClaimSubmisionMaster} and its DTO {@link PrimaryClaimSubmisionMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface PrimaryClaimSubmisionMasterMapper extends EntityMapper<PrimaryClaimSubmisionMasterDTO, PrimaryClaimSubmisionMaster> {}
