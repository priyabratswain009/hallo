package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimProgramMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimProgramMasterAuditLog.class);
        ClaimProgramMasterAuditLog claimProgramMasterAuditLog1 = new ClaimProgramMasterAuditLog();
        claimProgramMasterAuditLog1.setId(1L);
        ClaimProgramMasterAuditLog claimProgramMasterAuditLog2 = new ClaimProgramMasterAuditLog();
        claimProgramMasterAuditLog2.setId(claimProgramMasterAuditLog1.getId());
        assertThat(claimProgramMasterAuditLog1).isEqualTo(claimProgramMasterAuditLog2);
        claimProgramMasterAuditLog2.setId(2L);
        assertThat(claimProgramMasterAuditLog1).isNotEqualTo(claimProgramMasterAuditLog2);
        claimProgramMasterAuditLog1.setId(null);
        assertThat(claimProgramMasterAuditLog1).isNotEqualTo(claimProgramMasterAuditLog2);
    }
}
