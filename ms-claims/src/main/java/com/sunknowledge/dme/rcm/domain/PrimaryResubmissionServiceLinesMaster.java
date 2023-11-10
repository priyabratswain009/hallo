package com.sunknowledge.dme.rcm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A PrimaryResubmissionServiceLinesMaster.
 */
@Entity
@Table(name = "t_primary_claim_resubmision_servicelines")
public class PrimaryResubmissionServiceLinesMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "change_health_primary_resubmision_servicelines_id", nullable = false)
    private Long changeHealthPrimaryResubmisionServicelinesId;

    @NotNull
    @Column(name = "change_health_primary_resubmision_master_id", nullable = false)
    private Long changeHealthPrimaryResubmisionMasterId;

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
    private Long insertedById;

    @Column(name = "inserted_date")
    private ZonedDateTime insertedDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private ZonedDateTime updatedDate;

    @Column(name = "claim_serviceline_control_no")
    private String claimServicelineControlNo;

    @NotNull
    @Column(name = "procedure_identifier", nullable = false)
    private String procedureIdentifier;

    @Column(name = "inserted_by_name")
    private String insertedByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

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

    @ManyToOne
    @JsonIgnoreProperties(value = { "serviceLines" }, allowSetters = true)
    private PrimaryClaimsReSubmissionMaster primaryClaimsReSubmissionMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getChangeHealthPrimaryResubmisionServicelinesId() {
        return this.changeHealthPrimaryResubmisionServicelinesId;
    }

    public PrimaryResubmissionServiceLinesMaster changeHealthPrimaryResubmisionServicelinesId(
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

    public PrimaryResubmissionServiceLinesMaster changeHealthPrimaryResubmisionMasterId(Long changeHealthPrimaryResubmisionMasterId) {
        this.setChangeHealthPrimaryResubmisionMasterId(changeHealthPrimaryResubmisionMasterId);
        return this;
    }

    public void setChangeHealthPrimaryResubmisionMasterId(Long changeHealthPrimaryResubmisionMasterId) {
        this.changeHealthPrimaryResubmisionMasterId = changeHealthPrimaryResubmisionMasterId;
    }

    public ZonedDateTime getOriginalDos() {
        return this.originalDos;
    }

    public PrimaryResubmissionServiceLinesMaster originalDos(ZonedDateTime originalDos) {
        this.setOriginalDos(originalDos);
        return this;
    }

    public void setOriginalDos(ZonedDateTime originalDos) {
        this.originalDos = originalDos;
    }

    public ZonedDateTime getDosTo() {
        return this.dosTo;
    }

    public PrimaryResubmissionServiceLinesMaster dosTo(ZonedDateTime dosTo) {
        this.setDosTo(dosTo);
        return this;
    }

    public void setDosTo(ZonedDateTime dosTo) {
        this.dosTo = dosTo;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public PrimaryResubmissionServiceLinesMaster procCode(String procCode) {
        this.setProcCode(procCode);
        return this;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public Double getChargeAmt() {
        return this.chargeAmt;
    }

    public PrimaryResubmissionServiceLinesMaster chargeAmt(Double chargeAmt) {
        this.setChargeAmt(chargeAmt);
        return this;
    }

    public void setChargeAmt(Double chargeAmt) {
        this.chargeAmt = chargeAmt;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public PrimaryResubmissionServiceLinesMaster itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public String getModifier1() {
        return this.modifier1;
    }

    public PrimaryResubmissionServiceLinesMaster modifier1(String modifier1) {
        this.setModifier1(modifier1);
        return this;
    }

    public void setModifier1(String modifier1) {
        this.modifier1 = modifier1;
    }

    public String getModifier2() {
        return this.modifier2;
    }

    public PrimaryResubmissionServiceLinesMaster modifier2(String modifier2) {
        this.setModifier2(modifier2);
        return this;
    }

    public void setModifier2(String modifier2) {
        this.modifier2 = modifier2;
    }

    public String getModifier3() {
        return this.modifier3;
    }

    public PrimaryResubmissionServiceLinesMaster modifier3(String modifier3) {
        this.setModifier3(modifier3);
        return this;
    }

    public void setModifier3(String modifier3) {
        this.modifier3 = modifier3;
    }

    public String getModifier4() {
        return this.modifier4;
    }

    public PrimaryResubmissionServiceLinesMaster modifier4(String modifier4) {
        this.setModifier4(modifier4);
        return this;
    }

    public void setModifier4(String modifier4) {
        this.modifier4 = modifier4;
    }

    public String getIcdPointer() {
        return this.icdPointer;
    }

    public PrimaryResubmissionServiceLinesMaster icdPointer(String icdPointer) {
        this.setIcdPointer(icdPointer);
        return this;
    }

    public void setIcdPointer(String icdPointer) {
        this.icdPointer = icdPointer;
    }

    public Long getQty() {
        return this.qty;
    }

    public PrimaryResubmissionServiceLinesMaster qty(Long qty) {
        this.setQty(qty);
        return this;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getInsertedById() {
        return this.insertedById;
    }

    public PrimaryResubmissionServiceLinesMaster insertedById(Long insertedById) {
        this.setInsertedById(insertedById);
        return this;
    }

    public void setInsertedById(Long insertedById) {
        this.insertedById = insertedById;
    }

    public ZonedDateTime getInsertedDate() {
        return this.insertedDate;
    }

    public PrimaryResubmissionServiceLinesMaster insertedDate(ZonedDateTime insertedDate) {
        this.setInsertedDate(insertedDate);
        return this;
    }

    public void setInsertedDate(ZonedDateTime insertedDate) {
        this.insertedDate = insertedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PrimaryResubmissionServiceLinesMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public ZonedDateTime getUpdatedDate() {
        return this.updatedDate;
    }

    public PrimaryResubmissionServiceLinesMaster updatedDate(ZonedDateTime updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getClaimServicelineControlNo() {
        return this.claimServicelineControlNo;
    }

    public PrimaryResubmissionServiceLinesMaster claimServicelineControlNo(String claimServicelineControlNo) {
        this.setClaimServicelineControlNo(claimServicelineControlNo);
        return this;
    }

    public void setClaimServicelineControlNo(String claimServicelineControlNo) {
        this.claimServicelineControlNo = claimServicelineControlNo;
    }

    public String getProcedureIdentifier() {
        return this.procedureIdentifier;
    }

    public PrimaryResubmissionServiceLinesMaster procedureIdentifier(String procedureIdentifier) {
        this.setProcedureIdentifier(procedureIdentifier);
        return this;
    }

    public void setProcedureIdentifier(String procedureIdentifier) {
        this.procedureIdentifier = procedureIdentifier;
    }

    public String getInsertedByName() {
        return this.insertedByName;
    }

    public PrimaryResubmissionServiceLinesMaster insertedByName(String insertedByName) {
        this.setInsertedByName(insertedByName);
        return this;
    }

    public void setInsertedByName(String insertedByName) {
        this.insertedByName = insertedByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PrimaryResubmissionServiceLinesMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getStatus() {
        return this.status;
    }

    public PrimaryResubmissionServiceLinesMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderingProviderFirstName() {
        return this.orderingProviderFirstName;
    }

    public PrimaryResubmissionServiceLinesMaster orderingProviderFirstName(String orderingProviderFirstName) {
        this.setOrderingProviderFirstName(orderingProviderFirstName);
        return this;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderLastName() {
        return this.orderingProviderLastName;
    }

    public PrimaryResubmissionServiceLinesMaster orderingProviderLastName(String orderingProviderLastName) {
        this.setOrderingProviderLastName(orderingProviderLastName);
        return this;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return this.orderingProviderNpi;
    }

    public PrimaryResubmissionServiceLinesMaster orderingProviderNpi(String orderingProviderNpi) {
        this.setOrderingProviderNpi(orderingProviderNpi);
        return this;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getReference() {
        return this.reference;
    }

    public PrimaryResubmissionServiceLinesMaster reference(String reference) {
        this.setReference(reference);
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public PrimaryClaimsReSubmissionMaster getPrimaryClaimsReSubmissionMaster() {
        return this.primaryClaimsReSubmissionMaster;
    }

    public void setPrimaryClaimsReSubmissionMaster(PrimaryClaimsReSubmissionMaster primaryClaimsReSubmissionMaster) {
        this.primaryClaimsReSubmissionMaster = primaryClaimsReSubmissionMaster;
    }

    public PrimaryResubmissionServiceLinesMaster primaryClaimsReSubmissionMaster(
        PrimaryClaimsReSubmissionMaster primaryClaimsReSubmissionMaster
    ) {
        this.setPrimaryClaimsReSubmissionMaster(primaryClaimsReSubmissionMaster);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrimaryResubmissionServiceLinesMaster)) {
            return false;
        }
        return (
            changeHealthPrimaryResubmisionServicelinesId != null &&
            changeHealthPrimaryResubmisionServicelinesId.equals(
                ((PrimaryResubmissionServiceLinesMaster) o).changeHealthPrimaryResubmisionServicelinesId
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
        return "PrimaryResubmissionServiceLinesMaster{" +
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
            "}";
    }
}
