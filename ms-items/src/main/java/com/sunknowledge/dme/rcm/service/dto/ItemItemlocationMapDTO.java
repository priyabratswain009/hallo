package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ItemItemlocationMap} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemItemlocationMapDTO implements Serializable {

    private Long itemItemlocationMapId;

    private Long itemId;

    private String itemName;

    private Long itemLocationId;

    private String itemLocationName;

    private String itemNo;

    private String itemUom;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID itemItemlocationMapUuid;

    public Long getItemItemlocationMapId() {
        return itemItemlocationMapId;
    }

    public void setItemItemlocationMapId(Long itemItemlocationMapId) {
        this.itemItemlocationMapId = itemItemlocationMapId;
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

    public Long getItemLocationId() {
        return itemLocationId;
    }

    public void setItemLocationId(Long itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    public String getItemLocationName() {
        return itemLocationName;
    }

    public void setItemLocationName(String itemLocationName) {
        this.itemLocationName = itemLocationName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemUom() {
        return itemUom;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
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

    public UUID getItemItemlocationMapUuid() {
        return itemItemlocationMapUuid;
    }

    public void setItemItemlocationMapUuid(UUID itemItemlocationMapUuid) {
        this.itemItemlocationMapUuid = itemItemlocationMapUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemItemlocationMapDTO)) {
            return false;
        }

        ItemItemlocationMapDTO itemItemlocationMapDTO = (ItemItemlocationMapDTO) o;
        if (this.itemItemlocationMapId == null) {
            return false;
        }
        return Objects.equals(this.itemItemlocationMapId, itemItemlocationMapDTO.itemItemlocationMapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.itemItemlocationMapId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemItemlocationMapDTO{" +
            "itemItemlocationMapId=" + getItemItemlocationMapId() +
            ", itemId=" + getItemId() +
            ", itemName='" + getItemName() + "'" +
            ", itemLocationId=" + getItemLocationId() +
            ", itemLocationName='" + getItemLocationName() + "'" +
            ", itemNo='" + getItemNo() + "'" +
            ", itemUom='" + getItemUom() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", itemItemlocationMapUuid='" + getItemItemlocationMapUuid() + "'" +
            "}";
    }
}
