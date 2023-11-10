package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A DepositMasterDetails.
 */
@Entity
@Table(name = "t_deposit_master_details")
public class DepositMasterDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "deposit_id", nullable = false)
    private Long depositId;

    @Column(name = "deposit_no")
    private String depositNo;

    @Column(name = "deposit_reference")
    private String depositReference;

    @Column(name = "payor_name")
    private String payorName;

    @Column(name = "deposit_date")
    private LocalDate depositDate;

    @Column(name = "deposit_notes")
    private String depositNotes;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "deposit_master_details_uuid")
    private UUID depositMasterDetailsUuid;

    @Column(name = "claim_cob_835_master_id")
    private Long claimCob835MasterId;

    @Column(name = "deposit_status")
    private String depositStatus;

    @Column(name = "deposit_amount")
    private Double depositAmount;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDepositId() {
        return this.depositId;
    }

    public DepositMasterDetails depositId(Long depositId) {
        this.setDepositId(depositId);
        return this;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public String getDepositNo() {
        return this.depositNo;
    }

    public DepositMasterDetails depositNo(String depositNo) {
        this.setDepositNo(depositNo);
        return this;
    }

    public void setDepositNo(String depositNo) {
        this.depositNo = depositNo;
    }

    public String getDepositReference() {
        return this.depositReference;
    }

    public DepositMasterDetails depositReference(String depositReference) {
        this.setDepositReference(depositReference);
        return this;
    }

    public void setDepositReference(String depositReference) {
        this.depositReference = depositReference;
    }

    public String getPayorName() {
        return this.payorName;
    }

    public DepositMasterDetails payorName(String payorName) {
        this.setPayorName(payorName);
        return this;
    }

    public void setPayorName(String payorName) {
        this.payorName = payorName;
    }

    public LocalDate getDepositDate() {
        return this.depositDate;
    }

    public DepositMasterDetails depositDate(LocalDate depositDate) {
        this.setDepositDate(depositDate);
        return this;
    }

    public void setDepositDate(LocalDate depositDate) {
        this.depositDate = depositDate;
    }

    public String getDepositNotes() {
        return this.depositNotes;
    }

    public DepositMasterDetails depositNotes(String depositNotes) {
        this.setDepositNotes(depositNotes);
        return this;
    }

    public void setDepositNotes(String depositNotes) {
        this.depositNotes = depositNotes;
    }

    public String getStatus() {
        return this.status;
    }

    public DepositMasterDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public DepositMasterDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public DepositMasterDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public DepositMasterDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public DepositMasterDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public DepositMasterDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public DepositMasterDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getDepositMasterDetailsUuid() {
        return this.depositMasterDetailsUuid;
    }

    public DepositMasterDetails depositMasterDetailsUuid(UUID depositMasterDetailsUuid) {
        this.setDepositMasterDetailsUuid(depositMasterDetailsUuid);
        return this;
    }

    public void setDepositMasterDetailsUuid(UUID depositMasterDetailsUuid) {
        this.depositMasterDetailsUuid = depositMasterDetailsUuid;
    }

    public Long getClaimCob835MasterId() {
        return this.claimCob835MasterId;
    }

    public DepositMasterDetails claimCob835MasterId(Long claimCob835MasterId) {
        this.setClaimCob835MasterId(claimCob835MasterId);
        return this;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public String getDepositStatus() {
        return this.depositStatus;
    }

    public DepositMasterDetails depositStatus(String depositStatus) {
        this.setDepositStatus(depositStatus);
        return this;
    }

    public void setDepositStatus(String depositStatus) {
        this.depositStatus = depositStatus;
    }

    public Double getDepositAmount() {
        return this.depositAmount;
    }

    public DepositMasterDetails depositAmount(Double depositAmount) {
        this.setDepositAmount(depositAmount);
        return this;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepositMasterDetails)) {
            return false;
        }
        return depositId != null && depositId.equals(((DepositMasterDetails) o).depositId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepositMasterDetails{" +
            "depositId=" + getDepositId() +
            ", depositNo='" + getDepositNo() + "'" +
            ", depositReference='" + getDepositReference() + "'" +
            ", payorName='" + getPayorName() + "'" +
            ", depositDate='" + getDepositDate() + "'" +
            ", depositNotes='" + getDepositNotes() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", depositMasterDetailsUuid='" + getDepositMasterDetailsUuid() + "'" +
            ", claimCob835MasterId=" + getClaimCob835MasterId() +
            ", depositStatus='" + getDepositStatus() + "'" +
            ", depositAmount=" + getDepositAmount() +
            "}";
    }
}
