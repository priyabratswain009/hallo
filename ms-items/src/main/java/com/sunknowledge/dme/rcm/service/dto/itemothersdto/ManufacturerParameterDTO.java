package com.sunknowledge.dme.rcm.service.dto.itemothersdto;

import com.sunknowledge.dme.rcm.commonconstant.RegexConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManufacturerParameterDTO {
    private Long manufacturerId;
    @NotNull(message="Manufacturer_Name must be provided.")
    @NotBlank(message="Manufacturer_Name must be provided.")
    private String manufacturerName;
    private String status;
    private String contactPersonName;
    private String webUrl;
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
    private String contactNo1;
    private String contactNo2;
    private String efax;
//    @NotNull(message="Manufacturer_No must be provided.")
//    @NotBlank(message="Manufacturer_No must be provided.")
//    @Pattern(regexp = RegexConstant.ALPHANUMERIC_REGEX,
//        message = "Provide only appropriate ManufacturerNo")
//    private String manufacturerNo;
}
