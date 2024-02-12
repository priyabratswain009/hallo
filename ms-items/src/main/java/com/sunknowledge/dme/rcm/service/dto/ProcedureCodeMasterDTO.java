package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ProcedureCodeMaster} entity.
 */
public class ProcedureCodeMasterDTO implements Serializable {

    private Long procedureCodeId;

    private String itemProcedureCodeDesc;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String procedureCode;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID itemProcedureCodeUuid;

    public Long getProcedureCodeId() {
        return procedureCodeId;
    }

    public void setProcedureCodeId(Long procedureCodeId) {
        this.procedureCodeId = procedureCodeId;
    }

    public String getItemProcedureCodeDesc() {
        return itemProcedureCodeDesc;
    }

    public void setItemProcedureCodeDesc(String itemProcedureCodeDesc) {
        this.itemProcedureCodeDesc = itemProcedureCodeDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
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

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getItemProcedureCodeUuid() {
        return itemProcedureCodeUuid;
    }

    public void setItemProcedureCodeUuid(UUID itemProcedureCodeUuid) {
        this.itemProcedureCodeUuid = itemProcedureCodeUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProcedureCodeMasterDTO)) {
            return false;
        }

        ProcedureCodeMasterDTO procedureCodeMasterDTO = (ProcedureCodeMasterDTO) o;
        if (this.procedureCodeId == null) {
            return false;
        }
        return Objects.equals(this.procedureCodeId, procedureCodeMasterDTO.procedureCodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.procedureCodeId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProcedureCodeMasterDTO{" +
            "procedureCodeId=" + getProcedureCodeId() +
            ", itemProcedureCodeDesc='" + getItemProcedureCodeDesc() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", procedureCode='" + getProcedureCode() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", itemProcedureCodeUuid='" + getItemProcedureCodeUuid() + "'" +
            "}";
    }
}
