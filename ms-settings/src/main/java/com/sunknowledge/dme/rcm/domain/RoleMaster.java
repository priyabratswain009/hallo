package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RoleMaster.
 */
@Entity
@Table(name = "t_role_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RoleMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_description")
    private String roleDescription;

    @Column(name = "role_no")
    private String roleNo;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "role_master_uuid")
    private UUID roleMasterUuid;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getRoleId() {
        return this.roleId;
    }

    public RoleMaster roleId(Long roleId) {
        this.setRoleId(roleId);
        return this;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return this.roleCode;
    }

    public RoleMaster roleCode(String roleCode) {
        this.setRoleCode(roleCode);
        return this;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public RoleMaster roleName(String roleName) {
        this.setRoleName(roleName);
        return this;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return this.roleDescription;
    }

    public RoleMaster roleDescription(String roleDescription) {
        this.setRoleDescription(roleDescription);
        return this;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleNo() {
        return this.roleNo;
    }

    public RoleMaster roleNo(String roleNo) {
        this.setRoleNo(roleNo);
        return this;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public String getStatus() {
        return this.status;
    }

    public RoleMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public RoleMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public RoleMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public RoleMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public RoleMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public RoleMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getRoleMasterUuid() {
        return this.roleMasterUuid;
    }

    public RoleMaster roleMasterUuid(UUID roleMasterUuid) {
        this.setRoleMasterUuid(roleMasterUuid);
        return this;
    }

    public void setRoleMasterUuid(UUID roleMasterUuid) {
        this.roleMasterUuid = roleMasterUuid;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public RoleMaster updatedDate(LocalDate updatedDate) {
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
        if (!(o instanceof RoleMaster)) {
            return false;
        }
        return roleId != null && roleId.equals(((RoleMaster) o).roleId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleMaster{" +
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
