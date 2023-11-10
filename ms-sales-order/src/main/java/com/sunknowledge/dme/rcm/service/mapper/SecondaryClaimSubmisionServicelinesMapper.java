package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionServicelines;
import com.sunknowledge.dme.rcm.service.dto.SecondaryClaimSubmisionServicelinesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SecondaryClaimSubmisionServicelines} and its DTO {@link SecondaryClaimSubmisionServicelinesDTO}.
 */
@Mapper(componentModel = "spring")
public interface SecondaryClaimSubmisionServicelinesMapper
    extends EntityMapper<SecondaryClaimSubmisionServicelinesDTO, SecondaryClaimSubmisionServicelines> {}
