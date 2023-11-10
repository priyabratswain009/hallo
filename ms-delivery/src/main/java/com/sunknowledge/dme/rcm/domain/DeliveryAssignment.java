package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A DeliveryAssignment.
 */
@Entity
@Table(name = "t_delivery_assignment")
public class DeliveryAssignment implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "delivery_assignment_id", nullable = false)
    private Long deliveryAssignmentId;

    @Column(name = "delivery_ticket_id")
    private Long deliveryTicketId;

    @Column(name = "delivery_no")
    private String deliveryNo;

    @Column(name = "so_id")
    private Long soId;

    @Column(name = "so_no")
    private String soNo;

    @Column(name = "agent_first_name")
    private String agentFirstName;

    @Column(name = "agent_last_name")
    private String agentLastName;

    @Column(name = "agent_id_no")
    private String agentIdNo;

    @Column(name = "agent_agency")
    private String agentAgency;

    @Column(name = "agent_contact_1")
    private String agentContact1;

    @Column(name = "agent_contact_2")
    private String agentContact2;

    @Column(name = "agent_vehicle_no")
    private String agentVehicleNo;

    @Column(name = "assignment_status")
    private String assignmentStatus;

    @Column(name = "assgnment_date")
    private LocalDate assgnmentDate;

    @Column(name = "priority")
    private String priority;

    @Column(name = "agent_comment")
    private String agentComment;

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

    @Column(name = "delivery_assignment_uuid")
    private UUID deliveryAssignmentUuid;

    @Column(name = "delivery_status")
    private String deliveryStatus;

    @Column(name = "actual_delivery_date_time")
    private LocalDate actualDeliveryDateTime;

    @Column(name = "delivery_schedule_date_time")
    private LocalDate deliveryScheduleDateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDeliveryAssignmentId() {
        return this.deliveryAssignmentId;
    }

    public DeliveryAssignment deliveryAssignmentId(Long deliveryAssignmentId) {
        this.setDeliveryAssignmentId(deliveryAssignmentId);
        return this;
    }

    public void setDeliveryAssignmentId(Long deliveryAssignmentId) {
        this.deliveryAssignmentId = deliveryAssignmentId;
    }

    public Long getDeliveryTicketId() {
        return this.deliveryTicketId;
    }

    public DeliveryAssignment deliveryTicketId(Long deliveryTicketId) {
        this.setDeliveryTicketId(deliveryTicketId);
        return this;
    }

    public void setDeliveryTicketId(Long deliveryTicketId) {
        this.deliveryTicketId = deliveryTicketId;
    }

    public String getDeliveryNo() {
        return this.deliveryNo;
    }

    public DeliveryAssignment deliveryNo(String deliveryNo) {
        this.setDeliveryNo(deliveryNo);
        return this;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public Long getSoId() {
        return this.soId;
    }

    public DeliveryAssignment soId(Long soId) {
        this.setSoId(soId);
        return this;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public String getSoNo() {
        return this.soNo;
    }

    public DeliveryAssignment soNo(String soNo) {
        this.setSoNo(soNo);
        return this;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public String getAgentFirstName() {
        return this.agentFirstName;
    }

    public DeliveryAssignment agentFirstName(String agentFirstName) {
        this.setAgentFirstName(agentFirstName);
        return this;
    }

    public void setAgentFirstName(String agentFirstName) {
        this.agentFirstName = agentFirstName;
    }

    public String getAgentLastName() {
        return this.agentLastName;
    }

    public DeliveryAssignment agentLastName(String agentLastName) {
        this.setAgentLastName(agentLastName);
        return this;
    }

    public void setAgentLastName(String agentLastName) {
        this.agentLastName = agentLastName;
    }

    public String getAgentIdNo() {
        return this.agentIdNo;
    }

    public DeliveryAssignment agentIdNo(String agentIdNo) {
        this.setAgentIdNo(agentIdNo);
        return this;
    }

    public void setAgentIdNo(String agentIdNo) {
        this.agentIdNo = agentIdNo;
    }

    public String getAgentAgency() {
        return this.agentAgency;
    }

    public DeliveryAssignment agentAgency(String agentAgency) {
        this.setAgentAgency(agentAgency);
        return this;
    }

    public void setAgentAgency(String agentAgency) {
        this.agentAgency = agentAgency;
    }

    public String getAgentContact1() {
        return this.agentContact1;
    }

    public DeliveryAssignment agentContact1(String agentContact1) {
        this.setAgentContact1(agentContact1);
        return this;
    }

    public void setAgentContact1(String agentContact1) {
        this.agentContact1 = agentContact1;
    }

    public String getAgentContact2() {
        return this.agentContact2;
    }

    public DeliveryAssignment agentContact2(String agentContact2) {
        this.setAgentContact2(agentContact2);
        return this;
    }

    public void setAgentContact2(String agentContact2) {
        this.agentContact2 = agentContact2;
    }

    public String getAgentVehicleNo() {
        return this.agentVehicleNo;
    }

    public DeliveryAssignment agentVehicleNo(String agentVehicleNo) {
        this.setAgentVehicleNo(agentVehicleNo);
        return this;
    }

    public void setAgentVehicleNo(String agentVehicleNo) {
        this.agentVehicleNo = agentVehicleNo;
    }

    public String getAssignmentStatus() {
        return this.assignmentStatus;
    }

    public DeliveryAssignment assignmentStatus(String assignmentStatus) {
        this.setAssignmentStatus(assignmentStatus);
        return this;
    }

    public void setAssignmentStatus(String assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public LocalDate getAssgnmentDate() {
        return this.assgnmentDate;
    }

    public DeliveryAssignment assgnmentDate(LocalDate assgnmentDate) {
        this.setAssgnmentDate(assgnmentDate);
        return this;
    }

    public void setAssgnmentDate(LocalDate assgnmentDate) {
        this.assgnmentDate = assgnmentDate;
    }

    public String getPriority() {
        return this.priority;
    }

    public DeliveryAssignment priority(String priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAgentComment() {
        return this.agentComment;
    }

    public DeliveryAssignment agentComment(String agentComment) {
        this.setAgentComment(agentComment);
        return this;
    }

    public void setAgentComment(String agentComment) {
        this.agentComment = agentComment;
    }

    public String getStatus() {
        return this.status;
    }

    public DeliveryAssignment status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedById() {
        return this.createdById;
    }

    public DeliveryAssignment createdById(Long createdById) {
        this.setCreatedById(createdById);
        return this;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public DeliveryAssignment createdByName(String createdByName) {
        this.setCreatedByName(createdByName);
        return this;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public DeliveryAssignment createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedById() {
        return this.updatedById;
    }

    public DeliveryAssignment updatedById(Long updatedById) {
        this.setUpdatedById(updatedById);
        return this;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return this.updatedByName;
    }

    public DeliveryAssignment updatedByName(String updatedByName) {
        this.setUpdatedByName(updatedByName);
        return this;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public LocalDate getUpdatedDate() {
        return this.updatedDate;
    }

    public DeliveryAssignment updatedDate(LocalDate updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getDeliveryAssignmentUuid() {
        return this.deliveryAssignmentUuid;
    }

    public DeliveryAssignment deliveryAssignmentUuid(UUID deliveryAssignmentUuid) {
        this.setDeliveryAssignmentUuid(deliveryAssignmentUuid);
        return this;
    }

    public void setDeliveryAssignmentUuid(UUID deliveryAssignmentUuid) {
        this.deliveryAssignmentUuid = deliveryAssignmentUuid;
    }

    public String getDeliveryStatus() {
        return this.deliveryStatus;
    }

    public DeliveryAssignment deliveryStatus(String deliveryStatus) {
        this.setDeliveryStatus(deliveryStatus);
        return this;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public LocalDate getActualDeliveryDateTime() {
        return this.actualDeliveryDateTime;
    }

    public DeliveryAssignment actualDeliveryDateTime(LocalDate actualDeliveryDateTime) {
        this.setActualDeliveryDateTime(actualDeliveryDateTime);
        return this;
    }

    public void setActualDeliveryDateTime(LocalDate actualDeliveryDateTime) {
        this.actualDeliveryDateTime = actualDeliveryDateTime;
    }

    public LocalDate getDeliveryScheduleDateTime() {
        return this.deliveryScheduleDateTime;
    }

    public DeliveryAssignment deliveryScheduleDateTime(LocalDate deliveryScheduleDateTime) {
        this.setDeliveryScheduleDateTime(deliveryScheduleDateTime);
        return this;
    }

    public void setDeliveryScheduleDateTime(LocalDate deliveryScheduleDateTime) {
        this.deliveryScheduleDateTime = deliveryScheduleDateTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryAssignment)) {
            return false;
        }
        return deliveryAssignmentId != null && deliveryAssignmentId.equals(((DeliveryAssignment) o).deliveryAssignmentId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryAssignment{" +
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
