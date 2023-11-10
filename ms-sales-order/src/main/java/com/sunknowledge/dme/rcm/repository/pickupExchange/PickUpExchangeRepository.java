package com.sunknowledge.dme.rcm.repository.pickupExchange;

import java.time.LocalDate;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.repository.PickupExchangeRepository;

import reactor.core.publisher.Mono;

public interface PickUpExchangeRepository extends PickupExchangeRepository {

	@Query("CALL so.so_pickup_exchange(:soid,:itemid,:pickupexchangetype,:pickupexchangescheduledatetime,:createdbyid,:createdbyname,:pickupexchangesupportingdocument1,:pickupexchangesupportingdocument2,:reason)")
	Mono<PickupExchange> pickupExchangeCreation(@Param("soid") Long soid, @Param("itemid") Long itemid,
			@Param("pickupexchangetype") String pickupexchangetype,
			@Param("pickupexchangescheduledatetime") LocalDate pickupexchangescheduledatetime,
			@Param("createdbyid") Long createdbyid, @Param("createdbyname") String createdbyname,
			@Param("pickupexchangesupportingdocument1") String pickupexchangesupportingdocument1,
			@Param("pickupexchangesupportingdocument2") String pickupexchangesupportingdocument2,
			@Param("reason") String reason);

}
