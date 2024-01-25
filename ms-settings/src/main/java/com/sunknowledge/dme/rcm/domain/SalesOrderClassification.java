package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SalesOrderClassification.
 */
@Entity
@Table(name = "t_sales_order_classification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalesOrderClassification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "order_classification_id")
    private Long orderClassificationId;

    @Column(name = "order_classification_description")
    private String orderClassificationDescription;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "status")
    private String status;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "sales_order_classification_uuid")
    private UUID salesOrderClassificationUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getOrderClassificationId() {
        return this.orderClassificationId;
    }

    public SalesOrderClassification orderClassificationId(Long orderClassificationId) {
        this.setOrderClassificationId(orderClassificationId);
        return this;
    }

    public void setOrderClassificationId(Long orderClassificationId) {
        this.orderClassificationId = orderClassificationId;
    }

    public String getOrderClassificationDescription() {
        return this.orderClassificationDescription;
    }

    public SalesOrderClassification orderClassificationDescription(String orderClassificationDescription) {
        this.setOrderClassificationDescription(orderClassificationDescription);
        return this;
    }

    public void setOrderClassificationDescription(String orderClassificationDescription) {
        this.orderClassificationDescription = orderClassificationDescription;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public SalesOrderClassification createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public SalesOrderClassification createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return this.status;
    }

    public SalesOrderClassification status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SalesOrderClassification updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public SalesOrderClassification updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public SalesOrderClassification createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SalesOrderClassification updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getSalesOrderClassificationUuid() {
        return this.salesOrderClassificationUuid;
    }

    public SalesOrderClassification salesOrderClassificationUuid(UUID salesOrderClassificationUuid) {
        this.setSalesOrderClassificationUuid(salesOrderClassificationUuid);
        return this;
    }

    public void setSalesOrderClassificationUuid(UUID salesOrderClassificationUuid) {
        this.salesOrderClassificationUuid = salesOrderClassificationUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderClassification)) {
            return false;
        }
        return orderClassificationId != null && orderClassificationId.equals(((SalesOrderClassification) o).orderClassificationId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderClassification{" +
            "orderClassificationId=" + getOrderClassificationId() +
            ", orderClassificationDescription='" + getOrderClassificationDescription() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", salesOrderClassificationUuid='" + getSalesOrderClassificationUuid() + "'" +
            "}";
    }
}
