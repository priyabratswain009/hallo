package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ItemVendorMapping} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemVendorMappingDTO implements Serializable {

    private Long itemVendorId;

    private Long itemId;

    @NotNull
    private String itemName;

    private Long vendorId;

    private String vendorName;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID itemVendorMappingUuid;

    public Long getItemVendorId() {
        return itemVendorId;
    }

    public void setItemVendorId(Long itemVendorId) {
        this.itemVendorId = itemVendorId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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

    public UUID getItemVendorMappingUuid() {
        return itemVendorMappingUuid;
    }

    public void setItemVendorMappingUuid(UUID itemVendorMappingUuid) {
        this.itemVendorMappingUuid = itemVendorMappingUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemVendorMappingDTO)) {
            return false;
        }

        ItemVendorMappingDTO itemVendorMappingDTO = (ItemVendorMappingDTO) o;
        if (this.itemVendorId == null) {
            return false;
        }
        return Objects.equals(this.itemVendorId, itemVendorMappingDTO.itemVendorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.itemVendorId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemVendorMappingDTO{" +
            "itemVendorId=" + getItemVendorId() +
            ", itemId=" + getItemId() +
            ", itemName='" + getItemName() + "'" +
            ", vendorId=" + getVendorId() +
            ", vendorName='" + getVendorName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", itemVendorMappingUuid='" + getItemVendorMappingUuid() + "'" +
            "}";
    }
}
