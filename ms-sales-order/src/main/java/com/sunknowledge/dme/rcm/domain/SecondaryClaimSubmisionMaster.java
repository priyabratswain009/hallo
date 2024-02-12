package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SecondaryClaimSubmisionMaster.
 */
@Table("t_secondary_claim_submision_master")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SecondaryClaimSubmisionMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("change_health_secondary_submision_master_id")
    private Long changeHealthSecondarySubmisionMasterId;

    @Column("sales_order_id")
    private Long salesOrderId;

    @Column("claim_control_no")
    private String claimControlNo;

    @Column("trading_partner_service_id")
    private String tradingPartnerServiceId;

    @Column("trading_partner_name")
    private String tradingPartnerName;

    @Column("submitter_organization_name")
    private String submitterOrganizationName;

    @Column("submitter_contact_person_name")
    private String submitterContactPersonName;

    @Column("submitter_contact_no")
    private String submitterContactNo;

    @Column("receiver_organization_name")
    private String receiverOrganizationName;

    @Column("subscriber_member_id_no")
    private String subscriberMemberIdNo;

    @Column("subscriber_payment_responsibility_level_code")
    private String subscriberPaymentResponsibilityLevelCode;

    @Column("subscriber_first_name")
    private String subscriberFirstName;

    @Column("subscriber_last_name")
    private String subscriberLastName;

    @Column("subscriber_gender")
    private String subscriberGender;

    @Column("subscriber_dob")
    private LocalDate subscriberDob;

    @Column("secondary_insurer_policy_no")
    private String secondaryInsurerPolicyNo;

    @Column("subscriber_address_line_1")
    private String subscriberAddressLine1;

    @Column("subscriber_city")
    private String subscriberCity;

    @Column("subscriber_state")
    private String subscriberState;

    @Column("subscriber_zip_code")
    private String subscriberZipCode;

    @Column("provider_type")
    private String providerType;

    @Column("billing_provider_npi")
    private String billingProviderNpi;

    @Column("billing_provider_ein")
    private String billingProviderEin;

    @Column("billing_provider_organization_name")
    private String billingProviderOrganizationName;

    @Column("billing_provider_address_line_1")
    private String billingProviderAddressLine1;

    @Column("billing_provider_address_line_2")
    private String billingProviderAddressLine2;

    @Column("billing_provider_city")
    private String billingProviderCity;

    @Column("billing_provider_state")
    private String billingProviderState;

    @Column("billing_provider_zip_code")
    private String billingProviderZipCode;

    @Column("billing_provider_contact_person_name")
    private String billingProviderContactPersonName;

    @Column("billing_provider_contact_no")
    private String billingProviderContactNo;

    @Column("claim_filing_code")
    private String claimFilingCode;

    @Column("patient_account_no")
    private String patientAccountNo;

    @Column("claim_charge_amount")
    private Double claimChargeAmount;

    @Column("pos_code")
    private String posCode;

    @Column("claim_frequency_code")
    private String claimFrequencyCode;

    @Column("signature_indicator")
    private String signatureIndicator;

    @Column("plan_participation_code")
    private String planParticipationCode;

    @Column("benefits_assignment_certification_indicator")
    private String benefitsAssignmentCertificationIndicator;

    @Column("release_information_code")
    private String releaseInformationCode;

    @Column("primary_diagnosis")
    private String primaryDiagnosis;

    @Column("icd_10_diagnosis_code_1")
    private String icd10DiagnosisCode1;

    @Column("icd_10_diagnosis_code_2")
    private String icd10DiagnosisCode2;

    @Column("icd_10_diagnosis_code_3")
    private String icd10DiagnosisCode3;

    @Column("icd_10_diagnosis_code_4")
    private String icd10DiagnosisCode4;

    @Column("icd_10_diagnosis_code_5")
    private String icd10DiagnosisCode5;

    @Column("icd_10_diagnosis_code_6")
    private String icd10DiagnosisCode6;

    @Column("icd_10_diagnosis_code_7")
    private String icd10DiagnosisCode7;

    @Column("icd_10_diagnosis_code_8")
    private String icd10DiagnosisCode8;

    @Column("icd_10_diagnosis_code_9")
    private String icd10DiagnosisCode9;

    @Column("icd_10_diagnosis_code_10")
    private String icd10DiagnosisCode10;

    @Column("icd_10_diagnosis_code_11")
    private String icd10DiagnosisCode11;

    @Column("icd_10_diagnosis_code_12")
    private String icd10DiagnosisCode12;

    @Column("inserted_by_id")
    private String insertedById;

    @Column("inserted_date")
    private LocalDate insertedDate;

    @Column("billing_provider_type")
    private String billingProviderType;

    @Column("inserted_by_name")
    private String insertedByName;

    @Column("status")
    private String status;

    @Column("other_subscriber_address_1")
    private String otherSubscriberAddress1;

    @Column("other_subscriber_address_2")
    private String otherSubscriberAddress2;

    @Column("other_subscriber_city")
    private String otherSubscriberCity;

    @Column("other_subscriber_state")
    private String otherSubscriberState;

    @Column("other_subscriber_zip")
    private String otherSubscriberZip;

    @Column("other_insured_qualifier")
    private String otherInsuredQualifier;

    @Column("other_insured_last_name")
    private String otherInsuredLastName;

    @Column("other_insured_first_name")
    private String otherInsuredFirstName;

    @Column("other_insured_identifier_type_code")
    private String otherInsuredIdentifierTypeCode;

    @Column("other_insured_identifier")
    private String otherInsuredIdentifier;

    @Column("other_payer_organization_name")
    private String otherPayerOrganizationName;

    @Column("other_payer_identifier_type_code")
    private String otherPayerIdentifierTypeCode;

    @Column("other_payer_identifier")
    private String otherPayerIdentifier;

    @Column("other_payer_adjudication_or_payment_date")
    private LocalDate otherPayerAdjudicationOrPaymentDate;

    @Column("other_payer_claim_adjustment_indicator")
    private String otherPayerClaimAdjustmentIndicator;

    @Column("other_payer_claim_control_number")
    private String otherPayerClaimControlNumber;

    @Column("payer_paid_amount")
    private Double payerPaidAmount;

    @Column("payment_responsibility_level_code")
    private String paymentResponsibilityLevelCode;

    @Column("individual_relationship_code")
    private String individualRelationshipCode;

    @Column("claim_filing_indicator_code")
    private String claimFilingIndicatorCode;

    @Column("other_payer_benefits_assignment_certification_indicator")
    private String otherPayerBenefitsAssignmentCertificationIndicator;

    @Column("release_of_information_code")
    private String releaseOfInformationCode;

    @Column("remaining_patient_liability")
    private Double remainingPatientLiability;

    @Column("patient_signature_generated_for_patient")
    private String patientSignatureGeneratedForPatient;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("change_health_primary_submision_master_id")
    private Long changeHealthPrimarySubmisionMasterId;

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

    @Column("insured_dob")
    private LocalDate insuredDob;

    @Column("insured_gender")
    private String insuredGender;

    @Column("ordering_provider_first_name")
    private String orderingProviderFirstName;

    @Column("ordering_provider_last_name")
    private String orderingProviderLastName;

    @Column("ordering_provider_npi")
    private String orderingProviderNpi;

    @Column("patient_relationship_insured")
    private String patientRelationshipInsured;

    @Column("patient_condition_employment")
    private String patientConditionEmployment;

    @Column("patient_condition_auto_accident")
    private String patientConditionAutoAccident;

    @Column("patient_condition_other_accident")
    private String patientConditionOtherAccident;

    @Column("is_next_level_insurer_present_status")
    private String isNextLevelInsurerPresentStatus;

    @Column("original_dos")
    private LocalDate originalDos;

    @Column("par_no")
    private String parNo;

    @Column("billing_provider_taxonomy")
    private String billingProviderTaxonomy;

    @Column("service_provider_npi")
    private String serviceProviderNpi;

    @Column("service_provider_organisation_name")
    private String serviceProviderOrganisationName;

    @Column("service_provider_address_line_1")
    private String serviceProviderAddressLine1;

    @Column("service_provider_address_line_2")
    private String serviceProviderAddressLine2;

    @Column("service_provider_city")
    private String serviceProviderCity;

    @Column("service_provider_state")
    private String serviceProviderState;

    @Column("service_provider_country")
    private String serviceProviderCountry;

    @Column("service_provider_zip_code")
    private String serviceProviderZipCode;

    @Column("service_provider_taxonomy")
    private String serviceProviderTaxonomy;

    @Column("cms_1500_form_name")
    private String cms1500FormName;

    @Column("trading_partner_address_line_1")
    private String tradingPartnerAddressLine1;

    @Column("trading_partner_address_line_2")
    private String tradingPartnerAddressLine2;

    @Column("trading_partner_city")
    private String tradingPartnerCity;

    @Column("trading_partner_state")
    private String tradingPartnerState;

    @Column("trading_partner_zip")
    private String tradingPartnerZip;

    @Column("diagnosis_code_type")
    private String diagnosisCodeType;

    @Column("secondary_claim_submision_master_uuid")
    private UUID secondaryClaimSubmisionMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getChangeHealthSecondarySubmisionMasterId() {
        return this.changeHealthSecondarySubmisionMasterId;
    }

    public SecondaryClaimSubmisionMaster changeHealthSecondarySubmisionMasterId(Long changeHealthSecondarySubmisionMasterId) {
        this.setChangeHealthSecondarySubmisionMasterId(changeHealthSecondarySubmisionMasterId);
        return this;
    }

    public void setChangeHealthSecondarySubmisionMasterId(Long changeHealthSecondarySubmisionMasterId) {
        this.changeHealthSecondarySubmisionMasterId = changeHealthSecondarySubmisionMasterId;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public SecondaryClaimSubmisionMaster salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getClaimControlNo() {
        return this.claimControlNo;
    }

    public SecondaryClaimSubmisionMaster claimControlNo(String claimControlNo) {
        this.setClaimControlNo(claimControlNo);
        return this;
    }

    public void setClaimControlNo(String claimControlNo) {
        this.claimControlNo = claimControlNo;
    }

    public String getTradingPartnerServiceId() {
        return this.tradingPartnerServiceId;
    }

    public SecondaryClaimSubmisionMaster tradingPartnerServiceId(String tradingPartnerServiceId) {
        this.setTradingPartnerServiceId(tradingPartnerServiceId);
        return this;
    }

    public void setTradingPartnerServiceId(String tradingPartnerServiceId) {
        this.tradingPartnerServiceId = tradingPartnerServiceId;
    }

    public String getTradingPartnerName() {
        return this.tradingPartnerName;
    }

    public SecondaryClaimSubmisionMaster tradingPartnerName(String tradingPartnerName) {
        this.setTradingPartnerName(tradingPartnerName);
        return this;
    }

    public void setTradingPartnerName(String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    public String getSubmitterOrganizationName() {
        return this.submitterOrganizationName;
    }

    public SecondaryClaimSubmisionMaster submitterOrganizationName(String submitterOrganizationName) {
        this.setSubmitterOrganizationName(submitterOrganizationName);
        return this;
    }

    public void setSubmitterOrganizationName(String submitterOrganizationName) {
        this.submitterOrganizationName = submitterOrganizationName;
    }

    public String getSubmitterContactPersonName() {
        return this.submitterContactPersonName;
    }

    public SecondaryClaimSubmisionMaster submitterContactPersonName(String submitterContactPersonName) {
        this.setSubmitterContactPersonName(submitterContactPersonName);
        return this;
    }

    public void setSubmitterContactPersonName(String submitterContactPersonName) {
        this.submitterContactPersonName = submitterContactPersonName;
    }

    public String getSubmitterContactNo() {
        return this.submitterContactNo;
    }

    public SecondaryClaimSubmisionMaster submitterContactNo(String submitterContactNo) {
        this.setSubmitterContactNo(submitterContactNo);
        return this;
    }

    public void setSubmitterContactNo(String submitterContactNo) {
        this.submitterContactNo = submitterContactNo;
    }

    public String getReceiverOrganizationName() {
        return this.receiverOrganizationName;
    }

    public SecondaryClaimSubmisionMaster receiverOrganizationName(String receiverOrganizationName) {
        this.setReceiverOrganizationName(receiverOrganizationName);
        return this;
    }

    public void setReceiverOrganizationName(String receiverOrganizationName) {
        this.receiverOrganizationName = receiverOrganizationName;
    }

    public String getSubscriberMemberIdNo() {
        return this.subscriberMemberIdNo;
    }

    public SecondaryClaimSubmisionMaster subscriberMemberIdNo(String subscriberMemberIdNo) {
        this.setSubscriberMemberIdNo(subscriberMemberIdNo);
        return this;
    }

    public void setSubscriberMemberIdNo(String subscriberMemberIdNo) {
        this.subscriberMemberIdNo = subscriberMemberIdNo;
    }

    public String getSubscriberPaymentResponsibilityLevelCode() {
        return this.subscriberPaymentResponsibilityLevelCode;
    }

    public SecondaryClaimSubmisionMaster subscriberPaymentResponsibilityLevelCode(String subscriberPaymentResponsibilityLevelCode) {
        this.setSubscriberPaymentResponsibilityLevelCode(subscriberPaymentResponsibilityLevelCode);
        return this;
    }

    public void setSubscriberPaymentResponsibilityLevelCode(String subscriberPaymentResponsibilityLevelCode) {
        this.subscriberPaymentResponsibilityLevelCode = subscriberPaymentResponsibilityLevelCode;
    }

    public String getSubscriberFirstName() {
        return this.subscriberFirstName;
    }

    public SecondaryClaimSubmisionMaster subscriberFirstName(String subscriberFirstName) {
        this.setSubscriberFirstName(subscriberFirstName);
        return this;
    }

    public void setSubscriberFirstName(String subscriberFirstName) {
        this.subscriberFirstName = subscriberFirstName;
    }

    public String getSubscriberLastName() {
        return this.subscriberLastName;
    }

    public SecondaryClaimSubmisionMaster subscriberLastName(String subscriberLastName) {
        this.setSubscriberLastName(subscriberLastName);
        return this;
    }

    public void setSubscriberLastName(String subscriberLastName) {
        this.subscriberLastName = subscriberLastName;
    }

    public String getSubscriberGender() {
        return this.subscriberGender;
    }

    public SecondaryClaimSubmisionMaster subscriberGender(String subscriberGender) {
        this.setSubscriberGender(subscriberGender);
        return this;
    }

    public void setSubscriberGender(String subscriberGender) {
        this.subscriberGender = subscriberGender;
    }

    public LocalDate getSubscriberDob() {
        return this.subscriberDob;
    }

    public SecondaryClaimSubmisionMaster subscriberDob(LocalDate subscriberDob) {
        this.setSubscriberDob(subscriberDob);
        return this;
    }

    public void setSubscriberDob(LocalDate subscriberDob) {
        this.subscriberDob = subscriberDob;
    }

    public String getSecondaryInsurerPolicyNo() {
        return this.secondaryInsurerPolicyNo;
    }

    public SecondaryClaimSubmisionMaster secondaryInsurerPolicyNo(String secondaryInsurerPolicyNo) {
        this.setSecondaryInsurerPolicyNo(secondaryInsurerPolicyNo);
        return this;
    }

    public void setSecondaryInsurerPolicyNo(String secondaryInsurerPolicyNo) {
        this.secondaryInsurerPolicyNo = secondaryInsurerPolicyNo;
    }

    public String getSubscriberAddressLine1() {
        return this.subscriberAddressLine1;
    }

    public SecondaryClaimSubmisionMaster subscriberAddressLine1(String subscriberAddressLine1) {
        this.setSubscriberAddressLine1(subscriberAddressLine1);
        return this;
    }

    public void setSubscriberAddressLine1(String subscriberAddressLine1) {
        this.subscriberAddressLine1 = subscriberAddressLine1;
    }

    public String getSubscriberCity() {
        return this.subscriberCity;
    }

    public SecondaryClaimSubmisionMaster subscriberCity(String subscriberCity) {
        this.setSubscriberCity(subscriberCity);
        return this;
    }

    public void setSubscriberCity(String subscriberCity) {
        this.subscriberCity = subscriberCity;
    }

    public String getSubscriberState() {
        return this.subscriberState;
    }

    public SecondaryClaimSubmisionMaster subscriberState(String subscriberState) {
        this.setSubscriberState(subscriberState);
        return this;
    }

    public void setSubscriberState(String subscriberState) {
        this.subscriberState = subscriberState;
    }

    public String getSubscriberZipCode() {
        return this.subscriberZipCode;
    }

    public SecondaryClaimSubmisionMaster subscriberZipCode(String subscriberZipCode) {
        this.setSubscriberZipCode(subscriberZipCode);
        return this;
    }

    public void setSubscriberZipCode(String subscriberZipCode) {
        this.subscriberZipCode = subscriberZipCode;
    }

    public String getProviderType() {
        return this.providerType;
    }

    public SecondaryClaimSubmisionMaster providerType(String providerType) {
        this.setProviderType(providerType);
        return this;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getBillingProviderNpi() {
        return this.billingProviderNpi;
    }

    public SecondaryClaimSubmisionMaster billingProviderNpi(String billingProviderNpi) {
        this.setBillingProviderNpi(billingProviderNpi);
        return this;
    }

    public void setBillingProviderNpi(String billingProviderNpi) {
        this.billingProviderNpi = billingProviderNpi;
    }

    public String getBillingProviderEin() {
        return this.billingProviderEin;
    }

    public SecondaryClaimSubmisionMaster billingProviderEin(String billingProviderEin) {
        this.setBillingProviderEin(billingProviderEin);
        return this;
    }

    public void setBillingProviderEin(String billingProviderEin) {
        this.billingProviderEin = billingProviderEin;
    }

    public String getBillingProviderOrganizationName() {
        return this.billingProviderOrganizationName;
    }

    public SecondaryClaimSubmisionMaster billingProviderOrganizationName(String billingProviderOrganizationName) {
        this.setBillingProviderOrganizationName(billingProviderOrganizationName);
        return this;
    }

    public void setBillingProviderOrganizationName(String billingProviderOrganizationName) {
        this.billingProviderOrganizationName = billingProviderOrganizationName;
    }

    public String getBillingProviderAddressLine1() {
        return this.billingProviderAddressLine1;
    }

    public SecondaryClaimSubmisionMaster billingProviderAddressLine1(String billingProviderAddressLine1) {
        this.setBillingProviderAddressLine1(billingProviderAddressLine1);
        return this;
    }

    public void setBillingProviderAddressLine1(String billingProviderAddressLine1) {
        this.billingProviderAddressLine1 = billingProviderAddressLine1;
    }

    public String getBillingProviderAddressLine2() {
        return this.billingProviderAddressLine2;
    }

    public SecondaryClaimSubmisionMaster billingProviderAddressLine2(String billingProviderAddressLine2) {
        this.setBillingProviderAddressLine2(billingProviderAddressLine2);
        return this;
    }

    public void setBillingProviderAddressLine2(String billingProviderAddressLine2) {
        this.billingProviderAddressLine2 = billingProviderAddressLine2;
    }

    public String getBillingProviderCity() {
        return this.billingProviderCity;
    }

    public SecondaryClaimSubmisionMaster billingProviderCity(String billingProviderCity) {
        this.setBillingProviderCity(billingProviderCity);
        return this;
    }

    public void setBillingProviderCity(String billingProviderCity) {
        this.billingProviderCity = billingProviderCity;
    }

    public String getBillingProviderState() {
        return this.billingProviderState;
    }

    public SecondaryClaimSubmisionMaster billingProviderState(String billingProviderState) {
        this.setBillingProviderState(billingProviderState);
        return this;
    }

    public void setBillingProviderState(String billingProviderState) {
        this.billingProviderState = billingProviderState;
    }

    public String getBillingProviderZipCode() {
        return this.billingProviderZipCode;
    }

    public SecondaryClaimSubmisionMaster billingProviderZipCode(String billingProviderZipCode) {
        this.setBillingProviderZipCode(billingProviderZipCode);
        return this;
    }

    public void setBillingProviderZipCode(String billingProviderZipCode) {
        this.billingProviderZipCode = billingProviderZipCode;
    }

    public String getBillingProviderContactPersonName() {
        return this.billingProviderContactPersonName;
    }

    public SecondaryClaimSubmisionMaster billingProviderContactPersonName(String billingProviderContactPersonName) {
        this.setBillingProviderContactPersonName(billingProviderContactPersonName);
        return this;
    }

    public void setBillingProviderContactPersonName(String billingProviderContactPersonName) {
        this.billingProviderContactPersonName = billingProviderContactPersonName;
    }

    public String getBillingProviderContactNo() {
        return this.billingProviderContactNo;
    }

    public SecondaryClaimSubmisionMaster billingProviderContactNo(String billingProviderContactNo) {
        this.setBillingProviderContactNo(billingProviderContactNo);
        return this;
    }

    public void setBillingProviderContactNo(String billingProviderContactNo) {
        this.billingProviderContactNo = billingProviderContactNo;
    }

    public String getClaimFilingCode() {
        return this.claimFilingCode;
    }

    public SecondaryClaimSubmisionMaster claimFilingCode(String claimFilingCode) {
        this.setClaimFilingCode(claimFilingCode);
        return this;
    }

    public void setClaimFilingCode(String claimFilingCode) {
        this.claimFilingCode = claimFilingCode;
    }

    public String getPatientAccountNo() {
        return this.patientAccountNo;
    }

    public SecondaryClaimSubmisionMaster patientAccountNo(String patientAccountNo) {
        this.setPatientAccountNo(patientAccountNo);
        return this;
    }

    public void setPatientAccountNo(String patientAccountNo) {
        this.patientAccountNo = patientAccountNo;
    }

    public Double getClaimChargeAmount() {
        return this.claimChargeAmount;
    }

    public SecondaryClaimSubmisionMaster claimChargeAmount(Double claimChargeAmount) {
        this.setClaimChargeAmount(claimChargeAmount);
        return this;
    }

    public void setClaimChargeAmount(Double claimChargeAmount) {
        this.claimChargeAmount = claimChargeAmount;
    }

    public String getPosCode() {
        return this.posCode;
    }

    public SecondaryClaimSubmisionMaster posCode(String posCode) {
        this.setPosCode(posCode);
        return this;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getClaimFrequencyCode() {
        return this.claimFrequencyCode;
    }

    public SecondaryClaimSubmisionMaster claimFrequencyCode(String claimFrequencyCode) {
        this.setClaimFrequencyCode(claimFrequencyCode);
        return this;
    }

    public void setClaimFrequencyCode(String claimFrequencyCode) {
        this.claimFrequencyCode = claimFrequencyCode;
    }

    public String getSignatureIndicator() {
        return this.signatureIndicator;
    }

    public SecondaryClaimSubmisionMaster signatureIndicator(String signatureIndicator) {
        this.setSignatureIndicator(signatureIndicator);
        return this;
    }

    public void setSignatureIndicator(String signatureIndicator) {
        this.signatureIndicator = signatureIndicator;
    }

    public String getPlanParticipationCode() {
        return this.planParticipationCode;
    }

    public SecondaryClaimSubmisionMaster planParticipationCode(String planParticipationCode) {
        this.setPlanParticipationCode(planParticipationCode);
        return this;
    }

    public void setPlanParticipationCode(String planParticipationCode) {
        this.planParticipationCode = planParticipationCode;
    }

    public String getBenefitsAssignmentCertificationIndicator() {
        return this.benefitsAssignmentCertificationIndicator;
    }

    public SecondaryClaimSubmisionMaster benefitsAssignmentCertificationIndicator(String benefitsAssignmentCertificationIndicator) {
        this.setBenefitsAssignmentCertificationIndicator(benefitsAssignmentCertificationIndicator);
        return this;
    }

    public void setBenefitsAssignmentCertificationIndicator(String benefitsAssignmentCertificationIndicator) {
        this.benefitsAssignmentCertificationIndicator = benefitsAssignmentCertificationIndicator;
    }

    public String getReleaseInformationCode() {
        return this.releaseInformationCode;
    }

    public SecondaryClaimSubmisionMaster releaseInformationCode(String releaseInformationCode) {
        this.setReleaseInformationCode(releaseInformationCode);
        return this;
    }

    public void setReleaseInformationCode(String releaseInformationCode) {
        this.releaseInformationCode = releaseInformationCode;
    }

    public String getPrimaryDiagnosis() {
        return this.primaryDiagnosis;
    }

    public SecondaryClaimSubmisionMaster primaryDiagnosis(String primaryDiagnosis) {
        this.setPrimaryDiagnosis(primaryDiagnosis);
        return this;
    }

    public void setPrimaryDiagnosis(String primaryDiagnosis) {
        this.primaryDiagnosis = primaryDiagnosis;
    }

    public String getIcd10DiagnosisCode1() {
        return this.icd10DiagnosisCode1;
    }

    public SecondaryClaimSubmisionMaster icd10DiagnosisCode1(String icd10DiagnosisCode1) {
        this.setIcd10DiagnosisCode1(icd10DiagnosisCode1);
        return this;
    }

    public void setIcd10DiagnosisCode1(String icd10DiagnosisCode1) {
        this.icd10DiagnosisCode1 = icd10DiagnosisCode1;
    }

    public String getIcd10DiagnosisCode2() {
        return this.icd10DiagnosisCode2;
    }

    public SecondaryClaimSubmisionMaster icd10DiagnosisCode2(String icd10DiagnosisCode2) {
        this.setIcd10DiagnosisCode2(icd10DiagnosisCode2);
        return this;
    }

    public void setIcd10DiagnosisCode2(String icd10DiagnosisCode2) {
        this.icd10DiagnosisCode2 = icd10DiagnosisCode2;
    }

    public String getIcd10DiagnosisCode3() {
        return this.icd10DiagnosisCode3;
    }

    public SecondaryClaimSubmisionMaster icd10DiagnosisCode3(String icd10DiagnosisCode3) {
        this.setIcd10DiagnosisCode3(icd10DiagnosisCode3);
        return this;
    }

    public void setIcd10DiagnosisCode3(String icd10DiagnosisCode3) {
        this.icd10DiagnosisCode3 = icd10DiagnosisCode3;
    }

    public String getIcd10DiagnosisCode4() {
        return this.icd10DiagnosisCode4;
    }

    public SecondaryClaimSubmisionMaster icd10DiagnosisCode4(String icd10DiagnosisCode4) {
        this.setIcd10DiagnosisCode4(icd10DiagnosisCode4);
        return this;
    }

    public void setIcd10DiagnosisCode4(String icd10DiagnosisCode4) {
        this.icd10DiagnosisCode4 = icd10DiagnosisCode4;
    }

    public String getIcd10DiagnosisCode5() {
        return this.icd10DiagnosisCode5;
    }

    public SecondaryClaimSubmisionMaster icd10DiagnosisCode5(String icd10DiagnosisCode5) {
        this.setIcd10DiagnosisCode5(icd10DiagnosisCode5);
        return this;
    }

    public void setIcd10DiagnosisCode5(String icd10DiagnosisCode5) {
        this.icd10DiagnosisCode5 = icd10DiagnosisCode5;
    }

    public String getIcd10DiagnosisCode6() {
        return this.icd10DiagnosisCode6;
    }

    public SecondaryClaimSubmisionMaster icd10DiagnosisCode6(String icd10DiagnosisCode6) {
        this.setIcd10DiagnosisCode6(icd10DiagnosisCode6);
        return this;
    }

    public void setIcd10DiagnosisCode6(String icd10DiagnosisCode6) {
        this.icd10DiagnosisCode6 = icd10DiagnosisCode6;
    }

    public String getIcd10DiagnosisCode7() {
        return this.icd10DiagnosisCode7;
    }

    public SecondaryClaimSubmisionMaster icd10DiagnosisCode7(String icd10DiagnosisCode7) {
        this.setIcd10DiagnosisCode7(icd10DiagnosisCode7);
        return this;
    }

    public void setIcd10DiagnosisCode7(String icd10DiagnosisCode7) {
        this.icd10DiagnosisCode7 = icd10DiagnosisCode7;
    }

    public String getIcd10DiagnosisCode8() {
        return this.icd10DiagnosisCode8;
    }

    public SecondaryClaimSubmisionMaster icd10DiagnosisCode8(String icd10DiagnosisCode8) {
        this.setIcd10DiagnosisCode8(icd10DiagnosisCode8);
        return this;
    }

    public void setIcd10DiagnosisCode8(String icd10DiagnosisCode8) {
        this.icd10DiagnosisCode8 = icd10DiagnosisCode8;
    }

    public String getIcd10DiagnosisCode9() {
        return this.icd10DiagnosisCode9;
    }

    public SecondaryClaimSubmisionMaster icd10DiagnosisCode9(String icd10DiagnosisCode9) {
        this.setIcd10DiagnosisCode9(icd10DiagnosisCode9);
        return this;
    }

    public void setIcd10DiagnosisCode9(String icd10DiagnosisCode9) {
        this.icd10DiagnosisCode9 = icd10DiagnosisCode9;
    }

    public String getIcd10DiagnosisCode10() {
        return this.icd10DiagnosisCode10;
    }

    public SecondaryClaimSubmisionMaster icd10DiagnosisCode10(String icd10DiagnosisCode10) {
        this.setIcd10DiagnosisCode10(icd10DiagnosisCode10);
        return this;
    }

    public void setIcd10DiagnosisCode10(String icd10DiagnosisCode10) {
        this.icd10DiagnosisCode10 = icd10DiagnosisCode10;
    }

    public String getIcd10DiagnosisCode11() {
        return this.icd10DiagnosisCode11;
    }

    public SecondaryClaimSubmisionMaster icd10DiagnosisCode11(String icd10DiagnosisCode11) {
        this.setIcd10DiagnosisCode11(icd10DiagnosisCode11);
        return this;
    }

    public void setIcd10DiagnosisCode11(String icd10DiagnosisCode11) {
        this.icd10DiagnosisCode11 = icd10DiagnosisCode11;
    }

    public String getIcd10DiagnosisCode12() {
        return this.icd10DiagnosisCode12;
    }

    public SecondaryClaimSubmisionMaster icd10DiagnosisCode12(String icd10DiagnosisCode12) {
        this.setIcd10DiagnosisCode12(icd10DiagnosisCode12);
        return this;
    }

    public void setIcd10DiagnosisCode12(String icd10DiagnosisCode12) {
        this.icd10DiagnosisCode12 = icd10DiagnosisCode12;
    }

    public String getInsertedById() {
        return this.insertedById;
    }

    public SecondaryClaimSubmisionMaster insertedById(String insertedById) {
        this.setInsertedById(insertedById);
        return this;
    }

    public void setInsertedById(String insertedById) {
        this.insertedById = insertedById;
    }

    public LocalDate getInsertedDate() {
        return this.insertedDate;
    }

    public SecondaryClaimSubmisionMaster insertedDate(LocalDate insertedDate) {
        this.setInsertedDate(insertedDate);
        return this;
    }

    public void setInsertedDate(LocalDate insertedDate) {
        this.insertedDate = insertedDate;
    }

    public String getBillingProviderType() {
        return this.billingProviderType;
    }

    public SecondaryClaimSubmisionMaster billingProviderType(String billingProviderType) {
        this.setBillingProviderType(billingProviderType);
        return this;
    }

    public void setBillingProviderType(String billingProviderType) {
        this.billingProviderType = billingProviderType;
    }

    public String getInsertedByName() {
        return this.insertedByName;
    }

    public SecondaryClaimSubmisionMaster insertedByName(String insertedByName) {
        this.setInsertedByName(insertedByName);
        return this;
    }

    public void setInsertedByName(String insertedByName) {
        this.insertedByName = insertedByName;
    }

    public String getStatus() {
        return this.status;
    }

    public SecondaryClaimSubmisionMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtherSubscriberAddress1() {
        return this.otherSubscriberAddress1;
    }

    public SecondaryClaimSubmisionMaster otherSubscriberAddress1(String otherSubscriberAddress1) {
        this.setOtherSubscriberAddress1(otherSubscriberAddress1);
        return this;
    }

    public void setOtherSubscriberAddress1(String otherSubscriberAddress1) {
        this.otherSubscriberAddress1 = otherSubscriberAddress1;
    }

    public String getOtherSubscriberAddress2() {
        return this.otherSubscriberAddress2;
    }

    public SecondaryClaimSubmisionMaster otherSubscriberAddress2(String otherSubscriberAddress2) {
        this.setOtherSubscriberAddress2(otherSubscriberAddress2);
        return this;
    }

    public void setOtherSubscriberAddress2(String otherSubscriberAddress2) {
        this.otherSubscriberAddress2 = otherSubscriberAddress2;
    }

    public String getOtherSubscriberCity() {
        return this.otherSubscriberCity;
    }

    public SecondaryClaimSubmisionMaster otherSubscriberCity(String otherSubscriberCity) {
        this.setOtherSubscriberCity(otherSubscriberCity);
        return this;
    }

    public void setOtherSubscriberCity(String otherSubscriberCity) {
        this.otherSubscriberCity = otherSubscriberCity;
    }

    public String getOtherSubscriberState() {
        return this.otherSubscriberState;
    }

    public SecondaryClaimSubmisionMaster otherSubscriberState(String otherSubscriberState) {
        this.setOtherSubscriberState(otherSubscriberState);
        return this;
    }

    public void setOtherSubscriberState(String otherSubscriberState) {
        this.otherSubscriberState = otherSubscriberState;
    }

    public String getOtherSubscriberZip() {
        return this.otherSubscriberZip;
    }

    public SecondaryClaimSubmisionMaster otherSubscriberZip(String otherSubscriberZip) {
        this.setOtherSubscriberZip(otherSubscriberZip);
        return this;
    }

    public void setOtherSubscriberZip(String otherSubscriberZip) {
        this.otherSubscriberZip = otherSubscriberZip;
    }

    public String getOtherInsuredQualifier() {
        return this.otherInsuredQualifier;
    }

    public SecondaryClaimSubmisionMaster otherInsuredQualifier(String otherInsuredQualifier) {
        this.setOtherInsuredQualifier(otherInsuredQualifier);
        return this;
    }

    public void setOtherInsuredQualifier(String otherInsuredQualifier) {
        this.otherInsuredQualifier = otherInsuredQualifier;
    }

    public String getOtherInsuredLastName() {
        return this.otherInsuredLastName;
    }

    public SecondaryClaimSubmisionMaster otherInsuredLastName(String otherInsuredLastName) {
        this.setOtherInsuredLastName(otherInsuredLastName);
        return this;
    }

    public void setOtherInsuredLastName(String otherInsuredLastName) {
        this.otherInsuredLastName = otherInsuredLastName;
    }

    public String getOtherInsuredFirstName() {
        return this.otherInsuredFirstName;
    }

    public SecondaryClaimSubmisionMaster otherInsuredFirstName(String otherInsuredFirstName) {
        this.setOtherInsuredFirstName(otherInsuredFirstName);
        return this;
    }

    public void setOtherInsuredFirstName(String otherInsuredFirstName) {
        this.otherInsuredFirstName = otherInsuredFirstName;
    }

    public String getOtherInsuredIdentifierTypeCode() {
        return this.otherInsuredIdentifierTypeCode;
    }

    public SecondaryClaimSubmisionMaster otherInsuredIdentifierTypeCode(String otherInsuredIdentifierTypeCode) {
        this.setOtherInsuredIdentifierTypeCode(otherInsuredIdentifierTypeCode);
        return this;
    }

    public void setOtherInsuredIdentifierTypeCode(String otherInsuredIdentifierTypeCode) {
        this.otherInsuredIdentifierTypeCode = otherInsuredIdentifierTypeCode;
    }

    public String getOtherInsuredIdentifier() {
        return this.otherInsuredIdentifier;
    }

    public SecondaryClaimSubmisionMaster otherInsuredIdentifier(String otherInsuredIdentifier) {
        this.setOtherInsuredIdentifier(otherInsuredIdentifier);
        return this;
    }

    public void setOtherInsuredIdentifier(String otherInsuredIdentifier) {
        this.otherInsuredIdentifier = otherInsuredIdentifier;
    }

    public String getOtherPayerOrganizationName() {
        return this.otherPayerOrganizationName;
    }

    public SecondaryClaimSubmisionMaster otherPayerOrganizationName(String otherPayerOrganizationName) {
        this.setOtherPayerOrganizationName(otherPayerOrganizationName);
        return this;
    }

    public void setOtherPayerOrganizationName(String otherPayerOrganizationName) {
        this.otherPayerOrganizationName = otherPayerOrganizationName;
    }

    public String getOtherPayerIdentifierTypeCode() {
        return this.otherPayerIdentifierTypeCode;
    }

    public SecondaryClaimSubmisionMaster otherPayerIdentifierTypeCode(String otherPayerIdentifierTypeCode) {
        this.setOtherPayerIdentifierTypeCode(otherPayerIdentifierTypeCode);
        return this;
    }

    public void setOtherPayerIdentifierTypeCode(String otherPayerIdentifierTypeCode) {
        this.otherPayerIdentifierTypeCode = otherPayerIdentifierTypeCode;
    }

    public String getOtherPayerIdentifier() {
        return this.otherPayerIdentifier;
    }

    public SecondaryClaimSubmisionMaster otherPayerIdentifier(String otherPayerIdentifier) {
        this.setOtherPayerIdentifier(otherPayerIdentifier);
        return this;
    }

    public void setOtherPayerIdentifier(String otherPayerIdentifier) {
        this.otherPayerIdentifier = otherPayerIdentifier;
    }

    public LocalDate getOtherPayerAdjudicationOrPaymentDate() {
        return this.otherPayerAdjudicationOrPaymentDate;
    }

    public SecondaryClaimSubmisionMaster otherPayerAdjudicationOrPaymentDate(LocalDate otherPayerAdjudicationOrPaymentDate) {
        this.setOtherPayerAdjudicationOrPaymentDate(otherPayerAdjudicationOrPaymentDate);
        return this;
    }

    public void setOtherPayerAdjudicationOrPaymentDate(LocalDate otherPayerAdjudicationOrPaymentDate) {
        this.otherPayerAdjudicationOrPaymentDate = otherPayerAdjudicationOrPaymentDate;
    }

    public String getOtherPayerClaimAdjustmentIndicator() {
        return this.otherPayerClaimAdjustmentIndicator;
    }

    public SecondaryClaimSubmisionMaster otherPayerClaimAdjustmentIndicator(String otherPayerClaimAdjustmentIndicator) {
        this.setOtherPayerClaimAdjustmentIndicator(otherPayerClaimAdjustmentIndicator);
        return this;
    }

    public void setOtherPayerClaimAdjustmentIndicator(String otherPayerClaimAdjustmentIndicator) {
        this.otherPayerClaimAdjustmentIndicator = otherPayerClaimAdjustmentIndicator;
    }

    public String getOtherPayerClaimControlNumber() {
        return this.otherPayerClaimControlNumber;
    }

    public SecondaryClaimSubmisionMaster otherPayerClaimControlNumber(String otherPayerClaimControlNumber) {
        this.setOtherPayerClaimControlNumber(otherPayerClaimControlNumber);
        return this;
    }

    public void setOtherPayerClaimControlNumber(String otherPayerClaimControlNumber) {
        this.otherPayerClaimControlNumber = otherPayerClaimControlNumber;
    }

    public Double getPayerPaidAmount() {
        return this.payerPaidAmount;
    }

    public SecondaryClaimSubmisionMaster payerPaidAmount(Double payerPaidAmount) {
        this.setPayerPaidAmount(payerPaidAmount);
        return this;
    }

    public void setPayerPaidAmount(Double payerPaidAmount) {
        this.payerPaidAmount = payerPaidAmount;
    }

    public String getPaymentResponsibilityLevelCode() {
        return this.paymentResponsibilityLevelCode;
    }

    public SecondaryClaimSubmisionMaster paymentResponsibilityLevelCode(String paymentResponsibilityLevelCode) {
        this.setPaymentResponsibilityLevelCode(paymentResponsibilityLevelCode);
        return this;
    }

    public void setPaymentResponsibilityLevelCode(String paymentResponsibilityLevelCode) {
        this.paymentResponsibilityLevelCode = paymentResponsibilityLevelCode;
    }

    public String getIndividualRelationshipCode() {
        return this.individualRelationshipCode;
    }

    public SecondaryClaimSubmisionMaster individualRelationshipCode(String individualRelationshipCode) {
        this.setIndividualRelationshipCode(individualRelationshipCode);
        return this;
    }

    public void setIndividualRelationshipCode(String individualRelationshipCode) {
        this.individualRelationshipCode = individualRelationshipCode;
    }

    public String getClaimFilingIndicatorCode() {
        return this.claimFilingIndicatorCode;
    }

    public SecondaryClaimSubmisionMaster claimFilingIndicatorCode(String claimFilingIndicatorCode) {
        this.setClaimFilingIndicatorCode(claimFilingIndicatorCode);
        return this;
    }

    public void setClaimFilingIndicatorCode(String claimFilingIndicatorCode) {
        this.claimFilingIndicatorCode = claimFilingIndicatorCode;
    }

    public String getOtherPayerBenefitsAssignmentCertificationIndicator() {
        return this.otherPayerBenefitsAssignmentCertificationIndicator;
    }

    public SecondaryClaimSubmisionMaster otherPayerBenefitsAssignmentCertificationIndicator(
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

    public SecondaryClaimSubmisionMaster releaseOfInformationCode(String releaseOfInformationCode) {
        this.setReleaseOfInformationCode(releaseOfInformationCode);
        return this;
    }

    public void setReleaseOfInformationCode(String releaseOfInformationCode) {
        this.releaseOfInformationCode = releaseOfInformationCode;
    }

    public Double getRemainingPatientLiability() {
        return this.remainingPatientLiability;
    }

    public SecondaryClaimSubmisionMaster remainingPatientLiability(Double remainingPatientLiability) {
        this.setRemainingPatientLiability(remainingPatientLiability);
        return this;
    }

    public void setRemainingPatientLiability(Double remainingPatientLiability) {
        this.remainingPatientLiability = remainingPatientLiability;
    }

    public String getPatientSignatureGeneratedForPatient() {
        return this.patientSignatureGeneratedForPatient;
    }

    public SecondaryClaimSubmisionMaster patientSignatureGeneratedForPatient(String patientSignatureGeneratedForPatient) {
        this.setPatientSignatureGeneratedForPatient(patientSignatureGeneratedForPatient);
        return this;
    }

    public void setPatientSignatureGeneratedForPatient(String patientSignatureGeneratedForPatient) {
        this.patientSignatureGeneratedForPatient = patientSignatureGeneratedForPatient;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SecondaryClaimSubmisionMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SecondaryClaimSubmisionMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public SecondaryClaimSubmisionMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getChangeHealthPrimarySubmisionMasterId() {
        return this.changeHealthPrimarySubmisionMasterId;
    }

    public SecondaryClaimSubmisionMaster changeHealthPrimarySubmisionMasterId(Long changeHealthPrimarySubmisionMasterId) {
        this.setChangeHealthPrimarySubmisionMasterId(changeHealthPrimarySubmisionMasterId);
        return this;
    }

    public void setChangeHealthPrimarySubmisionMasterId(Long changeHealthPrimarySubmisionMasterId) {
        this.changeHealthPrimarySubmisionMasterId = changeHealthPrimarySubmisionMasterId;
    }

    public String getInsuredFirstName() {
        return this.insuredFirstName;
    }

    public SecondaryClaimSubmisionMaster insuredFirstName(String insuredFirstName) {
        this.setInsuredFirstName(insuredFirstName);
        return this;
    }

    public void setInsuredFirstName(String insuredFirstName) {
        this.insuredFirstName = insuredFirstName;
    }

    public String getInsuredLastName() {
        return this.insuredLastName;
    }

    public SecondaryClaimSubmisionMaster insuredLastName(String insuredLastName) {
        this.setInsuredLastName(insuredLastName);
        return this;
    }

    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }

    public String getInsuredAddressLine1() {
        return this.insuredAddressLine1;
    }

    public SecondaryClaimSubmisionMaster insuredAddressLine1(String insuredAddressLine1) {
        this.setInsuredAddressLine1(insuredAddressLine1);
        return this;
    }

    public void setInsuredAddressLine1(String insuredAddressLine1) {
        this.insuredAddressLine1 = insuredAddressLine1;
    }

    public String getInsuredAddressLine2() {
        return this.insuredAddressLine2;
    }

    public SecondaryClaimSubmisionMaster insuredAddressLine2(String insuredAddressLine2) {
        this.setInsuredAddressLine2(insuredAddressLine2);
        return this;
    }

    public void setInsuredAddressLine2(String insuredAddressLine2) {
        this.insuredAddressLine2 = insuredAddressLine2;
    }

    public String getInsuredCity() {
        return this.insuredCity;
    }

    public SecondaryClaimSubmisionMaster insuredCity(String insuredCity) {
        this.setInsuredCity(insuredCity);
        return this;
    }

    public void setInsuredCity(String insuredCity) {
        this.insuredCity = insuredCity;
    }

    public String getInsuredState() {
        return this.insuredState;
    }

    public SecondaryClaimSubmisionMaster insuredState(String insuredState) {
        this.setInsuredState(insuredState);
        return this;
    }

    public void setInsuredState(String insuredState) {
        this.insuredState = insuredState;
    }

    public String getInsuredZip() {
        return this.insuredZip;
    }

    public SecondaryClaimSubmisionMaster insuredZip(String insuredZip) {
        this.setInsuredZip(insuredZip);
        return this;
    }

    public void setInsuredZip(String insuredZip) {
        this.insuredZip = insuredZip;
    }

    public String getInsuredContactNo() {
        return this.insuredContactNo;
    }

    public SecondaryClaimSubmisionMaster insuredContactNo(String insuredContactNo) {
        this.setInsuredContactNo(insuredContactNo);
        return this;
    }

    public void setInsuredContactNo(String insuredContactNo) {
        this.insuredContactNo = insuredContactNo;
    }

    public LocalDate getInsuredDob() {
        return this.insuredDob;
    }

    public SecondaryClaimSubmisionMaster insuredDob(LocalDate insuredDob) {
        this.setInsuredDob(insuredDob);
        return this;
    }

    public void setInsuredDob(LocalDate insuredDob) {
        this.insuredDob = insuredDob;
    }

    public String getInsuredGender() {
        return this.insuredGender;
    }

    public SecondaryClaimSubmisionMaster insuredGender(String insuredGender) {
        this.setInsuredGender(insuredGender);
        return this;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public String getOrderingProviderFirstName() {
        return this.orderingProviderFirstName;
    }

    public SecondaryClaimSubmisionMaster orderingProviderFirstName(String orderingProviderFirstName) {
        this.setOrderingProviderFirstName(orderingProviderFirstName);
        return this;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderLastName() {
        return this.orderingProviderLastName;
    }

    public SecondaryClaimSubmisionMaster orderingProviderLastName(String orderingProviderLastName) {
        this.setOrderingProviderLastName(orderingProviderLastName);
        return this;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return this.orderingProviderNpi;
    }

    public SecondaryClaimSubmisionMaster orderingProviderNpi(String orderingProviderNpi) {
        this.setOrderingProviderNpi(orderingProviderNpi);
        return this;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getPatientRelationshipInsured() {
        return this.patientRelationshipInsured;
    }

    public SecondaryClaimSubmisionMaster patientRelationshipInsured(String patientRelationshipInsured) {
        this.setPatientRelationshipInsured(patientRelationshipInsured);
        return this;
    }

    public void setPatientRelationshipInsured(String patientRelationshipInsured) {
        this.patientRelationshipInsured = patientRelationshipInsured;
    }

    public String getPatientConditionEmployment() {
        return this.patientConditionEmployment;
    }

    public SecondaryClaimSubmisionMaster patientConditionEmployment(String patientConditionEmployment) {
        this.setPatientConditionEmployment(patientConditionEmployment);
        return this;
    }

    public void setPatientConditionEmployment(String patientConditionEmployment) {
        this.patientConditionEmployment = patientConditionEmployment;
    }

    public String getPatientConditionAutoAccident() {
        return this.patientConditionAutoAccident;
    }

    public SecondaryClaimSubmisionMaster patientConditionAutoAccident(String patientConditionAutoAccident) {
        this.setPatientConditionAutoAccident(patientConditionAutoAccident);
        return this;
    }

    public void setPatientConditionAutoAccident(String patientConditionAutoAccident) {
        this.patientConditionAutoAccident = patientConditionAutoAccident;
    }

    public String getPatientConditionOtherAccident() {
        return this.patientConditionOtherAccident;
    }

    public SecondaryClaimSubmisionMaster patientConditionOtherAccident(String patientConditionOtherAccident) {
        this.setPatientConditionOtherAccident(patientConditionOtherAccident);
        return this;
    }

    public void setPatientConditionOtherAccident(String patientConditionOtherAccident) {
        this.patientConditionOtherAccident = patientConditionOtherAccident;
    }

    public String getIsNextLevelInsurerPresentStatus() {
        return this.isNextLevelInsurerPresentStatus;
    }

    public SecondaryClaimSubmisionMaster isNextLevelInsurerPresentStatus(String isNextLevelInsurerPresentStatus) {
        this.setIsNextLevelInsurerPresentStatus(isNextLevelInsurerPresentStatus);
        return this;
    }

    public void setIsNextLevelInsurerPresentStatus(String isNextLevelInsurerPresentStatus) {
        this.isNextLevelInsurerPresentStatus = isNextLevelInsurerPresentStatus;
    }

    public LocalDate getOriginalDos() {
        return this.originalDos;
    }

    public SecondaryClaimSubmisionMaster originalDos(LocalDate originalDos) {
        this.setOriginalDos(originalDos);
        return this;
    }

    public void setOriginalDos(LocalDate originalDos) {
        this.originalDos = originalDos;
    }

    public String getParNo() {
        return this.parNo;
    }

    public SecondaryClaimSubmisionMaster parNo(String parNo) {
        this.setParNo(parNo);
        return this;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public String getBillingProviderTaxonomy() {
        return this.billingProviderTaxonomy;
    }

    public SecondaryClaimSubmisionMaster billingProviderTaxonomy(String billingProviderTaxonomy) {
        this.setBillingProviderTaxonomy(billingProviderTaxonomy);
        return this;
    }

    public void setBillingProviderTaxonomy(String billingProviderTaxonomy) {
        this.billingProviderTaxonomy = billingProviderTaxonomy;
    }

    public String getServiceProviderNpi() {
        return this.serviceProviderNpi;
    }

    public SecondaryClaimSubmisionMaster serviceProviderNpi(String serviceProviderNpi) {
        this.setServiceProviderNpi(serviceProviderNpi);
        return this;
    }

    public void setServiceProviderNpi(String serviceProviderNpi) {
        this.serviceProviderNpi = serviceProviderNpi;
    }

    public String getServiceProviderOrganisationName() {
        return this.serviceProviderOrganisationName;
    }

    public SecondaryClaimSubmisionMaster serviceProviderOrganisationName(String serviceProviderOrganisationName) {
        this.setServiceProviderOrganisationName(serviceProviderOrganisationName);
        return this;
    }

    public void setServiceProviderOrganisationName(String serviceProviderOrganisationName) {
        this.serviceProviderOrganisationName = serviceProviderOrganisationName;
    }

    public String getServiceProviderAddressLine1() {
        return this.serviceProviderAddressLine1;
    }

    public SecondaryClaimSubmisionMaster serviceProviderAddressLine1(String serviceProviderAddressLine1) {
        this.setServiceProviderAddressLine1(serviceProviderAddressLine1);
        return this;
    }

    public void setServiceProviderAddressLine1(String serviceProviderAddressLine1) {
        this.serviceProviderAddressLine1 = serviceProviderAddressLine1;
    }

    public String getServiceProviderAddressLine2() {
        return this.serviceProviderAddressLine2;
    }

    public SecondaryClaimSubmisionMaster serviceProviderAddressLine2(String serviceProviderAddressLine2) {
        this.setServiceProviderAddressLine2(serviceProviderAddressLine2);
        return this;
    }

    public void setServiceProviderAddressLine2(String serviceProviderAddressLine2) {
        this.serviceProviderAddressLine2 = serviceProviderAddressLine2;
    }

    public String getServiceProviderCity() {
        return this.serviceProviderCity;
    }

    public SecondaryClaimSubmisionMaster serviceProviderCity(String serviceProviderCity) {
        this.setServiceProviderCity(serviceProviderCity);
        return this;
    }

    public void setServiceProviderCity(String serviceProviderCity) {
        this.serviceProviderCity = serviceProviderCity;
    }

    public String getServiceProviderState() {
        return this.serviceProviderState;
    }

    public SecondaryClaimSubmisionMaster serviceProviderState(String serviceProviderState) {
        this.setServiceProviderState(serviceProviderState);
        return this;
    }

    public void setServiceProviderState(String serviceProviderState) {
        this.serviceProviderState = serviceProviderState;
    }

    public String getServiceProviderCountry() {
        return this.serviceProviderCountry;
    }

    public SecondaryClaimSubmisionMaster serviceProviderCountry(String serviceProviderCountry) {
        this.setServiceProviderCountry(serviceProviderCountry);
        return this;
    }

    public void setServiceProviderCountry(String serviceProviderCountry) {
        this.serviceProviderCountry = serviceProviderCountry;
    }

    public String getServiceProviderZipCode() {
        return this.serviceProviderZipCode;
    }

    public SecondaryClaimSubmisionMaster serviceProviderZipCode(String serviceProviderZipCode) {
        this.setServiceProviderZipCode(serviceProviderZipCode);
        return this;
    }

    public void setServiceProviderZipCode(String serviceProviderZipCode) {
        this.serviceProviderZipCode = serviceProviderZipCode;
    }

    public String getServiceProviderTaxonomy() {
        return this.serviceProviderTaxonomy;
    }

    public SecondaryClaimSubmisionMaster serviceProviderTaxonomy(String serviceProviderTaxonomy) {
        this.setServiceProviderTaxonomy(serviceProviderTaxonomy);
        return this;
    }

    public void setServiceProviderTaxonomy(String serviceProviderTaxonomy) {
        this.serviceProviderTaxonomy = serviceProviderTaxonomy;
    }

    public String getCms1500FormName() {
        return this.cms1500FormName;
    }

    public SecondaryClaimSubmisionMaster cms1500FormName(String cms1500FormName) {
        this.setCms1500FormName(cms1500FormName);
        return this;
    }

    public void setCms1500FormName(String cms1500FormName) {
        this.cms1500FormName = cms1500FormName;
    }

    public String getTradingPartnerAddressLine1() {
        return this.tradingPartnerAddressLine1;
    }

    public SecondaryClaimSubmisionMaster tradingPartnerAddressLine1(String tradingPartnerAddressLine1) {
        this.setTradingPartnerAddressLine1(tradingPartnerAddressLine1);
        return this;
    }

    public void setTradingPartnerAddressLine1(String tradingPartnerAddressLine1) {
        this.tradingPartnerAddressLine1 = tradingPartnerAddressLine1;
    }

    public String getTradingPartnerAddressLine2() {
        return this.tradingPartnerAddressLine2;
    }

    public SecondaryClaimSubmisionMaster tradingPartnerAddressLine2(String tradingPartnerAddressLine2) {
        this.setTradingPartnerAddressLine2(tradingPartnerAddressLine2);
        return this;
    }

    public void setTradingPartnerAddressLine2(String tradingPartnerAddressLine2) {
        this.tradingPartnerAddressLine2 = tradingPartnerAddressLine2;
    }

    public String getTradingPartnerCity() {
        return this.tradingPartnerCity;
    }

    public SecondaryClaimSubmisionMaster tradingPartnerCity(String tradingPartnerCity) {
        this.setTradingPartnerCity(tradingPartnerCity);
        return this;
    }

    public void setTradingPartnerCity(String tradingPartnerCity) {
        this.tradingPartnerCity = tradingPartnerCity;
    }

    public String getTradingPartnerState() {
        return this.tradingPartnerState;
    }

    public SecondaryClaimSubmisionMaster tradingPartnerState(String tradingPartnerState) {
        this.setTradingPartnerState(tradingPartnerState);
        return this;
    }

    public void setTradingPartnerState(String tradingPartnerState) {
        this.tradingPartnerState = tradingPartnerState;
    }

    public String getTradingPartnerZip() {
        return this.tradingPartnerZip;
    }

    public SecondaryClaimSubmisionMaster tradingPartnerZip(String tradingPartnerZip) {
        this.setTradingPartnerZip(tradingPartnerZip);
        return this;
    }

    public void setTradingPartnerZip(String tradingPartnerZip) {
        this.tradingPartnerZip = tradingPartnerZip;
    }

    public String getDiagnosisCodeType() {
        return this.diagnosisCodeType;
    }

    public SecondaryClaimSubmisionMaster diagnosisCodeType(String diagnosisCodeType) {
        this.setDiagnosisCodeType(diagnosisCodeType);
        return this;
    }

    public void setDiagnosisCodeType(String diagnosisCodeType) {
        this.diagnosisCodeType = diagnosisCodeType;
    }

    public UUID getSecondaryClaimSubmisionMasterUuid() {
        return this.secondaryClaimSubmisionMasterUuid;
    }

    public SecondaryClaimSubmisionMaster secondaryClaimSubmisionMasterUuid(UUID secondaryClaimSubmisionMasterUuid) {
        this.setSecondaryClaimSubmisionMasterUuid(secondaryClaimSubmisionMasterUuid);
        return this;
    }

    public void setSecondaryClaimSubmisionMasterUuid(UUID secondaryClaimSubmisionMasterUuid) {
        this.secondaryClaimSubmisionMasterUuid = secondaryClaimSubmisionMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SecondaryClaimSubmisionMaster)) {
            return false;
        }
        return (
            changeHealthSecondarySubmisionMasterId != null &&
            changeHealthSecondarySubmisionMasterId.equals(((SecondaryClaimSubmisionMaster) o).changeHealthSecondarySubmisionMasterId)
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
        return "SecondaryClaimSubmisionMaster{" +
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
            ", secondaryInsurerPolicyNo='" + getSecondaryInsurerPolicyNo() + "'" +
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
            ", icd10DiagnosisCode1='" + getIcd10DiagnosisCode1() + "'" +
            ", icd10DiagnosisCode2='" + getIcd10DiagnosisCode2() + "'" +
            ", icd10DiagnosisCode3='" + getIcd10DiagnosisCode3() + "'" +
            ", icd10DiagnosisCode4='" + getIcd10DiagnosisCode4() + "'" +
            ", icd10DiagnosisCode5='" + getIcd10DiagnosisCode5() + "'" +
            ", icd10DiagnosisCode6='" + getIcd10DiagnosisCode6() + "'" +
            ", icd10DiagnosisCode7='" + getIcd10DiagnosisCode7() + "'" +
            ", icd10DiagnosisCode8='" + getIcd10DiagnosisCode8() + "'" +
            ", icd10DiagnosisCode9='" + getIcd10DiagnosisCode9() + "'" +
            ", icd10DiagnosisCode10='" + getIcd10DiagnosisCode10() + "'" +
            ", icd10DiagnosisCode11='" + getIcd10DiagnosisCode11() + "'" +
            ", icd10DiagnosisCode12='" + getIcd10DiagnosisCode12() + "'" +
            ", insertedById='" + getInsertedById() + "'" +
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
            ", secondaryClaimSubmisionMasterUuid='" + getSecondaryClaimSubmisionMasterUuid() + "'" +
            "}";
    }
}
