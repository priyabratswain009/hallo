package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.UspsAddressVerificationResponse} entity.
 */
public class UspsAddressVerificationResponseDTO implements Serializable {

    private Long uspsAddressVerificationId;

    private Long patientId;

    private String reqAddressLine1;

    private String reqAddressLine2;

    private String reqCity;

    private String reqState;

    private String reqZip4;

    private String reqZip5;

    private LocalDate reqDate;

    private String rspAddressLine1;

    private String rspAddressLine2;

    private String rspCity;

    private String rspState;

    private String rspZip4;

    private String rspZip5;

    private LocalDate rspDate;

    private String dpvFootnotesCode;

    private String dpvFootnotesDescription;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private Long updatedById;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private UUID uspsAddressVerificationResponseUuid;

    public Long getUspsAddressVerificationId() {
        return uspsAddressVerificationId;
    }

    public void setUspsAddressVerificationId(Long uspsAddressVerificationId) {
        this.uspsAddressVerificationId = uspsAddressVerificationId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getReqAddressLine1() {
        return reqAddressLine1;
    }

    public void setReqAddressLine1(String reqAddressLine1) {
        this.reqAddressLine1 = reqAddressLine1;
    }

    public String getReqAddressLine2() {
        return reqAddressLine2;
    }

    public void setReqAddressLine2(String reqAddressLine2) {
        this.reqAddressLine2 = reqAddressLine2;
    }

    public String getReqCity() {
        return reqCity;
    }

    public void setReqCity(String reqCity) {
        this.reqCity = reqCity;
    }

    public String getReqState() {
        return reqState;
    }

    public void setReqState(String reqState) {
        this.reqState = reqState;
    }

    public String getReqZip4() {
        return reqZip4;
    }

    public void setReqZip4(String reqZip4) {
        this.reqZip4 = reqZip4;
    }

    public String getReqZip5() {
        return reqZip5;
    }

    public void setReqZip5(String reqZip5) {
        this.reqZip5 = reqZip5;
    }

    public LocalDate getReqDate() {
        return reqDate;
    }

    public void setReqDate(LocalDate reqDate) {
        this.reqDate = reqDate;
    }

    public String getRspAddressLine1() {
        return rspAddressLine1;
    }

    public void setRspAddressLine1(String rspAddressLine1) {
        this.rspAddressLine1 = rspAddressLine1;
    }

    public String getRspAddressLine2() {
        return rspAddressLine2;
    }

    public void setRspAddressLine2(String rspAddressLine2) {
        this.rspAddressLine2 = rspAddressLine2;
    }

    public String getRspCity() {
        return rspCity;
    }

    public void setRspCity(String rspCity) {
        this.rspCity = rspCity;
    }

    public String getRspState() {
        return rspState;
    }

    public void setRspState(String rspState) {
        this.rspState = rspState;
    }

    public String getRspZip4() {
        return rspZip4;
    }

    public void setRspZip4(String rspZip4) {
        this.rspZip4 = rspZip4;
    }

    public String getRspZip5() {
        return rspZip5;
    }

    public void setRspZip5(String rspZip5) {
        this.rspZip5 = rspZip5;
    }

    public LocalDate getRspDate() {
        return rspDate;
    }

    public void setRspDate(LocalDate rspDate) {
        this.rspDate = rspDate;
    }

    public String getDpvFootnotesCode() {
        return dpvFootnotesCode;
    }

    public void setDpvFootnotesCode(String dpvFootnotesCode) {
        this.dpvFootnotesCode = dpvFootnotesCode;
    }

    public String getDpvFootnotesDescription() {
        return dpvFootnotesDescription;
    }

    public void setDpvFootnotesDescription(String dpvFootnotesDescription) {
        this.dpvFootnotesDescription = dpvFootnotesDescription;
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

    public UUID getUspsAddressVerificationResponseUuid() {
        return uspsAddressVerificationResponseUuid;
    }

    public void setUspsAddressVerificationResponseUuid(UUID uspsAddressVerificationResponseUuid) {
        this.uspsAddressVerificationResponseUuid = uspsAddressVerificationResponseUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UspsAddressVerificationResponseDTO)) {
            return false;
        }

        UspsAddressVerificationResponseDTO uspsAddressVerificationResponseDTO = (UspsAddressVerificationResponseDTO) o;
        if (this.uspsAddressVerificationId == null) {
            return false;
        }
        return Objects.equals(this.uspsAddressVerificationId, uspsAddressVerificationResponseDTO.uspsAddressVerificationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.uspsAddressVerificationId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UspsAddressVerificationResponseDTO{" +
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
