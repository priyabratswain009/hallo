package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.BranchOffice} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BranchOfficeDTO implements Serializable {

    private Long branchId;

    private String branchName;

    private Long branchGroupId;

    private String npi;

    private String ptan;

    private String taxonomyCode;

    private String taxonomyCodeDescription;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String taxIdType;

    private String taxIdNo;

    private String branchNo;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID branchOfficeUuid;

    private String billingAddressLine1;

    private String billingAddressLine2;

    private String billingCity;

    private String billingState;

    private String billingZipCode;

    private String submitterContactPersonName;

    private String submitterContactPhoneNo1;

    private String submitterContactPhoneNo2;

    private String billingFax;

    private String contactEmail;

    private String zip;

    private String billingContactNo1;

    private String billingContactNo2;

    private String billingEmail;

    private String branchCompany;

    private String branchGroupName;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String signatureName;

    private String itemLocationId;

    private String itemLocationName;

    private Long branchCompanyId;

    private String isDropshipAllowed;

    private String billingTaxonomyCode;

    private String billingNpi;

    private String billingOrganisationName;

    private String billingTaxIdNo;

    private String billingBranchName;

    private String fax;

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

    public Long getBranchGroupId() {
        return branchGroupId;
    }

    public void setBranchGroupId(Long branchGroupId) {
        this.branchGroupId = branchGroupId;
    }

    public String getNpi() {
        return npi;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    public String getPtan() {
        return ptan;
    }

    public void setPtan(String ptan) {
        this.ptan = ptan;
    }

    public String getTaxonomyCode() {
        return taxonomyCode;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
    }

    public String getTaxonomyCodeDescription() {
        return taxonomyCodeDescription;
    }

    public void setTaxonomyCodeDescription(String taxonomyCodeDescription) {
        this.taxonomyCodeDescription = taxonomyCodeDescription;
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

    public String getTaxIdType() {
        return taxIdType;
    }

    public void setTaxIdType(String taxIdType) {
        this.taxIdType = taxIdType;
    }

    public String getTaxIdNo() {
        return taxIdNo;
    }

    public void setTaxIdNo(String taxIdNo) {
        this.taxIdNo = taxIdNo;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
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

    public UUID getBranchOfficeUuid() {
        return branchOfficeUuid;
    }

    public void setBranchOfficeUuid(UUID branchOfficeUuid) {
        this.branchOfficeUuid = branchOfficeUuid;
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

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingZipCode() {
        return billingZipCode;
    }

    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
    }

    public String getSubmitterContactPersonName() {
        return submitterContactPersonName;
    }

    public void setSubmitterContactPersonName(String submitterContactPersonName) {
        this.submitterContactPersonName = submitterContactPersonName;
    }

    public String getSubmitterContactPhoneNo1() {
        return submitterContactPhoneNo1;
    }

    public void setSubmitterContactPhoneNo1(String submitterContactPhoneNo1) {
        this.submitterContactPhoneNo1 = submitterContactPhoneNo1;
    }

    public String getSubmitterContactPhoneNo2() {
        return submitterContactPhoneNo2;
    }

    public void setSubmitterContactPhoneNo2(String submitterContactPhoneNo2) {
        this.submitterContactPhoneNo2 = submitterContactPhoneNo2;
    }

    public String getBillingFax() {
        return billingFax;
    }

    public void setBillingFax(String billingFax) {
        this.billingFax = billingFax;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getBillingContactNo1() {
        return billingContactNo1;
    }

    public void setBillingContactNo1(String billingContactNo1) {
        this.billingContactNo1 = billingContactNo1;
    }

    public String getBillingContactNo2() {
        return billingContactNo2;
    }

    public void setBillingContactNo2(String billingContactNo2) {
        this.billingContactNo2 = billingContactNo2;
    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public String getBranchCompany() {
        return branchCompany;
    }

    public void setBranchCompany(String branchCompany) {
        this.branchCompany = branchCompany;
    }

    public String getBranchGroupName() {
        return branchGroupName;
    }

    public void setBranchGroupName(String branchGroupName) {
        this.branchGroupName = branchGroupName;
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

    public String getSignatureName() {
        return signatureName;
    }

    public void setSignatureName(String signatureName) {
        this.signatureName = signatureName;
    }

    public String getItemLocationId() {
        return itemLocationId;
    }

    public void setItemLocationId(String itemLocationId) {
        this.itemLocationId = itemLocationId;
    }

    public String getItemLocationName() {
        return itemLocationName;
    }

    public void setItemLocationName(String itemLocationName) {
        this.itemLocationName = itemLocationName;
    }

    public Long getBranchCompanyId() {
        return branchCompanyId;
    }

    public void setBranchCompanyId(Long branchCompanyId) {
        this.branchCompanyId = branchCompanyId;
    }

    public String getIsDropshipAllowed() {
        return isDropshipAllowed;
    }

    public void setIsDropshipAllowed(String isDropshipAllowed) {
        this.isDropshipAllowed = isDropshipAllowed;
    }

    public String getBillingTaxonomyCode() {
        return billingTaxonomyCode;
    }

    public void setBillingTaxonomyCode(String billingTaxonomyCode) {
        this.billingTaxonomyCode = billingTaxonomyCode;
    }

    public String getBillingNpi() {
        return billingNpi;
    }

    public void setBillingNpi(String billingNpi) {
        this.billingNpi = billingNpi;
    }

    public String getBillingOrganisationName() {
        return billingOrganisationName;
    }

    public void setBillingOrganisationName(String billingOrganisationName) {
        this.billingOrganisationName = billingOrganisationName;
    }

    public String getBillingTaxIdNo() {
        return billingTaxIdNo;
    }

    public void setBillingTaxIdNo(String billingTaxIdNo) {
        this.billingTaxIdNo = billingTaxIdNo;
    }

    public String getBillingBranchName() {
        return billingBranchName;
    }

    public void setBillingBranchName(String billingBranchName) {
        this.billingBranchName = billingBranchName;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchOfficeDTO)) {
            return false;
        }

        BranchOfficeDTO branchOfficeDTO = (BranchOfficeDTO) o;
        if (this.branchId == null) {
            return false;
        }
        return Objects.equals(this.branchId, branchOfficeDTO.branchId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.branchId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchOfficeDTO{" +
            "branchId=" + getBranchId() +
            ", branchName='" + getBranchName() + "'" +
            ", branchGroupId=" + getBranchGroupId() +
            ", npi='" + getNpi() + "'" +
            ", ptan='" + getPtan() + "'" +
            ", taxonomyCode='" + getTaxonomyCode() + "'" +
            ", taxonomyCodeDescription='" + getTaxonomyCodeDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", taxIdType='" + getTaxIdType() + "'" +
            ", taxIdNo='" + getTaxIdNo() + "'" +
            ", branchNo='" + getBranchNo() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", branchOfficeUuid='" + getBranchOfficeUuid() + "'" +
            ", billingAddressLine1='" + getBillingAddressLine1() + "'" +
            ", billingAddressLine2='" + getBillingAddressLine2() + "'" +
            ", billingCity='" + getBillingCity() + "'" +
            ", billingState='" + getBillingState() + "'" +
            ", billingZipCode='" + getBillingZipCode() + "'" +
            ", submitterContactPersonName='" + getSubmitterContactPersonName() + "'" +
            ", submitterContactPhoneNo1='" + getSubmitterContactPhoneNo1() + "'" +
            ", submitterContactPhoneNo2='" + getSubmitterContactPhoneNo2() + "'" +
            ", billingFax='" + getBillingFax() + "'" +
            ", contactEmail='" + getContactEmail() + "'" +
            ", zip='" + getZip() + "'" +
            ", billingContactNo1='" + getBillingContactNo1() + "'" +
            ", billingContactNo2='" + getBillingContactNo2() + "'" +
            ", billingEmail='" + getBillingEmail() + "'" +
            ", branchCompany='" + getBranchCompany() + "'" +
            ", branchGroupName='" + getBranchGroupName() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", signatureName='" + getSignatureName() + "'" +
            ", itemLocationId='" + getItemLocationId() + "'" +
            ", itemLocationName='" + getItemLocationName() + "'" +
            ", branchCompanyId=" + getBranchCompanyId() +
            ", isDropshipAllowed='" + getIsDropshipAllowed() + "'" +
            ", billingTaxonomyCode='" + getBillingTaxonomyCode() + "'" +
            ", billingNpi='" + getBillingNpi() + "'" +
            ", billingOrganisationName='" + getBillingOrganisationName() + "'" +
            ", billingTaxIdNo='" + getBillingTaxIdNo() + "'" +
            ", billingBranchName='" + getBillingBranchName() + "'" +
            ", fax='" + getFax() + "'" +
            "}";
    }
}
