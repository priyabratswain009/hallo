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
 * A ClaimsSubmissionMaster.
 */
@Entity
@Table(name = "t_primary_claim_submision_master")
public class ClaimsSubmissionMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "change_health_primary_submision_master_id", nullable = false)
    private Long changeHealthPrimarySubmisionMasterId;

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

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private ZonedDateTime updatedDate;

    @NotNull
    @Column(name = "billing_provider_type", nullable = false)
    private String billingProviderType;

    @Column(name = "inserted_by_name")
    private String insertedByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "status")
    private String status;

    @Column(name = "billing_provider_address_line_2")
    private String billingProviderAddressLine2;

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

    @OneToMany(mappedBy = "claimsSubmissionMaster")
    @JsonIgnoreProperties(value = { "claimsSubmissionMaster" }, allowSetters = true)
    private Set<ServiceLinesMaster> serviceLines = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getChangeHealthPrimarySubmisionMasterId() {
        return this.changeHealthPrimarySubmisionMasterId;
    }

    public ClaimsSubmissionMaster changeHealthPrimarySubmisionMasterId(Long changeHealthPrimarySubmisionMasterId) {
        this.setChangeHealthPrimarySubmisionMasterId(changeHealthPrimarySubmisionMasterId);
        return this;
    }

    public void setChangeHealthPrimarySubmisionMasterId(Long changeHealthPrimarySubmisionMasterId) {
        this.changeHealthPrimarySubmisionMasterId = changeHealthPrimarySubmisionMasterId;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public ClaimsSubmissionMaster salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getClaimControlNo() {
        return this.claimControlNo;
    }

    public ClaimsSubmissionMaster claimControlNo(String claimControlNo) {
        this.setClaimControlNo(claimControlNo);
        return this;
    }

    public void setClaimControlNo(String claimControlNo) {
        this.claimControlNo = claimControlNo;
    }

    public String getTradingPartnerServiceId() {
        return this.tradingPartnerServiceId;
    }

    public ClaimsSubmissionMaster tradingPartnerServiceId(String tradingPartnerServiceId) {
        this.setTradingPartnerServiceId(tradingPartnerServiceId);
        return this;
    }

    public void setTradingPartnerServiceId(String tradingPartnerServiceId) {
        this.tradingPartnerServiceId = tradingPartnerServiceId;
    }

    public String getTradingPartnerName() {
        return this.tradingPartnerName;
    }

    public ClaimsSubmissionMaster tradingPartnerName(String tradingPartnerName) {
        this.setTradingPartnerName(tradingPartnerName);
        return this;
    }

    public void setTradingPartnerName(String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    public String getSubmitterOrganizationName() {
        return this.submitterOrganizationName;
    }

    public ClaimsSubmissionMaster submitterOrganizationName(String submitterOrganizationName) {
        this.setSubmitterOrganizationName(submitterOrganizationName);
        return this;
    }

    public void setSubmitterOrganizationName(String submitterOrganizationName) {
        this.submitterOrganizationName = submitterOrganizationName;
    }

    public String getSubmitterContactPersonName() {
        return this.submitterContactPersonName;
    }

    public ClaimsSubmissionMaster submitterContactPersonName(String submitterContactPersonName) {
        this.setSubmitterContactPersonName(submitterContactPersonName);
        return this;
    }

    public void setSubmitterContactPersonName(String submitterContactPersonName) {
        this.submitterContactPersonName = submitterContactPersonName;
    }

    public String getSubmitterContactNo() {
        return this.submitterContactNo;
    }

    public ClaimsSubmissionMaster submitterContactNo(String submitterContactNo) {
        this.setSubmitterContactNo(submitterContactNo);
        return this;
    }

    public void setSubmitterContactNo(String submitterContactNo) {
        this.submitterContactNo = submitterContactNo;
    }

    public String getReceiverOrganizationName() {
        return this.receiverOrganizationName;
    }

    public ClaimsSubmissionMaster receiverOrganizationName(String receiverOrganizationName) {
        this.setReceiverOrganizationName(receiverOrganizationName);
        return this;
    }

    public void setReceiverOrganizationName(String receiverOrganizationName) {
        this.receiverOrganizationName = receiverOrganizationName;
    }

    public String getSubscriberMemberIdNo() {
        return this.subscriberMemberIdNo;
    }

    public ClaimsSubmissionMaster subscriberMemberIdNo(String subscriberMemberIdNo) {
        this.setSubscriberMemberIdNo(subscriberMemberIdNo);
        return this;
    }

    public void setSubscriberMemberIdNo(String subscriberMemberIdNo) {
        this.subscriberMemberIdNo = subscriberMemberIdNo;
    }

    public String getSubscriberPaymentResponsibilityLevelCode() {
        return this.subscriberPaymentResponsibilityLevelCode;
    }

    public ClaimsSubmissionMaster subscriberPaymentResponsibilityLevelCode(String subscriberPaymentResponsibilityLevelCode) {
        this.setSubscriberPaymentResponsibilityLevelCode(subscriberPaymentResponsibilityLevelCode);
        return this;
    }

    public void setSubscriberPaymentResponsibilityLevelCode(String subscriberPaymentResponsibilityLevelCode) {
        this.subscriberPaymentResponsibilityLevelCode = subscriberPaymentResponsibilityLevelCode;
    }

    public String getSubscriberFirstName() {
        return this.subscriberFirstName;
    }

    public ClaimsSubmissionMaster subscriberFirstName(String subscriberFirstName) {
        this.setSubscriberFirstName(subscriberFirstName);
        return this;
    }

    public void setSubscriberFirstName(String subscriberFirstName) {
        this.subscriberFirstName = subscriberFirstName;
    }

    public String getSubscriberLastName() {
        return this.subscriberLastName;
    }

    public ClaimsSubmissionMaster subscriberLastName(String subscriberLastName) {
        this.setSubscriberLastName(subscriberLastName);
        return this;
    }

    public void setSubscriberLastName(String subscriberLastName) {
        this.subscriberLastName = subscriberLastName;
    }

    public String getSubscriberGender() {
        return this.subscriberGender;
    }

    public ClaimsSubmissionMaster subscriberGender(String subscriberGender) {
        this.setSubscriberGender(subscriberGender);
        return this;
    }

    public void setSubscriberGender(String subscriberGender) {
        this.subscriberGender = subscriberGender;
    }

    public LocalDate getSubscriberDob() {
        return this.subscriberDob;
    }

    public ClaimsSubmissionMaster subscriberDob(LocalDate subscriberDob) {
        this.setSubscriberDob(subscriberDob);
        return this;
    }

    public void setSubscriberDob(LocalDate subscriberDob) {
        this.subscriberDob = subscriberDob;
    }

    public String getPrimaryInsurerPolicyNo() {
        return this.primaryInsurerPolicyNo;
    }

    public ClaimsSubmissionMaster primaryInsurerPolicyNo(String primaryInsurerPolicyNo) {
        this.setPrimaryInsurerPolicyNo(primaryInsurerPolicyNo);
        return this;
    }

    public void setPrimaryInsurerPolicyNo(String primaryInsurerPolicyNo) {
        this.primaryInsurerPolicyNo = primaryInsurerPolicyNo;
    }

    public String getSubscriberAddressLine1() {
        return this.subscriberAddressLine1;
    }

    public ClaimsSubmissionMaster subscriberAddressLine1(String subscriberAddressLine1) {
        this.setSubscriberAddressLine1(subscriberAddressLine1);
        return this;
    }

    public void setSubscriberAddressLine1(String subscriberAddressLine1) {
        this.subscriberAddressLine1 = subscriberAddressLine1;
    }

    public String getSubscriberCity() {
        return this.subscriberCity;
    }

    public ClaimsSubmissionMaster subscriberCity(String subscriberCity) {
        this.setSubscriberCity(subscriberCity);
        return this;
    }

    public void setSubscriberCity(String subscriberCity) {
        this.subscriberCity = subscriberCity;
    }

    public String getSubscriberState() {
        return this.subscriberState;
    }

    public ClaimsSubmissionMaster subscriberState(String subscriberState) {
        this.setSubscriberState(subscriberState);
        return this;
    }

    public void setSubscriberState(String subscriberState) {
        this.subscriberState = subscriberState;
    }

    public String getSubscriberZipCode() {
        return this.subscriberZipCode;
    }

    public ClaimsSubmissionMaster subscriberZipCode(String subscriberZipCode) {
        this.setSubscriberZipCode(subscriberZipCode);
        return this;
    }

    public void setSubscriberZipCode(String subscriberZipCode) {
        this.subscriberZipCode = subscriberZipCode;
    }

    public String getBillingProviderNpi() {
        return this.billingProviderNpi;
    }

    public ClaimsSubmissionMaster billingProviderNpi(String billingProviderNpi) {
        this.setBillingProviderNpi(billingProviderNpi);
        return this;
    }

    public void setBillingProviderNpi(String billingProviderNpi) {
        this.billingProviderNpi = billingProviderNpi;
    }

    public String getBillingProviderEin() {
        return this.billingProviderEin;
    }

    public ClaimsSubmissionMaster billingProviderEin(String billingProviderEin) {
        this.setBillingProviderEin(billingProviderEin);
        return this;
    }

    public void setBillingProviderEin(String billingProviderEin) {
        this.billingProviderEin = billingProviderEin;
    }

    public String getBillingProviderOrganizationName() {
        return this.billingProviderOrganizationName;
    }

    public ClaimsSubmissionMaster billingProviderOrganizationName(String billingProviderOrganizationName) {
        this.setBillingProviderOrganizationName(billingProviderOrganizationName);
        return this;
    }

    public void setBillingProviderOrganizationName(String billingProviderOrganizationName) {
        this.billingProviderOrganizationName = billingProviderOrganizationName;
    }

    public String getBillingProviderAddressLine1() {
        return this.billingProviderAddressLine1;
    }

    public ClaimsSubmissionMaster billingProviderAddressLine1(String billingProviderAddressLine1) {
        this.setBillingProviderAddressLine1(billingProviderAddressLine1);
        return this;
    }

    public void setBillingProviderAddressLine1(String billingProviderAddressLine1) {
        this.billingProviderAddressLine1 = billingProviderAddressLine1;
    }

    public String getBillingProviderCity() {
        return this.billingProviderCity;
    }

    public ClaimsSubmissionMaster billingProviderCity(String billingProviderCity) {
        this.setBillingProviderCity(billingProviderCity);
        return this;
    }

    public void setBillingProviderCity(String billingProviderCity) {
        this.billingProviderCity = billingProviderCity;
    }

    public String getBillingProviderState() {
        return this.billingProviderState;
    }

    public ClaimsSubmissionMaster billingProviderState(String billingProviderState) {
        this.setBillingProviderState(billingProviderState);
        return this;
    }

    public void setBillingProviderState(String billingProviderState) {
        this.billingProviderState = billingProviderState;
    }

    public String getBillingProviderZipCode() {
        return this.billingProviderZipCode;
    }

    public ClaimsSubmissionMaster billingProviderZipCode(String billingProviderZipCode) {
        this.setBillingProviderZipCode(billingProviderZipCode);
        return this;
    }

    public void setBillingProviderZipCode(String billingProviderZipCode) {
        this.billingProviderZipCode = billingProviderZipCode;
    }

    public String getBillingProviderContactPersonName() {
        return this.billingProviderContactPersonName;
    }

    public ClaimsSubmissionMaster billingProviderContactPersonName(String billingProviderContactPersonName) {
        this.setBillingProviderContactPersonName(billingProviderContactPersonName);
        return this;
    }

    public void setBillingProviderContactPersonName(String billingProviderContactPersonName) {
        this.billingProviderContactPersonName = billingProviderContactPersonName;
    }

    public String getBillingProviderContactNo() {
        return this.billingProviderContactNo;
    }

    public ClaimsSubmissionMaster billingProviderContactNo(String billingProviderContactNo) {
        this.setBillingProviderContactNo(billingProviderContactNo);
        return this;
    }

    public void setBillingProviderContactNo(String billingProviderContactNo) {
        this.billingProviderContactNo = billingProviderContactNo;
    }

    public String getClaimFilingCode() {
        return this.claimFilingCode;
    }

    public ClaimsSubmissionMaster claimFilingCode(String claimFilingCode) {
        this.setClaimFilingCode(claimFilingCode);
        return this;
    }

    public void setClaimFilingCode(String claimFilingCode) {
        this.claimFilingCode = claimFilingCode;
    }

    public Double getClaimChargeAmount() {
        return this.claimChargeAmount;
    }

    public ClaimsSubmissionMaster claimChargeAmount(Double claimChargeAmount) {
        this.setClaimChargeAmount(claimChargeAmount);
        return this;
    }

    public void setClaimChargeAmount(Double claimChargeAmount) {
        this.claimChargeAmount = claimChargeAmount;
    }

    public String getPosCode() {
        return this.posCode;
    }

    public ClaimsSubmissionMaster posCode(String posCode) {
        this.setPosCode(posCode);
        return this;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getClaimFrequencyCode() {
        return this.claimFrequencyCode;
    }

    public ClaimsSubmissionMaster claimFrequencyCode(String claimFrequencyCode) {
        this.setClaimFrequencyCode(claimFrequencyCode);
        return this;
    }

    public void setClaimFrequencyCode(String claimFrequencyCode) {
        this.claimFrequencyCode = claimFrequencyCode;
    }

    public String getSignatureIndicator() {
        return this.signatureIndicator;
    }

    public ClaimsSubmissionMaster signatureIndicator(String signatureIndicator) {
        this.setSignatureIndicator(signatureIndicator);
        return this;
    }

    public void setSignatureIndicator(String signatureIndicator) {
        this.signatureIndicator = signatureIndicator;
    }

    public String getPlanParticipationCode() {
        return this.planParticipationCode;
    }

    public ClaimsSubmissionMaster planParticipationCode(String planParticipationCode) {
        this.setPlanParticipationCode(planParticipationCode);
        return this;
    }

    public void setPlanParticipationCode(String planParticipationCode) {
        this.planParticipationCode = planParticipationCode;
    }

    public String getBenefitsAssignmentCertificationIndicator() {
        return this.benefitsAssignmentCertificationIndicator;
    }

    public ClaimsSubmissionMaster benefitsAssignmentCertificationIndicator(String benefitsAssignmentCertificationIndicator) {
        this.setBenefitsAssignmentCertificationIndicator(benefitsAssignmentCertificationIndicator);
        return this;
    }

    public void setBenefitsAssignmentCertificationIndicator(String benefitsAssignmentCertificationIndicator) {
        this.benefitsAssignmentCertificationIndicator = benefitsAssignmentCertificationIndicator;
    }

    public String getReleaseInformationCode() {
        return this.releaseInformationCode;
    }

    public ClaimsSubmissionMaster releaseInformationCode(String releaseInformationCode) {
        this.setReleaseInformationCode(releaseInformationCode);
        return this;
    }

    public void setReleaseInformationCode(String releaseInformationCode) {
        this.releaseInformationCode = releaseInformationCode;
    }

    public String getPrimaryDiagnosis() {
        return this.primaryDiagnosis;
    }

    public ClaimsSubmissionMaster primaryDiagnosis(String primaryDiagnosis) {
        this.setPrimaryDiagnosis(primaryDiagnosis);
        return this;
    }

    public void setPrimaryDiagnosis(String primaryDiagnosis) {
        this.primaryDiagnosis = primaryDiagnosis;
    }

    public String getIcd10diagnosisCode1() {
        return this.icd10diagnosisCode1;
    }

    public ClaimsSubmissionMaster icd10diagnosisCode1(String icd10diagnosisCode1) {
        this.setIcd10diagnosisCode1(icd10diagnosisCode1);
        return this;
    }

    public void setIcd10diagnosisCode1(String icd10diagnosisCode1) {
        this.icd10diagnosisCode1 = icd10diagnosisCode1;
    }

    public String getIcd10diagnosisCode2() {
        return this.icd10diagnosisCode2;
    }

    public ClaimsSubmissionMaster icd10diagnosisCode2(String icd10diagnosisCode2) {
        this.setIcd10diagnosisCode2(icd10diagnosisCode2);
        return this;
    }

    public void setIcd10diagnosisCode2(String icd10diagnosisCode2) {
        this.icd10diagnosisCode2 = icd10diagnosisCode2;
    }

    public String getIcd10diagnosisCode3() {
        return this.icd10diagnosisCode3;
    }

    public ClaimsSubmissionMaster icd10diagnosisCode3(String icd10diagnosisCode3) {
        this.setIcd10diagnosisCode3(icd10diagnosisCode3);
        return this;
    }

    public void setIcd10diagnosisCode3(String icd10diagnosisCode3) {
        this.icd10diagnosisCode3 = icd10diagnosisCode3;
    }

    public String getIcd10diagnosisCode4() {
        return this.icd10diagnosisCode4;
    }

    public ClaimsSubmissionMaster icd10diagnosisCode4(String icd10diagnosisCode4) {
        this.setIcd10diagnosisCode4(icd10diagnosisCode4);
        return this;
    }

    public void setIcd10diagnosisCode4(String icd10diagnosisCode4) {
        this.icd10diagnosisCode4 = icd10diagnosisCode4;
    }

    public String getIcd10diagnosisCode5() {
        return this.icd10diagnosisCode5;
    }

    public ClaimsSubmissionMaster icd10diagnosisCode5(String icd10diagnosisCode5) {
        this.setIcd10diagnosisCode5(icd10diagnosisCode5);
        return this;
    }

    public void setIcd10diagnosisCode5(String icd10diagnosisCode5) {
        this.icd10diagnosisCode5 = icd10diagnosisCode5;
    }

    public String getIcd10diagnosisCode6() {
        return this.icd10diagnosisCode6;
    }

    public ClaimsSubmissionMaster icd10diagnosisCode6(String icd10diagnosisCode6) {
        this.setIcd10diagnosisCode6(icd10diagnosisCode6);
        return this;
    }

    public void setIcd10diagnosisCode6(String icd10diagnosisCode6) {
        this.icd10diagnosisCode6 = icd10diagnosisCode6;
    }

    public String getIcd10diagnosisCode7() {
        return this.icd10diagnosisCode7;
    }

    public ClaimsSubmissionMaster icd10diagnosisCode7(String icd10diagnosisCode7) {
        this.setIcd10diagnosisCode7(icd10diagnosisCode7);
        return this;
    }

    public void setIcd10diagnosisCode7(String icd10diagnosisCode7) {
        this.icd10diagnosisCode7 = icd10diagnosisCode7;
    }

    public String getIcd10diagnosisCode8() {
        return this.icd10diagnosisCode8;
    }

    public ClaimsSubmissionMaster icd10diagnosisCode8(String icd10diagnosisCode8) {
        this.setIcd10diagnosisCode8(icd10diagnosisCode8);
        return this;
    }

    public void setIcd10diagnosisCode8(String icd10diagnosisCode8) {
        this.icd10diagnosisCode8 = icd10diagnosisCode8;
    }

    public String getIcd10diagnosisCode9() {
        return this.icd10diagnosisCode9;
    }

    public ClaimsSubmissionMaster icd10diagnosisCode9(String icd10diagnosisCode9) {
        this.setIcd10diagnosisCode9(icd10diagnosisCode9);
        return this;
    }

    public void setIcd10diagnosisCode9(String icd10diagnosisCode9) {
        this.icd10diagnosisCode9 = icd10diagnosisCode9;
    }

    public String getIcd10diagnosisCode10() {
        return this.icd10diagnosisCode10;
    }

    public ClaimsSubmissionMaster icd10diagnosisCode10(String icd10diagnosisCode10) {
        this.setIcd10diagnosisCode10(icd10diagnosisCode10);
        return this;
    }

    public void setIcd10diagnosisCode10(String icd10diagnosisCode10) {
        this.icd10diagnosisCode10 = icd10diagnosisCode10;
    }

    public String getIcd10diagnosisCode11() {
        return this.icd10diagnosisCode11;
    }

    public ClaimsSubmissionMaster icd10diagnosisCode11(String icd10diagnosisCode11) {
        this.setIcd10diagnosisCode11(icd10diagnosisCode11);
        return this;
    }

    public void setIcd10diagnosisCode11(String icd10diagnosisCode11) {
        this.icd10diagnosisCode11 = icd10diagnosisCode11;
    }

    public String getIcd10diagnosisCode12() {
        return this.icd10diagnosisCode12;
    }

    public ClaimsSubmissionMaster icd10diagnosisCode12(String icd10diagnosisCode12) {
        this.setIcd10diagnosisCode12(icd10diagnosisCode12);
        return this;
    }

    public void setIcd10diagnosisCode12(String icd10diagnosisCode12) {
        this.icd10diagnosisCode12 = icd10diagnosisCode12;
    }

    public Long getInsertedById() {
        return this.insertedById;
    }

    public ClaimsSubmissionMaster insertedById(Long insertedById) {
        this.setInsertedById(insertedById);
        return this;
    }

    public void setInsertedById(Long insertedById) {
        this.insertedById = insertedById;
    }

    public ZonedDateTime getInsertedDate() {
        return this.insertedDate;
    }

    public ClaimsSubmissionMaster insertedDate(ZonedDateTime insertedDate) {
        this.setInsertedDate(insertedDate);
        return this;
    }

    public void setInsertedDate(ZonedDateTime insertedDate) {
        this.insertedDate = insertedDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ClaimsSubmissionMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public ZonedDateTime getUpdatedDate() {
        return this.updatedDate;
    }

    public ClaimsSubmissionMaster updatedDate(ZonedDateTime updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getBillingProviderType() {
        return this.billingProviderType;
    }

    public ClaimsSubmissionMaster billingProviderType(String billingProviderType) {
        this.setBillingProviderType(billingProviderType);
        return this;
    }

    public void setBillingProviderType(String billingProviderType) {
        this.billingProviderType = billingProviderType;
    }

    public String getInsertedByName() {
        return this.insertedByName;
    }

    public ClaimsSubmissionMaster insertedByName(String insertedByName) {
        this.setInsertedByName(insertedByName);
        return this;
    }

    public void setInsertedByName(String insertedByName) {
        this.insertedByName = insertedByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ClaimsSubmissionMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getStatus() {
        return this.status;
    }

    public ClaimsSubmissionMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBillingProviderAddressLine2() {
        return this.billingProviderAddressLine2;
    }

    public ClaimsSubmissionMaster billingProviderAddressLine2(String billingProviderAddressLine2) {
        this.setBillingProviderAddressLine2(billingProviderAddressLine2);
        return this;
    }

    public void setBillingProviderAddressLine2(String billingProviderAddressLine2) {
        this.billingProviderAddressLine2 = billingProviderAddressLine2;
    }

    public String getInsuredFirstName() {
        return this.insuredFirstName;
    }

    public ClaimsSubmissionMaster insuredFirstName(String insuredFirstName) {
        this.setInsuredFirstName(insuredFirstName);
        return this;
    }

    public void setInsuredFirstName(String insuredFirstName) {
        this.insuredFirstName = insuredFirstName;
    }

    public String getInsuredLastName() {
        return this.insuredLastName;
    }

    public ClaimsSubmissionMaster insuredLastName(String insuredLastName) {
        this.setInsuredLastName(insuredLastName);
        return this;
    }

    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }

    public String getInsuredAddressLine1() {
        return this.insuredAddressLine1;
    }

    public ClaimsSubmissionMaster insuredAddressLine1(String insuredAddressLine1) {
        this.setInsuredAddressLine1(insuredAddressLine1);
        return this;
    }

    public void setInsuredAddressLine1(String insuredAddressLine1) {
        this.insuredAddressLine1 = insuredAddressLine1;
    }

    public String getInsuredAddressLine2() {
        return this.insuredAddressLine2;
    }

    public ClaimsSubmissionMaster insuredAddressLine2(String insuredAddressLine2) {
        this.setInsuredAddressLine2(insuredAddressLine2);
        return this;
    }

    public void setInsuredAddressLine2(String insuredAddressLine2) {
        this.insuredAddressLine2 = insuredAddressLine2;
    }

    public String getInsuredCity() {
        return this.insuredCity;
    }

    public ClaimsSubmissionMaster insuredCity(String insuredCity) {
        this.setInsuredCity(insuredCity);
        return this;
    }

    public void setInsuredCity(String insuredCity) {
        this.insuredCity = insuredCity;
    }

    public String getInsuredState() {
        return this.insuredState;
    }

    public ClaimsSubmissionMaster insuredState(String insuredState) {
        this.setInsuredState(insuredState);
        return this;
    }

    public void setInsuredState(String insuredState) {
        this.insuredState = insuredState;
    }

    public String getInsuredZip() {
        return this.insuredZip;
    }

    public ClaimsSubmissionMaster insuredZip(String insuredZip) {
        this.setInsuredZip(insuredZip);
        return this;
    }

    public void setInsuredZip(String insuredZip) {
        this.insuredZip = insuredZip;
    }

    public String getInsuredContactNo() {
        return this.insuredContactNo;
    }

    public ClaimsSubmissionMaster insuredContactNo(String insuredContactNo) {
        this.setInsuredContactNo(insuredContactNo);
        return this;
    }

    public void setInsuredContactNo(String insuredContactNo) {
        this.insuredContactNo = insuredContactNo;
    }

    public LocalDate getInsuredDob() {
        return this.insuredDob;
    }

    public ClaimsSubmissionMaster insuredDob(LocalDate insuredDob) {
        this.setInsuredDob(insuredDob);
        return this;
    }

    public void setInsuredDob(LocalDate insuredDob) {
        this.insuredDob = insuredDob;
    }

    public String getInsuredGender() {
        return this.insuredGender;
    }

    public ClaimsSubmissionMaster insuredGender(String insuredGender) {
        this.setInsuredGender(insuredGender);
        return this;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public String getOrderingProviderFirstName() {
        return this.orderingProviderFirstName;
    }

    public ClaimsSubmissionMaster orderingProviderFirstName(String orderingProviderFirstName) {
        this.setOrderingProviderFirstName(orderingProviderFirstName);
        return this;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderLastName() {
        return this.orderingProviderLastName;
    }

    public ClaimsSubmissionMaster orderingProviderLastName(String orderingProviderLastName) {
        this.setOrderingProviderLastName(orderingProviderLastName);
        return this;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return this.orderingProviderNpi;
    }

    public ClaimsSubmissionMaster orderingProviderNpi(String orderingProviderNpi) {
        this.setOrderingProviderNpi(orderingProviderNpi);
        return this;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getPatientRelationshipInsured() {
        return this.patientRelationshipInsured;
    }

    public ClaimsSubmissionMaster patientRelationshipInsured(String patientRelationshipInsured) {
        this.setPatientRelationshipInsured(patientRelationshipInsured);
        return this;
    }

    public void setPatientRelationshipInsured(String patientRelationshipInsured) {
        this.patientRelationshipInsured = patientRelationshipInsured;
    }

    public String getPatientConditionEmployment() {
        return this.patientConditionEmployment;
    }

    public ClaimsSubmissionMaster patientConditionEmployment(String patientConditionEmployment) {
        this.setPatientConditionEmployment(patientConditionEmployment);
        return this;
    }

    public void setPatientConditionEmployment(String patientConditionEmployment) {
        this.patientConditionEmployment = patientConditionEmployment;
    }

    public String getPatientConditionAutoAccident() {
        return this.patientConditionAutoAccident;
    }

    public ClaimsSubmissionMaster patientConditionAutoAccident(String patientConditionAutoAccident) {
        this.setPatientConditionAutoAccident(patientConditionAutoAccident);
        return this;
    }

    public void setPatientConditionAutoAccident(String patientConditionAutoAccident) {
        this.patientConditionAutoAccident = patientConditionAutoAccident;
    }

    public String getPatientConditionOtherAccident() {
        return this.patientConditionOtherAccident;
    }

    public ClaimsSubmissionMaster patientConditionOtherAccident(String patientConditionOtherAccident) {
        this.setPatientConditionOtherAccident(patientConditionOtherAccident);
        return this;
    }

    public void setPatientConditionOtherAccident(String patientConditionOtherAccident) {
        this.patientConditionOtherAccident = patientConditionOtherAccident;
    }

    public String getIsNextLevelInsurerPresentStatus() {
        return this.isNextLevelInsurerPresentStatus;
    }

    public ClaimsSubmissionMaster isNextLevelInsurerPresentStatus(String isNextLevelInsurerPresentStatus) {
        this.setIsNextLevelInsurerPresentStatus(isNextLevelInsurerPresentStatus);
        return this;
    }

    public void setIsNextLevelInsurerPresentStatus(String isNextLevelInsurerPresentStatus) {
        this.isNextLevelInsurerPresentStatus = isNextLevelInsurerPresentStatus;
    }

    public LocalDate getOriginalDos() {
        return this.originalDos;
    }

    public ClaimsSubmissionMaster originalDos(LocalDate originalDos) {
        this.setOriginalDos(originalDos);
        return this;
    }

    public void setOriginalDos(LocalDate originalDos) {
        this.originalDos = originalDos;
    }

    public String getParNo() {
        return this.parNo;
    }

    public ClaimsSubmissionMaster parNo(String parNo) {
        this.setParNo(parNo);
        return this;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public String getBillingProviderTaxonomy() {
        return this.billingProviderTaxonomy;
    }

    public ClaimsSubmissionMaster billingProviderTaxonomy(String billingProviderTaxonomy) {
        this.setBillingProviderTaxonomy(billingProviderTaxonomy);
        return this;
    }

    public void setBillingProviderTaxonomy(String billingProviderTaxonomy) {
        this.billingProviderTaxonomy = billingProviderTaxonomy;
    }

    public String getServiceProviderNpi() {
        return this.serviceProviderNpi;
    }

    public ClaimsSubmissionMaster serviceProviderNpi(String serviceProviderNpi) {
        this.setServiceProviderNpi(serviceProviderNpi);
        return this;
    }

    public void setServiceProviderNpi(String serviceProviderNpi) {
        this.serviceProviderNpi = serviceProviderNpi;
    }

    public String getServiceProviderOrganisationName() {
        return this.serviceProviderOrganisationName;
    }

    public ClaimsSubmissionMaster serviceProviderOrganisationName(String serviceProviderOrganisationName) {
        this.setServiceProviderOrganisationName(serviceProviderOrganisationName);
        return this;
    }

    public void setServiceProviderOrganisationName(String serviceProviderOrganisationName) {
        this.serviceProviderOrganisationName = serviceProviderOrganisationName;
    }

    public String getServiceProviderAddressLine1() {
        return this.serviceProviderAddressLine1;
    }

    public ClaimsSubmissionMaster serviceProviderAddressLine1(String serviceProviderAddressLine1) {
        this.setServiceProviderAddressLine1(serviceProviderAddressLine1);
        return this;
    }

    public void setServiceProviderAddressLine1(String serviceProviderAddressLine1) {
        this.serviceProviderAddressLine1 = serviceProviderAddressLine1;
    }

    public String getServiceProviderAddressLine2() {
        return this.serviceProviderAddressLine2;
    }

    public ClaimsSubmissionMaster serviceProviderAddressLine2(String serviceProviderAddressLine2) {
        this.setServiceProviderAddressLine2(serviceProviderAddressLine2);
        return this;
    }

    public void setServiceProviderAddressLine2(String serviceProviderAddressLine2) {
        this.serviceProviderAddressLine2 = serviceProviderAddressLine2;
    }

    public String getServiceProviderCity() {
        return this.serviceProviderCity;
    }

    public ClaimsSubmissionMaster serviceProviderCity(String serviceProviderCity) {
        this.setServiceProviderCity(serviceProviderCity);
        return this;
    }

    public void setServiceProviderCity(String serviceProviderCity) {
        this.serviceProviderCity = serviceProviderCity;
    }

    public String getServiceProviderState() {
        return this.serviceProviderState;
    }

    public ClaimsSubmissionMaster serviceProviderState(String serviceProviderState) {
        this.setServiceProviderState(serviceProviderState);
        return this;
    }

    public void setServiceProviderState(String serviceProviderState) {
        this.serviceProviderState = serviceProviderState;
    }

    public String getServiceProviderCountry() {
        return this.serviceProviderCountry;
    }

    public ClaimsSubmissionMaster serviceProviderCountry(String serviceProviderCountry) {
        this.setServiceProviderCountry(serviceProviderCountry);
        return this;
    }

    public void setServiceProviderCountry(String serviceProviderCountry) {
        this.serviceProviderCountry = serviceProviderCountry;
    }

    public String getServiceProviderZipCode() {
        return this.serviceProviderZipCode;
    }

    public ClaimsSubmissionMaster serviceProviderZipCode(String serviceProviderZipCode) {
        this.setServiceProviderZipCode(serviceProviderZipCode);
        return this;
    }

    public void setServiceProviderZipCode(String serviceProviderZipCode) {
        this.serviceProviderZipCode = serviceProviderZipCode;
    }

    public String getServiceProviderTaxonomy() {
        return this.serviceProviderTaxonomy;
    }

    public ClaimsSubmissionMaster serviceProviderTaxonomy(String serviceProviderTaxonomy) {
        this.setServiceProviderTaxonomy(serviceProviderTaxonomy);
        return this;
    }

    public void setServiceProviderTaxonomy(String serviceProviderTaxonomy) {
        this.serviceProviderTaxonomy = serviceProviderTaxonomy;
    }

    public String getCms1500FormName() {
        return this.cms1500FormName;
    }

    public ClaimsSubmissionMaster cms1500FormName(String cms1500FormName) {
        this.setCms1500FormName(cms1500FormName);
        return this;
    }

    public void setCms1500FormName(String cms1500FormName) {
        this.cms1500FormName = cms1500FormName;
    }

    public String getTradingPartnerAddressLine1() {
        return this.tradingPartnerAddressLine1;
    }

    public ClaimsSubmissionMaster tradingPartnerAddressLine1(String tradingPartnerAddressLine1) {
        this.setTradingPartnerAddressLine1(tradingPartnerAddressLine1);
        return this;
    }

    public void setTradingPartnerAddressLine1(String tradingPartnerAddressLine1) {
        this.tradingPartnerAddressLine1 = tradingPartnerAddressLine1;
    }

    public String getTradingPartnerAddressLine2() {
        return this.tradingPartnerAddressLine2;
    }

    public ClaimsSubmissionMaster tradingPartnerAddressLine2(String tradingPartnerAddressLine2) {
        this.setTradingPartnerAddressLine2(tradingPartnerAddressLine2);
        return this;
    }

    public void setTradingPartnerAddressLine2(String tradingPartnerAddressLine2) {
        this.tradingPartnerAddressLine2 = tradingPartnerAddressLine2;
    }

    public String getTradingPartnerCity() {
        return this.tradingPartnerCity;
    }

    public ClaimsSubmissionMaster tradingPartnerCity(String tradingPartnerCity) {
        this.setTradingPartnerCity(tradingPartnerCity);
        return this;
    }

    public void setTradingPartnerCity(String tradingPartnerCity) {
        this.tradingPartnerCity = tradingPartnerCity;
    }

    public String getTradingPartnerState() {
        return this.tradingPartnerState;
    }

    public ClaimsSubmissionMaster tradingPartnerState(String tradingPartnerState) {
        this.setTradingPartnerState(tradingPartnerState);
        return this;
    }

    public void setTradingPartnerState(String tradingPartnerState) {
        this.tradingPartnerState = tradingPartnerState;
    }

    public String getTradingPartnerZip() {
        return this.tradingPartnerZip;
    }

    public ClaimsSubmissionMaster tradingPartnerZip(String tradingPartnerZip) {
        this.setTradingPartnerZip(tradingPartnerZip);
        return this;
    }

    public void setTradingPartnerZip(String tradingPartnerZip) {
        this.tradingPartnerZip = tradingPartnerZip;
    }

    public String getDiagnosisCodeType() {
        return this.diagnosisCodeType;
    }

    public ClaimsSubmissionMaster diagnosisCodeType(String diagnosisCodeType) {
        this.setDiagnosisCodeType(diagnosisCodeType);
        return this;
    }

    public void setDiagnosisCodeType(String diagnosisCodeType) {
        this.diagnosisCodeType = diagnosisCodeType;
    }

    public Set<ServiceLinesMaster> getServiceLines() {
        return this.serviceLines;
    }

    public void setServiceLines(Set<ServiceLinesMaster> serviceLinesMasters) {
        if (this.serviceLines != null) {
            this.serviceLines.forEach(i -> i.setClaimsSubmissionMaster(null));
        }
        if (serviceLinesMasters != null) {
            serviceLinesMasters.forEach(i -> i.setClaimsSubmissionMaster(this));
        }
        this.serviceLines = serviceLinesMasters;
    }

    public ClaimsSubmissionMaster serviceLines(Set<ServiceLinesMaster> serviceLinesMasters) {
        this.setServiceLines(serviceLinesMasters);
        return this;
    }

    public ClaimsSubmissionMaster addServiceLines(ServiceLinesMaster serviceLinesMaster) {
        this.serviceLines.add(serviceLinesMaster);
        serviceLinesMaster.setClaimsSubmissionMaster(this);
        return this;
    }

    public ClaimsSubmissionMaster removeServiceLines(ServiceLinesMaster serviceLinesMaster) {
        this.serviceLines.remove(serviceLinesMaster);
        serviceLinesMaster.setClaimsSubmissionMaster(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimsSubmissionMaster)) {
            return false;
        }
        return (
            changeHealthPrimarySubmisionMasterId != null &&
            changeHealthPrimarySubmisionMasterId.equals(((ClaimsSubmissionMaster) o).changeHealthPrimarySubmisionMasterId)
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
        return "ClaimsSubmissionMaster{" +
            "changeHealthPrimarySubmisionMasterId=" + getChangeHealthPrimarySubmisionMasterId() +
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
            ", billingProviderNpi='" + getBillingProviderNpi() + "'" +
            ", billingProviderEin='" + getBillingProviderEin() + "'" +
            ", billingProviderOrganizationName='" + getBillingProviderOrganizationName() + "'" +
            ", billingProviderAddressLine1='" + getBillingProviderAddressLine1() + "'" +
            ", billingProviderCity='" + getBillingProviderCity() + "'" +
            ", billingProviderState='" + getBillingProviderState() + "'" +
            ", billingProviderZipCode='" + getBillingProviderZipCode() + "'" +
            ", billingProviderContactPersonName='" + getBillingProviderContactPersonName() + "'" +
            ", billingProviderContactNo='" + getBillingProviderContactNo() + "'" +
            ", claimFilingCode='" + getClaimFilingCode() + "'" +
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
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", billingProviderType='" + getBillingProviderType() + "'" +
            ", insertedByName='" + getInsertedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", status='" + getStatus() + "'" +
            ", billingProviderAddressLine2='" + getBillingProviderAddressLine2() + "'" +
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
