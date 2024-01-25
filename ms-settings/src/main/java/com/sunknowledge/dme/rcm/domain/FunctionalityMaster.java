package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FunctionalityMaster.
 */
@Entity
@Table(name = "t_functionality_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FunctionalityMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "functionality_id")
    private Long functionalityId;

    @Column(name = "functionality_no")
    private String functionalityNo;

    @Column(name = "functionality_name")
    private String functionalityName;

    @Column(name = "functionality_description")
    private String functionalityDescription;

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

    @Column(name = "functionality_master_uuid")
    private UUID functionalityMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getFunctionalityId() {
        return this.functionalityId;
    }

    public FunctionalityMaster functionalityId(Long functionalityId) {
        this.setFunctionalityId(functionalityId);
        return this;
    }

    public void setFunctionalityId(Long functionalityId) {
        this.functionalityId = functionalityId;
    }

    public String getFunctionalityNo() {
        return this.functionalityNo;
    }

    public FunctionalityMaster functionalityNo(String functionalityNo) {
        this.setFunctionalityNo(functionalityNo);
        return this;
    }

    public void setFunctionalityNo(String functionalityNo) {
        this.functionalityNo = functionalityNo;
    }

    public String getFunctionalityName() {
        return this.functionalityName;
    }

    public FunctionalityMaster functionalityName(String functionalityName) {
        this.setFunctionalityName(functionalityName);
        return this;
    }

    public void setFunctionalityName(String functionalityName) {
        this.functionalityName = functionalityName;
    }

    public String getFunctionalityDescription() {
        return this.functionalityDescription;
    }

    public FunctionalityMaster functionalityDescription(String functionalityDescription) {
        this.setFunctionalityDescription(functionalityDescription);
        return this;
    }

    public void setFunctionalityDescription(String functionalityDescription) {
        this.functionalityDescription = functionalityDescription;
    }

    public String getStatus() {
        return this.status;
    }

    public FunctionalityMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public FunctionalityMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public FunctionalityMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public FunctionalityMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public FunctionalityMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public FunctionalityMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public FunctionalityMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getFunctionalityMasterUuid() {
        return this.functionalityMasterUuid;
    }

    public FunctionalityMaster functionalityMasterUuid(UUID functionalityMasterUuid) {
        this.setFunctionalityMasterUuid(functionalityMasterUuid);
        return this;
    }

    public void setFunctionalityMasterUuid(UUID functionalityMasterUuid) {
        this.functionalityMasterUuid = functionalityMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FunctionalityMaster)) {
            return false;
        }
        return functionalityId != null && functionalityId.equals(((FunctionalityMaster) o).functionalityId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FunctionalityMaster{" +
            "functionalityId=" + getFunctionalityId() +
            ", functionalityNo='" + getFunctionalityNo() + "'" +
            ", functionalityName='" + getFunctionalityName() + "'" +
            ", functionalityDescription='" + getFunctionalityDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", functionalityMasterUuid='" + getFunctionalityMasterUuid() + "'" +
            "}";
    }
}
