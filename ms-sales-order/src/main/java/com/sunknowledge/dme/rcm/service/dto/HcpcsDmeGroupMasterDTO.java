package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.HcpcsDmeGroupMaster} entity.
 */
public class HcpcsDmeGroupMasterDTO implements Serializable {

    private Long hcpcsDmeId;

    private String hcpcsCode;

    private String dmeGroupName;

    private Long dmeGroupId;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID hcpcsDmeGroupMasterUuid;

    public Long getHcpcsDmeId() {
        return hcpcsDmeId;
    }

    public void setHcpcsDmeId(Long hcpcsDmeId) {
        this.hcpcsDmeId = hcpcsDmeId;
    }

    public String getHcpcsCode() {
        return hcpcsCode;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public String getDmeGroupName() {
        return dmeGroupName;
    }

    public void setDmeGroupName(String dmeGroupName) {
        this.dmeGroupName = dmeGroupName;
    }

    public Long getDmeGroupId() {
        return dmeGroupId;
    }

    public void setDmeGroupId(Long dmeGroupId) {
        this.dmeGroupId = dmeGroupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
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

    public UUID getHcpcsDmeGroupMasterUuid() {
        return hcpcsDmeGroupMasterUuid;
    }

    public void setHcpcsDmeGroupMasterUuid(UUID hcpcsDmeGroupMasterUuid) {
        this.hcpcsDmeGroupMasterUuid = hcpcsDmeGroupMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HcpcsDmeGroupMasterDTO)) {
            return false;
        }

        HcpcsDmeGroupMasterDTO hcpcsDmeGroupMasterDTO = (HcpcsDmeGroupMasterDTO) o;
        if (this.hcpcsDmeId == null) {
            return false;
        }
        return Objects.equals(this.hcpcsDmeId, hcpcsDmeGroupMasterDTO.hcpcsDmeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.hcpcsDmeId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HcpcsDmeGroupMasterDTO{" +
            "hcpcsDmeId=" + getHcpcsDmeId() +
            ", hcpcsCode='" + getHcpcsCode() + "'" +
            ", dmeGroupName='" + getDmeGroupName() + "'" +
            ", dmeGroupId=" + getDmeGroupId() +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", hcpcsDmeGroupMasterUuid='" + getHcpcsDmeGroupMasterUuid() + "'" +
            "}";
    }
}
