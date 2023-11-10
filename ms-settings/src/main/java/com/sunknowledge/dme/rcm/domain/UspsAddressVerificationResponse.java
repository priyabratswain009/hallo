package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UspsAddressVerificationResponse.
 */
@Entity
@Table(name = "t_usps_address_verification_response")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UspsAddressVerificationResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "usps_address_verification_id")
    private Long uspsAddressVerificationId;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "req_address_line_1")
    private String reqAddressLine1;

    @Column(name = "req_address_line_2")
    private String reqAddressLine2;

    @Column(name = "req_city")
    private String reqCity;

    @Column(name = "req_state")
    private String reqState;

    @Column(name = "req_zip_4")
    private String reqZip4;

    @Column(name = "req_zip_5")
    private String reqZip5;

    @Column(name = "req_date")
    private LocalDate reqDate;

    @Column(name = "rsp_address_line_1")
    private String rspAddressLine1;

    @Column(name = "rsp_address_line_2")
    private String rspAddressLine2;

    @Column(name = "rsp_city")
    private String rspCity;

    @Column(name = "rsp_state")
    private String rspState;

    @Column(name = "rsp_zip_4")
    private String rspZip4;

    @Column(name = "rsp_zip_5")
    private String rspZip5;

    @Column(name = "rsp_date")
    private LocalDate rspDate;

    @Column(name = "dpv_footnotes_code")
    private String dpvFootnotesCode;

    @Column(name = "dpv_footnotes_description")
    private String dpvFootnotesDescription;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "usps_address_verification_response_uuid")
    private UUID uspsAddressVerificationResponseUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getUspsAddressVerificationId() {
        return this.uspsAddressVerificationId;
    }

    public UspsAddressVerificationResponse uspsAddressVerificationId(Long uspsAddressVerificationId) {
        this.setUspsAddressVerificationId(uspsAddressVerificationId);
        return this;
    }

    public void setUspsAddressVerificationId(Long uspsAddressVerificationId) {
        this.uspsAddressVerificationId = uspsAddressVerificationId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public UspsAddressVerificationResponse patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getReqAddressLine1() {
        return this.reqAddressLine1;
    }

    public UspsAddressVerificationResponse reqAddressLine1(String reqAddressLine1) {
        this.setReqAddressLine1(reqAddressLine1);
        return this;
    }

    public void setReqAddressLine1(String reqAddressLine1) {
        this.reqAddressLine1 = reqAddressLine1;
    }

    public String getReqAddressLine2() {
        return this.reqAddressLine2;
    }

    public UspsAddressVerificationResponse reqAddressLine2(String reqAddressLine2) {
        this.setReqAddressLine2(reqAddressLine2);
        return this;
    }

    public void setReqAddressLine2(String reqAddressLine2) {
        this.reqAddressLine2 = reqAddressLine2;
    }

    public String getReqCity() {
        return this.reqCity;
    }

    public UspsAddressVerificationResponse reqCity(String reqCity) {
        this.setReqCity(reqCity);
        return this;
    }

    public void setReqCity(String reqCity) {
        this.reqCity = reqCity;
    }

    public String getReqState() {
        return this.reqState;
    }

    public UspsAddressVerificationResponse reqState(String reqState) {
        this.setReqState(reqState);
        return this;
    }

    public void setReqState(String reqState) {
        this.reqState = reqState;
    }

    public String getReqZip4() {
        return this.reqZip4;
    }

    public UspsAddressVerificationResponse reqZip4(String reqZip4) {
        this.setReqZip4(reqZip4);
        return this;
    }

    public void setReqZip4(String reqZip4) {
        this.reqZip4 = reqZip4;
    }

    public String getReqZip5() {
        return this.reqZip5;
    }

    public UspsAddressVerificationResponse reqZip5(String reqZip5) {
        this.setReqZip5(reqZip5);
        return this;
    }

    public void setReqZip5(String reqZip5) {
        this.reqZip5 = reqZip5;
    }

    public LocalDate getReqDate() {
        return this.reqDate;
    }

    public UspsAddressVerificationResponse reqDate(LocalDate reqDate) {
        this.setReqDate(reqDate);
        return this;
    }

    public void setReqDate(LocalDate reqDate) {
        this.reqDate = reqDate;
    }

    public String getRspAddressLine1() {
        return this.rspAddressLine1;
    }

    public UspsAddressVerificationResponse rspAddressLine1(String rspAddressLine1) {
        this.setRspAddressLine1(rspAddressLine1);
        return this;
    }

    public void setRspAddressLine1(String rspAddressLine1) {
        this.rspAddressLine1 = rspAddressLine1;
    }

    public String getRspAddressLine2() {
        return this.rspAddressLine2;
    }

    public UspsAddressVerificationResponse rspAddressLine2(String rspAddressLine2) {
        this.setRspAddressLine2(rspAddressLine2);
        return this;
    }

    public void setRspAddressLine2(String rspAddressLine2) {
        this.rspAddressLine2 = rspAddressLine2;
    }

    public String getRspCity() {
        return this.rspCity;
    }

    public UspsAddressVerificationResponse rspCity(String rspCity) {
        this.setRspCity(rspCity);
        return this;
    }

    public void setRspCity(String rspCity) {
        this.rspCity = rspCity;
    }

    public String getRspState() {
        return this.rspState;
    }

    public UspsAddressVerificationResponse rspState(String rspState) {
        this.setRspState(rspState);
        return this;
    }

    public void setRspState(String rspState) {
        this.rspState = rspState;
    }

    public String getRspZip4() {
        return this.rspZip4;
    }

    public UspsAddressVerificationResponse rspZip4(String rspZip4) {
        this.setRspZip4(rspZip4);
        return this;
    }

    public void setRspZip4(String rspZip4) {
        this.rspZip4 = rspZip4;
    }

    public String getRspZip5() {
        return this.rspZip5;
    }

    public UspsAddressVerificationResponse rspZip5(String rspZip5) {
        this.setRspZip5(rspZip5);
        return this;
    }

    public void setRspZip5(String rspZip5) {
        this.rspZip5 = rspZip5;
    }

    public LocalDate getRspDate() {
        return this.rspDate;
    }

    public UspsAddressVerificationResponse rspDate(LocalDate rspDate) {
        this.setRspDate(rspDate);
        return this;
    }

    public void setRspDate(LocalDate rspDate) {
        this.rspDate = rspDate;
    }

    public String getDpvFootnotesCode() {
        return this.dpvFootnotesCode;
    }

    public UspsAddressVerificationResponse dpvFootnotesCode(String dpvFootnotesCode) {
        this.setDpvFootnotesCode(dpvFootnotesCode);
        return this;
    }

    public void setDpvFootnotesCode(String dpvFootnotesCode) {
        this.dpvFootnotesCode = dpvFootnotesCode;
    }

    public String getDpvFootnotesDescription() {
        return this.dpvFootnotesDescription;
    }

    public UspsAddressVerificationResponse dpvFootnotesDescription(String dpvFootnotesDescription) {
        this.setDpvFootnotesDescription(dpvFootnotesDescription);
        return this;
    }

    public void setDpvFootnotesDescription(String dpvFootnotesDescription) {
        this.dpvFootnotesDescription = dpvFootnotesDescription;
    }

    public String getStatus() {
        return this.status;
    }

    public UspsAddressVerificationResponse status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public UspsAddressVerificationResponse createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public UspsAddressVerificationResponse createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public UspsAddressVerificationResponse updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public UspsAddressVerificationResponse updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public UspsAddressVerificationResponse createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public UspsAddressVerificationResponse updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getUspsAddressVerificationResponseUuid() {
        return this.uspsAddressVerificationResponseUuid;
    }

    public UspsAddressVerificationResponse uspsAddressVerificationResponseUuid(UUID uspsAddressVerificationResponseUuid) {
        this.setUspsAddressVerificationResponseUuid(uspsAddressVerificationResponseUuid);
        return this;
    }

    public void setUspsAddressVerificationResponseUuid(UUID uspsAddressVerificationResponseUuid) {
        this.uspsAddressVerificationResponseUuid = uspsAddressVerificationResponseUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UspsAddressVerificationResponse)) {
            return false;
        }
        return (
            uspsAddressVerificationId != null &&
            uspsAddressVerificationId.equals(((UspsAddressVerificationResponse) o).uspsAddressVerificationId)
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
        return "UspsAddressVerificationResponse{" +
            "uspsAddressVerificationId=" + getUspsAddressVerificationId() +
            ", patientId=" + getPatientId() +
            ", reqAddressLine1='" + getReqAddressLine1() + "'" +
            ", reqAddressLine2='" + getReqAddressLine2() + "'" +
            ", reqCity='" + getReqCity() + "'" +
            ", reqState='" + getReqState() + "'" +
            ", reqZip4='" + getReqZip4() + "'" +
            ", reqZip5='" + getReqZip5() + "'" +
            ", reqDate='" + getReqDate() + "'" +
            ", rspAddressLine1='" + getRspAddressLine1() + "'" +
            ", rspAddressLine2='" + getRspAddressLine2() + "'" +
            ", rspCity='" + getRspCity() + "'" +
            ", rspState='" + getRspState() + "'" +
            ", rspZip4='" + getRspZip4() + "'" +
            ", rspZip5='" + getRspZip5() + "'" +
            ", rspDate='" + getRspDate() + "'" +
            ", dpvFootnotesCode='" + getDpvFootnotesCode() + "'" +
            ", dpvFootnotesDescription='" + getDpvFootnotesDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", uspsAddressVerificationResponseUuid='" + getUspsAddressVerificationResponseUuid() + "'" +
            "}";
    }
}
