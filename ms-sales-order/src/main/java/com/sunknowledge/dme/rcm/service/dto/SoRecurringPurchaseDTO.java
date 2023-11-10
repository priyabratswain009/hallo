package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SoRecurringPurchase} entity.
 */
public class SoRecurringPurchaseDTO implements Serializable {

    private Long rpId;

    private Long soId;

    private Long itemId;

    private String itemName;

    private String procCode;

    private Double qty;

    private Double billingInterval;

    private LocalDate initialDos;

    private Double period;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private LocalDate updatedDate;

    private Long updatedById;

    private UUID soRecurringPurchaseUuid;

    private String createdByName;

    private String updatedByName;

    public Long getRpId() {
        return rpId;
    }

    public void setRpId(Long rpId) {
        this.rpId = rpId;
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

    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getBillingInterval() {
        return billingInterval;
    }

    public void setBillingInterval(Double billingInterval) {
        this.billingInterval = billingInterval;
    }

    public LocalDate getInitialDos() {
        return initialDos;
    }

    public void setInitialDos(LocalDate initialDos) {
        this.initialDos = initialDos;
    }

    public Double getPeriod() {
        return period;
    }

    public void setPeriod(Double period) {
        this.period = period;
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

    public UUID getSoRecurringPurchaseUuid() {
        return soRecurringPurchaseUuid;
    }

    public void setSoRecurringPurchaseUuid(UUID soRecurringPurchaseUuid) {
        this.soRecurringPurchaseUuid = soRecurringPurchaseUuid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoRecurringPurchaseDTO)) {
            return false;
        }

        SoRecurringPurchaseDTO soRecurringPurchaseDTO = (SoRecurringPurchaseDTO) o;
        if (this.rpId == null) {
            return false;
        }
        return Objects.equals(this.rpId, soRecurringPurchaseDTO.rpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.rpId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoRecurringPurchaseDTO{" +
            "rpId=" + getRpId() +
            ", soId=" + getSoId() +
            ", itemId=" + getItemId() +
            ", itemName='" + getItemName() + "'" +
            ", procCode='" + getProcCode() + "'" +
            ", qty=" + getQty() +
            ", billingInterval=" + getBillingInterval() +
            ", initialDos='" + getInitialDos() + "'" +
            ", period=" + getPeriod() +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", soRecurringPurchaseUuid='" + getSoRecurringPurchaseUuid() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            "}";
    }
}
