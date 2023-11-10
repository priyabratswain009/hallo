package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParMasterMapperTest {

    private ParMasterMapper parMasterMapper;

    @BeforeEach
    public void setUp() {
        parMasterMapper = new ParMasterMapperImpl();
    }
}
