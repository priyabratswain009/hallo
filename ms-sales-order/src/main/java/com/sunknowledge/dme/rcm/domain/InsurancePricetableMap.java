package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A InsurancePricetableMap.
 */
@Table("t_insurance_pricetable_map")
public class InsurancePricetableMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("insurance_pricetable_map_id")
    private Long insurancePricetableMapId;

    @Column("insurance_id_no")
    private String insuranceIdNo;

    @Column("insurance_name")
    private String insuranceName;

    @Column("insurance_payer_id_no")
    private String insurancePayerIdNo;

    @Column("price_table_id")
    private Long priceTableId;

    @Column("price_table_name")
    private String priceTableName;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("insurance_pricetable_map_uuid")
    private UUID insurancePricetableMapUuid;

    @Column("insurance_id")
    private Long insuranceId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getInsurancePricetableMapId() {
        return this.insurancePricetableMapId;
    }

    public InsurancePricetableMap insurancePricetableMapId(Long insurancePricetableMapId) {
        this.setInsurancePricetableMapId(insurancePricetableMapId);
        return this;
    }

    public void setInsurancePricetableMapId(Long insurancePricetableMapId) {
        this.insurancePricetableMapId = insurancePricetableMapId;
    }

    public String getInsuranceIdNo() {
        return this.insuranceIdNo;
    }

    public InsurancePricetableMap insuranceIdNo(String insuranceIdNo) {
        this.setInsuranceIdNo(insuranceIdNo);
        return this;
    }

    public void setInsuranceIdNo(String insuranceIdNo) {
        this.insuranceIdNo = insuranceIdNo;
    }

    public String getInsuranceName() {
        return this.insuranceName;
    }

    public InsurancePricetableMap insuranceName(String insuranceName) {
        this.setInsuranceName(insuranceName);
        return this;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsurancePayerIdNo() {
        return this.insurancePayerIdNo;
    }

    public InsurancePricetableMap insurancePayerIdNo(String insurancePayerIdNo) {
        this.setInsurancePayerIdNo(insurancePayerIdNo);
        return this;
    }

    public void setInsurancePayerIdNo(String insurancePayerIdNo) {
        this.insurancePayerIdNo = insurancePayerIdNo;
    }

    public Long getPriceTableId() {
        return this.priceTableId;
    }

    public InsurancePricetableMap priceTableId(Long priceTableId) {
        this.setPriceTableId(priceTableId);
        return this;
    }

    public void setPriceTableId(Long priceTableId) {
        this.priceTableId = priceTableId;
    }

    public String getPriceTableName() {
        return this.priceTableName;
    }

    public InsurancePricetableMap priceTableName(String priceTableName) {
        this.setPriceTableName(priceTableName);
        return this;
    }

    public void setPriceTableName(String priceTableName) {
        this.priceTableName = priceTableName;
    }

    public String getStatus() {
        return this.status;
    }

    public InsurancePricetableMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public InsurancePricetableMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public InsurancePricetableMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public InsurancePricetableMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public InsurancePricetableMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public InsurancePricetableMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public InsurancePricetableMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getInsurancePricetableMapUuid() {
        return this.insurancePricetableMapUuid;
    }

    public InsurancePricetableMap insurancePricetableMapUuid(UUID insurancePricetableMapUuid) {
        this.setInsurancePricetableMapUuid(insurancePricetableMapUuid);
        return this;
    }

    public void setInsurancePricetableMapUuid(UUID insurancePricetableMapUuid) {
        this.insurancePricetableMapUuid = insurancePricetableMapUuid;
    }

    public Long getInsuranceId() {
        return this.insuranceId;
    }

    public InsurancePricetableMap insuranceId(Long insuranceId) {
        this.setInsuranceId(insuranceId);
        return this;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsurancePricetableMap)) {
            return false;
        }
        return insurancePricetableMapId != null && insurancePricetableMapId.equals(((InsurancePricetableMap) o).insurancePricetableMapId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InsurancePricetableMap{" +
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
