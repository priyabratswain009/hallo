package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SecondaryClaimSubmisionMasterAuditLog.
 */
@Table("t_secondary_claim_submision_master_audit_log")
public class SecondaryClaimSubmisionMasterAuditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("chg_health_sconary_submn_mster_id")
    private Long chgHealthSconarySubmnMsterId;

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

    @Column("id")
    private Long id;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getChgHealthSconarySubmnMsterId() {
        return this.chgHealthSconarySubmnMsterId;
    }

    public SecondaryClaimSubmisionMasterAuditLog chgHealthSconarySubmnMsterId(Long chgHealthSconarySubmnMsterId) {
        this.setChgHealthSconarySubmnMsterId(chgHealthSconarySubmnMsterId);
        return this;
    }

    public void setChgHealthSconarySubmnMsterId(Long chgHealthSconarySubmnMsterId) {
        this.chgHealthSconarySubmnMsterId = chgHealthSconarySubmnMsterId;
    }

    public String getOldRowData() {
        return this.oldRowData;
    }

    public SecondaryClaimSubmisionMasterAuditLog oldRowData(String oldRowData) {
        this.setOldRowData(oldRowData);
        return this;
    }

    public void setOldRowData(String oldRowData) {
        this.oldRowData = oldRowData;
    }

    public String getNewRowData() {
        return this.newRowData;
    }

    public SecondaryClaimSubmisionMasterAuditLog newRowData(String newRowData) {
        this.setNewRowData(newRowData);
        return this;
    }

    public void setNewRowData(String newRowData) {
        this.newRowData = newRowData;
    }

    public String getDmlType() {
        return this.dmlType;
    }

    public SecondaryClaimSubmisionMasterAuditLog dmlType(String dmlType) {
        this.setDmlType(dmlType);
        return this;
    }

    public void setDmlType(String dmlType) {
        this.dmlType = dmlType;
    }

    public LocalDate getDmlTimestamp() {
        return this.dmlTimestamp;
    }

    public SecondaryClaimSubmisionMasterAuditLog dmlTimestamp(LocalDate dmlTimestamp) {
        this.setDmlTimestamp(dmlTimestamp);
        return this;
    }

    public void setDmlTimestamp(LocalDate dmlTimestamp) {
        this.dmlTimestamp = dmlTimestamp;
    }

    public String getDmlCreatedBy() {
        return this.dmlCreatedBy;
    }

    public SecondaryClaimSubmisionMasterAuditLog dmlCreatedBy(String dmlCreatedBy) {
        this.setDmlCreatedBy(dmlCreatedBy);
        return this;
    }

    public void setDmlCreatedBy(String dmlCreatedBy) {
        this.dmlCreatedBy = dmlCreatedBy;
    }

    public Long getId() {
        return this.id;
    }

    public SecondaryClaimSubmisionMasterAuditLog id(Long id) {
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
        if (!(o instanceof SecondaryClaimSubmisionMasterAuditLog)) {
            return false;
        }
        return (
            chgHealthSconarySubmnMsterId != null &&
            chgHealthSconarySubmnMsterId.equals(((SecondaryClaimSubmisionMasterAuditLog) o).chgHealthSconarySubmnMsterId)
        );
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SecondaryClaimSubmisionMasterAuditLog{" +
            "chgHealthSconarySubmnMsterId=" + getChgHealthSconarySubmnMsterId() +
            ", oldRowData='" + getOldRowData() + "'" +
            ", newRowData='" + getNewRowData() + "'" +
            ", dmlType='" + getDmlType() + "'" +
            ", dmlTimestamp='" + getDmlTimestamp() + "'" +
            ", dmlCreatedBy='" + getDmlCreatedBy() + "'" +
            ", id=" + getId() +
            "}";
    }
}