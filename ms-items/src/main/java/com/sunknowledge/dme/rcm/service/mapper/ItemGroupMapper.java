package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemGroup;
import com.sunknowledge.dme.rcm.service.dto.ItemGroupDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemGroup} and its DTO {@link ItemGroupDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemGroupMapper extends EntityMapper<ItemGroupDTO, ItemGroup> {}
