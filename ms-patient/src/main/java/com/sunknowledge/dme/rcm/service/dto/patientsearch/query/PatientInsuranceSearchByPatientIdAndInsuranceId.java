package com.sunknowledge.dme.rcm.service.dto.patientsearch.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientInsuranceSearchByPatientIdAndInsuranceId {
    private Long patientID;
    private Long patInsuranceId;
}
