package com.sunknowledge.dme.rcm.service.dto.itemothersdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerRejectedDTO {

    private String message;

    private String manufacturerName;

    private String status;

    private String contactPersonName;

    private String webUrl;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String zip;

    private String email;

    private String fax;

    private String contactNo1;

    private String contactNo2;

    private String efax;

    private String manufacturerNo;
}
