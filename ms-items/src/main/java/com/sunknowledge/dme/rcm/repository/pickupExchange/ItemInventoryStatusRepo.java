package com.sunknowledge.dme.rcm.repository.pickupExchange;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.ItemInventoryStatus;
import com.sunknowledge.dme.rcm.repository.ItemInventoryStatusRepository;

public interface ItemInventoryStatusRepo extends ItemInventoryStatusRepository{

	@Query(value = "From ItemInventoryStatus where itemNo=:itemNo and itemId=:itemId and itemLocationId=:itemLocationId")
	ItemInventoryStatus getItemItemInventoryStatusByitemNoIdLocation(@Param("itemNo") String itemNo, @Param("itemId") Long itemId,
			@Param("itemLocationId") Long itemLocationId);
	
}
