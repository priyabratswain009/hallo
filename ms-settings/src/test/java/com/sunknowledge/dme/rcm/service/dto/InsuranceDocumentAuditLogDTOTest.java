package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceDocumentAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceDocumentAuditLogDTO.class);
        InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO1 = new InsuranceDocumentAuditLogDTO();
        insuranceDocumentAuditLogDTO1.setId(1L);
        InsuranceDocumentAuditLogDTO insuranceDocumentAuditLogDTO2 = new InsuranceDocumentAuditLogDTO();
        assertThat(insuranceDocumentAuditLogDTO1).isNotEqualTo(insuranceDocumentAuditLogDTO2);
        insuranceDocumentAuditLogDTO2.setId(insuranceDocumentAuditLogDTO1.getId());
        assertThat(insuranceDocumentAuditLogDTO1).isEqualTo(insuranceDocumentAuditLogDTO2);
        insuranceDocumentAuditLogDTO2.setId(2L);
        assertThat(insuranceDocumentAuditLogDTO1).isNotEqualTo(insuranceDocumentAuditLogDTO2);
        insuranceDocumentAuditLogDTO1.setId(null);
        assertThat(insuranceDocumentAuditLogDTO1).isNotEqualTo(insuranceDocumentAuditLogDTO2);
    }
}
