package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.TaxonomyDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaxonomyDetailsDTO implements Serializable {

    private Long taxonomyDetailsId;

    private String taxonomyCode;

    private String taxonomyName;

    private String taxonomyDetails;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID taxonomyDetailsUuid;

    public Long getTaxonomyDetailsId() {
        return taxonomyDetailsId;
    }

    public void setTaxonomyDetailsId(Long taxonomyDetailsId) {
        this.taxonomyDetailsId = taxonomyDetailsId;
    }

    public String getTaxonomyCode() {
        return taxonomyCode;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
    }

    public String getTaxonomyName() {
        return taxonomyName;
    }

    public void setTaxonomyName(String taxonomyName) {
        this.taxonomyName = taxonomyName;
    }

    public String getTaxonomyDetails() {
        return taxonomyDetails;
    }

    public void setTaxonomyDetails(String taxonomyDetails) {
        this.taxonomyDetails = taxonomyDetails;
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

    public UUID getTaxonomyDetailsUuid() {
        return taxonomyDetailsUuid;
    }

    public void setTaxonomyDetailsUuid(UUID taxonomyDetailsUuid) {
        this.taxonomyDetailsUuid = taxonomyDetailsUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaxonomyDetailsDTO)) {
            return false;
        }

        TaxonomyDetailsDTO taxonomyDetailsDTO = (TaxonomyDetailsDTO) o;
        if (this.taxonomyDetailsId == null) {
            return false;
        }
        return Objects.equals(this.taxonomyDetailsId, taxonomyDetailsDTO.taxonomyDetailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.taxonomyDetailsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaxonomyDetailsDTO{" +
            "taxonomyDetailsId=" + getTaxonomyDetailsId() +
            ", taxonomyCode='" + getTaxonomyCode() + "'" +
            ", taxonomyName='" + getTaxonomyName() + "'" +
            ", taxonomyDetails='" + getTaxonomyDetails() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", taxonomyDetailsUuid='" + getTaxonomyDetailsUuid() + "'" +
            "}";
    }
}
