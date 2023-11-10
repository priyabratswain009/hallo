package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ChecklistCoverageCriteriaMap;
import com.sunknowledge.dme.rcm.service.dto.ChecklistCoverageCriteriaMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChecklistCoverageCriteriaMap} and its DTO {@link ChecklistCoverageCriteriaMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface ChecklistCoverageCriteriaMapMapper extends EntityMapper<ChecklistCoverageCriteriaMapDTO, ChecklistCoverageCriteriaMap> {}
