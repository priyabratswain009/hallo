package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ParMaster.
 */
@Table("t_par_master")
public class ParMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("par_id")
    private Long parId;

    @Column("par_no")
    private String parNo;

    @Column("patient_id")
    private Long patientId;

    @Column("patient_id_number")
    private String patientIdNumber;

    @Column("patient_first_name")
    private String patientFirstName;

    @Column("patient_last_name")
    private String patientLastName;

    @Column("patient_dob")
    private LocalDate patientDob;

    @Column("patient_gender")
    private String patientGender;

    @Column("insurance_id")
    private Long insuranceId;

    @Column("insurance_name")
    private String insuranceName;

    @Column("payer_id_no")
    private String payerIdNo;

    @Column("payer_level")
    private String payerLevel;

    @Column("policy_number")
    private String policyNumber;

    @Column("policy_start_date")
    private LocalDate policyStartDate;

    @Column("policy_end_date")
    private LocalDate policyEndDate;

    @Column("payer_contact_number")
    private String payerContactNumber;

    @Column("payer_contact_name")
    private String payerContactName;

    @Column("date_of_contact")
    private LocalDate dateOfContact;

    @Column("description")
    private String description;

    @Column("initial_date")
    private LocalDate initialDate;

    @Column("effective_start_date")
    private LocalDate effectiveStartDate;

    @Column("expiration_date")
    private LocalDate expirationDate;

    @Column("authorized_by")
    private String authorizedBy;

    @Column("addl_information")
    private String addlInformation;

    @Column("par_status")
    private String parStatus;

    @Column("comments")
    private String comments;

    @Column("log_in_status")
    private String logInStatus;

    @Column("log_in_date")
    private LocalDate logInDate;

    @Column("renewal_status")
    private String renewalStatus;

    @Column("renewal_date")
    private LocalDate renewalDate;

    @Column("renewal_authorized_by")
    private String renewalAuthorizedBy;

    @Column("renewal_req_sent_status")
    private String renewalReqSentStatus;

    @Column("renewal_req_reply_status")
    private String renewalReqReplyStatus;

    @Column("original_par_no")
    private String originalParNo;

    @Column("extension_status")
    private String extensionStatus;

    @Column("extension_approval_date")
    private LocalDate extensionApprovalDate;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_by_name")
    private String createdByName;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("par_uuid")
    private UUID parUuid;

    @Column("par_id_no")
    private String parIdNo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getParId() {
        return this.parId;
    }

    public ParMaster parId(Long parId) {
        this.setParId(parId);
        return this;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParNo() {
        return this.parNo;
    }

    public ParMaster parNo(String parNo) {
        this.setParNo(parNo);
        return this;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public ParMaster patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientIdNumber() {
        return this.patientIdNumber;
    }

    public ParMaster patientIdNumber(String patientIdNumber) {
        this.setPatientIdNumber(patientIdNumber);
        return this;
    }

    public void setPatientIdNumber(String patientIdNumber) {
        this.patientIdNumber = patientIdNumber;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public ParMaster patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public ParMaster patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public LocalDate getPatientDob() {
        return this.patientDob;
    }

    public ParMaster patientDob(LocalDate patientDob) {
        this.setPatientDob(patientDob);
        return this;
    }

    public void setPatientDob(LocalDate patientDob) {
        this.patientDob = patientDob;
    }

    public String getPatientGender() {
        return this.patientGender;
    }

    public ParMaster patientGender(String patientGender) {
        this.setPatientGender(patientGender);
        return this;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public Long getInsuranceId() {
        return this.insuranceId;
    }

    public ParMaster insuranceId(Long insuranceId) {
        this.setInsuranceId(insuranceId);
        return this;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return this.insuranceName;
    }

    public ParMaster insuranceName(String insuranceName) {
        this.setInsuranceName(insuranceName);
        return this;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getPayerIdNo() {
        return this.payerIdNo;
    }

    public ParMaster payerIdNo(String payerIdNo) {
        this.setPayerIdNo(payerIdNo);
        return this;
    }

    public void setPayerIdNo(String payerIdNo) {
        this.payerIdNo = payerIdNo;
    }

    public String getPayerLevel() {
        return this.payerLevel;
    }

    public ParMaster payerLevel(String payerLevel) {
        this.setPayerLevel(payerLevel);
        return this;
    }

    public void setPayerLevel(String payerLevel) {
        this.payerLevel = payerLevel;
    }

    public String getPolicyNumber() {
        return this.policyNumber;
    }

    public ParMaster policyNumber(String policyNumber) {
        this.setPolicyNumber(policyNumber);
        return this;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public LocalDate getPolicyStartDate() {
        return this.policyStartDate;
    }

    public ParMaster policyStartDate(LocalDate policyStartDate) {
        this.setPolicyStartDate(policyStartDate);
        return this;
    }

    public void setPolicyStartDate(LocalDate policyStartDate) {
        this.policyStartDate = policyStartDate;
    }

    public LocalDate getPolicyEndDate() {
        return this.policyEndDate;
    }

    public ParMaster policyEndDate(LocalDate policyEndDate) {
        this.setPolicyEndDate(policyEndDate);
        return this;
    }

    public void setPolicyEndDate(LocalDate policyEndDate) {
        this.policyEndDate = policyEndDate;
    }

    public String getPayerContactNumber() {
        return this.payerContactNumber;
    }

    public ParMaster payerContactNumber(String payerContactNumber) {
        this.setPayerContactNumber(payerContactNumber);
        return this;
    }

    public void setPayerContactNumber(String payerContactNumber) {
        this.payerContactNumber = payerContactNumber;
    }

    public String getPayerContactName() {
        return this.payerContactName;
    }

    public ParMaster payerContactName(String payerContactName) {
        this.setPayerContactName(payerContactName);
        return this;
    }

    public void setPayerContactName(String payerContactName) {
        this.payerContactName = payerContactName;
    }

    public LocalDate getDateOfContact() {
        return this.dateOfContact;
    }

    public ParMaster dateOfContact(LocalDate dateOfContact) {
        this.setDateOfContact(dateOfContact);
        return this;
    }

    public void setDateOfContact(LocalDate dateOfContact) {
        this.dateOfContact = dateOfContact;
    }

    public String getDescription() {
        return this.description;
    }

    public ParMaster description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getInitialDate() {
        return this.initialDate;
    }

    public ParMaster initialDate(LocalDate initialDate) {
        this.setInitialDate(initialDate);
        return this;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getEffectiveStartDate() {
        return this.effectiveStartDate;
    }

    public ParMaster effectiveStartDate(LocalDate effectiveStartDate) {
        this.setEffectiveStartDate(effectiveStartDate);
        return this;
    }

    public void setEffectiveStartDate(LocalDate effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public ParMaster expirationDate(LocalDate expirationDate) {
        this.setExpirationDate(expirationDate);
        return this;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAuthorizedBy() {
        return this.authorizedBy;
    }

    public ParMaster authorizedBy(String authorizedBy) {
        this.setAuthorizedBy(authorizedBy);
        return this;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public String getAddlInformation() {
        return this.addlInformation;
    }

    public ParMaster addlInformation(String addlInformation) {
        this.setAddlInformation(addlInformation);
        return this;
    }

    public void setAddlInformation(String addlInformation) {
        this.addlInformation = addlInformation;
    }

    public String getParStatus() {
        return this.parStatus;
    }

    public ParMaster parStatus(String parStatus) {
        this.setParStatus(parStatus);
        return this;
    }

    public void setParStatus(String parStatus) {
        this.parStatus = parStatus;
    }

    public String getComments() {
        return this.comments;
    }

    public ParMaster comments(String comments) {
        this.setComments(comments);
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLogInStatus() {
        return this.logInStatus;
    }

    public ParMaster logInStatus(String logInStatus) {
        this.setLogInStatus(logInStatus);
        return this;
    }

    public void setLogInStatus(String logInStatus) {
        this.logInStatus = logInStatus;
    }

    public LocalDate getLogInDate() {
        return this.logInDate;
    }

    public ParMaster logInDate(LocalDate logInDate) {
        this.setLogInDate(logInDate);
        return this;
    }

    public void setLogInDate(LocalDate logInDate) {
        this.logInDate = logInDate;
    }

    public String getRenewalStatus() {
        return this.renewalStatus;
    }

    public ParMaster renewalStatus(String renewalStatus) {
        this.setRenewalStatus(renewalStatus);
        return this;
    }

    public void setRenewalStatus(String renewalStatus) {
        this.renewalStatus = renewalStatus;
    }

    public LocalDate getRenewalDate() {
        return this.renewalDate;
    }

    public ParMaster renewalDate(LocalDate renewalDate) {
        this.setRenewalDate(renewalDate);
        return this;
    }

    public void setRenewalDate(LocalDate renewalDate) {
        this.renewalDate = renewalDate;
    }

    public String getRenewalAuthorizedBy() {
        return this.renewalAuthorizedBy;
    }

    public ParMaster renewalAuthorizedBy(String renewalAuthorizedBy) {
        this.setRenewalAuthorizedBy(renewalAuthorizedBy);
        return this;
    }

    public void setRenewalAuthorizedBy(String renewalAuthorizedBy) {
        this.renewalAuthorizedBy = renewalAuthorizedBy;
    }

    public String getRenewalReqSentStatus() {
        return this.renewalReqSentStatus;
    }

    public ParMaster renewalReqSentStatus(String renewalReqSentStatus) {
        this.setRenewalReqSentStatus(renewalReqSentStatus);
        return this;
    }

    public void setRenewalReqSentStatus(String renewalReqSentStatus) {
        this.renewalReqSentStatus = renewalReqSentStatus;
    }

    public String getRenewalReqReplyStatus() {
        return this.renewalReqReplyStatus;
    }

    public ParMaster renewalReqReplyStatus(String renewalReqReplyStatus) {
        this.setRenewalReqReplyStatus(renewalReqReplyStatus);
        return this;
    }

    public void setRenewalReqReplyStatus(String renewalReqReplyStatus) {
        this.renewalReqReplyStatus = renewalReqReplyStatus;
    }

    public String getOriginalParNo() {
        return this.originalParNo;
    }

    public ParMaster originalParNo(String originalParNo) {
        this.setOriginalParNo(originalParNo);
        return this;
    }

    public void setOriginalParNo(String originalParNo) {
        this.originalParNo = originalParNo;
    }

    public String getExtensionStatus() {
        return this.extensionStatus;
    }

    public ParMaster extensionStatus(String extensionStatus) {
        this.setExtensionStatus(extensionStatus);
        return this;
    }

    public void setExtensionStatus(String extensionStatus) {
        this.extensionStatus = extensionStatus;
    }

    public LocalDate getExtensionApprovalDate() {
        return this.extensionApprovalDate;
    }

    public ParMaster extensionApprovalDate(LocalDate extensionApprovalDate) {
        this.setExtensionApprovalDate(extensionApprovalDate);
        return this;
    }

    public void setExtensionApprovalDate(LocalDate extensionApprovalDate) {
        this.extensionApprovalDate = extensionApprovalDate;
    }

    public String getStatus() {
        return this.status;
    }

    public ParMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ParMaster createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ParMaster createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ParMaster createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ParMaster updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ParMaster updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ParMaster updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getParUuid() {
        return this.parUuid;
    }

    public ParMaster parUuid(UUID parUuid) {
        this.setParUuid(parUuid);
        return this;
    }

    public void setParUuid(UUID parUuid) {
        this.parUuid = parUuid;
    }

    public String getParIdNo() {
        return this.parIdNo;
    }

    public ParMaster parIdNo(String parIdNo) {
        this.setParIdNo(parIdNo);
        return this;
    }

    public void setParIdNo(String parIdNo) {
        this.parIdNo = parIdNo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParMaster)) {
            return false;
        }
        return parId != null && parId.equals(((ParMaster) o).parId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParMaster{" +
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
