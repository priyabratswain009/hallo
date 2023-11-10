package com.sunknowledge.dme.rcm.service.dto.patientsearch.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientSearchByBasicInfoOrAddressOrBranch {
    private Long patientID;
    private String name;
    private LocalDate dob;
    private String ssnNo;
    //    private String addressLine1;
//    private String addressLine2;
//    private String city;
//    private String sate;
//    private String country;
//    private String postalCode;
    private String address;
    private String phone;
    private Long branchId;
    private String branchName;
}
