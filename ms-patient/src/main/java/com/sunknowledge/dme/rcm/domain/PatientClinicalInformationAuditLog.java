package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PatientClinicalInformationAuditLog.
 */
@Table("t_patient_clinical_information_audit_log")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientClinicalInformationAuditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("patnt_cliical_infoation_id")
    private Long patntCliicalInfoationId;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PatientClinicalInformationAuditLog id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatntCliicalInfoationId() {
        return this.patntCliicalInfoationId;
    }

    public PatientClinicalInformationAuditLog patntCliicalInfoationId(Long patntCliicalInfoationId) {
        this.setPatntCliicalInfoationId(patntCliicalInfoationId);
        return this;
    }

    public void setPatntCliicalInfoationId(Long patntCliicalInfoationId) {
        this.patntCliicalInfoationId = patntCliicalInfoationId;
    }

    public String getOldRowData() {
        return this.oldRowData;
    }

    public PatientClinicalInformationAuditLog oldRowData(String oldRowData) {
        this.setOldRowData(oldRowData);
        return this;
    }

    public void setOldRowData(String oldRowData) {
        this.oldRowData = oldRowData;
    }

    public String getNewRowData() {
        return this.newRowData;
    }

    public PatientClinicalInformationAuditLog newRowData(String newRowData) {
        this.setNewRowData(newRowData);
        return this;
    }

    public void setNewRowData(String newRowData) {
        this.newRowData = newRowData;
    }

    public String getDmlType() {
        return this.dmlType;
    }

    public PatientClinicalInformationAuditLog dmlType(String dmlType) {
        this.setDmlType(dmlType);
        return this;
    }

    public void setDmlType(String dmlType) {
        this.dmlType = dmlType;
    }

    public LocalDate getDmlTimestamp() {
        return this.dmlTimestamp;
    }

    public PatientClinicalInformationAuditLog dmlTimestamp(LocalDate dmlTimestamp) {
        this.setDmlTimestamp(dmlTimestamp);
        return this;
    }

    public void setDmlTimestamp(LocalDate dmlTimestamp) {
        this.dmlTimestamp = dmlTimestamp;
    }

    public String getDmlCreatedBy() {
        return this.dmlCreatedBy;
    }

    public PatientClinicalInformationAuditLog dmlCreatedBy(String dmlCreatedBy) {
        this.setDmlCreatedBy(dmlCreatedBy);
        return this;
    }

    public void setDmlCreatedBy(String dmlCreatedBy) {
        this.dmlCreatedBy = dmlCreatedBy;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientClinicalInformationAuditLog)) {
            return false;
        }
        return id != null && id.equals(((PatientClinicalInformationAuditLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientClinicalInformationAuditLog{" +
            "id=" + getId() +
            ", patntCliicalInfoationId=" + getPatntCliicalInfoationId() +
            ", oldRowData='" + getOldRowData() + "'" +
            ", newRowData='" + getNewRowData() + "'" +
            ", dmlType='" + getDmlType() + "'" +
            ", dmlTimestamp='" + getDmlTimestamp() + "'" +
            ", dmlCreatedBy='" + getDmlCreatedBy() + "'" +
            "}";
    }
}
