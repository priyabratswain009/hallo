package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PayerMasterMapperTest {

    private PayerMasterMapper payerMasterMapper;

    @BeforeEach
    public void setUp() {
        payerMasterMapper = new PayerMasterMapperImpl();
    }
}
