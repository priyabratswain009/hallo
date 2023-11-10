package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SalesOrderClinicalDetailsAuditLog.
 */
@Table("t_sales_order_clinical_details_audit_log")
public class SalesOrderClinicalDetailsAuditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column("sals_odr_clincal_detils_id")
    private Long salsOdrClincalDetilsId;

    @Column("old_row_data")
    private String oldRowData;

    @Column("new_row_data")
    private String newRowData;

    @Column("dml_type")
    private String dmlType;

    @Column("dml_timestamp")
    private LocalDate dmlTimestamp;

    @Column("dml_created_by")
    private String dmlCreatedBy;

    @Id
    @Column("id")
    private Long id;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getSalsOdrClincalDetilsId() {
        return this.salsOdrClincalDetilsId;
    }

    public SalesOrderClinicalDetailsAuditLog salsOdrClincalDetilsId(Long salsOdrClincalDetilsId) {
        this.setSalsOdrClincalDetilsId(salsOdrClincalDetilsId);
        return this;
    }

    public void setSalsOdrClincalDetilsId(Long salsOdrClincalDetilsId) {
        this.salsOdrClincalDetilsId = salsOdrClincalDetilsId;
    }

    public String getOldRowData() {
        return this.oldRowData;
    }

    public SalesOrderClinicalDetailsAuditLog oldRowData(String oldRowData) {
        this.setOldRowData(oldRowData);
        return this;
    }

    public void setOldRowData(String oldRowData) {
        this.oldRowData = oldRowData;
    }

    public String getNewRowData() {
        return this.newRowData;
    }

    public SalesOrderClinicalDetailsAuditLog newRowData(String newRowData) {
        this.setNewRowData(newRowData);
        return this;
    }

    public void setNewRowData(String newRowData) {
        this.newRowData = newRowData;
    }

    public String getDmlType() {
        return this.dmlType;
    }

    public SalesOrderClinicalDetailsAuditLog dmlType(String dmlType) {
        this.setDmlType(dmlType);
        return this;
    }

    public void setDmlType(String dmlType) {
        this.dmlType = dmlType;
    }

    public LocalDate getDmlTimestamp() {
        return this.dmlTimestamp;
    }

    public SalesOrderClinicalDetailsAuditLog dmlTimestamp(LocalDate dmlTimestamp) {
        this.setDmlTimestamp(dmlTimestamp);
        return this;
    }

    public void setDmlTimestamp(LocalDate dmlTimestamp) {
        this.dmlTimestamp = dmlTimestamp;
    }

    public String getDmlCreatedBy() {
        return this.dmlCreatedBy;
    }

    public SalesOrderClinicalDetailsAuditLog dmlCreatedBy(String dmlCreatedBy) {
        this.setDmlCreatedBy(dmlCreatedBy);
        return this;
    }

    public void setDmlCreatedBy(String dmlCreatedBy) {
        this.dmlCreatedBy = dmlCreatedBy;
    }

    public Long getId() {
        return this.id;
    }

    public SalesOrderClinicalDetailsAuditLog id(Long id) {
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
        if (!(o instanceof SalesOrderClinicalDetailsAuditLog)) {
            return false;
        }
        return id != null && id.equals(((SalesOrderClinicalDetailsAuditLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderClinicalDetailsAuditLog{" +
            "id=" + getId() +
            ", salsOdrClincalDetilsId=" + getSalsOdrClincalDetilsId() +
            ", oldRowData='" + getOldRowData() + "'" +
            ", newRowData='" + getNewRowData() + "'" +
            ", dmlType='" + getDmlType() + "'" +
            ", dmlTimestamp='" + getDmlTimestamp() + "'" +
            ", dmlCreatedBy='" + getDmlCreatedBy() + "'" +
            "}";
    }
}
