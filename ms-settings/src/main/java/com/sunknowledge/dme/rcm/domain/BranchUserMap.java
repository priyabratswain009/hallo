package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BranchUserMap.
 */
@Entity
@Table(name = "t_branch_user_map")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BranchUserMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "map_id")
    private Long mapId;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "branch_user_map_uuid")
    private UUID branchUserMapUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getMapId() {
        return this.mapId;
    }

    public BranchUserMap mapId(Long mapId) {
        this.setMapId(mapId);
        return this;
    }

    public void setMapId(Long mapId) {
        this.mapId = mapId;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public BranchUserMap branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public BranchUserMap userId(Long userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return this.description;
    }

    public BranchUserMap description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public BranchUserMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public BranchUserMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public BranchUserMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public BranchUserMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public BranchUserMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public BranchUserMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public BranchUserMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public UUID getBranchUserMapUuid() {
        return this.branchUserMapUuid;
    }

    public BranchUserMap branchUserMapUuid(UUID branchUserMapUuid) {
        this.setBranchUserMapUuid(branchUserMapUuid);
        return this;
    }

    public void setBranchUserMapUuid(UUID branchUserMapUuid) {
        this.branchUserMapUuid = branchUserMapUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchUserMap)) {
            return false;
        }
        return mapId != null && mapId.equals(((BranchUserMap) o).mapId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchUserMap{" +
            "mapId=" + getMapId() +
            ", branchId=" + getBranchId() +
            ", userId=" + getUserId() +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", branchUserMapUuid='" + getBranchUserMapUuid() + "'" +
            "}";
    }
}
