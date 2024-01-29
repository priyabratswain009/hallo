package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.VendorMaster} entity.
 */
public class VendorMasterDTO implements Serializable {

    private Long vendorId;

    private String vendorName;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private String vendorDescription;

    private String vendorNo;

    private UUID vendorMasterUuid;

    private String vendorAccountNo;

    private String defaultPoType;

    private String defaultShopType;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String zip;

    private String email;

    private String fax;

    private String efax;

    private String contactPersonName;

    private String contactNo1;

    private String contactNo2;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getVendorDescription() {
        return vendorDescription;
    }

    public void setVendorDescription(String vendorDescription) {
        this.vendorDescription = vendorDescription;
    }

    public String getVendorNo() {
        return vendorNo;
    }

    public void setVendorNo(String vendorNo) {
        this.vendorNo = vendorNo;
    }

    public UUID getVendorMasterUuid() {
        return vendorMasterUuid;
    }

    public void setVendorMasterUuid(UUID vendorMasterUuid) {
        this.vendorMasterUuid = vendorMasterUuid;
    }

    public String getVendorAccountNo() {
        return vendorAccountNo;
    }

    public void setVendorAccountNo(String vendorAccountNo) {
        this.vendorAccountNo = vendorAccountNo;
    }

    public String getDefaultPoType() {
        return defaultPoType;
    }

    public void setDefaultPoType(String defaultPoType) {
        this.defaultPoType = defaultPoType;
    }

    public String getDefaultShopType() {
        return defaultShopType;
    }

    public void setDefaultShopType(String defaultShopType) {
        this.defaultShopType = defaultShopType;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEfax() {
        return efax;
    }

    public void setEfax(String efax) {
        this.efax = efax;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactNo1() {
        return contactNo1;
    }

    public void setContactNo1(String contactNo1) {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2() {
        return contactNo2;
    }

    public void setContactNo2(String contactNo2) {
        this.contactNo2 = contactNo2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendorMasterDTO)) {
            return false;
        }

        VendorMasterDTO vendorMasterDTO = (VendorMasterDTO) o;
        if (this.vendorId == null) {
            return false;
        }
        return Objects.equals(this.vendorId, vendorMasterDTO.vendorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.vendorId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VendorMasterDTO{" +
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
