package com.sunknowledge.dme.rcm.repository.pickupExchange;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails;
import com.sunknowledge.dme.rcm.repository.SoItemTransactionDetailsRepository;

public interface SoItemTransactionDetailsRepo extends SoItemTransactionDetailsRepository {

	@Query(value = "From SoItemTransactionDetails where serialNo=:serialNumber and itemId=:itemId and itemNo=:itemNo and salesOrderNo=:sono")
	SoItemTransactionDetails getitemTransactionDetailsDataUpdate(@Param("serialNumber") String serialNumber,
			@Param("itemNo") String itemNo, @Param("itemId") Long itemId, @Param("sono") String sono);
	
	@Query(value = "select * from item.f_get_transaction_no()", nativeQuery = true)
	String getTransactionNumber();

}
