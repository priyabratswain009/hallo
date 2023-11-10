package com.sunknowledge.dme.rcm.service.items;

import com.sunknowledge.dme.rcm.service.ItemVendorMappingService;
import com.sunknowledge.dme.rcm.service.dto.ItemVendorMappingDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemVendorMapExtendedDTO;
import org.springframework.beans.PropertyValues;

import java.util.List;
import java.util.Map;

public interface ItemVendorMappingServiceExtended extends ItemVendorMappingService {
    ResponseDTO saveItemVendorMap(ItemVendorMapExtendedDTO itemVendorMapExtendedDTO);

    ResponseDTO deactiveItemVendorMap(ItemVendorMapExtendedDTO itemVendorMapExtendedDTO);

    List<ItemVendorMappingDTO> getItemVendorMapByItemId(Long itemId);

    List<ItemVendorMappingDTO> getItemVendorMapByVendorId(Long vendorId);

    List<ItemVendorMappingDTO> getItemVendorMapByItemName(String itemName);

    List<ItemVendorMappingDTO> getItemVendorMapByVendorName(String vendorName);

    List<ItemVendorMappingDTO> getItemVendorMapByStatus(String status);

    List<ItemVendorMappingDTO> getItemVendorMapByItemVendorId(Long itemVendorId);

    ResponseDTO setItemVendorStatusById(Long itemVendorId, String status);

    List<ItemVendorMappingDTO> getItemVendorMapByItemIdAndVendorId(Long itemId, Long vendorId);

    List<Map> getVendorsByItemIdDropdown(Long itemId);
}
