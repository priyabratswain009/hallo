package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A InsuranceGroupMaster.
 */
@Entity
@Table(name = "t_insurance_group_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InsuranceGroupMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "insurance_group_master_id")
    private Long insuranceGroupMasterId;

    @Column(name = "insurance_group_master_name")
    private String insuranceGroupMasterName;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "insurance_group_master_uuid")
    private UUID insuranceGroupMasterUuid;

    @Column(name = "insurance_group_master_no")
    private String insuranceGroupMasterNo;

    @Column(name = "insurance_group_master_description")
    private String insuranceGroupMasterDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getInsuranceGroupMasterId() {
        return this.insuranceGroupMasterId;
    }

    public InsuranceGroupMaster insuranceGroupMasterId(Long insuranceGroupMasterId) {
        this.setInsuranceGroupMasterId(insuranceGroupMasterId);
        return this;
    }

    public void setInsuranceGroupMasterId(Long insuranceGroupMasterId) {
        this.insuranceGroupMasterId = insuranceGroupMasterId;
    }

    public String getInsuranceGroupMasterName() {
        return this.insuranceGroupMasterName;
    }

    public InsuranceGroupMaster insuranceGroupMasterName(String insuranceGroupMasterName) {
        this.setInsuranceGroupMasterName(insuranceGroupMasterName);
        return this;
    }

    public void setInsuranceGroupMasterName(String insuranceGroupMasterName) {
        this.insuranceGroupMasterName = insuranceGroupMasterName;
    }

    public String getStatus() {
        return this.status;
    }

    public InsuranceGroupMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public InsuranceGroupMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public InsuranceGroupMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public InsuranceGroupMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public InsuranceGroupMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public InsuranceGroupMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public InsuranceGroupMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getInsuranceGroupMasterUuid() {
        return this.insuranceGroupMasterUuid;
    }

    public InsuranceGroupMaster insuranceGroupMasterUuid(UUID insuranceGroupMasterUuid) {
        this.setInsuranceGroupMasterUuid(insuranceGroupMasterUuid);
        return this;
    }

    public void setInsuranceGroupMasterUuid(UUID insuranceGroupMasterUuid) {
        this.insuranceGroupMasterUuid = insuranceGroupMasterUuid;
    }

    public String getInsuranceGroupMasterNo() {
        return this.insuranceGroupMasterNo;
    }

    public InsuranceGroupMaster insuranceGroupMasterNo(String insuranceGroupMasterNo) {
        this.setInsuranceGroupMasterNo(insuranceGroupMasterNo);
        return this;
    }

    public void setInsuranceGroupMasterNo(String insuranceGroupMasterNo) {
        this.insuranceGroupMasterNo = insuranceGroupMasterNo;
    }

    public String getInsuranceGroupMasterDescription() {
        return this.insuranceGroupMasterDescription;
    }

    public InsuranceGroupMaster insuranceGroupMasterDescription(String insuranceGroupMasterDescription) {
        this.setInsuranceGroupMasterDescription(insuranceGroupMasterDescription);
        return this;
    }

    public void setInsuranceGroupMasterDescription(String insuranceGroupMasterDescription) {
        this.insuranceGroupMasterDescription = insuranceGroupMasterDescription;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsuranceGroupMaster)) {
            return false;
        }
        return insuranceGroupMasterId != null && insuranceGroupMasterId.equals(((InsuranceGroupMaster) o).insuranceGroupMasterId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InsuranceGroupMaster{" +
            "insuranceGroupMasterId=" + getInsuranceGroupMasterId() +
            ", insuranceGroupMasterName='" + getInsuranceGroupMasterName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", insuranceGroupMasterUuid='" + getInsuranceGroupMasterUuid() + "'" +
            ", insuranceGroupMasterNo='" + getInsuranceGroupMasterNo() + "'" +
            ", insuranceGroupMasterDescription='" + getInsuranceGroupMasterDescription() + "'" +
            "}";
    }
}
