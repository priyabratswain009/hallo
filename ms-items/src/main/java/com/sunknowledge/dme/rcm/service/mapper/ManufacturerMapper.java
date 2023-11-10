package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.Manufacturer;
import com.sunknowledge.dme.rcm.service.dto.ManufacturerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Manufacturer} and its DTO {@link ManufacturerDTO}.
 */
@Mapper(componentModel = "spring")
public interface ManufacturerMapper extends EntityMapper<ManufacturerDTO, Manufacturer> {}
