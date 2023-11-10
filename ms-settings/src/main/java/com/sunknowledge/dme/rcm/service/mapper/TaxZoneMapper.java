package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.TaxZone;
import com.sunknowledge.dme.rcm.service.dto.TaxZoneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaxZone} and its DTO {@link TaxZoneDTO}.
 */
@Mapper(componentModel = "spring")
public interface TaxZoneMapper extends EntityMapper<TaxZoneDTO, TaxZone> {}
