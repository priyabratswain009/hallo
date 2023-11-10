package com.sunknowledge.dme.rcm.service.items;

import com.sunknowledge.dme.rcm.service.ItemSerialNumberService;
import com.sunknowledge.dme.rcm.service.dto.ItemSerialNumberDTO;

import java.util.List;

public interface ItemSerialNumberServiceExtended extends ItemSerialNumberService {
	
    List<ItemSerialNumberDTO> getItemSerialNumberByItemId(Long itemId);
    ItemSerialNumberDTO getItemSerialNumberByserialNo(String serialNumber, Long itemId);
    
}
