package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SoLcdCoverageCriteriaTransaction} entity.
 */
public class SoLcdCoverageCriteriaTransactionDTO implements Serializable {

    private Long soLcdDocRefId;

    private Long soId;

    private Long itemId;

    private String itemName;

    private String hcpcsCode;

    private Long checklistId;

    private String checklistName;

    private Long coverageCriteriaId;

    private String value;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID soLcdCoverageCriteriaTransactionUuid;

    public Long getSoLcdDocRefId() {
        return soLcdDocRefId;
    }

    public void setSoLcdDocRefId(Long soLcdDocRefId) {
        this.soLcdDocRefId = soLcdDocRefId;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
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

    public String getHcpcsCode() {
        return hcpcsCode;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

    public String getChecklistName() {
        return checklistName;
    }

    public void setChecklistName(String checklistName) {
        this.checklistName = checklistName;
    }

    public Long getCoverageCriteriaId() {
        return coverageCriteriaId;
    }

    public void setCoverageCriteriaId(Long coverageCriteriaId) {
        this.coverageCriteriaId = coverageCriteriaId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getSoLcdCoverageCriteriaTransactionUuid() {
        return soLcdCoverageCriteriaTransactionUuid;
    }

    public void setSoLcdCoverageCriteriaTransactionUuid(UUID soLcdCoverageCriteriaTransactionUuid) {
        this.soLcdCoverageCriteriaTransactionUuid = soLcdCoverageCriteriaTransactionUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoLcdCoverageCriteriaTransactionDTO)) {
            return false;
        }

        SoLcdCoverageCriteriaTransactionDTO soLcdCoverageCriteriaTransactionDTO = (SoLcdCoverageCriteriaTransactionDTO) o;
        if (this.soLcdDocRefId == null) {
            return false;
        }
        return Objects.equals(this.soLcdDocRefId, soLcdCoverageCriteriaTransactionDTO.soLcdDocRefId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.soLcdDocRefId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoLcdCoverageCriteriaTransactionDTO{" +
            "soLcdDocRefId=" + getSoLcdDocRefId() +
            ", soId=" + getSoId() +
            ", itemId=" + getItemId() +
            ", itemName='" + getItemName() + "'" +
            ", hcpcsCode='" + getHcpcsCode() + "'" +
            ", checklistId=" + getChecklistId() +
            ", checklistName='" + getChecklistName() + "'" +
            ", coverageCriteriaId=" + getCoverageCriteriaId() +
            ", value='" + getValue() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", soLcdCoverageCriteriaTransactionUuid='" + getSoLcdCoverageCriteriaTransactionUuid() + "'" +
            "}";
    }
}
