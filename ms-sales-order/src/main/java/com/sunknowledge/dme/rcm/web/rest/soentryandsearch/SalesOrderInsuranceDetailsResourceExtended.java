package com.sunknowledge.dme.rcm.web.rest.soentryandsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.InsurancePricetableMap;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderInsuranceEntryParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.soentryandsearch.SalesOrderSecondaryInsuranceParameterExtendedDTO;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderInsuranceDetailsMapper;
import com.sunknowledge.dme.rcm.service.soentryandsearch.InsurancePricetableMapServiceExtended;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderInsuranceDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterServiceExtented;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Validated
@RestController
@RequestMapping("/api")
public class SalesOrderInsuranceDetailsResourceExtended {
    private final Logger log = LoggerFactory.getLogger(SalesOrderInsuranceDetailsResourceExtended.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    SalesOrderInsuranceDetailsServiceExtended salesOrderInsuranceDetailsServiceExtended;
    @Autowired
    SalesOrderMasterServiceExtented salesOrderMasterServiceExtented;
    @Autowired
    InsurancePricetableMapServiceExtended insurancePricetableMapServiceExtended;
    @Autowired
    SalesOrderInsuranceDetailsMapper mapper;

    @GetMapping("/getSOInsuranceDetailsByInsuranceUUID")
    public Mono<SalesOrderInsuranceDetails> getSOInsuranceDetailsByInsuranceUUID(
        @NotNull(message = "SalesOrder_InsuranceDetails_UUID must be provided")
        @RequestParam("sOInsuranceDetailsUUID") UUID sOInsuranceDetailsUUID) {
        //----- Implementing UUID_To_ID Bridge Method ----------
        Long id = 0L;
        if (sOInsuranceDetailsUUID != null) {
            id = salesOrderInsuranceDetailsServiceExtended.getIDByUUID(sOInsuranceDetailsUUID);
            id = id != null ? id : 0L;
        }
        return salesOrderInsuranceDetailsServiceExtended.findById(id);
    }

    @GetMapping("/getSOInsuranceDetailsBySOUUID")
    public Flux<SalesOrderInsuranceDetails> getSOInsuranceDetailsBySOUUID(
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
        return salesOrderInsuranceDetailsServiceExtended.findBySalesOrderId(id);
    }

    @GetMapping("/getSOInsuranceDetailsBySOID")
    public Mono<ServiceOutcome<SalesOrderInsuranceDetailsDTO>> getSOInsuranceDetailsBySOID(
        @NotNull(message = "Sales_Order_ID must be provided")
        @RequestParam("salesOrderID") Long salesOrderID) {
        try {
            List<SalesOrderInsuranceDetails> list = salesOrderInsuranceDetailsServiceExtended.findBySalesOrderId(salesOrderID).collectList().toFuture().get();
            if (list != null && list.size() > 0) {
                return Mono.just(new ServiceOutcome<SalesOrderInsuranceDetailsDTO>(mapper.toDto(list.get(0)), true, "Successfully Fetched."));
            } else {
                return Mono.just(new ServiceOutcome<SalesOrderInsuranceDetailsDTO>(null, false, "Data Not Found."));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(value = "saveSOInsuranceDetails", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ResponseDTO> saveSOInsuranceDetails(
        @RequestBody @Valid SalesOrderInsuranceEntryParameterDTO salesOrderInsuranceEntryParameterDTO
    ) {

        if ((salesOrderInsuranceEntryParameterDTO.getPrimaryInsurerId() != null &&
            !salesOrderInsuranceEntryParameterDTO.getPrimaryInsurerId().equals(0L) &&
            salesOrderInsuranceEntryParameterDTO.getPrimaryInsurerName() != null &&
            !salesOrderInsuranceEntryParameterDTO.getPrimaryInsurerName().equals("") &&
            salesOrderInsuranceEntryParameterDTO.getPrimaryInsurerPayPercentage() != null &&
            !salesOrderInsuranceEntryParameterDTO.getPrimaryInsurerPayPercentage().equals(0L)) ||
            (salesOrderInsuranceEntryParameterDTO.getPatientPayPercentage() != null &&
                !salesOrderInsuranceEntryParameterDTO.getPatientPayPercentage().equals(0L))) {

            SalesOrderInsuranceDetailsDTO obj = new SalesOrderInsuranceDetailsDTO();
            BeanUtils.copyProperties(salesOrderInsuranceEntryParameterDTO, obj);
            //----- Implementing UUID_To_ID Bridge Method ----------
            // ----If UUID not required, then pass UUID as null ----------
            Long soInsuranceId = null;
            if (salesOrderInsuranceEntryParameterDTO.getSalesOrderInsuranceDetailsUuid() != null) {
                soInsuranceId = salesOrderInsuranceDetailsServiceExtended.getIDByUUID(salesOrderInsuranceEntryParameterDTO.getSalesOrderInsuranceDetailsUuid());
                soInsuranceId = soInsuranceId == null ? 0L : soInsuranceId;
                obj.setSalesOrderInsuranceDetailsUuid(salesOrderInsuranceEntryParameterDTO.getSalesOrderInsuranceDetailsUuid());
            }
            obj.setSalesOrderInsuranceDetailsId(soInsuranceId);

            List<SalesOrderMaster> soList = null;
            try {
                soList = salesOrderMasterServiceExtented.getSOByUUID(salesOrderInsuranceEntryParameterDTO.getSalesOrderUUID())
                    .collectList().toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            SalesOrderMaster salesOrderMaster = null;
            if (soList == null || soList.size() == 0) {
                String message = "Sales Order does not exist.";
                return Mono.just(new ResponseDTO(false, message, new ArrayList<>()));
            } else {
                salesOrderMaster = soList.get(0);
            }
            obj.setSalesOrderId(salesOrderMaster.getSalesOrderId());
            obj.setSalesOrderNo(salesOrderMaster.getSalesOrderNo());

            //------------------- Insurance Pricetable Map -------------------------
            if (salesOrderInsuranceEntryParameterDTO.getPrimaryInsurerId() != null &&
                !salesOrderInsuranceEntryParameterDTO.getPrimaryInsurerId().equals(0L)) {
                Mono<InsurancePricetableMap> insurancePricetableMapMono = insurancePricetableMapServiceExtended.
                    findInsurancePricetableMapDetailsByInsuranceId(obj.getPrimaryInsurerId())
                    .reduce((max, current) -> current.getInsurancePricetableMapId() >
                        max.getInsurancePricetableMapId() ? current : max);
                InsurancePricetableMap insurancePricetableMap = null;
                try {
                    insurancePricetableMap = insurancePricetableMapMono.toFuture().get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
//                System.out.println("insurancePricetableMap=" + insurancePricetableMap);

                if (insurancePricetableMap != null) {
                    obj.setPriceTableId(insurancePricetableMap.getPriceTableId());
                    obj.setPriceTableName(insurancePricetableMap.getPriceTableName());
                } else {
                    String message = "Insurance is not map with any Price_Table";
                    return Mono.just(new ResponseDTO(true, message, new ArrayList()));
                }
            }
            //------------------- Insurance Pricetable Map -------------------------

            return salesOrderInsuranceDetailsServiceExtended.saveSOInsuranceDetails(obj, salesOrderInsuranceEntryParameterDTO, salesOrderMaster.getBranchId());
        } else {
            String message = "Primary_Insurance_Information or Patient_Pay_Percentage must be provided.";
            return Mono.just(new ResponseDTO(false, message, new ArrayList<>()));
        }

    }

    @PatchMapping(value = "updateSOSecondaryInsuranceInfo", consumes = {"application/json", "application/merge-patch+json"})
    public Mono<ServiceOutcome<SalesOrderInsuranceDetailsDTO>> updateSOSecondaryInsuranceInfo(@RequestBody @Valid SalesOrderSecondaryInsuranceParameterExtendedDTO inputDTO) throws ExecutionException, InterruptedException {
        if (inputDTO.getSalesOrderId() != null && inputDTO.getSalesOrderId() != 0) {
            List<SalesOrderInsuranceDetails> salesOrderInsuranceDetails = salesOrderInsuranceDetailsServiceExtended.findBySalesOrderId(inputDTO.getSalesOrderId()).collectList().toFuture().get();
            if (salesOrderInsuranceDetails.size() > 0) {
                SalesOrderInsuranceDetailsDTO existingInsData= mapper.toDto(salesOrderInsuranceDetails.get(0));
                existingInsData.setSecondaryInsurerId(inputDTO.getSecondaryInsurerId());
                existingInsData.setSecondaryInsurerName(inputDTO.getSecondaryInsurerName());
                existingInsData.setSecondaryInsurerPolicyNo(inputDTO.getSecondaryInsurerPolicyNo());
                existingInsData.setSecondaryInsurerPatientIdNumber(inputDTO.getSecondaryInsurerPatientIdNumber());
                existingInsData.setSecondaryInsurerEffectiveDate(inputDTO.getSecondaryInsurerEffectiveDate());
                existingInsData.setSecondaryInsurerVerificationStatus(inputDTO.getSecondaryInsurerVerificationStatus());
                existingInsData.setSecondaryInsurerVerificationDate(inputDTO.getSecondaryInsurerVerificationDate());
                existingInsData.setSecondaryInsurerPayPercentage(inputDTO.getSecondaryInsurerPayPercentage());
                existingInsData.setSecondaryBox10D(inputDTO.getSecondaryBox10d());
                existingInsData.setSecondaryBox19(inputDTO.getSecondaryBox19());
                existingInsData.setSecondaryBox24Ia(inputDTO.getSecondaryBox24Ia());
                existingInsData.setSecondaryBox24Ja(inputDTO.getSecondaryBox24Ja());
                existingInsData.setSecondaryBox24Jb(inputDTO.getSecondaryBox24Jb());
                existingInsData.setSecondaryIncludeBox24JbStatus(inputDTO.getSecondaryIncludeBox24JbStatus());
                existingInsData.setSecondaryIncludePayerSalesOrderStatus(inputDTO.getSecondaryIncludePayerSalesOrderStatus());
                existingInsData.setSecondaryWaitPreviousPayerBefrBillingStatus(inputDTO.getSecondaryWaitPreviousPayerBefrBillingStatus());
                existingInsData.setSecondaryPayPercentageStatus(inputDTO.getSecondaryPayPercentageStatus());
                existingInsData.setSecondaryInsuranceGroupId(inputDTO.getSecondaryInsuranceGroupId());
                existingInsData.setSecondaryInsuranceGroupName(inputDTO.getSecondaryInsuranceGroupName());
                existingInsData.setSecondaryInsurancePlanId(inputDTO.getSecondaryInsurancePlanId());
                existingInsData.setSecondaryInsurancePlanType(inputDTO.getSecondaryInsurancePlanType());
                existingInsData.setSecondaryInsurancePayerId(inputDTO.getSecondaryInsurancePayerId());
                existingInsData.setSecondaryInsuranceIndicatorCode(inputDTO.getSecondaryInsuranceIndicatorCode());
                existingInsData.setSecondaryInsurerAddressLine1(inputDTO.getSecondaryInsurerAddressLine1());
                existingInsData.setSecondaryInsurerAddressLine2(inputDTO.getSecondaryInsurerAddressLine2());
                existingInsData.setSecondaryInsurerCity(inputDTO.getSecondaryInsurerCity());
                existingInsData.setSecondaryInsurerState(inputDTO.getSecondaryInsurerState());
                existingInsData.setSecondaryInsurerZip(inputDTO.getSecondaryInsurerZip());
                existingInsData.setSecondaryInsurerContact1(inputDTO.getSecondaryInsurerContact1());
                existingInsData.setSecondaryInsurerFax(inputDTO.getSecondaryInsurerFax());
                existingInsData.setSecondaryClaimProgram(inputDTO.getSecondaryClaimProgram());
                existingInsData.setUpdatedDate(LocalDate.now());
                existingInsData.setUpdatedByName("Falguni");
                existingInsData.setUpdatedById(31L);
                existingInsData.setUpdatedDate(LocalDate.now());
                return salesOrderInsuranceDetailsServiceExtended.updateSOSecondaryInsuranceInfo(existingInsData);
            } else {
                return Mono.just(new ServiceOutcome<SalesOrderInsuranceDetailsDTO>(null, false, "Invalid Sales Order Id."));
            }
        } else {
            return Mono.just(new ServiceOutcome<SalesOrderInsuranceDetailsDTO>(null, false, "Sales Order Id should not be null."));
        }
    }
}
