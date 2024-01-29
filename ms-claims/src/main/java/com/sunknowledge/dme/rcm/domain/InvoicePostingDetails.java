package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A InvoicePostingDetails.
 */
@Entity
@Table(name = "t_invoice_posting_details")
public class InvoicePostingDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "invoice_line_item_posting_id", nullable = false)
    private Long invoiceLineItemPostingId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "posting_date")
    private LocalDate postingDate;

    @Column(name = "posted_by_id")
    private Long postedById;

    @Column(name = "posted_by_name")
    private String postedByName;

    @Column(name = "posting_comment")
    private String postingComment;

    @Column(name = "post_type")
    private String postType;

    @Column(name = "deposit_id")
    private Long depositId;

    @Column(name = "receipt_id")
    private Long receiptId;

    @Column(name = "post_amount")
    private Double postAmount;

    @Column(name = "post_status")
    private String postStatus;

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

    @Column(name = "invoice_posting_details_uuid")
    private UUID invoicePostingDetailsUuid;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @Column(name = "invoice_line_item_details_id")
    private Long invoiceLineItemDetailsId;

    @Column(name = "hcpcs_code")
    private String hcpcsCode;

    @Column(name = "posting_no")
    private String postingNo;

    @Column(name = "is_manual_posting")
    private Boolean isManualPosting;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getInvoiceLineItemPostingId() {
        return this.invoiceLineItemPostingId;
    }

    public InvoicePostingDetails invoiceLineItemPostingId(Long invoiceLineItemPostingId) {
        this.setInvoiceLineItemPostingId(invoiceLineItemPostingId);
        return this;
    }

    public void setInvoiceLineItemPostingId(Long invoiceLineItemPostingId) {
        this.invoiceLineItemPostingId = invoiceLineItemPostingId;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public InvoicePostingDetails itemId(Long itemId) {
        this.setItemId(itemId);
        return this;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public LocalDate getPostingDate() {
        return this.postingDate;
    }

    public InvoicePostingDetails postingDate(LocalDate postingDate) {
        this.setPostingDate(postingDate);
        return this;
    }

    public void setPostingDate(LocalDate postingDate) {
        this.postingDate = postingDate;
    }

    public Long getPostedById() {
        return this.postedById;
    }

    public InvoicePostingDetails postedById(Long postedById) {
        this.setPostedById(postedById);
        return this;
    }

    public void setPostedById(Long postedById) {
        this.postedById = postedById;
    }

    public String getPostedByName() {
        return this.postedByName;
    }

    public InvoicePostingDetails postedByName(String postedByName) {
        this.setPostedByName(postedByName);
        return this;
    }

    public void setPostedByName(String postedByName) {
        this.postedByName = postedByName;
    }

    public String getPostingComment() {
        return this.postingComment;
    }

    public InvoicePostingDetails postingComment(String postingComment) {
        this.setPostingComment(postingComment);
        return this;
    }

    public void setPostingComment(String postingComment) {
        this.postingComment = postingComment;
    }

    public String getPostType() {
        return this.postType;
    }

    public InvoicePostingDetails postType(String postType) {
        this.setPostType(postType);
        return this;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Long getDepositId() {
        return this.depositId;
    }

    public InvoicePostingDetails depositId(Long depositId) {
        this.setDepositId(depositId);
        return this;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public Long getReceiptId() {
        return this.receiptId;
    }

    public InvoicePostingDetails receiptId(Long receiptId) {
        this.setReceiptId(receiptId);
        return this;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public Double getPostAmount() {
        return this.postAmount;
    }

    public InvoicePostingDetails postAmount(Double postAmount) {
        this.setPostAmount(postAmount);
        return this;
    }

    public void setPostAmount(Double postAmount) {
        this.postAmount = postAmount;
    }

    public String getPostStatus() {
        return this.postStatus;
    }

    public InvoicePostingDetails postStatus(String postStatus) {
        this.setPostStatus(postStatus);
        return this;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public String getStatus() {
        return this.status;
    }

    public InvoicePostingDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public InvoicePostingDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public InvoicePostingDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public InvoicePostingDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public InvoicePostingDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public InvoicePostingDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public InvoicePostingDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getInvoicePostingDetailsUuid() {
        return this.invoicePostingDetailsUuid;
    }

    public InvoicePostingDetails invoicePostingDetailsUuid(UUID invoicePostingDetailsUuid) {
        this.setInvoicePostingDetailsUuid(invoicePostingDetailsUuid);
        return this;
    }

    public void setInvoicePostingDetailsUuid(UUID invoicePostingDetailsUuid) {
        this.invoicePostingDetailsUuid = invoicePostingDetailsUuid;
    }

    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    public InvoicePostingDetails invoiceNo(String invoiceNo) {
        this.setInvoiceNo(invoiceNo);
        return this;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public LocalDate getInvoiceDate() {
        return this.invoiceDate;
    }

    public InvoicePostingDetails invoiceDate(LocalDate invoiceDate) {
        this.setInvoiceDate(invoiceDate);
        return this;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getInvoiceLineItemDetailsId() {
        return this.invoiceLineItemDetailsId;
    }

    public InvoicePostingDetails invoiceLineItemDetailsId(Long invoiceLineItemDetailsId) {
        this.setInvoiceLineItemDetailsId(invoiceLineItemDetailsId);
        return this;
    }

    public void setInvoiceLineItemDetailsId(Long invoiceLineItemDetailsId) {
        this.invoiceLineItemDetailsId = invoiceLineItemDetailsId;
    }

    public String getHcpcsCode() {
        return this.hcpcsCode;
    }

    public InvoicePostingDetails hcpcsCode(String hcpcsCode) {
        this.setHcpcsCode(hcpcsCode);
        return this;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public String getPostingNo() {
        return this.postingNo;
    }

    public InvoicePostingDetails postingNo(String postingNo) {
        this.setPostingNo(postingNo);
        return this;
    }

    public void setPostingNo(String postingNo) {
        this.postingNo = postingNo;
    }

    public Boolean getIsManualPosting() {
        return this.isManualPosting;
    }

    public InvoicePostingDetails isManualPosting(Boolean isManualPosting) {
        this.setIsManualPosting(isManualPosting);
        return this;
    }

    public void setIsManualPosting(Boolean isManualPosting) {
        this.isManualPosting = isManualPosting;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoicePostingDetails)) {
            return false;
        }
        return invoiceLineItemPostingId != null && invoiceLineItemPostingId.equals(((InvoicePostingDetails) o).invoiceLineItemPostingId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoicePostingDetails{" +
            "invoiceLineItemPostingId=" + getInvoiceLineItemPostingId() +
            ", itemId=" + getItemId() +
            ", postingDate='" + getPostingDate() + "'" +
            ", postedById=" + getPostedById() +
            ", postedByName='" + getPostedByName() + "'" +
            ", postingComment='" + getPostingComment() + "'" +
            ", postType='" + getPostType() + "'" +
            ", depositId=" + getDepositId() +
            ", receiptId=" + getReceiptId() +
            ", postAmount=" + getPostAmount() +
            ", postStatus='" + getPostStatus() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", invoicePostingDetailsUuid='" + getInvoicePostingDetailsUuid() + "'" +
            ", invoiceNo='" + getInvoiceNo() + "'" +
            ", invoiceDate='" + getInvoiceDate() + "'" +
            ", invoiceLineItemDetailsId=" + getInvoiceLineItemDetailsId() +
            ", hcpcsCode='" + getHcpcsCode() + "'" +
            ", postingNo='" + getPostingNo() + "'" +
            ", isManualPosting='" + getIsManualPosting() + "'" +
            "}";
    }
}
