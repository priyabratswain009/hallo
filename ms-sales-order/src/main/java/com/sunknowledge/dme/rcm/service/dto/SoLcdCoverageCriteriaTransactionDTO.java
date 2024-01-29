package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SoLcdCoverageCriteriaTransaction} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoLcdCoverageCriteriaTransactionDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long soLcdCoverageCriteriaTransactionId;

    private Long soId;

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

    private Long itemGroupId;

    private String itemGroupName;

    private String coverageCriteriaName;

    public Long getSoLcdCoverageCriteriaTransactionId() {
        return soLcdCoverageCriteriaTransactionId;
    }

    public void setSoLcdCoverageCriteriaTransactionId(Long soLcdCoverageCriteriaTransactionId) {
        this.soLcdCoverageCriteriaTransactionId = soLcdCoverageCriteriaTransactionId;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
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

    public String getCoverageCriteriaName() {
        return coverageCriteriaName;
    }

    public void setCoverageCriteriaName(String coverageCriteriaName) {
        this.coverageCriteriaName = coverageCriteriaName;
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
        if (this.soLcdCoverageCriteriaTransactionId == null) {
            return false;
        }
        return Objects.equals(
            this.soLcdCoverageCriteriaTransactionId,
            soLcdCoverageCriteriaTransactionDTO.soLcdCoverageCriteriaTransactionId
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.soLcdCoverageCriteriaTransactionId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoLcdCoverageCriteriaTransactionDTO{" +
            "soLcdCoverageCriteriaTransactionId=" + getSoLcdCoverageCriteriaTransactionId() +
            ", soId=" + getSoId() +
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
            ", itemGroupId=" + getItemGroupId() +
            ", itemGroupName='" + getItemGroupName() + "'" +
            ", coverageCriteriaName='" + getCoverageCriteriaName() + "'" +
            "}";
    }
}
