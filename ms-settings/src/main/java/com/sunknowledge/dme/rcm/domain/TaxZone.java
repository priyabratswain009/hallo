package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TaxZone.
 */
@Entity
@Table(name = "t_tax_zone")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TaxZone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "tax_zone_id")
    private Long taxZoneId;

    @Column(name = "state_code_id")
    private Long stateCodeId;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "tax_rate")
    private Double taxRate;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "tax_zone_uuid")
    private UUID taxZoneUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getTaxZoneId() {
        return this.taxZoneId;
    }

    public TaxZone taxZoneId(Long taxZoneId) {
        this.setTaxZoneId(taxZoneId);
        return this;
    }

    public void setTaxZoneId(Long taxZoneId) {
        this.taxZoneId = taxZoneId;
    }

    public Long getStateCodeId() {
        return this.stateCodeId;
    }

    public TaxZone stateCodeId(Long stateCodeId) {
        this.setStateCodeId(stateCodeId);
        return this;
    }

    public void setStateCodeId(Long stateCodeId) {
        this.stateCodeId = stateCodeId;
    }

    public String getStateName() {
        return this.stateName;
    }

    public TaxZone stateName(String stateName) {
        this.setStateName(stateName);
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return this.stateCode;
    }

    public TaxZone stateCode(String stateCode) {
        this.setStateCode(stateCode);
        return this;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public Double getTaxRate() {
        return this.taxRate;
    }

    public TaxZone taxRate(Double taxRate) {
        this.setTaxRate(taxRate);
        return this;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getStatus() {
        return this.status;
    }

    public TaxZone status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public TaxZone createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public TaxZone createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public TaxZone createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public TaxZone updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public TaxZone updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public TaxZone updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getTaxZoneUuid() {
        return this.taxZoneUuid;
    }

    public TaxZone taxZoneUuid(UUID taxZoneUuid) {
        this.setTaxZoneUuid(taxZoneUuid);
        return this;
    }

    public void setTaxZoneUuid(UUID taxZoneUuid) {
        this.taxZoneUuid = taxZoneUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaxZone)) {
            return false;
        }
        return taxZoneId != null && taxZoneId.equals(((TaxZone) o).taxZoneId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaxZone{" +
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
