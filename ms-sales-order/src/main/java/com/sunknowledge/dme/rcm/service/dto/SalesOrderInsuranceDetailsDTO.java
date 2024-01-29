package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalesOrderInsuranceDetailsDTO implements Serializable {

    private Long salesOrderInsuranceDetailsId;

    private Long salesOrderId;

    private Long patientId;

    private Long primaryInsurerId;

    private String primaryInsurerName;

    private String primaryInsurerPolicyNo;

    private String primaryInsurerPatientIdNumber;

    private LocalDate primaryInsurerEffectiveDate;

    private String primaryInsurerVerificationStatus;

    private LocalDate primaryInsurerVerificationDate;

    private Long primaryInsurerPayPercentage;

    private String primaryBox10D;

    private String primaryBox19;

    private String primaryBox24Ia;

    private String primaryBox24Ja;

    private String primaryBox24Jb;

    private String primaryIncludeBox24Jbstatus;

    private String primaryIncludePayerSalesOrderStatus;

    private String primaryWaitForPreviousPayerBeforeBillingStatus;

    private String primaryPayPercentageStatus;

    private Long secondaryInsurerId;

    private String secondaryInsurerName;

    private String secondaryInsurerPolicyNo;

    private String secondaryInsurerPatientIdNumber;

    private LocalDate secondaryInsurerEffectiveDate;

    private String secondaryInsurerVerificationStatus;

    private LocalDate secondaryInsurerVerificationDate;

    private Long secondaryInsurerPayPercentage;

    private String secondaryBox10D;

    private String secondaryBox19;

    private String secondaryBox24Ia;

    private String secondaryBox24Ja;

    private String secondaryBox24Jb;

    private String secondaryIncludeBox24JbStatus;

    private String secondaryIncludePayerSalesOrderStatus;

    private String secondaryWaitPreviousPayerBefrBillingStatus;

    private String secondaryPayPercentageStatus;

    private Long tertiaryInsurerId;

    private String tertiaryInsurerName;

    private String tertiaryInsurerPolicyno;

    private String tertiaryInsurerPatientIdNumber;

    private LocalDate tertiaryInsurerEffectiveDate;

    private String tertiaryInsurerVerificationStatus;

    private LocalDate tertiaryInsurerVerificationDate;

    private Long tertiaryInsurerPayPercentage;

    private String tertiaryBox10D;

    private String tertiaryBox19;

    private String tertiaryBox24Ia;

    private String tertiaryBox24Ja;

    private String tertiaryBox24Jb;

    private String tertiaryIncludeBox24JbStatus;

    private String tertiaryIncludePayerInSalesOrderStatus;

    private String tertiaryWaitPreviousPayerBeforeBillingStatus;

    private String tertiaryPayPercentageStatus;

    private String insuranceVerificationStatus;

    private String coverageVerificationStatus;

    private String excludeFromEligibilityCheckStatus;

    private Long patientPayPercentage;

    private String patientIncludeThisPayorInSalesOrderStatus;

    private String patientWaitForPreviousPayerBeforeBillingStatus;

    private LocalDate workersCompDateOfOnset;

    private String workersCompInjuryRelatedEmploymentStatus;

    private String workersCompInjuryRelatedAutoAccidentStatus;

    private String workersCompAutoAccidentStateCode;

    private String workersCompInjuryRelatedToOtherAccidentStatus;

    private String eclaimsAttachmentStatus;

    private Long attachmentNumber;

    private String typeCode;

    private String transactionCode;

    private String claimsNoteType;

    private String claimsNote;

    private Long createdById;

    private LocalDate createdDate;

    private String status;

    private Long updatedById;

    private LocalDate updatedDate;

    private String salesOrderNo;

    private String createdByName;

    private String updatedByName;

    private Long primaryInsuranceGroupId;

    private String primaryInsuranceGroupName;

    private Long secondaryInsuranceGroupId;

    private String secondaryInsuranceGroupName;

    private Long tertiaryInsuranceGroupId;

    private String tertiaryInsuranceGroupName;

    private Long primaryInsurancePlanId;

    private String primaryInsurancePlanType;

    private Long secondaryInsurancePlanId;

    private String secondaryInsurancePlanType;

    private Long tertiaryInsurancePlanId;

    private String tertiaryInsurancePlanType;

    private UUID salesOrderInsuranceDetailsUuid;

    private String primaryInsurancePayerId;

    private String secondaryInsurancePayerId;

    private String tertiaryInsurancePayerId;

    private String primaryInsuranceIndicatorCode;

    private String secondaryInsuranceIndicatorCode;

    private String tertiaryInsuranceIndicatorCode;

    private Long priceTableId;

    private String priceTableName;

    private String primaryInsurerAddressLine1;

    private String primaryInsurerAddressLine2;

    private String primaryInsurerCity;

    private String primaryInsurerState;

    private String primaryInsurerZip;

    private String primaryInsurerContact1;

    private String primaryInsurerFax;

    private String secondaryInsurerAddressLine1;

    private String secondaryInsurerAddressLine2;

    private String secondaryInsurerCity;

    private String secondaryInsurerState;

    private String secondaryInsurerZip;

    private String secondaryInsurerContact1;

    private String secondaryInsurerFax;

    private String tertiaryInsurerAddressLine1;

    private String tertiaryInsurerAddressLine2;

    private String tertiaryInsurerCity;

    private String tertiaryInsurerState;

    private String tertiaryInsurerZip;

    private String tertiaryInsurerContact1;

    private String tertiaryInsurerFax;

    private LocalDate primaryInsurerPolicyEndDate;

    private String primaryInsurerContactName;

    private String primaryClaimProgram;

    private String secondaryClaimProgram;

    private String tertiaryClaimProgram;

    private String workersCompInsuredEmployer;

    private String workersCompPayerIdNumber;

    private String workersCompPlanName;

    private String workersCompAdditionalDtls;

    private String workersCompClaimFillingCode;

    private String workersCompTplCode;

    private String workersCompTplName;

    private String workersCompPropertyCasualtyAgencyClaimNo;

    private String workersCompCarrierId;

    private String workersCompEmployerAddressLine1;

    private String workersCompEmployerAddressLine2;

    private String workersCompEmployerCity;

    private String workersCompEmployerState;

    private String workersCompEmployerCountry;

    private String workersCompEmployerZip;

    private String workersCompEmployerContactNo1;

    private String workersCompEmployerContactNo2;

    private String workersCompEmployerFax;

    private String workersCompEmployerEfax;

    private String workersCompEmployerEmail;

    private String workersCompRelationship;

    private String workersCompModeOfContact;

    public Long getSalesOrderInsuranceDetailsId() {
        return salesOrderInsuranceDetailsId;
    }

    public void setSalesOrderInsuranceDetailsId(Long salesOrderInsuranceDetailsId) {
        this.salesOrderInsuranceDetailsId = salesOrderInsuranceDetailsId;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPrimaryInsurerId() {
        return primaryInsurerId;
    }

    public void setPrimaryInsurerId(Long primaryInsurerId) {
        this.primaryInsurerId = primaryInsurerId;
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

    public LocalDate getPrimaryInsurerEffectiveDate() {
        return primaryInsurerEffectiveDate;
    }

    public void setPrimaryInsurerEffectiveDate(LocalDate primaryInsurerEffectiveDate) {
        this.primaryInsurerEffectiveDate = primaryInsurerEffectiveDate;
    }

    public String getPrimaryInsurerVerificationStatus() {
        return primaryInsurerVerificationStatus;
    }

    public void setPrimaryInsurerVerificationStatus(String primaryInsurerVerificationStatus) {
        this.primaryInsurerVerificationStatus = primaryInsurerVerificationStatus;
    }

    public LocalDate getPrimaryInsurerVerificationDate() {
        return primaryInsurerVerificationDate;
    }

    public void setPrimaryInsurerVerificationDate(LocalDate primaryInsurerVerificationDate) {
        this.primaryInsurerVerificationDate = primaryInsurerVerificationDate;
    }

    public Long getPrimaryInsurerPayPercentage() {
        return primaryInsurerPayPercentage;
    }

    public void setPrimaryInsurerPayPercentage(Long primaryInsurerPayPercentage) {
        this.primaryInsurerPayPercentage = primaryInsurerPayPercentage;
    }

    public String getPrimaryBox10D() {
        return primaryBox10D;
    }

    public void setPrimaryBox10D(String primaryBox10D) {
        this.primaryBox10D = primaryBox10D;
    }

    public String getPrimaryBox19() {
        return primaryBox19;
    }

    public void setPrimaryBox19(String primaryBox19) {
        this.primaryBox19 = primaryBox19;
    }

    public String getPrimaryBox24Ia() {
        return primaryBox24Ia;
    }

    public void setPrimaryBox24Ia(String primaryBox24Ia) {
        this.primaryBox24Ia = primaryBox24Ia;
    }

    public String getPrimaryBox24Ja() {
        return primaryBox24Ja;
    }

    public void setPrimaryBox24Ja(String primaryBox24Ja) {
        this.primaryBox24Ja = primaryBox24Ja;
    }

    public String getPrimaryBox24Jb() {
        return primaryBox24Jb;
    }

    public void setPrimaryBox24Jb(String primaryBox24Jb) {
        this.primaryBox24Jb = primaryBox24Jb;
    }

    public String getPrimaryIncludeBox24Jbstatus() {
        return primaryIncludeBox24Jbstatus;
    }

    public void setPrimaryIncludeBox24Jbstatus(String primaryIncludeBox24Jbstatus) {
        this.primaryIncludeBox24Jbstatus = primaryIncludeBox24Jbstatus;
    }

    public String getPrimaryIncludePayerSalesOrderStatus() {
        return primaryIncludePayerSalesOrderStatus;
    }

    public void setPrimaryIncludePayerSalesOrderStatus(String primaryIncludePayerSalesOrderStatus) {
        this.primaryIncludePayerSalesOrderStatus = primaryIncludePayerSalesOrderStatus;
    }

    public String getPrimaryWaitForPreviousPayerBeforeBillingStatus() {
        return primaryWaitForPreviousPayerBeforeBillingStatus;
    }

    public void setPrimaryWaitForPreviousPayerBeforeBillingStatus(String primaryWaitForPreviousPayerBeforeBillingStatus) {
        this.primaryWaitForPreviousPayerBeforeBillingStatus = primaryWaitForPreviousPayerBeforeBillingStatus;
    }

    public String getPrimaryPayPercentageStatus() {
        return primaryPayPercentageStatus;
    }

    public void setPrimaryPayPercentageStatus(String primaryPayPercentageStatus) {
        this.primaryPayPercentageStatus = primaryPayPercentageStatus;
    }

    public Long getSecondaryInsurerId() {
        return secondaryInsurerId;
    }

    public void setSecondaryInsurerId(Long secondaryInsurerId) {
        this.secondaryInsurerId = secondaryInsurerId;
    }

    public String getSecondaryInsurerName() {
        return secondaryInsurerName;
    }

    public void setSecondaryInsurerName(String secondaryInsurerName) {
        this.secondaryInsurerName = secondaryInsurerName;
    }

    public String getSecondaryInsurerPolicyNo() {
        return secondaryInsurerPolicyNo;
    }

    public void setSecondaryInsurerPolicyNo(String secondaryInsurerPolicyNo) {
        this.secondaryInsurerPolicyNo = secondaryInsurerPolicyNo;
    }

    public String getSecondaryInsurerPatientIdNumber() {
        return secondaryInsurerPatientIdNumber;
    }

    public void setSecondaryInsurerPatientIdNumber(String secondaryInsurerPatientIdNumber) {
        this.secondaryInsurerPatientIdNumber = secondaryInsurerPatientIdNumber;
    }

    public LocalDate getSecondaryInsurerEffectiveDate() {
        return secondaryInsurerEffectiveDate;
    }

    public void setSecondaryInsurerEffectiveDate(LocalDate secondaryInsurerEffectiveDate) {
        this.secondaryInsurerEffectiveDate = secondaryInsurerEffectiveDate;
    }

    public String getSecondaryInsurerVerificationStatus() {
        return secondaryInsurerVerificationStatus;
    }

    public void setSecondaryInsurerVerificationStatus(String secondaryInsurerVerificationStatus) {
        this.secondaryInsurerVerificationStatus = secondaryInsurerVerificationStatus;
    }

    public LocalDate getSecondaryInsurerVerificationDate() {
        return secondaryInsurerVerificationDate;
    }

    public void setSecondaryInsurerVerificationDate(LocalDate secondaryInsurerVerificationDate) {
        this.secondaryInsurerVerificationDate = secondaryInsurerVerificationDate;
    }

    public Long getSecondaryInsurerPayPercentage() {
        return secondaryInsurerPayPercentage;
    }

    public void setSecondaryInsurerPayPercentage(Long secondaryInsurerPayPercentage) {
        this.secondaryInsurerPayPercentage = secondaryInsurerPayPercentage;
    }

    public String getSecondaryBox10D() {
        return secondaryBox10D;
    }

    public void setSecondaryBox10D(String secondaryBox10D) {
        this.secondaryBox10D = secondaryBox10D;
    }

    public String getSecondaryBox19() {
        return secondaryBox19;
    }

    public void setSecondaryBox19(String secondaryBox19) {
        this.secondaryBox19 = secondaryBox19;
    }

    public String getSecondaryBox24Ia() {
        return secondaryBox24Ia;
    }

    public void setSecondaryBox24Ia(String secondaryBox24Ia) {
        this.secondaryBox24Ia = secondaryBox24Ia;
    }

    public String getSecondaryBox24Ja() {
        return secondaryBox24Ja;
    }

    public void setSecondaryBox24Ja(String secondaryBox24Ja) {
        this.secondaryBox24Ja = secondaryBox24Ja;
    }

    public String getSecondaryBox24Jb() {
        return secondaryBox24Jb;
    }

    public void setSecondaryBox24Jb(String secondaryBox24Jb) {
        this.secondaryBox24Jb = secondaryBox24Jb;
    }

    public String getSecondaryIncludeBox24JbStatus() {
        return secondaryIncludeBox24JbStatus;
    }

    public void setSecondaryIncludeBox24JbStatus(String secondaryIncludeBox24JbStatus) {
        this.secondaryIncludeBox24JbStatus = secondaryIncludeBox24JbStatus;
    }

    public String getSecondaryIncludePayerSalesOrderStatus() {
        return secondaryIncludePayerSalesOrderStatus;
    }

    public void setSecondaryIncludePayerSalesOrderStatus(String secondaryIncludePayerSalesOrderStatus) {
        this.secondaryIncludePayerSalesOrderStatus = secondaryIncludePayerSalesOrderStatus;
    }

    public String getSecondaryWaitPreviousPayerBefrBillingStatus() {
        return secondaryWaitPreviousPayerBefrBillingStatus;
    }

    public void setSecondaryWaitPreviousPayerBefrBillingStatus(String secondaryWaitPreviousPayerBefrBillingStatus) {
        this.secondaryWaitPreviousPayerBefrBillingStatus = secondaryWaitPreviousPayerBefrBillingStatus;
    }

    public String getSecondaryPayPercentageStatus() {
        return secondaryPayPercentageStatus;
    }

    public void setSecondaryPayPercentageStatus(String secondaryPayPercentageStatus) {
        this.secondaryPayPercentageStatus = secondaryPayPercentageStatus;
    }

    public Long getTertiaryInsurerId() {
        return tertiaryInsurerId;
    }

    public void setTertiaryInsurerId(Long tertiaryInsurerId) {
        this.tertiaryInsurerId = tertiaryInsurerId;
    }

    public String getTertiaryInsurerName() {
        return tertiaryInsurerName;
    }

    public void setTertiaryInsurerName(String tertiaryInsurerName) {
        this.tertiaryInsurerName = tertiaryInsurerName;
    }

    public String getTertiaryInsurerPolicyno() {
        return tertiaryInsurerPolicyno;
    }

    public void setTertiaryInsurerPolicyno(String tertiaryInsurerPolicyno) {
        this.tertiaryInsurerPolicyno = tertiaryInsurerPolicyno;
    }

    public String getTertiaryInsurerPatientIdNumber() {
        return tertiaryInsurerPatientIdNumber;
    }

    public void setTertiaryInsurerPatientIdNumber(String tertiaryInsurerPatientIdNumber) {
        this.tertiaryInsurerPatientIdNumber = tertiaryInsurerPatientIdNumber;
    }

    public LocalDate getTertiaryInsurerEffectiveDate() {
        return tertiaryInsurerEffectiveDate;
    }

    public void setTertiaryInsurerEffectiveDate(LocalDate tertiaryInsurerEffectiveDate) {
        this.tertiaryInsurerEffectiveDate = tertiaryInsurerEffectiveDate;
    }

    public String getTertiaryInsurerVerificationStatus() {
        return tertiaryInsurerVerificationStatus;
    }

    public void setTertiaryInsurerVerificationStatus(String tertiaryInsurerVerificationStatus) {
        this.tertiaryInsurerVerificationStatus = tertiaryInsurerVerificationStatus;
    }

    public LocalDate getTertiaryInsurerVerificationDate() {
        return tertiaryInsurerVerificationDate;
    }

    public void setTertiaryInsurerVerificationDate(LocalDate tertiaryInsurerVerificationDate) {
        this.tertiaryInsurerVerificationDate = tertiaryInsurerVerificationDate;
    }

    public Long getTertiaryInsurerPayPercentage() {
        return tertiaryInsurerPayPercentage;
    }

    public void setTertiaryInsurerPayPercentage(Long tertiaryInsurerPayPercentage) {
        this.tertiaryInsurerPayPercentage = tertiaryInsurerPayPercentage;
    }

    public String getTertiaryBox10D() {
        return tertiaryBox10D;
    }

    public void setTertiaryBox10D(String tertiaryBox10D) {
        this.tertiaryBox10D = tertiaryBox10D;
    }

    public String getTertiaryBox19() {
        return tertiaryBox19;
    }

    public void setTertiaryBox19(String tertiaryBox19) {
        this.tertiaryBox19 = tertiaryBox19;
    }

    public String getTertiaryBox24Ia() {
        return tertiaryBox24Ia;
    }

    public void setTertiaryBox24Ia(String tertiaryBox24Ia) {
        this.tertiaryBox24Ia = tertiaryBox24Ia;
    }

    public String getTertiaryBox24Ja() {
        return tertiaryBox24Ja;
    }

    public void setTertiaryBox24Ja(String tertiaryBox24Ja) {
        this.tertiaryBox24Ja = tertiaryBox24Ja;
    }

    public String getTertiaryBox24Jb() {
        return tertiaryBox24Jb;
    }

    public void setTertiaryBox24Jb(String tertiaryBox24Jb) {
        this.tertiaryBox24Jb = tertiaryBox24Jb;
    }

    public String getTertiaryIncludeBox24JbStatus() {
        return tertiaryIncludeBox24JbStatus;
    }

    public void setTertiaryIncludeBox24JbStatus(String tertiaryIncludeBox24JbStatus) {
        this.tertiaryIncludeBox24JbStatus = tertiaryIncludeBox24JbStatus;
    }

    public String getTertiaryIncludePayerInSalesOrderStatus() {
        return tertiaryIncludePayerInSalesOrderStatus;
    }

    public void setTertiaryIncludePayerInSalesOrderStatus(String tertiaryIncludePayerInSalesOrderStatus) {
        this.tertiaryIncludePayerInSalesOrderStatus = tertiaryIncludePayerInSalesOrderStatus;
    }

    public String getTertiaryWaitPreviousPayerBeforeBillingStatus() {
        return tertiaryWaitPreviousPayerBeforeBillingStatus;
    }

    public void setTertiaryWaitPreviousPayerBeforeBillingStatus(String tertiaryWaitPreviousPayerBeforeBillingStatus) {
        this.tertiaryWaitPreviousPayerBeforeBillingStatus = tertiaryWaitPreviousPayerBeforeBillingStatus;
    }

    public String getTertiaryPayPercentageStatus() {
        return tertiaryPayPercentageStatus;
    }

    public void setTertiaryPayPercentageStatus(String tertiaryPayPercentageStatus) {
        this.tertiaryPayPercentageStatus = tertiaryPayPercentageStatus;
    }

    public String getInsuranceVerificationStatus() {
        return insuranceVerificationStatus;
    }

    public void setInsuranceVerificationStatus(String insuranceVerificationStatus) {
        this.insuranceVerificationStatus = insuranceVerificationStatus;
    }

    public String getCoverageVerificationStatus() {
        return coverageVerificationStatus;
    }

    public void setCoverageVerificationStatus(String coverageVerificationStatus) {
        this.coverageVerificationStatus = coverageVerificationStatus;
    }

    public String getExcludeFromEligibilityCheckStatus() {
        return excludeFromEligibilityCheckStatus;
    }

    public void setExcludeFromEligibilityCheckStatus(String excludeFromEligibilityCheckStatus) {
        this.excludeFromEligibilityCheckStatus = excludeFromEligibilityCheckStatus;
    }

    public Long getPatientPayPercentage() {
        return patientPayPercentage;
    }

    public void setPatientPayPercentage(Long patientPayPercentage) {
        this.patientPayPercentage = patientPayPercentage;
    }

    public String getPatientIncludeThisPayorInSalesOrderStatus() {
        return patientIncludeThisPayorInSalesOrderStatus;
    }

    public void setPatientIncludeThisPayorInSalesOrderStatus(String patientIncludeThisPayorInSalesOrderStatus) {
        this.patientIncludeThisPayorInSalesOrderStatus = patientIncludeThisPayorInSalesOrderStatus;
    }

    public String getPatientWaitForPreviousPayerBeforeBillingStatus() {
        return patientWaitForPreviousPayerBeforeBillingStatus;
    }

    public void setPatientWaitForPreviousPayerBeforeBillingStatus(String patientWaitForPreviousPayerBeforeBillingStatus) {
        this.patientWaitForPreviousPayerBeforeBillingStatus = patientWaitForPreviousPayerBeforeBillingStatus;
    }

    public LocalDate getWorkersCompDateOfOnset() {
        return workersCompDateOfOnset;
    }

    public void setWorkersCompDateOfOnset(LocalDate workersCompDateOfOnset) {
        this.workersCompDateOfOnset = workersCompDateOfOnset;
    }

    public String getWorkersCompInjuryRelatedEmploymentStatus() {
        return workersCompInjuryRelatedEmploymentStatus;
    }

    public void setWorkersCompInjuryRelatedEmploymentStatus(String workersCompInjuryRelatedEmploymentStatus) {
        this.workersCompInjuryRelatedEmploymentStatus = workersCompInjuryRelatedEmploymentStatus;
    }

    public String getWorkersCompInjuryRelatedAutoAccidentStatus() {
        return workersCompInjuryRelatedAutoAccidentStatus;
    }

    public void setWorkersCompInjuryRelatedAutoAccidentStatus(String workersCompInjuryRelatedAutoAccidentStatus) {
        this.workersCompInjuryRelatedAutoAccidentStatus = workersCompInjuryRelatedAutoAccidentStatus;
    }

    public String getWorkersCompAutoAccidentStateCode() {
        return workersCompAutoAccidentStateCode;
    }

    public void setWorkersCompAutoAccidentStateCode(String workersCompAutoAccidentStateCode) {
        this.workersCompAutoAccidentStateCode = workersCompAutoAccidentStateCode;
    }

    public String getWorkersCompInjuryRelatedToOtherAccidentStatus() {
        return workersCompInjuryRelatedToOtherAccidentStatus;
    }

    public void setWorkersCompInjuryRelatedToOtherAccidentStatus(String workersCompInjuryRelatedToOtherAccidentStatus) {
        this.workersCompInjuryRelatedToOtherAccidentStatus = workersCompInjuryRelatedToOtherAccidentStatus;
    }

    public String getEclaimsAttachmentStatus() {
        return eclaimsAttachmentStatus;
    }

    public void setEclaimsAttachmentStatus(String eclaimsAttachmentStatus) {
        this.eclaimsAttachmentStatus = eclaimsAttachmentStatus;
    }

    public Long getAttachmentNumber() {
        return attachmentNumber;
    }

    public void setAttachmentNumber(Long attachmentNumber) {
        this.attachmentNumber = attachmentNumber;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getClaimsNoteType() {
        return claimsNoteType;
    }

    public void setClaimsNoteType(String claimsNoteType) {
        this.claimsNoteType = claimsNoteType;
    }

    public String getClaimsNote() {
        return claimsNote;
    }

    public void setClaimsNote(String claimsNote) {
        this.claimsNote = claimsNote;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
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

    public Long getPrimaryInsuranceGroupId() {
        return primaryInsuranceGroupId;
    }

    public void setPrimaryInsuranceGroupId(Long primaryInsuranceGroupId) {
        this.primaryInsuranceGroupId = primaryInsuranceGroupId;
    }

    public String getPrimaryInsuranceGroupName() {
        return primaryInsuranceGroupName;
    }

    public void setPrimaryInsuranceGroupName(String primaryInsuranceGroupName) {
        this.primaryInsuranceGroupName = primaryInsuranceGroupName;
    }

    public Long getSecondaryInsuranceGroupId() {
        return secondaryInsuranceGroupId;
    }

    public void setSecondaryInsuranceGroupId(Long secondaryInsuranceGroupId) {
        this.secondaryInsuranceGroupId = secondaryInsuranceGroupId;
    }

    public String getSecondaryInsuranceGroupName() {
        return secondaryInsuranceGroupName;
    }

    public void setSecondaryInsuranceGroupName(String secondaryInsuranceGroupName) {
        this.secondaryInsuranceGroupName = secondaryInsuranceGroupName;
    }

    public Long getTertiaryInsuranceGroupId() {
        return tertiaryInsuranceGroupId;
    }

    public void setTertiaryInsuranceGroupId(Long tertiaryInsuranceGroupId) {
        this.tertiaryInsuranceGroupId = tertiaryInsuranceGroupId;
    }

    public String getTertiaryInsuranceGroupName() {
        return tertiaryInsuranceGroupName;
    }

    public void setTertiaryInsuranceGroupName(String tertiaryInsuranceGroupName) {
        this.tertiaryInsuranceGroupName = tertiaryInsuranceGroupName;
    }

    public Long getPrimaryInsurancePlanId() {
        return primaryInsurancePlanId;
    }

    public void setPrimaryInsurancePlanId(Long primaryInsurancePlanId) {
        this.primaryInsurancePlanId = primaryInsurancePlanId;
    }

    public String getPrimaryInsurancePlanType() {
        return primaryInsurancePlanType;
    }

    public void setPrimaryInsurancePlanType(String primaryInsurancePlanType) {
        this.primaryInsurancePlanType = primaryInsurancePlanType;
    }

    public Long getSecondaryInsurancePlanId() {
        return secondaryInsurancePlanId;
    }

    public void setSecondaryInsurancePlanId(Long secondaryInsurancePlanId) {
        this.secondaryInsurancePlanId = secondaryInsurancePlanId;
    }

    public String getSecondaryInsurancePlanType() {
        return secondaryInsurancePlanType;
    }

    public void setSecondaryInsurancePlanType(String secondaryInsurancePlanType) {
        this.secondaryInsurancePlanType = secondaryInsurancePlanType;
    }

    public Long getTertiaryInsurancePlanId() {
        return tertiaryInsurancePlanId;
    }

    public void setTertiaryInsurancePlanId(Long tertiaryInsurancePlanId) {
        this.tertiaryInsurancePlanId = tertiaryInsurancePlanId;
    }

    public String getTertiaryInsurancePlanType() {
        return tertiaryInsurancePlanType;
    }

    public void setTertiaryInsurancePlanType(String tertiaryInsurancePlanType) {
        this.tertiaryInsurancePlanType = tertiaryInsurancePlanType;
    }

    public UUID getSalesOrderInsuranceDetailsUuid() {
        return salesOrderInsuranceDetailsUuid;
    }

    public void setSalesOrderInsuranceDetailsUuid(UUID salesOrderInsuranceDetailsUuid) {
        this.salesOrderInsuranceDetailsUuid = salesOrderInsuranceDetailsUuid;
    }

    public String getPrimaryInsurancePayerId() {
        return primaryInsurancePayerId;
    }

    public void setPrimaryInsurancePayerId(String primaryInsurancePayerId) {
        this.primaryInsurancePayerId = primaryInsurancePayerId;
    }

    public String getSecondaryInsurancePayerId() {
        return secondaryInsurancePayerId;
    }

    public void setSecondaryInsurancePayerId(String secondaryInsurancePayerId) {
        this.secondaryInsurancePayerId = secondaryInsurancePayerId;
    }

    public String getTertiaryInsurancePayerId() {
        return tertiaryInsurancePayerId;
    }

    public void setTertiaryInsurancePayerId(String tertiaryInsurancePayerId) {
        this.tertiaryInsurancePayerId = tertiaryInsurancePayerId;
    }

    public String getPrimaryInsuranceIndicatorCode() {
        return primaryInsuranceIndicatorCode;
    }

    public void setPrimaryInsuranceIndicatorCode(String primaryInsuranceIndicatorCode) {
        this.primaryInsuranceIndicatorCode = primaryInsuranceIndicatorCode;
    }

    public String getSecondaryInsuranceIndicatorCode() {
        return secondaryInsuranceIndicatorCode;
    }

    public void setSecondaryInsuranceIndicatorCode(String secondaryInsuranceIndicatorCode) {
        this.secondaryInsuranceIndicatorCode = secondaryInsuranceIndicatorCode;
    }

    public String getTertiaryInsuranceIndicatorCode() {
        return tertiaryInsuranceIndicatorCode;
    }

    public void setTertiaryInsuranceIndicatorCode(String tertiaryInsuranceIndicatorCode) {
        this.tertiaryInsuranceIndicatorCode = tertiaryInsuranceIndicatorCode;
    }

    public Long getPriceTableId() {
        return priceTableId;
    }

    public void setPriceTableId(Long priceTableId) {
        this.priceTableId = priceTableId;
    }

    public String getPriceTableName() {
        return priceTableName;
    }

    public void setPriceTableName(String priceTableName) {
        this.priceTableName = priceTableName;
    }

    public String getPrimaryInsurerAddressLine1() {
        return primaryInsurerAddressLine1;
    }

    public void setPrimaryInsurerAddressLine1(String primaryInsurerAddressLine1) {
        this.primaryInsurerAddressLine1 = primaryInsurerAddressLine1;
    }

    public String getPrimaryInsurerAddressLine2() {
        return primaryInsurerAddressLine2;
    }

    public void setPrimaryInsurerAddressLine2(String primaryInsurerAddressLine2) {
        this.primaryInsurerAddressLine2 = primaryInsurerAddressLine2;
    }

    public String getPrimaryInsurerCity() {
        return primaryInsurerCity;
    }

    public void setPrimaryInsurerCity(String primaryInsurerCity) {
        this.primaryInsurerCity = primaryInsurerCity;
    }

    public String getPrimaryInsurerState() {
        return primaryInsurerState;
    }

    public void setPrimaryInsurerState(String primaryInsurerState) {
        this.primaryInsurerState = primaryInsurerState;
    }

    public String getPrimaryInsurerZip() {
        return primaryInsurerZip;
    }

    public void setPrimaryInsurerZip(String primaryInsurerZip) {
        this.primaryInsurerZip = primaryInsurerZip;
    }

    public String getPrimaryInsurerContact1() {
        return primaryInsurerContact1;
    }

    public void setPrimaryInsurerContact1(String primaryInsurerContact1) {
        this.primaryInsurerContact1 = primaryInsurerContact1;
    }

    public String getPrimaryInsurerFax() {
        return primaryInsurerFax;
    }

    public void setPrimaryInsurerFax(String primaryInsurerFax) {
        this.primaryInsurerFax = primaryInsurerFax;
    }

    public String getSecondaryInsurerAddressLine1() {
        return secondaryInsurerAddressLine1;
    }

    public void setSecondaryInsurerAddressLine1(String secondaryInsurerAddressLine1) {
        this.secondaryInsurerAddressLine1 = secondaryInsurerAddressLine1;
    }

    public String getSecondaryInsurerAddressLine2() {
        return secondaryInsurerAddressLine2;
    }

    public void setSecondaryInsurerAddressLine2(String secondaryInsurerAddressLine2) {
        this.secondaryInsurerAddressLine2 = secondaryInsurerAddressLine2;
    }

    public String getSecondaryInsurerCity() {
        return secondaryInsurerCity;
    }

    public void setSecondaryInsurerCity(String secondaryInsurerCity) {
        this.secondaryInsurerCity = secondaryInsurerCity;
    }

    public String getSecondaryInsurerState() {
        return secondaryInsurerState;
    }

    public void setSecondaryInsurerState(String secondaryInsurerState) {
        this.secondaryInsurerState = secondaryInsurerState;
    }

    public String getSecondaryInsurerZip() {
        return secondaryInsurerZip;
    }

    public void setSecondaryInsurerZip(String secondaryInsurerZip) {
        this.secondaryInsurerZip = secondaryInsurerZip;
    }

    public String getSecondaryInsurerContact1() {
        return secondaryInsurerContact1;
    }

    public void setSecondaryInsurerContact1(String secondaryInsurerContact1) {
        this.secondaryInsurerContact1 = secondaryInsurerContact1;
    }

    public String getSecondaryInsurerFax() {
        return secondaryInsurerFax;
    }

    public void setSecondaryInsurerFax(String secondaryInsurerFax) {
        this.secondaryInsurerFax = secondaryInsurerFax;
    }

    public String getTertiaryInsurerAddressLine1() {
        return tertiaryInsurerAddressLine1;
    }

    public void setTertiaryInsurerAddressLine1(String tertiaryInsurerAddressLine1) {
        this.tertiaryInsurerAddressLine1 = tertiaryInsurerAddressLine1;
    }

    public String getTertiaryInsurerAddressLine2() {
        return tertiaryInsurerAddressLine2;
    }

    public void setTertiaryInsurerAddressLine2(String tertiaryInsurerAddressLine2) {
        this.tertiaryInsurerAddressLine2 = tertiaryInsurerAddressLine2;
    }

    public String getTertiaryInsurerCity() {
        return tertiaryInsurerCity;
    }

    public void setTertiaryInsurerCity(String tertiaryInsurerCity) {
        this.tertiaryInsurerCity = tertiaryInsurerCity;
    }

    public String getTertiaryInsurerState() {
        return tertiaryInsurerState;
    }

    public void setTertiaryInsurerState(String tertiaryInsurerState) {
        this.tertiaryInsurerState = tertiaryInsurerState;
    }

    public String getTertiaryInsurerZip() {
        return tertiaryInsurerZip;
    }

    public void setTertiaryInsurerZip(String tertiaryInsurerZip) {
        this.tertiaryInsurerZip = tertiaryInsurerZip;
    }

    public String getTertiaryInsurerContact1() {
        return tertiaryInsurerContact1;
    }

    public void setTertiaryInsurerContact1(String tertiaryInsurerContact1) {
        this.tertiaryInsurerContact1 = tertiaryInsurerContact1;
    }

    public String getTertiaryInsurerFax() {
        return tertiaryInsurerFax;
    }

    public void setTertiaryInsurerFax(String tertiaryInsurerFax) {
        this.tertiaryInsurerFax = tertiaryInsurerFax;
    }

    public LocalDate getPrimaryInsurerPolicyEndDate() {
        return primaryInsurerPolicyEndDate;
    }

    public void setPrimaryInsurerPolicyEndDate(LocalDate primaryInsurerPolicyEndDate) {
        this.primaryInsurerPolicyEndDate = primaryInsurerPolicyEndDate;
    }

    public String getPrimaryInsurerContactName() {
        return primaryInsurerContactName;
    }

    public void setPrimaryInsurerContactName(String primaryInsurerContactName) {
        this.primaryInsurerContactName = primaryInsurerContactName;
    }

    public String getPrimaryClaimProgram() {
        return primaryClaimProgram;
    }

    public void setPrimaryClaimProgram(String primaryClaimProgram) {
        this.primaryClaimProgram = primaryClaimProgram;
    }

    public String getSecondaryClaimProgram() {
        return secondaryClaimProgram;
    }

    public void setSecondaryClaimProgram(String secondaryClaimProgram) {
        this.secondaryClaimProgram = secondaryClaimProgram;
    }

    public String getTertiaryClaimProgram() {
        return tertiaryClaimProgram;
    }

    public void setTertiaryClaimProgram(String tertiaryClaimProgram) {
        this.tertiaryClaimProgram = tertiaryClaimProgram;
    }

    public String getWorkersCompInsuredEmployer() {
        return workersCompInsuredEmployer;
    }

    public void setWorkersCompInsuredEmployer(String workersCompInsuredEmployer) {
        this.workersCompInsuredEmployer = workersCompInsuredEmployer;
    }

    public String getWorkersCompPayerIdNumber() {
        return workersCompPayerIdNumber;
    }

    public void setWorkersCompPayerIdNumber(String workersCompPayerIdNumber) {
        this.workersCompPayerIdNumber = workersCompPayerIdNumber;
    }

    public String getWorkersCompPlanName() {
        return workersCompPlanName;
    }

    public void setWorkersCompPlanName(String workersCompPlanName) {
        this.workersCompPlanName = workersCompPlanName;
    }

    public String getWorkersCompAdditionalDtls() {
        return workersCompAdditionalDtls;
    }

    public void setWorkersCompAdditionalDtls(String workersCompAdditionalDtls) {
        this.workersCompAdditionalDtls = workersCompAdditionalDtls;
    }

    public String getWorkersCompClaimFillingCode() {
        return workersCompClaimFillingCode;
    }

    public void setWorkersCompClaimFillingCode(String workersCompClaimFillingCode) {
        this.workersCompClaimFillingCode = workersCompClaimFillingCode;
    }

    public String getWorkersCompTplCode() {
        return workersCompTplCode;
    }

    public void setWorkersCompTplCode(String workersCompTplCode) {
        this.workersCompTplCode = workersCompTplCode;
    }

    public String getWorkersCompTplName() {
        return workersCompTplName;
    }

    public void setWorkersCompTplName(String workersCompTplName) {
        this.workersCompTplName = workersCompTplName;
    }

    public String getWorkersCompPropertyCasualtyAgencyClaimNo() {
        return workersCompPropertyCasualtyAgencyClaimNo;
    }

    public void setWorkersCompPropertyCasualtyAgencyClaimNo(String workersCompPropertyCasualtyAgencyClaimNo) {
        this.workersCompPropertyCasualtyAgencyClaimNo = workersCompPropertyCasualtyAgencyClaimNo;
    }

    public String getWorkersCompCarrierId() {
        return workersCompCarrierId;
    }

    public void setWorkersCompCarrierId(String workersCompCarrierId) {
        this.workersCompCarrierId = workersCompCarrierId;
    }

    public String getWorkersCompEmployerAddressLine1() {
        return workersCompEmployerAddressLine1;
    }

    public void setWorkersCompEmployerAddressLine1(String workersCompEmployerAddressLine1) {
        this.workersCompEmployerAddressLine1 = workersCompEmployerAddressLine1;
    }

    public String getWorkersCompEmployerAddressLine2() {
        return workersCompEmployerAddressLine2;
    }

    public void setWorkersCompEmployerAddressLine2(String workersCompEmployerAddressLine2) {
        this.workersCompEmployerAddressLine2 = workersCompEmployerAddressLine2;
    }

    public String getWorkersCompEmployerCity() {
        return workersCompEmployerCity;
    }

    public void setWorkersCompEmployerCity(String workersCompEmployerCity) {
        this.workersCompEmployerCity = workersCompEmployerCity;
    }

    public String getWorkersCompEmployerState() {
        return workersCompEmployerState;
    }

    public void setWorkersCompEmployerState(String workersCompEmployerState) {
        this.workersCompEmployerState = workersCompEmployerState;
    }

    public String getWorkersCompEmployerCountry() {
        return workersCompEmployerCountry;
    }

    public void setWorkersCompEmployerCountry(String workersCompEmployerCountry) {
        this.workersCompEmployerCountry = workersCompEmployerCountry;
    }

    public String getWorkersCompEmployerZip() {
        return workersCompEmployerZip;
    }

    public void setWorkersCompEmployerZip(String workersCompEmployerZip) {
        this.workersCompEmployerZip = workersCompEmployerZip;
    }

    public String getWorkersCompEmployerContactNo1() {
        return workersCompEmployerContactNo1;
    }

    public void setWorkersCompEmployerContactNo1(String workersCompEmployerContactNo1) {
        this.workersCompEmployerContactNo1 = workersCompEmployerContactNo1;
    }

    public String getWorkersCompEmployerContactNo2() {
        return workersCompEmployerContactNo2;
    }

    public void setWorkersCompEmployerContactNo2(String workersCompEmployerContactNo2) {
        this.workersCompEmployerContactNo2 = workersCompEmployerContactNo2;
    }

    public String getWorkersCompEmployerFax() {
        return workersCompEmployerFax;
    }

    public void setWorkersCompEmployerFax(String workersCompEmployerFax) {
        this.workersCompEmployerFax = workersCompEmployerFax;
    }

    public String getWorkersCompEmployerEfax() {
        return workersCompEmployerEfax;
    }

    public void setWorkersCompEmployerEfax(String workersCompEmployerEfax) {
        this.workersCompEmployerEfax = workersCompEmployerEfax;
    }

    public String getWorkersCompEmployerEmail() {
        return workersCompEmployerEmail;
    }

    public void setWorkersCompEmployerEmail(String workersCompEmployerEmail) {
        this.workersCompEmployerEmail = workersCompEmployerEmail;
    }

    public String getWorkersCompRelationship() {
        return workersCompRelationship;
    }

    public void setWorkersCompRelationship(String workersCompRelationship) {
        this.workersCompRelationship = workersCompRelationship;
    }

    public String getWorkersCompModeOfContact() {
        return workersCompModeOfContact;
    }

    public void setWorkersCompModeOfContact(String workersCompModeOfContact) {
        this.workersCompModeOfContact = workersCompModeOfContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderInsuranceDetailsDTO)) {
            return false;
        }

        SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetailsDTO = (SalesOrderInsuranceDetailsDTO) o;
        if (this.salesOrderInsuranceDetailsId == null) {
            return false;
        }
        return Objects.equals(this.salesOrderInsuranceDetailsId, salesOrderInsuranceDetailsDTO.salesOrderInsuranceDetailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.salesOrderInsuranceDetailsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderInsuranceDetailsDTO{" +
            "salesOrderInsuranceDetailsId=" + getSalesOrderInsuranceDetailsId() +
            ", salesOrderId=" + getSalesOrderId() +
            ", patientId=" + getPatientId() +
            ", primaryInsurerId=" + getPrimaryInsurerId() +
            ", primaryInsurerName='" + getPrimaryInsurerName() + "'" +
            ", primaryInsurerPolicyNo='" + getPrimaryInsurerPolicyNo() + "'" +
            ", primaryInsurerPatientIdNumber='" + getPrimaryInsurerPatientIdNumber() + "'" +
            ", primaryInsurerEffectiveDate='" + getPrimaryInsurerEffectiveDate() + "'" +
            ", primaryInsurerVerificationStatus='" + getPrimaryInsurerVerificationStatus() + "'" +
            ", primaryInsurerVerificationDate='" + getPrimaryInsurerVerificationDate() + "'" +
            ", primaryInsurerPayPercentage=" + getPrimaryInsurerPayPercentage() +
            ", primaryBox10D='" + getPrimaryBox10D() + "'" +
            ", primaryBox19='" + getPrimaryBox19() + "'" +
            ", primaryBox24Ia='" + getPrimaryBox24Ia() + "'" +
            ", primaryBox24Ja='" + getPrimaryBox24Ja() + "'" +
            ", primaryBox24Jb='" + getPrimaryBox24Jb() + "'" +
            ", primaryIncludeBox24Jbstatus='" + getPrimaryIncludeBox24Jbstatus() + "'" +
            ", primaryIncludePayerSalesOrderStatus='" + getPrimaryIncludePayerSalesOrderStatus() + "'" +
            ", primaryWaitForPreviousPayerBeforeBillingStatus='" + getPrimaryWaitForPreviousPayerBeforeBillingStatus() + "'" +
            ", primaryPayPercentageStatus='" + getPrimaryPayPercentageStatus() + "'" +
            ", secondaryInsurerId=" + getSecondaryInsurerId() +
            ", secondaryInsurerName='" + getSecondaryInsurerName() + "'" +
            ", secondaryInsurerPolicyNo='" + getSecondaryInsurerPolicyNo() + "'" +
            ", secondaryInsurerPatientIdNumber='" + getSecondaryInsurerPatientIdNumber() + "'" +
            ", secondaryInsurerEffectiveDate='" + getSecondaryInsurerEffectiveDate() + "'" +
            ", secondaryInsurerVerificationStatus='" + getSecondaryInsurerVerificationStatus() + "'" +
            ", secondaryInsurerVerificationDate='" + getSecondaryInsurerVerificationDate() + "'" +
            ", secondaryInsurerPayPercentage=" + getSecondaryInsurerPayPercentage() +
            ", secondaryBox10D='" + getSecondaryBox10D() + "'" +
            ", secondaryBox19='" + getSecondaryBox19() + "'" +
            ", secondaryBox24Ia='" + getSecondaryBox24Ia() + "'" +
            ", secondaryBox24Ja='" + getSecondaryBox24Ja() + "'" +
            ", secondaryBox24Jb='" + getSecondaryBox24Jb() + "'" +
            ", secondaryIncludeBox24JbStatus='" + getSecondaryIncludeBox24JbStatus() + "'" +
            ", secondaryIncludePayerSalesOrderStatus='" + getSecondaryIncludePayerSalesOrderStatus() + "'" +
            ", secondaryWaitPreviousPayerBefrBillingStatus='" + getSecondaryWaitPreviousPayerBefrBillingStatus() + "'" +
            ", secondaryPayPercentageStatus='" + getSecondaryPayPercentageStatus() + "'" +
            ", tertiaryInsurerId=" + getTertiaryInsurerId() +
            ", tertiaryInsurerName='" + getTertiaryInsurerName() + "'" +
            ", tertiaryInsurerPolicyno='" + getTertiaryInsurerPolicyno() + "'" +
            ", tertiaryInsurerPatientIdNumber='" + getTertiaryInsurerPatientIdNumber() + "'" +
            ", tertiaryInsurerEffectiveDate='" + getTertiaryInsurerEffectiveDate() + "'" +
            ", tertiaryInsurerVerificationStatus='" + getTertiaryInsurerVerificationStatus() + "'" +
            ", tertiaryInsurerVerificationDate='" + getTertiaryInsurerVerificationDate() + "'" +
            ", tertiaryInsurerPayPercentage=" + getTertiaryInsurerPayPercentage() +
            ", tertiaryBox10D='" + getTertiaryBox10D() + "'" +
            ", tertiaryBox19='" + getTertiaryBox19() + "'" +
            ", tertiaryBox24Ia='" + getTertiaryBox24Ia() + "'" +
            ", tertiaryBox24Ja='" + getTertiaryBox24Ja() + "'" +
            ", tertiaryBox24Jb='" + getTertiaryBox24Jb() + "'" +
            ", tertiaryIncludeBox24JbStatus='" + getTertiaryIncludeBox24JbStatus() + "'" +
            ", tertiaryIncludePayerInSalesOrderStatus='" + getTertiaryIncludePayerInSalesOrderStatus() + "'" +
            ", tertiaryWaitPreviousPayerBeforeBillingStatus='" + getTertiaryWaitPreviousPayerBeforeBillingStatus() + "'" +
            ", tertiaryPayPercentageStatus='" + getTertiaryPayPercentageStatus() + "'" +
            ", insuranceVerificationStatus='" + getInsuranceVerificationStatus() + "'" +
            ", coverageVerificationStatus='" + getCoverageVerificationStatus() + "'" +
            ", excludeFromEligibilityCheckStatus='" + getExcludeFromEligibilityCheckStatus() + "'" +
            ", patientPayPercentage=" + getPatientPayPercentage() +
            ", patientIncludeThisPayorInSalesOrderStatus='" + getPatientIncludeThisPayorInSalesOrderStatus() + "'" +
            ", patientWaitForPreviousPayerBeforeBillingStatus='" + getPatientWaitForPreviousPayerBeforeBillingStatus() + "'" +
            ", workersCompDateOfOnset='" + getWorkersCompDateOfOnset() + "'" +
            ", workersCompInjuryRelatedEmploymentStatus='" + getWorkersCompInjuryRelatedEmploymentStatus() + "'" +
            ", workersCompInjuryRelatedAutoAccidentStatus='" + getWorkersCompInjuryRelatedAutoAccidentStatus() + "'" +
            ", workersCompAutoAccidentStateCode='" + getWorkersCompAutoAccidentStateCode() + "'" +
            ", workersCompInjuryRelatedToOtherAccidentStatus='" + getWorkersCompInjuryRelatedToOtherAccidentStatus() + "'" +
            ", eclaimsAttachmentStatus='" + getEclaimsAttachmentStatus() + "'" +
            ", attachmentNumber=" + getAttachmentNumber() +
            ", typeCode='" + getTypeCode() + "'" +
            ", transactionCode='" + getTransactionCode() + "'" +
            ", claimsNoteType='" + getClaimsNoteType() + "'" +
            ", claimsNote='" + getClaimsNote() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", salesOrderNo='" + getSalesOrderNo() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", primaryInsuranceGroupId=" + getPrimaryInsuranceGroupId() +
            ", primaryInsuranceGroupName='" + getPrimaryInsuranceGroupName() + "'" +
            ", secondaryInsuranceGroupId=" + getSecondaryInsuranceGroupId() +
            ", secondaryInsuranceGroupName='" + getSecondaryInsuranceGroupName() + "'" +
            ", tertiaryInsuranceGroupId=" + getTertiaryInsuranceGroupId() +
            ", tertiaryInsuranceGroupName='" + getTertiaryInsuranceGroupName() + "'" +
            ", primaryInsurancePlanId=" + getPrimaryInsurancePlanId() +
            ", primaryInsurancePlanType='" + getPrimaryInsurancePlanType() + "'" +
            ", secondaryInsurancePlanId=" + getSecondaryInsurancePlanId() +
            ", secondaryInsurancePlanType='" + getSecondaryInsurancePlanType() + "'" +
            ", tertiaryInsurancePlanId=" + getTertiaryInsurancePlanId() +
            ", tertiaryInsurancePlanType='" + getTertiaryInsurancePlanType() + "'" +
            ", salesOrderInsuranceDetailsUuid='" + getSalesOrderInsuranceDetailsUuid() + "'" +
            ", primaryInsurancePayerId='" + getPrimaryInsurancePayerId() + "'" +
            ", secondaryInsurancePayerId='" + getSecondaryInsurancePayerId() + "'" +
            ", tertiaryInsurancePayerId='" + getTertiaryInsurancePayerId() + "'" +
            ", primaryInsuranceIndicatorCode='" + getPrimaryInsuranceIndicatorCode() + "'" +
            ", secondaryInsuranceIndicatorCode='" + getSecondaryInsuranceIndicatorCode() + "'" +
            ", tertiaryInsuranceIndicatorCode='" + getTertiaryInsuranceIndicatorCode() + "'" +
            ", priceTableId=" + getPriceTableId() +
            ", priceTableName='" + getPriceTableName() + "'" +
            ", primaryInsurerAddressLine1='" + getPrimaryInsurerAddressLine1() + "'" +
            ", primaryInsurerAddressLine2='" + getPrimaryInsurerAddressLine2() + "'" +
            ", primaryInsurerCity='" + getPrimaryInsurerCity() + "'" +
            ", primaryInsurerState='" + getPrimaryInsurerState() + "'" +
            ", primaryInsurerZip='" + getPrimaryInsurerZip() + "'" +
            ", primaryInsurerContact1='" + getPrimaryInsurerContact1() + "'" +
            ", primaryInsurerFax='" + getPrimaryInsurerFax() + "'" +
            ", secondaryInsurerAddressLine1='" + getSecondaryInsurerAddressLine1() + "'" +
            ", secondaryInsurerAddressLine2='" + getSecondaryInsurerAddressLine2() + "'" +
            ", secondaryInsurerCity='" + getSecondaryInsurerCity() + "'" +
            ", secondaryInsurerState='" + getSecondaryInsurerState() + "'" +
            ", secondaryInsurerZip='" + getSecondaryInsurerZip() + "'" +
            ", secondaryInsurerContact1='" + getSecondaryInsurerContact1() + "'" +
            ", secondaryInsurerFax='" + getSecondaryInsurerFax() + "'" +
            ", tertiaryInsurerAddressLine1='" + getTertiaryInsurerAddressLine1() + "'" +
            ", tertiaryInsurerAddressLine2='" + getTertiaryInsurerAddressLine2() + "'" +
            ", tertiaryInsurerCity='" + getTertiaryInsurerCity() + "'" +
            ", tertiaryInsurerState='" + getTertiaryInsurerState() + "'" +
            ", tertiaryInsurerZip='" + getTertiaryInsurerZip() + "'" +
            ", tertiaryInsurerContact1='" + getTertiaryInsurerContact1() + "'" +
            ", tertiaryInsurerFax='" + getTertiaryInsurerFax() + "'" +
            ", primaryInsurerPolicyEndDate='" + getPrimaryInsurerPolicyEndDate() + "'" +
            ", primaryInsurerContactName='" + getPrimaryInsurerContactName() + "'" +
            ", primaryClaimProgram='" + getPrimaryClaimProgram() + "'" +
            ", secondaryClaimProgram='" + getSecondaryClaimProgram() + "'" +
            ", tertiaryClaimProgram='" + getTertiaryClaimProgram() + "'" +
            ", workersCompInsuredEmployer='" + getWorkersCompInsuredEmployer() + "'" +
            ", workersCompPayerIdNumber='" + getWorkersCompPayerIdNumber() + "'" +
            ", workersCompPlanName='" + getWorkersCompPlanName() + "'" +
            ", workersCompAdditionalDtls='" + getWorkersCompAdditionalDtls() + "'" +
            ", workersCompClaimFillingCode='" + getWorkersCompClaimFillingCode() + "'" +
            ", workersCompTplCode='" + getWorkersCompTplCode() + "'" +
            ", workersCompTplName='" + getWorkersCompTplName() + "'" +
            ", workersCompPropertyCasualtyAgencyClaimNo='" + getWorkersCompPropertyCasualtyAgencyClaimNo() + "'" +
            ", workersCompCarrierId='" + getWorkersCompCarrierId() + "'" +
            ", workersCompEmployerAddressLine1='" + getWorkersCompEmployerAddressLine1() + "'" +
            ", workersCompEmployerAddressLine2='" + getWorkersCompEmployerAddressLine2() + "'" +
            ", workersCompEmployerCity='" + getWorkersCompEmployerCity() + "'" +
            ", workersCompEmployerState='" + getWorkersCompEmployerState() + "'" +
            ", workersCompEmployerCountry='" + getWorkersCompEmployerCountry() + "'" +
            ", workersCompEmployerZip='" + getWorkersCompEmployerZip() + "'" +
            ", workersCompEmployerContactNo1='" + getWorkersCompEmployerContactNo1() + "'" +
            ", workersCompEmployerContactNo2='" + getWorkersCompEmployerContactNo2() + "'" +
            ", workersCompEmployerFax='" + getWorkersCompEmployerFax() + "'" +
            ", workersCompEmployerEfax='" + getWorkersCompEmployerEfax() + "'" +
            ", workersCompEmployerEmail='" + getWorkersCompEmployerEmail() + "'" +
            ", workersCompRelationship='" + getWorkersCompRelationship() + "'" +
            ", workersCompModeOfContact='" + getWorkersCompModeOfContact() + "'" +
            "}";
    }
}
