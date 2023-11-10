package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimFormMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimFormMasterAuditLogDTO.class);
        ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO1 = new ClaimFormMasterAuditLogDTO();
        claimFormMasterAuditLogDTO1.setId(1L);
        ClaimFormMasterAuditLogDTO claimFormMasterAuditLogDTO2 = new ClaimFormMasterAuditLogDTO();
        assertThat(claimFormMasterAuditLogDTO1).isNotEqualTo(claimFormMasterAuditLogDTO2);
        claimFormMasterAuditLogDTO2.setId(claimFormMasterAuditLogDTO1.getId());
        assertThat(claimFormMasterAuditLogDTO1).isEqualTo(claimFormMasterAuditLogDTO2);
        claimFormMasterAuditLogDTO2.setId(2L);
        assertThat(claimFormMasterAuditLogDTO1).isNotEqualTo(claimFormMasterAuditLogDTO2);
        claimFormMasterAuditLogDTO1.setId(null);
        assertThat(claimFormMasterAuditLogDTO1).isNotEqualTo(claimFormMasterAuditLogDTO2);
    }
}
