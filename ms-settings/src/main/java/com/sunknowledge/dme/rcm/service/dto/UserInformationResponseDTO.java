package com.sunknowledge.dme.rcm.service.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.sunknowledge.dme.rcm.service.impl.others.Scenarios;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInformationResponseDTO {

    private Long userId;
    @JsonView({Scenarios.WithOutPassword.class, Scenarios.WithToken.class})
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private Long age;
    private String gender;
    private String status;
    private Long createdById;
    private LocalDate createdDate;
    private String createdByName;
    private String updatedByName;
    private Long updatedById;
    private LocalDate updatedDate;
    private UUID userMasterUuid;
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
    private String jobTitle;
    private String modeOfContact;
    private Boolean isDeactivate;
    private String username;
    @JsonView(Scenarios.WithPassword.class)
    private String password;
    @JsonView(Scenarios.WithToken.class)
    private String keyCloakToken;
}
