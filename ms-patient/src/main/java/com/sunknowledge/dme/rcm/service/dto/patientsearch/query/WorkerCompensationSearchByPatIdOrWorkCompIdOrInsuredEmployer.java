package com.sunknowledge.dme.rcm.service.dto.patientsearch.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerCompensationSearchByPatIdOrWorkCompIdOrInsuredEmployer {
    private Long workersCompensationId;

    private Long patientId;

    private String insuredEmployer;
}
