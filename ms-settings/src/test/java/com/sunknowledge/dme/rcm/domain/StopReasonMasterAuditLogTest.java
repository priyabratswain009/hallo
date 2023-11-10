package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StopReasonMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StopReasonMasterAuditLog.class);
        StopReasonMasterAuditLog stopReasonMasterAuditLog1 = new StopReasonMasterAuditLog();
        stopReasonMasterAuditLog1.setId(1L);
        StopReasonMasterAuditLog stopReasonMasterAuditLog2 = new StopReasonMasterAuditLog();
        stopReasonMasterAuditLog2.setId(stopReasonMasterAuditLog1.getId());
        assertThat(stopReasonMasterAuditLog1).isEqualTo(stopReasonMasterAuditLog2);
        stopReasonMasterAuditLog2.setId(2L);
        assertThat(stopReasonMasterAuditLog1).isNotEqualTo(stopReasonMasterAuditLog2);
        stopReasonMasterAuditLog1.setId(null);
        assertThat(stopReasonMasterAuditLog1).isNotEqualTo(stopReasonMasterAuditLog2);
    }
}
