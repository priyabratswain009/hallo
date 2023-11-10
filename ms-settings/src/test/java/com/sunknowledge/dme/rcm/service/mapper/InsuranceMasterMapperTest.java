package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InsuranceMasterMapperTest {

    private InsuranceMasterMapper insuranceMasterMapper;

    @BeforeEach
    public void setUp() {
        insuranceMasterMapper = new InsuranceMasterMapperImpl();
    }
}
