package com.sunknowledge.dme.rcm.repository.pickupExchange;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.PickupExchangeItems;
import com.sunknowledge.dme.rcm.repository.PickupExchangeItemsRepository;

import reactor.core.publisher.Flux;

public interface PickupExchangeItemsRepo extends PickupExchangeItemsRepository {

	@Query("select * from so.t_pickup_exchange_items where pickup_exchange_id = :pickupExchangeId")
	Flux<PickupExchangeItems> getPickupExchangeItemsByPickupExchangeId(@Param("pickupExchangeId") Long pickupExchangeId);
	
}
