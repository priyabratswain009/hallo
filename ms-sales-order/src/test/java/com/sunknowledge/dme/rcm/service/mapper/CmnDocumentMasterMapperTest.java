package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CmnDocumentMasterMapperTest {

    private CmnDocumentMasterMapper cmnDocumentMasterMapper;

    @BeforeEach
    public void setUp() {
        cmnDocumentMasterMapper = new CmnDocumentMasterMapperImpl();
    }
}
