package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.AbnDelivery} entity.
 */
public class AbnDeliveryDTO implements Serializable {

    @NotNull
    private Long abnDeliveryId;

    private Long abnDeliveryDataId;

    private String abnDocumentName;

    private LocalDate scheduleDeliveryDateTime;

    private LocalDate actualDeliveryDateTime;

    private Long deliveryAgentId;

    private String deliveryAgentName;

    private String abnDeliveryStatus;

    private String abnDeliveryDocumentAckStatus;

    private String abnDeliveryDocPatientReplyStatus;

    private LocalDate abnDeliveryDocPatientReplyDateTime;

    private String abnResponseJsonData;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID abnDeliveryUuid;

    public Long getAbnDeliveryId() {
        return abnDeliveryId;
    }

    public void setAbnDeliveryId(Long abnDeliveryId) {
        this.abnDeliveryId = abnDeliveryId;
    }

    public Long getAbnDeliveryDataId() {
        return abnDeliveryDataId;
    }

    public void setAbnDeliveryDataId(Long abnDeliveryDataId) {
        this.abnDeliveryDataId = abnDeliveryDataId;
    }

    public String getAbnDocumentName() {
        return abnDocumentName;
    }

    public void setAbnDocumentName(String abnDocumentName) {
        this.abnDocumentName = abnDocumentName;
    }

    public LocalDate getScheduleDeliveryDateTime() {
        return scheduleDeliveryDateTime;
    }

    public void setScheduleDeliveryDateTime(LocalDate scheduleDeliveryDateTime) {
        this.scheduleDeliveryDateTime = scheduleDeliveryDateTime;
    }

    public LocalDate getActualDeliveryDateTime() {
        return actualDeliveryDateTime;
    }

    public void setActualDeliveryDateTime(LocalDate actualDeliveryDateTime) {
        this.actualDeliveryDateTime = actualDeliveryDateTime;
    }

    public Long getDeliveryAgentId() {
        return deliveryAgentId;
    }

    public void setDeliveryAgentId(Long deliveryAgentId) {
        this.deliveryAgentId = deliveryAgentId;
    }

    public String getDeliveryAgentName() {
        return deliveryAgentName;
    }

    public void setDeliveryAgentName(String deliveryAgentName) {
        this.deliveryAgentName = deliveryAgentName;
    }

    public String getAbnDeliveryStatus() {
        return abnDeliveryStatus;
    }

    public void setAbnDeliveryStatus(String abnDeliveryStatus) {
        this.abnDeliveryStatus = abnDeliveryStatus;
    }

    public String getAbnDeliveryDocumentAckStatus() {
        return abnDeliveryDocumentAckStatus;
    }

    public void setAbnDeliveryDocumentAckStatus(String abnDeliveryDocumentAckStatus) {
        this.abnDeliveryDocumentAckStatus = abnDeliveryDocumentAckStatus;
    }

    public String getAbnDeliveryDocPatientReplyStatus() {
        return abnDeliveryDocPatientReplyStatus;
    }

    public void setAbnDeliveryDocPatientReplyStatus(String abnDeliveryDocPatientReplyStatus) {
        this.abnDeliveryDocPatientReplyStatus = abnDeliveryDocPatientReplyStatus;
    }

    public LocalDate getAbnDeliveryDocPatientReplyDateTime() {
        return abnDeliveryDocPatientReplyDateTime;
    }

    public void setAbnDeliveryDocPatientReplyDateTime(LocalDate abnDeliveryDocPatientReplyDateTime) {
        this.abnDeliveryDocPatientReplyDateTime = abnDeliveryDocPatientReplyDateTime;
    }

    public String getAbnResponseJsonData() {
        return abnResponseJsonData;
    }

    public void setAbnResponseJsonData(String abnResponseJsonData) {
        this.abnResponseJsonData = abnResponseJsonData;
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

    public UUID getAbnDeliveryUuid() {
        return abnDeliveryUuid;
    }

    public void setAbnDeliveryUuid(UUID abnDeliveryUuid) {
        this.abnDeliveryUuid = abnDeliveryUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbnDeliveryDTO)) {
            return false;
        }

        AbnDeliveryDTO abnDeliveryDTO = (AbnDeliveryDTO) o;
        if (this.abnDeliveryId == null) {
            return false;
        }
        return Objects.equals(this.abnDeliveryId, abnDeliveryDTO.abnDeliveryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.abnDeliveryId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AbnDeliveryDTO{" +
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
