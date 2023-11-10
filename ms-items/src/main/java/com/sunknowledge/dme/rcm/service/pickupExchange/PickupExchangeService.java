package com.sunknowledge.dme.rcm.service.pickupExchange;

import com.sunknowledge.dme.rcm.service.dto.SoItemTransactionDetailsDTO;

public interface PickupExchangeService {

	public void updateInventoryStatusQtyandserialNo(String itemNo, String pickupSerialNumber, String exchangeserialNumber,
			Long itemId, String itemPickupExchangeType, Long itemLocationId, Long qty);
	
	public boolean validatePickupSerialNumber(String pickupSerialNumber, String itemNo);
	
	public SoItemTransactionDetailsDTO itemTransactionDetailsDataUpdate(String pickupserialNumber, String exchserialNumber, String exchassetNumber, String itemNo, Long itemId, String sono, String pickupExchangeType);

}
