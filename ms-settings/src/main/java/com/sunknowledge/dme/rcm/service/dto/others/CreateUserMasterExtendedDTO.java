package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserMasterExtendedDTO {
    private String firstName;

    private String middleName;

    private String lastName;

    private LocalDate dob;

    private Long age;

    private String gender;

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

    private String jobTitle;

    private String modeOfContact;

    private Boolean isDeactivate;

    private String username;

    private String password;

    private UUID roleUUID;
}
