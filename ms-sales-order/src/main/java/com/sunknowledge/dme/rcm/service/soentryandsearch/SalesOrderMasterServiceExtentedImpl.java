package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.dropbox.core.InvalidAccessTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.InsurancePricetableMap;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.repository.InsurancePricetableMapRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.salesorder.SalesOrderMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.InventoryInputDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.InventoryStatusByItemIdAndItemLocationIdInputDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.ItemInventoryStatusExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.validateDeliveryInitiationSOItemDetailsResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderEntryParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderMasterMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

//@Primary
@Service("salesOrderMasterServiceExtentedImpl")
@Slf4j
public class SalesOrderMasterServiceExtentedImpl implements SalesOrderMasterServiceExtented {

    @Autowired
    SalesOrderMasterMapper salesOrderMasterMapper;
    @Autowired
    SalesOrderMasterRepositoryExtended salesOrderMasterRepositoryExtended;
    @Autowired
    InsurancePricetableMapRepositoryExtended insurancePricetableMapRepositoryExtended;

    @Override
    public Mono<SalesOrderMasterDTO> save(SalesOrderMasterDTO salesOrderMasterDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderMasterDTO> update(SalesOrderMasterDTO salesOrderMasterDTO) {
        return null;
    }

    @Override
    public Mono<SalesOrderMasterDTO> partialUpdate(SalesOrderMasterDTO salesOrderMasterDTO) {
        return null;
    }

    @Override
    public Flux<SalesOrderMasterDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Long> countAll() {
        return null;
    }

    @Override
    public Mono<SalesOrderMasterDTO> findOne(Long id) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }

    @Override
    public Flux<SalesOrderMaster> getSOByUUID(UUID salesOrderUUID) {
        return salesOrderMasterRepositoryExtended.getSOBysalesOrderMasterUuid(salesOrderUUID);
    }

    @Override
    public Mono<Long> getIDByUUID(UUID salesOrderUUID) {
        return salesOrderMasterRepositoryExtended.getIDBysalesOrderMasterUuid(salesOrderUUID);
    }

    public Mono<ResponseDTO> saveSOMasterDetails(SalesOrderMasterDTO obj, SalesOrderEntryParameterDTO salesOrderEntryParameterDTO) {
        String message = "";
        Boolean status = false;
        List<Object> responseList = new ArrayList<>();




        try {
            //----- Get details for check doctor is exist or not -----
            String accessToken = InternalAccessTokenUtilities.getAccessToken();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();



            String responseBody = "[]";
            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                Long branchId = obj.getBranchId() == null ? 0 : obj.getBranchId();
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                HttpEntity entityData = new HttpEntity<>(headersData);
                ResponseEntity responseData = restTemplateData.exchange(
                    "http://localhost:8080/services/settings/api/getBranchOfficeById" +
                        "?branchId={branchId}",
                    HttpMethod.GET,
                    entityData,
                    String.class,
                    branchId);
                if (responseData.getStatusCode() == HttpStatus.OK) {
                    responseBody = (String) responseData.getBody();
                } else {
                    message = "API Error: No Response Data Available";
                    return Mono.just(new ResponseDTO(status, message, responseList));
                }
            } else {
                message = "Missing Access Token";
                return Mono.just(new ResponseDTO(status, message, responseList));
            }
            JSONObject branchJson = (JSONObject) parser.parse(responseBody == null ? "{}" : responseBody);
//            System.out.println("branchJson=" + branchJson);



            //----------- Get branch details ------------
            if (!branchJson.isEmpty() && branchJson.get("data") != null
                && ((JSONArray) branchJson.get("data")).size() > 0) {
                JSONObject branchInfoRow = (JSONObject) ((JSONArray) branchJson.get("data")).get(0);



                Long patientBranchId = obj.getBranchId();
                obj.setPatientBranchId(patientBranchId);
                String branchContactPersonName = branchInfoRow.get("submitterContactPersonName") == null ?
                    "" : (String) branchInfoRow.get("submitterContactPersonName");
                obj.setBranchContactPersonName(branchContactPersonName);
                String branchNpi = branchInfoRow.get("npi") == null ?
                    "" : (String) branchInfoRow.get("npi");
                obj.setBranchNpi(branchNpi);
                String branchEin = branchInfoRow.get("taxIdNo") == null ?
                    "" : (String) branchInfoRow.get("taxIdNo");
                obj.setBranchEin(branchEin);
                String branchContactNo1 = branchInfoRow.get("submitterContactPhoneNo1") == null ?
                    "" : (String) branchInfoRow.get("submitterContactPhoneNo1");
                obj.setBranchContactNo1(branchContactNo1);
                String branchContactNo2 = branchInfoRow.get("submitterContactPhoneNo2") == null ?
                    "" : (String) branchInfoRow.get("submitterContactPhoneNo2");
                obj.setBranchContactNo2(branchContactNo2);
                String branchAddressLine1 = branchInfoRow.get("addressLine1") == null ?
                    "" : (String) branchInfoRow.get("addressLine1");
                obj.setBranchAddressLine1(branchAddressLine1);
                String branchAddressLine2 = branchInfoRow.get("addressLine2") == null ?
                    "" : (String) branchInfoRow.get("addressLine2");
                obj.setBranchAddressLine2(branchAddressLine2);
                String branchCity = branchInfoRow.get("city") == null ?
                    "" : (String) branchInfoRow.get("city");
                obj.setBranchCity(branchCity);
                String branchState = branchInfoRow.get("state") == null ?
                    "" : (String) branchInfoRow.get("state");
                obj.setBranchState(branchState);
                String branchZipCode = branchInfoRow.get("zip") == null ?
                    "" : (String) branchInfoRow.get("zip");
                obj.setBranchZipCode(branchZipCode);
                String branchFax = branchInfoRow.get("fax") == null ?
                    "" : (String) branchInfoRow.get("fax");
                obj.setBranchFax(branchFax);
                String billingProviderTaxonomy = branchInfoRow.get("billingTaxonomyCode") == null ?
                    "" : (String) branchInfoRow.get("billingTaxonomyCode");
                obj.setBillingProviderTaxonomy(billingProviderTaxonomy);
                String billingProviderNpi = branchInfoRow.get("billingNpi") == null ?
                    "" : (String) branchInfoRow.get("billingNpi");
                obj.setBillingProviderNpi(billingProviderNpi);
                String billingProviderOrganisationName = branchInfoRow.get("billingOrganisationName") == null ?
                    "" : (String) branchInfoRow.get("billingOrganisationName");
                obj.setBillingProviderOrganisationName(billingProviderOrganisationName);
                String billingProviderAddressLine1 = branchInfoRow.get("billingAddressLine1") == null ?
                    "" : (String) branchInfoRow.get("billingAddressLine1");
                obj.setBillingProviderAddressLine1(billingProviderAddressLine1);
                String billingProviderAddressLine2 = branchInfoRow.get("billingAddressLine2") == null ?
                    "" : (String) branchInfoRow.get("billingAddressLine2");
                obj.setBillingProviderAddressLine2(billingProviderAddressLine2);
                String billingProviderCity = branchInfoRow.get("billingCity") == null ?
                    "" : (String) branchInfoRow.get("billingCity");
                obj.setBillingProviderCity(billingProviderCity);
                String billingProviderState = branchInfoRow.get("billingState") == null ?
                    "" : (String) branchInfoRow.get("billingState");
                obj.setBillingProviderState(billingProviderState);
                String billingProviderCountry = obj.getBranchCountry();
                obj.setBillingProviderCountry(billingProviderCountry);
                String billingProviderZipCode = branchInfoRow.get("billingZipCode") == null ?
                    "" : (String) branchInfoRow.get("billingZipCode");
                obj.setBillingProviderZipCode(billingProviderZipCode);
                String branchTaxonomy = branchInfoRow.get("taxonomyCode") == null ?
                    "" : (String) branchInfoRow.get("taxonomyCode");
                obj.setBranchTaxonomy(branchTaxonomy);
                String billingBranchName = branchInfoRow.get("billingBranchName") == null ?
                    "" : (String) branchInfoRow.get("billingBranchName");
                obj.setBillingBranchName(billingBranchName);
                String patientDeliveryAddressLine1 = obj.getDeliveryAddressLine1();
                obj.setPatientDeliveryAddressLine1(patientDeliveryAddressLine1);
                String patientDeliveryAddressLine2 = obj.getDeliveryAddressLine2();
                obj.setPatientDeliveryAddressLine2(patientDeliveryAddressLine2);
                String patientDeliveryCity = obj.getDeliveryCityName();
                obj.setPatientDeliveryCity(patientDeliveryCity);
                String patientDeliveryState = obj.getDeliveryStateName();
                obj.setPatientDeliveryState(patientDeliveryState);
                String patientDeliveryCountry = obj.getBranchCountry();
                obj.setPatientDeliveryCountry(patientDeliveryCountry);
                String patientDeliveryZip = obj.getDeliveryZipCode();
                obj.setPatientDeliveryZip(patientDeliveryZip);
                String patientBillingAddressLine1 = obj.getPatientAddressLine1();
                obj.setPatientBillingAddressLine1(patientBillingAddressLine1);
                String patientBillingAddressLine2 = obj.getPatientAddressLine2();
                obj.setPatientBillingAddressLine2(patientBillingAddressLine2);
                String patientBillingCity = obj.getCityName();
                obj.setPatientBillingCity(patientBillingCity);
                String patientBillingState = obj.getStateName();
                obj.setPatientBillingState(patientBillingState);
                String patientBillingCountry = obj.getBranchCountry();
                obj.setPatientBillingCountry(patientBillingCountry);
                String patientBillingZip = obj.getZipCode();
                obj.setPatientBillingZip(patientBillingZip);

                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
                String formattedString2 = ZonedDateTime.now().format(formatter2);
                String salesOrderNote = formattedString2 + ": " + obj.getSalesOrderNote();
                obj.setSalesOrderNote(salesOrderNote);

                //------------------- Insurance Pricetable Map -------------------------
                Mono<InsurancePricetableMap> insurancePricetableMapMono = insurancePricetableMapRepositoryExtended.
                    findByInsuranceIdNo(salesOrderEntryParameterDTO.getInsuranceIdNo())
                    .reduce((max, current) -> current.getInsurancePricetableMapId() >
                        max.getInsurancePricetableMapId() ? current : max);
                InsurancePricetableMap insurancePricetableMap = insurancePricetableMapMono.toFuture().get();

                if (insurancePricetableMap != null) {
                    obj.setPrimaryInsurerPriceTableId(insurancePricetableMap.getPriceTableId());
                    obj.setPrimaryInsurerPriceTableName(insurancePricetableMap.getPriceTableName());
                    String itemLocationName = branchInfoRow.get("itemLocationName") == null ?
                        "" : (String) branchInfoRow.get("itemLocationName");
                    obj.setInventoryLocationName(itemLocationName);
                } else {
                    message = "Insurance is not map with any Price_Table";
                    return Mono.just(new ResponseDTO(status, message, responseList));
                }
                //------------------- Insurance Pricetable Map -------------------------


            } else {
                message = "Branch does not exist.";
                return Mono.just(new ResponseDTO(status, message, responseList));
            }
            //----------- Get branch details ------------

            //------------Saving Data-----------------------------------------------------
            if (obj.getSalesOrderId() == null || obj.getSalesOrderId() == 0) {
                //=============== Get Sales Order No ===================================
                String soIDNumber = salesOrderMasterRepositoryExtended.generatePatientIDNumber().toFuture().get();
                obj.setSalesOrderNo(soIDNumber);
                obj.setSoControlNumber(soIDNumber);
                //=============== Get Sales Order No ===================================
                obj.setCreatedDate(LocalDate.now());
                obj.setCreatedById(1L);   //----- Data taken from login user service
                obj.setCreatedByName("Abhijit Chowdhury"); //----- Data taken from login user service
                obj.setSalesOrderId(null);
                obj.setSalesOrderMasterUuid(UUID.randomUUID());
                return salesOrderMasterRepositoryExtended
                    .save(salesOrderMasterMapper.toEntity(obj))
                    .map(salesOrderMasterMapper::toDto).map(
                        i -> new ResponseDTO(true, "Successfully Saved", List.of(i)));
            } else {
                List<SalesOrderMaster> salesOrderMasterForUpdate = salesOrderMasterRepositoryExtended.
                    getSOBysalesOrderMasterUuid(obj.getSalesOrderMasterUuid()).collectList().toFuture().get();
                if (salesOrderMasterForUpdate.size() > 0) {
                    SalesOrderMaster updateObj = salesOrderMasterForUpdate.get(0);
                    BeanUtils.copyProperties(salesOrderEntryParameterDTO, updateObj);
                    updateObj.setUpdatedDate(LocalDate.now());
                    updateObj.setUpdatedById(1L);      //----- Data taken from login user service
                    updateObj.setUpdatedByName("Abhijit Chowdhury");   //----- Data taken from login user service
                    return salesOrderMasterRepositoryExtended
                        .save(salesOrderMasterMapper.toEntity(obj))
                        .map(salesOrderMasterMapper::toDto).map(
                            i -> new ResponseDTO(true, "Successfully Saved", List.of(i)));
                } else {
                    message = "Update failed! Sales_Order does not exist.";
                    return Mono.just(new ResponseDTO(status, message, responseList));
                }
            }

            //-----------------------------Saving Data----------------------------------------------

        }
//        catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
        catch (HttpClientErrorException e) {
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
//        return Mono.just(new ResponseDTO());
    }

    @Override
    public Mono<ServiceOutcome> patientDateOfDeathIncorporation(Long patientId, LocalDate patientDod) throws ExecutionException, InterruptedException {
        List<SalesOrderMaster> salesOrderMasterList = salesOrderMasterRepositoryExtended.getSOByPatientId(patientId).collectList().toFuture().get();
        System.out.println("salesOrderMasterList " + salesOrderMasterList);
        List<SalesOrderMaster> salesOrderMasters = new ArrayList<>();
        salesOrderMasterList.stream().forEach(existingObj -> {
            existingObj.setStopDate(patientDod);
            existingObj.setStopReasonDescription("Date of Death Recorded");
            try {
                SalesOrderMaster salesOrderMaster = salesOrderMasterRepositoryExtended.save(existingObj).toFuture().get();
                salesOrderMasters.add(salesOrderMaster);
                System.out.println("salesOrderMaster " + salesOrderMaster);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        if (salesOrderMasterList.size() > 0) {
            if (salesOrderMasters.size() > 0) {
                return Mono.just(new ServiceOutcome(salesOrderMasters, true, "Updated Successfully."));
            } else {
                return Mono.just(new ServiceOutcome(null, false, "Data not found."));
            }
        } else {
            return Mono.just(new ServiceOutcome(null, false, "Data not found."));
        }
    }

    @Override
    public Mono<ServiceOutcome> getSOBySoId(Long soId) {
        SalesOrderMasterDTO salesOrderMasterDTO = null;
        try {
            salesOrderMasterDTO = salesOrderMasterMapper.toDto(salesOrderMasterRepositoryExtended.findById(soId).toFuture().get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return Mono.just(new ServiceOutcome<>(salesOrderMasterDTO, salesOrderMasterDTO != null ? true : false, salesOrderMasterDTO != null ? "Fetched Successfully" : "Data Not Found."));
    }

    @Override
    public List<SalesOrderMaster> updateHoldStatus(List<SalesOrderMaster> salesOrderMasters) {
        List<SalesOrderMaster> salesOrderMasters1 = new ArrayList<>();
        salesOrderMasters.forEach(updatedObj -> {
            updatedObj.setHoldStatus("T");
            updatedObj.setHoldReasonDescription("Denial/Rejection");
            updatedObj.setHoldStatus("True");

            updatedObj.setUpdatedById(1l);
            updatedObj.setUpdatedDate(LocalDate.now());
            updatedObj.setUpdatedByName("Updated By Static Name");
            try {
                salesOrderMasters1.add(salesOrderMasterRepositoryExtended.save(updatedObj).toFuture().get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        return salesOrderMasters1;
    }

    @Override
    public Flux<SalesOrderMaster> findAllById(List<Long> soIds) {
        return salesOrderMasterRepositoryExtended.findAllById(soIds);
    }

    @Override
    public Mono<ServiceOutcome> getSODetailsBysoId(Long soId) throws ExecutionException, InterruptedException {
        System.out.println("==============Inside getSODetailsBysoId::==============" + soId);
        return salesOrderMasterRepositoryExtended.getSODetailsBysoId(soId).map(existingSOMasterData -> {
            if (existingSOMasterData.getSalesOrderId() != null) {
                return Mono.just(new ServiceOutcome(existingSOMasterData, true, "Data fetched Successfully."));
            } else {
                return Mono.just(new ServiceOutcome(null, false, "Data not found."));
            }
        }).flatMap(obj -> {
            return obj;
        });

    }

    @Override
    public Mono<ServiceOutcome> makeSOCommited(Long soId, List<validateDeliveryInitiationSOItemDetailsResponseDTO> soItemDetailsList,
                                               List<String> cmnStatus, List<String> parStatus, List<ItemInventoryStatusExtendedDTO> itemInventoryStatusList,
                                               ServiceOutcome deliverableAddr) throws ExecutionException, InterruptedException, IOException, ParseException {
        //cmnStatus.remove("initiated");
        //cmnStatus.add("logged");
        for (String eachCmnStatus : cmnStatus) {
            if (!eachCmnStatus.trim().equalsIgnoreCase("logged")) {
                return Mono.just(new ServiceOutcome(null, false, "Sales Order Can Not be Committed Due to CMN is not Logged/Active (Approved)."));
            }
        }

        //parStatus.remove("initiated");
        //parStatus.add("active");
        if (parStatus.size() > 0) {
            for (String eachParStatus : parStatus) {
                if (!eachParStatus.trim().equalsIgnoreCase("active")) {
                    return Mono.just(new ServiceOutcome(null, false, "Sales Order Can Not be Committed Due to PAR is not Active (Approved)."));
                }
            }
        }

        HashMap<Long, String> mapItemIdDropshipStatus = new HashMap<>();
        for (validateDeliveryInitiationSOItemDetailsResponseDTO eachItemDetailsObj : soItemDetailsList) {
            String dropShipStatus = (eachItemDetailsObj.getDropshipStatus() != null && !eachItemDetailsObj.getDropshipStatus().trim().equals("")) ? eachItemDetailsObj.getDropshipStatus() : "N";
            if(eachItemDetailsObj.getSalesOrderDetailsItemId()!= null){
                mapItemIdDropshipStatus.put(eachItemDetailsObj.getSalesOrderDetailsItemId(), dropShipStatus);
            }
        }
        log.info("========mapItemIdDropshipStatus========"+mapItemIdDropshipStatus);
        //Microservice will be called(update in item.t_item_inventory_status table) if every Item Inventory is Valid
        List<Boolean> commitSOFlag = new ArrayList<>();
        for (ItemInventoryStatusExtendedDTO eachObj : itemInventoryStatusList) {
            Long onHandQty = eachObj.getOnhandQty() != null && eachObj.getOnhandQty() != 0 ? eachObj.getOnhandQty() : 0L;
            Long inorderQty = eachObj.getInorderQty() != null && eachObj.getInorderQty() != 0 ? eachObj.getInorderQty() : 0L;
            Long committedQty = eachObj.getComittedQty() != null && eachObj.getComittedQty() != 0 ? eachObj.getComittedQty() : 0L;
            Long itemQty = eachObj.getItemQty() != null && eachObj.getItemQty() != 0 ? eachObj.getItemQty() : 0;
            String dropShipStatusForItemId = mapItemIdDropshipStatus.get(eachObj.getItemId());
            if (!dropShipStatusForItemId.equals("Y") && ((itemQty > (onHandQty - (inorderQty + committedQty))) || (itemQty > inorderQty))) {
                log.info("itemQty::" + itemQty + " ,onHandQty::" + onHandQty + " ,inorderQty" + inorderQty + " ,committedQty" + committedQty + " ,itemQty" + itemQty + " ," + eachObj.getItemId());
                return (Mono.just(new ServiceOutcome(null, false, "Insufficient Quantity.")));
            }
        }

        //Also update in item.t_item_inventory_status table with comitted_qty = (comitted_qty + <so_item_qty>) and inorder_qty = (inorder_qty - <so_item_qty>) by item_id
        // and item_location_id [item_id, <so_item_qty> and item_location_id will be taken so.t_sales_order_item_details - should be done in loop]
        List<InventoryStatusByItemIdAndItemLocationIdInputDTO> inventoryStatusInput = new ArrayList<>();
        InventoryInputDTO inputDTO = new InventoryInputDTO();
        for (validateDeliveryInitiationSOItemDetailsResponseDTO eachObj : soItemDetailsList) {
            Long itemId = eachObj.getSalesOrderDetailsItemId() != null && eachObj.getSalesOrderDetailsItemId() != 0 ? eachObj.getSalesOrderDetailsItemId() : 0;
            Long itemLocationId = eachObj.getItemLocationId() != null && eachObj.getItemLocationId() != 0 ? eachObj.getItemLocationId() : 0;
            Long itemQty = eachObj.getSalesOrderDetailsQty() != null && eachObj.getSalesOrderDetailsQty() != 0 ? eachObj.getSalesOrderDetailsQty() : 0;
            log.info("itemId::" + itemId + " ,itemLocationId::" + itemLocationId + " ,itemQty::" + itemQty);
            if (itemId != 0 && itemLocationId != 0) {
                //Check ALl Item Inventory Before transaction into
                InventoryStatusByItemIdAndItemLocationIdInputDTO inventoryStatusByItemIdAndItemLocationIdInputDTO = new InventoryStatusByItemIdAndItemLocationIdInputDTO();
                inventoryStatusByItemIdAndItemLocationIdInputDTO.setItemId(itemId);
                inventoryStatusByItemIdAndItemLocationIdInputDTO.setItemLocationId(itemLocationId);
                inventoryStatusByItemIdAndItemLocationIdInputDTO.setItemQty(itemQty);
                inventoryStatusByItemIdAndItemLocationIdInputDTO.setOperationType("Committed");
                inventoryStatusInput.add(inventoryStatusByItemIdAndItemLocationIdInputDTO);
            } else {
                log.info("In Else Part== itemId::" + itemId + " ,itemLocationId::" + itemLocationId + " ,itemQty::" + itemQty);
                return Mono.just(new ServiceOutcome(null, false, "Sales Order Can Not be Commited Due to Inappropriate Item Id and Item Location Id."));
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(inventoryStatusInput);
        String encodedJson = URLEncoder.encode(json, "UTF-8");
        ServiceOutcome responseBody = saveInventoryStatusByItemIdAndItemLocationId(encodedJson);
        log.info("=======responseBody======" + responseBody);
        //boolean allTrue = commitSOFlag.stream().allMatch(Boolean::booleanValue); // Check if all elements in the list are true.
        if (responseBody.getOutcome()) {
            String addressData = deliverableAddr.getData().toString();
            log.info("=======addressData=======" + addressData.contains("message=Valid Address"));
            String deliverableAddress = null;
            if (addressData.contains("message=Valid Address")) {
                deliverableAddress = "With Valid Delivery Address.";
            } else {
                deliverableAddress = "With Invalid Delivery Address.";
            }
            //String finalDeliverableAddress = DeliverableAddress;
            String finalDeliverableAddress = deliverableAddress;
            return salesOrderMasterRepositoryExtended.findById(soId).map(existingSOData -> {
                    existingSOData.setOrderStatus("Committed");
                    existingSOData.setUpdatedDate(LocalDate.now());
                    existingSOData.setUpdatedByName("Falguni");
                    existingSOData.setUpdatedById(31L);
                    existingSOData.setUpdatedDate(LocalDate.now());
                    return existingSOData;
                }).flatMap(salesOrderMasterRepositoryExtended::save)
                .map(salesOrderMasterMapper::toDto)
                .map(updatedObj -> new ServiceOutcome(updatedObj, true, "Sales Order Successfully Committed " + finalDeliverableAddress))
                .switchIfEmpty(Mono.just(new ServiceOutcome<SalesOrderMasterDTO>(null, false, "Sales Order Does Not Exist.")));
        } else {
            return Mono.just(new ServiceOutcome(null, false, "Error Occurred."));
        }
    }

    @Override
    public Mono<ItemInventoryStatusExtendedDTO> getItemInventoryStatusData(Long itemId, Long itemLocationId) {
        return salesOrderMasterRepositoryExtended.getItemInventoryStatusData(itemId, itemLocationId);
    }

    public Flux<String> getCMNStatusByCMNIds(List<Long> cmnIdList) {
        return salesOrderMasterRepositoryExtended.getCMNStatusByCMNId(cmnIdList);
    }

    public Flux<String> getParStatusByParId(List<Long> parIdList) {
        return salesOrderMasterRepositoryExtended.getParStatusByParId(parIdList);
    }

    public ServiceOutcome saveInventoryStatusByItemIdAndItemLocationId(String json) throws IOException, ParseException {
        System.out.println("=========json=========" + json);
        Map saveInventoryStatusInputDTOMap = new HashMap();
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
                String url = propData.getProperty("items_inventory_status");

                String paramValue = "?inputParams=" + json;
                String completeUrl = url + paramValue;
                System.out.println("completeUrl " + completeUrl);

                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                HttpEntity entityData = new HttpEntity<>(headersData);
                ResponseEntity responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.PUT,
                    entityData,
                    ServiceOutcome.class
                );

                if (responseData.getStatusCode() == HttpStatus.OK) {
                    responseBody = (ServiceOutcome) responseData.getBody();
                    System.out.println("========responseBody=======" + responseBody);
                } else {
                    responseBody.setOutcome(false);
                    responseBody.setMessage("API Error: No Response Data Available");
                    responseBody.setData(new JSONArray());
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

        return responseBody;
    }
    @Override
    public Mono<ServiceOutcome> updateSalesOrderedStatusAsDelivered(Long salesOrderId,String soNo, Flux<SalesOrderItemDetails> salesOrderItemDetailsFlux) {
        return salesOrderItemDetailsFlux.collectList()
            .flatMap(salesOrderItemDetailsList -> {
                List<InventoryStatusByItemIdAndItemLocationIdInputDTO> inventoryStatusInputs = new ArrayList<>();
                for(SalesOrderItemDetails salesOrderItemDetails:salesOrderItemDetailsList){
                    Long itemId = salesOrderItemDetails.getSalesOrderDetailsItemId() != null && salesOrderItemDetails.getSalesOrderDetailsItemId() != 0 ? salesOrderItemDetails.getSalesOrderDetailsItemId() : 0;
                    Long itemLocationId = salesOrderItemDetails.getItemLocationId() != null && salesOrderItemDetails.getItemLocationId() != 0 ? salesOrderItemDetails.getItemLocationId() : 0;
                    Long itemQty = salesOrderItemDetails.getSalesOrderDetailsQty() != null && salesOrderItemDetails.getSalesOrderDetailsQty() != 0 ? salesOrderItemDetails.getSalesOrderDetailsQty() : 0;
                    String saleType = salesOrderItemDetails.getSalesOrderDetailsSaleType();
                    String isDropShip = salesOrderItemDetails.getIsDropshipAllowed();
                    log.info("itemId::"+itemId+" ,itemLocationId::"+itemLocationId+" ,itemQty::"+itemQty);
                    if (itemId != 0 && itemLocationId != 0) {
                        //Check ALl Item Inventory Before transaction into
                        InventoryStatusByItemIdAndItemLocationIdInputDTO inventoryStatusByItemIdAndItemLocationIdInputDTO = new InventoryStatusByItemIdAndItemLocationIdInputDTO();
                        inventoryStatusByItemIdAndItemLocationIdInputDTO.setItemId(itemId);
                        inventoryStatusByItemIdAndItemLocationIdInputDTO.setItemLocationId(itemLocationId);
                        inventoryStatusByItemIdAndItemLocationIdInputDTO.setItemQty(itemQty);
                        inventoryStatusByItemIdAndItemLocationIdInputDTO.setSaleType(saleType);
                        inventoryStatusByItemIdAndItemLocationIdInputDTO.setSoNo(soNo);
                        inventoryStatusByItemIdAndItemLocationIdInputDTO.setIsDropshipFlag(isDropShip);
                        inventoryStatusByItemIdAndItemLocationIdInputDTO.setOperationType("Delivered");
                        inventoryStatusInputs.add(inventoryStatusByItemIdAndItemLocationIdInputDTO);
                    } else {
                        return Mono.just(new ServiceOutcome(null, false, "Sales Order Can Not be Commited Due to Inappropriate Item Id and Item Location Id."));
                    }
                }
                ObjectMapper objectMapper = new ObjectMapper();
                String json = null;
                String encodedJson = null;
                try {
                    json = objectMapper.writeValueAsString(inventoryStatusInputs);
                    encodedJson = URLEncoder.encode(json, "UTF-8");
                    ServiceOutcome serviceOutcome = saveInventoryStatusByItemIdAndItemLocationId(encodedJson);
                    System.out.println("=====serviceOutcome==== "+serviceOutcome);
                    if(serviceOutcome.getOutcome()){
                        System.out.println("=====Inside If ==== "+salesOrderId);
                        return salesOrderMasterRepositoryExtended.findById(salesOrderId)
                            .map(data -> {
                                data.setOrderStatus("Delivered");
                                return data;
                            }).flatMap(salesOrderMasterRepositoryExtended::save)
                            .map(d -> new ServiceOutcome(d,true,"Data Successfully Saved."));
                    }else{
                        return Mono.just(serviceOutcome);
                    }
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    @Override
    public Mono<ServiceOutcome> addSalesOrderNotes(Long soId, String soNote) {
        return salesOrderMasterRepositoryExtended.findById(soId).map(existingSOData -> {
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
                String formattedString2 = ZonedDateTime.now().format(formatter2);

                String salesOrderNote = formattedString2 + ": " + soNote;
                existingSOData.setSalesOrderNote(existingSOData.getSalesOrderNote() + "###" + salesOrderNote);
                existingSOData.setUpdatedByName("Falguni");
                existingSOData.setUpdatedById(31L);
                existingSOData.setUpdatedDate(LocalDate.now());
                return existingSOData;
            }).flatMap(salesOrderMasterRepositoryExtended::save)
            .map(salesOrderMasterMapper::toDto)
            .map(updatedObj -> new ServiceOutcome(updatedObj, true, "Note Successfully Added."))
            .switchIfEmpty(Mono.just(new ServiceOutcome<SalesOrderMasterDTO>(null, false, "Sales Order Does Not Exist.")));
    }

    @Override
    public Mono<ServiceOutcome<JSONArray>> getSalesOrderNotes(Long soId) {
        System.out.println("==============Inside getSODetailsBysoId::==============" + soId);
        return salesOrderMasterRepositoryExtended.getSODetailsBysoId(soId).map(existingSOMasterData -> {
            String salesOrderNote = existingSOMasterData.getSalesOrderNote();
            if (salesOrderNote != null && !salesOrderNote.trim().equals("")) {
                String soNote[] = salesOrderNote.split("###");
                JSONArray noteArray = new JSONArray();
                for (int i = 0; i < soNote.length; i++) {
                    JSONObject noteObj = new JSONObject();
                    noteObj.put("note", soNote[i]);

                    noteArray.add(noteObj);
                }
                return new ServiceOutcome<JSONArray>(noteArray, true, "Data fetched Successfully.");
            } else {
                return new ServiceOutcome<JSONArray>(new JSONArray(), false, "SO Note not available.");
            }
        }).switchIfEmpty(Mono.just(new ServiceOutcome<JSONArray>(null, false, "Sales Order Does Not Exist.")));

    }
}
