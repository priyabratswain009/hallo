package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemSerialNumber;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemSerialNumber} and its DTO {@link ItemSerialNumberDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemSerialNumberMapper extends EntityMapper<ItemSerialNumberDTO, ItemSerialNumber> {}
