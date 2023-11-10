package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BranchItemLocationMap.
 */
@Entity
@Table(name = "t_branch_item_location_map")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BranchItemLocationMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "branch_item_location_map_id")
    private Long branchItemLocationMapId;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "item_location_id")
    private Long itemLocationId;

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

    @Column(name = "branch_item_location_map_uuid")
    private UUID branchItemLocationMapUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getBranchItemLocationMapId() {
        return this.branchItemLocationMapId;
    }

    public BranchItemLocationMap branchItemLocationMapId(Long branchItemLocationMapId) {
        this.setBranchItemLocationMapId(branchItemLocationMapId);
        return this;
    }

    public void setBranchItemLocationMapId(Long branchItemLocationMapId) {
        this.branchItemLocationMapId = branchItemLocationMapId;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public BranchItemLocationMap branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getItemLocationId() {
        return this.itemLocationId;
    }

    public BranchItemLocationMap itemLocationId(Long itemLocationId) {
        this.setItemLocationId(itemLocationId);
        return this;
    }

    public void setItemLocationId(Long itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    public String getStatus() {
        return this.status;
    }

    public BranchItemLocationMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public BranchItemLocationMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public BranchItemLocationMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public BranchItemLocationMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public BranchItemLocationMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public BranchItemLocationMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public BranchItemLocationMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getBranchItemLocationMapUuid() {
        return this.branchItemLocationMapUuid;
    }

    public BranchItemLocationMap branchItemLocationMapUuid(UUID branchItemLocationMapUuid) {
        this.setBranchItemLocationMapUuid(branchItemLocationMapUuid);
        return this;
    }

    public void setBranchItemLocationMapUuid(UUID branchItemLocationMapUuid) {
        this.branchItemLocationMapUuid = branchItemLocationMapUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchItemLocationMap)) {
            return false;
        }
        return branchItemLocationMapId != null && branchItemLocationMapId.equals(((BranchItemLocationMap) o).branchItemLocationMapId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchItemLocationMap{" +
            "branchItemLocationMapId=" + getBranchItemLocationMapId() +
            ", branchId=" + getBranchId() +
            ", itemLocationId=" + getItemLocationId() +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", branchItemLocationMapUuid='" + getBranchItemLocationMapUuid() + "'" +
            "}";
    }
}
