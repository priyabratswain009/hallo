package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PatientDoctorMapAuditLog.
 */
@Table("t_patient_doctor_map_audit_log")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PatientDoctorMapAuditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("paent_dctor_map_id")
    private Long paentDctorMapId;

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

    public PatientDoctorMapAuditLog id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaentDctorMapId() {
        return this.paentDctorMapId;
    }

    public PatientDoctorMapAuditLog paentDctorMapId(Long paentDctorMapId) {
        this.setPaentDctorMapId(paentDctorMapId);
        return this;
    }

    public void setPaentDctorMapId(Long paentDctorMapId) {
        this.paentDctorMapId = paentDctorMapId;
    }

    public String getOldRowData() {
        return this.oldRowData;
    }

    public PatientDoctorMapAuditLog oldRowData(String oldRowData) {
        this.setOldRowData(oldRowData);
        return this;
    }

    public void setOldRowData(String oldRowData) {
        this.oldRowData = oldRowData;
    }

    public String getNewRowData() {
        return this.newRowData;
    }

    public PatientDoctorMapAuditLog newRowData(String newRowData) {
        this.setNewRowData(newRowData);
        return this;
    }

    public void setNewRowData(String newRowData) {
        this.newRowData = newRowData;
    }

    public String getDmlType() {
        return this.dmlType;
    }

    public PatientDoctorMapAuditLog dmlType(String dmlType) {
        this.setDmlType(dmlType);
        return this;
    }

    public void setDmlType(String dmlType) {
        this.dmlType = dmlType;
    }

    public LocalDate getDmlTimestamp() {
        return this.dmlTimestamp;
    }

    public PatientDoctorMapAuditLog dmlTimestamp(LocalDate dmlTimestamp) {
        this.setDmlTimestamp(dmlTimestamp);
        return this;
    }

    public void setDmlTimestamp(LocalDate dmlTimestamp) {
        this.dmlTimestamp = dmlTimestamp;
    }

    public String getDmlCreatedBy() {
        return this.dmlCreatedBy;
    }

    public PatientDoctorMapAuditLog dmlCreatedBy(String dmlCreatedBy) {
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
        if (!(o instanceof PatientDoctorMapAuditLog)) {
            return false;
        }
        return id != null && id.equals(((PatientDoctorMapAuditLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDoctorMapAuditLog{" +
            "id=" + getId() +
            ", paentDctorMapId=" + getPaentDctorMapId() +
            ", oldRowData='" + getOldRowData() + "'" +
            ", newRowData='" + getNewRowData() + "'" +
            ", dmlType='" + getDmlType() + "'" +
            ", dmlTimestamp='" + getDmlTimestamp() + "'" +
            ", dmlCreatedBy='" + getDmlCreatedBy() + "'" +
            "}";
    }
}
