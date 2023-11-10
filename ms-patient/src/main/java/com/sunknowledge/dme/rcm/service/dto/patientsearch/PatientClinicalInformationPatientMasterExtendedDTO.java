package com.sunknowledge.dme.rcm.service.dto.patientsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientClinicalInformationPatientMasterExtendedDTO {
    private Long patientClinicalInformationId;

    private Double height;

    private Double weight;

    private String functionalAbilities;

    private LocalDate captureDate;

    private String infectionConditionStatus;

    private String diabetesStatus;

    private String status;

    private Long createdById;

    private LocalDate createdDate;

    private Long patientId;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private LocalDate updatedDate;

    private UUID patientClinicalInformationUuid;

    private String patientFirstName;

    private String patientMiddleName;

    private String patientLastName;

    private LocalDate dob;

    private LocalDate patientDod;

    private String gender;

    private String fullName;
}
