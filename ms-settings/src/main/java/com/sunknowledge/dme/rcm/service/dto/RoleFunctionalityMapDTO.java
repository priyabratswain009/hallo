package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.RoleFunctionalityMap} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RoleFunctionalityMapDTO implements Serializable {

    private Long roleFunctionalityMapId;

    private Long functionalityId;

    private Long roleId;

    private String description;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private Long updatedById;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private UUID roleFunctionalityMapUuid;

    public Long getRoleFunctionalityMapId() {
        return roleFunctionalityMapId;
    }

    public void setRoleFunctionalityMapId(Long roleFunctionalityMapId) {
        this.roleFunctionalityMapId = roleFunctionalityMapId;
    }

    public Long getFunctionalityId() {
        return functionalityId;
    }

    public void setFunctionalityId(Long functionalityId) {
        this.functionalityId = functionalityId;
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

    public UUID getRoleFunctionalityMapUuid() {
        return roleFunctionalityMapUuid;
    }

    public void setRoleFunctionalityMapUuid(UUID roleFunctionalityMapUuid) {
        this.roleFunctionalityMapUuid = roleFunctionalityMapUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleFunctionalityMapDTO)) {
            return false;
        }

        RoleFunctionalityMapDTO roleFunctionalityMapDTO = (RoleFunctionalityMapDTO) o;
        if (this.roleFunctionalityMapId == null) {
            return false;
        }
        return Objects.equals(this.roleFunctionalityMapId, roleFunctionalityMapDTO.roleFunctionalityMapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.roleFunctionalityMapId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleFunctionalityMapDTO{" +
            "roleFunctionalityMapId=" + getRoleFunctionalityMapId() +
            ", functionalityId=" + getFunctionalityId() +
            ", roleId=" + getRoleId() +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", roleFunctionalityMapUuid='" + getRoleFunctionalityMapUuid() + "'" +
            "}";
    }
}
