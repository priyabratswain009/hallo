package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PatientInsuranceMapperTest {

    private PatientInsuranceMapper patientInsuranceMapper;

    @BeforeEach
    public void setUp() {
        patientInsuranceMapper = new PatientInsuranceMapperImpl();
    }
}