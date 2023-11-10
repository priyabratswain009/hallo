package com.sunknowledge.dme.rcm.service.items;

import com.sunknowledge.dme.rcm.service.ItemProcedureCodeMapService;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemProcedureCodeMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemMasterParameterDTO;

import java.util.List;

public interface ItemProcedureCodeMapServiceExtended extends ItemProcedureCodeMapService {
    void saveItemProcedureCodeMap(ItemMasterParameterDTO itemMasterParameterDTO, ItemMasterDTO itemMasterDTO);

    List<ItemProcedureCodeMapDTO> getItemProcedureCodeMapById(Long itemMasterItemId);

    List<ItemProcedureCodeMapDTO> getItemProcedureCodeMapByItemName(String itemName);

    List<ItemProcedureCodeMapDTO> getItemProcedureCodeMapByItemNo(String itemNumber);

    List<ItemProcedureCodeMapDTO> getItemProcedureCodeMapByItemDescription(String itemDescription);

    List<ItemProcedureCodeMapDTO> getAllItemProcedureCodeMapData();

    List<ItemProcedureCodeMapDTO> getItemProcedureCodeMapByStatus(String status);

    List<ItemProcedureCodeMapDTO> getItemProcedureCodeMapByProcedureCode(String procedureCode);

    ResponseDTO setItemProcedureCodeMapStatusById(Long itemProcedureCodeMapId, String status);
}
