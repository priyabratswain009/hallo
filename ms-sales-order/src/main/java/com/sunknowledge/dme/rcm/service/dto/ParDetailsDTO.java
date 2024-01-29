package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ParDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ParDetailsDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long parDetailsId;

    private Long parId;

    private Long itemId;

    private String itemNo;

    private String itemUom;

    private Double itemQuantity;

    private String hcpcsCode;

    private String description;

    private String itemName;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID parDetailsUuid;

    public Long getParDetailsId() {
        return parDetailsId;
    }

    public void setParDetailsId(Long parDetailsId) {
        this.parDetailsId = parDetailsId;
    }

    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public Double getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Double itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getHcpcsCode() {
        return hcpcsCode;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public UUID getParDetailsUuid() {
        return parDetailsUuid;
    }

    public void setParDetailsUuid(UUID parDetailsUuid) {
        this.parDetailsUuid = parDetailsUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParDetailsDTO)) {
            return false;
        }

        ParDetailsDTO parDetailsDTO = (ParDetailsDTO) o;
        if (this.parDetailsId == null) {
            return false;
        }
        return Objects.equals(this.parDetailsId, parDetailsDTO.parDetailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.parDetailsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParDetailsDTO{" +
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
