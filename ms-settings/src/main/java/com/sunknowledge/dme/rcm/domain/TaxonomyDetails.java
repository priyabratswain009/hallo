package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TaxonomyDetails.
 */
@Entity
@Table(name = "t_taxonomy_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaxonomyDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "taxonomy_details_id")
    private Long taxonomyDetailsId;

    @Column(name = "taxonomy_code")
    private String taxonomyCode;

    @Column(name = "taxonomy_name")
    private String taxonomyName;

    @Column(name = "taxonomy_details")
    private String taxonomyDetails;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "taxonomy_details_uuid")
    private UUID taxonomyDetailsUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getTaxonomyDetailsId() {
        return this.taxonomyDetailsId;
    }

    public TaxonomyDetails taxonomyDetailsId(Long taxonomyDetailsId) {
        this.setTaxonomyDetailsId(taxonomyDetailsId);
        return this;
    }

    public void setTaxonomyDetailsId(Long taxonomyDetailsId) {
        this.taxonomyDetailsId = taxonomyDetailsId;
    }

    public String getTaxonomyCode() {
        return this.taxonomyCode;
    }

    public TaxonomyDetails taxonomyCode(String taxonomyCode) {
        this.setTaxonomyCode(taxonomyCode);
        return this;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
    }

    public String getTaxonomyName() {
        return this.taxonomyName;
    }

    public TaxonomyDetails taxonomyName(String taxonomyName) {
        this.setTaxonomyName(taxonomyName);
        return this;
    }

    public void setTaxonomyName(String taxonomyName) {
        this.taxonomyName = taxonomyName;
    }

    public String getTaxonomyDetails() {
        return this.taxonomyDetails;
    }

    public TaxonomyDetails taxonomyDetails(String taxonomyDetails) {
        this.setTaxonomyDetails(taxonomyDetails);
        return this;
    }

    public void setTaxonomyDetails(String taxonomyDetails) {
        this.taxonomyDetails = taxonomyDetails;
    }

    public String getStatus() {
        return this.status;
    }

    public TaxonomyDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public TaxonomyDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public TaxonomyDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public TaxonomyDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public TaxonomyDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public TaxonomyDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public TaxonomyDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getTaxonomyDetailsUuid() {
        return this.taxonomyDetailsUuid;
    }

    public TaxonomyDetails taxonomyDetailsUuid(UUID taxonomyDetailsUuid) {
        this.setTaxonomyDetailsUuid(taxonomyDetailsUuid);
        return this;
    }

    public void setTaxonomyDetailsUuid(UUID taxonomyDetailsUuid) {
        this.taxonomyDetailsUuid = taxonomyDetailsUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaxonomyDetails)) {
            return false;
        }
        return taxonomyDetailsId != null && taxonomyDetailsId.equals(((TaxonomyDetails) o).taxonomyDetailsId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaxonomyDetails{" +
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
