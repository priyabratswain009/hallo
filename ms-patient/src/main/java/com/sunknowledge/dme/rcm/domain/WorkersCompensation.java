package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A WorkersCompensation.
 */
@Table("t_workers_compensation")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WorkersCompensation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("workers_compensation_id")
    private Long workersCompensationId;

    @Column("patient_id")
    private Long patientId;

    @Column("insured_employer")
    private String insuredEmployer;

    @Column("workers_compensation_payer_id_number")
    private String workersCompensationPayerIdNumber;

    @Column("workers_compensation_plan_name")
    private String workersCompensationPlanName;

    @Column("workers_compensation_additional_dtls")
    private String workersCompensationAdditionalDtls;

    @Column("workers_compensation_claim_filling_code")
    private String workersCompensationClaimFillingCode;

    @Column("workers_compensation_tpl_code")
    private String workersCompensationTplCode;

    @Column("workers_compensation_tpl_name")
    private String workersCompensationTplName;

    @Column("wc_property_casualty_agency_claim_no")
    private String wcPropertyCasualtyAgencyClaimNo;

    @Column("wc_carrier_id")
    private Long wcCarrierId;

    @Column("status")
    private String status;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("workers_compensation_uuid")
    private UUID workersCompensationUuid;

    @Column("employer_address_line_1")
    private String employerAddressLine1;

    @Column("employer_address_line_2")
    private String employerAddressLine2;

    @Column("employer_city")
    private String employerCity;

    @Column("employer_state")
    private String employerState;

    @Column("employer_country")
    private String employerCountry;

    @Column("employer_zip")
    private String employerZip;

    @Column("employer_contact_no_1")
    private String employerContactNo1;

    @Column("employer_contact_no_2")
    private String employerContactNo2;

    @Column("employer_fax")
    private String employerFax;

    @Column("employer_efax")
    private String employerEfax;

    @Column("employer_email")
    private String employerEmail;

    @Column("relationship")
    private String relationship;

    @Column("mode_of_contact")
    private String modeOfContact;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getWorkersCompensationId() {
        return this.workersCompensationId;
    }

    public WorkersCompensation workersCompensationId(Long workersCompensationId) {
        this.setWorkersCompensationId(workersCompensationId);
        return this;
    }

    public void setWorkersCompensationId(Long workersCompensationId) {
        this.workersCompensationId = workersCompensationId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public WorkersCompensation patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getInsuredEmployer() {
        return this.insuredEmployer;
    }

    public WorkersCompensation insuredEmployer(String insuredEmployer) {
        this.setInsuredEmployer(insuredEmployer);
        return this;
    }

    public void setInsuredEmployer(String insuredEmployer) {
        this.insuredEmployer = insuredEmployer;
    }

    public String getWorkersCompensationPayerIdNumber() {
        return this.workersCompensationPayerIdNumber;
    }

    public WorkersCompensation workersCompensationPayerIdNumber(String workersCompensationPayerIdNumber) {
        this.setWorkersCompensationPayerIdNumber(workersCompensationPayerIdNumber);
        return this;
    }

    public void setWorkersCompensationPayerIdNumber(String workersCompensationPayerIdNumber) {
        this.workersCompensationPayerIdNumber = workersCompensationPayerIdNumber;
    }

    public String getWorkersCompensationPlanName() {
        return this.workersCompensationPlanName;
    }

    public WorkersCompensation workersCompensationPlanName(String workersCompensationPlanName) {
        this.setWorkersCompensationPlanName(workersCompensationPlanName);
        return this;
    }

    public void setWorkersCompensationPlanName(String workersCompensationPlanName) {
        this.workersCompensationPlanName = workersCompensationPlanName;
    }

    public String getWorkersCompensationAdditionalDtls() {
        return this.workersCompensationAdditionalDtls;
    }

    public WorkersCompensation workersCompensationAdditionalDtls(String workersCompensationAdditionalDtls) {
        this.setWorkersCompensationAdditionalDtls(workersCompensationAdditionalDtls);
        return this;
    }

    public void setWorkersCompensationAdditionalDtls(String workersCompensationAdditionalDtls) {
        this.workersCompensationAdditionalDtls = workersCompensationAdditionalDtls;
    }

    public String getWorkersCompensationClaimFillingCode() {
        return this.workersCompensationClaimFillingCode;
    }

    public WorkersCompensation workersCompensationClaimFillingCode(String workersCompensationClaimFillingCode) {
        this.setWorkersCompensationClaimFillingCode(workersCompensationClaimFillingCode);
        return this;
    }

    public void setWorkersCompensationClaimFillingCode(String workersCompensationClaimFillingCode) {
        this.workersCompensationClaimFillingCode = workersCompensationClaimFillingCode;
    }

    public String getWorkersCompensationTplCode() {
        return this.workersCompensationTplCode;
    }

    public WorkersCompensation workersCompensationTplCode(String workersCompensationTplCode) {
        this.setWorkersCompensationTplCode(workersCompensationTplCode);
        return this;
    }

    public void setWorkersCompensationTplCode(String workersCompensationTplCode) {
        this.workersCompensationTplCode = workersCompensationTplCode;
    }

    public String getWorkersCompensationTplName() {
        return this.workersCompensationTplName;
    }

    public WorkersCompensation workersCompensationTplName(String workersCompensationTplName) {
        this.setWorkersCompensationTplName(workersCompensationTplName);
        return this;
    }

    public void setWorkersCompensationTplName(String workersCompensationTplName) {
        this.workersCompensationTplName = workersCompensationTplName;
    }

    public String getWcPropertyCasualtyAgencyClaimNo() {
        return this.wcPropertyCasualtyAgencyClaimNo;
    }

    public WorkersCompensation wcPropertyCasualtyAgencyClaimNo(String wcPropertyCasualtyAgencyClaimNo) {
        this.setWcPropertyCasualtyAgencyClaimNo(wcPropertyCasualtyAgencyClaimNo);
        return this;
    }

    public void setWcPropertyCasualtyAgencyClaimNo(String wcPropertyCasualtyAgencyClaimNo) {
        this.wcPropertyCasualtyAgencyClaimNo = wcPropertyCasualtyAgencyClaimNo;
    }

    public Long getWcCarrierId() {
        return this.wcCarrierId;
    }

    public WorkersCompensation wcCarrierId(Long wcCarrierId) {
        this.setWcCarrierId(wcCarrierId);
        return this;
    }

    public void setWcCarrierId(Long wcCarrierId) {
        this.wcCarrierId = wcCarrierId;
    }

    public String getStatus() {
        return this.status;
    }

    public WorkersCompensation status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public WorkersCompensation createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public WorkersCompensation createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public WorkersCompensation createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public WorkersCompensation updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public WorkersCompensation updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public WorkersCompensation updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getWorkersCompensationUuid() {
        return this.workersCompensationUuid;
    }

    public WorkersCompensation workersCompensationUuid(UUID workersCompensationUuid) {
        this.setWorkersCompensationUuid(workersCompensationUuid);
        return this;
    }

    public void setWorkersCompensationUuid(UUID workersCompensationUuid) {
        this.workersCompensationUuid = workersCompensationUuid;
    }

    public String getEmployerAddressLine1() {
        return this.employerAddressLine1;
    }

    public WorkersCompensation employerAddressLine1(String employerAddressLine1) {
        this.setEmployerAddressLine1(employerAddressLine1);
        return this;
    }

    public void setEmployerAddressLine1(String employerAddressLine1) {
        this.employerAddressLine1 = employerAddressLine1;
    }

    public String getEmployerAddressLine2() {
        return this.employerAddressLine2;
    }

    public WorkersCompensation employerAddressLine2(String employerAddressLine2) {
        this.setEmployerAddressLine2(employerAddressLine2);
        return this;
    }

    public void setEmployerAddressLine2(String employerAddressLine2) {
        this.employerAddressLine2 = employerAddressLine2;
    }

    public String getEmployerCity() {
        return this.employerCity;
    }

    public WorkersCompensation employerCity(String employerCity) {
        this.setEmployerCity(employerCity);
        return this;
    }

    public void setEmployerCity(String employerCity) {
        this.employerCity = employerCity;
    }

    public String getEmployerState() {
        return this.employerState;
    }

    public WorkersCompensation employerState(String employerState) {
        this.setEmployerState(employerState);
        return this;
    }

    public void setEmployerState(String employerState) {
        this.employerState = employerState;
    }

    public String getEmployerCountry() {
        return this.employerCountry;
    }

    public WorkersCompensation employerCountry(String employerCountry) {
        this.setEmployerCountry(employerCountry);
        return this;
    }

    public void setEmployerCountry(String employerCountry) {
        this.employerCountry = employerCountry;
    }

    public String getEmployerZip() {
        return this.employerZip;
    }

    public WorkersCompensation employerZip(String employerZip) {
        this.setEmployerZip(employerZip);
        return this;
    }

    public void setEmployerZip(String employerZip) {
        this.employerZip = employerZip;
    }

    public String getEmployerContactNo1() {
        return this.employerContactNo1;
    }

    public WorkersCompensation employerContactNo1(String employerContactNo1) {
        this.setEmployerContactNo1(employerContactNo1);
        return this;
    }

    public void setEmployerContactNo1(String employerContactNo1) {
        this.employerContactNo1 = employerContactNo1;
    }

    public String getEmployerContactNo2() {
        return this.employerContactNo2;
    }

    public WorkersCompensation employerContactNo2(String employerContactNo2) {
        this.setEmployerContactNo2(employerContactNo2);
        return this;
    }

    public void setEmployerContactNo2(String employerContactNo2) {
        this.employerContactNo2 = employerContactNo2;
    }

    public String getEmployerFax() {
        return this.employerFax;
    }

    public WorkersCompensation employerFax(String employerFax) {
        this.setEmployerFax(employerFax);
        return this;
    }

    public void setEmployerFax(String employerFax) {
        this.employerFax = employerFax;
    }

    public String getEmployerEfax() {
        return this.employerEfax;
    }

    public WorkersCompensation employerEfax(String employerEfax) {
        this.setEmployerEfax(employerEfax);
        return this;
    }

    public void setEmployerEfax(String employerEfax) {
        this.employerEfax = employerEfax;
    }

    public String getEmployerEmail() {
        return this.employerEmail;
    }

    public WorkersCompensation employerEmail(String employerEmail) {
        this.setEmployerEmail(employerEmail);
        return this;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public WorkersCompensation relationship(String relationship) {
        this.setRelationship(relationship);
        return this;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getModeOfContact() {
        return this.modeOfContact;
    }

    public WorkersCompensation modeOfContact(String modeOfContact) {
        this.setModeOfContact(modeOfContact);
        return this;
    }

    public void setModeOfContact(String modeOfContact) {
        this.modeOfContact = modeOfContact;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkersCompensation)) {
            return false;
        }
        return workersCompensationId != null && workersCompensationId.equals(((WorkersCompensation) o).workersCompensationId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkersCompensation{" +
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
