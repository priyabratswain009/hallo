package com.sunknowledge.dme.rcm.service.items;

import com.sunknowledge.dme.rcm.service.ItemItemlocationMapService;
import com.sunknowledge.dme.rcm.service.dto.ItemItemlocationMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemItemlocationMapInputDTO;

import java.util.List;

public interface ItemItemlocationMapServiceExtended extends ItemItemlocationMapService {
    ResponseDTO saveItemItemlocationMap(ItemItemlocationMapInputDTO itemItemlocationMapInputDTO);

    ResponseDTO deactiveItemItemLocationMap(ItemItemlocationMapInputDTO itemItemlocationMapInputDTO);

    List<ItemItemlocationMapDTO> getItemItemlocationMapByItemId(Long itemId);

    List<ItemItemlocationMapDTO> getItemItemlocationMapByItemLocationId(Long itemLocationId);

    List<ItemItemlocationMapDTO> getItemItemlocationMapByItemLocationName(String itemName);

    List<ItemItemlocationMapDTO> getItemItemlocationMapByItemName(String itemName);

    List<ItemItemlocationMapDTO> getItemItemlocationMapByStatus(String status);

    List<ItemItemlocationMapDTO> getAll();

    ResponseDTO setItemItemlocationMapStatusById(Long itemItemlocationMapId, String status);
}
