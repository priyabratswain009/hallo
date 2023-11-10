package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.SalesRep;
import com.sunknowledge.dme.rcm.service.dto.SalesRepDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesRep} and its DTO {@link SalesRepDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesRepMapper extends EntityMapper<SalesRepDTO, SalesRep> {}
