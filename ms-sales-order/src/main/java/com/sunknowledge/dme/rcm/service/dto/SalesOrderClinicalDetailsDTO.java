package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails} entity.
 */
public class SalesOrderClinicalDetailsDTO implements Serializable {

    private Long salesOrderClinicalDetailsId;

    private Long salesOrderId;

    private Long patientId;

    private Double patientWeightInKg;

    private Double patientWeightInLbs;

    private Double heightInInches;

    private Double heightInCm;

    private Long salesRepId;

    private String salesRepName;

    private Long renderingProviderFacilityId;

    private String renderingProviderFacilityName;

    private Long renderingProviderId;

    private String renderingProviderType;

    private String renderingProviderFirstName;

    private String renderingProviderMiddleName;

    private String renderingProviderLastName;

    private String renderingProviderNpi;

    private String renderingProviderDea;

    private String renderingProviderAddressLine1;

    private String renderingProviderAddressLine2;

    private String renderingProviderEmail;

    private String renderingProviderFax;

    private Long referringProviderFacilityId;

    private String referringProviderFacilityName;

    private Long referringProviderId;

    private String referringProviderType;

    private String referringProviderFirstName;

    private String referringProviderMiddleName;

    private String referringProviderLastName;

    private String referringProviderNpi;

    private String referringProviderDea;

    private String referringProviderAddressLine1;

    private String referringProviderAddressLine2;

    private String referringProviderEmail;

    private String referringProviderFax;

    private Long orderingProviderFacilityId;

    private String orderingProviderFacilityName;

    private Long orderingProviderId;

    private String orderingProviderType;

    private String orderingProviderFirstName;

    private String orderingProviderMiddleName;

    private String orderingProviderLastName;

    private String orderingProviderNpi;

    private String orderingProviderDea;

    private String orderingProviderAddressLine1;

    private String orderingProviderAddressLine2;

    private String orderingProviderEmail;

    private String orderingProviderFax;

    private Long marketingReferralTypeId;

    private String marketingReferralTypeDescription;

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

    private String epsdtCertificationConditionIndicator;

    private String epsdtCertificationCode;

    private String status;

    private String createdByName;

    private LocalDate createdDate;

    private String updatedByName;

    private LocalDate updatedDate;

    private String renderingProviderZip;

    private String referringProviderZip;

    private String orderingProviderZip;

    private Long marketingReferralId;

    private String marketingReferralName;

    private Long updatedById;

    private Long createdById;

    private UUID salesOrderClinicalDetailsUuid;

    private String primaryDiagnosis;

    private String orderingProviderCity;

    private String orderingProviderState;

    private String orderingProviderCountry;

    private String orderingProviderContactNo1;

    private String orderingProviderContactNo2;

    private String orderingProviderEfax;

    private String relationship;

    private String modeOfContact;

    private String referringProviderCity;

    private String referringProviderState;

    private String referringProviderCountry;

    private String referringProviderContactNo1;

    private String referringProviderContactNo2;

    private String referringProviderEfax;

    private String renderingProviderCity;

    private String renderingProviderState;

    private String renderingProviderCountry;

    private String renderingProviderContactNo1;

    private String renderingProviderContactNo2;

    private String renderingProviderEfax;

    private String diagnosisCodeType;

    public Long getSalesOrderClinicalDetailsId() {
        return salesOrderClinicalDetailsId;
    }

    public void setSalesOrderClinicalDetailsId(Long salesOrderClinicalDetailsId) {
        this.salesOrderClinicalDetailsId = salesOrderClinicalDetailsId;
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

    public Double getPatientWeightInKg() {
        return patientWeightInKg;
    }

    public void setPatientWeightInKg(Double patientWeightInKg) {
        this.patientWeightInKg = patientWeightInKg;
    }

    public Double getPatientWeightInLbs() {
        return patientWeightInLbs;
    }

    public void setPatientWeightInLbs(Double patientWeightInLbs) {
        this.patientWeightInLbs = patientWeightInLbs;
    }

    public Double getHeightInInches() {
        return heightInInches;
    }

    public void setHeightInInches(Double heightInInches) {
        this.heightInInches = heightInInches;
    }

    public Double getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(Double heightInCm) {
        this.heightInCm = heightInCm;
    }

    public Long getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Long salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getSalesRepName() {
        return salesRepName;
    }

    public void setSalesRepName(String salesRepName) {
        this.salesRepName = salesRepName;
    }

    public Long getRenderingProviderFacilityId() {
        return renderingProviderFacilityId;
    }

    public void setRenderingProviderFacilityId(Long renderingProviderFacilityId) {
        this.renderingProviderFacilityId = renderingProviderFacilityId;
    }

    public String getRenderingProviderFacilityName() {
        return renderingProviderFacilityName;
    }

    public void setRenderingProviderFacilityName(String renderingProviderFacilityName) {
        this.renderingProviderFacilityName = renderingProviderFacilityName;
    }

    public Long getRenderingProviderId() {
        return renderingProviderId;
    }

    public void setRenderingProviderId(Long renderingProviderId) {
        this.renderingProviderId = renderingProviderId;
    }

    public String getRenderingProviderType() {
        return renderingProviderType;
    }

    public void setRenderingProviderType(String renderingProviderType) {
        this.renderingProviderType = renderingProviderType;
    }

    public String getRenderingProviderFirstName() {
        return renderingProviderFirstName;
    }

    public void setRenderingProviderFirstName(String renderingProviderFirstName) {
        this.renderingProviderFirstName = renderingProviderFirstName;
    }

    public String getRenderingProviderMiddleName() {
        return renderingProviderMiddleName;
    }

    public void setRenderingProviderMiddleName(String renderingProviderMiddleName) {
        this.renderingProviderMiddleName = renderingProviderMiddleName;
    }

    public String getRenderingProviderLastName() {
        return renderingProviderLastName;
    }

    public void setRenderingProviderLastName(String renderingProviderLastName) {
        this.renderingProviderLastName = renderingProviderLastName;
    }

    public String getRenderingProviderNpi() {
        return renderingProviderNpi;
    }

    public void setRenderingProviderNpi(String renderingProviderNpi) {
        this.renderingProviderNpi = renderingProviderNpi;
    }

    public String getRenderingProviderDea() {
        return renderingProviderDea;
    }

    public void setRenderingProviderDea(String renderingProviderDea) {
        this.renderingProviderDea = renderingProviderDea;
    }

    public String getRenderingProviderAddressLine1() {
        return renderingProviderAddressLine1;
    }

    public void setRenderingProviderAddressLine1(String renderingProviderAddressLine1) {
        this.renderingProviderAddressLine1 = renderingProviderAddressLine1;
    }

    public String getRenderingProviderAddressLine2() {
        return renderingProviderAddressLine2;
    }

    public void setRenderingProviderAddressLine2(String renderingProviderAddressLine2) {
        this.renderingProviderAddressLine2 = renderingProviderAddressLine2;
    }

    public String getRenderingProviderEmail() {
        return renderingProviderEmail;
    }

    public void setRenderingProviderEmail(String renderingProviderEmail) {
        this.renderingProviderEmail = renderingProviderEmail;
    }

    public String getRenderingProviderFax() {
        return renderingProviderFax;
    }

    public void setRenderingProviderFax(String renderingProviderFax) {
        this.renderingProviderFax = renderingProviderFax;
    }

    public Long getReferringProviderFacilityId() {
        return referringProviderFacilityId;
    }

    public void setReferringProviderFacilityId(Long referringProviderFacilityId) {
        this.referringProviderFacilityId = referringProviderFacilityId;
    }

    public String getReferringProviderFacilityName() {
        return referringProviderFacilityName;
    }

    public void setReferringProviderFacilityName(String referringProviderFacilityName) {
        this.referringProviderFacilityName = referringProviderFacilityName;
    }

    public Long getReferringProviderId() {
        return referringProviderId;
    }

    public void setReferringProviderId(Long referringProviderId) {
        this.referringProviderId = referringProviderId;
    }

    public String getReferringProviderType() {
        return referringProviderType;
    }

    public void setReferringProviderType(String referringProviderType) {
        this.referringProviderType = referringProviderType;
    }

    public String getReferringProviderFirstName() {
        return referringProviderFirstName;
    }

    public void setReferringProviderFirstName(String referringProviderFirstName) {
        this.referringProviderFirstName = referringProviderFirstName;
    }

    public String getReferringProviderMiddleName() {
        return referringProviderMiddleName;
    }

    public void setReferringProviderMiddleName(String referringProviderMiddleName) {
        this.referringProviderMiddleName = referringProviderMiddleName;
    }

    public String getReferringProviderLastName() {
        return referringProviderLastName;
    }

    public void setReferringProviderLastName(String referringProviderLastName) {
        this.referringProviderLastName = referringProviderLastName;
    }

    public String getReferringProviderNpi() {
        return referringProviderNpi;
    }

    public void setReferringProviderNpi(String referringProviderNpi) {
        this.referringProviderNpi = referringProviderNpi;
    }

    public String getReferringProviderDea() {
        return referringProviderDea;
    }

    public void setReferringProviderDea(String referringProviderDea) {
        this.referringProviderDea = referringProviderDea;
    }

    public String getReferringProviderAddressLine1() {
        return referringProviderAddressLine1;
    }

    public void setReferringProviderAddressLine1(String referringProviderAddressLine1) {
        this.referringProviderAddressLine1 = referringProviderAddressLine1;
    }

    public String getReferringProviderAddressLine2() {
        return referringProviderAddressLine2;
    }

    public void setReferringProviderAddressLine2(String referringProviderAddressLine2) {
        this.referringProviderAddressLine2 = referringProviderAddressLine2;
    }

    public String getReferringProviderEmail() {
        return referringProviderEmail;
    }

    public void setReferringProviderEmail(String referringProviderEmail) {
        this.referringProviderEmail = referringProviderEmail;
    }

    public String getReferringProviderFax() {
        return referringProviderFax;
    }

    public void setReferringProviderFax(String referringProviderFax) {
        this.referringProviderFax = referringProviderFax;
    }

    public Long getOrderingProviderFacilityId() {
        return orderingProviderFacilityId;
    }

    public void setOrderingProviderFacilityId(Long orderingProviderFacilityId) {
        this.orderingProviderFacilityId = orderingProviderFacilityId;
    }

    public String getOrderingProviderFacilityName() {
        return orderingProviderFacilityName;
    }

    public void setOrderingProviderFacilityName(String orderingProviderFacilityName) {
        this.orderingProviderFacilityName = orderingProviderFacilityName;
    }

    public Long getOrderingProviderId() {
        return orderingProviderId;
    }

    public void setOrderingProviderId(Long orderingProviderId) {
        this.orderingProviderId = orderingProviderId;
    }

    public String getOrderingProviderType() {
        return orderingProviderType;
    }

    public void setOrderingProviderType(String orderingProviderType) {
        this.orderingProviderType = orderingProviderType;
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

    public String getOrderingProviderDea() {
        return orderingProviderDea;
    }

    public void setOrderingProviderDea(String orderingProviderDea) {
        this.orderingProviderDea = orderingProviderDea;
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

    public String getOrderingProviderEmail() {
        return orderingProviderEmail;
    }

    public void setOrderingProviderEmail(String orderingProviderEmail) {
        this.orderingProviderEmail = orderingProviderEmail;
    }

    public String getOrderingProviderFax() {
        return orderingProviderFax;
    }

    public void setOrderingProviderFax(String orderingProviderFax) {
        this.orderingProviderFax = orderingProviderFax;
    }

    public Long getMarketingReferralTypeId() {
        return marketingReferralTypeId;
    }

    public void setMarketingReferralTypeId(Long marketingReferralTypeId) {
        this.marketingReferralTypeId = marketingReferralTypeId;
    }

    public String getMarketingReferralTypeDescription() {
        return marketingReferralTypeDescription;
    }

    public void setMarketingReferralTypeDescription(String marketingReferralTypeDescription) {
        this.marketingReferralTypeDescription = marketingReferralTypeDescription;
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

    public String getEpsdtCertificationConditionIndicator() {
        return epsdtCertificationConditionIndicator;
    }

    public void setEpsdtCertificationConditionIndicator(String epsdtCertificationConditionIndicator) {
        this.epsdtCertificationConditionIndicator = epsdtCertificationConditionIndicator;
    }

    public String getEpsdtCertificationCode() {
        return epsdtCertificationCode;
    }

    public void setEpsdtCertificationCode(String epsdtCertificationCode) {
        this.epsdtCertificationCode = epsdtCertificationCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getRenderingProviderZip() {
        return renderingProviderZip;
    }

    public void setRenderingProviderZip(String renderingProviderZip) {
        this.renderingProviderZip = renderingProviderZip;
    }

    public String getReferringProviderZip() {
        return referringProviderZip;
    }

    public void setReferringProviderZip(String referringProviderZip) {
        this.referringProviderZip = referringProviderZip;
    }

    public String getOrderingProviderZip() {
        return orderingProviderZip;
    }

    public void setOrderingProviderZip(String orderingProviderZip) {
        this.orderingProviderZip = orderingProviderZip;
    }

    public Long getMarketingReferralId() {
        return marketingReferralId;
    }

    public void setMarketingReferralId(Long marketingReferralId) {
        this.marketingReferralId = marketingReferralId;
    }

    public String getMarketingReferralName() {
        return marketingReferralName;
    }

    public void setMarketingReferralName(String marketingReferralName) {
        this.marketingReferralName = marketingReferralName;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public UUID getSalesOrderClinicalDetailsUuid() {
        return salesOrderClinicalDetailsUuid;
    }

    public void setSalesOrderClinicalDetailsUuid(UUID salesOrderClinicalDetailsUuid) {
        this.salesOrderClinicalDetailsUuid = salesOrderClinicalDetailsUuid;
    }

    public String getPrimaryDiagnosis() {
        return primaryDiagnosis;
    }

    public void setPrimaryDiagnosis(String primaryDiagnosis) {
        this.primaryDiagnosis = primaryDiagnosis;
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

    public String getOrderingProviderCountry() {
        return orderingProviderCountry;
    }

    public void setOrderingProviderCountry(String orderingProviderCountry) {
        this.orderingProviderCountry = orderingProviderCountry;
    }

    public String getOrderingProviderContactNo1() {
        return orderingProviderContactNo1;
    }

    public void setOrderingProviderContactNo1(String orderingProviderContactNo1) {
        this.orderingProviderContactNo1 = orderingProviderContactNo1;
    }

    public String getOrderingProviderContactNo2() {
        return orderingProviderContactNo2;
    }

    public void setOrderingProviderContactNo2(String orderingProviderContactNo2) {
        this.orderingProviderContactNo2 = orderingProviderContactNo2;
    }

    public String getOrderingProviderEfax() {
        return orderingProviderEfax;
    }

    public void setOrderingProviderEfax(String orderingProviderEfax) {
        this.orderingProviderEfax = orderingProviderEfax;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getModeOfContact() {
        return modeOfContact;
    }

    public void setModeOfContact(String modeOfContact) {
        this.modeOfContact = modeOfContact;
    }

    public String getReferringProviderCity() {
        return referringProviderCity;
    }

    public void setReferringProviderCity(String referringProviderCity) {
        this.referringProviderCity = referringProviderCity;
    }

    public String getReferringProviderState() {
        return referringProviderState;
    }

    public void setReferringProviderState(String referringProviderState) {
        this.referringProviderState = referringProviderState;
    }

    public String getReferringProviderCountry() {
        return referringProviderCountry;
    }

    public void setReferringProviderCountry(String referringProviderCountry) {
        this.referringProviderCountry = referringProviderCountry;
    }

    public String getReferringProviderContactNo1() {
        return referringProviderContactNo1;
    }

    public void setReferringProviderContactNo1(String referringProviderContactNo1) {
        this.referringProviderContactNo1 = referringProviderContactNo1;
    }

    public String getReferringProviderContactNo2() {
        return referringProviderContactNo2;
    }

    public void setReferringProviderContactNo2(String referringProviderContactNo2) {
        this.referringProviderContactNo2 = referringProviderContactNo2;
    }

    public String getReferringProviderEfax() {
        return referringProviderEfax;
    }

    public void setReferringProviderEfax(String referringProviderEfax) {
        this.referringProviderEfax = referringProviderEfax;
    }

    public String getRenderingProviderCity() {
        return renderingProviderCity;
    }

    public void setRenderingProviderCity(String renderingProviderCity) {
        this.renderingProviderCity = renderingProviderCity;
    }

    public String getRenderingProviderState() {
        return renderingProviderState;
    }

    public void setRenderingProviderState(String renderingProviderState) {
        this.renderingProviderState = renderingProviderState;
    }

    public String getRenderingProviderCountry() {
        return renderingProviderCountry;
    }

    public void setRenderingProviderCountry(String renderingProviderCountry) {
        this.renderingProviderCountry = renderingProviderCountry;
    }

    public String getRenderingProviderContactNo1() {
        return renderingProviderContactNo1;
    }

    public void setRenderingProviderContactNo1(String renderingProviderContactNo1) {
        this.renderingProviderContactNo1 = renderingProviderContactNo1;
    }

    public String getRenderingProviderContactNo2() {
        return renderingProviderContactNo2;
    }

    public void setRenderingProviderContactNo2(String renderingProviderContactNo2) {
        this.renderingProviderContactNo2 = renderingProviderContactNo2;
    }

    public String getRenderingProviderEfax() {
        return renderingProviderEfax;
    }

    public void setRenderingProviderEfax(String renderingProviderEfax) {
        this.renderingProviderEfax = renderingProviderEfax;
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
        if (!(o instanceof SalesOrderClinicalDetailsDTO)) {
            return false;
        }

        SalesOrderClinicalDetailsDTO salesOrderClinicalDetailsDTO = (SalesOrderClinicalDetailsDTO) o;
        if (this.salesOrderClinicalDetailsId == null) {
            return false;
        }
        return Objects.equals(this.salesOrderClinicalDetailsId, salesOrderClinicalDetailsDTO.salesOrderClinicalDetailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.salesOrderClinicalDetailsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesOrderClinicalDetailsDTO{" +
            "salesOrderClinicalDetailsId=" + getSalesOrderClinicalDetailsId() +
            ", salesOrderId=" + getSalesOrderId() +
            ", patientId=" + getPatientId() +
            ", patientWeightInKg=" + getPatientWeightInKg() +
            ", patientWeightInLbs=" + getPatientWeightInLbs() +
            ", heightInInches=" + getHeightInInches() +
            ", heightInCm=" + getHeightInCm() +
            ", salesRepId=" + getSalesRepId() +
            ", salesRepName='" + getSalesRepName() + "'" +
            ", renderingProviderFacilityId=" + getRenderingProviderFacilityId() +
            ", renderingProviderFacilityName='" + getRenderingProviderFacilityName() + "'" +
            ", renderingProviderId=" + getRenderingProviderId() +
            ", renderingProviderType='" + getRenderingProviderType() + "'" +
            ", renderingProviderFirstName='" + getRenderingProviderFirstName() + "'" +
            ", renderingProviderMiddleName='" + getRenderingProviderMiddleName() + "'" +
            ", renderingProviderLastName='" + getRenderingProviderLastName() + "'" +
            ", renderingProviderNpi='" + getRenderingProviderNpi() + "'" +
            ", renderingProviderDea='" + getRenderingProviderDea() + "'" +
            ", renderingProviderAddressLine1='" + getRenderingProviderAddressLine1() + "'" +
            ", renderingProviderAddressLine2='" + getRenderingProviderAddressLine2() + "'" +
            ", renderingProviderEmail='" + getRenderingProviderEmail() + "'" +
            ", renderingProviderFax='" + getRenderingProviderFax() + "'" +
            ", referringProviderFacilityId=" + getReferringProviderFacilityId() +
            ", referringProviderFacilityName='" + getReferringProviderFacilityName() + "'" +
            ", referringProviderId=" + getReferringProviderId() +
            ", referringProviderType='" + getReferringProviderType() + "'" +
            ", referringProviderFirstName='" + getReferringProviderFirstName() + "'" +
            ", referringProviderMiddleName='" + getReferringProviderMiddleName() + "'" +
            ", referringProviderLastName='" + getReferringProviderLastName() + "'" +
            ", referringProviderNpi='" + getReferringProviderNpi() + "'" +
            ", referringProviderDea='" + getReferringProviderDea() + "'" +
            ", referringProviderAddressLine1='" + getReferringProviderAddressLine1() + "'" +
            ", referringProviderAddressLine2='" + getReferringProviderAddressLine2() + "'" +
            ", referringProviderEmail='" + getReferringProviderEmail() + "'" +
            ", referringProviderFax='" + getReferringProviderFax() + "'" +
            ", orderingProviderFacilityId=" + getOrderingProviderFacilityId() +
            ", orderingProviderFacilityName='" + getOrderingProviderFacilityName() + "'" +
            ", orderingProviderId=" + getOrderingProviderId() +
            ", orderingProviderType='" + getOrderingProviderType() + "'" +
            ", orderingProviderFirstName='" + getOrderingProviderFirstName() + "'" +
            ", orderingProviderMiddleName='" + getOrderingProviderMiddleName() + "'" +
            ", orderingProviderLastName='" + getOrderingProviderLastName() + "'" +
            ", orderingProviderNpi='" + getOrderingProviderNpi() + "'" +
            ", orderingProviderDea='" + getOrderingProviderDea() + "'" +
            ", orderingProviderAddressLine1='" + getOrderingProviderAddressLine1() + "'" +
            ", orderingProviderAddressLine2='" + getOrderingProviderAddressLine2() + "'" +
            ", orderingProviderEmail='" + getOrderingProviderEmail() + "'" +
            ", orderingProviderFax='" + getOrderingProviderFax() + "'" +
            ", marketingReferralTypeId=" + getMarketingReferralTypeId() +
            ", marketingReferralTypeDescription='" + getMarketingReferralTypeDescription() + "'" +
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
            ", epsdtCertificationConditionIndicator='" + getEpsdtCertificationConditionIndicator() + "'" +
            ", epsdtCertificationCode='" + getEpsdtCertificationCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", renderingProviderZip='" + getRenderingProviderZip() + "'" +
            ", referringProviderZip='" + getReferringProviderZip() + "'" +
            ", orderingProviderZip='" + getOrderingProviderZip() + "'" +
            ", marketingReferralId=" + getMarketingReferralId() +
            ", marketingReferralName='" + getMarketingReferralName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", createdById=" + getCreatedById() +
            ", salesOrderClinicalDetailsUuid='" + getSalesOrderClinicalDetailsUuid() + "'" +
            ", primaryDiagnosis='" + getPrimaryDiagnosis() + "'" +
            ", orderingProviderCity='" + getOrderingProviderCity() + "'" +
            ", orderingProviderState='" + getOrderingProviderState() + "'" +
            ", orderingProviderCountry='" + getOrderingProviderCountry() + "'" +
            ", orderingProviderContactNo1='" + getOrderingProviderContactNo1() + "'" +
            ", orderingProviderContactNo2='" + getOrderingProviderContactNo2() + "'" +
            ", orderingProviderEfax='" + getOrderingProviderEfax() + "'" +
            ", relationship='" + getRelationship() + "'" +
            ", modeOfContact='" + getModeOfContact() + "'" +
            ", referringProviderCity='" + getReferringProviderCity() + "'" +
            ", referringProviderState='" + getReferringProviderState() + "'" +
            ", referringProviderCountry='" + getReferringProviderCountry() + "'" +
            ", referringProviderContactNo1='" + getReferringProviderContactNo1() + "'" +
            ", referringProviderContactNo2='" + getReferringProviderContactNo2() + "'" +
            ", referringProviderEfax='" + getReferringProviderEfax() + "'" +
            ", renderingProviderCity='" + getRenderingProviderCity() + "'" +
            ", renderingProviderState='" + getRenderingProviderState() + "'" +
            ", renderingProviderCountry='" + getRenderingProviderCountry() + "'" +
            ", renderingProviderContactNo1='" + getRenderingProviderContactNo1() + "'" +
            ", renderingProviderContactNo2='" + getRenderingProviderContactNo2() + "'" +
            ", renderingProviderEfax='" + getRenderingProviderEfax() + "'" +
            ", diagnosisCodeType='" + getDiagnosisCodeType() + "'" +
            "}";
    }
}
