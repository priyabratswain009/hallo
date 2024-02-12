package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ItemGroup} entity.
 */
public class ItemGroupDTO implements Serializable {

    private Long itemGroupId;

    private String itemGroupName;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private UUID itemGroupUuid;

    public Long getItemGroupId() {
        return itemGroupId;
    }

    public void setItemGroupId(Long itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getItemGroupUuid() {
        return itemGroupUuid;
    }

    public void setItemGroupUuid(UUID itemGroupUuid) {
        this.itemGroupUuid = itemGroupUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemGroupDTO)) {
            return false;
        }

        ItemGroupDTO itemGroupDTO = (ItemGroupDTO) o;
        if (this.itemGroupId == null) {
            return false;
        }
        return Objects.equals(this.itemGroupId, itemGroupDTO.itemGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.itemGroupId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemGroupDTO{" +
            "itemGroupId=" + getItemGroupId() +
            ", itemGroupName='" + getItemGroupName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", itemGroupUuid='" + getItemGroupUuid() + "'" +
            "}";
    }
}
