package com.sunknowledge.dme.rcm.repository.delivery;

import com.sunknowledge.dme.rcm.domain.DeliveryTicket;
import com.sunknowledge.dme.rcm.repository.DeliveryTicketRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DeliveryTicketRepo extends DeliveryTicketRepository {
    @Query(value = "CALL so.so_create_delivery_ticket_with_dropship(:soid, :deliverytype, :setupmethod, :uid, :uname)")
    Mono<Void> createDeliveryTicketOnRequestedParams(@Param("soid") Long soid, @Param("deliverytype") String deliverytype,
                                               @Param("setupmethod") String setupmethod, @Param("uid") Long uid, @Param("uname") String uname);

    @Query(value = "select * from t_delivery_ticket tdt where tdt.so_id = :soId and tdt.created_by_id = :uId and date(tdt.created_date) = date(:createdDate) and tdt.status in ('Active', 'active')")
    Flux<DeliveryTicket> getDeliveryTicketOnSalesOrderUserIdNCurrentDate(@Param("soId") Long soId, @Param("uId") Long uId, @Param("createdDate") String createdDate);
}
