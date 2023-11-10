package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.RoleUserMap} entity.
 */
public class RoleUserMapDTO implements Serializable {

    private Long roleUserMapId;

    private Long userId;

    private Long roleId;

    private String description;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private Long updatedById;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private UUID roleUserMapUuid;

    public Long getRoleUserMapId() {
        return roleUserMapId;
    }

    public void setRoleUserMapId(Long roleUserMapId) {
        this.roleUserMapId = roleUserMapId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getRoleUserMapUuid() {
        return roleUserMapUuid;
    }

    public void setRoleUserMapUuid(UUID roleUserMapUuid) {
        this.roleUserMapUuid = roleUserMapUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleUserMapDTO)) {
            return false;
        }

        RoleUserMapDTO roleUserMapDTO = (RoleUserMapDTO) o;
        if (this.roleUserMapId == null) {
            return false;
        }
        return Objects.equals(this.roleUserMapId, roleUserMapDTO.roleUserMapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.roleUserMapId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleUserMapDTO{" +
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
