package com.sunknowledge.dme.rcm.dto.usps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfoDTO {
    private Long patientId;
    private String reqAddressLine1;
    private String reqAddressLine2;
    private String reqCity;
    private String reqState;
    private String reqZip4;
    private String reqZip5;
    private LocalDate reqDate;
    private String rspAddressLine1;
    private String rspAddressLine2;
    private String rspCity;
    private String rspState;
    private String rspZip4;
    private String rspZip5;
    private LocalDate rspDate;
    private String dpvFootnotesCode;
    private String dpvFootnotesDescription;
    private String status;
}
