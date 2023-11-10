package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Transaction835MasterDetailsMapperTest {

    private Transaction835MasterDetailsMapper transaction835MasterDetailsMapper;

    @BeforeEach
    public void setUp() {
        transaction835MasterDetailsMapper = new Transaction835MasterDetailsMapperImpl();
    }
}
