package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A DeliveryTicket.
 */
@Table("t_delivery_ticket")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DeliveryTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("delivery_ticket_id")
    private Long deliveryTicketId;

    @Column("delivery_ticket_no")
    private String deliveryTicketNo;

    @Column("so_id")
    private Long soId;

    @Column("so_no")
    private String soNo;

    @Column("patient_first_name")
    private String patientFirstName;

    @Column("patient_middle_name")
    private String patientMiddleName;

    @Column("patient_last_name")
    private String patientLastName;

    @Column("gender")
    private String gender;

    @Column("age_as_on_date")
    private Integer ageAsOnDate;

    @Column("phone_1")
    private String phone1;

    @Column("phone_2")
    private String phone2;

    @Column("email")
    private String email;

    @Column("delivery_address_1")
    private String deliveryAddress1;

    @Column("delivery_address_2")
    private String deliveryAddress2;

    @Column("delivery_city")
    private String deliveryCity;

    @Column("delivery_state")
    private String deliveryState;

    @Column("delivery_zip")
    private String deliveryZip;

    @Column("delivery_status")
    private String deliveryStatus;

    @Column("delivery_date")
    private LocalDate deliveryDate;

    @Column("delivery_priority")
    private String deliveryPriority;

    @Column("delivery_note")
    private String deliveryNote;

    @Column("delivery_comment")
    private String deliveryComment;

    @Column("delivery_accepted_by")
    private String deliveryAcceptedBy;

    @Column("relationship_with_patient")
    private String relationshipWithPatient;

    @Column("delivery_accepted_by_contact_no")
    private String deliveryAcceptedByContactNo;

    @Column("primary_insurer_name")
    private String primaryInsurerName;

    @Column("primary_insurer_policy_no")
    private String primaryInsurerPolicyNo;

    @Column("primary_insurer_patient_id_number")
    private String primaryInsurerPatientIdNumber;

    @Column("patient_id_no")
    private String patientIdNo;

    @Column("branch_address_line_1")
    private String branchAddressLine1;

    @Column("branch_address_line_2")
    private String branchAddressLine2;

    @Column("branch_city")
    private String branchCity;

    @Column("branch_state")
    private String branchState;

    @Column("branch_zip_code")
    private String branchZipCode;

    @Column("branch_contact_no_1")
    private String branchContactNo1;

    @Column("branch_contact_no_2")
    private String branchContactNo2;

    @Column("branch_npi")
    private String branchNpi;

    @Column("branch_ein")
    private String branchEin;

    @Column("branch_fax")
    private String branchFax;

    @Column("ordering_provider_first_name")
    private String orderingProviderFirstName;

    @Column("ordering_provider_middle_name")
    private String orderingProviderMiddleName;

    @Column("ordering_provider_last_name")
    private String orderingProviderLastName;

    @Column("ordering_provider_npi")
    private String orderingProviderNpi;

    @Column("ordering_provider_address_line_1")
    private String orderingProviderAddressLine1;

    @Column("ordering_provider_address_line_2")
    private String orderingProviderAddressLine2;

    @Column("ordering_provider_city")
    private String orderingProviderCity;

    @Column("ordering_provider_state")
    private String orderingProviderState;

    @Column("ordering_provider_zip")
    private String orderingProviderZip;

    @Column("ordering_provider_phone_1")
    private String orderingProviderPhone1;

    @Column("ordering_provider_phone_2")
    private String orderingProviderPhone2;

    @Column("ordering_provider_fax")
    private String orderingProviderFax;

    @Column("ordering_provider_email")
    private String orderingProviderEmail;

    @Column("branch_name")
    private String branchName;

    @Column("patient_branch_id")
    private Long patientBranchId;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_by")
    private Long updatedBy;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("delivery_ticket_uuid")
    private UUID deliveryTicketUuid;

    @Column("billing_address_line_1")
    private String billingAddressLine1;

    @Column("billing_address_line_2")
    private String billingAddressLine2;

    @Column("billing_city")
    private String billingCity;

    @Column("billing_state")
    private String billingState;

    @Column("billing_zip")
    private String billingZip;

    @Column("inventory_location_id")
    private Long inventoryLocationId;

    @Column("inventory_location_name")
    private String inventoryLocationName;

    @Column("delivery_ticket_document_id")
    private Long deliveryTicketDocumentId;

    @Column("delivery_ticket_document_no")
    private String deliveryTicketDocumentNo;

    @Column("delivery_ticket_document_name")
    private String deliveryTicketDocumentName;

    @Column("delivery_type")
    private String deliveryType;

    @Column("carrier_name")
    private String carrierName;

    @Column("shipping_date")
    private LocalDate shippingDate;

    @Column("tracking_no")
    private String trackingNo;

    @Column("reference_no")
    private String referenceNo;

    @Column("package_weight")
    private String packageWeight;

    @Column("setup_method")
    private String setupMethod;

    @Column("setup_technician_no")
    private String setupTechnicianNo;

    @Column("setup_technician_contact_no")
    private String setupTechnicianContactNo;

    @Column("setup_technician_first_name")
    private String setupTechnicianFirstName;

    @Column("setup_technician_middle_name")
    private String setupTechnicianMiddleName;

    @Column("setup_technician_last_name")
    private String setupTechnicianLastName;

    @Column("setup_date_time")
    private LocalDate setupDateTime;

    @Column("schedule_setup_date_time")
    private LocalDate scheduleSetupDateTime;

    @Column("setup_comments")
    private String setupComments;

    @Column("setup_status")
    private String setupStatus;

    @Column("courier_package_accepted_by")
    private String courierPackageAcceptedBy;

    @Column("therapist_first_name")
    private String therapistFirstName;

    @Column("therapist_middle_name")
    private String therapistMiddleName;

    @Column("therapist_last_name")
    private String therapistLastName;

    @Column("therapist_license_no")
    private String therapistLicenseNo;

    @Column("therapist_npi")
    private String therapistNpi;

    @Column("therapist_taxonomy_code")
    private String therapistTaxonomyCode;

    @Column("schedule_therapy_date")
    private LocalDate scheduleTherapyDate;

    @Column("actual_therapy_date")
    private LocalDate actualTherapyDate;

    @Column("therapy_mode")
    private String therapyMode;

    @Column("therapy_status")
    private String therapyStatus;

    @Column("therapy_notes")
    private String therapyNotes;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDeliveryTicketId() {
        return this.deliveryTicketId;
    }

    public DeliveryTicket deliveryTicketId(Long deliveryTicketId) {
        this.setDeliveryTicketId(deliveryTicketId);
        return this;
    }

    public void setDeliveryTicketId(Long deliveryTicketId) {
        this.deliveryTicketId = deliveryTicketId;
    }

    public String getDeliveryTicketNo() {
        return this.deliveryTicketNo;
    }

    public DeliveryTicket deliveryTicketNo(String deliveryTicketNo) {
        this.setDeliveryTicketNo(deliveryTicketNo);
        return this;
    }

    public void setDeliveryTicketNo(String deliveryTicketNo) {
        this.deliveryTicketNo = deliveryTicketNo;
    }

    public Long getSoId() {
        return this.soId;
    }

    public DeliveryTicket soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return this.soNo;
    }

    public DeliveryTicket soNo(String soNo) {
        this.setSoNo(soNo);
        return this;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public DeliveryTicket patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return this.patientMiddleName;
    }

    public DeliveryTicket patientMiddleName(String patientMiddleName) {
        this.setPatientMiddleName(patientMiddleName);
        return this;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public DeliveryTicket patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getGender() {
        return this.gender;
    }

    public DeliveryTicket gender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAgeAsOnDate() {
        return this.ageAsOnDate;
    }

    public DeliveryTicket ageAsOnDate(Integer ageAsOnDate) {
        this.setAgeAsOnDate(ageAsOnDate);
        return this;
    }

    public void setAgeAsOnDate(Integer ageAsOnDate) {
        this.ageAsOnDate = ageAsOnDate;
    }

    public String getPhone1() {
        return this.phone1;
    }

    public DeliveryTicket phone1(String phone1) {
        this.setPhone1(phone1);
        return this;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return this.phone2;
    }

    public DeliveryTicket phone2(String phone2) {
        this.setPhone2(phone2);
        return this;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return this.email;
    }

    public DeliveryTicket email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryAddress1() {
        return this.deliveryAddress1;
    }

    public DeliveryTicket deliveryAddress1(String deliveryAddress1) {
        this.setDeliveryAddress1(deliveryAddress1);
        return this;
    }

    public void setDeliveryAddress1(String deliveryAddress1) {
        this.deliveryAddress1 = deliveryAddress1;
    }

    public String getDeliveryAddress2() {
        return this.deliveryAddress2;
    }

    public DeliveryTicket deliveryAddress2(String deliveryAddress2) {
        this.setDeliveryAddress2(deliveryAddress2);
        return this;
    }

    public void setDeliveryAddress2(String deliveryAddress2) {
        this.deliveryAddress2 = deliveryAddress2;
    }

    public String getDeliveryCity() {
        return this.deliveryCity;
    }

    public DeliveryTicket deliveryCity(String deliveryCity) {
        this.setDeliveryCity(deliveryCity);
        return this;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryState() {
        return this.deliveryState;
    }

    public DeliveryTicket deliveryState(String deliveryState) {
        this.setDeliveryState(deliveryState);
        return this;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryZip() {
        return this.deliveryZip;
    }

    public DeliveryTicket deliveryZip(String deliveryZip) {
        this.setDeliveryZip(deliveryZip);
        return this;
    }

    public void setDeliveryZip(String deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    public String getDeliveryStatus() {
        return this.deliveryStatus;
    }

    public DeliveryTicket deliveryStatus(String deliveryStatus) {
        this.setDeliveryStatus(deliveryStatus);
        return this;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public LocalDate getDeliveryDate() {
        return this.deliveryDate;
    }

    public DeliveryTicket deliveryDate(LocalDate deliveryDate) {
        this.setDeliveryDate(deliveryDate);
        return this;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryPriority() {
        return this.deliveryPriority;
    }

    public DeliveryTicket deliveryPriority(String deliveryPriority) {
        this.setDeliveryPriority(deliveryPriority);
        return this;
    }

    public void setDeliveryPriority(String deliveryPriority) {
        this.deliveryPriority = deliveryPriority;
    }

    public String getDeliveryNote() {
        return this.deliveryNote;
    }

    public DeliveryTicket deliveryNote(String deliveryNote) {
        this.setDeliveryNote(deliveryNote);
        return this;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public String getDeliveryComment() {
        return this.deliveryComment;
    }

    public DeliveryTicket deliveryComment(String deliveryComment) {
        this.setDeliveryComment(deliveryComment);
        return this;
    }

    public void setDeliveryComment(String deliveryComment) {
        this.deliveryComment = deliveryComment;
    }

    public String getDeliveryAcceptedBy() {
        return this.deliveryAcceptedBy;
    }

    public DeliveryTicket deliveryAcceptedBy(String deliveryAcceptedBy) {
        this.setDeliveryAcceptedBy(deliveryAcceptedBy);
        return this;
    }

    public void setDeliveryAcceptedBy(String deliveryAcceptedBy) {
        this.deliveryAcceptedBy = deliveryAcceptedBy;
    }

    public String getRelationshipWithPatient() {
        return this.relationshipWithPatient;
    }

    public DeliveryTicket relationshipWithPatient(String relationshipWithPatient) {
        this.setRelationshipWithPatient(relationshipWithPatient);
        return this;
    }

    public void setRelationshipWithPatient(String relationshipWithPatient) {
        this.relationshipWithPatient = relationshipWithPatient;
    }

    public String getDeliveryAcceptedByContactNo() {
        return this.deliveryAcceptedByContactNo;
    }

    public DeliveryTicket deliveryAcceptedByContactNo(String deliveryAcceptedByContactNo) {
        this.setDeliveryAcceptedByContactNo(deliveryAcceptedByContactNo);
        return this;
    }

    public void setDeliveryAcceptedByContactNo(String deliveryAcceptedByContactNo) {
        this.deliveryAcceptedByContactNo = deliveryAcceptedByContactNo;
    }

    public String getPrimaryInsurerName() {
        return this.primaryInsurerName;
    }

    public DeliveryTicket primaryInsurerName(String primaryInsurerName) {
        this.setPrimaryInsurerName(primaryInsurerName);
        return this;
    }

    public void setPrimaryInsurerName(String primaryInsurerName) {
        this.primaryInsurerName = primaryInsurerName;
    }

    public String getPrimaryInsurerPolicyNo() {
        return this.primaryInsurerPolicyNo;
    }

    public DeliveryTicket primaryInsurerPolicyNo(String primaryInsurerPolicyNo) {
        this.setPrimaryInsurerPolicyNo(primaryInsurerPolicyNo);
        return this;
    }

    public void setPrimaryInsurerPolicyNo(String primaryInsurerPolicyNo) {
        this.primaryInsurerPolicyNo = primaryInsurerPolicyNo;
    }

    public String getPrimaryInsurerPatientIdNumber() {
        return this.primaryInsurerPatientIdNumber;
    }

    public DeliveryTicket primaryInsurerPatientIdNumber(String primaryInsurerPatientIdNumber) {
        this.setPrimaryInsurerPatientIdNumber(primaryInsurerPatientIdNumber);
        return this;
    }

    public void setPrimaryInsurerPatientIdNumber(String primaryInsurerPatientIdNumber) {
        this.primaryInsurerPatientIdNumber = primaryInsurerPatientIdNumber;
    }

    public String getPatientIdNo() {
        return this.patientIdNo;
    }

    public DeliveryTicket patientIdNo(String patientIdNo) {
        this.setPatientIdNo(patientIdNo);
        return this;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public String getBranchAddressLine1() {
        return this.branchAddressLine1;
    }

    public DeliveryTicket branchAddressLine1(String branchAddressLine1) {
        this.setBranchAddressLine1(branchAddressLine1);
        return this;
    }

    public void setBranchAddressLine1(String branchAddressLine1) {
        this.branchAddressLine1 = branchAddressLine1;
    }

    public String getBranchAddressLine2() {
        return this.branchAddressLine2;
    }

    public DeliveryTicket branchAddressLine2(String branchAddressLine2) {
        this.setBranchAddressLine2(branchAddressLine2);
        return this;
    }

    public void setBranchAddressLine2(String branchAddressLine2) {
        this.branchAddressLine2 = branchAddressLine2;
    }

    public String getBranchCity() {
        return this.branchCity;
    }

    public DeliveryTicket branchCity(String branchCity) {
        this.setBranchCity(branchCity);
        return this;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    public String getBranchState() {
        return this.branchState;
    }

    public DeliveryTicket branchState(String branchState) {
        this.setBranchState(branchState);
        return this;
    }

    public void setBranchState(String branchState) {
        this.branchState = branchState;
    }

    public String getBranchZipCode() {
        return this.branchZipCode;
    }

    public DeliveryTicket branchZipCode(String branchZipCode) {
        this.setBranchZipCode(branchZipCode);
        return this;
    }

    public void setBranchZipCode(String branchZipCode) {
        this.branchZipCode = branchZipCode;
    }

    public String getBranchContactNo1() {
        return this.branchContactNo1;
    }

    public DeliveryTicket branchContactNo1(String branchContactNo1) {
        this.setBranchContactNo1(branchContactNo1);
        return this;
    }

    public void setBranchContactNo1(String branchContactNo1) {
        this.branchContactNo1 = branchContactNo1;
    }

    public String getBranchContactNo2() {
        return this.branchContactNo2;
    }

    public DeliveryTicket branchContactNo2(String branchContactNo2) {
        this.setBranchContactNo2(branchContactNo2);
        return this;
    }

    public void setBranchContactNo2(String branchContactNo2) {
        this.branchContactNo2 = branchContactNo2;
    }

    public String getBranchNpi() {
        return this.branchNpi;
    }

    public DeliveryTicket branchNpi(String branchNpi) {
        this.setBranchNpi(branchNpi);
        return this;
    }

    public void setBranchNpi(String branchNpi) {
        this.branchNpi = branchNpi;
    }

    public String getBranchEin() {
        return this.branchEin;
    }

    public DeliveryTicket branchEin(String branchEin) {
        this.setBranchEin(branchEin);
        return this;
    }

    public void setBranchEin(String branchEin) {
        this.branchEin = branchEin;
    }

    public String getBranchFax() {
        return this.branchFax;
    }

    public DeliveryTicket branchFax(String branchFax) {
        this.setBranchFax(branchFax);
        return this;
    }

    public void setBranchFax(String branchFax) {
        this.branchFax = branchFax;
    }

    public String getOrderingProviderFirstName() {
        return this.orderingProviderFirstName;
    }

    public DeliveryTicket orderingProviderFirstName(String orderingProviderFirstName) {
        this.setOrderingProviderFirstName(orderingProviderFirstName);
        return this;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderMiddleName() {
        return this.orderingProviderMiddleName;
    }

    public DeliveryTicket orderingProviderMiddleName(String orderingProviderMiddleName) {
        this.setOrderingProviderMiddleName(orderingProviderMiddleName);
        return this;
    }

    public void setOrderingProviderMiddleName(String orderingProviderMiddleName) {
        this.orderingProviderMiddleName = orderingProviderMiddleName;
    }

    public String getOrderingProviderLastName() {
        return this.orderingProviderLastName;
    }

    public DeliveryTicket orderingProviderLastName(String orderingProviderLastName) {
        this.setOrderingProviderLastName(orderingProviderLastName);
        return this;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return this.orderingProviderNpi;
    }

    public DeliveryTicket orderingProviderNpi(String orderingProviderNpi) {
        this.setOrderingProviderNpi(orderingProviderNpi);
        return this;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getOrderingProviderAddressLine1() {
        return this.orderingProviderAddressLine1;
    }

    public DeliveryTicket orderingProviderAddressLine1(String orderingProviderAddressLine1) {
        this.setOrderingProviderAddressLine1(orderingProviderAddressLine1);
        return this;
    }

    public void setOrderingProviderAddressLine1(String orderingProviderAddressLine1) {
        this.orderingProviderAddressLine1 = orderingProviderAddressLine1;
    }

    public String getOrderingProviderAddressLine2() {
        return this.orderingProviderAddressLine2;
    }

    public DeliveryTicket orderingProviderAddressLine2(String orderingProviderAddressLine2) {
        this.setOrderingProviderAddressLine2(orderingProviderAddressLine2);
        return this;
    }

    public void setOrderingProviderAddressLine2(String orderingProviderAddressLine2) {
        this.orderingProviderAddressLine2 = orderingProviderAddressLine2;
    }

    public String getOrderingProviderCity() {
        return this.orderingProviderCity;
    }

    public DeliveryTicket orderingProviderCity(String orderingProviderCity) {
        this.setOrderingProviderCity(orderingProviderCity);
        return this;
    }

    public void setOrderingProviderCity(String orderingProviderCity) {
        this.orderingProviderCity = orderingProviderCity;
    }

    public String getOrderingProviderState() {
        return this.orderingProviderState;
    }

    public DeliveryTicket orderingProviderState(String orderingProviderState) {
        this.setOrderingProviderState(orderingProviderState);
        return this;
    }

    public void setOrderingProviderState(String orderingProviderState) {
        this.orderingProviderState = orderingProviderState;
    }

    public String getOrderingProviderZip() {
        return this.orderingProviderZip;
    }

    public DeliveryTicket orderingProviderZip(String orderingProviderZip) {
        this.setOrderingProviderZip(orderingProviderZip);
        return this;
    }

    public void setOrderingProviderZip(String orderingProviderZip) {
        this.orderingProviderZip = orderingProviderZip;
    }

    public String getOrderingProviderPhone1() {
        return this.orderingProviderPhone1;
    }

    public DeliveryTicket orderingProviderPhone1(String orderingProviderPhone1) {
        this.setOrderingProviderPhone1(orderingProviderPhone1);
        return this;
    }

    public void setOrderingProviderPhone1(String orderingProviderPhone1) {
        this.orderingProviderPhone1 = orderingProviderPhone1;
    }

    public String getOrderingProviderPhone2() {
        return this.orderingProviderPhone2;
    }

    public DeliveryTicket orderingProviderPhone2(String orderingProviderPhone2) {
        this.setOrderingProviderPhone2(orderingProviderPhone2);
        return this;
    }

    public void setOrderingProviderPhone2(String orderingProviderPhone2) {
        this.orderingProviderPhone2 = orderingProviderPhone2;
    }

    public String getOrderingProviderFax() {
        return this.orderingProviderFax;
    }

    public DeliveryTicket orderingProviderFax(String orderingProviderFax) {
        this.setOrderingProviderFax(orderingProviderFax);
        return this;
    }

    public void setOrderingProviderFax(String orderingProviderFax) {
        this.orderingProviderFax = orderingProviderFax;
    }

    public String getOrderingProviderEmail() {
        return this.orderingProviderEmail;
    }

    public DeliveryTicket orderingProviderEmail(String orderingProviderEmail) {
        this.setOrderingProviderEmail(orderingProviderEmail);
        return this;
    }

    public void setOrderingProviderEmail(String orderingProviderEmail) {
        this.orderingProviderEmail = orderingProviderEmail;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public DeliveryTicket branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getPatientBranchId() {
        return this.patientBranchId;
    }

    public DeliveryTicket patientBranchId(Long patientBranchId) {
        this.setPatientBranchId(patientBranchId);
        return this;
    }

    public void setPatientBranchId(Long patientBranchId) {
        this.patientBranchId = patientBranchId;
    }

    public String getStatus() {
        return this.status;
    }

    public DeliveryTicket status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public DeliveryTicket createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public DeliveryTicket createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public DeliveryTicket createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedBy() {
        return this.updatedBy;
    }

    public DeliveryTicket updatedBy(Long updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public DeliveryTicket updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public DeliveryTicket updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getDeliveryTicketUuid() {
        return this.deliveryTicketUuid;
    }

    public DeliveryTicket deliveryTicketUuid(UUID deliveryTicketUuid) {
        this.setDeliveryTicketUuid(deliveryTicketUuid);
        return this;
    }

    public void setDeliveryTicketUuid(UUID deliveryTicketUuid) {
        this.deliveryTicketUuid = deliveryTicketUuid;
    }

    public String getBillingAddressLine1() {
        return this.billingAddressLine1;
    }

    public DeliveryTicket billingAddressLine1(String billingAddressLine1) {
        this.setBillingAddressLine1(billingAddressLine1);
        return this;
    }

    public void setBillingAddressLine1(String billingAddressLine1) {
        this.billingAddressLine1 = billingAddressLine1;
    }

    public String getBillingAddressLine2() {
        return this.billingAddressLine2;
    }

    public DeliveryTicket billingAddressLine2(String billingAddressLine2) {
        this.setBillingAddressLine2(billingAddressLine2);
        return this;
    }

    public void setBillingAddressLine2(String billingAddressLine2) {
        this.billingAddressLine2 = billingAddressLine2;
    }

    public String getBillingCity() {
        return this.billingCity;
    }

    public DeliveryTicket billingCity(String billingCity) {
        this.setBillingCity(billingCity);
        return this;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return this.billingState;
    }

    public DeliveryTicket billingState(String billingState) {
        this.setBillingState(billingState);
        return this;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingZip() {
        return this.billingZip;
    }

    public DeliveryTicket billingZip(String billingZip) {
        this.setBillingZip(billingZip);
        return this;
    }

    public void setBillingZip(String billingZip) {
        this.billingZip = billingZip;
    }

    public Long getInventoryLocationId() {
        return this.inventoryLocationId;
    }

    public DeliveryTicket inventoryLocationId(Long inventoryLocationId) {
        this.setInventoryLocationId(inventoryLocationId);
        return this;
    }

    public void setInventoryLocationId(Long inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
    }

    public String getInventoryLocationName() {
        return this.inventoryLocationName;
    }

    public DeliveryTicket inventoryLocationName(String inventoryLocationName) {
        this.setInventoryLocationName(inventoryLocationName);
        return this;
    }

    public void setInventoryLocationName(String inventoryLocationName) {
        this.inventoryLocationName = inventoryLocationName;
    }

    public Long getDeliveryTicketDocumentId() {
        return this.deliveryTicketDocumentId;
    }

    public DeliveryTicket deliveryTicketDocumentId(Long deliveryTicketDocumentId) {
        this.setDeliveryTicketDocumentId(deliveryTicketDocumentId);
        return this;
    }

    public void setDeliveryTicketDocumentId(Long deliveryTicketDocumentId) {
        this.deliveryTicketDocumentId = deliveryTicketDocumentId;
    }

    public String getDeliveryTicketDocumentNo() {
        return this.deliveryTicketDocumentNo;
    }

    public DeliveryTicket deliveryTicketDocumentNo(String deliveryTicketDocumentNo) {
        this.setDeliveryTicketDocumentNo(deliveryTicketDocumentNo);
        return this;
    }

    public void setDeliveryTicketDocumentNo(String deliveryTicketDocumentNo) {
        this.deliveryTicketDocumentNo = deliveryTicketDocumentNo;
    }

    public String getDeliveryTicketDocumentName() {
        return this.deliveryTicketDocumentName;
    }

    public DeliveryTicket deliveryTicketDocumentName(String deliveryTicketDocumentName) {
        this.setDeliveryTicketDocumentName(deliveryTicketDocumentName);
        return this;
    }

    public void setDeliveryTicketDocumentName(String deliveryTicketDocumentName) {
        this.deliveryTicketDocumentName = deliveryTicketDocumentName;
    }

    public String getDeliveryType() {
        return this.deliveryType;
    }

    public DeliveryTicket deliveryType(String deliveryType) {
        this.setDeliveryType(deliveryType);
        return this;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getCarrierName() {
        return this.carrierName;
    }

    public DeliveryTicket carrierName(String carrierName) {
        this.setCarrierName(carrierName);
        return this;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public LocalDate getShippingDate() {
        return this.shippingDate;
    }

    public DeliveryTicket shippingDate(LocalDate shippingDate) {
        this.setShippingDate(shippingDate);
        return this;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getTrackingNo() {
        return this.trackingNo;
    }

    public DeliveryTicket trackingNo(String trackingNo) {
        this.setTrackingNo(trackingNo);
        return this;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getReferenceNo() {
        return this.referenceNo;
    }

    public DeliveryTicket referenceNo(String referenceNo) {
        this.setReferenceNo(referenceNo);
        return this;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getPackageWeight() {
        return this.packageWeight;
    }

    public DeliveryTicket packageWeight(String packageWeight) {
        this.setPackageWeight(packageWeight);
        return this;
    }

    public void setPackageWeight(String packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getSetupMethod() {
        return this.setupMethod;
    }

    public DeliveryTicket setupMethod(String setupMethod) {
        this.setSetupMethod(setupMethod);
        return this;
    }

    public void setSetupMethod(String setupMethod) {
        this.setupMethod = setupMethod;
    }

    public String getSetupTechnicianNo() {
        return this.setupTechnicianNo;
    }

    public DeliveryTicket setupTechnicianNo(String setupTechnicianNo) {
        this.setSetupTechnicianNo(setupTechnicianNo);
        return this;
    }

    public void setSetupTechnicianNo(String setupTechnicianNo) {
        this.setupTechnicianNo = setupTechnicianNo;
    }

    public String getSetupTechnicianContactNo() {
        return this.setupTechnicianContactNo;
    }

    public DeliveryTicket setupTechnicianContactNo(String setupTechnicianContactNo) {
        this.setSetupTechnicianContactNo(setupTechnicianContactNo);
        return this;
    }

    public void setSetupTechnicianContactNo(String setupTechnicianContactNo) {
        this.setupTechnicianContactNo = setupTechnicianContactNo;
    }

    public String getSetupTechnicianFirstName() {
        return this.setupTechnicianFirstName;
    }

    public DeliveryTicket setupTechnicianFirstName(String setupTechnicianFirstName) {
        this.setSetupTechnicianFirstName(setupTechnicianFirstName);
        return this;
    }

    public void setSetupTechnicianFirstName(String setupTechnicianFirstName) {
        this.setupTechnicianFirstName = setupTechnicianFirstName;
    }

    public String getSetupTechnicianMiddleName() {
        return this.setupTechnicianMiddleName;
    }

    public DeliveryTicket setupTechnicianMiddleName(String setupTechnicianMiddleName) {
        this.setSetupTechnicianMiddleName(setupTechnicianMiddleName);
        return this;
    }

    public void setSetupTechnicianMiddleName(String setupTechnicianMiddleName) {
        this.setupTechnicianMiddleName = setupTechnicianMiddleName;
    }

    public String getSetupTechnicianLastName() {
        return this.setupTechnicianLastName;
    }

    public DeliveryTicket setupTechnicianLastName(String setupTechnicianLastName) {
        this.setSetupTechnicianLastName(setupTechnicianLastName);
        return this;
    }

    public void setSetupTechnicianLastName(String setupTechnicianLastName) {
        this.setupTechnicianLastName = setupTechnicianLastName;
    }

    public LocalDate getSetupDateTime() {
        return this.setupDateTime;
    }

    public DeliveryTicket setupDateTime(LocalDate setupDateTime) {
        this.setSetupDateTime(setupDateTime);
        return this;
    }

    public void setSetupDateTime(LocalDate setupDateTime) {
        this.setupDateTime = setupDateTime;
    }

    public LocalDate getScheduleSetupDateTime() {
        return this.scheduleSetupDateTime;
    }

    public DeliveryTicket scheduleSetupDateTime(LocalDate scheduleSetupDateTime) {
        this.setScheduleSetupDateTime(scheduleSetupDateTime);
        return this;
    }

    public void setScheduleSetupDateTime(LocalDate scheduleSetupDateTime) {
        this.scheduleSetupDateTime = scheduleSetupDateTime;
    }

    public String getSetupComments() {
        return this.setupComments;
    }

    public DeliveryTicket setupComments(String setupComments) {
        this.setSetupComments(setupComments);
        return this;
    }

    public void setSetupComments(String setupComments) {
        this.setupComments = setupComments;
    }

    public String getSetupStatus() {
        return this.setupStatus;
    }

    public DeliveryTicket setupStatus(String setupStatus) {
        this.setSetupStatus(setupStatus);
        return this;
    }

    public void setSetupStatus(String setupStatus) {
        this.setupStatus = setupStatus;
    }

    public String getCourierPackageAcceptedBy() {
        return this.courierPackageAcceptedBy;
    }

    public DeliveryTicket courierPackageAcceptedBy(String courierPackageAcceptedBy) {
        this.setCourierPackageAcceptedBy(courierPackageAcceptedBy);
        return this;
    }

    public void setCourierPackageAcceptedBy(String courierPackageAcceptedBy) {
        this.courierPackageAcceptedBy = courierPackageAcceptedBy;
    }

    public String getTherapistFirstName() {
        return this.therapistFirstName;
    }

    public DeliveryTicket therapistFirstName(String therapistFirstName) {
        this.setTherapistFirstName(therapistFirstName);
        return this;
    }

    public void setTherapistFirstName(String therapistFirstName) {
        this.therapistFirstName = therapistFirstName;
    }

    public String getTherapistMiddleName() {
        return this.therapistMiddleName;
    }

    public DeliveryTicket therapistMiddleName(String therapistMiddleName) {
        this.setTherapistMiddleName(therapistMiddleName);
        return this;
    }

    public void setTherapistMiddleName(String therapistMiddleName) {
        this.therapistMiddleName = therapistMiddleName;
    }

    public String getTherapistLastName() {
        return this.therapistLastName;
    }

    public DeliveryTicket therapistLastName(String therapistLastName) {
        this.setTherapistLastName(therapistLastName);
        return this;
    }

    public void setTherapistLastName(String therapistLastName) {
        this.therapistLastName = therapistLastName;
    }

    public String getTherapistLicenseNo() {
        return this.therapistLicenseNo;
    }

    public DeliveryTicket therapistLicenseNo(String therapistLicenseNo) {
        this.setTherapistLicenseNo(therapistLicenseNo);
        return this;
    }

    public void setTherapistLicenseNo(String therapistLicenseNo) {
        this.therapistLicenseNo = therapistLicenseNo;
    }

    public String getTherapistNpi() {
        return this.therapistNpi;
    }

    public DeliveryTicket therapistNpi(String therapistNpi) {
        this.setTherapistNpi(therapistNpi);
        return this;
    }

    public void setTherapistNpi(String therapistNpi) {
        this.therapistNpi = therapistNpi;
    }

    public String getTherapistTaxonomyCode() {
        return this.therapistTaxonomyCode;
    }

    public DeliveryTicket therapistTaxonomyCode(String therapistTaxonomyCode) {
        this.setTherapistTaxonomyCode(therapistTaxonomyCode);
        return this;
    }

    public void setTherapistTaxonomyCode(String therapistTaxonomyCode) {
        this.therapistTaxonomyCode = therapistTaxonomyCode;
    }

    public LocalDate getScheduleTherapyDate() {
        return this.scheduleTherapyDate;
    }

    public DeliveryTicket scheduleTherapyDate(LocalDate scheduleTherapyDate) {
        this.setScheduleTherapyDate(scheduleTherapyDate);
        return this;
    }

    public void setScheduleTherapyDate(LocalDate scheduleTherapyDate) {
        this.scheduleTherapyDate = scheduleTherapyDate;
    }

    public LocalDate getActualTherapyDate() {
        return this.actualTherapyDate;
    }

    public DeliveryTicket actualTherapyDate(LocalDate actualTherapyDate) {
        this.setActualTherapyDate(actualTherapyDate);
        return this;
    }

    public void setActualTherapyDate(LocalDate actualTherapyDate) {
        this.actualTherapyDate = actualTherapyDate;
    }

    public String getTherapyMode() {
        return this.therapyMode;
    }

    public DeliveryTicket therapyMode(String therapyMode) {
        this.setTherapyMode(therapyMode);
        return this;
    }

    public void setTherapyMode(String therapyMode) {
        this.therapyMode = therapyMode;
    }

    public String getTherapyStatus() {
        return this.therapyStatus;
    }

    public DeliveryTicket therapyStatus(String therapyStatus) {
        this.setTherapyStatus(therapyStatus);
        return this;
    }

    public void setTherapyStatus(String therapyStatus) {
        this.therapyStatus = therapyStatus;
    }

    public String getTherapyNotes() {
        return this.therapyNotes;
    }

    public DeliveryTicket therapyNotes(String therapyNotes) {
        this.setTherapyNotes(therapyNotes);
        return this;
    }

    public void setTherapyNotes(String therapyNotes) {
        this.therapyNotes = therapyNotes;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryTicket)) {
            return false;
        }
        return deliveryTicketId != null && deliveryTicketId.equals(((DeliveryTicket) o).deliveryTicketId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryTicket{" +
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
