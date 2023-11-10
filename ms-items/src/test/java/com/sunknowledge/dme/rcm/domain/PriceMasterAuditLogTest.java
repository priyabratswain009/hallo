package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PriceMasterAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PriceMasterAuditLog.class);
        PriceMasterAuditLog priceMasterAuditLog1 = new PriceMasterAuditLog();
        priceMasterAuditLog1.setId(1L);
        PriceMasterAuditLog priceMasterAuditLog2 = new PriceMasterAuditLog();
        priceMasterAuditLog2.setId(priceMasterAuditLog1.getId());
        assertThat(priceMasterAuditLog1).isEqualTo(priceMasterAuditLog2);
        priceMasterAuditLog2.setId(2L);
        assertThat(priceMasterAuditLog1).isNotEqualTo(priceMasterAuditLog2);
        priceMasterAuditLog1.setId(null);
        assertThat(priceMasterAuditLog1).isNotEqualTo(priceMasterAuditLog2);
    }
}
