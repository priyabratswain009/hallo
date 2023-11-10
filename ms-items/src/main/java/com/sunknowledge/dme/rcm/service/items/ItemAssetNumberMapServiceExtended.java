package com.sunknowledge.dme.rcm.service.items;

import com.sunknowledge.dme.rcm.service.ItemAssetNumberMapService;
import com.sunknowledge.dme.rcm.service.dto.ItemAssetNumberMapDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemAssetNumberMapExtendedDTO;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.UUID;

public interface ItemAssetNumberMapServiceExtended extends ItemAssetNumberMapService {
    ResponseDTO saveItemAssetNumberMap(ItemAssetNumberMapExtendedDTO itemAssetNumberMapExtendedDTO) throws InvalidAttributeValueException;

    List<ItemAssetNumberMapDTO> getAllItemAssetNumberMapInfo();

    List<ItemAssetNumberMapDTO> getItemAssetNumberMapByUUID(UUID itemAssetNumberUuid);

    List<ItemAssetNumberMapDTO> getItemAssetNumberMapByItemIdAndAssetNo(Long itemId, String assetNo);

    List<ItemAssetNumberMapDTO> getAssetNumberByItemId(Long itemId);
}
