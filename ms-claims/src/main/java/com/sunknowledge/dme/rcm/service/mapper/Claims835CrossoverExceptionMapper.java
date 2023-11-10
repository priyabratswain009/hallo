package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.Claims835CrossoverException;
import com.sunknowledge.dme.rcm.service.dto.Claims835CrossoverExceptionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Claims835CrossoverException} and its DTO {@link Claims835CrossoverExceptionDTO}.
 */
@Mapper(componentModel = "spring")
public interface Claims835CrossoverExceptionMapper extends EntityMapper<Claims835CrossoverExceptionDTO, Claims835CrossoverException> {}
