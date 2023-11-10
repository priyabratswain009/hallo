package com.sunknowledge.dme.rcm.repository.inventory;

import com.sunknowledge.dme.rcm.domain.ItemInventoryStatus;
import com.sunknowledge.dme.rcm.repository.ItemInventoryStatusRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemInventoryStatusReposito extends ItemInventoryStatusRepository {
    @Query(value = "FROM ItemInventoryStatus WHERE itemId = :itemId and itemLocationId = :itemLocationId")
    ItemInventoryStatus getItemInventoryStatusByItemLocationId(@Param("itemId") Long itemId, @Param("itemLocationId") Long itemLocationId);
}
