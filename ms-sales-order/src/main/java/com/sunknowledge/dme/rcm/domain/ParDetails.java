package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ParDetails.
 */
@Table("t_par_details")
public class ParDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("par_details_id")
    private Long parDetailsId;

    @Column("par_id")
    private Long parId;

    @Column("item_id")
    private Long itemId;

    @Column("item_no")
    private String itemNo;

    @Column("item_uom")
    private String itemUom;

    @Column("item_quantity")
    private Double itemQuantity;

    @Column("hcpcs_code")
    private String hcpcsCode;

    @Column("description")
    private String description;

    @Column("item_name")
    private String itemName;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("par_details_uuid")
    private UUID parDetailsUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getParDetailsId() {
        return this.parDetailsId;
    }

    public ParDetails parDetailsId(Long parDetailsId) {
        this.setParDetailsId(parDetailsId);
        return this;
    }

    public void setParDetailsId(Long parDetailsId) {
        this.parDetailsId = parDetailsId;
    }

    public Long getParId() {
        return this.parId;
    }

    public ParDetails parId(Long parId) {
        this.setParId(parId);
        return this;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public ParDetails itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return this.itemNo;
    }

    public ParDetails itemNo(String itemNo) {
        this.setItemNo(itemNo);
        return this;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemUom() {
        return this.itemUom;
    }

    public ParDetails itemUom(String itemUom) {
        this.setItemUom(itemUom);
        return this;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public Double getItemQuantity() {
        return this.itemQuantity;
    }

    public ParDetails itemQuantity(Double itemQuantity) {
        this.setItemQuantity(itemQuantity);
        return this;
    }

    public void setItemQuantity(Double itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getHcpcsCode() {
        return this.hcpcsCode;
    }

    public ParDetails hcpcsCode(String hcpcsCode) {
        this.setHcpcsCode(hcpcsCode);
        return this;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public String getDescription() {
        return this.description;
    }

    public ParDetails description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ParDetails itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStatus() {
        return this.status;
    }

    public ParDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ParDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ParDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ParDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ParDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ParDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ParDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getParDetailsUuid() {
        return this.parDetailsUuid;
    }

    public ParDetails parDetailsUuid(UUID parDetailsUuid) {
        this.setParDetailsUuid(parDetailsUuid);
        return this;
    }

    public void setParDetailsUuid(UUID parDetailsUuid) {
        this.parDetailsUuid = parDetailsUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParDetails)) {
            return false;
        }
        return parDetailsId != null && parDetailsId.equals(((ParDetails) o).parDetailsId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParDetails{" +
            "parDetailsId=" + getParDetailsId() +
            ", parId=" + getParId() +
            ", itemId=" + getItemId() +
            ", itemNo='" + getItemNo() + "'" +
            ", itemUom='" + getItemUom() + "'" +
            ", itemQuantity=" + getItemQuantity() +
            ", hcpcsCode='" + getHcpcsCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", parDetailsUuid='" + getParDetailsUuid() + "'" +
            "}";
    }
}
