package com.sunknowledge.dme.rcm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A SecondaryServiceLinesMaster.
 */
@Entity
@Table(name = "t_secondary_claim_submision_servicelines")
public class SecondaryServiceLinesMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "change_health_secondary_submision_servicelines_id", nullable = false)
    private Long changeHealthSecondarySubmisionServicelinesId;

    @NotNull
    @Column(name = "change_health_secondary_submision_master_id", nullable = false)
    private Long changeHealthSecondarySubmisionMasterId;

    @NotNull
    @Column(name = "original_dos", nullable = false)
    private ZonedDateTime originalDos;

    @NotNull
    @Column(name = "dos_to", nullable = false)
    private ZonedDateTime dosTo;

    @NotNull
    @Column(name = "proc_code", nullable = false)
    private String procCode;

    @NotNull
    @Column(name = "charge_amt", nullable = false)
    private Double chargeAmt;

    @NotNull
    @Column(name = "item_uom", nullable = false)
    private String itemUom;

    @NotNull
    @Column(name = "modifier_1", nullable = false)
    private String modifier1;

    @NotNull
    @Column(name = "modifier_2", nullable = false)
    private String modifier2;

    @NotNull
    @Column(name = "modifier_3", nullable = false)
    private String modifier3;

    @NotNull
    @Column(name = "modifier_4", nullable = false)
    private String modifier4;

    @NotNull
    @Column(name = "icd_pointer", nullable = false)
    private String icdPointer;

    @NotNull
    @Column(name = "qty", nullable = false)
    private Long qty;

    @Column(name = "inserted_by_id")
    private String insertedById;

    @Column(name = "inserted_date")
    private ZonedDateTime insertedDate;

    @Column(name = "claim_serviceline_control_no")
    private String claimServicelineControlNo;

    @Column(name = "procedure_identifier")
    private String procedureIdentifier;

    @Column(name = "inserted_by_name")
    private String insertedByName;

    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "ordering_provider_first_name", nullable = false)
    private String orderingProviderFirstName;

    @Column(name = "ordering_provider_last_name")
    private String orderingProviderLastName;

    @Column(name = "ordering_provider_npi")
    private String orderingProviderNpi;

    @Column(name = "reference")
    private String reference;

    @Column(name = "payor_claim_control_no")
    private String payorClaimControlNo;

    @NotNull
    @Column(name = "provider_payment_amount", nullable = false)
    private Double providerPaymentAmount;

    @Column(name = "line_adjustment")
    private String lineAdjustment;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private ZonedDateTime updatedDate;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @ManyToOne
    @JsonIgnoreProperties(value = { "secondaryserviceLines" }, allowSetters = true)
    private SecondaryClaimsSubmissionMaster secondaryClaimsSubmissionMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getChangeHealthSecondarySubmisionServicelinesId() {
        return this.changeHealthSecondarySubmisionServicelinesId;
    }

    public SecondaryServiceLinesMaster changeHealthSecondarySubmisionServicelinesId(Long changeHealthSecondarySubmisionServicelinesId) {
        this.setChangeHealthSecondarySubmisionServicelinesId(changeHealthSecondarySubmisionServicelinesId);
        return this;
    }

    public void setChangeHealthSecondarySubmisionServicelinesId(Long changeHealthSecondarySubmisionServicelinesId) {
        this.changeHealthSecondarySubmisionServicelinesId = changeHealthSecondarySubmisionServicelinesId;
    }

    public Long getChangeHealthSecondarySubmisionMasterId() {
        return this.changeHealthSecondarySubmisionMasterId;
    }

    public SecondaryServiceLinesMaster changeHealthSecondarySubmisionMasterId(Long changeHealthSecondarySubmisionMasterId) {
        this.setChangeHealthSecondarySubmisionMasterId(changeHealthSecondarySubmisionMasterId);
        return this;
    }

    public void setChangeHealthSecondarySubmisionMasterId(Long changeHealthSecondarySubmisionMasterId) {
        this.changeHealthSecondarySubmisionMasterId = changeHealthSecondarySubmisionMasterId;
    }

    public ZonedDateTime getOriginalDos() {
        return this.originalDos;
    }

    public SecondaryServiceLinesMaster originalDos(ZonedDateTime originalDos) {
        this.setOriginalDos(originalDos);
        return this;
    }

    public void setOriginalDos(ZonedDateTime originalDos) {
        this.originalDos = originalDos;
    }

    public ZonedDateTime getDosTo() {
        return this.dosTo;
    }

    public SecondaryServiceLinesMaster dosTo(ZonedDateTime dosTo) {
        this.setDosTo(dosTo);
        return this;
    }

    public void setDosTo(ZonedDateTime dosTo) {
        this.dosTo = dosTo;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public SecondaryServiceLinesMaster procCode(String procCode) {
        this.setProcCode(procCode);
        return this;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public Double getChargeAmt() {
        return this.chargeAmt;
    }

    public SecondaryServiceLinesMaster chargeAmt(Double chargeAmt) {
        this.setChargeAmt(chargeAmt);
        return this;
    }

    public void setChargeAmt(Double chargeAmt) {
        this.chargeAmt = chargeAmt;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public SecondaryServiceLinesMaster itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public String getModifier1() {
        return this.modifier1;
    }

    public SecondaryServiceLinesMaster modifier1(String modifier1) {
        this.setModifier1(modifier1);
        return this;
    }

    public void setModifier1(String modifier1) {
        this.modifier1 = modifier1;
    }

    public String getModifier2() {
        return this.modifier2;
    }

    public SecondaryServiceLinesMaster modifier2(String modifier2) {
        this.setModifier2(modifier2);
        return this;
    }

    public void setModifier2(String modifier2) {
        this.modifier2 = modifier2;
    }

    public String getModifier3() {
        return this.modifier3;
    }

    public SecondaryServiceLinesMaster modifier3(String modifier3) {
        this.setModifier3(modifier3);
        return this;
    }

    public void setModifier3(String modifier3) {
        this.modifier3 = modifier3;
    }

    public String getModifier4() {
        return this.modifier4;
    }

    public SecondaryServiceLinesMaster modifier4(String modifier4) {
        this.setModifier4(modifier4);
        return this;
    }

    public void setModifier4(String modifier4) {
        this.modifier4 = modifier4;
    }

    public String getIcdPointer() {
        return this.icdPointer;
    }

    public SecondaryServiceLinesMaster icdPointer(String icdPointer) {
        this.setIcdPointer(icdPointer);
        return this;
    }

    public void setIcdPointer(String icdPointer) {
        this.icdPointer = icdPointer;
    }

    public Long getQty() {
        return this.qty;
    }

    public SecondaryServiceLinesMaster qty(Long qty) {
        this.setQty(qty);
        return this;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getInsertedById() {
        return this.insertedById;
    }

    public SecondaryServiceLinesMaster insertedById(String insertedById) {
        this.setInsertedById(insertedById);
        return this;
    }

    public void setInsertedById(String insertedById) {
        this.insertedById = insertedById;
    }

    public ZonedDateTime getInsertedDate() {
        return this.insertedDate;
    }

    public SecondaryServiceLinesMaster insertedDate(ZonedDateTime insertedDate) {
        this.setInsertedDate(insertedDate);
        return this;
    }

    public void setInsertedDate(ZonedDateTime insertedDate) {
        this.insertedDate = insertedDate;
    }

    public String getClaimServicelineControlNo() {
        return this.claimServicelineControlNo;
    }

    public SecondaryServiceLinesMaster claimServicelineControlNo(String claimServicelineControlNo) {
        this.setClaimServicelineControlNo(claimServicelineControlNo);
        return this;
    }

    public void setClaimServicelineControlNo(String claimServicelineControlNo) {
        this.claimServicelineControlNo = claimServicelineControlNo;
    }

    public String getProcedureIdentifier() {
        return this.procedureIdentifier;
    }

    public SecondaryServiceLinesMaster procedureIdentifier(String procedureIdentifier) {
        this.setProcedureIdentifier(procedureIdentifier);
        return this;
    }

    public void setProcedureIdentifier(String procedureIdentifier) {
        this.procedureIdentifier = procedureIdentifier;
    }

    public String getInsertedByName() {
        return this.insertedByName;
    }

    public SecondaryServiceLinesMaster insertedByName(String insertedByName) {
        this.setInsertedByName(insertedByName);
        return this;
    }

    public void setInsertedByName(String insertedByName) {
        this.insertedByName = insertedByName;
    }

    public String getStatus() {
        return this.status;
    }

    public SecondaryServiceLinesMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderingProviderFirstName() {
        return this.orderingProviderFirstName;
    }

    public SecondaryServiceLinesMaster orderingProviderFirstName(String orderingProviderFirstName) {
        this.setOrderingProviderFirstName(orderingProviderFirstName);
        return this;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderLastName() {
        return this.orderingProviderLastName;
    }

    public SecondaryServiceLinesMaster orderingProviderLastName(String orderingProviderLastName) {
        this.setOrderingProviderLastName(orderingProviderLastName);
        return this;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return this.orderingProviderNpi;
    }

    public SecondaryServiceLinesMaster orderingProviderNpi(String orderingProviderNpi) {
        this.setOrderingProviderNpi(orderingProviderNpi);
        return this;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getReference() {
        return this.reference;
    }

    public SecondaryServiceLinesMaster reference(String reference) {
        this.setReference(reference);
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPayorClaimControlNo() {
        return this.payorClaimControlNo;
    }

    public SecondaryServiceLinesMaster payorClaimControlNo(String payorClaimControlNo) {
        this.setPayorClaimControlNo(payorClaimControlNo);
        return this;
    }

    public void setPayorClaimControlNo(String payorClaimControlNo) {
        this.payorClaimControlNo = payorClaimControlNo;
    }

    public Double getProviderPaymentAmount() {
        return this.providerPaymentAmount;
    }

    public SecondaryServiceLinesMaster providerPaymentAmount(Double providerPaymentAmount) {
        this.setProviderPaymentAmount(providerPaymentAmount);
        return this;
    }

    public void setProviderPaymentAmount(Double providerPaymentAmount) {
        this.providerPaymentAmount = providerPaymentAmount;
    }

    public String getLineAdjustment() {
        return this.lineAdjustment;
    }

    public SecondaryServiceLinesMaster lineAdjustment(String lineAdjustment) {
        this.setLineAdjustment(lineAdjustment);
        return this;
    }

    public void setLineAdjustment(String lineAdjustment) {
        this.lineAdjustment = lineAdjustment;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SecondaryServiceLinesMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public ZonedDateTime getUpdatedDate() {
        return this.updatedDate;
    }

    public SecondaryServiceLinesMaster updatedDate(ZonedDateTime updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SecondaryServiceLinesMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public SecondaryClaimsSubmissionMaster getSecondaryClaimsSubmissionMaster() {
        return this.secondaryClaimsSubmissionMaster;
    }

    public void setSecondaryClaimsSubmissionMaster(SecondaryClaimsSubmissionMaster secondaryClaimsSubmissionMaster) {
        this.secondaryClaimsSubmissionMaster = secondaryClaimsSubmissionMaster;
    }

    public SecondaryServiceLinesMaster secondaryClaimsSubmissionMaster(SecondaryClaimsSubmissionMaster secondaryClaimsSubmissionMaster) {
        this.setSecondaryClaimsSubmissionMaster(secondaryClaimsSubmissionMaster);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SecondaryServiceLinesMaster)) {
            return false;
        }
        return (
            changeHealthSecondarySubmisionServicelinesId != null &&
            changeHealthSecondarySubmisionServicelinesId.equals(
                ((SecondaryServiceLinesMaster) o).changeHealthSecondarySubmisionServicelinesId
            )
        );
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SecondaryServiceLinesMaster{" +
            "changeHealthSecondarySubmisionServicelinesId=" + getChangeHealthSecondarySubmisionServicelinesId() +
            ", changeHealthSecondarySubmisionMasterId=" + getChangeHealthSecondarySubmisionMasterId() +
            ", originalDos='" + getOriginalDos() + "'" +
            ", dosTo='" + getDosTo() + "'" +
            ", procCode='" + getProcCode() + "'" +
            ", chargeAmt=" + getChargeAmt() +
            ", itemUom='" + getItemUom() + "'" +
            ", modifier1='" + getModifier1() + "'" +
            ", modifier2='" + getModifier2() + "'" +
            ", modifier3='" + getModifier3() + "'" +
            ", modifier4='" + getModifier4() + "'" +
            ", icdPointer='" + getIcdPointer() + "'" +
            ", qty=" + getQty() +
            ", insertedById='" + getInsertedById() + "'" +
            ", insertedDate='" + getInsertedDate() + "'" +
            ", claimServicelineControlNo='" + getClaimServicelineControlNo() + "'" +
            ", procedureIdentifier='" + getProcedureIdentifier() + "'" +
            ", insertedByName='" + getInsertedByName() + "'" +
            ", status='" + getStatus() + "'" +
            ", orderingProviderFirstName='" + getOrderingProviderFirstName() + "'" +
            ", orderingProviderLastName='" + getOrderingProviderLastName() + "'" +
            ", orderingProviderNpi='" + getOrderingProviderNpi() + "'" +
            ", reference='" + getReference() + "'" +
            ", payorClaimControlNo='" + getPayorClaimControlNo() + "'" +
            ", providerPaymentAmount=" + getProviderPaymentAmount() +
            ", lineAdjustment='" + getLineAdjustment() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            "}";
    }
}
