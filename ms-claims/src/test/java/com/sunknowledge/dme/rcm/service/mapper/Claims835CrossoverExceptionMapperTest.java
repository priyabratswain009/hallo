package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Claims835CrossoverExceptionMapperTest {

    private Claims835CrossoverExceptionMapper claims835CrossoverExceptionMapper;

    @BeforeEach
    public void setUp() {
        claims835CrossoverExceptionMapper = new Claims835CrossoverExceptionMapperImpl();
    }
}
