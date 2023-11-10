package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.HcpcsDocType} entity.
 */
public class HcpcsDocTypeDTO implements Serializable {

    @NotNull
    private Long hcpcsDoctypeId;

    private String hcpcsCode;

    private Long documentId;

    private String documentName;

    private String documentFilePath;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID hcpcsDocTypeUuid;

    private String fileType;

    private String formName;

    public Long getHcpcsDoctypeId() {
        return hcpcsDoctypeId;
    }

    public void setHcpcsDoctypeId(Long hcpcsDoctypeId) {
        this.hcpcsDoctypeId = hcpcsDoctypeId;
    }

    public String getHcpcsCode() {
        return hcpcsCode;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentFilePath() {
        return documentFilePath;
    }

    public void setDocumentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
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

    public UUID getHcpcsDocTypeUuid() {
        return hcpcsDocTypeUuid;
    }

    public void setHcpcsDocTypeUuid(UUID hcpcsDocTypeUuid) {
        this.hcpcsDocTypeUuid = hcpcsDocTypeUuid;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HcpcsDocTypeDTO)) {
            return false;
        }

        HcpcsDocTypeDTO hcpcsDocTypeDTO = (HcpcsDocTypeDTO) o;
        if (this.hcpcsDoctypeId == null) {
            return false;
        }
        return Objects.equals(this.hcpcsDoctypeId, hcpcsDocTypeDTO.hcpcsDoctypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.hcpcsDoctypeId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HcpcsDocTypeDTO{" +
            "hcpcsDoctypeId=" + getHcpcsDoctypeId() +
            ", hcpcsCode='" + getHcpcsCode() + "'" +
            ", documentId=" + getDocumentId() +
            ", documentName='" + getDocumentName() + "'" +
            ", documentFilePath='" + getDocumentFilePath() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", hcpcsDocTypeUuid='" + getHcpcsDocTypeUuid() + "'" +
            ", fileType='" + getFileType() + "'" +
            ", formName='" + getFormName() + "'" +
            "}";
    }
}
