package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PriceDetailsMaster.
 */
@Table("t_price_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PriceDetailsMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("price_details_id")
    private Long priceDetailsId;

    @Column("price_table_id")
    private Long priceTableId;

    @Column("item_id")
    private Long itemId;

    @Column("hcpcs")
    private String hcpcs;

    @Column("billing_code_when_secondary")
    private String billingCodeWhenSecondary;

    @Column("price_type")
    private String priceType;

    @Column("effective_start_date")
    private LocalDate effectiveStartDate;

    @Column("effective_end_date")
    private LocalDate effectiveEndDate;

    @Column("cmn_reqd_to_bill_status")
    private String cmnReqdToBillStatus;

    @Column("cmn_form_name")
    private String cmnFormName;

    @Column("prior_auth_req_status")
    private String priorAuthReqStatus;

    @Column("option_number")
    private String optionNumber;

    @Column("option_name")
    private String optionName;

    @Column("default_option_status")
    private String defaultOptionStatus;

    @Column("billing_cycle_period")
    private String billingCyclePeriod;

    @Column("billing_cycle_interval")
    private String billingCycleInterval;

    @Column("billing_in_arrears_status")
    private String billingInArrearsStatus;

    @Column("pro_rate_billing_status")
    private String proRateBillingStatus;

    @Column("daily_billing_invoice_freq")
    private String dailyBillingInvoiceFreq;

    @Column("daily_billing_invoice_interval")
    private String dailyBillingInvoiceInterval;

    @Column("charge_amt")
    private Double chargeAmt;

    @Column("allowed_amt")
    private Double allowedAmt;

    @Column("allowed_modifier_1")
    private String allowedModifier1;

    @Column("allowed_modifier_2")
    private String allowedModifier2;

    @Column("allowed_modifier_3")
    private String allowedModifier3;

    @Column("allowed_modifier_4")
    private String allowedModifier4;

    @Column("accept_assignment_status")
    private String acceptAssignmentStatus;

    @Column("taxable_status")
    private String taxableStatus;

    @Column("nontax_type_name")
    private String nontaxTypeName;

    @Column("convert_to_purchase_last_status")
    private String convertToPurchaseLastStatus;

    @Column("convert_to_purchase_charge_amt")
    private Double convertToPurchaseChargeAmt;

    @Column("convert_to_purchase_allow_amt")
    private Double convertToPurchaseAllowAmt;

    @Column("convert_to_purchase_modifier_1")
    private String convertToPurchaseModifier1;

    @Column("convert_to_purchase_modifier_2")
    private String convertToPurchaseModifier2;

    @Column("convert_to_purchase_modifier_3")
    private String convertToPurchaseModifier3;

    @Column("convert_to_purchase_modifier_4")
    private String convertToPurchaseModifier4;

    @Column("billing_multiplier_unit")
    private Long billingMultiplierUnit;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private ZonedDateTime createdDate;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_by_id")
    private String updatedById;

    @Column("price_details_uuid")
    private UUID priceDetailsUuid;

    @Column("price_table_name")
    private String priceTableName;

    @Column("item_no")
    private String itemNo;

    @Column("item_name")
    private String itemName;

    @Column("item_uom")
    private String itemUom;

    @Column("updated_date")
    private ZonedDateTime updatedDate;

    @Column("price_option_billing_period_start")
    private Long priceOptionBillingPeriodStart;

    @Column("price_option_billing_period_end")
    private Long priceOptionBillingPeriodEnd;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPriceDetailsId() {
        return this.priceDetailsId;
    }

    public PriceDetailsMaster priceDetailsId(Long priceDetailsId) {
        this.setPriceDetailsId(priceDetailsId);
        return this;
    }

    public void setPriceDetailsId(Long priceDetailsId) {
        this.priceDetailsId = priceDetailsId;
    }

    public Long getPriceTableId() {
        return this.priceTableId;
    }

    public PriceDetailsMaster priceTableId(Long priceTableId) {
        this.setPriceTableId(priceTableId);
        return this;
    }

    public void setPriceTableId(Long priceTableId) {
        this.priceTableId = priceTableId;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public PriceDetailsMaster itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getHcpcs() {
        return this.hcpcs;
    }

    public PriceDetailsMaster hcpcs(String hcpcs) {
        this.setHcpcs(hcpcs);
        return this;
    }

    public void setHcpcs(String hcpcs) {
        this.hcpcs = hcpcs;
    }

    public String getBillingCodeWhenSecondary() {
        return this.billingCodeWhenSecondary;
    }

    public PriceDetailsMaster billingCodeWhenSecondary(String billingCodeWhenSecondary) {
        this.setBillingCodeWhenSecondary(billingCodeWhenSecondary);
        return this;
    }

    public void setBillingCodeWhenSecondary(String billingCodeWhenSecondary) {
        this.billingCodeWhenSecondary = billingCodeWhenSecondary;
    }

    public String getPriceType() {
        return this.priceType;
    }

    public PriceDetailsMaster priceType(String priceType) {
        this.setPriceType(priceType);
        return this;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public LocalDate getEffectiveStartDate() {
        return this.effectiveStartDate;
    }

    public PriceDetailsMaster effectiveStartDate(LocalDate effectiveStartDate) {
        this.setEffectiveStartDate(effectiveStartDate);
        return this;
    }

    public void setEffectiveStartDate(LocalDate effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public LocalDate getEffectiveEndDate() {
        return this.effectiveEndDate;
    }

    public PriceDetailsMaster effectiveEndDate(LocalDate effectiveEndDate) {
        this.setEffectiveEndDate(effectiveEndDate);
        return this;
    }

    public void setEffectiveEndDate(LocalDate effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public String getCmnReqdToBillStatus() {
        return this.cmnReqdToBillStatus;
    }

    public PriceDetailsMaster cmnReqdToBillStatus(String cmnReqdToBillStatus) {
        this.setCmnReqdToBillStatus(cmnReqdToBillStatus);
        return this;
    }

    public void setCmnReqdToBillStatus(String cmnReqdToBillStatus) {
        this.cmnReqdToBillStatus = cmnReqdToBillStatus;
    }

    public String getCmnFormName() {
        return this.cmnFormName;
    }

    public PriceDetailsMaster cmnFormName(String cmnFormName) {
        this.setCmnFormName(cmnFormName);
        return this;
    }

    public void setCmnFormName(String cmnFormName) {
        this.cmnFormName = cmnFormName;
    }

    public String getPriorAuthReqStatus() {
        return this.priorAuthReqStatus;
    }

    public PriceDetailsMaster priorAuthReqStatus(String priorAuthReqStatus) {
        this.setPriorAuthReqStatus(priorAuthReqStatus);
        return this;
    }

    public void setPriorAuthReqStatus(String priorAuthReqStatus) {
        this.priorAuthReqStatus = priorAuthReqStatus;
    }

    public String getOptionNumber() {
        return this.optionNumber;
    }

    public PriceDetailsMaster optionNumber(String optionNumber) {
        this.setOptionNumber(optionNumber);
        return this;
    }

    public void setOptionNumber(String optionNumber) {
        this.optionNumber = optionNumber;
    }

    public String getOptionName() {
        return this.optionName;
    }

    public PriceDetailsMaster optionName(String optionName) {
        this.setOptionName(optionName);
        return this;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getDefaultOptionStatus() {
        return this.defaultOptionStatus;
    }

    public PriceDetailsMaster defaultOptionStatus(String defaultOptionStatus) {
        this.setDefaultOptionStatus(defaultOptionStatus);
        return this;
    }

    public void setDefaultOptionStatus(String defaultOptionStatus) {
        this.defaultOptionStatus = defaultOptionStatus;
    }

    public String getBillingCyclePeriod() {
        return this.billingCyclePeriod;
    }

    public PriceDetailsMaster billingCyclePeriod(String billingCyclePeriod) {
        this.setBillingCyclePeriod(billingCyclePeriod);
        return this;
    }

    public void setBillingCyclePeriod(String billingCyclePeriod) {
        this.billingCyclePeriod = billingCyclePeriod;
    }

    public String getBillingCycleInterval() {
        return this.billingCycleInterval;
    }

    public PriceDetailsMaster billingCycleInterval(String billingCycleInterval) {
        this.setBillingCycleInterval(billingCycleInterval);
        return this;
    }

    public void setBillingCycleInterval(String billingCycleInterval) {
        this.billingCycleInterval = billingCycleInterval;
    }

    public String getBillingInArrearsStatus() {
        return this.billingInArrearsStatus;
    }

    public PriceDetailsMaster billingInArrearsStatus(String billingInArrearsStatus) {
        this.setBillingInArrearsStatus(billingInArrearsStatus);
        return this;
    }

    public void setBillingInArrearsStatus(String billingInArrearsStatus) {
        this.billingInArrearsStatus = billingInArrearsStatus;
    }

    public String getProRateBillingStatus() {
        return this.proRateBillingStatus;
    }

    public PriceDetailsMaster proRateBillingStatus(String proRateBillingStatus) {
        this.setProRateBillingStatus(proRateBillingStatus);
        return this;
    }

    public void setProRateBillingStatus(String proRateBillingStatus) {
        this.proRateBillingStatus = proRateBillingStatus;
    }

    public String getDailyBillingInvoiceFreq() {
        return this.dailyBillingInvoiceFreq;
    }

    public PriceDetailsMaster dailyBillingInvoiceFreq(String dailyBillingInvoiceFreq) {
        this.setDailyBillingInvoiceFreq(dailyBillingInvoiceFreq);
        return this;
    }

    public void setDailyBillingInvoiceFreq(String dailyBillingInvoiceFreq) {
        this.dailyBillingInvoiceFreq = dailyBillingInvoiceFreq;
    }

    public String getDailyBillingInvoiceInterval() {
        return this.dailyBillingInvoiceInterval;
    }

    public PriceDetailsMaster dailyBillingInvoiceInterval(String dailyBillingInvoiceInterval) {
        this.setDailyBillingInvoiceInterval(dailyBillingInvoiceInterval);
        return this;
    }

    public void setDailyBillingInvoiceInterval(String dailyBillingInvoiceInterval) {
        this.dailyBillingInvoiceInterval = dailyBillingInvoiceInterval;
    }

    public Double getChargeAmt() {
        return this.chargeAmt;
    }

    public PriceDetailsMaster chargeAmt(Double chargeAmt) {
        this.setChargeAmt(chargeAmt);
        return this;
    }

    public void setChargeAmt(Double chargeAmt) {
        this.chargeAmt = chargeAmt;
    }

    public Double getAllowedAmt() {
        return this.allowedAmt;
    }

    public PriceDetailsMaster allowedAmt(Double allowedAmt) {
        this.setAllowedAmt(allowedAmt);
        return this;
    }

    public void setAllowedAmt(Double allowedAmt) {
        this.allowedAmt = allowedAmt;
    }

    public String getAllowedModifier1() {
        return this.allowedModifier1;
    }

    public PriceDetailsMaster allowedModifier1(String allowedModifier1) {
        this.setAllowedModifier1(allowedModifier1);
        return this;
    }

    public void setAllowedModifier1(String allowedModifier1) {
        this.allowedModifier1 = allowedModifier1;
    }

    public String getAllowedModifier2() {
        return this.allowedModifier2;
    }

    public PriceDetailsMaster allowedModifier2(String allowedModifier2) {
        this.setAllowedModifier2(allowedModifier2);
        return this;
    }

    public void setAllowedModifier2(String allowedModifier2) {
        this.allowedModifier2 = allowedModifier2;
    }

    public String getAllowedModifier3() {
        return this.allowedModifier3;
    }

    public PriceDetailsMaster allowedModifier3(String allowedModifier3) {
        this.setAllowedModifier3(allowedModifier3);
        return this;
    }

    public void setAllowedModifier3(String allowedModifier3) {
        this.allowedModifier3 = allowedModifier3;
    }

    public String getAllowedModifier4() {
        return this.allowedModifier4;
    }

    public PriceDetailsMaster allowedModifier4(String allowedModifier4) {
        this.setAllowedModifier4(allowedModifier4);
        return this;
    }

    public void setAllowedModifier4(String allowedModifier4) {
        this.allowedModifier4 = allowedModifier4;
    }

    public String getAcceptAssignmentStatus() {
        return this.acceptAssignmentStatus;
    }

    public PriceDetailsMaster acceptAssignmentStatus(String acceptAssignmentStatus) {
        this.setAcceptAssignmentStatus(acceptAssignmentStatus);
        return this;
    }

    public void setAcceptAssignmentStatus(String acceptAssignmentStatus) {
        this.acceptAssignmentStatus = acceptAssignmentStatus;
    }

    public String getTaxableStatus() {
        return this.taxableStatus;
    }

    public PriceDetailsMaster taxableStatus(String taxableStatus) {
        this.setTaxableStatus(taxableStatus);
        return this;
    }

    public void setTaxableStatus(String taxableStatus) {
        this.taxableStatus = taxableStatus;
    }

    public String getNontaxTypeName() {
        return this.nontaxTypeName;
    }

    public PriceDetailsMaster nontaxTypeName(String nontaxTypeName) {
        this.setNontaxTypeName(nontaxTypeName);
        return this;
    }

    public void setNontaxTypeName(String nontaxTypeName) {
        this.nontaxTypeName = nontaxTypeName;
    }

    public String getConvertToPurchaseLastStatus() {
        return this.convertToPurchaseLastStatus;
    }

    public PriceDetailsMaster convertToPurchaseLastStatus(String convertToPurchaseLastStatus) {
        this.setConvertToPurchaseLastStatus(convertToPurchaseLastStatus);
        return this;
    }

    public void setConvertToPurchaseLastStatus(String convertToPurchaseLastStatus) {
        this.convertToPurchaseLastStatus = convertToPurchaseLastStatus;
    }

    public Double getConvertToPurchaseChargeAmt() {
        return this.convertToPurchaseChargeAmt;
    }

    public PriceDetailsMaster convertToPurchaseChargeAmt(Double convertToPurchaseChargeAmt) {
        this.setConvertToPurchaseChargeAmt(convertToPurchaseChargeAmt);
        return this;
    }

    public void setConvertToPurchaseChargeAmt(Double convertToPurchaseChargeAmt) {
        this.convertToPurchaseChargeAmt = convertToPurchaseChargeAmt;
    }

    public Double getConvertToPurchaseAllowAmt() {
        return this.convertToPurchaseAllowAmt;
    }

    public PriceDetailsMaster convertToPurchaseAllowAmt(Double convertToPurchaseAllowAmt) {
        this.setConvertToPurchaseAllowAmt(convertToPurchaseAllowAmt);
        return this;
    }

    public void setConvertToPurchaseAllowAmt(Double convertToPurchaseAllowAmt) {
        this.convertToPurchaseAllowAmt = convertToPurchaseAllowAmt;
    }

    public String getConvertToPurchaseModifier1() {
        return this.convertToPurchaseModifier1;
    }

    public PriceDetailsMaster convertToPurchaseModifier1(String convertToPurchaseModifier1) {
        this.setConvertToPurchaseModifier1(convertToPurchaseModifier1);
        return this;
    }

    public void setConvertToPurchaseModifier1(String convertToPurchaseModifier1) {
        this.convertToPurchaseModifier1 = convertToPurchaseModifier1;
    }

    public String getConvertToPurchaseModifier2() {
        return this.convertToPurchaseModifier2;
    }

    public PriceDetailsMaster convertToPurchaseModifier2(String convertToPurchaseModifier2) {
        this.setConvertToPurchaseModifier2(convertToPurchaseModifier2);
        return this;
    }

    public void setConvertToPurchaseModifier2(String convertToPurchaseModifier2) {
        this.convertToPurchaseModifier2 = convertToPurchaseModifier2;
    }

    public String getConvertToPurchaseModifier3() {
        return this.convertToPurchaseModifier3;
    }

    public PriceDetailsMaster convertToPurchaseModifier3(String convertToPurchaseModifier3) {
        this.setConvertToPurchaseModifier3(convertToPurchaseModifier3);
        return this;
    }

    public void setConvertToPurchaseModifier3(String convertToPurchaseModifier3) {
        this.convertToPurchaseModifier3 = convertToPurchaseModifier3;
    }

    public String getConvertToPurchaseModifier4() {
        return this.convertToPurchaseModifier4;
    }

    public PriceDetailsMaster convertToPurchaseModifier4(String convertToPurchaseModifier4) {
        this.setConvertToPurchaseModifier4(convertToPurchaseModifier4);
        return this;
    }

    public void setConvertToPurchaseModifier4(String convertToPurchaseModifier4) {
        this.convertToPurchaseModifier4 = convertToPurchaseModifier4;
    }

    public Long getBillingMultiplierUnit() {
        return this.billingMultiplierUnit;
    }

    public PriceDetailsMaster billingMultiplierUnit(Long billingMultiplierUnit) {
        this.setBillingMultiplierUnit(billingMultiplierUnit);
        return this;
    }

    public void setBillingMultiplierUnit(Long billingMultiplierUnit) {
        this.billingMultiplierUnit = billingMultiplierUnit;
    }

    public String getStatus() {
        return this.status;
    }

    public PriceDetailsMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public PriceDetailsMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public ZonedDateTime getCreatedDate() {
        return this.createdDate;
    }

    public PriceDetailsMaster createdDate(ZonedDateTime createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public PriceDetailsMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PriceDetailsMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getUpdatedById() {
        return this.updatedById;
    }

    public PriceDetailsMaster updatedById(String updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(String updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getPriceDetailsUuid() {
        return this.priceDetailsUuid;
    }

    public PriceDetailsMaster priceDetailsUuid(UUID priceDetailsUuid) {
        this.setPriceDetailsUuid(priceDetailsUuid);
        return this;
    }

    public void setPriceDetailsUuid(UUID priceDetailsUuid) {
        this.priceDetailsUuid = priceDetailsUuid;
    }

    public String getPriceTableName() {
        return this.priceTableName;
    }

    public PriceDetailsMaster priceTableName(String priceTableName) {
        this.setPriceTableName(priceTableName);
        return this;
    }

    public void setPriceTableName(String priceTableName) {
        this.priceTableName = priceTableName;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public PriceDetailsMaster itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return this.itemName;
    }

    public PriceDetailsMaster itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public PriceDetailsMaster itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public ZonedDateTime getUpdatedDate() {
        return this.updatedDate;
    }

    public PriceDetailsMaster updatedDate(ZonedDateTime updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getPriceOptionBillingPeriodStart() {
        return this.priceOptionBillingPeriodStart;
    }

    public PriceDetailsMaster priceOptionBillingPeriodStart(Long priceOptionBillingPeriodStart) {
        this.setPriceOptionBillingPeriodStart(priceOptionBillingPeriodStart);
        return this;
    }

    public void setPriceOptionBillingPeriodStart(Long priceOptionBillingPeriodStart) {
        this.priceOptionBillingPeriodStart = priceOptionBillingPeriodStart;
    }

    public Long getPriceOptionBillingPeriodEnd() {
        return this.priceOptionBillingPeriodEnd;
    }

    public PriceDetailsMaster priceOptionBillingPeriodEnd(Long priceOptionBillingPeriodEnd) {
        this.setPriceOptionBillingPeriodEnd(priceOptionBillingPeriodEnd);
        return this;
    }

    public void setPriceOptionBillingPeriodEnd(Long priceOptionBillingPeriodEnd) {
        this.priceOptionBillingPeriodEnd = priceOptionBillingPeriodEnd;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PriceDetailsMaster)) {
            return false;
        }
        return priceDetailsId != null && priceDetailsId.equals(((PriceDetailsMaster) o).priceDetailsId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PriceDetailsMaster{" +
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
