package com.sunknowledge.dme.rcm.service.pickupExchange;

import com.sunknowledge.dme.rcm.domain.PickupExchange;

import reactor.core.publisher.Mono;

public interface PickUpExchangeService {

	public Mono<PickupExchange> pickupExchangeCreation(Long soid, Long itemid, String replacementItemSerialNo,
			String replacementItemAssetNo, String pickupexchangetype, String pickupexchangescheduledatetime,
			Long createdbyid, String createdbyname, String pickupexchangesupportingdocument1,
			String pickupexchangesupportingdocument2, String reason);

}
