package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemVendorMappingMapperTest {

    private ItemVendorMappingMapper itemVendorMappingMapper;

    @BeforeEach
    public void setUp() {
        itemVendorMappingMapper = new ItemVendorMappingMapperImpl();
    }
}
