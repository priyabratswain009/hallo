package com.sunknowledge.dme.rcm.service.dto.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemLocationParameterDTO {
    private Long itemLocationId;
    private String itemLocationName;
    private String description;
    private String contactFirstName;
    private String status;
    private String billingAddressLine1;
    private String billingAddressLine2;
    private String billingCity;
    private String billingState;
    private String billingCountry;
    private String billingZip;
    private String contactMiddleName;
    private String contactLastName;
    private String contactNo1;
    private String contactNo2;
    private String fax;
    private String email;
    private String billlingAddressCompanyName;
    private String deliveryAddressLine1;
    private String deliveryAddressLine2;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
}
