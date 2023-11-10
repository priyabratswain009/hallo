package com.sunknowledge.dme.rcm.web.rest.delivery;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.service.delivery.DeliverySalesOrderService;
import com.sunknowledge.dme.rcm.service.dto.delivery.CreateDeliveryTicketParams;
import com.sunknowledge.dme.rcm.service.dto.delivery.ItemInventoryStatusResponse;
import com.sunknowledge.dme.rcm.validation.ValidationUtility;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Bimal K Sahoo
 * @since 25/05/2023
 */
@RestController
@RequestMapping("/api/delivery")
@Slf4j
public class DeliverySalesOrderResource {
    @Autowired
    private DeliverySalesOrderService deliverySalesOrderService;

    @ApiOperation(value = "Create Delivery Ticket")
    @PostMapping(value="/createDeliveryTicket")
//    @CircuitBreaker(name = SALESORDER_DELIVERY_SERVICE, fallbackMethod = "salesOrderDeliveryServiceFallBack")
    public Mono<ServiceOutcome<ItemInventoryStatusResponse>> createDeliveryTicket(@RequestBody CreateDeliveryTicketParams createDeliveryTicketParams) {
        ServiceOutcome<ItemInventoryStatusResponse> validateOutcome = null;
        try {
            System.out.println("======================Result============================" + createDeliveryTicketParams.getSalesOrderId());
//            Mono<ServiceOutcome<Boolean>> status = deliverySalesOrderService.itemServiceHealthCheck();
//            status.subscribe(System.out::println);
            validateOutcome = ValidationUtility.validateCreateDeliveryTicketRequestParams(createDeliveryTicketParams);
            if (validateOutcome.getOutcome()){
                return deliverySalesOrderService.createDeliveryTicketNew(createDeliveryTicketParams);
            }
            else
                return Mono.just(new ServiceOutcome<>(new ItemInventoryStatusResponse(), false, "Failed to create the delivery ticket!!!"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just(new ServiceOutcome<>(new ItemInventoryStatusResponse(), false, "Failed to create the delivery ticket!!!"));
    }

    public Mono<String> salesOrderDeliveryServiceFallBack(Exception e){
        return Mono.just("This is a fallback method of SALESORDER_DELIVERY_SERVICE");
    }

    /*@ApiOperation(value = "Validate Delivery Initiation Subroutine Validation")
    @PostMapping(value="/validateDeliveryInitiation")
    public Mono<ServiceOutcome> validateDeliveryInitiation(@RequestParam("soId") Long soId){
        try {
            System.out.println("==========validateDeliveryInitiation============");

            *//*************Use this Non Reactive part from where it will be called(for sub-routine integration)************//*
            List<validateDeliveryInitiationSOItemDetailsResponseDTO> soItemDetailsList = deliverySalesOrderService.getSoItemDetailsList(soId).collectList().toFuture().get();

            SalesOrderInsuranceDetails soInsuranceDetails = deliverySalesOrderService.getSoInsuranceDetailsBySOId(soId).toFuture().get();
            log.info("=========soItemDetailsList========="+soItemDetailsList);
            if(soItemDetailsList.size() > 0 && soInsuranceDetails.getSalesOrderInsuranceDetailsId()!= null){
                return deliverySalesOrderService.validateDeliveryInitiation(soId, soItemDetailsList, soInsuranceDetails);
            }else{
                return Mono.just(new ServiceOutcome<>(null, false, "Invalid Input."));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
//{
//    "salesOrderId": 20,
//    "deliveryType": "Courier",
//    "setupMethod": "Technician",
//    "userId": 4,
//    "userName": "Debabrata"
//    }
