package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.Claims835CrossoverProcessed;
import com.sunknowledge.dme.rcm.service.dto.Claims835CrossoverProcessedDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Claims835CrossoverProcessed} and its DTO {@link Claims835CrossoverProcessedDTO}.
 */
@Mapper(componentModel = "spring")
public interface Claims835CrossoverProcessedMapper extends EntityMapper<Claims835CrossoverProcessedDTO, Claims835CrossoverProcessed> {}
