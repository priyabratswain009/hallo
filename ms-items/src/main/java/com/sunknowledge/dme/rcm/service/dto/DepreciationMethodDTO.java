package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DepreciationMethod} entity.
 */
public class DepreciationMethodDTO implements Serializable {

    private Long depreciationMethodId;

    private String depreciationMethodName;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID depreciationMethodUuid;

    private LocalDate updatedDate;

    public Long getDepreciationMethodId() {
        return depreciationMethodId;
    }

    public void setDepreciationMethodId(Long depreciationMethodId) {
        this.depreciationMethodId = depreciationMethodId;
    }

    public String getDepreciationMethodName() {
        return depreciationMethodName;
    }

    public void setDepreciationMethodName(String depreciationMethodName) {
        this.depreciationMethodName = depreciationMethodName;
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

    public UUID getDepreciationMethodUuid() {
        return depreciationMethodUuid;
    }

    public void setDepreciationMethodUuid(UUID depreciationMethodUuid) {
        this.depreciationMethodUuid = depreciationMethodUuid;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepreciationMethodDTO)) {
            return false;
        }

        DepreciationMethodDTO depreciationMethodDTO = (DepreciationMethodDTO) o;
        if (this.depreciationMethodId == null) {
            return false;
        }
        return Objects.equals(this.depreciationMethodId, depreciationMethodDTO.depreciationMethodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.depreciationMethodId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepreciationMethodDTO{" +
            "depreciationMethodId=" + getDepreciationMethodId() +
            ", depreciationMethodName='" + getDepreciationMethodName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", depreciationMethodUuid='" + getDepreciationMethodUuid() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
}
