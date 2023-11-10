package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ManufacturerAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManufacturerAuditLog.class);
        ManufacturerAuditLog manufacturerAuditLog1 = new ManufacturerAuditLog();
        manufacturerAuditLog1.setId(1L);
        ManufacturerAuditLog manufacturerAuditLog2 = new ManufacturerAuditLog();
        manufacturerAuditLog2.setId(manufacturerAuditLog1.getId());
        assertThat(manufacturerAuditLog1).isEqualTo(manufacturerAuditLog2);
        manufacturerAuditLog2.setId(2L);
        assertThat(manufacturerAuditLog1).isNotEqualTo(manufacturerAuditLog2);
        manufacturerAuditLog1.setId(null);
        assertThat(manufacturerAuditLog1).isNotEqualTo(manufacturerAuditLog2);
    }
}
