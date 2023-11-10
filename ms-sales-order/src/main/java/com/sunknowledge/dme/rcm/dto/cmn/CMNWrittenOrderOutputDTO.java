package com.sunknowledge.dme.rcm.dto.cmn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CMNWrittenOrderOutputDTO {
    private Long cmnId;
    //private LocalDate patient_dob;
    private String providerName;
    private String providerAddress;
    private String providerPhone;
    private String providerFax;
    private String providerDocId;
    private String patientName;
    private String patientAddress;
    private String patientPhone;
    private String patientDob;
    private String patientPolicy;
    private String physicianName;
    private String physicianAddress;
    private String physicianLicense;
    private String physicianPhone;
    private String physicianNpi;
    private String physicianFax;
    private String orderInitialDate;
    private String orderRevisedDate;
    private String orderRecertificationDate;
    private String orderLengthOfNeed;
    private String orderPrognosis;
    private String refillAuthorized;
    private List<Diagnosis> diagnosis;
    private List<EquipmentDetailsForCMNDTO> equipmentDetails;
    private String addNote;
    private String date;
}
