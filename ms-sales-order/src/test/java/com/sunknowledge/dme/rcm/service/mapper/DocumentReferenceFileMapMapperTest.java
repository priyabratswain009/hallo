package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DocumentReferenceFileMapMapperTest {

    private DocumentReferenceFileMapMapper documentReferenceFileMapMapper;

    @BeforeEach
    public void setUp() {
        documentReferenceFileMapMapper = new DocumentReferenceFileMapMapperImpl();
    }
}
