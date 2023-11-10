package com.sunknowledge.dme.rcm.repository.pickupExchange;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.PickupExchangeItems;
import com.sunknowledge.dme.rcm.repository.PickupExchangeItemsRepository;

public interface PickupExchangeItemsRepo extends PickupExchangeItemsRepository{

	@Query(value = "From PickupExchangeItems where pickupExchangeId=:pickupExchangeId")
	List<PickupExchangeItems> getPickupExchangeItemsByPickupId(@Param("pickupExchangeId") Long pickupExchangeId);

	@Query(value = "From PickupExchangeItems where pickupExchangeItemId=:pickupExchangeItemId")
	PickupExchangeItems getPickupExchangeItemsByPickupExchangeItemId(@Param("pickupExchangeItemId") Long pickupExchangeItemId);

	@Query(value = "From PickupExchangeItems where pickupExchangeItemUuid=:pickupExchangeItemUuid")
	PickupExchangeItems getPickupExchangeItemsByUUID(@Param("pickupExchangeItemUuid") UUID pickupExchangeItemUuid);

}
