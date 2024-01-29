package com.sunknowledge.dme.rcm.dto.par;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewPARDetailsForEfaxDTO {
    private Long parId;
    private String parNo;
    private Long patientId;
    private String patientIdNumber;
    private String patientFirstName;
    private String patientLastName;
    private Long insuranceId;
    private String insuranceName;
    private String payerIdNo;
    private String payerLevel;
    private String policyNumber;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    private LocalDate date_of_contact;
    private String description;
    private LocalDate initialDate;
    private LocalDate effectiveStartDate;
    private LocalDate expirationDate;
    private String authorizedBy;
    private String logInStatus;
    private String renewalStatus;
    private LocalDate renewalDate;
    private String originalParNo;
    private String parStatus;
    private String extensionStatus;
    private LocalDate extensionApprovalDate;
    private String parIdNo;
    private String parRequestType;
    private String parRequestDocName;
    private String parRequestDocLocation;
    private String parRequestDetailsUuid;
    private Long soId;
    private String soNo;
    private String parUuid;
}
