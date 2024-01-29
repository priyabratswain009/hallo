package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ItemProcedureCodeMap} entity.
 */
public class ItemProcedureCodeMapDTO implements Serializable {

    private Long itemProcedureCodeMapId;

    private String itemName;

    private String itemNo;

    private String itemDescription;

    private String itemUom;

    private String procedureCode;

    private String modifier1;

    private String modifier2;

    private String modifier3;

    private String modifier4;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedName;

    private LocalDate updatedDate;

    private UUID itemProcedureCodeMapUuid;

    private ItemMasterDTO itemMaster;

    public Long getItemProcedureCodeMapId() {
        return itemProcedureCodeMapId;
    }

    public void setItemProcedureCodeMapId(Long itemProcedureCodeMapId) {
        this.itemProcedureCodeMapId = itemProcedureCodeMapId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemUom() {
        return itemUom;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getModifier1() {
        return modifier1;
    }

    public void setModifier1(String modifier1) {
        this.modifier1 = modifier1;
    }

    public String getModifier2() {
        return modifier2;
    }

    public void setModifier2(String modifier2) {
        this.modifier2 = modifier2;
    }

    public String getModifier3() {
        return modifier3;
    }

    public void setModifier3(String modifier3) {
        this.modifier3 = modifier3;
    }

    public String getModifier4() {
        return modifier4;
    }

    public void setModifier4(String modifier4) {
        this.modifier4 = modifier4;
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

    public String getUpdatedName() {
        return updatedName;
    }

    public void setUpdatedName(String updatedName) {
        this.updatedName = updatedName;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getItemProcedureCodeMapUuid() {
        return itemProcedureCodeMapUuid;
    }

    public void setItemProcedureCodeMapUuid(UUID itemProcedureCodeMapUuid) {
        this.itemProcedureCodeMapUuid = itemProcedureCodeMapUuid;
    }

    public ItemMasterDTO getItemMaster() {
        return itemMaster;
    }

    public void setItemMaster(ItemMasterDTO itemMaster) {
        this.itemMaster = itemMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemProcedureCodeMapDTO)) {
            return false;
        }

        ItemProcedureCodeMapDTO itemProcedureCodeMapDTO = (ItemProcedureCodeMapDTO) o;
        if (this.itemProcedureCodeMapId == null) {
            return false;
        }
        return Objects.equals(this.itemProcedureCodeMapId, itemProcedureCodeMapDTO.itemProcedureCodeMapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.itemProcedureCodeMapId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemProcedureCodeMapDTO{" +
            "itemProcedureCodeMapId=" + getItemProcedureCodeMapId() +
            ", itemName='" + getItemName() + "'" +
            ", itemNo='" + getItemNo() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", itemUom='" + getItemUom() + "'" +
            ", procedureCode='" + getProcedureCode() + "'" +
            ", modifier1='" + getModifier1() + "'" +
            ", modifier2='" + getModifier2() + "'" +
            ", modifier3='" + getModifier3() + "'" +
            ", modifier4='" + getModifier4() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedName='" + getUpdatedName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", itemProcedureCodeMapUuid='" + getItemProcedureCodeMapUuid() + "'" +
            ", itemMaster=" + getItemMaster() +
            "}";
    }
}
