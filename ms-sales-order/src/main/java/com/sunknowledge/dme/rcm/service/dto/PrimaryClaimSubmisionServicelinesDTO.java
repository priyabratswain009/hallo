package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PrimaryClaimSubmisionServicelines} entity.
 */
public class PrimaryClaimSubmisionServicelinesDTO implements Serializable {

    private Long changeHealthPrimarySubmisionServicelinesId;

    private Long changeHealthPrimarySubmisionMasterId;

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

    private Long insertedById;

    private LocalDate insertedDate;

    private Long updatedById;

    private LocalDate updatedDate;

    private String claimServicelineControlNo;

    private String procedureIdentifier;

    private String insertedByName;

    private String updatedByName;

    private String status;

    private String orderingProviderFirstName;

    private String orderingProviderLastName;

    private String orderingProviderNpi;

    private String reference;

    private UUID primaryClaimSubmisionServicelinesUuid;

    public Long getChangeHealthPrimarySubmisionServicelinesId() {
        return changeHealthPrimarySubmisionServicelinesId;
    }

    public void setChangeHealthPrimarySubmisionServicelinesId(Long changeHealthPrimarySubmisionServicelinesId) {
        this.changeHealthPrimarySubmisionServicelinesId = changeHealthPrimarySubmisionServicelinesId;
    }

    public Long getChangeHealthPrimarySubmisionMasterId() {
        return changeHealthPrimarySubmisionMasterId;
    }

    public void setChangeHealthPrimarySubmisionMasterId(Long changeHealthPrimarySubmisionMasterId) {
        this.changeHealthPrimarySubmisionMasterId = changeHealthPrimarySubmisionMasterId;
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

    public Long getInsertedById() {
        return insertedById;
    }

    public void setInsertedById(Long insertedById) {
        this.insertedById = insertedById;
    }

    public LocalDate getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(LocalDate insertedDate) {
        this.insertedDate = insertedDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
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

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
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

    public UUID getPrimaryClaimSubmisionServicelinesUuid() {
        return primaryClaimSubmisionServicelinesUuid;
    }

    public void setPrimaryClaimSubmisionServicelinesUuid(UUID primaryClaimSubmisionServicelinesUuid) {
        this.primaryClaimSubmisionServicelinesUuid = primaryClaimSubmisionServicelinesUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrimaryClaimSubmisionServicelinesDTO)) {
            return false;
        }

        PrimaryClaimSubmisionServicelinesDTO primaryClaimSubmisionServicelinesDTO = (PrimaryClaimSubmisionServicelinesDTO) o;
        if (this.changeHealthPrimarySubmisionServicelinesId == null) {
            return false;
        }
        return Objects.equals(
            this.changeHealthPrimarySubmisionServicelinesId,
            primaryClaimSubmisionServicelinesDTO.changeHealthPrimarySubmisionServicelinesId
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.changeHealthPrimarySubmisionServicelinesId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrimaryClaimSubmisionServicelinesDTO{" +
            "changeHealthPrimarySubmisionServicelinesId=" + getChangeHealthPrimarySubmisionServicelinesId() +
            ", changeHealthPrimarySubmisionMasterId=" + getChangeHealthPrimarySubmisionMasterId() +
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
            ", insertedById=" + getInsertedById() +
            ", insertedDate='" + getInsertedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", claimServicelineControlNo='" + getClaimServicelineControlNo() + "'" +
            ", procedureIdentifier='" + getProcedureIdentifier() + "'" +
            ", insertedByName='" + getInsertedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", status='" + getStatus() + "'" +
            ", orderingProviderFirstName='" + getOrderingProviderFirstName() + "'" +
            ", orderingProviderLastName='" + getOrderingProviderLastName() + "'" +
            ", orderingProviderNpi='" + getOrderingProviderNpi() + "'" +
            ", reference='" + getReference() + "'" +
            ", primaryClaimSubmisionServicelinesUuid='" + getPrimaryClaimSubmisionServicelinesUuid() + "'" +
            "}";
    }
}
