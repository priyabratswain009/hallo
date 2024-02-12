package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.CoverageCriteriaFileMap;
import com.sunknowledge.dme.rcm.service.dto.CoverageCriteriaFileMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CoverageCriteriaFileMap} and its DTO {@link CoverageCriteriaFileMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface CoverageCriteriaFileMapMapper extends EntityMapper<CoverageCriteriaFileMapDTO, CoverageCriteriaFileMap> {}
