package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageRequestDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BenefitCoverageRequest} and its DTO {@link BenefitCoverageRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface BenefitCoverageRequestMapper extends EntityMapper<BenefitCoverageRequestDTO, BenefitCoverageRequest> {}
