package com.sunknowledge.dme.rcm.web.rest.pickupExchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.service.dto.SoItemTransactionDetailsDTO;
import com.sunknowledge.dme.rcm.service.pickupExchange.PickupExchangeService;

@RestController
@RequestMapping("/api")
public class PickupExchangeResource {

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	@Autowired
	private PickupExchangeService pickupExchangeServiceImpl;

	@PostMapping("/updateInventoryStatusQtyandserialNo")
	public void updateInventoryStatusQtyandserialNo(@RequestParam("itemNo") String itemNo,
			@RequestParam("PickupserialNumber") String pickupserialNumber,
			@RequestParam(value = "ExchangeserialNumber", required = false) String exchangeserialNumber,
			@RequestParam("itemId") Long itemId, @RequestParam("itemPickupExchangeType") String itemPickupExchangeType,
			@RequestParam("itemLocationId") Long itemLocationId, @RequestParam("qty") Long qty) {
		pickupExchangeServiceImpl.updateInventoryStatusQtyandserialNo(itemNo, pickupserialNumber, exchangeserialNumber,
				itemId, itemPickupExchangeType, itemLocationId, qty);
	}

	@PostMapping("/validatePickupSerialNumber")
	public boolean validateSerialNo(@RequestParam("PickupserialNumber") String pickupserialNumber, @RequestParam("itemNo") String itemNo) {
		return pickupExchangeServiceImpl.validatePickupSerialNumber(pickupserialNumber, itemNo);
	}

	@PostMapping("/itemTransactionDetailsDataUpdate")
	public SoItemTransactionDetailsDTO itemTransactionDetailsDataUpdate(@RequestParam("pickupserialNumber") String pickupserialNumber, @RequestParam("exchassetNumber") String exchassetNumber,@RequestParam("exchserialNumber") String exchserialNumber,
			@RequestParam("itemNo") String itemNo, @RequestParam("itemId") Long itemId,
			@RequestParam("sono") String sono, @RequestParam("pickupExchangeType") String pickupExchangeType) {
		return pickupExchangeServiceImpl.itemTransactionDetailsDataUpdate(pickupserialNumber, exchserialNumber, exchassetNumber, itemNo, itemId, sono, pickupExchangeType);
	}

}
