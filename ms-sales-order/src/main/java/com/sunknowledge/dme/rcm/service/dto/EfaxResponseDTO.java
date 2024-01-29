package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.EfaxResponse} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EfaxResponseDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long efaxResponseId;

    private LocalDate efaxReceivedDate;

    private String senderEmail;

    private String emailSubjectLine;

    private String attachmentName;

    private Boolean isQrDecoded;

    private String qrValue;

    private Boolean isCmn;

    private Boolean isPar;

    private String patientIdNo;

    private String patientFirstName;

    private String patientLastName;

    private String cmnIdNo;

    private String parIdNo;

    private Long soId;

    private String soNo;

    private Boolean isManuallyTagged;

    private Boolean isPatientRecord;

    private String efaxNumber;

    private Boolean isPoAck;

    private String documentRenameTo;

    private Long createdById;

    private LocalDate createdDate;

    private String createdByName;

    private Long updatedById;

    private LocalDate updatedDate;

    private String updatedByName;

    private String status;

    private UUID efaxResponseUuid;

    public Long getEfaxResponseId() {
        return efaxResponseId;
    }

    public void setEfaxResponseId(Long efaxResponseId) {
        this.efaxResponseId = efaxResponseId;
    }

    public LocalDate getEfaxReceivedDate() {
        return efaxReceivedDate;
    }

    public void setEfaxReceivedDate(LocalDate efaxReceivedDate) {
        this.efaxReceivedDate = efaxReceivedDate;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getEmailSubjectLine() {
        return emailSubjectLine;
    }

    public void setEmailSubjectLine(String emailSubjectLine) {
        this.emailSubjectLine = emailSubjectLine;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public Boolean getIsQrDecoded() {
        return isQrDecoded;
    }

    public void setIsQrDecoded(Boolean isQrDecoded) {
        this.isQrDecoded = isQrDecoded;
    }

    public String getQrValue() {
        return qrValue;
    }

    public void setQrValue(String qrValue) {
        this.qrValue = qrValue;
    }

    public Boolean getIsCmn() {
        return isCmn;
    }

    public void setIsCmn(Boolean isCmn) {
        this.isCmn = isCmn;
    }

    public Boolean getIsPar() {
        return isPar;
    }

    public void setIsPar(Boolean isPar) {
        this.isPar = isPar;
    }

    public String getPatientIdNo() {
        return patientIdNo;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getCmnIdNo() {
        return cmnIdNo;
    }

    public void setCmnIdNo(String cmnIdNo) {
        this.cmnIdNo = cmnIdNo;
    }

    public String getParIdNo() {
        return parIdNo;
    }

    public void setParIdNo(String parIdNo) {
        this.parIdNo = parIdNo;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public Boolean getIsManuallyTagged() {
        return isManuallyTagged;
    }

    public void setIsManuallyTagged(Boolean isManuallyTagged) {
        this.isManuallyTagged = isManuallyTagged;
    }

    public Boolean getIsPatientRecord() {
        return isPatientRecord;
    }

    public void setIsPatientRecord(Boolean isPatientRecord) {
        this.isPatientRecord = isPatientRecord;
    }

    public String getEfaxNumber() {
        return efaxNumber;
    }

    public void setEfaxNumber(String efaxNumber) {
        this.efaxNumber = efaxNumber;
    }

    public Boolean getIsPoAck() {
        return isPoAck;
    }

    public void setIsPoAck(Boolean isPoAck) {
        this.isPoAck = isPoAck;
    }

    public String getDocumentRenameTo() {
        return documentRenameTo;
    }

    public void setDocumentRenameTo(String documentRenameTo) {
        this.documentRenameTo = documentRenameTo;
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

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getEfaxResponseUuid() {
        return efaxResponseUuid;
    }

    public void setEfaxResponseUuid(UUID efaxResponseUuid) {
        this.efaxResponseUuid = efaxResponseUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EfaxResponseDTO)) {
            return false;
        }

        EfaxResponseDTO efaxResponseDTO = (EfaxResponseDTO) o;
        if (this.efaxResponseId == null) {
            return false;
        }
        return Objects.equals(this.efaxResponseId, efaxResponseDTO.efaxResponseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.efaxResponseId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EfaxResponseDTO{" +
            "efaxResponseId=" + getEfaxResponseId() +
            ", efaxReceivedDate='" + getEfaxReceivedDate() + "'" +
            ", senderEmail='" + getSenderEmail() + "'" +
            ", emailSubjectLine='" + getEmailSubjectLine() + "'" +
            ", attachmentName='" + getAttachmentName() + "'" +
            ", isQrDecoded='" + getIsQrDecoded() + "'" +
            ", qrValue='" + getQrValue() + "'" +
            ", isCmn='" + getIsCmn() + "'" +
            ", isPar='" + getIsPar() + "'" +
            ", patientIdNo='" + getPatientIdNo() + "'" +
            ", patientFirstName='" + getPatientFirstName() + "'" +
            ", patientLastName='" + getPatientLastName() + "'" +
            ", cmnIdNo='" + getCmnIdNo() + "'" +
            ", parIdNo='" + getParIdNo() + "'" +
            ", soId=" + getSoId() +
            ", soNo='" + getSoNo() + "'" +
            ", isManuallyTagged='" + getIsManuallyTagged() + "'" +
            ", isPatientRecord='" + getIsPatientRecord() + "'" +
            ", efaxNumber='" + getEfaxNumber() + "'" +
            ", isPoAck='" + getIsPoAck() + "'" +
            ", documentRenameTo='" + getDocumentRenameTo() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", status='" + getStatus() + "'" +
            ", efaxResponseUuid='" + getEfaxResponseUuid() + "'" +
            "}";
    }
}
