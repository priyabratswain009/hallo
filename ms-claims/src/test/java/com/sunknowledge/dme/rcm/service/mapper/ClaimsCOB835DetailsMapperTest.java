package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClaimsCOB835DetailsMapperTest {

    private ClaimsCOB835DetailsMapper claimsCOB835DetailsMapper;

    @BeforeEach
    public void setUp() {
        claimsCOB835DetailsMapper = new ClaimsCOB835DetailsMapperImpl();
    }
}
