package com.sunknowledge.dme.rcm.web.rest.pickupExchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.domain.PickupExchange;
import com.sunknowledge.dme.rcm.service.pickupExchange.PickUpExchangeService;

import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/pickupexchange")
public class PickUpExchangeResource {

	@Autowired
	private PickUpExchangeService pickUpExchangeService;

	@ApiOperation(value = "Create pickupexchange Data")
	@PostMapping("/pickupexchangeCreation")
	public Mono<PickupExchange> abnCreation(@RequestParam("soid") Long soid, @RequestParam("itemid") Long itemid,
			@RequestParam("pickupexchangetype") String pickupexchangetype,
			@RequestParam("replacementItemSerialNo") String replacementItemSerialNo,
			@RequestParam("replacementItemAssetNo") String replacementItemAssetNo,
			@RequestParam("pickupexchangescheduledatetime") String pickupexchangescheduledatetime,
			@RequestParam("createdbyid") Long createdbyid, @RequestParam("createdbyname") String createdbyname,
			@RequestParam("pickupexchangesupportingdocument1") String pickupexchangesupportingdocument1,
			@RequestParam("pickupexchangesupportingdocument2") String pickupexchangesupportingdocument2,
			@RequestParam("reason") String reason) throws Exception {
		return pickUpExchangeService.pickupExchangeCreation(soid, itemid, replacementItemSerialNo, replacementItemAssetNo, pickupexchangetype,
				pickupexchangescheduledatetime, createdbyid, createdbyname, pickupexchangesupportingdocument1,
				pickupexchangesupportingdocument2, reason);
	}

}
