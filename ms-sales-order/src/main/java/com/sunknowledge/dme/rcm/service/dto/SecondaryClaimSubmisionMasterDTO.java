package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SecondaryClaimSubmisionMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SecondaryClaimSubmisionMasterDTO implements Serializable {

    private Long changeHealthSecondarySubmisionMasterId;

    private Long salesOrderId;

    private String claimControlNo;

    private String tradingPartnerServiceId;

    private String tradingPartnerName;

    private String submitterOrganizationName;

    private String submitterContactPersonName;

    private String submitterContactNo;

    private String receiverOrganizationName;

    private String subscriberMemberIdNo;

    private String subscriberPaymentResponsibilityLevelCode;

    private String subscriberFirstName;

    private String subscriberLastName;

    private String subscriberGender;

    private LocalDate subscriberDob;

    private String secondaryInsurerPolicyNo;

    private String subscriberAddressLine1;

    private String subscriberCity;

    private String subscriberState;

    private String subscriberZipCode;

    private String providerType;

    private String billingProviderNpi;

    private String billingProviderEin;

    private String billingProviderOrganizationName;

    private String billingProviderAddressLine1;

    private String billingProviderAddressLine2;

    private String billingProviderCity;

    private String billingProviderState;

    private String billingProviderZipCode;

    private String billingProviderContactPersonName;

    private String billingProviderContactNo;

    private String claimFilingCode;

    private String patientAccountNo;

    private Double claimChargeAmount;

    private String posCode;

    private String claimFrequencyCode;

    private String signatureIndicator;

    private String planParticipationCode;

    private String benefitsAssignmentCertificationIndicator;

    private String releaseInformationCode;

    private String primaryDiagnosis;

    private String icd10DiagnosisCode1;

    private String icd10DiagnosisCode2;

    private String icd10DiagnosisCode3;

    private String icd10DiagnosisCode4;

    private String icd10DiagnosisCode5;

    private String icd10DiagnosisCode6;

    private String icd10DiagnosisCode7;

    private String icd10DiagnosisCode8;

    private String icd10DiagnosisCode9;

    private String icd10DiagnosisCode10;

    private String icd10DiagnosisCode11;

    private String icd10DiagnosisCode12;

    private String insertedById;

    private LocalDate insertedDate;

    private String billingProviderType;

    private String insertedByName;

    private String status;

    private String otherSubscriberAddress1;

    private String otherSubscriberAddress2;

    private String otherSubscriberCity;

    private String otherSubscriberState;

    private String otherSubscriberZip;

    private String otherInsuredQualifier;

    private String otherInsuredLastName;

    private String otherInsuredFirstName;

    private String otherInsuredIdentifierTypeCode;

    private String otherInsuredIdentifier;

    private String otherPayerOrganizationName;

    private String otherPayerIdentifierTypeCode;

    private String otherPayerIdentifier;

    private LocalDate otherPayerAdjudicationOrPaymentDate;

    private String otherPayerClaimAdjustmentIndicator;

    private String otherPayerClaimControlNumber;

    private Double payerPaidAmount;

    private String paymentResponsibilityLevelCode;

    private String individualRelationshipCode;

    private String claimFilingIndicatorCode;

    private String otherPayerBenefitsAssignmentCertificationIndicator;

    private String releaseOfInformationCode;

    private Double remainingPatientLiability;

    private String patientSignatureGeneratedForPatient;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private Long changeHealthPrimarySubmisionMasterId;

    private String insuredFirstName;

    private String insuredLastName;

    private String insuredAddressLine1;

    private String insuredAddressLine2;

    private String insuredCity;

    private String insuredState;

    private String insuredZip;

    private String insuredContactNo;

    private LocalDate insuredDob;

    private String insuredGender;

    private String orderingProviderFirstName;

    private String orderingProviderLastName;

    private String orderingProviderNpi;

    private String patientRelationshipInsured;

    private String patientConditionEmployment;

    private String patientConditionAutoAccident;

    private String patientConditionOtherAccident;

    private String isNextLevelInsurerPresentStatus;

    private LocalDate originalDos;

    private String parNo;

    private String billingProviderTaxonomy;

    private String serviceProviderNpi;

    private String serviceProviderOrganisationName;

    private String serviceProviderAddressLine1;

    private String serviceProviderAddressLine2;

    private String serviceProviderCity;

    private String serviceProviderState;

    private String serviceProviderCountry;

    private String serviceProviderZipCode;

    private String serviceProviderTaxonomy;

    private String cms1500FormName;

    private String tradingPartnerAddressLine1;

    private String tradingPartnerAddressLine2;

    private String tradingPartnerCity;

    private String tradingPartnerState;

    private String tradingPartnerZip;

    private String diagnosisCodeType;

    private UUID secondaryClaimSubmisionMasterUuid;

    public Long getChangeHealthSecondarySubmisionMasterId() {
        return changeHealthSecondarySubmisionMasterId;
    }

    public void setChangeHealthSecondarySubmisionMasterId(Long changeHealthSecondarySubmisionMasterId) {
        this.changeHealthSecondarySubmisionMasterId = changeHealthSecondarySubmisionMasterId;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getClaimControlNo() {
        return claimControlNo;
    }

    public void setClaimControlNo(String claimControlNo) {
        this.claimControlNo = claimControlNo;
    }

    public String getTradingPartnerServiceId() {
        return tradingPartnerServiceId;
    }

    public void setTradingPartnerServiceId(String tradingPartnerServiceId) {
        this.tradingPartnerServiceId = tradingPartnerServiceId;
    }

    public String getTradingPartnerName() {
        return tradingPartnerName;
    }

    public void setTradingPartnerName(String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    public String getSubmitterOrganizationName() {
        return submitterOrganizationName;
    }

    public void setSubmitterOrganizationName(String submitterOrganizationName) {
        this.submitterOrganizationName = submitterOrganizationName;
    }

    public String getSubmitterContactPersonName() {
        return submitterContactPersonName;
    }

    public void setSubmitterContactPersonName(String submitterContactPersonName) {
        this.submitterContactPersonName = submitterContactPersonName;
    }

    public String getSubmitterContactNo() {
        return submitterContactNo;
    }

    public void setSubmitterContactNo(String submitterContactNo) {
        this.submitterContactNo = submitterContactNo;
    }

    public String getReceiverOrganizationName() {
        return receiverOrganizationName;
    }

    public void setReceiverOrganizationName(String receiverOrganizationName) {
        this.receiverOrganizationName = receiverOrganizationName;
    }

    public String getSubscriberMemberIdNo() {
        return subscriberMemberIdNo;
    }

    public void setSubscriberMemberIdNo(String subscriberMemberIdNo) {
        this.subscriberMemberIdNo = subscriberMemberIdNo;
    }

    public String getSubscriberPaymentResponsibilityLevelCode() {
        return subscriberPaymentResponsibilityLevelCode;
    }

    public void setSubscriberPaymentResponsibilityLevelCode(String subscriberPaymentResponsibilityLevelCode) {
        this.subscriberPaymentResponsibilityLevelCode = subscriberPaymentResponsibilityLevelCode;
    }

    public String getSubscriberFirstName() {
        return subscriberFirstName;
    }

    public void setSubscriberFirstName(String subscriberFirstName) {
        this.subscriberFirstName = subscriberFirstName;
    }

    public String getSubscriberLastName() {
        return subscriberLastName;
    }

    public void setSubscriberLastName(String subscriberLastName) {
        this.subscriberLastName = subscriberLastName;
    }

    public String getSubscriberGender() {
        return subscriberGender;
    }

    public void setSubscriberGender(String subscriberGender) {
        this.subscriberGender = subscriberGender;
    }

    public LocalDate getSubscriberDob() {
        return subscriberDob;
    }

    public void setSubscriberDob(LocalDate subscriberDob) {
        this.subscriberDob = subscriberDob;
    }

    public String getSecondaryInsurerPolicyNo() {
        return secondaryInsurerPolicyNo;
    }

    public void setSecondaryInsurerPolicyNo(String secondaryInsurerPolicyNo) {
        this.secondaryInsurerPolicyNo = secondaryInsurerPolicyNo;
    }

    public String getSubscriberAddressLine1() {
        return subscriberAddressLine1;
    }

    public void setSubscriberAddressLine1(String subscriberAddressLine1) {
        this.subscriberAddressLine1 = subscriberAddressLine1;
    }

    public String getSubscriberCity() {
        return subscriberCity;
    }

    public void setSubscriberCity(String subscriberCity) {
        this.subscriberCity = subscriberCity;
    }

    public String getSubscriberState() {
        return subscriberState;
    }

    public void setSubscriberState(String subscriberState) {
        this.subscriberState = subscriberState;
    }

    public String getSubscriberZipCode() {
        return subscriberZipCode;
    }

    public void setSubscriberZipCode(String subscriberZipCode) {
        this.subscriberZipCode = subscriberZipCode;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getBillingProviderNpi() {
        return billingProviderNpi;
    }

    public void setBillingProviderNpi(String billingProviderNpi) {
        this.billingProviderNpi = billingProviderNpi;
    }

    public String getBillingProviderEin() {
        return billingProviderEin;
    }

    public void setBillingProviderEin(String billingProviderEin) {
        this.billingProviderEin = billingProviderEin;
    }

    public String getBillingProviderOrganizationName() {
        return billingProviderOrganizationName;
    }

    public void setBillingProviderOrganizationName(String billingProviderOrganizationName) {
        this.billingProviderOrganizationName = billingProviderOrganizationName;
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

    public String getBillingProviderZipCode() {
        return billingProviderZipCode;
    }

    public void setBillingProviderZipCode(String billingProviderZipCode) {
        this.billingProviderZipCode = billingProviderZipCode;
    }

    public String getBillingProviderContactPersonName() {
        return billingProviderContactPersonName;
    }

    public void setBillingProviderContactPersonName(String billingProviderContactPersonName) {
        this.billingProviderContactPersonName = billingProviderContactPersonName;
    }

    public String getBillingProviderContactNo() {
        return billingProviderContactNo;
    }

    public void setBillingProviderContactNo(String billingProviderContactNo) {
        this.billingProviderContactNo = billingProviderContactNo;
    }

    public String getClaimFilingCode() {
        return claimFilingCode;
    }

    public void setClaimFilingCode(String claimFilingCode) {
        this.claimFilingCode = claimFilingCode;
    }

    public String getPatientAccountNo() {
        return patientAccountNo;
    }

    public void setPatientAccountNo(String patientAccountNo) {
        this.patientAccountNo = patientAccountNo;
    }

    public Double getClaimChargeAmount() {
        return claimChargeAmount;
    }

    public void setClaimChargeAmount(Double claimChargeAmount) {
        this.claimChargeAmount = claimChargeAmount;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getClaimFrequencyCode() {
        return claimFrequencyCode;
    }

    public void setClaimFrequencyCode(String claimFrequencyCode) {
        this.claimFrequencyCode = claimFrequencyCode;
    }

    public String getSignatureIndicator() {
        return signatureIndicator;
    }

    public void setSignatureIndicator(String signatureIndicator) {
        this.signatureIndicator = signatureIndicator;
    }

    public String getPlanParticipationCode() {
        return planParticipationCode;
    }

    public void setPlanParticipationCode(String planParticipationCode) {
        this.planParticipationCode = planParticipationCode;
    }

    public String getBenefitsAssignmentCertificationIndicator() {
        return benefitsAssignmentCertificationIndicator;
    }

    public void setBenefitsAssignmentCertificationIndicator(String benefitsAssignmentCertificationIndicator) {
        this.benefitsAssignmentCertificationIndicator = benefitsAssignmentCertificationIndicator;
    }

    public String getReleaseInformationCode() {
        return releaseInformationCode;
    }

    public void setReleaseInformationCode(String releaseInformationCode) {
        this.releaseInformationCode = releaseInformationCode;
    }

    public String getPrimaryDiagnosis() {
        return primaryDiagnosis;
    }

    public void setPrimaryDiagnosis(String primaryDiagnosis) {
        this.primaryDiagnosis = primaryDiagnosis;
    }

    public String getIcd10DiagnosisCode1() {
        return icd10DiagnosisCode1;
    }

    public void setIcd10DiagnosisCode1(String icd10DiagnosisCode1) {
        this.icd10DiagnosisCode1 = icd10DiagnosisCode1;
    }

    public String getIcd10DiagnosisCode2() {
        return icd10DiagnosisCode2;
    }

    public void setIcd10DiagnosisCode2(String icd10DiagnosisCode2) {
        this.icd10DiagnosisCode2 = icd10DiagnosisCode2;
    }

    public String getIcd10DiagnosisCode3() {
        return icd10DiagnosisCode3;
    }

    public void setIcd10DiagnosisCode3(String icd10DiagnosisCode3) {
        this.icd10DiagnosisCode3 = icd10DiagnosisCode3;
    }

    public String getIcd10DiagnosisCode4() {
        return icd10DiagnosisCode4;
    }

    public void setIcd10DiagnosisCode4(String icd10DiagnosisCode4) {
        this.icd10DiagnosisCode4 = icd10DiagnosisCode4;
    }

    public String getIcd10DiagnosisCode5() {
        return icd10DiagnosisCode5;
    }

    public void setIcd10DiagnosisCode5(String icd10DiagnosisCode5) {
        this.icd10DiagnosisCode5 = icd10DiagnosisCode5;
    }

    public String getIcd10DiagnosisCode6() {
        return icd10DiagnosisCode6;
    }

    public void setIcd10DiagnosisCode6(String icd10DiagnosisCode6) {
        this.icd10DiagnosisCode6 = icd10DiagnosisCode6;
    }

    public String getIcd10DiagnosisCode7() {
        return icd10DiagnosisCode7;
    }

    public void setIcd10DiagnosisCode7(String icd10DiagnosisCode7) {
        this.icd10DiagnosisCode7 = icd10DiagnosisCode7;
    }

    public String getIcd10DiagnosisCode8() {
        return icd10DiagnosisCode8;
    }

    public void setIcd10DiagnosisCode8(String icd10DiagnosisCode8) {
        this.icd10DiagnosisCode8 = icd10DiagnosisCode8;
    }

    public String getIcd10DiagnosisCode9() {
        return icd10DiagnosisCode9;
    }

    public void setIcd10DiagnosisCode9(String icd10DiagnosisCode9) {
        this.icd10DiagnosisCode9 = icd10DiagnosisCode9;
    }

    public String getIcd10DiagnosisCode10() {
        return icd10DiagnosisCode10;
    }

    public void setIcd10DiagnosisCode10(String icd10DiagnosisCode10) {
        this.icd10DiagnosisCode10 = icd10DiagnosisCode10;
    }

    public String getIcd10DiagnosisCode11() {
        return icd10DiagnosisCode11;
    }

    public void setIcd10DiagnosisCode11(String icd10DiagnosisCode11) {
        this.icd10DiagnosisCode11 = icd10DiagnosisCode11;
    }

    public String getIcd10DiagnosisCode12() {
        return icd10DiagnosisCode12;
    }

    public void setIcd10DiagnosisCode12(String icd10DiagnosisCode12) {
        this.icd10DiagnosisCode12 = icd10DiagnosisCode12;
    }

    public String getInsertedById() {
        return insertedById;
    }

    public void setInsertedById(String insertedById) {
        this.insertedById = insertedById;
    }

    public LocalDate getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(LocalDate insertedDate) {
        this.insertedDate = insertedDate;
    }

    public String getBillingProviderType() {
        return billingProviderType;
    }

    public void setBillingProviderType(String billingProviderType) {
        this.billingProviderType = billingProviderType;
    }

    public String getInsertedByName() {
        return insertedByName;
    }

    public void setInsertedByName(String insertedByName) {
        this.insertedByName = insertedByName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtherSubscriberAddress1() {
        return otherSubscriberAddress1;
    }

    public void setOtherSubscriberAddress1(String otherSubscriberAddress1) {
        this.otherSubscriberAddress1 = otherSubscriberAddress1;
    }

    public String getOtherSubscriberAddress2() {
        return otherSubscriberAddress2;
    }

    public void setOtherSubscriberAddress2(String otherSubscriberAddress2) {
        this.otherSubscriberAddress2 = otherSubscriberAddress2;
    }

    public String getOtherSubscriberCity() {
        return otherSubscriberCity;
    }

    public void setOtherSubscriberCity(String otherSubscriberCity) {
        this.otherSubscriberCity = otherSubscriberCity;
    }

    public String getOtherSubscriberState() {
        return otherSubscriberState;
    }

    public void setOtherSubscriberState(String otherSubscriberState) {
        this.otherSubscriberState = otherSubscriberState;
    }

    public String getOtherSubscriberZip() {
        return otherSubscriberZip;
    }

    public void setOtherSubscriberZip(String otherSubscriberZip) {
        this.otherSubscriberZip = otherSubscriberZip;
    }

    public String getOtherInsuredQualifier() {
        return otherInsuredQualifier;
    }

    public void setOtherInsuredQualifier(String otherInsuredQualifier) {
        this.otherInsuredQualifier = otherInsuredQualifier;
    }

    public String getOtherInsuredLastName() {
        return otherInsuredLastName;
    }

    public void setOtherInsuredLastName(String otherInsuredLastName) {
        this.otherInsuredLastName = otherInsuredLastName;
    }

    public String getOtherInsuredFirstName() {
        return otherInsuredFirstName;
    }

    public void setOtherInsuredFirstName(String otherInsuredFirstName) {
        this.otherInsuredFirstName = otherInsuredFirstName;
    }

    public String getOtherInsuredIdentifierTypeCode() {
        return otherInsuredIdentifierTypeCode;
    }

    public void setOtherInsuredIdentifierTypeCode(String otherInsuredIdentifierTypeCode) {
        this.otherInsuredIdentifierTypeCode = otherInsuredIdentifierTypeCode;
    }

    public String getOtherInsuredIdentifier() {
        return otherInsuredIdentifier;
    }

    public void setOtherInsuredIdentifier(String otherInsuredIdentifier) {
        this.otherInsuredIdentifier = otherInsuredIdentifier;
    }

    public String getOtherPayerOrganizationName() {
        return otherPayerOrganizationName;
    }

    public void setOtherPayerOrganizationName(String otherPayerOrganizationName) {
        this.otherPayerOrganizationName = otherPayerOrganizationName;
    }

    public String getOtherPayerIdentifierTypeCode() {
        return otherPayerIdentifierTypeCode;
    }

    public void setOtherPayerIdentifierTypeCode(String otherPayerIdentifierTypeCode) {
        this.otherPayerIdentifierTypeCode = otherPayerIdentifierTypeCode;
    }

    public String getOtherPayerIdentifier() {
        return otherPayerIdentifier;
    }

    public void setOtherPayerIdentifier(String otherPayerIdentifier) {
        this.otherPayerIdentifier = otherPayerIdentifier;
    }

    public LocalDate getOtherPayerAdjudicationOrPaymentDate() {
        return otherPayerAdjudicationOrPaymentDate;
    }

    public void setOtherPayerAdjudicationOrPaymentDate(LocalDate otherPayerAdjudicationOrPaymentDate) {
        this.otherPayerAdjudicationOrPaymentDate = otherPayerAdjudicationOrPaymentDate;
    }

    public String getOtherPayerClaimAdjustmentIndicator() {
        return otherPayerClaimAdjustmentIndicator;
    }

    public void setOtherPayerClaimAdjustmentIndicator(String otherPayerClaimAdjustmentIndicator) {
        this.otherPayerClaimAdjustmentIndicator = otherPayerClaimAdjustmentIndicator;
    }

    public String getOtherPayerClaimControlNumber() {
        return otherPayerClaimControlNumber;
    }

    public void setOtherPayerClaimControlNumber(String otherPayerClaimControlNumber) {
        this.otherPayerClaimControlNumber = otherPayerClaimControlNumber;
    }

    public Double getPayerPaidAmount() {
        return payerPaidAmount;
    }

    public void setPayerPaidAmount(Double payerPaidAmount) {
        this.payerPaidAmount = payerPaidAmount;
    }

    public String getPaymentResponsibilityLevelCode() {
        return paymentResponsibilityLevelCode;
    }

    public void setPaymentResponsibilityLevelCode(String paymentResponsibilityLevelCode) {
        this.paymentResponsibilityLevelCode = paymentResponsibilityLevelCode;
    }

    public String getIndividualRelationshipCode() {
        return individualRelationshipCode;
    }

    public void setIndividualRelationshipCode(String individualRelationshipCode) {
        this.individualRelationshipCode = individualRelationshipCode;
    }

    public String getClaimFilingIndicatorCode() {
        return claimFilingIndicatorCode;
    }

    public void setClaimFilingIndicatorCode(String claimFilingIndicatorCode) {
        this.claimFilingIndicatorCode = claimFilingIndicatorCode;
    }

    public String getOtherPayerBenefitsAssignmentCertificationIndicator() {
        return otherPayerBenefitsAssignmentCertificationIndicator;
    }

    public void setOtherPayerBenefitsAssignmentCertificationIndicator(String otherPayerBenefitsAssignmentCertificationIndicator) {
        this.otherPayerBenefitsAssignmentCertificationIndicator = otherPayerBenefitsAssignmentCertificationIndicator;
    }

    public String getReleaseOfInformationCode() {
        return releaseOfInformationCode;
    }

    public void setReleaseOfInformationCode(String releaseOfInformationCode) {
        this.releaseOfInformationCode = releaseOfInformationCode;
    }

    public Double getRemainingPatientLiability() {
        return remainingPatientLiability;
    }

    public void setRemainingPatientLiability(Double remainingPatientLiability) {
        this.remainingPatientLiability = remainingPatientLiability;
    }

    public String getPatientSignatureGeneratedForPatient() {
        return patientSignatureGeneratedForPatient;
    }

    public void setPatientSignatureGeneratedForPatient(String patientSignatureGeneratedForPatient) {
        this.patientSignatureGeneratedForPatient = patientSignatureGeneratedForPatient;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
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

    public Long getChangeHealthPrimarySubmisionMasterId() {
        return changeHealthPrimarySubmisionMasterId;
    }

    public void setChangeHealthPrimarySubmisionMasterId(Long changeHealthPrimarySubmisionMasterId) {
        this.changeHealthPrimarySubmisionMasterId = changeHealthPrimarySubmisionMasterId;
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

    public LocalDate getInsuredDob() {
        return insuredDob;
    }

    public void setInsuredDob(LocalDate insuredDob) {
        this.insuredDob = insuredDob;
    }

    public String getInsuredGender() {
        return insuredGender;
    }

    public void setInsuredGender(String insuredGender) {
        this.insuredGender = insuredGender;
    }

    public String getOrderingProviderFirstName() {
        return orderingProviderFirstName;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
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

    public String getIsNextLevelInsurerPresentStatus() {
        return isNextLevelInsurerPresentStatus;
    }

    public void setIsNextLevelInsurerPresentStatus(String isNextLevelInsurerPresentStatus) {
        this.isNextLevelInsurerPresentStatus = isNextLevelInsurerPresentStatus;
    }

    public LocalDate getOriginalDos() {
        return originalDos;
    }

    public void setOriginalDos(LocalDate originalDos) {
        this.originalDos = originalDos;
    }

    public String getParNo() {
        return parNo;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public String getBillingProviderTaxonomy() {
        return billingProviderTaxonomy;
    }

    public void setBillingProviderTaxonomy(String billingProviderTaxonomy) {
        this.billingProviderTaxonomy = billingProviderTaxonomy;
    }

    public String getServiceProviderNpi() {
        return serviceProviderNpi;
    }

    public void setServiceProviderNpi(String serviceProviderNpi) {
        this.serviceProviderNpi = serviceProviderNpi;
    }

    public String getServiceProviderOrganisationName() {
        return serviceProviderOrganisationName;
    }

    public void setServiceProviderOrganisationName(String serviceProviderOrganisationName) {
        this.serviceProviderOrganisationName = serviceProviderOrganisationName;
    }

    public String getServiceProviderAddressLine1() {
        return serviceProviderAddressLine1;
    }

    public void setServiceProviderAddressLine1(String serviceProviderAddressLine1) {
        this.serviceProviderAddressLine1 = serviceProviderAddressLine1;
    }

    public String getServiceProviderAddressLine2() {
        return serviceProviderAddressLine2;
    }

    public void setServiceProviderAddressLine2(String serviceProviderAddressLine2) {
        this.serviceProviderAddressLine2 = serviceProviderAddressLine2;
    }

    public String getServiceProviderCity() {
        return serviceProviderCity;
    }

    public void setServiceProviderCity(String serviceProviderCity) {
        this.serviceProviderCity = serviceProviderCity;
    }

    public String getServiceProviderState() {
        return serviceProviderState;
    }

    public void setServiceProviderState(String serviceProviderState) {
        this.serviceProviderState = serviceProviderState;
    }

    public String getServiceProviderCountry() {
        return serviceProviderCountry;
    }

    public void setServiceProviderCountry(String serviceProviderCountry) {
        this.serviceProviderCountry = serviceProviderCountry;
    }

    public String getServiceProviderZipCode() {
        return serviceProviderZipCode;
    }

    public void setServiceProviderZipCode(String serviceProviderZipCode) {
        this.serviceProviderZipCode = serviceProviderZipCode;
    }

    public String getServiceProviderTaxonomy() {
        return serviceProviderTaxonomy;
    }

    public void setServiceProviderTaxonomy(String serviceProviderTaxonomy) {
        this.serviceProviderTaxonomy = serviceProviderTaxonomy;
    }

    public String getCms1500FormName() {
        return cms1500FormName;
    }

    public void setCms1500FormName(String cms1500FormName) {
        this.cms1500FormName = cms1500FormName;
    }

    public String getTradingPartnerAddressLine1() {
        return tradingPartnerAddressLine1;
    }

    public void setTradingPartnerAddressLine1(String tradingPartnerAddressLine1) {
        this.tradingPartnerAddressLine1 = tradingPartnerAddressLine1;
    }

    public String getTradingPartnerAddressLine2() {
        return tradingPartnerAddressLine2;
    }

    public void setTradingPartnerAddressLine2(String tradingPartnerAddressLine2) {
        this.tradingPartnerAddressLine2 = tradingPartnerAddressLine2;
    }

    public String getTradingPartnerCity() {
        return tradingPartnerCity;
    }

    public void setTradingPartnerCity(String tradingPartnerCity) {
        this.tradingPartnerCity = tradingPartnerCity;
    }

    public String getTradingPartnerState() {
        return tradingPartnerState;
    }

    public void setTradingPartnerState(String tradingPartnerState) {
        this.tradingPartnerState = tradingPartnerState;
    }

    public String getTradingPartnerZip() {
        return tradingPartnerZip;
    }

    public void setTradingPartnerZip(String tradingPartnerZip) {
        this.tradingPartnerZip = tradingPartnerZip;
    }

    public String getDiagnosisCodeType() {
        return diagnosisCodeType;
    }

    public void setDiagnosisCodeType(String diagnosisCodeType) {
        this.diagnosisCodeType = diagnosisCodeType;
    }

    public UUID getSecondaryClaimSubmisionMasterUuid() {
        return secondaryClaimSubmisionMasterUuid;
    }

    public void setSecondaryClaimSubmisionMasterUuid(UUID secondaryClaimSubmisionMasterUuid) {
        this.secondaryClaimSubmisionMasterUuid = secondaryClaimSubmisionMasterUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SecondaryClaimSubmisionMasterDTO)) {
            return false;
        }

        SecondaryClaimSubmisionMasterDTO secondaryClaimSubmisionMasterDTO = (SecondaryClaimSubmisionMasterDTO) o;
        if (this.changeHealthSecondarySubmisionMasterId == null) {
            return false;
        }
        return Objects.equals(
            this.changeHealthSecondarySubmisionMasterId,
            secondaryClaimSubmisionMasterDTO.changeHealthSecondarySubmisionMasterId
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.changeHealthSecondarySubmisionMasterId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SecondaryClaimSubmisionMasterDTO{" +
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
