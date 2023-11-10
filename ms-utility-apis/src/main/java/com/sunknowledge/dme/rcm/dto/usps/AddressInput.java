package com.sunknowledge.dme.rcm.dto.usps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressInput {
    private String firmName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String urbanization;
    private String zip5;
    private String zip4;
    private Long patientId;
}
