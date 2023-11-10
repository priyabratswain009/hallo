package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.WorkersCompensation} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WorkersCompensationDTO implements Serializable {

    private Long workersCompensationId;

    private Long patientId;

    private String insuredEmployer;

    private String workersCompensationPayerIdNumber;

    private String workersCompensationPlanName;

    private String workersCompensationAdditionalDtls;

    private String workersCompensationClaimFillingCode;

    private String workersCompensationTplCode;

    private String workersCompensationTplName;

    private String wcPropertyCasualtyAgencyClaimNo;

    private Long wcCarrierId;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private LocalDate updatedDate;

    private UUID workersCompensationUuid;

    private String employerAddressLine1;

    private String employerAddressLine2;

    private String employerCity;

    private String employerState;

    private String employerCountry;

    private String employerZip;

    private String employerContactNo1;

    private String employerContactNo2;

    private String employerFax;

    private String employerEfax;

    private String employerEmail;

    private String relationship;

    private String modeOfContact;

    public Long getWorkersCompensationId() {
        return workersCompensationId;
    }

    public void setWorkersCompensationId(Long workersCompensationId) {
        this.workersCompensationId = workersCompensationId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getInsuredEmployer() {
        return insuredEmployer;
    }

    public void setInsuredEmployer(String insuredEmployer) {
        this.insuredEmployer = insuredEmployer;
    }

    public String getWorkersCompensationPayerIdNumber() {
        return workersCompensationPayerIdNumber;
    }

    public void setWorkersCompensationPayerIdNumber(String workersCompensationPayerIdNumber) {
        this.workersCompensationPayerIdNumber = workersCompensationPayerIdNumber;
    }

    public String getWorkersCompensationPlanName() {
        return workersCompensationPlanName;
    }

    public void setWorkersCompensationPlanName(String workersCompensationPlanName) {
        this.workersCompensationPlanName = workersCompensationPlanName;
    }

    public String getWorkersCompensationAdditionalDtls() {
        return workersCompensationAdditionalDtls;
    }

    public void setWorkersCompensationAdditionalDtls(String workersCompensationAdditionalDtls) {
        this.workersCompensationAdditionalDtls = workersCompensationAdditionalDtls;
    }

    public String getWorkersCompensationClaimFillingCode() {
        return workersCompensationClaimFillingCode;
    }

    public void setWorkersCompensationClaimFillingCode(String workersCompensationClaimFillingCode) {
        this.workersCompensationClaimFillingCode = workersCompensationClaimFillingCode;
    }

    public String getWorkersCompensationTplCode() {
        return workersCompensationTplCode;
    }

    public void setWorkersCompensationTplCode(String workersCompensationTplCode) {
        this.workersCompensationTplCode = workersCompensationTplCode;
    }

    public String getWorkersCompensationTplName() {
        return workersCompensationTplName;
    }

    public void setWorkersCompensationTplName(String workersCompensationTplName) {
        this.workersCompensationTplName = workersCompensationTplName;
    }

    public String getWcPropertyCasualtyAgencyClaimNo() {
        return wcPropertyCasualtyAgencyClaimNo;
    }

    public void setWcPropertyCasualtyAgencyClaimNo(String wcPropertyCasualtyAgencyClaimNo) {
        this.wcPropertyCasualtyAgencyClaimNo = wcPropertyCasualtyAgencyClaimNo;
    }

    public Long getWcCarrierId() {
        return wcCarrierId;
    }

    public void setWcCarrierId(Long wcCarrierId) {
        this.wcCarrierId = wcCarrierId;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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

    public UUID getWorkersCompensationUuid() {
        return workersCompensationUuid;
    }

    public void setWorkersCompensationUuid(UUID workersCompensationUuid) {
        this.workersCompensationUuid = workersCompensationUuid;
    }

    public String getEmployerAddressLine1() {
        return employerAddressLine1;
    }

    public void setEmployerAddressLine1(String employerAddressLine1) {
        this.employerAddressLine1 = employerAddressLine1;
    }

    public String getEmployerAddressLine2() {
        return employerAddressLine2;
    }

    public void setEmployerAddressLine2(String employerAddressLine2) {
        this.employerAddressLine2 = employerAddressLine2;
    }

    public String getEmployerCity() {
        return employerCity;
    }

    public void setEmployerCity(String employerCity) {
        this.employerCity = employerCity;
    }

    public String getEmployerState() {
        return employerState;
    }

    public void setEmployerState(String employerState) {
        this.employerState = employerState;
    }

    public String getEmployerCountry() {
        return employerCountry;
    }

    public void setEmployerCountry(String employerCountry) {
        this.employerCountry = employerCountry;
    }

    public String getEmployerZip() {
        return employerZip;
    }

    public void setEmployerZip(String employerZip) {
        this.employerZip = employerZip;
    }

    public String getEmployerContactNo1() {
        return employerContactNo1;
    }

    public void setEmployerContactNo1(String employerContactNo1) {
        this.employerContactNo1 = employerContactNo1;
    }

    public String getEmployerContactNo2() {
        return employerContactNo2;
    }

    public void setEmployerContactNo2(String employerContactNo2) {
        this.employerContactNo2 = employerContactNo2;
    }

    public String getEmployerFax() {
        return employerFax;
    }

    public void setEmployerFax(String employerFax) {
        this.employerFax = employerFax;
    }

    public String getEmployerEfax() {
        return employerEfax;
    }

    public void setEmployerEfax(String employerEfax) {
        this.employerEfax = employerEfax;
    }

    public String getEmployerEmail() {
        return employerEmail;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkersCompensationDTO)) {
            return false;
        }

        WorkersCompensationDTO workersCompensationDTO = (WorkersCompensationDTO) o;
        if (this.workersCompensationId == null) {
            return false;
        }
        return Objects.equals(this.workersCompensationId, workersCompensationDTO.workersCompensationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.workersCompensationId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkersCompensationDTO{" +
            "workersCompensationId=" + getWorkersCompensationId() +
            ", patientId=" + getPatientId() +
            ", insuredEmployer='" + getInsuredEmployer() + "'" +
            ", workersCompensationPayerIdNumber='" + getWorkersCompensationPayerIdNumber() + "'" +
            ", workersCompensationPlanName='" + getWorkersCompensationPlanName() + "'" +
            ", workersCompensationAdditionalDtls='" + getWorkersCompensationAdditionalDtls() + "'" +
            ", workersCompensationClaimFillingCode='" + getWorkersCompensationClaimFillingCode() + "'" +
            ", workersCompensationTplCode='" + getWorkersCompensationTplCode() + "'" +
            ", workersCompensationTplName='" + getWorkersCompensationTplName() + "'" +
            ", wcPropertyCasualtyAgencyClaimNo='" + getWcPropertyCasualtyAgencyClaimNo() + "'" +
            ", wcCarrierId=" + getWcCarrierId() +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", workersCompensationUuid='" + getWorkersCompensationUuid() + "'" +
            ", employerAddressLine1='" + getEmployerAddressLine1() + "'" +
            ", employerAddressLine2='" + getEmployerAddressLine2() + "'" +
            ", employerCity='" + getEmployerCity() + "'" +
            ", employerState='" + getEmployerState() + "'" +
            ", employerCountry='" + getEmployerCountry() + "'" +
            ", employerZip='" + getEmployerZip() + "'" +
            ", employerContactNo1='" + getEmployerContactNo1() + "'" +
            ", employerContactNo2='" + getEmployerContactNo2() + "'" +
            ", employerFax='" + getEmployerFax() + "'" +
            ", employerEfax='" + getEmployerEfax() + "'" +
            ", employerEmail='" + getEmployerEmail() + "'" +
            ", relationship='" + getRelationship() + "'" +
            ", modeOfContact='" + getModeOfContact() + "'" +
            "}";
    }
}
