package com.sunknowledge.dme.rcm.service.dto.itemothersdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorMasterRejectedDTO {

    private String message;

    private String vendorName;

    private String status;

    private String vendorDescription;

    private String vendorNo;

    private String vendorAccountNo;

    private String defaultPoType;

    private String defaultShopType;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String zip;

    private String email;

    private String fax;

    private String efax;

    private String contactPersonName;

    private String contactNo1;

    private String contactNo2;
}
