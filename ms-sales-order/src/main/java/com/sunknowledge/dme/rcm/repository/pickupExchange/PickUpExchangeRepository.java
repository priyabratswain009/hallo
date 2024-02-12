package com.sunknowledge.dme.rcm.repository.pickupExchange;

import java.time.LocalDate;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.repository.PickupExchangeRepository;
import com.sunknowledge.dme.rcm.service.dto.pickupExchange.PickupExchangeDataForListDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PickUpExchangeRepository extends PickupExchangeRepository {

	@Query("CALL so.so_pickup_exchange(:soid,:itemid,:replacementserialnumber,:replacementassetnumber, :pickupexchangetype,:pickupexchangescheduledatetime,:createdbyid,:createdbyname,:pickupexchangesupportingdocument1,:pickupexchangesupportingdocument2,:reason)")
	Mono<PickupExchange> pickupExchangeCreation(@Param("soid") Long soid, @Param("itemid") Long itemid,
			@Param("replacementserialnumber") String replacementserialnumber,
			@Param("replacementassetnumber") String replacementassetnumber,
			@Param("pickupexchangetype") String pickupexchangetype,
			@Param("pickupexchangescheduledatetime") LocalDate pickupexchangescheduledatetime,
			@Param("createdbyid") Long createdbyid, @Param("createdbyname") String createdbyname,
			@Param("pickupexchangesupportingdocument1") String pickupexchangesupportingdocument1,
			@Param("pickupexchangesupportingdocument2") String pickupexchangesupportingdocument2,
			@Param("reason") String reason);

	@Query("select * from t_pickup_exchange tpm where tpm.status = :status")
	Flux<PickupExchange> getPickupExchangeByStatus(@Param("status") String status);

	@Query("select * from so.t_pickup_exchange where pickup_exchange_no = :pickupExchangeNo")
	Mono<PickupExchange> getPickupExchangeByPickupExchangeNo(@Param("pickupExchangeNo") String pickupExchangeNo);

	@Query("select * from so.getpickupExchangeDataList(:pickupExchangeNo,:pickupexchangetype,:sono,:patientidno,:patientfirstname,:patientlastname,:pickupexchangeagentidno,:pickupexchangeagentname,:pickupexchangestatus)")
	Flux<PickupExchangeDataForListDTO> getPickupExchangeDataForList(@Param("pickupExchangeNo") String pickupExchangeNo,
			@Param("pickupexchangetype") String pickupexchangetype, @Param("sono") String sono,
			@Param("patientidno") String patientidno, @Param("patientfirstname") String patientfirstname,
			@Param("patientlastname") String patientlastname,
			@Param("pickupexchangeagentidno") String pickupexchangeagentidno,
			@Param("pickupexchangeagentname") String pickupexchangeagentname,
			@Param("pickupexchangestatus") String pickupexchangestatus);

}
