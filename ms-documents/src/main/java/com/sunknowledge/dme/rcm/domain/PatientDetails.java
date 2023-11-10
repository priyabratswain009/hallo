package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PatientDetails.
 */
@Table("patient_details")
public class PatientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("patient_id")
    private Long patientId;

    @Column("patient_fname")
    private String patientFname;

    @Column("patient_lname")
    private String patientLname;

    @NotNull(message = "must not be null")
    @Column("dob")
    private Instant dob;

    @Column("address")
    private String address;

    @Column("city")
    private String city;

    @Column("zip")
    private String zip;

    @Column("email")
    private String email;

    @Column("phone_no")
    private String phoneNo;

    @Column("document_name")
    private String documentName;

    @Column("description")
    private String description;

    @Column("mrno")
    private String mrno;

    @Column("date_time")
    private String dateTime;

    @Column("status")
    private Integer status;

    @Column("is_tagged")
    private Boolean isTagged;

    @Column("document_path")
    private String documentPath;

    @Column("qr_code_status")
    private Boolean qrCodeStatus;

    @Transient
    private StateMaster stateMaster;

    @Transient
    private DocumentType documentType;

    @Column("state_master_state_id")
    private Long stateMasterId;

    @Column("document_type_document_type_id")
    private Long documentTypeId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPatientId() {
        return this.patientId;
    }

    public PatientDetails patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFname() {
        return this.patientFname;
    }

    public PatientDetails patientFname(String patientFname) {
        this.setPatientFname(patientFname);
        return this;
    }

    public void setPatientFname(String patientFname) {
        this.patientFname = patientFname;
    }

    public String getPatientLname() {
        return this.patientLname;
    }

    public PatientDetails patientLname(String patientLname) {
        this.setPatientLname(patientLname);
        return this;
    }

    public void setPatientLname(String patientLname) {
        this.patientLname = patientLname;
    }

    public Instant getDob() {
        return this.dob;
    }

    public PatientDetails dob(Instant dob) {
        this.setDob(dob);
        return this;
    }

    public void setDob(Instant dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return this.address;
    }

    public PatientDetails address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public PatientDetails city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return this.zip;
    }

    public PatientDetails zip(String zip) {
        this.setZip(zip);
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return this.email;
    }

    public PatientDetails email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public PatientDetails phoneNo(String phoneNo) {
        this.setPhoneNo(phoneNo);
        return this;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDocumentName() {
        return this.documentName;
    }

    public PatientDetails documentName(String documentName) {
        this.setDocumentName(documentName);
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDescription() {
        return this.description;
    }

    public PatientDetails description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMrno() {
        return this.mrno;
    }

    public PatientDetails mrno(String mrno) {
        this.setMrno(mrno);
        return this;
    }

    public void setMrno(String mrno) {
        this.mrno = mrno;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public PatientDetails dateTime(String dateTime) {
        this.setDateTime(dateTime);
        return this;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public PatientDetails status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsTagged() {
        return this.isTagged;
    }

    public PatientDetails isTagged(Boolean isTagged) {
        this.setIsTagged(isTagged);
        return this;
    }

    public void setIsTagged(Boolean isTagged) {
        this.isTagged = isTagged;
    }

    public String getDocumentPath() {
        return this.documentPath;
    }

    public PatientDetails documentPath(String documentPath) {
        this.setDocumentPath(documentPath);
        return this;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public Boolean getQrCodeStatus() {
        return this.qrCodeStatus;
    }

    public PatientDetails qrCodeStatus(Boolean qrCodeStatus) {
        this.setQrCodeStatus(qrCodeStatus);
        return this;
    }

    public void setQrCodeStatus(Boolean qrCodeStatus) {
        this.qrCodeStatus = qrCodeStatus;
    }

    public StateMaster getStateMaster() {
        return this.stateMaster;
    }

    public void setStateMaster(StateMaster stateMaster) {
        this.stateMaster = stateMaster;
        this.stateMasterId = stateMaster != null ? stateMaster.getStateId() : null;
    }

    public PatientDetails stateMaster(StateMaster stateMaster) {
        this.setStateMaster(stateMaster);
        return this;
    }

    public DocumentType getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
        this.documentTypeId = documentType != null ? documentType.getDocumentTypeId() : null;
    }

    public PatientDetails documentType(DocumentType documentType) {
        this.setDocumentType(documentType);
        return this;
    }

    public Long getStateMasterId() {
        return this.stateMasterId;
    }

    public void setStateMasterId(Long stateMaster) {
        this.stateMasterId = stateMaster;
    }

    public Long getDocumentTypeId() {
        return this.documentTypeId;
    }

    public void setDocumentTypeId(Long documentType) {
        this.documentTypeId = documentType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientDetails)) {
            return false;
        }
        return patientId != null && patientId.equals(((PatientDetails) o).patientId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientDetails{" +
            "patientId=" + getPatientId() +
            ", patientFname='" + getPatientFname() + "'" +
            ", patientLname='" + getPatientLname() + "'" +
            ", dob='" + getDob() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", zip='" + getZip() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNo='" + getPhoneNo() + "'" +
            ", documentName='" + getDocumentName() + "'" +
            ", description='" + getDescription() + "'" +
            ", mrno='" + getMrno() + "'" +
            ", dateTime='" + getDateTime() + "'" +
            ", status=" + getStatus() +
            ", isTagged='" + getIsTagged() + "'" +
            ", documentPath='" + getDocumentPath() + "'" +
            ", qrCodeStatus='" + getQrCodeStatus() + "'" +
            "}";
    }
}
