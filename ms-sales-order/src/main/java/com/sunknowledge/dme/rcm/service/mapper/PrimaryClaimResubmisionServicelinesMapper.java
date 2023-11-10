package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimResubmisionServicelines;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimResubmisionServicelinesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrimaryClaimResubmisionServicelines} and its DTO {@link PrimaryClaimResubmisionServicelinesDTO}.
 */
@Mapper(componentModel = "spring")
public interface PrimaryClaimResubmisionServicelinesMapper
    extends EntityMapper<PrimaryClaimResubmisionServicelinesDTO, PrimaryClaimResubmisionServicelines> {}
