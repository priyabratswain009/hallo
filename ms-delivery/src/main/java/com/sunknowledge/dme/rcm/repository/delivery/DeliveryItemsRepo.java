package com.sunknowledge.dme.rcm.repository.delivery;

import com.sunknowledge.dme.rcm.domain.DeliveryItems;
import com.sunknowledge.dme.rcm.repository.DeliveryItemsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryItemsRepo extends DeliveryItemsRepository {
    @Query("FROM DeliveryItems WHERE deliveryTicketId = :deliveryTicketId AND status in ('Active', 'active')")
    List<DeliveryItems> getDeliveryItemsListOnDeliveryTicket(@Param("deliveryTicketId") Long deliveryTicketId);

    @Query("FROM DeliveryItems WHERE deliveryTicketId = :deliveryTicketId AND hcpcsCode = :procCode")
    DeliveryItems getDeliveryItemOnDeliveryTicketAndHcpcsCode(@Param("deliveryTicketId") Long deliveryTicketId, @Param("procCode") String procCode);
}
