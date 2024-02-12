package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SalesOrderMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalesOrderMasterDTO implements Serializable {

    private Long salesOrderId;

    private String salesOrderNo;

    private Long patientId;

    private String patientFirstName;

    private String patientMiddleName;

    private String patientLastName;

    private String patientAddressLine1;

    private String patientAddressLine2;

    private String patientContactNo1;

    private String patientContactNo2;

    private LocalDate patientDob;

    private Double patientHeight;

    private Double patientWeight;

    private String patientSsn;

    private String patientGender;

    private String hipaaOnFileStatus;

    private Long patientBranchId;

    private String branchName;

    private LocalDate deliveryScheduleDatetime;

    private LocalDate deliveryActualDatetime;

    private String deliveryAddressLine1;

    private String deliveryAddressLine2;

    private String deliveryPhoneNo1;

    private String deliveryPhoneNo2;

    private Long facilityId;

    private String facilityName;

    private String facilityNpi;

    private Long taxZoneId;

    private Double taxRate;

    private String salesOrderNote;

    private String deliveryNote;

    private String deliveryTechnician;

    private String signatureRequiredStatus;

    private String podStatus;

    private LocalDate podStatusDatetime;

    private String podLastMessage;

    private LocalDate podMessageDatetime;

    private String mutualHoldStatus;

    private String holdStatus;

    private String holdReasonDescription;

    private LocalDate stopDate;

    private String stopReasonDescription;

    private Long branchId;

    private String billingBranchName;

    private Long inventoryLocationId;

    private String orderStatus;

    private Long orderClassificationId;

    private Long posId;

    private LocalDate admissionDate;

    private LocalDate dischargeDate;

    private Long discountPercentage;

    private String poNumber;

    private String userField1;

    private String userField2;

    private String userField3;

    private String userField4;

    private String reference;

    private String wipStatus;

    private Long wipDaysInState;

    private Long wipAssignedToId;

    private LocalDate wipDateNeeded;

    private String completedStatus;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String cityName;

    private String stateName;

    private String zipCode;

    private String deliveryCityName;

    private String deliveryStateName;

    private String deliveryZipCode;

    private LocalDate patientDod;

    private String posName;

    private Long updatedById;

    private Long confirmationById;

    private String confirmationByName;

    private LocalDate confirmationDate;

    private String createdByName;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID salesOrderMasterUuid;

    private String branchContactPersonName;

    private String branchNpi;

    private String branchEin;

    private String branchContactNo1;

    private String branchContactNo2;

    private String patientIdNo;

    private String posCode;

    private String eclaimCarrierName;

    private String planParticipationCode;

    private String patientMemberId;

    private String contactPersonName;

    private String providerType;

    private String branchAddressLine1;

    private String branchAddressLine2;

    private String branchCity;

    private String branchState;

    private String branchZipCode;

    private String patientDeliveryAddressLine1;

    private String patientDeliveryAddressLine2;

    private String patientDeliveryCity;

    private String patientDeliveryState;

    private String patientDeliveryCountry;

    private String patientDeliveryZip;

    private String patientBillingAddressLine1;

    private String patientBillingAddressLine2;

    private String patientBillingCity;

    private String patientBillingState;

    private String patientBillingCountry;

    private String patientBillingZip;

    private String patientFax;

    private String branchFax;

    private String patientEmail;

    private String relationship;

    private String modeOfContact;

    private String insuredFirstName;

    private String insuredLastName;

    private String insuredAddressLine1;

    private String insuredAddressLine2;

    private String insuredCity;

    private String insuredState;

    private String insuredZip;

    private String insuredContactNo;

    private String insuredGender;

    private String patientRelationshipInsured;

    private String patientConditionEmployment;

    private String patientConditionAutoAccident;

    private String patientConditionOtherAccident;

    private String billingProviderTaxonomy;

    private String billingProviderNpi;

    private String billingProviderOrganisationName;

    private String billingProviderAddressLine1;

    private String billingProviderAddressLine2;

    private String billingProviderCity;

    private String billingProviderState;

    private String billingProviderCountry;

    private String billingProviderZipCode;

    private LocalDate insuredDob;

    private String branchCountry;

    private String branchTaxonomy;

    private Long primaryInsurerPriceTableId;

    private String primaryInsurerPriceTableName;

    private String inventoryLocationName;

    private String soControlNumber;

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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientAddressLine1() {
        return patientAddressLine1;
    }

    public void setPatientAddressLine1(String patientAddressLine1) {
        this.patientAddressLine1 = patientAddressLine1;
    }

    public String getPatientAddressLine2() {
        return patientAddressLine2;
    }

    public void setPatientAddressLine2(String patientAddressLine2) {
        this.patientAddressLine2 = patientAddressLine2;
    }

    public String getPatientContactNo1() {
        return patientContactNo1;
    }

    public void setPatientContactNo1(String patientContactNo1) {
        this.patientContactNo1 = patientContactNo1;
    }

    public String getPatientContactNo2() {
        return patientContactNo2;
    }

    public void setPatientContactNo2(String patientContactNo2) {
        this.patientContactNo2 = patientContactNo2;
    }

    public LocalDate getPatientDob() {
        return patientDob;
    }

    public void setPatientDob(LocalDate patientDob) {
        this.patientDob = patientDob;
    }

    public Double getPatientHeight() {
        return patientHeight;
    }

    public void setPatientHeight(Double patientHeight) {
        this.patientHeight = patientHeight;
    }

    public Double getPatientWeight() {
        return patientWeight;
    }

    public void setPatientWeight(Double patientWeight) {
        this.patientWeight = patientWeight;
    }

    public String getPatientSsn() {
        return patientSsn;
    }

    public void setPatientSsn(String patientSsn) {
        this.patientSsn = patientSsn;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getHipaaOnFileStatus() {
        return hipaaOnFileStatus;
    }

    public void setHipaaOnFileStatus(String hipaaOnFileStatus) {
        this.hipaaOnFileStatus = hipaaOnFileStatus;
    }

    public Long getPatientBranchId() {
        return patientBranchId;
    }

    public void setPatientBranchId(Long patientBranchId) {
        this.patientBranchId = patientBranchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public LocalDate getDeliveryScheduleDatetime() {
        return deliveryScheduleDatetime;
    }

    public void setDeliveryScheduleDatetime(LocalDate deliveryScheduleDatetime) {
        this.deliveryScheduleDatetime = deliveryScheduleDatetime;
    }

    public LocalDate getDeliveryActualDatetime() {
        return deliveryActualDatetime;
    }

    public void setDeliveryActualDatetime(LocalDate deliveryActualDatetime) {
        this.deliveryActualDatetime = deliveryActualDatetime;
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

    public String getDeliveryPhoneNo1() {
        return deliveryPhoneNo1;
    }

    public void setDeliveryPhoneNo1(String deliveryPhoneNo1) {
        this.deliveryPhoneNo1 = deliveryPhoneNo1;
    }

    public String getDeliveryPhoneNo2() {
        return deliveryPhoneNo2;
    }

    public void setDeliveryPhoneNo2(String deliveryPhoneNo2) {
        this.deliveryPhoneNo2 = deliveryPhoneNo2;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityNpi() {
        return facilityNpi;
    }

    public void setFacilityNpi(String facilityNpi) {
        this.facilityNpi = facilityNpi;
    }

    public Long getTaxZoneId() {
        return taxZoneId;
    }

    public void setTaxZoneId(Long taxZoneId) {
        this.taxZoneId = taxZoneId;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getSalesOrderNote() {
        return salesOrderNote;
    }

    public void setSalesOrderNote(String salesOrderNote) {
        this.salesOrderNote = salesOrderNote;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public String getDeliveryTechnician() {
        return deliveryTechnician;
    }

    public void setDeliveryTechnician(String deliveryTechnician) {
        this.deliveryTechnician = deliveryTechnician;
    }

    public String getSignatureRequiredStatus() {
        return signatureRequiredStatus;
    }

    public void setSignatureRequiredStatus(String signatureRequiredStatus) {
        this.signatureRequiredStatus = signatureRequiredStatus;
    }

    public String getPodStatus() {
        return podStatus;
    }

    public void setPodStatus(String podStatus) {
        this.podStatus = podStatus;
    }

    public LocalDate getPodStatusDatetime() {
        return podStatusDatetime;
    }

    public void setPodStatusDatetime(LocalDate podStatusDatetime) {
        this.podStatusDatetime = podStatusDatetime;
    }

    public String getPodLastMessage() {
        return podLastMessage;
    }

    public void setPodLastMessage(String podLastMessage) {
        this.podLastMessage = podLastMessage;
    }

    public LocalDate getPodMessageDatetime() {
        return podMessageDatetime;
    }

    public void setPodMessageDatetime(LocalDate podMessageDatetime) {
        this.podMessageDatetime = podMessageDatetime;
    }

    public String getMutualHoldStatus() {
        return mutualHoldStatus;
    }

    public void setMutualHoldStatus(String mutualHoldStatus) {
        this.mutualHoldStatus = mutualHoldStatus;
    }

    public String getHoldStatus() {
        return holdStatus;
    }

    public void setHoldStatus(String holdStatus) {
        this.holdStatus = holdStatus;
    }

    public String getHoldReasonDescription() {
        return holdReasonDescription;
    }

    public void setHoldReasonDescription(String holdReasonDescription) {
        this.holdReasonDescription = holdReasonDescription;
    }

    public LocalDate getStopDate() {
        return stopDate;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = stopDate;
    }

    public String getStopReasonDescription() {
        return stopReasonDescription;
    }

    public void setStopReasonDescription(String stopReasonDescription) {
        this.stopReasonDescription = stopReasonDescription;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBillingBranchName() {
        return billingBranchName;
    }

    public void setBillingBranchName(String billingBranchName) {
        this.billingBranchName = billingBranchName;
    }

    public Long getInventoryLocationId() {
        return inventoryLocationId;
    }

    public void setInventoryLocationId(Long inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderClassificationId() {
        return orderClassificationId;
    }

    public void setOrderClassificationId(Long orderClassificationId) {
        this.orderClassificationId = orderClassificationId;
    }

    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public Long getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Long discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getUserField1() {
        return userField1;
    }

    public void setUserField1(String userField1) {
        this.userField1 = userField1;
    }

    public String getUserField2() {
        return userField2;
    }

    public void setUserField2(String userField2) {
        this.userField2 = userField2;
    }

    public String getUserField3() {
        return userField3;
    }

    public void setUserField3(String userField3) {
        this.userField3 = userField3;
    }

    public String getUserField4() {
        return userField4;
    }

    public void setUserField4(String userField4) {
        this.userField4 = userField4;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getWipStatus() {
        return wipStatus;
    }

    public void setWipStatus(String wipStatus) {
        this.wipStatus = wipStatus;
    }

    public Long getWipDaysInState() {
        return wipDaysInState;
    }

    public void setWipDaysInState(Long wipDaysInState) {
        this.wipDaysInState = wipDaysInState;
    }

    public Long getWipAssignedToId() {
        return wipAssignedToId;
    }

    public void setWipAssignedToId(Long wipAssignedToId) {
        this.wipAssignedToId = wipAssignedToId;
    }

    public LocalDate getWipDateNeeded() {
        return wipDateNeeded;
    }

    public void setWipDateNeeded(LocalDate wipDateNeeded) {
        this.wipDateNeeded = wipDateNeeded;
    }

    public String getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(String completedStatus) {
        this.completedStatus = completedStatus;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDeliveryCityName() {
        return deliveryCityName;
    }

    public void setDeliveryCityName(String deliveryCityName) {
        this.deliveryCityName = deliveryCityName;
    }

    public String getDeliveryStateName() {
        return deliveryStateName;
    }

    public void setDeliveryStateName(String deliveryStateName) {
        this.deliveryStateName = deliveryStateName;
    }

    public String getDeliveryZipCode() {
        return deliveryZipCode;
    }

    public void setDeliveryZipCode(String deliveryZipCode) {
        this.deliveryZipCode = deliveryZipCode;
    }

    public LocalDate getPatientDod() {
        return patientDod;
    }

    public void setPatientDod(LocalDate patientDod) {
        this.patientDod = patientDod;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public Long getConfirmationById() {
        return confirmationById;
    }

    public void setConfirmationById(Long confirmationById) {
        this.confirmationById = confirmationById;
    }

    public String getConfirmationByName() {
        return confirmationByName;
    }

    public void setConfirmationByName(String confirmationByName) {
        this.confirmationByName = confirmationByName;
    }

    public LocalDate getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(LocalDate confirmationDate) {
        this.confirmationDate = confirmationDate;
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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getSalesOrderMasterUuid() {
        return salesOrderMasterUuid;
    }

    public void setSalesOrderMasterUuid(UUID salesOrderMasterUuid) {
        this.salesOrderMasterUuid = salesOrderMasterUuid;
    }

    public String getBranchContactPersonName() {
        return branchContactPersonName;
    }

    public void setBranchContactPersonName(String branchContactPersonName) {
        this.branchContactPersonName = branchContactPersonName;
    }

    public String getBranchNpi() {
        return branchNpi;
    }

    public void setBranchNpi(String branchNpi) {
        this.branchNpi = branchNpi;
    }

    public String getBranchEin() {
        return branchEin;
    }

    public void setBranchEin(String branchEin) {
        this.branchEin = branchEin;
    }

    public String getBranchContactNo1() {
        return branchContactNo1;
    }

    public void setBranchContactNo1(String branchContactNo1) {
        this.branchContactNo1 = branchContactNo1;
    }

    public String getBranchContactNo2() {
        return branchContactNo2;
    }

    public void setBranchContactNo2(String branchContactNo2) {
        this.branchContactNo2 = branchContactNo2;
    }

    public String getPatientIdNo() {
        return patientIdNo;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getEclaimCarrierName() {
        return eclaimCarrierName;
    }

    public void setEclaimCarrierName(String eclaimCarrierName) {
        this.eclaimCarrierName = eclaimCarrierName;
    }

    public String getPlanParticipationCode() {
        return planParticipationCode;
    }

    public void setPlanParticipationCode(String planParticipationCode) {
        this.planParticipationCode = planParticipationCode;
    }

    public String getPatientMemberId() {
        return patientMemberId;
    }

    public void setPatientMemberId(String patientMemberId) {
        this.patientMemberId = patientMemberId;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getBranchAddressLine1() {
        return branchAddressLine1;
    }

    public void setBranchAddressLine1(String branchAddressLine1) {
        this.branchAddressLine1 = branchAddressLine1;
    }

    public String getBranchAddressLine2() {
        return branchAddressLine2;
    }

    public void setBranchAddressLine2(String branchAddressLine2) {
        this.branchAddressLine2 = branchAddressLine2;
    }

    public String getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    public String getBranchState() {
        return branchState;
    }

    public void setBranchState(String branchState) {
        this.branchState = branchState;
    }

    public String getBranchZipCode() {
        return branchZipCode;
    }

    public void setBranchZipCode(String branchZipCode) {
        this.branchZipCode = branchZipCode;
    }

    public String getPatientDeliveryAddressLine1() {
        return patientDeliveryAddressLine1;
    }

    public void setPatientDeliveryAddressLine1(String patientDeliveryAddressLine1) {
        this.patientDeliveryAddressLine1 = patientDeliveryAddressLine1;
    }

    public String getPatientDeliveryAddressLine2() {
        return patientDeliveryAddressLine2;
    }

    public void setPatientDeliveryAddressLine2(String patientDeliveryAddressLine2) {
        this.patientDeliveryAddressLine2 = patientDeliveryAddressLine2;
    }

    public String getPatientDeliveryCity() {
        return patientDeliveryCity;
    }

    public void setPatientDeliveryCity(String patientDeliveryCity) {
        this.patientDeliveryCity = patientDeliveryCity;
    }

    public String getPatientDeliveryState() {
        return patientDeliveryState;
    }

    public void setPatientDeliveryState(String patientDeliveryState) {
        this.patientDeliveryState = patientDeliveryState;
    }

    public String getPatientDeliveryCountry() {
        return patientDeliveryCountry;
    }

    public void setPatientDeliveryCountry(String patientDeliveryCountry) {
        this.patientDeliveryCountry = patientDeliveryCountry;
    }

    public String getPatientDeliveryZip() {
        return patientDeliveryZip;
    }

    public void setPatientDeliveryZip(String patientDeliveryZip) {
        this.patientDeliveryZip = patientDeliveryZip;
    }

    public String getPatientBillingAddressLine1() {
        return patientBillingAddressLine1;
    }

    public void setPatientBillingAddressLine1(String patientBillingAddressLine1) {
        this.patientBillingAddressLine1 = patientBillingAddressLine1;
    }

    public String getPatientBillingAddressLine2() {
        return patientBillingAddressLine2;
    }

    public void setPatientBillingAddressLine2(String patientBillingAddressLine2) {
        this.patientBillingAddressLine2 = patientBillingAddressLine2;
    }

    public String getPatientBillingCity() {
        return patientBillingCity;
    }

    public void setPatientBillingCity(String patientBillingCity) {
        this.patientBillingCity = patientBillingCity;
    }

    public String getPatientBillingState() {
        return patientBillingState;
    }

    public void setPatientBillingState(String patientBillingState) {
        this.patientBillingState = patientBillingState;
    }

    public String getPatientBillingCountry() {
        return patientBillingCountry;
    }

    public void setPatientBillingCountry(String patientBillingCountry) {
        this.patientBillingCountry = patientBillingCountry;
    }

    public String getPatientBillingZip() {
        return patientBillingZip;
    }

    public void setPatientBillingZip(String patientBillingZip) {
        this.patientBillingZip = patientBillingZip;
    }

    public String getPatientFax() {
        return patientFax;
    }

    public void setPatientFax(String patientFax) {
        this.patientFax = patientFax;
    }

    public String getBranchFax() {
        return branchFax;
    }

    public void setBranchFax(String branchFax) {
        this.branchFax = branchFax;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
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

    public String getInsuredFirstName() {
        return insuredFirstName;
    }

    public void setInsuredFirstName(String insuredFirstName) {
        this.insuredFirstName = insuredFirstName;
    }

    public String getInsuredLastName() {
        return insuredLastName;
    }

    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }

    public String getInsuredAddressLine1() {
        return insuredAddressLine1;
    }

    public void setInsuredAddressLine1(String insuredAddressLine1) {
        this.insuredAddressLine1 = insuredAddressLine1;
    }

    public String getInsuredAddressLine2() {
        return insuredAddressLine2;
    }

    public void setInsuredAddressLine2(String insuredAddressLine2) {
        this.insuredAddressLine2 = insuredAddressLine2;
    }

    public String getInsuredCity() {
        return insuredCity;
    }

    public void setInsuredCity(String insuredCity) {
        this.insuredCity = insuredCity;
    }

    public String getInsuredState() {
        return insuredState;
    }

    public void setInsuredState(String insuredState) {
        this.insuredState = insuredState;
    }

    public String getInsuredZip() {
        return insuredZip;
    }

    public void setInsuredZip(String insuredZip) {
        this.insuredZip = insuredZip;
    }

    public String getInsuredContactNo() {
        return insuredContactNo;
    }

    public void setInsuredContactNo(String insuredContactNo) {
        this.insuredContactNo = insuredContactNo;
    }

    public String getInsuredGender() {
        return insuredGender;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public String getPatientRelationshipInsured() {
        return patientRelationshipInsured;
    }

    public void setPatientRelationshipInsured(String patientRelationshipInsured) {
        this.patientRelationshipInsured = patientRelationshipInsured;
    }

    public String getPatientConditionEmployment() {
        return patientConditionEmployment;
    }

    public void setPatientConditionEmployment(String patientConditionEmployment) {
        this.patientConditionEmployment = patientConditionEmployment;
    }

    public String getPatientConditionAutoAccident() {
        return patientConditionAutoAccident;
    }

    public void setPatientConditionAutoAccident(String patientConditionAutoAccident) {
        this.patientConditionAutoAccident = patientConditionAutoAccident;
    }

    public String getPatientConditionOtherAccident() {
        return patientConditionOtherAccident;
    }

    public void setPatientConditionOtherAccident(String patientConditionOtherAccident) {
        this.patientConditionOtherAccident = patientConditionOtherAccident;
    }

    public String getBillingProviderTaxonomy() {
        return billingProviderTaxonomy;
    }

    public void setBillingProviderTaxonomy(String billingProviderTaxonomy) {
        this.billingProviderTaxonomy = billingProviderTaxonomy;
    }

    public String getBillingProviderNpi() {
        return billingProviderNpi;
    }

    public void setBillingProviderNpi(String billingProviderNpi) {
        this.billingProviderNpi = billingProviderNpi;
    }

    public String getBillingProviderOrganisationName() {
        return billingProviderOrganisationName;
    }

    public void setBillingProviderOrganisationName(String billingProviderOrganisationName) {
        this.billingProviderOrganisationName = billingProviderOrganisationName;
    }

    public String getBillingProviderAddressLine1() {
        return billingProviderAddressLine1;
    }

    public void setBillingProviderAddressLine1(String billingProviderAddressLine1) {
        this.billingProviderAddressLine1 = billingProviderAddressLine1;
    }

    public String getBillingProviderAddressLine2() {
        return billingProviderAddressLine2;
    }

    public void setBillingProviderAddressLine2(String billingProviderAddressLine2) {
        this.billingProviderAddressLine2 = billingProviderAddressLine2;
    }

    public String getBillingProviderCity() {
        return billingProviderCity;
    }

    public void setBillingProviderCity(String billingProviderCity) {
        this.billingProviderCity = billingProviderCity;
    }

    public String getBillingProviderState() {
        return billingProviderState;
    }

    public void setBillingProviderState(String billingProviderState) {
        this.billingProviderState = billingProviderState;
    }

    public String getBillingProviderCountry() {
        return billingProviderCountry;
    }

    public void setBillingProviderCountry(String billingProviderCountry) {
        this.billingProviderCountry = billingProviderCountry;
    }

    public String getBillingProviderZipCode() {
        return billingProviderZipCode;
    }

    public void setBillingProviderZipCode(String billingProviderZipCode) {
        this.billingProviderZipCode = billingProviderZipCode;
    }

    public LocalDate getInsuredDob() {
        return insuredDob;
    }

    public void setInsuredDob(LocalDate insuredDob) {
        this.insuredDob = insuredDob;
    }

    public String getBranchCountry() {
        return branchCountry;
    }

    public void setBranchCountry(String branchCountry) {
        this.branchCountry = branchCountry;
    }

    public String getBranchTaxonomy() {
        return branchTaxonomy;
    }

    public void setBranchTaxonomy(String branchTaxonomy) {
        this.branchTaxonomy = branchTaxonomy;
    }

    public Long getPrimaryInsurerPriceTableId() {
        return primaryInsurerPriceTableId;
    }

    public void setPrimaryInsurerPriceTableId(Long primaryInsurerPriceTableId) {
        this.primaryInsurerPriceTableId = primaryInsurerPriceTableId;
    }

    public String getPrimaryInsurerPriceTableName() {
        return primaryInsurerPriceTableName;
    }

    public void setPrimaryInsurerPriceTableName(String primaryInsurerPriceTableName) {
        this.primaryInsurerPriceTableName = primaryInsurerPriceTableName;
    }

    public String getInventoryLocationName() {
        return inventoryLocationName;
    }

    public void setInventoryLocationName(String inventoryLocationName) {
        this.inventoryLocationName = inventoryLocationName;
    }

    public String getSoControlNumber() {
        return soControlNumber;
    }

    public void setSoControlNumber(String soControlNumber) {
        this.soControlNumber = soControlNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderMasterDTO)) {
            return false;
        }

        SalesOrderMasterDTO salesOrderMasterDTO = (SalesOrderMasterDTO) o;
        if (this.salesOrderId == null) {
            return false;
        }
        return Objects.equals(this.salesOrderId, salesOrderMasterDTO.salesOrderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.salesOrderId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderMasterDTO{" +
            "salesOrderId=" + getSalesOrderId() +
            ", salesOrderNo='" + getSalesOrderNo() + "'" +
            ", patientId=" + getPatientId() +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientMiddleName='" + getPatientMiddleName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", patientAddressLine1='" + getPatientAddressLine1() + "'" +
            ", patientAddressLine2='" + getPatientAddressLine2() + "'" +
            ", patientContactNo1='" + getPatientContactNo1() + "'" +
            ", patientContactNo2='" + getPatientContactNo2() + "'" +
            ", patientDob='" + getPatientDob() + "'" +
            ", patientHeight=" + getPatientHeight() +
            ", patientWeight=" + getPatientWeight() +
            ", patientSsn='" + getPatientSsn() + "'" +
            ", patientGender='" + getPatientGender() + "'" +
            ", hipaaOnFileStatus='" + getHipaaOnFileStatus() + "'" +
            ", patientBranchId=" + getPatientBranchId() +
            ", branchName='" + getBranchName() + "'" +
            ", deliveryScheduleDatetime='" + getDeliveryScheduleDatetime() + "'" +
            ", deliveryActualDatetime='" + getDeliveryActualDatetime() + "'" +
            ", deliveryAddressLine1='" + getDeliveryAddressLine1() + "'" +
            ", deliveryAddressLine2='" + getDeliveryAddressLine2() + "'" +
            ", deliveryPhoneNo1='" + getDeliveryPhoneNo1() + "'" +
            ", deliveryPhoneNo2='" + getDeliveryPhoneNo2() + "'" +
            ", facilityId=" + getFacilityId() +
            ", facilityName='" + getFacilityName() + "'" +
            ", facilityNpi='" + getFacilityNpi() + "'" +
            ", taxZoneId=" + getTaxZoneId() +
            ", taxRate=" + getTaxRate() +
            ", salesOrderNote='" + getSalesOrderNote() + "'" +
            ", deliveryNote='" + getDeliveryNote() + "'" +
            ", deliveryTechnician='" + getDeliveryTechnician() + "'" +
            ", signatureRequiredStatus='" + getSignatureRequiredStatus() + "'" +
            ", podStatus='" + getPodStatus() + "'" +
            ", podStatusDatetime='" + getPodStatusDatetime() + "'" +
            ", podLastMessage='" + getPodLastMessage() + "'" +
            ", podMessageDatetime='" + getPodMessageDatetime() + "'" +
            ", mutualHoldStatus='" + getMutualHoldStatus() + "'" +
            ", holdStatus='" + getHoldStatus() + "'" +
            ", holdReasonDescription='" + getHoldReasonDescription() + "'" +
            ", stopDate='" + getStopDate() + "'" +
            ", stopReasonDescription='" + getStopReasonDescription() + "'" +
            ", branchId=" + getBranchId() +
            ", billingBranchName='" + getBillingBranchName() + "'" +
            ", inventoryLocationId=" + getInventoryLocationId() +
            ", orderStatus='" + getOrderStatus() + "'" +
            ", orderClassificationId=" + getOrderClassificationId() +
            ", posId=" + getPosId() +
            ", admissionDate='" + getAdmissionDate() + "'" +
            ", dischargeDate='" + getDischargeDate() + "'" +
            ", discountPercentage=" + getDiscountPercentage() +
            ", poNumber='" + getPoNumber() + "'" +
            ", userField1='" + getUserField1() + "'" +
            ", userField2='" + getUserField2() + "'" +
            ", userField3='" + getUserField3() + "'" +
            ", userField4='" + getUserField4() + "'" +
            ", reference='" + getReference() + "'" +
            ", wipStatus='" + getWipStatus() + "'" +
            ", wipDaysInState=" + getWipDaysInState() +
            ", wipAssignedToId=" + getWipAssignedToId() +
            ", wipDateNeeded='" + getWipDateNeeded() + "'" +
            ", completedStatus='" + getCompletedStatus() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", cityName='" + getCityName() + "'" +
            ", stateName='" + getStateName() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", deliveryCityName='" + getDeliveryCityName() + "'" +
            ", deliveryStateName='" + getDeliveryStateName() + "'" +
            ", deliveryZipCode='" + getDeliveryZipCode() + "'" +
            ", patientDod='" + getPatientDod() + "'" +
            ", posName='" + getPosName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", confirmationById=" + getConfirmationById() +
            ", confirmationByName='" + getConfirmationByName() + "'" +
            ", confirmationDate='" + getConfirmationDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", salesOrderMasterUuid='" + getSalesOrderMasterUuid() + "'" +
            ", branchContactPersonName='" + getBranchContactPersonName() + "'" +
            ", branchNpi='" + getBranchNpi() + "'" +
            ", branchEin='" + getBranchEin() + "'" +
            ", branchContactNo1='" + getBranchContactNo1() + "'" +
            ", branchContactNo2='" + getBranchContactNo2() + "'" +
            ", patientIdNo='" + getPatientIdNo() + "'" +
            ", posCode='" + getPosCode() + "'" +
            ", eclaimCarrierName='" + getEclaimCarrierName() + "'" +
            ", planParticipationCode='" + getPlanParticipationCode() + "'" +
            ", patientMemberId='" + getPatientMemberId() + "'" +
            ", contactPersonName='" + getContactPersonName() + "'" +
            ", providerType='" + getProviderType() + "'" +
            ", branchAddressLine1='" + getBranchAddressLine1() + "'" +
            ", branchAddressLine2='" + getBranchAddressLine2() + "'" +
            ", branchCity='" + getBranchCity() + "'" +
            ", branchState='" + getBranchState() + "'" +
            ", branchZipCode='" + getBranchZipCode() + "'" +
            ", patientDeliveryAddressLine1='" + getPatientDeliveryAddressLine1() + "'" +
            ", patientDeliveryAddressLine2='" + getPatientDeliveryAddressLine2() + "'" +
            ", patientDeliveryCity='" + getPatientDeliveryCity() + "'" +
            ", patientDeliveryState='" + getPatientDeliveryState() + "'" +
            ", patientDeliveryCountry='" + getPatientDeliveryCountry() + "'" +
            ", patientDeliveryZip='" + getPatientDeliveryZip() + "'" +
            ", patientBillingAddressLine1='" + getPatientBillingAddressLine1() + "'" +
            ", patientBillingAddressLine2='" + getPatientBillingAddressLine2() + "'" +
            ", patientBillingCity='" + getPatientBillingCity() + "'" +
            ", patientBillingState='" + getPatientBillingState() + "'" +
            ", patientBillingCountry='" + getPatientBillingCountry() + "'" +
            ", patientBillingZip='" + getPatientBillingZip() + "'" +
            ", patientFax='" + getPatientFax() + "'" +
            ", branchFax='" + getBranchFax() + "'" +
            ", patientEmail='" + getPatientEmail() + "'" +
            ", relationship='" + getRelationship() + "'" +
            ", modeOfContact='" + getModeOfContact() + "'" +
            ", insuredFirstName='" + getInsuredFirstName() + "'" +
            ", insuredLastName='" + getInsuredLastName() + "'" +
            ", insuredAddressLine1='" + getInsuredAddressLine1() + "'" +
            ", insuredAddressLine2='" + getInsuredAddressLine2() + "'" +
            ", insuredCity='" + getInsuredCity() + "'" +
            ", insuredState='" + getInsuredState() + "'" +
            ", insuredZip='" + getInsuredZip() + "'" +
            ", insuredContactNo='" + getInsuredContactNo() + "'" +
            ", insuredGender='" + getInsuredGender() + "'" +
            ", patientRelationshipInsured='" + getPatientRelationshipInsured() + "'" +
            ", patientConditionEmployment='" + getPatientConditionEmployment() + "'" +
            ", patientConditionAutoAccident='" + getPatientConditionAutoAccident() + "'" +
            ", patientConditionOtherAccident='" + getPatientConditionOtherAccident() + "'" +
            ", billingProviderTaxonomy='" + getBillingProviderTaxonomy() + "'" +
            ", billingProviderNpi='" + getBillingProviderNpi() + "'" +
            ", billingProviderOrganisationName='" + getBillingProviderOrganisationName() + "'" +
            ", billingProviderAddressLine1='" + getBillingProviderAddressLine1() + "'" +
            ", billingProviderAddressLine2='" + getBillingProviderAddressLine2() + "'" +
            ", billingProviderCity='" + getBillingProviderCity() + "'" +
            ", billingProviderState='" + getBillingProviderState() + "'" +
            ", billingProviderCountry='" + getBillingProviderCountry() + "'" +
            ", billingProviderZipCode='" + getBillingProviderZipCode() + "'" +
            ", insuredDob='" + getInsuredDob() + "'" +
            ", branchCountry='" + getBranchCountry() + "'" +
            ", branchTaxonomy='" + getBranchTaxonomy() + "'" +
            ", primaryInsurerPriceTableId=" + getPrimaryInsurerPriceTableId() +
            ", primaryInsurerPriceTableName='" + getPrimaryInsurerPriceTableName() + "'" +
            ", inventoryLocationName='" + getInventoryLocationName() + "'" +
            ", soControlNumber='" + getSoControlNumber() + "'" +
            "}";
    }
}
