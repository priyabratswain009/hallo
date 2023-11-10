package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.BranchUserMap;
import com.sunknowledge.dme.rcm.service.dto.BranchUserMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BranchUserMap} and its DTO {@link BranchUserMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface BranchUserMapMapper extends EntityMapper<BranchUserMapDTO, BranchUserMap> {}
