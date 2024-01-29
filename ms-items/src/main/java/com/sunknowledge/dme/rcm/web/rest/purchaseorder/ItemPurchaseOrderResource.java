package com.sunknowledge.dme.rcm.web.rest.purchaseorder;

import com.sunknowledge.dme.rcm.application.applicationstatus.MicroserviceNames;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.properties.AsyncConfigurationSetup;
import com.sunknowledge.dme.rcm.mshealthcheck.service.MicroserviceHealthCheck;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderDTO;
import com.sunknowledge.dme.rcm.service.dto.salesorder.DeliveryAddress;
import com.sunknowledge.dme.rcm.service.purchaseorder.ItemPurchaseOrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Bimal K Sahoo
 * @since 17/03/2023
 */
@RestController
@RequestMapping("/api/purchaseorder")
@Slf4j
public class ItemPurchaseOrderResource {
    @Autowired
    private ItemPurchaseOrderService itemPurchaseOrderService;
    @Autowired
    private MicroserviceHealthCheck microserviceHealthCheck;

    @ApiOperation(value = "Generate Purchase Order")
    @GetMapping(value = "/generatePurchaseOrder")
    @Async(AsyncConfigurationSetup.TASK_EXECUTOR_CONTROLLER)
    public CompletableFuture<ServiceOutcome<DeliveryAddress>> generatePurchaseOrder(@RequestParam("poNumber") String poNumber) {
        System.out.println("=======================generatePurchaseOrder=========================");
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info("================HEALTH=========================");
                return microserviceHealthCheck.microserviceHealthCheckNonReactive(MicroserviceNames.salesorder.name());
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).thenComposeAsync(healthOutcome -> {
            if (healthOutcome){
                return itemPurchaseOrderService.generatePurchaseOrderOnPoNumber(poNumber);
            }
            else{
                return CompletableFuture.completedFuture(new ServiceOutcome<>(null, false, "Please try again after some time due to service being unavailable!!!"));
            }
        });
    }

    @ApiOperation(value = "Get Purchase Order Details On Purchase Order Number")
    @GetMapping(value = "getPurchaseOrderDetailsOnPONumber")
    @Async(AsyncConfigurationSetup.TASK_EXECUTOR_CONTROLLER)
    public CompletableFuture<ServiceOutcome<PurchaseOrderDTO>> getPurchaseOrderDetailsOnPONumber(@RequestParam("poNumber") String poNumber){
        System.out.println("=======================getPurchaseOrderDetailsOnPONumber=========================");
        return itemPurchaseOrderService.getPurchaseOrderDetailsOnPONumber(poNumber);
    }
}
