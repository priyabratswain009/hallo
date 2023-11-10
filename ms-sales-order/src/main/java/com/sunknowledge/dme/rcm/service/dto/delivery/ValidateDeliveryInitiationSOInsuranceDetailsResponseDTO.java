package com.sunknowledge.dme.rcm.service.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateDeliveryInitiationSOInsuranceDetailsResponseDTO {
    Long salesOrderInsuranceDetailsId;
    Long primaryInsurerId;
    String primaryInsurerVerificationStatus;
    Long secondaryInsurerId;
    String secondaryInsurerVerificationStatus;
    Long tertiaryInsurerId;
    String tertiaryInsurerVerificationStatus;
    String insuranceVerificationStatus;
    /*Long updatedById;
    String updatedByName;
    LocalDate updatedDate;*/
}
