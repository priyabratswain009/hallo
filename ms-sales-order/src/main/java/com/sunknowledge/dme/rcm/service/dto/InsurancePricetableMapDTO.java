package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.InsurancePricetableMap} entity.
 */
public class InsurancePricetableMapDTO implements Serializable {

    private Long insurancePricetableMapId;

    private String insuranceIdNo;

    private String insuranceName;

    private String insurancePayerIdNo;

    private Long priceTableId;

    private String priceTableName;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID insurancePricetableMapUuid;

    private Long insuranceId;

    public Long getInsurancePricetableMapId() {
        return insurancePricetableMapId;
    }

    public void setInsurancePricetableMapId(Long insurancePricetableMapId) {
        this.insurancePricetableMapId = insurancePricetableMapId;
    }

    public String getInsuranceIdNo() {
        return insuranceIdNo;
    }

    public void setInsuranceIdNo(String insuranceIdNo) {
        this.insuranceIdNo = insuranceIdNo;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsurancePayerIdNo() {
        return insurancePayerIdNo;
    }

    public void setInsurancePayerIdNo(String insurancePayerIdNo) {
        this.insurancePayerIdNo = insurancePayerIdNo;
    }

    public Long getPriceTableId() {
        return priceTableId;
    }

    public void setPriceTableId(Long priceTableId) {
        this.priceTableId = priceTableId;
    }

    public String getPriceTableName() {
        return priceTableName;
    }

    public void setPriceTableName(String priceTableName) {
        this.priceTableName = priceTableName;
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

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
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

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getInsurancePricetableMapUuid() {
        return insurancePricetableMapUuid;
    }

    public void setInsurancePricetableMapUuid(UUID insurancePricetableMapUuid) {
        this.insurancePricetableMapUuid = insurancePricetableMapUuid;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsurancePricetableMapDTO)) {
            return false;
        }

        InsurancePricetableMapDTO insurancePricetableMapDTO = (InsurancePricetableMapDTO) o;
        if (this.insurancePricetableMapId == null) {
            return false;
        }
        return Objects.equals(this.insurancePricetableMapId, insurancePricetableMapDTO.insurancePricetableMapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.insurancePricetableMapId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InsurancePricetableMapDTO{" +
            "insurancePricetableMapId=" + getInsurancePricetableMapId() +
            ", insuranceIdNo='" + getInsuranceIdNo() + "'" +
            ", insuranceName='" + getInsuranceName() + "'" +
            ", insurancePayerIdNo='" + getInsurancePayerIdNo() + "'" +
            ", priceTableId=" + getPriceTableId() +
            ", priceTableName='" + getPriceTableName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", insurancePricetableMapUuid='" + getInsurancePricetableMapUuid() + "'" +
            ", insuranceId=" + getInsuranceId() +
            "}";
    }
}
