package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDocumentResponse {
    private Long deliveryDocId;
    private Long deliveryTicketId;
    private String deliveryTicketNo;
    private Long soId;
    private String soNo;
    private Long hcpcsDoctypeId;
    private String docPatientName;
    private String documentPatientFile;
    private String isPatientSigned;
    private String isCaregiverSigned;
    private LocalDate signedDate;
    private String isDeliveryAgentSigned;
    private String isScannedUploaded;
    private UUID deliveryDocumentsUuid;
    private String documentPatientFilePath;
    private List<UploadFileResponse> uploadFileResponseList;
}
