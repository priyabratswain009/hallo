package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClaimsCob835MasterMapperTest {

    private ClaimsCob835MasterMapper claimsCob835MasterMapper;

    @BeforeEach
    public void setUp() {
        claimsCob835MasterMapper = new ClaimsCob835MasterMapperImpl();
    }
}
