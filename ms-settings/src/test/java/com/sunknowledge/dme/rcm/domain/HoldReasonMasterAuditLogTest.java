package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HoldReasonMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HoldReasonMasterAuditLog.class);
        HoldReasonMasterAuditLog holdReasonMasterAuditLog1 = new HoldReasonMasterAuditLog();
        holdReasonMasterAuditLog1.setId(1L);
        HoldReasonMasterAuditLog holdReasonMasterAuditLog2 = new HoldReasonMasterAuditLog();
        holdReasonMasterAuditLog2.setId(holdReasonMasterAuditLog1.getId());
        assertThat(holdReasonMasterAuditLog1).isEqualTo(holdReasonMasterAuditLog2);
        holdReasonMasterAuditLog2.setId(2L);
        assertThat(holdReasonMasterAuditLog1).isNotEqualTo(holdReasonMasterAuditLog2);
        holdReasonMasterAuditLog1.setId(null);
        assertThat(holdReasonMasterAuditLog1).isNotEqualTo(holdReasonMasterAuditLog2);
    }
}
