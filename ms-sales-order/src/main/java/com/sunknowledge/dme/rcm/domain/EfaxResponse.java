package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A EfaxResponse.
 */
@Table("t_efax_response")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EfaxResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "must not be null")
    @Id
    @Column("efax_response_id")
    private Long efaxResponseId;

    @Column("efax_received_date")
    private LocalDate efaxReceivedDate;

    @Column("sender_email")
    private String senderEmail;

    @Column("email_subject_line")
    private String emailSubjectLine;

    @Column("attachment_name")
    private String attachmentName;

    @Column("is_qr_decoded")
    private Boolean isQrDecoded;

    @Column("qr_value")
    private String qrValue;

    @Column("is_cmn")
    private Boolean isCmn;

    @Column("is_par")
    private Boolean isPar;

    @Column("patient_id_no")
    private String patientIdNo;

    @Column("patient_first_name")
    private String patientFirstName;

    @Column("patient_last_name")
    private String patientLastName;

    @Column("cmn_id_no")
    private String cmnIdNo;

    @Column("par_id_no")
    private String parIdNo;

    @Column("so_id")
    private Long soId;

    @Column("so_no")
    private String soNo;

    @Column("is_manually_tagged")
    private Boolean isManuallyTagged;

    @Column("is_patient_record")
    private Boolean isPatientRecord;

    @Column("efax_number")
    private String efaxNumber;

    @Column("is_po_ack")
    private Boolean isPoAck;

    @Column("document_rename_to")
    private String documentRenameTo;

    @Column("created_by_id")
    private Long createdById;

    @Column("created_date")
    private LocalDate createdDate;

    @Column("created_by_name")
    private String createdByName;

    @Column("updated_by_id")
    private Long updatedById;

    @Column("updated_date")
    private LocalDate updatedDate;

    @Column("updated_by_name")
    private String updatedByName;

    @Column("status")
    private String status;

    @Column("efax_response_uuid")
    private UUID efaxResponseUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getEfaxResponseId() {
        return this.efaxResponseId;
    }

    public EfaxResponse efaxResponseId(Long efaxResponseId) {
        this.setEfaxResponseId(efaxResponseId);
        return this;
    }

    public void setEfaxResponseId(Long efaxResponseId) {
        this.efaxResponseId = efaxResponseId;
    }

    public LocalDate getEfaxReceivedDate() {
        return this.efaxReceivedDate;
    }

    public EfaxResponse efaxReceivedDate(LocalDate efaxReceivedDate) {
        this.setEfaxReceivedDate(efaxReceivedDate);
        return this;
    }

    public void setEfaxReceivedDate(LocalDate efaxReceivedDate) {
        this.efaxReceivedDate = efaxReceivedDate;
    }

    public String getSenderEmail() {
        return this.senderEmail;
    }

    public EfaxResponse senderEmail(String senderEmail) {
        this.setSenderEmail(senderEmail);
        return this;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getEmailSubjectLine() {
        return this.emailSubjectLine;
    }

    public EfaxResponse emailSubjectLine(String emailSubjectLine) {
        this.setEmailSubjectLine(emailSubjectLine);
        return this;
    }

    public void setEmailSubjectLine(String emailSubjectLine) {
        this.emailSubjectLine = emailSubjectLine;
    }

    public String getAttachmentName() {
        return this.attachmentName;
    }

    public EfaxResponse attachmentName(String attachmentName) {
        this.setAttachmentName(attachmentName);
        return this;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public Boolean getIsQrDecoded() {
        return this.isQrDecoded;
    }

    public EfaxResponse isQrDecoded(Boolean isQrDecoded) {
        this.setIsQrDecoded(isQrDecoded);
        return this;
    }

    public void setIsQrDecoded(Boolean isQrDecoded) {
        this.isQrDecoded = isQrDecoded;
    }

    public String getQrValue() {
        return this.qrValue;
    }

    public EfaxResponse qrValue(String qrValue) {
        this.setQrValue(qrValue);
        return this;
    }

    public void setQrValue(String qrValue) {
        this.qrValue = qrValue;
    }

    public Boolean getIsCmn() {
        return this.isCmn;
    }

    public EfaxResponse isCmn(Boolean isCmn) {
        this.setIsCmn(isCmn);
        return this;
    }

    public void setIsCmn(Boolean isCmn) {
        this.isCmn = isCmn;
    }

    public Boolean getIsPar() {
        return this.isPar;
    }

    public EfaxResponse isPar(Boolean isPar) {
        this.setIsPar(isPar);
        return this;
    }

    public void setIsPar(Boolean isPar) {
        this.isPar = isPar;
    }

    public String getPatientIdNo() {
        return this.patientIdNo;
    }

    public EfaxResponse patientIdNo(String patientIdNo) {
        this.setPatientIdNo(patientIdNo);
        return this;
    }

    public void setPatientIdNo(String patientIdNo) {
        this.patientIdNo = patientIdNo;
    }

    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public EfaxResponse patientFirstName(String patientFirstName) {
        this.setPatientFirstName(patientFirstName);
        return this;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return this.patientLastName;
    }

    public EfaxResponse patientLastName(String patientLastName) {
        this.setPatientLastName(patientLastName);
        return this;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getCmnIdNo() {
        return this.cmnIdNo;
    }

    public EfaxResponse cmnIdNo(String cmnIdNo) {
        this.setCmnIdNo(cmnIdNo);
        return this;
    }

    public void setCmnIdNo(String cmnIdNo) {
        this.cmnIdNo = cmnIdNo;
    }

    public String getParIdNo() {
        return this.parIdNo;
    }

    public EfaxResponse parIdNo(String parIdNo) {
        this.setParIdNo(parIdNo);
        return this;
    }

    public void setParIdNo(String parIdNo) {
        this.parIdNo = parIdNo;
    }

    public Long getSoId() {
        return this.soId;
    }

    public EfaxResponse soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return this.soNo;
    }

    public EfaxResponse soNo(String soNo) {
        this.setSoNo(soNo);
        return this;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public Boolean getIsManuallyTagged() {
        return this.isManuallyTagged;
    }

    public EfaxResponse isManuallyTagged(Boolean isManuallyTagged) {
        this.setIsManuallyTagged(isManuallyTagged);
        return this;
    }

    public void setIsManuallyTagged(Boolean isManuallyTagged) {
        this.isManuallyTagged = isManuallyTagged;
    }

    public Boolean getIsPatientRecord() {
        return this.isPatientRecord;
    }

    public EfaxResponse isPatientRecord(Boolean isPatientRecord) {
        this.setIsPatientRecord(isPatientRecord);
        return this;
    }

    public void setIsPatientRecord(Boolean isPatientRecord) {
        this.isPatientRecord = isPatientRecord;
    }

    public String getEfaxNumber() {
        return this.efaxNumber;
    }

    public EfaxResponse efaxNumber(String efaxNumber) {
        this.setEfaxNumber(efaxNumber);
        return this;
    }

    public void setEfaxNumber(String efaxNumber) {
        this.efaxNumber = efaxNumber;
    }

    public Boolean getIsPoAck() {
        return this.isPoAck;
    }

    public EfaxResponse isPoAck(Boolean isPoAck) {
        this.setIsPoAck(isPoAck);
        return this;
    }

    public void setIsPoAck(Boolean isPoAck) {
        this.isPoAck = isPoAck;
    }

    public String getDocumentRenameTo() {
        return this.documentRenameTo;
    }

    public EfaxResponse documentRenameTo(String documentRenameTo) {
        this.setDocumentRenameTo(documentRenameTo);
        return this;
    }

    public void setDocumentRenameTo(String documentRenameTo) {
        this.documentRenameTo = documentRenameTo;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public EfaxResponse createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public EfaxResponse createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public EfaxResponse createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public EfaxResponse updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public EfaxResponse updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public EfaxResponse updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getStatus() {
        return this.status;
    }

    public EfaxResponse status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getEfaxResponseUuid() {
        return this.efaxResponseUuid;
    }

    public EfaxResponse efaxResponseUuid(UUID efaxResponseUuid) {
        this.setEfaxResponseUuid(efaxResponseUuid);
        return this;
    }

    public void setEfaxResponseUuid(UUID efaxResponseUuid) {
        this.efaxResponseUuid = efaxResponseUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EfaxResponse)) {
            return false;
        }
        return efaxResponseId != null && efaxResponseId.equals(((EfaxResponse) o).efaxResponseId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EfaxResponse{" +
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
