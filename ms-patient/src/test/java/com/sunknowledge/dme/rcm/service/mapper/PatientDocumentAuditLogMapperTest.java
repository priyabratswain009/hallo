package com.sunknowledge.dme.rcm.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PatientDocumentAuditLogMapperTest {

    private PatientDocumentAuditLogMapper patientDocumentAuditLogMapper;

    @BeforeEach
    public void setUp() {
        patientDocumentAuditLogMapper = new PatientDocumentAuditLogMapperImpl();
    }
}
