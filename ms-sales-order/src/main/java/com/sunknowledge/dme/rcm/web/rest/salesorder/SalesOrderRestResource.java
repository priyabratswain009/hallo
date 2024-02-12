package com.sunknowledge.dme.rcm.web.rest.salesorder;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.dto.salesorder.DeliveryAddress;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderDetailsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author Bimal K Sahoo
 * @since 20/03/2023
 */
@RestController
@RequestMapping("/api/salesorder")
@Slf4j
public class SalesOrderRestResource {
    @Autowired
    private SalesOrderDetailsService salesOrderDetailsService;

    @ApiOperation(value = "Get Sales Order Details on Sales Order ID")
    @GetMapping(value = "/getSalesOrderDeliveryAddressOnSalesOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ServiceOutcome<DeliveryAddress>> getSalesOrderDeliveryAddressOnSalesOrder(@RequestParam("salesOrderId") Long salesOrderId) throws Exception {
        log.info("===================getSalesOrderDeliveryAddressOnSalesOrder======================"+salesOrderId);
        return salesOrderDetailsService.getDeliveryAddressOnSalesOrder(salesOrderId);
    }
}
