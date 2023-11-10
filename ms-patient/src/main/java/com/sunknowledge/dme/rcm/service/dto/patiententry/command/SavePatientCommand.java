package com.sunknowledge.dme.rcm.service.dto.patiententry.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavePatientCommand {
    private Long patientId;
    //    private String patientName;
    private String patientFirstName;
    private String patientMiddleName;
    private String patientLastName;
    private LocalDate dob;
    private String gender;
    private String ssn;
    private Long addressTypeId;
    private Long taxZoneId;
    private Double discountPercent;
    private Long posId;
    private String priorSystemKey;
//    private String status;
    private Long createdById;
    private Long branchId;
    private String createdByName;
    private String updatedByName;
    private Long updatedById;
    private UUID patientMasterUuid;
    private String patientIdNumber;
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
//    private String relationship;
    private String modeOfContact;
    private String branchName;
    private String billingAddressLine1;
    private String billingAddressLine2;
    private String billingAddressCity;
    private String billingAddressState;
    private String billingAddressZip;
    private String caregiverName;
    private String caregiverContact;
    private String caregiverRelatinshipPatient;
    private String taxZoneName;
    private Double taxRate;
    private LocalDate patientDod;
}
