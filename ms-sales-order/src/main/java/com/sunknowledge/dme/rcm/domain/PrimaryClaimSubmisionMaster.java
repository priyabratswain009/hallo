package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PrimaryClaimSubmisionMaster.
 */
@Table("t_primary_claim_submision_master")
public class PrimaryClaimSubmisionMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("change_health_primary_submision_master_id")
    private Long changeHealthPrimarySubmisionMasterId;

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

    @Column("primary_insurer_policy_no")
    private String primaryInsurerPolicyNo;

    @Column("subscriber_address_line_1")
    private String subscriberAddressLine1;

    @Column("subscriber_city")
    private String subscriberCity;

    @Column("subscriber_state")
    private String subscriberState;

    @Column("subscriber_zip_code")
    private String subscriberZipCode;

    @Column("billing_provider_npi")
    private String billingProviderNpi;

    @Column("billing_provider_ein")
    private String billingProviderEin;

    @Column("billing_provider_organization_name")
    private String billingProviderOrganizationName;

    @Column("billing_provider_address_line_1")
    private String billingProviderAddressLine1;

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
    private Long insertedById;

    @Column("inserted_date")
    private LocalDate insertedDate;

    @Column("ipdated_by_id")
    private Long ipdatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("billing_provider_type")
    private String billingProviderType;

    @Column("inserted_by_name")
    private String insertedByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("status")
    private String status;

    @Column("billing_provider_address_line_2")
    private String billingProviderAddressLine2;

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

    @Column("trading_patner_city")
    private String tradingPatnerCity;

    @Column("trading_partner_state")
    private String tradingPartnerState;

    @Column("trading_partner_zip")
    private String tradingPartnerZip;

    @Column("diagnosis_code_type")
    private String diagnosisCodeType;

    @Column("primary_claim_submision_master_uuid")
    private UUID primaryClaimSubmisionMasterUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getChangeHealthPrimarySubmisionMasterId() {
        return this.changeHealthPrimarySubmisionMasterId;
    }

    public PrimaryClaimSubmisionMaster changeHealthPrimarySubmisionMasterId(Long changeHealthPrimarySubmisionMasterId) {
        this.setChangeHealthPrimarySubmisionMasterId(changeHealthPrimarySubmisionMasterId);
        return this;
    }

    public void setChangeHealthPrimarySubmisionMasterId(Long changeHealthPrimarySubmisionMasterId) {
        this.changeHealthPrimarySubmisionMasterId = changeHealthPrimarySubmisionMasterId;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public PrimaryClaimSubmisionMaster salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getClaimControlNo() {
        return this.claimControlNo;
    }

    public PrimaryClaimSubmisionMaster claimControlNo(String claimControlNo) {
        this.setClaimControlNo(claimControlNo);
        return this;
    }

    public void setClaimControlNo(String claimControlNo) {
        this.claimControlNo = claimControlNo;
    }

    public String getTradingPartnerServiceId() {
        return this.tradingPartnerServiceId;
    }

    public PrimaryClaimSubmisionMaster tradingPartnerServiceId(String tradingPartnerServiceId) {
        this.setTradingPartnerServiceId(tradingPartnerServiceId);
        return this;
    }

    public void setTradingPartnerServiceId(String tradingPartnerServiceId) {
        this.tradingPartnerServiceId = tradingPartnerServiceId;
    }

    public String getTradingPartnerName() {
        return this.tradingPartnerName;
    }

    public PrimaryClaimSubmisionMaster tradingPartnerName(String tradingPartnerName) {
        this.setTradingPartnerName(tradingPartnerName);
        return this;
    }

    public void setTradingPartnerName(String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    public String getSubmitterOrganizationName() {
        return this.submitterOrganizationName;
    }

    public PrimaryClaimSubmisionMaster submitterOrganizationName(String submitterOrganizationName) {
        this.setSubmitterOrganizationName(submitterOrganizationName);
        return this;
    }

    public void setSubmitterOrganizationName(String submitterOrganizationName) {
        this.submitterOrganizationName = submitterOrganizationName;
    }

    public String getSubmitterContactPersonName() {
        return this.submitterContactPersonName;
    }

    public PrimaryClaimSubmisionMaster submitterContactPersonName(String submitterContactPersonName) {
        this.setSubmitterContactPersonName(submitterContactPersonName);
        return this;
    }

    public void setSubmitterContactPersonName(String submitterContactPersonName) {
        this.submitterContactPersonName = submitterContactPersonName;
    }

    public String getSubmitterContactNo() {
        return this.submitterContactNo;
    }

    public PrimaryClaimSubmisionMaster submitterContactNo(String submitterContactNo) {
        this.setSubmitterContactNo(submitterContactNo);
        return this;
    }

    public void setSubmitterContactNo(String submitterContactNo) {
        this.submitterContactNo = submitterContactNo;
    }

    public String getReceiverOrganizationName() {
        return this.receiverOrganizationName;
    }

    public PrimaryClaimSubmisionMaster receiverOrganizationName(String receiverOrganizationName) {
        this.setReceiverOrganizationName(receiverOrganizationName);
        return this;
    }

    public void setReceiverOrganizationName(String receiverOrganizationName) {
        this.receiverOrganizationName = receiverOrganizationName;
    }

    public String getSubscriberMemberIdNo() {
        return this.subscriberMemberIdNo;
    }

    public PrimaryClaimSubmisionMaster subscriberMemberIdNo(String subscriberMemberIdNo) {
        this.setSubscriberMemberIdNo(subscriberMemberIdNo);
        return this;
    }

    public void setSubscriberMemberIdNo(String subscriberMemberIdNo) {
        this.subscriberMemberIdNo = subscriberMemberIdNo;
    }

    public String getSubscriberPaymentResponsibilityLevelCode() {
        return this.subscriberPaymentResponsibilityLevelCode;
    }

    public PrimaryClaimSubmisionMaster subscriberPaymentResponsibilityLevelCode(String subscriberPaymentResponsibilityLevelCode) {
        this.setSubscriberPaymentResponsibilityLevelCode(subscriberPaymentResponsibilityLevelCode);
        return this;
    }

    public void setSubscriberPaymentResponsibilityLevelCode(String subscriberPaymentResponsibilityLevelCode) {
        this.subscriberPaymentResponsibilityLevelCode = subscriberPaymentResponsibilityLevelCode;
    }

    public String getSubscriberFirstName() {
        return this.subscriberFirstName;
    }

    public PrimaryClaimSubmisionMaster subscriberFirstName(String subscriberFirstName) {
        this.setSubscriberFirstName(subscriberFirstName);
        return this;
    }

    public void setSubscriberFirstName(String subscriberFirstName) {
        this.subscriberFirstName = subscriberFirstName;
    }

    public String getSubscriberLastName() {
        return this.subscriberLastName;
    }

    public PrimaryClaimSubmisionMaster subscriberLastName(String subscriberLastName) {
        this.setSubscriberLastName(subscriberLastName);
        return this;
    }

    public void setSubscriberLastName(String subscriberLastName) {
        this.subscriberLastName = subscriberLastName;
    }

    public String getSubscriberGender() {
        return this.subscriberGender;
    }

    public PrimaryClaimSubmisionMaster subscriberGender(String subscriberGender) {
        this.setSubscriberGender(subscriberGender);
        return this;
    }

    public void setSubscriberGender(String subscriberGender) {
        this.subscriberGender = subscriberGender;
    }

    public LocalDate getSubscriberDob() {
        return this.subscriberDob;
    }

    public PrimaryClaimSubmisionMaster subscriberDob(LocalDate subscriberDob) {
        this.setSubscriberDob(subscriberDob);
        return this;
    }

    public void setSubscriberDob(LocalDate subscriberDob) {
        this.subscriberDob = subscriberDob;
    }

    public String getPrimaryInsurerPolicyNo() {
        return this.primaryInsurerPolicyNo;
    }

    public PrimaryClaimSubmisionMaster primaryInsurerPolicyNo(String primaryInsurerPolicyNo) {
        this.setPrimaryInsurerPolicyNo(primaryInsurerPolicyNo);
        return this;
    }

    public void setPrimaryInsurerPolicyNo(String primaryInsurerPolicyNo) {
        this.primaryInsurerPolicyNo = primaryInsurerPolicyNo;
    }

    public String getSubscriberAddressLine1() {
        return this.subscriberAddressLine1;
    }

    public PrimaryClaimSubmisionMaster subscriberAddressLine1(String subscriberAddressLine1) {
        this.setSubscriberAddressLine1(subscriberAddressLine1);
        return this;
    }

    public void setSubscriberAddressLine1(String subscriberAddressLine1) {
        this.subscriberAddressLine1 = subscriberAddressLine1;
    }

    public String getSubscriberCity() {
        return this.subscriberCity;
    }

    public PrimaryClaimSubmisionMaster subscriberCity(String subscriberCity) {
        this.setSubscriberCity(subscriberCity);
        return this;
    }

    public void setSubscriberCity(String subscriberCity) {
        this.subscriberCity = subscriberCity;
    }

    public String getSubscriberState() {
        return this.subscriberState;
    }

    public PrimaryClaimSubmisionMaster subscriberState(String subscriberState) {
        this.setSubscriberState(subscriberState);
        return this;
    }

    public void setSubscriberState(String subscriberState) {
        this.subscriberState = subscriberState;
    }

    public String getSubscriberZipCode() {
        return this.subscriberZipCode;
    }

    public PrimaryClaimSubmisionMaster subscriberZipCode(String subscriberZipCode) {
        this.setSubscriberZipCode(subscriberZipCode);
        return this;
    }

    public void setSubscriberZipCode(String subscriberZipCode) {
        this.subscriberZipCode = subscriberZipCode;
    }

    public String getBillingProviderNpi() {
        return this.billingProviderNpi;
    }

    public PrimaryClaimSubmisionMaster billingProviderNpi(String billingProviderNpi) {
        this.setBillingProviderNpi(billingProviderNpi);
        return this;
    }

    public void setBillingProviderNpi(String billingProviderNpi) {
        this.billingProviderNpi = billingProviderNpi;
    }

    public String getBillingProviderEin() {
        return this.billingProviderEin;
    }

    public PrimaryClaimSubmisionMaster billingProviderEin(String billingProviderEin) {
        this.setBillingProviderEin(billingProviderEin);
        return this;
    }

    public void setBillingProviderEin(String billingProviderEin) {
        this.billingProviderEin = billingProviderEin;
    }

    public String getBillingProviderOrganizationName() {
        return this.billingProviderOrganizationName;
    }

    public PrimaryClaimSubmisionMaster billingProviderOrganizationName(String billingProviderOrganizationName) {
        this.setBillingProviderOrganizationName(billingProviderOrganizationName);
        return this;
    }

    public void setBillingProviderOrganizationName(String billingProviderOrganizationName) {
        this.billingProviderOrganizationName = billingProviderOrganizationName;
    }

    public String getBillingProviderAddressLine1() {
        return this.billingProviderAddressLine1;
    }

    public PrimaryClaimSubmisionMaster billingProviderAddressLine1(String billingProviderAddressLine1) {
        this.setBillingProviderAddressLine1(billingProviderAddressLine1);
        return this;
    }

    public void setBillingProviderAddressLine1(String billingProviderAddressLine1) {
        this.billingProviderAddressLine1 = billingProviderAddressLine1;
    }

    public String getBillingProviderCity() {
        return this.billingProviderCity;
    }

    public PrimaryClaimSubmisionMaster billingProviderCity(String billingProviderCity) {
        this.setBillingProviderCity(billingProviderCity);
        return this;
    }

    public void setBillingProviderCity(String billingProviderCity) {
        this.billingProviderCity = billingProviderCity;
    }

    public String getBillingProviderState() {
        return this.billingProviderState;
    }

    public PrimaryClaimSubmisionMaster billingProviderState(String billingProviderState) {
        this.setBillingProviderState(billingProviderState);
        return this;
    }

    public void setBillingProviderState(String billingProviderState) {
        this.billingProviderState = billingProviderState;
    }

    public String getBillingProviderZipCode() {
        return this.billingProviderZipCode;
    }

    public PrimaryClaimSubmisionMaster billingProviderZipCode(String billingProviderZipCode) {
        this.setBillingProviderZipCode(billingProviderZipCode);
        return this;
    }

    public void setBillingProviderZipCode(String billingProviderZipCode) {
        this.billingProviderZipCode = billingProviderZipCode;
    }

    public String getBillingProviderContactPersonName() {
        return this.billingProviderContactPersonName;
    }

    public PrimaryClaimSubmisionMaster billingProviderContactPersonName(String billingProviderContactPersonName) {
        this.setBillingProviderContactPersonName(billingProviderContactPersonName);
        return this;
    }

    public void setBillingProviderContactPersonName(String billingProviderContactPersonName) {
        this.billingProviderContactPersonName = billingProviderContactPersonName;
    }

    public String getBillingProviderContactNo() {
        return this.billingProviderContactNo;
    }

    public PrimaryClaimSubmisionMaster billingProviderContactNo(String billingProviderContactNo) {
        this.setBillingProviderContactNo(billingProviderContactNo);
        return this;
    }

    public void setBillingProviderContactNo(String billingProviderContactNo) {
        this.billingProviderContactNo = billingProviderContactNo;
    }

    public String getClaimFilingCode() {
        return this.claimFilingCode;
    }

    public PrimaryClaimSubmisionMaster claimFilingCode(String claimFilingCode) {
        this.setClaimFilingCode(claimFilingCode);
        return this;
    }

    public void setClaimFilingCode(String claimFilingCode) {
        this.claimFilingCode = claimFilingCode;
    }

    public Double getClaimChargeAmount() {
        return this.claimChargeAmount;
    }

    public PrimaryClaimSubmisionMaster claimChargeAmount(Double claimChargeAmount) {
        this.setClaimChargeAmount(claimChargeAmount);
        return this;
    }

    public void setClaimChargeAmount(Double claimChargeAmount) {
        this.claimChargeAmount = claimChargeAmount;
    }

    public String getPosCode() {
        return this.posCode;
    }

    public PrimaryClaimSubmisionMaster posCode(String posCode) {
        this.setPosCode(posCode);
        return this;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getClaimFrequencyCode() {
        return this.claimFrequencyCode;
    }

    public PrimaryClaimSubmisionMaster claimFrequencyCode(String claimFrequencyCode) {
        this.setClaimFrequencyCode(claimFrequencyCode);
        return this;
    }

    public void setClaimFrequencyCode(String claimFrequencyCode) {
        this.claimFrequencyCode = claimFrequencyCode;
    }

    public String getSignatureIndicator() {
        return this.signatureIndicator;
    }

    public PrimaryClaimSubmisionMaster signatureIndicator(String signatureIndicator) {
        this.setSignatureIndicator(signatureIndicator);
        return this;
    }

    public void setSignatureIndicator(String signatureIndicator) {
        this.signatureIndicator = signatureIndicator;
    }

    public String getPlanParticipationCode() {
        return this.planParticipationCode;
    }

    public PrimaryClaimSubmisionMaster planParticipationCode(String planParticipationCode) {
        this.setPlanParticipationCode(planParticipationCode);
        return this;
    }

    public void setPlanParticipationCode(String planParticipationCode) {
        this.planParticipationCode = planParticipationCode;
    }

    public String getBenefitsAssignmentCertificationIndicator() {
        return this.benefitsAssignmentCertificationIndicator;
    }

    public PrimaryClaimSubmisionMaster benefitsAssignmentCertificationIndicator(String benefitsAssignmentCertificationIndicator) {
        this.setBenefitsAssignmentCertificationIndicator(benefitsAssignmentCertificationIndicator);
        return this;
    }

    public void setBenefitsAssignmentCertificationIndicator(String benefitsAssignmentCertificationIndicator) {
        this.benefitsAssignmentCertificationIndicator = benefitsAssignmentCertificationIndicator;
    }

    public String getReleaseInformationCode() {
        return this.releaseInformationCode;
    }

    public PrimaryClaimSubmisionMaster releaseInformationCode(String releaseInformationCode) {
        this.setReleaseInformationCode(releaseInformationCode);
        return this;
    }

    public void setReleaseInformationCode(String releaseInformationCode) {
        this.releaseInformationCode = releaseInformationCode;
    }

    public String getPrimaryDiagnosis() {
        return this.primaryDiagnosis;
    }

    public PrimaryClaimSubmisionMaster primaryDiagnosis(String primaryDiagnosis) {
        this.setPrimaryDiagnosis(primaryDiagnosis);
        return this;
    }

    public void setPrimaryDiagnosis(String primaryDiagnosis) {
        this.primaryDiagnosis = primaryDiagnosis;
    }

    public String getIcd10DiagnosisCode1() {
        return this.icd10DiagnosisCode1;
    }

    public PrimaryClaimSubmisionMaster icd10DiagnosisCode1(String icd10DiagnosisCode1) {
        this.setIcd10DiagnosisCode1(icd10DiagnosisCode1);
        return this;
    }

    public void setIcd10DiagnosisCode1(String icd10DiagnosisCode1) {
        this.icd10DiagnosisCode1 = icd10DiagnosisCode1;
    }

    public String getIcd10DiagnosisCode2() {
        return this.icd10DiagnosisCode2;
    }

    public PrimaryClaimSubmisionMaster icd10DiagnosisCode2(String icd10DiagnosisCode2) {
        this.setIcd10DiagnosisCode2(icd10DiagnosisCode2);
        return this;
    }

    public void setIcd10DiagnosisCode2(String icd10DiagnosisCode2) {
        this.icd10DiagnosisCode2 = icd10DiagnosisCode2;
    }

    public String getIcd10DiagnosisCode3() {
        return this.icd10DiagnosisCode3;
    }

    public PrimaryClaimSubmisionMaster icd10DiagnosisCode3(String icd10DiagnosisCode3) {
        this.setIcd10DiagnosisCode3(icd10DiagnosisCode3);
        return this;
    }

    public void setIcd10DiagnosisCode3(String icd10DiagnosisCode3) {
        this.icd10DiagnosisCode3 = icd10DiagnosisCode3;
    }

    public String getIcd10DiagnosisCode4() {
        return this.icd10DiagnosisCode4;
    }

    public PrimaryClaimSubmisionMaster icd10DiagnosisCode4(String icd10DiagnosisCode4) {
        this.setIcd10DiagnosisCode4(icd10DiagnosisCode4);
        return this;
    }

    public void setIcd10DiagnosisCode4(String icd10DiagnosisCode4) {
        this.icd10DiagnosisCode4 = icd10DiagnosisCode4;
    }

    public String getIcd10DiagnosisCode5() {
        return this.icd10DiagnosisCode5;
    }

    public PrimaryClaimSubmisionMaster icd10DiagnosisCode5(String icd10DiagnosisCode5) {
        this.setIcd10DiagnosisCode5(icd10DiagnosisCode5);
        return this;
    }

    public void setIcd10DiagnosisCode5(String icd10DiagnosisCode5) {
        this.icd10DiagnosisCode5 = icd10DiagnosisCode5;
    }

    public String getIcd10DiagnosisCode6() {
        return this.icd10DiagnosisCode6;
    }

    public PrimaryClaimSubmisionMaster icd10DiagnosisCode6(String icd10DiagnosisCode6) {
        this.setIcd10DiagnosisCode6(icd10DiagnosisCode6);
        return this;
    }

    public void setIcd10DiagnosisCode6(String icd10DiagnosisCode6) {
        this.icd10DiagnosisCode6 = icd10DiagnosisCode6;
    }

    public String getIcd10DiagnosisCode7() {
        return this.icd10DiagnosisCode7;
    }

    public PrimaryClaimSubmisionMaster icd10DiagnosisCode7(String icd10DiagnosisCode7) {
        this.setIcd10DiagnosisCode7(icd10DiagnosisCode7);
        return this;
    }

    public void setIcd10DiagnosisCode7(String icd10DiagnosisCode7) {
        this.icd10DiagnosisCode7 = icd10DiagnosisCode7;
    }

    public String getIcd10DiagnosisCode8() {
        return this.icd10DiagnosisCode8;
    }

    public PrimaryClaimSubmisionMaster icd10DiagnosisCode8(String icd10DiagnosisCode8) {
        this.setIcd10DiagnosisCode8(icd10DiagnosisCode8);
        return this;
    }

    public void setIcd10DiagnosisCode8(String icd10DiagnosisCode8) {
        this.icd10DiagnosisCode8 = icd10DiagnosisCode8;
    }

    public String getIcd10DiagnosisCode9() {
        return this.icd10DiagnosisCode9;
    }

    public PrimaryClaimSubmisionMaster icd10DiagnosisCode9(String icd10DiagnosisCode9) {
        this.setIcd10DiagnosisCode9(icd10DiagnosisCode9);
        return this;
    }

    public void setIcd10DiagnosisCode9(String icd10DiagnosisCode9) {
        this.icd10DiagnosisCode9 = icd10DiagnosisCode9;
    }

    public String getIcd10DiagnosisCode10() {
        return this.icd10DiagnosisCode10;
    }

    public PrimaryClaimSubmisionMaster icd10DiagnosisCode10(String icd10DiagnosisCode10) {
        this.setIcd10DiagnosisCode10(icd10DiagnosisCode10);
        return this;
    }

    public void setIcd10DiagnosisCode10(String icd10DiagnosisCode10) {
        this.icd10DiagnosisCode10 = icd10DiagnosisCode10;
    }

    public String getIcd10DiagnosisCode11() {
        return this.icd10DiagnosisCode11;
    }

    public PrimaryClaimSubmisionMaster icd10DiagnosisCode11(String icd10DiagnosisCode11) {
        this.setIcd10DiagnosisCode11(icd10DiagnosisCode11);
        return this;
    }

    public void setIcd10DiagnosisCode11(String icd10DiagnosisCode11) {
        this.icd10DiagnosisCode11 = icd10DiagnosisCode11;
    }

    public String getIcd10DiagnosisCode12() {
        return this.icd10DiagnosisCode12;
    }

    public PrimaryClaimSubmisionMaster icd10DiagnosisCode12(String icd10DiagnosisCode12) {
        this.setIcd10DiagnosisCode12(icd10DiagnosisCode12);
        return this;
    }

    public void setIcd10DiagnosisCode12(String icd10DiagnosisCode12) {
        this.icd10DiagnosisCode12 = icd10DiagnosisCode12;
    }

    public Long getInsertedById() {
        return this.insertedById;
    }

    public PrimaryClaimSubmisionMaster insertedById(Long insertedById) {
        this.setInsertedById(insertedById);
        return this;
    }

    public void setInsertedById(Long insertedById) {
        this.insertedById = insertedById;
    }

    public LocalDate getInsertedDate() {
        return this.insertedDate;
    }

    public PrimaryClaimSubmisionMaster insertedDate(LocalDate insertedDate) {
        this.setInsertedDate(insertedDate);
        return this;
    }

    public void setInsertedDate(LocalDate insertedDate) {
        this.insertedDate = insertedDate;
    }

    public Long getIpdatedById() {
        return this.ipdatedById;
    }

    public PrimaryClaimSubmisionMaster ipdatedById(Long ipdatedById) {
        this.setIpdatedById(ipdatedById);
        return this;
    }

    public void setIpdatedById(Long ipdatedById) {
        this.ipdatedById = ipdatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public PrimaryClaimSubmisionMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getBillingProviderType() {
        return this.billingProviderType;
    }

    public PrimaryClaimSubmisionMaster billingProviderType(String billingProviderType) {
        this.setBillingProviderType(billingProviderType);
        return this;
    }

    public void setBillingProviderType(String billingProviderType) {
        this.billingProviderType = billingProviderType;
    }

    public String getInsertedByName() {
        return this.insertedByName;
    }

    public PrimaryClaimSubmisionMaster insertedByName(String insertedByName) {
        this.setInsertedByName(insertedByName);
        return this;
    }

    public void setInsertedByName(String insertedByName) {
        this.insertedByName = insertedByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public PrimaryClaimSubmisionMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getStatus() {
        return this.status;
    }

    public PrimaryClaimSubmisionMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBillingProviderAddressLine2() {
        return this.billingProviderAddressLine2;
    }

    public PrimaryClaimSubmisionMaster billingProviderAddressLine2(String billingProviderAddressLine2) {
        this.setBillingProviderAddressLine2(billingProviderAddressLine2);
        return this;
    }

    public void setBillingProviderAddressLine2(String billingProviderAddressLine2) {
        this.billingProviderAddressLine2 = billingProviderAddressLine2;
    }

    public String getInsuredFirstName() {
        return this.insuredFirstName;
    }

    public PrimaryClaimSubmisionMaster insuredFirstName(String insuredFirstName) {
        this.setInsuredFirstName(insuredFirstName);
        return this;
    }

    public void setInsuredFirstName(String insuredFirstName) {
        this.insuredFirstName = insuredFirstName;
    }

    public String getInsuredLastName() {
        return this.insuredLastName;
    }

    public PrimaryClaimSubmisionMaster insuredLastName(String insuredLastName) {
        this.setInsuredLastName(insuredLastName);
        return this;
    }

    public void setInsuredLastName(String insuredLastName) {
        this.insuredLastName = insuredLastName;
    }

    public String getInsuredAddressLine1() {
        return this.insuredAddressLine1;
    }

    public PrimaryClaimSubmisionMaster insuredAddressLine1(String insuredAddressLine1) {
        this.setInsuredAddressLine1(insuredAddressLine1);
        return this;
    }

    public void setInsuredAddressLine1(String insuredAddressLine1) {
        this.insuredAddressLine1 = insuredAddressLine1;
    }

    public String getInsuredAddressLine2() {
        return this.insuredAddressLine2;
    }

    public PrimaryClaimSubmisionMaster insuredAddressLine2(String insuredAddressLine2) {
        this.setInsuredAddressLine2(insuredAddressLine2);
        return this;
    }

    public void setInsuredAddressLine2(String insuredAddressLine2) {
        this.insuredAddressLine2 = insuredAddressLine2;
    }

    public String getInsuredCity() {
        return this.insuredCity;
    }

    public PrimaryClaimSubmisionMaster insuredCity(String insuredCity) {
        this.setInsuredCity(insuredCity);
        return this;
    }

    public void setInsuredCity(String insuredCity) {
        this.insuredCity = insuredCity;
    }

    public String getInsuredState() {
        return this.insuredState;
    }

    public PrimaryClaimSubmisionMaster insuredState(String insuredState) {
        this.setInsuredState(insuredState);
        return this;
    }

    public void setInsuredState(String insuredState) {
        this.insuredState = insuredState;
    }

    public String getInsuredZip() {
        return this.insuredZip;
    }

    public PrimaryClaimSubmisionMaster insuredZip(String insuredZip) {
        this.setInsuredZip(insuredZip);
        return this;
    }

    public void setInsuredZip(String insuredZip) {
        this.insuredZip = insuredZip;
    }

    public String getInsuredContactNo() {
        return this.insuredContactNo;
    }

    public PrimaryClaimSubmisionMaster insuredContactNo(String insuredContactNo) {
        this.setInsuredContactNo(insuredContactNo);
        return this;
    }

    public void setInsuredContactNo(String insuredContactNo) {
        this.insuredContactNo = insuredContactNo;
    }

    public LocalDate getInsuredDob() {
        return this.insuredDob;
    }

    public PrimaryClaimSubmisionMaster insuredDob(LocalDate insuredDob) {
        this.setInsuredDob(insuredDob);
        return this;
    }

    public void setInsuredDob(LocalDate insuredDob) {
        this.insuredDob = insuredDob;
    }

    public String getInsuredGender() {
        return this.insuredGender;
    }

    public PrimaryClaimSubmisionMaster insuredGender(String insuredGender) {
        this.setInsuredGender(insuredGender);
        return this;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public String getOrderingProviderFirstName() {
        return this.orderingProviderFirstName;
    }

    public PrimaryClaimSubmisionMaster orderingProviderFirstName(String orderingProviderFirstName) {
        this.setOrderingProviderFirstName(orderingProviderFirstName);
        return this;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderLastName() {
        return this.orderingProviderLastName;
    }

    public PrimaryClaimSubmisionMaster orderingProviderLastName(String orderingProviderLastName) {
        this.setOrderingProviderLastName(orderingProviderLastName);
        return this;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return this.orderingProviderNpi;
    }

    public PrimaryClaimSubmisionMaster orderingProviderNpi(String orderingProviderNpi) {
        this.setOrderingProviderNpi(orderingProviderNpi);
        return this;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getPatientRelationshipInsured() {
        return this.patientRelationshipInsured;
    }

    public PrimaryClaimSubmisionMaster patientRelationshipInsured(String patientRelationshipInsured) {
        this.setPatientRelationshipInsured(patientRelationshipInsured);
        return this;
    }

    public void setPatientRelationshipInsured(String patientRelationshipInsured) {
        this.patientRelationshipInsured = patientRelationshipInsured;
    }

    public String getPatientConditionEmployment() {
        return this.patientConditionEmployment;
    }

    public PrimaryClaimSubmisionMaster patientConditionEmployment(String patientConditionEmployment) {
        this.setPatientConditionEmployment(patientConditionEmployment);
        return this;
    }

    public void setPatientConditionEmployment(String patientConditionEmployment) {
        this.patientConditionEmployment = patientConditionEmployment;
    }

    public String getPatientConditionAutoAccident() {
        return this.patientConditionAutoAccident;
    }

    public PrimaryClaimSubmisionMaster patientConditionAutoAccident(String patientConditionAutoAccident) {
        this.setPatientConditionAutoAccident(patientConditionAutoAccident);
        return this;
    }

    public void setPatientConditionAutoAccident(String patientConditionAutoAccident) {
        this.patientConditionAutoAccident = patientConditionAutoAccident;
    }

    public String getPatientConditionOtherAccident() {
        return this.patientConditionOtherAccident;
    }

    public PrimaryClaimSubmisionMaster patientConditionOtherAccident(String patientConditionOtherAccident) {
        this.setPatientConditionOtherAccident(patientConditionOtherAccident);
        return this;
    }

    public void setPatientConditionOtherAccident(String patientConditionOtherAccident) {
        this.patientConditionOtherAccident = patientConditionOtherAccident;
    }

    public String getIsNextLevelInsurerPresentStatus() {
        return this.isNextLevelInsurerPresentStatus;
    }

    public PrimaryClaimSubmisionMaster isNextLevelInsurerPresentStatus(String isNextLevelInsurerPresentStatus) {
        this.setIsNextLevelInsurerPresentStatus(isNextLevelInsurerPresentStatus);
        return this;
    }

    public void setIsNextLevelInsurerPresentStatus(String isNextLevelInsurerPresentStatus) {
        this.isNextLevelInsurerPresentStatus = isNextLevelInsurerPresentStatus;
    }

    public LocalDate getOriginalDos() {
        return this.originalDos;
    }

    public PrimaryClaimSubmisionMaster originalDos(LocalDate originalDos) {
        this.setOriginalDos(originalDos);
        return this;
    }

    public void setOriginalDos(LocalDate originalDos) {
        this.originalDos = originalDos;
    }

    public String getParNo() {
        return this.parNo;
    }

    public PrimaryClaimSubmisionMaster parNo(String parNo) {
        this.setParNo(parNo);
        return this;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public String getBillingProviderTaxonomy() {
        return this.billingProviderTaxonomy;
    }

    public PrimaryClaimSubmisionMaster billingProviderTaxonomy(String billingProviderTaxonomy) {
        this.setBillingProviderTaxonomy(billingProviderTaxonomy);
        return this;
    }

    public void setBillingProviderTaxonomy(String billingProviderTaxonomy) {
        this.billingProviderTaxonomy = billingProviderTaxonomy;
    }

    public String getServiceProviderNpi() {
        return this.serviceProviderNpi;
    }

    public PrimaryClaimSubmisionMaster serviceProviderNpi(String serviceProviderNpi) {
        this.setServiceProviderNpi(serviceProviderNpi);
        return this;
    }

    public void setServiceProviderNpi(String serviceProviderNpi) {
        this.serviceProviderNpi = serviceProviderNpi;
    }

    public String getServiceProviderOrganisationName() {
        return this.serviceProviderOrganisationName;
    }

    public PrimaryClaimSubmisionMaster serviceProviderOrganisationName(String serviceProviderOrganisationName) {
        this.setServiceProviderOrganisationName(serviceProviderOrganisationName);
        return this;
    }

    public void setServiceProviderOrganisationName(String serviceProviderOrganisationName) {
        this.serviceProviderOrganisationName = serviceProviderOrganisationName;
    }

    public String getServiceProviderAddressLine1() {
        return this.serviceProviderAddressLine1;
    }

    public PrimaryClaimSubmisionMaster serviceProviderAddressLine1(String serviceProviderAddressLine1) {
        this.setServiceProviderAddressLine1(serviceProviderAddressLine1);
        return this;
    }

    public void setServiceProviderAddressLine1(String serviceProviderAddressLine1) {
        this.serviceProviderAddressLine1 = serviceProviderAddressLine1;
    }

    public String getServiceProviderAddressLine2() {
        return this.serviceProviderAddressLine2;
    }

    public PrimaryClaimSubmisionMaster serviceProviderAddressLine2(String serviceProviderAddressLine2) {
        this.setServiceProviderAddressLine2(serviceProviderAddressLine2);
        return this;
    }

    public void setServiceProviderAddressLine2(String serviceProviderAddressLine2) {
        this.serviceProviderAddressLine2 = serviceProviderAddressLine2;
    }

    public String getServiceProviderCity() {
        return this.serviceProviderCity;
    }

    public PrimaryClaimSubmisionMaster serviceProviderCity(String serviceProviderCity) {
        this.setServiceProviderCity(serviceProviderCity);
        return this;
    }

    public void setServiceProviderCity(String serviceProviderCity) {
        this.serviceProviderCity = serviceProviderCity;
    }

    public String getServiceProviderState() {
        return this.serviceProviderState;
    }

    public PrimaryClaimSubmisionMaster serviceProviderState(String serviceProviderState) {
        this.setServiceProviderState(serviceProviderState);
        return this;
    }

    public void setServiceProviderState(String serviceProviderState) {
        this.serviceProviderState = serviceProviderState;
    }

    public String getServiceProviderCountry() {
        return this.serviceProviderCountry;
    }

    public PrimaryClaimSubmisionMaster serviceProviderCountry(String serviceProviderCountry) {
        this.setServiceProviderCountry(serviceProviderCountry);
        return this;
    }

    public void setServiceProviderCountry(String serviceProviderCountry) {
        this.serviceProviderCountry = serviceProviderCountry;
    }

    public String getServiceProviderZipCode() {
        return this.serviceProviderZipCode;
    }

    public PrimaryClaimSubmisionMaster serviceProviderZipCode(String serviceProviderZipCode) {
        this.setServiceProviderZipCode(serviceProviderZipCode);
        return this;
    }

    public void setServiceProviderZipCode(String serviceProviderZipCode) {
        this.serviceProviderZipCode = serviceProviderZipCode;
    }

    public String getServiceProviderTaxonomy() {
        return this.serviceProviderTaxonomy;
    }

    public PrimaryClaimSubmisionMaster serviceProviderTaxonomy(String serviceProviderTaxonomy) {
        this.setServiceProviderTaxonomy(serviceProviderTaxonomy);
        return this;
    }

    public void setServiceProviderTaxonomy(String serviceProviderTaxonomy) {
        this.serviceProviderTaxonomy = serviceProviderTaxonomy;
    }

    public String getCms1500FormName() {
        return this.cms1500FormName;
    }

    public PrimaryClaimSubmisionMaster cms1500FormName(String cms1500FormName) {
        this.setCms1500FormName(cms1500FormName);
        return this;
    }

    public void setCms1500FormName(String cms1500FormName) {
        this.cms1500FormName = cms1500FormName;
    }

    public String getTradingPartnerAddressLine1() {
        return this.tradingPartnerAddressLine1;
    }

    public PrimaryClaimSubmisionMaster tradingPartnerAddressLine1(String tradingPartnerAddressLine1) {
        this.setTradingPartnerAddressLine1(tradingPartnerAddressLine1);
        return this;
    }

    public void setTradingPartnerAddressLine1(String tradingPartnerAddressLine1) {
        this.tradingPartnerAddressLine1 = tradingPartnerAddressLine1;
    }

    public String getTradingPartnerAddressLine2() {
        return this.tradingPartnerAddressLine2;
    }

    public PrimaryClaimSubmisionMaster tradingPartnerAddressLine2(String tradingPartnerAddressLine2) {
        this.setTradingPartnerAddressLine2(tradingPartnerAddressLine2);
        return this;
    }

    public void setTradingPartnerAddressLine2(String tradingPartnerAddressLine2) {
        this.tradingPartnerAddressLine2 = tradingPartnerAddressLine2;
    }

    public String getTradingPatnerCity() {
        return this.tradingPatnerCity;
    }

    public PrimaryClaimSubmisionMaster tradingPatnerCity(String tradingPatnerCity) {
        this.setTradingPatnerCity(tradingPatnerCity);
        return this;
    }

    public void setTradingPatnerCity(String tradingPatnerCity) {
        this.tradingPatnerCity = tradingPatnerCity;
    }

    public String getTradingPartnerState() {
        return this.tradingPartnerState;
    }

    public PrimaryClaimSubmisionMaster tradingPartnerState(String tradingPartnerState) {
        this.setTradingPartnerState(tradingPartnerState);
        return this;
    }

    public void setTradingPartnerState(String tradingPartnerState) {
        this.tradingPartnerState = tradingPartnerState;
    }

    public String getTradingPartnerZip() {
        return this.tradingPartnerZip;
    }

    public PrimaryClaimSubmisionMaster tradingPartnerZip(String tradingPartnerZip) {
        this.setTradingPartnerZip(tradingPartnerZip);
        return this;
    }

    public void setTradingPartnerZip(String tradingPartnerZip) {
        this.tradingPartnerZip = tradingPartnerZip;
    }

    public String getDiagnosisCodeType() {
        return this.diagnosisCodeType;
    }

    public PrimaryClaimSubmisionMaster diagnosisCodeType(String diagnosisCodeType) {
        this.setDiagnosisCodeType(diagnosisCodeType);
        return this;
    }

    public void setDiagnosisCodeType(String diagnosisCodeType) {
        this.diagnosisCodeType = diagnosisCodeType;
    }

    public UUID getPrimaryClaimSubmisionMasterUuid() {
        return this.primaryClaimSubmisionMasterUuid;
    }

    public PrimaryClaimSubmisionMaster primaryClaimSubmisionMasterUuid(UUID primaryClaimSubmisionMasterUuid) {
        this.setPrimaryClaimSubmisionMasterUuid(primaryClaimSubmisionMasterUuid);
        return this;
    }

    public void setPrimaryClaimSubmisionMasterUuid(UUID primaryClaimSubmisionMasterUuid) {
        this.primaryClaimSubmisionMasterUuid = primaryClaimSubmisionMasterUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrimaryClaimSubmisionMaster)) {
            return false;
        }
        return (
            changeHealthPrimarySubmisionMasterId != null &&
            changeHealthPrimarySubmisionMasterId.equals(((PrimaryClaimSubmisionMaster) o).changeHealthPrimarySubmisionMasterId)
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
        return "PrimaryClaimSubmisionMaster{" +
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
            ", insertedById=" + getInsertedById() +
            ", insertedDate='" + getInsertedDate() + "'" +
            ", ipdatedById=" + getIpdatedById() +
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
            ", tradingPatnerCity='" + getTradingPatnerCity() + "'" +
            ", tradingPartnerState='" + getTradingPartnerState() + "'" +
            ", tradingPartnerZip='" + getTradingPartnerZip() + "'" +
            ", diagnosisCodeType='" + getDiagnosisCodeType() + "'" +
            ", primaryClaimSubmisionMasterUuid='" + getPrimaryClaimSubmisionMasterUuid() + "'" +
            "}";
    }
}
