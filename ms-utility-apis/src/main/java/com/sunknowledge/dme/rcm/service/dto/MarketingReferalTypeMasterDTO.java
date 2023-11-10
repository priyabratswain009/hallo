package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.MarketingReferalTypeMaster} entity.
 */
public class MarketingReferalTypeMasterDTO implements Serializable {

    private Long marketingReferralTypeId;

    private String marketingReferralTypeCode;

    private String marketingReferalTypeDescription;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private Long updatedById;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private UUID marketingReferalTypeMasterUUID;

    public Long getMarketingReferralTypeId() {
        return marketingReferralTypeId;
    }

    public void setMarketingReferralTypeId(Long marketingReferralTypeId) {
        this.marketingReferralTypeId = marketingReferralTypeId;
    }

    public String getMarketingReferralTypeCode() {
        return marketingReferralTypeCode;
    }

    public void setMarketingReferralTypeCode(String marketingReferralTypeCode) {
        this.marketingReferralTypeCode = marketingReferralTypeCode;
    }

    public String getMarketingReferalTypeDescription() {
        return marketingReferalTypeDescription;
    }

    public void setMarketingReferalTypeDescription(String marketingReferalTypeDescription) {
        this.marketingReferalTypeDescription = marketingReferalTypeDescription;
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

    public UUID getMarketingReferalTypeMasterUUID() {
        return marketingReferalTypeMasterUUID;
    }

    public void setMarketingReferalTypeMasterUUID(UUID marketingReferalTypeMasterUUID) {
        this.marketingReferalTypeMasterUUID = marketingReferalTypeMasterUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarketingReferalTypeMasterDTO)) {
            return false;
        }

        MarketingReferalTypeMasterDTO marketingReferalTypeMasterDTO = (MarketingReferalTypeMasterDTO) o;
        if (this.marketingReferralTypeId == null) {
            return false;
        }
        return Objects.equals(this.marketingReferralTypeId, marketingReferalTypeMasterDTO.marketingReferralTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.marketingReferralTypeId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MarketingReferalTypeMasterDTO{" +
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
