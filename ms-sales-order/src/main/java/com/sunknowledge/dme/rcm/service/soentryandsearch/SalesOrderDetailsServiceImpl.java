package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.repository.pricetabledata.SalesOrderMasterRepo;
import com.sunknowledge.dme.rcm.service.dto.salesorder.DeliveryAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class SalesOrderDetailsServiceImpl implements SalesOrderDetailsService{
    @Autowired
    private SalesOrderMasterRepo salesOrderMasterRepository;

    @Override
    public Mono<ServiceOutcome<DeliveryAddress>> getDeliveryAddressOnSalesOrder(Long salesOrderId) throws Exception{
        return salesOrderMasterRepository.getSalesOrderDeliveryAddressOnSalesOrder(salesOrderId)
            .map(deliveryAddress -> new ServiceOutcome<>(deliveryAddress, true, ""))
            .onErrorResume(error -> Mono.just(new ServiceOutcome<>(null, false, "Failed to retrieve delivery address: " + error.getMessage())));
    }
}
