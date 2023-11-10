package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.ParRequestDetails} entity.
 */
public class ParRequestDetailsDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long parRequestDetailsId;

    private String parRequestType;

    private Long parId;

    private String parNo;

    private LocalDate parInitiationDate;

    private LocalDate parNoEffetiveStartDate;

    private LocalDate parNoEffetiveEndDate;

    private String parAuthorisedBy;

    private String parRequestDocName;

    private String parRequestDocLocation;

    private String parRequestFaxNumber;

    private String parRequestFaxStatus;

    private ZonedDateTime parRequestFaxDatetime;

    private String faxResponseStatus;

    private String parRequestResponseDocName;

    private String docQrCode;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID parRequestDetailsUuid;

    public Long getParRequestDetailsId() {
        return parRequestDetailsId;
    }

    public void setParRequestDetailsId(Long parRequestDetailsId) {
        this.parRequestDetailsId = parRequestDetailsId;
    }

    public String getParRequestType() {
        return parRequestType;
    }

    public void setParRequestType(String parRequestType) {
        this.parRequestType = parRequestType;
    }

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

    public LocalDate getParInitiationDate() {
        return parInitiationDate;
    }

    public void setParInitiationDate(LocalDate parInitiationDate) {
        this.parInitiationDate = parInitiationDate;
    }

    public LocalDate getParNoEffetiveStartDate() {
        return parNoEffetiveStartDate;
    }

    public void setParNoEffetiveStartDate(LocalDate parNoEffetiveStartDate) {
        this.parNoEffetiveStartDate = parNoEffetiveStartDate;
    }

    public LocalDate getParNoEffetiveEndDate() {
        return parNoEffetiveEndDate;
    }

    public void setParNoEffetiveEndDate(LocalDate parNoEffetiveEndDate) {
        this.parNoEffetiveEndDate = parNoEffetiveEndDate;
    }

    public String getParAuthorisedBy() {
        return parAuthorisedBy;
    }

    public void setParAuthorisedBy(String parAuthorisedBy) {
        this.parAuthorisedBy = parAuthorisedBy;
    }

    public String getParRequestDocName() {
        return parRequestDocName;
    }

    public void setParRequestDocName(String parRequestDocName) {
        this.parRequestDocName = parRequestDocName;
    }

    public String getParRequestDocLocation() {
        return parRequestDocLocation;
    }

    public void setParRequestDocLocation(String parRequestDocLocation) {
        this.parRequestDocLocation = parRequestDocLocation;
    }

    public String getParRequestFaxNumber() {
        return parRequestFaxNumber;
    }

    public void setParRequestFaxNumber(String parRequestFaxNumber) {
        this.parRequestFaxNumber = parRequestFaxNumber;
    }

    public String getParRequestFaxStatus() {
        return parRequestFaxStatus;
    }

    public void setParRequestFaxStatus(String parRequestFaxStatus) {
        this.parRequestFaxStatus = parRequestFaxStatus;
    }

    public ZonedDateTime getParRequestFaxDatetime() {
        return parRequestFaxDatetime;
    }

    public void setParRequestFaxDatetime(ZonedDateTime parRequestFaxDatetime) {
        this.parRequestFaxDatetime = parRequestFaxDatetime;
    }

    public String getFaxResponseStatus() {
        return faxResponseStatus;
    }

    public void setFaxResponseStatus(String faxResponseStatus) {
        this.faxResponseStatus = faxResponseStatus;
    }

    public String getParRequestResponseDocName() {
        return parRequestResponseDocName;
    }

    public void setParRequestResponseDocName(String parRequestResponseDocName) {
        this.parRequestResponseDocName = parRequestResponseDocName;
    }

    public String getDocQrCode() {
        return docQrCode;
    }

    public void setDocQrCode(String docQrCode) {
        this.docQrCode = docQrCode;
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

    public UUID getParRequestDetailsUuid() {
        return parRequestDetailsUuid;
    }

    public void setParRequestDetailsUuid(UUID parRequestDetailsUuid) {
        this.parRequestDetailsUuid = parRequestDetailsUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParRequestDetailsDTO)) {
            return false;
        }

        ParRequestDetailsDTO parRequestDetailsDTO = (ParRequestDetailsDTO) o;
        if (this.parRequestDetailsId == null) {
            return false;
        }
        return Objects.equals(this.parRequestDetailsId, parRequestDetailsDTO.parRequestDetailsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.parRequestDetailsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParRequestDetailsDTO{" +
            "parRequestDetailsId=" + getParRequestDetailsId() +
            ", parRequestType='" + getParRequestType() + "'" +
            ", parId=" + getParId() +
            ", parNo='" + getParNo() + "'" +
            ", parInitiationDate='" + getParInitiationDate() + "'" +
            ", parNoEffetiveStartDate='" + getParNoEffetiveStartDate() + "'" +
            ", parNoEffetiveEndDate='" + getParNoEffetiveEndDate() + "'" +
            ", parAuthorisedBy='" + getParAuthorisedBy() + "'" +
            ", parRequestDocName='" + getParRequestDocName() + "'" +
            ", parRequestDocLocation='" + getParRequestDocLocation() + "'" +
            ", parRequestFaxNumber='" + getParRequestFaxNumber() + "'" +
            ", parRequestFaxStatus='" + getParRequestFaxStatus() + "'" +
            ", parRequestFaxDatetime='" + getParRequestFaxDatetime() + "'" +
            ", faxResponseStatus='" + getFaxResponseStatus() + "'" +
            ", parRequestResponseDocName='" + getParRequestResponseDocName() + "'" +
            ", docQrCode='" + getDocQrCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", parRequestDetailsUuid='" + getParRequestDetailsUuid() + "'" +
            "}";
    }
}
