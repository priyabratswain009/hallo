package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.BranchInsuranceMap;
import com.sunknowledge.dme.rcm.service.dto.BranchInsuranceMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BranchInsuranceMap} and its DTO {@link BranchInsuranceMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface BranchInsuranceMapMapper extends EntityMapper<BranchInsuranceMapDTO, BranchInsuranceMap> {}
