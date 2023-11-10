package com.sunknowledge.dme.rcm.service.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesRepParameterDTO {
    private UUID salesRepUuid;
    @NotBlank(message = "FirstName must not be Blank.")
    @NotNull(message = "FirstName must not be Blank.")
    private String firstName;

    private String middleName;

    private String lastName;

    private String suffix;

    private String status;

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

    private String salesRepNo;
}
