package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.FunctionalityEndpointMapping} entity.
 */
public class FunctionalityEndpointMappingDTO implements Serializable {

    private Long functionalityEndpointMappingId;

    private Long endpointId;

    private Long functionalityId;

    private String mappingDesc;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID functionalityEndpointMappingUuid;

    public Long getFunctionalityEndpointMappingId() {
        return functionalityEndpointMappingId;
    }

    public void setFunctionalityEndpointMappingId(Long functionalityEndpointMappingId) {
        this.functionalityEndpointMappingId = functionalityEndpointMappingId;
    }

    public Long getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(Long endpointId) {
        this.endpointId = endpointId;
    }

    public Long getFunctionalityId() {
        return functionalityId;
    }

    public void setFunctionalityId(Long functionalityId) {
        this.functionalityId = functionalityId;
    }

    public String getMappingDesc() {
        return mappingDesc;
    }

    public void setMappingDesc(String mappingDesc) {
        this.mappingDesc = mappingDesc;
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

    public UUID getFunctionalityEndpointMappingUuid() {
        return functionalityEndpointMappingUuid;
    }

    public void setFunctionalityEndpointMappingUuid(UUID functionalityEndpointMappingUuid) {
        this.functionalityEndpointMappingUuid = functionalityEndpointMappingUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FunctionalityEndpointMappingDTO)) {
            return false;
        }

        FunctionalityEndpointMappingDTO functionalityEndpointMappingDTO = (FunctionalityEndpointMappingDTO) o;
        if (this.functionalityEndpointMappingId == null) {
            return false;
        }
        return Objects.equals(this.functionalityEndpointMappingId, functionalityEndpointMappingDTO.functionalityEndpointMappingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.functionalityEndpointMappingId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FunctionalityEndpointMappingDTO{" +
            "functionalityEndpointMappingId=" + getFunctionalityEndpointMappingId() +
            ", endpointId=" + getEndpointId() +
            ", functionalityId=" + getFunctionalityId() +
            ", mappingDesc='" + getMappingDesc() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", functionalityEndpointMappingUuid='" + getFunctionalityEndpointMappingUuid() + "'" +
            "}";
    }
}
