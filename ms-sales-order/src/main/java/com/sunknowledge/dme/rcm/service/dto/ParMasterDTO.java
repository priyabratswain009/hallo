package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ParMaster} entity.
 */
public class ParMasterDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long parId;

    private String parNo;

    private Long patientId;

    private String patientIdNumber;

    private String patientFirstName;

    private String patientLastName;

    private LocalDate patientDob;

    private String patientGender;

    private Long insuranceId;

    private String insuranceName;

    private String payerIdNo;

    private String payerLevel;

    private String policyNumber;

    private LocalDate policyStartDate;

    private LocalDate policyEndDate;

    private String payerContactNumber;

    private String payerContactName;

    private LocalDate dateOfContact;

    private String description;

    private LocalDate initialDate;

    private LocalDate effectiveStartDate;

    private LocalDate expirationDate;

    private String authorizedBy;

    private String addlInformation;

    private String parStatus;

    private String comments;

    private String logInStatus;

    private LocalDate logInDate;

    private String renewalStatus;

    private LocalDate renewalDate;

    private String renewalAuthorizedBy;

    private String renewalReqSentStatus;

    private String renewalReqReplyStatus;

    private String originalParNo;

    private String extensionStatus;

    private LocalDate extensionApprovalDate;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID parUuid;

    private String parIdNo;

    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParNo() {
        return parNo;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientIdNumber() {
        return patientIdNumber;
    }

    public void setPatientIdNumber(String patientIdNumber) {
        this.patientIdNumber = patientIdNumber;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public LocalDate getPatientDob() {
        return patientDob;
    }

    public void setPatientDob(LocalDate patientDob) {
        this.patientDob = patientDob;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
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

    public String getPayerIdNo() {
        return payerIdNo;
    }

    public void setPayerIdNo(String payerIdNo) {
        this.payerIdNo = payerIdNo;
    }

    public String getPayerLevel() {
        return payerLevel;
    }

    public void setPayerLevel(String payerLevel) {
        this.payerLevel = payerLevel;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public LocalDate getPolicyStartDate() {
        return policyStartDate;
    }

    public void setPolicyStartDate(LocalDate policyStartDate) {
        this.policyStartDate = policyStartDate;
    }

    public LocalDate getPolicyEndDate() {
        return policyEndDate;
    }

    public void setPolicyEndDate(LocalDate policyEndDate) {
        this.policyEndDate = policyEndDate;
    }

    public String getPayerContactNumber() {
        return payerContactNumber;
    }

    public void setPayerContactNumber(String payerContactNumber) {
        this.payerContactNumber = payerContactNumber;
    }

    public String getPayerContactName() {
        return payerContactName;
    }

    public void setPayerContactName(String payerContactName) {
        this.payerContactName = payerContactName;
    }

    public LocalDate getDateOfContact() {
        return dateOfContact;
    }

    public void setDateOfContact(LocalDate dateOfContact) {
        this.dateOfContact = dateOfContact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(LocalDate effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public String getAddlInformation() {
        return addlInformation;
    }

    public void setAddlInformation(String addlInformation) {
        this.addlInformation = addlInformation;
    }

    public String getParStatus() {
        return parStatus;
    }

    public void setParStatus(String parStatus) {
        this.parStatus = parStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLogInStatus() {
        return logInStatus;
    }

    public void setLogInStatus(String logInStatus) {
        this.logInStatus = logInStatus;
    }

    public LocalDate getLogInDate() {
        return logInDate;
    }

    public void setLogInDate(LocalDate logInDate) {
        this.logInDate = logInDate;
    }

    public String getRenewalStatus() {
        return renewalStatus;
    }

    public void setRenewalStatus(String renewalStatus) {
        this.renewalStatus = renewalStatus;
    }

    public LocalDate getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(LocalDate renewalDate) {
        this.renewalDate = renewalDate;
    }

    public String getRenewalAuthorizedBy() {
        return renewalAuthorizedBy;
    }

    public void setRenewalAuthorizedBy(String renewalAuthorizedBy) {
        this.renewalAuthorizedBy = renewalAuthorizedBy;
    }

    public String getRenewalReqSentStatus() {
        return renewalReqSentStatus;
    }

    public void setRenewalReqSentStatus(String renewalReqSentStatus) {
        this.renewalReqSentStatus = renewalReqSentStatus;
    }

    public String getRenewalReqReplyStatus() {
        return renewalReqReplyStatus;
    }

    public void setRenewalReqReplyStatus(String renewalReqReplyStatus) {
        this.renewalReqReplyStatus = renewalReqReplyStatus;
    }

    public String getOriginalParNo() {
        return originalParNo;
    }

    public void setOriginalParNo(String originalParNo) {
        this.originalParNo = originalParNo;
    }

    public String getExtensionStatus() {
        return extensionStatus;
    }

    public void setExtensionStatus(String extensionStatus) {
        this.extensionStatus = extensionStatus;
    }

    public LocalDate getExtensionApprovalDate() {
        return extensionApprovalDate;
    }

    public void setExtensionApprovalDate(LocalDate extensionApprovalDate) {
        this.extensionApprovalDate = extensionApprovalDate;
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

    public UUID getParUuid() {
        return parUuid;
    }

    public void setParUuid(UUID parUuid) {
        this.parUuid = parUuid;
    }

    public String getParIdNo() {
        return parIdNo;
    }

    public void setParIdNo(String parIdNo) {
        this.parIdNo = parIdNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParMasterDTO)) {
            return false;
        }

        ParMasterDTO parMasterDTO = (ParMasterDTO) o;
        if (this.parId == null) {
            return false;
        }
        return Objects.equals(this.parId, parMasterDTO.parId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.parId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParMasterDTO{" +
            "parId=" + getParId() +
            ", parNo='" + getParNo() + "'" +
            ", patientId=" + getPatientId() +
            ", patientIdNumber='" + getPatientIdNumber() + "'" +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", patientDob='" + getPatientDob() + "'" +
            ", patientGender='" + getPatientGender() + "'" +
            ", insuranceId=" + getInsuranceId() +
            ", insuranceName='" + getInsuranceName() + "'" +
            ", payerIdNo='" + getPayerIdNo() + "'" +
            ", payerLevel='" + getPayerLevel() + "'" +
            ", policyNumber='" + getPolicyNumber() + "'" +
            ", policyStartDate='" + getPolicyStartDate() + "'" +
            ", policyEndDate='" + getPolicyEndDate() + "'" +
            ", payerContactNumber='" + getPayerContactNumber() + "'" +
            ", payerContactName='" + getPayerContactName() + "'" +
            ", dateOfContact='" + getDateOfContact() + "'" +
            ", description='" + getDescription() + "'" +
            ", initialDate='" + getInitialDate() + "'" +
            ", effectiveStartDate='" + getEffectiveStartDate() + "'" +
            ", expirationDate='" + getExpirationDate() + "'" +
            ", authorizedBy='" + getAuthorizedBy() + "'" +
            ", addlInformation='" + getAddlInformation() + "'" +
            ", parStatus='" + getParStatus() + "'" +
            ", comments='" + getComments() + "'" +
            ", logInStatus='" + getLogInStatus() + "'" +
            ", logInDate='" + getLogInDate() + "'" +
            ", renewalStatus='" + getRenewalStatus() + "'" +
            ", renewalDate='" + getRenewalDate() + "'" +
            ", renewalAuthorizedBy='" + getRenewalAuthorizedBy() + "'" +
            ", renewalReqSentStatus='" + getRenewalReqSentStatus() + "'" +
            ", renewalReqReplyStatus='" + getRenewalReqReplyStatus() + "'" +
            ", originalParNo='" + getOriginalParNo() + "'" +
            ", extensionStatus='" + getExtensionStatus() + "'" +
            ", extensionApprovalDate='" + getExtensionApprovalDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", parUuid='" + getParUuid() + "'" +
            ", parIdNo='" + getParIdNo() + "'" +
            "}";
    }
}
