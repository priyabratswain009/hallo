package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RoleUserMap.
 */
@Entity
@Table(name = "t_role_user_map")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RoleUserMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "role_user_map_id")
    private Long roleUserMapId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "role_user_map_uuid")
    private UUID roleUserMapUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getRoleUserMapId() {
        return this.roleUserMapId;
    }

    public RoleUserMap roleUserMapId(Long roleUserMapId) {
        this.setRoleUserMapId(roleUserMapId);
        return this;
    }

    public void setRoleUserMapId(Long roleUserMapId) {
        this.roleUserMapId = roleUserMapId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public RoleUserMap userId(Long userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public RoleUserMap roleId(Long roleId) {
        this.setRoleId(roleId);
        return this;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return this.description;
    }

    public RoleUserMap description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public RoleUserMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public RoleUserMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public RoleUserMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public RoleUserMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public RoleUserMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public RoleUserMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public RoleUserMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getRoleUserMapUuid() {
        return this.roleUserMapUuid;
    }

    public RoleUserMap roleUserMapUuid(UUID roleUserMapUuid) {
        this.setRoleUserMapUuid(roleUserMapUuid);
        return this;
    }

    public void setRoleUserMapUuid(UUID roleUserMapUuid) {
        this.roleUserMapUuid = roleUserMapUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleUserMap)) {
            return false;
        }
        return roleUserMapId != null && roleUserMapId.equals(((RoleUserMap) o).roleUserMapId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleUserMap{" +
            "roleUserMapId=" + getRoleUserMapId() +
            ", userId=" + getUserId() +
            ", roleId=" + getRoleId() +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", roleUserMapUuid='" + getRoleUserMapUuid() + "'" +
            "}";
    }
}
