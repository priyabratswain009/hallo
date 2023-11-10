package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceGroupMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceGroupMasterAuditLogDTO.class);
        InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO1 = new InsuranceGroupMasterAuditLogDTO();
        insuranceGroupMasterAuditLogDTO1.setId(1L);
        InsuranceGroupMasterAuditLogDTO insuranceGroupMasterAuditLogDTO2 = new InsuranceGroupMasterAuditLogDTO();
        assertThat(insuranceGroupMasterAuditLogDTO1).isNotEqualTo(insuranceGroupMasterAuditLogDTO2);
        insuranceGroupMasterAuditLogDTO2.setId(insuranceGroupMasterAuditLogDTO1.getId());
        assertThat(insuranceGroupMasterAuditLogDTO1).isEqualTo(insuranceGroupMasterAuditLogDTO2);
        insuranceGroupMasterAuditLogDTO2.setId(2L);
        assertThat(insuranceGroupMasterAuditLogDTO1).isNotEqualTo(insuranceGroupMasterAuditLogDTO2);
        insuranceGroupMasterAuditLogDTO1.setId(null);
        assertThat(insuranceGroupMasterAuditLogDTO1).isNotEqualTo(insuranceGroupMasterAuditLogDTO2);
    }
}
