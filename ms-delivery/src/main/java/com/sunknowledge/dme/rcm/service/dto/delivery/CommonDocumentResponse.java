package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonDocumentResponse {
    File patientSignatureFile;
    File techSignatureFile;
    DeliveryDocumentResponse deliveryDocumentResponse;
    CommonDeliveryInputData commonDeliveryInputData;
}
