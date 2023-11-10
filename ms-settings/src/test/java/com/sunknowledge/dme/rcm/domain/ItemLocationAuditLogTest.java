package com.sunknowledge.dme.rcm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sunknowledge.dme.rcm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ItemLocationAuditLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemLocationAuditLog.class);
        ItemLocationAuditLog itemLocationAuditLog1 = new ItemLocationAuditLog();
        itemLocationAuditLog1.setId(1L);
        ItemLocationAuditLog itemLocationAuditLog2 = new ItemLocationAuditLog();
        itemLocationAuditLog2.setId(itemLocationAuditLog1.getId());
        assertThat(itemLocationAuditLog1).isEqualTo(itemLocationAuditLog2);
        itemLocationAuditLog2.setId(2L);
        assertThat(itemLocationAuditLog1).isNotEqualTo(itemLocationAuditLog2);
        itemLocationAuditLog1.setId(null);
        assertThat(itemLocationAuditLog1).isNotEqualTo(itemLocationAuditLog2);
    }
}
