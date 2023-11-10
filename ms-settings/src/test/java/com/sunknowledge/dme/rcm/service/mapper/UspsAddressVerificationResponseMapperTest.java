package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UspsAddressVerificationResponseMapperTest {

    private UspsAddressVerificationResponseMapper uspsAddressVerificationResponseMapper;

    @BeforeEach
    public void setUp() {
        uspsAddressVerificationResponseMapper = new UspsAddressVerificationResponseMapperImpl();
    }
}
