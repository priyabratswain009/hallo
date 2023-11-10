package com.sunknowledge.dme.rcm.service.items;

import com.sunknowledge.dme.rcm.service.ItemTypeService;
import com.sunknowledge.dme.rcm.service.dto.ItemTypeDTO;
import org.springframework.beans.PropertyValues;

import java.util.List;

public interface ItemTypeServiceExtended extends ItemTypeService {
    List<ItemTypeDTO> getItemTypeByItemTypeId(Long itemTypeId);
}
