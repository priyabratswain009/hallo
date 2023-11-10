package com.sunknowledge.dme.rcm.service.dto.patiententry.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavePatientClinicalDetailsCommand {
    private Long patientClinicalInformationId;
    private Double height;
    private Double weight;
    private String functionalAbilities;
    private LocalDate captureDate;
    private String infectionConditionStatus;
    private String diabetesStatus;
    private String status;
    private Long createdById;
    private Long patientId;
    private String createdByName;
    private String updatedByName;
    private Long updatedById;
    private UUID patientClinicalInformationUuid;
}
