package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DocumentTypeMasterAuditLog} entity.
 */
public class DocumentTypeMasterAuditLogDTO implements Serializable {

    private Long dcumetTpeId;

    private String oldRowData;

    private String newRowData;

    private String dmlType;

    private LocalDate dmlTimestamp;

    private String dmlCreatedBy;

    private Long id;

    public Long getDcumetTpeId() {
        return dcumetTpeId;
    }

    public void setDcumetTpeId(Long dcumetTpeId) {
        this.dcumetTpeId = dcumetTpeId;
    }

    public String getOldRowData() {
        return oldRowData;
    }

    public void setOldRowData(String oldRowData) {
        this.oldRowData = oldRowData;
    }

    public String getNewRowData() {
        return newRowData;
    }

    public void setNewRowData(String newRowData) {
        this.newRowData = newRowData;
    }

    public String getDmlType() {
        return dmlType;
    }

    public void setDmlType(String dmlType) {
        this.dmlType = dmlType;
    }

    public LocalDate getDmlTimestamp() {
        return dmlTimestamp;
    }

    public void setDmlTimestamp(LocalDate dmlTimestamp) {
        this.dmlTimestamp = dmlTimestamp;
    }

    public String getDmlCreatedBy() {
        return dmlCreatedBy;
    }

    public void setDmlCreatedBy(String dmlCreatedBy) {
        this.dmlCreatedBy = dmlCreatedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentTypeMasterAuditLogDTO)) {
            return false;
        }

        DocumentTypeMasterAuditLogDTO documentTypeMasterAuditLogDTO = (DocumentTypeMasterAuditLogDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, documentTypeMasterAuditLogDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentTypeMasterAuditLogDTO{" +
            "dcumetTpeId=" + getDcumetTpeId() +
            ", oldRowData='" + getOldRowData() + "'" +
            ", newRowData='" + getNewRowData() + "'" +
            ", dmlType='" + getDmlType() + "'" +
            ", dmlTimestamp='" + getDmlTimestamp() + "'" +
            ", dmlCreatedBy='" + getDmlCreatedBy() + "'" +
            ", id=" + getId() +
            "}";
    }
}
