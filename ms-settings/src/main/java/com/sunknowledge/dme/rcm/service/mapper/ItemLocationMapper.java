package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemLocation;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemLocation} and its DTO {@link ItemLocationDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemLocationMapper extends EntityMapper<ItemLocationDTO, ItemLocation> {}
