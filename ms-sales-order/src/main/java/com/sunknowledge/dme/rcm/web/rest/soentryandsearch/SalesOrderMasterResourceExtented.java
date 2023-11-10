package com.sunknowledge.dme.rcm.web.rest.soentryandsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.service.delivery.DeliverySalesOrderService;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.ItemInventoryStatusExtendedDTO;
import com.sunknowledge.dme.rcm.service.dto.delivery.validateDeliveryInitiationSOItemDetailsResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderEntryParameterDTO;
import com.sunknowledge.dme.rcm.service.par.PriorAuthorizationService;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderItemDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterServiceExtented;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Validated
@RestController
@RequestMapping("/api")
public class SalesOrderMasterResourceExtented {
    private final Logger log = LoggerFactory.getLogger(SalesOrderMasterResourceExtented.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    @Qualifier("salesOrderMasterServiceExtentedImpl")
    SalesOrderMasterServiceExtented salesOrderMasterServiceExtented;

    @Autowired
    private DeliverySalesOrderService deliverySalesOrderService;

    @Autowired
    PriorAuthorizationService priorAuthorizationService;
    @Autowired
    SalesOrderItemDetailsServiceExtended salesOrderItemDetailsServiceExtended;

    @GetMapping("/getSOByUUID")
    public Flux<SalesOrderMaster> getSOByUUID(
        @NotNull(message = "SalesOrder_UUID must be provided")
        @RequestParam("salesOrderUUID") UUID salesOrderUUID){
        return salesOrderMasterServiceExtented.getSOByUUID(salesOrderUUID);
    }

    @PatchMapping(value = "saveSOMasterDetails", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ResponseDTO> saveSOMasterDetails(
        @RequestBody @Valid SalesOrderEntryParameterDTO salesOrderEntryParameterDTO
    ) {
        if(salesOrderEntryParameterDTO.getPatientDod() == null) {
            SalesOrderMasterDTO obj = new SalesOrderMasterDTO();
            BeanUtils.copyProperties(salesOrderEntryParameterDTO, obj);
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long soId = null;
            if (salesOrderEntryParameterDTO.getSalesOrderMasterUuid() != null) {
                try {
                    soId = salesOrderMasterServiceExtented.getIDByUUID(salesOrderEntryParameterDTO.getSalesOrderMasterUuid()).toFuture().get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
                soId = soId == null ? 0L : soId;
                obj.setSalesOrderMasterUuid(salesOrderEntryParameterDTO.getSalesOrderMasterUuid());
            }
            obj.setSalesOrderId(soId);

            return salesOrderMasterServiceExtented.saveSOMasterDetails(obj, salesOrderEntryParameterDTO);
        }
        else{
            return Mono.just(new ResponseDTO(Boolean.FALSE,"Dead patient is not allow for create Sales Order",new ArrayList<>()));
        }
    }

    @PostMapping("/patientDateOfDeathIncorporation")
    public Mono<ServiceOutcome> patientDateOfDeathIncorporation(@NotNull(message = "Patient_Id must be provided")
                                                                    @RequestParam("patientId") Long patientId,
                                                                @NotNull(message = "Patient_Dod must be provided")
                                                                @RequestParam("patientDod") LocalDate patientDod) throws ExecutionException, InterruptedException {
        //LocalDate localDate = LocalDate.parse(patientDod);
        return salesOrderMasterServiceExtented.patientDateOfDeathIncorporation(patientId,patientDod);
    }

    @GetMapping("/getSOById")
    public Mono<ServiceOutcome> getSOById(
        @NotNull(message = "SalesOrder_Id must be provided")
        @RequestParam("soId") Long soId){
        return salesOrderMasterServiceExtented.getSOBySoId(soId);
    }

    @PostMapping("/updateHoldStatus")
    public Mono<ServiceOutcome> updateHoldStatus(@RequestBody List<Long> soIds){
        List<SalesOrderMaster> salesOrderMasters = null;
        try {
             salesOrderMasters = salesOrderMasterServiceExtented.findAllById(soIds).collectList().toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        List<SalesOrderMaster> salesOrderMasters1 = salesOrderMasterServiceExtented.updateHoldStatus(salesOrderMasters);

        return Mono.just(new ServiceOutcome<>(salesOrderMasters1,salesOrderMasters1.size()>0?true:false,salesOrderMasters1.size()>0?"Fetched Successfully":"Data Not Found."));
    }

    @ApiOperation(value = "Make SO Commited With Validate Delivery Initiation Subroutine Validation")
    @PostMapping(value="/makeSOCommited")
    public Mono<ServiceOutcome> makeSOCommited(@RequestParam("salesOrderMasterUuid") UUID salesOrderMasterUuid){
        Mono<ServiceOutcome> output = null;
        try {
            Long soId = 0L;
            soId = salesOrderMasterServiceExtented.getIDByUUID(salesOrderMasterUuid).toFuture().get();
            if(soId == null || soId == 0L){
                return Mono.just(new ServiceOutcome<>(null, false, "Sales Order Master Uuid Does Not Exist."));
            }

            log.info("==========validateDeliveryInitiation Serice Code Starts============");
            List<validateDeliveryInitiationSOItemDetailsResponseDTO> soItemDetailsList = deliverySalesOrderService.getSoItemDetailsList(soId).collectList().toFuture().get();
            if(soItemDetailsList.size() > 0){
                SalesOrderInsuranceDetails soInsuranceDetails = deliverySalesOrderService.getSoInsuranceDetailsBySOId(soId).toFuture().get();

                log.info("=========soItemDetailsList========="+soItemDetailsList);
                log.info("=========soInsuranceDetails========="+soInsuranceDetails);
                List<Long> parIdList = new ArrayList<>();
                List<Long> cmnIdList = new ArrayList<>();
                List<ItemInventoryStatusExtendedDTO> itemInventoryStatusList = new ArrayList<>();
                for(validateDeliveryInitiationSOItemDetailsResponseDTO eachObj: soItemDetailsList) {
                    String hcpcsNo = eachObj.getSalesOrderDetailsProcCode() != null ? eachObj.getSalesOrderDetailsProcCode() : "";
                    String itemNo = eachObj.getSalesOrderItemNumber() != null ? eachObj.getSalesOrderItemNumber() : "";
                    if(eachObj.getSalesOrderDetailsOriginalDos() == null || eachObj.getSalesOrderDetailsOriginalDos().trim().equals("")){
                        return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Sales Order Details Original Dos should not be null/empty."));
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String orgDos = eachObj.getSalesOrderDetailsOriginalDos() != null ? eachObj.getSalesOrderDetailsOriginalDos() : null;
                    LocalDateTime dateTime = LocalDateTime.parse(orgDos, formatter);
                    LocalDate dos = dateTime.toLocalDate();

                    Long priceTableId = eachObj.getPrimaryInsurerPriceTableId() != null ? eachObj.getPrimaryInsurerPriceTableId() : null;
                    log.info("hcpcsNo::"+hcpcsNo+", itemNo::"+itemNo+", dos::"+dos+" ,priceTableId::"+priceTableId);
                    if (!hcpcsNo.trim().equals("") && !itemNo.trim().equals("") && dos != null && priceTableId != null) {
                        String isParRequired = priorAuthorizationService.isPARRequiredReactive(hcpcsNo, itemNo, dos, priceTableId).toFuture().get();
                        log.info("========Step-2::isPARRequired========" + isParRequired+", Par Id::"+eachObj.getParId()+", Par No::"+eachObj.getParNo());
                        if (isParRequired != null && isParRequired.trim().equals("Y")) {
                            if ((eachObj.getParId() == null || eachObj.getParId().trim().equals("") || eachObj.getParId().trim().equals("0")) && (eachObj.getParNo() == null || eachObj.getParNo().trim().equals(""))) {
                                return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Par Id and Par No  should not be null/empty."));
                            }
                        }
                    }else{
                        return Mono.justOrEmpty(new ServiceOutcome<>(null, false, "Hcpcs No, Item No, DOS and Price Table Id  should not be null/empty."));
                    }

                    Long parId = eachObj.getParId()!=null ? Long.valueOf(eachObj.getParId()) : 0;
                    Long cmnId = eachObj.getCmnId()!=null ? eachObj.getCmnId() : 0;
                    if(parId!= 0L)
                        parIdList.add(parId);

                    if(cmnId!=0L)
                        cmnIdList.add(cmnId);
                }

                if(soItemDetailsList.size() > 0 && soInsuranceDetails.getSalesOrderInsuranceDetailsId()!= null){
                    ServiceOutcome validationDeliveryInitiationOutput = deliverySalesOrderService.validateDeliveryInitiation(soId, soItemDetailsList, soInsuranceDetails).toFuture().get();
                    ServiceOutcome deliverableAddr = null;
                    if(validationDeliveryInitiationOutput.getOutcome()){
                        deliverableAddr = deliverySalesOrderService.getDeliveryAddressDelivarableOrNot(soId).toFuture().get();
                    }
                    if(validationDeliveryInitiationOutput.getOutcome()){
                        System.out.println("==========validateDeliveryInitiation Serice Code Ends============"+validationDeliveryInitiationOutput);
                        //CMN must be logged.
                        List<String> cmnStatus = salesOrderMasterServiceExtented.getCMNStatusByCMNIds(cmnIdList).collectList().toFuture().get();
                        //If PAR available, so PAR must be logged.
                        List<String> parStatus = new ArrayList<>();
                        if(parIdList.size()>0)
                            parStatus = salesOrderMasterServiceExtented.getParStatusByParId(parIdList).collectList().toFuture().get();

                        log.info("cmnStatus:: "+cmnStatus+", parStatus:: "+parStatus);
                        for(validateDeliveryInitiationSOItemDetailsResponseDTO eachObj: soItemDetailsList) {
                            //To Check Eligibility of Item Inventory
                            Long itemId = eachObj.getSalesOrderDetailsItemId() != null && eachObj.getSalesOrderDetailsItemId() != 0 ? eachObj.getSalesOrderDetailsItemId() : 0;
                            Long itemLocationId = eachObj.getItemLocationId() != null && eachObj.getItemLocationId() != 0 ? eachObj.getItemLocationId() : 0;
                            Long itemQty = eachObj.getSalesOrderDetailsQty() != null && eachObj.getSalesOrderDetailsQty() != 0 ? eachObj.getSalesOrderDetailsQty() : 0;
                            ItemInventoryStatusExtendedDTO itemInventoryStatusObj = salesOrderMasterServiceExtented.getItemInventoryStatusData(itemId, itemLocationId).toFuture().get();
                            if(itemInventoryStatusObj.getItemInventoryStatusId()!=null){
                                itemInventoryStatusObj.setItemQty(itemQty);
                                itemInventoryStatusList.add(itemInventoryStatusObj);
                            }
                        }
                        log.info("==========itemInventoryStatusList==========="+itemInventoryStatusList);
                        output = salesOrderMasterServiceExtented.makeSOCommited(soId,soItemDetailsList, cmnStatus, parStatus, itemInventoryStatusList, deliverableAddr);
                    }else{
                        return Mono.just(new ServiceOutcome<>(null, false, validationDeliveryInitiationOutput.getMessage()));
                    }
                }else{
                    return Mono.just(new ServiceOutcome<>(null, false, "Invalid Input."));
                }
            }
            else{
                return Mono.just(new ServiceOutcome<>(null, false, "At least one Sales Order Item must be added."));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }


    @PostMapping("/updateSalesOrderedStatusAsDelivered")
    public Mono<ServiceOutcome> updateSalesOrderedStatusAsDelivered(@RequestParam("salesOrderUuid") UUID salesOrderUuid){
        Long salesOrderId = 0L;
        String soNo = null;
        Flux<SalesOrderItemDetails> salesOrderItemDetailsFlux = null;
        if (Objects.nonNull(salesOrderUuid)) {
            try {
                salesOrderId = salesOrderMasterServiceExtented.getIDByUUID(salesOrderUuid).toFuture().get();
                if(salesOrderId>0) {
                    ServiceOutcome serviceOutcome = salesOrderMasterServiceExtented.getSOBySoId(salesOrderId).toFuture().get();
                    if(serviceOutcome.getOutcome()){
                        SalesOrderMasterDTO salesOrderMasterDTO = (SalesOrderMasterDTO) serviceOutcome.getData();
                        soNo = salesOrderMasterDTO.getSalesOrderNo();
                    }
                }
                salesOrderItemDetailsFlux = salesOrderItemDetailsServiceExtended.findBySalesOrderId(salesOrderId);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            salesOrderId = salesOrderId != null ? salesOrderId : 0L;
        }
        if (!salesOrderId.equals(0))
            return salesOrderMasterServiceExtented.updateSalesOrderedStatusAsDelivered(salesOrderId,soNo,salesOrderItemDetailsFlux);
        else
            return Mono.just(new ServiceOutcome(null, false, ""));

    }

    // ----------------------- Sales Order Notes -------------------------------
    @PostMapping("/addSalesOrderNotes")
    public Mono<ServiceOutcome> addSalesOrderNotes(@NotNull(message = "SO_UUID must be provided")
                                                   @RequestParam("soUuid") UUID soUuid,
                                                   @NotNull(message = "SO_Note must be provided")
                                                   @RequestParam("soNote") String soNote) throws ExecutionException, InterruptedException {
        //LocalDate localDate = LocalDate.parse(patientDod);

        Long soId = 0L;
        soId = salesOrderMasterServiceExtented.getIDByUUID(soUuid).toFuture().get();
        if (soId == null || soId == 0L) {
            return Mono.just(new ServiceOutcome<>(null, false, "Sales Order Does Not Exist."));
        }
        return salesOrderMasterServiceExtented.addSalesOrderNotes(soId, soNote);
    }

    @GetMapping("/getSalesOrderNotes")
    public Mono<ServiceOutcome<JSONArray>> getSalesOrderNotes(
        @NotNull(message = "Sales_Order_UUID must be provided")
        @RequestParam("salesOrderUUID") UUID salesOrderUUID) {

        Long soId = 0L;
        try {
            soId = salesOrderMasterServiceExtented.getIDByUUID(salesOrderUUID).toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        if (soId == null || soId == 0L) {
            return Mono.just(new ServiceOutcome<>(null, false, "Sales Order Does Not Exist."));
        }
        return salesOrderMasterServiceExtented.getSalesOrderNotes(soId);
    }
    // ----------------------- Sales Order Notes -------------------------------
}
