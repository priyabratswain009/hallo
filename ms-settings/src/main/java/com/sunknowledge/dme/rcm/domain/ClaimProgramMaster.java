package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ClaimProgramMaster.
 */
@Entity
@Table(name = "t_claim_program_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClaimProgramMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "claim_program_master_id")
    private Long claimProgramMasterId;

    @Column(name = "claim_program_master_key")
    private String claimProgramMasterKey;

    @Column(name = "claim_program_master_uuid")
    private UUID claimProgramMasterUuid;

    @Column(name = "claim_program_master_value")
    private String claimProgramMasterValue;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "status")
    private String status;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getClaimProgramMasterId() {
        return this.claimProgramMasterId;
    }

    public ClaimProgramMaster claimProgramMasterId(Long claimProgramMasterId) {
        this.setClaimProgramMasterId(claimProgramMasterId);
        return this;
    }

    public void setClaimProgramMasterId(Long claimProgramMasterId) {
        this.claimProgramMasterId = claimProgramMasterId;
    }

    public String getClaimProgramMasterKey() {
        return this.claimProgramMasterKey;
    }

    public ClaimProgramMaster claimProgramMasterKey(String claimProgramMasterKey) {
        this.setClaimProgramMasterKey(claimProgramMasterKey);
        return this;
    }

    public void setClaimProgramMasterKey(String claimProgramMasterKey) {
        this.claimProgramMasterKey = claimProgramMasterKey;
    }

    public UUID getClaimProgramMasterUuid() {
        return this.claimProgramMasterUuid;
    }

    public ClaimProgramMaster claimProgramMasterUuid(UUID claimProgramMasterUuid) {
        this.setClaimProgramMasterUuid(claimProgramMasterUuid);
        return this;
    }

    public void setClaimProgramMasterUuid(UUID claimProgramMasterUuid) {
        this.claimProgramMasterUuid = claimProgramMasterUuid;
    }

    public String getClaimProgramMasterValue() {
        return this.claimProgramMasterValue;
    }

    public ClaimProgramMaster claimProgramMasterValue(String claimProgramMasterValue) {
        this.setClaimProgramMasterValue(claimProgramMasterValue);
        return this;
    }

    public void setClaimProgramMasterValue(String claimProgramMasterValue) {
        this.claimProgramMasterValue = claimProgramMasterValue;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ClaimProgramMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ClaimProgramMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ClaimProgramMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return this.status;
    }

    public ClaimProgramMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ClaimProgramMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ClaimProgramMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ClaimProgramMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimProgramMaster)) {
            return false;
        }
        return claimProgramMasterId != null && claimProgramMasterId.equals(((ClaimProgramMaster) o).claimProgramMasterId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimProgramMaster{" +
            "claimProgramMasterId=" + getClaimProgramMasterId() +
            ", claimProgramMasterKey='" + getClaimProgramMasterKey() + "'" +
            ", claimProgramMasterUuid='" + getClaimProgramMasterUuid() + "'" +
            ", claimProgramMasterValue='" + getClaimProgramMasterValue() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
}
