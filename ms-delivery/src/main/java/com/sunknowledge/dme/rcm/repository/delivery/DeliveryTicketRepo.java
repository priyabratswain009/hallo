package com.sunknowledge.dme.rcm.repository.delivery;

import com.sunknowledge.dme.rcm.domain.DeliveryTicket;
import com.sunknowledge.dme.rcm.repository.DeliveryTicketRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryTicketRepo extends DeliveryTicketRepository {
    @Query("FROM DeliveryTicket WHERE soId = :salesOrderId AND status in('Active', 'active')")
    List<DeliveryTicket> getDeliveryTicketsOnSalesOrder(@Param("salesOrderId") Long salesOrderId);
}
