package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.RoleMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RoleMasterDTO implements Serializable {

    private Long roleId;

    private String roleCode;

    private String roleName;

    private String roleDescription;

    private String roleNo;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private Long updatedById;

    private String createdByName;

    private String updatedByName;

    private UUID roleMasterUuid;

    private LocalDate updatedDate;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
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

    public UUID getRoleMasterUuid() {
        return roleMasterUuid;
    }

    public void setRoleMasterUuid(UUID roleMasterUuid) {
        this.roleMasterUuid = roleMasterUuid;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleMasterDTO)) {
            return false;
        }

        RoleMasterDTO roleMasterDTO = (RoleMasterDTO) o;
        if (this.roleId == null) {
            return false;
        }
        return Objects.equals(this.roleId, roleMasterDTO.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.roleId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleMasterDTO{" +
            "roleId=" + getRoleId() +
            ", roleCode='" + getRoleCode() + "'" +
            ", roleName='" + getRoleName() + "'" +
            ", roleDescription='" + getRoleDescription() + "'" +
            ", roleNo='" + getRoleNo() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", roleMasterUuid='" + getRoleMasterUuid() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
}
