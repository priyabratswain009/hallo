package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ItemType} entity.
 */
public class ItemTypeDTO implements Serializable {

    private Long itemTypeId;

    private String itemTypeName;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID itemTypeUuid;

    public Long getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Long itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
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

    public UUID getItemTypeUuid() {
        return itemTypeUuid;
    }

    public void setItemTypeUuid(UUID itemTypeUuid) {
        this.itemTypeUuid = itemTypeUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemTypeDTO)) {
            return false;
        }

        ItemTypeDTO itemTypeDTO = (ItemTypeDTO) o;
        if (this.itemTypeId == null) {
            return false;
        }
        return Objects.equals(this.itemTypeId, itemTypeDTO.itemTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.itemTypeId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemTypeDTO{" +
            "itemTypeId=" + getItemTypeId() +
            ", itemTypeName='" + getItemTypeName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", itemTypeUuid='" + getItemTypeUuid() + "'" +
            "}";
    }
}
