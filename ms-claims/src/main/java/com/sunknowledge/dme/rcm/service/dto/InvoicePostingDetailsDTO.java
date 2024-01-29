package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.InvoicePostingDetails} entity.
 */
public class InvoicePostingDetailsDTO implements Serializable {

    @NotNull
    private Long invoiceLineItemPostingId;

    private Long itemId;

    private LocalDate postingDate;

    private Long postedById;

    private String postedByName;

    private String postingComment;

    private String postType;

    private Long depositId;

    private Long receiptId;

    private Double postAmount;

    private String postStatus;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID invoicePostingDetailsUuid;

    private String invoiceNo;

    private LocalDate invoiceDate;

    private Long invoiceLineItemDetailsId;

    private String hcpcsCode;

    private String postingNo;

    private Boolean isManualPosting;

    public Long getInvoiceLineItemPostingId() {
        return invoiceLineItemPostingId;
    }

    public void setInvoiceLineItemPostingId(Long invoiceLineItemPostingId) {
        this.invoiceLineItemPostingId = invoiceLineItemPostingId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public LocalDate getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(LocalDate postingDate) {
        this.postingDate = postingDate;
    }

    public Long getPostedById() {
        return postedById;
    }

    public void setPostedById(Long postedById) {
        this.postedById = postedById;
    }

    public String getPostedByName() {
        return postedByName;
    }

    public void setPostedByName(String postedByName) {
        this.postedByName = postedByName;
    }

    public String getPostingComment() {
        return postingComment;
    }

    public void setPostingComment(String postingComment) {
        this.postingComment = postingComment;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public Double getPostAmount() {
        return postAmount;
    }

    public void setPostAmount(Double postAmount) {
        this.postAmount = postAmount;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getInvoicePostingDetailsUuid() {
        return invoicePostingDetailsUuid;
    }

    public void setInvoicePostingDetailsUuid(UUID invoicePostingDetailsUuid) {
        this.invoicePostingDetailsUuid = invoicePostingDetailsUuid;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getInvoiceLineItemDetailsId() {
        return invoiceLineItemDetailsId;
    }

    public void setInvoiceLineItemDetailsId(Long invoiceLineItemDetailsId) {
        this.invoiceLineItemDetailsId = invoiceLineItemDetailsId;
    }

    public String getHcpcsCode() {
        return hcpcsCode;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public String getPostingNo() {
        return postingNo;
    }

    public void setPostingNo(String postingNo) {
        this.postingNo = postingNo;
    }

    public Boolean getIsManualPosting() {
        return isManualPosting;
    }

    public void setIsManualPosting(Boolean isManualPosting) {
        this.isManualPosting = isManualPosting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoicePostingDetailsDTO)) {
            return false;
        }

        InvoicePostingDetailsDTO invoicePostingDetailsDTO = (InvoicePostingDetailsDTO) o;
        if (this.invoiceLineItemPostingId == null) {
            return false;
        }
        return Objects.equals(this.invoiceLineItemPostingId, invoicePostingDetailsDTO.invoiceLineItemPostingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.invoiceLineItemPostingId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoicePostingDetailsDTO{" +
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
