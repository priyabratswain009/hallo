package com.sunknowledge.dme.rcm.service.dto.patientsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientSearchCombinedParameterDTO {
    @NotNull(message = "Patient Id Number should not be null")
    private String patientIdNo;

    @NotNull(message = "Branch Id should not be null")
    @Min(value=1, message="Branch_Id must be greater than and equals to 1.")
    private Long branchId;

    @NotNull(message = "Patient First Name should not be null")
    private String patientFirstName;

    @NotNull(message = "Patient Middle Name should not be null")
    private String patientMiddleName;

    @NotNull(message = "Patient Last Name should not be null")
    private String patientLastName;

    @NotNull(message = "Patient Date of Birth should not be null")
    private String pdob;

    @NotNull(message = "Patient SSN No should not be null")
    private String ssnNo;

    @NotNull(message = "Patient Address 1 should not be null")
    private String addressLine1;

    @NotNull(message = "Patient Address 2 should not be null")
    private String addressLine2;

    @NotNull(message = "Patient City should not be null")
    private String pcity;

    @NotNull(message = "Patient State should not be null")
    private String patientState;

    @NotNull(message = "Patient Zip code should not be null")
    private String pzip;

    @NotNull(message = "Patient Phone No should not be null")
    private String phone;
}

