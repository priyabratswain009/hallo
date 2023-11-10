package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientCommunicationInputParams {
    private Long deliveryTicketId;
    private String deliveryTicketNo;
    private String reasonForCommunication;
    private String patientPhoneNo;
    private String personSpokenToName;
    private String personSpokenToRelationWithPatient;
    private String communicationSummery;
    private String csrName;
}
