package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SoRecurringPurchase.
 */
@Table("t_so_recurring_purchase")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoRecurringPurchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("rp_id")
    private Long rpId;

    @Column("so_id")
    private Long soId;

    @Column("item_id")
    private Long itemId;

    @Column("item_name")
    private String itemName;

    @Column("proc_code")
    private String procCode;

    @Column("qty")
    private Double qty;

    @Column("billing_interval")
    private Double billingInterval;

    @Column("initial_dos")
    private LocalDate initialDos;

    @Column("period")
    private Double period;

    @Column("status")
    private String status;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("created_by_id")
    private Long createdById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("so_recurring_purchase_uuid")
    private UUID soRecurringPurchaseUuid;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getRpId() {
        return this.rpId;
    }

    public SoRecurringPurchase rpId(Long rpId) {
        this.setRpId(rpId);
        return this;
    }

    public void setRpId(Long rpId) {
        this.rpId = rpId;
    }

    public Long getSoId() {
        return this.soId;
    }

    public SoRecurringPurchase soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public SoRecurringPurchase itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public SoRecurringPurchase itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getProcCode() {
        return this.procCode;
    }

    public SoRecurringPurchase procCode(String procCode) {
        this.setProcCode(procCode);
        return this;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public Double getQty() {
        return this.qty;
    }

    public SoRecurringPurchase qty(Double qty) {
        this.setQty(qty);
        return this;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getBillingInterval() {
        return this.billingInterval;
    }

    public SoRecurringPurchase billingInterval(Double billingInterval) {
        this.setBillingInterval(billingInterval);
        return this;
    }

    public void setBillingInterval(Double billingInterval) {
        this.billingInterval = billingInterval;
    }

    public LocalDate getInitialDos() {
        return this.initialDos;
    }

    public SoRecurringPurchase initialDos(LocalDate initialDos) {
        this.setInitialDos(initialDos);
        return this;
    }

    public void setInitialDos(LocalDate initialDos) {
        this.initialDos = initialDos;
    }

    public Double getPeriod() {
        return this.period;
    }

    public SoRecurringPurchase period(Double period) {
        this.setPeriod(period);
        return this;
    }

    public void setPeriod(Double period) {
        this.period = period;
    }

    public String getStatus() {
        return this.status;
    }

    public SoRecurringPurchase status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public SoRecurringPurchase createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public SoRecurringPurchase createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public SoRecurringPurchase updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SoRecurringPurchase updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getSoRecurringPurchaseUuid() {
        return this.soRecurringPurchaseUuid;
    }

    public SoRecurringPurchase soRecurringPurchaseUuid(UUID soRecurringPurchaseUuid) {
        this.setSoRecurringPurchaseUuid(soRecurringPurchaseUuid);
        return this;
    }

    public void setSoRecurringPurchaseUuid(UUID soRecurringPurchaseUuid) {
        this.soRecurringPurchaseUuid = soRecurringPurchaseUuid;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public SoRecurringPurchase createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SoRecurringPurchase updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoRecurringPurchase)) {
            return false;
        }
        return rpId != null && rpId.equals(((SoRecurringPurchase) o).rpId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoRecurringPurchase{" +
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
