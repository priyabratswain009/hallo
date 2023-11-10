package com.sunknowledge.dme.rcm.service.dto.itemothersdto;

import com.sunknowledge.dme.rcm.commonconstant.RegexConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorMasterParameterDTO {
    private Long vendorId;
    @NotNull(message="Vendor_Name must be provided.")
    @NotBlank(message="Vendor_Name must be provided.")
    private String vendorName;
    private String status;
    private String vendorDescription;
//    @NotNull(message="Vendor_No must be provided.")
//    @NotBlank(message="Vendor_No must be provided.")
//    private String vendorNo;
    private String vendorAccountNo;
    private String defaultPoType;
    private String defaultShopType;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    @NotBlank(message = "Email must be provided")
    @NotNull(message = "Email must be provided")
    @Pattern(regexp = RegexConstant.EMAIL_REGEX,
        message = "Provide only appropriate Email")
    private String email;
    private String fax;
    private String efax;
    private String contactPersonName;
    private String contactNo1;
    private String contactNo2;
}
