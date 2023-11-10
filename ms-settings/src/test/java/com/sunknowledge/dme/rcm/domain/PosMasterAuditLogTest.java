package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PosMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PosMasterAuditLog.class);
        PosMasterAuditLog posMasterAuditLog1 = new PosMasterAuditLog();
        posMasterAuditLog1.setId(1L);
        PosMasterAuditLog posMasterAuditLog2 = new PosMasterAuditLog();
        posMasterAuditLog2.setId(posMasterAuditLog1.getId());
        assertThat(posMasterAuditLog1).isEqualTo(posMasterAuditLog2);
        posMasterAuditLog2.setId(2L);
        assertThat(posMasterAuditLog1).isNotEqualTo(posMasterAuditLog2);
        posMasterAuditLog1.setId(null);
        assertThat(posMasterAuditLog1).isNotEqualTo(posMasterAuditLog2);
    }
}
