package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemInventoryStatus;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemInventoryStatus} and its DTO {@link ItemInventoryStatusDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemInventoryStatusMapper extends EntityMapper<ItemInventoryStatusDTO, ItemInventoryStatus> {}
