package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.DeliveryAssignment} entity.
 */
public class DeliveryAssignmentDTO implements Serializable {

    @NotNull
    private Long deliveryAssignmentId;

    private Long deliveryTicketId;

    private String deliveryNo;

    private Long soId;

    private String soNo;

    private String agentFirstName;

    private String agentLastName;

    private String agentIdNo;

    private String agentAgency;

    private String agentContact1;

    private String agentContact2;

    private String agentVehicleNo;

    private String assignmentStatus;

    private LocalDate assgnmentDate;

    private String priority;

    private String agentComment;

    private String status;

    private Long createdById;

    private String createdByName;

    private LocalDate createdDate;

    private Long updatedById;

    private String updatedByName;

    private LocalDate updatedDate;

    private UUID deliveryAssignmentUuid;

    private String deliveryStatus;

    private LocalDate actualDeliveryDateTime;

    private LocalDate deliveryScheduleDateTime;

    public Long getDeliveryAssignmentId() {
        return deliveryAssignmentId;
    }

    public void setDeliveryAssignmentId(Long deliveryAssignmentId) {
        this.deliveryAssignmentId = deliveryAssignmentId;
    }

    public Long getDeliveryTicketId() {
        return deliveryTicketId;
    }

    public void setDeliveryTicketId(Long deliveryTicketId) {
        this.deliveryTicketId = deliveryTicketId;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
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

    public String getAgentFirstName() {
        return agentFirstName;
    }

    public void setAgentFirstName(String agentFirstName) {
        this.agentFirstName = agentFirstName;
    }

    public String getAgentLastName() {
        return agentLastName;
    }

    public void setAgentLastName(String agentLastName) {
        this.agentLastName = agentLastName;
    }

    public String getAgentIdNo() {
        return agentIdNo;
    }

    public void setAgentIdNo(String agentIdNo) {
        this.agentIdNo = agentIdNo;
    }

    public String getAgentAgency() {
        return agentAgency;
    }

    public void setAgentAgency(String agentAgency) {
        this.agentAgency = agentAgency;
    }

    public String getAgentContact1() {
        return agentContact1;
    }

    public void setAgentContact1(String agentContact1) {
        this.agentContact1 = agentContact1;
    }

    public String getAgentContact2() {
        return agentContact2;
    }

    public void setAgentContact2(String agentContact2) {
        this.agentContact2 = agentContact2;
    }

    public String getAgentVehicleNo() {
        return agentVehicleNo;
    }

    public void setAgentVehicleNo(String agentVehicleNo) {
        this.agentVehicleNo = agentVehicleNo;
    }

    public String getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(String assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public LocalDate getAssgnmentDate() {
        return assgnmentDate;
    }

    public void setAssgnmentDate(LocalDate assgnmentDate) {
        this.assgnmentDate = assgnmentDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAgentComment() {
        return agentComment;
    }

    public void setAgentComment(String agentComment) {
        this.agentComment = agentComment;
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

    public UUID getDeliveryAssignmentUuid() {
        return deliveryAssignmentUuid;
    }

    public void setDeliveryAssignmentUuid(UUID deliveryAssignmentUuid) {
        this.deliveryAssignmentUuid = deliveryAssignmentUuid;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public LocalDate getActualDeliveryDateTime() {
        return actualDeliveryDateTime;
    }

    public void setActualDeliveryDateTime(LocalDate actualDeliveryDateTime) {
        this.actualDeliveryDateTime = actualDeliveryDateTime;
    }

    public LocalDate getDeliveryScheduleDateTime() {
        return deliveryScheduleDateTime;
    }

    public void setDeliveryScheduleDateTime(LocalDate deliveryScheduleDateTime) {
        this.deliveryScheduleDateTime = deliveryScheduleDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryAssignmentDTO)) {
            return false;
        }

        DeliveryAssignmentDTO deliveryAssignmentDTO = (DeliveryAssignmentDTO) o;
        if (this.deliveryAssignmentId == null) {
            return false;
        }
        return Objects.equals(this.deliveryAssignmentId, deliveryAssignmentDTO.deliveryAssignmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.deliveryAssignmentId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryAssignmentDTO{" +
            "deliveryAssignmentId=" + getDeliveryAssignmentId() +
            ", deliveryTicketId=" + getDeliveryTicketId() +
            ", deliveryNo='" + getDeliveryNo() + "'" +
            ", soId=" + getSoId() +
            ", soNo='" + getSoNo() + "'" +
            ", agentFirstName='" + getAgentFirstName() + "'" +
            ", agentLastName='" + getAgentLastName() + "'" +
            ", agentIdNo='" + getAgentIdNo() + "'" +
            ", agentAgency='" + getAgentAgency() + "'" +
            ", agentContact1='" + getAgentContact1() + "'" +
            ", agentContact2='" + getAgentContact2() + "'" +
            ", agentVehicleNo='" + getAgentVehicleNo() + "'" +
            ", assignmentStatus='" + getAssignmentStatus() + "'" +
            ", assgnmentDate='" + getAssgnmentDate() + "'" +
            ", priority='" + getPriority() + "'" +
            ", agentComment='" + getAgentComment() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", deliveryAssignmentUuid='" + getDeliveryAssignmentUuid() + "'" +
            ", deliveryStatus='" + getDeliveryStatus() + "'" +
            ", actualDeliveryDateTime='" + getActualDeliveryDateTime() + "'" +
            ", deliveryScheduleDateTime='" + getDeliveryScheduleDateTime() + "'" +
            "}";
    }
}
