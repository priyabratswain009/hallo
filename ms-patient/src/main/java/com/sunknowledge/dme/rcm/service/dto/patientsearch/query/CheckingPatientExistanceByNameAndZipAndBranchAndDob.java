package com.sunknowledge.dme.rcm.service.dto.patientsearch.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckingPatientExistanceByNameAndZipAndBranchAndDob {
    private String name;
    private LocalDate dob;
    private String zip;
    private String phone;
    private Long branchId;
}
