package com.sunknowledge.dme.rcm.web.rest.soentryandsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.DeliveryAbnData;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.dto.soItemDetails.ItemDefaultVendorResponseDTO;
import com.sunknowledge.dme.rcm.dto.soItemDetailsAndClicnicalAndInsurance.SoItemDetailsAndClinicalAndInsuranceResponseData;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderItemDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.po.RemoveDropshipParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderItemDetailsEntryParameterDTO;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderItemDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterServiceExtented;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Validated
@RestController
@RequestMapping("/api")
public class SalesOrderItemDetailsResourceExtended {
    private final Logger log = LoggerFactory.getLogger(SalesOrderItemDetailsResourceExtended.class);
    @Autowired
    SalesOrderItemDetailsServiceExtended salesOrderItemDetailsServiceExtended;
    @Autowired
    SalesOrderMasterServiceExtented salesOrderMasterServiceExtented;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @GetMapping("/getSOItemDetailsBySOItemDetailsUUID")
    public Mono<SalesOrderItemDetails> getSOItemDetailsBySOItemDetailsUUID(
        @NotNull(message = "SalesOrder_ItemDetails_UUID must be provided")
        @RequestParam("sOItemDetailsUUID") UUID sOItemDetailsUUID) {
        //----- Implementing UUID_To_ID Bridge Method ----------
        Long id = 0L;
        if (sOItemDetailsUUID != null) {
            try {
                id = salesOrderItemDetailsServiceExtended.getIDByUUID(sOItemDetailsUUID).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            id = id != null ? id : 0L;
        }
        return salesOrderItemDetailsServiceExtended.findById(id);
    }

    @GetMapping("/getSOItemDetailsBySOUUID")
    public Flux<SalesOrderItemDetails> getSOItemDetailsBySOUUID(
        @NotNull(message = "SalesOrder_UUID must be provided")
        @RequestParam("salesOrderUUID") UUID salesOrderUUID) {
        //----- Implementing UUID_To_ID Bridge Method ----------
        Long id = 0L;
        if (salesOrderUUID != null) {
            try {
                id = salesOrderMasterServiceExtented.getIDByUUID(salesOrderUUID).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            id = id != null ? id : 0L;
        }
        return salesOrderItemDetailsServiceExtended.findBySalesOrderId(id);
    }

    @ApiOperation(value = "Create ABN Data For Sales Order Item Details")
    @PostMapping("/createABNForSalesOrderItemDetails")
    public Mono<ServiceOutcome> createABNForSalesOrderItemDetails(@RequestParam("soid") Long soid,
                                                                  @RequestParam("item_proc") String item_proc,
                                                                  @RequestParam("abn_reason") String abn_reason,
                                                                  @RequestParam("abn_modifier_1") String abn_modifier_1,
                                                                  @RequestParam("abn_modifier_2") String abn_modifier_2,
                                                                  @RequestParam("uid") Long uid,
                                                                  @RequestParam("uname") String uname,
                                                                  @RequestParam("soItemDetailsId") Long soItemDetailsId) throws Exception {

        ServiceOutcome<List<DeliveryAbnData>> serviceOutcome;
        Mono<ServiceOutcome> outcome;
        serviceOutcome = salesOrderItemDetailsServiceExtended.getabnData(soid, item_proc);
        if (serviceOutcome.getData() != null && serviceOutcome.getData().size() > 0) {
            //Logic for tagged
            outcome = salesOrderItemDetailsServiceExtended.abnTagging(soItemDetailsId, serviceOutcome.getData().get(0).getAbnNumber());
        } else {
            outcome = salesOrderItemDetailsServiceExtended.abnCreationAndTagging(soid, item_proc, abn_reason, abn_modifier_1, abn_modifier_2, uid, uname, soItemDetailsId);
        }
        return outcome;
    }

    @PatchMapping(value = "saveSOItemDetails", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ResponseDTO> saveSOItemDetails(
        @RequestBody @Valid SalesOrderItemDetailsEntryParameterDTO salesOrderItemDetailsEntryParameterDTO
    ) {
        SalesOrderItemDetailsDTO obj = new SalesOrderItemDetailsDTO();
        BeanUtils.copyProperties(salesOrderItemDetailsEntryParameterDTO, obj);
        //----- SO Item UUID ----------
        Long soItemId = null;
        if (salesOrderItemDetailsEntryParameterDTO.getSalesOrderItemDetailsUuid() != null) {
            try {
                soItemId = salesOrderItemDetailsServiceExtended.getIDByUUID(salesOrderItemDetailsEntryParameterDTO.getSalesOrderItemDetailsUuid()).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            soItemId = soItemId == null ? 0L : soItemId;
            obj.setSalesOrderItemDetailsUuid(salesOrderItemDetailsEntryParameterDTO.getSalesOrderItemDetailsUuid());
        }
        obj.setSalesOrderItemDetailsId(soItemId);
        //----- SO Item UUID ----------

        // ----SO Master UUID----------
        Long soId = null;
        if (salesOrderItemDetailsEntryParameterDTO.getSalesOrderUUID() != null) {
            try {
                soId = salesOrderMasterServiceExtented.getIDByUUID(salesOrderItemDetailsEntryParameterDTO.getSalesOrderUUID()).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            soId = soId == null ? 0L : soId;
        }
        obj.setSalesOrderId(soId);
        // ----SO Master UUID----------
        System.out.println("So Item Id: " + soId);

        //--------------- Set default vendor --------------------
        System.out.println("Outside Try: Default Vendor ....");
        ServiceOutcome<ItemDefaultVendorResponseDTO> defaultVendor = null;
        try {
            System.out.println("Before: Default Vendor Method Calling .......");
            defaultVendor = salesOrderItemDetailsServiceExtended.getItemDefaultVendorByItemId(obj.getSalesOrderDetailsItemId()).toFuture().get();
            System.out.println("After: Default Vendor Method Calling .......");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (defaultVendor.getOutcome() == true && defaultVendor.getData() != null)
            obj.setSalesOrderDetailsDefaultVendor(defaultVendor.getData().getTitle());
        System.out.println("After getting default vendor: " + defaultVendor);
        //--------------- Set default vendor --------------------

        //-------- Get Sales Order, Clinical and Insurance ------------------
        SoItemDetailsAndClinicalAndInsuranceResponseData fetchMasterClinicalIns = null;
        ServiceOutcome<SoItemDetailsAndClinicalAndInsuranceResponseData> data = null;
        try {
            System.out.println("Before Call: checkDataAvailabilityOfSOMasterAndClinicalAndInsurance");
            data = salesOrderItemDetailsServiceExtended
                .checkDataAvailabilityOfSOMasterAndClinicalAndInsurance(obj.getSalesOrderId()).toFuture().get();
            System.out.println("After Call: checkDataAvailabilityOfSOMasterAndClinicalAndInsurance");
            System.out.println("After Call: Data=" + data);
            if (data != null && data.getOutcome())
                fetchMasterClinicalIns = data.getData();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //-------- Get Sales Order, Clinical and Insurance ------------------

        if (data != null && fetchMasterClinicalIns != null) {
            if (data != null && data.getOutcome()) {
                return salesOrderItemDetailsServiceExtended.saveSOItemDetails(obj, salesOrderItemDetailsEntryParameterDTO, fetchMasterClinicalIns);
            } else {
                return Mono.just(new ResponseDTO(data.getOutcome(), data.getMessage(), List.of(data.getData())));
            }
        } else {
            return Mono.just(new ResponseDTO(false, "Sales Order Not Found", new ArrayList()));
        }
    }

    @PatchMapping(value = "/saveSOItemDetailsWithDropship", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ResponseDTO> saveSOItemDetailsWithDropship(
        @RequestBody @Valid SalesOrderItemDetailsEntryParameterDTO salesOrderItemDetailsEntryParameterDTO
    ) {

        SalesOrderItemDetailsDTO obj = new SalesOrderItemDetailsDTO();
        BeanUtils.copyProperties(salesOrderItemDetailsEntryParameterDTO, obj);
        //----- SO Item UUID ----------
        Long soItemId = null;
        if (salesOrderItemDetailsEntryParameterDTO.getSalesOrderItemDetailsUuid() != null) {
            try {
                soItemId = salesOrderItemDetailsServiceExtended.getIDByUUID(salesOrderItemDetailsEntryParameterDTO.getSalesOrderItemDetailsUuid()).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            soItemId = soItemId == null ? 0L : soItemId;
            obj.setSalesOrderItemDetailsUuid(salesOrderItemDetailsEntryParameterDTO.getSalesOrderItemDetailsUuid());
        }
        obj.setSalesOrderItemDetailsId(soItemId);
        //----- SO Item UUID ----------

        // ----SO Master UUID----------
        Long soId = null;
        if (salesOrderItemDetailsEntryParameterDTO.getSalesOrderUUID() != null) {
            try {
                soId = salesOrderMasterServiceExtented.getIDByUUID(salesOrderItemDetailsEntryParameterDTO.getSalesOrderUUID()).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            soId = soId == null ? 0L : soId;
        }
        obj.setSalesOrderId(soId);
        // ----SO Master UUID----------
        System.out.println("So Item Id: " + soId);

        //--------------- Set default vendor --------------------
        System.out.println("Outside Try: Default Vendor ....");
        ServiceOutcome<ItemDefaultVendorResponseDTO> defaultVendor = null;
        try {
            System.out.println("Before: Default Vendor Method Calling .......");
            defaultVendor = salesOrderItemDetailsServiceExtended.getItemDefaultVendorByItemId(obj.getSalesOrderDetailsItemId()).toFuture().get();
            System.out.println("After: Default Vendor Method Calling .......");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (defaultVendor.getOutcome() == true && defaultVendor.getData() != null)
            obj.setSalesOrderDetailsDefaultVendor(defaultVendor.getData().getTitle());
        System.out.println("After getting default vendor: " + defaultVendor);
        //--------------- Set default vendor --------------------

        //-------- Get Sales Order, Clinical and Insurance ------------------
        SoItemDetailsAndClinicalAndInsuranceResponseData fetchMasterClinicalIns = null;
        ServiceOutcome<SoItemDetailsAndClinicalAndInsuranceResponseData> data = null;
        try {
            System.out.println("Before Call: checkDataAvailabilityOfSOMasterAndClinicalAndInsurance");
            data = salesOrderItemDetailsServiceExtended
                .checkDataAvailabilityOfSOMasterAndClinicalAndInsurance(obj.getSalesOrderId()).toFuture().get();
            System.out.println("After Call: checkDataAvailabilityOfSOMasterAndClinicalAndInsurance");
            System.out.println("After Call: Data=" + data);
            if (data != null && data.getOutcome())
                fetchMasterClinicalIns = data.getData();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //-------- Get Sales Order, Clinical and Insurance ------------------

        if (data != null && fetchMasterClinicalIns != null) {
            if (data != null && data.getOutcome()) {
                if (salesOrderItemDetailsEntryParameterDTO.getSalesOrderDetailsSaleType().equalsIgnoreCase("Purchase")
                    && salesOrderItemDetailsEntryParameterDTO.getIsResupplyType().equalsIgnoreCase("Y")
                    && (salesOrderItemDetailsEntryParameterDTO.getFrequencyCount() == null || salesOrderItemDetailsEntryParameterDTO.getFrequencyCount() == 0
                    || salesOrderItemDetailsEntryParameterDTO.getFrequencyInterval() == null || salesOrderItemDetailsEntryParameterDTO.getFrequencyInterval() == 0)) {
                    return Mono.just(new ResponseDTO(false, "Frequency Count and Frequency Interval must be provided.", new ArrayList()));
                } else {
                    if (salesOrderItemDetailsEntryParameterDTO.getSalesOrderDetailsSaleType().equalsIgnoreCase("Rental")
                        && salesOrderItemDetailsEntryParameterDTO.getIsResupplyType().equalsIgnoreCase("Y")) {
                        return Mono.just(new ResponseDTO(false, "Item of type resupply is not allow for sale type 'Rental'.", new ArrayList()));
                    } else {
                        return salesOrderItemDetailsServiceExtended.saveSOItemDetailsWithDropship(obj, salesOrderItemDetailsEntryParameterDTO, fetchMasterClinicalIns);
                    }
                }
            } else {
                return Mono.just(new ResponseDTO(data.getOutcome(), data.getMessage(), List.of(data.getData())));
            }
        } else {
            return Mono.just(new ResponseDTO(false, "Sales Order Not Found", new ArrayList()));
        }

    }

    @GetMapping("/removeSalesOrderItemDetails")
    public Mono<ServiceOutcome> removeSalesOrderItemDetails(RemoveDropshipParameterDTO removeDropshipParameterDTO) throws ExecutionException, InterruptedException {
        return salesOrderItemDetailsServiceExtended.removeSalesOrderItemDetails(removeDropshipParameterDTO);
    }

//    @GetMapping("/soItemInventoryTransactionUpdate")
//    public Mono<String> soItemInventoryTransactionUpdate(SOItemInventoryTransactionDTO soItemInventoryTransactionDTO) throws ExecutionException, InterruptedException {
//        return salesOrderItemDetailsServiceExtended.soItemInventoryTransactionUpdate(soItemInventoryTransactionDTO);
//    }

    /**
     * This API will help to D-Link Single/Multiple Sales Order Item for CMN.
     *
     * @param cmnId
     * @param salesOrderUuid
     * @param salesOrderDetailsItemId
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/dLinkForCmnItems")
    public Mono<ServiceOutcome> dLinkForCmnItems(Long cmnId, UUID salesOrderUuid, String salesOrderDetailsItemId) throws ExecutionException, InterruptedException {

        Long salesOrderId = 0L;
        if (Objects.nonNull(salesOrderUuid)) {
            try {
                salesOrderId = salesOrderMasterServiceExtented.getIDByUUID(salesOrderUuid).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            salesOrderId = salesOrderId != null ? salesOrderId : 0L;
        }
        if (!salesOrderId.equals(0) && Objects.nonNull(cmnId))
            return salesOrderItemDetailsServiceExtended.dLinkForCmnItems(cmnId, salesOrderId, salesOrderDetailsItemId);
        else
            return Mono.just(new ServiceOutcome(null, false, " Please Check Cmn_Id OR Sales_Order_UUID : " + cmnId + " or " + salesOrderUuid));
    }

    /**
     * This API will help to Link Single/Multiple Sales Order Item for CMN.
     *
     * @param cmnId
     * @param salesOrderUuid
     * @param salesOrderDetailsItemId
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/linkForCmnItems")
    public Mono<ServiceOutcome> linkForCmnItems(Long cmnId, UUID salesOrderUuid, String salesOrderDetailsItemId) throws ExecutionException, InterruptedException {

        Long salesOrderId = 0L;
        if (Objects.nonNull(salesOrderUuid)) {
            try {
                salesOrderId = salesOrderMasterServiceExtented.getIDByUUID(salesOrderUuid).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            salesOrderId = salesOrderId != null ? salesOrderId : 0L;
        }
        if (!salesOrderId.equals(0) && Objects.nonNull(cmnId))
            return salesOrderItemDetailsServiceExtended.linkForCmnItems(cmnId, salesOrderId, salesOrderDetailsItemId);
        else
            return Mono.just(new ServiceOutcome(null, false, " Please Check Cmn_Id OR Sales_Order_UUID : " + cmnId + " or " + salesOrderUuid));
    }
}
