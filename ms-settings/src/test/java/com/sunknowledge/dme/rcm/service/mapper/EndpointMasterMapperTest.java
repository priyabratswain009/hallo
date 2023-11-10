package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EndpointMasterMapperTest {

    private EndpointMasterMapper endpointMasterMapper;

    @BeforeEach
    public void setUp() {
        endpointMasterMapper = new EndpointMasterMapperImpl();
    }
}
