package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.FunctionalAbility} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FunctionalAbilityDTO implements Serializable {

    private Long functionalAbilityId;

    private String functionalAbilityCode;

    private String functionalAbilityName;

    private String description;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private LocalDate updatedDate;

    private UUID functionalAbilityUuid;

    public Long getFunctionalAbilityId() {
        return functionalAbilityId;
    }

    public void setFunctionalAbilityId(Long functionalAbilityId) {
        this.functionalAbilityId = functionalAbilityId;
    }

    public String getFunctionalAbilityCode() {
        return functionalAbilityCode;
    }

    public void setFunctionalAbilityCode(String functionalAbilityCode) {
        this.functionalAbilityCode = functionalAbilityCode;
    }

    public String getFunctionalAbilityName() {
        return functionalAbilityName;
    }

    public void setFunctionalAbilityName(String functionalAbilityName) {
        this.functionalAbilityName = functionalAbilityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getFunctionalAbilityUuid() {
        return functionalAbilityUuid;
    }

    public void setFunctionalAbilityUuid(UUID functionalAbilityUuid) {
        this.functionalAbilityUuid = functionalAbilityUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FunctionalAbilityDTO)) {
            return false;
        }

        FunctionalAbilityDTO functionalAbilityDTO = (FunctionalAbilityDTO) o;
        if (this.functionalAbilityId == null) {
            return false;
        }
        return Objects.equals(this.functionalAbilityId, functionalAbilityDTO.functionalAbilityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.functionalAbilityId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FunctionalAbilityDTO{" +
            "functionalAbilityId=" + getFunctionalAbilityId() +
            ", functionalAbilityCode='" + getFunctionalAbilityCode() + "'" +
            ", functionalAbilityName='" + getFunctionalAbilityName() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", functionalAbilityUuid='" + getFunctionalAbilityUuid() + "'" +
            "}";
    }
}
