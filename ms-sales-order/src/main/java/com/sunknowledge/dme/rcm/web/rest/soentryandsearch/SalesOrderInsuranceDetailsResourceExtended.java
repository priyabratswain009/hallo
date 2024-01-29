package com.sunknowledge.dme.rcm.web.rest.soentryandsearch;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import com.sunknowledge.dme.rcm.domain.InsurancePricetableMap;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderMaster;
import com.sunknowledge.dme.rcm.domain.ServiceReview.*;
import com.sunknowledge.dme.rcm.domain.coverage.CoverageInput;
import com.sunknowledge.dme.rcm.domain.coverage.CoverageOutput;
import com.sunknowledge.dme.rcm.domain.elligibility.TokenOutCome;
import com.sunknowledge.dme.rcm.service.claimssubmissiondata.TokenGenerationService;
import com.sunknowledge.dme.rcm.service.coverage.BenefitCoverageRequestServiceExtended;
import com.sunknowledge.dme.rcm.service.coverage.BenefitCoverageResponseServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.BenefitCoverageResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderInsuranceDetailsDTO;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
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
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    TokenGenerationService tokenGenerationService;
    @Autowired
    BenefitCoverageRequestServiceExtended benefitCoverageRequestServiceExtended;

    @Autowired
    BenefitCoverageResponseServiceExtended benefitCoverageResponseServiceExtended;

    @GetMapping("/getSOInsuranceDetailsByInsuranceUUID")
    public Mono<ServiceOutcome> getSOInsuranceDetailsByInsuranceUUID(
        @NotNull(message = "SalesOrder_InsuranceDetails_UUID must be provided")
        @RequestParam("sOInsuranceDetailsUUID") UUID sOInsuranceDetailsUUID) throws ExecutionException, InterruptedException {
        //----- Implementing UUID_To_ID Bridge Method ----------
        Long id = 0L;
        if (sOInsuranceDetailsUUID != null) {
            id = salesOrderInsuranceDetailsServiceExtended.getIDByUUIDReactive(sOInsuranceDetailsUUID).toFuture().get();
            id = id != null ? id : 0L;
        }
        if(id==0l){
            return Mono.just(new ServiceOutcome(new SalesOrderInsuranceDetailsDTO(),true,"Data Not found.","200"));
        }
        return salesOrderInsuranceDetailsServiceExtended.findById(id)
            .map(data-> new ServiceOutcome(data,true,"","200"))
            .switchIfEmpty(Mono.just(new ServiceOutcome(new SalesOrderInsuranceDetailsDTO(),true,"Data Not found.","200")));
    }

    @GetMapping("/getSOInsuranceDetailsBySOUUID")
    public Mono<ServiceOutcome> getSOInsuranceDetailsBySOUUID(
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
        System.out.println("Clinical Data "+ id);
        return salesOrderInsuranceDetailsServiceExtended.findBySalesOrderId(id)
            .collectList()
            .map(data-> {
                System.out.println("Clinical Data "+ data);
                return new ServiceOutcome(data.get(0), true, "", "200");
            })
            .switchIfEmpty(Mono.just(new ServiceOutcome(new SalesOrderInsuranceDetailsDTO(),true,"Data Not found.","200")));
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

            return salesOrderInsuranceDetailsServiceExtended.saveSOInsuranceDetails(obj, salesOrderInsuranceEntryParameterDTO,
                salesOrderMaster.getBranchId());
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

    @PutMapping(value = "verifySOInsuranceManually")
    public Mono<ServiceOutcome<SalesOrderInsuranceDetailsDTO>> verifySOInsuranceManually(
        @NotNull(message = "SalesOrder Insurance Details_uuid must be provided")
        @RequestParam("salesOrderInsuranceDetailsUuid") UUID salesOrderInsuranceDetailsUuid,
        @NotNull(message = "Insurance Verification Status must be provided")
        @RequestParam("insuranceVerificationStatus") String insuranceVerificationStatus,
        @NotNull(message = "Payer Level must be provided")
        @RequestParam("payerLevel") String payerLevel
    ){
        Long soInsuranceId = null;
        if (salesOrderInsuranceDetailsUuid != null) {
            try {
                soInsuranceId = salesOrderInsuranceDetailsServiceExtended.getIDByUUIDReactive(salesOrderInsuranceDetailsUuid).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            soInsuranceId = soInsuranceId == null ? 0L : soInsuranceId;
        }
        if(soInsuranceId != null && soInsuranceId>0) {
            return salesOrderInsuranceDetailsServiceExtended.verifySOInsuranceManually(soInsuranceId,insuranceVerificationStatus,payerLevel)
                .map(data -> {
                    System.out.println("======SalesOrderInsuranceDetails====== "+data);
                    return new ServiceOutcome<SalesOrderInsuranceDetailsDTO>(data,Boolean.TRUE,"Data Updated Successfully");
                });
        }else{
            return Mono.just(new ServiceOutcome(null,false,""));
        }
    }

    @PutMapping(value = "verifySOInsuranceAutomatic")
    public Mono<ServiceOutcome<SalesOrderInsuranceDetailsDTO>> verifySOInsuranceAutomatic(
        @NotNull(message = "SalesOrderId must be provided")
        @RequestParam("soId") Long soId,
        @NotNull(message = "Payer Level must be provided")
        @RequestParam("payerLevel") String payerLevel
    ){
        SalesOrderInsuranceDetails salesOrderInsuranceDetails=null;
        SalesOrderMasterDTO salesOrderMasterDTO = null;
        String accessToken = null;
        if (soId != null) {
            try {
                salesOrderInsuranceDetails = salesOrderInsuranceDetailsServiceExtended.findBySOId(soId).toFuture().get();
                ServiceOutcome serviceOutcome = salesOrderMasterServiceExtented.getSOBySoId(soId).toFuture().get();
                if(serviceOutcome.getOutcome()){
                    salesOrderMasterDTO = (SalesOrderMasterDTO) serviceOutcome.getData();
                }
                TokenOutCome routcome = tokenGenerationService.getTokenMono().toFuture().get();

                if(routcome.getOutcome()){
                    accessToken = routcome.getTokenResponseOutput().getAccessToken();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        }

        if(salesOrderInsuranceDetails != null && salesOrderMasterDTO != null) {
            return salesOrderInsuranceDetailsServiceExtended.verifySOInsuranceAutomatic(salesOrderMasterDTO,salesOrderInsuranceDetails,payerLevel,accessToken)
                .map(data -> {
                    System.out.println("======SalesOrderInsuranceDetails====== "+data);
                    return new ServiceOutcome<SalesOrderInsuranceDetailsDTO>(data,Boolean.TRUE,"Data Updated Successfully");
                });
        }else{
            return Mono.just(new ServiceOutcome(null,false,""));
        }
    }

    @PostMapping("/getPatientBenefitCoverage")
    public Mono<ServiceOutcome<BenefitCoverageResponse>> patientBenefitCoverage(
        @NotNull(message = "SalesOrderId must be provided")
        @RequestParam("soId") Long soId) {

        SalesOrderInsuranceDetails salesOrderInsuranceDetails=null;
        SalesOrderMasterDTO salesOrderMasterDTO = null;
        if (soId != null) {
            try {
                salesOrderInsuranceDetails = salesOrderInsuranceDetailsServiceExtended.findBySOId(soId).toFuture().get();
                ServiceOutcome serviceOutcome = salesOrderMasterServiceExtented.getSOBySoId(soId).toFuture().get();
                if(serviceOutcome.getOutcome()){
                    salesOrderMasterDTO = (SalesOrderMasterDTO) serviceOutcome.getData();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        }

        Mono<ServiceOutcome<BenefitCoverageResponse>> resultOutcomeJson = null;
        CoverageInput objCoverageInput = new CoverageInput();
        objCoverageInput.setPayerId(salesOrderInsuranceDetails.getPrimaryInsurancePayerId());
        objCoverageInput.setProviderLastName("");
        objCoverageInput.setProviderType("Billing");
        objCoverageInput.setProviderNpi(salesOrderMasterDTO.getBillingProviderNpi());
        objCoverageInput.setProviderCity(salesOrderMasterDTO.getBillingProviderCity());
        objCoverageInput.setProviderState(salesOrderMasterDTO.getBillingProviderState());
        objCoverageInput.setProviderZipCode(salesOrderMasterDTO.getBillingProviderZipCode());
        objCoverageInput.setAsOfDate(String.valueOf(LocalDate.now()));
        objCoverageInput.setServiceType("DM");
        objCoverageInput.setMemberId(salesOrderInsuranceDetails.getPrimaryInsurerPolicyNo());
        objCoverageInput.setPatientLastName(salesOrderMasterDTO.getPatientLastName());
        objCoverageInput.setPatientFirstName(salesOrderMasterDTO.getPatientFirstName());
        objCoverageInput.setPatientBirthDate(salesOrderMasterDTO.getPatientDob().toString());
        objCoverageInput.setPatientGender(salesOrderMasterDTO.getPatientGender());
        objCoverageInput.setPatientState(salesOrderMasterDTO.getPatientBillingState());
        objCoverageInput.setSubscriberRelationship(salesOrderMasterDTO.getRelationship());
        System.out.println("objCoverageInput");
        try {
            resultOutcomeJson = benefitCoverageRequestServiceExtended.getSOBenefitCoverage(objCoverageInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultOutcomeJson;
    }

    @GetMapping("/getAllPatientBenefitCoverage")
    public Mono<ServiceOutcome> getAllPatientBenefitCoverage(){
        return benefitCoverageResponseServiceExtended.getAllPatientBenefitCoverage().collectList()
            .map(data -> {
                return Mono.just(new ServiceOutcome<>(data,true,"Data Fetched Successfully"));
            }).flatMap(x->x);
    }

    @GetMapping("/getPatientBenefitCoverageByMemberId")
    public Mono<ServiceOutcome<List<String>>> getPatientBenefitCoverageByMemberId(
        @NotNull(message = "MemberId must be provided")
        @RequestParam("memberId") String memberId
    ){
        return benefitCoverageResponseServiceExtended.getPatientBenefitCoverageByMemberId(memberId).collectList()
            .map(data -> {
                return Mono.just(new ServiceOutcome<List<String>>(data,true,"Data Fetched Successfully"));
            }).flatMap(x->x)
            .switchIfEmpty(Mono.just(new ServiceOutcome<>(null,false,"Data not found")));
    }

    @PostMapping("/getServiceReviewById")
    public Mono<ServiceOutcome<String>> getServiceReviewById(@RequestParam("id") Long id) {
        return salesOrderInsuranceDetailsServiceExtended.getServiceReviewById(id);
    }

    @PostMapping("/updateCoverageVerificationStatus")
    public Mono<ServiceOutcome> updateCoverageVerificationStatus(@RequestParam("salesOrderInsuranceDetailsUuid") UUID salesOrderInsuranceDetailsUuid,
                                                                 @NotNull(message = "Coverage Verification Status must be provided")
                                                                 @RequestParam("coverageVerificationStatus") String coverageVerificationStatus){
        Long soInsuranceId = null;
        if (salesOrderInsuranceDetailsUuid != null) {
            try {
                soInsuranceId = salesOrderInsuranceDetailsServiceExtended.getIDByUUIDReactive(salesOrderInsuranceDetailsUuid).toFuture().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            soInsuranceId = soInsuranceId == null ? 0L : soInsuranceId;
        }
        if(soInsuranceId != null && soInsuranceId>0) {
            return salesOrderInsuranceDetailsServiceExtended.updateCoverageVerificationStatus(soInsuranceId,coverageVerificationStatus.toUpperCase().trim())
                .map(data -> {
                    System.out.println("======SalesOrderInsuranceDetails====== "+data);
                    return new ServiceOutcome<SalesOrderInsuranceDetailsDTO>(data,Boolean.TRUE,"Data Updated Successfully");
                });
        }else{
            return Mono.just(new ServiceOutcome(null,false,""));
        }
    }
}
