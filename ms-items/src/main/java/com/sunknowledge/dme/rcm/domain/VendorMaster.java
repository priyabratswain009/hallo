package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

/**
 * A VendorMaster.
 */
@Entity
@Table(name = "t_vendor_master")
public class VendorMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "vendor_description")
    private String vendorDescription;

    @Column(name = "vendor_no")
    private String vendorNo;

    @Column(name = "vendor_master_uuid")
    private UUID vendorMasterUuid;

    @Column(name = "vendor_account_no")
    private String vendorAccountNo;

    @Column(name = "default_po_type")
    private String defaultPoType;

    @Column(name = "default_shop_type")
    private String defaultShopType;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "email")
    private String email;

    @Column(name = "fax")
    private String fax;

    @Column(name = "efax")
    private String efax;

    @Column(name = "contact_person_name")
    private String contactPersonName;

    @Column(name = "contact_no_1")
    private String contactNo1;

    @Column(name = "contact_no_2")
    private String contactNo2;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getVendorId() {
        return this.vendorId;
    }

    public VendorMaster vendorId(Long vendorId) {
        this.setVendorId(vendorId);
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return this.vendorName;
    }

    public VendorMaster vendorName(String vendorName) {
        this.setVendorName(vendorName);
        return this;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getStatus() {
        return this.status;
    }

    public VendorMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public VendorMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public VendorMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public VendorMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public VendorMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public VendorMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public VendorMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getVendorDescription() {
        return this.vendorDescription;
    }

    public VendorMaster vendorDescription(String vendorDescription) {
        this.setVendorDescription(vendorDescription);
        return this;
    }

    public void setVendorDescription(String vendorDescription) {
        this.vendorDescription = vendorDescription;
    }

    public String getVendorNo() {
        return this.vendorNo;
    }

    public VendorMaster vendorNo(String vendorNo) {
        this.setVendorNo(vendorNo);
        return this;
    }

    public void setVendorNo(String vendorNo) {
        this.vendorNo = vendorNo;
    }

    public UUID getVendorMasterUuid() {
        return this.vendorMasterUuid;
    }

    public VendorMaster vendorMasterUuid(UUID vendorMasterUuid) {
        this.setVendorMasterUuid(vendorMasterUuid);
        return this;
    }

    public void setVendorMasterUuid(UUID vendorMasterUuid) {
        this.vendorMasterUuid = vendorMasterUuid;
    }

    public String getVendorAccountNo() {
        return this.vendorAccountNo;
    }

    public VendorMaster vendorAccountNo(String vendorAccountNo) {
        this.setVendorAccountNo(vendorAccountNo);
        return this;
    }

    public void setVendorAccountNo(String vendorAccountNo) {
        this.vendorAccountNo = vendorAccountNo;
    }

    public String getDefaultPoType() {
        return this.defaultPoType;
    }

    public VendorMaster defaultPoType(String defaultPoType) {
        this.setDefaultPoType(defaultPoType);
        return this;
    }

    public void setDefaultPoType(String defaultPoType) {
        this.defaultPoType = defaultPoType;
    }

    public String getDefaultShopType() {
        return this.defaultShopType;
    }

    public VendorMaster defaultShopType(String defaultShopType) {
        this.setDefaultShopType(defaultShopType);
        return this;
    }

    public void setDefaultShopType(String defaultShopType) {
        this.defaultShopType = defaultShopType;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public VendorMaster addressLine1(String addressLine1) {
        this.setAddressLine1(addressLine1);
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public VendorMaster addressLine2(String addressLine2) {
        this.setAddressLine2(addressLine2);
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return this.city;
    }

    public VendorMaster city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public VendorMaster state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return this.zip;
    }

    public VendorMaster zip(String zip) {
        this.setZip(zip);
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return this.email;
    }

    public VendorMaster email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return this.fax;
    }

    public VendorMaster fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEfax() {
        return this.efax;
    }

    public VendorMaster efax(String efax) {
        this.setEfax(efax);
        return this;
    }

    public void setEfax(String efax) {
        this.efax = efax;
    }

    public String getContactPersonName() {
        return this.contactPersonName;
    }

    public VendorMaster contactPersonName(String contactPersonName) {
        this.setContactPersonName(contactPersonName);
        return this;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactNo1() {
        return this.contactNo1;
    }

    public VendorMaster contactNo1(String contactNo1) {
        this.setContactNo1(contactNo1);
        return this;
    }

    public void setContactNo1(String contactNo1) {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2() {
        return this.contactNo2;
    }

    public VendorMaster contactNo2(String contactNo2) {
        this.setContactNo2(contactNo2);
        return this;
    }

    public void setContactNo2(String contactNo2) {
        this.contactNo2 = contactNo2;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendorMaster)) {
            return false;
        }
        return vendorId != null && vendorId.equals(((VendorMaster) o).vendorId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VendorMaster{" +
            "vendorId=" + getVendorId() +
            ", vendorName='" + getVendorName() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", vendorDescription='" + getVendorDescription() + "'" +
            ", vendorNo='" + getVendorNo() + "'" +
            ", vendorMasterUuid='" + getVendorMasterUuid() + "'" +
            ", vendorAccountNo='" + getVendorAccountNo() + "'" +
            ", defaultPoType='" + getDefaultPoType() + "'" +
            ", defaultShopType='" + getDefaultShopType() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", zip='" + getZip() + "'" +
            ", email='" + getEmail() + "'" +
            ", fax='" + getFax() + "'" +
            ", efax='" + getEfax() + "'" +
            ", contactPersonName='" + getContactPersonName() + "'" +
            ", contactNo1='" + getContactNo1() + "'" +
            ", contactNo2='" + getContactNo2() + "'" +
            "}";
    }
}
