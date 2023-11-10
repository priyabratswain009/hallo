package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.dto.par.SearchPARInputParameters;
import com.sunknowledge.dme.rcm.dto.soItemDetails.ItemDefaultVendorResponseDTO;
import com.sunknowledge.dme.rcm.dto.soItemDetailsAndClicnicalAndInsurance.SoItemDetailsAndClinicalAndInsuranceResponseData;
import com.sunknowledge.dme.rcm.service.SalesOrderItemDetailsService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.po.RemoveDropshipParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.salesorder.SOItemInventoryTransactionDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderItemDetailsEntryParameterDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface SalesOrderItemDetailsServiceExtended extends SalesOrderItemDetailsService {

    Flux<SalesOrderItemDetails> findBySalesOrderId(Long SOID);

    Flux<SalesOrderItemDetails> getSOItemDetailsBySOItemDetailsUUID(UUID sOItemDetailsUUID);

    Mono<Long> getIDByUUID(UUID sOItemDetailsUUID);

    Mono<SalesOrderItemDetails> findById(Long id);

//    Mono<SalesOrderItemDetails> getCMNSubroutineDataForSalesOrderItemDetails(SalesOrderItemCmnSubroutineDTO salesOrderItemCmnSubroutineDTO);

    Mono<ServiceOutcome> abnCreationAndTagging(Long soid, String item_proc, String abn_reason, String abn_modifier_1, String abn_modifier_2, Long uid,
                                               String uname, Long soItemDetailsId) throws ExecutionException, InterruptedException;

    Mono<ServiceOutcome> abnTagging(Long soItemDetailsId, String abnNumber);

    Mono<ParMaster> getPARDataForSalesOrderItemDetails(SearchPARInputParameters searchPARInputParameters);

    Mono<ResponseDTO> saveSOItemDetails(SalesOrderItemDetailsDTO obj, SalesOrderItemDetailsEntryParameterDTO salesOrderItemDetailsEntryParameterDTO, SoItemDetailsAndClinicalAndInsuranceResponseData fetchMasterClinicalIns);

    public ServiceOutcome<List<DeliveryAbnData>> getabnData(Long soid, String item_proc) throws InterruptedException, ExecutionException;

    Mono<ServiceOutcome<SoItemDetailsAndClinicalAndInsuranceResponseData>> checkDataAvailabilityOfSOMasterAndClinicalAndInsurance(Long soId) throws ExecutionException, InterruptedException;

    Mono<ResponseDTO> saveSOItemDetailsWithDropship(SalesOrderItemDetailsDTO obj, SalesOrderItemDetailsEntryParameterDTO salesOrderItemDetailsEntryParameterDTO, SoItemDetailsAndClinicalAndInsuranceResponseData fetchMasterClinicalIns);

    Mono<ServiceOutcome> removeSalesOrderItemDetails(RemoveDropshipParameterDTO removeDropshipParameterDTO) throws ExecutionException, InterruptedException;

    Mono<ServiceOutcome<ItemDefaultVendorResponseDTO>> getItemDefaultVendorByItemId(Long itemId) throws ExecutionException, InterruptedException;

    Mono<String> soItemInventoryTransactionUpdate(SOItemInventoryTransactionDTO soItemInventoryTransactionDTO);

    Mono<ServiceOutcome> dLinkForCmnItems(Long cmnId, Long salesOrderId, String salesOrderDetailsItemId) throws ExecutionException, InterruptedException;

    Mono<ServiceOutcome> linkForCmnItems(Long cmnId, Long salesOrderId, String salesOrderDetailsItemId) throws ExecutionException, InterruptedException;

}
