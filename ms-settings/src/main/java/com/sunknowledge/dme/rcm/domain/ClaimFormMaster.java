package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ClaimFormMaster.
 */
@Entity
@Table(name = "t_claim_form_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClaimFormMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "claim_form_id")
    private Long claimFormId;

    @Column(name = "claim_form_name")
    private String claimFormName;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "claim_form_master_uuid")
    private UUID claimFormMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getClaimFormId() {
        return this.claimFormId;
    }

    public ClaimFormMaster claimFormId(Long claimFormId) {
        this.setClaimFormId(claimFormId);
        return this;
    }

    public void setClaimFormId(Long claimFormId) {
        this.claimFormId = claimFormId;
    }

    public String getClaimFormName() {
        return this.claimFormName;
    }

    public ClaimFormMaster claimFormName(String claimFormName) {
        this.setClaimFormName(claimFormName);
        return this;
    }

    public void setClaimFormName(String claimFormName) {
        this.claimFormName = claimFormName;
    }

    public String getStatus() {
        return this.status;
    }

    public ClaimFormMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ClaimFormMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ClaimFormMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ClaimFormMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ClaimFormMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ClaimFormMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ClaimFormMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getClaimFormMasterUuid() {
        return this.claimFormMasterUuid;
    }

    public ClaimFormMaster claimFormMasterUuid(UUID claimFormMasterUuid) {
        this.setClaimFormMasterUuid(claimFormMasterUuid);
        return this;
    }

    public void setClaimFormMasterUuid(UUID claimFormMasterUuid) {
        this.claimFormMasterUuid = claimFormMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimFormMaster)) {
            return false;
        }
        return claimFormId != null && claimFormId.equals(((ClaimFormMaster) o).claimFormId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClaimFormMaster{" +
            "claimFormId=" + getClaimFormId() +
            ", claimFormName='" + getClaimFormName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", claimFormMasterUuid='" + getClaimFormMasterUuid() + "'" +
            "}";
    }
}
