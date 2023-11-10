package com.sunknowledge.dme.rcm.service.dto.patientsearch.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientSearchByPatientId {
    private Long patientId;
}
