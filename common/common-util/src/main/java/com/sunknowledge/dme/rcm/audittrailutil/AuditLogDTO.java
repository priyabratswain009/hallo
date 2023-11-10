package com.sunknowledge.dme.rcm.audittrailutil;

import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.audittrailutil.AuditTrailUtil}.
 */
public class AuditLogDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long id;

    @NotNull(message = "must not be null")
    private Long parentId;

    private String oldRowData;

    private String newRowData;

    @NotNull(message = "must not be null")
    private String dmlType;

    @NotNull(message = "must not be null")
    private LocalDate dmlTimestamp;

    @NotNull(message = "must not be null")
    private String dmlCreatedBy;

    @NotNull(message = "must not be null")
    private String section;

    public AuditLogDTO(Long id, Long parentId, String oldRowData, String newRowData, String dmlType, LocalDate dmlTimestamp, String dmlCreatedBy, String section) {
        this.id = id;
        this.parentId = parentId;
        this.oldRowData = oldRowData;
        this.newRowData = newRowData;
        this.dmlType = dmlType;
        this.dmlTimestamp = dmlTimestamp;
        this.dmlCreatedBy = dmlCreatedBy;
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuditLogDTO)) {
            return false;
        }

        AuditLogDTO salesOrderMasterAuditLogDTO = (AuditLogDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, salesOrderMasterAuditLogDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderMasterAuditLogDTO{" +
            "id=" + getId() +
            ", parentId=" + getParentId() +
            ", oldRowData='" + getOldRowData() + "'" +
            ", newRowData='" + getNewRowData() + "'" +
            ", dmlType='" + getDmlType() + "'" +
            ", dmlTimestamp='" + getDmlTimestamp() + "'" +
            ", dmlCreatedBy='" + getDmlCreatedBy() + "'" +
            ", section='" + getSection() + "'" +
            "}";
    }
}
