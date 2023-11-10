package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClaimProgramMasterMapperTest {

    private ClaimProgramMasterMapper claimProgramMasterMapper;

    @BeforeEach
    public void setUp() {
        claimProgramMasterMapper = new ClaimProgramMasterMapperImpl();
    }
}
