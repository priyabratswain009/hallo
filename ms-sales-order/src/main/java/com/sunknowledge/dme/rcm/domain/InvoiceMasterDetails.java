package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A InvoiceMasterDetails.
 */
@Table("t_invoice_master_details")
public class InvoiceMasterDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("invoice_id")
    private Long invoiceId;

    @Column("invoice_no")
    private String invoiceNo;

    @Column("invoice_date")
    private LocalDate invoiceDate;

    @Column("invoice_to_entity")
    private String invoiceToEntity;

    @Column("invoice_to_payor_id")
    private Long invoiceToPayorId;

    @Column("invoice_to_payor_name")
    private String invoiceToPayorName;

    @Column("sales_order_id")
    private Long salesOrderId;

    @Column("sales_order_no")
    private String salesOrderNo;

    @Column("patient_id")
    private Long patientId;

    @Column("patient_first_name")
    private String patientFirstName;

    @Column("patient_middle_name")
    private String patientMiddleName;

    @Column("patient_last_name")
    private String patientLastName;

    @Column("item_qty_total")
    private Long itemQtyTotal;

    @Column("charged_amount_total")
    private Double chargedAmountTotal;

    @Column("allow_amount_total")
    private Double allowAmountTotal;

    @Column("payment_amount_total")
    private Double paymentAmountTotal;

    @Column("tax_amount_total")
    private Double taxAmountTotal;

    @Column("adj_amount_total")
    private Double adjAmountTotal;

    @Column("balance_amount_total")
    private Double balanceAmountTotal;

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

    @Column("invoice_master_details_uuid")
    private UUID invoiceMasterDetailsUuid;

    @Column("invoice_status")
    private String invoiceStatus;

    @Column("primary_submission_master_id")
    private Long primarySubmissionMasterId;

    @Column("claim_control_no")
    private String claimControlNo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getInvoiceId() {
        return this.invoiceId;
    }

    public InvoiceMasterDetails invoiceId(Long invoiceId) {
        this.setInvoiceId(invoiceId);
        return this;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    public InvoiceMasterDetails invoiceNo(String invoiceNo) {
        this.setInvoiceNo(invoiceNo);
        return this;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public LocalDate getInvoiceDate() {
        return this.invoiceDate;
    }

    public InvoiceMasterDetails invoiceDate(LocalDate invoiceDate) {
        this.setInvoiceDate(invoiceDate);
        return this;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceToEntity() {
        return this.invoiceToEntity;
    }

    public InvoiceMasterDetails invoiceToEntity(String invoiceToEntity) {
        this.setInvoiceToEntity(invoiceToEntity);
        return this;
    }

    public void setInvoiceToEntity(String invoiceToEntity) {
        this.invoiceToEntity = invoiceToEntity;
    }

    public Long getInvoiceToPayorId() {
        return this.invoiceToPayorId;
    }

    public InvoiceMasterDetails invoiceToPayorId(Long invoiceToPayorId) {
        this.setInvoiceToPayorId(invoiceToPayorId);
        return this;
    }

    public void setInvoiceToPayorId(Long invoiceToPayorId) {
        this.invoiceToPayorId = invoiceToPayorId;
    }

    public String getInvoiceToPayorName() {
        return this.invoiceToPayorName;
    }

    public InvoiceMasterDetails invoiceToPayorName(String invoiceToPayorName) {
        this.setInvoiceToPayorName(invoiceToPayorName);
        return this;
    }

    public void setInvoiceToPayorName(String invoiceToPayorName) {
        this.invoiceToPayorName = invoiceToPayorName;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public InvoiceMasterDetails salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesOrderNo() {
        return this.salesOrderNo;
    }

    public InvoiceMasterDetails salesOrderNo(String salesOrderNo) {
        this.setSalesOrderNo(salesOrderNo);
        return this;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public InvoiceMasterDetails patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public InvoiceMasterDetails patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return this.patientMiddleName;
    }

    public InvoiceMasterDetails patientMiddleName(String patientMiddleName) {
        this.setPatientMiddleName(patientMiddleName);
        return this;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public InvoiceMasterDetails patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public Long getItemQtyTotal() {
        return this.itemQtyTotal;
    }

    public InvoiceMasterDetails itemQtyTotal(Long itemQtyTotal) {
        this.setItemQtyTotal(itemQtyTotal);
        return this;
    }

    public void setItemQtyTotal(Long itemQtyTotal) {
        this.itemQtyTotal = itemQtyTotal;
    }

    public Double getChargedAmountTotal() {
        return this.chargedAmountTotal;
    }

    public InvoiceMasterDetails chargedAmountTotal(Double chargedAmountTotal) {
        this.setChargedAmountTotal(chargedAmountTotal);
        return this;
    }

    public void setChargedAmountTotal(Double chargedAmountTotal) {
        this.chargedAmountTotal = chargedAmountTotal;
    }

    public Double getAllowAmountTotal() {
        return this.allowAmountTotal;
    }

    public InvoiceMasterDetails allowAmountTotal(Double allowAmountTotal) {
        this.setAllowAmountTotal(allowAmountTotal);
        return this;
    }

    public void setAllowAmountTotal(Double allowAmountTotal) {
        this.allowAmountTotal = allowAmountTotal;
    }

    public Double getPaymentAmountTotal() {
        return this.paymentAmountTotal;
    }

    public InvoiceMasterDetails paymentAmountTotal(Double paymentAmountTotal) {
        this.setPaymentAmountTotal(paymentAmountTotal);
        return this;
    }

    public void setPaymentAmountTotal(Double paymentAmountTotal) {
        this.paymentAmountTotal = paymentAmountTotal;
    }

    public Double getTaxAmountTotal() {
        return this.taxAmountTotal;
    }

    public InvoiceMasterDetails taxAmountTotal(Double taxAmountTotal) {
        this.setTaxAmountTotal(taxAmountTotal);
        return this;
    }

    public void setTaxAmountTotal(Double taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public Double getAdjAmountTotal() {
        return this.adjAmountTotal;
    }

    public InvoiceMasterDetails adjAmountTotal(Double adjAmountTotal) {
        this.setAdjAmountTotal(adjAmountTotal);
        return this;
    }

    public void setAdjAmountTotal(Double adjAmountTotal) {
        this.adjAmountTotal = adjAmountTotal;
    }

    public Double getBalanceAmountTotal() {
        return this.balanceAmountTotal;
    }

    public InvoiceMasterDetails balanceAmountTotal(Double balanceAmountTotal) {
        this.setBalanceAmountTotal(balanceAmountTotal);
        return this;
    }

    public void setBalanceAmountTotal(Double balanceAmountTotal) {
        this.balanceAmountTotal = balanceAmountTotal;
    }

    public String getStatus() {
        return this.status;
    }

    public InvoiceMasterDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public InvoiceMasterDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public InvoiceMasterDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public InvoiceMasterDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public InvoiceMasterDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public InvoiceMasterDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public InvoiceMasterDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getInvoiceMasterDetailsUuid() {
        return this.invoiceMasterDetailsUuid;
    }

    public InvoiceMasterDetails invoiceMasterDetailsUuid(UUID invoiceMasterDetailsUuid) {
        this.setInvoiceMasterDetailsUuid(invoiceMasterDetailsUuid);
        return this;
    }

    public void setInvoiceMasterDetailsUuid(UUID invoiceMasterDetailsUuid) {
        this.invoiceMasterDetailsUuid = invoiceMasterDetailsUuid;
    }

    public String getInvoiceStatus() {
        return this.invoiceStatus;
    }

    public InvoiceMasterDetails invoiceStatus(String invoiceStatus) {
        this.setInvoiceStatus(invoiceStatus);
        return this;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Long getPrimarySubmissionMasterId() {
        return this.primarySubmissionMasterId;
    }

    public InvoiceMasterDetails primarySubmissionMasterId(Long primarySubmissionMasterId) {
        this.setPrimarySubmissionMasterId(primarySubmissionMasterId);
        return this;
    }

    public void setPrimarySubmissionMasterId(Long primarySubmissionMasterId) {
        this.primarySubmissionMasterId = primarySubmissionMasterId;
    }

    public String getClaimControlNo() {
        return this.claimControlNo;
    }

    public InvoiceMasterDetails claimControlNo(String claimControlNo) {
        this.setClaimControlNo(claimControlNo);
        return this;
    }

    public void setClaimControlNo(String claimControlNo) {
        this.claimControlNo = claimControlNo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoiceMasterDetails)) {
            return false;
        }
        return invoiceId != null && invoiceId.equals(((InvoiceMasterDetails) o).invoiceId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoiceMasterDetails{" +
            "invoiceId=" + getInvoiceId() +
            ", invoiceNo='" + getInvoiceNo() + "'" +
            ", invoiceDate='" + getInvoiceDate() + "'" +
            ", invoiceToEntity='" + getInvoiceToEntity() + "'" +
            ", invoiceToPayorId=" + getInvoiceToPayorId() +
            ", invoiceToPayorName='" + getInvoiceToPayorName() + "'" +
            ", salesOrderId=" + getSalesOrderId() +
            ", salesOrderNo='" + getSalesOrderNo() + "'" +
            ", patientId=" + getPatientId() +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientMiddleName='" + getPatientMiddleName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", itemQtyTotal=" + getItemQtyTotal() +
            ", chargedAmountTotal=" + getChargedAmountTotal() +
            ", allowAmountTotal=" + getAllowAmountTotal() +
            ", paymentAmountTotal=" + getPaymentAmountTotal() +
            ", taxAmountTotal=" + getTaxAmountTotal() +
            ", adjAmountTotal=" + getAdjAmountTotal() +
            ", balanceAmountTotal=" + getBalanceAmountTotal() +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", invoiceMasterDetailsUuid='" + getInvoiceMasterDetailsUuid() + "'" +
            ", invoiceStatus='" + getInvoiceStatus() + "'" +
            ", primarySubmissionMasterId=" + getPrimarySubmissionMasterId() +
            ", claimControlNo='" + getClaimControlNo() + "'" +
            "}";
    }
}
