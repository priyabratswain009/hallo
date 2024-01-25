package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.Company} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CompanyDTO implements Serializable {

    private Long companyId;

    private String companyName;

    private String primaryContactName;

    private String primaryContactCredential;

    private String contractFileUploadPath;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID companyUuid;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String zip;

    private String contactNo1;

    private String contactNo2;

    private String fax;

    private String efax;

    private String email;

    private String relationship;

    private String modeOfContact;

    private String companyBranchLogo;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrimaryContactName() {
        return primaryContactName;
    }

    public void setPrimaryContactName(String primaryContactName) {
        this.primaryContactName = primaryContactName;
    }

    public String getPrimaryContactCredential() {
        return primaryContactCredential;
    }

    public void setPrimaryContactCredential(String primaryContactCredential) {
        this.primaryContactCredential = primaryContactCredential;
    }

    public String getContractFileUploadPath() {
        return contractFileUploadPath;
    }

    public void setContractFileUploadPath(String contractFileUploadPath) {
        this.contractFileUploadPath = contractFileUploadPath;
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

    public UUID getCompanyUuid() {
        return companyUuid;
    }

    public void setCompanyUuid(UUID companyUuid) {
        this.companyUuid = companyUuid;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getModeOfContact() {
        return modeOfContact;
    }

    public void setModeOfContact(String modeOfContact) {
        this.modeOfContact = modeOfContact;
    }

    public String getCompanyBranchLogo() {
        return companyBranchLogo;
    }

    public void setCompanyBranchLogo(String companyBranchLogo) {
        this.companyBranchLogo = companyBranchLogo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompanyDTO)) {
            return false;
        }

        CompanyDTO companyDTO = (CompanyDTO) o;
        if (this.companyId == null) {
            return false;
        }
        return Objects.equals(this.companyId, companyDTO.companyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.companyId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CompanyDTO{" +
            "companyId=" + getCompanyId() +
            ", companyName='" + getCompanyName() + "'" +
            ", primaryContactName='" + getPrimaryContactName() + "'" +
            ", primaryContactCredential='" + getPrimaryContactCredential() + "'" +
            ", contractFileUploadPath='" + getContractFileUploadPath() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", companyUuid='" + getCompanyUuid() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", country='" + getCountry() + "'" +
            ", zip='" + getZip() + "'" +
            ", contactNo1='" + getContactNo1() + "'" +
            ", contactNo2='" + getContactNo2() + "'" +
            ", fax='" + getFax() + "'" +
            ", efax='" + getEfax() + "'" +
            ", email='" + getEmail() + "'" +
            ", relationship='" + getRelationship() + "'" +
            ", modeOfContact='" + getModeOfContact() + "'" +
            ", companyBranchLogo='" + getCompanyBranchLogo() + "'" +
            "}";
    }
}
