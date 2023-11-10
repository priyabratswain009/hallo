package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SalesOrderClassification} entity.
 */
public class SalesOrderClassificationDTO implements Serializable {

    private Long orderClassificationId;

    private String orderClassificationDescription;

    private Long createdById;

    private LocalDate createdDate;

    private String status;

    private Long updatedById;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private UUID salesOrderClassificationUuid;

    public Long getOrderClassificationId() {
        return orderClassificationId;
    }

    public void setOrderClassificationId(Long orderClassificationId) {
        this.orderClassificationId = orderClassificationId;
    }

    public String getOrderClassificationDescription() {
        return orderClassificationDescription;
    }

    public void setOrderClassificationDescription(String orderClassificationDescription) {
        this.orderClassificationDescription = orderClassificationDescription;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getSalesOrderClassificationUuid() {
        return salesOrderClassificationUuid;
    }

    public void setSalesOrderClassificationUuid(UUID salesOrderClassificationUuid) {
        this.salesOrderClassificationUuid = salesOrderClassificationUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderClassificationDTO)) {
            return false;
        }

        SalesOrderClassificationDTO salesOrderClassificationDTO = (SalesOrderClassificationDTO) o;
        if (this.orderClassificationId == null) {
            return false;
        }
        return Objects.equals(this.orderClassificationId, salesOrderClassificationDTO.orderClassificationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.orderClassificationId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderClassificationDTO{" +
            "orderClassificationId=" + getOrderClassificationId() +
            ", orderClassificationDescription='" + getOrderClassificationDescription() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", salesOrderClassificationUuid='" + getSalesOrderClassificationUuid() + "'" +
            "}";
    }
}