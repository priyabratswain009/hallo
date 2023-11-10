package com.sunknowledge.dme.rcm.service.dto.patiententry.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveWorkerCompensationCommand {
    private Long workersCompensationId;

    private Long patientId;

    private String insuredEmployer;

    private Long workersCompensationPayerId;

    private Long workersCompensationPlanId;

    private String workersCompensationAdditionalDtls;

    private String workersCompensationClaimFillingCode;

    private String workersCompensationTplCode;

    private String workersCompensationTplName;

    private String wcPropertyCasualtyAgencyClaimNo;

    private Long wcCarrierId;

    private String status;

    private Long createdById;

    private String createdByName;

    private String updatedByName;

    private Long updatedById;

    private UUID workersCompensationUuid;

    private String employerAddressLine1;

    private String employerAddressLine2;

    private String employerCity;

    private String employerState;

    private String employerCountry;

    private String employerZip;

    private String employerContactNo1;

    private String employerContactNo2;

    private String employerFax;

    private String employerEfax;

    private String employerEmail;

    private String relationship;

    private String modeOfContact;
}
