package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DeliveryTicket} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DeliveryTicketDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long deliveryTicketId;

    private String deliveryTicketNo;

    private Long soId;

    private String soNo;

    private String patientFirstName;

    private String patientMiddleName;

    private String patientLastName;

    private String gender;

    private Integer ageAsOnDate;

    private String phone1;

    private String phone2;

    private String email;

    private String deliveryAddress1;

    private String deliveryAddress2;

    private String deliveryCity;

    private String deliveryState;

    private String deliveryZip;

    private String deliveryStatus;

    private LocalDate deliveryDate;

    private String deliveryPriority;

    private String deliveryNote;

    private String deliveryComment;

    private String deliveryAcceptedBy;

    private String relationshipWithPatient;

    private String deliveryAcceptedByContactNo;

    private String primaryInsurerName;

    private String primaryInsurerPolicyNo;

    private String primaryInsurerPatientIdNumber;

    private String patientIdNo;

    private String branchAddressLine1;

    private String branchAddressLine2;

    private String branchCity;

    private String branchState;

    private String branchZipCode;

    private String branchContactNo1;

    private String branchContactNo2;

    private String branchNpi;

    private String branchEin;

    private String branchFax;

    private String orderingProviderFirstName;

    private String orderingProviderMiddleName;

    private String orderingProviderLastName;

    private String orderingProviderNpi;

    private String orderingProviderAddressLine1;

    private String orderingProviderAddressLine2;

    private String orderingProviderCity;

    private String orderingProviderState;

    private String orderingProviderZip;

    private String orderingProviderPhone1;

    private String orderingProviderPhone2;

    private String orderingProviderFax;

    private String orderingProviderEmail;

    private String branchName;

    private Long patientBranchId;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedBy;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID deliveryTicketUuid;

    private String billingAddressLine1;

    private String billingAddressLine2;

    private String billingCity;

    private String billingState;

    private String billingZip;

    private Long inventoryLocationId;

    private String inventoryLocationName;

    private Long deliveryTicketDocumentId;

    private String deliveryTicketDocumentNo;

    private String deliveryTicketDocumentName;

    private String deliveryType;

    private String carrierName;

    private LocalDate shippingDate;

    private String trackingNo;

    private String referenceNo;

    private String packageWeight;

    private String setupMethod;

    private String setupTechnicianNo;

    private String setupTechnicianContactNo;

    private String setupTechnicianFirstName;

    private String setupTechnicianMiddleName;

    private String setupTechnicianLastName;

    private LocalDate setupDateTime;

    private LocalDate scheduleSetupDateTime;

    private String setupComments;

    private String setupStatus;

    private String courierPackageAcceptedBy;

    private String therapistFirstName;

    private String therapistMiddleName;

    private String therapistLastName;

    private String therapistLicenseNo;

    private String therapistNpi;

    private String therapistTaxonomyCode;

    private LocalDate scheduleTherapyDate;

    private LocalDate actualTherapyDate;

    private String therapyMode;

    private String therapyStatus;

    private String therapyNotes;

    public Long getDeliveryTicketId() {
        return deliveryTicketId;
    }

    public void setDeliveryTicketId(Long deliveryTicketId) {
        this.deliveryTicketId = deliveryTicketId;
    }

    public String getDeliveryTicketNo() {
        return deliveryTicketNo;
    }

    public void setDeliveryTicketNo(String deliveryTicketNo) {
        this.deliveryTicketNo = deliveryTicketNo;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAgeAsOnDate() {
        return ageAsOnDate;
    }

    public void setAgeAsOnDate(Integer ageAsOnDate) {
        this.ageAsOnDate = ageAsOnDate;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryAddress1() {
        return deliveryAddress1;
    }

    public void setDeliveryAddress1(String deliveryAddress1) {
        this.deliveryAddress1 = deliveryAddress1;
    }

    public String getDeliveryAddress2() {
        return deliveryAddress2;
    }

    public void setDeliveryAddress2(String deliveryAddress2) {
        this.deliveryAddress2 = deliveryAddress2;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryZip() {
        return deliveryZip;
    }

    public void setDeliveryZip(String deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryPriority() {
        return deliveryPriority;
    }

    public void setDeliveryPriority(String deliveryPriority) {
        this.deliveryPriority = deliveryPriority;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public String getDeliveryComment() {
        return deliveryComment;
    }

    public void setDeliveryComment(String deliveryComment) {
        this.deliveryComment = deliveryComment;
    }

    public String getDeliveryAcceptedBy() {
        return deliveryAcceptedBy;
    }

    public void setDeliveryAcceptedBy(String deliveryAcceptedBy) {
        this.deliveryAcceptedBy = deliveryAcceptedBy;
    }

    public String getRelationshipWithPatient() {
        return relationshipWithPatient;
    }

    public void setRelationshipWithPatient(String relationshipWithPatient) {
        this.relationshipWithPatient = relationshipWithPatient;
    }

    public String getDeliveryAcceptedByContactNo() {
        return deliveryAcceptedByContactNo;
    }

    public void setDeliveryAcceptedByContactNo(String deliveryAcceptedByContactNo) {
        this.deliveryAcceptedByContactNo = deliveryAcceptedByContactNo;
    }

    public String getPrimaryInsurerName() {
        return primaryInsurerName;
    }

    public void setPrimaryInsurerName(String primaryInsurerName) {
        this.primaryInsurerName = primaryInsurerName;
    }

    public String getPrimaryInsurerPolicyNo() {
        return primaryInsurerPolicyNo;
    }

    public void setPrimaryInsurerPolicyNo(String primaryInsurerPolicyNo) {
        this.primaryInsurerPolicyNo = primaryInsurerPolicyNo;
    }

    public String getPrimaryInsurerPatientIdNumber() {
        return primaryInsurerPatientIdNumber;
    }

    public void setPrimaryInsurerPatientIdNumber(String primaryInsurerPatientIdNumber) {
        this.primaryInsurerPatientIdNumber = primaryInsurerPatientIdNumber;
    }

    public String getPatientIdNo() {
        return patientIdNo;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
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

    public String getBranchFax() {
        return branchFax;
    }

    public void setBranchFax(String branchFax) {
        this.branchFax = branchFax;
    }

    public String getOrderingProviderFirstName() {
        return orderingProviderFirstName;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderMiddleName() {
        return orderingProviderMiddleName;
    }

    public void setOrderingProviderMiddleName(String orderingProviderMiddleName) {
        this.orderingProviderMiddleName = orderingProviderMiddleName;
    }

    public String getOrderingProviderLastName() {
        return orderingProviderLastName;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return orderingProviderNpi;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getOrderingProviderAddressLine1() {
        return orderingProviderAddressLine1;
    }

    public void setOrderingProviderAddressLine1(String orderingProviderAddressLine1) {
        this.orderingProviderAddressLine1 = orderingProviderAddressLine1;
    }

    public String getOrderingProviderAddressLine2() {
        return orderingProviderAddressLine2;
    }

    public void setOrderingProviderAddressLine2(String orderingProviderAddressLine2) {
        this.orderingProviderAddressLine2 = orderingProviderAddressLine2;
    }

    public String getOrderingProviderCity() {
        return orderingProviderCity;
    }

    public void setOrderingProviderCity(String orderingProviderCity) {
        this.orderingProviderCity = orderingProviderCity;
    }

    public String getOrderingProviderState() {
        return orderingProviderState;
    }

    public void setOrderingProviderState(String orderingProviderState) {
        this.orderingProviderState = orderingProviderState;
    }

    public String getOrderingProviderZip() {
        return orderingProviderZip;
    }

    public void setOrderingProviderZip(String orderingProviderZip) {
        this.orderingProviderZip = orderingProviderZip;
    }

    public String getOrderingProviderPhone1() {
        return orderingProviderPhone1;
    }

    public void setOrderingProviderPhone1(String orderingProviderPhone1) {
        this.orderingProviderPhone1 = orderingProviderPhone1;
    }

    public String getOrderingProviderPhone2() {
        return orderingProviderPhone2;
    }

    public void setOrderingProviderPhone2(String orderingProviderPhone2) {
        this.orderingProviderPhone2 = orderingProviderPhone2;
    }

    public String getOrderingProviderFax() {
        return orderingProviderFax;
    }

    public void setOrderingProviderFax(String orderingProviderFax) {
        this.orderingProviderFax = orderingProviderFax;
    }

    public String getOrderingProviderEmail() {
        return orderingProviderEmail;
    }

    public void setOrderingProviderEmail(String orderingProviderEmail) {
        this.orderingProviderEmail = orderingProviderEmail;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getPatientBranchId() {
        return patientBranchId;
    }

    public void setPatientBranchId(Long patientBranchId) {
        this.patientBranchId = patientBranchId;
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

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
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

    public UUID getDeliveryTicketUuid() {
        return deliveryTicketUuid;
    }

    public void setDeliveryTicketUuid(UUID deliveryTicketUuid) {
        this.deliveryTicketUuid = deliveryTicketUuid;
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

    public String getBillingZip() {
        return billingZip;
    }

    public void setBillingZip(String billingZip) {
        this.billingZip = billingZip;
    }

    public Long getInventoryLocationId() {
        return inventoryLocationId;
    }

    public void setInventoryLocationId(Long inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
    }

    public String getInventoryLocationName() {
        return inventoryLocationName;
    }

    public void setInventoryLocationName(String inventoryLocationName) {
        this.inventoryLocationName = inventoryLocationName;
    }

    public Long getDeliveryTicketDocumentId() {
        return deliveryTicketDocumentId;
    }

    public void setDeliveryTicketDocumentId(Long deliveryTicketDocumentId) {
        this.deliveryTicketDocumentId = deliveryTicketDocumentId;
    }

    public String getDeliveryTicketDocumentNo() {
        return deliveryTicketDocumentNo;
    }

    public void setDeliveryTicketDocumentNo(String deliveryTicketDocumentNo) {
        this.deliveryTicketDocumentNo = deliveryTicketDocumentNo;
    }

    public String getDeliveryTicketDocumentName() {
        return deliveryTicketDocumentName;
    }

    public void setDeliveryTicketDocumentName(String deliveryTicketDocumentName) {
        this.deliveryTicketDocumentName = deliveryTicketDocumentName;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(String packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getSetupMethod() {
        return setupMethod;
    }

    public void setSetupMethod(String setupMethod) {
        this.setupMethod = setupMethod;
    }

    public String getSetupTechnicianNo() {
        return setupTechnicianNo;
    }

    public void setSetupTechnicianNo(String setupTechnicianNo) {
        this.setupTechnicianNo = setupTechnicianNo;
    }

    public String getSetupTechnicianContactNo() {
        return setupTechnicianContactNo;
    }

    public void setSetupTechnicianContactNo(String setupTechnicianContactNo) {
        this.setupTechnicianContactNo = setupTechnicianContactNo;
    }

    public String getSetupTechnicianFirstName() {
        return setupTechnicianFirstName;
    }

    public void setSetupTechnicianFirstName(String setupTechnicianFirstName) {
        this.setupTechnicianFirstName = setupTechnicianFirstName;
    }

    public String getSetupTechnicianMiddleName() {
        return setupTechnicianMiddleName;
    }

    public void setSetupTechnicianMiddleName(String setupTechnicianMiddleName) {
        this.setupTechnicianMiddleName = setupTechnicianMiddleName;
    }

    public String getSetupTechnicianLastName() {
        return setupTechnicianLastName;
    }

    public void setSetupTechnicianLastName(String setupTechnicianLastName) {
        this.setupTechnicianLastName = setupTechnicianLastName;
    }

    public LocalDate getSetupDateTime() {
        return setupDateTime;
    }

    public void setSetupDateTime(LocalDate setupDateTime) {
        this.setupDateTime = setupDateTime;
    }

    public LocalDate getScheduleSetupDateTime() {
        return scheduleSetupDateTime;
    }

    public void setScheduleSetupDateTime(LocalDate scheduleSetupDateTime) {
        this.scheduleSetupDateTime = scheduleSetupDateTime;
    }

    public String getSetupComments() {
        return setupComments;
    }

    public void setSetupComments(String setupComments) {
        this.setupComments = setupComments;
    }

    public String getSetupStatus() {
        return setupStatus;
    }

    public void setSetupStatus(String setupStatus) {
        this.setupStatus = setupStatus;
    }

    public String getCourierPackageAcceptedBy() {
        return courierPackageAcceptedBy;
    }

    public void setCourierPackageAcceptedBy(String courierPackageAcceptedBy) {
        this.courierPackageAcceptedBy = courierPackageAcceptedBy;
    }

    public String getTherapistFirstName() {
        return therapistFirstName;
    }

    public void setTherapistFirstName(String therapistFirstName) {
        this.therapistFirstName = therapistFirstName;
    }

    public String getTherapistMiddleName() {
        return therapistMiddleName;
    }

    public void setTherapistMiddleName(String therapistMiddleName) {
        this.therapistMiddleName = therapistMiddleName;
    }

    public String getTherapistLastName() {
        return therapistLastName;
    }

    public void setTherapistLastName(String therapistLastName) {
        this.therapistLastName = therapistLastName;
    }

    public String getTherapistLicenseNo() {
        return therapistLicenseNo;
    }

    public void setTherapistLicenseNo(String therapistLicenseNo) {
        this.therapistLicenseNo = therapistLicenseNo;
    }

    public String getTherapistNpi() {
        return therapistNpi;
    }

    public void setTherapistNpi(String therapistNpi) {
        this.therapistNpi = therapistNpi;
    }

    public String getTherapistTaxonomyCode() {
        return therapistTaxonomyCode;
    }

    public void setTherapistTaxonomyCode(String therapistTaxonomyCode) {
        this.therapistTaxonomyCode = therapistTaxonomyCode;
    }

    public LocalDate getScheduleTherapyDate() {
        return scheduleTherapyDate;
    }

    public void setScheduleTherapyDate(LocalDate scheduleTherapyDate) {
        this.scheduleTherapyDate = scheduleTherapyDate;
    }

    public LocalDate getActualTherapyDate() {
        return actualTherapyDate;
    }

    public void setActualTherapyDate(LocalDate actualTherapyDate) {
        this.actualTherapyDate = actualTherapyDate;
    }

    public String getTherapyMode() {
        return therapyMode;
    }

    public void setTherapyMode(String therapyMode) {
        this.therapyMode = therapyMode;
    }

    public String getTherapyStatus() {
        return therapyStatus;
    }

    public void setTherapyStatus(String therapyStatus) {
        this.therapyStatus = therapyStatus;
    }

    public String getTherapyNotes() {
        return therapyNotes;
    }

    public void setTherapyNotes(String therapyNotes) {
        this.therapyNotes = therapyNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryTicketDTO)) {
            return false;
        }

        DeliveryTicketDTO deliveryTicketDTO = (DeliveryTicketDTO) o;
        if (this.deliveryTicketId == null) {
            return false;
        }
        return Objects.equals(this.deliveryTicketId, deliveryTicketDTO.deliveryTicketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.deliveryTicketId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryTicketDTO{" +
            "deliveryTicketId=" + getDeliveryTicketId() +
            ", deliveryTicketNo='" + getDeliveryTicketNo() + "'" +
            ", soId=" + getSoId() +
            ", soNo='" + getSoNo() + "'" +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientMiddleName='" + getPatientMiddleName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", gender='" + getGender() + "'" +
            ", ageAsOnDate=" + getAgeAsOnDate() +
            ", phone1='" + getPhone1() + "'" +
            ", phone2='" + getPhone2() + "'" +
            ", email='" + getEmail() + "'" +
            ", deliveryAddress1='" + getDeliveryAddress1() + "'" +
            ", deliveryAddress2='" + getDeliveryAddress2() + "'" +
            ", deliveryCity='" + getDeliveryCity() + "'" +
            ", deliveryState='" + getDeliveryState() + "'" +
            ", deliveryZip='" + getDeliveryZip() + "'" +
            ", deliveryStatus='" + getDeliveryStatus() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", deliveryPriority='" + getDeliveryPriority() + "'" +
            ", deliveryNote='" + getDeliveryNote() + "'" +
            ", deliveryComment='" + getDeliveryComment() + "'" +
            ", deliveryAcceptedBy='" + getDeliveryAcceptedBy() + "'" +
            ", relationshipWithPatient='" + getRelationshipWithPatient() + "'" +
            ", deliveryAcceptedByContactNo='" + getDeliveryAcceptedByContactNo() + "'" +
            ", primaryInsurerName='" + getPrimaryInsurerName() + "'" +
            ", primaryInsurerPolicyNo='" + getPrimaryInsurerPolicyNo() + "'" +
            ", primaryInsurerPatientIdNumber='" + getPrimaryInsurerPatientIdNumber() + "'" +
            ", patientIdNo='" + getPatientIdNo() + "'" +
            ", branchAddressLine1='" + getBranchAddressLine1() + "'" +
            ", branchAddressLine2='" + getBranchAddressLine2() + "'" +
            ", branchCity='" + getBranchCity() + "'" +
            ", branchState='" + getBranchState() + "'" +
            ", branchZipCode='" + getBranchZipCode() + "'" +
            ", branchContactNo1='" + getBranchContactNo1() + "'" +
            ", branchContactNo2='" + getBranchContactNo2() + "'" +
            ", branchNpi='" + getBranchNpi() + "'" +
            ", branchEin='" + getBranchEin() + "'" +
            ", branchFax='" + getBranchFax() + "'" +
            ", orderingProviderFirstName='" + getOrderingProviderFirstName() + "'" +
            ", orderingProviderMiddleName='" + getOrderingProviderMiddleName() + "'" +
            ", orderingProviderLastName='" + getOrderingProviderLastName() + "'" +
            ", orderingProviderNpi='" + getOrderingProviderNpi() + "'" +
            ", orderingProviderAddressLine1='" + getOrderingProviderAddressLine1() + "'" +
            ", orderingProviderAddressLine2='" + getOrderingProviderAddressLine2() + "'" +
            ", orderingProviderCity='" + getOrderingProviderCity() + "'" +
            ", orderingProviderState='" + getOrderingProviderState() + "'" +
            ", orderingProviderZip='" + getOrderingProviderZip() + "'" +
            ", orderingProviderPhone1='" + getOrderingProviderPhone1() + "'" +
            ", orderingProviderPhone2='" + getOrderingProviderPhone2() + "'" +
            ", orderingProviderFax='" + getOrderingProviderFax() + "'" +
            ", orderingProviderEmail='" + getOrderingProviderEmail() + "'" +
            ", branchName='" + getBranchName() + "'" +
            ", patientBranchId=" + getPatientBranchId() +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedBy=" + getUpdatedBy() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", deliveryTicketUuid='" + getDeliveryTicketUuid() + "'" +
            ", billingAddressLine1='" + getBillingAddressLine1() + "'" +
            ", billingAddressLine2='" + getBillingAddressLine2() + "'" +
            ", billingCity='" + getBillingCity() + "'" +
            ", billingState='" + getBillingState() + "'" +
            ", billingZip='" + getBillingZip() + "'" +
            ", inventoryLocationId=" + getInventoryLocationId() +
            ", inventoryLocationName='" + getInventoryLocationName() + "'" +
            ", deliveryTicketDocumentId=" + getDeliveryTicketDocumentId() +
            ", deliveryTicketDocumentNo='" + getDeliveryTicketDocumentNo() + "'" +
            ", deliveryTicketDocumentName='" + getDeliveryTicketDocumentName() + "'" +
            ", deliveryType='" + getDeliveryType() + "'" +
            ", carrierName='" + getCarrierName() + "'" +
            ", shippingDate='" + getShippingDate() + "'" +
            ", trackingNo='" + getTrackingNo() + "'" +
            ", referenceNo='" + getReferenceNo() + "'" +
            ", packageWeight='" + getPackageWeight() + "'" +
            ", setupMethod='" + getSetupMethod() + "'" +
            ", setupTechnicianNo='" + getSetupTechnicianNo() + "'" +
            ", setupTechnicianContactNo='" + getSetupTechnicianContactNo() + "'" +
            ", setupTechnicianFirstName='" + getSetupTechnicianFirstName() + "'" +
            ", setupTechnicianMiddleName='" + getSetupTechnicianMiddleName() + "'" +
            ", setupTechnicianLastName='" + getSetupTechnicianLastName() + "'" +
            ", setupDateTime='" + getSetupDateTime() + "'" +
            ", scheduleSetupDateTime='" + getScheduleSetupDateTime() + "'" +
            ", setupComments='" + getSetupComments() + "'" +
            ", setupStatus='" + getSetupStatus() + "'" +
            ", courierPackageAcceptedBy='" + getCourierPackageAcceptedBy() + "'" +
            ", therapistFirstName='" + getTherapistFirstName() + "'" +
            ", therapistMiddleName='" + getTherapistMiddleName() + "'" +
            ", therapistLastName='" + getTherapistLastName() + "'" +
            ", therapistLicenseNo='" + getTherapistLicenseNo() + "'" +
            ", therapistNpi='" + getTherapistNpi() + "'" +
            ", therapistTaxonomyCode='" + getTherapistTaxonomyCode() + "'" +
            ", scheduleTherapyDate='" + getScheduleTherapyDate() + "'" +
            ", actualTherapyDate='" + getActualTherapyDate() + "'" +
            ", therapyMode='" + getTherapyMode() + "'" +
            ", therapyStatus='" + getTherapyStatus() + "'" +
            ", therapyNotes='" + getTherapyNotes() + "'" +
            "}";
    }
}
