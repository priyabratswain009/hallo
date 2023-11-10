package com.sunknowledge.dme.rcm.service.dto.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FacilityMasterParameterDTO {
    private UUID facilityMasterUuid;
    @NotNull(message="Facility_Name must be provided.")
    @NotBlank(message="Facility_Name must be provided.")
    private String facilityName;
    @NotNull(message="NPI must be provided.")
    @NotBlank(message="NPI must be provided.")
    private String npi;

    private Long salesRepId;

    private String status;

    private Long facilityTypeId;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String zip;

    private String contactNo1;

    private String contactNo2;

    private String fax;

    private String efax;

    private String email;

    private String relationship;

    private String modeOfContact;
}
