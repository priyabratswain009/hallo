package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.TaxZone} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaxZoneDTO implements Serializable {

    private Long taxZoneId;

    private Long stateCodeId;

    private String stateName;

    private String stateCode;

    private Double taxRate;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private LocalDate updatedDate;

    private UUID taxZoneUuid;

    public Long getTaxZoneId() {
        return taxZoneId;
    }

    public void setTaxZoneId(Long taxZoneId) {
        this.taxZoneId = taxZoneId;
    }

    public Long getStateCodeId() {
        return stateCodeId;
    }

    public void setStateCodeId(Long stateCodeId) {
        this.stateCodeId = stateCodeId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getTaxZoneUuid() {
        return taxZoneUuid;
    }

    public void setTaxZoneUuid(UUID taxZoneUuid) {
        this.taxZoneUuid = taxZoneUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaxZoneDTO)) {
            return false;
        }

        TaxZoneDTO taxZoneDTO = (TaxZoneDTO) o;
        if (this.taxZoneId == null) {
            return false;
        }
        return Objects.equals(this.taxZoneId, taxZoneDTO.taxZoneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.taxZoneId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaxZoneDTO{" +
            "taxZoneId=" + getTaxZoneId() +
            ", stateCodeId=" + getStateCodeId() +
            ", stateName='" + getStateName() + "'" +
            ", stateCode='" + getStateCode() + "'" +
            ", taxRate=" + getTaxRate() +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", taxZoneUuid='" + getTaxZoneUuid() + "'" +
            "}";
    }
}
