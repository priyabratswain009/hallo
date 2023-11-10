package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemAssetNumberMap;
import com.sunknowledge.dme.rcm.service.dto.ItemAssetNumberMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemAssetNumberMap} and its DTO {@link ItemAssetNumberMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemAssetNumberMapMapper extends EntityMapper<ItemAssetNumberMapDTO, ItemAssetNumberMap> {}
