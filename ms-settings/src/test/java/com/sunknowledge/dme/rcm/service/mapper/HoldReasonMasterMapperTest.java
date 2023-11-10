package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HoldReasonMasterMapperTest {

    private HoldReasonMasterMapper holdReasonMasterMapper;

    @BeforeEach
    public void setUp() {
        holdReasonMasterMapper = new HoldReasonMasterMapperImpl();
    }
}
