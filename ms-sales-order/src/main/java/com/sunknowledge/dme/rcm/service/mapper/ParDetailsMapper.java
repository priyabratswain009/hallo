package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ParDetails;
import com.sunknowledge.dme.rcm.service.dto.ParDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ParDetails} and its DTO {@link ParDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ParDetailsMapper extends EntityMapper<ParDetailsDTO, ParDetails> {}
