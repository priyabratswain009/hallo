package com.sunknowledge.dme.rcm.service.soentryandsearch;

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
    public Mono<DeliveryAddress> getDeliveryAddressOnSalesOrder(Long salesOrderId) throws Exception{
        Mono<DeliveryAddress> deliveryAddress = salesOrderMasterRepository.getSalesOrderDeliveryAddressOnSalesOrder(salesOrderId);
        return deliveryAddress;
    }
}
