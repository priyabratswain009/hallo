package com.sunknowledge.dme.rcm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A SecondaryClaimsSubmissionMaster.
 */
@Entity
@Table(name = "t_secondary_claim_submision_master")
public class SecondaryClaimsSubmissionMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "change_health_secondary_submision_master_id", nullable = false)
    private Long changeHealthSecondarySubmisionMasterId;

    @NotNull
    @Column(name = "sales_order_id", nullable = false)
    private Long salesOrderId;

    @NotNull
    @Column(name = "claim_control_no", nullable = false)
    private String claimControlNo;

    @NotNull
    @Column(name = "trading_partner_service_id", nullable = false)
    private String tradingPartnerServiceId;

    @NotNull
    @Column(name = "trading_partner_name", nullable = false)
    private String tradingPartnerName;

    @NotNull
    @Column(name = "submitter_organization_name", nullable = false)
    private String submitterOrganizationName;

    @NotNull
    @Column(name = "submitter_contact_person_name", nullable = false)
    private String submitterContactPersonName;

    @NotNull
    @Column(name = "submitter_contact_no", nullable = false)
    private String submitterContactNo;

    @NotNull
    @Column(name = "receiver_organization_name", nullable = false)
    private String receiverOrganizationName;

    @NotNull
    @Column(name = "subscriber_member_id_no", nullable = false)
    private String subscriberMemberIdNo;

    @NotNull
    @Column(name = "subscriber_payment_responsibility_level_code", nullable = false)
    private String subscriberPaymentResponsibilityLevelCode;

    @NotNull
    @Column(name = "subscriber_first_name", nullable = false)
    private String subscriberFirstName;

    @NotNull
    @Column(name = "subscriber_last_name", nullable = false)
    private String subscriberLastName;

    @NotNull
    @Column(name = "subscriber_gender", nullable = false)
    private String subscriberGender;

    @NotNull
    @Column(name = "subscriber_dob", nullable = false)
    private LocalDate subscriberDob;

    @Column(name = "primary_insurer_policy_no")
    private String primaryInsurerPolicyNo;

    @Column(name = "subscriber_address_line_1")
    private String subscriberAddressLine1;

    @NotNull
    @Column(name = "subscriber_city", nullable = false)
    private String subscriberCity;

    @NotNull
    @Column(name = "subscriber_state", nullable = false)
    private String subscriberState;

    @NotNull
    @Column(name = "subscriber_zip_code", nullable = false)
    private String subscriberZipCode;

    @NotNull
    @Column(name = "provider_type", nullable = false)
    private String providerType;

    @NotNull
    @Column(name = "billing_provider_npi", nullable = false)
    private String billingProviderNpi;

    @NotNull
    @Column(name = "billing_provider_ein", nullable = false)
    private String billingProviderEin;

    @NotNull
    @Column(name = "billing_provider_organization_name", nullable = false)
    private String billingProviderOrganizationName;

    @NotNull
    @Column(name = "billing_provider_address_line_1", nullable = false)
    private String billingProviderAddressLine1;

    @Column(name = "billing_provider_address_line_2")
    private String billingProviderAddressLine2;

    @NotNull
    @Column(name = "billing_provider_city", nullable = false)
    private String billingProviderCity;

    @NotNull
    @Column(name = "billing_provider_state", nullable = false)
    private String billingProviderState;

    @NotNull
    @Column(name = "billing_provider_zip_code", nullable = false)
    private String billingProviderZipCode;

    @NotNull
    @Column(name = "billing_provider_contact_person_name", nullable = false)
    private String billingProviderContactPersonName;

    @NotNull
    @Column(name = "billing_provider_contact_no", nullable = false)
    private String billingProviderContactNo;

    @NotNull
    @Column(name = "claim_filing_code", nullable = false)
    private String claimFilingCode;

    @NotNull
    @Column(name = "patient_account_no", nullable = false)
    private String patientAccountNo;

    @NotNull
    @Column(name = "claim_charge_amount", nullable = false)
    private Double claimChargeAmount;

    @NotNull
    @Column(name = "pos_code", nullable = false)
    private String posCode;

    @NotNull
    @Column(name = "claim_frequency_code", nullable = false)
    private String claimFrequencyCode;

    @NotNull
    @Column(name = "signature_indicator", nullable = false)
    private String signatureIndicator;

    @NotNull
    @Column(name = "plan_participation_code", nullable = false)
    private String planParticipationCode;

    @NotNull
    @Column(name = "benefits_assignment_certification_indicator", nullable = false)
    private String benefitsAssignmentCertificationIndicator;

    @NotNull
    @Column(name = "release_information_code", nullable = false)
    private String releaseInformationCode;

    @Column(name = "primary_diagnosis")
    private String primaryDiagnosis;

    @Column(name = "icd_10_diagnosis_code_1")
    private String icd10diagnosisCode1;

    @Column(name = "icd_10_diagnosis_code_2")
    private String icd10diagnosisCode2;

    @Column(name = "icd_10_diagnosis_code_3")
    private String icd10diagnosisCode3;

    @Column(name = "icd_10_diagnosis_code_4")
    private String icd10diagnosisCode4;

    @Column(name = "icd_10_diagnosis_code_5")
    private String icd10diagnosisCode5;

    @Column(name = "icd_10_diagnosis_code_6")
    private String icd10diagnosisCode6;

    @Column(name = "icd_10_diagnosis_code_7")
    private String icd10diagnosisCode7;

    @Column(name = "icd_10_diagnosis_code_8")
    private String icd10diagnosisCode8;

    @Column(name = "icd_10_diagnosis_code_9")
    private String icd10diagnosisCode9;

    @Column(name = "icd_10_diagnosis_code_10")
    private String icd10diagnosisCode10;

    @Column(name = "icd_10_diagnosis_code_11")
    private String icd10diagnosisCode11;

    @Column(name = "icd_10_diagnosis_code_12")
    private String icd10diagnosisCode12;

    @Column(name = "inserted_by_id")
    private Long insertedById;

    @Column(name = "inserted_date")
    private ZonedDateTime insertedDate;

    @NotNull
    @Column(name = "billing_provider_type", nullable = false)
    private String billingProviderType;

    @Column(name = "inserted_by_name")
    private String insertedByName;

    @Column(name = "status")
    private String status;

    @Column(name = "other_subscriber_address_1")
    private String otherSubscriberAddress1;

    @Column(name = "other_subscriber_address_2")
    private String otherSubscriberAddress2;

    @Column(name = "other_subscriber_city")
    private String otherSubscriberCity;

    @Column(name = "other_subscriber_state")
    private String otherSubscriberState;

    @Column(name = "other_subscriber_zip")
    private String otherSubscriberZip;

    @Column(name = "other_insured_qualifier")
    private String otherInsuredQualifier;

    @Column(name = "other_insured_last_name")
    private String otherInsuredLastName;

    @Column(name = "other_insured_first_name")
    private String otherInsuredFirstName;

    @Column(name = "other_insured_identifier_type_code")
    private String otherInsuredIdentifierTypeCode;

    @Column(name = "other_insured_identifier")
    private String otherInsuredIdentifier;

    @Column(name = "other_payer_organization_name")
    private String otherPayerOrganizationName;

    @Column(name = "other_payer_identifier_type_code")
    private String otherPayerIdentifierTypeCode;

    @Column(name = "other_payer_identifier")
    private String otherPayerIdentifier;

    @Column(name = "other_payer_adjudication_or_payment_date")
    private LocalDate otherPayerAdjudicationOrPaymentDate;

    @Column(name = "other_payer_claim_adjustment_indicator")
    private String otherPayerClaimAdjustmentIndicator;

    @Column(name = "other_payer_claim_control_number")
    private String otherPayerClaimControlNumber;

    @Column(name = "payer_paid_amount")
    private Double payerPaidAmount;

    @Column(name = "payment_responsibility_level_code")
    private String paymentResponsibilityLevelCode;

    @Column(name = "individual_relationship_code")
    private String individualRelationshipCode;

    @Column(name = "claim_filing_indicator_code")
    private String claimFilingIndicatorCode;

    @Column(name = "other_payer_benefits_assignment_certification_indicator")
    private String otherPayerBenefitsAssignmentCertificationIndicator;

    @Column(name = "release_of_information_code")
    private String releaseOfInformationCode;

    @Column(name = "remaining_patient_liability")
    private Double remainingPatientLiability;

    @Column(name = "patient_signature_generated_for_patient")
    private String patientSignatureGeneratedForPatient;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_date")
    private ZonedDateTime updatedDate;

    @Column(name = "change_health_primary_submision_master_id")
    private Long changeHealthPrimarySubmisionMasterId;

    @Column(name = "insured_first_name")
    private String insuredFirstName;

    @Column(name = "insured_last_name")
    private String insuredLastName;

    @Column(name = "insured_address_line_1")
    private String insuredAddressLine1;

    @Column(name = "insured_address_line_2")
    private String insuredAddressLine2;

    @Column(name = "insured_city")
    private String insuredCity;

    @Column(name = "insured_state")
    private String insuredState;

    @Column(name = "insured_zip")
    private String insuredZip;

    @Column(name = "insured_contact_no")
    private String insuredContactNo;

    @Column(name = "insured_dob")
    private LocalDate insuredDob;

    @Column(name = "insured_gender")
    private String insuredGender;

    @Column(name = "ordering_provider_first_name")
    private String orderingProviderFirstName;

    @Column(name = "ordering_provider_last_name")
    private String orderingProviderLastName;

    @Column(name = "ordering_provider_npi")
    private String orderingProviderNpi;

    @Column(name = "patient_relationship_insured")
    private String patientRelationshipInsured;

    @Column(name = "patient_condition_employment")
    private String patientConditionEmployment;

    @Column(name = "patient_condition_auto_accident")
    private String patientConditionAutoAccident;

    @Column(name = "patient_condition_other_accident")
    private String patientConditionOtherAccident;

    @Column(name = "is_next_level_insurer_present_status")
    private String isNextLevelInsurerPresentStatus;

    @Column(name = "original_dos")
    private LocalDate originalDos;

    @Column(name = "par_no")
    private String parNo;

    @Column(name = "billing_provider_taxonomy")
    private String billingProviderTaxonomy;

    @Column(name = "service_provider_npi")
    private String serviceProviderNpi;

    @Column(name = "service_provider_organisation_name")
    private String serviceProviderOrganisationName;

    @Column(name = "service_provider_address_line_1")
    private String serviceProviderAddressLine1;

    @Column(name = "service_provider_address_line_2")
    private String serviceProviderAddressLine2;

    @Column(name = "service_provider_city")
    private String serviceProviderCity;

    @Column(name = "service_provider_state")
    private String serviceProviderState;

    @Column(name = "service_provider_country")
    private String serviceProviderCountry;

    @Column(name = "service_provider_zip_code")
    private String serviceProviderZipCode;

    @Column(name = "service_provider_taxonomy")
    private String serviceProviderTaxonomy;

    @Column(name = "cms_1500_form_name")
    private String cms1500FormName;

    @Column(name = "trading_partner_address_line_1")
    private String tradingPartnerAddressLine1;

    @Column(name = "trading_partner_address_line_2")
    private String tradingPartnerAddressLine2;

    @Column(name = "trading_partner_city")
    private String tradingPartnerCity;

    @Column(name = "trading_partner_state")
    private String tradingPartnerState;

    @Column(name = "trading_partner_zip")
    private String tradingPartnerZip;

    @Column(name = "diagnosis_code_type")
    private String diagnosisCodeType;

    @OneToMany(mappedBy = "secondaryClaimsSubmissionMaster")
    @JsonIgnoreProperties(value = { "secondaryClaimsSubmissionMaster" }, allowSetters = true)
    private Set<SecondaryServiceLinesMaster> secondaryserviceLines = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getChangeHealthSecondarySubmisionMasterId() {
        return this.changeHealthSecondarySubmisionMasterId;
    }

    public SecondaryClaimsSubmissionMaster changeHealthSecondarySubmisionMasterId(Long changeHealthSecondarySubmisionMasterId) {
        this.setChangeHealthSecondarySubmisionMasterId(changeHealthSecondarySubmisionMasterId);
        return this;
    }

    public void setChangeHealthSecondarySubmisionMasterId(Long changeHealthSecondarySubmisionMasterId) {
        this.changeHealthSecondarySubmisionMasterId = changeHealthSecondarySubmisionMasterId;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public SecondaryClaimsSubmissionMaster salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getClaimControlNo() {
        return this.claimControlNo;
    }

    public SecondaryClaimsSubmissionMaster claimControlNo(String claimControlNo) {
        this.setClaimControlNo(claimControlNo);
        return this;
    }

    public void setClaimControlNo(String claimControlNo) {
        this.claimControlNo = claimControlNo;
    }

    public String getTradingPartnerServiceId() {
        return this.tradingPartnerServiceId;
    }

    public SecondaryClaimsSubmissionMaster tradingPartnerServiceId(String tradingPartnerServiceId) {
        this.setTradingPartnerServiceId(tradingPartnerServiceId);
        return this;
    }

    public void setTradingPartnerServiceId(String tradingPartnerServiceId) {
        this.tradingPartnerServiceId = tradingPartnerServiceId;
    }

    public String getTradingPartnerName() {
        return this.tradingPartnerName;
    }

    public SecondaryClaimsSubmissionMaster tradingPartnerName(String tradingPartnerName) {
        this.setTradingPartnerName(tradingPartnerName);
        return this;
    }

    public void setTradingPartnerName(String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    public String getSubmitterOrganizationName() {
        return this.submitterOrganizationName;
    }

    public SecondaryClaimsSubmissionMaster submitterOrganizationName(String submitterOrganizationName) {
        this.setSubmitterOrganizationName(submitterOrganizationName);
        return this;
    }

    public void setSubmitterOrganizationName(String submitterOrganizationName) {
        this.submitterOrganizationName = submitterOrganizationName;
    }

    public String getSubmitterContactPersonName() {
        return this.submitterContactPersonName;
    }

    public SecondaryClaimsSubmissionMaster submitterContactPersonName(String submitterContactPersonName) {
        this.setSubmitterContactPersonName(submitterContactPersonName);
        return this;
    }

    public void setSubmitterContactPersonName(String submitterContactPersonName) {
        this.submitterContactPersonName = submitterContactPersonName;
    }

    public String getSubmitterContactNo() {
        return this.submitterContactNo;
    }

    public SecondaryClaimsSubmissionMaster submitterContactNo(String submitterContactNo) {
        this.setSubmitterContactNo(submitterContactNo);
        return this;
    }

    public void setSubmitterContactNo(String submitterContactNo) {
        this.submitterContactNo = submitterContactNo;
    }

    public String getReceiverOrganizationName() {
        return this.receiverOrganizationName;
    }

    public SecondaryClaimsSubmissionMaster receiverOrganizationName(String receiverOrganizationName) {
        this.setReceiverOrganizationName(receiverOrganizationName);
        return this;
    }

    public void setReceiverOrganizationName(String receiverOrganizationName) {
        this.receiverOrganizationName = receiverOrganizationName;
    }

    public String getSubscriberMemberIdNo() {
        return this.subscriberMemberIdNo;
    }

    public SecondaryClaimsSubmissionMaster subscriberMemberIdNo(String subscriberMemberIdNo) {
        this.setSubscriberMemberIdNo(subscriberMemberIdNo);
        return this;
    }

    public void setSubscriberMemberIdNo(String subscriberMemberIdNo) {
        this.subscriberMemberIdNo = subscriberMemberIdNo;
    }

    public String getSubscriberPaymentResponsibilityLevelCode() {
        return this.subscriberPaymentResponsibilityLevelCode;
    }

    public SecondaryClaimsSubmissionMaster subscriberPaymentResponsibilityLevelCode(String subscriberPaymentResponsibilityLevelCode) {
        this.setSubscriberPaymentResponsibilityLevelCode(subscriberPaymentResponsibilityLevelCode);
        return this;
    }

    public void setSubscriberPaymentResponsibilityLevelCode(String subscriberPaymentResponsibilityLevelCode) {
        this.subscriberPaymentResponsibilityLevelCode = subscriberPaymentResponsibilityLevelCode;
    }

    public String getSubscriberFirstName() {
        return this.subscriberFirstName;
    }

    public SecondaryClaimsSubmissionMaster subscriberFirstName(String subscriberFirstName) {
        this.setSubscriberFirstName(subscriberFirstName);
        return this;
    }

    public void setSubscriberFirstName(String subscriberFirstName) {
        this.subscriberFirstName = subscriberFirstName;
    }

    public String getSubscriberLastName() {
        return this.subscriberLastName;
    }

    public SecondaryClaimsSubmissionMaster subscriberLastName(String subscriberLastName) {
        this.setSubscriberLastName(subscriberLastName);
        return this;
    }

    public void setSubscriberLastName(String subscriberLastName) {
        this.subscriberLastName = subscriberLastName;
    }

    public String getSubscriberGender() {
        return this.subscriberGender;
    }

    public SecondaryClaimsSubmissionMaster subscriberGender(String subscriberGender) {
        this.setSubscriberGender(subscriberGender);
        return this;
    }

    public void setSubscriberGender(String subscriberGender) {
        this.subscriberGender = subscriberGender;
    }

    public LocalDate getSubscriberDob() {
        return this.subscriberDob;
    }

    public SecondaryClaimsSubmissionMaster subscriberDob(LocalDate subscriberDob) {
        this.setSubscriberDob(subscriberDob);
        return this;
    }

    public void setSubscriberDob(LocalDate subscriberDob) {
        this.subscriberDob = subscriberDob;
    }

    public String getPrimaryInsurerPolicyNo() {
        return this.primaryInsurerPolicyNo;
    }

    public SecondaryClaimsSubmissionMaster primaryInsurerPolicyNo(String primaryInsurerPolicyNo) {
        this.setPrimaryInsurerPolicyNo(primaryInsurerPolicyNo);
        return this;
    }

    public void setPrimaryInsurerPolicyNo(String primaryInsurerPolicyNo) {
        this.primaryInsurerPolicyNo = primaryInsurerPolicyNo;
    }

    public String getSubscriberAddressLine1() {
        return this.subscriberAddressLine1;
    }

    public SecondaryClaimsSubmissionMaster subscriberAddressLine1(String subscriberAddressLine1) {
        this.setSubscriberAddressLine1(subscriberAddressLine1);
        return this;
    }

    public void setSubscriberAddressLine1(String subscriberAddressLine1) {
        this.subscriberAddressLine1 = subscriberAddressLine1;
    }

    public String getSubscriberCity() {
        return this.subscriberCity;
    }

    public SecondaryClaimsSubmissionMaster subscriberCity(String subscriberCity) {
        this.setSubscriberCity(subscriberCity);
        return this;
    }

    public void setSubscriberCity(String subscriberCity) {
        this.subscriberCity = subscriberCity;
    }

    public String getSubscriberState() {
        return this.subscriberState;
    }

    public SecondaryClaimsSubmissionMaster subscriberState(String subscriberState) {
        this.setSubscriberState(subscriberState);
        return this;
    }

    public void setSubscriberState(String subscriberState) {
        this.subscriberState = subscriberState;
    }

    public String getSubscriberZipCode() {
        return this.subscriberZipCode;
    }

    public SecondaryClaimsSubmissionMaster subscriberZipCode(String subscriberZipCode) {
        this.setSubscriberZipCode(subscriberZipCode);
        return this;
    }

    public void setSubscriberZipCode(String subscriberZipCode) {
        this.subscriberZipCode = subscriberZipCode;
    }

    public String getProviderType() {
        return this.providerType;
    }

    public SecondaryClaimsSubmissionMaster providerType(String providerType) {
        this.setProviderType(providerType);
        return this;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getBillingProviderNpi() {
        return this.billingProviderNpi;
    }

    public SecondaryClaimsSubmissionMaster billingProviderNpi(String billingProviderNpi) {
        this.setBillingProviderNpi(billingProviderNpi);
        return this;
    }

    public void setBillingProviderNpi(String billingProviderNpi) {
        this.billingProviderNpi = billingProviderNpi;
    }

    public String getBillingProviderEin() {
        return this.billingProviderEin;
    }

    public SecondaryClaimsSubmissionMaster billingProviderEin(String billingProviderEin) {
        this.setBillingProviderEin(billingProviderEin);
        return this;
    }

    public void setBillingProviderEin(String billingProviderEin) {
        this.billingProviderEin = billingProviderEin;
    }

    public String getBillingProviderOrganizationName() {
        return this.billingProviderOrganizationName;
    }

    public SecondaryClaimsSubmissionMaster billingProviderOrganizationName(String billingProviderOrganizationName) {
        this.setBillingProviderOrganizationName(billingProviderOrganizationName);
        return this;
    }

    public void setBillingProviderOrganizationName(String billingProviderOrganizationName) {
        this.billingProviderOrganizationName = billingProviderOrganizationName;
    }

    public String getBillingProviderAddressLine1() {
        return this.billingProviderAddressLine1;
    }

    public SecondaryClaimsSubmissionMaster billingProviderAddressLine1(String billingProviderAddressLine1) {
        this.setBillingProviderAddressLine1(billingProviderAddressLine1);
        return this;
    }

    public void setBillingProviderAddressLine1(String billingProviderAddressLine1) {
        this.billingProviderAddressLine1 = billingProviderAddressLine1;
    }

    public String getBillingProviderAddressLine2() {
        return this.billingProviderAddressLine2;
    }

    public SecondaryClaimsSubmissionMaster billingProviderAddressLine2(String billingProviderAddressLine2) {
        this.setBillingProviderAddressLine2(billingProviderAddressLine2);
        return this;
    }

    public void setBillingProviderAddressLine2(String billingProviderAddressLine2) {
        this.billingProviderAddressLine2 = billingProviderAddressLine2;
    }

    public String getBillingProviderCity() {
        return this.billingProviderCity;
    }

    public SecondaryClaimsSubmissionMaster billingProviderCity(String billingProviderCity) {
        this.setBillingProviderCity(billingProviderCity);
        return this;
    }

    public void setBillingProviderCity(String billingProviderCity) {
        this.billingProviderCity = billingProviderCity;
    }

    public String getBillingProviderState() {
        return this.billingProviderState;
    }

    public SecondaryClaimsSubmissionMaster billingProviderState(String billingProviderState) {
        this.setBillingProviderState(billingProviderState);
        return this;
    }

    public void setBillingProviderState(String billingProviderState) {
        this.billingProviderState = billingProviderState;
    }

    public String getBillingProviderZipCode() {
        return this.billingProviderZipCode;
    }

    public SecondaryClaimsSubmissionMaster billingProviderZipCode(String billingProviderZipCode) {
        this.setBillingProviderZipCode(billingProviderZipCode);
        return this;
    }

    public void setBillingProviderZipCode(String billingProviderZipCode) {
        this.billingProviderZipCode = billingProviderZipCode;
    }

    public String getBillingProviderContactPersonName() {
        return this.billingProviderContactPersonName;
    }

    public SecondaryClaimsSubmissionMaster billingProviderContactPersonName(String billingProviderContactPersonName) {
        this.setBillingProviderContactPersonName(billingProviderContactPersonName);
        return this;
    }

    public void setBillingProviderContactPersonName(String billingProviderContactPersonName) {
        this.billingProviderContactPersonName = billingProviderContactPersonName;
    }

    public String getBillingProviderContactNo() {
        return this.billingProviderContactNo;
    }

    public SecondaryClaimsSubmissionMaster billingProviderContactNo(String billingProviderContactNo) {
        this.setBillingProviderContactNo(billingProviderContactNo);
        return this;
    }

    public void setBillingProviderContactNo(String billingProviderContactNo) {
        this.billingProviderContactNo = billingProviderContactNo;
    }

    public String getClaimFilingCode() {
        return this.claimFilingCode;
    }

    public SecondaryClaimsSubmissionMaster claimFilingCode(String claimFilingCode) {
        this.setClaimFilingCode(claimFilingCode);
        return this;
    }

    public void setClaimFilingCode(String claimFilingCode) {
        this.claimFilingCode = claimFilingCode;
    }

    public String getPatientAccountNo() {
        return this.patientAccountNo;
    }

    public SecondaryClaimsSubmissionMaster patientAccountNo(String patientAccountNo) {
        this.setPatientAccountNo(patientAccountNo);
        return this;
    }

    public void setPatientAccountNo(String patientAccountNo) {
        this.patientAccountNo = patientAccountNo;
    }

    public Double getClaimChargeAmount() {
        return this.claimChargeAmount;
    }

    public SecondaryClaimsSubmissionMaster claimChargeAmount(Double claimChargeAmount) {
        this.setClaimChargeAmount(claimChargeAmount);
        return this;
    }

    public void setClaimChargeAmount(Double claimChargeAmount) {
        this.claimChargeAmount = claimChargeAmount;
    }

    public String getPosCode() {
        return this.posCode;
    }

    public SecondaryClaimsSubmissionMaster posCode(String posCode) {
        this.setPosCode(posCode);
        return this;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getClaimFrequencyCode() {
        return this.claimFrequencyCode;
    }

    public SecondaryClaimsSubmissionMaster claimFrequencyCode(String claimFrequencyCode) {
        this.setClaimFrequencyCode(claimFrequencyCode);
        return this;
    }

    public void setClaimFrequencyCode(String claimFrequencyCode) {
        this.claimFrequencyCode = claimFrequencyCode;
    }

    public String getSignatureIndicator() {
        return this.signatureIndicator;
    }

    public SecondaryClaimsSubmissionMaster signatureIndicator(String signatureIndicator) {
        this.setSignatureIndicator(signatureIndicator);
        return this;
    }

    public void setSignatureIndicator(String signatureIndicator) {
        this.signatureIndicator = signatureIndicator;
    }

    public String getPlanParticipationCode() {
        return this.planParticipationCode;
    }

    public SecondaryClaimsSubmissionMaster planParticipationCode(String planParticipationCode) {
        this.setPlanParticipationCode(planParticipationCode);
        return this;
    }

    public void setPlanParticipationCode(String planParticipationCode) {
        this.planParticipationCode = planParticipationCode;
    }

    public String getBenefitsAssignmentCertificationIndicator() {
        return this.benefitsAssignmentCertificationIndicator;
    }

    public SecondaryClaimsSubmissionMaster benefitsAssignmentCertificationIndicator(String benefitsAssignmentCertificationIndicator) {
        this.setBenefitsAssignmentCertificationIndicator(benefitsAssignmentCertificationIndicator);
        return this;
    }

    public void setBenefitsAssignmentCertificationIndicator(String benefitsAssignmentCertificationIndicator) {
        this.benefitsAssignmentCertificationIndicator = benefitsAssignmentCertificationIndicator;
    }

    public String getReleaseInformationCode() {
        return this.releaseInformationCode;
    }

    public SecondaryClaimsSubmissionMaster releaseInformationCode(String releaseInformationCode) {
        this.setReleaseInformationCode(releaseInformationCode);
        return this;
    }

    public void setReleaseInformationCode(String releaseInformationCode) {
        this.releaseInformationCode = releaseInformationCode;
    }

    public String getPrimaryDiagnosis() {
        return this.primaryDiagnosis;
    }

    public SecondaryClaimsSubmissionMaster primaryDiagnosis(String primaryDiagnosis) {
        this.setPrimaryDiagnosis(primaryDiagnosis);
        return this;
    }

    public void setPrimaryDiagnosis(String primaryDiagnosis) {
        this.primaryDiagnosis = primaryDiagnosis;
    }

    public String getIcd10diagnosisCode1() {
        return this.icd10diagnosisCode1;
    }

    public SecondaryClaimsSubmissionMaster icd10diagnosisCode1(String icd10diagnosisCode1) {
        this.setIcd10diagnosisCode1(icd10diagnosisCode1);
        return this;
    }

    public void setIcd10diagnosisCode1(String icd10diagnosisCode1) {
        this.icd10diagnosisCode1 = icd10diagnosisCode1;
    }

    public String getIcd10diagnosisCode2() {
        return this.icd10diagnosisCode2;
    }

    public SecondaryClaimsSubmissionMaster icd10diagnosisCode2(String icd10diagnosisCode2) {
        this.setIcd10diagnosisCode2(icd10diagnosisCode2);
        return this;
    }

    public void setIcd10diagnosisCode2(String icd10diagnosisCode2) {
        this.icd10diagnosisCode2 = icd10diagnosisCode2;
    }

    public String getIcd10diagnosisCode3() {
        return this.icd10diagnosisCode3;
    }

    public SecondaryClaimsSubmissionMaster icd10diagnosisCode3(String icd10diagnosisCode3) {
        this.setIcd10diagnosisCode3(icd10diagnosisCode3);
        return this;
    }

    public void setIcd10diagnosisCode3(String icd10diagnosisCode3) {
        this.icd10diagnosisCode3 = icd10diagnosisCode3;
    }

    public String getIcd10diagnosisCode4() {
        return this.icd10diagnosisCode4;
    }

    public SecondaryClaimsSubmissionMaster icd10diagnosisCode4(String icd10diagnosisCode4) {
        this.setIcd10diagnosisCode4(icd10diagnosisCode4);
        return this;
    }

    public void setIcd10diagnosisCode4(String icd10diagnosisCode4) {
        this.icd10diagnosisCode4 = icd10diagnosisCode4;
    }

    public String getIcd10diagnosisCode5() {
        return this.icd10diagnosisCode5;
    }

    public SecondaryClaimsSubmissionMaster icd10diagnosisCode5(String icd10diagnosisCode5) {
        this.setIcd10diagnosisCode5(icd10diagnosisCode5);
        return this;
    }

    public void setIcd10diagnosisCode5(String icd10diagnosisCode5) {
        this.icd10diagnosisCode5 = icd10diagnosisCode5;
    }

    public String getIcd10diagnosisCode6() {
        return this.icd10diagnosisCode6;
    }

    public SecondaryClaimsSubmissionMaster icd10diagnosisCode6(String icd10diagnosisCode6) {
        this.setIcd10diagnosisCode6(icd10diagnosisCode6);
        return this;
    }

    public void setIcd10diagnosisCode6(String icd10diagnosisCode6) {
        this.icd10diagnosisCode6 = icd10diagnosisCode6;
    }

    public String getIcd10diagnosisCode7() {
        return this.icd10diagnosisCode7;
    }

    public SecondaryClaimsSubmissionMaster icd10diagnosisCode7(String icd10diagnosisCode7) {
        this.setIcd10diagnosisCode7(icd10diagnosisCode7);
        return this;
    }

    public void setIcd10diagnosisCode7(String icd10diagnosisCode7) {
        this.icd10diagnosisCode7 = icd10diagnosisCode7;
    }

    public String getIcd10diagnosisCode8() {
        return this.icd10diagnosisCode8;
    }

    public SecondaryClaimsSubmissionMaster icd10diagnosisCode8(String icd10diagnosisCode8) {
        this.setIcd10diagnosisCode8(icd10diagnosisCode8);
        return this;
    }

    public void setIcd10diagnosisCode8(String icd10diagnosisCode8) {
        this.icd10diagnosisCode8 = icd10diagnosisCode8;
    }

    public String getIcd10diagnosisCode9() {
        return this.icd10diagnosisCode9;
    }

    public SecondaryClaimsSubmissionMaster icd10diagnosisCode9(String icd10diagnosisCode9) {
        this.setIcd10diagnosisCode9(icd10diagnosisCode9);
        return this;
    }

    public void setIcd10diagnosisCode9(String icd10diagnosisCode9) {
        this.icd10diagnosisCode9 = icd10diagnosisCode9;
    }

    public String getIcd10diagnosisCode10() {
        return this.icd10diagnosisCode10;
    }

    public SecondaryClaimsSubmissionMaster icd10diagnosisCode10(String icd10diagnosisCode10) {
        this.setIcd10diagnosisCode10(icd10diagnosisCode10);
        return this;
    }

    public void setIcd10diagnosisCode10(String icd10diagnosisCode10) {
        this.icd10diagnosisCode10 = icd10diagnosisCode10;
    }

    public String getIcd10diagnosisCode11() {
        return this.icd10diagnosisCode11;
    }

    public SecondaryClaimsSubmissionMaster icd10diagnosisCode11(String icd10diagnosisCode11) {
        this.setIcd10diagnosisCode11(icd10diagnosisCode11);
        return this;
    }

    public void setIcd10diagnosisCode11(String icd10diagnosisCode11) {
        this.icd10diagnosisCode11 = icd10diagnosisCode11;
    }

    public String getIcd10diagnosisCode12() {
        return this.icd10diagnosisCode12;
    }

    public SecondaryClaimsSubmissionMaster icd10diagnosisCode12(String icd10diagnosisCode12) {
        this.setIcd10diagnosisCode12(icd10diagnosisCode12);
        return this;
    }

    public void setIcd10diagnosisCode12(String icd10diagnosisCode12) {
        this.icd10diagnosisCode12 = icd10diagnosisCode12;
    }

    public Long getInsertedById() {
        return this.insertedById;
    }

    public SecondaryClaimsSubmissionMaster insertedById(Long insertedById) {
        this.setInsertedById(insertedById);
        return this;
    }

    public void setInsertedById(Long insertedById) {
        this.insertedById = insertedById;
    }

    public ZonedDateTime getInsertedDate() {
        return this.insertedDate;
    }

    public SecondaryClaimsSubmissionMaster insertedDate(ZonedDateTime insertedDate) {
        this.setInsertedDate(insertedDate);
        return this;
    }

    public void setInsertedDate(ZonedDateTime insertedDate) {
        this.insertedDate = insertedDate;
    }

    public String getBillingProviderType() {
        return this.billingProviderType;
    }

    public SecondaryClaimsSubmissionMaster billingProviderType(String billingProviderType) {
        this.setBillingProviderType(billingProviderType);
        return this;
    }

    public void setBillingProviderType(String billingProviderType) {
        this.billingProviderType = billingProviderType;
    }

    public String getInsertedByName() {
        return this.insertedByName;
    }

    public SecondaryClaimsSubmissionMaster insertedByName(String insertedByName) {
        this.setInsertedByName(insertedByName);
        return this;
    }

    public void setInsertedByName(String insertedByName) {
        this.insertedByName = insertedByName;
    }

    public String getStatus() {
        return this.status;
    }

    public SecondaryClaimsSubmissionMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtherSubscriberAddress1() {
        return this.otherSubscriberAddress1;
    }

    public SecondaryClaimsSubmissionMaster otherSubscriberAddress1(String otherSubscriberAddress1) {
        this.setOtherSubscriberAddress1(otherSubscriberAddress1);
        return this;
    }

    public void setOtherSubscriberAddress1(String otherSubscriberAddress1) {
        this.otherSubscriberAddress1 = otherSubscriberAddress1;
    }

    public String getOtherSubscriberAddress2() {
        return this.otherSubscriberAddress2;
    }

    public SecondaryClaimsSubmissionMaster otherSubscriberAddress2(String otherSubscriberAddress2) {
        this.setOtherSubscriberAddress2(otherSubscriberAddress2);
        return this;
    }

    public void setOtherSubscriberAddress2(String otherSubscriberAddress2) {
        this.otherSubscriberAddress2 = otherSubscriberAddress2;
    }

    public String getOtherSubscriberCity() {
        return this.otherSubscriberCity;
    }

    public SecondaryClaimsSubmissionMaster otherSubscriberCity(String otherSubscriberCity) {
        this.setOtherSubscriberCity(otherSubscriberCity);
        return this;
    }

    public void setOtherSubscriberCity(String otherSubscriberCity) {
        this.otherSubscriberCity = otherSubscriberCity;
    }

    public String getOtherSubscriberState() {
        return this.otherSubscriberState;
    }

    public SecondaryClaimsSubmissionMaster otherSubscriberState(String otherSubscriberState) {
        this.setOtherSubscriberState(otherSubscriberState);
        return this;
    }

    public void setOtherSubscriberState(String otherSubscriberState) {
        this.otherSubscriberState = otherSubscriberState;
    }

    public String getOtherSubscriberZip() {
        return this.otherSubscriberZip;
    }

    public SecondaryClaimsSubmissionMaster otherSubscriberZip(String otherSubscriberZip) {
        this.setOtherSubscriberZip(otherSubscriberZip);
        return this;
    }

    public void setOtherSubscriberZip(String otherSubscriberZip) {
        this.otherSubscriberZip = otherSubscriberZip;
    }

    public String getOtherInsuredQualifier() {
        return this.otherInsuredQualifier;
    }

    public SecondaryClaimsSubmissionMaster otherInsuredQualifier(String otherInsuredQualifier) {
        this.setOtherInsuredQualifier(otherInsuredQualifier);
        return this;
    }

    public void setOtherInsuredQualifier(String otherInsuredQualifier) {
        this.otherInsuredQualifier = otherInsuredQualifier;
    }

    public String getOtherInsuredLastName() {
        return this.otherInsuredLastName;
    }

    public SecondaryClaimsSubmissionMaster otherInsuredLastName(String otherInsuredLastName) {
        this.setOtherInsuredLastName(otherInsuredLastName);
        return this;
    }

    public void setOtherInsuredLastName(String otherInsuredLastName) {
        this.otherInsuredLastName = otherInsuredLastName;
    }

    public String getOtherInsuredFirstName() {
        return this.otherInsuredFirstName;
    }

    public SecondaryClaimsSubmissionMaster otherInsuredFirstName(String otherInsuredFirstName) {
        this.setOtherInsuredFirstName(otherInsuredFirstName);
        return this;
    }

    public void setOtherInsuredFirstName(String otherInsuredFirstName) {
        this.otherInsuredFirstName = otherInsuredFirstName;
    }

    public String getOtherInsuredIdentifierTypeCode() {
        return this.otherInsuredIdentifierTypeCode;
    }

    public SecondaryClaimsSubmissionMaster otherInsuredIdentifierTypeCode(String otherInsuredIdentifierTypeCode) {
        this.setOtherInsuredIdentifierTypeCode(otherInsuredIdentifierTypeCode);
        return this;
    }

    public void setOtherInsuredIdentifierTypeCode(String otherInsuredIdentifierTypeCode) {
        this.otherInsuredIdentifierTypeCode = otherInsuredIdentifierTypeCode;
    }

    public String getOtherInsuredIdentifier() {
        return this.otherInsuredIdentifier;
    }

    public SecondaryClaimsSubmissionMaster otherInsuredIdentifier(String otherInsuredIdentifier) {
        this.setOtherInsuredIdentifier(otherInsuredIdentifier);
        return this;
    }

    public void setOtherInsuredIdentifier(String otherInsuredIdentifier) {
        this.otherInsuredIdentifier = otherInsuredIdentifier;
    }

    public String getOtherPayerOrganizationName() {
        return this.otherPayerOrganizationName;
    }

    public SecondaryClaimsSubmissionMaster otherPayerOrganizationName(String otherPayerOrganizationName) {
        this.setOtherPayerOrganizationName(otherPayerOrganizationName);
        return this;
    }

    public void setOtherPayerOrganizationName(String otherPayerOrganizationName) {
        this.otherPayerOrganizationName = otherPayerOrganizationName;
    }

    public String getOtherPayerIdentifierTypeCode() {
        return this.otherPayerIdentifierTypeCode;
    }

    public SecondaryClaimsSubmissionMaster otherPayerIdentifierTypeCode(String otherPayerIdentifierTypeCode) {
        this.setOtherPayerIdentifierTypeCode(otherPayerIdentifierTypeCode);
        return this;
    }

    public void setOtherPayerIdentifierTypeCode(String otherPayerIdentifierTypeCode) {
        this.otherPayerIdentifierTypeCode = otherPayerIdentifierTypeCode;
    }

    public String getOtherPayerIdentifier() {
        return this.otherPayerIdentifier;
    }

    public SecondaryClaimsSubmissionMaster otherPayerIdentifier(String otherPayerIdentifier) {
        this.setOtherPayerIdentifier(otherPayerIdentifier);
        return this;
    }

    public void setOtherPayerIdentifier(String otherPayerIdentifier) {
        this.otherPayerIdentifier = otherPayerIdentifier;
    }

    public LocalDate getOtherPayerAdjudicationOrPaymentDate() {
        return this.otherPayerAdjudicationOrPaymentDate;
    }

    public SecondaryClaimsSubmissionMaster otherPayerAdjudicationOrPaymentDate(LocalDate otherPayerAdjudicationOrPaymentDate) {
        this.setOtherPayerAdjudicationOrPaymentDate(otherPayerAdjudicationOrPaymentDate);
        return this;
    }

    public void setOtherPayerAdjudicationOrPaymentDate(LocalDate otherPayerAdjudicationOrPaymentDate) {
        this.otherPayerAdjudicationOrPaymentDate = otherPayerAdjudicationOrPaymentDate;
    }

    public String getOtherPayerClaimAdjustmentIndicator() {
        return this.otherPayerClaimAdjustmentIndicator;
    }

    public SecondaryClaimsSubmissionMaster otherPayerClaimAdjustmentIndicator(String otherPayerClaimAdjustmentIndicator) {
        this.setOtherPayerClaimAdjustmentIndicator(otherPayerClaimAdjustmentIndicator);
        return this;
    }

    public void setOtherPayerClaimAdjustmentIndicator(String otherPayerClaimAdjustmentIndicator) {
        this.otherPayerClaimAdjustmentIndicator = otherPayerClaimAdjustmentIndicator;
    }

    public String getOtherPayerClaimControlNumber() {
        return this.otherPayerClaimControlNumber;
    }

    public SecondaryClaimsSubmissionMaster otherPayerClaimControlNumber(String otherPayerClaimControlNumber) {
        this.setOtherPayerClaimControlNumber(otherPayerClaimControlNumber);
        return this;
    }

    public void setOtherPayerClaimControlNumber(String otherPayerClaimControlNumber) {
        this.otherPayerClaimControlNumber = otherPayerClaimControlNumber;
    }

    public Double getPayerPaidAmount() {
        return this.payerPaidAmount;
    }

    public SecondaryClaimsSubmissionMaster payerPaidAmount(Double payerPaidAmount) {
        this.setPayerPaidAmount(payerPaidAmount);
        return this;
    }

    public void setPayerPaidAmount(Double payerPaidAmount) {
        this.payerPaidAmount = payerPaidAmount;
    }

    public String getPaymentResponsibilityLevelCode() {
        return this.paymentResponsibilityLevelCode;
    }

    public SecondaryClaimsSubmissionMaster paymentResponsibilityLevelCode(String paymentResponsibilityLevelCode) {
        this.setPaymentResponsibilityLevelCode(paymentResponsibilityLevelCode);
        return this;
    }

    public void setPaymentResponsibilityLevelCode(String paymentResponsibilityLevelCode) {
        this.paymentResponsibilityLevelCode = paymentResponsibilityLevelCode;
    }

    public String getIndividualRelationshipCode() {
        return this.individualRelationshipCode;
    }

    public SecondaryClaimsSubmissionMaster individualRelationshipCode(String individualRelationshipCode) {
        this.setIndividualRelationshipCode(individualRelationshipCode);
        return this;
    }

    public void setIndividualRelationshipCode(String individualRelationshipCode) {
        this.individualRelationshipCode = individualRelationshipCode;
    }

    public String getClaimFilingIndicatorCode() {
        return this.claimFilingIndicatorCode;
    }

    public SecondaryClaimsSubmissionMaster claimFilingIndicatorCode(String claimFilingIndicatorCode) {
        this.setClaimFilingIndicatorCode(claimFilingIndicatorCode);
        return this;
    }

    public void setClaimFilingIndicatorCode(String claimFilingIndicatorCode) {
        this.claimFilingIndicatorCode = claimFilingIndicatorCode;
    }

    public String getOtherPayerBenefitsAssignmentCertificationIndicator() {
        return this.otherPayerBenefitsAssignmentCertificationIndicator;
    }

    public SecondaryClaimsSubmissionMaster otherPayerBenefitsAssignmentCertificationIndicator(
        String otherPayerBenefitsAssignmentCertificationIndicator
    ) {
        this.setOtherPayerBenefitsAssignmentCertificationIndicator(otherPayerBenefitsAssignmentCertificationIndicator);
        return this;
    }

    public void setOtherPayerBenefitsAssignmentCertificationIndicator(String otherPayerBenefitsAssignmentCertificationIndicator) {
        this.otherPayerBenefitsAssignmentCertificationIndicator = otherPayerBenefitsAssignmentCertificationIndicator;
    }

    public String getReleaseOfInformationCode() {
        return this.releaseOfInformationCode;
    }

    public SecondaryClaimsSubmissionMaster releaseOfInformationCode(String releaseOfInformationCode) {
        this.setReleaseOfInformationCode(releaseOfInformationCode);
        return this;
    }

    public void setReleaseOfInformationCode(String releaseOfInformationCode) {
        this.releaseOfInformationCode = releaseOfInformationCode;
    }

    public Double getRemainingPatientLiability() {
        return this.remainingPatientLiability;
    }

    public SecondaryClaimsSubmissionMaster remainingPatientLiability(Double remainingPatientLiability) {
        this.setRemainingPatientLiability(remainingPatientLiability);
        return this;
    }

    public void setRemainingPatientLiability(Double remainingPatientLiability) {
        this.remainingPatientLiability = remainingPatientLiability;
    }

    public String getPatientSignatureGeneratedForPatient() {
        return this.patientSignatureGeneratedForPatient;
    }

    public SecondaryClaimsSubmissionMaster patientSignatureGeneratedForPatient(String patientSignatureGeneratedForPatient) {
        this.setPatientSignatureGeneratedForPatient(patientSignatureGeneratedForPatient);
        return this;
    }

    public void setPatientSignatureGeneratedForPatient(String patientSignatureGeneratedForPatient) {
        this.patientSignatureGeneratedForPatient = patientSignatureGeneratedForPatient;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SecondaryClaimsSubmissionMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SecondaryClaimsSubmissionMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public ZonedDateTime getUpdatedDate() {
        return this.updatedDate;
    }

    public SecondaryClaimsSubmissionMaster updatedDate(ZonedDateTime updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getChangeHealthPrimarySubmisionMasterId() {
        return this.changeHealthPrimarySubmisionMasterId;
    }

    public SecondaryClaimsSubmissionMaster changeHealthPrimarySubmisionMasterId(Long changeHealthPrimarySubmisionMasterId) {
        this.setChangeHealthPrimarySubmisionMasterId(changeHealthPrimarySubmisionMasterId);
        return this;
    }

    public void setChangeHealthPrimarySubmisionMasterId(Long changeHealthPrimarySubmisionMasterId) {
        this.changeHealthPrimarySubmisionMasterId = changeHealthPrimarySubmisionMasterId;
    }

    public String getInsuredFirstName() {
        return this.insuredFirstName;
    }

    public SecondaryClaimsSubmissionMaster insuredFirstName(String insuredFirstName) {
        this.setInsuredFirstName(insuredFirstName);
        return this;
    }

    public void setInsuredFirstName(String insuredFirstName) {
        this.insuredFirstName = insuredFirstName;
    }

    public String getInsuredLastName() {
        return this.insuredLastName;
    }

    public SecondaryClaimsSubmissionMaster insuredLastName(String insuredLastName) {
        this.setInsuredLastName(insuredLastName);
        return this;
    }

    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }

    public String getInsuredAddressLine1() {
        return this.insuredAddressLine1;
    }

    public SecondaryClaimsSubmissionMaster insuredAddressLine1(String insuredAddressLine1) {
        this.setInsuredAddressLine1(insuredAddressLine1);
        return this;
    }

    public void setInsuredAddressLine1(String insuredAddressLine1) {
        this.insuredAddressLine1 = insuredAddressLine1;
    }

    public String getInsuredAddressLine2() {
        return this.insuredAddressLine2;
    }

    public SecondaryClaimsSubmissionMaster insuredAddressLine2(String insuredAddressLine2) {
        this.setInsuredAddressLine2(insuredAddressLine2);
        return this;
    }

    public void setInsuredAddressLine2(String insuredAddressLine2) {
        this.insuredAddressLine2 = insuredAddressLine2;
    }

    public String getInsuredCity() {
        return this.insuredCity;
    }

    public SecondaryClaimsSubmissionMaster insuredCity(String insuredCity) {
        this.setInsuredCity(insuredCity);
        return this;
    }

    public void setInsuredCity(String insuredCity) {
        this.insuredCity = insuredCity;
    }

    public String getInsuredState() {
        return this.insuredState;
    }

    public SecondaryClaimsSubmissionMaster insuredState(String insuredState) {
        this.setInsuredState(insuredState);
        return this;
    }

    public void setInsuredState(String insuredState) {
        this.insuredState = insuredState;
    }

    public String getInsuredZip() {
        return this.insuredZip;
    }

    public SecondaryClaimsSubmissionMaster insuredZip(String insuredZip) {
        this.setInsuredZip(insuredZip);
        return this;
    }

    public void setInsuredZip(String insuredZip) {
        this.insuredZip = insuredZip;
    }

    public String getInsuredContactNo() {
        return this.insuredContactNo;
    }

    public SecondaryClaimsSubmissionMaster insuredContactNo(String insuredContactNo) {
        this.setInsuredContactNo(insuredContactNo);
        return this;
    }

    public void setInsuredContactNo(String insuredContactNo) {
        this.insuredContactNo = insuredContactNo;
    }

    public LocalDate getInsuredDob() {
        return this.insuredDob;
    }

    public SecondaryClaimsSubmissionMaster insuredDob(LocalDate insuredDob) {
        this.setInsuredDob(insuredDob);
        return this;
    }

    public void setInsuredDob(LocalDate insuredDob) {
        this.insuredDob = insuredDob;
    }

    public String getInsuredGender() {
        return this.insuredGender;
    }

    public SecondaryClaimsSubmissionMaster insuredGender(String insuredGender) {
        this.setInsuredGender(insuredGender);
        return this;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public String getOrderingProviderFirstName() {
        return this.orderingProviderFirstName;
    }

    public SecondaryClaimsSubmissionMaster orderingProviderFirstName(String orderingProviderFirstName) {
        this.setOrderingProviderFirstName(orderingProviderFirstName);
        return this;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderLastName() {
        return this.orderingProviderLastName;
    }

    public SecondaryClaimsSubmissionMaster orderingProviderLastName(String orderingProviderLastName) {
        this.setOrderingProviderLastName(orderingProviderLastName);
        return this;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return this.orderingProviderNpi;
    }

    public SecondaryClaimsSubmissionMaster orderingProviderNpi(String orderingProviderNpi) {
        this.setOrderingProviderNpi(orderingProviderNpi);
        return this;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getPatientRelationshipInsured() {
        return this.patientRelationshipInsured;
    }

    public SecondaryClaimsSubmissionMaster patientRelationshipInsured(String patientRelationshipInsured) {
        this.setPatientRelationshipInsured(patientRelationshipInsured);
        return this;
    }

    public void setPatientRelationshipInsured(String patientRelationshipInsured) {
        this.patientRelationshipInsured = patientRelationshipInsured;
    }

    public String getPatientConditionEmployment() {
        return this.patientConditionEmployment;
    }

    public SecondaryClaimsSubmissionMaster patientConditionEmployment(String patientConditionEmployment) {
        this.setPatientConditionEmployment(patientConditionEmployment);
        return this;
    }

    public void setPatientConditionEmployment(String patientConditionEmployment) {
        this.patientConditionEmployment = patientConditionEmployment;
    }

    public String getPatientConditionAutoAccident() {
        return this.patientConditionAutoAccident;
    }

    public SecondaryClaimsSubmissionMaster patientConditionAutoAccident(String patientConditionAutoAccident) {
        this.setPatientConditionAutoAccident(patientConditionAutoAccident);
        return this;
    }

    public void setPatientConditionAutoAccident(String patientConditionAutoAccident) {
        this.patientConditionAutoAccident = patientConditionAutoAccident;
    }

    public String getPatientConditionOtherAccident() {
        return this.patientConditionOtherAccident;
    }

    public SecondaryClaimsSubmissionMaster patientConditionOtherAccident(String patientConditionOtherAccident) {
        this.setPatientConditionOtherAccident(patientConditionOtherAccident);
        return this;
    }

    public void setPatientConditionOtherAccident(String patientConditionOtherAccident) {
        this.patientConditionOtherAccident = patientConditionOtherAccident;
    }

    public String getIsNextLevelInsurerPresentStatus() {
        return this.isNextLevelInsurerPresentStatus;
    }

    public SecondaryClaimsSubmissionMaster isNextLevelInsurerPresentStatus(String isNextLevelInsurerPresentStatus) {
        this.setIsNextLevelInsurerPresentStatus(isNextLevelInsurerPresentStatus);
        return this;
    }

    public void setIsNextLevelInsurerPresentStatus(String isNextLevelInsurerPresentStatus) {
        this.isNextLevelInsurerPresentStatus = isNextLevelInsurerPresentStatus;
    }

    public LocalDate getOriginalDos() {
        return this.originalDos;
    }

    public SecondaryClaimsSubmissionMaster originalDos(LocalDate originalDos) {
        this.setOriginalDos(originalDos);
        return this;
    }

    public void setOriginalDos(LocalDate originalDos) {
        this.originalDos = originalDos;
    }

    public String getParNo() {
        return this.parNo;
    }

    public SecondaryClaimsSubmissionMaster parNo(String parNo) {
        this.setParNo(parNo);
        return this;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public String getBillingProviderTaxonomy() {
        return this.billingProviderTaxonomy;
    }

    public SecondaryClaimsSubmissionMaster billingProviderTaxonomy(String billingProviderTaxonomy) {
        this.setBillingProviderTaxonomy(billingProviderTaxonomy);
        return this;
    }

    public void setBillingProviderTaxonomy(String billingProviderTaxonomy) {
        this.billingProviderTaxonomy = billingProviderTaxonomy;
    }

    public String getServiceProviderNpi() {
        return this.serviceProviderNpi;
    }

    public SecondaryClaimsSubmissionMaster serviceProviderNpi(String serviceProviderNpi) {
        this.setServiceProviderNpi(serviceProviderNpi);
        return this;
    }

    public void setServiceProviderNpi(String serviceProviderNpi) {
        this.serviceProviderNpi = serviceProviderNpi;
    }

    public String getServiceProviderOrganisationName() {
        return this.serviceProviderOrganisationName;
    }

    public SecondaryClaimsSubmissionMaster serviceProviderOrganisationName(String serviceProviderOrganisationName) {
        this.setServiceProviderOrganisationName(serviceProviderOrganisationName);
        return this;
    }

    public void setServiceProviderOrganisationName(String serviceProviderOrganisationName) {
        this.serviceProviderOrganisationName = serviceProviderOrganisationName;
    }

    public String getServiceProviderAddressLine1() {
        return this.serviceProviderAddressLine1;
    }

    public SecondaryClaimsSubmissionMaster serviceProviderAddressLine1(String serviceProviderAddressLine1) {
        this.setServiceProviderAddressLine1(serviceProviderAddressLine1);
        return this;
    }

    public void setServiceProviderAddressLine1(String serviceProviderAddressLine1) {
        this.serviceProviderAddressLine1 = serviceProviderAddressLine1;
    }

    public String getServiceProviderAddressLine2() {
        return this.serviceProviderAddressLine2;
    }

    public SecondaryClaimsSubmissionMaster serviceProviderAddressLine2(String serviceProviderAddressLine2) {
        this.setServiceProviderAddressLine2(serviceProviderAddressLine2);
        return this;
    }

    public void setServiceProviderAddressLine2(String serviceProviderAddressLine2) {
        this.serviceProviderAddressLine2 = serviceProviderAddressLine2;
    }

    public String getServiceProviderCity() {
        return this.serviceProviderCity;
    }

    public SecondaryClaimsSubmissionMaster serviceProviderCity(String serviceProviderCity) {
        this.setServiceProviderCity(serviceProviderCity);
        return this;
    }

    public void setServiceProviderCity(String serviceProviderCity) {
        this.serviceProviderCity = serviceProviderCity;
    }

    public String getServiceProviderState() {
        return this.serviceProviderState;
    }

    public SecondaryClaimsSubmissionMaster serviceProviderState(String serviceProviderState) {
        this.setServiceProviderState(serviceProviderState);
        return this;
    }

    public void setServiceProviderState(String serviceProviderState) {
        this.serviceProviderState = serviceProviderState;
    }

    public String getServiceProviderCountry() {
        return this.serviceProviderCountry;
    }

    public SecondaryClaimsSubmissionMaster serviceProviderCountry(String serviceProviderCountry) {
        this.setServiceProviderCountry(serviceProviderCountry);
        return this;
    }

    public void setServiceProviderCountry(String serviceProviderCountry) {
        this.serviceProviderCountry = serviceProviderCountry;
    }

    public String getServiceProviderZipCode() {
        return this.serviceProviderZipCode;
    }

    public SecondaryClaimsSubmissionMaster serviceProviderZipCode(String serviceProviderZipCode) {
        this.setServiceProviderZipCode(serviceProviderZipCode);
        return this;
    }

    public void setServiceProviderZipCode(String serviceProviderZipCode) {
        this.serviceProviderZipCode = serviceProviderZipCode;
    }

    public String getServiceProviderTaxonomy() {
        return this.serviceProviderTaxonomy;
    }

    public SecondaryClaimsSubmissionMaster serviceProviderTaxonomy(String serviceProviderTaxonomy) {
        this.setServiceProviderTaxonomy(serviceProviderTaxonomy);
        return this;
    }

    public void setServiceProviderTaxonomy(String serviceProviderTaxonomy) {
        this.serviceProviderTaxonomy = serviceProviderTaxonomy;
    }

    public String getCms1500FormName() {
        return this.cms1500FormName;
    }

    public SecondaryClaimsSubmissionMaster cms1500FormName(String cms1500FormName) {
        this.setCms1500FormName(cms1500FormName);
        return this;
    }

    public void setCms1500FormName(String cms1500FormName) {
        this.cms1500FormName = cms1500FormName;
    }

    public String getTradingPartnerAddressLine1() {
        return this.tradingPartnerAddressLine1;
    }

    public SecondaryClaimsSubmissionMaster tradingPartnerAddressLine1(String tradingPartnerAddressLine1) {
        this.setTradingPartnerAddressLine1(tradingPartnerAddressLine1);
        return this;
    }

    public void setTradingPartnerAddressLine1(String tradingPartnerAddressLine1) {
        this.tradingPartnerAddressLine1 = tradingPartnerAddressLine1;
    }

    public String getTradingPartnerAddressLine2() {
        return this.tradingPartnerAddressLine2;
    }

    public SecondaryClaimsSubmissionMaster tradingPartnerAddressLine2(String tradingPartnerAddressLine2) {
        this.setTradingPartnerAddressLine2(tradingPartnerAddressLine2);
        return this;
    }

    public void setTradingPartnerAddressLine2(String tradingPartnerAddressLine2) {
        this.tradingPartnerAddressLine2 = tradingPartnerAddressLine2;
    }

    public String getTradingPartnerCity() {
        return this.tradingPartnerCity;
    }

    public SecondaryClaimsSubmissionMaster tradingPartnerCity(String tradingPartnerCity) {
        this.setTradingPartnerCity(tradingPartnerCity);
        return this;
    }

    public void setTradingPartnerCity(String tradingPartnerCity) {
        this.tradingPartnerCity = tradingPartnerCity;
    }

    public String getTradingPartnerState() {
        return this.tradingPartnerState;
    }

    public SecondaryClaimsSubmissionMaster tradingPartnerState(String tradingPartnerState) {
        this.setTradingPartnerState(tradingPartnerState);
        return this;
    }

    public void setTradingPartnerState(String tradingPartnerState) {
        this.tradingPartnerState = tradingPartnerState;
    }

    public String getTradingPartnerZip() {
        return this.tradingPartnerZip;
    }

    public SecondaryClaimsSubmissionMaster tradingPartnerZip(String tradingPartnerZip) {
        this.setTradingPartnerZip(tradingPartnerZip);
        return this;
    }

    public void setTradingPartnerZip(String tradingPartnerZip) {
        this.tradingPartnerZip = tradingPartnerZip;
    }

    public String getDiagnosisCodeType() {
        return this.diagnosisCodeType;
    }

    public SecondaryClaimsSubmissionMaster diagnosisCodeType(String diagnosisCodeType) {
        this.setDiagnosisCodeType(diagnosisCodeType);
        return this;
    }

    public void setDiagnosisCodeType(String diagnosisCodeType) {
        this.diagnosisCodeType = diagnosisCodeType;
    }

    public Set<SecondaryServiceLinesMaster> getSecondaryserviceLines() {
        return this.secondaryserviceLines;
    }

    public void setSecondaryserviceLines(Set<SecondaryServiceLinesMaster> secondaryServiceLinesMasters) {
        if (this.secondaryserviceLines != null) {
            this.secondaryserviceLines.forEach(i -> i.setSecondaryClaimsSubmissionMaster(null));
        }
        if (secondaryServiceLinesMasters != null) {
            secondaryServiceLinesMasters.forEach(i -> i.setSecondaryClaimsSubmissionMaster(this));
        }
        this.secondaryserviceLines = secondaryServiceLinesMasters;
    }

    public SecondaryClaimsSubmissionMaster secondaryserviceLines(Set<SecondaryServiceLinesMaster> secondaryServiceLinesMasters) {
        this.setSecondaryserviceLines(secondaryServiceLinesMasters);
        return this;
    }

    public SecondaryClaimsSubmissionMaster addSecondaryserviceLines(SecondaryServiceLinesMaster secondaryServiceLinesMaster) {
        this.secondaryserviceLines.add(secondaryServiceLinesMaster);
        secondaryServiceLinesMaster.setSecondaryClaimsSubmissionMaster(this);
        return this;
    }

    public SecondaryClaimsSubmissionMaster removeSecondaryserviceLines(SecondaryServiceLinesMaster secondaryServiceLinesMaster) {
        this.secondaryserviceLines.remove(secondaryServiceLinesMaster);
        secondaryServiceLinesMaster.setSecondaryClaimsSubmissionMaster(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SecondaryClaimsSubmissionMaster)) {
            return false;
        }
        return (
            changeHealthSecondarySubmisionMasterId != null &&
            changeHealthSecondarySubmisionMasterId.equals(((SecondaryClaimsSubmissionMaster) o).changeHealthSecondarySubmisionMasterId)
        );
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SecondaryClaimsSubmissionMaster{" +
            "changeHealthSecondarySubmisionMasterId=" + getChangeHealthSecondarySubmisionMasterId() +
            ", salesOrderId=" + getSalesOrderId() +
            ", claimControlNo='" + getClaimControlNo() + "'" +
            ", tradingPartnerServiceId='" + getTradingPartnerServiceId() + "'" +
            ", tradingPartnerName='" + getTradingPartnerName() + "'" +
            ", submitterOrganizationName='" + getSubmitterOrganizationName() + "'" +
            ", submitterContactPersonName='" + getSubmitterContactPersonName() + "'" +
            ", submitterContactNo='" + getSubmitterContactNo() + "'" +
            ", receiverOrganizationName='" + getReceiverOrganizationName() + "'" +
            ", subscriberMemberIdNo='" + getSubscriberMemberIdNo() + "'" +
            ", subscriberPaymentResponsibilityLevelCode='" + getSubscriberPaymentResponsibilityLevelCode() + "'" +
            ", subscriberFirstName='" + getSubscriberFirstName() + "'" +
            ", subscriberLastName='" + getSubscriberLastName() + "'" +
            ", subscriberGender='" + getSubscriberGender() + "'" +
            ", subscriberDob='" + getSubscriberDob() + "'" +
            ", primaryInsurerPolicyNo='" + getPrimaryInsurerPolicyNo() + "'" +
            ", subscriberAddressLine1='" + getSubscriberAddressLine1() + "'" +
            ", subscriberCity='" + getSubscriberCity() + "'" +
            ", subscriberState='" + getSubscriberState() + "'" +
            ", subscriberZipCode='" + getSubscriberZipCode() + "'" +
            ", providerType='" + getProviderType() + "'" +
            ", billingProviderNpi='" + getBillingProviderNpi() + "'" +
            ", billingProviderEin='" + getBillingProviderEin() + "'" +
            ", billingProviderOrganizationName='" + getBillingProviderOrganizationName() + "'" +
            ", billingProviderAddressLine1='" + getBillingProviderAddressLine1() + "'" +
            ", billingProviderAddressLine2='" + getBillingProviderAddressLine2() + "'" +
            ", billingProviderCity='" + getBillingProviderCity() + "'" +
            ", billingProviderState='" + getBillingProviderState() + "'" +
            ", billingProviderZipCode='" + getBillingProviderZipCode() + "'" +
            ", billingProviderContactPersonName='" + getBillingProviderContactPersonName() + "'" +
            ", billingProviderContactNo='" + getBillingProviderContactNo() + "'" +
            ", claimFilingCode='" + getClaimFilingCode() + "'" +
            ", patientAccountNo='" + getPatientAccountNo() + "'" +
            ", claimChargeAmount=" + getClaimChargeAmount() +
            ", posCode='" + getPosCode() + "'" +
            ", claimFrequencyCode='" + getClaimFrequencyCode() + "'" +
            ", signatureIndicator='" + getSignatureIndicator() + "'" +
            ", planParticipationCode='" + getPlanParticipationCode() + "'" +
            ", benefitsAssignmentCertificationIndicator='" + getBenefitsAssignmentCertificationIndicator() + "'" +
            ", releaseInformationCode='" + getReleaseInformationCode() + "'" +
            ", primaryDiagnosis='" + getPrimaryDiagnosis() + "'" +
            ", icd10diagnosisCode1='" + getIcd10diagnosisCode1() + "'" +
            ", icd10diagnosisCode2='" + getIcd10diagnosisCode2() + "'" +
            ", icd10diagnosisCode3='" + getIcd10diagnosisCode3() + "'" +
            ", icd10diagnosisCode4='" + getIcd10diagnosisCode4() + "'" +
            ", icd10diagnosisCode5='" + getIcd10diagnosisCode5() + "'" +
            ", icd10diagnosisCode6='" + getIcd10diagnosisCode6() + "'" +
            ", icd10diagnosisCode7='" + getIcd10diagnosisCode7() + "'" +
            ", icd10diagnosisCode8='" + getIcd10diagnosisCode8() + "'" +
            ", icd10diagnosisCode9='" + getIcd10diagnosisCode9() + "'" +
            ", icd10diagnosisCode10='" + getIcd10diagnosisCode10() + "'" +
            ", icd10diagnosisCode11='" + getIcd10diagnosisCode11() + "'" +
            ", icd10diagnosisCode12='" + getIcd10diagnosisCode12() + "'" +
            ", insertedById=" + getInsertedById() +
            ", insertedDate='" + getInsertedDate() + "'" +
            ", billingProviderType='" + getBillingProviderType() + "'" +
            ", insertedByName='" + getInsertedByName() + "'" +
            ", status='" + getStatus() + "'" +
            ", otherSubscriberAddress1='" + getOtherSubscriberAddress1() + "'" +
            ", otherSubscriberAddress2='" + getOtherSubscriberAddress2() + "'" +
            ", otherSubscriberCity='" + getOtherSubscriberCity() + "'" +
            ", otherSubscriberState='" + getOtherSubscriberState() + "'" +
            ", otherSubscriberZip='" + getOtherSubscriberZip() + "'" +
            ", otherInsuredQualifier='" + getOtherInsuredQualifier() + "'" +
            ", otherInsuredLastName='" + getOtherInsuredLastName() + "'" +
            ", otherInsuredFirstName='" + getOtherInsuredFirstName() + "'" +
            ", otherInsuredIdentifierTypeCode='" + getOtherInsuredIdentifierTypeCode() + "'" +
            ", otherInsuredIdentifier='" + getOtherInsuredIdentifier() + "'" +
            ", otherPayerOrganizationName='" + getOtherPayerOrganizationName() + "'" +
            ", otherPayerIdentifierTypeCode='" + getOtherPayerIdentifierTypeCode() + "'" +
            ", otherPayerIdentifier='" + getOtherPayerIdentifier() + "'" +
            ", otherPayerAdjudicationOrPaymentDate='" + getOtherPayerAdjudicationOrPaymentDate() + "'" +
            ", otherPayerClaimAdjustmentIndicator='" + getOtherPayerClaimAdjustmentIndicator() + "'" +
            ", otherPayerClaimControlNumber='" + getOtherPayerClaimControlNumber() + "'" +
            ", payerPaidAmount=" + getPayerPaidAmount() +
            ", paymentResponsibilityLevelCode='" + getPaymentResponsibilityLevelCode() + "'" +
            ", individualRelationshipCode='" + getIndividualRelationshipCode() + "'" +
            ", claimFilingIndicatorCode='" + getClaimFilingIndicatorCode() + "'" +
            ", otherPayerBenefitsAssignmentCertificationIndicator='" + getOtherPayerBenefitsAssignmentCertificationIndicator() + "'" +
            ", releaseOfInformationCode='" + getReleaseOfInformationCode() + "'" +
            ", remainingPatientLiability=" + getRemainingPatientLiability() +
            ", patientSignatureGeneratedForPatient='" + getPatientSignatureGeneratedForPatient() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", changeHealthPrimarySubmisionMasterId=" + getChangeHealthPrimarySubmisionMasterId() +
            ", insuredFirstName='" + getInsuredFirstName() + "'" +
            ", insuredLastName='" + getInsuredLastName() + "'" +
            ", insuredAddressLine1='" + getInsuredAddressLine1() + "'" +
            ", insuredAddressLine2='" + getInsuredAddressLine2() + "'" +
            ", insuredCity='" + getInsuredCity() + "'" +
            ", insuredState='" + getInsuredState() + "'" +
            ", insuredZip='" + getInsuredZip() + "'" +
            ", insuredContactNo='" + getInsuredContactNo() + "'" +
            ", insuredDob='" + getInsuredDob() + "'" +
            ", insuredGender='" + getInsuredGender() + "'" +
            ", orderingProviderFirstName='" + getOrderingProviderFirstName() + "'" +
            ", orderingProviderLastName='" + getOrderingProviderLastName() + "'" +
            ", orderingProviderNpi='" + getOrderingProviderNpi() + "'" +
            ", patientRelationshipInsured='" + getPatientRelationshipInsured() + "'" +
            ", patientConditionEmployment='" + getPatientConditionEmployment() + "'" +
            ", patientConditionAutoAccident='" + getPatientConditionAutoAccident() + "'" +
            ", patientConditionOtherAccident='" + getPatientConditionOtherAccident() + "'" +
            ", isNextLevelInsurerPresentStatus='" + getIsNextLevelInsurerPresentStatus() + "'" +
            ", originalDos='" + getOriginalDos() + "'" +
            ", parNo='" + getParNo() + "'" +
            ", billingProviderTaxonomy='" + getBillingProviderTaxonomy() + "'" +
            ", serviceProviderNpi='" + getServiceProviderNpi() + "'" +
            ", serviceProviderOrganisationName='" + getServiceProviderOrganisationName() + "'" +
            ", serviceProviderAddressLine1='" + getServiceProviderAddressLine1() + "'" +
            ", serviceProviderAddressLine2='" + getServiceProviderAddressLine2() + "'" +
            ", serviceProviderCity='" + getServiceProviderCity() + "'" +
            ", serviceProviderState='" + getServiceProviderState() + "'" +
            ", serviceProviderCountry='" + getServiceProviderCountry() + "'" +
            ", serviceProviderZipCode='" + getServiceProviderZipCode() + "'" +
            ", serviceProviderTaxonomy='" + getServiceProviderTaxonomy() + "'" +
            ", cms1500FormName='" + getCms1500FormName() + "'" +
            ", tradingPartnerAddressLine1='" + getTradingPartnerAddressLine1() + "'" +
            ", tradingPartnerAddressLine2='" + getTradingPartnerAddressLine2() + "'" +
            ", tradingPartnerCity='" + getTradingPartnerCity() + "'" +
            ", tradingPartnerState='" + getTradingPartnerState() + "'" +
            ", tradingPartnerZip='" + getTradingPartnerZip() + "'" +
            ", diagnosisCodeType='" + getDiagnosisCodeType() + "'" +
            "}";
    }
}