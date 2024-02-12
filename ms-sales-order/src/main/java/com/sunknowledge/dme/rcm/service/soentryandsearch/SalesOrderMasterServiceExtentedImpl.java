package com.sunknowledge.dme.rcm.service.soentryandsearch;

import com.dropbox.core.InvalidAccessTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.AccessTokenUtilities;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.InsurancePricetableMap;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.repository.InsurancePricetableMapRepositoryExtended;
import com.sunknowledge.dme.rcm.repository.salesorder.SalesOrderMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.dto.SOConfigPropertyConst;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.InventoryInputDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.InventoryStatusByItemIdAndItemLocationIdInputDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.ItemInventoryStatusExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.validateDeliveryInitiationSOItemDetailsResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderEntryParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SoClinicalInsuranceOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.TaskDetailsOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.WipQueueDetailsOutputDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.WipStatusUpdateInfoDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderMasterMapper;
import org.springframework.beans.BeanUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class SalesOrderMasterServiceExtentedImpl implements SalesOrderMasterServiceExtented {

    private final Logger log = LoggerFactory.getLogger(SalesOrderMasterServiceExtented.class);

    @Autowired
    SalesOrderMasterMapper salesOrderMasterMapper;
    @Autowired
    SalesOrderMasterRepositoryExtended salesOrderMasterRepositoryExtended;
    @Autowired
    InsurancePricetableMapRepositoryExtended insurancePricetableMapRepositoryExtended;
    private final WebClient webClient;

    public SalesOrderMasterServiceExtentedImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/services").build();
    }

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
    public Mono<SalesOrderMasterDTO> getSOBySoUUID(UUID salesOrderUUID) {
        return salesOrderMasterRepositoryExtended.getSOBySOUuid(salesOrderUUID)
            .map(salesOrderMasterMapper::toDto);
    }
    @Override
    public Flux<SalesOrderMaster> getSOByUUID(UUID salesOrderUUID) {
        return salesOrderMasterRepositoryExtended.getSOBysalesOrderMasterUuid(salesOrderUUID);
    }

    @Override
    public Mono<Long> getIDByUUID(UUID salesOrderUUID) {
        return salesOrderMasterRepositoryExtended.getIDBysalesOrderMasterUuid(salesOrderUUID);
    }

    public Mono<ResponseDTO> saveSOMasterDetails(SalesOrderMasterDTO obj, SalesOrderEntryParameterDTO salesOrderEntryParameterDTO,InsurancePricetableMap insurancePricetableMap) {
        String message = "";
        Boolean status = false;
        List<Object> responseList = new ArrayList<>();
        System.out.println("Inside 1");
        System.out.println("salesOrderEntryParameterDTO 1 "+ salesOrderEntryParameterDTO);
        try {
            //----- Get details for check doctor is exist or not -----
            String accessToken = InternalAccessTokenUtilities.getAccessToken();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            System.out.println("Inside 2");
            String responseBody = "[]";
            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                CommonUtilities commonUtilitiesObj = new CommonUtilities();
                Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");
                String url = propData.getProperty("branchOffice_url");
                System.out.println("branchOffice_url "+ url);
                Long branchId = obj.getBranchId() == null ? 0 : obj.getBranchId();
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                HttpEntity entityData = new HttpEntity<>(headersData);
                ResponseEntity responseData = restTemplateData.exchange(
                    url +
                        "?branchId={branchId}",
                    HttpMethod.GET,
                    entityData,
                    String.class,
                    branchId);
                if (responseData.getStatusCode() == HttpStatus.OK) {
                    System.out.println("Inside 3");
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
            System.out.println("branchJson=" + branchJson);

            System.out.println("branchJson " + branchJson);
            //----------- Get branch details ------------
            if (!branchJson.isEmpty() && branchJson.get("data") != null) {
                JSONObject branchInfoRow = (JSONObject) branchJson.get("data");

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
                obj.setOrderStatus("Initiated");
                //------------------- Insurance Pricetable Map -------------------------
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
                System.out.println("salesOrderEntryParameterDTO 2 "+ salesOrderEntryParameterDTO);

            } else {
                message = "Branch does not exist.";
                return Mono.just(new ResponseDTO(status, message, responseList));
            }
            //----------- Get branch details ------------

            //------------Saving Data-----------------------------------------------------
            if (obj.getSalesOrderId() == null || obj.getSalesOrderId() == 0) {
                //=============== Get Sales Order No ===================================
                return salesOrderMasterRepositoryExtended.generatePatientIDNumber()
                        .flatMap(soIDNumber -> {
                            obj.setSalesOrderNo(soIDNumber);
                            obj.setSoControlNumber(soIDNumber);
                            //=============== Get Sales Order No ===================================
                            obj.setCreatedDate(LocalDate.now());
                            obj.setCreatedById(1L);   //----- Data taken from login user service
                            obj.setCreatedByName("Abhijit Chowdhury"); //----- Data taken from login user service
                            obj.setSalesOrderId(null);
                            obj.setStatus("active");
                            obj.setSalesOrderMasterUuid(UUID.randomUUID());
                            return salesOrderMasterRepositoryExtended
                                .save(salesOrderMasterMapper.toEntity(obj))
                                .map(salesOrderMasterMapper::toDto).map(
                                    i -> new ResponseDTO(true, "Successfully Saved", (i)));
                        });
            } else {
                System.out.println("salesOrderEntryParameterDTO 3 "+ salesOrderEntryParameterDTO);
                 return salesOrderMasterRepositoryExtended.
                    getSOBySOUuid(obj.getSalesOrderMasterUuid())
                        .flatMap(updateObj -> {
                            if (updateObj!=null) {
                                if(updateObj.getOrderStatus().equalsIgnoreCase("delivered")){
                                    updateObj.setUserField1(salesOrderEntryParameterDTO.getUserField1());
                                    updateObj.setUserField2(salesOrderEntryParameterDTO.getUserField2());
                                    updateObj.setUserField3(salesOrderEntryParameterDTO.getUserField3());
                                    updateObj.setUserField4(salesOrderEntryParameterDTO.getUserField4());
                                }else{
                                    BeanUtils.copyProperties(salesOrderEntryParameterDTO, updateObj);
                                }

                                System.out.println("salesOrderMasterForUpdate "+ updateObj);
                                System.out.println("salesOrderEntryParameterDTO "+ salesOrderEntryParameterDTO);
                                updateObj.setUpdatedDate(LocalDate.now());
                                updateObj.setUpdatedById(1L);      //----- Data taken from login user service
                                updateObj.setStatus("active");
                                updateObj.setUpdatedByName("Abhijit Chowdhury");   //----- Data taken from login user service
                                return salesOrderMasterRepositoryExtended
                                    .save(updateObj)
                                    .map(salesOrderMasterMapper::toDto).map(
                                        i -> new ResponseDTO(true, "Successfully Saved", (i)));
                            } else {
                                return Mono.just(new ResponseDTO(status, "Update failed! Sales_Order does not exist.", responseList));
                            }
                        });
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
        cmnStatus.remove("initiated");
        cmnStatus.add("logged");
        for (String eachCmnStatus : cmnStatus) {
            if (!eachCmnStatus.trim().equalsIgnoreCase("logged")) {
                return Mono.just(new ServiceOutcome(null, false, "Sales Order Can Not be Committed Due to CMN is not Logged/Active (Approved)."));
            }
        }

        parStatus.remove("initiated");
        parStatus.add("active");
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
            if (eachItemDetailsObj.getSalesOrderDetailsItemId() != null) {
                mapItemIdDropshipStatus.put(eachItemDetailsObj.getSalesOrderDetailsItemId(), dropShipStatus);
            }
        }
        log.info("========mapItemIdDropshipStatus========" + mapItemIdDropshipStatus);
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
            System.out.println("===========================> itemId=" + itemId);
            System.out.println("===========================> itemLocationId=" + itemLocationId);
            System.out.println("===========================> itemQty=" + itemQty);
            System.out.println("======================================================================================================");
//            log.info("itemId::" + itemId + " ,itemLocationId::" + itemLocationId + " ,itemQty::" + itemQty);
            if (itemId != 0 && itemLocationId != 0) {
                //Check ALl Item Inventory Before transaction into
                String dropShipStatusForItemId = mapItemIdDropshipStatus.get(itemId);
                if (!dropShipStatusForItemId.equals("Y")) {
                    InventoryStatusByItemIdAndItemLocationIdInputDTO inventoryStatusByItemIdAndItemLocationIdInputDTO = new InventoryStatusByItemIdAndItemLocationIdInputDTO();
                    inventoryStatusByItemIdAndItemLocationIdInputDTO.setItemId(itemId);
                    inventoryStatusByItemIdAndItemLocationIdInputDTO.setItemLocationId(itemLocationId);
                    inventoryStatusByItemIdAndItemLocationIdInputDTO.setItemQty(itemQty);
                    inventoryStatusByItemIdAndItemLocationIdInputDTO.setOperationType("Committed");
                    inventoryStatusInput.add(inventoryStatusByItemIdAndItemLocationIdInputDTO);
                }
            } else {
                log.info("In Else Part== itemId::" + itemId + " ,itemLocationId::" + itemLocationId + " ,itemQty::" + itemQty);
                return Mono.just(new ServiceOutcome(null, false, "Sales Order Can Not be Committed Due to Inappropriate Item Id and Item Location Id."));
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
            return Mono.just(new ServiceOutcome(null, false, "Error: Item Inventory Service not Working."));
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
    public Mono<ServiceOutcome> updateSalesOrderedStatusAsDelivered(Long salesOrderId, String soNo, Flux<SalesOrderItemDetails> salesOrderItemDetailsFlux) {
        return salesOrderItemDetailsFlux.collectList()
            .flatMap(salesOrderItemDetailsList -> {
                List<InventoryStatusByItemIdAndItemLocationIdInputDTO> inventoryStatusInputs = new ArrayList<>();
                for (SalesOrderItemDetails salesOrderItemDetails : salesOrderItemDetailsList) {
                    Long itemId = salesOrderItemDetails.getSalesOrderDetailsItemId() != null && salesOrderItemDetails.getSalesOrderDetailsItemId() != 0 ? salesOrderItemDetails.getSalesOrderDetailsItemId() : 0;
                    Long itemLocationId = salesOrderItemDetails.getItemLocationId() != null && salesOrderItemDetails.getItemLocationId() != 0 ? salesOrderItemDetails.getItemLocationId() : 0;
                    Long itemQty = salesOrderItemDetails.getSalesOrderDetailsQty() != null && salesOrderItemDetails.getSalesOrderDetailsQty() != 0 ? salesOrderItemDetails.getSalesOrderDetailsQty() : 0;
                    String saleType = salesOrderItemDetails.getSalesOrderDetailsSaleType();
                    String isDropShip = salesOrderItemDetails.getIsDropshipAllowed();
                    log.info("itemId::" + itemId + " ,itemLocationId::" + itemLocationId + " ,itemQty::" + itemQty);
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
                    System.out.println("=====serviceOutcome==== " + serviceOutcome);
                    if (serviceOutcome.getOutcome()) {
                        System.out.println("=====Inside If ==== " + salesOrderId);
                        return salesOrderMasterRepositoryExtended.findById(salesOrderId)
                            .map(data -> {
                                data.setOrderStatus("Delivered");
                                return data;
                            }).flatMap(salesOrderMasterRepositoryExtended::save)
                            .map(d -> new ServiceOutcome(d, true, "Data Successfully Saved."));
                    } else {
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

    @Override
    public Mono<SoClinicalInsuranceOutputDTO> getSalesOrderMasterClinicalInsuranceData(Long soId) {
        return salesOrderMasterRepositoryExtended.getSalesOrderMasterClinicalInsuranceData(soId);
    }

    @Override
    public Mono<ServiceOutcome> getSOWIPDetails(String taskId, String wipStatusId, Long soId,String objectId) {
        CommonUtilities commonUtilitiesObj = new CommonUtilities();
        Properties propData = null;
        try {
            propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
        log.info("============><=============" + token);
        String url = propData.getProperty("utilitis_wip_url");

        String finalUrl = url;
        log.info("====final URL===GET=============>" + finalUrl);

        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path(finalUrl)
                .queryParam("taskId", taskId!=null?Long.valueOf(taskId):taskId)
                .queryParam("objectinstanceid", soId!=null?Long.valueOf(soId):soId)
                .queryParam("wipstatusid", wipStatusId)
                .queryParam("objectid", objectId!=null?Long.valueOf(objectId):objectId)
                .build())
            .header("Authorization", "Bearer " + token)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<>() {
            });
    }

    @Override
    public Mono<String> getTaskIdByTaskName(String taskName) {
        return salesOrderMasterRepositoryExtended.getTaskIdByTaskName(taskName);
    }

    @Override
    public Mono<String> getWipStatusIdByWipStatusName(String wipStatusName) {
        return salesOrderMasterRepositoryExtended.getWipStatusIdByWipStatusName(wipStatusName);
    }

    @Override
    public Mono<String> getObjectIdByObjectName(String objectName) {
        return salesOrderMasterRepositoryExtended.getObjectIdByObjectName(objectName);
    }

    @Override
    public Mono<String> getUserNameByUserId(Long userId) {
        return salesOrderMasterRepositoryExtended.getUserNameByUserId(userId);
    }

    @Override
    public Mono<ServiceOutcome<WipQueueDetailsOutputDTO>> updateWIPStatus(WipStatusUpdateInfoDTO obj) {
        obj.setAssignedById(1L);
        obj.setAssignedByName("Abhijit Chowdhury");
        obj.setAssignedDate(LocalDate.now());
        obj.setWipSetById(1L);
        obj.setWipSetByName("Abhijit Chowdhury");

        //========================================================================================
        CommonUtilities commonUtilitiesObj = new CommonUtilities();
        Properties propData = null;
        try {
            propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String save_wip_queue_details_url = propData.getProperty("save_wip_queue_details_url");
//        String update_wip_date_needed_url = propData.getProperty("update_wip_date_needed_url");

        String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
        log.info("==== Token =====>" + token);

        WipQueueDetailsOutputDTO wipQueueDetailsOutputDTO = null;
        Mono<ServiceOutcome<WipQueueDetailsOutputDTO>> wipOutcome = null;

        log.info("====URL_POST====save_wip_queue_details_url====>" + save_wip_queue_details_url);
//        log.info("====URL_POST====update_wip_date_needed_url====>" + update_wip_date_needed_url);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("wipStatusId", String.valueOf(obj.getWipStatusId()));
        formData.add("taskId", String.valueOf(obj.getTaskId()));
        formData.add("objectId", String.valueOf(obj.getObjectId()));
        formData.add("objectInstanceId", String.valueOf(obj.getObjectInstanceId()));
        formData.add("objectInstanceIdUuid", String.valueOf(obj.getObjectInstanceIdUuid()));
        formData.add("wipSetById", String.valueOf(obj.getWipSetById()));
        formData.add("wipSetByName", String.valueOf(obj.getWipSetByName()));
        formData.add("assignedById", String.valueOf(obj.getAssignedById()));
        formData.add("assignedByName", String.valueOf(obj.getAssignedByName()));
        formData.add("assignedToId", String.valueOf(obj.getAssignedToId()));
        formData.add("assignedToName", String.valueOf(obj.getAssignedToName()));
        formData.add("assignedDate", String.valueOf(obj.getAssignedDate()));
        formData.add("assignedStatus", String.valueOf(obj.getAssignedStatus()));
        formData.add("wipNotes", String.valueOf(obj.getWipNotes()));
        formData.add("assignmentNotes", String.valueOf(obj.getAssignmentNotes()));
        formData.add("assignmentStatusNotes", String.valueOf(obj.getAssignmentStatusNotes()));
        formData.add("objectInstanceIdNo", String.valueOf(obj.getObjectInstanceIdNo()));
        formData.add("state", String.valueOf(obj.getState()));

        wipOutcome = webClient.post()
            .uri(uriBuilder -> uriBuilder.path(save_wip_queue_details_url).queryParams(formData).build())
            .header("Authorization", "Bearer " + token)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<>() {
            });

        return wipOutcome.map(outcome -> {
//            MultiValueMap<String, String> formData1 = new LinkedMultiValueMap<>();
//            formData.add("taskId", String.valueOf(obj.getTaskId()));
//            formData.add("assignedToId", String.valueOf(obj.getAssignedToId()));
//            formData.add("objectid", String.valueOf(obj.getObjectId()));
//            formData.add("objectinstanceidno", String.valueOf(obj.getObjectInstanceIdNo()));
//            formData.add("wipstatusid", String.valueOf(obj.getWipStatusId()));
//            formData.add("dateNeeded", String.valueOf(obj.getDateNeeded()));

            if (outcome != null && outcome.getOutcome() == true) {
//                Mono<ServiceOutcome<TaskDetailsOutputDTO>> taskDetailsOutput = webClient.post()
//                    .uri(uriBuilder -> uriBuilder.path(update_wip_date_needed_url).queryParams(formData).build())
//                    .header("Authorization", "Bearer " + token)
//                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                    .retrieve()
//                    .bodyToMono(new ParameterizedTypeReference<ServiceOutcome<TaskDetailsOutputDTO>>() {
//                    });
//                if (outcome.getMessage().equals("WIP Queue Details already exist. Please complete it")
//                    || outcome.getMessage().equals("State of the Task is updated to Completed")
//                    || outcome.getMessage().equals("Task is already Completed, Kindly add an updated task")) {

                return Mono.just(new ServiceOutcome<WipQueueDetailsOutputDTO>(outcome.getData(), true, outcome.getMessage()));
//                } else {
//                    return taskDetailsOutput.flatMap(outcomeTask -> {
//                        if (outcomeTask != null && outcomeTask.getOutcome() == true) {
//                            return Mono.just(new ServiceOutcome<WipQueueDetailsOutputDTO>(outcome.getData(), true, "WIP_Status_Details Successfully Updated."));
//                        } else {
//                            return Mono.just(new ServiceOutcome<WipQueueDetailsOutputDTO>(null, true, "WIP_Status_Details Successfully Updated without Date_Needed."));
//                        }
//                    });
//                }
            } else {
                return Mono.just(new ServiceOutcome<WipQueueDetailsOutputDTO>(null, false, "Failed to Update WIP_Status_Details: Data or Service_Communication Error."));
            }
        }).flatMap(obj1 -> obj1);
    }
}
