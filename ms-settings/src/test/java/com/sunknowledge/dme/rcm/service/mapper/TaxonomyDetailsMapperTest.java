package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaxonomyDetailsMapperTest {

    private TaxonomyDetailsMapper taxonomyDetailsMapper;

    @BeforeEach
    public void setUp() {
        taxonomyDetailsMapper = new TaxonomyDetailsMapperImpl();
    }
}
