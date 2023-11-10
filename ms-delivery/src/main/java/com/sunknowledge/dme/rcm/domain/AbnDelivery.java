package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A AbnDelivery.
 */
@Entity
@Table(name = "t_abn_delivery")
public class AbnDelivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "abn_delivery_id", nullable = false)
    private Long abnDeliveryId;

    @Column(name = "abn_delivery_data_id")
    private Long abnDeliveryDataId;

    @Column(name = "abn_document_name")
    private String abnDocumentName;

    @Column(name = "schedule_delivery_date_time")
    private LocalDate scheduleDeliveryDateTime;

    @Column(name = "actual_delivery_date_time")
    private LocalDate actualDeliveryDateTime;

    @Column(name = "delivery_agent_id")
    private Long deliveryAgentId;

    @Column(name = "delivery_agent_name")
    private String deliveryAgentName;

    @Column(name = "abn_delivery_status")
    private String abnDeliveryStatus;

    @Column(name = "abn_delivery_document_ack_status")
    private String abnDeliveryDocumentAckStatus;

    @Column(name = "abn_delivery_doc_patient_reply_status")
    private String abnDeliveryDocPatientReplyStatus;

    @Column(name = "abn_delivery_doc_patient_reply_date_time")
    private LocalDate abnDeliveryDocPatientReplyDateTime;

    @Column(name = "abn_response_json_data")
    private String abnResponseJsonData;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "created_by_name")
    private String createdByName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "updated_by_name")
    private String updatedByName;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "abn_delivery_uuid")
    private UUID abnDeliveryUuid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getAbnDeliveryId() {
        return this.abnDeliveryId;
    }

    public AbnDelivery abnDeliveryId(Long abnDeliveryId) {
        this.setAbnDeliveryId(abnDeliveryId);
        return this;
    }

    public void setAbnDeliveryId(Long abnDeliveryId) {
        this.abnDeliveryId = abnDeliveryId;
    }

    public Long getAbnDeliveryDataId() {
        return this.abnDeliveryDataId;
    }

    public AbnDelivery abnDeliveryDataId(Long abnDeliveryDataId) {
        this.setAbnDeliveryDataId(abnDeliveryDataId);
        return this;
    }

    public void setAbnDeliveryDataId(Long abnDeliveryDataId) {
        this.abnDeliveryDataId = abnDeliveryDataId;
    }

    public String getAbnDocumentName() {
        return this.abnDocumentName;
    }

    public AbnDelivery abnDocumentName(String abnDocumentName) {
        this.setAbnDocumentName(abnDocumentName);
        return this;
    }

    public void setAbnDocumentName(String abnDocumentName) {
        this.abnDocumentName = abnDocumentName;
    }

    public LocalDate getScheduleDeliveryDateTime() {
        return this.scheduleDeliveryDateTime;
    }

    public AbnDelivery scheduleDeliveryDateTime(LocalDate scheduleDeliveryDateTime) {
        this.setScheduleDeliveryDateTime(scheduleDeliveryDateTime);
        return this;
    }

    public void setScheduleDeliveryDateTime(LocalDate scheduleDeliveryDateTime) {
        this.scheduleDeliveryDateTime = scheduleDeliveryDateTime;
    }

    public LocalDate getActualDeliveryDateTime() {
        return this.actualDeliveryDateTime;
    }

    public AbnDelivery actualDeliveryDateTime(LocalDate actualDeliveryDateTime) {
        this.setActualDeliveryDateTime(actualDeliveryDateTime);
        return this;
    }

    public void setActualDeliveryDateTime(LocalDate actualDeliveryDateTime) {
        this.actualDeliveryDateTime = actualDeliveryDateTime;
    }

    public Long getDeliveryAgentId() {
        return this.deliveryAgentId;
    }

    public AbnDelivery deliveryAgentId(Long deliveryAgentId) {
        this.setDeliveryAgentId(deliveryAgentId);
        return this;
    }

    public void setDeliveryAgentId(Long deliveryAgentId) {
        this.deliveryAgentId = deliveryAgentId;
    }

    public String getDeliveryAgentName() {
        return this.deliveryAgentName;
    }

    public AbnDelivery deliveryAgentName(String deliveryAgentName) {
        this.setDeliveryAgentName(deliveryAgentName);
        return this;
    }

    public void setDeliveryAgentName(String deliveryAgentName) {
        this.deliveryAgentName = deliveryAgentName;
    }

    public String getAbnDeliveryStatus() {
        return this.abnDeliveryStatus;
    }

    public AbnDelivery abnDeliveryStatus(String abnDeliveryStatus) {
        this.setAbnDeliveryStatus(abnDeliveryStatus);
        return this;
    }

    public void setAbnDeliveryStatus(String abnDeliveryStatus) {
        this.abnDeliveryStatus = abnDeliveryStatus;
    }

    public String getAbnDeliveryDocumentAckStatus() {
        return this.abnDeliveryDocumentAckStatus;
    }

    public AbnDelivery abnDeliveryDocumentAckStatus(String abnDeliveryDocumentAckStatus) {
        this.setAbnDeliveryDocumentAckStatus(abnDeliveryDocumentAckStatus);
        return this;
    }

    public void setAbnDeliveryDocumentAckStatus(String abnDeliveryDocumentAckStatus) {
        this.abnDeliveryDocumentAckStatus = abnDeliveryDocumentAckStatus;
    }

    public String getAbnDeliveryDocPatientReplyStatus() {
        return this.abnDeliveryDocPatientReplyStatus;
    }

    public AbnDelivery abnDeliveryDocPatientReplyStatus(String abnDeliveryDocPatientReplyStatus) {
        this.setAbnDeliveryDocPatientReplyStatus(abnDeliveryDocPatientReplyStatus);
        return this;
    }

    public void setAbnDeliveryDocPatientReplyStatus(String abnDeliveryDocPatientReplyStatus) {
        this.abnDeliveryDocPatientReplyStatus = abnDeliveryDocPatientReplyStatus;
    }

    public LocalDate getAbnDeliveryDocPatientReplyDateTime() {
        return this.abnDeliveryDocPatientReplyDateTime;
    }

    public AbnDelivery abnDeliveryDocPatientReplyDateTime(LocalDate abnDeliveryDocPatientReplyDateTime) {
        this.setAbnDeliveryDocPatientReplyDateTime(abnDeliveryDocPatientReplyDateTime);
        return this;
    }

    public void setAbnDeliveryDocPatientReplyDateTime(LocalDate abnDeliveryDocPatientReplyDateTime) {
        this.abnDeliveryDocPatientReplyDateTime = abnDeliveryDocPatientReplyDateTime;
    }

    public String getAbnResponseJsonData() {
        return this.abnResponseJsonData;
    }

    public AbnDelivery abnResponseJsonData(String abnResponseJsonData) {
        this.setAbnResponseJsonData(abnResponseJsonData);
        return this;
    }

    public void setAbnResponseJsonData(String abnResponseJsonData) {
        this.abnResponseJsonData = abnResponseJsonData;
    }

    public String getStatus() {
        return this.status;
    }

    public AbnDelivery status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public AbnDelivery createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public AbnDelivery createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public AbnDelivery createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public AbnDelivery updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public AbnDelivery updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public AbnDelivery updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getAbnDeliveryUuid() {
        return this.abnDeliveryUuid;
    }

    public AbnDelivery abnDeliveryUuid(UUID abnDeliveryUuid) {
        this.setAbnDeliveryUuid(abnDeliveryUuid);
        return this;
    }

    public void setAbnDeliveryUuid(UUID abnDeliveryUuid) {
        this.abnDeliveryUuid = abnDeliveryUuid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbnDelivery)) {
            return false;
        }
        return abnDeliveryId != null && abnDeliveryId.equals(((AbnDelivery) o).abnDeliveryId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AbnDelivery{" +
            "abnDeliveryId=" + getAbnDeliveryId() +
            ", abnDeliveryDataId=" + getAbnDeliveryDataId() +
            ", abnDocumentName='" + getAbnDocumentName() + "'" +
            ", scheduleDeliveryDateTime='" + getScheduleDeliveryDateTime() + "'" +
            ", actualDeliveryDateTime='" + getActualDeliveryDateTime() + "'" +
            ", deliveryAgentId=" + getDeliveryAgentId() +
            ", deliveryAgentName='" + getDeliveryAgentName() + "'" +
            ", abnDeliveryStatus='" + getAbnDeliveryStatus() + "'" +
            ", abnDeliveryDocumentAckStatus='" + getAbnDeliveryDocumentAckStatus() + "'" +
            ", abnDeliveryDocPatientReplyStatus='" + getAbnDeliveryDocPatientReplyStatus() + "'" +
            ", abnDeliveryDocPatientReplyDateTime='" + getAbnDeliveryDocPatientReplyDateTime() + "'" +
            ", abnResponseJsonData='" + getAbnResponseJsonData() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", abnDeliveryUuid='" + getAbnDeliveryUuid() + "'" +
            "}";
    }
}
