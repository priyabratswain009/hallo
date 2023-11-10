package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepositMasterDetailsMapperTest {

    private DepositMasterDetailsMapper depositMasterDetailsMapper;

    @BeforeEach
    public void setUp() {
        depositMasterDetailsMapper = new DepositMasterDetailsMapperImpl();
    }
}
