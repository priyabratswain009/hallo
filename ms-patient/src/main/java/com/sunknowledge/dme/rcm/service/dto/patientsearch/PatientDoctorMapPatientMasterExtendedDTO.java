package com.sunknowledge.dme.rcm.service.dto.patientsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDoctorMapPatientMasterExtendedDTO {

    private Long patientDoctorMapId;

    private Long patientId;

    private Long createdById;

    private LocalDate createdDate;

    private String status;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID patientDoctorMapUuid;

    private LocalDate updatedDate;

    private String doctorFirstName;

    private String doctorMiddleName;

    private String doctorLastName;

    private String doctorNameSuffix;

    private String doctorAddressLine1;

    private String doctorAddressLine2;

    private String doctorAddressCity;

    private String doctorAddressState;

    private String doctorAddressZip;

    private String doctorContact1;

    private String doctorContact2;

    private String doctorFax;

    private String doctorNpiNumber;

    private String doctorGender;

    private String doctorTaxonomyCode;

    private String doctorTaxonomyDescription;

    private String doctorPracticeState;

    private String doctorLicenseNumber;

    private String patientFirstName;

    private String patientMiddleName;

    private String patientLastName;

    private LocalDate dob;

    private LocalDate patientDod;

    private String gender;

    private String fullName;
}
