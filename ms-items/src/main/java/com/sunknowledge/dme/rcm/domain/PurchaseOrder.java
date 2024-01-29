package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A PurchaseOrder.
 */
@Entity
@Table(name = "t_purchase_order")
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "po_id")
    private Long poId;

    @Column(name = "po_number")
    private String poNumber;

    @Column(name = "po_date")
    private LocalDate poDate;

    @Column(name = "item_location_id")
    private Long itemLocationId;

    @Column(name = "item_location_name")
    private String itemLocationName;

    @Column(name = "sales_order_id")
    private Long salesOrderId;

    @Column(name = "sales_order_no")
    private String salesOrderNo;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "billing_address_line_1")
    private String billingAddressLine1;

    @Column(name = "billing_address_line_2")
    private String billingAddressLine2;

    @Column(name = "billing_address_city")
    private String billingAddressCity;

    @Column(name = "billing_address_state")
    private String billingAddressState;

    @Column(name = "billing_address_zip")
    private String billingAddressZip;

    @Column(name = "billing_contact_no")
    private String billingContactNo;

    @Column(name = "billing_contact_name")
    private String billingContactName;

    @Column(name = "billing_contact_email")
    private String billingContactEmail;

    @Column(name = "delivery_address_line_1")
    private String deliveryAddressLine1;

    @Column(name = "delivery_address_line_2")
    private String deliveryAddressLine2;

    @Column(name = "delivery_address_city")
    private String deliveryAddressCity;

    @Column(name = "delivery_address_state")
    private String deliveryAddressState;

    @Column(name = "delivery_address_zip")
    private String deliveryAddressZip;

    @Column(name = "delivery_contact_no")
    private String deliveryContactNo;

    @Column(name = "delivery_contact_name")
    private String deliveryContactName;

    @Column(name = "delivery_contact_email")
    private String deliveryContactEmail;

    @Column(name = "priority")
    private String priority;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @Column(name = "freight_charges")
    private Double freightCharges;

    @Column(name = "status")
    private String status;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "purchase_order_uuid")
    private UUID purchaseOrderUuid;

    @Column(name = "po_status")
    private String poStatus;

    @Column(name = "vendor_delivery")
    private Boolean vendorDelivery;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "vendor_fax_no")
    private String vendorFaxNo;

    @Column(name = "vendor_email")
    private String vendorEmail;

    @Column(name = "vendor_fax_request_status")
    private String vendorFaxRequestStatus;

    @Column(name = "vendor_email_request_status")
    private String vendorEmailRequestStatus;

    @Column(name = "po_request_document_name")
    private String poRequestDocumentName;

    @Column(name = "po_request_ack_received_status")
    private String poRequestAckReceivedStatus;

    @Column(name = "po_850_edi_string")
    private String po850EdiString;

    @Column(name = "po_855_edi_string")
    private String po855EdiString;

    @Column(name = "po_request_send_datetime")
    private LocalDate poRequestSendDatetime;

    @Column(name = "po_ack_received_datetime")
    private LocalDate poAckReceivedDatetime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPoId() {
        return this.poId;
    }

    public PurchaseOrder poId(Long poId) {
        this.setPoId(poId);
        return this;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public String getPoNumber() {
        return this.poNumber;
    }

    public PurchaseOrder poNumber(String poNumber) {
        this.setPoNumber(poNumber);
        return this;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public LocalDate getPoDate() {
        return this.poDate;
    }

    public PurchaseOrder poDate(LocalDate poDate) {
        this.setPoDate(poDate);
        return this;
    }

    public void setPoDate(LocalDate poDate) {
        this.poDate = poDate;
    }

    public Long getItemLocationId() {
        return this.itemLocationId;
    }

    public PurchaseOrder itemLocationId(Long itemLocationId) {
        this.setItemLocationId(itemLocationId);
        return this;
    }

    public void setItemLocationId(Long itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    public String getItemLocationName() {
        return this.itemLocationName;
    }

    public PurchaseOrder itemLocationName(String itemLocationName) {
        this.setItemLocationName(itemLocationName);
        return this;
    }

    public void setItemLocationName(String itemLocationName) {
        this.itemLocationName = itemLocationName;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public PurchaseOrder salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesOrderNo() {
        return this.salesOrderNo;
    }

    public PurchaseOrder salesOrderNo(String salesOrderNo) {
        this.setSalesOrderNo(salesOrderNo);
        return this;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public Long getVendorId() {
        return this.vendorId;
    }

    public PurchaseOrder vendorId(Long vendorId) {
        this.setVendorId(vendorId);
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return this.vendorName;
    }

    public PurchaseOrder vendorName(String vendorName) {
        this.setVendorName(vendorName);
        return this;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getBillingAddressLine1() {
        return this.billingAddressLine1;
    }

    public PurchaseOrder billingAddressLine1(String billingAddressLine1) {
        this.setBillingAddressLine1(billingAddressLine1);
        return this;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
    }

    public String getBillingAddressLine2() {
        return this.billingAddressLine2;
    }

    public PurchaseOrder billingAddressLine2(String billingAddressLine2) {
        this.setBillingAddressLine2(billingAddressLine2);
        return this;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
    }

    public String getBillingAddressCity() {
        return this.billingAddressCity;
    }

    public PurchaseOrder billingAddressCity(String billingAddressCity) {
        this.setBillingAddressCity(billingAddressCity);
        return this;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressState() {
        return this.billingAddressState;
    }

    public PurchaseOrder billingAddressState(String billingAddressState) {
        this.setBillingAddressState(billingAddressState);
        return this;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    public String getBillingAddressZip() {
        return this.billingAddressZip;
    }

    public PurchaseOrder billingAddressZip(String billingAddressZip) {
        this.setBillingAddressZip(billingAddressZip);
        return this;
    }

    public void setBillingAddressZip(String billingAddressZip) {
        this.billingAddressZip = billingAddressZip;
    }

    public String getBillingContactNo() {
        return this.billingContactNo;
    }

    public PurchaseOrder billingContactNo(String billingContactNo) {
        this.setBillingContactNo(billingContactNo);
        return this;
    }

    public void setBillingContactNo(String billingContactNo) {
        this.billingContactNo = billingContactNo;
    }

    public String getBillingContactName() {
        return this.billingContactName;
    }

    public PurchaseOrder billingContactName(String billingContactName) {
        this.setBillingContactName(billingContactName);
        return this;
    }

    public void setBillingContactName(String billingContactName) {
        this.billingContactName = billingContactName;
    }

    public String getBillingContactEmail() {
        return this.billingContactEmail;
    }

    public PurchaseOrder billingContactEmail(String billingContactEmail) {
        this.setBillingContactEmail(billingContactEmail);
        return this;
    }

    public void setBillingContactEmail(String billingContactEmail) {
        this.billingContactEmail = billingContactEmail;
    }

    public String getDeliveryAddressLine1() {
        return this.deliveryAddressLine1;
    }

    public PurchaseOrder deliveryAddressLine1(String deliveryAddressLine1) {
        this.setDeliveryAddressLine1(deliveryAddressLine1);
        return this;
    }

    public void setDeliveryAddressLine1(String deliveryAddressLine1) {
        this.deliveryAddressLine1 = deliveryAddressLine1;
    }

    public String getDeliveryAddressLine2() {
        return this.deliveryAddressLine2;
    }

    public PurchaseOrder deliveryAddressLine2(String deliveryAddressLine2) {
        this.setDeliveryAddressLine2(deliveryAddressLine2);
        return this;
    }

    public void setDeliveryAddressLine2(String deliveryAddressLine2) {
        this.deliveryAddressLine2 = deliveryAddressLine2;
    }

    public String getDeliveryAddressCity() {
        return this.deliveryAddressCity;
    }

    public PurchaseOrder deliveryAddressCity(String deliveryAddressCity) {
        this.setDeliveryAddressCity(deliveryAddressCity);
        return this;
    }

    public void setDeliveryAddressCity(String deliveryAddressCity) {
        this.deliveryAddressCity = deliveryAddressCity;
    }

    public String getDeliveryAddressState() {
        return this.deliveryAddressState;
    }

    public PurchaseOrder deliveryAddressState(String deliveryAddressState) {
        this.setDeliveryAddressState(deliveryAddressState);
        return this;
    }

    public void setDeliveryAddressState(String deliveryAddressState) {
        this.deliveryAddressState = deliveryAddressState;
    }

    public String getDeliveryAddressZip() {
        return this.deliveryAddressZip;
    }

    public PurchaseOrder deliveryAddressZip(String deliveryAddressZip) {
        this.setDeliveryAddressZip(deliveryAddressZip);
        return this;
    }

    public void setDeliveryAddressZip(String deliveryAddressZip) {
        this.deliveryAddressZip = deliveryAddressZip;
    }

    public String getDeliveryContactNo() {
        return this.deliveryContactNo;
    }

    public PurchaseOrder deliveryContactNo(String deliveryContactNo) {
        this.setDeliveryContactNo(deliveryContactNo);
        return this;
    }

    public void setDeliveryContactNo(String deliveryContactNo) {
        this.deliveryContactNo = deliveryContactNo;
    }

    public String getDeliveryContactName() {
        return this.deliveryContactName;
    }

    public PurchaseOrder deliveryContactName(String deliveryContactName) {
        this.setDeliveryContactName(deliveryContactName);
        return this;
    }

    public void setDeliveryContactName(String deliveryContactName) {
        this.deliveryContactName = deliveryContactName;
    }

    public String getDeliveryContactEmail() {
        return this.deliveryContactEmail;
    }

    public PurchaseOrder deliveryContactEmail(String deliveryContactEmail) {
        this.setDeliveryContactEmail(deliveryContactEmail);
        return this;
    }

    public void setDeliveryContactEmail(String deliveryContactEmail) {
        this.deliveryContactEmail = deliveryContactEmail;
    }

    public String getPriority() {
        return this.priority;
    }

    public PurchaseOrder priority(String priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getShippingMethod() {
        return this.shippingMethod;
    }

    public PurchaseOrder shippingMethod(String shippingMethod) {
        this.setShippingMethod(shippingMethod);
        return this;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Double getFreightCharges() {
        return this.freightCharges;
    }

    public PurchaseOrder freightCharges(Double freightCharges) {
        this.setFreightCharges(freightCharges);
        return this;
    }

    public void setFreightCharges(Double freightCharges) {
        this.freightCharges = freightCharges;
    }

    public String getStatus() {
        return this.status;
    }

    public PurchaseOrder status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return this.notes;
    }

    public PurchaseOrder notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public PurchaseOrder createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public PurchaseOrder createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public PurchaseOrder createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public PurchaseOrder updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PurchaseOrder updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getPurchaseOrderUuid() {
        return this.purchaseOrderUuid;
    }

    public PurchaseOrder purchaseOrderUuid(UUID purchaseOrderUuid) {
        this.setPurchaseOrderUuid(purchaseOrderUuid);
        return this;
    }

    public void setPurchaseOrderUuid(UUID purchaseOrderUuid) {
        this.purchaseOrderUuid = purchaseOrderUuid;
    }

    public String getPoStatus() {
        return this.poStatus;
    }

    public PurchaseOrder poStatus(String poStatus) {
        this.setPoStatus(poStatus);
        return this;
    }

    public void setPoStatus(String poStatus) {
        this.poStatus = poStatus;
    }

    public Boolean getVendorDelivery() {
        return this.vendorDelivery;
    }

    public PurchaseOrder vendorDelivery(Boolean vendorDelivery) {
        this.setVendorDelivery(vendorDelivery);
        return this;
    }

    public void setVendorDelivery(Boolean vendorDelivery) {
        this.vendorDelivery = vendorDelivery;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PurchaseOrder updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public PurchaseOrder branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public PurchaseOrder branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getVendorFaxNo() {
        return this.vendorFaxNo;
    }

    public PurchaseOrder vendorFaxNo(String vendorFaxNo) {
        this.setVendorFaxNo(vendorFaxNo);
        return this;
    }

    public void setVendorFaxNo(String vendorFaxNo) {
        this.vendorFaxNo = vendorFaxNo;
    }

    public String getVendorEmail() {
        return this.vendorEmail;
    }

    public PurchaseOrder vendorEmail(String vendorEmail) {
        this.setVendorEmail(vendorEmail);
        return this;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorFaxRequestStatus() {
        return this.vendorFaxRequestStatus;
    }

    public PurchaseOrder vendorFaxRequestStatus(String vendorFaxRequestStatus) {
        this.setVendorFaxRequestStatus(vendorFaxRequestStatus);
        return this;
    }

    public void setVendorFaxRequestStatus(String vendorFaxRequestStatus) {
        this.vendorFaxRequestStatus = vendorFaxRequestStatus;
    }

    public String getVendorEmailRequestStatus() {
        return this.vendorEmailRequestStatus;
    }

    public PurchaseOrder vendorEmailRequestStatus(String vendorEmailRequestStatus) {
        this.setVendorEmailRequestStatus(vendorEmailRequestStatus);
        return this;
    }

    public void setVendorEmailRequestStatus(String vendorEmailRequestStatus) {
        this.vendorEmailRequestStatus = vendorEmailRequestStatus;
    }

    public String getPoRequestDocumentName() {
        return this.poRequestDocumentName;
    }

    public PurchaseOrder poRequestDocumentName(String poRequestDocumentName) {
        this.setPoRequestDocumentName(poRequestDocumentName);
        return this;
    }

    public void setPoRequestDocumentName(String poRequestDocumentName) {
        this.poRequestDocumentName = poRequestDocumentName;
    }

    public String getPoRequestAckReceivedStatus() {
        return this.poRequestAckReceivedStatus;
    }

    public PurchaseOrder poRequestAckReceivedStatus(String poRequestAckReceivedStatus) {
        this.setPoRequestAckReceivedStatus(poRequestAckReceivedStatus);
        return this;
    }

    public void setPoRequestAckReceivedStatus(String poRequestAckReceivedStatus) {
        this.poRequestAckReceivedStatus = poRequestAckReceivedStatus;
    }

    public String getPo850EdiString() {
        return this.po850EdiString;
    }

    public PurchaseOrder po850EdiString(String po850EdiString) {
        this.setPo850EdiString(po850EdiString);
        return this;
    }

    public void setPo850EdiString(String po850EdiString) {
        this.po850EdiString = po850EdiString;
    }

    public String getPo855EdiString() {
        return this.po855EdiString;
    }

    public PurchaseOrder po855EdiString(String po855EdiString) {
        this.setPo855EdiString(po855EdiString);
        return this;
    }

    public void setPo855EdiString(String po855EdiString) {
        this.po855EdiString = po855EdiString;
    }

    public LocalDate getPoRequestSendDatetime() {
        return this.poRequestSendDatetime;
    }

    public PurchaseOrder poRequestSendDatetime(LocalDate poRequestSendDatetime) {
        this.setPoRequestSendDatetime(poRequestSendDatetime);
        return this;
    }

    public void setPoRequestSendDatetime(LocalDate poRequestSendDatetime) {
        this.poRequestSendDatetime = poRequestSendDatetime;
    }

    public LocalDate getPoAckReceivedDatetime() {
        return this.poAckReceivedDatetime;
    }

    public PurchaseOrder poAckReceivedDatetime(LocalDate poAckReceivedDatetime) {
        this.setPoAckReceivedDatetime(poAckReceivedDatetime);
        return this;
    }

    public void setPoAckReceivedDatetime(LocalDate poAckReceivedDatetime) {
        this.poAckReceivedDatetime = poAckReceivedDatetime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseOrder)) {
            return false;
        }
        return poId != null && poId.equals(((PurchaseOrder) o).poId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseOrder{" +
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
            ", vendorFaxNo='" + getVendorFaxNo() + "'" +
            ", vendorEmail='" + getVendorEmail() + "'" +
            ", vendorFaxRequestStatus='" + getVendorFaxRequestStatus() + "'" +
            ", vendorEmailRequestStatus='" + getVendorEmailRequestStatus() + "'" +
            ", poRequestDocumentName='" + getPoRequestDocumentName() + "'" +
            ", poRequestAckReceivedStatus='" + getPoRequestAckReceivedStatus() + "'" +
            ", po850EdiString='" + getPo850EdiString() + "'" +
            ", po855EdiString='" + getPo855EdiString() + "'" +
            ", poRequestSendDatetime='" + getPoRequestSendDatetime() + "'" +
            ", poAckReceivedDatetime='" + getPoAckReceivedDatetime() + "'" +
            "}";
    }
}
