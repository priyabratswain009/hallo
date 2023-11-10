package com.sunknowledge.dme.rcm.service.dto.patiententry;

import com.sunknowledge.dme.rcm.commonconstant.RegexConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDoctorMappingParameterDTO {
    private UUID patientDoctorMapUuid;

    @NotNull(message = "Patient_UUID must be provided.")
    private UUID patientUUID;

    @NotNull(message = "Valid Doctor_NPI must be provided.")
    @NotBlank(message = "Valid Doctor_NPI must be provided.")
    @Pattern(regexp = RegexConstant.NUMERIC_REGEX, message = "Doctor_NPI must be 10 digit numeric value.")
    private String doctorNpiNumber;
}
