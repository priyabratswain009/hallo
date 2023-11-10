package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.BranchGroup;
import com.sunknowledge.dme.rcm.service.dto.BranchGroupDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BranchGroup} and its DTO {@link BranchGroupDTO}.
 */
@Mapper(componentModel = "spring")
public interface BranchGroupMapper extends EntityMapper<BranchGroupDTO, BranchGroup> {}
