package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemSerialNumberMapperTest {

    private ItemSerialNumberMapper itemSerialNumberMapper;

    @BeforeEach
    public void setUp() {
        itemSerialNumberMapper = new ItemSerialNumberMapperImpl();
    }
}
