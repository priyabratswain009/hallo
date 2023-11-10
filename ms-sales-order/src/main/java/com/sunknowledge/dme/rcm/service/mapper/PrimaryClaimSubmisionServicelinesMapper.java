package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionServicelines;
import com.sunknowledge.dme.rcm.service.dto.PrimaryClaimSubmisionServicelinesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrimaryClaimSubmisionServicelines} and its DTO {@link PrimaryClaimSubmisionServicelinesDTO}.
 */
@Mapper(componentModel = "spring")
public interface PrimaryClaimSubmisionServicelinesMapper
    extends EntityMapper<PrimaryClaimSubmisionServicelinesDTO, PrimaryClaimSubmisionServicelines> {}
