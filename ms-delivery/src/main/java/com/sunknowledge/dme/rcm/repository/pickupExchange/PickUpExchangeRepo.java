package com.sunknowledge.dme.rcm.repository.pickupExchange;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.repository.PickupExchangeRepository;

public interface PickUpExchangeRepo extends PickupExchangeRepository {

	@Query(value = "From PickupExchange")
	List<PickupExchange> getAllPickupExchangeData();

	@Query(value = "From PickupExchange where pickupExchangeUuid=:pickupExchangeUuid")
	PickupExchange getPickupExchangeByUUID(@Param("pickupExchangeUuid") UUID pickupExchangeUuid);

	@Query(value = "From PickupExchange where pickupExchangeAgentIdNo=:pickupExchangeAgentIdNo and pickupExchangeScheduleDateTime=:pickupExchangeScheduleDateTime")
	PickupExchange getPickupExchangeDataperAgent(@Param("pickupExchangeAgentIdNo") String pickupExchangeAgentIdNo,
			@Param("pickupExchangeScheduleDateTime") LocalDate pickupExchangeScheduleDateTime);

}
