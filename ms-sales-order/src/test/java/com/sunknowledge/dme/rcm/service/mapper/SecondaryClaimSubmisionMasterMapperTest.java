package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SecondaryClaimSubmisionMasterMapperTest {

    private SecondaryClaimSubmisionMasterMapper secondaryClaimSubmisionMasterMapper;

    @BeforeEach
    public void setUp() {
        secondaryClaimSubmisionMasterMapper = new SecondaryClaimSubmisionMasterMapperImpl();
    }
}
