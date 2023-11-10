package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Claims835CrossoverProcessedMapperTest {

    private Claims835CrossoverProcessedMapper claims835CrossoverProcessedMapper;

    @BeforeEach
    public void setUp() {
        claims835CrossoverProcessedMapper = new Claims835CrossoverProcessedMapperImpl();
    }
}
