package com.sunknowledge.dme.rcm.repository.po;

import com.sunknowledge.dme.rcm.domain.PurchaseOrderItemsReceived;
import com.sunknowledge.dme.rcm.repository.PurchaseOrderItemsReceivedRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseOrderItemsReceivedRepositoryExtended extends PurchaseOrderItemsReceivedRepository {
    List<PurchaseOrderItemsReceived> findByItemId(Long itemId);

    List<PurchaseOrderItemsReceived> findByItemIdAndStatusIgnoreCase(Long itemId, String active);

    List<PurchaseOrderItemsReceived> findByPoIdAndStatus(Long poId, String status);

    List<PurchaseOrderItemsReceived> findByPoNumberAndStatus(String poNumber, String status);

    List<PurchaseOrderItemsReceived> findByPoItemReceivedIdAndStatus(Long itemReceivedID, String status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL item.poreceipt_dropship(:pono,:itemid,:receivedqty,:whetherserialised,:serialnos," +
        ":lotnos,:mfgdates,:receiveddate,:note,:uid,:uname)", nativeQuery = true)
    Object saveReceiptDropshipPurchaseOrder(@Param("pono") String pono,
                                            @Param("itemid") String itemid,
                                            @Param("receivedqty") String receivedqty,
                                            @Param("whetherserialised") String whetherserialised,
                                            @Param("serialnos") String serialnos,
                                            @Param("lotnos") String lotnos,
                                            @Param("mfgdates") String mfgdates,
                                            @Param("receiveddate") LocalDate receiveddate,
                                            @Param("note") String note,
                                            @Param("uid") Long uid,
                                            @Param("uname") String uname);
}
