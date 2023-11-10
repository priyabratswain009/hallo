package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.Claim835277Exception;
import com.sunknowledge.dme.rcm.service.dto.Claim835277ExceptionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Claim835277Exception} and its DTO {@link Claim835277ExceptionDTO}.
 */
@Mapper(componentModel = "spring")
public interface Claim835277ExceptionMapper extends EntityMapper<Claim835277ExceptionDTO, Claim835277Exception> {}
