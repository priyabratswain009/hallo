package com.sunknowledge.dme.rcm.service.dto.par;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderInsurancePriceDetails {
    private Long salesOrderInsuranceDetailsId;
    private Long salesOrderId;
    private Long patientId;
    private Long primaryInsurerId;
    private String primaryInsurerName;
    private String primaryInsurerPolicyNo;
    private String primaryInsurerPatientIdNumber;
    private LocalDate primaryInsurerEffectiveDate;
    private LocalDate primaryInsurerVerificationDate;
    private Long primaryInsurerPayPercentage;
    private String salesOrderNo;


    private Long priceDetailsId;
    private Long priceTableId;
    private Long itemId;
    private String hcpcs;
    private String priceType;
    private LocalDate effectiveStartDate;
    private String priorAuthReqStatus;
    private String optionNumber;
    private String optionName;
    private Double chargeAmt;
    private Double allowedAmt;
    private String priceTableName;
    private String itemNo;
    private String itemName;
}
