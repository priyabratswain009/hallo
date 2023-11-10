package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemMasterMapperTest {

    private ItemMasterMapper itemMasterMapper;

    @BeforeEach
    public void setUp() {
        itemMasterMapper = new ItemMasterMapperImpl();
    }
}
