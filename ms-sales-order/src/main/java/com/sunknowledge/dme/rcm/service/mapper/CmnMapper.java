package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.Cmn;
import com.sunknowledge.dme.rcm.service.dto.CmnDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cmn} and its DTO {@link CmnDTO}.
 */
@Mapper(componentModel = "spring")
public interface CmnMapper extends EntityMapper<CmnDTO, Cmn> {}
