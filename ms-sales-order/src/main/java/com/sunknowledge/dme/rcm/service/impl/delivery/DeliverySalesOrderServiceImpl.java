package com.sunknowledge.dme.rcm.service.impl.delivery;

import com.dropbox.core.InvalidAccessTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.AccessTokenUtilities;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.*;
import com.sunknowledge.dme.rcm.repository.SalesOrderInsuranceDetailsRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.cmn.CmnRepo;
import com.sunknowledge.dme.rcm.repository.delivery.DeliveryItemsRepo;
import com.sunknowledge.dme.rcm.repository.delivery.DeliveryTicketRepo;
import com.sunknowledge.dme.rcm.repository.pricetabledata.SalesOrderMasterRepo;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.delivery.DeliverySalesOrderService;
import com.sunknowledge.dme.rcm.service.dto.delivery.*;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderInsuranceDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterServiceExtented;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class DeliverySalesOrderServiceImpl implements DeliverySalesOrderService {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private DeliveryTicketRepo deliveryTicketRepository;
    @Autowired
    private SalesOrderMasterRepo salesOrderMasterRepository;
    @Autowired
    private CmnRepo cmnRepository;
    @Autowired
    private DeliveryItemsRepo deliveryItemsRepository;
    @Autowired
    SalesOrderInsuranceDetailsRepositoryExtended salesOrderInsuranceDetailsRepositoryExtended;
    @Autowired
    SalesOrderMasterServiceExtented salesOrderMasterServiceExtented;
    @Autowired
    SalesOrderInsuranceDetailsServiceExtended salesOrderInsuranceDetailsServiceExtended;

    public Mono<ServiceOutcome<ItemInventoryStatusResponse>> createDeliveryTicket(CreateDeliveryTicketParams createDeliveryTicketParams) throws Exception {
        Cmn cmn = cmnRepository.getCmnDetailsOnSalesOrder(createDeliveryTicketParams.getSalesOrderId()).toFuture().get();
        ItemInventoryStatusInputRequest itemInventoryStatusInputRequest = new ItemInventoryStatusInputRequest();
        Boolean outcome = false;
        String message = "";
        Mono<ServiceOutcome<ItemInventoryStatusResponse>> externalServiceOutcome = null;
        List<ItemInventoryStatusInputParams> itemInventoryStatusInputParamsList = new ArrayList<>();
        if(cmn != null){
            log.info("=================CMN======================="+cmn.getCmnNumber());
            if(cmn.getCmnLoggedBy() != null && cmn.getCmnLoggedDate() != null) {
                SalesOrderMaster salesOrderMaster = salesOrderMasterRepository.findById(createDeliveryTicketParams.getSalesOrderId()).toFuture().get();
                if (salesOrderMaster != null) {
                    deliveryTicketRepository.createDeliveryTicketOnRequestedParams(createDeliveryTicketParams.getSalesOrderId(),
                        createDeliveryTicketParams.getDeliveryType(), createDeliveryTicketParams.getSetupMethod(),
                        createDeliveryTicketParams.getUserId(), createDeliveryTicketParams.getUserName()).toFuture().get();
                    List<DeliveryTicket> deliveryTicketList = deliveryTicketRepository.getDeliveryTicketOnSalesOrderUserIdNCurrentDate(createDeliveryTicketParams.getSalesOrderId(), createDeliveryTicketParams.getUserId(), dateFormat.format(new Date())).collectList().toFuture().get();
                    if (deliveryTicketList != null && deliveryTicketList.size() > 0) {
                        deliveryTicketList.stream().forEach(deliveryTicket -> {
                            log.info("==========================>"+deliveryTicketList.get(0).getDeliveryTicketNo());
                            log.info("==========================>"+deliveryTicketList.get(1).getDeliveryTicketNo());
                            List<DeliveryItems> deliveryItemsList;
                            ItemInventoryStatusInputParams itemInventoryStatusInputParams = new ItemInventoryStatusInputParams();
                            itemInventoryStatusInputParams.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId());
                            itemInventoryStatusInputParams.setDeliveryTicketNo(deliveryTicket.getDeliveryTicketNo());
                            itemInventoryStatusInputParams.setItemLocationId(deliveryTicket.getInventoryLocationId());
                            itemInventoryStatusInputParams.setSoId(deliveryTicket.getSoId());
                            itemInventoryStatusInputParams.setSoNumber(deliveryTicket.getSoNo());
                            itemInventoryStatusInputParams.setDeliveryType(deliveryTicket.getDeliveryType());
                            itemInventoryStatusInputParams.setServiceType("SALESORDER");
                            itemInventoryStatusInputParams.setDeliveryTicketUuid(deliveryTicket.getDeliveryTicketUuid().toString());
                            try {
                                deliveryItemsList = deliveryItemsRepository.getDeliveryItemsListOnDeliveryTicket(deliveryTicket.getDeliveryTicketId()).collectList().toFuture().get();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            } catch (ExecutionException e) {
                                throw new RuntimeException(e);
                            }
                            List<DeliveryItemData> deliveryItemDataList = new ArrayList<>();
                            if(deliveryItemsList != null && deliveryItemsList.size() > 0){
                                deliveryItemsList.stream().forEach(item -> {
                                    DeliveryItemData deliveryItemData = new DeliveryItemData();
                                    deliveryItemData.setDeliveryItemId(item.getDeliveryItemId());
                                    deliveryItemData.setItemId(item.getItemId());
                                    deliveryItemData.setItemNumber(item.getItemNo());
                                    deliveryItemData.setItemName(item.getItemName());
                                    deliveryItemData.setHcpcsCode(item.getHcpcsCode());
                                    deliveryItemData.setItemQuantity(item.getItemQuantity());
                                    deliveryItemData.setItemSerialNumber(item.getItemSerial());
                                    deliveryItemData.setItemSaleType(item.getSoSaleType());
                                    if(item.getIsDropship() != null)
                                        deliveryItemData.setIsDropship(item.getIsDropship());
                                    else
                                        deliveryItemData.setIsDropship("");
                                    if(item.getPoNumber() != null)
                                        deliveryItemData.setPoNumber(item.getPoNumber());
                                    else
                                        deliveryItemData.setPoNumber("");
                                    deliveryItemData.setDeliveryItemsUuid(item.getDeliveryItemsUuid().toString());
                                    deliveryItemDataList.add(deliveryItemData);
                                });
                                itemInventoryStatusInputParams.setDeliveryItemData(deliveryItemDataList);
                                itemInventoryStatusInputParamsList.add(itemInventoryStatusInputParams);
                            }
                        });
                        itemInventoryStatusInputRequest.setItemInventoryStatusInputParamsList(itemInventoryStatusInputParamsList);
                        /*if(itemInventoryStatusInputRequest != null){
                            log.info("================>ItemInventoryStatusResponse===================="+itemInventoryStatusInputRequest.getItemInventoryStatusInputParamsList().get(0).getDeliveryTicketNo());
                            ObjectMapper mapper = new ObjectMapper();
                            String formJsonData = null;
                            try {
                                formJsonData = mapper.writeValueAsString(itemInventoryStatusInputRequest);
                                System.out.println("====JSON FORMATTED DATA=====>"+formJsonData);
                                String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
                                log.info("============><============="+token);
                                MultiValueMap<String, ItemInventoryStatusInputRequest> formData = new LinkedMultiValueMap<>();
                                formData.add("itemInventoryStatusInputRequest", itemInventoryStatusInputRequest);

                                String url = "http://localhost:8080/services/items/api/inventory/updateItemInventoryStatusByItemAndLocation";
                                externalServiceOutcome = webClientBuilder.build().post()
                                    .uri(url)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer "+token)
                                    .body(Mono.just(itemInventoryStatusInputRequest), ItemInventoryStatusInputRequest.class)
                                    .retrieve()
                                    .bodyToMono(new ParameterizedTypeReference<ServiceOutcome<ItemInventoryStatusResponse>>() {});
                                externalServiceOutcome.subscribe().dispose();
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                        }*/
                    }//First IF
                } else {
                    outcome = false;
                    message = "Failed to create delivery ticket due to requested sales order does not exists!";
                }
            }
            else{
                outcome = false;
                message = "Delivery ticket can not be created due to cmn is not logged!!";
            }
        }
        else{
            outcome = false;
            message = "CMN is not exists for this sales order!!";
        }
        return externalServiceOutcome;
    }

    @Override
    public Mono<ServiceOutcome<ItemInventoryStatusResponse>> createDeliveryTicketNew(CreateDeliveryTicketParams createDeliveryTicketParams) {
        log.info("=====================Service================================"+createDeliveryTicketParams.getSalesOrderId());
        return cmnRepository.getCmnDetailsOnSalesOrder(createDeliveryTicketParams.getSalesOrderId())
            .flatMap(cmn -> {
                ItemInventoryStatusInputRequest itemInventoryStatusInputRequest = new ItemInventoryStatusInputRequest();
                ItemInventoryStatusResponse itemInventoryStatusResponse = new ItemInventoryStatusResponse();
                return salesOrderMasterRepository.findById(createDeliveryTicketParams.getSalesOrderId())
                    .flatMap(salesOrderMaster ->{
                        log.info("===========salesorder==============Not null================");
                        List<ItemInventoryStatusInputParams> itemInventoryStatusInputParamsList = new ArrayList<>();
                        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(LocalDateTime.now());
                        return deliveryTicketRepository.callToCreateDeliveryTicket(
                                createDeliveryTicketParams.getSalesOrderId(),
                                createDeliveryTicketParams.getDeliveryType(), createDeliveryTicketParams.getSetupMethod(),
                                createDeliveryTicketParams.getUserId(), createDeliveryTicketParams.getUserName(), date)
                            .log("=========Date:"+date)
                            .log("=========Ticket ID:"+createDeliveryTicketParams.getSalesOrderId())
                            .flatMapSequential(deliveryTicket -> {
                                log.info("==================>" + deliveryTicket.getDeliveryticketno());
                                log.info("==================>" + deliveryTicket.getDeliveryticketid());
                                log.info("========================================================================");
                                ItemInventoryStatusInputParams itemInventoryStatusInputParams = new ItemInventoryStatusInputParams();
                                itemInventoryStatusInputParams.setDeliveryTicketId(deliveryTicket.getDeliveryticketid());
                                itemInventoryStatusInputParams.setDeliveryTicketNo(deliveryTicket.getDeliveryticketno());
                                itemInventoryStatusInputParams.setItemLocationId(deliveryTicket.getInventorylocationid());
                                itemInventoryStatusInputParams.setSoId(deliveryTicket.getSalesorderid());
                                itemInventoryStatusInputParams.setSoNumber(deliveryTicket.getSalesorderno());
                                itemInventoryStatusInputParams.setDeliveryType(deliveryTicket.getDeliverytypee());
                                itemInventoryStatusInputParams.setServiceType("SALESORDER");
                                itemInventoryStatusInputParams.setDeliveryTicketUuid(deliveryTicket.getDeliveryticketuuid().toString());

                                List<DeliveryItemData> deliveryItemDataList = new ArrayList<>();
                                return deliveryItemsRepository.getDeliveryItemsListOnDeliveryTicket(deliveryTicket.getDeliveryticketid())
                                    .flatMapIterable(deliveryItem -> {
                                        log.info("===================XXXXXXXXXXXXXXXXXXXXXXXX=================>");
                                        log.info("==================Item No==============================" + deliveryItem.getItemNo());
                                        log.info("============>Add Items================");
                                        DeliveryItemData deliveryItemData = new DeliveryItemData();
                                        deliveryItemData.setDeliveryItemId(deliveryItem.getDeliveryItemId());
                                        deliveryItemData.setItemId(deliveryItem.getItemId());
                                        deliveryItemData.setItemNumber(deliveryItem.getItemNo());
                                        deliveryItemData.setItemName(deliveryItem.getItemName());
                                        deliveryItemData.setHcpcsCode(deliveryItem.getHcpcsCode());
                                        deliveryItemData.setItemQuantity(deliveryItem.getItemQuantity());
                                        deliveryItemData.setItemSerialNumber(deliveryItem.getItemSerial());
                                        deliveryItemData.setItemSaleType(deliveryItem.getSoSaleType());
                                        deliveryItemData.setIsDropship(deliveryItem.getIsDropship());
                                        deliveryItemData.setPoNumber(deliveryItem.getPoNumber());
                                        deliveryItemData.setDeliveryItemsUuid(deliveryItem.getDeliveryItemsUuid().toString());
                                        deliveryItemDataList.add(deliveryItemData);
                                        log.info("=====================YYYYYYYYYYYYYYY==========================");
                                        return deliveryItemDataList;
                                    }).collectList()
                                    .map(data -> {
                                        itemInventoryStatusInputParams.setDeliveryItemData(deliveryItemDataList);
                                        itemInventoryStatusInputParamsList.add(itemInventoryStatusInputParams);
                                        itemInventoryStatusInputRequest.setItemInventoryStatusInputParamsList(itemInventoryStatusInputParamsList);
                                        itemInventoryStatusResponse.setItemInventoryStatusInputParamsList(itemInventoryStatusInputParamsList);
                                        return itemInventoryStatusResponse;
                                    });
                            }).collectList()
                            .flatMap(z -> {
                                ObjectMapper mapper = new ObjectMapper();
                                String formJsonData;
                                Mono<ServiceOutcome<ItemInventoryStatusResponse>> externalServiceOutcome = null;
                                try {
                                    formJsonData = mapper.writeValueAsString(itemInventoryStatusResponse);
                                    System.out.println("====JSON FORMATTED DATA=====>" + formJsonData);
                                    String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
                                    log.info("============><=============" + token);
                                    MultiValueMap<String, ItemInventoryStatusInputRequest> formData = new LinkedMultiValueMap<>();
                                    formData.add("itemInventoryStatusInputRequest", itemInventoryStatusInputRequest);

                                    String url = "http://localhost:8080/services/items/api/inventory/updateItemInventoryStatusByItemAndLocation";
                                    externalServiceOutcome = webClientBuilder.build().post()
                                        .uri(url)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .header("Authorization", "Bearer "+token)
                                        .body(Mono.just(itemInventoryStatusInputRequest), ItemInventoryStatusInputRequest.class)
                                        .retrieve()
                                        .bodyToMono(new ParameterizedTypeReference<>() {});
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                                log.info("============Response=============>>>>>>>>>"+z);
                                return externalServiceOutcome;
                            });
                        });
            });
    }

    private Mono<ItemInventoryStatusInputParams> createItemInventory(DeliveryTicket deliveryTicket) {
        Flux<DeliveryItems> deliveryItemsList = deliveryItemsRepository.getDeliveryItemsListOnDeliveryTicket(deliveryTicket.getDeliveryTicketId());
        ItemInventoryStatusInputParams itemInventoryStatusInputParams = new ItemInventoryStatusInputParams();
        itemInventoryStatusInputParams.setDeliveryTicketId(deliveryTicket.getDeliveryTicketId());
        itemInventoryStatusInputParams.setDeliveryTicketNo(deliveryTicket.getDeliveryTicketNo());
        itemInventoryStatusInputParams.setItemLocationId(deliveryTicket.getInventoryLocationId());
        itemInventoryStatusInputParams.setSoId(deliveryTicket.getSoId());
        List<DeliveryItemData> deliveryItemDataList = new ArrayList<>();
        deliveryItemsList.flatMap(deliveryItem -> {
            DeliveryItemData deliveryItemData = new DeliveryItemData();
            deliveryItemData.setDeliveryItemId(deliveryItem.getDeliveryItemId());
            deliveryItemData.setItemId(deliveryItem.getItemId());
            deliveryItemData.setItemNumber(deliveryItem.getItemNo());
            deliveryItemData.setItemName(deliveryItem.getItemName());
            deliveryItemData.setHcpcsCode(deliveryItem.getHcpcsCode());
            deliveryItemData.setItemQuantity(deliveryItem.getItemQuantity());
            deliveryItemData.setItemSerialNumber(deliveryItem.getItemSerial());
            deliveryItemData.setItemSaleType(deliveryItem.getSoSaleType());
            deliveryItemData.setIsDropship(deliveryItem.getIsDropship());
            deliveryItemData.setPoNumber(deliveryItem.getPoNumber());
            deliveryItemData.setDeliveryItemsUuid(deliveryItem.getDeliveryItemsUuid().toString());
            deliveryItemDataList.add(deliveryItemData);
            log.info("=====================YYYYYYYYYYYYYYY==========================");
            return Mono.just(deliveryItem);
        });
        itemInventoryStatusInputParams.setDeliveryItemData(deliveryItemDataList);
        ItemInventoryStatusInputRequest itemInventoryStatusInputRequest = new ItemInventoryStatusInputRequest();
        return Mono.just(itemInventoryStatusInputParams);
    }

    private Flux<DeliveryItemData> createDeliveryItemDetails(DeliveryItems deliveryItem) {
        DeliveryItemData deliveryItemData = new DeliveryItemData();
        deliveryItemData.setDeliveryItemId(deliveryItem.getDeliveryItemId());
        deliveryItemData.setItemId(deliveryItem.getItemId());
        deliveryItemData.setItemNumber(deliveryItem.getItemNo());
        deliveryItemData.setItemName(deliveryItem.getItemName());
        deliveryItemData.setHcpcsCode(deliveryItem.getHcpcsCode());
        deliveryItemData.setItemQuantity(deliveryItem.getItemQuantity());
        deliveryItemData.setItemSerialNumber(deliveryItem.getItemSerial());
        deliveryItemData.setItemSaleType(deliveryItem.getSoSaleType());
        deliveryItemData.setIsDropship(deliveryItem.getIsDropship());
        deliveryItemData.setPoNumber(deliveryItem.getPoNumber());
        deliveryItemData.setDeliveryItemsUuid(deliveryItem.getDeliveryItemsUuid().toString());
        return Flux.just(deliveryItemData);
    }

    public Flux<validateDeliveryInitiationSOItemDetailsResponseDTO> getSoItemDetailsList(Long soId){
        return deliveryItemsRepository.getSoMasterAndSoItemDetails(soId);
    }

    public Mono<SalesOrderInsuranceDetails> getSoInsuranceDetailsBySOId(Long soId){
        return salesOrderInsuranceDetailsRepositoryExtended.findSOInsuranceOnSOId(soId);
    }

    public Mono<ServiceOutcome> validateDeliveryInitiation(Long soId, List<validateDeliveryInitiationSOItemDetailsResponseDTO> soItemDetailsList, SalesOrderInsuranceDetails soInsuranceDetails) throws ExecutionException, InterruptedException {
        log.info("======validateDeliveryInitiation:: soId======"+soId);
        Mono<ServiceOutcome> serviceOutcome = null;
        //List<validateDeliveryInitiationSOItemDetailsResponseDTO> soMasterAndSoItemDetailsList = deliveryItemsRepository.getSoMasterAndSoItemDetails(soId).collectList().toFuture().get();

        log.info("=======Step-0:: soItemDetailsList======="+soItemDetailsList);
        boolean soItemDetailsValidationFlag = false;
        for(validateDeliveryInitiationSOItemDetailsResponseDTO eachObj: soItemDetailsList) {

            log.info("=======Step-1=======");
            if ((eachObj.getCmnId() == null || eachObj.getCmnId() == 0) && (eachObj.getCmnKey() == null || eachObj.getCmnKey().trim().equals(""))) {
                return Mono.just(new ServiceOutcome<>(null, false, "CMN Id and CMN key should not be null/empty."));
            }

            log.info("=======Step-3=======");
            if(eachObj.getItemLocationId() == null){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Item Location Id should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsItemId() == null){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Item Id should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsItemName() == null || eachObj.getSalesOrderDetailsItemName().trim().equals("")){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Item Name should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsStockingUom() == null || eachObj.getSalesOrderDetailsStockingUom().trim().equals("")){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "sales Order Details Stocking Uom should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsItemDescription() == null || eachObj.getSalesOrderDetailsItemDescription().trim().equals("")){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Item Description should not be null/empty."));
            }else if(eachObj.getSalesOrderItemNumber() == null || eachObj.getSalesOrderItemNumber().trim().equals("")){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Item Number should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsSaleType() == null || eachObj.getSalesOrderDetailsSaleType().trim().equals("")){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Sale Type should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsQty() == null){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Qty should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsBqty() == null){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Bqty should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsLineQty() == null){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Line Qty should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsProcCode() == null || eachObj.getSalesOrderDetailsProcCode().trim().equals("")){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details ProcCode should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsModifier1() == null || eachObj.getSalesOrderDetailsModifier1().trim().equals("")){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Modifier 1 should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsPriceOption() == null || eachObj.getSalesOrderDetailsPriceOption().trim().equals("")){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Price Option should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsChargeAmt() == null){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Charge Amt should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsAllowedAmt() == null){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Allowed Amt should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsTaxable() == null || eachObj.getSalesOrderDetailsTaxable().trim().equals("")){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Taxable should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsOriginalDos() == null || eachObj.getSalesOrderDetailsOriginalDos().trim().equals("")){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Original Dos should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsDosTo() == null || eachObj.getSalesOrderDetailsDosTo().trim().equals("")){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Dos To should not be null/empty."));
            }else if(eachObj.getSalesOrderDetailsTaxRate() == null){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Tax Rate should not be null/empty."));
            }
            else{
                //Further Steps Execution
                log.info("=============Step-4=============");
                soItemDetailsValidationFlag = true;  // true means all validation till step-3 are checked and validated
            }
        }

        if(soItemDetailsValidationFlag){
            serviceOutcome = validateSoInsuranceAndUpdateInsVerificationStatus(soId, soInsuranceDetails); //Validate SalesOrderInsuranceDetails before Update
            log.info("===========response=========="+serviceOutcome.toFuture().get());
        }

        return serviceOutcome;
    }

    public Mono<ServiceOutcome> validateSoInsuranceAndUpdateInsVerificationStatus(Long soId, SalesOrderInsuranceDetails soInsuranceDetailsDataObj) throws ExecutionException, InterruptedException {
        //ValidateDeliveryInitiationSOInsuranceDetailsResponseDTO soInsuranceDetailsObj = null;
        //soInsuranceDetailsObj
        if(soInsuranceDetailsDataObj.getSalesOrderInsuranceDetailsId()!=null){
            Long primaryInsurerId = soInsuranceDetailsDataObj.getPrimaryInsurerId()!= null ? soInsuranceDetailsDataObj.getPrimaryInsurerId() : 0;
            Long secondaryInsurerId = soInsuranceDetailsDataObj.getSecondaryInsurerId()!= null ? soInsuranceDetailsDataObj.getSecondaryInsurerId() : 0;
            Long tertiaryInsurerId = soInsuranceDetailsDataObj.getTertiaryInsurerId()!= null ? soInsuranceDetailsDataObj.getTertiaryInsurerId() : 0;

            String primaryInsurerVerificationStatus = null;
            String secondaryInsurerVerificationStatus = null;
            String tertiaryInsurerVerificationStatus = null;

            boolean insuranceVerificationStatusFlag = false;
            if(soInsuranceDetailsDataObj.getCoverageVerificationStatus() != null && !soInsuranceDetailsDataObj.getCoverageVerificationStatus().equals("Y")){
                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Inappropriate Sales Order Insurance Details Coverage Verification Status."));
            }

            if(primaryInsurerId!=null && primaryInsurerId > 0){
                primaryInsurerVerificationStatus = (soInsuranceDetailsDataObj.getPrimaryInsurerVerificationStatus()!=null && soInsuranceDetailsDataObj.getPrimaryInsurerVerificationStatus().trim().equals("Y")) ? "Y" : "N";
                if(secondaryInsurerId!=null && secondaryInsurerId > 0){
                    secondaryInsurerVerificationStatus = (soInsuranceDetailsDataObj.getSecondaryInsurerVerificationStatus()!=null && soInsuranceDetailsDataObj.getSecondaryInsurerVerificationStatus().trim().equals("Y")) ? "Y" : "N";
                    if(tertiaryInsurerId!=null && tertiaryInsurerId > 0){
                        tertiaryInsurerVerificationStatus = (soInsuranceDetailsDataObj.getTertiaryInsurerVerificationStatus()!=null && soInsuranceDetailsDataObj.getTertiaryInsurerVerificationStatus().trim().equals("Y")) ? "Y" : "N";
                        //When primaryInsurerId, secondaryInsurerId and tertiaryInsurerId exists
                        //check primaryInsurerVerificationStatus "Y", secondaryInsurerVerificationStatus "Y" and tertiaryInsurerVerificationStatus "Y" then Update insuranceVerificationStatusFlag to true
                        if(primaryInsurerVerificationStatus.equals("Y") && secondaryInsurerVerificationStatus.equals("Y") && tertiaryInsurerVerificationStatus.equals("Y")){
                            insuranceVerificationStatusFlag = true;
                        }
                    }else{
                        //When primaryInsurerId and secondaryInsurerId exists
                        //check primaryInsurerVerificationStatus "Y" and secondaryInsurerVerificationStatus "Y" then Update insuranceVerificationStatusFlag to true
                        if(primaryInsurerVerificationStatus.equals("Y") && secondaryInsurerVerificationStatus.equals("Y")){
                            insuranceVerificationStatusFlag = true;
                        }
                    }
                }else{
                    //When primaryInsurerId exists
                    //check primaryInsurerVerificationStatus "Y" then Update insuranceVerificationStatusFlag to true
                    if(primaryInsurerVerificationStatus.equals("Y")){
                        insuranceVerificationStatusFlag = true;
                    }
                }
            }

            log.info("tertiaryInsurerId:: "+tertiaryInsurerId+" ,tertiaryInsurerVerificationStatus::"+tertiaryInsurerVerificationStatus);
            log.info("secondaryInsurerId:: "+secondaryInsurerId+" ,secondaryInsurerVerificationStatus::"+secondaryInsurerVerificationStatus);
            log.info("primaryInsurerId:: "+primaryInsurerId+" ,primaryInsurerVerificationStatus::"+primaryInsurerVerificationStatus);
            log.info("=======insuranceVerificationStatusFlag======="+insuranceVerificationStatusFlag);

            if(insuranceVerificationStatusFlag){
                //Reactive Code Put Here
                log.info("==========Ready to Update insuranceVerificationStatusFlag to Y============");
                log.info("=========soInsuranceDetailsDataObj========="+soInsuranceDetailsDataObj);
                Long soInsId = soInsuranceDetailsDataObj.getSalesOrderInsuranceDetailsId();
                return salesOrderInsuranceDetailsServiceExtended.updateSOInsuranceStatus(soInsId).map(existingSoInsuranceObj -> {
                    return existingSoInsuranceObj;
                    /*SalesOrderInsuranceDetails salesOrderInsuranceDetails = new SalesOrderInsuranceDetails();
                    if(existingSoInsuranceObj.getOutcome()){
                        salesOrderInsuranceDetails = (SalesOrderInsuranceDetails) existingSoInsuranceObj.getData();
                        System.out.println("=======FH salesOrderInsuranceDetails======="+salesOrderInsuranceDetails);
                    }
                    SalesOrderInsuranceDetails finalSalesOrderInsuranceDetails = salesOrderInsuranceDetails;
                    return new ServiceOutcome(null, true, "Delivery Address Not Verified.");*/
                }).switchIfEmpty(Mono.just(new ServiceOutcome(null, false, "Unable to Update Sales Order Insurance Details.")));
            }else{
                log.info("======insuranceVerificationStatusFlag :: false=====");
                return Mono.just(new ServiceOutcome<>(null, false, "All insurances are not verified."));
            }
        }
        return Mono.just(new ServiceOutcome<>(null, false, "Sales Order Insurance Details does not exist."));
    }

    public Mono<ServiceOutcome> getDeliveryAddressDelivarableOrNot(Long soId) throws ExecutionException, InterruptedException {
        log.info("============Inside getDeliveryAddressDelivarableOrNot================");
        return salesOrderMasterServiceExtented.getSODetailsBysoId(soId).map(outCome ->{
            log.info("========outCome::getSODetailsBysoId======="+outCome);
            String deliveryAddr1 = "";
            String deliveryAddr2 = "";
            String deliveryCity = "";
            String deliveryState = "";
            String deliveryZip = "";
            SalesOrderMaster data = (SalesOrderMaster) outCome.getData();
            if(outCome.getOutcome() &&  data.getSalesOrderId()!=null){
                deliveryAddr1 = data.getDeliveryAddressLine1()!= null ? data.getDeliveryAddressLine1() : "";
                deliveryAddr2 = data.getDeliveryAddressLine2()!= null ? data.getDeliveryAddressLine2() : "";
                deliveryCity = data.getDeliveryCityName()!= null ? data.getDeliveryCityName() : "";
                deliveryState = data.getDeliveryStateName()!= null ? data.getDeliveryStateName() : "";
                deliveryZip = data.getDeliveryZipCode()!= null ? data.getDeliveryZipCode() : "";
            }

            ObjectMapper mapper = new ObjectMapper();
            ServiceOutcome responseBody = new ServiceOutcome();

            try {
                String accessToken = InternalAccessTokenUtilities.getAccessToken();
                JSONParser parser = new JSONParser();
                JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
                String token = accessTokenJson.get("access_token").toString();
                if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                    CommonUtilities commonUtilitiesObj = new CommonUtilities();
                    Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");
                    //address1=&address2=1104%20DIANTHUS%20LN&city=EL%20DORADO%20HILLS&state=CA&zip5=95762&zip4=
                    String url = propData.getProperty("usps_url");
                    String paramValue = "?address1=" + deliveryAddr1 + "&address2=" + deliveryAddr2 +"&city="+ deliveryCity +"&state="+ deliveryState + "&zip5=" + deliveryZip;
                    String completeUrl = url + paramValue;

                    log.info("Address Deliverable completeUrl ::" + completeUrl);

                    RestTemplate restTemplateData = new RestTemplate();
                    HttpHeaders headersData = new HttpHeaders();
                    headersData.add("Authorization", "Bearer " + token);
                    HttpEntity entityData = new HttpEntity<>(headersData);
                    ResponseEntity responseData = restTemplateData.exchange(
                        completeUrl,
                        HttpMethod.GET,
                        entityData,
                        Object.class
                    );

                    if (responseData.getStatusCode() == HttpStatus.OK) {
                        responseBody = mapper.convertValue(responseData.getBody(), ServiceOutcome.class);
                        responseBody.setOutcome(responseBody.getOutcome());  // true means deliverable, false means not deliverable
                        responseBody.setMessage("Successfully Fetched.");
                        responseBody.setData(responseBody.getData());

                    } else {
                        responseBody.setOutcome(false);
                        responseBody.setMessage("API Error: No Response Data Available");
                        responseBody.setData(null);
                    }
                }
            } catch (HttpClientErrorException e) {
                // Client-side errors (4xx)
                if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                    try {
                        throw new InvalidAccessTokenException("REQUEST_CODE_498", "Invalid Access Token - " + e.getMessage());
                    } catch (InvalidAccessTokenException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
                } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
                } else if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request: Request Datatype Mismatch/Error in Request Body");
                } else {
                    throw new RuntimeException(e);
                }
            } catch (HttpServerErrorException e) {
                // Server-side errors (5xx)
                if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
                    throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
                } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
                } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                    throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
                } else {
                    throw new RuntimeException(e);
                }

            } catch (ResourceAccessException e) {
                // Timeouts, connection refused, etc.
                throw new RuntimeException(e);
            } catch (Exception e) {
                // Any other exception
                throw new RuntimeException(e);
            }

            return Mono.just(responseBody);
        }).flatMap(obj->{
            return obj;
        });

    }
}
//    public static void main(String[] args) {
//        Flux<Integer> source = Flux.just(1, 2, 3);
//
//        Flux<Student> result = source.flatMap(id -> {
//            System.out.println("Processing student with ID: " + id);
//
//            // Simulate an asynchronous operation to fetch student data using Mono
//            Mono<Student> fetchStudentMono = fetchStudentById(id);
//
//            return fetchStudentMono.flatMap(student -> {
//                // Modify the student object or perform other operations
//                student.setName(student.getName() + " Modified");
//                student.setAge(student.getAge() + 1);
//
//                return Mono.just(student);
//            });
//        });
//
//        result.subscribe(student -> System.out.println("Processed student: " + student.getName()));
//    }
//
//    // Simulated method to fetch student data using Mono
//    private static Mono<Student> fetchStudentById(int id) {
//        // Simulating asynchronous fetching of student data
//        return Mono.just(new Student("Student " + id, 20));
//    }
//}



