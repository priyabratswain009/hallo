package com.sunknowledge.dme.rcm.service.mapper;

import com.sunknowledge.dme.rcm.domain.ItemMaster;
import com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMap;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemProcedureCodeMap} and its DTO {@link ItemProcedureCodeMapDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItemProcedureCodeMapMapper extends EntityMapper<ItemProcedureCodeMapDTO, ItemProcedureCodeMap> {
    @Mapping(target = "itemMaster", source = "itemMaster", qualifiedByName = "itemMasterItemId")
    ItemProcedureCodeMapDTO toDto(ItemProcedureCodeMap s);

    @Named("itemMasterItemId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "itemId", source = "itemId")
    ItemMasterDTO toDtoItemMasterItemId(ItemMaster itemMaster);
}
