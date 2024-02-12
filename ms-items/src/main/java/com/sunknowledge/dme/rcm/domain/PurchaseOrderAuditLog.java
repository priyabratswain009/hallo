package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A PurchaseOrderAuditLog.
 */
@Entity
@Table(name = "t_purchase_order_audit_log")
public class PurchaseOrderAuditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "p_id")
    private Long pId;

    @Column(name = "old_row_data")
    private String oldRowData;

    @Column(name = "new_row_data")
    private String newRowData;

    @Column(name = "dml_type")
    private String dmlType;

    @Column(name = "dml_timestamp")
    private LocalDate dmlTimestamp;

    @Column(name = "dml_created_by")
    private String dmlCreatedBy;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getpId() {
        return this.pId;
    }

    public PurchaseOrderAuditLog pId(Long pId) {
        this.setpId(pId);
        return this;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getOldRowData() {
        return this.oldRowData;
    }

    public PurchaseOrderAuditLog oldRowData(String oldRowData) {
        this.setOldRowData(oldRowData);
        return this;
    }

    public void setOldRowData(String oldRowData) {
        this.oldRowData = oldRowData;
    }

    public String getNewRowData() {
        return this.newRowData;
    }

    public PurchaseOrderAuditLog newRowData(String newRowData) {
        this.setNewRowData(newRowData);
        return this;
    }

    public void setNewRowData(String newRowData) {
        this.newRowData = newRowData;
    }

    public String getDmlType() {
        return this.dmlType;
    }

    public PurchaseOrderAuditLog dmlType(String dmlType) {
        this.setDmlType(dmlType);
        return this;
    }

    public void setDmlType(String dmlType) {
        this.dmlType = dmlType;
    }

    public LocalDate getDmlTimestamp() {
        return this.dmlTimestamp;
    }

    public PurchaseOrderAuditLog dmlTimestamp(LocalDate dmlTimestamp) {
        this.setDmlTimestamp(dmlTimestamp);
        return this;
    }

    public void setDmlTimestamp(LocalDate dmlTimestamp) {
        this.dmlTimestamp = dmlTimestamp;
    }

    public String getDmlCreatedBy() {
        return this.dmlCreatedBy;
    }

    public PurchaseOrderAuditLog dmlCreatedBy(String dmlCreatedBy) {
        this.setDmlCreatedBy(dmlCreatedBy);
        return this;
    }

    public void setDmlCreatedBy(String dmlCreatedBy) {
        this.dmlCreatedBy = dmlCreatedBy;
    }

    public Long getId() {
        return this.id;
    }

    public PurchaseOrderAuditLog id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseOrderAuditLog)) {
            return false;
        }
        return id != null && id.equals(((PurchaseOrderAuditLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseOrderAuditLog{" +
            "id=" + getId() +
            ", pId=" + getpId() +
            ", oldRowData='" + getOldRowData() + "'" +
            ", newRowData='" + getNewRowData() + "'" +
            ", dmlType='" + getDmlType() + "'" +
            ", dmlTimestamp='" + getDmlTimestamp() + "'" +
            ", dmlCreatedBy='" + getDmlCreatedBy() + "'" +
            "}";
    }
}
