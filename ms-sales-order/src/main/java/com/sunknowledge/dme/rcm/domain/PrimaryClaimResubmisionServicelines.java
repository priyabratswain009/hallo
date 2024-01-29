package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PrimaryClaimResubmisionServicelines.
 */
@Table("t_primary_claim_resubmision_servicelines")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PrimaryClaimResubmisionServicelines implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("change_health_primary_resubmision_servicelines_id")
    private Long changeHealthPrimaryResubmisionServicelinesId;

    @Column("change_health_primary_resubmision_master_id")
    private Long changeHealthPrimaryResubmisionMasterId;

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
    private Long insertedById;

    @Column("nserted_date")
    private LocalDate nsertedDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("claim_serviceline_control_no")
    private String claimServicelineControlNo;

    @Column("procedure_identifier")
    private String procedureIdentifier;

    @Column("inserted_by_name")
    private String insertedByName;

    @Column("updated_by_name")
    private String updatedByName;

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

    @Column("primary_claim_resubmision_servicelines_uuid")
    private UUID primaryClaimResubmisionServicelinesUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getChangeHealthPrimaryResubmisionServicelinesId() {
        return this.changeHealthPrimaryResubmisionServicelinesId;
    }

    public PrimaryClaimResubmisionServicelines changeHealthPrimaryResubmisionServicelinesId(
        Long changeHealthPrimaryResubmisionServicelinesId
    ) {
        this.setChangeHealthPrimaryResubmisionServicelinesId(changeHealthPrimaryResubmisionServicelinesId);
        return this;
    }

    public void setChangeHealthPrimaryResubmisionServicelinesId(Long changeHealthPrimaryResubmisionServicelinesId) {
        this.changeHealthPrimaryResubmisionServicelinesId = changeHealthPrimaryResubmisionServicelinesId;
    }

    public Long getChangeHealthPrimaryResubmisionMasterId() {
        return this.changeHealthPrimaryResubmisionMasterId;
    }

    public PrimaryClaimResubmisionServicelines changeHealthPrimaryResubmisionMasterId(Long changeHealthPrimaryResubmisionMasterId) {
        this.setChangeHealthPrimaryResubmisionMasterId(changeHealthPrimaryResubmisionMasterId);
        return this;
    }

    public void setChangeHealthPrimaryResubmisionMasterId(Long changeHealthPrimaryResubmisionMasterId) {
        this.changeHealthPrimaryResubmisionMasterId = changeHealthPrimaryResubmisionMasterId;
    }

    public LocalDate getOriginalDos() {
        return this.originalDos;
    }

    public PrimaryClaimResubmisionServicelines originalDos(LocalDate originalDos) {
        this.setOriginalDos(originalDos);
        return this;
    }

    public void setOriginalDos(LocalDate originalDos) {
        this.originalDos = originalDos;
    }

    public LocalDate getDosTo() {
        return this.dosTo;
    }

    public PrimaryClaimResubmisionServicelines dosTo(LocalDate dosTo) {
        this.setDosTo(dosTo);
        return this;
    }

    public void setDosTo(LocalDate dosTo) {
        this.dosTo = dosTo;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public PrimaryClaimResubmisionServicelines procCode(String procCode) {
        this.setProcCode(procCode);
        return this;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public Double getChargeAmt() {
        return this.chargeAmt;
    }

    public PrimaryClaimResubmisionServicelines chargeAmt(Double chargeAmt) {
        this.setChargeAmt(chargeAmt);
        return this;
    }

    public void setChargeAmt(Double chargeAmt) {
        this.chargeAmt = chargeAmt;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public PrimaryClaimResubmisionServicelines itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public String getModifier1() {
        return this.modifier1;
    }

    public PrimaryClaimResubmisionServicelines modifier1(String modifier1) {
        this.setModifier1(modifier1);
        return this;
    }

    public void setModifier1(String modifier1) {
        this.modifier1 = modifier1;
    }

    public String getModifier2() {
        return this.modifier2;
    }

    public PrimaryClaimResubmisionServicelines modifier2(String modifier2) {
        this.setModifier2(modifier2);
        return this;
    }

    public void setModifier2(String modifier2) {
        this.modifier2 = modifier2;
    }

    public String getModifier3() {
        return this.modifier3;
    }

    public PrimaryClaimResubmisionServicelines modifier3(String modifier3) {
        this.setModifier3(modifier3);
        return this;
    }

    public void setModifier3(String modifier3) {
        this.modifier3 = modifier3;
    }

    public String getModifier4() {
        return this.modifier4;
    }

    public PrimaryClaimResubmisionServicelines modifier4(String modifier4) {
        this.setModifier4(modifier4);
        return this;
    }

    public void setModifier4(String modifier4) {
        this.modifier4 = modifier4;
    }

    public String getIcdPointer() {
        return this.icdPointer;
    }

    public PrimaryClaimResubmisionServicelines icdPointer(String icdPointer) {
        this.setIcdPointer(icdPointer);
        return this;
    }

    public void setIcdPointer(String icdPointer) {
        this.icdPointer = icdPointer;
    }

    public Long getQty() {
        return this.qty;
    }

    public PrimaryClaimResubmisionServicelines qty(Long qty) {
        this.setQty(qty);
        return this;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getInsertedById() {
        return this.insertedById;
    }

    public PrimaryClaimResubmisionServicelines insertedById(Long insertedById) {
        this.setInsertedById(insertedById);
        return this;
    }

    public void setInsertedById(Long insertedById) {
        this.insertedById = insertedById;
    }

    public LocalDate getNsertedDate() {
        return this.nsertedDate;
    }

    public PrimaryClaimResubmisionServicelines nsertedDate(LocalDate nsertedDate) {
        this.setNsertedDate(nsertedDate);
        return this;
    }

    public void setNsertedDate(LocalDate nsertedDate) {
        this.nsertedDate = nsertedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PrimaryClaimResubmisionServicelines updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PrimaryClaimResubmisionServicelines updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getClaimServicelineControlNo() {
        return this.claimServicelineControlNo;
    }

    public PrimaryClaimResubmisionServicelines claimServicelineControlNo(String claimServicelineControlNo) {
        this.setClaimServicelineControlNo(claimServicelineControlNo);
        return this;
    }

    public void setClaimServicelineControlNo(String claimServicelineControlNo) {
        this.claimServicelineControlNo = claimServicelineControlNo;
    }

    public String getProcedureIdentifier() {
        return this.procedureIdentifier;
    }

    public PrimaryClaimResubmisionServicelines procedureIdentifier(String procedureIdentifier) {
        this.setProcedureIdentifier(procedureIdentifier);
        return this;
    }

    public void setProcedureIdentifier(String procedureIdentifier) {
        this.procedureIdentifier = procedureIdentifier;
    }

    public String getInsertedByName() {
        return this.insertedByName;
    }

    public PrimaryClaimResubmisionServicelines insertedByName(String insertedByName) {
        this.setInsertedByName(insertedByName);
        return this;
    }

    public void setInsertedByName(String insertedByName) {
        this.insertedByName = insertedByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PrimaryClaimResubmisionServicelines updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getStatus() {
        return this.status;
    }

    public PrimaryClaimResubmisionServicelines status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderingProviderFirstName() {
        return this.orderingProviderFirstName;
    }

    public PrimaryClaimResubmisionServicelines orderingProviderFirstName(String orderingProviderFirstName) {
        this.setOrderingProviderFirstName(orderingProviderFirstName);
        return this;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderLastName() {
        return this.orderingProviderLastName;
    }

    public PrimaryClaimResubmisionServicelines orderingProviderLastName(String orderingProviderLastName) {
        this.setOrderingProviderLastName(orderingProviderLastName);
        return this;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return this.orderingProviderNpi;
    }

    public PrimaryClaimResubmisionServicelines orderingProviderNpi(String orderingProviderNpi) {
        this.setOrderingProviderNpi(orderingProviderNpi);
        return this;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getReference() {
        return this.reference;
    }

    public PrimaryClaimResubmisionServicelines reference(String reference) {
        this.setReference(reference);
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public UUID getPrimaryClaimResubmisionServicelinesUuid() {
        return this.primaryClaimResubmisionServicelinesUuid;
    }

    public PrimaryClaimResubmisionServicelines primaryClaimResubmisionServicelinesUuid(UUID primaryClaimResubmisionServicelinesUuid) {
        this.setPrimaryClaimResubmisionServicelinesUuid(primaryClaimResubmisionServicelinesUuid);
        return this;
    }

    public void setPrimaryClaimResubmisionServicelinesUuid(UUID primaryClaimResubmisionServicelinesUuid) {
        this.primaryClaimResubmisionServicelinesUuid = primaryClaimResubmisionServicelinesUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrimaryClaimResubmisionServicelines)) {
            return false;
        }
        return (
            changeHealthPrimaryResubmisionServicelinesId != null &&
            changeHealthPrimaryResubmisionServicelinesId.equals(
                ((PrimaryClaimResubmisionServicelines) o).changeHealthPrimaryResubmisionServicelinesId
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
        return "PrimaryClaimResubmisionServicelines{" +
            "changeHealthPrimaryResubmisionServicelinesId=" + getChangeHealthPrimaryResubmisionServicelinesId() +
            ", changeHealthPrimaryResubmisionMasterId=" + getChangeHealthPrimaryResubmisionMasterId() +
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
            ", nsertedDate='" + getNsertedDate() + "'" +
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
            ", primaryClaimResubmisionServicelinesUuid='" + getPrimaryClaimResubmisionServicelinesUuid() + "'" +
            "}";
    }
}
