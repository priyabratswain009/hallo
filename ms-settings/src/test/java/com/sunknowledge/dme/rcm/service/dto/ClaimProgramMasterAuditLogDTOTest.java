package com.sunknowledge.dme.rcm.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimProgramMasterAuditLogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimProgramMasterAuditLogDTO.class);
        ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO1 = new ClaimProgramMasterAuditLogDTO();
        claimProgramMasterAuditLogDTO1.setId(1L);
        ClaimProgramMasterAuditLogDTO claimProgramMasterAuditLogDTO2 = new ClaimProgramMasterAuditLogDTO();
        assertThat(claimProgramMasterAuditLogDTO1).isNotEqualTo(claimProgramMasterAuditLogDTO2);
        claimProgramMasterAuditLogDTO2.setId(claimProgramMasterAuditLogDTO1.getId());
        assertThat(claimProgramMasterAuditLogDTO1).isEqualTo(claimProgramMasterAuditLogDTO2);
        claimProgramMasterAuditLogDTO2.setId(2L);
        assertThat(claimProgramMasterAuditLogDTO1).isNotEqualTo(claimProgramMasterAuditLogDTO2);
        claimProgramMasterAuditLogDTO1.setId(null);
        assertThat(claimProgramMasterAuditLogDTO1).isNotEqualTo(claimProgramMasterAuditLogDTO2);
    }
}
