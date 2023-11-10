package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.service.dto.salesorder.DeliveryAddress;
import reactor.core.publisher.Mono;

public interface SalesOrderDetailsService {
    Mono<DeliveryAddress> getDeliveryAddressOnSalesOrder(Long salesOrderId) throws Exception;
}
