package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PriceDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PriceDetailsDTO implements Serializable {

    private Long priceDetailsId;

    private Long priceTableId;

    private Long itemId;

    private String hcpcs;

    private String billingCodeWhenSecondary;

    private String priceType;

    private LocalDate effectiveStartDate;

    private LocalDate effectiveEndDate;

    private String cmnReqdToBillStatus;

    private String cmnFormName;

    private String priorAuthReqStatus;

    private String functionalAbilityReqStatus;

    private String optionNumber;

    private String optionName;

    private String defaultOptionStatus;

    private String billingCyclePeriod;

    private String billingCycleInterval;

    private String billingInArrearsStatus;

    private String proRateBillingStatus;

    private String dailyBillingInvoiceFreq;

    private String dailyBillingInvoiceInterval;

    private Double chargeAmt;

    private Double allowedAmt;

    private String allowedModifier1;

    private String allowedModifier2;

    private String allowedModifier3;

    private String allowedModifier4;

    private String acceptAssignmentStatus;

    private String taxableStatus;

    private String nontaxTypeName;

    private String convertToPurchaseLastStatus;

    private Double convertToPurchaseChargeAmt;

    private Double convertToPurchaseAllowAmt;

    private String convertToPurchaseModifier1;

    private String convertToPurchaseModifier2;

    private String convertToPurchaseModifier3;

    private String convertToPurchaseModifier4;

    private Long billingMultiplierUnit;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedByName;

    private String updatedById;

    private UUID priceDetailsUuid;

    private String priceTableName;

    private String itemNo;

    private String itemName;

    private String itemUom;

    private LocalDate updatedDate;

    private Long priceOptionBillingPeriodStart;

    private Long priceOptionBillingPeriodEnd;

    public Long getPriceDetailsId() {
        return priceDetailsId;
    }

    public void setPriceDetailsId(Long priceDetailsId) {
        this.priceDetailsId = priceDetailsId;
    }

    public Long getPriceTableId() {
        return priceTableId;
    }

    public void setPriceTableId(Long priceTableId) {
        this.priceTableId = priceTableId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getHcpcs() {
        return hcpcs;
    }

    public void setHcpcs(String hcpcs) {
        this.hcpcs = hcpcs;
    }

    public String getBillingCodeWhenSecondary() {
        return billingCodeWhenSecondary;
    }

    public void setBillingCodeWhenSecondary(String billingCodeWhenSecondary) {
        this.billingCodeWhenSecondary = billingCodeWhenSecondary;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public LocalDate getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(LocalDate effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public LocalDate getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(LocalDate effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public String getCmnReqdToBillStatus() {
        return cmnReqdToBillStatus;
    }

    public void setCmnReqdToBillStatus(String cmnReqdToBillStatus) {
        this.cmnReqdToBillStatus = cmnReqdToBillStatus;
    }

    public String getCmnFormName() {
        return cmnFormName;
    }

    public void setCmnFormName(String cmnFormName) {
        this.cmnFormName = cmnFormName;
    }

    public String getPriorAuthReqStatus() {
        return priorAuthReqStatus;
    }

    public void setPriorAuthReqStatus(String priorAuthReqStatus) {
        this.priorAuthReqStatus = priorAuthReqStatus;
    }

    public String getFunctionalAbilityReqStatus() {
        return functionalAbilityReqStatus;
    }

    public void setFunctionalAbilityReqStatus(String functionalAbilityReqStatus) {
        this.functionalAbilityReqStatus = functionalAbilityReqStatus;
    }

    public String getOptionNumber() {
        return optionNumber;
    }

    public void setOptionNumber(String optionNumber) {
        this.optionNumber = optionNumber;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getDefaultOptionStatus() {
        return defaultOptionStatus;
    }

    public void setDefaultOptionStatus(String defaultOptionStatus) {
        this.defaultOptionStatus = defaultOptionStatus;
    }

    public String getBillingCyclePeriod() {
        return billingCyclePeriod;
    }

    public void setBillingCyclePeriod(String billingCyclePeriod) {
        this.billingCyclePeriod = billingCyclePeriod;
    }

    public String getBillingCycleInterval() {
        return billingCycleInterval;
    }

    public void setBillingCycleInterval(String billingCycleInterval) {
        this.billingCycleInterval = billingCycleInterval;
    }

    public String getBillingInArrearsStatus() {
        return billingInArrearsStatus;
    }

    public void setBillingInArrearsStatus(String billingInArrearsStatus) {
        this.billingInArrearsStatus = billingInArrearsStatus;
    }

    public String getProRateBillingStatus() {
        return proRateBillingStatus;
    }

    public void setProRateBillingStatus(String proRateBillingStatus) {
        this.proRateBillingStatus = proRateBillingStatus;
    }

    public String getDailyBillingInvoiceFreq() {
        return dailyBillingInvoiceFreq;
    }

    public void setDailyBillingInvoiceFreq(String dailyBillingInvoiceFreq) {
        this.dailyBillingInvoiceFreq = dailyBillingInvoiceFreq;
    }

    public String getDailyBillingInvoiceInterval() {
        return dailyBillingInvoiceInterval;
    }

    public void setDailyBillingInvoiceInterval(String dailyBillingInvoiceInterval) {
        this.dailyBillingInvoiceInterval = dailyBillingInvoiceInterval;
    }

    public Double getChargeAmt() {
        return chargeAmt;
    }

    public void setChargeAmt(Double chargeAmt) {
        this.chargeAmt = chargeAmt;
    }

    public Double getAllowedAmt() {
        return allowedAmt;
    }

    public void setAllowedAmt(Double allowedAmt) {
        this.allowedAmt = allowedAmt;
    }

    public String getAllowedModifier1() {
        return allowedModifier1;
    }

    public void setAllowedModifier1(String allowedModifier1) {
        this.allowedModifier1 = allowedModifier1;
    }

    public String getAllowedModifier2() {
        return allowedModifier2;
    }

    public void setAllowedModifier2(String allowedModifier2) {
        this.allowedModifier2 = allowedModifier2;
    }

    public String getAllowedModifier3() {
        return allowedModifier3;
    }

    public void setAllowedModifier3(String allowedModifier3) {
        this.allowedModifier3 = allowedModifier3;
    }

    public String getAllowedModifier4() {
        return allowedModifier4;
    }

    public void setAllowedModifier4(String allowedModifier4) {
        this.allowedModifier4 = allowedModifier4;
    }

    public String getAcceptAssignmentStatus() {
        return acceptAssignmentStatus;
    }

    public void setAcceptAssignmentStatus(String acceptAssignmentStatus) {
        this.acceptAssignmentStatus = acceptAssignmentStatus;
    }

    public String getTaxableStatus() {
        return taxableStatus;
    }

    public void setTaxableStatus(String taxableStatus) {
        this.taxableStatus = taxableStatus;
    }

    public String getNontaxTypeName() {
        return nontaxTypeName;
    }

    public void setNontaxTypeName(String nontaxTypeName) {
        this.nontaxTypeName = nontaxTypeName;
    }

    public String getConvertToPurchaseLastStatus() {
        return convertToPurchaseLastStatus;
    }

    public void setConvertToPurchaseLastStatus(String convertToPurchaseLastStatus) {
        this.convertToPurchaseLastStatus = convertToPurchaseLastStatus;
    }

    public Double getConvertToPurchaseChargeAmt() {
        return convertToPurchaseChargeAmt;
    }

    public void setConvertToPurchaseChargeAmt(Double convertToPurchaseChargeAmt) {
        this.convertToPurchaseChargeAmt = convertToPurchaseChargeAmt;
    }

    public Double getConvertToPurchaseAllowAmt() {
        return convertToPurchaseAllowAmt;
    }

    public void setConvertToPurchaseAllowAmt(Double convertToPurchaseAllowAmt) {
        this.convertToPurchaseAllowAmt = convertToPurchaseAllowAmt;
    }

    public String getConvertToPurchaseModifier1() {
        return convertToPurchaseModifier1;
    }

    public void setConvertToPurchaseModifier1(String convertToPurchaseModifier1) {
        this.convertToPurchaseModifier1 = convertToPurchaseModifier1;
    }

    public String getConvertToPurchaseModifier2() {
        return convertToPurchaseModifier2;
    }

    public void setConvertToPurchaseModifier2(String convertToPurchaseModifier2) {
        this.convertToPurchaseModifier2 = convertToPurchaseModifier2;
    }

    public String getConvertToPurchaseModifier3() {
        return convertToPurchaseModifier3;
    }

    public void setConvertToPurchaseModifier3(String convertToPurchaseModifier3) {
        this.convertToPurchaseModifier3 = convertToPurchaseModifier3;
    }

    public String getConvertToPurchaseModifier4() {
        return convertToPurchaseModifier4;
    }

    public void setConvertToPurchaseModifier4(String convertToPurchaseModifier4) {
        this.convertToPurchaseModifier4 = convertToPurchaseModifier4;
    }

    public Long getBillingMultiplierUnit() {
        return billingMultiplierUnit;
    }

    public void setBillingMultiplierUnit(Long billingMultiplierUnit) {
        this.billingMultiplierUnit = billingMultiplierUnit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(String updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getPriceDetailsUuid() {
        return priceDetailsUuid;
    }

    public void setPriceDetailsUuid(UUID priceDetailsUuid) {
        this.priceDetailsUuid = priceDetailsUuid;
    }

    public String getPriceTableName() {
        return priceTableName;
    }

    public void setPriceTableName(String priceTableName) {
        this.priceTableName = priceTableName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUom() {
        return itemUom;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getPriceOptionBillingPeriodStart() {
        return priceOptionBillingPeriodStart;
    }

    public void setPriceOptionBillingPeriodStart(Long priceOptionBillingPeriodStart) {
        this.priceOptionBillingPeriodStart = priceOptionBillingPeriodStart;
    }

    public Long getPriceOptionBillingPeriodEnd() {
        return priceOptionBillingPeriodEnd;
    }

    public void setPriceOptionBillingPeriodEnd(Long priceOptionBillingPeriodEnd) {
        this.priceOptionBillingPeriodEnd = priceOptionBillingPeriodEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PriceDetailsDTO)) {
            return false;
        }

        PriceDetailsDTO priceDetailsDTO = (PriceDetailsDTO) o;
        if (this.priceDetailsId == null) {
            return false;
        }
        return Objects.equals(this.priceDetailsId, priceDetailsDTO.priceDetailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.priceDetailsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PriceDetailsDTO{" +
            "priceDetailsId=" + getPriceDetailsId() +
            ", priceTableId=" + getPriceTableId() +
            ", itemId=" + getItemId() +
            ", hcpcs='" + getHcpcs() + "'" +
            ", billingCodeWhenSecondary='" + getBillingCodeWhenSecondary() + "'" +
            ", priceType='" + getPriceType() + "'" +
            ", effectiveStartDate='" + getEffectiveStartDate() + "'" +
            ", effectiveEndDate='" + getEffectiveEndDate() + "'" +
            ", cmnReqdToBillStatus='" + getCmnReqdToBillStatus() + "'" +
            ", cmnFormName='" + getCmnFormName() + "'" +
            ", priorAuthReqStatus='" + getPriorAuthReqStatus() + "'" +
            ", functionalAbilityReqStatus='" + getFunctionalAbilityReqStatus() + "'" +
            ", optionNumber='" + getOptionNumber() + "'" +
            ", optionName='" + getOptionName() + "'" +
            ", defaultOptionStatus='" + getDefaultOptionStatus() + "'" +
            ", billingCyclePeriod='" + getBillingCyclePeriod() + "'" +
            ", billingCycleInterval='" + getBillingCycleInterval() + "'" +
            ", billingInArrearsStatus='" + getBillingInArrearsStatus() + "'" +
            ", proRateBillingStatus='" + getProRateBillingStatus() + "'" +
            ", dailyBillingInvoiceFreq='" + getDailyBillingInvoiceFreq() + "'" +
            ", dailyBillingInvoiceInterval='" + getDailyBillingInvoiceInterval() + "'" +
            ", chargeAmt=" + getChargeAmt() +
            ", allowedAmt=" + getAllowedAmt() +
            ", allowedModifier1='" + getAllowedModifier1() + "'" +
            ", allowedModifier2='" + getAllowedModifier2() + "'" +
            ", allowedModifier3='" + getAllowedModifier3() + "'" +
            ", allowedModifier4='" + getAllowedModifier4() + "'" +
            ", acceptAssignmentStatus='" + getAcceptAssignmentStatus() + "'" +
            ", taxableStatus='" + getTaxableStatus() + "'" +
            ", nontaxTypeName='" + getNontaxTypeName() + "'" +
            ", convertToPurchaseLastStatus='" + getConvertToPurchaseLastStatus() + "'" +
            ", convertToPurchaseChargeAmt=" + getConvertToPurchaseChargeAmt() +
            ", convertToPurchaseAllowAmt=" + getConvertToPurchaseAllowAmt() +
            ", convertToPurchaseModifier1='" + getConvertToPurchaseModifier1() + "'" +
            ", convertToPurchaseModifier2='" + getConvertToPurchaseModifier2() + "'" +
            ", convertToPurchaseModifier3='" + getConvertToPurchaseModifier3() + "'" +
            ", convertToPurchaseModifier4='" + getConvertToPurchaseModifier4() + "'" +
            ", billingMultiplierUnit=" + getBillingMultiplierUnit() +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById='" + getUpdatedById() + "'" +
            ", priceDetailsUuid='" + getPriceDetailsUuid() + "'" +
            ", priceTableName='" + getPriceTableName() + "'" +
            ", itemNo='" + getItemNo() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", itemUom='" + getItemUom() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", priceOptionBillingPeriodStart=" + getPriceOptionBillingPeriodStart() +
            ", priceOptionBillingPeriodEnd=" + getPriceOptionBillingPeriodEnd() +
            "}";
    }
}
