package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.CmnDocumentMaster} entity.
 */
public class CmnDocumentMasterDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long cmnDocumentId;

    private Long cmnId;

    private String cmnNo;

    private LocalDate generationDate;

    private String initialDocumentName;

    private String faxStatus;

    private String outBoundFaxStatus;

    private String outBoundFaxNo;

    private LocalDate outBoundFaxDateTime;

    private String inBoundFaxStatus;

    private String inBoundFaxNo;

    private LocalDate inBoundFaxDateTime;

    private UUID cmnDocumentMasterUuid;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private String returnDocumentName;

    private String cmnComments;

    private String status;

    public Long getCmnDocumentId() {
        return cmnDocumentId;
    }

    public void setCmnDocumentId(Long cmnDocumentId) {
        this.cmnDocumentId = cmnDocumentId;
    }

    public Long getCmnId() {
        return cmnId;
    }

    public void setCmnId(Long cmnId) {
        this.cmnId = cmnId;
    }

    public String getCmnNo() {
        return cmnNo;
    }

    public void setCmnNo(String cmnNo) {
        this.cmnNo = cmnNo;
    }

    public LocalDate getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(LocalDate generationDate) {
        this.generationDate = generationDate;
    }

    public String getInitialDocumentName() {
        return initialDocumentName;
    }

    public void setInitialDocumentName(String initialDocumentName) {
        this.initialDocumentName = initialDocumentName;
    }

    public String getFaxStatus() {
        return faxStatus;
    }

    public void setFaxStatus(String faxStatus) {
        this.faxStatus = faxStatus;
    }

    public String getOutBoundFaxStatus() {
        return outBoundFaxStatus;
    }

    public void setOutBoundFaxStatus(String outBoundFaxStatus) {
        this.outBoundFaxStatus = outBoundFaxStatus;
    }

    public String getOutBoundFaxNo() {
        return outBoundFaxNo;
    }

    public void setOutBoundFaxNo(String outBoundFaxNo) {
        this.outBoundFaxNo = outBoundFaxNo;
    }

    public LocalDate getOutBoundFaxDateTime() {
        return outBoundFaxDateTime;
    }

    public void setOutBoundFaxDateTime(LocalDate outBoundFaxDateTime) {
        this.outBoundFaxDateTime = outBoundFaxDateTime;
    }

    public String getInBoundFaxStatus() {
        return inBoundFaxStatus;
    }

    public void setInBoundFaxStatus(String inBoundFaxStatus) {
        this.inBoundFaxStatus = inBoundFaxStatus;
    }

    public String getInBoundFaxNo() {
        return inBoundFaxNo;
    }

    public void setInBoundFaxNo(String inBoundFaxNo) {
        this.inBoundFaxNo = inBoundFaxNo;
    }

    public LocalDate getInBoundFaxDateTime() {
        return inBoundFaxDateTime;
    }

    public void setInBoundFaxDateTime(LocalDate inBoundFaxDateTime) {
        this.inBoundFaxDateTime = inBoundFaxDateTime;
    }

    public UUID getCmnDocumentMasterUuid() {
        return cmnDocumentMasterUuid;
    }

    public void setCmnDocumentMasterUuid(UUID cmnDocumentMasterUuid) {
        this.cmnDocumentMasterUuid = cmnDocumentMasterUuid;
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

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
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

    public String getReturnDocumentName() {
        return returnDocumentName;
    }

    public void setReturnDocumentName(String returnDocumentName) {
        this.returnDocumentName = returnDocumentName;
    }

    public String getCmnComments() {
        return cmnComments;
    }

    public void setCmnComments(String cmnComments) {
        this.cmnComments = cmnComments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CmnDocumentMasterDTO)) {
            return false;
        }

        CmnDocumentMasterDTO cmnDocumentMasterDTO = (CmnDocumentMasterDTO) o;
        if (this.cmnDocumentId == null) {
            return false;
        }
        return Objects.equals(this.cmnDocumentId, cmnDocumentMasterDTO.cmnDocumentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.cmnDocumentId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CmnDocumentMasterDTO{" +
            "cmnDocumentId=" + getCmnDocumentId() +
            ", cmnId=" + getCmnId() +
            ", cmnNo='" + getCmnNo() + "'" +
            ", generationDate='" + getGenerationDate() + "'" +
            ", initialDocumentName='" + getInitialDocumentName() + "'" +
            ", faxStatus='" + getFaxStatus() + "'" +
            ", outBoundFaxStatus='" + getOutBoundFaxStatus() + "'" +
            ", outBoundFaxNo='" + getOutBoundFaxNo() + "'" +
            ", outBoundFaxDateTime='" + getOutBoundFaxDateTime() + "'" +
            ", inBoundFaxStatus='" + getInBoundFaxStatus() + "'" +
            ", inBoundFaxNo='" + getInBoundFaxNo() + "'" +
            ", inBoundFaxDateTime='" + getInBoundFaxDateTime() + "'" +
            ", cmnDocumentMasterUuid='" + getCmnDocumentMasterUuid() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", returnDocumentName='" + getReturnDocumentName() + "'" +
            ", cmnComments='" + getCmnComments() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
