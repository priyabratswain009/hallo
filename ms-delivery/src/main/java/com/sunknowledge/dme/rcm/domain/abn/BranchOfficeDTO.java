package com.sunknowledge.dme.rcm.domain.abn;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class BranchOfficeDTO {

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

    private String payToProviderZip;

    private String contactNo1;

    private String contactNo2;

    private String billingEmail;

    private String branchCompany;

    private String branchGroupName;

    private String payToProviderAddressLine1;

    private String payToProviderAddressLine2;

    private String payToProviderCity;

    private String payToProviderState;

    private String signatureName;

    private String itemLocationId;

    private String itemLocationName;

    private LocalDate updatedDate;

    private Long branchCompanyId;

    private String isDropshipAllowed;

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

    public String getPayToProviderZip() {
        return payToProviderZip;
    }

    public void setPayToProviderZip(String payToProviderZip) {
        this.payToProviderZip = payToProviderZip;
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

    public String getPayToProviderAddressLine1() {
        return payToProviderAddressLine1;
    }

    public void setPayToProviderAddressLine1(String payToProviderAddressLine1) {
        this.payToProviderAddressLine1 = payToProviderAddressLine1;
    }

    public String getPayToProviderAddressLine2() {
        return payToProviderAddressLine2;
    }

    public void setPayToProviderAddressLine2(String payToProviderAddressLine2) {
        this.payToProviderAddressLine2 = payToProviderAddressLine2;
    }

    public String getPayToProviderCity() {
        return payToProviderCity;
    }

    public void setPayToProviderCity(String payToProviderCity) {
        this.payToProviderCity = payToProviderCity;
    }

    public String getPayToProviderState() {
        return payToProviderState;
    }

    public void setPayToProviderState(String payToProviderState) {
        this.payToProviderState = payToProviderState;
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
            ", payToProviderZip='" + getPayToProviderZip() + "'" +
            ", contactNo1='" + getContactNo1() + "'" +
            ", contactNo2='" + getContactNo2() + "'" +
            ", billingEmail='" + getBillingEmail() + "'" +
            ", branchCompany='" + getBranchCompany() + "'" +
            ", branchGroupName='" + getBranchGroupName() + "'" +
            ", payToProviderAddressLine1='" + getPayToProviderAddressLine1() + "'" +
            ", payToProviderAddressLine2='" + getPayToProviderAddressLine2() + "'" +
            ", payToProviderCity='" + getPayToProviderCity() + "'" +
            ", payToProviderState='" + getPayToProviderState() + "'" +
            ", signatureName='" + getSignatureName() + "'" +
            ", itemLocationId='" + getItemLocationId() + "'" +
            ", itemLocationName='" + getItemLocationName() + "'" +
            ", branchCompanyId=" + getBranchCompanyId() +
            ", isDropshipAllowed='" + getIsDropshipAllowed() + "'" +
            "}";
    }
}
