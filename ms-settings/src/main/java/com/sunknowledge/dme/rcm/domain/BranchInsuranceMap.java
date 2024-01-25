package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BranchInsuranceMap.
 */
@Entity
@Table(name = "t_branch_insurance_map")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BranchInsuranceMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "branch_insurance_map_id")
    private Long branchInsuranceMapId;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "insurance_id")
    private Long insuranceId;

    @Column(name = "insurance_name")
    private String insuranceName;

    @Column(name = "insurance_id_no")
    private String insuranceIdNo;

    @Column(name = "insurance_state_name")
    private String insuranceStateName;

    @Column(name = "price_table_id")
    private Long priceTableId;

    @Column(name = "price_table_name")
    private String priceTableName;

    @Column(name = "branch_npi")
    private String branchNpi;

    @Column(name = "branch_ptan")
    private String branchPtan;

    @Column(name = "esubmitter_payor_id")
    private String esubmitterPayorId;

    @Column(name = "branch_taxonomy_code")
    private String branchTaxonomyCode;

    @Column(name = "billing_provider_override_company_name")
    private String billingProviderOverrideCompanyName;

    @Column(name = "billing_provider_override_tax_id_ein")
    private String billingProviderOverrideTaxIdEin;

    @Column(name = "billing_provider_override_address_line_1")
    private String billingProviderOverrideAddressLine1;

    @Column(name = "billing_provider_override_address_line_2")
    private String billingProviderOverrideAddressLine2;

    @Column(name = "billing_provider_override_city")
    private String billingProviderOverrideCity;

    @Column(name = "billing_provider_override_state")
    private String billingProviderOverrideState;

    @Column(name = "billing_provider_override_zip")
    private String billingProviderOverrideZip;

    @Column(name = "billing_provider_override_contact_1")
    private String billingProviderOverrideContact1;

    @Column(name = "billing_provider_override_contact_2")
    private String billingProviderOverrideContact2;

    @Column(name = "billing_provider_override_fax")
    private String billingProviderOverrideFax;

    @Column(name = "billing_provider_override_email")
    private String billingProviderOverrideEmail;

    @Column(name = "pay_to_provider_company_name")
    private String payToProviderCompanyName;

    @Column(name = "pay_to_provider_tax_id_ein")
    private String payToProviderTaxIdEin;

    @Column(name = "pay_to_provider_address_line_1")
    private String payToProviderAddressLine1;

    @Column(name = "pay_to_provider_address_line_2")
    private String payToProviderAddressLine2;

    @Column(name = "pay_to_provider_city")
    private String payToProviderCity;

    @Column(name = "pay_to_provider_state")
    private String payToProviderState;

    @Column(name = "pay_to_provider_zip")
    private String payToProviderZip;

    @Column(name = "pay_to_provider_contact_1")
    private String payToProviderContact1;

    @Column(name = "pay_to_provider_contact_2")
    private String payToProviderContact2;

    @Column(name = "pay_to_provider_fax")
    private String payToProviderFax;

    @Column(name = "pay_to_provider_email")
    private String payToProviderEmail;

    @Column(name = "submitter_name")
    private String submitterName;

    @Column(name = "submitter_contact_1")
    private String submitterContact1;

    @Column(name = "submitter_contact_2")
    private String submitterContact2;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "branch_insurance_map_uuid")
    private UUID branchInsuranceMapUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getBranchInsuranceMapId() {
        return this.branchInsuranceMapId;
    }

    public BranchInsuranceMap branchInsuranceMapId(Long branchInsuranceMapId) {
        this.setBranchInsuranceMapId(branchInsuranceMapId);
        return this;
    }

    public void setBranchInsuranceMapId(Long branchInsuranceMapId) {
        this.branchInsuranceMapId = branchInsuranceMapId;
    }

    public Long getBranchId() {
        return this.branchId;
    }

    public BranchInsuranceMap branchId(Long branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public BranchInsuranceMap branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getInsuranceId() {
        return this.insuranceId;
    }

    public BranchInsuranceMap insuranceId(Long insuranceId) {
        this.setInsuranceId(insuranceId);
        return this;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return this.insuranceName;
    }

    public BranchInsuranceMap insuranceName(String insuranceName) {
        this.setInsuranceName(insuranceName);
        return this;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsuranceIdNo() {
        return this.insuranceIdNo;
    }

    public BranchInsuranceMap insuranceIdNo(String insuranceIdNo) {
        this.setInsuranceIdNo(insuranceIdNo);
        return this;
    }

    public void setInsuranceIdNo(String insuranceIdNo) {
        this.insuranceIdNo = insuranceIdNo;
    }

    public String getInsuranceStateName() {
        return this.insuranceStateName;
    }

    public BranchInsuranceMap insuranceStateName(String insuranceStateName) {
        this.setInsuranceStateName(insuranceStateName);
        return this;
    }

    public void setInsuranceStateName(String insuranceStateName) {
        this.insuranceStateName = insuranceStateName;
    }

    public Long getPriceTableId() {
        return this.priceTableId;
    }

    public BranchInsuranceMap priceTableId(Long priceTableId) {
        this.setPriceTableId(priceTableId);
        return this;
    }

    public void setPriceTableId(Long priceTableId) {
        this.priceTableId = priceTableId;
    }

    public String getPriceTableName() {
        return this.priceTableName;
    }

    public BranchInsuranceMap priceTableName(String priceTableName) {
        this.setPriceTableName(priceTableName);
        return this;
    }

    public void setPriceTableName(String priceTableName) {
        this.priceTableName = priceTableName;
    }

    public String getBranchNpi() {
        return this.branchNpi;
    }

    public BranchInsuranceMap branchNpi(String branchNpi) {
        this.setBranchNpi(branchNpi);
        return this;
    }

    public void setBranchNpi(String branchNpi) {
        this.branchNpi = branchNpi;
    }

    public String getBranchPtan() {
        return this.branchPtan;
    }

    public BranchInsuranceMap branchPtan(String branchPtan) {
        this.setBranchPtan(branchPtan);
        return this;
    }

    public void setBranchPtan(String branchPtan) {
        this.branchPtan = branchPtan;
    }

    public String getEsubmitterPayorId() {
        return this.esubmitterPayorId;
    }

    public BranchInsuranceMap esubmitterPayorId(String esubmitterPayorId) {
        this.setEsubmitterPayorId(esubmitterPayorId);
        return this;
    }

    public void setEsubmitterPayorId(String esubmitterPayorId) {
        this.esubmitterPayorId = esubmitterPayorId;
    }

    public String getBranchTaxonomyCode() {
        return this.branchTaxonomyCode;
    }

    public BranchInsuranceMap branchTaxonomyCode(String branchTaxonomyCode) {
        this.setBranchTaxonomyCode(branchTaxonomyCode);
        return this;
    }

    public void setBranchTaxonomyCode(String branchTaxonomyCode) {
        this.branchTaxonomyCode = branchTaxonomyCode;
    }

    public String getBillingProviderOverrideCompanyName() {
        return this.billingProviderOverrideCompanyName;
    }

    public BranchInsuranceMap billingProviderOverrideCompanyName(String billingProviderOverrideCompanyName) {
        this.setBillingProviderOverrideCompanyName(billingProviderOverrideCompanyName);
        return this;
    }

    public void setBillingProviderOverrideCompanyName(String billingProviderOverrideCompanyName) {
        this.billingProviderOverrideCompanyName = billingProviderOverrideCompanyName;
    }

    public String getBillingProviderOverrideTaxIdEin() {
        return this.billingProviderOverrideTaxIdEin;
    }

    public BranchInsuranceMap billingProviderOverrideTaxIdEin(String billingProviderOverrideTaxIdEin) {
        this.setBillingProviderOverrideTaxIdEin(billingProviderOverrideTaxIdEin);
        return this;
    }

    public void setBillingProviderOverrideTaxIdEin(String billingProviderOverrideTaxIdEin) {
        this.billingProviderOverrideTaxIdEin = billingProviderOverrideTaxIdEin;
    }

    public String getBillingProviderOverrideAddressLine1() {
        return this.billingProviderOverrideAddressLine1;
    }

    public BranchInsuranceMap billingProviderOverrideAddressLine1(String billingProviderOverrideAddressLine1) {
        this.setBillingProviderOverrideAddressLine1(billingProviderOverrideAddressLine1);
        return this;
    }

    public void setBillingProviderOverrideAddressLine1(String billingProviderOverrideAddressLine1) {
        this.billingProviderOverrideAddressLine1 = billingProviderOverrideAddressLine1;
    }

    public String getBillingProviderOverrideAddressLine2() {
        return this.billingProviderOverrideAddressLine2;
    }

    public BranchInsuranceMap billingProviderOverrideAddressLine2(String billingProviderOverrideAddressLine2) {
        this.setBillingProviderOverrideAddressLine2(billingProviderOverrideAddressLine2);
        return this;
    }

    public void setBillingProviderOverrideAddressLine2(String billingProviderOverrideAddressLine2) {
        this.billingProviderOverrideAddressLine2 = billingProviderOverrideAddressLine2;
    }

    public String getBillingProviderOverrideCity() {
        return this.billingProviderOverrideCity;
    }

    public BranchInsuranceMap billingProviderOverrideCity(String billingProviderOverrideCity) {
        this.setBillingProviderOverrideCity(billingProviderOverrideCity);
        return this;
    }

    public void setBillingProviderOverrideCity(String billingProviderOverrideCity) {
        this.billingProviderOverrideCity = billingProviderOverrideCity;
    }

    public String getBillingProviderOverrideState() {
        return this.billingProviderOverrideState;
    }

    public BranchInsuranceMap billingProviderOverrideState(String billingProviderOverrideState) {
        this.setBillingProviderOverrideState(billingProviderOverrideState);
        return this;
    }

    public void setBillingProviderOverrideState(String billingProviderOverrideState) {
        this.billingProviderOverrideState = billingProviderOverrideState;
    }

    public String getBillingProviderOverrideZip() {
        return this.billingProviderOverrideZip;
    }

    public BranchInsuranceMap billingProviderOverrideZip(String billingProviderOverrideZip) {
        this.setBillingProviderOverrideZip(billingProviderOverrideZip);
        return this;
    }

    public void setBillingProviderOverrideZip(String billingProviderOverrideZip) {
        this.billingProviderOverrideZip = billingProviderOverrideZip;
    }

    public String getBillingProviderOverrideContact1() {
        return this.billingProviderOverrideContact1;
    }

    public BranchInsuranceMap billingProviderOverrideContact1(String billingProviderOverrideContact1) {
        this.setBillingProviderOverrideContact1(billingProviderOverrideContact1);
        return this;
    }

    public void setBillingProviderOverrideContact1(String billingProviderOverrideContact1) {
        this.billingProviderOverrideContact1 = billingProviderOverrideContact1;
    }

    public String getBillingProviderOverrideContact2() {
        return this.billingProviderOverrideContact2;
    }

    public BranchInsuranceMap billingProviderOverrideContact2(String billingProviderOverrideContact2) {
        this.setBillingProviderOverrideContact2(billingProviderOverrideContact2);
        return this;
    }

    public void setBillingProviderOverrideContact2(String billingProviderOverrideContact2) {
        this.billingProviderOverrideContact2 = billingProviderOverrideContact2;
    }

    public String getBillingProviderOverrideFax() {
        return this.billingProviderOverrideFax;
    }

    public BranchInsuranceMap billingProviderOverrideFax(String billingProviderOverrideFax) {
        this.setBillingProviderOverrideFax(billingProviderOverrideFax);
        return this;
    }

    public void setBillingProviderOverrideFax(String billingProviderOverrideFax) {
        this.billingProviderOverrideFax = billingProviderOverrideFax;
    }

    public String getBillingProviderOverrideEmail() {
        return this.billingProviderOverrideEmail;
    }

    public BranchInsuranceMap billingProviderOverrideEmail(String billingProviderOverrideEmail) {
        this.setBillingProviderOverrideEmail(billingProviderOverrideEmail);
        return this;
    }

    public void setBillingProviderOverrideEmail(String billingProviderOverrideEmail) {
        this.billingProviderOverrideEmail = billingProviderOverrideEmail;
    }

    public String getPayToProviderCompanyName() {
        return this.payToProviderCompanyName;
    }

    public BranchInsuranceMap payToProviderCompanyName(String payToProviderCompanyName) {
        this.setPayToProviderCompanyName(payToProviderCompanyName);
        return this;
    }

    public void setPayToProviderCompanyName(String payToProviderCompanyName) {
        this.payToProviderCompanyName = payToProviderCompanyName;
    }

    public String getPayToProviderTaxIdEin() {
        return this.payToProviderTaxIdEin;
    }

    public BranchInsuranceMap payToProviderTaxIdEin(String payToProviderTaxIdEin) {
        this.setPayToProviderTaxIdEin(payToProviderTaxIdEin);
        return this;
    }

    public void setPayToProviderTaxIdEin(String payToProviderTaxIdEin) {
        this.payToProviderTaxIdEin = payToProviderTaxIdEin;
    }

    public String getPayToProviderAddressLine1() {
        return this.payToProviderAddressLine1;
    }

    public BranchInsuranceMap payToProviderAddressLine1(String payToProviderAddressLine1) {
        this.setPayToProviderAddressLine1(payToProviderAddressLine1);
        return this;
    }

    public void setPayToProviderAddressLine1(String payToProviderAddressLine1) {
        this.payToProviderAddressLine1 = payToProviderAddressLine1;
    }

    public String getPayToProviderAddressLine2() {
        return this.payToProviderAddressLine2;
    }

    public BranchInsuranceMap payToProviderAddressLine2(String payToProviderAddressLine2) {
        this.setPayToProviderAddressLine2(payToProviderAddressLine2);
        return this;
    }

    public void setPayToProviderAddressLine2(String payToProviderAddressLine2) {
        this.payToProviderAddressLine2 = payToProviderAddressLine2;
    }

    public String getPayToProviderCity() {
        return this.payToProviderCity;
    }

    public BranchInsuranceMap payToProviderCity(String payToProviderCity) {
        this.setPayToProviderCity(payToProviderCity);
        return this;
    }

    public void setPayToProviderCity(String payToProviderCity) {
        this.payToProviderCity = payToProviderCity;
    }

    public String getPayToProviderState() {
        return this.payToProviderState;
    }

    public BranchInsuranceMap payToProviderState(String payToProviderState) {
        this.setPayToProviderState(payToProviderState);
        return this;
    }

    public void setPayToProviderState(String payToProviderState) {
        this.payToProviderState = payToProviderState;
    }

    public String getPayToProviderZip() {
        return this.payToProviderZip;
    }

    public BranchInsuranceMap payToProviderZip(String payToProviderZip) {
        this.setPayToProviderZip(payToProviderZip);
        return this;
    }

    public void setPayToProviderZip(String payToProviderZip) {
        this.payToProviderZip = payToProviderZip;
    }

    public String getPayToProviderContact1() {
        return this.payToProviderContact1;
    }

    public BranchInsuranceMap payToProviderContact1(String payToProviderContact1) {
        this.setPayToProviderContact1(payToProviderContact1);
        return this;
    }

    public void setPayToProviderContact1(String payToProviderContact1) {
        this.payToProviderContact1 = payToProviderContact1;
    }

    public String getPayToProviderContact2() {
        return this.payToProviderContact2;
    }

    public BranchInsuranceMap payToProviderContact2(String payToProviderContact2) {
        this.setPayToProviderContact2(payToProviderContact2);
        return this;
    }

    public void setPayToProviderContact2(String payToProviderContact2) {
        this.payToProviderContact2 = payToProviderContact2;
    }

    public String getPayToProviderFax() {
        return this.payToProviderFax;
    }

    public BranchInsuranceMap payToProviderFax(String payToProviderFax) {
        this.setPayToProviderFax(payToProviderFax);
        return this;
    }

    public void setPayToProviderFax(String payToProviderFax) {
        this.payToProviderFax = payToProviderFax;
    }

    public String getPayToProviderEmail() {
        return this.payToProviderEmail;
    }

    public BranchInsuranceMap payToProviderEmail(String payToProviderEmail) {
        this.setPayToProviderEmail(payToProviderEmail);
        return this;
    }

    public void setPayToProviderEmail(String payToProviderEmail) {
        this.payToProviderEmail = payToProviderEmail;
    }

    public String getSubmitterName() {
        return this.submitterName;
    }

    public BranchInsuranceMap submitterName(String submitterName) {
        this.setSubmitterName(submitterName);
        return this;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
    }

    public String getSubmitterContact1() {
        return this.submitterContact1;
    }

    public BranchInsuranceMap submitterContact1(String submitterContact1) {
        this.setSubmitterContact1(submitterContact1);
        return this;
    }

    public void setSubmitterContact1(String submitterContact1) {
        this.submitterContact1 = submitterContact1;
    }

    public String getSubmitterContact2() {
        return this.submitterContact2;
    }

    public BranchInsuranceMap submitterContact2(String submitterContact2) {
        this.setSubmitterContact2(submitterContact2);
        return this;
    }

    public void setSubmitterContact2(String submitterContact2) {
        this.submitterContact2 = submitterContact2;
    }

    public String getStatus() {
        return this.status;
    }

    public BranchInsuranceMap status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public BranchInsuranceMap createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public BranchInsuranceMap createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public BranchInsuranceMap createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public BranchInsuranceMap updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public BranchInsuranceMap updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public BranchInsuranceMap updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getBranchInsuranceMapUuid() {
        return this.branchInsuranceMapUuid;
    }

    public BranchInsuranceMap branchInsuranceMapUuid(UUID branchInsuranceMapUuid) {
        this.setBranchInsuranceMapUuid(branchInsuranceMapUuid);
        return this;
    }

    public void setBranchInsuranceMapUuid(UUID branchInsuranceMapUuid) {
        this.branchInsuranceMapUuid = branchInsuranceMapUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchInsuranceMap)) {
            return false;
        }
        return branchInsuranceMapId != null && branchInsuranceMapId.equals(((BranchInsuranceMap) o).branchInsuranceMapId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchInsuranceMap{" +
            "branchInsuranceMapId=" + getBranchInsuranceMapId() +
            ", branchId=" + getBranchId() +
            ", branchName='" + getBranchName() + "'" +
            ", insuranceId=" + getInsuranceId() +
            ", insuranceName='" + getInsuranceName() + "'" +
            ", insuranceIdNo='" + getInsuranceIdNo() + "'" +
            ", insuranceStateName='" + getInsuranceStateName() + "'" +
            ", priceTableId=" + getPriceTableId() +
            ", priceTableName='" + getPriceTableName() + "'" +
            ", branchNpi='" + getBranchNpi() + "'" +
            ", branchPtan='" + getBranchPtan() + "'" +
            ", esubmitterPayorId='" + getEsubmitterPayorId() + "'" +
            ", branchTaxonomyCode='" + getBranchTaxonomyCode() + "'" +
            ", billingProviderOverrideCompanyName='" + getBillingProviderOverrideCompanyName() + "'" +
            ", billingProviderOverrideTaxIdEin='" + getBillingProviderOverrideTaxIdEin() + "'" +
            ", billingProviderOverrideAddressLine1='" + getBillingProviderOverrideAddressLine1() + "'" +
            ", billingProviderOverrideAddressLine2='" + getBillingProviderOverrideAddressLine2() + "'" +
            ", billingProviderOverrideCity='" + getBillingProviderOverrideCity() + "'" +
            ", billingProviderOverrideState='" + getBillingProviderOverrideState() + "'" +
            ", billingProviderOverrideZip='" + getBillingProviderOverrideZip() + "'" +
            ", billingProviderOverrideContact1='" + getBillingProviderOverrideContact1() + "'" +
            ", billingProviderOverrideContact2='" + getBillingProviderOverrideContact2() + "'" +
            ", billingProviderOverrideFax='" + getBillingProviderOverrideFax() + "'" +
            ", billingProviderOverrideEmail='" + getBillingProviderOverrideEmail() + "'" +
            ", payToProviderCompanyName='" + getPayToProviderCompanyName() + "'" +
            ", payToProviderTaxIdEin='" + getPayToProviderTaxIdEin() + "'" +
            ", payToProviderAddressLine1='" + getPayToProviderAddressLine1() + "'" +
            ", payToProviderAddressLine2='" + getPayToProviderAddressLine2() + "'" +
            ", payToProviderCity='" + getPayToProviderCity() + "'" +
            ", payToProviderState='" + getPayToProviderState() + "'" +
            ", payToProviderZip='" + getPayToProviderZip() + "'" +
            ", payToProviderContact1='" + getPayToProviderContact1() + "'" +
            ", payToProviderContact2='" + getPayToProviderContact2() + "'" +
            ", payToProviderFax='" + getPayToProviderFax() + "'" +
            ", payToProviderEmail='" + getPayToProviderEmail() + "'" +
            ", submitterName='" + getSubmitterName() + "'" +
            ", submitterContact1='" + getSubmitterContact1() + "'" +
            ", submitterContact2='" + getSubmitterContact2() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", branchInsuranceMapUuid='" + getBranchInsuranceMapUuid() + "'" +
            "}";
    }
}
