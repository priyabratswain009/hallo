package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PayerMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PayerMasterDTO implements Serializable {

    private Long payerMasterId;

    private String payerId;

    private String payerName;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID payerMasterUuid;

    public Long getPayerMasterId() {
        return payerMasterId;
    }

    public void setPayerMasterId(Long payerMasterId) {
        this.payerMasterId = payerMasterId;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
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

    public UUID getPayerMasterUuid() {
        return payerMasterUuid;
    }

    public void setPayerMasterUuid(UUID payerMasterUuid) {
        this.payerMasterUuid = payerMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PayerMasterDTO)) {
            return false;
        }

        PayerMasterDTO payerMasterDTO = (PayerMasterDTO) o;
        if (this.payerMasterId == null) {
            return false;
        }
        return Objects.equals(this.payerMasterId, payerMasterDTO.payerMasterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.payerMasterId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PayerMasterDTO{" +
            "payerMasterId=" + getPayerMasterId() +
            ", payerId='" + getPayerId() + "'" +
            ", payerName='" + getPayerName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", payerMasterUuid='" + getPayerMasterUuid() + "'" +
            "}";
    }
}
