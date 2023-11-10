package com.sunknowledge.dme.rcm.repository.inventory;

import com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails;
import com.sunknowledge.dme.rcm.repository.SoItemTransactionDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SoItemTransactionDetailsReposi extends SoItemTransactionDetailsRepository {
    @Query(value = "FROM SoItemTransactionDetails WHERE salesOrderNo = :salesOrderNo AND itemId = :itemId AND serialNo = :serialNumber")
    SoItemTransactionDetails getItemTransactionDetailsOnSalesOrderNItemNSerialNumber(@Param("salesOrderNo") String salesOrderNo, @Param("itemId") Long itemId, @Param("serialNumber") String serialNumber);

    @Query(value = "select * from item.f_get_transaction_no()", nativeQuery = true)
    String getTransactionNumber();
}
