package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SalesOrderMaster.
 */
@Table("t_sales_order_master")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalesOrderMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("sales_order_id")
    private Long salesOrderId;

    @Column("sales_order_no")
    private String salesOrderNo;

    @Column("patient_id")
    private Long patientId;

    @Column("patient_first_name")
    private String patientFirstName;

    @Column("patient_middle_name")
    private String patientMiddleName;

    @Column("patient_last_name")
    private String patientLastName;

    @Column("patient_address_line_1")
    private String patientAddressLine1;

    @Column("patient_address_line_2")
    private String patientAddressLine2;

    @Column("patient_contact_no_1")
    private String patientContactNo1;

    @Column("patient_contact_no_2")
    private String patientContactNo2;

    @Column("patient_dob")
    private LocalDate patientDob;

    @Column("patient_height")
    private Double patientHeight;

    @Column("patient_weight")
    private Double patientWeight;

    @Column("patient_ssn")
    private String patientSsn;

    @Column("patient_gender")
    private String patientGender;

    @Column("hipaa_on_file_status")
    private String hipaaOnFileStatus;

    @Column("patient_branch_id")
    private Long patientBranchId;

    @Column("branch_name")
    private String branchName;

    @Column("delivery_schedule_datetime")
    private LocalDate deliveryScheduleDatetime;

    @Column("delivery_actual_datetime")
    private LocalDate deliveryActualDatetime;

    @Column("delivery_address_line_1")
    private String deliveryAddressLine1;

    @Column("delivery_address_line_2")
    private String deliveryAddressLine2;

    @Column("delivery_phone_no_1")
    private String deliveryPhoneNo1;

    @Column("delivery_phone_no_2")
    private String deliveryPhoneNo2;

    @Column("facility_id")
    private Long facilityId;

    @Column("facility_name")
    private String facilityName;

    @Column("facility_npi")
    private String facilityNpi;

    @Column("tax_zone_id")
    private Long taxZoneId;

    @Column("tax_rate")
    private Double taxRate;

    @Column("sales_order_note")
    private String salesOrderNote;

    @Column("delivery_note")
    private String deliveryNote;

    @Column("delivery_technician")
    private String deliveryTechnician;

    @Column("signature_required_status")
    private String signatureRequiredStatus;

    @Column("pod_status")
    private String podStatus;

    @Column("pod_status_datetime")
    private LocalDate podStatusDatetime;

    @Column("pod_last_message")
    private String podLastMessage;

    @Column("pod_message_datetime")
    private LocalDate podMessageDatetime;

    @Column("mutual_hold_status")
    private String mutualHoldStatus;

    @Column("hold_status")
    private String holdStatus;

    @Column("hold_reason_description")
    private String holdReasonDescription;

    @Column("stop_date")
    private LocalDate stopDate;

    @Column("stop_reason_description")
    private String stopReasonDescription;

    @Column("branch_id")
    private Long branchId;

    @Column("billing_branch_name")
    private String billingBranchName;

    @Column("inventory_location_id")
    private Long inventoryLocationId;

    @Column("order_status")
    private String orderStatus;

    @Column("order_classification_id")
    private Long orderClassificationId;

    @Column("pos_id")
    private Long posId;

    @Column("admission_date")
    private LocalDate admissionDate;

    @Column("discharge_date")
    private LocalDate dischargeDate;

    @Column("discount_percentage")
    private Long discountPercentage;

    @Column("po_number")
    private String poNumber;

    @Column("user_field_1")
    private String userField1;

    @Column("user_field_2")
    private String userField2;

    @Column("user_field_3")
    private String userField3;

    @Column("user_field_4")
    private String userField4;

    @Column("reference")
    private String reference;

    @Column("wip_status")
    private String wipStatus;

    @Column("wip_days_in_state")
    private Long wipDaysInState;

    @Column("wip_assigned_to_id")
    private Long wipAssignedToId;

    @Column("wip_date_needed")
    private LocalDate wipDateNeeded;

    @Column("completed_status")
    private String completedStatus;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("city_name")
    private String cityName;

    @Column("state_name")
    private String stateName;

    @Column("zip_code")
    private String zipCode;

    @Column("delivery_city_name")
    private String deliveryCityName;

    @Column("delivery_state_name")
    private String deliveryStateName;

    @Column("delivery_zip_code")
    private String deliveryZipCode;

    @Column("patient_dod")
    private LocalDate patientDod;

    @Column("pos_name")
    private String posName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("confirmation_by_id")
    private Long confirmationById;

    @Column("confirmation_by_name")
    private String confirmationByName;

    @Column("confirmation_date")
    private LocalDate confirmationDate;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("sales_order_master_uuid")
    private UUID salesOrderMasterUuid;

    @Column("branch_contact_person_name")
    private String branchContactPersonName;

    @Column("branch_npi")
    private String branchNpi;

    @Column("branch_ein")
    private String branchEin;

    @Column("branch_contact_no_1")
    private String branchContactNo1;

    @Column("branch_contact_no_2")
    private String branchContactNo2;

    @Column("patient_id_no")
    private String patientIdNo;

    @Column("pos_code")
    private String posCode;

    @Column("eclaim_carrier_name")
    private String eclaimCarrierName;

    @Column("plan_participation_code")
    private String planParticipationCode;

    @Column("patient_member_id")
    private String patientMemberId;

    @Column("contact_person_name")
    private String contactPersonName;

    @Column("provider_type")
    private String providerType;

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

    @Column("patient_delivery_address_line_1")
    private String patientDeliveryAddressLine1;

    @Column("patient_delivery_address_line_2")
    private String patientDeliveryAddressLine2;

    @Column("patient_delivery_city")
    private String patientDeliveryCity;

    @Column("patient_delivery_state")
    private String patientDeliveryState;

    @Column("patient_delivery_country")
    private String patientDeliveryCountry;

    @Column("patient_delivery_zip")
    private String patientDeliveryZip;

    @Column("patient_billing_address_line_1")
    private String patientBillingAddressLine1;

    @Column("patient_billing_address_line_2")
    private String patientBillingAddressLine2;

    @Column("patient_billing_city")
    private String patientBillingCity;

    @Column("patient_billing_state")
    private String patientBillingState;

    @Column("patient_billing_country")
    private String patientBillingCountry;

    @Column("patient_billing_zip")
    private String patientBillingZip;

    @Column("patient_fax")
    private String patientFax;

    @Column("branch_fax")
    private String branchFax;

    @Column("patient_email")
    private String patientEmail;

    @Column("relationship")
    private String relationship;

    @Column("mode_of_contact")
    private String modeOfContact;

    @Column("insured_first_name")
    private String insuredFirstName;

    @Column("insured_last_name")
    private String insuredLastName;

    @Column("insured_address_line_1")
    private String insuredAddressLine1;

    @Column("insured_address_line_2")
    private String insuredAddressLine2;

    @Column("insured_city")
    private String insuredCity;

    @Column("insured_state")
    private String insuredState;

    @Column("insured_zip")
    private String insuredZip;

    @Column("insured_contact_no")
    private String insuredContactNo;

    @Column("insured_gender")
    private String insuredGender;

    @Column("patient_relationship_insured")
    private String patientRelationshipInsured;

    @Column("patient_condition_employment")
    private String patientConditionEmployment;

    @Column("patient_condition_auto_accident")
    private String patientConditionAutoAccident;

    @Column("patient_condition_other_accident")
    private String patientConditionOtherAccident;

    @Column("billing_provider_taxonomy")
    private String billingProviderTaxonomy;

    @Column("billing_provider_npi")
    private String billingProviderNpi;

    @Column("billing_provider_organisation_name")
    private String billingProviderOrganisationName;

    @Column("billing_provider_address_line_1")
    private String billingProviderAddressLine1;

    @Column("billing_provider_address_line_2")
    private String billingProviderAddressLine2;

    @Column("billing_provider_city")
    private String billingProviderCity;

    @Column("billing_provider_state")
    private String billingProviderState;

    @Column("billing_provider_country")
    private String billingProviderCountry;

    @Column("billing_provider_zip_code")
    private String billingProviderZipCode;

    @Column("insured_dob")
    private LocalDate insuredDob;

    @Column("branch_country")
    private String branchCountry;

    @Column("branch_taxonomy")
    private String branchTaxonomy;

    @Column("primary_insurer_price_table_id")
    private Long primaryInsurerPriceTableId;

    @Column("primary_insurer_price_table_name")
    private String primaryInsurerPriceTableName;

    @Column("inventory_location_name")
    private String inventoryLocationName;

    @Column("so_control_number")
    private String soControlNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public SalesOrderMaster salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesOrderNo() {
        return this.salesOrderNo;
    }

    public SalesOrderMaster salesOrderNo(String salesOrderNo) {
        this.setSalesOrderNo(salesOrderNo);
        return this;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public SalesOrderMaster patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public SalesOrderMaster patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return this.patientMiddleName;
    }

    public SalesOrderMaster patientMiddleName(String patientMiddleName) {
        this.setPatientMiddleName(patientMiddleName);
        return this;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public SalesOrderMaster patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientAddressLine1() {
        return this.patientAddressLine1;
    }

    public SalesOrderMaster patientAddressLine1(String patientAddressLine1) {
        this.setPatientAddressLine1(patientAddressLine1);
        return this;
    }

    public void setPatientAddressLine1(String patientAddressLine1) {
        this.patientAddressLine1 = patientAddressLine1;
    }

    public String getPatientAddressLine2() {
        return this.patientAddressLine2;
    }

    public SalesOrderMaster patientAddressLine2(String patientAddressLine2) {
        this.setPatientAddressLine2(patientAddressLine2);
        return this;
    }

    public void setPatientAddressLine2(String patientAddressLine2) {
        this.patientAddressLine2 = patientAddressLine2;
    }

    public String getPatientContactNo1() {
        return this.patientContactNo1;
    }

    public SalesOrderMaster patientContactNo1(String patientContactNo1) {
        this.setPatientContactNo1(patientContactNo1);
        return this;
    }

    public void setPatientContactNo1(String patientContactNo1) {
        this.patientContactNo1 = patientContactNo1;
    }

    public String getPatientContactNo2() {
        return this.patientContactNo2;
    }

    public SalesOrderMaster patientContactNo2(String patientContactNo2) {
        this.setPatientContactNo2(patientContactNo2);
        return this;
    }

    public void setPatientContactNo2(String patientContactNo2) {
        this.patientContactNo2 = patientContactNo2;
    }

    public LocalDate getPatientDob() {
        return this.patientDob;
    }

    public SalesOrderMaster patientDob(LocalDate patientDob) {
        this.setPatientDob(patientDob);
        return this;
    }

    public void setPatientDob(LocalDate patientDob) {
        this.patientDob = patientDob;
    }

    public Double getPatientHeight() {
        return this.patientHeight;
    }

    public SalesOrderMaster patientHeight(Double patientHeight) {
        this.setPatientHeight(patientHeight);
        return this;
    }

    public void setPatientHeight(Double patientHeight) {
        this.patientHeight = patientHeight;
    }

    public Double getPatientWeight() {
        return this.patientWeight;
    }

    public SalesOrderMaster patientWeight(Double patientWeight) {
        this.setPatientWeight(patientWeight);
        return this;
    }

    public void setPatientWeight(Double patientWeight) {
        this.patientWeight = patientWeight;
    }

    public String getPatientSsn() {
        return this.patientSsn;
    }

    public SalesOrderMaster patientSsn(String patientSsn) {
        this.setPatientSsn(patientSsn);
        return this;
    }

    public void setPatientSsn(String patientSsn) {
        this.patientSsn = patientSsn;
    }

    public String getPatientGender() {
        return this.patientGender;
    }

    public SalesOrderMaster patientGender(String patientGender) {
        this.setPatientGender(patientGender);
        return this;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getHipaaOnFileStatus() {
        return this.hipaaOnFileStatus;
    }

    public SalesOrderMaster hipaaOnFileStatus(String hipaaOnFileStatus) {
        this.setHipaaOnFileStatus(hipaaOnFileStatus);
        return this;
    }

    public void setHipaaOnFileStatus(String hipaaOnFileStatus) {
        this.hipaaOnFileStatus = hipaaOnFileStatus;
    }

    public Long getPatientBranchId() {
        return this.patientBranchId;
    }

    public SalesOrderMaster patientBranchId(Long patientBranchId) {
        this.setPatientBranchId(patientBranchId);
        return this;
    }

    public void setPatientBranchId(Long patientBranchId) {
        this.patientBranchId = patientBranchId;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public SalesOrderMaster branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public LocalDate getDeliveryScheduleDatetime() {
        return this.deliveryScheduleDatetime;
    }

    public SalesOrderMaster deliveryScheduleDatetime(LocalDate deliveryScheduleDatetime) {
        this.setDeliveryScheduleDatetime(deliveryScheduleDatetime);
        return this;
    }

    public void setDeliveryScheduleDatetime(LocalDate deliveryScheduleDatetime) {
        this.deliveryScheduleDatetime = deliveryScheduleDatetime;
    }

    public LocalDate getDeliveryActualDatetime() {
        return this.deliveryActualDatetime;
    }

    public SalesOrderMaster deliveryActualDatetime(LocalDate deliveryActualDatetime) {
        this.setDeliveryActualDatetime(deliveryActualDatetime);
        return this;
    }

    public void setDeliveryActualDatetime(LocalDate deliveryActualDatetime) {
        this.deliveryActualDatetime = deliveryActualDatetime;
    }

    public String getDeliveryAddressLine1() {
        return this.deliveryAddressLine1;
    }

    public SalesOrderMaster deliveryAddressLine1(String deliveryAddressLine1) {
        this.setDeliveryAddressLine1(deliveryAddressLine1);
        return this;
    }

    public void setDeliveryAddressLine1(String deliveryAddressLine1) {
        this.deliveryAddressLine1 = deliveryAddressLine1;
    }

    public String getDeliveryAddressLine2() {
        return this.deliveryAddressLine2;
    }

    public SalesOrderMaster deliveryAddressLine2(String deliveryAddressLine2) {
        this.setDeliveryAddressLine2(deliveryAddressLine2);
        return this;
    }

    public void setDeliveryAddressLine2(String deliveryAddressLine2) {
        this.deliveryAddressLine2 = deliveryAddressLine2;
    }

    public String getDeliveryPhoneNo1() {
        return this.deliveryPhoneNo1;
    }

    public SalesOrderMaster deliveryPhoneNo1(String deliveryPhoneNo1) {
        this.setDeliveryPhoneNo1(deliveryPhoneNo1);
        return this;
    }

    public void setDeliveryPhoneNo1(String deliveryPhoneNo1) {
        this.deliveryPhoneNo1 = deliveryPhoneNo1;
    }

    public String getDeliveryPhoneNo2() {
        return this.deliveryPhoneNo2;
    }

    public SalesOrderMaster deliveryPhoneNo2(String deliveryPhoneNo2) {
        this.setDeliveryPhoneNo2(deliveryPhoneNo2);
        return this;
    }

    public void setDeliveryPhoneNo2(String deliveryPhoneNo2) {
        this.deliveryPhoneNo2 = deliveryPhoneNo2;
    }

    public Long getFacilityId() {
        return this.facilityId;
    }

    public SalesOrderMaster facilityId(Long facilityId) {
        this.setFacilityId(facilityId);
        return this;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return this.facilityName;
    }

    public SalesOrderMaster facilityName(String facilityName) {
        this.setFacilityName(facilityName);
        return this;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityNpi() {
        return this.facilityNpi;
    }

    public SalesOrderMaster facilityNpi(String facilityNpi) {
        this.setFacilityNpi(facilityNpi);
        return this;
    }

    public void setFacilityNpi(String facilityNpi) {
        this.facilityNpi = facilityNpi;
    }

    public Long getTaxZoneId() {
        return this.taxZoneId;
    }

    public SalesOrderMaster taxZoneId(Long taxZoneId) {
        this.setTaxZoneId(taxZoneId);
        return this;
    }

    public void setTaxZoneId(Long taxZoneId) {
        this.taxZoneId = taxZoneId;
    }

    public Double getTaxRate() {
        return this.taxRate;
    }

    public SalesOrderMaster taxRate(Double taxRate) {
        this.setTaxRate(taxRate);
        return this;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getSalesOrderNote() {
        return this.salesOrderNote;
    }

    public SalesOrderMaster salesOrderNote(String salesOrderNote) {
        this.setSalesOrderNote(salesOrderNote);
        return this;
    }

    public void setSalesOrderNote(String salesOrderNote) {
        this.salesOrderNote = salesOrderNote;
    }

    public String getDeliveryNote() {
        return this.deliveryNote;
    }

    public SalesOrderMaster deliveryNote(String deliveryNote) {
        this.setDeliveryNote(deliveryNote);
        return this;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public String getDeliveryTechnician() {
        return this.deliveryTechnician;
    }

    public SalesOrderMaster deliveryTechnician(String deliveryTechnician) {
        this.setDeliveryTechnician(deliveryTechnician);
        return this;
    }

    public void setDeliveryTechnician(String deliveryTechnician) {
        this.deliveryTechnician = deliveryTechnician;
    }

    public String getSignatureRequiredStatus() {
        return this.signatureRequiredStatus;
    }

    public SalesOrderMaster signatureRequiredStatus(String signatureRequiredStatus) {
        this.setSignatureRequiredStatus(signatureRequiredStatus);
        return this;
    }

    public void setSignatureRequiredStatus(String signatureRequiredStatus) {
        this.signatureRequiredStatus = signatureRequiredStatus;
    }

    public String getPodStatus() {
        return this.podStatus;
    }

    public SalesOrderMaster podStatus(String podStatus) {
        this.setPodStatus(podStatus);
        return this;
    }

    public void setPodStatus(String podStatus) {
        this.podStatus = podStatus;
    }

    public LocalDate getPodStatusDatetime() {
        return this.podStatusDatetime;
    }

    public SalesOrderMaster podStatusDatetime(LocalDate podStatusDatetime) {
        this.setPodStatusDatetime(podStatusDatetime);
        return this;
    }

    public void setPodStatusDatetime(LocalDate podStatusDatetime) {
        this.podStatusDatetime = podStatusDatetime;
    }

    public String getPodLastMessage() {
        return this.podLastMessage;
    }

    public SalesOrderMaster podLastMessage(String podLastMessage) {
        this.setPodLastMessage(podLastMessage);
        return this;
    }

    public void setPodLastMessage(String podLastMessage) {
        this.podLastMessage = podLastMessage;
    }

    public LocalDate getPodMessageDatetime() {
        return this.podMessageDatetime;
    }

    public SalesOrderMaster podMessageDatetime(LocalDate podMessageDatetime) {
        this.setPodMessageDatetime(podMessageDatetime);
        return this;
    }

    public void setPodMessageDatetime(LocalDate podMessageDatetime) {
        this.podMessageDatetime = podMessageDatetime;
    }

    public String getMutualHoldStatus() {
        return this.mutualHoldStatus;
    }

    public SalesOrderMaster mutualHoldStatus(String mutualHoldStatus) {
        this.setMutualHoldStatus(mutualHoldStatus);
        return this;
    }

    public void setMutualHoldStatus(String mutualHoldStatus) {
        this.mutualHoldStatus = mutualHoldStatus;
    }

    public String getHoldStatus() {
        return this.holdStatus;
    }

    public SalesOrderMaster holdStatus(String holdStatus) {
        this.setHoldStatus(holdStatus);
        return this;
    }

    public void setHoldStatus(String holdStatus) {
        this.holdStatus = holdStatus;
    }

    public String getHoldReasonDescription() {
        return this.holdReasonDescription;
    }

    public SalesOrderMaster holdReasonDescription(String holdReasonDescription) {
        this.setHoldReasonDescription(holdReasonDescription);
        return this;
    }

    public void setHoldReasonDescription(String holdReasonDescription) {
        this.holdReasonDescription = holdReasonDescription;
    }

    public LocalDate getStopDate() {
        return this.stopDate;
    }

    public SalesOrderMaster stopDate(LocalDate stopDate) {
        this.setStopDate(stopDate);
        return this;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = stopDate;
    }

    public String getStopReasonDescription() {
        return this.stopReasonDescription;
    }

    public SalesOrderMaster stopReasonDescription(String stopReasonDescription) {
        this.setStopReasonDescription(stopReasonDescription);
        return this;
    }

    public void setStopReasonDescription(String stopReasonDescription) {
        this.stopReasonDescription = stopReasonDescription;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public SalesOrderMaster branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBillingBranchName() {
        return this.billingBranchName;
    }

    public SalesOrderMaster billingBranchName(String billingBranchName) {
        this.setBillingBranchName(billingBranchName);
        return this;
    }

    public void setBillingBranchName(String billingBranchName) {
        this.billingBranchName = billingBranchName;
    }

    public Long getInventoryLocationId() {
        return this.inventoryLocationId;
    }

    public SalesOrderMaster inventoryLocationId(Long inventoryLocationId) {
        this.setInventoryLocationId(inventoryLocationId);
        return this;
    }

    public void setInventoryLocationId(Long inventoryLocationId) {
        this.inventoryLocationId = inventoryLocationId;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public SalesOrderMaster orderStatus(String orderStatus) {
        this.setOrderStatus(orderStatus);
        return this;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderClassificationId() {
        return this.orderClassificationId;
    }

    public SalesOrderMaster orderClassificationId(Long orderClassificationId) {
        this.setOrderClassificationId(orderClassificationId);
        return this;
    }

    public void setOrderClassificationId(Long orderClassificationId) {
        this.orderClassificationId = orderClassificationId;
    }

    public Long getPosId() {
        return this.posId;
    }

    public SalesOrderMaster posId(Long posId) {
        this.setPosId(posId);
        return this;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public LocalDate getAdmissionDate() {
        return this.admissionDate;
    }

    public SalesOrderMaster admissionDate(LocalDate admissionDate) {
        this.setAdmissionDate(admissionDate);
        return this;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getDischargeDate() {
        return this.dischargeDate;
    }

    public SalesOrderMaster dischargeDate(LocalDate dischargeDate) {
        this.setDischargeDate(dischargeDate);
        return this;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public Long getDiscountPercentage() {
        return this.discountPercentage;
    }

    public SalesOrderMaster discountPercentage(Long discountPercentage) {
        this.setDiscountPercentage(discountPercentage);
        return this;
    }

    public void setDiscountPercentage(Long discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getPoNumber() {
        return this.poNumber;
    }

    public SalesOrderMaster poNumber(String poNumber) {
        this.setPoNumber(poNumber);
        return this;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getUserField1() {
        return this.userField1;
    }

    public SalesOrderMaster userField1(String userField1) {
        this.setUserField1(userField1);
        return this;
    }

    public void setUserField1(String userField1) {
        this.userField1 = userField1;
    }

    public String getUserField2() {
        return this.userField2;
    }

    public SalesOrderMaster userField2(String userField2) {
        this.setUserField2(userField2);
        return this;
    }

    public void setUserField2(String userField2) {
        this.userField2 = userField2;
    }

    public String getUserField3() {
        return this.userField3;
    }

    public SalesOrderMaster userField3(String userField3) {
        this.setUserField3(userField3);
        return this;
    }

    public void setUserField3(String userField3) {
        this.userField3 = userField3;
    }

    public String getUserField4() {
        return this.userField4;
    }

    public SalesOrderMaster userField4(String userField4) {
        this.setUserField4(userField4);
        return this;
    }

    public void setUserField4(String userField4) {
        this.userField4 = userField4;
    }

    public String getReference() {
        return this.reference;
    }

    public SalesOrderMaster reference(String reference) {
        this.setReference(reference);
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getWipStatus() {
        return this.wipStatus;
    }

    public SalesOrderMaster wipStatus(String wipStatus) {
        this.setWipStatus(wipStatus);
        return this;
    }

    public void setWipStatus(String wipStatus) {
        this.wipStatus = wipStatus;
    }

    public Long getWipDaysInState() {
        return this.wipDaysInState;
    }

    public SalesOrderMaster wipDaysInState(Long wipDaysInState) {
        this.setWipDaysInState(wipDaysInState);
        return this;
    }

    public void setWipDaysInState(Long wipDaysInState) {
        this.wipDaysInState = wipDaysInState;
    }

    public Long getWipAssignedToId() {
        return this.wipAssignedToId;
    }

    public SalesOrderMaster wipAssignedToId(Long wipAssignedToId) {
        this.setWipAssignedToId(wipAssignedToId);
        return this;
    }

    public void setWipAssignedToId(Long wipAssignedToId) {
        this.wipAssignedToId = wipAssignedToId;
    }

    public LocalDate getWipDateNeeded() {
        return this.wipDateNeeded;
    }

    public SalesOrderMaster wipDateNeeded(LocalDate wipDateNeeded) {
        this.setWipDateNeeded(wipDateNeeded);
        return this;
    }

    public void setWipDateNeeded(LocalDate wipDateNeeded) {
        this.wipDateNeeded = wipDateNeeded;
    }

    public String getCompletedStatus() {
        return this.completedStatus;
    }

    public SalesOrderMaster completedStatus(String completedStatus) {
        this.setCompletedStatus(completedStatus);
        return this;
    }

    public void setCompletedStatus(String completedStatus) {
        this.completedStatus = completedStatus;
    }

    public String getStatus() {
        return this.status;
    }

    public SalesOrderMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public SalesOrderMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public SalesOrderMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCityName() {
        return this.cityName;
    }

    public SalesOrderMaster cityName(String cityName) {
        this.setCityName(cityName);
        return this;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return this.stateName;
    }

    public SalesOrderMaster stateName(String stateName) {
        this.setStateName(stateName);
        return this;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public SalesOrderMaster zipCode(String zipCode) {
        this.setZipCode(zipCode);
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDeliveryCityName() {
        return this.deliveryCityName;
    }

    public SalesOrderMaster deliveryCityName(String deliveryCityName) {
        this.setDeliveryCityName(deliveryCityName);
        return this;
    }

    public void setDeliveryCityName(String deliveryCityName) {
        this.deliveryCityName = deliveryCityName;
    }

    public String getDeliveryStateName() {
        return this.deliveryStateName;
    }

    public SalesOrderMaster deliveryStateName(String deliveryStateName) {
        this.setDeliveryStateName(deliveryStateName);
        return this;
    }

    public void setDeliveryStateName(String deliveryStateName) {
        this.deliveryStateName = deliveryStateName;
    }

    public String getDeliveryZipCode() {
        return this.deliveryZipCode;
    }

    public SalesOrderMaster deliveryZipCode(String deliveryZipCode) {
        this.setDeliveryZipCode(deliveryZipCode);
        return this;
    }

    public void setDeliveryZipCode(String deliveryZipCode) {
        this.deliveryZipCode = deliveryZipCode;
    }

    public LocalDate getPatientDod() {
        return this.patientDod;
    }

    public SalesOrderMaster patientDod(LocalDate patientDod) {
        this.setPatientDod(patientDod);
        return this;
    }

    public void setPatientDod(LocalDate patientDod) {
        this.patientDod = patientDod;
    }

    public String getPosName() {
        return this.posName;
    }

    public SalesOrderMaster posName(String posName) {
        this.setPosName(posName);
        return this;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SalesOrderMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public Long getConfirmationById() {
        return this.confirmationById;
    }

    public SalesOrderMaster confirmationById(Long confirmationById) {
        this.setConfirmationById(confirmationById);
        return this;
    }

    public void setConfirmationById(Long confirmationById) {
        this.confirmationById = confirmationById;
    }

    public String getConfirmationByName() {
        return this.confirmationByName;
    }

    public SalesOrderMaster confirmationByName(String confirmationByName) {
        this.setConfirmationByName(confirmationByName);
        return this;
    }

    public void setConfirmationByName(String confirmationByName) {
        this.confirmationByName = confirmationByName;
    }

    public LocalDate getConfirmationDate() {
        return this.confirmationDate;
    }

    public SalesOrderMaster confirmationDate(LocalDate confirmationDate) {
        this.setConfirmationDate(confirmationDate);
        return this;
    }

    public void setConfirmationDate(LocalDate confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public SalesOrderMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SalesOrderMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public SalesOrderMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getSalesOrderMasterUuid() {
        return this.salesOrderMasterUuid;
    }

    public SalesOrderMaster salesOrderMasterUuid(UUID salesOrderMasterUuid) {
        this.setSalesOrderMasterUuid(salesOrderMasterUuid);
        return this;
    }

    public void setSalesOrderMasterUuid(UUID salesOrderMasterUuid) {
        this.salesOrderMasterUuid = salesOrderMasterUuid;
    }

    public String getBranchContactPersonName() {
        return this.branchContactPersonName;
    }

    public SalesOrderMaster branchContactPersonName(String branchContactPersonName) {
        this.setBranchContactPersonName(branchContactPersonName);
        return this;
    }

    public void setBranchContactPersonName(String branchContactPersonName) {
        this.branchContactPersonName = branchContactPersonName;
    }

    public String getBranchNpi() {
        return this.branchNpi;
    }

    public SalesOrderMaster branchNpi(String branchNpi) {
        this.setBranchNpi(branchNpi);
        return this;
    }

    public void setBranchNpi(String branchNpi) {
        this.branchNpi = branchNpi;
    }

    public String getBranchEin() {
        return this.branchEin;
    }

    public SalesOrderMaster branchEin(String branchEin) {
        this.setBranchEin(branchEin);
        return this;
    }

    public void setBranchEin(String branchEin) {
        this.branchEin = branchEin;
    }

    public String getBranchContactNo1() {
        return this.branchContactNo1;
    }

    public SalesOrderMaster branchContactNo1(String branchContactNo1) {
        this.setBranchContactNo1(branchContactNo1);
        return this;
    }

    public void setBranchContactNo1(String branchContactNo1) {
        this.branchContactNo1 = branchContactNo1;
    }

    public String getBranchContactNo2() {
        return this.branchContactNo2;
    }

    public SalesOrderMaster branchContactNo2(String branchContactNo2) {
        this.setBranchContactNo2(branchContactNo2);
        return this;
    }

    public void setBranchContactNo2(String branchContactNo2) {
        this.branchContactNo2 = branchContactNo2;
    }

    public String getPatientIdNo() {
        return this.patientIdNo;
    }

    public SalesOrderMaster patientIdNo(String patientIdNo) {
        this.setPatientIdNo(patientIdNo);
        return this;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public String getPosCode() {
        return this.posCode;
    }

    public SalesOrderMaster posCode(String posCode) {
        this.setPosCode(posCode);
        return this;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getEclaimCarrierName() {
        return this.eclaimCarrierName;
    }

    public SalesOrderMaster eclaimCarrierName(String eclaimCarrierName) {
        this.setEclaimCarrierName(eclaimCarrierName);
        return this;
    }

    public void setEclaimCarrierName(String eclaimCarrierName) {
        this.eclaimCarrierName = eclaimCarrierName;
    }

    public String getPlanParticipationCode() {
        return this.planParticipationCode;
    }

    public SalesOrderMaster planParticipationCode(String planParticipationCode) {
        this.setPlanParticipationCode(planParticipationCode);
        return this;
    }

    public void setPlanParticipationCode(String planParticipationCode) {
        this.planParticipationCode = planParticipationCode;
    }

    public String getPatientMemberId() {
        return this.patientMemberId;
    }

    public SalesOrderMaster patientMemberId(String patientMemberId) {
        this.setPatientMemberId(patientMemberId);
        return this;
    }

    public void setPatientMemberId(String patientMemberId) {
        this.patientMemberId = patientMemberId;
    }

    public String getContactPersonName() {
        return this.contactPersonName;
    }

    public SalesOrderMaster contactPersonName(String contactPersonName) {
        this.setContactPersonName(contactPersonName);
        return this;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getProviderType() {
        return this.providerType;
    }

    public SalesOrderMaster providerType(String providerType) {
        this.setProviderType(providerType);
        return this;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getBranchAddressLine1() {
        return this.branchAddressLine1;
    }

    public SalesOrderMaster branchAddressLine1(String branchAddressLine1) {
        this.setBranchAddressLine1(branchAddressLine1);
        return this;
    }

    public void setBranchAddressLine1(String branchAddressLine1) {
        this.branchAddressLine1 = branchAddressLine1;
    }

    public String getBranchAddressLine2() {
        return this.branchAddressLine2;
    }

    public SalesOrderMaster branchAddressLine2(String branchAddressLine2) {
        this.setBranchAddressLine2(branchAddressLine2);
        return this;
    }

    public void setBranchAddressLine2(String branchAddressLine2) {
        this.branchAddressLine2 = branchAddressLine2;
    }

    public String getBranchCity() {
        return this.branchCity;
    }

    public SalesOrderMaster branchCity(String branchCity) {
        this.setBranchCity(branchCity);
        return this;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    public String getBranchState() {
        return this.branchState;
    }

    public SalesOrderMaster branchState(String branchState) {
        this.setBranchState(branchState);
        return this;
    }

    public void setBranchState(String branchState) {
        this.branchState = branchState;
    }

    public String getBranchZipCode() {
        return this.branchZipCode;
    }

    public SalesOrderMaster branchZipCode(String branchZipCode) {
        this.setBranchZipCode(branchZipCode);
        return this;
    }

    public void setBranchZipCode(String branchZipCode) {
        this.branchZipCode = branchZipCode;
    }

    public String getPatientDeliveryAddressLine1() {
        return this.patientDeliveryAddressLine1;
    }

    public SalesOrderMaster patientDeliveryAddressLine1(String patientDeliveryAddressLine1) {
        this.setPatientDeliveryAddressLine1(patientDeliveryAddressLine1);
        return this;
    }

    public void setPatientDeliveryAddressLine1(String patientDeliveryAddressLine1) {
        this.patientDeliveryAddressLine1 = patientDeliveryAddressLine1;
    }

    public String getPatientDeliveryAddressLine2() {
        return this.patientDeliveryAddressLine2;
    }

    public SalesOrderMaster patientDeliveryAddressLine2(String patientDeliveryAddressLine2) {
        this.setPatientDeliveryAddressLine2(patientDeliveryAddressLine2);
        return this;
    }

    public void setPatientDeliveryAddressLine2(String patientDeliveryAddressLine2) {
        this.patientDeliveryAddressLine2 = patientDeliveryAddressLine2;
    }

    public String getPatientDeliveryCity() {
        return this.patientDeliveryCity;
    }

    public SalesOrderMaster patientDeliveryCity(String patientDeliveryCity) {
        this.setPatientDeliveryCity(patientDeliveryCity);
        return this;
    }

    public void setPatientDeliveryCity(String patientDeliveryCity) {
        this.patientDeliveryCity = patientDeliveryCity;
    }

    public String getPatientDeliveryState() {
        return this.patientDeliveryState;
    }

    public SalesOrderMaster patientDeliveryState(String patientDeliveryState) {
        this.setPatientDeliveryState(patientDeliveryState);
        return this;
    }

    public void setPatientDeliveryState(String patientDeliveryState) {
        this.patientDeliveryState = patientDeliveryState;
    }

    public String getPatientDeliveryCountry() {
        return this.patientDeliveryCountry;
    }

    public SalesOrderMaster patientDeliveryCountry(String patientDeliveryCountry) {
        this.setPatientDeliveryCountry(patientDeliveryCountry);
        return this;
    }

    public void setPatientDeliveryCountry(String patientDeliveryCountry) {
        this.patientDeliveryCountry = patientDeliveryCountry;
    }

    public String getPatientDeliveryZip() {
        return this.patientDeliveryZip;
    }

    public SalesOrderMaster patientDeliveryZip(String patientDeliveryZip) {
        this.setPatientDeliveryZip(patientDeliveryZip);
        return this;
    }

    public void setPatientDeliveryZip(String patientDeliveryZip) {
        this.patientDeliveryZip = patientDeliveryZip;
    }

    public String getPatientBillingAddressLine1() {
        return this.patientBillingAddressLine1;
    }

    public SalesOrderMaster patientBillingAddressLine1(String patientBillingAddressLine1) {
        this.setPatientBillingAddressLine1(patientBillingAddressLine1);
        return this;
    }

    public void setPatientBillingAddressLine1(String patientBillingAddressLine1) {
        this.patientBillingAddressLine1 = patientBillingAddressLine1;
    }

    public String getPatientBillingAddressLine2() {
        return this.patientBillingAddressLine2;
    }

    public SalesOrderMaster patientBillingAddressLine2(String patientBillingAddressLine2) {
        this.setPatientBillingAddressLine2(patientBillingAddressLine2);
        return this;
    }

    public void setPatientBillingAddressLine2(String patientBillingAddressLine2) {
        this.patientBillingAddressLine2 = patientBillingAddressLine2;
    }

    public String getPatientBillingCity() {
        return this.patientBillingCity;
    }

    public SalesOrderMaster patientBillingCity(String patientBillingCity) {
        this.setPatientBillingCity(patientBillingCity);
        return this;
    }

    public void setPatientBillingCity(String patientBillingCity) {
        this.patientBillingCity = patientBillingCity;
    }

    public String getPatientBillingState() {
        return this.patientBillingState;
    }

    public SalesOrderMaster patientBillingState(String patientBillingState) {
        this.setPatientBillingState(patientBillingState);
        return this;
    }

    public void setPatientBillingState(String patientBillingState) {
        this.patientBillingState = patientBillingState;
    }

    public String getPatientBillingCountry() {
        return this.patientBillingCountry;
    }

    public SalesOrderMaster patientBillingCountry(String patientBillingCountry) {
        this.setPatientBillingCountry(patientBillingCountry);
        return this;
    }

    public void setPatientBillingCountry(String patientBillingCountry) {
        this.patientBillingCountry = patientBillingCountry;
    }

    public String getPatientBillingZip() {
        return this.patientBillingZip;
    }

    public SalesOrderMaster patientBillingZip(String patientBillingZip) {
        this.setPatientBillingZip(patientBillingZip);
        return this;
    }

    public void setPatientBillingZip(String patientBillingZip) {
        this.patientBillingZip = patientBillingZip;
    }

    public String getPatientFax() {
        return this.patientFax;
    }

    public SalesOrderMaster patientFax(String patientFax) {
        this.setPatientFax(patientFax);
        return this;
    }

    public void setPatientFax(String patientFax) {
        this.patientFax = patientFax;
    }

    public String getBranchFax() {
        return this.branchFax;
    }

    public SalesOrderMaster branchFax(String branchFax) {
        this.setBranchFax(branchFax);
        return this;
    }

    public void setBranchFax(String branchFax) {
        this.branchFax = branchFax;
    }

    public String getPatientEmail() {
        return this.patientEmail;
    }

    public SalesOrderMaster patientEmail(String patientEmail) {
        this.setPatientEmail(patientEmail);
        return this;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public SalesOrderMaster relationship(String relationship) {
        this.setRelationship(relationship);
        return this;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getModeOfContact() {
        return this.modeOfContact;
    }

    public SalesOrderMaster modeOfContact(String modeOfContact) {
        this.setModeOfContact(modeOfContact);
        return this;
    }

    public void setModeOfContact(String modeOfContact) {
        this.modeOfContact = modeOfContact;
    }

    public String getInsuredFirstName() {
        return this.insuredFirstName;
    }

    public SalesOrderMaster insuredFirstName(String insuredFirstName) {
        this.setInsuredFirstName(insuredFirstName);
        return this;
    }

    public void setInsuredFirstName(String insuredFirstName) {
        this.insuredFirstName = insuredFirstName;
    }

    public String getInsuredLastName() {
        return this.insuredLastName;
    }

    public SalesOrderMaster insuredLastName(String insuredLastName) {
        this.setInsuredLastName(insuredLastName);
        return this;
    }

    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }

    public String getInsuredAddressLine1() {
        return this.insuredAddressLine1;
    }

    public SalesOrderMaster insuredAddressLine1(String insuredAddressLine1) {
        this.setInsuredAddressLine1(insuredAddressLine1);
        return this;
    }

    public void setInsuredAddressLine1(String insuredAddressLine1) {
        this.insuredAddressLine1 = insuredAddressLine1;
    }

    public String getInsuredAddressLine2() {
        return this.insuredAddressLine2;
    }

    public SalesOrderMaster insuredAddressLine2(String insuredAddressLine2) {
        this.setInsuredAddressLine2(insuredAddressLine2);
        return this;
    }

    public void setInsuredAddressLine2(String insuredAddressLine2) {
        this.insuredAddressLine2 = insuredAddressLine2;
    }

    public String getInsuredCity() {
        return this.insuredCity;
    }

    public SalesOrderMaster insuredCity(String insuredCity) {
        this.setInsuredCity(insuredCity);
        return this;
    }

    public void setInsuredCity(String insuredCity) {
        this.insuredCity = insuredCity;
    }

    public String getInsuredState() {
        return this.insuredState;
    }

    public SalesOrderMaster insuredState(String insuredState) {
        this.setInsuredState(insuredState);
        return this;
    }

    public void setInsuredState(String insuredState) {
        this.insuredState = insuredState;
    }

    public String getInsuredZip() {
        return this.insuredZip;
    }

    public SalesOrderMaster insuredZip(String insuredZip) {
        this.setInsuredZip(insuredZip);
        return this;
    }

    public void setInsuredZip(String insuredZip) {
        this.insuredZip = insuredZip;
    }

    public String getInsuredContactNo() {
        return this.insuredContactNo;
    }

    public SalesOrderMaster insuredContactNo(String insuredContactNo) {
        this.setInsuredContactNo(insuredContactNo);
        return this;
    }

    public void setInsuredContactNo(String insuredContactNo) {
        this.insuredContactNo = insuredContactNo;
    }

    public String getInsuredGender() {
        return this.insuredGender;
    }

    public SalesOrderMaster insuredGender(String insuredGender) {
        this.setInsuredGender(insuredGender);
        return this;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public String getPatientRelationshipInsured() {
        return this.patientRelationshipInsured;
    }

    public SalesOrderMaster patientRelationshipInsured(String patientRelationshipInsured) {
        this.setPatientRelationshipInsured(patientRelationshipInsured);
        return this;
    }

    public void setPatientRelationshipInsured(String patientRelationshipInsured) {
        this.patientRelationshipInsured = patientRelationshipInsured;
    }

    public String getPatientConditionEmployment() {
        return this.patientConditionEmployment;
    }

    public SalesOrderMaster patientConditionEmployment(String patientConditionEmployment) {
        this.setPatientConditionEmployment(patientConditionEmployment);
        return this;
    }

    public void setPatientConditionEmployment(String patientConditionEmployment) {
        this.patientConditionEmployment = patientConditionEmployment;
    }

    public String getPatientConditionAutoAccident() {
        return this.patientConditionAutoAccident;
    }

    public SalesOrderMaster patientConditionAutoAccident(String patientConditionAutoAccident) {
        this.setPatientConditionAutoAccident(patientConditionAutoAccident);
        return this;
    }

    public void setPatientConditionAutoAccident(String patientConditionAutoAccident) {
        this.patientConditionAutoAccident = patientConditionAutoAccident;
    }

    public String getPatientConditionOtherAccident() {
        return this.patientConditionOtherAccident;
    }

    public SalesOrderMaster patientConditionOtherAccident(String patientConditionOtherAccident) {
        this.setPatientConditionOtherAccident(patientConditionOtherAccident);
        return this;
    }

    public void setPatientConditionOtherAccident(String patientConditionOtherAccident) {
        this.patientConditionOtherAccident = patientConditionOtherAccident;
    }

    public String getBillingProviderTaxonomy() {
        return this.billingProviderTaxonomy;
    }

    public SalesOrderMaster billingProviderTaxonomy(String billingProviderTaxonomy) {
        this.setBillingProviderTaxonomy(billingProviderTaxonomy);
        return this;
    }

    public void setBillingProviderTaxonomy(String billingProviderTaxonomy) {
        this.billingProviderTaxonomy = billingProviderTaxonomy;
    }

    public String getBillingProviderNpi() {
        return this.billingProviderNpi;
    }

    public SalesOrderMaster billingProviderNpi(String billingProviderNpi) {
        this.setBillingProviderNpi(billingProviderNpi);
        return this;
    }

    public void setBillingProviderNpi(String billingProviderNpi) {
        this.billingProviderNpi = billingProviderNpi;
    }

    public String getBillingProviderOrganisationName() {
        return this.billingProviderOrganisationName;
    }

    public SalesOrderMaster billingProviderOrganisationName(String billingProviderOrganisationName) {
        this.setBillingProviderOrganisationName(billingProviderOrganisationName);
        return this;
    }

    public void setBillingProviderOrganisationName(String billingProviderOrganisationName) {
        this.billingProviderOrganisationName = billingProviderOrganisationName;
    }

    public String getBillingProviderAddressLine1() {
        return this.billingProviderAddressLine1;
    }

    public SalesOrderMaster billingProviderAddressLine1(String billingProviderAddressLine1) {
        this.setBillingProviderAddressLine1(billingProviderAddressLine1);
        return this;
    }

    public void setBillingProviderAddressLine1(String billingProviderAddressLine1) {
        this.billingProviderAddressLine1 = billingProviderAddressLine1;
    }

    public String getBillingProviderAddressLine2() {
        return this.billingProviderAddressLine2;
    }

    public SalesOrderMaster billingProviderAddressLine2(String billingProviderAddressLine2) {
        this.setBillingProviderAddressLine2(billingProviderAddressLine2);
        return this;
    }

    public void setBillingProviderAddressLine2(String billingProviderAddressLine2) {
        this.billingProviderAddressLine2 = billingProviderAddressLine2;
    }

    public String getBillingProviderCity() {
        return this.billingProviderCity;
    }

    public SalesOrderMaster billingProviderCity(String billingProviderCity) {
        this.setBillingProviderCity(billingProviderCity);
        return this;
    }

    public void setBillingProviderCity(String billingProviderCity) {
        this.billingProviderCity = billingProviderCity;
    }

    public String getBillingProviderState() {
        return this.billingProviderState;
    }

    public SalesOrderMaster billingProviderState(String billingProviderState) {
        this.setBillingProviderState(billingProviderState);
        return this;
    }

    public void setBillingProviderState(String billingProviderState) {
        this.billingProviderState = billingProviderState;
    }

    public String getBillingProviderCountry() {
        return this.billingProviderCountry;
    }

    public SalesOrderMaster billingProviderCountry(String billingProviderCountry) {
        this.setBillingProviderCountry(billingProviderCountry);
        return this;
    }

    public void setBillingProviderCountry(String billingProviderCountry) {
        this.billingProviderCountry = billingProviderCountry;
    }

    public String getBillingProviderZipCode() {
        return this.billingProviderZipCode;
    }

    public SalesOrderMaster billingProviderZipCode(String billingProviderZipCode) {
        this.setBillingProviderZipCode(billingProviderZipCode);
        return this;
    }

    public void setBillingProviderZipCode(String billingProviderZipCode) {
        this.billingProviderZipCode = billingProviderZipCode;
    }

    public LocalDate getInsuredDob() {
        return this.insuredDob;
    }

    public SalesOrderMaster insuredDob(LocalDate insuredDob) {
        this.setInsuredDob(insuredDob);
        return this;
    }

    public void setInsuredDob(LocalDate insuredDob) {
        this.insuredDob = insuredDob;
    }

    public String getBranchCountry() {
        return this.branchCountry;
    }

    public SalesOrderMaster branchCountry(String branchCountry) {
        this.setBranchCountry(branchCountry);
        return this;
    }

    public void setBranchCountry(String branchCountry) {
        this.branchCountry = branchCountry;
    }

    public String getBranchTaxonomy() {
        return this.branchTaxonomy;
    }

    public SalesOrderMaster branchTaxonomy(String branchTaxonomy) {
        this.setBranchTaxonomy(branchTaxonomy);
        return this;
    }

    public void setBranchTaxonomy(String branchTaxonomy) {
        this.branchTaxonomy = branchTaxonomy;
    }

    public Long getPrimaryInsurerPriceTableId() {
        return this.primaryInsurerPriceTableId;
    }

    public SalesOrderMaster primaryInsurerPriceTableId(Long primaryInsurerPriceTableId) {
        this.setPrimaryInsurerPriceTableId(primaryInsurerPriceTableId);
        return this;
    }

    public void setPrimaryInsurerPriceTableId(Long primaryInsurerPriceTableId) {
        this.primaryInsurerPriceTableId = primaryInsurerPriceTableId;
    }

    public String getPrimaryInsurerPriceTableName() {
        return this.primaryInsurerPriceTableName;
    }

    public SalesOrderMaster primaryInsurerPriceTableName(String primaryInsurerPriceTableName) {
        this.setPrimaryInsurerPriceTableName(primaryInsurerPriceTableName);
        return this;
    }

    public void setPrimaryInsurerPriceTableName(String primaryInsurerPriceTableName) {
        this.primaryInsurerPriceTableName = primaryInsurerPriceTableName;
    }

    public String getInventoryLocationName() {
        return this.inventoryLocationName;
    }

    public SalesOrderMaster inventoryLocationName(String inventoryLocationName) {
        this.setInventoryLocationName(inventoryLocationName);
        return this;
    }

    public void setInventoryLocationName(String inventoryLocationName) {
        this.inventoryLocationName = inventoryLocationName;
    }

    public String getSoControlNumber() {
        return this.soControlNumber;
    }

    public SalesOrderMaster soControlNumber(String soControlNumber) {
        this.setSoControlNumber(soControlNumber);
        return this;
    }

    public void setSoControlNumber(String soControlNumber) {
        this.soControlNumber = soControlNumber;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderMaster)) {
            return false;
        }
        return salesOrderId != null && salesOrderId.equals(((SalesOrderMaster) o).salesOrderId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderMaster{" +
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
