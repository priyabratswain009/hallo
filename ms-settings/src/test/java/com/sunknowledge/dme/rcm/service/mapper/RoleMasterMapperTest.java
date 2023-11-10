package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoleMasterMapperTest {

    private RoleMasterMapper roleMasterMapper;

    @BeforeEach
    public void setUp() {
        roleMasterMapper = new RoleMasterMapperImpl();
    }
}
