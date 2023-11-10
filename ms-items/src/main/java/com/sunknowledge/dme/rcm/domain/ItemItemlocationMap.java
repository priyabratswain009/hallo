package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A ItemItemlocationMap.
 */
@Entity
@Table(name = "t_item_itemlocation_map")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemItemlocationMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "item_itemlocation_map_id")
    private Long itemItemlocationMapId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_location_id")
    private Long itemLocationId;

    @Column(name = "item_location_name")
    private String itemLocationName;

    @Column(name = "item_no")
    private String itemNo;

    @Column(name = "item_uom")
    private String itemUom;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "item_itemlocation_map_uuid")
    private UUID itemItemlocationMapUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getItemItemlocationMapId() {
        return this.itemItemlocationMapId;
    }

    public ItemItemlocationMap itemItemlocationMapId(Long itemItemlocationMapId) {
        this.setItemItemlocationMapId(itemItemlocationMapId);
        return this;
    }

    public void setItemItemlocationMapId(Long itemItemlocationMapId) {
        this.itemItemlocationMapId = itemItemlocationMapId;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public ItemItemlocationMap itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ItemItemlocationMap itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemLocationId() {
        return this.itemLocationId;
    }

    public ItemItemlocationMap itemLocationId(Long itemLocationId) {
        this.setItemLocationId(itemLocationId);
        return this;
    }

    public void setItemLocationId(Long itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    public String getItemLocationName() {
        return this.itemLocationName;
    }

    public ItemItemlocationMap itemLocationName(String itemLocationName) {
        this.setItemLocationName(itemLocationName);
        return this;
    }

    public void setItemLocationName(String itemLocationName) {
        this.itemLocationName = itemLocationName;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public ItemItemlocationMap itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public ItemItemlocationMap itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public String getStatus() {
        return this.status;
    }

    public ItemItemlocationMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ItemItemlocationMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ItemItemlocationMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ItemItemlocationMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ItemItemlocationMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ItemItemlocationMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ItemItemlocationMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getItemItemlocationMapUuid() {
        return this.itemItemlocationMapUuid;
    }

    public ItemItemlocationMap itemItemlocationMapUuid(UUID itemItemlocationMapUuid) {
        this.setItemItemlocationMapUuid(itemItemlocationMapUuid);
        return this;
    }

    public void setItemItemlocationMapUuid(UUID itemItemlocationMapUuid) {
        this.itemItemlocationMapUuid = itemItemlocationMapUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemItemlocationMap)) {
            return false;
        }
        return itemItemlocationMapId != null && itemItemlocationMapId.equals(((ItemItemlocationMap) o).itemItemlocationMapId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemItemlocationMap{" +
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
