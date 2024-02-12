package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SecondaryClaimSubmisionServicelines.
 */
@Table("t_secondary_claim_submision_servicelines")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SecondaryClaimSubmisionServicelines implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("change_health_secondary_submision_servicelines_id")
    private Long changeHealthSecondarySubmisionServicelinesId;

    @Column("change_health_secondary_submision_master_id")
    private Long changeHealthSecondarySubmisionMasterId;

    @Column("original_dos")
    private LocalDate originalDos;

    @Column("dos_to")
    private LocalDate dosTo;

    @Column("proc_code")
    private String procCode;

    @Column("charge_amt")
    private Double chargeAmt;

    @Column("item_uom")
    private String itemUom;

    @Column("modifier_1")
    private String modifier1;

    @Column("modifier_2")
    private String modifier2;

    @Column("modifier_3")
    private String modifier3;

    @Column("modifier_4")
    private String modifier4;

    @Column("icd_pointer")
    private String icdPointer;

    @Column("qty")
    private Long qty;

    @Column("inserted_by_id")
    private String insertedById;

    @Column("inserted_date")
    private LocalDate insertedDate;

    @Column("claim_serviceline_control_no")
    private String claimServicelineControlNo;

    @Column("procedure_identifier")
    private String procedureIdentifier;

    @Column("inserted_by_name")
    private String insertedByName;

    @Column("status")
    private String status;

    @Column("ordering_provider_first_name")
    private String orderingProviderFirstName;

    @Column("ordering_provider_last_name")
    private String orderingProviderLastName;

    @Column("ordering_provider_npi")
    private String orderingProviderNpi;

    @Column("reference")
    private String reference;

    @Column("payor_claim_control_no")
    private String payorClaimControlNo;

    @Column("provider_payment_amount")
    private Double providerPaymentAmount;

    @Column("line_adjustment")
    private String lineAdjustment;

    @Column("updated_by_id")
    private String updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("secondary_claim_submision_servicelines_uuid")
    private UUID secondaryClaimSubmisionServicelinesUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getChangeHealthSecondarySubmisionServicelinesId() {
        return this.changeHealthSecondarySubmisionServicelinesId;
    }

    public SecondaryClaimSubmisionServicelines changeHealthSecondarySubmisionServicelinesId(
        Long changeHealthSecondarySubmisionServicelinesId
    ) {
        this.setChangeHealthSecondarySubmisionServicelinesId(changeHealthSecondarySubmisionServicelinesId);
        return this;
    }

    public void setChangeHealthSecondarySubmisionServicelinesId(Long changeHealthSecondarySubmisionServicelinesId) {
        this.changeHealthSecondarySubmisionServicelinesId = changeHealthSecondarySubmisionServicelinesId;
    }

    public Long getChangeHealthSecondarySubmisionMasterId() {
        return this.changeHealthSecondarySubmisionMasterId;
    }

    public SecondaryClaimSubmisionServicelines changeHealthSecondarySubmisionMasterId(Long changeHealthSecondarySubmisionMasterId) {
        this.setChangeHealthSecondarySubmisionMasterId(changeHealthSecondarySubmisionMasterId);
        return this;
    }

    public void setChangeHealthSecondarySubmisionMasterId(Long changeHealthSecondarySubmisionMasterId) {
        this.changeHealthSecondarySubmisionMasterId = changeHealthSecondarySubmisionMasterId;
    }

    public LocalDate getOriginalDos() {
        return this.originalDos;
    }

    public SecondaryClaimSubmisionServicelines originalDos(LocalDate originalDos) {
        this.setOriginalDos(originalDos);
        return this;
    }

    public void setOriginalDos(LocalDate originalDos) {
        this.originalDos = originalDos;
    }

    public LocalDate getDosTo() {
        return this.dosTo;
    }

    public SecondaryClaimSubmisionServicelines dosTo(LocalDate dosTo) {
        this.setDosTo(dosTo);
        return this;
    }

    public void setDosTo(LocalDate dosTo) {
        this.dosTo = dosTo;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public SecondaryClaimSubmisionServicelines procCode(String procCode) {
        this.setProcCode(procCode);
        return this;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public Double getChargeAmt() {
        return this.chargeAmt;
    }

    public SecondaryClaimSubmisionServicelines chargeAmt(Double chargeAmt) {
        this.setChargeAmt(chargeAmt);
        return this;
    }

    public void setChargeAmt(Double chargeAmt) {
        this.chargeAmt = chargeAmt;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public SecondaryClaimSubmisionServicelines itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public String getModifier1() {
        return this.modifier1;
    }

    public SecondaryClaimSubmisionServicelines modifier1(String modifier1) {
        this.setModifier1(modifier1);
        return this;
    }

    public void setModifier1(String modifier1) {
        this.modifier1 = modifier1;
    }

    public String getModifier2() {
        return this.modifier2;
    }

    public SecondaryClaimSubmisionServicelines modifier2(String modifier2) {
        this.setModifier2(modifier2);
        return this;
    }

    public void setModifier2(String modifier2) {
        this.modifier2 = modifier2;
    }

    public String getModifier3() {
        return this.modifier3;
    }

    public SecondaryClaimSubmisionServicelines modifier3(String modifier3) {
        this.setModifier3(modifier3);
        return this;
    }

    public void setModifier3(String modifier3) {
        this.modifier3 = modifier3;
    }

    public String getModifier4() {
        return this.modifier4;
    }

    public SecondaryClaimSubmisionServicelines modifier4(String modifier4) {
        this.setModifier4(modifier4);
        return this;
    }

    public void setModifier4(String modifier4) {
        this.modifier4 = modifier4;
    }

    public String getIcdPointer() {
        return this.icdPointer;
    }

    public SecondaryClaimSubmisionServicelines icdPointer(String icdPointer) {
        this.setIcdPointer(icdPointer);
        return this;
    }

    public void setIcdPointer(String icdPointer) {
        this.icdPointer = icdPointer;
    }

    public Long getQty() {
        return this.qty;
    }

    public SecondaryClaimSubmisionServicelines qty(Long qty) {
        this.setQty(qty);
        return this;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getInsertedById() {
        return this.insertedById;
    }

    public SecondaryClaimSubmisionServicelines insertedById(String insertedById) {
        this.setInsertedById(insertedById);
        return this;
    }

    public void setInsertedById(String insertedById) {
        this.insertedById = insertedById;
    }

    public LocalDate getInsertedDate() {
        return this.insertedDate;
    }

    public SecondaryClaimSubmisionServicelines insertedDate(LocalDate insertedDate) {
        this.setInsertedDate(insertedDate);
        return this;
    }

    public void setInsertedDate(LocalDate insertedDate) {
        this.insertedDate = insertedDate;
    }

    public String getClaimServicelineControlNo() {
        return this.claimServicelineControlNo;
    }

    public SecondaryClaimSubmisionServicelines claimServicelineControlNo(String claimServicelineControlNo) {
        this.setClaimServicelineControlNo(claimServicelineControlNo);
        return this;
    }

    public void setClaimServicelineControlNo(String claimServicelineControlNo) {
        this.claimServicelineControlNo = claimServicelineControlNo;
    }

    public String getProcedureIdentifier() {
        return this.procedureIdentifier;
    }

    public SecondaryClaimSubmisionServicelines procedureIdentifier(String procedureIdentifier) {
        this.setProcedureIdentifier(procedureIdentifier);
        return this;
    }

    public void setProcedureIdentifier(String procedureIdentifier) {
        this.procedureIdentifier = procedureIdentifier;
    }

    public String getInsertedByName() {
        return this.insertedByName;
    }

    public SecondaryClaimSubmisionServicelines insertedByName(String insertedByName) {
        this.setInsertedByName(insertedByName);
        return this;
    }

    public void setInsertedByName(String insertedByName) {
        this.insertedByName = insertedByName;
    }

    public String getStatus() {
        return this.status;
    }

    public SecondaryClaimSubmisionServicelines status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderingProviderFirstName() {
        return this.orderingProviderFirstName;
    }

    public SecondaryClaimSubmisionServicelines orderingProviderFirstName(String orderingProviderFirstName) {
        this.setOrderingProviderFirstName(orderingProviderFirstName);
        return this;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderLastName() {
        return this.orderingProviderLastName;
    }

    public SecondaryClaimSubmisionServicelines orderingProviderLastName(String orderingProviderLastName) {
        this.setOrderingProviderLastName(orderingProviderLastName);
        return this;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return this.orderingProviderNpi;
    }

    public SecondaryClaimSubmisionServicelines orderingProviderNpi(String orderingProviderNpi) {
        this.setOrderingProviderNpi(orderingProviderNpi);
        return this;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getReference() {
        return this.reference;
    }

    public SecondaryClaimSubmisionServicelines reference(String reference) {
        this.setReference(reference);
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPayorClaimControlNo() {
        return this.payorClaimControlNo;
    }

    public SecondaryClaimSubmisionServicelines payorClaimControlNo(String payorClaimControlNo) {
        this.setPayorClaimControlNo(payorClaimControlNo);
        return this;
    }

    public void setPayorClaimControlNo(String payorClaimControlNo) {
        this.payorClaimControlNo = payorClaimControlNo;
    }

    public Double getProviderPaymentAmount() {
        return this.providerPaymentAmount;
    }

    public SecondaryClaimSubmisionServicelines providerPaymentAmount(Double providerPaymentAmount) {
        this.setProviderPaymentAmount(providerPaymentAmount);
        return this;
    }

    public void setProviderPaymentAmount(Double providerPaymentAmount) {
        this.providerPaymentAmount = providerPaymentAmount;
    }

    public String getLineAdjustment() {
        return this.lineAdjustment;
    }

    public SecondaryClaimSubmisionServicelines lineAdjustment(String lineAdjustment) {
        this.setLineAdjustment(lineAdjustment);
        return this;
    }

    public void setLineAdjustment(String lineAdjustment) {
        this.lineAdjustment = lineAdjustment;
    }

    public String getUpdatedById() {
        return this.updatedById;
    }

    public SecondaryClaimSubmisionServicelines updatedById(String updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(String updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public SecondaryClaimSubmisionServicelines updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SecondaryClaimSubmisionServicelines updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getSecondaryClaimSubmisionServicelinesUuid() {
        return this.secondaryClaimSubmisionServicelinesUuid;
    }

    public SecondaryClaimSubmisionServicelines secondaryClaimSubmisionServicelinesUuid(UUID secondaryClaimSubmisionServicelinesUuid) {
        this.setSecondaryClaimSubmisionServicelinesUuid(secondaryClaimSubmisionServicelinesUuid);
        return this;
    }

    public void setSecondaryClaimSubmisionServicelinesUuid(UUID secondaryClaimSubmisionServicelinesUuid) {
        this.secondaryClaimSubmisionServicelinesUuid = secondaryClaimSubmisionServicelinesUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SecondaryClaimSubmisionServicelines)) {
            return false;
        }
        return (
            changeHealthSecondarySubmisionServicelinesId != null &&
            changeHealthSecondarySubmisionServicelinesId.equals(
                ((SecondaryClaimSubmisionServicelines) o).changeHealthSecondarySubmisionServicelinesId
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
        return "SecondaryClaimSubmisionServicelines{" +
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
            ", updatedById='" + getUpdatedById() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", secondaryClaimSubmisionServicelinesUuid='" + getSecondaryClaimSubmisionServicelinesUuid() + "'" +
            "}";
    }
}
