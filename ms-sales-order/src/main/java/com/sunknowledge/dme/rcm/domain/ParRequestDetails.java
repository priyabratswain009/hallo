package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ParRequestDetails.
 */
@Table("t_par_request_details")
public class ParRequestDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("par_request_details_id")
    private Long parRequestDetailsId;

    @Column("par_request_type")
    private String parRequestType;

    @Column("par_id")
    private Long parId;

    @Column("par_no")
    private String parNo;

    @Column("par_initiation_date")
    private LocalDate parInitiationDate;

    @Column("par_no_effetive_start_date")
    private LocalDate parNoEffetiveStartDate;

    @Column("par_no_effetive_end_date")
    private LocalDate parNoEffetiveEndDate;

    @Column("par_authorised_by")
    private String parAuthorisedBy;

    @Column("par_request_doc_name")
    private String parRequestDocName;

    @Column("par_request_doc_location")
    private String parRequestDocLocation;

    @Column("par_request_fax_number")
    private String parRequestFaxNumber;

    @Column("par_request_fax_status")
    private String parRequestFaxStatus;

    @Column("par_request_fax_datetime")
    private ZonedDateTime parRequestFaxDatetime;

    @Column("fax_response_status")
    private String faxResponseStatus;

    @Column("par_request_response_doc_name")
    private String parRequestResponseDocName;

    @Column("doc_qr_code")
    private String docQrCode;

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

    @Column("par_request_details_uuid")
    private UUID parRequestDetailsUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getParRequestDetailsId() {
        return this.parRequestDetailsId;
    }

    public ParRequestDetails parRequestDetailsId(Long parRequestDetailsId) {
        this.setParRequestDetailsId(parRequestDetailsId);
        return this;
    }

    public void setParRequestDetailsId(Long parRequestDetailsId) {
        this.parRequestDetailsId = parRequestDetailsId;
    }

    public String getParRequestType() {
        return this.parRequestType;
    }

    public ParRequestDetails parRequestType(String parRequestType) {
        this.setParRequestType(parRequestType);
        return this;
    }

    public void setParRequestType(String parRequestType) {
        this.parRequestType = parRequestType;
    }

    public Long getParId() {
        return this.parId;
    }

    public ParRequestDetails parId(Long parId) {
        this.setParId(parId);
        return this;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParNo() {
        return this.parNo;
    }

    public ParRequestDetails parNo(String parNo) {
        this.setParNo(parNo);
        return this;
    }

    public void setParNo(String parNo) {
        this.parNo = parNo;
    }

    public LocalDate getParInitiationDate() {
        return this.parInitiationDate;
    }

    public ParRequestDetails parInitiationDate(LocalDate parInitiationDate) {
        this.setParInitiationDate(parInitiationDate);
        return this;
    }

    public void setParInitiationDate(LocalDate parInitiationDate) {
        this.parInitiationDate = parInitiationDate;
    }

    public LocalDate getParNoEffetiveStartDate() {
        return this.parNoEffetiveStartDate;
    }

    public ParRequestDetails parNoEffetiveStartDate(LocalDate parNoEffetiveStartDate) {
        this.setParNoEffetiveStartDate(parNoEffetiveStartDate);
        return this;
    }

    public void setParNoEffetiveStartDate(LocalDate parNoEffetiveStartDate) {
        this.parNoEffetiveStartDate = parNoEffetiveStartDate;
    }

    public LocalDate getParNoEffetiveEndDate() {
        return this.parNoEffetiveEndDate;
    }

    public ParRequestDetails parNoEffetiveEndDate(LocalDate parNoEffetiveEndDate) {
        this.setParNoEffetiveEndDate(parNoEffetiveEndDate);
        return this;
    }

    public void setParNoEffetiveEndDate(LocalDate parNoEffetiveEndDate) {
        this.parNoEffetiveEndDate = parNoEffetiveEndDate;
    }

    public String getParAuthorisedBy() {
        return this.parAuthorisedBy;
    }

    public ParRequestDetails parAuthorisedBy(String parAuthorisedBy) {
        this.setParAuthorisedBy(parAuthorisedBy);
        return this;
    }

    public void setParAuthorisedBy(String parAuthorisedBy) {
        this.parAuthorisedBy = parAuthorisedBy;
    }

    public String getParRequestDocName() {
        return this.parRequestDocName;
    }

    public ParRequestDetails parRequestDocName(String parRequestDocName) {
        this.setParRequestDocName(parRequestDocName);
        return this;
    }

    public void setParRequestDocName(String parRequestDocName) {
        this.parRequestDocName = parRequestDocName;
    }

    public String getParRequestDocLocation() {
        return this.parRequestDocLocation;
    }

    public ParRequestDetails parRequestDocLocation(String parRequestDocLocation) {
        this.setParRequestDocLocation(parRequestDocLocation);
        return this;
    }

    public void setParRequestDocLocation(String parRequestDocLocation) {
        this.parRequestDocLocation = parRequestDocLocation;
    }

    public String getParRequestFaxNumber() {
        return this.parRequestFaxNumber;
    }

    public ParRequestDetails parRequestFaxNumber(String parRequestFaxNumber) {
        this.setParRequestFaxNumber(parRequestFaxNumber);
        return this;
    }

    public void setParRequestFaxNumber(String parRequestFaxNumber) {
        this.parRequestFaxNumber = parRequestFaxNumber;
    }

    public String getParRequestFaxStatus() {
        return this.parRequestFaxStatus;
    }

    public ParRequestDetails parRequestFaxStatus(String parRequestFaxStatus) {
        this.setParRequestFaxStatus(parRequestFaxStatus);
        return this;
    }

    public void setParRequestFaxStatus(String parRequestFaxStatus) {
        this.parRequestFaxStatus = parRequestFaxStatus;
    }

    public ZonedDateTime getParRequestFaxDatetime() {
        return this.parRequestFaxDatetime;
    }

    public ParRequestDetails parRequestFaxDatetime(ZonedDateTime parRequestFaxDatetime) {
        this.setParRequestFaxDatetime(parRequestFaxDatetime);
        return this;
    }

    public void setParRequestFaxDatetime(ZonedDateTime parRequestFaxDatetime) {
        this.parRequestFaxDatetime = parRequestFaxDatetime;
    }

    public String getFaxResponseStatus() {
        return this.faxResponseStatus;
    }

    public ParRequestDetails faxResponseStatus(String faxResponseStatus) {
        this.setFaxResponseStatus(faxResponseStatus);
        return this;
    }

    public void setFaxResponseStatus(String faxResponseStatus) {
        this.faxResponseStatus = faxResponseStatus;
    }

    public String getParRequestResponseDocName() {
        return this.parRequestResponseDocName;
    }

    public ParRequestDetails parRequestResponseDocName(String parRequestResponseDocName) {
        this.setParRequestResponseDocName(parRequestResponseDocName);
        return this;
    }

    public void setParRequestResponseDocName(String parRequestResponseDocName) {
        this.parRequestResponseDocName = parRequestResponseDocName;
    }

    public String getDocQrCode() {
        return this.docQrCode;
    }

    public ParRequestDetails docQrCode(String docQrCode) {
        this.setDocQrCode(docQrCode);
        return this;
    }

    public void setDocQrCode(String docQrCode) {
        this.docQrCode = docQrCode;
    }

    public String getStatus() {
        return this.status;
    }

    public ParRequestDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public ParRequestDetails createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public ParRequestDetails createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public ParRequestDetails createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public ParRequestDetails updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public ParRequestDetails updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public ParRequestDetails updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getParRequestDetailsUuid() {
        return this.parRequestDetailsUuid;
    }

    public ParRequestDetails parRequestDetailsUuid(UUID parRequestDetailsUuid) {
        this.setParRequestDetailsUuid(parRequestDetailsUuid);
        return this;
    }

    public void setParRequestDetailsUuid(UUID parRequestDetailsUuid) {
        this.parRequestDetailsUuid = parRequestDetailsUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParRequestDetails)) {
            return false;
        }
        return parRequestDetailsId != null && parRequestDetailsId.equals(((ParRequestDetails) o).parRequestDetailsId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParRequestDetails{" +
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
