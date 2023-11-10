package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A AddressVerificationResponse.
 */
@Entity
@Table(name = "t_usps_address_verification_response")
public class AddressVerificationResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "usps_address_verification_id", nullable = false)
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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getUspsAddressVerificationId() {
        return this.uspsAddressVerificationId;
    }

    public AddressVerificationResponse uspsAddressVerificationId(Long uspsAddressVerificationId) {
        this.setUspsAddressVerificationId(uspsAddressVerificationId);
        return this;
    }

    public void setUspsAddressVerificationId(Long uspsAddressVerificationId) {
        this.uspsAddressVerificationId = uspsAddressVerificationId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public AddressVerificationResponse patientId(Long patientId) {
        this.setPatientId(patientId);
        return this;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getReqAddressLine1() {
        return this.reqAddressLine1;
    }

    public AddressVerificationResponse reqAddressLine1(String reqAddressLine1) {
        this.setReqAddressLine1(reqAddressLine1);
        return this;
    }

    public void setReqAddressLine1(String reqAddressLine1) {
        this.reqAddressLine1 = reqAddressLine1;
    }

    public String getReqAddressLine2() {
        return this.reqAddressLine2;
    }

    public AddressVerificationResponse reqAddressLine2(String reqAddressLine2) {
        this.setReqAddressLine2(reqAddressLine2);
        return this;
    }

    public void setReqAddressLine2(String reqAddressLine2) {
        this.reqAddressLine2 = reqAddressLine2;
    }

    public String getReqCity() {
        return this.reqCity;
    }

    public AddressVerificationResponse reqCity(String reqCity) {
        this.setReqCity(reqCity);
        return this;
    }

    public void setReqCity(String reqCity) {
        this.reqCity = reqCity;
    }

    public String getReqState() {
        return this.reqState;
    }

    public AddressVerificationResponse reqState(String reqState) {
        this.setReqState(reqState);
        return this;
    }

    public void setReqState(String reqState) {
        this.reqState = reqState;
    }

    public String getReqZip4() {
        return this.reqZip4;
    }

    public AddressVerificationResponse reqZip4(String reqZip4) {
        this.setReqZip4(reqZip4);
        return this;
    }

    public void setReqZip4(String reqZip4) {
        this.reqZip4 = reqZip4;
    }

    public String getReqZip5() {
        return this.reqZip5;
    }

    public AddressVerificationResponse reqZip5(String reqZip5) {
        this.setReqZip5(reqZip5);
        return this;
    }

    public void setReqZip5(String reqZip5) {
        this.reqZip5 = reqZip5;
    }

    public LocalDate getReqDate() {
        return this.reqDate;
    }

    public AddressVerificationResponse reqDate(LocalDate reqDate) {
        this.setReqDate(reqDate);
        return this;
    }

    public void setReqDate(LocalDate reqDate) {
        this.reqDate = reqDate;
    }

    public String getRspAddressLine1() {
        return this.rspAddressLine1;
    }

    public AddressVerificationResponse rspAddressLine1(String rspAddressLine1) {
        this.setRspAddressLine1(rspAddressLine1);
        return this;
    }

    public void setRspAddressLine1(String rspAddressLine1) {
        this.rspAddressLine1 = rspAddressLine1;
    }

    public String getRspAddressLine2() {
        return this.rspAddressLine2;
    }

    public AddressVerificationResponse rspAddressLine2(String rspAddressLine2) {
        this.setRspAddressLine2(rspAddressLine2);
        return this;
    }

    public void setRspAddressLine2(String rspAddressLine2) {
        this.rspAddressLine2 = rspAddressLine2;
    }

    public String getRspCity() {
        return this.rspCity;
    }

    public AddressVerificationResponse rspCity(String rspCity) {
        this.setRspCity(rspCity);
        return this;
    }

    public void setRspCity(String rspCity) {
        this.rspCity = rspCity;
    }

    public String getRspState() {
        return this.rspState;
    }

    public AddressVerificationResponse rspState(String rspState) {
        this.setRspState(rspState);
        return this;
    }

    public void setRspState(String rspState) {
        this.rspState = rspState;
    }

    public String getRspZip4() {
        return this.rspZip4;
    }

    public AddressVerificationResponse rspZip4(String rspZip4) {
        this.setRspZip4(rspZip4);
        return this;
    }

    public void setRspZip4(String rspZip4) {
        this.rspZip4 = rspZip4;
    }

    public String getRspZip5() {
        return this.rspZip5;
    }

    public AddressVerificationResponse rspZip5(String rspZip5) {
        this.setRspZip5(rspZip5);
        return this;
    }

    public void setRspZip5(String rspZip5) {
        this.rspZip5 = rspZip5;
    }

    public LocalDate getRspDate() {
        return this.rspDate;
    }

    public AddressVerificationResponse rspDate(LocalDate rspDate) {
        this.setRspDate(rspDate);
        return this;
    }

    public void setRspDate(LocalDate rspDate) {
        this.rspDate = rspDate;
    }

    public String getDpvFootnotesCode() {
        return this.dpvFootnotesCode;
    }

    public AddressVerificationResponse dpvFootnotesCode(String dpvFootnotesCode) {
        this.setDpvFootnotesCode(dpvFootnotesCode);
        return this;
    }

    public void setDpvFootnotesCode(String dpvFootnotesCode) {
        this.dpvFootnotesCode = dpvFootnotesCode;
    }

    public String getDpvFootnotesDescription() {
        return this.dpvFootnotesDescription;
    }

    public AddressVerificationResponse dpvFootnotesDescription(String dpvFootnotesDescription) {
        this.setDpvFootnotesDescription(dpvFootnotesDescription);
        return this;
    }

    public void setDpvFootnotesDescription(String dpvFootnotesDescription) {
        this.dpvFootnotesDescription = dpvFootnotesDescription;
    }

    public String getStatus() {
        return this.status;
    }

    public AddressVerificationResponse status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressVerificationResponse)) {
            return false;
        }
        return (
            uspsAddressVerificationId != null &&
            uspsAddressVerificationId.equals(((AddressVerificationResponse) o).uspsAddressVerificationId)
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
        return "AddressVerificationResponse{" +
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
            "}";
    }
}
