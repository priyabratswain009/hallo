package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageResponseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BenefitCoverageResponse} and its DTO {@link BenefitCoverageResponseDTO}.
 */
@Mapper(componentModel = "spring")
public interface BenefitCoverageResponseMapper extends EntityMapper<BenefitCoverageResponseDTO, BenefitCoverageResponse> {}
