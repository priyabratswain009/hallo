package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyParameterDTO {
    private Long companyId;

    private String companyName;

    private String primaryContactName;

    private String primaryContactCredential;

    private String contractFileUploadPath;

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

    private String companyBranchLogo;
}
