package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ClaimsCOB835Master;
import com.sunknowledge.dme.rcm.service.dto.ClaimsCOB835MasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimsCOB835Master} and its DTO {@link ClaimsCOB835MasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClaimsCOB835MasterMapper extends EntityMapper<ClaimsCOB835MasterDTO, ClaimsCOB835Master> {}
