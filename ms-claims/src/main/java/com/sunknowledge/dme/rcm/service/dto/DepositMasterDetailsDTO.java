package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DepositMasterDetails} entity.
 */
public class DepositMasterDetailsDTO implements Serializable {

    @NotNull
    private Long depositId;

    private String depositNo;

    private String depositReference;

    private String payorName;

    private LocalDate depositDate;

    private String depositNotes;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID depositMasterDetailsUuid;

    private Long claimCob835MasterId;

    private String depositStatus;

    private Double depositAmount;

    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public String getDepositNo() {
        return depositNo;
    }

    public void setDepositNo(String depositNo) {
        this.depositNo = depositNo;
    }

    public String getDepositReference() {
        return depositReference;
    }

    public void setDepositReference(String depositReference) {
        this.depositReference = depositReference;
    }

    public String getPayorName() {
        return payorName;
    }

    public void setPayorName(String payorName) {
        this.payorName = payorName;
    }

    public LocalDate getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(LocalDate depositDate) {
        this.depositDate = depositDate;
    }

    public String getDepositNotes() {
        return depositNotes;
    }

    public void setDepositNotes(String depositNotes) {
        this.depositNotes = depositNotes;
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

    public UUID getDepositMasterDetailsUuid() {
        return depositMasterDetailsUuid;
    }

    public void setDepositMasterDetailsUuid(UUID depositMasterDetailsUuid) {
        this.depositMasterDetailsUuid = depositMasterDetailsUuid;
    }

    public Long getClaimCob835MasterId() {
        return claimCob835MasterId;
    }

    public void setClaimCob835MasterId(Long claimCob835MasterId) {
        this.claimCob835MasterId = claimCob835MasterId;
    }

    public String getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(String depositStatus) {
        this.depositStatus = depositStatus;
    }

    public Double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepositMasterDetailsDTO)) {
            return false;
        }

        DepositMasterDetailsDTO depositMasterDetailsDTO = (DepositMasterDetailsDTO) o;
        if (this.depositId == null) {
            return false;
        }
        return Objects.equals(this.depositId, depositMasterDetailsDTO.depositId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.depositId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepositMasterDetailsDTO{" +
            "depositId=" + getDepositId() +
            ", depositNo='" + getDepositNo() + "'" +
            ", depositReference='" + getDepositReference() + "'" +
            ", payorName='" + getPayorName() + "'" +
            ", depositDate='" + getDepositDate() + "'" +
            ", depositNotes='" + getDepositNotes() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", depositMasterDetailsUuid='" + getDepositMasterDetailsUuid() + "'" +
            ", claimCob835MasterId=" + getClaimCob835MasterId() +
            ", depositStatus='" + getDepositStatus() + "'" +
            ", depositAmount=" + getDepositAmount() +
            "}";
    }
}
