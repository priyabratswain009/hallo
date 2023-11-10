package com.sunknowledge.dme.rcm.service.dto.patiententry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDoctorMappingOutputDTO {
    private Long doctorId;

    private String doctorEmail;
    private String doctorAddressCountry;
    private String doctorEfax;
    private Long patientDoctorMapId;

    private Long patientId;

    private Long createdById;

    private LocalDate createdDate;

    private String status;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private LocalDate updatedDate;

    private String doctorFirstName;

    private String doctorMiddleName;

    private String doctorLastName;

    private String doctorNameSuffix;

    private String doctorAddressLineI;

    private String doctorAddressLineIi;

    private String doctorAddressCity;

    private String doctorAddressState;

    private String doctorAddressZip;

    private String doctorContactI;

    private String doctorContactIi;

    private String doctorFax;

    private String doctorNpiNumber;

    private String doctorGender;

    private String doctorTaxonomyCode;

    private String doctorTaxonomyDescription;

    private String doctorPracticeState;

    private String doctorLicenseNumber;

    private UUID patientDoctorMapUuid;
}
