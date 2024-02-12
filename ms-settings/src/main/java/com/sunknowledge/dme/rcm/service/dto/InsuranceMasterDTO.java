package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.InsuranceMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InsuranceMasterDTO implements Serializable {

    private Long insuranceId;

    private String insuranceName;

    private String insurancePlanType;

    private String taxType;

    private String taxIncludedAllowableStatus;

    private Long insuranceGroupId;

    private Long priceTableId;

    private String claimTypeDmeStatus;

    private String claimTypeMajorMadicalStatus;

    private String claimTypePharmacyStatus;

    private Double payPercentage;

    private String enableSecondaryBillingStatus;

    private String amtPrintOnDeliveryTicketStatus;

    private String medigapStatus;

    private Long medigapNum;

    private String notes;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID insuranceMasterUuid;

    private String insurancePayerIdNo;

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

    private String claimProgramCode;

    private String claimProgramName;

    private String insuranceIdNo;

    private String insuranceGroupNo;

    private String priceTableName;

    private String claimProgramId;

    private String claimFormName;

    private String insuranceGroupName;

    private String contactPersonFirstName;

    private String contactPersonMiddleName;

    private String contactPersonLastName;

    private String taxTncludedAllowableStatus;

    private String cmsCrossoverInsuranceIdNo;

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsurancePlanType() {
        return insurancePlanType;
    }

    public void setInsurancePlanType(String insurancePlanType) {
        this.insurancePlanType = insurancePlanType;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getTaxIncludedAllowableStatus() {
        return taxIncludedAllowableStatus;
    }

    public void setTaxIncludedAllowableStatus(String taxIncludedAllowableStatus) {
        this.taxIncludedAllowableStatus = taxIncludedAllowableStatus;
    }

    public Long getInsuranceGroupId() {
        return insuranceGroupId;
    }

    public void setInsuranceGroupId(Long insuranceGroupId) {
        this.insuranceGroupId = insuranceGroupId;
    }

    public Long getPriceTableId() {
        return priceTableId;
    }

    public void setPriceTableId(Long priceTableId) {
        this.priceTableId = priceTableId;
    }

    public String getClaimTypeDmeStatus() {
        return claimTypeDmeStatus;
    }

    public void setClaimTypeDmeStatus(String claimTypeDmeStatus) {
        this.claimTypeDmeStatus = claimTypeDmeStatus;
    }

    public String getClaimTypeMajorMadicalStatus() {
        return claimTypeMajorMadicalStatus;
    }

    public void setClaimTypeMajorMadicalStatus(String claimTypeMajorMadicalStatus) {
        this.claimTypeMajorMadicalStatus = claimTypeMajorMadicalStatus;
    }

    public String getClaimTypePharmacyStatus() {
        return claimTypePharmacyStatus;
    }

    public void setClaimTypePharmacyStatus(String claimTypePharmacyStatus) {
        this.claimTypePharmacyStatus = claimTypePharmacyStatus;
    }

    public Double getPayPercentage() {
        return payPercentage;
    }

    public void setPayPercentage(Double payPercentage) {
        this.payPercentage = payPercentage;
    }

    public String getEnableSecondaryBillingStatus() {
        return enableSecondaryBillingStatus;
    }

    public void setEnableSecondaryBillingStatus(String enableSecondaryBillingStatus) {
        this.enableSecondaryBillingStatus = enableSecondaryBillingStatus;
    }

    public String getAmtPrintOnDeliveryTicketStatus() {
        return amtPrintOnDeliveryTicketStatus;
    }

    public void setAmtPrintOnDeliveryTicketStatus(String amtPrintOnDeliveryTicketStatus) {
        this.amtPrintOnDeliveryTicketStatus = amtPrintOnDeliveryTicketStatus;
    }

    public String getMedigapStatus() {
        return medigapStatus;
    }

    public void setMedigapStatus(String medigapStatus) {
        this.medigapStatus = medigapStatus;
    }

    public Long getMedigapNum() {
        return medigapNum;
    }

    public void setMedigapNum(Long medigapNum) {
        this.medigapNum = medigapNum;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public UUID getInsuranceMasterUuid() {
        return insuranceMasterUuid;
    }

    public void setInsuranceMasterUuid(UUID insuranceMasterUuid) {
        this.insuranceMasterUuid = insuranceMasterUuid;
    }

    public String getInsurancePayerIdNo() {
        return insurancePayerIdNo;
    }

    public void setInsurancePayerIdNo(String insurancePayerIdNo) {
        this.insurancePayerIdNo = insurancePayerIdNo;
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

    public String getClaimProgramCode() {
        return claimProgramCode;
    }

    public void setClaimProgramCode(String claimProgramCode) {
        this.claimProgramCode = claimProgramCode;
    }

    public String getClaimProgramName() {
        return claimProgramName;
    }

    public void setClaimProgramName(String claimProgramName) {
        this.claimProgramName = claimProgramName;
    }

    public String getInsuranceIdNo() {
        return insuranceIdNo;
    }

    public void setInsuranceIdNo(String insuranceIdNo) {
        this.insuranceIdNo = insuranceIdNo;
    }

    public String getInsuranceGroupNo() {
        return insuranceGroupNo;
    }

    public void setInsuranceGroupNo(String insuranceGroupNo) {
        this.insuranceGroupNo = insuranceGroupNo;
    }

    public String getPriceTableName() {
        return priceTableName;
    }

    public void setPriceTableName(String priceTableName) {
        this.priceTableName = priceTableName;
    }

    public String getClaimProgramId() {
        return claimProgramId;
    }

    public void setClaimProgramId(String claimProgramId) {
        this.claimProgramId = claimProgramId;
    }

    public String getClaimFormName() {
        return claimFormName;
    }

    public void setClaimFormName(String claimFormName) {
        this.claimFormName = claimFormName;
    }

    public String getInsuranceGroupName() {
        return insuranceGroupName;
    }

    public void setInsuranceGroupName(String insuranceGroupName) {
        this.insuranceGroupName = insuranceGroupName;
    }

    public String getContactPersonFirstName() {
        return contactPersonFirstName;
    }

    public void setContactPersonFirstName(String contactPersonFirstName) {
        this.contactPersonFirstName = contactPersonFirstName;
    }

    public String getContactPersonMiddleName() {
        return contactPersonMiddleName;
    }

    public void setContactPersonMiddleName(String contactPersonMiddleName) {
        this.contactPersonMiddleName = contactPersonMiddleName;
    }

    public String getContactPersonLastName() {
        return contactPersonLastName;
    }

    public void setContactPersonLastName(String contactPersonLastName) {
        this.contactPersonLastName = contactPersonLastName;
    }

    public String getTaxTncludedAllowableStatus() {
        return taxTncludedAllowableStatus;
    }

    public void setTaxTncludedAllowableStatus(String taxTncludedAllowableStatus) {
        this.taxTncludedAllowableStatus = taxTncludedAllowableStatus;
    }

    public String getCmsCrossoverInsuranceIdNo() {
        return cmsCrossoverInsuranceIdNo;
    }

    public void setCmsCrossoverInsuranceIdNo(String cmsCrossoverInsuranceIdNo) {
        this.cmsCrossoverInsuranceIdNo = cmsCrossoverInsuranceIdNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsuranceMasterDTO)) {
            return false;
        }

        InsuranceMasterDTO insuranceMasterDTO = (InsuranceMasterDTO) o;
        if (this.insuranceId == null) {
            return false;
        }
        return Objects.equals(this.insuranceId, insuranceMasterDTO.insuranceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.insuranceId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InsuranceMasterDTO{" +
            "insuranceId=" + getInsuranceId() +
            ", insuranceName='" + getInsuranceName() + "'" +
            ", insurancePlanType='" + getInsurancePlanType() + "'" +
            ", taxType='" + getTaxType() + "'" +
            ", taxIncludedAllowableStatus='" + getTaxIncludedAllowableStatus() + "'" +
            ", insuranceGroupId=" + getInsuranceGroupId() +
            ", priceTableId=" + getPriceTableId() +
            ", claimTypeDmeStatus='" + getClaimTypeDmeStatus() + "'" +
            ", claimTypeMajorMadicalStatus='" + getClaimTypeMajorMadicalStatus() + "'" +
            ", claimTypePharmacyStatus='" + getClaimTypePharmacyStatus() + "'" +
            ", payPercentage=" + getPayPercentage() +
            ", enableSecondaryBillingStatus='" + getEnableSecondaryBillingStatus() + "'" +
            ", amtPrintOnDeliveryTicketStatus='" + getAmtPrintOnDeliveryTicketStatus() + "'" +
            ", medigapStatus='" + getMedigapStatus() + "'" +
            ", medigapNum=" + getMedigapNum() +
            ", notes='" + getNotes() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", insuranceMasterUuid='" + getInsuranceMasterUuid() + "'" +
            ", insurancePayerIdNo='" + getInsurancePayerIdNo() + "'" +
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
            ", claimProgramCode='" + getClaimProgramCode() + "'" +
            ", claimProgramName='" + getClaimProgramName() + "'" +
            ", insuranceIdNo='" + getInsuranceIdNo() + "'" +
            ", insuranceGroupNo='" + getInsuranceGroupNo() + "'" +
            ", priceTableName='" + getPriceTableName() + "'" +
            ", claimProgramId='" + getClaimProgramId() + "'" +
            ", claimFormName='" + getClaimFormName() + "'" +
            ", insuranceGroupName='" + getInsuranceGroupName() + "'" +
            ", contactPersonFirstName='" + getContactPersonFirstName() + "'" +
            ", contactPersonMiddleName='" + getContactPersonMiddleName() + "'" +
            ", contactPersonLastName='" + getContactPersonLastName() + "'" +
            ", taxTncludedAllowableStatus='" + getTaxTncludedAllowableStatus() + "'" +
            ", cmsCrossoverInsuranceIdNo='" + getCmsCrossoverInsuranceIdNo() + "'" +
            "}";
    }
}
