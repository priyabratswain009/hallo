package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChecklistDocumentReferenceMapMapperTest {

    private ChecklistDocumentReferenceMapMapper checklistDocumentReferenceMapMapper;

    @BeforeEach
    public void setUp() {
        checklistDocumentReferenceMapMapper = new ChecklistDocumentReferenceMapMapperImpl();
    }
}
