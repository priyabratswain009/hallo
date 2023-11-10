package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SalesOrderFinancialDetails} entity.
 */
public class SalesOrderFinancialDetailsDTO implements Serializable {

    private Long salesOrderFinancialId;

    private Long salesOrderId;

    private Long patientId;

    private Long itemId;

    private String itemName;

    private String itemProcCode;

    private Double chargedAmount;

    private Double allowedAmount;

    private Long primaryInsurerId;

    private String primaryInsurerName;

    private String primaryInsurerStatus;

    private Long primaryInsurerCoveragePercentage;

    private Double primaryInsurerCoverageAmount;

    private Double primaryInsurerDeductibleAmount;

    private Double primaryInsurerPayment;

    private Double primaryInsurerBalanceAmount;

    private Long secondaryInsurerId;

    private String secondaryInsurerName;

    private String secondaryInsurerStatus;

    private Long secondaryInsurerCoveragerPercentage;

    private Double secondaryInsurerCoverageAmount;

    private Double secondaryInsurerPayment;

    private Double secondaryInsurerBalanceAmount;

    private Long tertiaryInsurerId;

    private String tertiaryInsurerName;

    private String tertiaryInsurerStatus;

    private Long tertiaryInsurerCoveragePercentage;

    private Double tertiaryInsurerCoverageAmount;

    private Double tertiaryInsurerPayment;

    private Double tertiaryInsurerBalanceAmount;

    private Long patientCoinsurancePercentage;

    private Double patientCoinsuranceAmount;

    private Double totalPatientResponsibilityAmount;

    private Double patientPayAmount;

    private String narration;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private String primaryInvoiceNo;

    private LocalDate primaryInvoiceDate;

    private String primaryInvoiceStatus;

    private String dos;

    private String secondaryInvoiceNo;

    private String tertiaryInvoiceNo;

    private LocalDate secondaryInvoiceDate;

    private LocalDate tertiaryInvoiceDate;

    private String secondaryInvoiceStatus;

    private String tertiaryInvoiceStatus;

    private UUID salesOrderFinancialDetailsUuid;

    public Long getSalesOrderFinancialId() {
        return salesOrderFinancialId;
    }

    public void setSalesOrderFinancialId(Long salesOrderFinancialId) {
        this.salesOrderFinancialId = salesOrderFinancialId;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemProcCode() {
        return itemProcCode;
    }

    public void setItemProcCode(String itemProcCode) {
        this.itemProcCode = itemProcCode;
    }

    public Double getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(Double chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    public Double getAllowedAmount() {
        return allowedAmount;
    }

    public void setAllowedAmount(Double allowedAmount) {
        this.allowedAmount = allowedAmount;
    }

    public Long getPrimaryInsurerId() {
        return primaryInsurerId;
    }

    public void setPrimaryInsurerId(Long primaryInsurerId) {
        this.primaryInsurerId = primaryInsurerId;
    }

    public String getPrimaryInsurerName() {
        return primaryInsurerName;
    }

    public void setPrimaryInsurerName(String primaryInsurerName) {
        this.primaryInsurerName = primaryInsurerName;
    }

    public String getPrimaryInsurerStatus() {
        return primaryInsurerStatus;
    }

    public void setPrimaryInsurerStatus(String primaryInsurerStatus) {
        this.primaryInsurerStatus = primaryInsurerStatus;
    }

    public Long getPrimaryInsurerCoveragePercentage() {
        return primaryInsurerCoveragePercentage;
    }

    public void setPrimaryInsurerCoveragePercentage(Long primaryInsurerCoveragePercentage) {
        this.primaryInsurerCoveragePercentage = primaryInsurerCoveragePercentage;
    }

    public Double getPrimaryInsurerCoverageAmount() {
        return primaryInsurerCoverageAmount;
    }

    public void setPrimaryInsurerCoverageAmount(Double primaryInsurerCoverageAmount) {
        this.primaryInsurerCoverageAmount = primaryInsurerCoverageAmount;
    }

    public Double getPrimaryInsurerDeductibleAmount() {
        return primaryInsurerDeductibleAmount;
    }

    public void setPrimaryInsurerDeductibleAmount(Double primaryInsurerDeductibleAmount) {
        this.primaryInsurerDeductibleAmount = primaryInsurerDeductibleAmount;
    }

    public Double getPrimaryInsurerPayment() {
        return primaryInsurerPayment;
    }

    public void setPrimaryInsurerPayment(Double primaryInsurerPayment) {
        this.primaryInsurerPayment = primaryInsurerPayment;
    }

    public Double getPrimaryInsurerBalanceAmount() {
        return primaryInsurerBalanceAmount;
    }

    public void setPrimaryInsurerBalanceAmount(Double primaryInsurerBalanceAmount) {
        this.primaryInsurerBalanceAmount = primaryInsurerBalanceAmount;
    }

    public Long getSecondaryInsurerId() {
        return secondaryInsurerId;
    }

    public void setSecondaryInsurerId(Long secondaryInsurerId) {
        this.secondaryInsurerId = secondaryInsurerId;
    }

    public String getSecondaryInsurerName() {
        return secondaryInsurerName;
    }

    public void setSecondaryInsurerName(String secondaryInsurerName) {
        this.secondaryInsurerName = secondaryInsurerName;
    }

    public String getSecondaryInsurerStatus() {
        return secondaryInsurerStatus;
    }

    public void setSecondaryInsurerStatus(String secondaryInsurerStatus) {
        this.secondaryInsurerStatus = secondaryInsurerStatus;
    }

    public Long getSecondaryInsurerCoveragerPercentage() {
        return secondaryInsurerCoveragerPercentage;
    }

    public void setSecondaryInsurerCoveragerPercentage(Long secondaryInsurerCoveragerPercentage) {
        this.secondaryInsurerCoveragerPercentage = secondaryInsurerCoveragerPercentage;
    }

    public Double getSecondaryInsurerCoverageAmount() {
        return secondaryInsurerCoverageAmount;
    }

    public void setSecondaryInsurerCoverageAmount(Double secondaryInsurerCoverageAmount) {
        this.secondaryInsurerCoverageAmount = secondaryInsurerCoverageAmount;
    }

    public Double getSecondaryInsurerPayment() {
        return secondaryInsurerPayment;
    }

    public void setSecondaryInsurerPayment(Double secondaryInsurerPayment) {
        this.secondaryInsurerPayment = secondaryInsurerPayment;
    }

    public Double getSecondaryInsurerBalanceAmount() {
        return secondaryInsurerBalanceAmount;
    }

    public void setSecondaryInsurerBalanceAmount(Double secondaryInsurerBalanceAmount) {
        this.secondaryInsurerBalanceAmount = secondaryInsurerBalanceAmount;
    }

    public Long getTertiaryInsurerId() {
        return tertiaryInsurerId;
    }

    public void setTertiaryInsurerId(Long tertiaryInsurerId) {
        this.tertiaryInsurerId = tertiaryInsurerId;
    }

    public String getTertiaryInsurerName() {
        return tertiaryInsurerName;
    }

    public void setTertiaryInsurerName(String tertiaryInsurerName) {
        this.tertiaryInsurerName = tertiaryInsurerName;
    }

    public String getTertiaryInsurerStatus() {
        return tertiaryInsurerStatus;
    }

    public void setTertiaryInsurerStatus(String tertiaryInsurerStatus) {
        this.tertiaryInsurerStatus = tertiaryInsurerStatus;
    }

    public Long getTertiaryInsurerCoveragePercentage() {
        return tertiaryInsurerCoveragePercentage;
    }

    public void setTertiaryInsurerCoveragePercentage(Long tertiaryInsurerCoveragePercentage) {
        this.tertiaryInsurerCoveragePercentage = tertiaryInsurerCoveragePercentage;
    }

    public Double getTertiaryInsurerCoverageAmount() {
        return tertiaryInsurerCoverageAmount;
    }

    public void setTertiaryInsurerCoverageAmount(Double tertiaryInsurerCoverageAmount) {
        this.tertiaryInsurerCoverageAmount = tertiaryInsurerCoverageAmount;
    }

    public Double getTertiaryInsurerPayment() {
        return tertiaryInsurerPayment;
    }

    public void setTertiaryInsurerPayment(Double tertiaryInsurerPayment) {
        this.tertiaryInsurerPayment = tertiaryInsurerPayment;
    }

    public Double getTertiaryInsurerBalanceAmount() {
        return tertiaryInsurerBalanceAmount;
    }

    public void setTertiaryInsurerBalanceAmount(Double tertiaryInsurerBalanceAmount) {
        this.tertiaryInsurerBalanceAmount = tertiaryInsurerBalanceAmount;
    }

    public Long getPatientCoinsurancePercentage() {
        return patientCoinsurancePercentage;
    }

    public void setPatientCoinsurancePercentage(Long patientCoinsurancePercentage) {
        this.patientCoinsurancePercentage = patientCoinsurancePercentage;
    }

    public Double getPatientCoinsuranceAmount() {
        return patientCoinsuranceAmount;
    }

    public void setPatientCoinsuranceAmount(Double patientCoinsuranceAmount) {
        this.patientCoinsuranceAmount = patientCoinsuranceAmount;
    }

    public Double getTotalPatientResponsibilityAmount() {
        return totalPatientResponsibilityAmount;
    }

    public void setTotalPatientResponsibilityAmount(Double totalPatientResponsibilityAmount) {
        this.totalPatientResponsibilityAmount = totalPatientResponsibilityAmount;
    }

    public Double getPatientPayAmount() {
        return patientPayAmount;
    }

    public void setPatientPayAmount(Double patientPayAmount) {
        this.patientPayAmount = patientPayAmount;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getPrimaryInvoiceNo() {
        return primaryInvoiceNo;
    }

    public void setPrimaryInvoiceNo(String primaryInvoiceNo) {
        this.primaryInvoiceNo = primaryInvoiceNo;
    }

    public LocalDate getPrimaryInvoiceDate() {
        return primaryInvoiceDate;
    }

    public void setPrimaryInvoiceDate(LocalDate primaryInvoiceDate) {
        this.primaryInvoiceDate = primaryInvoiceDate;
    }

    public String getPrimaryInvoiceStatus() {
        return primaryInvoiceStatus;
    }

    public void setPrimaryInvoiceStatus(String primaryInvoiceStatus) {
        this.primaryInvoiceStatus = primaryInvoiceStatus;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getSecondaryInvoiceNo() {
        return secondaryInvoiceNo;
    }

    public void setSecondaryInvoiceNo(String secondaryInvoiceNo) {
        this.secondaryInvoiceNo = secondaryInvoiceNo;
    }

    public String getTertiaryInvoiceNo() {
        return tertiaryInvoiceNo;
    }

    public void setTertiaryInvoiceNo(String tertiaryInvoiceNo) {
        this.tertiaryInvoiceNo = tertiaryInvoiceNo;
    }

    public LocalDate getSecondaryInvoiceDate() {
        return secondaryInvoiceDate;
    }

    public void setSecondaryInvoiceDate(LocalDate secondaryInvoiceDate) {
        this.secondaryInvoiceDate = secondaryInvoiceDate;
    }

    public LocalDate getTertiaryInvoiceDate() {
        return tertiaryInvoiceDate;
    }

    public void setTertiaryInvoiceDate(LocalDate tertiaryInvoiceDate) {
        this.tertiaryInvoiceDate = tertiaryInvoiceDate;
    }

    public String getSecondaryInvoiceStatus() {
        return secondaryInvoiceStatus;
    }

    public void setSecondaryInvoiceStatus(String secondaryInvoiceStatus) {
        this.secondaryInvoiceStatus = secondaryInvoiceStatus;
    }

    public String getTertiaryInvoiceStatus() {
        return tertiaryInvoiceStatus;
    }

    public void setTertiaryInvoiceStatus(String tertiaryInvoiceStatus) {
        this.tertiaryInvoiceStatus = tertiaryInvoiceStatus;
    }

    public UUID getSalesOrderFinancialDetailsUuid() {
        return salesOrderFinancialDetailsUuid;
    }

    public void setSalesOrderFinancialDetailsUuid(UUID salesOrderFinancialDetailsUuid) {
        this.salesOrderFinancialDetailsUuid = salesOrderFinancialDetailsUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderFinancialDetailsDTO)) {
            return false;
        }

        SalesOrderFinancialDetailsDTO salesOrderFinancialDetailsDTO = (SalesOrderFinancialDetailsDTO) o;
        if (this.salesOrderFinancialId == null) {
            return false;
        }
        return Objects.equals(this.salesOrderFinancialId, salesOrderFinancialDetailsDTO.salesOrderFinancialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.salesOrderFinancialId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderFinancialDetailsDTO{" +
            "salesOrderFinancialId=" + getSalesOrderFinancialId() +
            ", salesOrderId=" + getSalesOrderId() +
            ", patientId=" + getPatientId() +
            ", itemId=" + getItemId() +
            ", itemName='" + getItemName() + "'" +
            ", itemProcCode='" + getItemProcCode() + "'" +
            ", chargedAmount=" + getChargedAmount() +
            ", allowedAmount=" + getAllowedAmount() +
            ", primaryInsurerId=" + getPrimaryInsurerId() +
            ", primaryInsurerName='" + getPrimaryInsurerName() + "'" +
            ", primaryInsurerStatus='" + getPrimaryInsurerStatus() + "'" +
            ", primaryInsurerCoveragePercentage=" + getPrimaryInsurerCoveragePercentage() +
            ", primaryInsurerCoverageAmount=" + getPrimaryInsurerCoverageAmount() +
            ", primaryInsurerDeductibleAmount=" + getPrimaryInsurerDeductibleAmount() +
            ", primaryInsurerPayment=" + getPrimaryInsurerPayment() +
            ", primaryInsurerBalanceAmount=" + getPrimaryInsurerBalanceAmount() +
            ", secondaryInsurerId=" + getSecondaryInsurerId() +
            ", secondaryInsurerName='" + getSecondaryInsurerName() + "'" +
            ", secondaryInsurerStatus='" + getSecondaryInsurerStatus() + "'" +
            ", secondaryInsurerCoveragerPercentage=" + getSecondaryInsurerCoveragerPercentage() +
            ", secondaryInsurerCoverageAmount=" + getSecondaryInsurerCoverageAmount() +
            ", secondaryInsurerPayment=" + getSecondaryInsurerPayment() +
            ", secondaryInsurerBalanceAmount=" + getSecondaryInsurerBalanceAmount() +
            ", tertiaryInsurerId=" + getTertiaryInsurerId() +
            ", tertiaryInsurerName='" + getTertiaryInsurerName() + "'" +
            ", tertiaryInsurerStatus='" + getTertiaryInsurerStatus() + "'" +
            ", tertiaryInsurerCoveragePercentage=" + getTertiaryInsurerCoveragePercentage() +
            ", tertiaryInsurerCoverageAmount=" + getTertiaryInsurerCoverageAmount() +
            ", tertiaryInsurerPayment=" + getTertiaryInsurerPayment() +
            ", tertiaryInsurerBalanceAmount=" + getTertiaryInsurerBalanceAmount() +
            ", patientCoinsurancePercentage=" + getPatientCoinsurancePercentage() +
            ", patientCoinsuranceAmount=" + getPatientCoinsuranceAmount() +
            ", totalPatientResponsibilityAmount=" + getTotalPatientResponsibilityAmount() +
            ", patientPayAmount=" + getPatientPayAmount() +
            ", narration='" + getNarration() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", primaryInvoiceNo='" + getPrimaryInvoiceNo() + "'" +
            ", primaryInvoiceDate='" + getPrimaryInvoiceDate() + "'" +
            ", primaryInvoiceStatus='" + getPrimaryInvoiceStatus() + "'" +
            ", dos='" + getDos() + "'" +
            ", secondaryInvoiceNo='" + getSecondaryInvoiceNo() + "'" +
            ", tertiaryInvoiceNo='" + getTertiaryInvoiceNo() + "'" +
            ", secondaryInvoiceDate='" + getSecondaryInvoiceDate() + "'" +
            ", tertiaryInvoiceDate='" + getTertiaryInvoiceDate() + "'" +
            ", secondaryInvoiceStatus='" + getSecondaryInvoiceStatus() + "'" +
            ", tertiaryInvoiceStatus='" + getTertiaryInvoiceStatus() + "'" +
            ", salesOrderFinancialDetailsUuid='" + getSalesOrderFinancialDetailsUuid() + "'" +
            "}";
    }
}
