package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PriceMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PriceMasterDTO implements Serializable {

    private Long priceTableId;

    private String priceTableName;

    private String description;

    private String type;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String priceCodeGroup;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID priceMasterUuid;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPriceCodeGroup() {
        return priceCodeGroup;
    }

    public void setPriceCodeGroup(String priceCodeGroup) {
        this.priceCodeGroup = priceCodeGroup;
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

    public UUID getPriceMasterUuid() {
        return priceMasterUuid;
    }

    public void setPriceMasterUuid(UUID priceMasterUuid) {
        this.priceMasterUuid = priceMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PriceMasterDTO)) {
            return false;
        }

        PriceMasterDTO priceMasterDTO = (PriceMasterDTO) o;
        if (this.priceTableId == null) {
            return false;
        }
        return Objects.equals(this.priceTableId, priceMasterDTO.priceTableId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.priceTableId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PriceMasterDTO{" +
            "priceTableId=" + getPriceTableId() +
            ", priceTableName='" + getPriceTableName() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", priceCodeGroup='" + getPriceCodeGroup() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", priceMasterUuid='" + getPriceMasterUuid() + "'" +
            "}";
    }
}
