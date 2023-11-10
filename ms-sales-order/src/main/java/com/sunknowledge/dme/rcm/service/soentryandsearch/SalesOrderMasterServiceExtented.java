package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.service.SalesOrderMasterService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.ItemInventoryStatusExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.validateDeliveryInitiationSOItemDetailsResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderEntryParameterDTO;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface SalesOrderMasterServiceExtented extends SalesOrderMasterService {
    Flux<SalesOrderMaster> getSOByUUID(UUID salesOrderUUID);

    Mono<Long> getIDByUUID(UUID salesOrderUUID);

    Mono<ResponseDTO> saveSOMasterDetails(SalesOrderMasterDTO obj, SalesOrderEntryParameterDTO salesOrderEntryParameterDTO);

    Mono<ServiceOutcome> patientDateOfDeathIncorporation(Long patientId, LocalDate patientDod) throws ExecutionException, InterruptedException;

    Mono<ServiceOutcome> getSOBySoId(Long soId);

    List<SalesOrderMaster> updateHoldStatus(List<SalesOrderMaster> salesOrderMasters);

    Flux<SalesOrderMaster> findAllById(List<Long> soIds);

    Mono<ServiceOutcome> getSODetailsBysoId(Long soId) throws ExecutionException, InterruptedException;
    Flux<String> getCMNStatusByCMNIds(List<Long> cmnIdList);
    Flux<String> getParStatusByParId(List<Long> parIdList);
    Mono<ServiceOutcome> makeSOCommited(Long soId, List<validateDeliveryInitiationSOItemDetailsResponseDTO> soItemDetailsList, List<String> cmnStatus, List<String> parStatus, List<ItemInventoryStatusExtendedDTO> itemInventoryStatusList, ServiceOutcome deliverableAddr) throws ExecutionException, InterruptedException, IOException, ParseException;
    Mono<ItemInventoryStatusExtendedDTO> getItemInventoryStatusData(Long itemId, Long itemLocationId);

    Mono<ServiceOutcome> addSalesOrderNotes(Long soId, String soNote);

    Mono<ServiceOutcome<JSONArray>> getSalesOrderNotes(Long soId);
    Mono<ServiceOutcome> updateSalesOrderedStatusAsDelivered(Long salesOrderId,String soNo, Flux<SalesOrderItemDetails> salesOrderItemDetailsFlux);
}
