package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.InsuranceGroupMaster} entity.
 */
public class InsuranceGroupMasterDTO implements Serializable {

    private Long insuranceGroupMasterId;

    private String insuranceGroupMasterName;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID insuranceGroupMasterUuid;

    private String insuranceGroupMasterNo;

    private String insuranceGroupMasterDescription;

    public Long getInsuranceGroupMasterId() {
        return insuranceGroupMasterId;
    }

    public void setInsuranceGroupMasterId(Long insuranceGroupMasterId) {
        this.insuranceGroupMasterId = insuranceGroupMasterId;
    }

    public String getInsuranceGroupMasterName() {
        return insuranceGroupMasterName;
    }

    public void setInsuranceGroupMasterName(String insuranceGroupMasterName) {
        this.insuranceGroupMasterName = insuranceGroupMasterName;
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

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getInsuranceGroupMasterUuid() {
        return insuranceGroupMasterUuid;
    }

    public void setInsuranceGroupMasterUuid(UUID insuranceGroupMasterUuid) {
        this.insuranceGroupMasterUuid = insuranceGroupMasterUuid;
    }

    public String getInsuranceGroupMasterNo() {
        return insuranceGroupMasterNo;
    }

    public void setInsuranceGroupMasterNo(String insuranceGroupMasterNo) {
        this.insuranceGroupMasterNo = insuranceGroupMasterNo;
    }

    public String getInsuranceGroupMasterDescription() {
        return insuranceGroupMasterDescription;
    }

    public void setInsuranceGroupMasterDescription(String insuranceGroupMasterDescription) {
        this.insuranceGroupMasterDescription = insuranceGroupMasterDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsuranceGroupMasterDTO)) {
            return false;
        }

        InsuranceGroupMasterDTO insuranceGroupMasterDTO = (InsuranceGroupMasterDTO) o;
        if (this.insuranceGroupMasterId == null) {
            return false;
        }
        return Objects.equals(this.insuranceGroupMasterId, insuranceGroupMasterDTO.insuranceGroupMasterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.insuranceGroupMasterId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InsuranceGroupMasterDTO{" +
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
