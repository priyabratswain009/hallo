package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PurchaseOrder} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PurchaseOrderDTO implements Serializable {

    private Long poId;

    private String poNumber;

    private LocalDate poDate;

    private Long itemLocationId;

    private String itemLocationName;

    private Long salesOrderId;

    private String salesOrderNo;

    private Long vendorId;

    private String vendorName;

    private String billingAddressLine1;

    private String billingAddressLine2;

    private String billingAddressCity;

    private String billingAddressState;

    private String billingAddressZip;

    private String billingContactNo;

    private String billingContactName;

    private String billingContactEmail;

    private String deliveryAddressLine1;

    private String deliveryAddressLine2;

    private String deliveryAddressCity;

    private String deliveryAddressState;

    private String deliveryAddressZip;

    private String deliveryContactNo;

    private String deliveryContactName;

    private String deliveryContactEmail;

    private String priority;

    private String shippingMethod;

    private Double freightCharges;

    private String status;

    private String notes;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private LocalDate updatedDate;

    private UUID purchaseOrderUuid;

    private String poStatus;

    private Boolean vendorDelivery;

    private String updatedByName;

    private Long branchId;

    private String branchName;

    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public LocalDate getPoDate() {
        return poDate;
    }

    public void setPoDate(LocalDate poDate) {
        this.poDate = poDate;
    }

    public Long getItemLocationId() {
        return itemLocationId;
    }

    public void setItemLocationId(Long itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    public String getItemLocationName() {
        return itemLocationName;
    }

    public void setItemLocationName(String itemLocationName) {
        this.itemLocationName = itemLocationName;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getBillingAddressLine1() {
        return billingAddressLine1;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
    }

    public String getBillingAddressLine2() {
        return billingAddressLine2;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
    }

    public String getBillingAddressCity() {
        return billingAddressCity;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressState() {
        return billingAddressState;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    public String getBillingAddressZip() {
        return billingAddressZip;
    }

    public void setBillingAddressZip(String billingAddressZip) {
        this.billingAddressZip = billingAddressZip;
    }

    public String getBillingContactNo() {
        return billingContactNo;
    }

    public void setBillingContactNo(String billingContactNo) {
        this.billingContactNo = billingContactNo;
    }

    public String getBillingContactName() {
        return billingContactName;
    }

    public void setBillingContactName(String billingContactName) {
        this.billingContactName = billingContactName;
    }

    public String getBillingContactEmail() {
        return billingContactEmail;
    }

    public void setBillingContactEmail(String billingContactEmail) {
        this.billingContactEmail = billingContactEmail;
    }

    public String getDeliveryAddressLine1() {
        return deliveryAddressLine1;
    }

    public void setDeliveryAddressLine1(String deliveryAddressLine1) {
        this.deliveryAddressLine1 = deliveryAddressLine1;
    }

    public String getDeliveryAddressLine2() {
        return deliveryAddressLine2;
    }

    public void setDeliveryAddressLine2(String deliveryAddressLine2) {
        this.deliveryAddressLine2 = deliveryAddressLine2;
    }

    public String getDeliveryAddressCity() {
        return deliveryAddressCity;
    }

    public void setDeliveryAddressCity(String deliveryAddressCity) {
        this.deliveryAddressCity = deliveryAddressCity;
    }

    public String getDeliveryAddressState() {
        return deliveryAddressState;
    }

    public void setDeliveryAddressState(String deliveryAddressState) {
        this.deliveryAddressState = deliveryAddressState;
    }

    public String getDeliveryAddressZip() {
        return deliveryAddressZip;
    }

    public void setDeliveryAddressZip(String deliveryAddressZip) {
        this.deliveryAddressZip = deliveryAddressZip;
    }

    public String getDeliveryContactNo() {
        return deliveryContactNo;
    }

    public void setDeliveryContactNo(String deliveryContactNo) {
        this.deliveryContactNo = deliveryContactNo;
    }

    public String getDeliveryContactName() {
        return deliveryContactName;
    }

    public void setDeliveryContactName(String deliveryContactName) {
        this.deliveryContactName = deliveryContactName;
    }

    public String getDeliveryContactEmail() {
        return deliveryContactEmail;
    }

    public void setDeliveryContactEmail(String deliveryContactEmail) {
        this.deliveryContactEmail = deliveryContactEmail;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Double getFreightCharges() {
        return freightCharges;
    }

    public void setFreightCharges(Double freightCharges) {
        this.freightCharges = freightCharges;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getPurchaseOrderUuid() {
        return purchaseOrderUuid;
    }

    public void setPurchaseOrderUuid(UUID purchaseOrderUuid) {
        this.purchaseOrderUuid = purchaseOrderUuid;
    }

    public String getPoStatus() {
        return poStatus;
    }

    public void setPoStatus(String poStatus) {
        this.poStatus = poStatus;
    }

    public Boolean getVendorDelivery() {
        return vendorDelivery;
    }

    public void setVendorDelivery(Boolean vendorDelivery) {
        this.vendorDelivery = vendorDelivery;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseOrderDTO)) {
            return false;
        }

        PurchaseOrderDTO purchaseOrderDTO = (PurchaseOrderDTO) o;
        if (this.poId == null) {
            return false;
        }
        return Objects.equals(this.poId, purchaseOrderDTO.poId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.poId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseOrderDTO{" +
            "poId=" + getPoId() +
            ", poNumber='" + getPoNumber() + "'" +
            ", poDate='" + getPoDate() + "'" +
            ", itemLocationId=" + getItemLocationId() +
            ", itemLocationName='" + getItemLocationName() + "'" +
            ", salesOrderId=" + getSalesOrderId() +
            ", salesOrderNo='" + getSalesOrderNo() + "'" +
            ", vendorId=" + getVendorId() +
            ", vendorName='" + getVendorName() + "'" +
            ", billingAddressLine1='" + getBillingAddressLine1() + "'" +
            ", billingAddressLine2='" + getBillingAddressLine2() + "'" +
            ", billingAddressCity='" + getBillingAddressCity() + "'" +
            ", billingAddressState='" + getBillingAddressState() + "'" +
            ", billingAddressZip='" + getBillingAddressZip() + "'" +
            ", billingContactNo='" + getBillingContactNo() + "'" +
            ", billingContactName='" + getBillingContactName() + "'" +
            ", billingContactEmail='" + getBillingContactEmail() + "'" +
            ", deliveryAddressLine1='" + getDeliveryAddressLine1() + "'" +
            ", deliveryAddressLine2='" + getDeliveryAddressLine2() + "'" +
            ", deliveryAddressCity='" + getDeliveryAddressCity() + "'" +
            ", deliveryAddressState='" + getDeliveryAddressState() + "'" +
            ", deliveryAddressZip='" + getDeliveryAddressZip() + "'" +
            ", deliveryContactNo='" + getDeliveryContactNo() + "'" +
            ", deliveryContactName='" + getDeliveryContactName() + "'" +
            ", deliveryContactEmail='" + getDeliveryContactEmail() + "'" +
            ", priority='" + getPriority() + "'" +
            ", shippingMethod='" + getShippingMethod() + "'" +
            ", freightCharges=" + getFreightCharges() +
            ", status='" + getStatus() + "'" +
            ", notes='" + getNotes() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", purchaseOrderUuid='" + getPurchaseOrderUuid() + "'" +
            ", poStatus='" + getPoStatus() + "'" +
            ", vendorDelivery='" + getVendorDelivery() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", branchId=" + getBranchId() +
            ", branchName='" + getBranchName() + "'" +
            "}";
    }
}
