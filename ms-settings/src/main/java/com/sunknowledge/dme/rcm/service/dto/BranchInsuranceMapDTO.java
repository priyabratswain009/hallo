package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.BranchInsuranceMap} entity.
 */
public class BranchInsuranceMapDTO implements Serializable {

    private Long branchInsuranceMapId;

    private Long branchId;

    private String branchName;

    private Long insuranceId;

    private String insuranceName;

    private String insuranceIdNo;

    private String insuranceStateName;

    private Long priceTableId;

    private String priceTableName;

    private String branchNpi;

    private String branchPtan;

    private String esubmitterPayorId;

    private String branchTaxonomyCode;

    private String billingProviderOverrideCompanyName;

    private String billingProviderOverrideTaxIdEin;

    private String billingProviderOverrideAddressLine1;

    private String billingProviderOverrideAddressLine2;

    private String billingProviderOverrideCity;

    private String billingProviderOverrideState;

    private String billingProviderOverrideZip;

    private String billingProviderOverrideContact1;

    private String billingProviderOverrideContact2;

    private String billingProviderOverrideFax;

    private String billingProviderOverrideEmail;

    private String payToProviderCompanyName;

    private String payToProviderTaxIdEin;

    private String payToProviderAddressLine1;

    private String payToProviderAddressLine2;

    private String payToProviderCity;

    private String payToProviderState;

    private String payToProviderZip;

    private String payToProviderContact1;

    private String payToProviderContact2;

    private String payToProviderFax;

    private String payToProviderEmail;

    private String submitterName;

    private String submitterContact1;

    private String submitterContact2;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID branchInsuranceMapUuid;

    public Long getBranchInsuranceMapId() {
        return branchInsuranceMapId;
    }

    public void setBranchInsuranceMapId(Long branchInsuranceMapId) {
        this.branchInsuranceMapId = branchInsuranceMapId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsuranceIdNo() {
        return insuranceIdNo;
    }

    public void setInsuranceIdNo(String insuranceIdNo) {
        this.insuranceIdNo = insuranceIdNo;
    }

    public String getInsuranceStateName() {
        return insuranceStateName;
    }

    public void setInsuranceStateName(String insuranceStateName) {
        this.insuranceStateName = insuranceStateName;
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

    public String getBranchNpi() {
        return branchNpi;
    }

    public void setBranchNpi(String branchNpi) {
        this.branchNpi = branchNpi;
    }

    public String getBranchPtan() {
        return branchPtan;
    }

    public void setBranchPtan(String branchPtan) {
        this.branchPtan = branchPtan;
    }

    public String getEsubmitterPayorId() {
        return esubmitterPayorId;
    }

    public void setEsubmitterPayorId(String esubmitterPayorId) {
        this.esubmitterPayorId = esubmitterPayorId;
    }

    public String getBranchTaxonomyCode() {
        return branchTaxonomyCode;
    }

    public void setBranchTaxonomyCode(String branchTaxonomyCode) {
        this.branchTaxonomyCode = branchTaxonomyCode;
    }

    public String getBillingProviderOverrideCompanyName() {
        return billingProviderOverrideCompanyName;
    }

    public void setBillingProviderOverrideCompanyName(String billingProviderOverrideCompanyName) {
        this.billingProviderOverrideCompanyName = billingProviderOverrideCompanyName;
    }

    public String getBillingProviderOverrideTaxIdEin() {
        return billingProviderOverrideTaxIdEin;
    }

    public void setBillingProviderOverrideTaxIdEin(String billingProviderOverrideTaxIdEin) {
        this.billingProviderOverrideTaxIdEin = billingProviderOverrideTaxIdEin;
    }

    public String getBillingProviderOverrideAddressLine1() {
        return billingProviderOverrideAddressLine1;
    }

    public void setBillingProviderOverrideAddressLine1(String billingProviderOverrideAddressLine1) {
        this.billingProviderOverrideAddressLine1 = billingProviderOverrideAddressLine1;
    }

    public String getBillingProviderOverrideAddressLine2() {
        return billingProviderOverrideAddressLine2;
    }

    public void setBillingProviderOverrideAddressLine2(String billingProviderOverrideAddressLine2) {
        this.billingProviderOverrideAddressLine2 = billingProviderOverrideAddressLine2;
    }

    public String getBillingProviderOverrideCity() {
        return billingProviderOverrideCity;
    }

    public void setBillingProviderOverrideCity(String billingProviderOverrideCity) {
        this.billingProviderOverrideCity = billingProviderOverrideCity;
    }

    public String getBillingProviderOverrideState() {
        return billingProviderOverrideState;
    }

    public void setBillingProviderOverrideState(String billingProviderOverrideState) {
        this.billingProviderOverrideState = billingProviderOverrideState;
    }

    public String getBillingProviderOverrideZip() {
        return billingProviderOverrideZip;
    }

    public void setBillingProviderOverrideZip(String billingProviderOverrideZip) {
        this.billingProviderOverrideZip = billingProviderOverrideZip;
    }

    public String getBillingProviderOverrideContact1() {
        return billingProviderOverrideContact1;
    }

    public void setBillingProviderOverrideContact1(String billingProviderOverrideContact1) {
        this.billingProviderOverrideContact1 = billingProviderOverrideContact1;
    }

    public String getBillingProviderOverrideContact2() {
        return billingProviderOverrideContact2;
    }

    public void setBillingProviderOverrideContact2(String billingProviderOverrideContact2) {
        this.billingProviderOverrideContact2 = billingProviderOverrideContact2;
    }

    public String getBillingProviderOverrideFax() {
        return billingProviderOverrideFax;
    }

    public void setBillingProviderOverrideFax(String billingProviderOverrideFax) {
        this.billingProviderOverrideFax = billingProviderOverrideFax;
    }

    public String getBillingProviderOverrideEmail() {
        return billingProviderOverrideEmail;
    }

    public void setBillingProviderOverrideEmail(String billingProviderOverrideEmail) {
        this.billingProviderOverrideEmail = billingProviderOverrideEmail;
    }

    public String getPayToProviderCompanyName() {
        return payToProviderCompanyName;
    }

    public void setPayToProviderCompanyName(String payToProviderCompanyName) {
        this.payToProviderCompanyName = payToProviderCompanyName;
    }

    public String getPayToProviderTaxIdEin() {
        return payToProviderTaxIdEin;
    }

    public void setPayToProviderTaxIdEin(String payToProviderTaxIdEin) {
        this.payToProviderTaxIdEin = payToProviderTaxIdEin;
    }

    public String getPayToProviderAddressLine1() {
        return payToProviderAddressLine1;
    }

    public void setPayToProviderAddressLine1(String payToProviderAddressLine1) {
        this.payToProviderAddressLine1 = payToProviderAddressLine1;
    }

    public String getPayToProviderAddressLine2() {
        return payToProviderAddressLine2;
    }

    public void setPayToProviderAddressLine2(String payToProviderAddressLine2) {
        this.payToProviderAddressLine2 = payToProviderAddressLine2;
    }

    public String getPayToProviderCity() {
        return payToProviderCity;
    }

    public void setPayToProviderCity(String payToProviderCity) {
        this.payToProviderCity = payToProviderCity;
    }

    public String getPayToProviderState() {
        return payToProviderState;
    }

    public void setPayToProviderState(String payToProviderState) {
        this.payToProviderState = payToProviderState;
    }

    public String getPayToProviderZip() {
        return payToProviderZip;
    }

    public void setPayToProviderZip(String payToProviderZip) {
        this.payToProviderZip = payToProviderZip;
    }

    public String getPayToProviderContact1() {
        return payToProviderContact1;
    }

    public void setPayToProviderContact1(String payToProviderContact1) {
        this.payToProviderContact1 = payToProviderContact1;
    }

    public String getPayToProviderContact2() {
        return payToProviderContact2;
    }

    public void setPayToProviderContact2(String payToProviderContact2) {
        this.payToProviderContact2 = payToProviderContact2;
    }

    public String getPayToProviderFax() {
        return payToProviderFax;
    }

    public void setPayToProviderFax(String payToProviderFax) {
        this.payToProviderFax = payToProviderFax;
    }

    public String getPayToProviderEmail() {
        return payToProviderEmail;
    }

    public void setPayToProviderEmail(String payToProviderEmail) {
        this.payToProviderEmail = payToProviderEmail;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
    }

    public String getSubmitterContact1() {
        return submitterContact1;
    }

    public void setSubmitterContact1(String submitterContact1) {
        this.submitterContact1 = submitterContact1;
    }

    public String getSubmitterContact2() {
        return submitterContact2;
    }

    public void setSubmitterContact2(String submitterContact2) {
        this.submitterContact2 = submitterContact2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
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

    public UUID getBranchInsuranceMapUuid() {
        return branchInsuranceMapUuid;
    }

    public void setBranchInsuranceMapUuid(UUID branchInsuranceMapUuid) {
        this.branchInsuranceMapUuid = branchInsuranceMapUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchInsuranceMapDTO)) {
            return false;
        }

        BranchInsuranceMapDTO branchInsuranceMapDTO = (BranchInsuranceMapDTO) o;
        if (this.branchInsuranceMapId == null) {
            return false;
        }
        return Objects.equals(this.branchInsuranceMapId, branchInsuranceMapDTO.branchInsuranceMapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.branchInsuranceMapId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchInsuranceMapDTO{" +
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
