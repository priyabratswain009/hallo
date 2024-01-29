package com.sunknowledge.dme.rcm.service.delivery;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.service.dto.delivery.CreateDeliveryTicketParams;
import com.sunknowledge.dme.rcm.service.dto.delivery.ItemInventoryStatusResponse;
import com.sunknowledge.dme.rcm.service.dto.delivery.validateDeliveryInitiationSOItemDetailsResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface DeliverySalesOrderService {
    Mono<ServiceOutcome<ItemInventoryStatusResponse>> createDeliveryTicket(CreateDeliveryTicketParams createDeliveryTicketParams) throws Exception;
    Mono<ServiceOutcome<ItemInventoryStatusResponse>> createDeliveryTicketNew(CreateDeliveryTicketParams createDeliveryTicketParams);
    Flux<validateDeliveryInitiationSOItemDetailsResponseDTO> getSoItemDetailsList(Long soId);
    Mono<SalesOrderInsuranceDetails> getSoInsuranceDetailsBySOId(Long soId);
    Mono<ServiceOutcome> validateDeliveryInitiation(Long soId, List<validateDeliveryInitiationSOItemDetailsResponseDTO> soItemDetailsList, SalesOrderInsuranceDetails soInsuranceDetails) throws ExecutionException, InterruptedException;
    Mono<ServiceOutcome> getDeliveryAddressDelivarableOrNot(Long soId) throws ExecutionException, InterruptedException;
}
