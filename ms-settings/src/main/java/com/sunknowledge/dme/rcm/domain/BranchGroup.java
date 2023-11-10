package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BranchGroup.
 */
@Entity
@Table(name = "t_branch_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BranchGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "branch_group_id")
    private Long branchGroupId;

    @Column(name = "branch_group_name")
    private String branchGroupName;

    @Column(name = "company_id")
    private Long companyId;

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

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "branch_group_uuid")
    private UUID branchGroupUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getBranchGroupId() {
        return this.branchGroupId;
    }

    public BranchGroup branchGroupId(Long branchGroupId) {
        this.setBranchGroupId(branchGroupId);
        return this;
    }

    public void setBranchGroupId(Long branchGroupId) {
        this.branchGroupId = branchGroupId;
    }

    public String getBranchGroupName() {
        return this.branchGroupName;
    }

    public BranchGroup branchGroupName(String branchGroupName) {
        this.setBranchGroupName(branchGroupName);
        return this;
    }

    public void setBranchGroupName(String branchGroupName) {
        this.branchGroupName = branchGroupName;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public BranchGroup companyId(Long companyId) {
        this.setCompanyId(companyId);
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getStatus() {
        return this.status;
    }

    public BranchGroup status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public BranchGroup createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public BranchGroup createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public BranchGroup updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public BranchGroup updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public BranchGroup createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public BranchGroup updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getBranchGroupUuid() {
        return this.branchGroupUuid;
    }

    public BranchGroup branchGroupUuid(UUID branchGroupUuid) {
        this.setBranchGroupUuid(branchGroupUuid);
        return this;
    }

    public void setBranchGroupUuid(UUID branchGroupUuid) {
        this.branchGroupUuid = branchGroupUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchGroup)) {
            return false;
        }
        return branchGroupId != null && branchGroupId.equals(((BranchGroup) o).branchGroupId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchGroup{" +
            "branchGroupId=" + getBranchGroupId() +
            ", branchGroupName='" + getBranchGroupName() + "'" +
            ", companyId=" + getCompanyId() +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", branchGroupUuid='" + getBranchGroupUuid() + "'" +
            "}";
    }
}
