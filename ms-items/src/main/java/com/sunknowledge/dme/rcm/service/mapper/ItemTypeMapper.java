package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemType;
import com.sunknowledge.dme.rcm.service.dto.ItemTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemType} and its DTO {@link ItemTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemTypeMapper extends EntityMapper<ItemTypeDTO, ItemType> {}
