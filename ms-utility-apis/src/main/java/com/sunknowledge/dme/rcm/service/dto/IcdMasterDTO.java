package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.IcdMaster} entity.
 */
public class IcdMasterDTO implements Serializable {

    @NotNull
    private Long icdMasterId;

    private String icdCode;

    private String icdCodeDesc;

    private String icdCodeType;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID icdMasterUuid;

    public Long getIcdMasterId() {
        return icdMasterId;
    }

    public void setIcdMasterId(Long icdMasterId) {
        this.icdMasterId = icdMasterId;
    }

    public String getIcdCode() {
        return icdCode;
    }

    public void setIcdCode(String icdCode) {
        this.icdCode = icdCode;
    }

    public String getIcdCodeDesc() {
        return icdCodeDesc;
    }

    public void setIcdCodeDesc(String icdCodeDesc) {
        this.icdCodeDesc = icdCodeDesc;
    }

    public String getIcdCodeType() {
        return icdCodeType;
    }

    public void setIcdCodeType(String icdCodeType) {
        this.icdCodeType = icdCodeType;
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

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getIcdMasterUuid() {
        return icdMasterUuid;
    }

    public void setIcdMasterUuid(UUID icdMasterUuid) {
        this.icdMasterUuid = icdMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IcdMasterDTO)) {
            return false;
        }

        IcdMasterDTO icdMasterDTO = (IcdMasterDTO) o;
        if (this.icdMasterId == null) {
            return false;
        }
        return Objects.equals(this.icdMasterId, icdMasterDTO.icdMasterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.icdMasterId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IcdMasterDTO{" +
            "icdMasterId=" + getIcdMasterId() +
            ", icdCode='" + getIcdCode() + "'" +
            ", icdCodeDesc='" + getIcdCodeDesc() + "'" +
            ", icdCodeType='" + getIcdCodeType() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", icdMasterUuid='" + getIcdMasterUuid() + "'" +
            "}";
    }
}
