package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserMasterMapperTest {

    private UserMasterMapper userMasterMapper;

    @BeforeEach
    public void setUp() {
        userMasterMapper = new UserMasterMapperImpl();
    }
}
