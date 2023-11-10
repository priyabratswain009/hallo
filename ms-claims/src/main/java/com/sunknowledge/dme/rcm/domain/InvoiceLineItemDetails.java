package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A InvoiceLineItemDetails.
 */
@Entity
@Table(name = "t_invoice_line_item_details")
public class InvoiceLineItemDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "invoice_line_item_details_id", nullable = false)
    private Long invoiceLineItemDetailsId;

    @Column(name = "primary_invoice_no")
    private String primaryInvoiceNo;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_qty")
    private Long itemQty;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "hcpcs_code")
    private String hcpcsCode;

    @Column(name = "charged_amount")
    private Double chargedAmount;

    @Column(name = "allow_amount")
    private Double allowAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "invoice_line_item_details_uuid")
    private UUID invoiceLineItemDetailsUuid;

    @Column(name = "secondary_invoice_no")
    private String secondaryInvoiceNo;

    @Column(name = "tertiary_invoice_no")
    private String tertiaryInvoiceNo;

    @Column(name = "patient_invoice_no")
    private String patientInvoiceNo;

    @Column(name = "primary_invoice_id")
    private Long primaryInvoiceId;

    @Column(name = "secondary_invoice_id")
    private Long secondaryInvoiceId;

    @Column(name = "tertiary_invoice_id")
    private Long tertiaryInvoiceId;

    @Column(name = "patient_invoice_id")
    private Long patientInvoiceId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getInvoiceLineItemDetailsId() {
        return this.invoiceLineItemDetailsId;
    }

    public InvoiceLineItemDetails invoiceLineItemDetailsId(Long invoiceLineItemDetailsId) {
        this.setInvoiceLineItemDetailsId(invoiceLineItemDetailsId);
        return this;
    }

    public void setInvoiceLineItemDetailsId(Long invoiceLineItemDetailsId) {
        this.invoiceLineItemDetailsId = invoiceLineItemDetailsId;
    }

    public String getPrimaryInvoiceNo() {
        return this.primaryInvoiceNo;
    }

    public InvoiceLineItemDetails primaryInvoiceNo(String primaryInvoiceNo) {
        this.setPrimaryInvoiceNo(primaryInvoiceNo);
        return this;
    }

    public void setPrimaryInvoiceNo(String primaryInvoiceNo) {
        this.primaryInvoiceNo = primaryInvoiceNo;
    }

    public LocalDate getInvoiceDate() {
        return this.invoiceDate;
    }

    public InvoiceLineItemDetails invoiceDate(LocalDate invoiceDate) {
        this.setInvoiceDate(invoiceDate);
        return this;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public InvoiceLineItemDetails itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemQty() {
        return this.itemQty;
    }

    public InvoiceLineItemDetails itemQty(Long itemQty) {
        this.setItemQty(itemQty);
        return this;
    }

    public void setItemQty(Long itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemName() {
        return this.itemName;
    }

    public InvoiceLineItemDetails itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getHcpcsCode() {
        return this.hcpcsCode;
    }

    public InvoiceLineItemDetails hcpcsCode(String hcpcsCode) {
        this.setHcpcsCode(hcpcsCode);
        return this;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public Double getChargedAmount() {
        return this.chargedAmount;
    }

    public InvoiceLineItemDetails chargedAmount(Double chargedAmount) {
        this.setChargedAmount(chargedAmount);
        return this;
    }

    public void setChargedAmount(Double chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    public Double getAllowAmount() {
        return this.allowAmount;
    }

    public InvoiceLineItemDetails allowAmount(Double allowAmount) {
        this.setAllowAmount(allowAmount);
        return this;
    }

    public void setAllowAmount(Double allowAmount) {
        this.allowAmount = allowAmount;
    }

    public String getStatus() {
        return this.status;
    }

    public InvoiceLineItemDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public InvoiceLineItemDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public InvoiceLineItemDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public InvoiceLineItemDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public InvoiceLineItemDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public InvoiceLineItemDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public InvoiceLineItemDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getInvoiceLineItemDetailsUuid() {
        return this.invoiceLineItemDetailsUuid;
    }

    public InvoiceLineItemDetails invoiceLineItemDetailsUuid(UUID invoiceLineItemDetailsUuid) {
        this.setInvoiceLineItemDetailsUuid(invoiceLineItemDetailsUuid);
        return this;
    }

    public void setInvoiceLineItemDetailsUuid(UUID invoiceLineItemDetailsUuid) {
        this.invoiceLineItemDetailsUuid = invoiceLineItemDetailsUuid;
    }

    public String getSecondaryInvoiceNo() {
        return this.secondaryInvoiceNo;
    }

    public InvoiceLineItemDetails secondaryInvoiceNo(String secondaryInvoiceNo) {
        this.setSecondaryInvoiceNo(secondaryInvoiceNo);
        return this;
    }

    public void setSecondaryInvoiceNo(String secondaryInvoiceNo) {
        this.secondaryInvoiceNo = secondaryInvoiceNo;
    }

    public String getTertiaryInvoiceNo() {
        return this.tertiaryInvoiceNo;
    }

    public InvoiceLineItemDetails tertiaryInvoiceNo(String tertiaryInvoiceNo) {
        this.setTertiaryInvoiceNo(tertiaryInvoiceNo);
        return this;
    }

    public void setTertiaryInvoiceNo(String tertiaryInvoiceNo) {
        this.tertiaryInvoiceNo = tertiaryInvoiceNo;
    }

    public String getPatientInvoiceNo() {
        return this.patientInvoiceNo;
    }

    public InvoiceLineItemDetails patientInvoiceNo(String patientInvoiceNo) {
        this.setPatientInvoiceNo(patientInvoiceNo);
        return this;
    }

    public void setPatientInvoiceNo(String patientInvoiceNo) {
        this.patientInvoiceNo = patientInvoiceNo;
    }

    public Long getPrimaryInvoiceId() {
        return this.primaryInvoiceId;
    }

    public InvoiceLineItemDetails primaryInvoiceId(Long primaryInvoiceId) {
        this.setPrimaryInvoiceId(primaryInvoiceId);
        return this;
    }

    public void setPrimaryInvoiceId(Long primaryInvoiceId) {
        this.primaryInvoiceId = primaryInvoiceId;
    }

    public Long getSecondaryInvoiceId() {
        return this.secondaryInvoiceId;
    }

    public InvoiceLineItemDetails secondaryInvoiceId(Long secondaryInvoiceId) {
        this.setSecondaryInvoiceId(secondaryInvoiceId);
        return this;
    }

    public void setSecondaryInvoiceId(Long secondaryInvoiceId) {
        this.secondaryInvoiceId = secondaryInvoiceId;
    }

    public Long getTertiaryInvoiceId() {
        return this.tertiaryInvoiceId;
    }

    public InvoiceLineItemDetails tertiaryInvoiceId(Long tertiaryInvoiceId) {
        this.setTertiaryInvoiceId(tertiaryInvoiceId);
        return this;
    }

    public void setTertiaryInvoiceId(Long tertiaryInvoiceId) {
        this.tertiaryInvoiceId = tertiaryInvoiceId;
    }

    public Long getPatientInvoiceId() {
        return this.patientInvoiceId;
    }

    public InvoiceLineItemDetails patientInvoiceId(Long patientInvoiceId) {
        this.setPatientInvoiceId(patientInvoiceId);
        return this;
    }

    public void setPatientInvoiceId(Long patientInvoiceId) {
        this.patientInvoiceId = patientInvoiceId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoiceLineItemDetails)) {
            return false;
        }
        return invoiceLineItemDetailsId != null && invoiceLineItemDetailsId.equals(((InvoiceLineItemDetails) o).invoiceLineItemDetailsId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoiceLineItemDetails{" +
            "invoiceLineItemDetailsId=" + getInvoiceLineItemDetailsId() +
            ", primaryInvoiceNo='" + getPrimaryInvoiceNo() + "'" +
            ", invoiceDate='" + getInvoiceDate() + "'" +
            ", itemId=" + getItemId() +
            ", itemQty=" + getItemQty() +
            ", itemName='" + getItemName() + "'" +
            ", hcpcsCode='" + getHcpcsCode() + "'" +
            ", chargedAmount=" + getChargedAmount() +
            ", allowAmount=" + getAllowAmount() +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", invoiceLineItemDetailsUuid='" + getInvoiceLineItemDetailsUuid() + "'" +
            ", secondaryInvoiceNo='" + getSecondaryInvoiceNo() + "'" +
            ", tertiaryInvoiceNo='" + getTertiaryInvoiceNo() + "'" +
            ", patientInvoiceNo='" + getPatientInvoiceNo() + "'" +
            ", primaryInvoiceId=" + getPrimaryInvoiceId() +
            ", secondaryInvoiceId=" + getSecondaryInvoiceId() +
            ", tertiaryInvoiceId=" + getTertiaryInvoiceId() +
            ", patientInvoiceId=" + getPatientInvoiceId() +
            "}";
    }
}
