package com.sunknowledge.dme.rcm.service.dto.patiententry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerCompensationParameterDTO {
    private UUID workersCompensationUuid;

//    @NotNull(message = "Patient_Id must be provided.")
//    @Min(value=1, message="Patient_Id must be greater than or equal to 1")
//    private Long patientId;

    @NotNull(message = "Patient must be selected.")
    //@NotBlank(message = "Patient must be selected.")
    private UUID patientMasterUUID;

    @NotNull(message = "Insured_Employer must be provided.")
    @NotBlank(message = "Insured_Employer must be provided.")
    private String insuredEmployer;

    private Long workersCompensationPayerId;

    private Long workersCompensationPlanId;

    private String workersCompensationAdditionalDtls;

    private String workersCompensationClaimFillingCode;

    private String workersCompensationTplCode;

    private String workersCompensationTplName;

    private String wcPropertyCasualtyAgencyClaimNo;

    private Long wcCarrierId;

//    @NotNull(message = "Status must be provided.")
//    @NotBlank(message = "Status must be provided.")
//    private String status;

    @NotNull(message = "Employer_Address_Line_1 must be provided.")
    @NotBlank(message = "Employer_Address_Line_1 must be provided.")
    private String employerAddressLine1;

    private String employerAddressLine2;

    @NotNull(message = "Employer_City must be provided.")
    @NotBlank(message = "Employer_City must be provided.")
    private String employerCity;

    @NotNull(message = "Employer_State must be provided.")
    @NotBlank(message = "Employer_State must be provided.")
    private String employerState;

    @NotNull(message = "Employer_Country must be provided.")
    @NotBlank(message = "Employer_Country must be provided.")
    private String employerCountry;

    @NotNull(message = "Employer_Zip must be provided.")
    @NotBlank(message = "Employer_Zip must be provided.")
    private String employerZip;

    @NotNull(message = "Employer_Contact_No_1 must be provided.")
    @NotBlank(message = "Employer_Contact_No_1 must be provided.")
    private String employerContactNo1;

    private String employerContactNo2;

    private String employerFax;

    private String employerEfax;

    private String employerEmail;

    private String relationship;

    private String modeOfContact;
}
