package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A InvoicePostingDetails.
 */
@Table("t_invoice_posting_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InvoicePostingDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("invoice_line_item_posting_id")
    private Long invoiceLineItemPostingId;

    @Column("item_id")
    private Long itemId;

    @Column("posting_date")
    private LocalDate postingDate;

    @Column("posted_by_id")
    private Long postedById;

    @Column("posted_by_name")
    private String postedByName;

    @Column("posting_comment")
    private String postingComment;

    @Column("post_type")
    private String postType;

    @Column("deposit_id")
    private String depositId;

    @Column("receipt_id")
    private String receiptId;

    @Column("post_amount")
    private Double postAmount;

    @Column("post_status")
    private String postStatus;

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

    @Column("invoice_posting_details_uuid")
    private UUID invoicePostingDetailsUuid;

    @Column("invoice_no")
    private String invoiceNo;

    @Column("invoice_date")
    private LocalDate invoiceDate;

    @Column("invoice_line_item_details_id")
    private Long invoiceLineItemDetailsId;

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

    public String getDepositId() {
        return this.depositId;
    }

    public InvoicePostingDetails depositId(String depositId) {
        this.setDepositId(depositId);
        return this;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public String getReceiptId() {
        return this.receiptId;
    }

    public InvoicePostingDetails receiptId(String receiptId) {
        this.setReceiptId(receiptId);
        return this;
    }

    public void setReceiptId(String receiptId) {
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
            ", depositId='" + getDepositId() + "'" +
            ", receiptId='" + getReceiptId() + "'" +
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
            "}";
    }
}
