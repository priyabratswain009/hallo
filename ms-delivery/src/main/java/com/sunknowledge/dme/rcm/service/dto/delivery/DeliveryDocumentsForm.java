package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDocumentsForm {
    private Long deliveryTicketId;
    private String documentPatientFile;
    private String documentPatientFilePath;
    private UUID deliveryDocumentsUuid;
}
