package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A DepreciationMethod.
 */
@Entity
@Table(name = "t_depreciation_method")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DepreciationMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "depreciation_method_id")
    private Long depreciationMethodId;

    @Column(name = "depreciation_method_name")
    private String depreciationMethodName;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "depreciation_method_uuid")
    private UUID depreciationMethodUuid;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDepreciationMethodId() {
        return this.depreciationMethodId;
    }

    public DepreciationMethod depreciationMethodId(Long depreciationMethodId) {
        this.setDepreciationMethodId(depreciationMethodId);
        return this;
    }

    public void setDepreciationMethodId(Long depreciationMethodId) {
        this.depreciationMethodId = depreciationMethodId;
    }

    public String getDepreciationMethodName() {
        return this.depreciationMethodName;
    }

    public DepreciationMethod depreciationMethodName(String depreciationMethodName) {
        this.setDepreciationMethodName(depreciationMethodName);
        return this;
    }

    public void setDepreciationMethodName(String depreciationMethodName) {
        this.depreciationMethodName = depreciationMethodName;
    }

    public String getStatus() {
        return this.status;
    }

    public DepreciationMethod status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public DepreciationMethod createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public DepreciationMethod createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public DepreciationMethod createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public DepreciationMethod updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public DepreciationMethod updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getDepreciationMethodUuid() {
        return this.depreciationMethodUuid;
    }

    public DepreciationMethod depreciationMethodUuid(UUID depreciationMethodUuid) {
        this.setDepreciationMethodUuid(depreciationMethodUuid);
        return this;
    }

    public void setDepreciationMethodUuid(UUID depreciationMethodUuid) {
        this.depreciationMethodUuid = depreciationMethodUuid;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public DepreciationMethod updatedDate(LocalDate updatedDate) {
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
        if (!(o instanceof DepreciationMethod)) {
            return false;
        }
        return depreciationMethodId != null && depreciationMethodId.equals(((DepreciationMethod) o).depreciationMethodId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepreciationMethod{" +
            "depreciationMethodId=" + getDepreciationMethodId() +
            ", depreciationMethodName='" + getDepreciationMethodName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", depreciationMethodUuid='" + getDepreciationMethodUuid() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
}
