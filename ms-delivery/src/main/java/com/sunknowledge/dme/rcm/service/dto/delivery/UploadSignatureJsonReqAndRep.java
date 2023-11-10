package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadSignatureJsonReqAndRep {
    private Long deliveryTicketId;
    private String signatureType;
    private String companyRepresentativeName;
    private String date;
    private String patientRepresentativeName;
    private String patientRelationship;
    private String reasonNotToSign;
    private String caregiverSignature;
    private String patientSignature;
    private String driverAgentSignature;
}
