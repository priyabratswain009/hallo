package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.PrimaryClaimsReSubmissionMaster} entity.
 */
public class PrimaryClaimsReSubmissionMasterDTO implements Serializable {

    @NotNull
    private Long changeHealthPrimaryResubmisionMasterId;

    @NotNull
    private Long salesOrderId;

    @NotNull
    private String claimControlNo;

    @NotNull
    private String tradingPartnerServiceId;

    @NotNull
    private String tradingPartnerName;

    @NotNull
    private String submitterOrganizationName;

    @NotNull
    private String submitterContactPersonName;

    @NotNull
    private String submitterContactNo;

    @NotNull
    private String receiverOrganizationName;

    @NotNull
    private String subscriberMemberIdNo;

    @NotNull
    private String subscriberPaymentResponsibilityLevelCode;

    @NotNull
    private String subscriberFirstName;

    @NotNull
    private String subscriberLastName;

    @NotNull
    private String subscriberGender;

    @NotNull
    private LocalDate subscriberDob;

    private String primaryInsurerPolicyNo;

    private String subscriberAddressLine1;

    @NotNull
    private String subscriberCity;

    @NotNull
    private String subscriberState;

    @NotNull
    private String subscriberZipCode;

    @NotNull
    private String billingProviderNpi;

    @NotNull
    private String billingProviderEin;

    @NotNull
    private String billingProviderOrganizationName;

    @NotNull
    private String billingProviderAddressLine1;

    @NotNull
    private String billingProviderCity;

    @NotNull
    private String billingProviderState;

    @NotNull
    private String billingProviderZipCode;

    @NotNull
    private String billingProviderContactPersonName;

    @NotNull
    private String billingProviderContactNo;

    @NotNull
    private String claimFilingCode;

    @NotNull
    private Double claimChargeAmount;

    @NotNull
    private String posCode;

    @NotNull
    private String claimFrequencyCode;

    @NotNull
    private String signatureIndicator;

    @NotNull
    private String planParticipationCode;

    @NotNull
    private String benefitsAssignmentCertificationIndicator;

    @NotNull
    private String releaseInformationCode;

    private String primaryDiagnosis;

    private String icd10diagnosisCode1;

    private String icd10diagnosisCode2;

    private String icd10diagnosisCode3;

    private String icd10diagnosisCode4;

    private String icd10diagnosisCode5;

    private String icd10diagnosisCode6;

    private String icd10diagnosisCode7;

    private String icd10diagnosisCode8;

    private String icd10diagnosisCode9;

    private String icd10diagnosisCode10;

    private String icd10diagnosisCode11;

    private String icd10diagnosisCode12;

    private Long insertedById;

    private ZonedDateTime insertedDate;

    private Long updatedById;

    private ZonedDateTime updatedDate;

    @NotNull
    private String billingProviderType;

    private String insertedByName;

    private String updatedByName;

    private String status;

    private String billingProviderAddressLine2;

    @NotNull
    private String payerClaimControlNumber;

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

    public Long getChangeHealthPrimaryResubmisionMasterId() {
        return changeHealthPrimaryResubmisionMasterId;
    }

    public void setChangeHealthPrimaryResubmisionMasterId(Long changeHealthPrimaryResubmisionMasterId) {
        this.changeHealthPrimaryResubmisionMasterId = changeHealthPrimaryResubmisionMasterId;
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

    public String getPrimaryInsurerPolicyNo() {
        return primaryInsurerPolicyNo;
    }

    public void setPrimaryInsurerPolicyNo(String primaryInsurerPolicyNo) {
        this.primaryInsurerPolicyNo = primaryInsurerPolicyNo;
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

    public String getIcd10diagnosisCode1() {
        return icd10diagnosisCode1;
    }

    public void setIcd10diagnosisCode1(String icd10diagnosisCode1) {
        this.icd10diagnosisCode1 = icd10diagnosisCode1;
    }

    public String getIcd10diagnosisCode2() {
        return icd10diagnosisCode2;
    }

    public void setIcd10diagnosisCode2(String icd10diagnosisCode2) {
        this.icd10diagnosisCode2 = icd10diagnosisCode2;
    }

    public String getIcd10diagnosisCode3() {
        return icd10diagnosisCode3;
    }

    public void setIcd10diagnosisCode3(String icd10diagnosisCode3) {
        this.icd10diagnosisCode3 = icd10diagnosisCode3;
    }

    public String getIcd10diagnosisCode4() {
        return icd10diagnosisCode4;
    }

    public void setIcd10diagnosisCode4(String icd10diagnosisCode4) {
        this.icd10diagnosisCode4 = icd10diagnosisCode4;
    }

    public String getIcd10diagnosisCode5() {
        return icd10diagnosisCode5;
    }

    public void setIcd10diagnosisCode5(String icd10diagnosisCode5) {
        this.icd10diagnosisCode5 = icd10diagnosisCode5;
    }

    public String getIcd10diagnosisCode6() {
        return icd10diagnosisCode6;
    }

    public void setIcd10diagnosisCode6(String icd10diagnosisCode6) {
        this.icd10diagnosisCode6 = icd10diagnosisCode6;
    }

    public String getIcd10diagnosisCode7() {
        return icd10diagnosisCode7;
    }

    public void setIcd10diagnosisCode7(String icd10diagnosisCode7) {
        this.icd10diagnosisCode7 = icd10diagnosisCode7;
    }

    public String getIcd10diagnosisCode8() {
        return icd10diagnosisCode8;
    }

    public void setIcd10diagnosisCode8(String icd10diagnosisCode8) {
        this.icd10diagnosisCode8 = icd10diagnosisCode8;
    }

    public String getIcd10diagnosisCode9() {
        return icd10diagnosisCode9;
    }

    public void setIcd10diagnosisCode9(String icd10diagnosisCode9) {
        this.icd10diagnosisCode9 = icd10diagnosisCode9;
    }

    public String getIcd10diagnosisCode10() {
        return icd10diagnosisCode10;
    }

    public void setIcd10diagnosisCode10(String icd10diagnosisCode10) {
        this.icd10diagnosisCode10 = icd10diagnosisCode10;
    }

    public String getIcd10diagnosisCode11() {
        return icd10diagnosisCode11;
    }

    public void setIcd10diagnosisCode11(String icd10diagnosisCode11) {
        this.icd10diagnosisCode11 = icd10diagnosisCode11;
    }

    public String getIcd10diagnosisCode12() {
        return icd10diagnosisCode12;
    }

    public void setIcd10diagnosisCode12(String icd10diagnosisCode12) {
        this.icd10diagnosisCode12 = icd10diagnosisCode12;
    }

    public Long getInsertedById() {
        return insertedById;
    }

    public void setInsertedById(Long insertedById) {
        this.insertedById = insertedById;
    }

    public ZonedDateTime getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(ZonedDateTime insertedDate) {
        this.insertedDate = insertedDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
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

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBillingProviderAddressLine2() {
        return billingProviderAddressLine2;
    }

    public void setBillingProviderAddressLine2(String billingProviderAddressLine2) {
        this.billingProviderAddressLine2 = billingProviderAddressLine2;
    }

    public String getPayerClaimControlNumber() {
        return payerClaimControlNumber;
    }

    public void setPayerClaimControlNumber(String payerClaimControlNumber) {
        this.payerClaimControlNumber = payerClaimControlNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrimaryClaimsReSubmissionMasterDTO)) {
            return false;
        }

        PrimaryClaimsReSubmissionMasterDTO primaryClaimsReSubmissionMasterDTO = (PrimaryClaimsReSubmissionMasterDTO) o;
        if (this.changeHealthPrimaryResubmisionMasterId == null) {
            return false;
        }
        return Objects.equals(
            this.changeHealthPrimaryResubmisionMasterId,
            primaryClaimsReSubmissionMasterDTO.changeHealthPrimaryResubmisionMasterId
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.changeHealthPrimaryResubmisionMasterId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrimaryClaimsReSubmissionMasterDTO{" +
            "changeHealthPrimaryResubmisionMasterId=" + getChangeHealthPrimaryResubmisionMasterId() +
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
            ", payerClaimControlNumber='" + getPayerClaimControlNumber() + "'" +
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
