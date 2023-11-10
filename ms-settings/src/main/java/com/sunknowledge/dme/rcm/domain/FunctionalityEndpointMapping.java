package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FunctionalityEndpointMapping.
 */
@Entity
@Table(name = "t_functionality_endpoint_mapping")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FunctionalityEndpointMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "functionality_endpoint_mapping_id")
    private Long functionalityEndpointMappingId;

    @Column(name = "endpoint_id")
    private Long endpointId;

    @Column(name = "functionality_id")
    private Long functionalityId;

    @Column(name = "mapping_desc")
    private String mappingDesc;

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

    @Column(name = "functionality_endpoint_mapping_uuid")
    private UUID functionalityEndpointMappingUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getFunctionalityEndpointMappingId() {
        return this.functionalityEndpointMappingId;
    }

    public FunctionalityEndpointMapping functionalityEndpointMappingId(Long functionalityEndpointMappingId) {
        this.setFunctionalityEndpointMappingId(functionalityEndpointMappingId);
        return this;
    }

    public void setFunctionalityEndpointMappingId(Long functionalityEndpointMappingId) {
        this.functionalityEndpointMappingId = functionalityEndpointMappingId;
    }

    public Long getEndpointId() {
        return this.endpointId;
    }

    public FunctionalityEndpointMapping endpointId(Long endpointId) {
        this.setEndpointId(endpointId);
        return this;
    }

    public void setEndpointId(Long endpointId) {
        this.endpointId = endpointId;
    }

    public Long getFunctionalityId() {
        return this.functionalityId;
    }

    public FunctionalityEndpointMapping functionalityId(Long functionalityId) {
        this.setFunctionalityId(functionalityId);
        return this;
    }

    public void setFunctionalityId(Long functionalityId) {
        this.functionalityId = functionalityId;
    }

    public String getMappingDesc() {
        return this.mappingDesc;
    }

    public FunctionalityEndpointMapping mappingDesc(String mappingDesc) {
        this.setMappingDesc(mappingDesc);
        return this;
    }

    public void setMappingDesc(String mappingDesc) {
        this.mappingDesc = mappingDesc;
    }

    public String getStatus() {
        return this.status;
    }

    public FunctionalityEndpointMapping status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public FunctionalityEndpointMapping createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public FunctionalityEndpointMapping createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public FunctionalityEndpointMapping createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public FunctionalityEndpointMapping updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public FunctionalityEndpointMapping updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public FunctionalityEndpointMapping updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getFunctionalityEndpointMappingUuid() {
        return this.functionalityEndpointMappingUuid;
    }

    public FunctionalityEndpointMapping functionalityEndpointMappingUuid(UUID functionalityEndpointMappingUuid) {
        this.setFunctionalityEndpointMappingUuid(functionalityEndpointMappingUuid);
        return this;
    }

    public void setFunctionalityEndpointMappingUuid(UUID functionalityEndpointMappingUuid) {
        this.functionalityEndpointMappingUuid = functionalityEndpointMappingUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FunctionalityEndpointMapping)) {
            return false;
        }
        return (
            functionalityEndpointMappingId != null &&
            functionalityEndpointMappingId.equals(((FunctionalityEndpointMapping) o).functionalityEndpointMappingId)
        );
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FunctionalityEndpointMapping{" +
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
