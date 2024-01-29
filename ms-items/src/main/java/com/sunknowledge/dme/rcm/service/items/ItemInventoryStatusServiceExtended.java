package com.sunknowledge.dme.rcm.service.items;

import com.sunknowledge.dme.rcm.service.dto.common.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.ItemInventoryStatusService;
import com.sunknowledge.dme.rcm.service.dto.ItemInventoryStatusDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.InventoryInputDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.ItemInventoryStatusParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.inventory.ItemInventoryStatusZeroQtyParameterDTO;

import java.util.List;

public interface ItemInventoryStatusServiceExtended extends ItemInventoryStatusService {
    List<ItemInventoryStatusDTO> getItemInventoryStatusByItemId(Long itemId);

    ResponseDTO saveInventoryStatusManualQtyUpdate(ItemInventoryStatusParameterDTO itemInventoryStatusParameterDTO);

    ResponseDTO saveInventoryStatusByZeroQtyUpdate(ItemInventoryStatusZeroQtyParameterDTO itemInventoryStatusZeroQtyParameterDTO);

    ItemInventoryStatusDTO getInventoryStatusByItemInventoryStatusId(Long itemInventoryStatusId);

    List<ItemInventoryStatusDTO> getInventoryStatusByLocationId(Long locationId);

    List<ItemInventoryStatusDTO> getAllInventoryStatusData();

    List<ItemInventoryStatusDTO> getInventoryStatusByStatus(String status);

    ResponseDTO setInventoryStatusById(Long itemInventoryStatusId, String status);

    List<ItemInventoryStatusDTO> getInventoryByItemIdAndLocationId(Long itemId, Long locationId);

    ServiceOutcome saveInventoryStatusByItemIdAndItemLocationId(InventoryInputDTO inputDTO) throws Exception;

    ServiceOutcome saveInventoryStatusByItemIdAndItemLocationIdForDelivered(InventoryInputDTO inputDTOs) throws Exception;
}
