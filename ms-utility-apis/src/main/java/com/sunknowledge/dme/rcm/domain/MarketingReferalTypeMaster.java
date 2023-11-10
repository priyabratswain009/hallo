package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A MarketingReferalTypeMaster.
 */
@Entity
@Table(name = "t_marketing_referal_type_master")
public class MarketingReferalTypeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "marketing_referral_type_id")
    private Long marketingReferralTypeId;

    @Column(name = "marketing_referral_type_code")
    private String marketingReferralTypeCode;

    @Column(name = "marketing_referal_type_description")
    private String marketingReferalTypeDescription;

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

    @Column(name = "marketing_referal_type_master_uuid")
    private UUID marketingReferalTypeMasterUUID;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getMarketingReferralTypeId() {
        return this.marketingReferralTypeId;
    }

    public MarketingReferalTypeMaster marketingReferralTypeId(Long marketingReferralTypeId) {
        this.setMarketingReferralTypeId(marketingReferralTypeId);
        return this;
    }

    public void setMarketingReferralTypeId(Long marketingReferralTypeId) {
        this.marketingReferralTypeId = marketingReferralTypeId;
    }

    public String getMarketingReferralTypeCode() {
        return this.marketingReferralTypeCode;
    }

    public MarketingReferalTypeMaster marketingReferralTypeCode(String marketingReferralTypeCode) {
        this.setMarketingReferralTypeCode(marketingReferralTypeCode);
        return this;
    }

    public void setMarketingReferralTypeCode(String marketingReferralTypeCode) {
        this.marketingReferralTypeCode = marketingReferralTypeCode;
    }

    public String getMarketingReferalTypeDescription() {
        return this.marketingReferalTypeDescription;
    }

    public MarketingReferalTypeMaster marketingReferalTypeDescription(String marketingReferalTypeDescription) {
        this.setMarketingReferalTypeDescription(marketingReferalTypeDescription);
        return this;
    }

    public void setMarketingReferalTypeDescription(String marketingReferalTypeDescription) {
        this.marketingReferalTypeDescription = marketingReferalTypeDescription;
    }

    public String getStatus() {
        return this.status;
    }

    public MarketingReferalTypeMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public MarketingReferalTypeMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public MarketingReferalTypeMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public MarketingReferalTypeMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public MarketingReferalTypeMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public MarketingReferalTypeMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public MarketingReferalTypeMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getMarketingReferalTypeMasterUUID() {
        return this.marketingReferalTypeMasterUUID;
    }

    public MarketingReferalTypeMaster marketingReferalTypeMasterUUID(UUID marketingReferalTypeMasterUUID) {
        this.setMarketingReferalTypeMasterUUID(marketingReferalTypeMasterUUID);
        return this;
    }

    public void setMarketingReferalTypeMasterUUID(UUID marketingReferalTypeMasterUUID) {
        this.marketingReferalTypeMasterUUID = marketingReferalTypeMasterUUID;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarketingReferalTypeMaster)) {
            return false;
        }
        return marketingReferralTypeId != null && marketingReferralTypeId.equals(((MarketingReferalTypeMaster) o).marketingReferralTypeId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MarketingReferalTypeMaster{" +
            "marketingReferralTypeId=" + getMarketingReferralTypeId() +
            ", marketingReferralTypeCode='" + getMarketingReferralTypeCode() + "'" +
            ", marketingReferalTypeDescription='" + getMarketingReferalTypeDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", marketingReferalTypeMasterUUID='" + getMarketingReferalTypeMasterUUID() + "'" +
            "}";
    }
}
