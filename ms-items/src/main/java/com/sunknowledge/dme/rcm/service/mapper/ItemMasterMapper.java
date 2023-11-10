package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemMaster;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemMaster} and its DTO {@link ItemMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemMasterMapper extends EntityMapper<ItemMasterDTO, ItemMaster> {}
