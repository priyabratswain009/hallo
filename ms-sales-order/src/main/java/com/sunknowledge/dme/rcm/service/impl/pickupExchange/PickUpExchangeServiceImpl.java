package com.sunknowledge.dme.rcm.service.impl.pickupExchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.repository.pickupExchange.PickUpExchangeRepository;
import com.sunknowledge.dme.rcm.service.pickupExchange.PickUpExchangeService;

import reactor.core.publisher.Mono;

@Service
public class PickUpExchangeServiceImpl implements PickUpExchangeService {

	@Autowired
	private PickUpExchangeRepository pickUpExchangeRepository;

	@Override
	public Mono<PickupExchange> pickupExchangeCreation(Long soid, Long itemid, String replacementItemSerialNo,
			String replacementItemAssetNo, String pickupexchangetype, String pickupexchangescheduledatetime,
			Long createdbyid, String createdbyname, String pickupexchangesupportingdocument1,
			String pickupexchangesupportingdocument2, String reason) {
		// TODO Auto-generated method stub
		return pickUpExchangeRepository.pickupExchangeCreation(soid, itemid, pickupexchangetype,
				CommonUtilities.stringtodateConverter(pickupexchangescheduledatetime), createdbyid, createdbyname,
				pickupexchangesupportingdocument1, pickupexchangesupportingdocument2, reason);
	}

}
