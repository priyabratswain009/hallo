package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceMasterAuditLogDTO.class);
        InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO1 = new InsuranceMasterAuditLogDTO();
        insuranceMasterAuditLogDTO1.setId(1L);
        InsuranceMasterAuditLogDTO insuranceMasterAuditLogDTO2 = new InsuranceMasterAuditLogDTO();
        assertThat(insuranceMasterAuditLogDTO1).isNotEqualTo(insuranceMasterAuditLogDTO2);
        insuranceMasterAuditLogDTO2.setId(insuranceMasterAuditLogDTO1.getId());
        assertThat(insuranceMasterAuditLogDTO1).isEqualTo(insuranceMasterAuditLogDTO2);
        insuranceMasterAuditLogDTO2.setId(2L);
        assertThat(insuranceMasterAuditLogDTO1).isNotEqualTo(insuranceMasterAuditLogDTO2);
        insuranceMasterAuditLogDTO1.setId(null);
        assertThat(insuranceMasterAuditLogDTO1).isNotEqualTo(insuranceMasterAuditLogDTO2);
    }
}
