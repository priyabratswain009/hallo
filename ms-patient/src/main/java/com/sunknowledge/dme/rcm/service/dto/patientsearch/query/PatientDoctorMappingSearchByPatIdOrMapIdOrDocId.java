package com.sunknowledge.dme.rcm.service.dto.patientsearch.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDoctorMappingSearchByPatIdOrMapIdOrDocId {
    private Long patientDoctorMapId;

    private Long patientId;

    private String doctorNpiNumber;
}
