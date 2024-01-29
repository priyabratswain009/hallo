package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A ProcedureCodeMaster.
 */
@Entity
@Table(name = "t_procedure_code_master")
public class ProcedureCodeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "procedure_code_id")
    private Long procedureCodeId;

    @Column(name = "item_procedure_code_desc")
    private String itemProcedureCodeDesc;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "procedure_code")
    private String procedureCode;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "item_procedure_code_uuid")
    private UUID itemProcedureCodeUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getProcedureCodeId() {
        return this.procedureCodeId;
    }

    public ProcedureCodeMaster procedureCodeId(Long procedureCodeId) {
        this.setProcedureCodeId(procedureCodeId);
        return this;
    }

    public void setProcedureCodeId(Long procedureCodeId) {
        this.procedureCodeId = procedureCodeId;
    }

    public String getItemProcedureCodeDesc() {
        return this.itemProcedureCodeDesc;
    }

    public ProcedureCodeMaster itemProcedureCodeDesc(String itemProcedureCodeDesc) {
        this.setItemProcedureCodeDesc(itemProcedureCodeDesc);
        return this;
    }

    public void setItemProcedureCodeDesc(String itemProcedureCodeDesc) {
        this.itemProcedureCodeDesc = itemProcedureCodeDesc;
    }

    public String getStatus() {
        return this.status;
    }

    public ProcedureCodeMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ProcedureCodeMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ProcedureCodeMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ProcedureCodeMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getProcedureCode() {
        return this.procedureCode;
    }

    public ProcedureCodeMaster procedureCode(String procedureCode) {
        this.setProcedureCode(procedureCode);
        return this;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ProcedureCodeMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ProcedureCodeMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ProcedureCodeMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public UUID getItemProcedureCodeUuid() {
        return this.itemProcedureCodeUuid;
    }

    public ProcedureCodeMaster itemProcedureCodeUuid(UUID itemProcedureCodeUuid) {
        this.setItemProcedureCodeUuid(itemProcedureCodeUuid);
        return this;
    }

    public void setItemProcedureCodeUuid(UUID itemProcedureCodeUuid) {
        this.itemProcedureCodeUuid = itemProcedureCodeUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProcedureCodeMaster)) {
            return false;
        }
        return procedureCodeId != null && procedureCodeId.equals(((ProcedureCodeMaster) o).procedureCodeId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProcedureCodeMaster{" +
            "procedureCodeId=" + getProcedureCodeId() +
            ", itemProcedureCodeDesc='" + getItemProcedureCodeDesc() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", procedureCode='" + getProcedureCode() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", itemProcedureCodeUuid='" + getItemProcedureCodeUuid() + "'" +
            "}";
    }
}
