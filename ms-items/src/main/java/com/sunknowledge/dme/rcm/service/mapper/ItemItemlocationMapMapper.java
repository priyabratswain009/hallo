package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemItemlocationMap;
import com.sunknowledge.dme.rcm.service.dto.ItemItemlocationMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemItemlocationMap} and its DTO {@link ItemItemlocationMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemItemlocationMapMapper extends EntityMapper<ItemItemlocationMapDTO, ItemItemlocationMap> {}
