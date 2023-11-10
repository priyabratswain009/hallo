package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.BranchItemLocationMap;
import com.sunknowledge.dme.rcm.service.dto.BranchItemLocationMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BranchItemLocationMap} and its DTO {@link BranchItemLocationMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface BranchItemLocationMapMapper extends EntityMapper<BranchItemLocationMapDTO, BranchItemLocationMap> {}
