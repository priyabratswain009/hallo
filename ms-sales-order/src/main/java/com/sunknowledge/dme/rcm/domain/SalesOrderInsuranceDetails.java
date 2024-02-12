package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SalesOrderInsuranceDetails.
 */
@Table("t_sales_order_insurance_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SalesOrderInsuranceDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("sales_order_insurance_details_id")
    private Long salesOrderInsuranceDetailsId;

    @Column("sales_order_id")
    private Long salesOrderId;

    @Column("patient_id")
    private Long patientId;

    @Column("primary_insurer_id")
    private Long primaryInsurerId;

    @Column("primary_insurer_name")
    private String primaryInsurerName;

    @Column("primary_insurer_policy_no")
    private String primaryInsurerPolicyNo;

    @Column("primary_insurer_patient_id_number")
    private String primaryInsurerPatientIdNumber;

    @Column("primary_insurer_effective_date")
    private LocalDate primaryInsurerEffectiveDate;

    @Column("primary_insurer_verification_status")
    private String primaryInsurerVerificationStatus;

    @Column("primary_insurer_verification_date")
    private LocalDate primaryInsurerVerificationDate;

    @Column("primary_insurer_pay_percentage")
    private Long primaryInsurerPayPercentage;

    @Column("primary_box_10_d")
    private String primaryBox10D;

    @Column("primary_box_19")
    private String primaryBox19;

    @Column("primary_box_24_ia")
    private String primaryBox24Ia;

    @Column("primary_box_24_ja")
    private String primaryBox24Ja;

    @Column("primary_box_24_jb")
    private String primaryBox24Jb;

    @Column("primary_include_box_24_jbstatus")
    private String primaryIncludeBox24Jbstatus;

    @Column("primary_include_payer_sales_order_status")
    private String primaryIncludePayerSalesOrderStatus;

    @Column("primary_wait_for_previous_payer_before_billing_status")
    private String primaryWaitForPreviousPayerBeforeBillingStatus;

    @Column("primary_pay_percentage_status")
    private String primaryPayPercentageStatus;

    @Column("secondary_insurer_id")
    private Long secondaryInsurerId;

    @Column("secondary_insurer_name")
    private String secondaryInsurerName;

    @Column("secondary_insurer_policy_no")
    private String secondaryInsurerPolicyNo;

    @Column("secondary_insurer_patient_id_number")
    private String secondaryInsurerPatientIdNumber;

    @Column("secondary_insurer_effective_date")
    private LocalDate secondaryInsurerEffectiveDate;

    @Column("secondary_insurer_verification_status")
    private String secondaryInsurerVerificationStatus;

    @Column("secondary_insurer_verification_date")
    private LocalDate secondaryInsurerVerificationDate;

    @Column("secondary_insurer_pay_percentage")
    private Long secondaryInsurerPayPercentage;

    @Column("secondary_box_10_d")
    private String secondaryBox10D;

    @Column("secondary_box_19")
    private String secondaryBox19;

    @Column("secondary_box_24_ia")
    private String secondaryBox24Ia;

    @Column("secondary_box_24_ja")
    private String secondaryBox24Ja;

    @Column("secondary_box_24_jb")
    private String secondaryBox24Jb;

    @Column("secondary_include_box_24_jb_status")
    private String secondaryIncludeBox24JbStatus;

    @Column("secondary_include_payer_sales_order_status")
    private String secondaryIncludePayerSalesOrderStatus;

    @Column("secondary_wait_previous_payer_befr_billing_status")
    private String secondaryWaitPreviousPayerBefrBillingStatus;

    @Column("secondary_pay_percentage_status")
    private String secondaryPayPercentageStatus;

    @Column("tertiary_insurer_id")
    private Long tertiaryInsurerId;

    @Column("tertiary_insurer_name")
    private String tertiaryInsurerName;

    @Column("tertiary_insurer_policyno")
    private String tertiaryInsurerPolicyno;

    @Column("tertiary_insurer_patient_id_number")
    private String tertiaryInsurerPatientIdNumber;

    @Column("tertiary_insurer_effective_date")
    private LocalDate tertiaryInsurerEffectiveDate;

    @Column("tertiary_insurer_verification_status")
    private String tertiaryInsurerVerificationStatus;

    @Column("tertiary_insurer_verification_date")
    private LocalDate tertiaryInsurerVerificationDate;

    @Column("tertiary_insurer_pay_percentage")
    private Long tertiaryInsurerPayPercentage;

    @Column("tertiary_box_10_d")
    private String tertiaryBox10D;

    @Column("tertiary_box_19")
    private String tertiaryBox19;

    @Column("tertiary_box_24_ia")
    private String tertiaryBox24Ia;

    @Column("tertiary_box_24_ja")
    private String tertiaryBox24Ja;

    @Column("tertiary_box_24_jb")
    private String tertiaryBox24Jb;

    @Column("tertiary_include_box_24_jb_status")
    private String tertiaryIncludeBox24JbStatus;

    @Column("tertiary_include_payer_in_sales_order_status")
    private String tertiaryIncludePayerInSalesOrderStatus;

    @Column("tertiary_wait_previous_payer_before_billing_status")
    private String tertiaryWaitPreviousPayerBeforeBillingStatus;

    @Column("tertiary_pay_percentage_status")
    private String tertiaryPayPercentageStatus;

    @Column("insurance_verification_status")
    private String insuranceVerificationStatus;

    @Column("coverage_verification_status")
    private String coverageVerificationStatus;

    @Column("exclude_from_eligibility_check_status")
    private String excludeFromEligibilityCheckStatus;

    @Column("patient_pay_percentage")
    private Long patientPayPercentage;

    @Column("patient_include_this_payor_in_sales_order_status")
    private String patientIncludeThisPayorInSalesOrderStatus;

    @Column("patient_wait_for_previous_payer_before_billing_status")
    private String patientWaitForPreviousPayerBeforeBillingStatus;

    @Column("workers_comp_date_of_onset")
    private LocalDate workersCompDateOfOnset;

    @Column("workers_comp_injury_related_employment_status")
    private String workersCompInjuryRelatedEmploymentStatus;

    @Column("workers_comp_injury_related_auto_accident_status")
    private String workersCompInjuryRelatedAutoAccidentStatus;

    @Column("workers_comp_auto_accident_state_code")
    private String workersCompAutoAccidentStateCode;

    @Column("workers_comp_injury_related_to_other_accident_status")
    private String workersCompInjuryRelatedToOtherAccidentStatus;

    @Column("eclaims_attachment_status")
    private String eclaimsAttachmentStatus;

    @Column("attachment_number")
    private Long attachmentNumber;

    @Column("type_code")
    private String typeCode;

    @Column("transaction_code")
    private String transactionCode;

    @Column("claims_note_type")
    private String claimsNoteType;

    @Column("claims_note")
    private String claimsNote;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("status")
    private String status;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("sales_order_no")
    private String salesOrderNo;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("primary_insurance_group_id")
    private Long primaryInsuranceGroupId;

    @Column("primary_insurance_group_name")
    private String primaryInsuranceGroupName;

    @Column("secondary_insurance_group_id")
    private Long secondaryInsuranceGroupId;

    @Column("secondary_insurance_group_name")
    private String secondaryInsuranceGroupName;

    @Column("tertiary_insurance_group_id")
    private Long tertiaryInsuranceGroupId;

    @Column("tertiary_insurance_group_name")
    private String tertiaryInsuranceGroupName;

    @Column("primary_insurance_plan_id")
    private Long primaryInsurancePlanId;

    @Column("primary_insurance_plan_type")
    private String primaryInsurancePlanType;

    @Column("secondary_insurance_plan_id")
    private Long secondaryInsurancePlanId;

    @Column("secondary_insurance_plan_type")
    private String secondaryInsurancePlanType;

    @Column("tertiary_insurance_plan_id")
    private Long tertiaryInsurancePlanId;

    @Column("tertiary_insurance_plan_type")
    private String tertiaryInsurancePlanType;

    @Column("sales_order_insurance_details_uuid")
    private UUID salesOrderInsuranceDetailsUuid;

    @Column("primary_insurance_payer_id")
    private String primaryInsurancePayerId;

    @Column("secondary_insurance_payer_id")
    private String secondaryInsurancePayerId;

    @Column("tertiary_insurance_payer_id")
    private String tertiaryInsurancePayerId;

    @Column("primary_insurance_indicator_code")
    private String primaryInsuranceIndicatorCode;

    @Column("secondary_insurance_indicator_code")
    private String secondaryInsuranceIndicatorCode;

    @Column("tertiary_insurance_indicator_code")
    private String tertiaryInsuranceIndicatorCode;

    @Column("price_table_id")
    private Long priceTableId;

    @Column("price_table_name")
    private String priceTableName;

    @Column("primary_insurer_address_line_1")
    private String primaryInsurerAddressLine1;

    @Column("primary_insurer_address_line_2")
    private String primaryInsurerAddressLine2;

    @Column("primary_insurer_city")
    private String primaryInsurerCity;

    @Column("primary_insurer_state")
    private String primaryInsurerState;

    @Column("primary_insurer_zip")
    private String primaryInsurerZip;

    @Column("primary_insurer_contact_1")
    private String primaryInsurerContact1;

    @Column("primary_insurer_fax")
    private String primaryInsurerFax;

    @Column("secondary_insurer_address_line_1")
    private String secondaryInsurerAddressLine1;

    @Column("secondary_insurer_address_line_2")
    private String secondaryInsurerAddressLine2;

    @Column("secondary_insurer_city")
    private String secondaryInsurerCity;

    @Column("secondary_insurer_state")
    private String secondaryInsurerState;

    @Column("secondary_insurer_zip")
    private String secondaryInsurerZip;

    @Column("secondary_insurer_contact_1")
    private String secondaryInsurerContact1;

    @Column("secondary_insurer_fax")
    private String secondaryInsurerFax;

    @Column("tertiary_insurer_address_line_1")
    private String tertiaryInsurerAddressLine1;

    @Column("tertiary_insurer_address_line_2")
    private String tertiaryInsurerAddressLine2;

    @Column("tertiary_insurer_city")
    private String tertiaryInsurerCity;

    @Column("tertiary_insurer_state")
    private String tertiaryInsurerState;

    @Column("tertiary_insurer_zip")
    private String tertiaryInsurerZip;

    @Column("tertiary_insurer_contact_1")
    private String tertiaryInsurerContact1;

    @Column("tertiary_insurer_fax")
    private String tertiaryInsurerFax;

    @Column("primary_insurer_policy_end_date")
    private LocalDate primaryInsurerPolicyEndDate;

    @Column("primary_insurer_contact_name")
    private String primaryInsurerContactName;

    @Column("primary_claim_program")
    private String primaryClaimProgram;

    @Column("secondary_claim_program")
    private String secondaryClaimProgram;

    @Column("tertiary_claim_program")
    private String tertiaryClaimProgram;

    @Column("workers_comp_insured_employer")
    private String workersCompInsuredEmployer;

    @Column("workers_comp_payer_id_number")
    private String workersCompPayerIdNumber;

    @Column("workers_comp_plan_name")
    private String workersCompPlanName;

    @Column("workers_comp_additional_dtls")
    private String workersCompAdditionalDtls;

    @Column("workers_comp_claim_filling_code")
    private String workersCompClaimFillingCode;

    @Column("workers_comp_tpl_code")
    private String workersCompTplCode;

    @Column("workers_comp_tpl_name")
    private String workersCompTplName;

    @Column("workers_comp_property_casualty_agency_claim_no")
    private String workersCompPropertyCasualtyAgencyClaimNo;

    @Column("workers_comp_carrier_id")
    private String workersCompCarrierId;

    @Column("workers_comp_employer_address_line_1")
    private String workersCompEmployerAddressLine1;

    @Column("workers_comp_employer_address_line_2")
    private String workersCompEmployerAddressLine2;

    @Column("workers_comp_employer_city")
    private String workersCompEmployerCity;

    @Column("workers_comp_employer_state")
    private String workersCompEmployerState;

    @Column("workers_comp_employer_country")
    private String workersCompEmployerCountry;

    @Column("workers_comp_employer_zip")
    private String workersCompEmployerZip;

    @Column("workers_comp_employer_contact_no_1")
    private String workersCompEmployerContactNo1;

    @Column("workers_comp_employer_contact_no_2")
    private String workersCompEmployerContactNo2;

    @Column("workers_comp_employer_fax")
    private String workersCompEmployerFax;

    @Column("workers_comp_employer_efax")
    private String workersCompEmployerEfax;

    @Column("workers_comp_employer_email")
    private String workersCompEmployerEmail;

    @Column("workers_comp_relationship")
    private String workersCompRelationship;

    @Column("workers_comp_mode_of_contact")
    private String workersCompModeOfContact;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getSalesOrderInsuranceDetailsId() {
        return this.salesOrderInsuranceDetailsId;
    }

    public SalesOrderInsuranceDetails salesOrderInsuranceDetailsId(Long salesOrderInsuranceDetailsId) {
        this.setSalesOrderInsuranceDetailsId(salesOrderInsuranceDetailsId);
        return this;
    }

    public void setSalesOrderInsuranceDetailsId(Long salesOrderInsuranceDetailsId) {
        this.salesOrderInsuranceDetailsId = salesOrderInsuranceDetailsId;
    }

    public Long getSalesOrderId() {
        return this.salesOrderId;
    }

    public SalesOrderInsuranceDetails salesOrderId(Long salesOrderId) {
        this.setSalesOrderId(salesOrderId);
        return this;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public SalesOrderInsuranceDetails patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPrimaryInsurerId() {
        return this.primaryInsurerId;
    }

    public SalesOrderInsuranceDetails primaryInsurerId(Long primaryInsurerId) {
        this.setPrimaryInsurerId(primaryInsurerId);
        return this;
    }

    public void setPrimaryInsurerId(Long primaryInsurerId) {
        this.primaryInsurerId = primaryInsurerId;
    }

    public String getPrimaryInsurerName() {
        return this.primaryInsurerName;
    }

    public SalesOrderInsuranceDetails primaryInsurerName(String primaryInsurerName) {
        this.setPrimaryInsurerName(primaryInsurerName);
        return this;
    }

    public void setPrimaryInsurerName(String primaryInsurerName) {
        this.primaryInsurerName = primaryInsurerName;
    }

    public String getPrimaryInsurerPolicyNo() {
        return this.primaryInsurerPolicyNo;
    }

    public SalesOrderInsuranceDetails primaryInsurerPolicyNo(String primaryInsurerPolicyNo) {
        this.setPrimaryInsurerPolicyNo(primaryInsurerPolicyNo);
        return this;
    }

    public void setPrimaryInsurerPolicyNo(String primaryInsurerPolicyNo) {
        this.primaryInsurerPolicyNo = primaryInsurerPolicyNo;
    }

    public String getPrimaryInsurerPatientIdNumber() {
        return this.primaryInsurerPatientIdNumber;
    }

    public SalesOrderInsuranceDetails primaryInsurerPatientIdNumber(String primaryInsurerPatientIdNumber) {
        this.setPrimaryInsurerPatientIdNumber(primaryInsurerPatientIdNumber);
        return this;
    }

    public void setPrimaryInsurerPatientIdNumber(String primaryInsurerPatientIdNumber) {
        this.primaryInsurerPatientIdNumber = primaryInsurerPatientIdNumber;
    }

    public LocalDate getPrimaryInsurerEffectiveDate() {
        return this.primaryInsurerEffectiveDate;
    }

    public SalesOrderInsuranceDetails primaryInsurerEffectiveDate(LocalDate primaryInsurerEffectiveDate) {
        this.setPrimaryInsurerEffectiveDate(primaryInsurerEffectiveDate);
        return this;
    }

    public void setPrimaryInsurerEffectiveDate(LocalDate primaryInsurerEffectiveDate) {
        this.primaryInsurerEffectiveDate = primaryInsurerEffectiveDate;
    }

    public String getPrimaryInsurerVerificationStatus() {
        return this.primaryInsurerVerificationStatus;
    }

    public SalesOrderInsuranceDetails primaryInsurerVerificationStatus(String primaryInsurerVerificationStatus) {
        this.setPrimaryInsurerVerificationStatus(primaryInsurerVerificationStatus);
        return this;
    }

    public void setPrimaryInsurerVerificationStatus(String primaryInsurerVerificationStatus) {
        this.primaryInsurerVerificationStatus = primaryInsurerVerificationStatus;
    }

    public LocalDate getPrimaryInsurerVerificationDate() {
        return this.primaryInsurerVerificationDate;
    }

    public SalesOrderInsuranceDetails primaryInsurerVerificationDate(LocalDate primaryInsurerVerificationDate) {
        this.setPrimaryInsurerVerificationDate(primaryInsurerVerificationDate);
        return this;
    }

    public void setPrimaryInsurerVerificationDate(LocalDate primaryInsurerVerificationDate) {
        this.primaryInsurerVerificationDate = primaryInsurerVerificationDate;
    }

    public Long getPrimaryInsurerPayPercentage() {
        return this.primaryInsurerPayPercentage;
    }

    public SalesOrderInsuranceDetails primaryInsurerPayPercentage(Long primaryInsurerPayPercentage) {
        this.setPrimaryInsurerPayPercentage(primaryInsurerPayPercentage);
        return this;
    }

    public void setPrimaryInsurerPayPercentage(Long primaryInsurerPayPercentage) {
        this.primaryInsurerPayPercentage = primaryInsurerPayPercentage;
    }

    public String getPrimaryBox10D() {
        return this.primaryBox10D;
    }

    public SalesOrderInsuranceDetails primaryBox10D(String primaryBox10D) {
        this.setPrimaryBox10D(primaryBox10D);
        return this;
    }

    public void setPrimaryBox10D(String primaryBox10D) {
        this.primaryBox10D = primaryBox10D;
    }

    public String getPrimaryBox19() {
        return this.primaryBox19;
    }

    public SalesOrderInsuranceDetails primaryBox19(String primaryBox19) {
        this.setPrimaryBox19(primaryBox19);
        return this;
    }

    public void setPrimaryBox19(String primaryBox19) {
        this.primaryBox19 = primaryBox19;
    }

    public String getPrimaryBox24Ia() {
        return this.primaryBox24Ia;
    }

    public SalesOrderInsuranceDetails primaryBox24Ia(String primaryBox24Ia) {
        this.setPrimaryBox24Ia(primaryBox24Ia);
        return this;
    }

    public void setPrimaryBox24Ia(String primaryBox24Ia) {
        this.primaryBox24Ia = primaryBox24Ia;
    }

    public String getPrimaryBox24Ja() {
        return this.primaryBox24Ja;
    }

    public SalesOrderInsuranceDetails primaryBox24Ja(String primaryBox24Ja) {
        this.setPrimaryBox24Ja(primaryBox24Ja);
        return this;
    }

    public void setPrimaryBox24Ja(String primaryBox24Ja) {
        this.primaryBox24Ja = primaryBox24Ja;
    }

    public String getPrimaryBox24Jb() {
        return this.primaryBox24Jb;
    }

    public SalesOrderInsuranceDetails primaryBox24Jb(String primaryBox24Jb) {
        this.setPrimaryBox24Jb(primaryBox24Jb);
        return this;
    }

    public void setPrimaryBox24Jb(String primaryBox24Jb) {
        this.primaryBox24Jb = primaryBox24Jb;
    }

    public String getPrimaryIncludeBox24Jbstatus() {
        return this.primaryIncludeBox24Jbstatus;
    }

    public SalesOrderInsuranceDetails primaryIncludeBox24Jbstatus(String primaryIncludeBox24Jbstatus) {
        this.setPrimaryIncludeBox24Jbstatus(primaryIncludeBox24Jbstatus);
        return this;
    }

    public void setPrimaryIncludeBox24Jbstatus(String primaryIncludeBox24Jbstatus) {
        this.primaryIncludeBox24Jbstatus = primaryIncludeBox24Jbstatus;
    }

    public String getPrimaryIncludePayerSalesOrderStatus() {
        return this.primaryIncludePayerSalesOrderStatus;
    }

    public SalesOrderInsuranceDetails primaryIncludePayerSalesOrderStatus(String primaryIncludePayerSalesOrderStatus) {
        this.setPrimaryIncludePayerSalesOrderStatus(primaryIncludePayerSalesOrderStatus);
        return this;
    }

    public void setPrimaryIncludePayerSalesOrderStatus(String primaryIncludePayerSalesOrderStatus) {
        this.primaryIncludePayerSalesOrderStatus = primaryIncludePayerSalesOrderStatus;
    }

    public String getPrimaryWaitForPreviousPayerBeforeBillingStatus() {
        return this.primaryWaitForPreviousPayerBeforeBillingStatus;
    }

    public SalesOrderInsuranceDetails primaryWaitForPreviousPayerBeforeBillingStatus(
        String primaryWaitForPreviousPayerBeforeBillingStatus
    ) {
        this.setPrimaryWaitForPreviousPayerBeforeBillingStatus(primaryWaitForPreviousPayerBeforeBillingStatus);
        return this;
    }

    public void setPrimaryWaitForPreviousPayerBeforeBillingStatus(String primaryWaitForPreviousPayerBeforeBillingStatus) {
        this.primaryWaitForPreviousPayerBeforeBillingStatus = primaryWaitForPreviousPayerBeforeBillingStatus;
    }

    public String getPrimaryPayPercentageStatus() {
        return this.primaryPayPercentageStatus;
    }

    public SalesOrderInsuranceDetails primaryPayPercentageStatus(String primaryPayPercentageStatus) {
        this.setPrimaryPayPercentageStatus(primaryPayPercentageStatus);
        return this;
    }

    public void setPrimaryPayPercentageStatus(String primaryPayPercentageStatus) {
        this.primaryPayPercentageStatus = primaryPayPercentageStatus;
    }

    public Long getSecondaryInsurerId() {
        return this.secondaryInsurerId;
    }

    public SalesOrderInsuranceDetails secondaryInsurerId(Long secondaryInsurerId) {
        this.setSecondaryInsurerId(secondaryInsurerId);
        return this;
    }

    public void setSecondaryInsurerId(Long secondaryInsurerId) {
        this.secondaryInsurerId = secondaryInsurerId;
    }

    public String getSecondaryInsurerName() {
        return this.secondaryInsurerName;
    }

    public SalesOrderInsuranceDetails secondaryInsurerName(String secondaryInsurerName) {
        this.setSecondaryInsurerName(secondaryInsurerName);
        return this;
    }

    public void setSecondaryInsurerName(String secondaryInsurerName) {
        this.secondaryInsurerName = secondaryInsurerName;
    }

    public String getSecondaryInsurerPolicyNo() {
        return this.secondaryInsurerPolicyNo;
    }

    public SalesOrderInsuranceDetails secondaryInsurerPolicyNo(String secondaryInsurerPolicyNo) {
        this.setSecondaryInsurerPolicyNo(secondaryInsurerPolicyNo);
        return this;
    }

    public void setSecondaryInsurerPolicyNo(String secondaryInsurerPolicyNo) {
        this.secondaryInsurerPolicyNo = secondaryInsurerPolicyNo;
    }

    public String getSecondaryInsurerPatientIdNumber() {
        return this.secondaryInsurerPatientIdNumber;
    }

    public SalesOrderInsuranceDetails secondaryInsurerPatientIdNumber(String secondaryInsurerPatientIdNumber) {
        this.setSecondaryInsurerPatientIdNumber(secondaryInsurerPatientIdNumber);
        return this;
    }

    public void setSecondaryInsurerPatientIdNumber(String secondaryInsurerPatientIdNumber) {
        this.secondaryInsurerPatientIdNumber = secondaryInsurerPatientIdNumber;
    }

    public LocalDate getSecondaryInsurerEffectiveDate() {
        return this.secondaryInsurerEffectiveDate;
    }

    public SalesOrderInsuranceDetails secondaryInsurerEffectiveDate(LocalDate secondaryInsurerEffectiveDate) {
        this.setSecondaryInsurerEffectiveDate(secondaryInsurerEffectiveDate);
        return this;
    }

    public void setSecondaryInsurerEffectiveDate(LocalDate secondaryInsurerEffectiveDate) {
        this.secondaryInsurerEffectiveDate = secondaryInsurerEffectiveDate;
    }

    public String getSecondaryInsurerVerificationStatus() {
        return this.secondaryInsurerVerificationStatus;
    }

    public SalesOrderInsuranceDetails secondaryInsurerVerificationStatus(String secondaryInsurerVerificationStatus) {
        this.setSecondaryInsurerVerificationStatus(secondaryInsurerVerificationStatus);
        return this;
    }

    public void setSecondaryInsurerVerificationStatus(String secondaryInsurerVerificationStatus) {
        this.secondaryInsurerVerificationStatus = secondaryInsurerVerificationStatus;
    }

    public LocalDate getSecondaryInsurerVerificationDate() {
        return this.secondaryInsurerVerificationDate;
    }

    public SalesOrderInsuranceDetails secondaryInsurerVerificationDate(LocalDate secondaryInsurerVerificationDate) {
        this.setSecondaryInsurerVerificationDate(secondaryInsurerVerificationDate);
        return this;
    }

    public void setSecondaryInsurerVerificationDate(LocalDate secondaryInsurerVerificationDate) {
        this.secondaryInsurerVerificationDate = secondaryInsurerVerificationDate;
    }

    public Long getSecondaryInsurerPayPercentage() {
        return this.secondaryInsurerPayPercentage;
    }

    public SalesOrderInsuranceDetails secondaryInsurerPayPercentage(Long secondaryInsurerPayPercentage) {
        this.setSecondaryInsurerPayPercentage(secondaryInsurerPayPercentage);
        return this;
    }

    public void setSecondaryInsurerPayPercentage(Long secondaryInsurerPayPercentage) {
        this.secondaryInsurerPayPercentage = secondaryInsurerPayPercentage;
    }

    public String getSecondaryBox10D() {
        return this.secondaryBox10D;
    }

    public SalesOrderInsuranceDetails secondaryBox10D(String secondaryBox10D) {
        this.setSecondaryBox10D(secondaryBox10D);
        return this;
    }

    public void setSecondaryBox10D(String secondaryBox10D) {
        this.secondaryBox10D = secondaryBox10D;
    }

    public String getSecondaryBox19() {
        return this.secondaryBox19;
    }

    public SalesOrderInsuranceDetails secondaryBox19(String secondaryBox19) {
        this.setSecondaryBox19(secondaryBox19);
        return this;
    }

    public void setSecondaryBox19(String secondaryBox19) {
        this.secondaryBox19 = secondaryBox19;
    }

    public String getSecondaryBox24Ia() {
        return this.secondaryBox24Ia;
    }

    public SalesOrderInsuranceDetails secondaryBox24Ia(String secondaryBox24Ia) {
        this.setSecondaryBox24Ia(secondaryBox24Ia);
        return this;
    }

    public void setSecondaryBox24Ia(String secondaryBox24Ia) {
        this.secondaryBox24Ia = secondaryBox24Ia;
    }

    public String getSecondaryBox24Ja() {
        return this.secondaryBox24Ja;
    }

    public SalesOrderInsuranceDetails secondaryBox24Ja(String secondaryBox24Ja) {
        this.setSecondaryBox24Ja(secondaryBox24Ja);
        return this;
    }

    public void setSecondaryBox24Ja(String secondaryBox24Ja) {
        this.secondaryBox24Ja = secondaryBox24Ja;
    }

    public String getSecondaryBox24Jb() {
        return this.secondaryBox24Jb;
    }

    public SalesOrderInsuranceDetails secondaryBox24Jb(String secondaryBox24Jb) {
        this.setSecondaryBox24Jb(secondaryBox24Jb);
        return this;
    }

    public void setSecondaryBox24Jb(String secondaryBox24Jb) {
        this.secondaryBox24Jb = secondaryBox24Jb;
    }

    public String getSecondaryIncludeBox24JbStatus() {
        return this.secondaryIncludeBox24JbStatus;
    }

    public SalesOrderInsuranceDetails secondaryIncludeBox24JbStatus(String secondaryIncludeBox24JbStatus) {
        this.setSecondaryIncludeBox24JbStatus(secondaryIncludeBox24JbStatus);
        return this;
    }

    public void setSecondaryIncludeBox24JbStatus(String secondaryIncludeBox24JbStatus) {
        this.secondaryIncludeBox24JbStatus = secondaryIncludeBox24JbStatus;
    }

    public String getSecondaryIncludePayerSalesOrderStatus() {
        return this.secondaryIncludePayerSalesOrderStatus;
    }

    public SalesOrderInsuranceDetails secondaryIncludePayerSalesOrderStatus(String secondaryIncludePayerSalesOrderStatus) {
        this.setSecondaryIncludePayerSalesOrderStatus(secondaryIncludePayerSalesOrderStatus);
        return this;
    }

    public void setSecondaryIncludePayerSalesOrderStatus(String secondaryIncludePayerSalesOrderStatus) {
        this.secondaryIncludePayerSalesOrderStatus = secondaryIncludePayerSalesOrderStatus;
    }

    public String getSecondaryWaitPreviousPayerBefrBillingStatus() {
        return this.secondaryWaitPreviousPayerBefrBillingStatus;
    }

    public SalesOrderInsuranceDetails secondaryWaitPreviousPayerBefrBillingStatus(String secondaryWaitPreviousPayerBefrBillingStatus) {
        this.setSecondaryWaitPreviousPayerBefrBillingStatus(secondaryWaitPreviousPayerBefrBillingStatus);
        return this;
    }

    public void setSecondaryWaitPreviousPayerBefrBillingStatus(String secondaryWaitPreviousPayerBefrBillingStatus) {
        this.secondaryWaitPreviousPayerBefrBillingStatus = secondaryWaitPreviousPayerBefrBillingStatus;
    }

    public String getSecondaryPayPercentageStatus() {
        return this.secondaryPayPercentageStatus;
    }

    public SalesOrderInsuranceDetails secondaryPayPercentageStatus(String secondaryPayPercentageStatus) {
        this.setSecondaryPayPercentageStatus(secondaryPayPercentageStatus);
        return this;
    }

    public void setSecondaryPayPercentageStatus(String secondaryPayPercentageStatus) {
        this.secondaryPayPercentageStatus = secondaryPayPercentageStatus;
    }

    public Long getTertiaryInsurerId() {
        return this.tertiaryInsurerId;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerId(Long tertiaryInsurerId) {
        this.setTertiaryInsurerId(tertiaryInsurerId);
        return this;
    }

    public void setTertiaryInsurerId(Long tertiaryInsurerId) {
        this.tertiaryInsurerId = tertiaryInsurerId;
    }

    public String getTertiaryInsurerName() {
        return this.tertiaryInsurerName;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerName(String tertiaryInsurerName) {
        this.setTertiaryInsurerName(tertiaryInsurerName);
        return this;
    }

    public void setTertiaryInsurerName(String tertiaryInsurerName) {
        this.tertiaryInsurerName = tertiaryInsurerName;
    }

    public String getTertiaryInsurerPolicyno() {
        return this.tertiaryInsurerPolicyno;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerPolicyno(String tertiaryInsurerPolicyno) {
        this.setTertiaryInsurerPolicyno(tertiaryInsurerPolicyno);
        return this;
    }

    public void setTertiaryInsurerPolicyno(String tertiaryInsurerPolicyno) {
        this.tertiaryInsurerPolicyno = tertiaryInsurerPolicyno;
    }

    public String getTertiaryInsurerPatientIdNumber() {
        return this.tertiaryInsurerPatientIdNumber;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerPatientIdNumber(String tertiaryInsurerPatientIdNumber) {
        this.setTertiaryInsurerPatientIdNumber(tertiaryInsurerPatientIdNumber);
        return this;
    }

    public void setTertiaryInsurerPatientIdNumber(String tertiaryInsurerPatientIdNumber) {
        this.tertiaryInsurerPatientIdNumber = tertiaryInsurerPatientIdNumber;
    }

    public LocalDate getTertiaryInsurerEffectiveDate() {
        return this.tertiaryInsurerEffectiveDate;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerEffectiveDate(LocalDate tertiaryInsurerEffectiveDate) {
        this.setTertiaryInsurerEffectiveDate(tertiaryInsurerEffectiveDate);
        return this;
    }

    public void setTertiaryInsurerEffectiveDate(LocalDate tertiaryInsurerEffectiveDate) {
        this.tertiaryInsurerEffectiveDate = tertiaryInsurerEffectiveDate;
    }

    public String getTertiaryInsurerVerificationStatus() {
        return this.tertiaryInsurerVerificationStatus;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerVerificationStatus(String tertiaryInsurerVerificationStatus) {
        this.setTertiaryInsurerVerificationStatus(tertiaryInsurerVerificationStatus);
        return this;
    }

    public void setTertiaryInsurerVerificationStatus(String tertiaryInsurerVerificationStatus) {
        this.tertiaryInsurerVerificationStatus = tertiaryInsurerVerificationStatus;
    }

    public LocalDate getTertiaryInsurerVerificationDate() {
        return this.tertiaryInsurerVerificationDate;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerVerificationDate(LocalDate tertiaryInsurerVerificationDate) {
        this.setTertiaryInsurerVerificationDate(tertiaryInsurerVerificationDate);
        return this;
    }

    public void setTertiaryInsurerVerificationDate(LocalDate tertiaryInsurerVerificationDate) {
        this.tertiaryInsurerVerificationDate = tertiaryInsurerVerificationDate;
    }

    public Long getTertiaryInsurerPayPercentage() {
        return this.tertiaryInsurerPayPercentage;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerPayPercentage(Long tertiaryInsurerPayPercentage) {
        this.setTertiaryInsurerPayPercentage(tertiaryInsurerPayPercentage);
        return this;
    }

    public void setTertiaryInsurerPayPercentage(Long tertiaryInsurerPayPercentage) {
        this.tertiaryInsurerPayPercentage = tertiaryInsurerPayPercentage;
    }

    public String getTertiaryBox10D() {
        return this.tertiaryBox10D;
    }

    public SalesOrderInsuranceDetails tertiaryBox10D(String tertiaryBox10D) {
        this.setTertiaryBox10D(tertiaryBox10D);
        return this;
    }

    public void setTertiaryBox10D(String tertiaryBox10D) {
        this.tertiaryBox10D = tertiaryBox10D;
    }

    public String getTertiaryBox19() {
        return this.tertiaryBox19;
    }

    public SalesOrderInsuranceDetails tertiaryBox19(String tertiaryBox19) {
        this.setTertiaryBox19(tertiaryBox19);
        return this;
    }

    public void setTertiaryBox19(String tertiaryBox19) {
        this.tertiaryBox19 = tertiaryBox19;
    }

    public String getTertiaryBox24Ia() {
        return this.tertiaryBox24Ia;
    }

    public SalesOrderInsuranceDetails tertiaryBox24Ia(String tertiaryBox24Ia) {
        this.setTertiaryBox24Ia(tertiaryBox24Ia);
        return this;
    }

    public void setTertiaryBox24Ia(String tertiaryBox24Ia) {
        this.tertiaryBox24Ia = tertiaryBox24Ia;
    }

    public String getTertiaryBox24Ja() {
        return this.tertiaryBox24Ja;
    }

    public SalesOrderInsuranceDetails tertiaryBox24Ja(String tertiaryBox24Ja) {
        this.setTertiaryBox24Ja(tertiaryBox24Ja);
        return this;
    }

    public void setTertiaryBox24Ja(String tertiaryBox24Ja) {
        this.tertiaryBox24Ja = tertiaryBox24Ja;
    }

    public String getTertiaryBox24Jb() {
        return this.tertiaryBox24Jb;
    }

    public SalesOrderInsuranceDetails tertiaryBox24Jb(String tertiaryBox24Jb) {
        this.setTertiaryBox24Jb(tertiaryBox24Jb);
        return this;
    }

    public void setTertiaryBox24Jb(String tertiaryBox24Jb) {
        this.tertiaryBox24Jb = tertiaryBox24Jb;
    }

    public String getTertiaryIncludeBox24JbStatus() {
        return this.tertiaryIncludeBox24JbStatus;
    }

    public SalesOrderInsuranceDetails tertiaryIncludeBox24JbStatus(String tertiaryIncludeBox24JbStatus) {
        this.setTertiaryIncludeBox24JbStatus(tertiaryIncludeBox24JbStatus);
        return this;
    }

    public void setTertiaryIncludeBox24JbStatus(String tertiaryIncludeBox24JbStatus) {
        this.tertiaryIncludeBox24JbStatus = tertiaryIncludeBox24JbStatus;
    }

    public String getTertiaryIncludePayerInSalesOrderStatus() {
        return this.tertiaryIncludePayerInSalesOrderStatus;
    }

    public SalesOrderInsuranceDetails tertiaryIncludePayerInSalesOrderStatus(String tertiaryIncludePayerInSalesOrderStatus) {
        this.setTertiaryIncludePayerInSalesOrderStatus(tertiaryIncludePayerInSalesOrderStatus);
        return this;
    }

    public void setTertiaryIncludePayerInSalesOrderStatus(String tertiaryIncludePayerInSalesOrderStatus) {
        this.tertiaryIncludePayerInSalesOrderStatus = tertiaryIncludePayerInSalesOrderStatus;
    }

    public String getTertiaryWaitPreviousPayerBeforeBillingStatus() {
        return this.tertiaryWaitPreviousPayerBeforeBillingStatus;
    }

    public SalesOrderInsuranceDetails tertiaryWaitPreviousPayerBeforeBillingStatus(String tertiaryWaitPreviousPayerBeforeBillingStatus) {
        this.setTertiaryWaitPreviousPayerBeforeBillingStatus(tertiaryWaitPreviousPayerBeforeBillingStatus);
        return this;
    }

    public void setTertiaryWaitPreviousPayerBeforeBillingStatus(String tertiaryWaitPreviousPayerBeforeBillingStatus) {
        this.tertiaryWaitPreviousPayerBeforeBillingStatus = tertiaryWaitPreviousPayerBeforeBillingStatus;
    }

    public String getTertiaryPayPercentageStatus() {
        return this.tertiaryPayPercentageStatus;
    }

    public SalesOrderInsuranceDetails tertiaryPayPercentageStatus(String tertiaryPayPercentageStatus) {
        this.setTertiaryPayPercentageStatus(tertiaryPayPercentageStatus);
        return this;
    }

    public void setTertiaryPayPercentageStatus(String tertiaryPayPercentageStatus) {
        this.tertiaryPayPercentageStatus = tertiaryPayPercentageStatus;
    }

    public String getInsuranceVerificationStatus() {
        return this.insuranceVerificationStatus;
    }

    public SalesOrderInsuranceDetails insuranceVerificationStatus(String insuranceVerificationStatus) {
        this.setInsuranceVerificationStatus(insuranceVerificationStatus);
        return this;
    }

    public void setInsuranceVerificationStatus(String insuranceVerificationStatus) {
        this.insuranceVerificationStatus = insuranceVerificationStatus;
    }

    public String getCoverageVerificationStatus() {
        return this.coverageVerificationStatus;
    }

    public SalesOrderInsuranceDetails coverageVerificationStatus(String coverageVerificationStatus) {
        this.setCoverageVerificationStatus(coverageVerificationStatus);
        return this;
    }

    public void setCoverageVerificationStatus(String coverageVerificationStatus) {
        this.coverageVerificationStatus = coverageVerificationStatus;
    }

    public String getExcludeFromEligibilityCheckStatus() {
        return this.excludeFromEligibilityCheckStatus;
    }

    public SalesOrderInsuranceDetails excludeFromEligibilityCheckStatus(String excludeFromEligibilityCheckStatus) {
        this.setExcludeFromEligibilityCheckStatus(excludeFromEligibilityCheckStatus);
        return this;
    }

    public void setExcludeFromEligibilityCheckStatus(String excludeFromEligibilityCheckStatus) {
        this.excludeFromEligibilityCheckStatus = excludeFromEligibilityCheckStatus;
    }

    public Long getPatientPayPercentage() {
        return this.patientPayPercentage;
    }

    public SalesOrderInsuranceDetails patientPayPercentage(Long patientPayPercentage) {
        this.setPatientPayPercentage(patientPayPercentage);
        return this;
    }

    public void setPatientPayPercentage(Long patientPayPercentage) {
        this.patientPayPercentage = patientPayPercentage;
    }

    public String getPatientIncludeThisPayorInSalesOrderStatus() {
        return this.patientIncludeThisPayorInSalesOrderStatus;
    }

    public SalesOrderInsuranceDetails patientIncludeThisPayorInSalesOrderStatus(String patientIncludeThisPayorInSalesOrderStatus) {
        this.setPatientIncludeThisPayorInSalesOrderStatus(patientIncludeThisPayorInSalesOrderStatus);
        return this;
    }

    public void setPatientIncludeThisPayorInSalesOrderStatus(String patientIncludeThisPayorInSalesOrderStatus) {
        this.patientIncludeThisPayorInSalesOrderStatus = patientIncludeThisPayorInSalesOrderStatus;
    }

    public String getPatientWaitForPreviousPayerBeforeBillingStatus() {
        return this.patientWaitForPreviousPayerBeforeBillingStatus;
    }

    public SalesOrderInsuranceDetails patientWaitForPreviousPayerBeforeBillingStatus(
        String patientWaitForPreviousPayerBeforeBillingStatus
    ) {
        this.setPatientWaitForPreviousPayerBeforeBillingStatus(patientWaitForPreviousPayerBeforeBillingStatus);
        return this;
    }

    public void setPatientWaitForPreviousPayerBeforeBillingStatus(String patientWaitForPreviousPayerBeforeBillingStatus) {
        this.patientWaitForPreviousPayerBeforeBillingStatus = patientWaitForPreviousPayerBeforeBillingStatus;
    }

    public LocalDate getWorkersCompDateOfOnset() {
        return this.workersCompDateOfOnset;
    }

    public SalesOrderInsuranceDetails workersCompDateOfOnset(LocalDate workersCompDateOfOnset) {
        this.setWorkersCompDateOfOnset(workersCompDateOfOnset);
        return this;
    }

    public void setWorkersCompDateOfOnset(LocalDate workersCompDateOfOnset) {
        this.workersCompDateOfOnset = workersCompDateOfOnset;
    }

    public String getWorkersCompInjuryRelatedEmploymentStatus() {
        return this.workersCompInjuryRelatedEmploymentStatus;
    }

    public SalesOrderInsuranceDetails workersCompInjuryRelatedEmploymentStatus(String workersCompInjuryRelatedEmploymentStatus) {
        this.setWorkersCompInjuryRelatedEmploymentStatus(workersCompInjuryRelatedEmploymentStatus);
        return this;
    }

    public void setWorkersCompInjuryRelatedEmploymentStatus(String workersCompInjuryRelatedEmploymentStatus) {
        this.workersCompInjuryRelatedEmploymentStatus = workersCompInjuryRelatedEmploymentStatus;
    }

    public String getWorkersCompInjuryRelatedAutoAccidentStatus() {
        return this.workersCompInjuryRelatedAutoAccidentStatus;
    }

    public SalesOrderInsuranceDetails workersCompInjuryRelatedAutoAccidentStatus(String workersCompInjuryRelatedAutoAccidentStatus) {
        this.setWorkersCompInjuryRelatedAutoAccidentStatus(workersCompInjuryRelatedAutoAccidentStatus);
        return this;
    }

    public void setWorkersCompInjuryRelatedAutoAccidentStatus(String workersCompInjuryRelatedAutoAccidentStatus) {
        this.workersCompInjuryRelatedAutoAccidentStatus = workersCompInjuryRelatedAutoAccidentStatus;
    }

    public String getWorkersCompAutoAccidentStateCode() {
        return this.workersCompAutoAccidentStateCode;
    }

    public SalesOrderInsuranceDetails workersCompAutoAccidentStateCode(String workersCompAutoAccidentStateCode) {
        this.setWorkersCompAutoAccidentStateCode(workersCompAutoAccidentStateCode);
        return this;
    }

    public void setWorkersCompAutoAccidentStateCode(String workersCompAutoAccidentStateCode) {
        this.workersCompAutoAccidentStateCode = workersCompAutoAccidentStateCode;
    }

    public String getWorkersCompInjuryRelatedToOtherAccidentStatus() {
        return this.workersCompInjuryRelatedToOtherAccidentStatus;
    }

    public SalesOrderInsuranceDetails workersCompInjuryRelatedToOtherAccidentStatus(String workersCompInjuryRelatedToOtherAccidentStatus) {
        this.setWorkersCompInjuryRelatedToOtherAccidentStatus(workersCompInjuryRelatedToOtherAccidentStatus);
        return this;
    }

    public void setWorkersCompInjuryRelatedToOtherAccidentStatus(String workersCompInjuryRelatedToOtherAccidentStatus) {
        this.workersCompInjuryRelatedToOtherAccidentStatus = workersCompInjuryRelatedToOtherAccidentStatus;
    }

    public String getEclaimsAttachmentStatus() {
        return this.eclaimsAttachmentStatus;
    }

    public SalesOrderInsuranceDetails eclaimsAttachmentStatus(String eclaimsAttachmentStatus) {
        this.setEclaimsAttachmentStatus(eclaimsAttachmentStatus);
        return this;
    }

    public void setEclaimsAttachmentStatus(String eclaimsAttachmentStatus) {
        this.eclaimsAttachmentStatus = eclaimsAttachmentStatus;
    }

    public Long getAttachmentNumber() {
        return this.attachmentNumber;
    }

    public SalesOrderInsuranceDetails attachmentNumber(Long attachmentNumber) {
        this.setAttachmentNumber(attachmentNumber);
        return this;
    }

    public void setAttachmentNumber(Long attachmentNumber) {
        this.attachmentNumber = attachmentNumber;
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    public SalesOrderInsuranceDetails typeCode(String typeCode) {
        this.setTypeCode(typeCode);
        return this;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTransactionCode() {
        return this.transactionCode;
    }

    public SalesOrderInsuranceDetails transactionCode(String transactionCode) {
        this.setTransactionCode(transactionCode);
        return this;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getClaimsNoteType() {
        return this.claimsNoteType;
    }

    public SalesOrderInsuranceDetails claimsNoteType(String claimsNoteType) {
        this.setClaimsNoteType(claimsNoteType);
        return this;
    }

    public void setClaimsNoteType(String claimsNoteType) {
        this.claimsNoteType = claimsNoteType;
    }

    public String getClaimsNote() {
        return this.claimsNote;
    }

    public SalesOrderInsuranceDetails claimsNote(String claimsNote) {
        this.setClaimsNote(claimsNote);
        return this;
    }

    public void setClaimsNote(String claimsNote) {
        this.claimsNote = claimsNote;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public SalesOrderInsuranceDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public SalesOrderInsuranceDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return this.status;
    }

    public SalesOrderInsuranceDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public SalesOrderInsuranceDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public SalesOrderInsuranceDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getSalesOrderNo() {
        return this.salesOrderNo;
    }

    public SalesOrderInsuranceDetails salesOrderNo(String salesOrderNo) {
        this.setSalesOrderNo(salesOrderNo);
        return this;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public SalesOrderInsuranceDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public SalesOrderInsuranceDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getPrimaryInsuranceGroupId() {
        return this.primaryInsuranceGroupId;
    }

    public SalesOrderInsuranceDetails primaryInsuranceGroupId(Long primaryInsuranceGroupId) {
        this.setPrimaryInsuranceGroupId(primaryInsuranceGroupId);
        return this;
    }

    public void setPrimaryInsuranceGroupId(Long primaryInsuranceGroupId) {
        this.primaryInsuranceGroupId = primaryInsuranceGroupId;
    }

    public String getPrimaryInsuranceGroupName() {
        return this.primaryInsuranceGroupName;
    }

    public SalesOrderInsuranceDetails primaryInsuranceGroupName(String primaryInsuranceGroupName) {
        this.setPrimaryInsuranceGroupName(primaryInsuranceGroupName);
        return this;
    }

    public void setPrimaryInsuranceGroupName(String primaryInsuranceGroupName) {
        this.primaryInsuranceGroupName = primaryInsuranceGroupName;
    }

    public Long getSecondaryInsuranceGroupId() {
        return this.secondaryInsuranceGroupId;
    }

    public SalesOrderInsuranceDetails secondaryInsuranceGroupId(Long secondaryInsuranceGroupId) {
        this.setSecondaryInsuranceGroupId(secondaryInsuranceGroupId);
        return this;
    }

    public void setSecondaryInsuranceGroupId(Long secondaryInsuranceGroupId) {
        this.secondaryInsuranceGroupId = secondaryInsuranceGroupId;
    }

    public String getSecondaryInsuranceGroupName() {
        return this.secondaryInsuranceGroupName;
    }

    public SalesOrderInsuranceDetails secondaryInsuranceGroupName(String secondaryInsuranceGroupName) {
        this.setSecondaryInsuranceGroupName(secondaryInsuranceGroupName);
        return this;
    }

    public void setSecondaryInsuranceGroupName(String secondaryInsuranceGroupName) {
        this.secondaryInsuranceGroupName = secondaryInsuranceGroupName;
    }

    public Long getTertiaryInsuranceGroupId() {
        return this.tertiaryInsuranceGroupId;
    }

    public SalesOrderInsuranceDetails tertiaryInsuranceGroupId(Long tertiaryInsuranceGroupId) {
        this.setTertiaryInsuranceGroupId(tertiaryInsuranceGroupId);
        return this;
    }

    public void setTertiaryInsuranceGroupId(Long tertiaryInsuranceGroupId) {
        this.tertiaryInsuranceGroupId = tertiaryInsuranceGroupId;
    }

    public String getTertiaryInsuranceGroupName() {
        return this.tertiaryInsuranceGroupName;
    }

    public SalesOrderInsuranceDetails tertiaryInsuranceGroupName(String tertiaryInsuranceGroupName) {
        this.setTertiaryInsuranceGroupName(tertiaryInsuranceGroupName);
        return this;
    }

    public void setTertiaryInsuranceGroupName(String tertiaryInsuranceGroupName) {
        this.tertiaryInsuranceGroupName = tertiaryInsuranceGroupName;
    }

    public Long getPrimaryInsurancePlanId() {
        return this.primaryInsurancePlanId;
    }

    public SalesOrderInsuranceDetails primaryInsurancePlanId(Long primaryInsurancePlanId) {
        this.setPrimaryInsurancePlanId(primaryInsurancePlanId);
        return this;
    }

    public void setPrimaryInsurancePlanId(Long primaryInsurancePlanId) {
        this.primaryInsurancePlanId = primaryInsurancePlanId;
    }

    public String getPrimaryInsurancePlanType() {
        return this.primaryInsurancePlanType;
    }

    public SalesOrderInsuranceDetails primaryInsurancePlanType(String primaryInsurancePlanType) {
        this.setPrimaryInsurancePlanType(primaryInsurancePlanType);
        return this;
    }

    public void setPrimaryInsurancePlanType(String primaryInsurancePlanType) {
        this.primaryInsurancePlanType = primaryInsurancePlanType;
    }

    public Long getSecondaryInsurancePlanId() {
        return this.secondaryInsurancePlanId;
    }

    public SalesOrderInsuranceDetails secondaryInsurancePlanId(Long secondaryInsurancePlanId) {
        this.setSecondaryInsurancePlanId(secondaryInsurancePlanId);
        return this;
    }

    public void setSecondaryInsurancePlanId(Long secondaryInsurancePlanId) {
        this.secondaryInsurancePlanId = secondaryInsurancePlanId;
    }

    public String getSecondaryInsurancePlanType() {
        return this.secondaryInsurancePlanType;
    }

    public SalesOrderInsuranceDetails secondaryInsurancePlanType(String secondaryInsurancePlanType) {
        this.setSecondaryInsurancePlanType(secondaryInsurancePlanType);
        return this;
    }

    public void setSecondaryInsurancePlanType(String secondaryInsurancePlanType) {
        this.secondaryInsurancePlanType = secondaryInsurancePlanType;
    }

    public Long getTertiaryInsurancePlanId() {
        return this.tertiaryInsurancePlanId;
    }

    public SalesOrderInsuranceDetails tertiaryInsurancePlanId(Long tertiaryInsurancePlanId) {
        this.setTertiaryInsurancePlanId(tertiaryInsurancePlanId);
        return this;
    }

    public void setTertiaryInsurancePlanId(Long tertiaryInsurancePlanId) {
        this.tertiaryInsurancePlanId = tertiaryInsurancePlanId;
    }

    public String getTertiaryInsurancePlanType() {
        return this.tertiaryInsurancePlanType;
    }

    public SalesOrderInsuranceDetails tertiaryInsurancePlanType(String tertiaryInsurancePlanType) {
        this.setTertiaryInsurancePlanType(tertiaryInsurancePlanType);
        return this;
    }

    public void setTertiaryInsurancePlanType(String tertiaryInsurancePlanType) {
        this.tertiaryInsurancePlanType = tertiaryInsurancePlanType;
    }

    public UUID getSalesOrderInsuranceDetailsUuid() {
        return this.salesOrderInsuranceDetailsUuid;
    }

    public SalesOrderInsuranceDetails salesOrderInsuranceDetailsUuid(UUID salesOrderInsuranceDetailsUuid) {
        this.setSalesOrderInsuranceDetailsUuid(salesOrderInsuranceDetailsUuid);
        return this;
    }

    public void setSalesOrderInsuranceDetailsUuid(UUID salesOrderInsuranceDetailsUuid) {
        this.salesOrderInsuranceDetailsUuid = salesOrderInsuranceDetailsUuid;
    }

    public String getPrimaryInsurancePayerId() {
        return this.primaryInsurancePayerId;
    }

    public SalesOrderInsuranceDetails primaryInsurancePayerId(String primaryInsurancePayerId) {
        this.setPrimaryInsurancePayerId(primaryInsurancePayerId);
        return this;
    }

    public void setPrimaryInsurancePayerId(String primaryInsurancePayerId) {
        this.primaryInsurancePayerId = primaryInsurancePayerId;
    }

    public String getSecondaryInsurancePayerId() {
        return this.secondaryInsurancePayerId;
    }

    public SalesOrderInsuranceDetails secondaryInsurancePayerId(String secondaryInsurancePayerId) {
        this.setSecondaryInsurancePayerId(secondaryInsurancePayerId);
        return this;
    }

    public void setSecondaryInsurancePayerId(String secondaryInsurancePayerId) {
        this.secondaryInsurancePayerId = secondaryInsurancePayerId;
    }

    public String getTertiaryInsurancePayerId() {
        return this.tertiaryInsurancePayerId;
    }

    public SalesOrderInsuranceDetails tertiaryInsurancePayerId(String tertiaryInsurancePayerId) {
        this.setTertiaryInsurancePayerId(tertiaryInsurancePayerId);
        return this;
    }

    public void setTertiaryInsurancePayerId(String tertiaryInsurancePayerId) {
        this.tertiaryInsurancePayerId = tertiaryInsurancePayerId;
    }

    public String getPrimaryInsuranceIndicatorCode() {
        return this.primaryInsuranceIndicatorCode;
    }

    public SalesOrderInsuranceDetails primaryInsuranceIndicatorCode(String primaryInsuranceIndicatorCode) {
        this.setPrimaryInsuranceIndicatorCode(primaryInsuranceIndicatorCode);
        return this;
    }

    public void setPrimaryInsuranceIndicatorCode(String primaryInsuranceIndicatorCode) {
        this.primaryInsuranceIndicatorCode = primaryInsuranceIndicatorCode;
    }

    public String getSecondaryInsuranceIndicatorCode() {
        return this.secondaryInsuranceIndicatorCode;
    }

    public SalesOrderInsuranceDetails secondaryInsuranceIndicatorCode(String secondaryInsuranceIndicatorCode) {
        this.setSecondaryInsuranceIndicatorCode(secondaryInsuranceIndicatorCode);
        return this;
    }

    public void setSecondaryInsuranceIndicatorCode(String secondaryInsuranceIndicatorCode) {
        this.secondaryInsuranceIndicatorCode = secondaryInsuranceIndicatorCode;
    }

    public String getTertiaryInsuranceIndicatorCode() {
        return this.tertiaryInsuranceIndicatorCode;
    }

    public SalesOrderInsuranceDetails tertiaryInsuranceIndicatorCode(String tertiaryInsuranceIndicatorCode) {
        this.setTertiaryInsuranceIndicatorCode(tertiaryInsuranceIndicatorCode);
        return this;
    }

    public void setTertiaryInsuranceIndicatorCode(String tertiaryInsuranceIndicatorCode) {
        this.tertiaryInsuranceIndicatorCode = tertiaryInsuranceIndicatorCode;
    }

    public Long getPriceTableId() {
        return this.priceTableId;
    }

    public SalesOrderInsuranceDetails priceTableId(Long priceTableId) {
        this.setPriceTableId(priceTableId);
        return this;
    }

    public void setPriceTableId(Long priceTableId) {
        this.priceTableId = priceTableId;
    }

    public String getPriceTableName() {
        return this.priceTableName;
    }

    public SalesOrderInsuranceDetails priceTableName(String priceTableName) {
        this.setPriceTableName(priceTableName);
        return this;
    }

    public void setPriceTableName(String priceTableName) {
        this.priceTableName = priceTableName;
    }

    public String getPrimaryInsurerAddressLine1() {
        return this.primaryInsurerAddressLine1;
    }

    public SalesOrderInsuranceDetails primaryInsurerAddressLine1(String primaryInsurerAddressLine1) {
        this.setPrimaryInsurerAddressLine1(primaryInsurerAddressLine1);
        return this;
    }

    public void setPrimaryInsurerAddressLine1(String primaryInsurerAddressLine1) {
        this.primaryInsurerAddressLine1 = primaryInsurerAddressLine1;
    }

    public String getPrimaryInsurerAddressLine2() {
        return this.primaryInsurerAddressLine2;
    }

    public SalesOrderInsuranceDetails primaryInsurerAddressLine2(String primaryInsurerAddressLine2) {
        this.setPrimaryInsurerAddressLine2(primaryInsurerAddressLine2);
        return this;
    }

    public void setPrimaryInsurerAddressLine2(String primaryInsurerAddressLine2) {
        this.primaryInsurerAddressLine2 = primaryInsurerAddressLine2;
    }

    public String getPrimaryInsurerCity() {
        return this.primaryInsurerCity;
    }

    public SalesOrderInsuranceDetails primaryInsurerCity(String primaryInsurerCity) {
        this.setPrimaryInsurerCity(primaryInsurerCity);
        return this;
    }

    public void setPrimaryInsurerCity(String primaryInsurerCity) {
        this.primaryInsurerCity = primaryInsurerCity;
    }

    public String getPrimaryInsurerState() {
        return this.primaryInsurerState;
    }

    public SalesOrderInsuranceDetails primaryInsurerState(String primaryInsurerState) {
        this.setPrimaryInsurerState(primaryInsurerState);
        return this;
    }

    public void setPrimaryInsurerState(String primaryInsurerState) {
        this.primaryInsurerState = primaryInsurerState;
    }

    public String getPrimaryInsurerZip() {
        return this.primaryInsurerZip;
    }

    public SalesOrderInsuranceDetails primaryInsurerZip(String primaryInsurerZip) {
        this.setPrimaryInsurerZip(primaryInsurerZip);
        return this;
    }

    public void setPrimaryInsurerZip(String primaryInsurerZip) {
        this.primaryInsurerZip = primaryInsurerZip;
    }

    public String getPrimaryInsurerContact1() {
        return this.primaryInsurerContact1;
    }

    public SalesOrderInsuranceDetails primaryInsurerContact1(String primaryInsurerContact1) {
        this.setPrimaryInsurerContact1(primaryInsurerContact1);
        return this;
    }

    public void setPrimaryInsurerContact1(String primaryInsurerContact1) {
        this.primaryInsurerContact1 = primaryInsurerContact1;
    }

    public String getPrimaryInsurerFax() {
        return this.primaryInsurerFax;
    }

    public SalesOrderInsuranceDetails primaryInsurerFax(String primaryInsurerFax) {
        this.setPrimaryInsurerFax(primaryInsurerFax);
        return this;
    }

    public void setPrimaryInsurerFax(String primaryInsurerFax) {
        this.primaryInsurerFax = primaryInsurerFax;
    }

    public String getSecondaryInsurerAddressLine1() {
        return this.secondaryInsurerAddressLine1;
    }

    public SalesOrderInsuranceDetails secondaryInsurerAddressLine1(String secondaryInsurerAddressLine1) {
        this.setSecondaryInsurerAddressLine1(secondaryInsurerAddressLine1);
        return this;
    }

    public void setSecondaryInsurerAddressLine1(String secondaryInsurerAddressLine1) {
        this.secondaryInsurerAddressLine1 = secondaryInsurerAddressLine1;
    }

    public String getSecondaryInsurerAddressLine2() {
        return this.secondaryInsurerAddressLine2;
    }

    public SalesOrderInsuranceDetails secondaryInsurerAddressLine2(String secondaryInsurerAddressLine2) {
        this.setSecondaryInsurerAddressLine2(secondaryInsurerAddressLine2);
        return this;
    }

    public void setSecondaryInsurerAddressLine2(String secondaryInsurerAddressLine2) {
        this.secondaryInsurerAddressLine2 = secondaryInsurerAddressLine2;
    }

    public String getSecondaryInsurerCity() {
        return this.secondaryInsurerCity;
    }

    public SalesOrderInsuranceDetails secondaryInsurerCity(String secondaryInsurerCity) {
        this.setSecondaryInsurerCity(secondaryInsurerCity);
        return this;
    }

    public void setSecondaryInsurerCity(String secondaryInsurerCity) {
        this.secondaryInsurerCity = secondaryInsurerCity;
    }

    public String getSecondaryInsurerState() {
        return this.secondaryInsurerState;
    }

    public SalesOrderInsuranceDetails secondaryInsurerState(String secondaryInsurerState) {
        this.setSecondaryInsurerState(secondaryInsurerState);
        return this;
    }

    public void setSecondaryInsurerState(String secondaryInsurerState) {
        this.secondaryInsurerState = secondaryInsurerState;
    }

    public String getSecondaryInsurerZip() {
        return this.secondaryInsurerZip;
    }

    public SalesOrderInsuranceDetails secondaryInsurerZip(String secondaryInsurerZip) {
        this.setSecondaryInsurerZip(secondaryInsurerZip);
        return this;
    }

    public void setSecondaryInsurerZip(String secondaryInsurerZip) {
        this.secondaryInsurerZip = secondaryInsurerZip;
    }

    public String getSecondaryInsurerContact1() {
        return this.secondaryInsurerContact1;
    }

    public SalesOrderInsuranceDetails secondaryInsurerContact1(String secondaryInsurerContact1) {
        this.setSecondaryInsurerContact1(secondaryInsurerContact1);
        return this;
    }

    public void setSecondaryInsurerContact1(String secondaryInsurerContact1) {
        this.secondaryInsurerContact1 = secondaryInsurerContact1;
    }

    public String getSecondaryInsurerFax() {
        return this.secondaryInsurerFax;
    }

    public SalesOrderInsuranceDetails secondaryInsurerFax(String secondaryInsurerFax) {
        this.setSecondaryInsurerFax(secondaryInsurerFax);
        return this;
    }

    public void setSecondaryInsurerFax(String secondaryInsurerFax) {
        this.secondaryInsurerFax = secondaryInsurerFax;
    }

    public String getTertiaryInsurerAddressLine1() {
        return this.tertiaryInsurerAddressLine1;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerAddressLine1(String tertiaryInsurerAddressLine1) {
        this.setTertiaryInsurerAddressLine1(tertiaryInsurerAddressLine1);
        return this;
    }

    public void setTertiaryInsurerAddressLine1(String tertiaryInsurerAddressLine1) {
        this.tertiaryInsurerAddressLine1 = tertiaryInsurerAddressLine1;
    }

    public String getTertiaryInsurerAddressLine2() {
        return this.tertiaryInsurerAddressLine2;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerAddressLine2(String tertiaryInsurerAddressLine2) {
        this.setTertiaryInsurerAddressLine2(tertiaryInsurerAddressLine2);
        return this;
    }

    public void setTertiaryInsurerAddressLine2(String tertiaryInsurerAddressLine2) {
        this.tertiaryInsurerAddressLine2 = tertiaryInsurerAddressLine2;
    }

    public String getTertiaryInsurerCity() {
        return this.tertiaryInsurerCity;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerCity(String tertiaryInsurerCity) {
        this.setTertiaryInsurerCity(tertiaryInsurerCity);
        return this;
    }

    public void setTertiaryInsurerCity(String tertiaryInsurerCity) {
        this.tertiaryInsurerCity = tertiaryInsurerCity;
    }

    public String getTertiaryInsurerState() {
        return this.tertiaryInsurerState;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerState(String tertiaryInsurerState) {
        this.setTertiaryInsurerState(tertiaryInsurerState);
        return this;
    }

    public void setTertiaryInsurerState(String tertiaryInsurerState) {
        this.tertiaryInsurerState = tertiaryInsurerState;
    }

    public String getTertiaryInsurerZip() {
        return this.tertiaryInsurerZip;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerZip(String tertiaryInsurerZip) {
        this.setTertiaryInsurerZip(tertiaryInsurerZip);
        return this;
    }

    public void setTertiaryInsurerZip(String tertiaryInsurerZip) {
        this.tertiaryInsurerZip = tertiaryInsurerZip;
    }

    public String getTertiaryInsurerContact1() {
        return this.tertiaryInsurerContact1;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerContact1(String tertiaryInsurerContact1) {
        this.setTertiaryInsurerContact1(tertiaryInsurerContact1);
        return this;
    }

    public void setTertiaryInsurerContact1(String tertiaryInsurerContact1) {
        this.tertiaryInsurerContact1 = tertiaryInsurerContact1;
    }

    public String getTertiaryInsurerFax() {
        return this.tertiaryInsurerFax;
    }

    public SalesOrderInsuranceDetails tertiaryInsurerFax(String tertiaryInsurerFax) {
        this.setTertiaryInsurerFax(tertiaryInsurerFax);
        return this;
    }

    public void setTertiaryInsurerFax(String tertiaryInsurerFax) {
        this.tertiaryInsurerFax = tertiaryInsurerFax;
    }

    public LocalDate getPrimaryInsurerPolicyEndDate() {
        return this.primaryInsurerPolicyEndDate;
    }

    public SalesOrderInsuranceDetails primaryInsurerPolicyEndDate(LocalDate primaryInsurerPolicyEndDate) {
        this.setPrimaryInsurerPolicyEndDate(primaryInsurerPolicyEndDate);
        return this;
    }

    public void setPrimaryInsurerPolicyEndDate(LocalDate primaryInsurerPolicyEndDate) {
        this.primaryInsurerPolicyEndDate = primaryInsurerPolicyEndDate;
    }

    public String getPrimaryInsurerContactName() {
        return this.primaryInsurerContactName;
    }

    public SalesOrderInsuranceDetails primaryInsurerContactName(String primaryInsurerContactName) {
        this.setPrimaryInsurerContactName(primaryInsurerContactName);
        return this;
    }

    public void setPrimaryInsurerContactName(String primaryInsurerContactName) {
        this.primaryInsurerContactName = primaryInsurerContactName;
    }

    public String getPrimaryClaimProgram() {
        return this.primaryClaimProgram;
    }

    public SalesOrderInsuranceDetails primaryClaimProgram(String primaryClaimProgram) {
        this.setPrimaryClaimProgram(primaryClaimProgram);
        return this;
    }

    public void setPrimaryClaimProgram(String primaryClaimProgram) {
        this.primaryClaimProgram = primaryClaimProgram;
    }

    public String getSecondaryClaimProgram() {
        return this.secondaryClaimProgram;
    }

    public SalesOrderInsuranceDetails secondaryClaimProgram(String secondaryClaimProgram) {
        this.setSecondaryClaimProgram(secondaryClaimProgram);
        return this;
    }

    public void setSecondaryClaimProgram(String secondaryClaimProgram) {
        this.secondaryClaimProgram = secondaryClaimProgram;
    }

    public String getTertiaryClaimProgram() {
        return this.tertiaryClaimProgram;
    }

    public SalesOrderInsuranceDetails tertiaryClaimProgram(String tertiaryClaimProgram) {
        this.setTertiaryClaimProgram(tertiaryClaimProgram);
        return this;
    }

    public void setTertiaryClaimProgram(String tertiaryClaimProgram) {
        this.tertiaryClaimProgram = tertiaryClaimProgram;
    }

    public String getWorkersCompInsuredEmployer() {
        return this.workersCompInsuredEmployer;
    }

    public SalesOrderInsuranceDetails workersCompInsuredEmployer(String workersCompInsuredEmployer) {
        this.setWorkersCompInsuredEmployer(workersCompInsuredEmployer);
        return this;
    }

    public void setWorkersCompInsuredEmployer(String workersCompInsuredEmployer) {
        this.workersCompInsuredEmployer = workersCompInsuredEmployer;
    }

    public String getWorkersCompPayerIdNumber() {
        return this.workersCompPayerIdNumber;
    }

    public SalesOrderInsuranceDetails workersCompPayerIdNumber(String workersCompPayerIdNumber) {
        this.setWorkersCompPayerIdNumber(workersCompPayerIdNumber);
        return this;
    }

    public void setWorkersCompPayerIdNumber(String workersCompPayerIdNumber) {
        this.workersCompPayerIdNumber = workersCompPayerIdNumber;
    }

    public String getWorkersCompPlanName() {
        return this.workersCompPlanName;
    }

    public SalesOrderInsuranceDetails workersCompPlanName(String workersCompPlanName) {
        this.setWorkersCompPlanName(workersCompPlanName);
        return this;
    }

    public void setWorkersCompPlanName(String workersCompPlanName) {
        this.workersCompPlanName = workersCompPlanName;
    }

    public String getWorkersCompAdditionalDtls() {
        return this.workersCompAdditionalDtls;
    }

    public SalesOrderInsuranceDetails workersCompAdditionalDtls(String workersCompAdditionalDtls) {
        this.setWorkersCompAdditionalDtls(workersCompAdditionalDtls);
        return this;
    }

    public void setWorkersCompAdditionalDtls(String workersCompAdditionalDtls) {
        this.workersCompAdditionalDtls = workersCompAdditionalDtls;
    }

    public String getWorkersCompClaimFillingCode() {
        return this.workersCompClaimFillingCode;
    }

    public SalesOrderInsuranceDetails workersCompClaimFillingCode(String workersCompClaimFillingCode) {
        this.setWorkersCompClaimFillingCode(workersCompClaimFillingCode);
        return this;
    }

    public void setWorkersCompClaimFillingCode(String workersCompClaimFillingCode) {
        this.workersCompClaimFillingCode = workersCompClaimFillingCode;
    }

    public String getWorkersCompTplCode() {
        return this.workersCompTplCode;
    }

    public SalesOrderInsuranceDetails workersCompTplCode(String workersCompTplCode) {
        this.setWorkersCompTplCode(workersCompTplCode);
        return this;
    }

    public void setWorkersCompTplCode(String workersCompTplCode) {
        this.workersCompTplCode = workersCompTplCode;
    }

    public String getWorkersCompTplName() {
        return this.workersCompTplName;
    }

    public SalesOrderInsuranceDetails workersCompTplName(String workersCompTplName) {
        this.setWorkersCompTplName(workersCompTplName);
        return this;
    }

    public void setWorkersCompTplName(String workersCompTplName) {
        this.workersCompTplName = workersCompTplName;
    }

    public String getWorkersCompPropertyCasualtyAgencyClaimNo() {
        return this.workersCompPropertyCasualtyAgencyClaimNo;
    }

    public SalesOrderInsuranceDetails workersCompPropertyCasualtyAgencyClaimNo(String workersCompPropertyCasualtyAgencyClaimNo) {
        this.setWorkersCompPropertyCasualtyAgencyClaimNo(workersCompPropertyCasualtyAgencyClaimNo);
        return this;
    }

    public void setWorkersCompPropertyCasualtyAgencyClaimNo(String workersCompPropertyCasualtyAgencyClaimNo) {
        this.workersCompPropertyCasualtyAgencyClaimNo = workersCompPropertyCasualtyAgencyClaimNo;
    }

    public String getWorkersCompCarrierId() {
        return this.workersCompCarrierId;
    }

    public SalesOrderInsuranceDetails workersCompCarrierId(String workersCompCarrierId) {
        this.setWorkersCompCarrierId(workersCompCarrierId);
        return this;
    }

    public void setWorkersCompCarrierId(String workersCompCarrierId) {
        this.workersCompCarrierId = workersCompCarrierId;
    }

    public String getWorkersCompEmployerAddressLine1() {
        return this.workersCompEmployerAddressLine1;
    }

    public SalesOrderInsuranceDetails workersCompEmployerAddressLine1(String workersCompEmployerAddressLine1) {
        this.setWorkersCompEmployerAddressLine1(workersCompEmployerAddressLine1);
        return this;
    }

    public void setWorkersCompEmployerAddressLine1(String workersCompEmployerAddressLine1) {
        this.workersCompEmployerAddressLine1 = workersCompEmployerAddressLine1;
    }

    public String getWorkersCompEmployerAddressLine2() {
        return this.workersCompEmployerAddressLine2;
    }

    public SalesOrderInsuranceDetails workersCompEmployerAddressLine2(String workersCompEmployerAddressLine2) {
        this.setWorkersCompEmployerAddressLine2(workersCompEmployerAddressLine2);
        return this;
    }

    public void setWorkersCompEmployerAddressLine2(String workersCompEmployerAddressLine2) {
        this.workersCompEmployerAddressLine2 = workersCompEmployerAddressLine2;
    }

    public String getWorkersCompEmployerCity() {
        return this.workersCompEmployerCity;
    }

    public SalesOrderInsuranceDetails workersCompEmployerCity(String workersCompEmployerCity) {
        this.setWorkersCompEmployerCity(workersCompEmployerCity);
        return this;
    }

    public void setWorkersCompEmployerCity(String workersCompEmployerCity) {
        this.workersCompEmployerCity = workersCompEmployerCity;
    }

    public String getWorkersCompEmployerState() {
        return this.workersCompEmployerState;
    }

    public SalesOrderInsuranceDetails workersCompEmployerState(String workersCompEmployerState) {
        this.setWorkersCompEmployerState(workersCompEmployerState);
        return this;
    }

    public void setWorkersCompEmployerState(String workersCompEmployerState) {
        this.workersCompEmployerState = workersCompEmployerState;
    }

    public String getWorkersCompEmployerCountry() {
        return this.workersCompEmployerCountry;
    }

    public SalesOrderInsuranceDetails workersCompEmployerCountry(String workersCompEmployerCountry) {
        this.setWorkersCompEmployerCountry(workersCompEmployerCountry);
        return this;
    }

    public void setWorkersCompEmployerCountry(String workersCompEmployerCountry) {
        this.workersCompEmployerCountry = workersCompEmployerCountry;
    }

    public String getWorkersCompEmployerZip() {
        return this.workersCompEmployerZip;
    }

    public SalesOrderInsuranceDetails workersCompEmployerZip(String workersCompEmployerZip) {
        this.setWorkersCompEmployerZip(workersCompEmployerZip);
        return this;
    }

    public void setWorkersCompEmployerZip(String workersCompEmployerZip) {
        this.workersCompEmployerZip = workersCompEmployerZip;
    }

    public String getWorkersCompEmployerContactNo1() {
        return this.workersCompEmployerContactNo1;
    }

    public SalesOrderInsuranceDetails workersCompEmployerContactNo1(String workersCompEmployerContactNo1) {
        this.setWorkersCompEmployerContactNo1(workersCompEmployerContactNo1);
        return this;
    }

    public void setWorkersCompEmployerContactNo1(String workersCompEmployerContactNo1) {
        this.workersCompEmployerContactNo1 = workersCompEmployerContactNo1;
    }

    public String getWorkersCompEmployerContactNo2() {
        return this.workersCompEmployerContactNo2;
    }

    public SalesOrderInsuranceDetails workersCompEmployerContactNo2(String workersCompEmployerContactNo2) {
        this.setWorkersCompEmployerContactNo2(workersCompEmployerContactNo2);
        return this;
    }

    public void setWorkersCompEmployerContactNo2(String workersCompEmployerContactNo2) {
        this.workersCompEmployerContactNo2 = workersCompEmployerContactNo2;
    }

    public String getWorkersCompEmployerFax() {
        return this.workersCompEmployerFax;
    }

    public SalesOrderInsuranceDetails workersCompEmployerFax(String workersCompEmployerFax) {
        this.setWorkersCompEmployerFax(workersCompEmployerFax);
        return this;
    }

    public void setWorkersCompEmployerFax(String workersCompEmployerFax) {
        this.workersCompEmployerFax = workersCompEmployerFax;
    }

    public String getWorkersCompEmployerEfax() {
        return this.workersCompEmployerEfax;
    }

    public SalesOrderInsuranceDetails workersCompEmployerEfax(String workersCompEmployerEfax) {
        this.setWorkersCompEmployerEfax(workersCompEmployerEfax);
        return this;
    }

    public void setWorkersCompEmployerEfax(String workersCompEmployerEfax) {
        this.workersCompEmployerEfax = workersCompEmployerEfax;
    }

    public String getWorkersCompEmployerEmail() {
        return this.workersCompEmployerEmail;
    }

    public SalesOrderInsuranceDetails workersCompEmployerEmail(String workersCompEmployerEmail) {
        this.setWorkersCompEmployerEmail(workersCompEmployerEmail);
        return this;
    }

    public void setWorkersCompEmployerEmail(String workersCompEmployerEmail) {
        this.workersCompEmployerEmail = workersCompEmployerEmail;
    }

    public String getWorkersCompRelationship() {
        return this.workersCompRelationship;
    }

    public SalesOrderInsuranceDetails workersCompRelationship(String workersCompRelationship) {
        this.setWorkersCompRelationship(workersCompRelationship);
        return this;
    }

    public void setWorkersCompRelationship(String workersCompRelationship) {
        this.workersCompRelationship = workersCompRelationship;
    }

    public String getWorkersCompModeOfContact() {
        return this.workersCompModeOfContact;
    }

    public SalesOrderInsuranceDetails workersCompModeOfContact(String workersCompModeOfContact) {
        this.setWorkersCompModeOfContact(workersCompModeOfContact);
        return this;
    }

    public void setWorkersCompModeOfContact(String workersCompModeOfContact) {
        this.workersCompModeOfContact = workersCompModeOfContact;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesOrderInsuranceDetails)) {
            return false;
        }
        return (
            salesOrderInsuranceDetailsId != null &&
            salesOrderInsuranceDetailsId.equals(((SalesOrderInsuranceDetails) o).salesOrderInsuranceDetailsId)
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
        return "SalesOrderInsuranceDetails{" +
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
