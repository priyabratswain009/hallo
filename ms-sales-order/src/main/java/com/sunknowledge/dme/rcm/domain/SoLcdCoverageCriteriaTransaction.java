package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SoLcdCoverageCriteriaTransaction.
 */
@Table("t_so_lcd_coverage_criteria_transaction")
public class SoLcdCoverageCriteriaTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("so_lcd_doc_ref_id")
    private Long soLcdDocRefId;

    @Column("so_id")
    private Long soId;

    @Column("item_id")
    private Long itemId;

    @Column("item_name")
    private String itemName;

    @Column("hcpcs_code")
    private String hcpcsCode;

    @Column("checklist_id")
    private Long checklistId;

    @Column("checklist_name")
    private String checklistName;

    @Column("coverage_criteria_id")
    private Long coverageCriteriaId;

    @Column("value")
    private String value;

    @Column("status")
    private String status;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("so_lcd_coverage_criteria_transaction_uuid")
    private UUID soLcdCoverageCriteriaTransactionUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getSoLcdDocRefId() {
        return this.soLcdDocRefId;
    }

    public SoLcdCoverageCriteriaTransaction soLcdDocRefId(Long soLcdDocRefId) {
        this.setSoLcdDocRefId(soLcdDocRefId);
        return this;
    }

    public void setSoLcdDocRefId(Long soLcdDocRefId) {
        this.soLcdDocRefId = soLcdDocRefId;
    }

    public Long getSoId() {
        return this.soId;
    }

    public SoLcdCoverageCriteriaTransaction soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public SoLcdCoverageCriteriaTransaction itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public SoLcdCoverageCriteriaTransaction itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getHcpcsCode() {
        return this.hcpcsCode;
    }

    public SoLcdCoverageCriteriaTransaction hcpcsCode(String hcpcsCode) {
        this.setHcpcsCode(hcpcsCode);
        return this;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public Long getChecklistId() {
        return this.checklistId;
    }

    public SoLcdCoverageCriteriaTransaction checklistId(Long checklistId) {
        this.setChecklistId(checklistId);
        return this;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

    public String getChecklistName() {
        return this.checklistName;
    }

    public SoLcdCoverageCriteriaTransaction checklistName(String checklistName) {
        this.setChecklistName(checklistName);
        return this;
    }

    public void setChecklistName(String checklistName) {
        this.checklistName = checklistName;
    }

    public Long getCoverageCriteriaId() {
        return this.coverageCriteriaId;
    }

    public SoLcdCoverageCriteriaTransaction coverageCriteriaId(Long coverageCriteriaId) {
        this.setCoverageCriteriaId(coverageCriteriaId);
        return this;
    }

    public void setCoverageCriteriaId(Long coverageCriteriaId) {
        this.coverageCriteriaId = coverageCriteriaId;
    }

    public String getValue() {
        return this.value;
    }

    public SoLcdCoverageCriteriaTransaction value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return this.status;
    }

    public SoLcdCoverageCriteriaTransaction status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public SoLcdCoverageCriteriaTransaction createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public SoLcdCoverageCriteriaTransaction createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public SoLcdCoverageCriteriaTransaction createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public SoLcdCoverageCriteriaTransaction updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SoLcdCoverageCriteriaTransaction updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SoLcdCoverageCriteriaTransaction updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getSoLcdCoverageCriteriaTransactionUuid() {
        return this.soLcdCoverageCriteriaTransactionUuid;
    }

    public SoLcdCoverageCriteriaTransaction soLcdCoverageCriteriaTransactionUuid(UUID soLcdCoverageCriteriaTransactionUuid) {
        this.setSoLcdCoverageCriteriaTransactionUuid(soLcdCoverageCriteriaTransactionUuid);
        return this;
    }

    public void setSoLcdCoverageCriteriaTransactionUuid(UUID soLcdCoverageCriteriaTransactionUuid) {
        this.soLcdCoverageCriteriaTransactionUuid = soLcdCoverageCriteriaTransactionUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoLcdCoverageCriteriaTransaction)) {
            return false;
        }
        return soLcdDocRefId != null && soLcdDocRefId.equals(((SoLcdCoverageCriteriaTransaction) o).soLcdDocRefId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoLcdCoverageCriteriaTransaction{" +
            "soLcdDocRefId=" + getSoLcdDocRefId() +
            ", soId=" + getSoId() +
            ", itemId=" + getItemId() +
            ", itemName='" + getItemName() + "'" +
            ", hcpcsCode='" + getHcpcsCode() + "'" +
            ", checklistId=" + getChecklistId() +
            ", checklistName='" + getChecklistName() + "'" +
            ", coverageCriteriaId=" + getCoverageCriteriaId() +
            ", value='" + getValue() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", soLcdCoverageCriteriaTransactionUuid='" + getSoLcdCoverageCriteriaTransactionUuid() + "'" +
            "}";
    }
}
