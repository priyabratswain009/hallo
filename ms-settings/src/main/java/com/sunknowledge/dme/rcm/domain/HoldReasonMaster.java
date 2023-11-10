package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HoldReasonMaster.
 */
@Entity
@Table(name = "t_hold_reason_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HoldReasonMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "hold_reason_id")
    private Long holdReasonId;

    @Column(name = "hold_reason_name")
    private String holdReasonName;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "status")
    private String status;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "hold_reason_master_uuid")
    private UUID holdReasonMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getHoldReasonId() {
        return this.holdReasonId;
    }

    public HoldReasonMaster holdReasonId(Long holdReasonId) {
        this.setHoldReasonId(holdReasonId);
        return this;
    }

    public void setHoldReasonId(Long holdReasonId) {
        this.holdReasonId = holdReasonId;
    }

    public String getHoldReasonName() {
        return this.holdReasonName;
    }

    public HoldReasonMaster holdReasonName(String holdReasonName) {
        this.setHoldReasonName(holdReasonName);
        return this;
    }

    public void setHoldReasonName(String holdReasonName) {
        this.holdReasonName = holdReasonName;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public HoldReasonMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public HoldReasonMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return this.status;
    }

    public HoldReasonMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public HoldReasonMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public HoldReasonMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public HoldReasonMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public HoldReasonMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getHoldReasonMasterUuid() {
        return this.holdReasonMasterUuid;
    }

    public HoldReasonMaster holdReasonMasterUuid(UUID holdReasonMasterUuid) {
        this.setHoldReasonMasterUuid(holdReasonMasterUuid);
        return this;
    }

    public void setHoldReasonMasterUuid(UUID holdReasonMasterUuid) {
        this.holdReasonMasterUuid = holdReasonMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HoldReasonMaster)) {
            return false;
        }
        return holdReasonId != null && holdReasonId.equals(((HoldReasonMaster) o).holdReasonId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HoldReasonMaster{" +
            "holdReasonId=" + getHoldReasonId() +
            ", holdReasonName='" + getHoldReasonName() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", holdReasonMasterUuid='" + getHoldReasonMasterUuid() + "'" +
            "}";
    }
}
