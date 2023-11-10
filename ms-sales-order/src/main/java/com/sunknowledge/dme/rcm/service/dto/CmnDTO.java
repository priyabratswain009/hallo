package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.Cmn} entity.
 */
public class CmnDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long cmnId;

    private String cmnNumber;

    private String cmnType;

    private String cmnFormName;

    private Long patientId;

    private Long salesOrderId;

    private String salesOrderNo;

    private LocalDate cmnCreateDate;

    private LocalDate cmnInitialDate;

    private LocalDate cmnRevisionDate;

    private LocalDate cmnRecertificationDate;

    private LocalDate cmnExpirationDate;

    private Long cmnLoggedBy;

    private LocalDate cmnLoggedDate;

    private Long cmnApprovedBy;

    private LocalDate cmnApprovedDate;

    private Long cmnPrintedBy;

    private LocalDate cmnPrintedDate;

    private String lengthOfNeed;

    private String faxCmnOption;

    private String cmnCoverLetterInclusionOption;

    private Long cmnFaxedBy;

    private LocalDate cmnFaxedDate;

    private String faxStatus;

    private String faxStatusReason;

    private String printCmnOption;

    private Long createdById;

    private LocalDate createdDate;

    private String status;

    private Long updatedById;

    private LocalDate updatedDate;

    private String createdByName;

    private String updatedByName;

    private UUID cmnIdUuid;

    private String patientPrognosis;

    private String cmnStatus;

    private String refillAuthorised;

    public Long getCmnId() {
        return cmnId;
    }

    public void setCmnId(Long cmnId) {
        this.cmnId = cmnId;
    }

    public String getCmnNumber() {
        return cmnNumber;
    }

    public void setCmnNumber(String cmnNumber) {
        this.cmnNumber = cmnNumber;
    }

    public String getCmnType() {
        return cmnType;
    }

    public void setCmnType(String cmnType) {
        this.cmnType = cmnType;
    }

    public String getCmnFormName() {
        return cmnFormName;
    }

    public void setCmnFormName(String cmnFormName) {
        this.cmnFormName = cmnFormName;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSalesOrderNo() {
        return salesOrderNo;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public LocalDate getCmnCreateDate() {
        return cmnCreateDate;
    }

    public void setCmnCreateDate(LocalDate cmnCreateDate) {
        this.cmnCreateDate = cmnCreateDate;
    }

    public LocalDate getCmnInitialDate() {
        return cmnInitialDate;
    }

    public void setCmnInitialDate(LocalDate cmnInitialDate) {
        this.cmnInitialDate = cmnInitialDate;
    }

    public LocalDate getCmnRevisionDate() {
        return cmnRevisionDate;
    }

    public void setCmnRevisionDate(LocalDate cmnRevisionDate) {
        this.cmnRevisionDate = cmnRevisionDate;
    }

    public LocalDate getCmnRecertificationDate() {
        return cmnRecertificationDate;
    }

    public void setCmnRecertificationDate(LocalDate cmnRecertificationDate) {
        this.cmnRecertificationDate = cmnRecertificationDate;
    }

    public LocalDate getCmnExpirationDate() {
        return cmnExpirationDate;
    }

    public void setCmnExpirationDate(LocalDate cmnExpirationDate) {
        this.cmnExpirationDate = cmnExpirationDate;
    }

    public Long getCmnLoggedBy() {
        return cmnLoggedBy;
    }

    public void setCmnLoggedBy(Long cmnLoggedBy) {
        this.cmnLoggedBy = cmnLoggedBy;
    }

    public LocalDate getCmnLoggedDate() {
        return cmnLoggedDate;
    }

    public void setCmnLoggedDate(LocalDate cmnLoggedDate) {
        this.cmnLoggedDate = cmnLoggedDate;
    }

    public Long getCmnApprovedBy() {
        return cmnApprovedBy;
    }

    public void setCmnApprovedBy(Long cmnApprovedBy) {
        this.cmnApprovedBy = cmnApprovedBy;
    }

    public LocalDate getCmnApprovedDate() {
        return cmnApprovedDate;
    }

    public void setCmnApprovedDate(LocalDate cmnApprovedDate) {
        this.cmnApprovedDate = cmnApprovedDate;
    }

    public Long getCmnPrintedBy() {
        return cmnPrintedBy;
    }

    public void setCmnPrintedBy(Long cmnPrintedBy) {
        this.cmnPrintedBy = cmnPrintedBy;
    }

    public LocalDate getCmnPrintedDate() {
        return cmnPrintedDate;
    }

    public void setCmnPrintedDate(LocalDate cmnPrintedDate) {
        this.cmnPrintedDate = cmnPrintedDate;
    }

    public String getLengthOfNeed() {
        return lengthOfNeed;
    }

    public void setLengthOfNeed(String lengthOfNeed) {
        this.lengthOfNeed = lengthOfNeed;
    }

    public String getFaxCmnOption() {
        return faxCmnOption;
    }

    public void setFaxCmnOption(String faxCmnOption) {
        this.faxCmnOption = faxCmnOption;
    }

    public String getCmnCoverLetterInclusionOption() {
        return cmnCoverLetterInclusionOption;
    }

    public void setCmnCoverLetterInclusionOption(String cmnCoverLetterInclusionOption) {
        this.cmnCoverLetterInclusionOption = cmnCoverLetterInclusionOption;
    }

    public Long getCmnFaxedBy() {
        return cmnFaxedBy;
    }

    public void setCmnFaxedBy(Long cmnFaxedBy) {
        this.cmnFaxedBy = cmnFaxedBy;
    }

    public LocalDate getCmnFaxedDate() {
        return cmnFaxedDate;
    }

    public void setCmnFaxedDate(LocalDate cmnFaxedDate) {
        this.cmnFaxedDate = cmnFaxedDate;
    }

    public String getFaxStatus() {
        return faxStatus;
    }

    public void setFaxStatus(String faxStatus) {
        this.faxStatus = faxStatus;
    }

    public String getFaxStatusReason() {
        return faxStatusReason;
    }

    public void setFaxStatusReason(String faxStatusReason) {
        this.faxStatusReason = faxStatusReason;
    }

    public String getPrintCmnOption() {
        return printCmnOption;
    }

    public void setPrintCmnOption(String printCmnOption) {
        this.printCmnOption = printCmnOption;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public UUID getCmnIdUuid() {
        return cmnIdUuid;
    }

    public void setCmnIdUuid(UUID cmnIdUuid) {
        this.cmnIdUuid = cmnIdUuid;
    }

    public String getPatientPrognosis() {
        return patientPrognosis;
    }

    public void setPatientPrognosis(String patientPrognosis) {
        this.patientPrognosis = patientPrognosis;
    }

    public String getCmnStatus() {
        return cmnStatus;
    }

    public void setCmnStatus(String cmnStatus) {
        this.cmnStatus = cmnStatus;
    }

    public String getRefillAuthorised() {
        return refillAuthorised;
    }

    public void setRefillAuthorised(String refillAuthorised) {
        this.refillAuthorised = refillAuthorised;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CmnDTO)) {
            return false;
        }

        CmnDTO cmnDTO = (CmnDTO) o;
        if (this.cmnId == null) {
            return false;
        }
        return Objects.equals(this.cmnId, cmnDTO.cmnId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.cmnId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CmnDTO{" +
            "cmnId=" + getCmnId() +
            ", cmnNumber='" + getCmnNumber() + "'" +
            ", cmnType='" + getCmnType() + "'" +
            ", cmnFormName='" + getCmnFormName() + "'" +
            ", patientId=" + getPatientId() +
            ", salesOrderId=" + getSalesOrderId() +
            ", salesOrderNo='" + getSalesOrderNo() + "'" +
            ", cmnCreateDate='" + getCmnCreateDate() + "'" +
            ", cmnInitialDate='" + getCmnInitialDate() + "'" +
            ", cmnRevisionDate='" + getCmnRevisionDate() + "'" +
            ", cmnRecertificationDate='" + getCmnRecertificationDate() + "'" +
            ", cmnExpirationDate='" + getCmnExpirationDate() + "'" +
            ", cmnLoggedBy=" + getCmnLoggedBy() +
            ", cmnLoggedDate='" + getCmnLoggedDate() + "'" +
            ", cmnApprovedBy=" + getCmnApprovedBy() +
            ", cmnApprovedDate='" + getCmnApprovedDate() + "'" +
            ", cmnPrintedBy=" + getCmnPrintedBy() +
            ", cmnPrintedDate='" + getCmnPrintedDate() + "'" +
            ", lengthOfNeed='" + getLengthOfNeed() + "'" +
            ", faxCmnOption='" + getFaxCmnOption() + "'" +
            ", cmnCoverLetterInclusionOption='" + getCmnCoverLetterInclusionOption() + "'" +
            ", cmnFaxedBy=" + getCmnFaxedBy() +
            ", cmnFaxedDate='" + getCmnFaxedDate() + "'" +
            ", faxStatus='" + getFaxStatus() + "'" +
            ", faxStatusReason='" + getFaxStatusReason() + "'" +
            ", printCmnOption='" + getPrintCmnOption() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", cmnIdUuid='" + getCmnIdUuid() + "'" +
            ", patientPrognosis='" + getPatientPrognosis() + "'" +
            ", cmnStatus='" + getCmnStatus() + "'" +
            ", refillAuthorised='" + getRefillAuthorised() + "'" +
            "}";
    }
}
