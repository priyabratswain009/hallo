package com.sunknowledge.dme.rcm.service.dto.patiententry.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavePatientDoctorMappingCommand {
    private Long patientDoctorMapId;

    private Long patientId;

    private Long doctorId;

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

    private UUID patientDoctorMapUuid;

    private String status;

}
