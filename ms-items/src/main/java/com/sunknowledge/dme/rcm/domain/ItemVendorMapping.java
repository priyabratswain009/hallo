package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ItemVendorMapping.
 */
@Entity
@Table(name = "t_item_vendor_mapping")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemVendorMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "item_vendor_id")
    private Long itemVendorId;

    @Column(name = "item_id")
    private Long itemId;

    @NotNull
    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "item_vendor_mapping_uuid")
    private UUID itemVendorMappingUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getItemVendorId() {
        return this.itemVendorId;
    }

    public ItemVendorMapping itemVendorId(Long itemVendorId) {
        this.setItemVendorId(itemVendorId);
        return this;
    }

    public void setItemVendorId(Long itemVendorId) {
        this.itemVendorId = itemVendorId;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public ItemVendorMapping itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ItemVendorMapping itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getVendorId() {
        return this.vendorId;
    }

    public ItemVendorMapping vendorId(Long vendorId) {
        this.setVendorId(vendorId);
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return this.vendorName;
    }

    public ItemVendorMapping vendorName(String vendorName) {
        this.setVendorName(vendorName);
        return this;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getStatus() {
        return this.status;
    }

    public ItemVendorMapping status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ItemVendorMapping createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ItemVendorMapping createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ItemVendorMapping updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ItemVendorMapping createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ItemVendorMapping updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ItemVendorMapping updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getItemVendorMappingUuid() {
        return this.itemVendorMappingUuid;
    }

    public ItemVendorMapping itemVendorMappingUuid(UUID itemVendorMappingUuid) {
        this.setItemVendorMappingUuid(itemVendorMappingUuid);
        return this;
    }

    public void setItemVendorMappingUuid(UUID itemVendorMappingUuid) {
        this.itemVendorMappingUuid = itemVendorMappingUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemVendorMapping)) {
            return false;
        }
        return itemVendorId != null && itemVendorId.equals(((ItemVendorMapping) o).itemVendorId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemVendorMapping{" +
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
