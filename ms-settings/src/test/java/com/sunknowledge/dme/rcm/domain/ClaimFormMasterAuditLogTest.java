package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClaimFormMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClaimFormMasterAuditLog.class);
        ClaimFormMasterAuditLog claimFormMasterAuditLog1 = new ClaimFormMasterAuditLog();
        claimFormMasterAuditLog1.setId(1L);
        ClaimFormMasterAuditLog claimFormMasterAuditLog2 = new ClaimFormMasterAuditLog();
        claimFormMasterAuditLog2.setId(claimFormMasterAuditLog1.getId());
        assertThat(claimFormMasterAuditLog1).isEqualTo(claimFormMasterAuditLog2);
        claimFormMasterAuditLog2.setId(2L);
        assertThat(claimFormMasterAuditLog1).isNotEqualTo(claimFormMasterAuditLog2);
        claimFormMasterAuditLog1.setId(null);
        assertThat(claimFormMasterAuditLog1).isNotEqualTo(claimFormMasterAuditLog2);
    }
}
