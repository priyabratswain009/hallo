package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SalesOrderFinancialDetails.
 */
@Table("t_sales_order_financial_details")
public class SalesOrderFinancialDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("sales_order_financial_id")
    private Long salesOrderFinancialId;

    @Column("sales_order_id")
    private Long salesOrderId;

    @Column("patient_id")
    private Long patientId;

    @Column("item_id")
    private Long itemId;

    @Column("item_name")
    private String itemName;

    @Column("item_proc_code")
    private String itemProcCode;

    @Column("charged_amount")
    private Double chargedAmount;

    @Column("allowed_amount")
    private Double allowedAmount;

    @Column("primary_insurer_id")
    private Long primaryInsurerId;

    @Column("primary_insurer_name")
    private String primaryInsurerName;

    @Column("primary_insurer_status")
    private String primaryInsurerStatus;

    @Column("primary_insurer_coverage_percentage")
    private Long primaryInsurerCoveragePercentage;

    @Column("primary_insurer_coverage_amount")
    private Double primaryInsurerCoverageAmount;

    @Column("primary_insurer_deductible_amount")
    private Double primaryInsurerDeductibleAmount;

    @Column("primary_insurer_payment")
    private Double primaryInsurerPayment;

    @Column("primary_insurer_balance_amount")
    private Double primaryInsurerBalanceAmount;

    @Column("secondary_insurer_id")
    private Long secondaryInsurerId;

    @Column("secondary_insurer_name")
    private String secondaryInsurerName;

    @Column("secondary_insurer_status")
    private String secondaryInsurerStatus;

    @Column("secondary_insurer_coverager_percentage")
    private Long secondaryInsurerCoveragerPercentage;

    @Column("secondary_insurer_coverage_amount")
    private Double secondaryInsurerCoverageAmount;

    @Column("secondary_insurer_payment")
    private Double secondaryInsurerPayment;

    @Column("secondary_insurer_balance_amount")
    private Double secondaryInsurerBalanceAmount;

    @Column("tertiary_insurer_id")
    private Long tertiaryInsurerId;

    @Column("tertiary_insurer_name")
    private String tertiaryInsurerName;

    @Column("tertiary_insurer_status")
    private String tertiaryInsurerStatus;

    @Column("tertiary_insurer_coverage_percentage")
    private Long tertiaryInsurerCoveragePercentage;

    @Column("tertiary_insurer_coverage_amount")
    private Double tertiaryInsurerCoverageAmount;

    @Column("tertiary_insurer_payment")
    private Double tertiaryInsurerPayment;

    @Column("tertiary_insurer_balance_amount")
    private Double tertiaryInsurerBalanceAmount;

    @Column("patient_coinsurance_percentage")
    private Long patientCoinsurancePercentage;

    @Column("patient_coinsurance_amount")
    private Double patientCoinsuranceAmount;

    @Column("total_patient_responsibility_amount")
    private Double totalPatientResponsibilityAmount;

    @Column("patient_pay_amount")
    private Double patientPayAmount;

    @Column("narration")
    private String narration;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("primary_invoice_no")
    private String primaryInvoiceNo;

    @Column("primary_invoice_date")
    private LocalDate primaryInvoiceDate;

    @Column("primary_invoice_status")
    private String primaryInvoiceStatus;

    @Column("dos")
    private String dos;

    @Column("secondary_invoice_no")
    private String secondaryInvoiceNo;

    @Column("tertiary_invoice_no")
    private String tertiaryInvoiceNo;

    @Column("secondary_invoice_date")
    private LocalDate secondaryInvoiceDate;

    @Column("tertiary_invoice_date")
    private LocalDate tertiaryInvoiceDate;

    @Column("secondary_invoice_status")
    private String secondaryInvoiceStatus;

    @Column("tertiary_invoice_status")
    private String tertiaryInvoiceStatus;

    @Column("sales_order_financial_details_uuid")
    private UUID salesOrderFinancialDetailsUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getSalesOrderFinancialId() {
        return this.salesOrderFinancialId;
    }

    public SalesOrderFinancialDetails salesOrderFinancialId(Long salesOrderFinancialId) {
        this.setSalesOrderFinancialId(salesOrderFinancialId);
        return this;
    }

    public void setSalesOrderFinancialId(Long salesOrderFinancialId) {
        this.salesOrderFinancialId = salesOrderFinancialId;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public SalesOrderFinancialDetails salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public SalesOrderFinancialDetails patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public SalesOrderFinancialDetails itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public SalesOrderFinancialDetails itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemProcCode() {
        return this.itemProcCode;
    }

    public SalesOrderFinancialDetails itemProcCode(String itemProcCode) {
        this.setItemProcCode(itemProcCode);
        return this;
    }

    public void setItemProcCode(String itemProcCode) {
        this.itemProcCode = itemProcCode;
    }

    public Double getChargedAmount() {
        return this.chargedAmount;
    }

    public SalesOrderFinancialDetails chargedAmount(Double chargedAmount) {
        this.setChargedAmount(chargedAmount);
        return this;
    }

    public void setChargedAmount(Double chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    public Double getAllowedAmount() {
        return this.allowedAmount;
    }

    public SalesOrderFinancialDetails allowedAmount(Double allowedAmount) {
        this.setAllowedAmount(allowedAmount);
        return this;
    }

    public void setAllowedAmount(Double allowedAmount) {
        this.allowedAmount = allowedAmount;
    }

    public Long getPrimaryInsurerId() {
        return this.primaryInsurerId;
    }

    public SalesOrderFinancialDetails primaryInsurerId(Long primaryInsurerId) {
        this.setPrimaryInsurerId(primaryInsurerId);
        return this;
    }

    public void setPrimaryInsurerId(Long primaryInsurerId) {
        this.primaryInsurerId = primaryInsurerId;
    }

    public String getPrimaryInsurerName() {
        return this.primaryInsurerName;
    }

    public SalesOrderFinancialDetails primaryInsurerName(String primaryInsurerName) {
        this.setPrimaryInsurerName(primaryInsurerName);
        return this;
    }

    public void setPrimaryInsurerName(String primaryInsurerName) {
        this.primaryInsurerName = primaryInsurerName;
    }

    public String getPrimaryInsurerStatus() {
        return this.primaryInsurerStatus;
    }

    public SalesOrderFinancialDetails primaryInsurerStatus(String primaryInsurerStatus) {
        this.setPrimaryInsurerStatus(primaryInsurerStatus);
        return this;
    }

    public void setPrimaryInsurerStatus(String primaryInsurerStatus) {
        this.primaryInsurerStatus = primaryInsurerStatus;
    }

    public Long getPrimaryInsurerCoveragePercentage() {
        return this.primaryInsurerCoveragePercentage;
    }

    public SalesOrderFinancialDetails primaryInsurerCoveragePercentage(Long primaryInsurerCoveragePercentage) {
        this.setPrimaryInsurerCoveragePercentage(primaryInsurerCoveragePercentage);
        return this;
    }

    public void setPrimaryInsurerCoveragePercentage(Long primaryInsurerCoveragePercentage) {
        this.primaryInsurerCoveragePercentage = primaryInsurerCoveragePercentage;
    }

    public Double getPrimaryInsurerCoverageAmount() {
        return this.primaryInsurerCoverageAmount;
    }

    public SalesOrderFinancialDetails primaryInsurerCoverageAmount(Double primaryInsurerCoverageAmount) {
        this.setPrimaryInsurerCoverageAmount(primaryInsurerCoverageAmount);
        return this;
    }

    public void setPrimaryInsurerCoverageAmount(Double primaryInsurerCoverageAmount) {
        this.primaryInsurerCoverageAmount = primaryInsurerCoverageAmount;
    }

    public Double getPrimaryInsurerDeductibleAmount() {
        return this.primaryInsurerDeductibleAmount;
    }

    public SalesOrderFinancialDetails primaryInsurerDeductibleAmount(Double primaryInsurerDeductibleAmount) {
        this.setPrimaryInsurerDeductibleAmount(primaryInsurerDeductibleAmount);
        return this;
    }

    public void setPrimaryInsurerDeductibleAmount(Double primaryInsurerDeductibleAmount) {
        this.primaryInsurerDeductibleAmount = primaryInsurerDeductibleAmount;
    }

    public Double getPrimaryInsurerPayment() {
        return this.primaryInsurerPayment;
    }

    public SalesOrderFinancialDetails primaryInsurerPayment(Double primaryInsurerPayment) {
        this.setPrimaryInsurerPayment(primaryInsurerPayment);
        return this;
    }

    public void setPrimaryInsurerPayment(Double primaryInsurerPayment) {
        this.primaryInsurerPayment = primaryInsurerPayment;
    }

    public Double getPrimaryInsurerBalanceAmount() {
        return this.primaryInsurerBalanceAmount;
    }

    public SalesOrderFinancialDetails primaryInsurerBalanceAmount(Double primaryInsurerBalanceAmount) {
        this.setPrimaryInsurerBalanceAmount(primaryInsurerBalanceAmount);
        return this;
    }

    public void setPrimaryInsurerBalanceAmount(Double primaryInsurerBalanceAmount) {
        this.primaryInsurerBalanceAmount = primaryInsurerBalanceAmount;
    }

    public Long getSecondaryInsurerId() {
        return this.secondaryInsurerId;
    }

    public SalesOrderFinancialDetails secondaryInsurerId(Long secondaryInsurerId) {
        this.setSecondaryInsurerId(secondaryInsurerId);
        return this;
    }

    public void setSecondaryInsurerId(Long secondaryInsurerId) {
        this.secondaryInsurerId = secondaryInsurerId;
    }

    public String getSecondaryInsurerName() {
        return this.secondaryInsurerName;
    }

    public SalesOrderFinancialDetails secondaryInsurerName(String secondaryInsurerName) {
        this.setSecondaryInsurerName(secondaryInsurerName);
        return this;
    }

    public void setSecondaryInsurerName(String secondaryInsurerName) {
        this.secondaryInsurerName = secondaryInsurerName;
    }

    public String getSecondaryInsurerStatus() {
        return this.secondaryInsurerStatus;
    }

    public SalesOrderFinancialDetails secondaryInsurerStatus(String secondaryInsurerStatus) {
        this.setSecondaryInsurerStatus(secondaryInsurerStatus);
        return this;
    }

    public void setSecondaryInsurerStatus(String secondaryInsurerStatus) {
        this.secondaryInsurerStatus = secondaryInsurerStatus;
    }

    public Long getSecondaryInsurerCoveragerPercentage() {
        return this.secondaryInsurerCoveragerPercentage;
    }

    public SalesOrderFinancialDetails secondaryInsurerCoveragerPercentage(Long secondaryInsurerCoveragerPercentage) {
        this.setSecondaryInsurerCoveragerPercentage(secondaryInsurerCoveragerPercentage);
        return this;
    }

    public void setSecondaryInsurerCoveragerPercentage(Long secondaryInsurerCoveragerPercentage) {
        this.secondaryInsurerCoveragerPercentage = secondaryInsurerCoveragerPercentage;
    }

    public Double getSecondaryInsurerCoverageAmount() {
        return this.secondaryInsurerCoverageAmount;
    }

    public SalesOrderFinancialDetails secondaryInsurerCoverageAmount(Double secondaryInsurerCoverageAmount) {
        this.setSecondaryInsurerCoverageAmount(secondaryInsurerCoverageAmount);
        return this;
    }

    public void setSecondaryInsurerCoverageAmount(Double secondaryInsurerCoverageAmount) {
        this.secondaryInsurerCoverageAmount = secondaryInsurerCoverageAmount;
    }

    public Double getSecondaryInsurerPayment() {
        return this.secondaryInsurerPayment;
    }

    public SalesOrderFinancialDetails secondaryInsurerPayment(Double secondaryInsurerPayment) {
        this.setSecondaryInsurerPayment(secondaryInsurerPayment);
        return this;
    }

    public void setSecondaryInsurerPayment(Double secondaryInsurerPayment) {
        this.secondaryInsurerPayment = secondaryInsurerPayment;
    }

    public Double getSecondaryInsurerBalanceAmount() {
        return this.secondaryInsurerBalanceAmount;
    }

    public SalesOrderFinancialDetails secondaryInsurerBalanceAmount(Double secondaryInsurerBalanceAmount) {
        this.setSecondaryInsurerBalanceAmount(secondaryInsurerBalanceAmount);
        return this;
    }

    public void setSecondaryInsurerBalanceAmount(Double secondaryInsurerBalanceAmount) {
        this.secondaryInsurerBalanceAmount = secondaryInsurerBalanceAmount;
    }

    public Long getTertiaryInsurerId() {
        return this.tertiaryInsurerId;
    }

    public SalesOrderFinancialDetails tertiaryInsurerId(Long tertiaryInsurerId) {
        this.setTertiaryInsurerId(tertiaryInsurerId);
        return this;
    }

    public void setTertiaryInsurerId(Long tertiaryInsurerId) {
        this.tertiaryInsurerId = tertiaryInsurerId;
    }

    public String getTertiaryInsurerName() {
        return this.tertiaryInsurerName;
    }

    public SalesOrderFinancialDetails tertiaryInsurerName(String tertiaryInsurerName) {
        this.setTertiaryInsurerName(tertiaryInsurerName);
        return this;
    }

    public void setTertiaryInsurerName(String tertiaryInsurerName) {
        this.tertiaryInsurerName = tertiaryInsurerName;
    }

    public String getTertiaryInsurerStatus() {
        return this.tertiaryInsurerStatus;
    }

    public SalesOrderFinancialDetails tertiaryInsurerStatus(String tertiaryInsurerStatus) {
        this.setTertiaryInsurerStatus(tertiaryInsurerStatus);
        return this;
    }

    public void setTertiaryInsurerStatus(String tertiaryInsurerStatus) {
        this.tertiaryInsurerStatus = tertiaryInsurerStatus;
    }

    public Long getTertiaryInsurerCoveragePercentage() {
        return this.tertiaryInsurerCoveragePercentage;
    }

    public SalesOrderFinancialDetails tertiaryInsurerCoveragePercentage(Long tertiaryInsurerCoveragePercentage) {
        this.setTertiaryInsurerCoveragePercentage(tertiaryInsurerCoveragePercentage);
        return this;
    }

    public void setTertiaryInsurerCoveragePercentage(Long tertiaryInsurerCoveragePercentage) {
        this.tertiaryInsurerCoveragePercentage = tertiaryInsurerCoveragePercentage;
    }

    public Double getTertiaryInsurerCoverageAmount() {
        return this.tertiaryInsurerCoverageAmount;
    }

    public SalesOrderFinancialDetails tertiaryInsurerCoverageAmount(Double tertiaryInsurerCoverageAmount) {
        this.setTertiaryInsurerCoverageAmount(tertiaryInsurerCoverageAmount);
        return this;
    }

    public void setTertiaryInsurerCoverageAmount(Double tertiaryInsurerCoverageAmount) {
        this.tertiaryInsurerCoverageAmount = tertiaryInsurerCoverageAmount;
    }

    public Double getTertiaryInsurerPayment() {
        return this.tertiaryInsurerPayment;
    }

    public SalesOrderFinancialDetails tertiaryInsurerPayment(Double tertiaryInsurerPayment) {
        this.setTertiaryInsurerPayment(tertiaryInsurerPayment);
        return this;
    }

    public void setTertiaryInsurerPayment(Double tertiaryInsurerPayment) {
        this.tertiaryInsurerPayment = tertiaryInsurerPayment;
    }

    public Double getTertiaryInsurerBalanceAmount() {
        return this.tertiaryInsurerBalanceAmount;
    }

    public SalesOrderFinancialDetails tertiaryInsurerBalanceAmount(Double tertiaryInsurerBalanceAmount) {
        this.setTertiaryInsurerBalanceAmount(tertiaryInsurerBalanceAmount);
        return this;
    }

    public void setTertiaryInsurerBalanceAmount(Double tertiaryInsurerBalanceAmount) {
        this.tertiaryInsurerBalanceAmount = tertiaryInsurerBalanceAmount;
    }

    public Long getPatientCoinsurancePercentage() {
        return this.patientCoinsurancePercentage;
    }

    public SalesOrderFinancialDetails patientCoinsurancePercentage(Long patientCoinsurancePercentage) {
        this.setPatientCoinsurancePercentage(patientCoinsurancePercentage);
        return this;
    }

    public void setPatientCoinsurancePercentage(Long patientCoinsurancePercentage) {
        this.patientCoinsurancePercentage = patientCoinsurancePercentage;
    }

    public Double getPatientCoinsuranceAmount() {
        return this.patientCoinsuranceAmount;
    }

    public SalesOrderFinancialDetails patientCoinsuranceAmount(Double patientCoinsuranceAmount) {
        this.setPatientCoinsuranceAmount(patientCoinsuranceAmount);
        return this;
    }

    public void setPatientCoinsuranceAmount(Double patientCoinsuranceAmount) {
        this.patientCoinsuranceAmount = patientCoinsuranceAmount;
    }

    public Double getTotalPatientResponsibilityAmount() {
        return this.totalPatientResponsibilityAmount;
    }

    public SalesOrderFinancialDetails totalPatientResponsibilityAmount(Double totalPatientResponsibilityAmount) {
        this.setTotalPatientResponsibilityAmount(totalPatientResponsibilityAmount);
        return this;
    }

    public void setTotalPatientResponsibilityAmount(Double totalPatientResponsibilityAmount) {
        this.totalPatientResponsibilityAmount = totalPatientResponsibilityAmount;
    }

    public Double getPatientPayAmount() {
        return this.patientPayAmount;
    }

    public SalesOrderFinancialDetails patientPayAmount(Double patientPayAmount) {
        this.setPatientPayAmount(patientPayAmount);
        return this;
    }

    public void setPatientPayAmount(Double patientPayAmount) {
        this.patientPayAmount = patientPayAmount;
    }

    public String getNarration() {
        return this.narration;
    }

    public SalesOrderFinancialDetails narration(String narration) {
        this.setNarration(narration);
        return this;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public SalesOrderFinancialDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public SalesOrderFinancialDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public SalesOrderFinancialDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SalesOrderFinancialDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SalesOrderFinancialDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public SalesOrderFinancialDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getPrimaryInvoiceNo() {
        return this.primaryInvoiceNo;
    }

    public SalesOrderFinancialDetails primaryInvoiceNo(String primaryInvoiceNo) {
        this.setPrimaryInvoiceNo(primaryInvoiceNo);
        return this;
    }

    public void setPrimaryInvoiceNo(String primaryInvoiceNo) {
        this.primaryInvoiceNo = primaryInvoiceNo;
    }

    public LocalDate getPrimaryInvoiceDate() {
        return this.primaryInvoiceDate;
    }

    public SalesOrderFinancialDetails primaryInvoiceDate(LocalDate primaryInvoiceDate) {
        this.setPrimaryInvoiceDate(primaryInvoiceDate);
        return this;
    }

    public void setPrimaryInvoiceDate(LocalDate primaryInvoiceDate) {
        this.primaryInvoiceDate = primaryInvoiceDate;
    }

    public String getPrimaryInvoiceStatus() {
        return this.primaryInvoiceStatus;
    }

    public SalesOrderFinancialDetails primaryInvoiceStatus(String primaryInvoiceStatus) {
        this.setPrimaryInvoiceStatus(primaryInvoiceStatus);
        return this;
    }

    public void setPrimaryInvoiceStatus(String primaryInvoiceStatus) {
        this.primaryInvoiceStatus = primaryInvoiceStatus;
    }

    public String getDos() {
        return this.dos;
    }

    public SalesOrderFinancialDetails dos(String dos) {
        this.setDos(dos);
        return this;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getSecondaryInvoiceNo() {
        return this.secondaryInvoiceNo;
    }

    public SalesOrderFinancialDetails secondaryInvoiceNo(String secondaryInvoiceNo) {
        this.setSecondaryInvoiceNo(secondaryInvoiceNo);
        return this;
    }

    public void setSecondaryInvoiceNo(String secondaryInvoiceNo) {
        this.secondaryInvoiceNo = secondaryInvoiceNo;
    }

    public String getTertiaryInvoiceNo() {
        return this.tertiaryInvoiceNo;
    }

    public SalesOrderFinancialDetails tertiaryInvoiceNo(String tertiaryInvoiceNo) {
        this.setTertiaryInvoiceNo(tertiaryInvoiceNo);
        return this;
    }

    public void setTertiaryInvoiceNo(String tertiaryInvoiceNo) {
        this.tertiaryInvoiceNo = tertiaryInvoiceNo;
    }

    public LocalDate getSecondaryInvoiceDate() {
        return this.secondaryInvoiceDate;
    }

    public SalesOrderFinancialDetails secondaryInvoiceDate(LocalDate secondaryInvoiceDate) {
        this.setSecondaryInvoiceDate(secondaryInvoiceDate);
        return this;
    }

    public void setSecondaryInvoiceDate(LocalDate secondaryInvoiceDate) {
        this.secondaryInvoiceDate = secondaryInvoiceDate;
    }

    public LocalDate getTertiaryInvoiceDate() {
        return this.tertiaryInvoiceDate;
    }

    public SalesOrderFinancialDetails tertiaryInvoiceDate(LocalDate tertiaryInvoiceDate) {
        this.setTertiaryInvoiceDate(tertiaryInvoiceDate);
        return this;
    }

    public void setTertiaryInvoiceDate(LocalDate tertiaryInvoiceDate) {
        this.tertiaryInvoiceDate = tertiaryInvoiceDate;
    }

    public String getSecondaryInvoiceStatus() {
        return this.secondaryInvoiceStatus;
    }

    public SalesOrderFinancialDetails secondaryInvoiceStatus(String secondaryInvoiceStatus) {
        this.setSecondaryInvoiceStatus(secondaryInvoiceStatus);
        return this;
    }

    public void setSecondaryInvoiceStatus(String secondaryInvoiceStatus) {
        this.secondaryInvoiceStatus = secondaryInvoiceStatus;
    }

    public String getTertiaryInvoiceStatus() {
        return this.tertiaryInvoiceStatus;
    }

    public SalesOrderFinancialDetails tertiaryInvoiceStatus(String tertiaryInvoiceStatus) {
        this.setTertiaryInvoiceStatus(tertiaryInvoiceStatus);
        return this;
    }

    public void setTertiaryInvoiceStatus(String tertiaryInvoiceStatus) {
        this.tertiaryInvoiceStatus = tertiaryInvoiceStatus;
    }

    public UUID getSalesOrderFinancialDetailsUuid() {
        return this.salesOrderFinancialDetailsUuid;
    }

    public SalesOrderFinancialDetails salesOrderFinancialDetailsUuid(UUID salesOrderFinancialDetailsUuid) {
        this.setSalesOrderFinancialDetailsUuid(salesOrderFinancialDetailsUuid);
        return this;
    }

    public void setSalesOrderFinancialDetailsUuid(UUID salesOrderFinancialDetailsUuid) {
        this.salesOrderFinancialDetailsUuid = salesOrderFinancialDetailsUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderFinancialDetails)) {
            return false;
        }
        return salesOrderFinancialId != null && salesOrderFinancialId.equals(((SalesOrderFinancialDetails) o).salesOrderFinancialId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderFinancialDetails{" +
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
