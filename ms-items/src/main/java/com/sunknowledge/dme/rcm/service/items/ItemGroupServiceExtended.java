package com.sunknowledge.dme.rcm.service.items;

import com.sunknowledge.dme.rcm.service.dto.ItemGroupDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemGroupParameterDTO;

import java.util.List;

public interface ItemGroupServiceExtended {
    ResponseDTO saveItemGroup(ItemGroupParameterDTO itemGroupParameterDTO);

    ItemGroupDTO getItemGroupById(Long itemGroupId);

    List<ItemGroupDTO> getItemGroupByName(String itemGroupName);

    List<ItemGroupDTO> getAllItemGroup();

    ResponseDTO setItemGroupStatusById(Long id, String status);
}

