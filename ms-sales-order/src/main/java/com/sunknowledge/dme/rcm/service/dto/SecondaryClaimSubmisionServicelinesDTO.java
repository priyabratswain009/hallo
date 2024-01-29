package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionServicelines} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SecondaryClaimSubmisionServicelinesDTO implements Serializable {

    private Long changeHealthSecondarySubmisionServicelinesId;

    private Long changeHealthSecondarySubmisionMasterId;

    private LocalDate originalDos;

    private LocalDate dosTo;

    private String procCode;

    private Double chargeAmt;

    private String itemUom;

    private String modifier1;

    private String modifier2;

    private String modifier3;

    private String modifier4;

    private String icdPointer;

    private Long qty;

    private String insertedById;

    private LocalDate insertedDate;

    private String claimServicelineControlNo;

    private String procedureIdentifier;

    private String insertedByName;

    private String status;

    private String orderingProviderFirstName;

    private String orderingProviderLastName;

    private String orderingProviderNpi;

    private String reference;

    private String payorClaimControlNo;

    private Double providerPaymentAmount;

    private String lineAdjustment;

    private String updatedById;

    private LocalDate updatedDate;

    private String updatedByName;

    private UUID secondaryClaimSubmisionServicelinesUuid;

    public Long getChangeHealthSecondarySubmisionServicelinesId() {
        return changeHealthSecondarySubmisionServicelinesId;
    }

    public void setChangeHealthSecondarySubmisionServicelinesId(Long changeHealthSecondarySubmisionServicelinesId) {
        this.changeHealthSecondarySubmisionServicelinesId = changeHealthSecondarySubmisionServicelinesId;
    }

    public Long getChangeHealthSecondarySubmisionMasterId() {
        return changeHealthSecondarySubmisionMasterId;
    }

    public void setChangeHealthSecondarySubmisionMasterId(Long changeHealthSecondarySubmisionMasterId) {
        this.changeHealthSecondarySubmisionMasterId = changeHealthSecondarySubmisionMasterId;
    }

    public LocalDate getOriginalDos() {
        return originalDos;
    }

    public void setOriginalDos(LocalDate originalDos) {
        this.originalDos = originalDos;
    }

    public LocalDate getDosTo() {
        return dosTo;
    }

    public void setDosTo(LocalDate dosTo) {
        this.dosTo = dosTo;
    }

    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public Double getChargeAmt() {
        return chargeAmt;
    }

    public void setChargeAmt(Double chargeAmt) {
        this.chargeAmt = chargeAmt;
    }

    public String getItemUom() {
        return itemUom;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public String getModifier1() {
        return modifier1;
    }

    public void setModifier1(String modifier1) {
        this.modifier1 = modifier1;
    }

    public String getModifier2() {
        return modifier2;
    }

    public void setModifier2(String modifier2) {
        this.modifier2 = modifier2;
    }

    public String getModifier3() {
        return modifier3;
    }

    public void setModifier3(String modifier3) {
        this.modifier3 = modifier3;
    }

    public String getModifier4() {
        return modifier4;
    }

    public void setModifier4(String modifier4) {
        this.modifier4 = modifier4;
    }

    public String getIcdPointer() {
        return icdPointer;
    }

    public void setIcdPointer(String icdPointer) {
        this.icdPointer = icdPointer;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getInsertedById() {
        return insertedById;
    }

    public void setInsertedById(String insertedById) {
        this.insertedById = insertedById;
    }

    public LocalDate getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(LocalDate insertedDate) {
        this.insertedDate = insertedDate;
    }

    public String getClaimServicelineControlNo() {
        return claimServicelineControlNo;
    }

    public void setClaimServicelineControlNo(String claimServicelineControlNo) {
        this.claimServicelineControlNo = claimServicelineControlNo;
    }

    public String getProcedureIdentifier() {
        return procedureIdentifier;
    }

    public void setProcedureIdentifier(String procedureIdentifier) {
        this.procedureIdentifier = procedureIdentifier;
    }

    public String getInsertedByName() {
        return insertedByName;
    }

    public void setInsertedByName(String insertedByName) {
        this.insertedByName = insertedByName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderingProviderFirstName() {
        return orderingProviderFirstName;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderLastName() {
        return orderingProviderLastName;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return orderingProviderNpi;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPayorClaimControlNo() {
        return payorClaimControlNo;
    }

    public void setPayorClaimControlNo(String payorClaimControlNo) {
        this.payorClaimControlNo = payorClaimControlNo;
    }

    public Double getProviderPaymentAmount() {
        return providerPaymentAmount;
    }

    public void setProviderPaymentAmount(Double providerPaymentAmount) {
        this.providerPaymentAmount = providerPaymentAmount;
    }

    public String getLineAdjustment() {
        return lineAdjustment;
    }

    public void setLineAdjustment(String lineAdjustment) {
        this.lineAdjustment = lineAdjustment;
    }

    public String getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(String updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getSecondaryClaimSubmisionServicelinesUuid() {
        return secondaryClaimSubmisionServicelinesUuid;
    }

    public void setSecondaryClaimSubmisionServicelinesUuid(UUID secondaryClaimSubmisionServicelinesUuid) {
        this.secondaryClaimSubmisionServicelinesUuid = secondaryClaimSubmisionServicelinesUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SecondaryClaimSubmisionServicelinesDTO)) {
            return false;
        }

        SecondaryClaimSubmisionServicelinesDTO secondaryClaimSubmisionServicelinesDTO = (SecondaryClaimSubmisionServicelinesDTO) o;
        if (this.changeHealthSecondarySubmisionServicelinesId == null) {
            return false;
        }
        return Objects.equals(
            this.changeHealthSecondarySubmisionServicelinesId,
            secondaryClaimSubmisionServicelinesDTO.changeHealthSecondarySubmisionServicelinesId
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.changeHealthSecondarySubmisionServicelinesId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SecondaryClaimSubmisionServicelinesDTO{" +
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
