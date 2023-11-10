package com.sunknowledge.dme.rcm.repository.items;

import com.sunknowledge.dme.rcm.domain.ItemInventoryStatus;
import com.sunknowledge.dme.rcm.repository.ItemInventoryStatusRepository;

import java.util.List;

public interface ItemInventoryStatusRepositoryExtended extends ItemInventoryStatusRepository {
    List<ItemInventoryStatus> findByItemIdAndStatusIgnoreCase(Long itemId, String active);

    ItemInventoryStatus findByItemInventoryStatusId(Long itemInventoryStatusId);

    List<ItemInventoryStatus> findByItemLocationIdAndStatusIgnoreCase(Long locationId, String status);

    List<ItemInventoryStatus> findByStatusIgnoreCase(String active);

    List<ItemInventoryStatus> findByItemIdAndItemLocationIdAndStatusIgnoreCase(Long itemId, Long locationId, String active);
}
