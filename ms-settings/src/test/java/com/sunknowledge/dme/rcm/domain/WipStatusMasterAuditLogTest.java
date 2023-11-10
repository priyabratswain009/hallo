package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WipStatusMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WipStatusMasterAuditLog.class);
        WipStatusMasterAuditLog wipStatusMasterAuditLog1 = new WipStatusMasterAuditLog();
        wipStatusMasterAuditLog1.setId(1L);
        WipStatusMasterAuditLog wipStatusMasterAuditLog2 = new WipStatusMasterAuditLog();
        wipStatusMasterAuditLog2.setId(wipStatusMasterAuditLog1.getId());
        assertThat(wipStatusMasterAuditLog1).isEqualTo(wipStatusMasterAuditLog2);
        wipStatusMasterAuditLog2.setId(2L);
        assertThat(wipStatusMasterAuditLog1).isNotEqualTo(wipStatusMasterAuditLog2);
        wipStatusMasterAuditLog1.setId(null);
        assertThat(wipStatusMasterAuditLog1).isNotEqualTo(wipStatusMasterAuditLog2);
    }
}
