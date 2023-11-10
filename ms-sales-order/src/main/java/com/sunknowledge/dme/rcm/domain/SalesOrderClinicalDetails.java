package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SalesOrderClinicalDetails.
 */
@Table("t_sales_order_clinical_details")
public class SalesOrderClinicalDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("sales_order_clinical_details_id")
    private Long salesOrderClinicalDetailsId;

    @Column("sales_order_id")
    private Long salesOrderId;

    @Column("patient_id")
    private Long patientId;

    @Column("patient_weight_in_kg")
    private Double patientWeightInKg;

    @Column("patient_weight_in_lbs")
    private Double patientWeightInLbs;

    @Column("height_in_inches")
    private Double heightInInches;

    @Column("height_in_cm")
    private Double heightInCm;

    @Column("sales_rep_id")
    private Long salesRepId;

    @Column("sales_rep_name")
    private String salesRepName;

    @Column("rendering_provider_facility_id")
    private Long renderingProviderFacilityId;

    @Column("rendering_provider_facility_name")
    private String renderingProviderFacilityName;

    @Column("rendering_provider_id")
    private Long renderingProviderId;

    @Column("rendering_provider_type")
    private String renderingProviderType;

    @Column("rendering_provider_first_name")
    private String renderingProviderFirstName;

    @Column("rendering_provider_middle_name")
    private String renderingProviderMiddleName;

    @Column("rendering_provider_last_name")
    private String renderingProviderLastName;

    @Column("rendering_provider_npi")
    private String renderingProviderNpi;

    @Column("rendering_provider_dea")
    private String renderingProviderDea;

    @Column("rendering_provider_address_line_1")
    private String renderingProviderAddressLine1;

    @Column("rendering_provider_address_line_2")
    private String renderingProviderAddressLine2;

    @Column("rendering_provider_email")
    private String renderingProviderEmail;

    @Column("rendering_provider_fax")
    private String renderingProviderFax;

    @Column("referring_provider_facility_id")
    private Long referringProviderFacilityId;

    @Column("referring_provider_facility_name")
    private String referringProviderFacilityName;

    @Column("referring_provider_id")
    private Long referringProviderId;

    @Column("referring_provider_type")
    private String referringProviderType;

    @Column("referring_provider_first_name")
    private String referringProviderFirstName;

    @Column("referring_provider_middle_name")
    private String referringProviderMiddleName;

    @Column("referring_provider_last_name")
    private String referringProviderLastName;

    @Column("referring_provider_npi")
    private String referringProviderNpi;

    @Column("referring_provider_dea")
    private String referringProviderDea;

    @Column("referring_provider_address_line_1")
    private String referringProviderAddressLine1;

    @Column("referring_provider_address_line_2")
    private String referringProviderAddressLine2;

    @Column("referring_provider_email")
    private String referringProviderEmail;

    @Column("referring_provider_fax")
    private String referringProviderFax;

    @Column("ordering_provider_facility_id")
    private Long orderingProviderFacilityId;

    @Column("ordering_provider_facility_name")
    private String orderingProviderFacilityName;

    @Column("ordering_provider_id")
    private Long orderingProviderId;

    @Column("ordering_provider_type")
    private String orderingProviderType;

    @Column("ordering_provider_first_name")
    private String orderingProviderFirstName;

    @Column("ordering_provider_middle_name")
    private String orderingProviderMiddleName;

    @Column("ordering_provider_last_name")
    private String orderingProviderLastName;

    @Column("ordering_provider_npi")
    private String orderingProviderNpi;

    @Column("ordering_provider_dea")
    private String orderingProviderDea;

    @Column("ordering_provider_address_line_1")
    private String orderingProviderAddressLine1;

    @Column("ordering_provider_address_line_2")
    private String orderingProviderAddressLine2;

    @Column("ordering_provider_email")
    private String orderingProviderEmail;

    @Column("ordering_provider_fax")
    private String orderingProviderFax;

    @Column("marketing_referral_type_id")
    private Long marketingReferralTypeId;

    @Column("marketing_referral_type_description")
    private String marketingReferralTypeDescription;

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

    @Column("epsdt_certification_condition_indicator")
    private String epsdtCertificationConditionIndicator;

    @Column("epsdt_certification_code")
    private String epsdtCertificationCode;

    @Column("status")
    private String status;

    @Column("created_by_name")
    private String createdByName;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("rendering_provider_zip")
    private String renderingProviderZip;

    @Column("referring_provider_zip")
    private String referringProviderZip;

    @Column("ordering_provider_zip")
    private String orderingProviderZip;

    @Column("marketing_referral_id")
    private Long marketingReferralId;

    @Column("marketing_referral_name")
    private String marketingReferralName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("created_by_id")
    private Long createdById;

    @Column("sales_order_clinical_details_uuid")
    private UUID salesOrderClinicalDetailsUuid;

    @Column("primary_diagnosis")
    private String primaryDiagnosis;

    @Column("ordering_provider_city")
    private String orderingProviderCity;

    @Column("ordering_provider_state")
    private String orderingProviderState;

    @Column("ordering_provider_country")
    private String orderingProviderCountry;

    @Column("ordering_provider_contact_no_1")
    private String orderingProviderContactNo1;

    @Column("ordering_provider_contact_no_2")
    private String orderingProviderContactNo2;

    @Column("ordering_provider_efax")
    private String orderingProviderEfax;

    @Column("relationship")
    private String relationship;

    @Column("mode_of_contact")
    private String modeOfContact;

    @Column("referring_provider_city")
    private String referringProviderCity;

    @Column("referring_provider_state")
    private String referringProviderState;

    @Column("referring_provider_country")
    private String referringProviderCountry;

    @Column("referring_provider_contact_no_1")
    private String referringProviderContactNo1;

    @Column("referring_provider_contact_no_2")
    private String referringProviderContactNo2;

    @Column("referring_provider_efax")
    private String referringProviderEfax;

    @Column("rendering_provider_city")
    private String renderingProviderCity;

    @Column("rendering_provider_state")
    private String renderingProviderState;

    @Column("rendering_provider_country")
    private String renderingProviderCountry;

    @Column("rendering_provider_contact_no_1")
    private String renderingProviderContactNo1;

    @Column("rendering_provider_contact_no_2")
    private String renderingProviderContactNo2;

    @Column("rendering_provider_efax")
    private String renderingProviderEfax;

    @Column("diagnosis_code_type")
    private String diagnosisCodeType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getSalesOrderClinicalDetailsId() {
        return this.salesOrderClinicalDetailsId;
    }

    public SalesOrderClinicalDetails salesOrderClinicalDetailsId(Long salesOrderClinicalDetailsId) {
        this.setSalesOrderClinicalDetailsId(salesOrderClinicalDetailsId);
        return this;
    }

    public void setSalesOrderClinicalDetailsId(Long salesOrderClinicalDetailsId) {
        this.salesOrderClinicalDetailsId = salesOrderClinicalDetailsId;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public SalesOrderClinicalDetails salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public SalesOrderClinicalDetails patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Double getPatientWeightInKg() {
        return this.patientWeightInKg;
    }

    public SalesOrderClinicalDetails patientWeightInKg(Double patientWeightInKg) {
        this.setPatientWeightInKg(patientWeightInKg);
        return this;
    }

    public void setPatientWeightInKg(Double patientWeightInKg) {
        this.patientWeightInKg = patientWeightInKg;
    }

    public Double getPatientWeightInLbs() {
        return this.patientWeightInLbs;
    }

    public SalesOrderClinicalDetails patientWeightInLbs(Double patientWeightInLbs) {
        this.setPatientWeightInLbs(patientWeightInLbs);
        return this;
    }

    public void setPatientWeightInLbs(Double patientWeightInLbs) {
        this.patientWeightInLbs = patientWeightInLbs;
    }

    public Double getHeightInInches() {
        return this.heightInInches;
    }

    public SalesOrderClinicalDetails heightInInches(Double heightInInches) {
        this.setHeightInInches(heightInInches);
        return this;
    }

    public void setHeightInInches(Double heightInInches) {
        this.heightInInches = heightInInches;
    }

    public Double getHeightInCm() {
        return this.heightInCm;
    }

    public SalesOrderClinicalDetails heightInCm(Double heightInCm) {
        this.setHeightInCm(heightInCm);
        return this;
    }

    public void setHeightInCm(Double heightInCm) {
        this.heightInCm = heightInCm;
    }

    public Long getSalesRepId() {
        return this.salesRepId;
    }

    public SalesOrderClinicalDetails salesRepId(Long salesRepId) {
        this.setSalesRepId(salesRepId);
        return this;
    }

    public void setSalesRepId(Long salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getSalesRepName() {
        return this.salesRepName;
    }

    public SalesOrderClinicalDetails salesRepName(String salesRepName) {
        this.setSalesRepName(salesRepName);
        return this;
    }

    public void setSalesRepName(String salesRepName) {
        this.salesRepName = salesRepName;
    }

    public Long getRenderingProviderFacilityId() {
        return this.renderingProviderFacilityId;
    }

    public SalesOrderClinicalDetails renderingProviderFacilityId(Long renderingProviderFacilityId) {
        this.setRenderingProviderFacilityId(renderingProviderFacilityId);
        return this;
    }

    public void setRenderingProviderFacilityId(Long renderingProviderFacilityId) {
        this.renderingProviderFacilityId = renderingProviderFacilityId;
    }

    public String getRenderingProviderFacilityName() {
        return this.renderingProviderFacilityName;
    }

    public SalesOrderClinicalDetails renderingProviderFacilityName(String renderingProviderFacilityName) {
        this.setRenderingProviderFacilityName(renderingProviderFacilityName);
        return this;
    }

    public void setRenderingProviderFacilityName(String renderingProviderFacilityName) {
        this.renderingProviderFacilityName = renderingProviderFacilityName;
    }

    public Long getRenderingProviderId() {
        return this.renderingProviderId;
    }

    public SalesOrderClinicalDetails renderingProviderId(Long renderingProviderId) {
        this.setRenderingProviderId(renderingProviderId);
        return this;
    }

    public void setRenderingProviderId(Long renderingProviderId) {
        this.renderingProviderId = renderingProviderId;
    }

    public String getRenderingProviderType() {
        return this.renderingProviderType;
    }

    public SalesOrderClinicalDetails renderingProviderType(String renderingProviderType) {
        this.setRenderingProviderType(renderingProviderType);
        return this;
    }

    public void setRenderingProviderType(String renderingProviderType) {
        this.renderingProviderType = renderingProviderType;
    }

    public String getRenderingProviderFirstName() {
        return this.renderingProviderFirstName;
    }

    public SalesOrderClinicalDetails renderingProviderFirstName(String renderingProviderFirstName) {
        this.setRenderingProviderFirstName(renderingProviderFirstName);
        return this;
    }

    public void setRenderingProviderFirstName(String renderingProviderFirstName) {
        this.renderingProviderFirstName = renderingProviderFirstName;
    }

    public String getRenderingProviderMiddleName() {
        return this.renderingProviderMiddleName;
    }

    public SalesOrderClinicalDetails renderingProviderMiddleName(String renderingProviderMiddleName) {
        this.setRenderingProviderMiddleName(renderingProviderMiddleName);
        return this;
    }

    public void setRenderingProviderMiddleName(String renderingProviderMiddleName) {
        this.renderingProviderMiddleName = renderingProviderMiddleName;
    }

    public String getRenderingProviderLastName() {
        return this.renderingProviderLastName;
    }

    public SalesOrderClinicalDetails renderingProviderLastName(String renderingProviderLastName) {
        this.setRenderingProviderLastName(renderingProviderLastName);
        return this;
    }

    public void setRenderingProviderLastName(String renderingProviderLastName) {
        this.renderingProviderLastName = renderingProviderLastName;
    }

    public String getRenderingProviderNpi() {
        return this.renderingProviderNpi;
    }

    public SalesOrderClinicalDetails renderingProviderNpi(String renderingProviderNpi) {
        this.setRenderingProviderNpi(renderingProviderNpi);
        return this;
    }

    public void setRenderingProviderNpi(String renderingProviderNpi) {
        this.renderingProviderNpi = renderingProviderNpi;
    }

    public String getRenderingProviderDea() {
        return this.renderingProviderDea;
    }

    public SalesOrderClinicalDetails renderingProviderDea(String renderingProviderDea) {
        this.setRenderingProviderDea(renderingProviderDea);
        return this;
    }

    public void setRenderingProviderDea(String renderingProviderDea) {
        this.renderingProviderDea = renderingProviderDea;
    }

    public String getRenderingProviderAddressLine1() {
        return this.renderingProviderAddressLine1;
    }

    public SalesOrderClinicalDetails renderingProviderAddressLine1(String renderingProviderAddressLine1) {
        this.setRenderingProviderAddressLine1(renderingProviderAddressLine1);
        return this;
    }

    public void setRenderingProviderAddressLine1(String renderingProviderAddressLine1) {
        this.renderingProviderAddressLine1 = renderingProviderAddressLine1;
    }

    public String getRenderingProviderAddressLine2() {
        return this.renderingProviderAddressLine2;
    }

    public SalesOrderClinicalDetails renderingProviderAddressLine2(String renderingProviderAddressLine2) {
        this.setRenderingProviderAddressLine2(renderingProviderAddressLine2);
        return this;
    }

    public void setRenderingProviderAddressLine2(String renderingProviderAddressLine2) {
        this.renderingProviderAddressLine2 = renderingProviderAddressLine2;
    }

    public String getRenderingProviderEmail() {
        return this.renderingProviderEmail;
    }

    public SalesOrderClinicalDetails renderingProviderEmail(String renderingProviderEmail) {
        this.setRenderingProviderEmail(renderingProviderEmail);
        return this;
    }

    public void setRenderingProviderEmail(String renderingProviderEmail) {
        this.renderingProviderEmail = renderingProviderEmail;
    }

    public String getRenderingProviderFax() {
        return this.renderingProviderFax;
    }

    public SalesOrderClinicalDetails renderingProviderFax(String renderingProviderFax) {
        this.setRenderingProviderFax(renderingProviderFax);
        return this;
    }

    public void setRenderingProviderFax(String renderingProviderFax) {
        this.renderingProviderFax = renderingProviderFax;
    }

    public Long getReferringProviderFacilityId() {
        return this.referringProviderFacilityId;
    }

    public SalesOrderClinicalDetails referringProviderFacilityId(Long referringProviderFacilityId) {
        this.setReferringProviderFacilityId(referringProviderFacilityId);
        return this;
    }

    public void setReferringProviderFacilityId(Long referringProviderFacilityId) {
        this.referringProviderFacilityId = referringProviderFacilityId;
    }

    public String getReferringProviderFacilityName() {
        return this.referringProviderFacilityName;
    }

    public SalesOrderClinicalDetails referringProviderFacilityName(String referringProviderFacilityName) {
        this.setReferringProviderFacilityName(referringProviderFacilityName);
        return this;
    }

    public void setReferringProviderFacilityName(String referringProviderFacilityName) {
        this.referringProviderFacilityName = referringProviderFacilityName;
    }

    public Long getReferringProviderId() {
        return this.referringProviderId;
    }

    public SalesOrderClinicalDetails referringProviderId(Long referringProviderId) {
        this.setReferringProviderId(referringProviderId);
        return this;
    }

    public void setReferringProviderId(Long referringProviderId) {
        this.referringProviderId = referringProviderId;
    }

    public String getReferringProviderType() {
        return this.referringProviderType;
    }

    public SalesOrderClinicalDetails referringProviderType(String referringProviderType) {
        this.setReferringProviderType(referringProviderType);
        return this;
    }

    public void setReferringProviderType(String referringProviderType) {
        this.referringProviderType = referringProviderType;
    }

    public String getReferringProviderFirstName() {
        return this.referringProviderFirstName;
    }

    public SalesOrderClinicalDetails referringProviderFirstName(String referringProviderFirstName) {
        this.setReferringProviderFirstName(referringProviderFirstName);
        return this;
    }

    public void setReferringProviderFirstName(String referringProviderFirstName) {
        this.referringProviderFirstName = referringProviderFirstName;
    }

    public String getReferringProviderMiddleName() {
        return this.referringProviderMiddleName;
    }

    public SalesOrderClinicalDetails referringProviderMiddleName(String referringProviderMiddleName) {
        this.setReferringProviderMiddleName(referringProviderMiddleName);
        return this;
    }

    public void setReferringProviderMiddleName(String referringProviderMiddleName) {
        this.referringProviderMiddleName = referringProviderMiddleName;
    }

    public String getReferringProviderLastName() {
        return this.referringProviderLastName;
    }

    public SalesOrderClinicalDetails referringProviderLastName(String referringProviderLastName) {
        this.setReferringProviderLastName(referringProviderLastName);
        return this;
    }

    public void setReferringProviderLastName(String referringProviderLastName) {
        this.referringProviderLastName = referringProviderLastName;
    }

    public String getReferringProviderNpi() {
        return this.referringProviderNpi;
    }

    public SalesOrderClinicalDetails referringProviderNpi(String referringProviderNpi) {
        this.setReferringProviderNpi(referringProviderNpi);
        return this;
    }

    public void setReferringProviderNpi(String referringProviderNpi) {
        this.referringProviderNpi = referringProviderNpi;
    }

    public String getReferringProviderDea() {
        return this.referringProviderDea;
    }

    public SalesOrderClinicalDetails referringProviderDea(String referringProviderDea) {
        this.setReferringProviderDea(referringProviderDea);
        return this;
    }

    public void setReferringProviderDea(String referringProviderDea) {
        this.referringProviderDea = referringProviderDea;
    }

    public String getReferringProviderAddressLine1() {
        return this.referringProviderAddressLine1;
    }

    public SalesOrderClinicalDetails referringProviderAddressLine1(String referringProviderAddressLine1) {
        this.setReferringProviderAddressLine1(referringProviderAddressLine1);
        return this;
    }

    public void setReferringProviderAddressLine1(String referringProviderAddressLine1) {
        this.referringProviderAddressLine1 = referringProviderAddressLine1;
    }

    public String getReferringProviderAddressLine2() {
        return this.referringProviderAddressLine2;
    }

    public SalesOrderClinicalDetails referringProviderAddressLine2(String referringProviderAddressLine2) {
        this.setReferringProviderAddressLine2(referringProviderAddressLine2);
        return this;
    }

    public void setReferringProviderAddressLine2(String referringProviderAddressLine2) {
        this.referringProviderAddressLine2 = referringProviderAddressLine2;
    }

    public String getReferringProviderEmail() {
        return this.referringProviderEmail;
    }

    public SalesOrderClinicalDetails referringProviderEmail(String referringProviderEmail) {
        this.setReferringProviderEmail(referringProviderEmail);
        return this;
    }

    public void setReferringProviderEmail(String referringProviderEmail) {
        this.referringProviderEmail = referringProviderEmail;
    }

    public String getReferringProviderFax() {
        return this.referringProviderFax;
    }

    public SalesOrderClinicalDetails referringProviderFax(String referringProviderFax) {
        this.setReferringProviderFax(referringProviderFax);
        return this;
    }

    public void setReferringProviderFax(String referringProviderFax) {
        this.referringProviderFax = referringProviderFax;
    }

    public Long getOrderingProviderFacilityId() {
        return this.orderingProviderFacilityId;
    }

    public SalesOrderClinicalDetails orderingProviderFacilityId(Long orderingProviderFacilityId) {
        this.setOrderingProviderFacilityId(orderingProviderFacilityId);
        return this;
    }

    public void setOrderingProviderFacilityId(Long orderingProviderFacilityId) {
        this.orderingProviderFacilityId = orderingProviderFacilityId;
    }

    public String getOrderingProviderFacilityName() {
        return this.orderingProviderFacilityName;
    }

    public SalesOrderClinicalDetails orderingProviderFacilityName(String orderingProviderFacilityName) {
        this.setOrderingProviderFacilityName(orderingProviderFacilityName);
        return this;
    }

    public void setOrderingProviderFacilityName(String orderingProviderFacilityName) {
        this.orderingProviderFacilityName = orderingProviderFacilityName;
    }

    public Long getOrderingProviderId() {
        return this.orderingProviderId;
    }

    public SalesOrderClinicalDetails orderingProviderId(Long orderingProviderId) {
        this.setOrderingProviderId(orderingProviderId);
        return this;
    }

    public void setOrderingProviderId(Long orderingProviderId) {
        this.orderingProviderId = orderingProviderId;
    }

    public String getOrderingProviderType() {
        return this.orderingProviderType;
    }

    public SalesOrderClinicalDetails orderingProviderType(String orderingProviderType) {
        this.setOrderingProviderType(orderingProviderType);
        return this;
    }

    public void setOrderingProviderType(String orderingProviderType) {
        this.orderingProviderType = orderingProviderType;
    }

    public String getOrderingProviderFirstName() {
        return this.orderingProviderFirstName;
    }

    public SalesOrderClinicalDetails orderingProviderFirstName(String orderingProviderFirstName) {
        this.setOrderingProviderFirstName(orderingProviderFirstName);
        return this;
    }

    public void setOrderingProviderFirstName(String orderingProviderFirstName) {
        this.orderingProviderFirstName = orderingProviderFirstName;
    }

    public String getOrderingProviderMiddleName() {
        return this.orderingProviderMiddleName;
    }

    public SalesOrderClinicalDetails orderingProviderMiddleName(String orderingProviderMiddleName) {
        this.setOrderingProviderMiddleName(orderingProviderMiddleName);
        return this;
    }

    public void setOrderingProviderMiddleName(String orderingProviderMiddleName) {
        this.orderingProviderMiddleName = orderingProviderMiddleName;
    }

    public String getOrderingProviderLastName() {
        return this.orderingProviderLastName;
    }

    public SalesOrderClinicalDetails orderingProviderLastName(String orderingProviderLastName) {
        this.setOrderingProviderLastName(orderingProviderLastName);
        return this;
    }

    public void setOrderingProviderLastName(String orderingProviderLastName) {
        this.orderingProviderLastName = orderingProviderLastName;
    }

    public String getOrderingProviderNpi() {
        return this.orderingProviderNpi;
    }

    public SalesOrderClinicalDetails orderingProviderNpi(String orderingProviderNpi) {
        this.setOrderingProviderNpi(orderingProviderNpi);
        return this;
    }

    public void setOrderingProviderNpi(String orderingProviderNpi) {
        this.orderingProviderNpi = orderingProviderNpi;
    }

    public String getOrderingProviderDea() {
        return this.orderingProviderDea;
    }

    public SalesOrderClinicalDetails orderingProviderDea(String orderingProviderDea) {
        this.setOrderingProviderDea(orderingProviderDea);
        return this;
    }

    public void setOrderingProviderDea(String orderingProviderDea) {
        this.orderingProviderDea = orderingProviderDea;
    }

    public String getOrderingProviderAddressLine1() {
        return this.orderingProviderAddressLine1;
    }

    public SalesOrderClinicalDetails orderingProviderAddressLine1(String orderingProviderAddressLine1) {
        this.setOrderingProviderAddressLine1(orderingProviderAddressLine1);
        return this;
    }

    public void setOrderingProviderAddressLine1(String orderingProviderAddressLine1) {
        this.orderingProviderAddressLine1 = orderingProviderAddressLine1;
    }

    public String getOrderingProviderAddressLine2() {
        return this.orderingProviderAddressLine2;
    }

    public SalesOrderClinicalDetails orderingProviderAddressLine2(String orderingProviderAddressLine2) {
        this.setOrderingProviderAddressLine2(orderingProviderAddressLine2);
        return this;
    }

    public void setOrderingProviderAddressLine2(String orderingProviderAddressLine2) {
        this.orderingProviderAddressLine2 = orderingProviderAddressLine2;
    }

    public String getOrderingProviderEmail() {
        return this.orderingProviderEmail;
    }

    public SalesOrderClinicalDetails orderingProviderEmail(String orderingProviderEmail) {
        this.setOrderingProviderEmail(orderingProviderEmail);
        return this;
    }

    public void setOrderingProviderEmail(String orderingProviderEmail) {
        this.orderingProviderEmail = orderingProviderEmail;
    }

    public String getOrderingProviderFax() {
        return this.orderingProviderFax;
    }

    public SalesOrderClinicalDetails orderingProviderFax(String orderingProviderFax) {
        this.setOrderingProviderFax(orderingProviderFax);
        return this;
    }

    public void setOrderingProviderFax(String orderingProviderFax) {
        this.orderingProviderFax = orderingProviderFax;
    }

    public Long getMarketingReferralTypeId() {
        return this.marketingReferralTypeId;
    }

    public SalesOrderClinicalDetails marketingReferralTypeId(Long marketingReferralTypeId) {
        this.setMarketingReferralTypeId(marketingReferralTypeId);
        return this;
    }

    public void setMarketingReferralTypeId(Long marketingReferralTypeId) {
        this.marketingReferralTypeId = marketingReferralTypeId;
    }

    public String getMarketingReferralTypeDescription() {
        return this.marketingReferralTypeDescription;
    }

    public SalesOrderClinicalDetails marketingReferralTypeDescription(String marketingReferralTypeDescription) {
        this.setMarketingReferralTypeDescription(marketingReferralTypeDescription);
        return this;
    }

    public void setMarketingReferralTypeDescription(String marketingReferralTypeDescription) {
        this.marketingReferralTypeDescription = marketingReferralTypeDescription;
    }

    public String getIcd10DiagnosisCode1() {
        return this.icd10DiagnosisCode1;
    }

    public SalesOrderClinicalDetails icd10DiagnosisCode1(String icd10DiagnosisCode1) {
        this.setIcd10DiagnosisCode1(icd10DiagnosisCode1);
        return this;
    }

    public void setIcd10DiagnosisCode1(String icd10DiagnosisCode1) {
        this.icd10DiagnosisCode1 = icd10DiagnosisCode1;
    }

    public String getIcd10DiagnosisCode2() {
        return this.icd10DiagnosisCode2;
    }

    public SalesOrderClinicalDetails icd10DiagnosisCode2(String icd10DiagnosisCode2) {
        this.setIcd10DiagnosisCode2(icd10DiagnosisCode2);
        return this;
    }

    public void setIcd10DiagnosisCode2(String icd10DiagnosisCode2) {
        this.icd10DiagnosisCode2 = icd10DiagnosisCode2;
    }

    public String getIcd10DiagnosisCode3() {
        return this.icd10DiagnosisCode3;
    }

    public SalesOrderClinicalDetails icd10DiagnosisCode3(String icd10DiagnosisCode3) {
        this.setIcd10DiagnosisCode3(icd10DiagnosisCode3);
        return this;
    }

    public void setIcd10DiagnosisCode3(String icd10DiagnosisCode3) {
        this.icd10DiagnosisCode3 = icd10DiagnosisCode3;
    }

    public String getIcd10DiagnosisCode4() {
        return this.icd10DiagnosisCode4;
    }

    public SalesOrderClinicalDetails icd10DiagnosisCode4(String icd10DiagnosisCode4) {
        this.setIcd10DiagnosisCode4(icd10DiagnosisCode4);
        return this;
    }

    public void setIcd10DiagnosisCode4(String icd10DiagnosisCode4) {
        this.icd10DiagnosisCode4 = icd10DiagnosisCode4;
    }

    public String getIcd10DiagnosisCode5() {
        return this.icd10DiagnosisCode5;
    }

    public SalesOrderClinicalDetails icd10DiagnosisCode5(String icd10DiagnosisCode5) {
        this.setIcd10DiagnosisCode5(icd10DiagnosisCode5);
        return this;
    }

    public void setIcd10DiagnosisCode5(String icd10DiagnosisCode5) {
        this.icd10DiagnosisCode5 = icd10DiagnosisCode5;
    }

    public String getIcd10DiagnosisCode6() {
        return this.icd10DiagnosisCode6;
    }

    public SalesOrderClinicalDetails icd10DiagnosisCode6(String icd10DiagnosisCode6) {
        this.setIcd10DiagnosisCode6(icd10DiagnosisCode6);
        return this;
    }

    public void setIcd10DiagnosisCode6(String icd10DiagnosisCode6) {
        this.icd10DiagnosisCode6 = icd10DiagnosisCode6;
    }

    public String getIcd10DiagnosisCode7() {
        return this.icd10DiagnosisCode7;
    }

    public SalesOrderClinicalDetails icd10DiagnosisCode7(String icd10DiagnosisCode7) {
        this.setIcd10DiagnosisCode7(icd10DiagnosisCode7);
        return this;
    }

    public void setIcd10DiagnosisCode7(String icd10DiagnosisCode7) {
        this.icd10DiagnosisCode7 = icd10DiagnosisCode7;
    }

    public String getIcd10DiagnosisCode8() {
        return this.icd10DiagnosisCode8;
    }

    public SalesOrderClinicalDetails icd10DiagnosisCode8(String icd10DiagnosisCode8) {
        this.setIcd10DiagnosisCode8(icd10DiagnosisCode8);
        return this;
    }

    public void setIcd10DiagnosisCode8(String icd10DiagnosisCode8) {
        this.icd10DiagnosisCode8 = icd10DiagnosisCode8;
    }

    public String getIcd10DiagnosisCode9() {
        return this.icd10DiagnosisCode9;
    }

    public SalesOrderClinicalDetails icd10DiagnosisCode9(String icd10DiagnosisCode9) {
        this.setIcd10DiagnosisCode9(icd10DiagnosisCode9);
        return this;
    }

    public void setIcd10DiagnosisCode9(String icd10DiagnosisCode9) {
        this.icd10DiagnosisCode9 = icd10DiagnosisCode9;
    }

    public String getIcd10DiagnosisCode10() {
        return this.icd10DiagnosisCode10;
    }

    public SalesOrderClinicalDetails icd10DiagnosisCode10(String icd10DiagnosisCode10) {
        this.setIcd10DiagnosisCode10(icd10DiagnosisCode10);
        return this;
    }

    public void setIcd10DiagnosisCode10(String icd10DiagnosisCode10) {
        this.icd10DiagnosisCode10 = icd10DiagnosisCode10;
    }

    public String getIcd10DiagnosisCode11() {
        return this.icd10DiagnosisCode11;
    }

    public SalesOrderClinicalDetails icd10DiagnosisCode11(String icd10DiagnosisCode11) {
        this.setIcd10DiagnosisCode11(icd10DiagnosisCode11);
        return this;
    }

    public void setIcd10DiagnosisCode11(String icd10DiagnosisCode11) {
        this.icd10DiagnosisCode11 = icd10DiagnosisCode11;
    }

    public String getIcd10DiagnosisCode12() {
        return this.icd10DiagnosisCode12;
    }

    public SalesOrderClinicalDetails icd10DiagnosisCode12(String icd10DiagnosisCode12) {
        this.setIcd10DiagnosisCode12(icd10DiagnosisCode12);
        return this;
    }

    public void setIcd10DiagnosisCode12(String icd10DiagnosisCode12) {
        this.icd10DiagnosisCode12 = icd10DiagnosisCode12;
    }

    public String getEpsdtCertificationConditionIndicator() {
        return this.epsdtCertificationConditionIndicator;
    }

    public SalesOrderClinicalDetails epsdtCertificationConditionIndicator(String epsdtCertificationConditionIndicator) {
        this.setEpsdtCertificationConditionIndicator(epsdtCertificationConditionIndicator);
        return this;
    }

    public void setEpsdtCertificationConditionIndicator(String epsdtCertificationConditionIndicator) {
        this.epsdtCertificationConditionIndicator = epsdtCertificationConditionIndicator;
    }

    public String getEpsdtCertificationCode() {
        return this.epsdtCertificationCode;
    }

    public SalesOrderClinicalDetails epsdtCertificationCode(String epsdtCertificationCode) {
        this.setEpsdtCertificationCode(epsdtCertificationCode);
        return this;
    }

    public void setEpsdtCertificationCode(String epsdtCertificationCode) {
        this.epsdtCertificationCode = epsdtCertificationCode;
    }

    public String getStatus() {
        return this.status;
    }

    public SalesOrderClinicalDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public SalesOrderClinicalDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public SalesOrderClinicalDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SalesOrderClinicalDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public SalesOrderClinicalDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getRenderingProviderZip() {
        return this.renderingProviderZip;
    }

    public SalesOrderClinicalDetails renderingProviderZip(String renderingProviderZip) {
        this.setRenderingProviderZip(renderingProviderZip);
        return this;
    }

    public void setRenderingProviderZip(String renderingProviderZip) {
        this.renderingProviderZip = renderingProviderZip;
    }

    public String getReferringProviderZip() {
        return this.referringProviderZip;
    }

    public SalesOrderClinicalDetails referringProviderZip(String referringProviderZip) {
        this.setReferringProviderZip(referringProviderZip);
        return this;
    }

    public void setReferringProviderZip(String referringProviderZip) {
        this.referringProviderZip = referringProviderZip;
    }

    public String getOrderingProviderZip() {
        return this.orderingProviderZip;
    }

    public SalesOrderClinicalDetails orderingProviderZip(String orderingProviderZip) {
        this.setOrderingProviderZip(orderingProviderZip);
        return this;
    }

    public void setOrderingProviderZip(String orderingProviderZip) {
        this.orderingProviderZip = orderingProviderZip;
    }

    public Long getMarketingReferralId() {
        return this.marketingReferralId;
    }

    public SalesOrderClinicalDetails marketingReferralId(Long marketingReferralId) {
        this.setMarketingReferralId(marketingReferralId);
        return this;
    }

    public void setMarketingReferralId(Long marketingReferralId) {
        this.marketingReferralId = marketingReferralId;
    }

    public String getMarketingReferralName() {
        return this.marketingReferralName;
    }

    public SalesOrderClinicalDetails marketingReferralName(String marketingReferralName) {
        this.setMarketingReferralName(marketingReferralName);
        return this;
    }

    public void setMarketingReferralName(String marketingReferralName) {
        this.marketingReferralName = marketingReferralName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SalesOrderClinicalDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public SalesOrderClinicalDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public UUID getSalesOrderClinicalDetailsUuid() {
        return this.salesOrderClinicalDetailsUuid;
    }

    public SalesOrderClinicalDetails salesOrderClinicalDetailsUuid(UUID salesOrderClinicalDetailsUuid) {
        this.setSalesOrderClinicalDetailsUuid(salesOrderClinicalDetailsUuid);
        return this;
    }

    public void setSalesOrderClinicalDetailsUuid(UUID salesOrderClinicalDetailsUuid) {
        this.salesOrderClinicalDetailsUuid = salesOrderClinicalDetailsUuid;
    }

    public String getPrimaryDiagnosis() {
        return this.primaryDiagnosis;
    }

    public SalesOrderClinicalDetails primaryDiagnosis(String primaryDiagnosis) {
        this.setPrimaryDiagnosis(primaryDiagnosis);
        return this;
    }

    public void setPrimaryDiagnosis(String primaryDiagnosis) {
        this.primaryDiagnosis = primaryDiagnosis;
    }

    public String getOrderingProviderCity() {
        return this.orderingProviderCity;
    }

    public SalesOrderClinicalDetails orderingProviderCity(String orderingProviderCity) {
        this.setOrderingProviderCity(orderingProviderCity);
        return this;
    }

    public void setOrderingProviderCity(String orderingProviderCity) {
        this.orderingProviderCity = orderingProviderCity;
    }

    public String getOrderingProviderState() {
        return this.orderingProviderState;
    }

    public SalesOrderClinicalDetails orderingProviderState(String orderingProviderState) {
        this.setOrderingProviderState(orderingProviderState);
        return this;
    }

    public void setOrderingProviderState(String orderingProviderState) {
        this.orderingProviderState = orderingProviderState;
    }

    public String getOrderingProviderCountry() {
        return this.orderingProviderCountry;
    }

    public SalesOrderClinicalDetails orderingProviderCountry(String orderingProviderCountry) {
        this.setOrderingProviderCountry(orderingProviderCountry);
        return this;
    }

    public void setOrderingProviderCountry(String orderingProviderCountry) {
        this.orderingProviderCountry = orderingProviderCountry;
    }

    public String getOrderingProviderContactNo1() {
        return this.orderingProviderContactNo1;
    }

    public SalesOrderClinicalDetails orderingProviderContactNo1(String orderingProviderContactNo1) {
        this.setOrderingProviderContactNo1(orderingProviderContactNo1);
        return this;
    }

    public void setOrderingProviderContactNo1(String orderingProviderContactNo1) {
        this.orderingProviderContactNo1 = orderingProviderContactNo1;
    }

    public String getOrderingProviderContactNo2() {
        return this.orderingProviderContactNo2;
    }

    public SalesOrderClinicalDetails orderingProviderContactNo2(String orderingProviderContactNo2) {
        this.setOrderingProviderContactNo2(orderingProviderContactNo2);
        return this;
    }

    public void setOrderingProviderContactNo2(String orderingProviderContactNo2) {
        this.orderingProviderContactNo2 = orderingProviderContactNo2;
    }

    public String getOrderingProviderEfax() {
        return this.orderingProviderEfax;
    }

    public SalesOrderClinicalDetails orderingProviderEfax(String orderingProviderEfax) {
        this.setOrderingProviderEfax(orderingProviderEfax);
        return this;
    }

    public void setOrderingProviderEfax(String orderingProviderEfax) {
        this.orderingProviderEfax = orderingProviderEfax;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public SalesOrderClinicalDetails relationship(String relationship) {
        this.setRelationship(relationship);
        return this;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getModeOfContact() {
        return this.modeOfContact;
    }

    public SalesOrderClinicalDetails modeOfContact(String modeOfContact) {
        this.setModeOfContact(modeOfContact);
        return this;
    }

    public void setModeOfContact(String modeOfContact) {
        this.modeOfContact = modeOfContact;
    }

    public String getReferringProviderCity() {
        return this.referringProviderCity;
    }

    public SalesOrderClinicalDetails referringProviderCity(String referringProviderCity) {
        this.setReferringProviderCity(referringProviderCity);
        return this;
    }

    public void setReferringProviderCity(String referringProviderCity) {
        this.referringProviderCity = referringProviderCity;
    }

    public String getReferringProviderState() {
        return this.referringProviderState;
    }

    public SalesOrderClinicalDetails referringProviderState(String referringProviderState) {
        this.setReferringProviderState(referringProviderState);
        return this;
    }

    public void setReferringProviderState(String referringProviderState) {
        this.referringProviderState = referringProviderState;
    }

    public String getReferringProviderCountry() {
        return this.referringProviderCountry;
    }

    public SalesOrderClinicalDetails referringProviderCountry(String referringProviderCountry) {
        this.setReferringProviderCountry(referringProviderCountry);
        return this;
    }

    public void setReferringProviderCountry(String referringProviderCountry) {
        this.referringProviderCountry = referringProviderCountry;
    }

    public String getReferringProviderContactNo1() {
        return this.referringProviderContactNo1;
    }

    public SalesOrderClinicalDetails referringProviderContactNo1(String referringProviderContactNo1) {
        this.setReferringProviderContactNo1(referringProviderContactNo1);
        return this;
    }

    public void setReferringProviderContactNo1(String referringProviderContactNo1) {
        this.referringProviderContactNo1 = referringProviderContactNo1;
    }

    public String getReferringProviderContactNo2() {
        return this.referringProviderContactNo2;
    }

    public SalesOrderClinicalDetails referringProviderContactNo2(String referringProviderContactNo2) {
        this.setReferringProviderContactNo2(referringProviderContactNo2);
        return this;
    }

    public void setReferringProviderContactNo2(String referringProviderContactNo2) {
        this.referringProviderContactNo2 = referringProviderContactNo2;
    }

    public String getReferringProviderEfax() {
        return this.referringProviderEfax;
    }

    public SalesOrderClinicalDetails referringProviderEfax(String referringProviderEfax) {
        this.setReferringProviderEfax(referringProviderEfax);
        return this;
    }

    public void setReferringProviderEfax(String referringProviderEfax) {
        this.referringProviderEfax = referringProviderEfax;
    }

    public String getRenderingProviderCity() {
        return this.renderingProviderCity;
    }

    public SalesOrderClinicalDetails renderingProviderCity(String renderingProviderCity) {
        this.setRenderingProviderCity(renderingProviderCity);
        return this;
    }

    public void setRenderingProviderCity(String renderingProviderCity) {
        this.renderingProviderCity = renderingProviderCity;
    }

    public String getRenderingProviderState() {
        return this.renderingProviderState;
    }

    public SalesOrderClinicalDetails renderingProviderState(String renderingProviderState) {
        this.setRenderingProviderState(renderingProviderState);
        return this;
    }

    public void setRenderingProviderState(String renderingProviderState) {
        this.renderingProviderState = renderingProviderState;
    }

    public String getRenderingProviderCountry() {
        return this.renderingProviderCountry;
    }

    public SalesOrderClinicalDetails renderingProviderCountry(String renderingProviderCountry) {
        this.setRenderingProviderCountry(renderingProviderCountry);
        return this;
    }

    public void setRenderingProviderCountry(String renderingProviderCountry) {
        this.renderingProviderCountry = renderingProviderCountry;
    }

    public String getRenderingProviderContactNo1() {
        return this.renderingProviderContactNo1;
    }

    public SalesOrderClinicalDetails renderingProviderContactNo1(String renderingProviderContactNo1) {
        this.setRenderingProviderContactNo1(renderingProviderContactNo1);
        return this;
    }

    public void setRenderingProviderContactNo1(String renderingProviderContactNo1) {
        this.renderingProviderContactNo1 = renderingProviderContactNo1;
    }

    public String getRenderingProviderContactNo2() {
        return this.renderingProviderContactNo2;
    }

    public SalesOrderClinicalDetails renderingProviderContactNo2(String renderingProviderContactNo2) {
        this.setRenderingProviderContactNo2(renderingProviderContactNo2);
        return this;
    }

    public void setRenderingProviderContactNo2(String renderingProviderContactNo2) {
        this.renderingProviderContactNo2 = renderingProviderContactNo2;
    }

    public String getRenderingProviderEfax() {
        return this.renderingProviderEfax;
    }

    public SalesOrderClinicalDetails renderingProviderEfax(String renderingProviderEfax) {
        this.setRenderingProviderEfax(renderingProviderEfax);
        return this;
    }

    public void setRenderingProviderEfax(String renderingProviderEfax) {
        this.renderingProviderEfax = renderingProviderEfax;
    }

    public String getDiagnosisCodeType() {
        return this.diagnosisCodeType;
    }

    public SalesOrderClinicalDetails diagnosisCodeType(String diagnosisCodeType) {
        this.setDiagnosisCodeType(diagnosisCodeType);
        return this;
    }

    public void setDiagnosisCodeType(String diagnosisCodeType) {
        this.diagnosisCodeType = diagnosisCodeType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderClinicalDetails)) {
            return false;
        }
        return (
            salesOrderClinicalDetailsId != null &&
            salesOrderClinicalDetailsId.equals(((SalesOrderClinicalDetails) o).salesOrderClinicalDetailsId)
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
        return "SalesOrderClinicalDetails{" +
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
