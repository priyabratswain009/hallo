package com.sunknowledge.dme.rcm.service.dto.patiententry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientClinicalParameterDTO {
    private UUID patientClinicalInformationUuid;
    @NotNull(message = "Height must be provided")
    @Min(value = 1, message = "Height must be greater than equals to 1")
    private Double height;
    @NotNull(message = "Weight must be provided")
    @Min(value = 1, message = "Weight must be greater than equals to 1")
    private Double weight;
    @NotNull(message = "Functional_Ability must be provided")
    @NotBlank(message = "Functional_Ability must be provided")
    private String functionalAbilities;
    @NotNull(message = "Capture_Date must be provided")
    private LocalDate captureDate;
    private String infectionConditionStatus;
    @NotBlank(message = "Diabetes_Status must be provided")
    @NotNull(message = "Diabetes_Status must be provided")
    private String diabetesStatus;
    //    @NotBlank(message = "Status must be provided")
//    @NotNull(message = "Status must be provided")
//    private String status;
    @NotNull(message = "Patient_UUID must be provided")
    private UUID patientUUID;
}
