package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParRequestDetailsMapperTest {

    private ParRequestDetailsMapper parRequestDetailsMapper;

    @BeforeEach
    public void setUp() {
        parRequestDetailsMapper = new ParRequestDetailsMapperImpl();
    }
}
