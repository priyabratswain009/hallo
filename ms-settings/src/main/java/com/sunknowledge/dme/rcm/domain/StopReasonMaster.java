package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A StopReasonMaster.
 */
@Entity
@Table(name = "t_stop_reason_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StopReasonMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "stop_reason_id")
    private Long stopReasonId;

    @Column(name = "stop_reason_name")
    private String stopReasonName;

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

    @Column(name = "stop_reason_master_uuid")
    private UUID stopReasonMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getStopReasonId() {
        return this.stopReasonId;
    }

    public StopReasonMaster stopReasonId(Long stopReasonId) {
        this.setStopReasonId(stopReasonId);
        return this;
    }

    public void setStopReasonId(Long stopReasonId) {
        this.stopReasonId = stopReasonId;
    }

    public String getStopReasonName() {
        return this.stopReasonName;
    }

    public StopReasonMaster stopReasonName(String stopReasonName) {
        this.setStopReasonName(stopReasonName);
        return this;
    }

    public void setStopReasonName(String stopReasonName) {
        this.stopReasonName = stopReasonName;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public StopReasonMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public StopReasonMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return this.status;
    }

    public StopReasonMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public StopReasonMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public StopReasonMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public StopReasonMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public StopReasonMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getStopReasonMasterUuid() {
        return this.stopReasonMasterUuid;
    }

    public StopReasonMaster stopReasonMasterUuid(UUID stopReasonMasterUuid) {
        this.setStopReasonMasterUuid(stopReasonMasterUuid);
        return this;
    }

    public void setStopReasonMasterUuid(UUID stopReasonMasterUuid) {
        this.stopReasonMasterUuid = stopReasonMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StopReasonMaster)) {
            return false;
        }
        return stopReasonId != null && stopReasonId.equals(((StopReasonMaster) o).stopReasonId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StopReasonMaster{" +
            "stopReasonId=" + getStopReasonId() +
            ", stopReasonName='" + getStopReasonName() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", stopReasonMasterUuid='" + getStopReasonMasterUuid() + "'" +
            "}";
    }
}
