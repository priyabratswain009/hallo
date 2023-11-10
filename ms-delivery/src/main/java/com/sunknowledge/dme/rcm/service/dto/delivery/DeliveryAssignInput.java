package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAssignInput {
    private Long deliveryTicketId;
    private String agentFirstName;
    private String agentLastName;
    private String agentIdNo;
    private String agentAgency;
    private String agentContact1;
    private String agentContact2;
    private String agentVehicleNo;
    private String assgnmentDate;
    private String priority;
    private String agentComment;
}
