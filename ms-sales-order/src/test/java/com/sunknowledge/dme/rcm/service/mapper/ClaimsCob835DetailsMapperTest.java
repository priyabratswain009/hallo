package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClaimsCob835DetailsMapperTest {

    private ClaimsCob835DetailsMapper claimsCob835DetailsMapper;

    @BeforeEach
    public void setUp() {
        claimsCob835DetailsMapper = new ClaimsCob835DetailsMapperImpl();
    }
}
