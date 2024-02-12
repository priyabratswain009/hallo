package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ParSoMap.
 */
@Table("t_par_so_map")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ParSoMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("par_so_id")
    private Long parSoId;

    @Column("par_id")
    private Long parId;

    @Column("par_no")
    private String parNo;

    @Column("so_id")
    private Long soId;

    @Column("so_no")
    private String soNo;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("par_so_map_uuid")
    private UUID parSoMapUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getParSoId() {
        return this.parSoId;
    }

    public ParSoMap parSoId(Long parSoId) {
        this.setParSoId(parSoId);
        return this;
    }

    public void setParSoId(Long parSoId) {
        this.parSoId = parSoId;
    }

    public Long getParId() {
        return this.parId;
    }

    public ParSoMap parId(Long parId) {
        this.setParId(parId);
        return this;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParNo() {
        return this.parNo;
    }

    public ParSoMap parNo(String parNo) {
        this.setParNo(parNo);
        return this;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public Long getSoId() {
        return this.soId;
    }

    public ParSoMap soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return this.soNo;
    }

    public ParSoMap soNo(String soNo) {
        this.setSoNo(soNo);
        return this;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public String getStatus() {
        return this.status;
    }

    public ParSoMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ParSoMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ParSoMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ParSoMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ParSoMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ParSoMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ParSoMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getParSoMapUuid() {
        return this.parSoMapUuid;
    }

    public ParSoMap parSoMapUuid(UUID parSoMapUuid) {
        this.setParSoMapUuid(parSoMapUuid);
        return this;
    }

    public void setParSoMapUuid(UUID parSoMapUuid) {
        this.parSoMapUuid = parSoMapUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParSoMap)) {
            return false;
        }
        return parSoId != null && parSoId.equals(((ParSoMap) o).parSoId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParSoMap{" +
            "parSoId=" + getParSoId() +
            ", parId=" + getParId() +
            ", parNo='" + getParNo() + "'" +
            ", soId=" + getSoId() +
            ", soNo='" + getSoNo() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", parSoMapUuid='" + getParSoMapUuid() + "'" +
            "}";
    }
}
