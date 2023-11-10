package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonDeliveryInputData {
    private Long deliveryTicketId;
    private String signatureType;
    private String patientRepresentativeName;
    private String patientRelationship;
    private String reasonNotToSign;
    private String date;
    private String companyRepresentativeName;
}
