package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.EndpointMaster} entity.
 */
public class EndpointMasterDTO implements Serializable {

    private Long endpointId;

    private String endpointName;

    private String endpointGroup;

    private String endpointDesc;

    private String endpointUrl;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID endpointMasterUuid;

    public Long getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(Long endpointId) {
        this.endpointId = endpointId;
    }

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getEndpointGroup() {
        return endpointGroup;
    }

    public void setEndpointGroup(String endpointGroup) {
        this.endpointGroup = endpointGroup;
    }

    public String getEndpointDesc() {
        return endpointDesc;
    }

    public void setEndpointDesc(String endpointDesc) {
        this.endpointDesc = endpointDesc;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
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

    public UUID getEndpointMasterUuid() {
        return endpointMasterUuid;
    }

    public void setEndpointMasterUuid(UUID endpointMasterUuid) {
        this.endpointMasterUuid = endpointMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EndpointMasterDTO)) {
            return false;
        }

        EndpointMasterDTO endpointMasterDTO = (EndpointMasterDTO) o;
        if (this.endpointId == null) {
            return false;
        }
        return Objects.equals(this.endpointId, endpointMasterDTO.endpointId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.endpointId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EndpointMasterDTO{" +
            "endpointId=" + getEndpointId() +
            ", endpointName='" + getEndpointName() + "'" +
            ", endpointGroup='" + getEndpointGroup() + "'" +
            ", endpointDesc='" + getEndpointDesc() + "'" +
            ", endpointUrl='" + getEndpointUrl() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", endpointMasterUuid='" + getEndpointMasterUuid() + "'" +
            "}";
    }
}
