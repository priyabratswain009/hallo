package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RoleFunctionalityMap.
 */
@Entity
@Table(name = "t_role_functionality_map")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RoleFunctionalityMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "role_functionality_map_id")
    private Long roleFunctionalityMapId;

    @Column(name = "functionality_id")
    private Long functionalityId;

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

    @Column(name = "role_functionality_map_uuid")
    private UUID roleFunctionalityMapUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getRoleFunctionalityMapId() {
        return this.roleFunctionalityMapId;
    }

    public RoleFunctionalityMap roleFunctionalityMapId(Long roleFunctionalityMapId) {
        this.setRoleFunctionalityMapId(roleFunctionalityMapId);
        return this;
    }

    public void setRoleFunctionalityMapId(Long roleFunctionalityMapId) {
        this.roleFunctionalityMapId = roleFunctionalityMapId;
    }

    public Long getFunctionalityId() {
        return this.functionalityId;
    }

    public RoleFunctionalityMap functionalityId(Long functionalityId) {
        this.setFunctionalityId(functionalityId);
        return this;
    }

    public void setFunctionalityId(Long functionalityId) {
        this.functionalityId = functionalityId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public RoleFunctionalityMap roleId(Long roleId) {
        this.setRoleId(roleId);
        return this;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return this.description;
    }

    public RoleFunctionalityMap description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public RoleFunctionalityMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public RoleFunctionalityMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public RoleFunctionalityMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public RoleFunctionalityMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public RoleFunctionalityMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public RoleFunctionalityMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public RoleFunctionalityMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getRoleFunctionalityMapUuid() {
        return this.roleFunctionalityMapUuid;
    }

    public RoleFunctionalityMap roleFunctionalityMapUuid(UUID roleFunctionalityMapUuid) {
        this.setRoleFunctionalityMapUuid(roleFunctionalityMapUuid);
        return this;
    }

    public void setRoleFunctionalityMapUuid(UUID roleFunctionalityMapUuid) {
        this.roleFunctionalityMapUuid = roleFunctionalityMapUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleFunctionalityMap)) {
            return false;
        }
        return roleFunctionalityMapId != null && roleFunctionalityMapId.equals(((RoleFunctionalityMap) o).roleFunctionalityMapId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleFunctionalityMap{" +
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
