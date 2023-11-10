package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.WorkersCompensation;
import com.sunknowledge.dme.rcm.service.dto.WorkersCompensationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkersCompensation} and its DTO {@link WorkersCompensationDTO}.
 */
@Mapper(componentModel = "spring")
public interface WorkersCompensationMapper extends EntityMapper<WorkersCompensationDTO, WorkersCompensation> {}
