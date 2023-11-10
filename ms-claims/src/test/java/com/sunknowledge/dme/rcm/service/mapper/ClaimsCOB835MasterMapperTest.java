package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClaimsCOB835MasterMapperTest {

    private ClaimsCOB835MasterMapper claimsCOB835MasterMapper;

    @BeforeEach
    public void setUp() {
        claimsCOB835MasterMapper = new ClaimsCOB835MasterMapperImpl();
    }
}
