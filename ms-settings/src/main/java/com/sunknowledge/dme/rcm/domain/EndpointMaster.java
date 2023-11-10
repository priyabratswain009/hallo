package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EndpointMaster.
 */
@Entity
@Table(name = "t_endpoint_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EndpointMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "endpoint_id")
    private Long endpointId;

    @Column(name = "endpoint_name")
    private String endpointName;

    @Column(name = "endpoint_group")
    private String endpointGroup;

    @Column(name = "endpoint_desc")
    private String endpointDesc;

    @Column(name = "endpoint_url")
    private String endpointUrl;

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

    @Column(name = "endpoint_master_uuid")
    private UUID endpointMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getEndpointId() {
        return this.endpointId;
    }

    public EndpointMaster endpointId(Long endpointId) {
        this.setEndpointId(endpointId);
        return this;
    }

    public void setEndpointId(Long endpointId) {
        this.endpointId = endpointId;
    }

    public String getEndpointName() {
        return this.endpointName;
    }

    public EndpointMaster endpointName(String endpointName) {
        this.setEndpointName(endpointName);
        return this;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getEndpointGroup() {
        return this.endpointGroup;
    }

    public EndpointMaster endpointGroup(String endpointGroup) {
        this.setEndpointGroup(endpointGroup);
        return this;
    }

    public void setEndpointGroup(String endpointGroup) {
        this.endpointGroup = endpointGroup;
    }

    public String getEndpointDesc() {
        return this.endpointDesc;
    }

    public EndpointMaster endpointDesc(String endpointDesc) {
        this.setEndpointDesc(endpointDesc);
        return this;
    }

    public void setEndpointDesc(String endpointDesc) {
        this.endpointDesc = endpointDesc;
    }

    public String getEndpointUrl() {
        return this.endpointUrl;
    }

    public EndpointMaster endpointUrl(String endpointUrl) {
        this.setEndpointUrl(endpointUrl);
        return this;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public String getStatus() {
        return this.status;
    }

    public EndpointMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public EndpointMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public EndpointMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public EndpointMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public EndpointMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public EndpointMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public EndpointMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getEndpointMasterUuid() {
        return this.endpointMasterUuid;
    }

    public EndpointMaster endpointMasterUuid(UUID endpointMasterUuid) {
        this.setEndpointMasterUuid(endpointMasterUuid);
        return this;
    }

    public void setEndpointMasterUuid(UUID endpointMasterUuid) {
        this.endpointMasterUuid = endpointMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EndpointMaster)) {
            return false;
        }
        return endpointId != null && endpointId.equals(((EndpointMaster) o).endpointId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EndpointMaster{" +
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
